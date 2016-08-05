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
package org.kuali.kra.timeandmoney.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.kra.award.AwardAmountInfoService;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyService;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.service.AwardDirectFandADistributionService;
import org.kuali.kra.award.timeandmoney.AwardDirectFandADistribution;
import org.kuali.kra.award.version.service.AwardVersionService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.history.TransactionDetail;
import org.kuali.kra.timeandmoney.history.TransactionDetailType;
import org.kuali.kra.timeandmoney.service.TimeAndMoneyService;
import org.kuali.kra.timeandmoney.transactions.AwardAmountTransaction;
import org.kuali.kra.timeandmoney.transactions.PendingTransaction;
import org.kuali.kra.timeandmoney.transactions.TransactionRuleImpl;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("timeAndMoneyService")
public class TimeAndMoneyServiceImpl implements TimeAndMoneyService {

    @Autowired
    @Qualifier("documentService")
    private DocumentService documentService;
    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;
    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;
    @Autowired
    @Qualifier("awardDirectFandADistributionService")
    private AwardDirectFandADistributionService awardDirectFandADistributionService;
    @Autowired
    @Qualifier("awardVersionService")
    private AwardVersionService awardVersionService;
    @Autowired
    @Qualifier("awardHierarchyService")
    private AwardHierarchyService awardHierarchyService;
    @Autowired
    @Qualifier("awardAmountInfoService")
    private AwardAmountInfoService awardAmountInfoService;
    @Autowired
    @Qualifier("sequenceAccessorService")
    private SequenceAccessorService sequenceAccessorService;

    private static Log LOG = LogFactory.getLog(TimeAndMoneyServiceImpl.class);

    private static final String TIMEANDMONEY_DOCUMENT = "timeandmoney document";
    private static final String ROOT_AWARD = "000000-00000";
    private static final String INITIAL_TRANSACTION_COMMENT = "Initial Time And Money creation transaction";
    private static final String ENABLE_AWARD_ANT_OBL_DIRECT_INDIRECT_COST_TRUE = "1";
    public static final String ENABLE_AWD_ANT_OBL_DIRECT_INDIRECT_COST = "ENABLE_AWD_ANT_OBL_DIRECT_INDIRECT_COST";
    public static final String AWARD_AMOUNT_INFOS = "awardAmountInfos";
    private static final String PROJECT_END_COMMENT = "Project End";
    private static final String OBLIGATED_END_COMMENT = "Obligated End";
    private static final String OBLIGATED_START_COMMENT = "Obligated Start";
    private static final Integer NO_COST_EXTENSION_CODE = 10;
    public static final String SINGLE_NODE_MONEY_TRANSACTION_COMMENT = "Single Node Money Transaction";
    public static final String TRANSACTION_SEQUENCE = "SEQ_TRANSACTION_ID";
    private TransactionRuleImpl transactionRuleImpl;


    public boolean isDirectIndirectViewEnabled() {
        boolean returnValue = false;
        String directIndirectEnabledValue = parameterService.getParameterValueAsString(Constants.PARAMETER_MODULE_AWARD,
                ParameterConstants.DOCUMENT_COMPONENT, ENABLE_AWD_ANT_OBL_DIRECT_INDIRECT_COST);
        if(directIndirectEnabledValue.equals(ENABLE_AWARD_ANT_OBL_DIRECT_INDIRECT_COST_TRUE)) {
            returnValue = true;
        }
        return returnValue;
    }

