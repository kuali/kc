/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.timeandmoney.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.history.TransactionDetail;
import org.kuali.kra.timeandmoney.service.ActivePendingTransactionsService;
import org.kuali.kra.timeandmoney.transactions.AwardAmountTransaction;
import org.kuali.kra.timeandmoney.transactions.PendingTransaction;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.KualiDecimal;

public class ActivePendingTransactionsServiceImpl implements ActivePendingTransactionsService {
    
    BusinessObjectService businessObjectService;

    /**
     * 
     * @see org.kuali.kra.timeandmoney.service.ActivePendingTransactionsService#approveTransactions(org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument, org.kuali.kra.timeandmoney.transactions.AwardAmountTransaction)
     */
    public void approveTransactions(TimeAndMoneyDocument doc, AwardAmountTransaction newAwardAmountTransaction) {
        
        List<PendingTransaction> pendingTransactionsToBeDeleted = new ArrayList<PendingTransaction>();
        List<PendingTransaction> pendingTransactionsAfterDeletingProcessedOnes = new ArrayList<PendingTransaction>();
        pendingTransactionsAfterDeletingProcessedOnes.addAll(doc.getPendingTransactions());
        
        List<TransactionDetail> transactionDetailItems = new ArrayList<TransactionDetail>();
        Map<String, AwardAmountTransaction> awardAmountTransactionItems = new HashMap<String, AwardAmountTransaction>();
        List<Award> awardItems = new ArrayList<Award>();
        
        for(PendingTransaction pendingTransaction: doc.getPendingTransactions()){
            Map<String, AwardHierarchyNode> awardHierarchyNodes = doc.getAwardHierarchyNodes();
            AwardHierarchyNode sourceAwardNode = awardHierarchyNodes.get(pendingTransaction.getSourceAwardNumber());
            AwardHierarchyNode destinationAwardNode = awardHierarchyNodes.get(pendingTransaction.getDestinationAwardNumber());

            if(StringUtils.equalsIgnoreCase(pendingTransaction.getSourceAwardNumber(),Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT)){                
                
                if(StringUtils.equalsIgnoreCase(pendingTransaction.getDestinationAwardNumber(), destinationAwardNode.getRootAwardNumber())){
                    handleSingleTransaction(true, true, pendingTransaction, destinationAwardNode.getAwardNumber(), doc.getDocumentNumber(), awardAmountTransactionItems, awardItems, pendingTransactionsAfterDeletingProcessedOnes, newAwardAmountTransaction, doc.getDocumentNumber());
                    
                    addTransactionDetails(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT,destinationAwardNode.getAwardNumber(),doc.getAward().getSequenceNumber(), pendingTransaction, doc.getAwardNumber(), doc.getDocumentNumber(), transactionDetailItems);    
                }else{
                    createIntermediateTransactionsWhenParentIsExternalprotected(doc, awardAmountTransactionItems, awardItems, pendingTransaction, destinationAwardNode.getAwardNumber()
                            , awardHierarchyNodes.get(destinationAwardNode.getAwardNumber()).getParentAwardNumber(), Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT, true
                            , transactionDetailItems, pendingTransactionsAfterDeletingProcessedOnes, newAwardAmountTransaction);
                    
                    handleSingleTransaction(true, true, pendingTransaction, destinationAwardNode.getAwardNumber(), doc.getDocumentNumber(), awardAmountTransactionItems, awardItems, pendingTransactionsAfterDeletingProcessedOnes, newAwardAmountTransaction, doc.getDocumentNumber());                    
                }
                
            }else if(StringUtils.equalsIgnoreCase(sourceAwardNode.getParentAwardNumber(), destinationAwardNode.getAwardNumber()) ||
                    StringUtils.equalsIgnoreCase(destinationAwardNode.getParentAwardNumber(), sourceAwardNode.getAwardNumber())){
                
                handleSingleTransaction(true, false, pendingTransaction, sourceAwardNode.getAwardNumber(), doc.getDocumentNumber(), awardAmountTransactionItems, awardItems, pendingTransactionsAfterDeletingProcessedOnes, newAwardAmountTransaction, doc.getDocumentNumber());
                
                handleSingleTransaction(true, true, pendingTransaction, destinationAwardNode.getAwardNumber(), doc.getDocumentNumber(), awardAmountTransactionItems, awardItems, pendingTransactionsAfterDeletingProcessedOnes, newAwardAmountTransaction, doc.getDocumentNumber());
                
                addTransactionDetails(sourceAwardNode.getAwardNumber(),destinationAwardNode.getAwardNumber(),doc.getAward().getSequenceNumber(), pendingTransaction, doc.getAwardNumber(), doc.getDocumentNumber(), transactionDetailItems);
                
            }else if(StringUtils.equalsIgnoreCase(sourceAwardNode.getParentAwardNumber(), destinationAwardNode.getParentAwardNumber())){
                
                handleSingleTransaction(false, false, pendingTransaction, sourceAwardNode.getParentAwardNumber(), doc.getDocumentNumber(), awardAmountTransactionItems, awardItems, pendingTransactionsAfterDeletingProcessedOnes, newAwardAmountTransaction, doc.getDocumentNumber());
                
                handleSingleTransaction(true, false, pendingTransaction, sourceAwardNode.getAwardNumber(), doc.getDocumentNumber(), awardAmountTransactionItems, awardItems, pendingTransactionsAfterDeletingProcessedOnes, newAwardAmountTransaction, doc.getDocumentNumber());
                
                handleSingleTransaction(true, true, pendingTransaction, destinationAwardNode.getAwardNumber(), doc.getDocumentNumber(), awardAmountTransactionItems, awardItems, pendingTransactionsAfterDeletingProcessedOnes, newAwardAmountTransaction, doc.getDocumentNumber());
                
                addTransactionDetails(sourceAwardNode.getAwardNumber(),sourceAwardNode.getParentAwardNumber(),doc.getAward().getSequenceNumber(), pendingTransaction, doc.getAwardNumber(), doc.getDocumentNumber(), transactionDetailItems);
                addTransactionDetails(sourceAwardNode.getParentAwardNumber(),destinationAwardNode.getAwardNumber(),doc.getAward().getSequenceNumber(), pendingTransaction, doc.getAwardNumber(), doc.getDocumentNumber(), transactionDetailItems);

            }else if(indirectParentChildRelationshipExistsBetween(sourceAwardNode.getAwardNumber(), destinationAwardNode.getAwardNumber(), awardHierarchyNodes)){
                handleSingleTransaction(true, false, pendingTransaction, sourceAwardNode.getAwardNumber(), doc.getDocumentNumber(), awardAmountTransactionItems, awardItems, pendingTransactionsAfterDeletingProcessedOnes, newAwardAmountTransaction, doc.getDocumentNumber());
                
                createIntermediateTransactions(doc, awardAmountTransactionItems, awardItems, pendingTransaction, destinationAwardNode.getAwardNumber()
                        , awardHierarchyNodes.get(destinationAwardNode.getAwardNumber()).getParentAwardNumber()
                        , awardHierarchyNodes.get(sourceAwardNode.getAwardNumber()).getParentAwardNumber(), true, transactionDetailItems
                        , pendingTransactionsAfterDeletingProcessedOnes, newAwardAmountTransaction);
                
                handleSingleTransaction(true, true, pendingTransaction, destinationAwardNode.getAwardNumber(), doc.getDocumentNumber(), awardAmountTransactionItems, awardItems, pendingTransactionsAfterDeletingProcessedOnes, newAwardAmountTransaction, doc.getDocumentNumber());
                
            }else if(indirectParentChildRelationshipExistsBetween(destinationAwardNode.getAwardNumber(), sourceAwardNode.getAwardNumber(), awardHierarchyNodes)){
                handleSingleTransaction(true, false, pendingTransaction, sourceAwardNode.getAwardNumber(), doc.getDocumentNumber(), awardAmountTransactionItems, awardItems, pendingTransactionsAfterDeletingProcessedOnes, newAwardAmountTransaction, doc.getDocumentNumber());
                
                createIntermediateTransactions(doc, awardAmountTransactionItems, awardItems, pendingTransaction, sourceAwardNode.getAwardNumber()
                        , awardHierarchyNodes.get(sourceAwardNode.getAwardNumber()).getParentAwardNumber()
                        , awardHierarchyNodes.get(destinationAwardNode.getAwardNumber()).getParentAwardNumber(), false, transactionDetailItems
                        , pendingTransactionsAfterDeletingProcessedOnes, newAwardAmountTransaction);
                
                handleSingleTransaction(true, true, pendingTransaction, destinationAwardNode.getAwardNumber(), doc.getDocumentNumber(), awardAmountTransactionItems, awardItems, pendingTransactionsAfterDeletingProcessedOnes, newAwardAmountTransaction, doc.getDocumentNumber());
            }else{
                handleSingleTransaction(true, false, pendingTransaction, sourceAwardNode.getAwardNumber(), doc.getDocumentNumber(), awardAmountTransactionItems, awardItems, pendingTransactionsAfterDeletingProcessedOnes, newAwardAmountTransaction, doc.getDocumentNumber());
                
                createTransaction(doc, pendingTransaction, sourceAwardNode, destinationAwardNode, transactionDetailItems, awardAmountTransactionItems, awardItems, pendingTransactionsAfterDeletingProcessedOnes, newAwardAmountTransaction);
                
                handleSingleTransaction(true, true, pendingTransaction, destinationAwardNode.getAwardNumber(), doc.getDocumentNumber(), awardAmountTransactionItems, awardItems, pendingTransactionsAfterDeletingProcessedOnes, newAwardAmountTransaction, doc.getDocumentNumber());
                                
            }
            
            pendingTransactionsAfterDeletingProcessedOnes.remove(pendingTransaction);
            pendingTransactionsToBeDeleted.add(pendingTransaction);
        }
        
        for(PendingTransaction pendingTransaction: pendingTransactionsToBeDeleted){
            doc.getPendingTransactions().remove(pendingTransaction);
        }
        
        List<AwardAmountTransaction> awardAmountTransactions = new ArrayList<AwardAmountTransaction>();
        for(Entry<String, AwardAmountTransaction> awardAmountTransaction:awardAmountTransactionItems.entrySet()){
            awardAmountTransactions.add(awardAmountTransaction.getValue());
        }
        
        businessObjectService.save(transactionDetailItems);
        businessObjectService.save(awardAmountTransactions);
        businessObjectService.save(awardItems);
        businessObjectService.save(doc);
    }

