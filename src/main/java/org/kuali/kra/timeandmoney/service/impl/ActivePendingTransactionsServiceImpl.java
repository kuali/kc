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

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.timeandmoney.TimeAndMoneyForm;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.history.TransactionDetail;
import org.kuali.kra.timeandmoney.service.ActivePendingTransactionsService;
import org.kuali.kra.timeandmoney.transactions.AwardAmountTransaction;
import org.kuali.kra.timeandmoney.transactions.PendingTransaction;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KualiDecimal;

public class ActivePendingTransactionsServiceImpl implements ActivePendingTransactionsService {
    
    private static final String PARENT_OF_ROOT = "000000-00000";
    BusinessObjectService businessObjectService;

    public void approveTransactions() {
        TimeAndMoneyForm form = (TimeAndMoneyForm) GlobalVariables.getKualiForm();
        TimeAndMoneyDocument doc = (TimeAndMoneyDocument) form.getDocument();
        
        List<PendingTransaction> pendingTransactionsToBeDeleted = new ArrayList<PendingTransaction>();
        List<TransactionDetail> transactionDetailItems = new ArrayList<TransactionDetail>();
        List<AwardAmountTransaction> awardAmountTransactionItems = new ArrayList<AwardAmountTransaction>();
        List<Award> awardItems = new ArrayList<Award>();
        
        for(PendingTransaction pendingTransaction: doc.getPendingTransactions()){
            AwardHierarchyNode sourceAwardNode = doc.getAwardHierarchyNodes().get(pendingTransaction.getSourceAwardNumber());
            AwardHierarchyNode destinationAwardNode = doc.getAwardHierarchyNodes().get(pendingTransaction.getDestinationAwardNumber());

            if(StringUtils.equalsIgnoreCase(pendingTransaction.getSourceAwardNumber(),PARENT_OF_ROOT)){                
                
                if(StringUtils.equalsIgnoreCase(pendingTransaction.getDestinationAwardNumber(), destinationAwardNode.getRootAwardNumber())){
                    handleSingleTransaction(true, true, pendingTransaction, destinationAwardNode.getAwardNumber(), doc.getDocumentNumber(), awardAmountTransactionItems, awardItems);
                    
                    persistTransactionDetailObject(PARENT_OF_ROOT,destinationAwardNode.getAwardNumber(),doc.getSequenceNumber(), pendingTransaction, doc.getAwardNumber(), doc.getDocumentNumber(), transactionDetailItems);    
                }else{
                    createIntermediateTransactions(doc, awardAmountTransactionItems, awardItems, pendingTransaction, doc.getAwardHierarchyNodes().get(destinationAwardNode.getAwardNumber()).getParentAwardNumber(), transactionDetailItems);
                    
                    handleSingleTransaction(true, true, pendingTransaction, destinationAwardNode.getAwardNumber(), doc.getDocumentNumber(), awardAmountTransactionItems, awardItems);                    
                }
                
            }else if(StringUtils.equalsIgnoreCase(sourceAwardNode.getParentAwardNumber(), destinationAwardNode.getAwardNumber()) ||
                    StringUtils.equalsIgnoreCase(destinationAwardNode.getParentAwardNumber(), sourceAwardNode.getAwardNumber())){
                
                handleSingleTransaction(true, false, pendingTransaction, sourceAwardNode.getAwardNumber(), doc.getDocumentNumber(), awardAmountTransactionItems, awardItems);
                
                handleSingleTransaction(true, true, pendingTransaction, destinationAwardNode.getAwardNumber(), doc.getDocumentNumber(), awardAmountTransactionItems, awardItems);
                
                persistTransactionDetailObject(sourceAwardNode.getAwardNumber(),destinationAwardNode.getAwardNumber(),doc.getSequenceNumber(), pendingTransaction, doc.getAwardNumber(), doc.getDocumentNumber(), transactionDetailItems);
                
            }else if(StringUtils.equalsIgnoreCase(sourceAwardNode.getParentAwardNumber(), destinationAwardNode.getParentAwardNumber())){
                
                handleSingleTransaction(false, false, pendingTransaction, sourceAwardNode.getParentAwardNumber(), doc.getDocumentNumber(), awardAmountTransactionItems, awardItems);
                
                handleSingleTransaction(true, false, pendingTransaction, sourceAwardNode.getAwardNumber(), doc.getDocumentNumber(), awardAmountTransactionItems, awardItems);
                
                handleSingleTransaction(true, true, pendingTransaction, destinationAwardNode.getAwardNumber(), doc.getDocumentNumber(), awardAmountTransactionItems, awardItems);
                
                persistTransactionDetailObject(sourceAwardNode.getAwardNumber(),sourceAwardNode.getParentAwardNumber(),doc.getSequenceNumber(), pendingTransaction, doc.getAwardNumber(), doc.getDocumentNumber(), transactionDetailItems);
                persistTransactionDetailObject(sourceAwardNode.getParentAwardNumber(),destinationAwardNode.getAwardNumber(),doc.getSequenceNumber(), pendingTransaction, doc.getAwardNumber(), doc.getDocumentNumber(), transactionDetailItems);

            }else if(indirectParentChildRelationshipExistsBetween(sourceAwardNode.getAwardNumber(), destinationAwardNode.getAwardNumber(), doc.getAwardHierarchyNodes())){
                handleSingleTransaction(true, false, pendingTransaction, sourceAwardNode.getAwardNumber(), doc.getDocumentNumber(), awardAmountTransactionItems, awardItems);
                
                createIntermediateTransactions(doc, awardAmountTransactionItems, awardItems, pendingTransaction, destinationAwardNode.getAwardNumber(), doc.getAwardHierarchyNodes().get(destinationAwardNode.getAwardNumber()).getParentAwardNumber(), doc.getAwardHierarchyNodes().get(destinationAwardNode.getAwardNumber()).getRootAwardNumber(), true, transactionDetailItems);
                
                handleSingleTransaction(true, true, pendingTransaction, destinationAwardNode.getAwardNumber(), doc.getDocumentNumber(), awardAmountTransactionItems, awardItems);
                
            }else if(indirectParentChildRelationshipExistsBetween(destinationAwardNode.getAwardNumber(), sourceAwardNode.getAwardNumber(), doc.getAwardHierarchyNodes())){
                handleSingleTransaction(true, false, pendingTransaction, sourceAwardNode.getAwardNumber(), doc.getDocumentNumber(), awardAmountTransactionItems, awardItems);
                
                createIntermediateTransactions(doc, awardAmountTransactionItems, awardItems, pendingTransaction, sourceAwardNode.getAwardNumber(), doc.getAwardHierarchyNodes().get(sourceAwardNode.getAwardNumber()).getParentAwardNumber(), doc.getAwardHierarchyNodes().get(destinationAwardNode.getAwardNumber()).getRootAwardNumber(), false, transactionDetailItems);
                
                handleSingleTransaction(true, true, pendingTransaction, destinationAwardNode.getAwardNumber(), doc.getDocumentNumber(), awardAmountTransactionItems, awardItems);
            }else{
                handleSingleTransaction(true, false, pendingTransaction, sourceAwardNode.getAwardNumber(), doc.getDocumentNumber(), awardAmountTransactionItems, awardItems);
                
                createTransaction(doc, pendingTransaction, sourceAwardNode, destinationAwardNode, transactionDetailItems, awardAmountTransactionItems, awardItems);
                
                handleSingleTransaction(true, true, pendingTransaction, destinationAwardNode.getAwardNumber(), doc.getDocumentNumber(), awardAmountTransactionItems, awardItems);
                                
            }
            
            pendingTransactionsToBeDeleted.add(pendingTransaction);
        }
        
        for(PendingTransaction pendingTransaction: pendingTransactionsToBeDeleted){
            doc.getPendingTransactions().remove(pendingTransaction);
        }
        
        businessObjectService.save(transactionDetailItems);
        businessObjectService.save(awardAmountTransactionItems);
        businessObjectService.save(awardItems);
    }