    private void addNewAwardAmountInfoForInitialTransaction(Award rootAward, String documentNumber) {

        AwardAmountInfo rootAwardAmountInfo = rootAward.getLastAwardAmountInfo();
        AwardAmountInfo newAwardAmountInfo = new AwardAmountInfo();
        newAwardAmountInfo.setAwardNumber(rootAward.getAwardNumber());
        newAwardAmountInfo.setSequenceNumber(rootAward.getSequenceNumber());
        newAwardAmountInfo.setFinalExpirationDate(rootAwardAmountInfo.getFinalExpirationDate());
        newAwardAmountInfo.setCurrentFundEffectiveDate(rootAwardAmountInfo.getCurrentFundEffectiveDate());
        newAwardAmountInfo.setObligationExpirationDate(rootAwardAmountInfo.getObligationExpirationDate());
        newAwardAmountInfo.setTimeAndMoneyDocumentNumber(documentNumber);
        newAwardAmountInfo.setTransactionId(null);
        newAwardAmountInfo.setAward(rootAward);
        //add transaction amounts to the AmountInfo
        if(isDirectIndirectViewEnabled()){
            newAwardAmountInfo.setAmountObligatedToDate(rootAward.getObligatedTotalDirect().add(rootAward.getObligatedTotalIndirect()));
            newAwardAmountInfo.setObligatedTotalDirect(rootAward.getObligatedTotalDirect());
            newAwardAmountInfo.setObligatedTotalIndirect(rootAward.getObligatedTotalIndirect());
            newAwardAmountInfo.setAnticipatedTotalAmount(rootAward.getAnticipatedTotalDirect().add(rootAward.getAnticipatedTotalIndirect()));
            newAwardAmountInfo.setAnticipatedTotalDirect(rootAward.getAnticipatedTotalDirect());
            newAwardAmountInfo.setAnticipatedTotalIndirect(rootAward.getAnticipatedTotalIndirect());
            newAwardAmountInfo.setObliDistributableAmount(rootAward.getObligatedTotalDirect().add(rootAward.getObligatedTotalIndirect()));
            newAwardAmountInfo.setAntDistributableAmount(rootAward.getAnticipatedTotalDirect().add(rootAward.getAnticipatedTotalIndirect()));
        } else {
            newAwardAmountInfo.setAmountObligatedToDate(rootAwardAmountInfo.getAmountObligatedToDate());
            newAwardAmountInfo.setObligatedTotalDirect(rootAward.getObligatedTotalDirect());
            newAwardAmountInfo.setObligatedTotalIndirect(rootAward.getObligatedTotalIndirect());
            newAwardAmountInfo.setAnticipatedTotalAmount(rootAward.getAnticipatedTotal());
            newAwardAmountInfo.setAnticipatedTotalDirect(rootAward.getAnticipatedTotalDirect());
            newAwardAmountInfo.setAnticipatedTotalIndirect(rootAward.getAnticipatedTotalIndirect());
            newAwardAmountInfo.setObliDistributableAmount(rootAward.getObligatedTotal());
            newAwardAmountInfo.setAntDistributableAmount(rootAward.getAnticipatedTotal());
        }
        newAwardAmountInfo.setOriginatingAwardVersion(rootAward.getSequenceNumber());
        rootAward.getAwardAmountInfos().add(newAwardAmountInfo);
        businessObjectService.save(rootAward);
    }