    protected void createIntermediateTransactions(TimeAndMoneyDocument doc, Map<String, AwardAmountTransaction> awardAmountTransactionItems, List<Award> awardItems
            , PendingTransaction pendingTransaction, String awardNumber, String parentAwardNumber, String parentOfParentAwardNumber, boolean direction
            , List<TransactionDetail> transactionDetailItems, List<PendingTransaction> pendingTransactionsAfterDeletingProcessedOnes
            , AwardAmountTransaction newAwardAmountTransaction) {
        
        String sourceAwardNumber;
        String destinationAwardNumber;
        
        while(!StringUtils.equalsIgnoreCase(parentAwardNumber, parentOfParentAwardNumber)){
            
            if(direction){                
                sourceAwardNumber = parentAwardNumber;
                destinationAwardNumber = awardNumber;
            }else{
                sourceAwardNumber = awardNumber;
                destinationAwardNumber = parentAwardNumber;
            }
            
            addTransactionDetails(sourceAwardNumber,destinationAwardNumber,doc.getAward().getSequenceNumber(), pendingTransaction, doc.getAwardNumber(), doc.getDocumentNumber(), transactionDetailItems);
            String tempParentAward = parentAwardNumber;
            awardNumber = parentAwardNumber;
            parentAwardNumber = doc.getAwardHierarchyNodes().get(parentAwardNumber).getParentAwardNumber();            
            if(!StringUtils.equalsIgnoreCase(parentAwardNumber, parentOfParentAwardNumber)){
                handleSingleTransaction(false, false, pendingTransaction, tempParentAward, doc.getDocumentNumber(), awardAmountTransactionItems, awardItems, pendingTransactionsAfterDeletingProcessedOnes, newAwardAmountTransaction, doc.getDocumentNumber());    
            }
                        
        }
    }
    