    /**
     * This method...
     * @param doc
     * @param awardAmountTransactionItems
     * @param awardItems
     * @param pendingTransaction
     * @param parentAwardNumber
     */
    private void createIntermediateTransactions(TimeAndMoneyDocument doc, List<AwardAmountTransaction> awardAmountTransactionItems,
            List<Award> awardItems, PendingTransaction pendingTransaction, String awardNumber, String parentAwardNumber, String rootAwardNumber, boolean direction, List<TransactionDetail> transactionDetailItems) {
        
        String sourceAwardNumber;
        String destinationAwardNumber;
        
        while(!StringUtils.equalsIgnoreCase(parentAwardNumber, rootAwardNumber)){
            
            if(direction){
                sourceAwardNumber = parentAwardNumber;
                destinationAwardNumber = awardNumber;
            }else{
                sourceAwardNumber = awardNumber;
                destinationAwardNumber = parentAwardNumber;
            }
            
            persistTransactionDetailObject(sourceAwardNumber,destinationAwardNumber,doc.getSequenceNumber(), pendingTransaction, doc.getAwardNumber(), doc.getDocumentNumber(), transactionDetailItems);            
            
            handleSingleTransaction(false, false, pendingTransaction, parentAwardNumber, doc.getDocumentNumber(), awardAmountTransactionItems, awardItems);
            parentAwardNumber = doc.getAwardHierarchyNodes().get(parentAwardNumber).getParentAwardNumber();            
        }
    }
    