    public TimeAndMoneyDocument setupTimeAndMoneyDocument(String rootAwardNumber, Award currentAward) throws WorkflowException {
        Award rootAward = awardVersionService.getWorkingAwardVersion(rootAwardNumber);
        generateDirectFandADistribution(currentAward);
        TimeAndMoneyDocument timeAndMoneyDocument = (TimeAndMoneyDocument) documentService.getNewDocument(TimeAndMoneyDocument.class);
        timeAndMoneyDocument.getDocumentHeader().setDocumentDescription(TIMEANDMONEY_DOCUMENT);
        timeAndMoneyDocument.setRootAwardNumber(rootAwardNumber);
        timeAndMoneyDocument.setAwardNumber(rootAward.getAwardNumber());
        timeAndMoneyDocument.setAward(rootAward);
        AwardAmountTransaction aat = new AwardAmountTransaction();
        aat.setAwardNumber(ROOT_AWARD);//need to initialize one element in this collection because the doc is saved on creation.
        aat.setDocumentNumber(timeAndMoneyDocument.getDocumentNumber());
        String defaultTxnTypeStr = parameterService.getParameterValueAsString(Constants.MODULE_NAMESPACE_TIME_AND_MONEY, ParameterConstants.DOCUMENT_COMPONENT, Constants.DEFAULT_TXN_TYPE_COPIED_AWARD);
        if(StringUtils.isNotEmpty(defaultTxnTypeStr)) {
            aat.setTransactionTypeCode(Integer.parseInt(defaultTxnTypeStr));
        }
        aat.setAwardNumber(rootAward.getAwardNumber());
        //any code for initial transaction and history.
        TransactionDetail transactionDetail  = addTransactionDetails(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT, rootAward.getAwardNumber(), rootAward.getSequenceNumber(),
                timeAndMoneyDocument.getDocumentNumber(), INITIAL_TRANSACTION_COMMENT, rootAward);
        //need this check so we don't add additional AAI object if Award has been copied and then creating first T&M doc.
        if(rootAward.getAwardAmountInfos().size() < 2) {
            addNewAwardAmountInfoForInitialTransaction(rootAward, timeAndMoneyDocument.getDocumentNumber());
        } else {
            rootAward.getLastAwardAmountInfo().setTimeAndMoneyDocumentNumber(timeAndMoneyDocument.getDocumentNumber());
            businessObjectService.save(rootAward);
        }
        timeAndMoneyDocument.getAwardAmountTransactions().add(aat);
        documentService.saveDocument(timeAndMoneyDocument);
        businessObjectService.save(transactionDetail);
        return timeAndMoneyDocument;
    }


    protected TransactionDetail addTransactionDetails(String sourceAwardNumber, String destinationAwardNumber, Integer sequenceNumber, String documentNumber,
                                                      String commentsString, Award rootAward){
        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setSourceAwardNumber(sourceAwardNumber);
        transactionDetail.setSequenceNumber(sequenceNumber);
        transactionDetail.setDestinationAwardNumber(destinationAwardNumber);
        if(isDirectIndirectViewEnabled()){
            transactionDetail.setAnticipatedAmount(rootAward.getAnticipatedTotalDirect().add(rootAward.getAnticipatedTotalIndirect()));
            transactionDetail.setAnticipatedDirectAmount(rootAward.getAnticipatedTotalDirect());
            transactionDetail.setAnticipatedIndirectAmount(rootAward.getAnticipatedTotalIndirect());
            transactionDetail.setObligatedAmount(rootAward.getObligatedTotalDirect().add(rootAward.getObligatedTotalIndirect()));
            transactionDetail.setObligatedDirectAmount(rootAward.getObligatedTotalDirect());
            transactionDetail.setObligatedIndirectAmount(rootAward.getObligatedTotalIndirect());
        } else {
            transactionDetail.setAnticipatedAmount(rootAward.getAnticipatedTotal());
            transactionDetail.setAnticipatedDirectAmount(rootAward.getAnticipatedTotal());
            transactionDetail.setAnticipatedIndirectAmount(new ScaleTwoDecimal(0));
            transactionDetail.setObligatedAmount(rootAward.getObligatedTotal());
            transactionDetail.setObligatedDirectAmount(rootAward.getObligatedTotal());
            transactionDetail.setObligatedIndirectAmount(new ScaleTwoDecimal(0));
        }
        transactionDetail.setAwardNumber(rootAward.getAwardNumber());
        transactionDetail.setTransactionId(0L);
        transactionDetail.setTimeAndMoneyDocumentNumber(documentNumber);
        transactionDetail.setComments(commentsString);
        transactionDetail.setTransactionDetailType(TransactionDetailType.PRIMARY.toString());
        return transactionDetail;

    }

    protected void generateDirectFandADistribution(Award award) {
        if(award.getAwardEffectiveDate() != null) {
            // delete entries that were added during previous T&M initiations but the doc cancelled.
            businessObjectService.delete(award.getAwardDirectFandADistributions());

            Boolean autoGenerate = parameterService.getParameterValueAsBoolean(Constants.PARAMETER_MODULE_AWARD, ParameterConstants.DOCUMENT_COMPONENT,
                    KeyConstants.AUTO_GENERATE_TIME_MONEY_FUNDS_DIST_PERIODS);
            if (autoGenerate) {
                award.setAwardDirectFandADistributions(awardDirectFandADistributionService.
                        generateDefaultAwardDirectFandADistributionPeriods(award));
            }
        }
    }