    private void createIntermediateTransactionsWhenParentIsExternalprotected(TimeAndMoneyDocument doc, Map<String, AwardAmountTransaction> awardAmountTransactionItems, List<Award> awardItems
            , PendingTransaction pendingTransaction, String awardNumber, String parentAwardNumber, String parentOfParentAwardNumber, boolean direction
            , List<TransactionDetail> transactionDetailItems, List<PendingTransaction> pendingTransactionsAfterDeletingProcessedOnes
            , AwardAmountTransaction newAwardAmountTransaction) {
        
        String sourceAwardNumber = null;
        String destinationAwardNumber;
                
        while(!StringUtils.equalsIgnoreCase(sourceAwardNumber, Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT)){    
            sourceAwardNumber = parentAwardNumber;
            destinationAwardNumber = awardNumber;
            addTransactionDetails(sourceAwardNumber,destinationAwardNumber,doc.getAward().getSequenceNumber(), pendingTransaction, doc.getAwardNumber(), doc.getDocumentNumber(), transactionDetailItems);
            if(!StringUtils.equalsIgnoreCase(parentAwardNumber, Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT)){
                handleSingleTransaction(false, false, pendingTransaction, parentAwardNumber, doc.getDocumentNumber(), awardAmountTransactionItems, awardItems, pendingTransactionsAfterDeletingProcessedOnes, newAwardAmountTransaction, doc.getDocumentNumber());
                awardNumber = parentAwardNumber;
                parentAwardNumber = doc.getAwardHierarchyNodes().get(parentAwardNumber).getParentAwardNumber();
            }
        }
    }