    private void createIntermediateTransactions(TimeAndMoneyDocument doc, List<AwardAmountTransaction> awardAmountTransactionItems,
            List<Award> awardItems, PendingTransaction pendingTransaction, String parentAwardNumber, List<TransactionDetail> transactionDetailItems) {
        while(!StringUtils.equalsIgnoreCase(parentAwardNumber, PARENT_OF_ROOT)){
            handleSingleTransaction(false, false, pendingTransaction, parentAwardNumber, doc.getDocumentNumber(), awardAmountTransactionItems, awardItems);
            parentAwardNumber = doc.getAwardHierarchyNodes().get(parentAwardNumber).getParentAwardNumber();
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
        while(!StringUtils.equalsIgnoreCase(parentAwardNumber, PARENT_OF_ROOT)){
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
     * @param pendingTransactionsToBeDeleted
     * @param pendingTransaction
     * @param sourceAwardNode
     * @param destinationAwardNode
     */
    private void createTransaction(TimeAndMoneyDocument doc, PendingTransaction pendingTransaction, AwardHierarchyNode sourceAwardNode
                , AwardHierarchyNode destinationAwardNode, List<TransactionDetail> transactionDetailItems
                , List<AwardAmountTransaction> awardAmountTransactionItems, List<Award> awardItems) {
        
        String commonParent = findCommonParent(doc, sourceAwardNode.getRootAwardNumber(), sourceAwardNode.getParentAwardNumber(), destinationAwardNode.getAwardNumber());
        
        createTransaction(doc, pendingTransaction, commonParent,sourceAwardNode.getParentAwardNumber(), sourceAwardNode.getAwardNumber(), true, transactionDetailItems, awardAmountTransactionItems, awardItems);
        
        createTransaction(doc, pendingTransaction, commonParent,destinationAwardNode.getParentAwardNumber(), destinationAwardNode.getAwardNumber(), false, transactionDetailItems, awardAmountTransactionItems, awardItems);
    }

    /**
     * This method...
     * @param doc
     * @param pendingTransactionsToBeDeleted
     * @param pendingTransaction
     * @param commonParent
     * @param parentAwardNumber
     * @param awardNumber TODO
     * @param direction TODO
     */
    protected void createTransaction(TimeAndMoneyDocument doc,
            PendingTransaction pendingTransaction, String commonParent, String parentAwardNumber, String awardNumber, boolean direction, List<TransactionDetail> transactionDetailItems, List<AwardAmountTransaction> awardAmountTransactionItems, List<Award> awardItems) {
        
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
            
            persistTransactionDetailObject(sourceAwardNumber,destinationAwardNumber,doc.getSequenceNumber(), pendingTransaction, doc.getAwardNumber(), doc.getDocumentNumber(), transactionDetailItems);
            
            AwardHierarchyNode node = doc.getAwardHierarchyNodes().get(parentAwardNumber);
            
            handleSingleTransaction(false, false, pendingTransaction, node.getAwardNumber(), doc.getDocumentNumber(), awardAmountTransactionItems, awardItems);
            awardNumber = parentAwardNumber;
            parentAwardNumber = node.getParentAwardNumber();
        }while(!StringUtils.equalsIgnoreCase(commonParent,parentAwardNumber) && !StringUtils.equalsIgnoreCase(parentAwardNumber,PARENT_OF_ROOT));
        
    }
    
    protected void persistTransactionDetailObject(String sourceAwardNumber, String destinationAwardNumber, Integer sequenceNumber, PendingTransaction pendingTransaction, String currentAwardNumber, String documentNumber, List<TransactionDetail> transactionDetailItems){
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
        
        if(StringUtils.equalsIgnoreCase(parentOfSource, PARENT_OF_ROOT)){
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
     * @param pendingTransactionsToBeDeleted
     * @param pendingTransaction
     * @param timeAndMoneyDocumentNumber TODO
     * @param awardListDestination
     */
    private void handleSingleTransaction(boolean updateAmounts, boolean addOrSubtract,
            PendingTransaction pendingTransaction, String awardNumber, String timeAndMoneyDocumentNumber, List<AwardAmountTransaction> awardAmountTransactionItems, List<Award> awardItems) {
        
        Award award = getActiveAwardVersion(awardNumber);
        AwardAmountInfo awardAmountInfo = fetchAwardAmountInfoWithHighestTransactionId(award.getAwardAmountInfos());
        if(updateAmounts){
            if(addOrSubtract){
                
            }else{
                if(pendingTransaction.getObligatedAmount()!=null){
                    if(awardAmountInfo.getAmountObligatedToDate().subtract(pendingTransaction.getObligatedAmount()).isLessThan(new KualiDecimal(0))){
                        throw new RuntimeException("Insufficient Obligated Money");
                    }
                }
                if(pendingTransaction.getAnticipatedAmount()!=null){
                    if(awardAmountInfo.getAnticipatedTotalAmount().subtract(pendingTransaction.getAnticipatedAmount()).isLessThan(new KualiDecimal(0))){
                        throw new RuntimeException("Insufficient Anticipated Money");
                    }
                }
            }
        }
        award.getAwardAmountInfos().add(getNewAwardAmountInfoEntry(updateAmounts, addOrSubtract, pendingTransaction, awardAmountInfo, timeAndMoneyDocumentNumber, awardAmountTransactionItems));
        //transactionDetailList.add(getNewTransactionDetailEntry());
        awardItems.add(award);
    }
   
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
        List<String> parentAwardNumbers = new ArrayList<String>();
        
        for(AwardHierarchy awardHierarchy: awardHierarchies){
            parentAwardNumbers.add(awardHierarchy.getAwardNumber());    
        }
        
        return parentAwardNumbers; 
    }

    /**
     * This method...
     * @param addOrSubtract
     * @param pendingTransaction
     * @param awardAmountInfo
     * @param timeAndMoneyDocumentNumber TODO
     * @return
     */
    private AwardAmountInfo getNewAwardAmountInfoEntry(boolean updateAmounts, boolean addOrSubtract, PendingTransaction pendingTransaction, AwardAmountInfo awardAmountInfo, String timeAndMoneyDocumentNumber, List<AwardAmountTransaction> awardAmountTransactionItems) {
        AwardAmountInfo newAwardAmountInfo = new AwardAmountInfo();
        newAwardAmountInfo.setAwardNumber(awardAmountInfo.getAwardNumber());
        newAwardAmountInfo.setSequenceNumber(awardAmountInfo.getSequenceNumber());
        newAwardAmountInfo.setFinalExpirationDate(awardAmountInfo.getFinalExpirationDate());
        
        newAwardAmountInfo.setObliDistributableAmount(processAmounts(awardAmountInfo.getObliDistributableAmount(), pendingTransaction.getObligatedAmount(),addOrSubtract, updateAmounts));
        newAwardAmountInfo.setAmountObligatedToDate(processAmounts(awardAmountInfo.getAmountObligatedToDate(), pendingTransaction.getObligatedAmount(),addOrSubtract, updateAmounts));
        newAwardAmountInfo.setAntDistributableAmount(processAmounts(awardAmountInfo.getAntDistributableAmount(), pendingTransaction.getAnticipatedAmount(),addOrSubtract, updateAmounts));
        newAwardAmountInfo.setAnticipatedTotalAmount(processAmounts(awardAmountInfo.getAnticipatedTotalAmount(), pendingTransaction.getAnticipatedAmount(),addOrSubtract, updateAmounts));
        if(updateAmounts){
            newAwardAmountInfo.setObligatedChange(pendingTransaction.getObligatedAmount());
            newAwardAmountInfo.setAnticipatedChange(pendingTransaction.getAnticipatedAmount());    
        }else{
            newAwardAmountInfo.setObligatedChange(new KualiDecimal(0));
            newAwardAmountInfo.setAnticipatedChange(new KualiDecimal(0));
        }
        
        newAwardAmountInfo.setTimeAndMoneyDocumentNumber(timeAndMoneyDocumentNumber);
        
        newAwardAmountInfo.setTransactionId(pendingTransaction.getTransactionId());
        
        persistAwardAmountTransactionEntry(newAwardAmountInfo.getAwardNumber(), awardAmountTransactionItems);

        return newAwardAmountInfo;
    }
    
    private void persistAwardAmountTransactionEntry(String awardNumber, List<AwardAmountTransaction> awardAmountTransactionItems) {
        TimeAndMoneyForm form = (TimeAndMoneyForm) GlobalVariables.getKualiForm();
        AwardAmountTransaction newAwardAmountTransaction = form.getTransactionBean().getNewAwardAmountTransaction();
        newAwardAmountTransaction.setAwardNumber(awardNumber);
        newAwardAmountTransaction.setDocumentNumber(form.getDocument().getDocumentNumber());
        awardAmountTransactionItems.add(newAwardAmountTransaction);        
    }

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
