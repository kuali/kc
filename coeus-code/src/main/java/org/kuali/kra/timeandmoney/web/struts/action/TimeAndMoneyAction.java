/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.timeandmoney.web.struts.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.ojb.broker.accesslayer.LookupException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.sys.framework.controller.KcTransactionalDocumentActionBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.award.AwardAmountInfoService;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyService;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.paymentreports.awardreports.reporting.service.ReportTrackingService;
import org.kuali.kra.award.timeandmoney.AwardDirectFandADistribution;
import org.kuali.kra.award.version.service.AwardVersionService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.timeandmoney.TimeAndMoneyForm;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.history.TransactionDetail;
import org.kuali.kra.timeandmoney.service.ActivePendingTransactionsService;
import org.kuali.kra.timeandmoney.service.TimeAndMoneyActionSummaryService;
import org.kuali.kra.timeandmoney.service.TimeAndMoneyHistoryService;
import org.kuali.kra.timeandmoney.service.TimeAndMoneyVersionService;
import org.kuali.kra.timeandmoney.transactions.AwardAmountTransaction;
import org.kuali.kra.timeandmoney.transactions.PendingTransaction;
import org.kuali.kra.timeandmoney.transactions.TransactionRuleImpl;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.document.authorization.DocumentAuthorizer;
import org.kuali.rice.kns.document.authorization.DocumentPresentationController;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.ParseException;
import java.sql.Date;
import java.util.*;
import java.util.Map.Entry;

public class TimeAndMoneyAction extends KcTransactionalDocumentActionBase {
    
    private static final String OBLIGATED_START_COMMENT = "Obligated Start";
    private static final String OBLIGATED_END_COMMENT = "Obligated End";
    private static final String PROJECT_END_COMMENT = "Project End";
    private static final Integer TEN = 10;
    BusinessObjectService businessObjectService;
    AwardVersionService awardVersionService;
    private ParameterService parameterService;
    TransactionRuleImpl transactionRuleImpl;
    private ActivePendingTransactionsService activePendingTransactionsService;
    private TimeAndMoneyVersionService timeAndMoneyVersionService;
    