    /*
     * If there is a parent and child relationship between @awardNumber1 and @awardNumber2. This relationship could be direct or indirect
     * meaning @awardNumber1 could be direct parent or indirect parent of @awardNumber2. Indirect Parent child relationship here means there
     * could be any number of levels between the two. 
     */
    private boolean indirectParentChildRelationshipExistsBetween(String awardNumber1, String awardNumber2, Map<String, AwardHierarchyNode> awardHierarchyNodes) {
        boolean parentChild = Boolean.FALSE;
        String parentAwardNumber = awardHierarchyNodes.get(awardNumber2).getParentAwardNumber();
        while(!StringUtils.equalsIgnoreCase(parentAwardNumber, Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT)){
            if(StringUtils.equalsIgnoreCase(parentAwardNumber, awardNumber1)){
                parentChild = Boolean.TRUE;
                break;                
            }
            parentAwardNumber = awardHierarchyNodes.get(parentAwardNumber).getParentAwardNumber();
        }
        return parentChild;
    }

    /**
     * This method...
     * @param doc
     * @param pendingTransaction
     * @param sourceAwardNode
     * @param destinationAwardNode
     * @param pendingTransactionsAfterDeletingProcessedOnes TODO
     * @param newAwardAmountTransaction TODO
     * @param pendingTransactionsToBeDeleted
     */
    private void createTransaction(TimeAndMoneyDocument doc, PendingTransaction pendingTransaction, AwardHierarchyNode sourceAwardNode
                , AwardHierarchyNode destinationAwardNode, List<TransactionDetail> transactionDetailItems
                , Map<String, AwardAmountTransaction> awardAmountTransactionItems, List<Award> awardItems, List<PendingTransaction> pendingTransactionsAfterDeletingProcessedOnes, AwardAmountTransaction newAwardAmountTransaction) {
        
        String commonParent = findCommonParent(doc, sourceAwardNode.getRootAwardNumber(), sourceAwardNode.getParentAwardNumber(), destinationAwardNode.getAwardNumber());
        
        createTransaction(doc, pendingTransaction, commonParent,sourceAwardNode.getParentAwardNumber(), sourceAwardNode.getAwardNumber(), true, transactionDetailItems, awardAmountTransactionItems, awardItems, pendingTransactionsAfterDeletingProcessedOnes, doc.getAwardHierarchyNodes().get(commonParent).getParentAwardNumber(), newAwardAmountTransaction);
        
        createTransaction(doc, pendingTransaction, commonParent,destinationAwardNode.getParentAwardNumber(), destinationAwardNode.getAwardNumber(), false, transactionDetailItems, awardAmountTransactionItems, awardItems, pendingTransactionsAfterDeletingProcessedOnes, doc.getAwardHierarchyNodes().get(commonParent).getParentAwardNumber(), newAwardAmountTransaction);
    }