    public Award populateAwardHierarchyItems(TimeAndMoneyDocument timeAndMoneyDocument, String rootAwardNumber, List<String> order) {
        timeAndMoneyDocument.setAwardHierarchyItems(awardHierarchyService.getAwardHierarchy(rootAwardNumber, order));
        timeAndMoneyDocument.setAwardNumber(rootAwardNumber);

        Award tmpAward = getCurrentAward(timeAndMoneyDocument);
        if(tmpAward != null) {
            awardHierarchyService.populateAwardHierarchyNodesForTandMDoc(
                    timeAndMoneyDocument.getAwardHierarchyItems(), timeAndMoneyDocument.getAwardHierarchyNodes(),
                    tmpAward.getAwardNumber(), tmpAward.getSequenceNumber().toString(),
                    timeAndMoneyDocument);
        } else {
            awardHierarchyService.populateAwardHierarchyNodesForTandMDoc(
                    timeAndMoneyDocument.getAwardHierarchyItems(), timeAndMoneyDocument.getAwardHierarchyNodes(),
                    null, null,
                    timeAndMoneyDocument);
        }
        return tmpAward;
    }

    private Award getCurrentAward(TimeAndMoneyDocument timeAndMoneyDocument) {
        Award tmpAward = timeAndMoneyDocument.getAward();
        if(tmpAward == null) {
            tmpAward = awardVersionService.getWorkingAwardVersion(timeAndMoneyDocument.getAwardNumber());
        }

        return tmpAward;
    }


    private int findAwardHierarchyNodeIndex(Map.Entry<String, AwardHierarchyNode> awardHierarchyNode) {
        final String nodeIndex = awardHierarchyNode.getValue().getAwardNumber().replaceAll("\\d*\\-0*", "");
        return Integer.parseInt(nodeIndex);
    }

    public void captureDateChangeTransactions(TimeAndMoneyDocument timeAndMoneyDocument, List<AwardHierarchyNode> awardHierarchyNodeItems) throws WorkflowException {
        //save rules have not been applied yet so there needs to be a null check on transaction type code before testing the value.
        boolean isNoCostExtension;
        if (timeAndMoneyDocument.getAwardAmountTransactions().get(0).getTransactionTypeCode() == null) {
            isNoCostExtension = false;
        }else {
            isNoCostExtension = timeAndMoneyDocument.getAwardAmountTransactions().get(0).getTransactionTypeCode().equals(NO_COST_EXTENSION_CODE);//Transaction type code for No Cost Extension
        }
        //if Dates have changed in a node in hierarchy view and the Transaction Type is a No Cost Extension,
        //we need to record this as a transaction in history.
        //build the transaction and add to this list for persistence later.
        List<TransactionDetail> dateChangeTransactionDetailItems = new ArrayList<>();

        updateDocumentFromSession(timeAndMoneyDocument);//not sure if I need to do this.
        updateAwardAmountTransactions(timeAndMoneyDocument);
        for(Map.Entry<String, AwardHierarchyNode> awardHierarchyNode : timeAndMoneyDocument.getAwardHierarchyNodes().entrySet()){
            Award award = awardVersionService.getWorkingAwardVersion(awardHierarchyNode.getValue().getAwardNumber());
            int index = findAwardHierarchyNodeIndex(awardHierarchyNode);
            AwardAmountInfo aai = awardAmountInfoService.fetchAwardAmountInfoWithHighestTransactionId(award.getAwardAmountInfos());
            boolean needToSaveAward = false;
            needToSaveAward |= inspectAndCaptureCurrentFundEffectiveDateChanges(awardHierarchyNodeItems, isNoCostExtension, aai, index, award,
                    timeAndMoneyDocument, awardHierarchyNode, dateChangeTransactionDetailItems);
            aai = awardAmountInfoService.fetchAwardAmountInfoWithHighestTransactionId(award.getAwardAmountInfos());//get new award amount info if date change transactions have been created.
            needToSaveAward |= inspectAndCaptureObligationExpirationDateChanges(awardHierarchyNodeItems, isNoCostExtension, aai, index, award,
                    timeAndMoneyDocument, awardHierarchyNode, dateChangeTransactionDetailItems);
            aai = awardAmountInfoService.fetchAwardAmountInfoWithHighestTransactionId(award.getAwardAmountInfos());//get new award amount info if date change transactions have been created.
            needToSaveAward |= inspectAndCaptureFinalExpirationDateChanges(awardHierarchyNodeItems, isNoCostExtension, aai, index, award,
                    timeAndMoneyDocument, awardHierarchyNode, dateChangeTransactionDetailItems);
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
                businessObjectService.save(award);
            }
        }

