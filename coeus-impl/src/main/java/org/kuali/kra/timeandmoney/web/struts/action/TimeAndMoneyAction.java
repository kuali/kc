/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.timeandmoney.web.struts.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.ojb.broker.accesslayer.LookupException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.kuali.coeus.award.finance.timeAndMoney.TimeAndMoneyPosts;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.sys.framework.controller.KcHoldingPageConstants;
import org.kuali.coeus.sys.framework.controller.KcTransactionalDocumentActionBase;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
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
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.timeandmoney.TimeAndMoneyForm;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.history.TransactionDetail;
import org.kuali.kra.timeandmoney.history.TransactionDetailType;
import org.kuali.kra.timeandmoney.service.ActivePendingTransactionsService;
import org.kuali.kra.timeandmoney.service.TimeAndMoneyActionSummaryService;
import org.kuali.kra.timeandmoney.service.TimeAndMoneyHistoryService;
import org.kuali.kra.timeandmoney.service.TimeAndMoneyVersionService;
import org.kuali.kra.timeandmoney.transactions.AwardAmountTransaction;
import org.kuali.kra.timeandmoney.transactions.PendingTransaction;
import org.kuali.kra.timeandmoney.transactions.TransactionRuleImpl;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.document.authorization.DocumentAuthorizer;
import org.kuali.rice.kns.document.authorization.DocumentPresentationController;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.SequenceAccessorService;
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

    private static final String INVALID_AWARD_NUMBER_ERROR = "error.timeandmoney.invalidawardnumber";
	private static final String GO_TO_AWARD_NUMBER_FIELD_NAME = "goToAwardNumber";
	private static final String TIME_AND_MONEY_SUMMARY_AND_HISTORY_MAPPING = "timeAndMoneySummaryAndHistory";
	private static final String TIME_AND_MONEY_MAPPING = "timeAndMoney";
    private static final String OBLIGATED_START_COMMENT = "Obligated Start";
    private static final String OBLIGATED_END_COMMENT = "Obligated End";
    private static final String PROJECT_END_COMMENT = "Project End";
    private static final Integer TEN = 10;
    public static final String AWARD_NUMBER = "awardNumber";
    public static final String TIME_AND_MONEY_DOCUMENT = "TimeAndMoneyDocument";
    public static final String DIRECT_INDIRECT_ENABLED = "1";
    public static final String AWARD_AMOUNT_INFOS = "awardAmountInfos";
    public static final String SINGLE_NODE_MONEY_TRANSACTION_COMMENT = "Single Node Money Transaction";
    public static final String TRANSACTION_SEQUENCE = "SEQ_TRANSACTION_ID";

    private AwardVersionService awardVersionService;
    private TransactionRuleImpl transactionRuleImpl;
    private ActivePendingTransactionsService activePendingTransactionsService;
    private TimeAndMoneyVersionService timeAndMoneyVersionService;
    private SequenceAccessorService sequenceAccessorService;
    private KcWorkflowService kcWorkflowService;
    private ReportTrackingService reportTrackingService;
    private AwardHierarchyService awardHierarchyService;
    private AwardAmountInfoService awardAmountInfoService;
    private TimeAndMoneyHistoryService timeAndMoneyHistoryService;
    private TimeAndMoneyActionSummaryService timeAndMoneyActionSummaryService;
    private GlobalVariableService globalVariableService;
    private DataObjectService dataObjectService;

    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        captureDateChangeTransactions(form);
        captureSingleNodeMoneyTransactions(mapping, form, request, response);
        return super.save(mapping, form, request, response);
    }
    
    private void captureSingleNodeMoneyTransactions(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyForm.getTimeAndMoneyDocument();
        List<TransactionDetail> moneyTransactionDetailItems = new ArrayList<>();
        updateAwardAmountTransactions(timeAndMoneyDocument);
        // Capture amount changes in hierarchy view
        if (timeAndMoneyDocument.getAwardHierarchyNodes().size() == 1 && !timeAndMoneyForm.getDisableCurrentValues()) {
            for(Entry<String, AwardHierarchyNode> awardHierarchyNode : timeAndMoneyDocument.getAwardHierarchyNodes().entrySet()){
                Award award = getAwardVersionService().getWorkingAwardVersion(awardHierarchyNode.getValue().getAwardNumber());
                AwardAmountInfo aai = getAwardAmountInfoService().fetchAwardAmountInfoWithHighestTransactionId(award.getAwardAmountInfos());
                boolean refreshNeeded = inspectAndCaptureAmountChanges(timeAndMoneyForm, aai, award, timeAndMoneyDocument, awardHierarchyNode.getValue());
                getBusinessObjectService().save(award);
                getBusinessObjectService().save(moneyTransactionDetailItems);
                timeAndMoneyDocument.getAward().refreshReferenceObject(AWARD_AMOUNT_INFOS);
                if (refreshNeeded) {
                    refreshView(mapping, timeAndMoneyForm, request, response);
                }
            }
        }   
    }

    protected GlobalVariableService getGlobalVariableService() {
        if (globalVariableService == null) {
            globalVariableService = KcServiceLocator.getService(GlobalVariableService.class);
        }
        return globalVariableService;
    }

    protected DataObjectService getDataObjectService() {
        if (dataObjectService == null) {
            dataObjectService = KcServiceLocator.getService(DataObjectService.class);
        }
        return dataObjectService;
    }

    protected void addPostEntry(Long awardId, String awardNumber, String documentNumber) {
        TimeAndMoneyPosts timeAndMoneyPosts = new TimeAndMoneyPosts();
        timeAndMoneyPosts.setAwardId(awardId);
        timeAndMoneyPosts.setDocumentNumber(documentNumber);
        String awardFamily = awardNumber.substring(0, StringUtils.indexOf(awardNumber, "-"));
        timeAndMoneyPosts.setAwardFamily(awardFamily);
        getDataObjectService().save(timeAndMoneyPosts);
    }

    public ActionForward postTimeAndMoney(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        final TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyForm.getTimeAndMoneyDocument();
        final Award award = timeAndMoneyDocument.getAward();
        addPostEntry(award.getAwardId(), award.getAwardNumber(), timeAndMoneyDocument.getDocumentNumber());
        getGlobalVariableService().getMessageMap().putInfo(KeyConstants.TM_INFORMATION_POSTED, KeyConstants.TM_INFORMATION_POSTED);
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }

    private boolean inspectAndCaptureAmountChanges(TimeAndMoneyForm timeAndMoneyForm, AwardAmountInfo aai, Award award, TimeAndMoneyDocument timeAndMoneyDocument, 
                                                            AwardHierarchyNode awardHierarchyNode) {
        if(isDirectIndirectViewEnabled()){
            return createAndValidateEnabledViewTransaction(timeAndMoneyForm, aai, award, timeAndMoneyDocument, awardHierarchyNode);
        } else {
            return createAndValidateDisabledViewTransaction(timeAndMoneyForm, aai, award, timeAndMoneyDocument, awardHierarchyNode);
        }
    }
    
    private boolean createAndValidateEnabledViewTransaction(TimeAndMoneyForm timeAndMoneyForm, AwardAmountInfo aai, Award award, 
                                                           TimeAndMoneyDocument timeAndMoneyDocument, AwardHierarchyNode ahn) {
        boolean result = false;  // assume no change to totals
        AwardHierarchyNode awardHierarchyNode = timeAndMoneyForm.getAwardHierarchyNodeItems().get(1);
        transactionRuleImpl = new TransactionRuleImpl();
        PendingTransaction pendingTransaction = new PendingTransaction();
        pendingTransaction.setComments(SINGLE_NODE_MONEY_TRANSACTION_COMMENT);
        // total up "current values" from transactions against current values
        ScaleTwoDecimal currentObligatedDirect = aai.getObligatedTotalDirect();
        ScaleTwoDecimal currentObligatedIndirect = aai.getObligatedTotalIndirect();
        ScaleTwoDecimal currentAnticipatedDirect = aai.getAnticipatedTotalDirect();
        ScaleTwoDecimal currentAnticipatedIndirect = aai.getAnticipatedTotalIndirect();
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
        if(timeAndMoneyForm.getTimeAndMoneyDocument().getPendingTransactions().size() == 0 && (!awardHierarchyNode.getObligatedTotalDirect().equals(currentObligatedDirect)||
                !awardHierarchyNode.getObligatedTotalIndirect().equals(currentObligatedIndirect) ||
                    !awardHierarchyNode.getAnticipatedTotalDirect().equals(currentAnticipatedDirect) ||
                        !awardHierarchyNode.getAnticipatedTotalIndirect().equals(currentAnticipatedIndirect))){
            ScaleTwoDecimal obligatedChangeDirect = awardHierarchyNode.getObligatedTotalDirect().subtract(currentObligatedDirect);
            ScaleTwoDecimal obligatedChangeIndirect = awardHierarchyNode.getObligatedTotalIndirect().subtract(currentObligatedIndirect);
            ScaleTwoDecimal anticipatedChangeDirect = awardHierarchyNode.getAnticipatedTotalDirect().subtract(currentAnticipatedDirect);
            ScaleTwoDecimal anticipatedChangeIndirect = awardHierarchyNode.getAnticipatedTotalIndirect().subtract(currentAnticipatedIndirect);
            if(transactionRuleImpl.processParameterEnabledRules(awardHierarchyNode, aai, timeAndMoneyDocument)){
                if (obligatedChangeDirect.isGreaterThan(ScaleTwoDecimal.ZERO)) {
                    pendingTransaction.setSourceAwardNumber(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT);
                    pendingTransaction.setDestinationAwardNumber(award.getAwardNumber());
                }else if (obligatedChangeDirect.isLessThan(ScaleTwoDecimal.ZERO)){
                    pendingTransaction.setSourceAwardNumber(award.getAwardNumber());
                    pendingTransaction.setDestinationAwardNumber(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT);
                }
                if (obligatedChangeIndirect.isGreaterThan(ScaleTwoDecimal.ZERO)) {
                    pendingTransaction.setSourceAwardNumber(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT);
                    pendingTransaction.setDestinationAwardNumber(award.getAwardNumber());
                }else if (obligatedChangeIndirect.isLessThan(ScaleTwoDecimal.ZERO)){
                    pendingTransaction.setSourceAwardNumber(award.getAwardNumber());
                    pendingTransaction.setDestinationAwardNumber(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT);
                }
                if (anticipatedChangeDirect.isGreaterThan(ScaleTwoDecimal.ZERO)) {
                    pendingTransaction.setSourceAwardNumber(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT);
                    pendingTransaction.setDestinationAwardNumber(award.getAwardNumber());
                }else if (anticipatedChangeDirect.isLessThan(ScaleTwoDecimal.ZERO)){
                    pendingTransaction.setSourceAwardNumber(award.getAwardNumber());
                    pendingTransaction.setDestinationAwardNumber(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT);
                }
                if (anticipatedChangeIndirect.isGreaterThan(ScaleTwoDecimal.ZERO)) {
                    pendingTransaction.setSourceAwardNumber(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT);
                    pendingTransaction.setDestinationAwardNumber(award.getAwardNumber());
                }else if (anticipatedChangeIndirect.isLessThan(ScaleTwoDecimal.ZERO)){
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
                                                            TimeAndMoneyDocument timeAndMoneyDocument, AwardHierarchyNode ahn) {
        boolean result = false;  // assume no change to totals
        AwardHierarchyNode awardHierarchyNode = timeAndMoneyForm.getAwardHierarchyNodeItems().get(timeAndMoneyForm.getAwardHierarchyNodeItems().size() - 1);
        transactionRuleImpl = new TransactionRuleImpl();
        PendingTransaction pendingTransaction = new PendingTransaction();
        pendingTransaction.setComments(SINGLE_NODE_MONEY_TRANSACTION_COMMENT);
        pendingTransaction.setSingleNodeTransaction(true);
        // total up "current values" from transactions against current values
        ScaleTwoDecimal currentObligated = aai.getAmountObligatedToDate();
        ScaleTwoDecimal currentAnticipated = aai.getAnticipatedTotalAmount();
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

        /*
            the award hierarchy node loaded is for the current view and is not the updated total. we need to somehow compare to latest
            total. If transactions are present, typically, this total is right so SNT do not
            need to occur. hence checking for empty pending transactions
             */
        if(timeAndMoneyForm.getTimeAndMoneyDocument().getPendingTransactions().size() == 0 && (!awardHierarchyNode.getAmountObligatedToDate().equals(currentObligated)
                || !awardHierarchyNode.getAnticipatedTotalAmount().equals(currentAnticipated))) {
            ScaleTwoDecimal obligatedChange = awardHierarchyNode.getAmountObligatedToDate().subtract(currentObligated);
            ScaleTwoDecimal anticipatedChange = awardHierarchyNode.getAnticipatedTotalAmount().subtract(currentAnticipated);

            if(transactionRuleImpl.processParameterDisabledRules(awardHierarchyNode, aai, timeAndMoneyDocument)){
                if (obligatedChange.isGreaterThan(ScaleTwoDecimal.ZERO)) {
                    pendingTransaction.setSourceAwardNumber(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT);
                    pendingTransaction.setDestinationAwardNumber(award.getAwardNumber());
                }else if (obligatedChange.isLessThan(ScaleTwoDecimal.ZERO)){
                    pendingTransaction.setSourceAwardNumber(award.getAwardNumber());
                    pendingTransaction.setDestinationAwardNumber(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT);
                }
                if (anticipatedChange.isGreaterThan(ScaleTwoDecimal.ZERO)) {
                    pendingTransaction.setSourceAwardNumber(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT);
                    pendingTransaction.setDestinationAwardNumber(award.getAwardNumber());
                }else if (anticipatedChange.isLessThan(ScaleTwoDecimal.ZERO)){
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
        final List<AwardHierarchyNode> awardHierarchyNodeItems = timeAndMoneyForm.getAwardHierarchyNodeItems();
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
        List<TransactionDetail> dateChangeTransactionDetailItems = new ArrayList<>();
        
        updateDocumentFromSession(timeAndMoneyDocument);//not sure if I need to do this.
        updateAwardAmountTransactions(timeAndMoneyDocument);
        for(Entry<String, AwardHierarchyNode> awardHierarchyNode : timeAndMoneyDocument.getAwardHierarchyNodes().entrySet()){
            Award award = getAwardVersionService().getWorkingAwardVersion(awardHierarchyNode.getValue().getAwardNumber());
            int index = findAwardHierarchyNodeIndex(awardHierarchyNode);
            AwardAmountInfo aai = getAwardAmountInfoService().fetchAwardAmountInfoWithHighestTransactionId(award.getAwardAmountInfos());
            boolean needToSaveAward = false;
            needToSaveAward |= inspectAndCaptureCurrentFundEffectiveDateChanges(timeAndMoneyForm, isNoCostExtension, aai, index, award, timeAndMoneyDocument, awardHierarchyNode, dateChangeTransactionDetailItems);
            aai = getAwardAmountInfoService().fetchAwardAmountInfoWithHighestTransactionId(award.getAwardAmountInfos());//get new award amount info if date change transactions have been created.
            needToSaveAward |= inspectAndCaptureObligationExpirationDateChanges(timeAndMoneyForm, isNoCostExtension, aai, index, award, timeAndMoneyDocument, awardHierarchyNode, dateChangeTransactionDetailItems);
            aai = getAwardAmountInfoService().fetchAwardAmountInfoWithHighestTransactionId(award.getAwardAmountInfos());//get new award amount info if date change transactions have been created.
            needToSaveAward |= inspectAndCaptureFinalExpirationDateChanges(timeAndMoneyForm, isNoCostExtension, aai, index, award, timeAndMoneyDocument, awardHierarchyNode, dateChangeTransactionDetailItems);
            //capture any changes of DirectFandADistributions, and add them to the Award working version for persistence.
            if(award.getAwardNumber().equals(timeAndMoneyDocument.getAward().getAwardNumber())) {
                //must use documentService to save the award document. businessObjectService.save() builds deletion award list on T&M doc and we
                //need it to be wired up on AwardDocument so that any deletes from collection will be caught and persisted correctly.
                AwardDocument awardDocument = (AwardDocument) getDocumentService().getByDocumentHeaderId(award.getAwardDocument().getDocumentNumber());
                if (mustSetFandADistributions(awardDocument.getAward().getAwardDirectFandADistributions(),timeAndMoneyDocument.getAward().getAwardDirectFandADistributions())) {
                    awardDocument.getAward().setAwardDirectFandADistributions(timeAndMoneyDocument.getAward().getAwardDirectFandADistributions());
                    getDocumentService().saveDocument(awardDocument);
                    needToSaveAward = true;
                }
            }
            if (needToSaveAward) {
                getBusinessObjectService().save(award);
            }
        }
        //we want to apply save rules to doc before we save any captured changes.
        getBusinessObjectService().save(timeAndMoneyDocument.getAwardAmountTransactions());
        //save all transaction details from No Cost extension date changes.
        getBusinessObjectService().save(dateChangeTransactionDetailItems);
        timeAndMoneyDocument.getAward().refreshReferenceObject(AWARD_AMOUNT_INFOS);//don't think I need to do this.
    }
        
    /**
     * Date changes in hierarchy view are captured here.  If the transaction is a No Cost Extension, we report the transaction
     * details for display in history tab.
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
                TransactionDetail transactionDetail = createTransDetailForDateChanges(aai.getAwardNumber(), aai.getAwardNumber(), aai.getSequenceNumber(), timeAndMoneyDocument.getAwardNumber(),
                        timeAndMoneyDocument.getDocumentNumber(), OBLIGATED_START_COMMENT);
                aai.setTransactionId(transactionDetail.getTransactionId());
                dateChangeTransactionDetailItems.add(transactionDetail);
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
     * Date changes in hierarchy view are captured here.  If the transaction is a No Cost Extension, we report the transaction
     * details for display in history tab.
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
            if (isNoCostExtension && (previousObligationExpirationDate == null || 
                    currentObligationExpirationDate.after(previousObligationExpirationDate))) {
                        aai = getNewAwardAmountInfoForDateChangeTransaction(aai, award, timeAndMoneyDocument.getDocumentNumber());
                        aai.setObligationExpirationDate(currentObligationExpirationDate);
                        awardHierarchyNode.getValue().setObligationExpirationDate(currentObligationExpirationDate);
                        award.getAwardAmountInfos().add(aai);
                TransactionDetail transactionDetail = createTransDetailForDateChanges(aai.getAwardNumber(), aai.getAwardNumber(), aai.getSequenceNumber(), timeAndMoneyDocument.getAwardNumber(),
                        timeAndMoneyDocument.getDocumentNumber(), OBLIGATED_END_COMMENT);
                aai.setTransactionId(transactionDetail.getTransactionId());
                dateChangeTransactionDetailItems.add(transactionDetail);
            }else {
                    aai = getNewAwardAmountInfoForDateChangeTransaction(aai, award, timeAndMoneyDocument.getDocumentNumber());
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
     * Date changes in hierarchy view are captured here.  If the transaction is a No Cost Extension, we report the transaction
     * details for display in history tab.
     */
    protected boolean inspectAndCaptureFinalExpirationDateChanges(TimeAndMoneyForm timeAndMoneyForm, Boolean isNoCostExtension, AwardAmountInfo awardAmountInfo, Integer index,
                                                        Award award, TimeAndMoneyDocument timeAndMoneyDocument, Entry<String, AwardHierarchyNode> awardHierarchyNode,
                                                        List<TransactionDetail> dateChangeTransactionDetailItems) {
       
        boolean needToSave = false;
        if(timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).isPopulatedFromClient()
                && timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getFinalExpirationDate()!=null 
                && !timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getFinalExpirationDate().equals(awardAmountInfo.getFinalExpirationDate())){
          if (isNoCostExtension && 
                  timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getFinalExpirationDate().after(awardAmountInfo.getFinalExpirationDate())) {
                    awardAmountInfo = getNewAwardAmountInfoForDateChangeTransaction(awardAmountInfo, award, timeAndMoneyDocument.getDocumentNumber());
                      awardAmountInfo.setFinalExpirationDate(timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getFinalExpirationDate());
              awardHierarchyNode.getValue().setFinalExpirationDate(timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getFinalExpirationDate());
              award.getAwardAmountInfos().add(awardAmountInfo);
              TransactionDetail transactionDetail = createTransDetailForDateChanges(awardAmountInfo.getAwardNumber(), awardAmountInfo.getAwardNumber(), awardAmountInfo.getSequenceNumber(), timeAndMoneyDocument.getAwardNumber(),
                      timeAndMoneyDocument.getDocumentNumber(), PROJECT_END_COMMENT);
              awardAmountInfo.setTransactionId(transactionDetail.getTransactionId());
              dateChangeTransactionDetailItems.add(transactionDetail);
          }else {
              awardAmountInfo = getNewAwardAmountInfoForDateChangeTransaction(awardAmountInfo, award, timeAndMoneyDocument.getDocumentNumber());
                  awardAmountInfo.setFinalExpirationDate(timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getFinalExpirationDate());
                  awardHierarchyNode.getValue().setFinalExpirationDate(timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getFinalExpirationDate());
                  award.getAwardAmountInfos().add(awardAmountInfo);
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
              timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getFinalExpirationDate().equals(awardAmountInfo.getFinalExpirationDate()) &&
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

    protected TransactionDetail createTransDetailForDateChanges(String sourceAwardNumber, String destinationAwardNumber, Integer sequenceNumber, String currentAwardNumber, String documentNumber,
                                                       String commentsString){
        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setSourceAwardNumber(sourceAwardNumber);
        transactionDetail.setSequenceNumber(sequenceNumber);
        transactionDetail.setDestinationAwardNumber(destinationAwardNumber);
        transactionDetail.setAwardNumber(currentAwardNumber);
        transactionDetail.setTransactionDetailType(TransactionDetailType.DATE.toString());
        transactionDetail.setTransactionId(getSequenceAccessorService().getNextAvailableSequenceNumber(TRANSACTION_SEQUENCE));
        transactionDetail.setTimeAndMoneyDocumentNumber(documentNumber);
        transactionDetail.setComments(commentsString);
        return transactionDetail;
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
        newAwardAmountInfo.setObligatedChangeDirect(ScaleTwoDecimal.ZERO);
        newAwardAmountInfo.setObligatedChangeIndirect(ScaleTwoDecimal.ZERO);
        newAwardAmountInfo.setAnticipatedChangeDirect(ScaleTwoDecimal.ZERO);
        newAwardAmountInfo.setAnticipatedChangeIndirect(ScaleTwoDecimal.ZERO);
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

    private int findAwardHierarchyNodeIndex(Entry<String, AwardHierarchyNode> awardHierarchyNode) {
        final String nodeIndex = awardHierarchyNode.getValue().getAwardNumber().replaceAll("\\d*\\-0*", "");
        return Integer.parseInt(nodeIndex);
    }
    
    /*
     * override route to call save before we route.
     */
    @Override
    public ActionForward route(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward;
        save(mapping, form, request, response);
        actionForward = super.route(mapping, form, request, response);

        return doRoutingTasks(mapping, (TimeAndMoneyForm) form, actionForward);
    }

    protected ActionForward doRoutingTasks(ActionMapping mapping, TimeAndMoneyForm timeAndMoneyForm, ActionForward actionForward) throws ParseException {
        saveReportTrackingItems(timeAndMoneyForm);
        String routeHeaderId = timeAndMoneyForm.getDocument().getDocumentNumber();

        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_AWARD_TIME_AND_MONEY_PAGE, TIME_AND_MONEY_DOCUMENT);
        ActionForward basicForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
        ActionRedirect holdingPageForward = new ActionRedirect(mapping.findForward(KcHoldingPageConstants.MAPPING_HOLDING_PAGE));
        holdingPageForward.addParameter(KcHoldingPageConstants.HOLDING_PAGE_DOCUMENT_ID, routeHeaderId);
        return routeToHoldingPage(basicForward, actionForward, holdingPageForward, returnLocation, routeHeaderId);
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

        return doRoutingTasks(mapping, (TimeAndMoneyForm) form, actionForward);
    }



    /**
     * must remove all award amount infos corresponding to this document.  Date changes create and add new Award Amount Info.  Pending Transactions
     * do not create new Award Amount Info until the document is routed or blanket approved.
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#cancel(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
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
            Award award = getAwardVersionService().getWorkingAwardVersion(awardHierarchy.getAwardNumber());
            List<AwardAmountInfo> deleteCollection = new ArrayList<>();
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
        timeAndMoneyDocument.setDocumentStatus(VersionStatus.CANCELED.toString());
        getBusinessObjectService().save(timeAndMoneyDocument);
        actionForward = super.cancel(mapping, form, request, response);   
        
        return actionForward;
    }

    /**
     * 
     * This method refreshes the view depending on various view options like either active or pending view or dates only, totals and 
     * distributed/distributable.
     */
    public ActionForward refreshView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        TimeAndMoneyDocument doc = timeAndMoneyForm.getTimeAndMoneyDocument();
        //perform this logic if pending view
        if(StringUtils.equalsIgnoreCase(timeAndMoneyForm.getCurrentOrPendingView(), TimeAndMoneyForm.PENDING)){
            
            Map<String, AwardAmountTransaction> awardAmountTransactionItems = new HashMap<>();
            List<Award> awardItems = new ArrayList<>();
            List<TransactionDetail> transactionDetailItems = new ArrayList<>();
            
            updateDocumentFromSession(doc);

            //added refreshFlag boolean to service method. If doing a refresh, we don't want to reset the processed flag.  Only when T&M doc is routed for approval.
            getActivePendingTransactionsService().processTransactions(doc, doc.getAwardAmountTransactions().get(0), awardAmountTransactionItems, awardItems, transactionDetailItems, true);
            GlobalVariables.getUserSession().addObject(GlobalVariables.getUserSession().getKualiSessionId()+Constants.TIME_AND_MONEY_DOCUMENT_STRING_FOR_SESSION, doc);
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

    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyForm.getTimeAndMoneyDocument();

        ActionForward forward = handleDocument(mapping, form, request, response, timeAndMoneyForm);

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
    
    public ActionForward addTransaction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        captureDateChangeTransactions(form);
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        if (timeAndMoneyForm.getTransactionBean().addPendingTransactionItem()) {
            timeAndMoneyForm.setToPendingView();
            refreshView(mapping, form, request, response);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);        
    }
    

    public ActionForward deleteTransaction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        captureDateChangeTransactions(form);
        ((TimeAndMoneyForm) form).getTransactionBean().deletePendingTransactionItem(getLineToDelete(request));
        refreshView(mapping, form, request, response);
        return mapping.findForward(Constants.MAPPING_BASIC);        
    }
    
    /**
     * This method switches the award for history, summary and Action Summary panels.
     */
    public ActionForward switchAward(ActionMapping mapping, ActionForm form , HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm)form;
        String goToAwardNumber = timeAndMoneyForm.getGoToAwardNumber();
        
        populateOtherPanels(timeAndMoneyForm.getTransactionBean().getNewAwardAmountTransaction(), timeAndMoneyForm, goToAwardNumber);
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward timeAndMoneySummaryAndHistory(ActionMapping mapping, ActionForm form , HttpServletRequest request, HttpServletResponse response) throws Exception {
    	TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
    	TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyForm.getTimeAndMoneyDocument();
    	String awardNumber = null;
    	if (StringUtils.isBlank(timeAndMoneyForm.getGoToAwardNumber())) {
    		awardNumber = timeAndMoneyDocument.getAwardNumber();
    	} else {
    		Award award = getAwardVersionService().getWorkingAwardVersion(timeAndMoneyForm.getGoToAwardNumber());
	        if (award == null) {
	            GlobalVariables.getMessageMap().putError(GO_TO_AWARD_NUMBER_FIELD_NAME, INVALID_AWARD_NUMBER_ERROR, timeAndMoneyForm.getGoToAwardNumber());
	            return mapping.findForward(Constants.MAPPING_BASIC);
	        }
	        awardNumber = award.getAwardNumber();
    	}

        timeAndMoneyDocument.setAwardVersionHistoryList(getTimeAndMoneyHistoryService().buildTimeAndMoneyHistoryObjects(awardNumber, true));
        timeAndMoneyDocument.setTimeAndMoneyActionSummaryItems(getTimeAndMoneyActionSummaryService().populateActionSummary(awardNumber));

    	return mapping.findForward(TIME_AND_MONEY_SUMMARY_AND_HISTORY_MAPPING);
    }

    /*
     * This method populates Summary, Action Summary and History panels for selected award.
     */
    private void populateOtherPanels(AwardAmountTransaction newAwardAmountTransaction, TimeAndMoneyForm timeAndMoneyForm, String goToAwardNumber)
            throws LookupException, SQLException, WorkflowException {
        Award award = getAwardVersionService().getWorkingAwardVersion(goToAwardNumber);
        if (award == null) {
            GlobalVariables.getMessageMap().putError(GO_TO_AWARD_NUMBER_FIELD_NAME, INVALID_AWARD_NUMBER_ERROR, goToAwardNumber);
            return;
        }
        TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyForm.getTimeAndMoneyDocument();
        timeAndMoneyDocument.setAwardNumber(award.getAwardNumber());
        timeAndMoneyDocument.setAward(award);
        timeAndMoneyDocument.setNewAwardAmountTransaction(newAwardAmountTransaction);
    }
    
    /**
     * This method tests if the award is new by checking the size of AwardDirectFandADistributions on the Award.
     */
    public boolean isNewAward(TimeAndMoneyForm timeAndMoneyForm) {
        return timeAndMoneyForm.getTimeAndMoneyDocument().getAward().getAwardDirectFandADistributions().size() == 0;
    }

    /**
     * 
     * This method builds the string for the ActionForward
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
     */
    public ActionForward deleteAwardDirectFandADistribution(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        captureDateChangeTransactions(form);
        AwardDirectFandADistribution awardDirectFandADistribution = timeAndMoneyForm.getTimeAndMoneyDocument().getAward().getAwardDirectFandADistributions().get(getLineToDelete(request));
        timeAndMoneyForm.getTimeAndMoneyDocument().getAward().getAwardDirectFandADistributions().remove(getLineToDelete(request));
        getBusinessObjectService().delete(awardDirectFandADistribution);
        timeAndMoneyForm.getAwardDirectFandADistributionBean().updateBudgetPeriodsAfterDelete(timeAndMoneyForm.getTimeAndMoneyDocument().getAward().getAwardDirectFandADistributions());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is used to recalculate the Total amounts in the Direct F and A Distribution panel.
     */
    public ActionForward timeAndMoney(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
       
        return mapping.findForward(TIME_AND_MONEY_MAPPING);
    }
    
    /**
     * This method is used to recalculate the Total amounts in the Direct F and A Distribution panel.
     */
    public ActionForward recalculateDirectFandADistributionTotals(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
       
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * 
     * This method retrieves the awardDocument from the session and redirects the user to the appropriate Award.
     */
    public ActionForward returnToAward(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyForm.getTimeAndMoneyDocument();
        if(!getKcWorkflowService().isInWorkflow(timeAndMoneyDocument)){
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
    
    public boolean isDirectIndirectViewEnabled() {
        String directIndirectEnabledValue = getParameterService().getParameterValueAsString(Constants.PARAMETER_MODULE_AWARD, ParameterConstants.DOCUMENT_COMPONENT, "ENABLE_AWD_ANT_OBL_DIRECT_INDIRECT_COST");
        return DIRECT_INDIRECT_ENABLED.equals(directIndirectEnabledValue);
    }

    public ActionForward goToNextAward(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        return goToAward(mapping, timeAndMoneyForm, timeAndMoneyForm.getNextNodeMap().get(timeAndMoneyForm.getAwardForSummaryPanelDisplay().getAwardNumber()));

    }
        
    public ActionForward goToPreviousAward(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        return goToAward(mapping, timeAndMoneyForm, timeAndMoneyForm.getPreviousNodeMap().get(timeAndMoneyForm.getAwardForSummaryPanelDisplay().getAwardNumber()));
    }

    private ActionForward goToAward(ActionMapping mapping, TimeAndMoneyForm timeAndMoneyForm, String awardNumber) throws Exception {
        Award awardForSummary = ((List<Award>)getBusinessObjectService().findMatching(Award.class, Collections.singletonMap(AWARD_NUMBER, awardNumber))).get(0);
        timeAndMoneyForm.setAwardForSummaryPanelDisplay(awardForSummary);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward editOrVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm)form;
        TimeAndMoneyDocument doc = timeAndMoneyForm.getTimeAndMoneyDocument();
        String rootAwardNumber = doc.getRootAwardNumber();
        TimeAndMoneyDocument finalTandM = getTimeAndMoneyVersionService().findOpenedTimeAndMoney(rootAwardNumber);
        if (finalTandM == null) {
        	return mapping.findForward(Constants.MAPPING_BASIC);
        } else {
	        String routeHeaderId = finalTandM.getDocumentHeader().getWorkflowDocument().getDocumentId();
	        String returnAwardDocId = (String) GlobalVariables.getUserSession().retrieveObject(Constants.AWARD_DOCUMENT_STRING_FOR_SESSION + "-" + doc.getDocumentNumber());
	        GlobalVariables.getUserSession().addObject(Constants.AWARD_DOCUMENT_STRING_FOR_SESSION + "-" + routeHeaderId, returnAwardDocId);
	        String forwardString = buildForwardUrl(routeHeaderId);
	        return new ActionForward(forwardString, true);
        }
    }

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
            
            Set<String> editModes = new HashSet<>();
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

    public SequenceAccessorService getSequenceAccessorService() {
        if (sequenceAccessorService == null) {
            sequenceAccessorService = KcServiceLocator.getService(SequenceAccessorService.class);
        }
        return sequenceAccessorService;
    }

    public void setSequenceAccessorService(SequenceAccessorService sequenceAccessorService) {
        this.sequenceAccessorService = sequenceAccessorService;
    }

    public AwardVersionService getAwardVersionService() {
        if(awardVersionService == null) {
            awardVersionService = KcServiceLocator.getService(AwardVersionService.class);
        }
        return awardVersionService;
    }

    protected ActivePendingTransactionsService getActivePendingTransactionsService(){
        if (activePendingTransactionsService == null) {
            activePendingTransactionsService = KcServiceLocator.getService(ActivePendingTransactionsService.class);
        }
        return activePendingTransactionsService;
    }

    protected KcWorkflowService getKcWorkflowService() {
        if (kcWorkflowService == null) {
            kcWorkflowService = KcServiceLocator.getService(KcWorkflowService.class);
        }
        return kcWorkflowService;
    }

    public ReportTrackingService getReportTrackingService() {
        if (reportTrackingService == null) {
            reportTrackingService = KcServiceLocator.getService(ReportTrackingService.class);
        }
        return reportTrackingService;
    }

    protected AwardHierarchyService getAwardHierarchyService(){
        if (awardHierarchyService == null) {
            awardHierarchyService = KcServiceLocator.getService(AwardHierarchyService.class);
        }
        return awardHierarchyService;
    }

    public AwardAmountInfoService getAwardAmountInfoService() {
        if (awardAmountInfoService == null) {
            awardAmountInfoService = KcServiceLocator.getService(AwardAmountInfoService.class);
        }
        return awardAmountInfoService;
    }

    public TimeAndMoneyHistoryService getTimeAndMoneyHistoryService() {
        if (timeAndMoneyHistoryService == null) {
            timeAndMoneyHistoryService = KcServiceLocator.getService(TimeAndMoneyHistoryService.class);
        }
        return timeAndMoneyHistoryService;
    }

    public TimeAndMoneyActionSummaryService getTimeAndMoneyActionSummaryService() {
        if (timeAndMoneyActionSummaryService == null) {
            timeAndMoneyActionSummaryService = KcServiceLocator.getService(TimeAndMoneyActionSummaryService.class);
        }
        return timeAndMoneyActionSummaryService;
    }
}