    /**
     * This method...
     * @param doc
     * @param pendingTransaction
     * @param commonParent
     * @param parentAwardNumber
     * @param awardNumber TODO
     * @param direction TODO
     * @param pendingTransactionsAfterDeletingProcessedOnes TODO
     * @param parentOfCommonParent TODO
     * @param newAwardAmountTransaction TODO
     * @param pendingTransactionsToBeDeleted
     */
    protected void createTransaction(TimeAndMoneyDocument doc,
            PendingTransaction pendingTransaction, String commonParent, String parentAwardNumber, String awardNumber, boolean direction, List<TransactionDetail> transactionDetailItems, Map<String, AwardAmountTransaction> awardAmountTransactionItems, List<Award> awardItems, List<PendingTransaction> pendingTransactionsAfterDeletingProcessedOnes, String parentOfCommonParent, AwardAmountTransaction newAwardAmountTransaction) {
        
        String sourceAwardNumber;
        String destinationAwardNumber;
        
        do{ 
            if(direction){
                sourceAwardNumber = awardNumber;
                destinationAwardNumber = parentAwardNumber;
            }else{
                sourceAwardNumber = parentAwardNumber;
                destinationAwardNumber = awardNumber;
            }
            
            addTransactionDetails(sourceAwardNumber,destinationAwardNumber,doc.getAward().getSequenceNumber(), pendingTransaction, doc.getAwardNumber(), doc.getDocumentNumber(), transactionDetailItems);
            
            AwardHierarchyNode node = doc.getAwardHierarchyNodes().get(parentAwardNumber);
            
            handleSingleTransaction(false, false, pendingTransaction, node.getAwardNumber(), doc.getDocumentNumber(), awardAmountTransactionItems, awardItems, pendingTransactionsAfterDeletingProcessedOnes, newAwardAmountTransaction, doc.getDocumentNumber());
            awardNumber = parentAwardNumber;
            parentAwardNumber = node.getParentAwardNumber();
        }while(!StringUtils.equalsIgnoreCase(parentOfCommonParent,parentAwardNumber));
        
    }
    
    protected void addTransactionDetails(String sourceAwardNumber, String destinationAwardNumber, Integer sequenceNumber, PendingTransaction pendingTransaction, String currentAwardNumber, String documentNumber, List<TransactionDetail> transactionDetailItems){
        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setSourceAwardNumber(sourceAwardNumber);
        transactionDetail.setSequenceNumber(sequenceNumber);
        transactionDetail.setDestinationAwardNumber(destinationAwardNumber);
        transactionDetail.setAnticipatedAmount(pendingTransaction.getAnticipatedAmount());
        transactionDetail.setObligatedAmount(pendingTransaction.getObligatedAmount());
        transactionDetail.setAwardNumber(currentAwardNumber);
        transactionDetail.setTransactionId(pendingTransaction.getTransactionId());
        transactionDetail.setTimeAndMoneyDocumentNumber(documentNumber);
        transactionDetail.setComments(pendingTransaction.getComments());
        transactionDetailItems.add(transactionDetail);
    }
    
    protected String findCommonParent(TimeAndMoneyDocument doc, String rootAwardNumber, String parentOfSource, String destinationAwardNumber){
        boolean commonParentFound = false;        
        String node = parentOfSource;
        
        if(StringUtils.equalsIgnoreCase(parentOfSource, Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT)){
            node = rootAwardNumber;
        }else{
            while(!commonParentFound){
                List<String> childrenAwardNumbers = findChildren(node);
                if(childrenAwardNumbers.contains(destinationAwardNumber)){
                    commonParentFound = true;
                }else{
                    node = doc.getAwardHierarchyNodes().get(node).getParentAwardNumber();
                }
            }
        }
        
        return node;
    }