        businessObjectService.save(timeAndMoneyDocument.getAwardAmountTransactions());
        businessObjectService.save(dateChangeTransactionDetailItems);
        timeAndMoneyDocument.getAward().refreshReferenceObject(AWARD_AMOUNT_INFOS);//don't think I need to do this.
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

    /**
     * Date changes in hierarchy view are captured here.  If the transaction is a No Cost Extension, we report the transaction
     * details for display in history tab.
     */
    protected boolean inspectAndCaptureFinalExpirationDateChanges(final List<AwardHierarchyNode> awardHierarchyNodeItems, Boolean isNoCostExtension, AwardAmountInfo awardAmountInfo, Integer index,
                                                                  Award award, TimeAndMoneyDocument timeAndMoneyDocument, Map.Entry<String, AwardHierarchyNode> awardHierarchyNode,
                                                                  List<TransactionDetail> dateChangeTransactionDetailItems) {

        boolean needToSave = false;
        if(awardHierarchyNodeItems.get(index).isPopulatedFromClient()
                && awardHierarchyNodeItems.get(index).getFinalExpirationDate()!=null
                && !awardHierarchyNodeItems.get(index).getFinalExpirationDate().equals(awardAmountInfo.getFinalExpirationDate())){
            if (isNoCostExtension &&
                    awardHierarchyNodeItems.get(index).getFinalExpirationDate().after(awardAmountInfo.getFinalExpirationDate())) {
                awardAmountInfo = getNewAwardAmountInfoForDateChangeTransaction(awardAmountInfo, award, timeAndMoneyDocument.getDocumentNumber());
                awardAmountInfo.setFinalExpirationDate(awardHierarchyNodeItems.get(index).getFinalExpirationDate());
                awardHierarchyNode.getValue().setFinalExpirationDate(awardHierarchyNodeItems.get(index).getFinalExpirationDate());
                award.getAwardAmountInfos().add(awardAmountInfo);
                TransactionDetail transactionDetail = createTransDetailForDateChanges(awardAmountInfo.getAwardNumber(), awardAmountInfo.getAwardNumber(), awardAmountInfo.getSequenceNumber(), timeAndMoneyDocument.getAwardNumber(),
                        timeAndMoneyDocument.getDocumentNumber(), PROJECT_END_COMMENT);
                awardAmountInfo.setTransactionId(transactionDetail.getTransactionId());
                dateChangeTransactionDetailItems.add(transactionDetail);
            }else {
                awardAmountInfo = getNewAwardAmountInfoForDateChangeTransaction(awardAmountInfo, award, timeAndMoneyDocument.getDocumentNumber());
                awardAmountInfo.setFinalExpirationDate(awardHierarchyNodeItems.get(index).getFinalExpirationDate());
                awardHierarchyNode.getValue().setFinalExpirationDate(awardHierarchyNodeItems.get(index).getFinalExpirationDate());
                award.getAwardAmountInfos().add(awardAmountInfo);
            }
            needToSave = true;
        } else if (awardHierarchyNodeItems.get(index).isPopulatedFromClient()
                && awardHierarchyNodeItems.get(index).getFinalExpirationDate() == null) {
            //FYI, this will show an erorr to the user, we are doing this such they can see the error, and that they had put in a null value
            awardHierarchyNode.getValue().setFinalExpirationDate(null);
        }
        //special case where a user can enter an invalid date that will throw a hard error.  If the user tries to change that date back
        //to the original date, we need to capture that and change the value on the document which is the date value that gets validated
        //in save rules.
        if(awardHierarchyNodeItems.get(index).getCurrentFundEffectiveDate()!=null &&
                awardHierarchyNodeItems.get(index).getFinalExpirationDate() != null &&
                awardHierarchyNodeItems.get(index).getFinalExpirationDate().equals(awardAmountInfo.getFinalExpirationDate()) &&
                !awardHierarchyNodeItems.get(index).getFinalExpirationDate().equals(awardHierarchyNode.getValue().getFinalExpirationDate())) {
            awardHierarchyNode.getValue().setFinalExpirationDate(awardHierarchyNodeItems.get(index).getFinalExpirationDate());
        }
        return needToSave;
    }