    /**
     * @see org.kuali.coeus.sys.framework.controller.KcTransactionalDocumentActionBase#save(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        captureDateChangeTransactions(form);
        captureSingleNodeMoneyTransactions(mapping, form, request, response);
        forward = super.save(mapping, form, request, response);
        return forward;
    }
    
    private void captureSingleNodeMoneyTransactions(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyForm.getTimeAndMoneyDocument();
        AwardAmountInfoService awardAmountInfoService = KcServiceLocator.getService(AwardAmountInfoService.class);
        List<TransactionDetail> moneyTransactionDetailItems = new ArrayList<TransactionDetail>();
        updateAwardAmountTransactions(timeAndMoneyDocument);
        // Capture amount changes in hierarchy view
        if (timeAndMoneyDocument.getAwardHierarchyNodes().size() == 1) {
            for(Entry<String, AwardHierarchyNode> awardHierarchyNode : timeAndMoneyDocument.getAwardHierarchyNodes().entrySet()){
                //Award award = aptService.getWorkingAwardVersion(awardHierarchyNode.getValue().getAwardNumber());
                Award award = getAwardVersionService().getWorkingAwardVersion(awardHierarchyNode.getValue().getAwardNumber());
                AwardAmountInfo aai = awardAmountInfoService.fetchAwardAmountInfoWithHighestTransactionId(award.getAwardAmountInfos());
                boolean refreshNeeded = inspectAndCaptureAmountChanges(timeAndMoneyForm, aai, award, timeAndMoneyDocument, awardHierarchyNode.getValue(), moneyTransactionDetailItems);
                getBusinessObjectService().save(award);
                getBusinessObjectService().save(moneyTransactionDetailItems);
                timeAndMoneyDocument.getAward().refreshReferenceObject("awardAmountInfos");
                if (refreshNeeded) {
                    refreshView(mapping, timeAndMoneyForm, request, response);
                }
            }
        }   
    }
    
    private boolean inspectAndCaptureAmountChanges(TimeAndMoneyForm timeAndMoneyForm, AwardAmountInfo aai, Award award, TimeAndMoneyDocument timeAndMoneyDocument, 
                                                            AwardHierarchyNode awardHierarchyNode,  List<TransactionDetail> moneyTransactionDetailItems) {
        if(isDirectIndirectViewEnabled()){
            return createAndValidateEnabledViewTransaction(timeAndMoneyForm, aai, award, timeAndMoneyDocument, awardHierarchyNode, moneyTransactionDetailItems);
        } else {
            return createAndValidateDisabledViewTransaction(timeAndMoneyForm, aai, award, timeAndMoneyDocument, awardHierarchyNode, moneyTransactionDetailItems);
        }
    }
    
    private boolean createAndValidateEnabledViewTransaction(TimeAndMoneyForm timeAndMoneyForm, AwardAmountInfo aai, Award award, 
                                                           TimeAndMoneyDocument timeAndMoneyDocument, AwardHierarchyNode ahn, List<TransactionDetail> moneyTransactionDetailItems) {
        boolean result = false;  // assume no change to totals
        AwardHierarchyNode awardHierarchyNode = timeAndMoneyForm.getAwardHierarchyNodeItems().get(1);
        transactionRuleImpl = new TransactionRuleImpl();
        PendingTransaction pendingTransaction = new PendingTransaction();
        pendingTransaction.setComments("Single Node Money Transaction");
        // total up "current values" from transactions against current values
        KualiDecimal currentObligatedDirect = aai.getObligatedTotalDirect();
        KualiDecimal currentObligatedIndirect = aai.getObligatedTotalIndirect();
        KualiDecimal currentAnticipatedDirect = aai.getAnticipatedTotalDirect();
        KualiDecimal currentAnticipatedIndirect = aai.getAnticipatedTotalIndirect();
        for(PendingTransaction penTran : timeAndMoneyDocument.getPendingTransactions()) {
            // if incoming transaction
            if (StringUtils.equalsIgnoreCase(penTran.getSourceAwardNumber(),Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT)){
                currentObligatedDirect = currentObligatedDirect.add(penTran.getObligatedDirectAmount());
                currentObligatedIndirect = currentObligatedIndirect.add(penTran.getObligatedIndirectAmount());
                currentAnticipatedDirect = currentAnticipatedDirect.add(penTran.getAnticipatedDirectAmount());
                currentAnticipatedIndirect = currentAnticipatedIndirect.add(penTran.getAnticipatedIndirectAmount());
            } else if (StringUtils.equalsIgnoreCase(penTran.getDestinationAwardNumber(),Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT)){
                currentObligatedDirect = currentObligatedDirect.subtract(penTran.getObligatedDirectAmount());
                currentObligatedIndirect = currentObligatedIndirect.subtract(penTran.getObligatedIndirectAmount());
                currentAnticipatedDirect = currentAnticipatedDirect.subtract(penTran.getAnticipatedDirectAmount());
                currentAnticipatedIndirect = currentAnticipatedIndirect.subtract(penTran.getAnticipatedIndirectAmount());
            }
        }
        if(!awardHierarchyNode.getObligatedTotalDirect().equals(currentObligatedDirect)|| 
                !awardHierarchyNode.getObligatedTotalIndirect().equals(currentObligatedIndirect) ||
                    !awardHierarchyNode.getAnticipatedTotalDirect().equals(currentAnticipatedDirect) ||
                        !awardHierarchyNode.getAnticipatedTotalIndirect().equals(currentAnticipatedIndirect)){
            KualiDecimal obligatedChangeDirect = awardHierarchyNode.getObligatedTotalDirect().subtract(currentObligatedDirect);
            KualiDecimal obligatedChangeIndirect = awardHierarchyNode.getObligatedTotalIndirect().subtract(currentObligatedIndirect);
            KualiDecimal anticipatedChangeDirect = awardHierarchyNode.getAnticipatedTotalDirect().subtract(currentAnticipatedDirect);
            KualiDecimal anticipatedChangeIndirect = awardHierarchyNode.getAnticipatedTotalIndirect().subtract(currentAnticipatedIndirect);
            if(transactionRuleImpl.processParameterEnabledRules(awardHierarchyNode, aai, timeAndMoneyDocument)){
                List<Award> awardItems = new ArrayList<Award>();
                awardItems.add(award);
            
                if (obligatedChangeDirect.isGreaterThan(new KualiDecimal(0))) {
                    pendingTransaction.setSourceAwardNumber(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT);
                    pendingTransaction.setDestinationAwardNumber(award.getAwardNumber());
                }else if (obligatedChangeDirect.isLessThan(new KualiDecimal(0))){
                    pendingTransaction.setSourceAwardNumber(award.getAwardNumber());
                    pendingTransaction.setDestinationAwardNumber(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT);
                }
                if (obligatedChangeIndirect.isGreaterThan(new KualiDecimal(0))) {
                    pendingTransaction.setSourceAwardNumber(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT);
                    pendingTransaction.setDestinationAwardNumber(award.getAwardNumber());
                }else if (obligatedChangeIndirect.isLessThan(new KualiDecimal(0))){
                    pendingTransaction.setSourceAwardNumber(award.getAwardNumber());
                    pendingTransaction.setDestinationAwardNumber(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT);
                }
                if (anticipatedChangeDirect.isGreaterThan(new KualiDecimal(0))) {
                    pendingTransaction.setSourceAwardNumber(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT);
                    pendingTransaction.setDestinationAwardNumber(award.getAwardNumber());
                }else if (anticipatedChangeDirect.isLessThan(new KualiDecimal(0))){
                    pendingTransaction.setSourceAwardNumber(award.getAwardNumber());
                    pendingTransaction.setDestinationAwardNumber(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT);
                }
                if (anticipatedChangeIndirect.isGreaterThan(new KualiDecimal(0))) {
                    pendingTransaction.setSourceAwardNumber(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT);
                    pendingTransaction.setDestinationAwardNumber(award.getAwardNumber());
                }else if (anticipatedChangeIndirect.isLessThan(new KualiDecimal(0))){
                    pendingTransaction.setSourceAwardNumber(award.getAwardNumber());
                    pendingTransaction.setDestinationAwardNumber(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT);
                }
                pendingTransaction.setObligatedDirectAmount(obligatedChangeDirect.abs());
                pendingTransaction.setObligatedIndirectAmount(obligatedChangeIndirect.abs());
                pendingTransaction.setAnticipatedDirectAmount(anticipatedChangeDirect.abs());
                pendingTransaction.setAnticipatedIndirectAmount(anticipatedChangeIndirect.abs());
                pendingTransaction.setObligatedAmount((obligatedChangeDirect.add(obligatedChangeIndirect)).abs());
                pendingTransaction.setAnticipatedAmount((anticipatedChangeDirect.add(anticipatedChangeIndirect)).abs());
                pendingTransaction.setDocumentNumber(timeAndMoneyDocument.getDocumentNumber());
                pendingTransaction.setProcessedFlag(false);
                pendingTransaction.setSingleNodeTransaction(true);
                pendingTransaction.setDocumentNumber(timeAndMoneyDocument.getDocumentNumber());
                timeAndMoneyDocument.getPendingTransactions().add(pendingTransaction);
                for(PendingTransaction penTran : timeAndMoneyDocument.getPendingTransactions()) {
                    penTran.setDocumentNumber(timeAndMoneyDocument.getDocumentNumber());
                }
                getBusinessObjectService().save(timeAndMoneyDocument.getPendingTransactions());//need pending transaction to have a primarykey value
                timeAndMoneyForm.setToPendingView();
                result = true;
            }else {
                ahn.setAmountObligatedToDate(aai.getAmountObligatedToDate().add((obligatedChangeDirect).add(obligatedChangeIndirect)));
                ahn.setObligatedTotalDirect(awardHierarchyNode.getObligatedTotalDirect());
                ahn.setObligatedTotalIndirect(awardHierarchyNode.getObligatedTotalIndirect());
                ahn.setObliDistributableAmount(awardHierarchyNode.getObliDistributableAmount());
                ahn.setAnticipatedTotalAmount(aai.getAnticipatedTotalAmount().add((anticipatedChangeDirect).add(anticipatedChangeIndirect)));
                ahn.setAnticipatedTotalDirect(awardHierarchyNode.getAnticipatedTotalDirect());
                ahn.setAnticipatedTotalIndirect(awardHierarchyNode.getAnticipatedTotalIndirect());
                ahn.setAntDistributableAmount(awardHierarchyNode.getAntDistributableAmount());
            }
        }
        return result;
    }
    
    private boolean createAndValidateDisabledViewTransaction(TimeAndMoneyForm timeAndMoneyForm, AwardAmountInfo aai, Award award,
                                                            TimeAndMoneyDocument timeAndMoneyDocument, AwardHierarchyNode ahn, List<TransactionDetail> moneyTransactionDetailItems) {
        boolean result = false;  // assume no change to totals
        AwardHierarchyNode awardHierarchyNode = timeAndMoneyForm.getAwardHierarchyNodeItems().get(1);
        transactionRuleImpl = new TransactionRuleImpl();
        PendingTransaction pendingTransaction = new PendingTransaction();
        pendingTransaction.setComments("Single Node Money Transaction");
        pendingTransaction.setSingleNodeTransaction(true);
        // total up "current values" from transactions against current values
        KualiDecimal currentObligated = aai.getAmountObligatedToDate();
        KualiDecimal currentAnticipated = aai.getAnticipatedTotalAmount();
        for(PendingTransaction penTran : timeAndMoneyDocument.getPendingTransactions()) {
            // if incoming transaction
            if (StringUtils.equalsIgnoreCase(penTran.getSourceAwardNumber(),Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT)){
                currentObligated = currentObligated.add(penTran.getObligatedAmount());
                currentAnticipated = currentAnticipated.add(penTran.getAnticipatedAmount());
            } else if (StringUtils.equalsIgnoreCase(penTran.getDestinationAwardNumber(),Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT)){
                currentObligated = currentObligated.subtract(penTran.getObligatedAmount());
                currentAnticipated = currentAnticipated.subtract(penTran.getAnticipatedAmount());
            }
        }
        if(!awardHierarchyNode.getAmountObligatedToDate().equals(currentObligated)
                || !awardHierarchyNode.getAnticipatedTotalAmount().equals(currentAnticipated)){
            KualiDecimal obligatedChange = awardHierarchyNode.getAmountObligatedToDate().subtract(currentObligated);
            KualiDecimal anticipatedChange = awardHierarchyNode.getAnticipatedTotalAmount().subtract(currentAnticipated);
            if(transactionRuleImpl.processParameterDisabledRules(awardHierarchyNode, aai, timeAndMoneyDocument)){
                List<Award> awardItems = new ArrayList<Award>();
                awardItems.add(award);
            
                if (obligatedChange.isGreaterThan(new KualiDecimal(0))) {
                    pendingTransaction.setSourceAwardNumber(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT);
                    pendingTransaction.setDestinationAwardNumber(award.getAwardNumber());
                }else if (obligatedChange.isLessThan(new KualiDecimal(0))){
                    pendingTransaction.setSourceAwardNumber(award.getAwardNumber());
                    pendingTransaction.setDestinationAwardNumber(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT);
                }
                if (anticipatedChange.isGreaterThan(new KualiDecimal(0))) {
                    pendingTransaction.setSourceAwardNumber(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT);
                    pendingTransaction.setDestinationAwardNumber(award.getAwardNumber());
                }else if (anticipatedChange.isLessThan(new KualiDecimal(0))){
                    pendingTransaction.setSourceAwardNumber(award.getAwardNumber());
                    pendingTransaction.setDestinationAwardNumber(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT);
                }
                pendingTransaction.setObligatedAmount(obligatedChange.abs());
                pendingTransaction.setAnticipatedAmount(anticipatedChange.abs());
                pendingTransaction.setDocumentNumber(timeAndMoneyDocument.getDocumentNumber());
                pendingTransaction.setProcessedFlag(false);
                timeAndMoneyDocument.getPendingTransactions().add(pendingTransaction);
                for(PendingTransaction penTran : timeAndMoneyDocument.getPendingTransactions()) {
                    penTran.setDocumentNumber(timeAndMoneyDocument.getDocumentNumber());
                }
                getBusinessObjectService().save(timeAndMoneyDocument.getPendingTransactions());//need pending transaction to have a primarykey value
                timeAndMoneyForm.setToPendingView();
                result = true;
            }else {
                ahn.setAmountObligatedToDate(awardHierarchyNode.getAmountObligatedToDate());
                ahn.setObliDistributableAmount(awardHierarchyNode.getObliDistributableAmount());
                ahn.setAnticipatedTotalAmount(awardHierarchyNode.getAnticipatedTotalAmount());
                ahn.setAntDistributableAmount(awardHierarchyNode.getAntDistributableAmount());
            }
        }
        return result;
    }
    
    
    private void captureDateChangeTransactions(ActionForm form) throws WorkflowException {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyForm.getTimeAndMoneyDocument();
        AwardAmountInfoService awardAmountInfoService = KcServiceLocator.getService(AwardAmountInfoService.class);
        List<AwardAmountInfo> awardAmountInfoObjects = new ArrayList<AwardAmountInfo>();
        DocumentService documentService = KcServiceLocator.getService(DocumentService.class);
        //save rules have not been applied yet so there needs to be a null check on transaction type code before testing the value.
        boolean isNoCostExtension;
        if (timeAndMoneyDocument.getAwardAmountTransactions().get(0).getTransactionTypeCode() == null) {
            isNoCostExtension = false;
        }else {
            isNoCostExtension = timeAndMoneyDocument.getAwardAmountTransactions().get(0).getTransactionTypeCode().equals(TEN);//Transaction type code for No Cost Extension
        }       
        //if Dates have changed in a node in hierarchy view and the Transaction Type is a No Cost Extension,
        //we need to record this as a transaction in history.
        //build the transaction and add to this list for persistence later.
        List<TransactionDetail> dateChangeTransactionDetailItems = new ArrayList<TransactionDetail>();
        
        updateDocumentFromSession(timeAndMoneyDocument);//not sure if I need to do this.
        updateAwardAmountTransactions(timeAndMoneyDocument);
        for(Entry<String, AwardHierarchyNode> awardHierarchyNode : timeAndMoneyDocument.getAwardHierarchyNodes().entrySet()){
            //Award award = aptService.getWorkingAwardVersion(awardHierarchyNode.getValue().getAwardNumber()); 
            Award award = getAwardVersionService().getWorkingAwardVersion(awardHierarchyNode.getValue().getAwardNumber());
            int index = findAwardHierarchyNodeIndex(awardHierarchyNode);
            AwardAmountInfo aai = awardAmountInfoService.fetchAwardAmountInfoWithHighestTransactionId(award.getAwardAmountInfos());
            boolean needToSaveAward = false;
            needToSaveAward |= inspectAndCaptureCurrentFundEffectiveDateChanges(timeAndMoneyForm, isNoCostExtension, aai, index, award, timeAndMoneyDocument, awardHierarchyNode, dateChangeTransactionDetailItems);
            aai = awardAmountInfoService.fetchAwardAmountInfoWithHighestTransactionId(award.getAwardAmountInfos());//get new award amount info if date change transactions have been created.
            needToSaveAward |= inspectAndCaptureObligationExpirationDateChanges(timeAndMoneyForm, isNoCostExtension, aai, index, award, timeAndMoneyDocument, awardHierarchyNode, dateChangeTransactionDetailItems);
            aai = awardAmountInfoService.fetchAwardAmountInfoWithHighestTransactionId(award.getAwardAmountInfos());//get new award amount info if date change transactions have been created.
            needToSaveAward |= inspectAndCaptureFinalExpirationDateChanges(timeAndMoneyForm, isNoCostExtension, aai, index, award, timeAndMoneyDocument, awardHierarchyNode, dateChangeTransactionDetailItems);
            //capture any changes of DirectFandADistributions, and add them to the Award working version for persistence.
            if(award.getAwardNumber().equals(timeAndMoneyDocument.getAward().getAwardNumber())) {
                //must use documentService to save the award document. businessObjectService.save() builds deletion award list on T&M doc and we
                //need it to be wired up on AwardDocument so that any deletes from collection will be caught and persisted correctly.
                AwardDocument awardDocument = (AwardDocument) documentService.getByDocumentHeaderId(award.getAwardDocument().getDocumentNumber());
                if (mustSetFandADistributions(awardDocument.getAward().getAwardDirectFandADistributions(),timeAndMoneyDocument.getAward().getAwardDirectFandADistributions())) {
                    awardDocument.getAward().setAwardDirectFandADistributions(timeAndMoneyDocument.getAward().getAwardDirectFandADistributions());
                    documentService.saveDocument(awardDocument);
                    needToSaveAward = true;
                }
            }
            if (needToSaveAward) {
                getBusinessObjectService().save(award);
            }
        }
        //we want to apply save rules to doc before we save any captured changes.
        //The save on awardAmountInfoObjects should always be after the save on entire award object otherwise awardAmountInfoObjects changes get overwritten.
        getBusinessObjectService().save(awardAmountInfoObjects);
        getBusinessObjectService().save(timeAndMoneyDocument.getAwardAmountTransactions());
        //save all transaction details from No Cost extension date changes.
        getBusinessObjectService().save(dateChangeTransactionDetailItems);
        timeAndMoneyDocument.getAward().refreshReferenceObject("awardAmountInfos");//don't think I need to do this.
    }
        
    /**
     *Date changes in hierarchy view are captured here.  If the transaction is a No Cost Extension, we report the transaction
     *details for display in history tab.
     */
    protected boolean inspectAndCaptureCurrentFundEffectiveDateChanges(TimeAndMoneyForm timeAndMoneyForm, Boolean isNoCostExtension, AwardAmountInfo aai, Integer index,
                                                        Award award, TimeAndMoneyDocument timeAndMoneyDocument, Entry<String, AwardHierarchyNode> awardHierarchyNode,
                                                        List<TransactionDetail> dateChangeTransactionDetailItems) {
        
        Date currentEffectiveDate = timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getCurrentFundEffectiveDate();
        Date previousEffectiveDate = aai.getCurrentFundEffectiveDate();
        boolean needToSave = false;
        if(timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).isPopulatedFromClient() 
            && currentEffectiveDate !=null && !currentEffectiveDate.equals(previousEffectiveDate)){
         
            //aai.getCurrentFundEffectiveDate == null and this throws a stack trace only when doign a before
            // if current transaction currentFEDate is < previosu trans currentFEDate
            if (isNoCostExtension &&  previousEffectiveDate != null && currentEffectiveDate.before(previousEffectiveDate) ) {
                        AwardAmountInfo tempAai = getNewAwardAmountInfoForDateChangeTransaction(aai, award, timeAndMoneyDocument.getDocumentNumber());
                        needToSave = true;
                        aai = tempAai;
                        aai.setCurrentFundEffectiveDate(currentEffectiveDate);
                        awardHierarchyNode.getValue().setCurrentFundEffectiveDate(currentEffectiveDate);
                        award.getAwardAmountInfos().add(aai);
                        addTransactionDetails(aai.getAwardNumber(), aai.getAwardNumber(), aai.getSequenceNumber(), timeAndMoneyDocument.getAwardNumber(),
                                                timeAndMoneyDocument.getDocumentNumber(), OBLIGATED_START_COMMENT, dateChangeTransactionDetailItems);
            } else {
                    AwardAmountInfo tempAai = getNewAwardAmountInfoForDateChangeTransaction(aai, award, timeAndMoneyDocument.getDocumentNumber());
                    needToSave = true;
                    aai = tempAai;
                    aai.setCurrentFundEffectiveDate(currentEffectiveDate);
                    awardHierarchyNode.getValue().setCurrentFundEffectiveDate(currentEffectiveDate);
                    award.getAwardAmountInfos().add(aai);
            }
        } else if (timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).isPopulatedFromClient() && currentEffectiveDate == null) {
          //FYI, this will show an erorr to the user, we are doing this such they can see the error, and that they had put in a null value
            awardHierarchyNode.getValue().setCurrentFundEffectiveDate(null);
        }
        //special case where a user can enter an invalid date that will throw a hard error.  If the user tries to change that date back
        //to the original date, we need to capture that and change the value on the document which is the date value that gets validated
        //in save rules.
        if(timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getCurrentFundEffectiveDate()!=null && 
                timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getCurrentFundEffectiveDate() != null &&
                timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getCurrentFundEffectiveDate().equals(aai.getCurrentFundEffectiveDate()) &&
                !timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getCurrentFundEffectiveDate().equals(awardHierarchyNode.getValue().getCurrentFundEffectiveDate())) {
            awardHierarchyNode.getValue().setCurrentFundEffectiveDate(timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getCurrentFundEffectiveDate());
        }
        return needToSave;
    }
    
    /**
     *Date changes in hierarchy view are captured here.  If the transaction is a No Cost Extension, we report the transaction
     *details for display in history tab.
     */
    protected boolean inspectAndCaptureObligationExpirationDateChanges(TimeAndMoneyForm timeAndMoneyForm, Boolean isNoCostExtension, AwardAmountInfo aai, Integer index,
                                                        Award award, TimeAndMoneyDocument timeAndMoneyDocument, Entry<String, AwardHierarchyNode> awardHierarchyNode,
                                                        List<TransactionDetail> dateChangeTransactionDetailItems) {
        boolean needToSave = false;
        Date previousObligationExpirationDate = aai.getObligationExpirationDate();
        Date currentObligationExpirationDate = timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getObligationExpirationDate();
      
        if(timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).isPopulatedFromClient()
                && currentObligationExpirationDate !=null 
                && !currentObligationExpirationDate.equals(previousObligationExpirationDate)){
            // previousObligationExpirationDate is null
            if (isNoCostExtension && previousObligationExpirationDate == null || 
                    currentObligationExpirationDate.after(previousObligationExpirationDate)) {
                        AwardAmountInfo tempAai = getNewAwardAmountInfoForDateChangeTransaction(aai, award, timeAndMoneyDocument.getDocumentNumber());
                        aai = tempAai;
                        aai.setObligationExpirationDate(currentObligationExpirationDate);
                        awardHierarchyNode.getValue().setObligationExpirationDate(currentObligationExpirationDate);
                        award.getAwardAmountInfos().add(aai);
                        addTransactionDetails(aai.getAwardNumber(), aai.getAwardNumber(), aai.getSequenceNumber(), timeAndMoneyDocument.getAwardNumber(),
                                                    timeAndMoneyDocument.getDocumentNumber(), OBLIGATED_END_COMMENT, dateChangeTransactionDetailItems);
            }else {AwardAmountInfo tempAai = getNewAwardAmountInfoForDateChangeTransaction(aai, award, timeAndMoneyDocument.getDocumentNumber());
                    aai = tempAai;
                    aai.setObligationExpirationDate(currentObligationExpirationDate);
                    awardHierarchyNode.getValue().setObligationExpirationDate(currentObligationExpirationDate);
                    award.getAwardAmountInfos().add(aai);
            }
            needToSave = true;
        } else if (timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).isPopulatedFromClient()
                && currentObligationExpirationDate == null) {
          //FYI, this will show an error to the user, we are doing this such they can see the error, and that they had put in a null value
            awardHierarchyNode.getValue().setObligationExpirationDate(null);
        }
        
        //special case where a user can enter an invalid date that will throw a hard error.  If the user tries to change that date back
        //to the original date, we need to capture that and change the value on the document which is the date value that gets validated
        //in save rules.
        if(timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getCurrentFundEffectiveDate()!=null && 
                currentObligationExpirationDate != null &&
                currentObligationExpirationDate.equals(previousObligationExpirationDate) &&
                !currentObligationExpirationDate.equals(awardHierarchyNode.getValue().getObligationExpirationDate())) {
            awardHierarchyNode.getValue().setObligationExpirationDate(currentObligationExpirationDate);
        }
        return needToSave;
    }
    
    /**
     *Date changes in hierarchy view are captured here.  If the transaction is a No Cost Extension, we report the transaction
     *details for display in history tab.
     */
    protected boolean inspectAndCaptureFinalExpirationDateChanges(TimeAndMoneyForm timeAndMoneyForm, Boolean isNoCostExtension, AwardAmountInfo aai, Integer index,
                                                        Award award, TimeAndMoneyDocument timeAndMoneyDocument, Entry<String, AwardHierarchyNode> awardHierarchyNode,
                                                        List<TransactionDetail> dateChangeTransactionDetailItems) {
       
        boolean needToSave = false;
        if(timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).isPopulatedFromClient()
                && timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getFinalExpirationDate()!=null 
                && !timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getFinalExpirationDate().equals(aai.getFinalExpirationDate())){ 
          if (isNoCostExtension && 
                  timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getFinalExpirationDate().after(aai.getFinalExpirationDate())) {
                      AwardAmountInfo tempAai = getNewAwardAmountInfoForDateChangeTransaction(aai, award, timeAndMoneyDocument.getDocumentNumber());
                      aai = tempAai;
                      aai.setFinalExpirationDate(timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getFinalExpirationDate());
                      awardHierarchyNode.getValue().setFinalExpirationDate(timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getFinalExpirationDate());
                      award.getAwardAmountInfos().add(aai);
                      addTransactionDetails(aai.getAwardNumber(), aai.getAwardNumber(), aai.getSequenceNumber(), timeAndMoneyDocument.getAwardNumber(),
                                              timeAndMoneyDocument.getDocumentNumber(), PROJECT_END_COMMENT, dateChangeTransactionDetailItems);
          }else {AwardAmountInfo tempAai = getNewAwardAmountInfoForDateChangeTransaction(aai, award, timeAndMoneyDocument.getDocumentNumber());
                  aai = tempAai;
                  aai.setFinalExpirationDate(timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getFinalExpirationDate());
                  awardHierarchyNode.getValue().setFinalExpirationDate(timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getFinalExpirationDate());
                  award.getAwardAmountInfos().add(aai);
          }
          needToSave = true;
      } else if (timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).isPopulatedFromClient()
              && timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getFinalExpirationDate() == null) {
          //FYI, this will show an erorr to the user, we are doing this such they can see the error, and that they had put in a null value
          awardHierarchyNode.getValue().setFinalExpirationDate(null);
      }
      //special case where a user can enter an invalid date that will throw a hard error.  If the user tries to change that date back
      //to the original date, we need to capture that and change the value on the document which is the date value that gets validated
      //in save rules.
      if(timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getCurrentFundEffectiveDate()!=null && 
              timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getFinalExpirationDate() != null &&
              timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getFinalExpirationDate().equals(aai.getFinalExpirationDate()) &&
              !timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getFinalExpirationDate().equals(awardHierarchyNode.getValue().getFinalExpirationDate())) {
          awardHierarchyNode.getValue().setFinalExpirationDate(timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getFinalExpirationDate());
      }
      return needToSave;
    }
    
    /* 
     * check for changes to Direct F and A Distribution lists, if any differences, then we need to save them in the current Award.
     */
    private boolean mustSetFandADistributions(List<AwardDirectFandADistribution> awardFandADistributions, List<AwardDirectFandADistribution> tAndMFandADistributions) {
        boolean needToSave = false;
         for (AwardDirectFandADistribution awardDistribution: awardFandADistributions) {
             boolean found = false;
             for (AwardDirectFandADistribution tAndMDistribution: tAndMFandADistributions) {
                 if (awardDistribution.equals(tAndMDistribution)) {
                     found = true;
                 }
             }
             if (!found) {
                 needToSave = true;
             }
         }
         return needToSave;
     }
    
    /*
     * 
     * This method creates a transactionDetail object and adds it to the list for persistence later.
     * 
     * @param sourceAwardNumber
     * @param destinationAwardNumber
     * @param sequenceNumber
     * @param pendingTransaction
     * @param currentAwardNumber
     * @param documentNumber
     * @param transactionDetailItems
     */
    protected void addTransactionDetails(String sourceAwardNumber, String destinationAwardNumber, Integer sequenceNumber, String currentAwardNumber, String documentNumber, 
            String commentsString, List<TransactionDetail> transactionDetailItems){
        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setSourceAwardNumber(sourceAwardNumber);
        transactionDetail.setSequenceNumber(sequenceNumber);
        transactionDetail.setDestinationAwardNumber(destinationAwardNumber);
        //transactionDetail.setAnticipatedAmount(pendingTransaction.getAnticipatedAmount());
        //transactionDetail.setObligatedAmount(pendingTransaction.getObligatedAmount());
        transactionDetail.setAwardNumber(currentAwardNumber);
        transactionDetail.setTransactionId(new Long(-1));
        transactionDetail.setTimeAndMoneyDocumentNumber(documentNumber);
        transactionDetail.setComments(commentsString);
        transactionDetailItems.add(transactionDetail);
    }
    
    /*
     * add money to amount info Totals, and Distributables.
     * 
     */
    private AwardAmountInfo getNewAwardAmountInfoForDateChangeTransaction(AwardAmountInfo awardAmountInfo,  Award award, String documentNumber) {
        
        AwardAmountInfo newAwardAmountInfo = new AwardAmountInfo();
        newAwardAmountInfo.setAwardNumber(awardAmountInfo.getAwardNumber());
        newAwardAmountInfo.setSequenceNumber(award.getSequenceNumber());
        newAwardAmountInfo.setFinalExpirationDate(awardAmountInfo.getFinalExpirationDate());
        newAwardAmountInfo.setCurrentFundEffectiveDate(awardAmountInfo.getCurrentFundEffectiveDate());
        newAwardAmountInfo.setObligationExpirationDate(awardAmountInfo.getObligationExpirationDate());
        newAwardAmountInfo.setTimeAndMoneyDocumentNumber(documentNumber);
        newAwardAmountInfo.setTransactionId(null);
        newAwardAmountInfo.setAward(award);
       //add transaction amounts to the AmountInfo
        newAwardAmountInfo.setObliDistributableAmount(awardAmountInfo.getObliDistributableAmount());
        newAwardAmountInfo.setAmountObligatedToDate(awardAmountInfo.getAmountObligatedToDate());
        newAwardAmountInfo.setObligatedTotalDirect(awardAmountInfo.getObligatedTotalDirect());
        newAwardAmountInfo.setObligatedTotalIndirect(awardAmountInfo.getObligatedTotalIndirect());
        newAwardAmountInfo.setAntDistributableAmount(awardAmountInfo.getAntDistributableAmount());
        newAwardAmountInfo.setAnticipatedTotalAmount(awardAmountInfo.getAnticipatedTotalAmount());
        newAwardAmountInfo.setAnticipatedTotalDirect(awardAmountInfo.getAnticipatedTotalDirect());
        newAwardAmountInfo.setAnticipatedTotalIndirect(awardAmountInfo.getAnticipatedTotalIndirect());
        newAwardAmountInfo.setObligatedChangeDirect(new KualiDecimal(0));
        newAwardAmountInfo.setObligatedChangeIndirect(new KualiDecimal(0));
        newAwardAmountInfo.setAnticipatedChangeDirect(new KualiDecimal(0));
        newAwardAmountInfo.setAnticipatedChangeIndirect(new KualiDecimal(0));
        newAwardAmountInfo.setOriginatingAwardVersion(award.getSequenceNumber());

        return newAwardAmountInfo;
    }
    
    private void updateAwardAmountTransactions(TimeAndMoneyDocument timeAndMoneyDocument) {
        AwardAmountTransaction aat = timeAndMoneyDocument.getNewAwardAmountTransaction();
        if(!(aat == null)) {
            if (timeAndMoneyDocument.getAwardAmountTransactions().size() == 0 ||
                    (timeAndMoneyDocument.getAwardAmountTransactions().size() == 1 && 
                            timeAndMoneyDocument.getAwardAmountTransactions().get(0).getTransactionTypeCode() == null)) {
                aat.setAwardNumber(timeAndMoneyDocument.getAwardNumber());
                aat.setDocumentNumber(timeAndMoneyDocument.getDocumentNumber());
                timeAndMoneyDocument.getAwardAmountTransactions().set(0, aat);
            }else {
                AwardAmountTransaction firstAatInList = timeAndMoneyDocument.getAwardAmountTransactions().get(0);
                for(AwardAmountTransaction awardAmountTransaction : timeAndMoneyDocument.getAwardAmountTransactions()) {
                    awardAmountTransaction.setTransactionTypeCode(firstAatInList.getTransactionTypeCode());
                    awardAmountTransaction.setNoticeDate(firstAatInList.getNoticeDate());
                    awardAmountTransaction.setComments(firstAatInList.getComments());
                }
            }
        }
    }

    /*
     * This method...
     */
    private int findAwardHierarchyNodeIndex(Entry<String, AwardHierarchyNode> awardHierarchyNode) {
        String i = awardHierarchyNode.getValue().getAwardNumber().replaceAll("\\d*\\-0*", "");
        int index = Integer.parseInt(i);
        return index;
    }
    
    /*
     * override route to call save before we route.
     */
    @Override
    public ActionForward route(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward;
        save(mapping, form, request, response);
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyForm.getTimeAndMoneyDocument();
        actionForward = super.route(mapping, form, request, response);  
        // save report tracking items
        saveReportTrackingItems(timeAndMoneyForm);
        
        String routeHeaderId = timeAndMoneyForm.getDocument().getDocumentNumber();
        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_AWARD_TIME_AND_MONEY_PAGE, "TimeAndMoneyDocument");
        
        ActionForward basicForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
        ActionForward holdingPageForward = mapping.findForward(Constants.MAPPING_HOLDING_PAGE);
        return routeToHoldingPage(basicForward, actionForward, holdingPageForward, returnLocation);
    }
    
    protected void saveReportTrackingItems(TimeAndMoneyForm timeAndMoneyForm) throws ParseException {
        TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyForm.getTimeAndMoneyDocument();
        Award award = timeAndMoneyDocument.getAward();
        getReportTrackingService().generateReportTrackingAndSave(award, true);
    }
        
    /**
     * override to call save before we blanket approve.
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#blanketApprove(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward blanketApprove(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward actionForward;
        save(mapping, form, request, response);
        actionForward = super.blanketApprove(mapping, form, request, response);      
        
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        saveReportTrackingItems(timeAndMoneyForm);

        String routeHeaderId = timeAndMoneyForm.getDocument().getDocumentNumber();
        
        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_AWARD_TIME_AND_MONEY_PAGE, "TimeAndMoneyDocument");
        ActionForward basicForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
        ActionForward holdingPageForward = mapping.findForward(Constants.MAPPING_HOLDING_PAGE);
        return routeToHoldingPage(basicForward, actionForward, holdingPageForward, returnLocation);
    }
    
    
    

    /**
     * must remove all award amount infos corresponding to this document.  Date changes create and add new Award Amount Info.  Pending Transactions
     * do not create new Award Amount Info until the document is routed or blanket approved.
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#cancel(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @SuppressWarnings("unchecked")
    @Override
    public ActionForward cancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward;
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyForm.getTimeAndMoneyDocument();
        //remove all Award Amount Infos from all affected awards.
        Map<String, AwardHierarchy> awardHierarchyItems = timeAndMoneyDocument.getAwardHierarchyItems();
        for (Map.Entry<String, AwardHierarchy> awardHierarchyEntry : awardHierarchyItems.entrySet()) {
            AwardHierarchy awardHierarchy = awardHierarchyEntry.getValue();
            //Award award = getWorkingAwardVersion(awardHierarchy.getAwardNumber());
            Award award = getAwardVersionService().getWorkingAwardVersion(awardHierarchy.getAwardNumber());
            List<AwardAmountInfo> deleteCollection = new ArrayList<AwardAmountInfo>();
            for (AwardAmountInfo awardAmountInfo : award.getAwardAmountInfos()) {
                if(!(awardAmountInfo.getTimeAndMoneyDocumentNumber() == null)) {
                    if(awardAmountInfo.getTimeAndMoneyDocumentNumber().equals(timeAndMoneyDocument.getDocumentNumber())) {
                        deleteCollection.add(awardAmountInfo);
                    }
                }
            }
            getBusinessObjectService().delete(deleteCollection);
            deleteCollection.clear();
        }
        actionForward = super.cancel(mapping, form, request, response);   
        
        return actionForward;
    }

    /**
     * 
     * This method refreshes the view depending on various view options like either active or pending view or dates only, totals and 
     * distributed/distributable. 
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward refreshView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        TimeAndMoneyDocument doc = timeAndMoneyForm.getTimeAndMoneyDocument();
        //perform this logic if pending view
        if(StringUtils.equalsIgnoreCase(timeAndMoneyForm.getCurrentOrPendingView(), TimeAndMoneyForm.PENDING)){
            
            Map<String, AwardAmountTransaction> awardAmountTransactionItems = new HashMap<String, AwardAmountTransaction>();
            List<Award> awardItems = new ArrayList<Award>();
            List<TransactionDetail> transactionDetailItems = new ArrayList<TransactionDetail>();
            
            updateDocumentFromSession(doc);

            //added refreshFlag boolean to service method. If doing a refresh, we don't want to reset the processed flag.  Only when T&M doc is routed for approval.
            getActivePendingTransactionsService().processTransactions(doc, doc.getAwardAmountTransactions().get(0), awardAmountTransactionItems, awardItems, transactionDetailItems, true);
            GlobalVariables.getUserSession().addObject(GlobalVariables.getUserSession().getKualiSessionId()+Constants.TIME_AND_MONEY_DOCUMENT_STRING_FOR_SESSION, doc);
            //doc.refreshReferenceObject(PENDING_TRANSACTIONS_ATTRIBUTE_NAME);
        //perform this logic if active view
        } else if(StringUtils.equalsIgnoreCase(timeAndMoneyForm.getCurrentOrPendingView(), TimeAndMoneyForm.CURRENT)){
            timeAndMoneyForm.setOrder(new ArrayList<String>());
            
            Award tmpAward = getCurrentAward(doc);
            doc.setAwardHierarchyItems(getAwardHierarchyService().getAwardHierarchy(doc.getRootAwardNumber(), timeAndMoneyForm.getOrder()));
            if(tmpAward != null) {
                getAwardHierarchyService().populateAwardHierarchyNodesForTandMDoc(doc.getAwardHierarchyItems(), doc.getAwardHierarchyNodes(), tmpAward.getAwardNumber(), tmpAward.getSequenceNumber().toString(), timeAndMoneyForm.getTimeAndMoneyDocument());
            } else {
                getAwardHierarchyService().populateAwardHierarchyNodesForTandMDoc(doc.getAwardHierarchyItems(), doc.getAwardHierarchyNodes(), null, null, timeAndMoneyForm.getTimeAndMoneyDocument());
            }
            GlobalVariables.getUserSession().addObject(GlobalVariables.getUserSession().getKualiSessionId()+Constants.TIME_AND_MONEY_DOCUMENT_STRING_FOR_SESSION, doc);
        }
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }

    /*
     * This method...
     * @param doc
     */
    private void updateDocumentFromSession(TimeAndMoneyDocument doc) {
        if(doc.getAwardHierarchyNodes()==null || doc.getAwardHierarchyNodes().size()==0){
            if(GlobalVariables.getUserSession().retrieveObject(GlobalVariables.getUserSession().getKualiSessionId()+Constants.TIME_AND_MONEY_DOCUMENT_STRING_FOR_SESSION)!=null){
                TimeAndMoneyDocument document = (TimeAndMoneyDocument)GlobalVariables.getUserSession().retrieveObject(GlobalVariables.getUserSession().getKualiSessionId()+Constants.TIME_AND_MONEY_DOCUMENT_STRING_FOR_SESSION);
                doc.setAwardHierarchyItems(document.getAwardHierarchyItems());
                doc.setAwardHierarchyNodes(document.getAwardHierarchyNodes());
            }else {
                throw new RuntimeException("Can't Retrieve Time And Money Document from Session");
            }
        }
    }
    
    private Award getCurrentAward(TimeAndMoneyDocument timeAndMoneyDocument) {
        Award tmpAward = timeAndMoneyDocument.getAward();
        if(tmpAward == null) {
            tmpAward = getAwardVersionService().getWorkingAwardVersion(timeAndMoneyDocument.getAwardNumber());
        }
        
        return tmpAward;
    }
    
   
    
    /**
     * 
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#docHandler(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyForm.getTimeAndMoneyDocument();
        String command = timeAndMoneyForm.getCommand();

        ActionForward forward = handleDocument(mapping, form, request, response, timeAndMoneyForm);
//        if ("timeAndMoney".equals(command)) {
//            forward = home(mapping, timeAndMoneyForm, request, response);
//        }
        timeAndMoneyForm.initializeFormOrDocumentBasedOnCommand();        
        String rootAwardNumber = timeAndMoneyForm.getTimeAndMoneyDocument().getRootAwardNumber();
                
        timeAndMoneyDocument.setAwardHierarchyItems(getAwardHierarchyService().getAwardHierarchy(rootAwardNumber, timeAndMoneyForm.getOrder()));
        timeAndMoneyDocument.setAwardNumber(rootAwardNumber);  
        
        Award tmpAward = getCurrentAward(timeAndMoneyDocument);
        if(tmpAward != null) {
            getAwardHierarchyService().populateAwardHierarchyNodesForTandMDoc(timeAndMoneyDocument.getAwardHierarchyItems(), timeAndMoneyDocument.getAwardHierarchyNodes(), tmpAward.getAwardNumber(), tmpAward.getSequenceNumber().toString(), timeAndMoneyForm.getTimeAndMoneyDocument());
        } else {
            getAwardHierarchyService().populateAwardHierarchyNodesForTandMDoc(timeAndMoneyDocument.getAwardHierarchyItems(), timeAndMoneyDocument.getAwardHierarchyNodes(), null, null, timeAndMoneyForm.getTimeAndMoneyDocument());
        }
        //initialize award for summary display to current version of root award
        timeAndMoneyForm.setAwardForSummaryPanelDisplay(tmpAward);
        
        getAwardHierarchyService().createNodeMapsOnFormForSummaryPanel(timeAndMoneyDocument.getAwardHierarchyNodes(), timeAndMoneyForm.getPreviousNodeMap(),
                timeAndMoneyForm.getNextNodeMap());

        GlobalVariables.getUserSession().addObject(GlobalVariables.getUserSession().getKualiSessionId()+Constants.TIME_AND_MONEY_DOCUMENT_STRING_FOR_SESSION, timeAndMoneyDocument);
        
        populateOtherPanels(timeAndMoneyForm.getTransactionBean().getNewAwardAmountTransaction(), timeAndMoneyForm, rootAwardNumber);
        
        return forward;
    }
         
    
    /*
     * This method retrieves AwardHierarchyService
     */
    protected AwardHierarchyService getAwardHierarchyService(){        
        return (AwardHierarchyService) KcServiceLocator.getService(AwardHierarchyService.class);
    }
    
    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @param awardForm
     * @return
     * @throws Exception
     */
    public ActionForward handleDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response, TimeAndMoneyForm timeAndMoneyForm) throws Exception {
        String command = timeAndMoneyForm.getCommand();
        ActionForward forward = null;        
        if (KewApiConstants.ACTIONLIST_INLINE_COMMAND.equals(command)) {
            String docIdRequestParameter = request.getParameter(KRADConstants.PARAMETER_DOC_ID);
            Document retrievedDocument = getDocumentService().getByDocumentHeaderId(docIdRequestParameter);
            timeAndMoneyForm.setDocument(retrievedDocument);
            request.setAttribute(KRADConstants.PARAMETER_DOC_ID, docIdRequestParameter);
            ActionForward baseForward = mapping.findForward(Constants.MAPPING_COPY_PROPOSAL_PAGE);
            forward = new ActionForward(buildForwardStringForActionListCommand(
                    baseForward.getPath(),docIdRequestParameter));  
        } else if (Constants.MAPPING_AWARD_TIME_AND_MONEY_PAGE.equals(command)) {
            loadDocument(timeAndMoneyForm);
        }else {
            forward = super.docHandler(mapping, form, request, response);
        }
        
        if (Constants.MAPPING_AWARD_TIME_AND_MONEY_PAGE.equals(command)) {
            forward = mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        }
        
        return forward;
    }
    
    /**
     * 
     * This method adds a new transaction.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addTransaction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        captureDateChangeTransactions(form);
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        if (timeAndMoneyForm.getTransactionBean().addPendingTransactionItem()) {
            timeAndMoneyForm.setToPendingView();
            refreshView(mapping, form, request, response);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);        
    }
    
    /**
     * 
     * This method adds a deletes a transaction.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteTransaction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        captureDateChangeTransactions(form);
        ((TimeAndMoneyForm) form).getTransactionBean().deletePendingTransactionItem(getLineToDelete(request));
        refreshView(mapping, form, request, response);
        return mapping.findForward(Constants.MAPPING_BASIC);        
    }
    
    /**
     * 
     * This method switches the award for history, summary and Action Summary panels.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward switchAward(ActionMapping mapping, ActionForm form , HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm)form;
        String goToAwardNumber = timeAndMoneyForm.getGoToAwardNumber();
        
        populateOtherPanels(timeAndMoneyForm.getTransactionBean().getNewAwardAmountTransaction(), timeAndMoneyForm, goToAwardNumber);
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /*
     * This method populates Summary, Action Summary and History panels for selected award.
     *  
     * @param timeAndMoneyForm
     * @param timeAndMoneyForm
     * @param goToAwardNumber
     * @throws LookupException
     * @throws SQLException
     */
    private void populateOtherPanels(AwardAmountTransaction newAwardAmountTransaction, TimeAndMoneyForm timeAndMoneyForm, String goToAwardNumber)
            throws LookupException, SQLException, WorkflowException {
        //Award award = getWorkingAwardVersion(goToAwardNumber);
        Award award = getAwardVersionService().getWorkingAwardVersion(goToAwardNumber);
        if (award == null) {
            GlobalVariables.getMessageMap().putError("goToAwardNumber", "error.timeandmoney.invalidawardnumber", goToAwardNumber);
            return;
        }
        TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyForm.getTimeAndMoneyDocument();
        timeAndMoneyDocument.setAwardNumber(award.getAwardNumber());
        timeAndMoneyDocument.setAward(award);

        TimeAndMoneyHistoryService tamhs = KcServiceLocator.getService(TimeAndMoneyHistoryService.class);
        
        tamhs.getTimeAndMoneyHistory(timeAndMoneyDocument.getAwardNumber(), timeAndMoneyDocument.getTimeAndMoneyHistory(), timeAndMoneyForm.getColumnSpan());
        
        timeAndMoneyDocument.getAwardVersionHistoryList().clear();
        tamhs.buildTimeAndMoneyHistoryObjects(award.getAwardNumber(), timeAndMoneyDocument.getAwardVersionHistoryList());
        TimeAndMoneyActionSummaryService tamass = KcServiceLocator.getService(TimeAndMoneyActionSummaryService.class);
        timeAndMoneyDocument.getTimeAndMoneyActionSummaryItems().clear();
        tamass.populateActionSummary(timeAndMoneyDocument.getTimeAndMoneyActionSummaryItems(), goToAwardNumber);
        
        timeAndMoneyDocument.setNewAwardAmountTransaction(newAwardAmountTransaction);
    }
    
    /**
     * This method tests if the award is new by checking the size of AwardDirectFandADistributions on the Award.
     * @param awardForm
     * @return
     */
    public boolean isNewAward(TimeAndMoneyForm timeAndMoneyForm) {
        return timeAndMoneyForm.getTimeAndMoneyDocument().getAward().getAwardDirectFandADistributions().size() == 0;
    }
    
    /**
     * Gets the businessObjectService attribute. 
     * @return Returns the businessObjectService.
     */
    public AwardVersionService getAwardVersionService() {
        awardVersionService = KcServiceLocator.getService(AwardVersionService.class);
        return awardVersionService;
    }

    /*
     * Retrieves an ActivePendingTransactionsService.
     */
    protected ActivePendingTransactionsService getActivePendingTransactionsService(){
        if (activePendingTransactionsService == null) {
            activePendingTransactionsService = KcServiceLocator.getService(ActivePendingTransactionsService.class);
        }
        return activePendingTransactionsService;
    }
    
    /**
     * 
     * This method builds the string for the ActionForward 
     * @param forwardPath
     * @param docIdRequestParameter
     * @return
     */
    public String buildForwardStringForActionListCommand(String forwardPath, String docIdRequestParameter){
        StringBuilder sb = new StringBuilder();
        sb.append(forwardPath);
        sb.append("?");
        sb.append(KRADConstants.PARAMETER_DOC_ID);
        sb.append("=");
        sb.append(docIdRequestParameter);
        return sb.toString();
    }    
    
    
    public ActionForward addTransaction(){
        return null;
    }
    
    public ActionForward deleteTransaction(){
        return null;
    }
    
    public ActionForward submit(){
        return null;
    }
    
    /**
     * 
     * This method adds a new AwardDirectFandADistribution to the list.  
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addAwardDirectFandADistribution(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        captureDateChangeTransactions(form);
        timeAndMoneyForm.getAwardDirectFandADistributionBean()
                    .addAwardDirectFandADistribution(((TimeAndMoneyForm) form).getAwardDirectFandADistributionBean());    
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method removes an AwardDirectFandADistribution from the list. 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteAwardDirectFandADistribution(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        captureDateChangeTransactions(form);
        timeAndMoneyForm.getTimeAndMoneyDocument().getAward().getAwardDirectFandADistributions().remove(getLineToDelete(request));
        timeAndMoneyForm.getAwardDirectFandADistributionBean().updateBudgetPeriodsAfterDelete(timeAndMoneyForm.getTimeAndMoneyDocument().getAward().getAwardDirectFandADistributions());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is used to recalculate the Total amounts in the Direct F and A Distribution panel.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward timeAndMoney(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
       
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is used to recalculate the Total amounts in the Direct F and A Distribution panel.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward recalculateDirectFandADistributionTotals(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
       
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Gets the businessObjectService attribute. 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        return businessObjectService;
    }
    
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {

        ActionForward forward = super.reload(mapping, form, request, response);

        return forward;
    }
    
    /**
     * 
     * This method retrieves the awardDocument from the session and redirects the user to the appropriate Award
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward returnToAward(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyForm.getTimeAndMoneyDocument();
        if(!getKraWorkflowService().isInWorkflow(timeAndMoneyDocument)){
            this.save(mapping, form, request, response);
        }
        String awardDocumentNumber = (String) GlobalVariables.getUserSession().retrieveObject(Constants.AWARD_DOCUMENT_STRING_FOR_SESSION + "-" + timeAndMoneyDocument.getDocumentNumber());
        if (StringUtils.isBlank(awardDocumentNumber)) {
            awardDocumentNumber = timeAndMoneyDocument.getAward().getAwardDocument().getDocumentNumber();
        }
        //reload document to make sure we have a valid workflow document
        AwardDocument awardDocument = (AwardDocument) getDocumentService().getByDocumentHeaderId(awardDocumentNumber);       
        String routeHeaderId = awardDocument.getDocumentHeader().getWorkflowDocument().getDocumentId();
        GlobalVariables.getUserSession().removeObject(Constants.AWARD_DOCUMENT_STRING_FOR_SESSION + "-" + timeAndMoneyDocument.getDocumentNumber());

        String forward = buildForwardUrl(routeHeaderId);
        return new ActionForward(forward, true);
    }
    
    protected KcWorkflowService getKraWorkflowService() {
        return KcServiceLocator.getService(KcWorkflowService.class);
    }
    
    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return this.parameterService;
    }
    
    public boolean isDirectIndirectViewEnabled() {
        boolean returnValue = false;
        String directIndirectEnabledValue = getParameterService().getParameterValueAsString(Constants.PARAMETER_MODULE_AWARD, ParameterConstants.DOCUMENT_COMPONENT, "ENABLE_AWD_ANT_OBL_DIRECT_INDIRECT_COST");
        if(directIndirectEnabledValue.equals("1")) {
            returnValue = true;
        }
        return returnValue;
    }
    
    @SuppressWarnings("unchecked")
    public ActionForward goToNextAward(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        Map<String, String> map = new HashMap<String,String>();
        String nextAwardNumber = timeAndMoneyForm.getNextNodeMap().get(timeAndMoneyForm.getAwardForSummaryPanelDisplay().getAwardNumber());
        map.put("awardNumber", nextAwardNumber);
        Award nextAward = ((List<Award>)getBusinessObjectService().findMatching(Award.class, map)).get(0);
        timeAndMoneyForm.setAwardForSummaryPanelDisplay(nextAward);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
        
    @SuppressWarnings("unchecked")
    public ActionForward goToPreviousAward(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        Map<String, String> map = new HashMap<String,String>();
        String previousAwardNumber = timeAndMoneyForm.getPreviousNodeMap().get(timeAndMoneyForm.getAwardForSummaryPanelDisplay().getAwardNumber());
        map.put("awardNumber", previousAwardNumber);
        Award previousAward = ((List<Award>)businessObjectService.findMatching(Award.class, map)).get(0);
        timeAndMoneyForm.setAwardForSummaryPanelDisplay(previousAward);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward editOrVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm)form;
        TimeAndMoneyDocument doc = timeAndMoneyForm.getTimeAndMoneyDocument();
        String rootAwardNumber = doc.getRootAwardNumber();
        TimeAndMoneyDocument finalTandM = getTimeAndMoneyVersionService().findOpenedTimeAndMoney(rootAwardNumber);
        String routeHeaderId = finalTandM.getDocumentHeader().getWorkflowDocument().getDocumentId();
        String forwardString = buildForwardUrl(routeHeaderId);
        return new ActionForward(forwardString, true);
    }
    
    /**
     * @see org.kuali.rice.kns.web.struts.action.KualiTransactionalDocumentActionBase#populateAuthorizationFields(org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase)
     */
    @SuppressWarnings("unchecked")
    @Override
    // Overriding this because KraTransactionalDocumentActionBase assumes the authorizer is of type KcDocumentAuthorizerBase
    protected void populateAuthorizationFields(KualiDocumentFormBase formBase) {
        if (formBase.isFormDocumentInitialized()) {
            Document document = formBase.getDocument();
            Person user = GlobalVariables.getUserSession().getPerson();
            DocumentPresentationController documentPresentationController = KNSServiceLocator.getDocumentHelperService().getDocumentPresentationController(document);
            DocumentAuthorizer documentAuthorizer = getDocumentHelperService().getDocumentAuthorizer(document);
            Set<String> documentActions =  documentPresentationController.getDocumentActions(document);
            documentActions = documentAuthorizer.getDocumentActions(document, user, documentActions);

            if (getDataDictionaryService().getDataDictionary().getDocumentEntry(document.getClass().getName()).getUsePessimisticLocking()) {
                documentActions = getPessimisticLockService().getDocumentActions(document, user, documentActions);
            }
            
            Set<String> editModes = new HashSet<String>();
            if (!documentAuthorizer.canOpen(document, user)) {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            } else if (documentActions.contains(KRADConstants.KUALI_ACTION_CAN_EDIT)) {
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            } else {
                editModes.add(AuthorizationConstants.EditMode.VIEW_ONLY);
            }
            Map editMode = this.convertSetToMap(editModes);
            if (getDataDictionaryService().getDataDictionary().getDocumentEntry(document.getClass().getName()).getUsePessimisticLocking()) {
                editMode = getPessimisticLockService().establishLocks(document, editMode, user);
            }
            
            // We don't want to use KNS way to determine can edit document overview
            // It should be the same as can edit
            if (editMode.containsKey(AuthorizationConstants.EditMode.FULL_ENTRY)) {
                if (!documentActions.contains(KRADConstants.KUALI_ACTION_CAN_EDIT_DOCUMENT_OVERVIEW)) {
                    documentActions.add(KRADConstants.KUALI_ACTION_CAN_EDIT_DOCUMENT_OVERVIEW);
                }
            } else {
                if (documentActions.contains(KRADConstants.KUALI_ACTION_CAN_EDIT_DOCUMENT_OVERVIEW)) {
                    documentActions.remove(KRADConstants.KUALI_ACTION_CAN_EDIT_DOCUMENT_OVERVIEW);
                }
            }
            //copy action is meaningless in T&M.
            if (documentActions.contains((KRADConstants.KUALI_ACTION_CAN_COPY))) {
                documentActions.remove(KRADConstants.KUALI_ACTION_CAN_COPY);
            }
            formBase.setDocumentActions(convertSetToMap(documentActions));
            formBase.setEditingMode(editMode);
        }
        
    }

    public TimeAndMoneyVersionService getTimeAndMoneyVersionService() {
        if (timeAndMoneyVersionService == null) {
            timeAndMoneyVersionService = KcServiceLocator.getService(TimeAndMoneyVersionService.class);
        }
        return timeAndMoneyVersionService;
    }

    public void setTimeAndMoneyVersionService(TimeAndMoneyVersionService timeAndMoneyVersionService) {
        this.timeAndMoneyVersionService = timeAndMoneyVersionService;
    }

    public ReportTrackingService getReportTrackingService() {
        return KcServiceLocator.getService(ReportTrackingService.class);
    }

}