    /**
     * This method...
     * @param pendingTransaction
     * @param timeAndMoneyDocumentNumber TODO
     * @param pendingTransactions TODO
     * @param pendingTransactionsToBeDeleted
     * @param awardListDestination
     */
    private void handleSingleTransaction(boolean updateAmounts, boolean addOrSubtract,
            PendingTransaction pendingTransaction, String awardNumber, String timeAndMoneyDocumentNumber, Map<String, AwardAmountTransaction> awardAmountTransactionItems, List<Award> awardItems, List<PendingTransaction> pendingTransactions, AwardAmountTransaction newAwardAmountTransaction, String documentNumber) {
        
        Award award = getActiveAwardVersion(awardNumber);
        AwardAmountInfo awardAmountInfo = fetchAwardAmountInfoWithHighestTransactionId(award.getAwardAmountInfos());
        if(updateAmounts){
            if(addOrSubtract){
                
            }else{
                KualiDecimal totalPendingObligated = new KualiDecimal(0);
                KualiDecimal totalPendingAnticipated = new KualiDecimal(0);
                
                for(PendingTransaction pt : pendingTransactions){
                    if(pt.getTransactionId()!=null && pendingTransaction.getTransactionId()!= null && !(pt.getTransactionId().longValue() == pendingTransaction.getTransactionId().longValue()) 
                        && StringUtils.equalsIgnoreCase(pt.getSourceAwardNumber(),pendingTransaction.getSourceAwardNumber())){
                        totalPendingObligated = totalPendingObligated.add(pt.getObligatedAmount());
                        totalPendingAnticipated = totalPendingAnticipated.add(pt.getAnticipatedAmount());    
                    }
                }
                
                if(pendingTransaction.getObligatedAmount()!=null){
                    if(awardAmountInfo.getObliDistributableAmount().subtract(pendingTransaction.getObligatedAmount().add(totalPendingObligated)).isLessThan(new KualiDecimal(0))){
                        throw new RuntimeException("Insufficient Obligated Money");
                    }
                }
                if(pendingTransaction.getAnticipatedAmount()!=null){
                    if(awardAmountInfo.getAntDistributableAmount().subtract(pendingTransaction.getAnticipatedAmount().add(totalPendingAnticipated)).isLessThan(new KualiDecimal(0))){
                        throw new RuntimeException("Insufficient Anticipated Money");
                    }
                }
            }
        }
        award.getAwardAmountInfos().add(getNewAwardAmountInfoEntry(updateAmounts, addOrSubtract, pendingTransaction, awardAmountInfo, timeAndMoneyDocumentNumber, awardAmountTransactionItems, newAwardAmountTransaction, documentNumber));
        awardItems.add(award);
    }
   
    /**
     * 
     * @see org.kuali.kra.timeandmoney.service.ActivePendingTransactionsService#fetchAwardAmountInfoWithHighestTransactionId(java.util.List)
     */
    public AwardAmountInfo fetchAwardAmountInfoWithHighestTransactionId(List<AwardAmountInfo> awardAmountInfos) {
        AwardAmountInfo awardAmountInfo = null;
        for(AwardAmountInfo aai : awardAmountInfos){
            if(awardAmountInfo == null){
                awardAmountInfo = aai;
            }else if(awardAmountInfo.getTransactionId() == null && aai.getTransactionId()!=null){
                awardAmountInfo = aai;
            }else if(awardAmountInfo.getTransactionId()!=null && aai.getTransactionId()!=null && awardAmountInfo.getTransactionId() < aai.getTransactionId()){
                awardAmountInfo = aai;
            }
        }
        return awardAmountInfo;
    }