    public TransactionDetail createTransDetailForDateChanges(String sourceAwardNumber, String destinationAwardNumber, Integer sequenceNumber, String currentAwardNumber, String documentNumber,
                                                                String commentsString){
        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setSourceAwardNumber(sourceAwardNumber);
        transactionDetail.setSequenceNumber(sequenceNumber);
        transactionDetail.setDestinationAwardNumber(destinationAwardNumber);
        transactionDetail.setAwardNumber(currentAwardNumber);
        transactionDetail.setTransactionDetailType(TransactionDetailType.DATE.toString());
        transactionDetail.setTransactionId((sequenceAccessorService.getNextAvailableSequenceNumber(TRANSACTION_SEQUENCE)));
        transactionDetail.setTimeAndMoneyDocumentNumber(documentNumber);
        transactionDetail.setComments(commentsString);
        return transactionDetail;
    }

    /*
    * add money to amount info Totals, and Distributables.
    *
    */
    public AwardAmountInfo getNewAwardAmountInfoForDateChangeTransaction(AwardAmountInfo awardAmountInfo,  Award award, String documentNumber) {

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

    /**
     * Date changes in hierarchy view are captured here.  If the transaction is a No Cost Extension, we report the transaction
     * details for display in history tab.
     */
    protected boolean inspectAndCaptureObligationExpirationDateChanges(List<AwardHierarchyNode> awardHierarchyNodeItems, Boolean isNoCostExtension, AwardAmountInfo aai, Integer index,
                                                                       Award award, TimeAndMoneyDocument timeAndMoneyDocument, Map.Entry<String, AwardHierarchyNode> awardHierarchyNode,
                                                                       List<TransactionDetail> dateChangeTransactionDetailItems) {
        boolean needToSave = false;
        Date previousObligationExpirationDate = aai.getObligationExpirationDate();
        Date currentObligationExpirationDate = awardHierarchyNodeItems.get(index).getObligationExpirationDate();

        if(awardHierarchyNodeItems.get(index).isPopulatedFromClient()
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
        } else if (awardHierarchyNodeItems.get(index).isPopulatedFromClient()
                && currentObligationExpirationDate == null) {
            //FYI, this will show an error to the user, we are doing this such they can see the error, and that they had put in a null value
            awardHierarchyNode.getValue().setObligationExpirationDate(null);
        }

        //special case where a user can enter an invalid date that will throw a hard error.  If the user tries to change that date back
        //to the original date, we need to capture that and change the value on the document which is the date value that gets validated
        //in save rules.
        if(awardHierarchyNodeItems.get(index).getCurrentFundEffectiveDate()!=null &&
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
    protected boolean inspectAndCaptureCurrentFundEffectiveDateChanges(List<AwardHierarchyNode> awardHierarchyNodeItems, Boolean isNoCostExtension, AwardAmountInfo aai, Integer index,
                                                                       Award award, TimeAndMoneyDocument timeAndMoneyDocument, Map.Entry<String, AwardHierarchyNode> awardHierarchyNode,
                                                                       List<TransactionDetail> dateChangeTransactionDetailItems) {

        Date currentEffectiveDate = awardHierarchyNodeItems.get(index).getCurrentFundEffectiveDate();
        Date previousEffectiveDate = aai.getCurrentFundEffectiveDate();
        boolean needToSave = false;
        if(awardHierarchyNodeItems.get(index).isPopulatedFromClient()
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
        } else if (awardHierarchyNodeItems.get(index).isPopulatedFromClient() && currentEffectiveDate == null) {
            //FYI, this will show an erorr to the user, we are doing this such they can see the error, and that they had put in a null value
            awardHierarchyNode.getValue().setCurrentFundEffectiveDate(null);
        }
        //special case where a user can enter an invalid date that will throw a hard error.  If the user tries to change that date back
        //to the original date, we need to capture that and change the value on the document which is the date value that gets validated
        //in save rules.
        if(awardHierarchyNodeItems.get(index).getCurrentFundEffectiveDate()!=null &&
                awardHierarchyNodeItems.get(index).getCurrentFundEffectiveDate() != null &&
                awardHierarchyNodeItems.get(index).getCurrentFundEffectiveDate().equals(aai.getCurrentFundEffectiveDate()) &&
                !awardHierarchyNodeItems.get(index).getCurrentFundEffectiveDate().equals(awardHierarchyNode.getValue().getCurrentFundEffectiveDate())) {
            awardHierarchyNode.getValue().setCurrentFundEffectiveDate(awardHierarchyNodeItems.get(index).getCurrentFundEffectiveDate());
        }
        return needToSave;
    }

    private void updateDocumentFromSession(TimeAndMoneyDocument doc) {
        if(doc.getAwardHierarchyNodes()==null || doc.getAwardHierarchyNodes().size()==0){
            if(GlobalVariables.getUserSession().retrieveObject(GlobalVariables.getUserSession().getKualiSessionId()+Constants.TIME_AND_MONEY_DOCUMENT_STRING_FOR_SESSION)!=null){
                TimeAndMoneyDocument document = (TimeAndMoneyDocument)GlobalVariables.getUserSession().retrieveObject(GlobalVariables.getUserSession().getKualiSessionId()+Constants.TIME_AND_MONEY_DOCUMENT_STRING_FOR_SESSION);
                doc.setAwardHierarchyItems(document.getAwardHierarchyItems());
                doc.setAwardHierarchyNodes(document.getAwardHierarchyNodes());
            } else {
                LOG.error("Can't Retrieve Time And Money Document from Session");
            }
        }
    }

    public void updateAwardAmountTransactions(TimeAndMoneyDocument timeAndMoneyDocument) {
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


    public boolean captureMoneyChanges(List<AwardHierarchyNode> awardHierarchyNodeItems, TimeAndMoneyDocument timeAndMoneyDocument, List<TransactionDetail> moneyTransactionDetailItems,
                                       Map.Entry<String, AwardHierarchyNode> awardHierarchyNode) {
        Award award = awardVersionService.getWorkingAwardVersion(awardHierarchyNode.getValue().getAwardNumber());
        AwardAmountInfo aai = awardAmountInfoService.fetchAwardAmountInfoWithHighestTransactionId(award.getAwardAmountInfos());
        boolean refreshNeeded = inspectAndCaptureAmountChanges(awardHierarchyNodeItems, aai, award, timeAndMoneyDocument, awardHierarchyNode.getValue());
        businessObjectService.save(award);
        businessObjectService.save(moneyTransactionDetailItems);
        timeAndMoneyDocument.getAward().refreshReferenceObject(AWARD_AMOUNT_INFOS);
        return refreshNeeded;
    }

    private boolean inspectAndCaptureAmountChanges(List<AwardHierarchyNode> awardHierarchyNodeItems, AwardAmountInfo aai, Award award, TimeAndMoneyDocument timeAndMoneyDocument,
                                                   AwardHierarchyNode awardHierarchyNode) {
        boolean result;
        if(isDirectIndirectViewEnabled()){
            result = createAndValidateEnabledViewTransaction(aai, award, timeAndMoneyDocument, awardHierarchyNode, awardHierarchyNodeItems);

        } else {
            result = createAndValidateDisabledViewTransaction(aai, award, timeAndMoneyDocument, awardHierarchyNode, awardHierarchyNodeItems);
        }
        return result;
    }

    private boolean createAndValidateEnabledViewTransaction(AwardAmountInfo aai, Award award,
                                                            TimeAndMoneyDocument timeAndMoneyDocument, AwardHierarchyNode ahn, List<AwardHierarchyNode> awardHierarchyNodeItems) {
        boolean result = false;  // assume no change to totals
        AwardHierarchyNode awardHierarchyNode = awardHierarchyNodeItems.get(1);
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
        if(timeAndMoneyDocument.getPendingTransactions().size() == 0 && (!awardHierarchyNode.getObligatedTotalDirect().equals(currentObligatedDirect)||
                !awardHierarchyNode.getObligatedTotalIndirect().equals(currentObligatedIndirect) ||
                !awardHierarchyNode.getAnticipatedTotalDirect().equals(currentAnticipatedDirect) ||
                !awardHierarchyNode.getAnticipatedTotalIndirect().equals(currentAnticipatedIndirect))){
            ScaleTwoDecimal obligatedChangeDirect = awardHierarchyNode.getObligatedTotalDirect().subtract(currentObligatedDirect);
            ScaleTwoDecimal obligatedChangeIndirect = awardHierarchyNode.getObligatedTotalIndirect().subtract(currentObligatedIndirect);
            ScaleTwoDecimal anticipatedChangeDirect = awardHierarchyNode.getAnticipatedTotalDirect().subtract(currentAnticipatedDirect);
            ScaleTwoDecimal anticipatedChangeIndirect = awardHierarchyNode.getAnticipatedTotalIndirect().subtract(currentAnticipatedIndirect);
            if(transactionRuleImpl.processParameterEnabledRules(awardHierarchyNode, aai, timeAndMoneyDocument)) {
                populatePendingTransactionAndSave(award, timeAndMoneyDocument, pendingTransaction, obligatedChangeDirect, obligatedChangeIndirect,
                        anticipatedChangeDirect, anticipatedChangeIndirect);
                result = true;
            } else {
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

    private void populatePendingTransactionAndSave(Award award, TimeAndMoneyDocument timeAndMoneyDocument, PendingTransaction pendingTransaction,
                                                   ScaleTwoDecimal obligatedChangeDirect, ScaleTwoDecimal obligatedChangeIndirect, ScaleTwoDecimal anticipatedChangeDirect,
                                                   ScaleTwoDecimal anticipatedChangeIndirect) {
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
        businessObjectService.save(timeAndMoneyDocument.getPendingTransactions());//need pending transaction to have a primarykey value
    }

    private boolean createAndValidateDisabledViewTransaction(AwardAmountInfo aai, Award award,
                                                             TimeAndMoneyDocument timeAndMoneyDocument, AwardHierarchyNode ahn, List<AwardHierarchyNode> awardHierarchyNodeItems) {
        boolean result = false;  // assume no change to totals
        AwardHierarchyNode awardHierarchyNode = awardHierarchyNodeItems.get(awardHierarchyNodeItems.size() - 1);
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
        if(timeAndMoneyDocument.getPendingTransactions().size() == 0 && (!awardHierarchyNode.getAmountObligatedToDate().equals(currentObligated)
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
                businessObjectService.save(timeAndMoneyDocument.getPendingTransactions());//need pending transaction to have a primarykey value
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

}