    private List<String> findChildren(String parent) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("parentAwardNumber", parent);
        Collection<AwardHierarchy> awardHierarchies = businessObjectService.findMatchingOrderBy(AwardHierarchy.class, fieldValues, "awardNumber", true);
        Collection<AwardHierarchy> awardHierarchiesBig = businessObjectService.findMatchingOrderBy(AwardHierarchy.class, fieldValues, "awardNumber", true);
        List<String> parentAwardNumbers = new ArrayList<String>();
        int i = 1;
        
        for(AwardHierarchy awardHierarchy: awardHierarchies){
            fieldValues.put("parentAwardNumber", awardHierarchy.getAwardNumber());
            awardHierarchiesBig.addAll(businessObjectService.findMatchingOrderBy(AwardHierarchy.class, fieldValues, "awardNumber", true));                
        }
        
        for(AwardHierarchy awardHierarchy: awardHierarchiesBig){
            parentAwardNumbers.add(awardHierarchy.getAwardNumber());    
        }
        
        return parentAwardNumbers; 
    }

    /*
     * This method will create a newAwardAmountInfo object based on the existing awardAmountInfo object.
     * It will also update various amount fields based on various conditions.
     * 
     */
    private AwardAmountInfo getNewAwardAmountInfoEntry(boolean updateAmounts, boolean addOrSubtract, PendingTransaction pendingTransaction
            , AwardAmountInfo awardAmountInfo, String timeAndMoneyDocumentNumber, Map<String, AwardAmountTransaction> awardAmountTransactionItems, AwardAmountTransaction newAwardAmountTransaction, String documentNumber) {
        AwardAmountInfo newAwardAmountInfo = new AwardAmountInfo();
        newAwardAmountInfo.setAwardNumber(awardAmountInfo.getAwardNumber());
        newAwardAmountInfo.setSequenceNumber(awardAmountInfo.getSequenceNumber());
        newAwardAmountInfo.setFinalExpirationDate(awardAmountInfo.getFinalExpirationDate());
        newAwardAmountInfo.setTimeAndMoneyDocumentNumber(timeAndMoneyDocumentNumber);
        newAwardAmountInfo.setTransactionId(pendingTransaction.getTransactionId());
        
        updateAmountFields(updateAmounts, addOrSubtract, pendingTransaction, awardAmountInfo, newAwardAmountInfo);
        
        addAwardAmountTransaction(newAwardAmountInfo.getAwardNumber(), awardAmountTransactionItems, newAwardAmountTransaction, documentNumber);

        return newAwardAmountInfo;
    }

    /*
     * This method will update the amount fields on newAwardAmountInfo object based on various conditions.
     * 
     */
    private void updateAmountFields(boolean updateAmounts, boolean addOrSubtract, PendingTransaction pendingTransaction, AwardAmountInfo awardAmountInfo
            , AwardAmountInfo newAwardAmountInfo) {

        newAwardAmountInfo.setObliDistributableAmount(processAmounts(awardAmountInfo.getObliDistributableAmount(), pendingTransaction.getObligatedAmount(),addOrSubtract, updateAmounts));            
        newAwardAmountInfo.setAntDistributableAmount(processAmounts(awardAmountInfo.getAntDistributableAmount(), pendingTransaction.getAnticipatedAmount(),addOrSubtract, updateAmounts));
        
        if(StringUtils.equalsIgnoreCase(pendingTransaction.getSourceAwardNumber(), Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT)){
            newAwardAmountInfo.setAnticipatedTotalAmount(processAmounts(awardAmountInfo.getAnticipatedTotalAmount(), pendingTransaction.getAnticipatedAmount(),true, true));
            newAwardAmountInfo.setAmountObligatedToDate(processAmounts(awardAmountInfo.getAmountObligatedToDate(), pendingTransaction.getObligatedAmount(),true, true));
        }
        else if(addOrSubtract){            
            newAwardAmountInfo.setAnticipatedTotalAmount(processAmounts(awardAmountInfo.getAnticipatedTotalAmount(), pendingTransaction.getAnticipatedAmount(),addOrSubtract, updateAmounts));
            newAwardAmountInfo.setAmountObligatedToDate(processAmounts(awardAmountInfo.getAmountObligatedToDate(), pendingTransaction.getObligatedAmount(),addOrSubtract, updateAmounts));
        }else{            
            newAwardAmountInfo.setAnticipatedTotalAmount(awardAmountInfo.getAnticipatedTotalAmount());
            newAwardAmountInfo.setAmountObligatedToDate(awardAmountInfo.getAmountObligatedToDate());
        }
        
        if(updateAmounts){
            newAwardAmountInfo.setObligatedChange(pendingTransaction.getObligatedAmount());
            newAwardAmountInfo.setAnticipatedChange(pendingTransaction.getAnticipatedAmount());    
        }else if(StringUtils.equalsIgnoreCase(pendingTransaction.getSourceAwardNumber(), Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT)){
            newAwardAmountInfo.setObligatedChange(pendingTransaction.getObligatedAmount());
            newAwardAmountInfo.setAnticipatedChange(pendingTransaction.getAnticipatedAmount());
        }else{
            newAwardAmountInfo.setObligatedChange(new KualiDecimal(0));
            newAwardAmountInfo.setAnticipatedChange(new KualiDecimal(0));
        }
    }
    
    /*
     * This is a helper method to add awardAmountTransaction information.
     * 
     * AwardAmountTransacion table is going to have one entry per document, per affected award.
     * Affected award here means an award that is part of any of the Pending Transactions.
     * 
     * That's why we will maintain a map of AwardAmountTransaction Objects with awardNumber as the key. If the key is present, we won't
     * add a new entry here, otherwise we will. We will persist all awardAmountTransaction objects later.
     */
    private void addAwardAmountTransaction(String awardNumber, Map<String, AwardAmountTransaction> awardAmountTransactionItems, AwardAmountTransaction newAwardAmountTransaction, String documentNumber) {
        if(!awardAmountTransactionItems.containsKey(awardNumber)){
            AwardAmountTransaction newAwardAmountTransaction1 = new AwardAmountTransaction(); 
            newAwardAmountTransaction1.setAwardNumber(awardNumber);
            newAwardAmountTransaction1.setDocumentNumber(documentNumber);
            newAwardAmountTransaction1.setTransactionTypeCode(newAwardAmountTransaction.getTransactionTypeCode());
            newAwardAmountTransaction1.setComments(newAwardAmountTransaction.getComments());
            newAwardAmountTransaction1.setNoticeDate(newAwardAmountTransaction.getNoticeDate());
            awardAmountTransactionItems.put(awardNumber, newAwardAmountTransaction1);    
        }       
    }

    /*
     * 
     * This is a helper method to carry out calculcation on various amount fields.
     * 
     * updateAmounts true means the calculations should be done otherwise the same value is returned.
     * addOrSubtract true means the value should be added and false means value should be subtracted.
     * 
     */
    protected KualiDecimal processAmounts(KualiDecimal value1, KualiDecimal value2, boolean addOrSubtract, boolean updateAmounts){
        KualiDecimal returnValue;
        if(updateAmounts){
            if(addOrSubtract){
                if(value1!=null){
                    returnValue =  value1.add(value2);    
                }else{
                    returnValue = value2;
                }   
            }else{
                if(value1!=null){
                    returnValue =  value1.subtract(value2);    
                }else{
                    returnValue = value2;
                }
            }
        }else{
            returnValue = value1;
        }
        return returnValue;
    }
    
    public Award getActiveAwardVersion(String awardNumber) {
        VersionHistoryService vhs = KraServiceLocator.getService(VersionHistoryService.class);  
        VersionHistory vh = vhs.findActiveVersion(Award.class, awardNumber);
        Award award = null;
        
        if(vh!=null){
            award = (Award) vh.getSequenceOwner();
        }else{
            BusinessObjectService businessObjectService =  KraServiceLocator.getService(BusinessObjectService.class);
            award = ((List<Award>)businessObjectService.findMatching(Award.class, getHashMap(awardNumber))).get(0);              
        }
        return award;
    }

    private Map<String, String> getHashMap(String goToAwardNumber) {
        Map<String, String> map = new HashMap<String,String>();
        map.put("awardNumber", goToAwardNumber);
        return map;
    }
    
    /**
     * Gets the businessObjectService attribute. 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}
