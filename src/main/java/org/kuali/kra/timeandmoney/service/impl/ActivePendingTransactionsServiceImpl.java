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

import org.codehaus.plexus.util.StringUtils;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
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
    
    BusinessObjectService businessObjectService;

    public void approveTransactions() {
        TimeAndMoneyForm form = (TimeAndMoneyForm) GlobalVariables.getKualiForm();
        TimeAndMoneyDocument doc = (TimeAndMoneyDocument) form.getDocument();
        
        List<PendingTransaction> pendingTransactionsToBeDeleted = new ArrayList<PendingTransaction>();
        
        for(PendingTransaction pendingTransaction: doc.getPendingTransactions()){
            AwardHierarchyNode sourceAwardNode = doc.getAwardHierarchyNodes().get(pendingTransaction.getSourceAwardNumber());
            AwardHierarchyNode destinationAwardNode = doc.getAwardHierarchyNodes().get(pendingTransaction.getDestinationAwardNumber());

            if(StringUtils.equalsIgnoreCase(pendingTransaction.getSourceAwardNumber(),"000000-00000")){                
                
                handleSingleTransaction(true, true, pendingTransactionsToBeDeleted, pendingTransaction, destinationAwardNode.getAwardNumber(), doc.getDocumentNumber());
                
                TransactionDetail transactionDetail = new TransactionDetail();
                transactionDetail.setSourceAwardNumber("000000-00000");
                transactionDetail.setSequenceNumber(doc.getSequenceNumber());
                transactionDetail.setDestinationAwardNumber(destinationAwardNode.getAwardNumber());
                transactionDetail.setAnticipatedAmount(pendingTransaction.getAnticipatedAmount());
                transactionDetail.setObligatedAmount(pendingTransaction.getObligatedAmount());
                transactionDetail.setAwardNumber(doc.getAwardNumber());
                transactionDetail.setTransactionId(pendingTransaction.getTransactionId());
                transactionDetail.setTimeAndMoneyDocumentNumber(doc.getDocumentNumber());
                transactionDetail.setComments(pendingTransaction.getComments());
                businessObjectService.save(transactionDetail);
                
            }else if(StringUtils.equalsIgnoreCase(sourceAwardNode.getParentAwardNumber(), destinationAwardNode.getAwardNumber()) ||
                    StringUtils.equalsIgnoreCase(destinationAwardNode.getParentAwardNumber(), sourceAwardNode.getAwardNumber())){
                
                handleSingleTransaction(true, false, pendingTransactionsToBeDeleted, pendingTransaction, sourceAwardNode.getAwardNumber(), doc.getDocumentNumber());
                
                handleSingleTransaction(true, true, pendingTransactionsToBeDeleted, pendingTransaction, destinationAwardNode.getAwardNumber(), doc.getDocumentNumber());
                
                TransactionDetail transactionDetail = new TransactionDetail();
                transactionDetail.setSourceAwardNumber(sourceAwardNode.getAwardNumber());
                transactionDetail.setSequenceNumber(doc.getSequenceNumber());
                transactionDetail.setDestinationAwardNumber(destinationAwardNode.getAwardNumber());
                transactionDetail.setAnticipatedAmount(pendingTransaction.getAnticipatedAmount());
                transactionDetail.setObligatedAmount(pendingTransaction.getObligatedAmount());
                transactionDetail.setAwardNumber(doc.getAwardNumber());
                transactionDetail.setTransactionId(pendingTransaction.getTransactionId());
                transactionDetail.setTimeAndMoneyDocumentNumber(doc.getDocumentNumber());
                transactionDetail.setComments(pendingTransaction.getComments());
                businessObjectService.save(transactionDetail);
                
            }else if(StringUtils.equalsIgnoreCase(sourceAwardNode.getParentAwardNumber(), destinationAwardNode.getParentAwardNumber())){
                
                handleSingleTransaction(false, false, pendingTransactionsToBeDeleted, pendingTransaction, sourceAwardNode.getParentAwardNumber(), doc.getDocumentNumber());
                
                handleSingleTransaction(true, false, pendingTransactionsToBeDeleted, pendingTransaction, sourceAwardNode.getAwardNumber(), doc.getDocumentNumber());
                
                handleSingleTransaction(true, true, pendingTransactionsToBeDeleted, pendingTransaction, destinationAwardNode.getAwardNumber(), doc.getDocumentNumber());
                
                TransactionDetail transactionDetail = new TransactionDetail();
                transactionDetail.setSourceAwardNumber(sourceAwardNode.getAwardNumber());
                transactionDetail.setSequenceNumber(doc.getSequenceNumber());
                transactionDetail.setDestinationAwardNumber(sourceAwardNode.getParentAwardNumber());
                transactionDetail.setAnticipatedAmount(pendingTransaction.getAnticipatedAmount());
                transactionDetail.setObligatedAmount(pendingTransaction.getObligatedAmount());
                transactionDetail.setAwardNumber(doc.getAwardNumber());
                transactionDetail.setTransactionId(pendingTransaction.getTransactionId());
                transactionDetail.setTimeAndMoneyDocumentNumber(doc.getDocumentNumber());
                transactionDetail.setComments(pendingTransaction.getComments());
                businessObjectService.save(transactionDetail);
                
                transactionDetail = new TransactionDetail();
                transactionDetail.setSourceAwardNumber(sourceAwardNode.getParentAwardNumber());
                transactionDetail.setSequenceNumber(doc.getSequenceNumber());
                transactionDetail.setDestinationAwardNumber(destinationAwardNode.getAwardNumber());
                transactionDetail.setAnticipatedAmount(pendingTransaction.getAnticipatedAmount());
                transactionDetail.setObligatedAmount(pendingTransaction.getObligatedAmount());
                transactionDetail.setAwardNumber(doc.getAwardNumber());
                transactionDetail.setTransactionId(pendingTransaction.getTransactionId());
                transactionDetail.setTimeAndMoneyDocumentNumber(doc.getDocumentNumber());
                transactionDetail.setComments(pendingTransaction.getComments());
                businessObjectService.save(transactionDetail);
                
            }else{
                handleSingleTransaction(true, false, pendingTransactionsToBeDeleted, pendingTransaction, sourceAwardNode.getAwardNumber(), doc.getDocumentNumber());
                
                createTransaction(doc, pendingTransactionsToBeDeleted, pendingTransaction, sourceAwardNode, destinationAwardNode);
                
                handleSingleTransaction(true, true, pendingTransactionsToBeDeleted, pendingTransaction, destinationAwardNode.getAwardNumber(), doc.getDocumentNumber());
                
                                
            }
            
            pendingTransactionsToBeDeleted.add(pendingTransaction);
        }
        
        for(PendingTransaction pendingTransaction: pendingTransactionsToBeDeleted){
            doc.getPendingTransactions().remove(pendingTransaction);
        }
    }

    /**
     * This method...
     * @param doc
     * @param pendingTransactionsToBeDeleted
     * @param pendingTransaction
     * @param sourceAwardNode
     * @param destinationAwardNode
     */
    private void createTransaction(TimeAndMoneyDocument doc, List<PendingTransaction> pendingTransactionsToBeDeleted,
            PendingTransaction pendingTransaction, AwardHierarchyNode sourceAwardNode, AwardHierarchyNode destinationAwardNode) {
        
        String commonParent = findCommonParent(doc, sourceAwardNode.getParentAwardNumber(), destinationAwardNode.getAwardNumber());
        
        createTransaction(doc, pendingTransactionsToBeDeleted, pendingTransaction, commonParent,sourceAwardNode.getParentAwardNumber(), sourceAwardNode.getAwardNumber(), true);
        
        createTransaction(doc, pendingTransactionsToBeDeleted, pendingTransaction, commonParent,destinationAwardNode.getParentAwardNumber(), destinationAwardNode.getAwardNumber(), false);
    }

    /**
     * This method...
     * @param doc
     * @param pendingTransactionsToBeDeleted
     * @param pendingTransaction
     * @param commonParent
     * @param parentAwardNumber
     * @param awardNumber TODO
     * @param addOrSubtract TODO
     */
    protected void createTransaction(TimeAndMoneyDocument doc, List<PendingTransaction> pendingTransactionsToBeDeleted,
            PendingTransaction pendingTransaction, String commonParent, String parentAwardNumber, String awardNumber, boolean addOrSubtract) {
        TransactionDetail transactionDetail;
        
        while(commonParent!=parentAwardNumber){
            transactionDetail = new TransactionDetail();
            if(addOrSubtract){
                transactionDetail.setSourceAwardNumber(awardNumber);
                transactionDetail.setDestinationAwardNumber(parentAwardNumber);    
            }else{
                transactionDetail.setSourceAwardNumber(parentAwardNumber);
                transactionDetail.setDestinationAwardNumber(awardNumber);
            }
            
            transactionDetail.setSequenceNumber(doc.getSequenceNumber());
            transactionDetail.setAnticipatedAmount(pendingTransaction.getAnticipatedAmount());
            transactionDetail.setObligatedAmount(pendingTransaction.getObligatedAmount());
            transactionDetail.setAwardNumber(doc.getAwardNumber());
            transactionDetail.setTransactionId(pendingTransaction.getTransactionId());
            transactionDetail.setTimeAndMoneyDocumentNumber(doc.getDocumentNumber());
            transactionDetail.setComments(pendingTransaction.getComments());
            businessObjectService.save(transactionDetail);
            
            AwardHierarchyNode node = doc.getAwardHierarchyNodes().get(parentAwardNumber);
            
            handleSingleTransaction(false, false, pendingTransactionsToBeDeleted, pendingTransaction, node.getAwardNumber(), doc.getDocumentNumber());
            awardNumber = parentAwardNumber;
            parentAwardNumber = node.getParentAwardNumber();
        }
        
    }
    
    protected String findCommonParent(TimeAndMoneyDocument doc, String parentOfSource, String destinationAwardNumber){
        boolean commonParentFound = false;
        AwardHierarchyNode parentNode = null;
        while(!commonParentFound){
            List<String> childrenAwardNumbers = findChildren(parentNode.getParentAwardNumber());
            if(childrenAwardNumbers.contains(destinationAwardNumber)){
                commonParentFound = true;
            }else{
                parentNode = doc.getAwardHierarchyNodes().get(parentOfSource);
            }
        }
        return parentNode.getParentAwardNumber();
    }

    /**
     * This method...
     * @param pendingTransactionsToBeDeleted
     * @param pendingTransaction
     * @param timeAndMoneyDocumentNumber TODO
     * @param awardListDestination
     */
    private void handleSingleTransaction(boolean updateAmounts, boolean addOrSubtract, List<PendingTransaction> pendingTransactionsToBeDeleted,
            PendingTransaction pendingTransaction, String awardNumber, String timeAndMoneyDocumentNumber) {
        
        Map<String, String> fieldValues = new HashMap<String, String>();
        //List<TransactionDetail> transactionDetailList = new ArrayList<TransactionDetail>();        
        fieldValues.put("awardNumber", awardNumber);                    
        List<Award> awardListDestination = (List<Award>)businessObjectService.findMatching(Award.class, fieldValues);
        fieldValues.remove("awardNumber");
        
        Award awardDestination = fetchAwardWithMaximumSequence(awardListDestination);
        AwardAmountInfo awardAmountInfo = awardDestination.getAwardAmountInfos().get(0);                
        awardDestination.getAwardAmountInfos().add(getNewAwardAmountInfoEntry(updateAmounts, addOrSubtract, pendingTransaction, awardAmountInfo, timeAndMoneyDocumentNumber));
        //transactionDetailList.add(getNewTransactionDetailEntry());
        businessObjectService.save(awardDestination);
    }
   
    private TransactionDetail getNewTransactionDetailEntry() {
        // TODO Auto-generated method stub
        return null;
    }

    private List<String> findChildren(String parent) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("parentAwardNumber", parent);
        Collection<AwardHierarchy> awardHierarchies = businessObjectService.findMatchingOrderBy(AwardHierarchy.class, fieldValues, "awardNumber", true);
        List<String> parentAwardNumbers = new ArrayList<String>();
        
        for(AwardHierarchy awardHierarchy: awardHierarchies){
            parentAwardNumbers.add(awardHierarchy.getParentAwardNumber());    
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
    private AwardAmountInfo getNewAwardAmountInfoEntry(boolean updateAmounts, boolean addOrSubtract, PendingTransaction pendingTransaction, AwardAmountInfo awardAmountInfo, String timeAndMoneyDocumentNumber) {
        AwardAmountInfo newAwardAmountInfo = new AwardAmountInfo();
        newAwardAmountInfo.setAwardNumber(awardAmountInfo.getAwardNumber());
        newAwardAmountInfo.setSequenceNumber(awardAmountInfo.getSequenceNumber());
        newAwardAmountInfo.setFinalExpirationDate(awardAmountInfo.getFinalExpirationDate());
        
        newAwardAmountInfo.setObliDistributableAmount(processAmounts(awardAmountInfo.getObliDistributableAmount(), pendingTransaction.getObligatedAmount(),addOrSubtract, updateAmounts));
        newAwardAmountInfo.setAmountObligatedToDate(processAmounts(awardAmountInfo.getAmountObligatedToDate(), pendingTransaction.getObligatedAmount(),addOrSubtract, updateAmounts));
        newAwardAmountInfo.setAntDistributableAmount(processAmounts(awardAmountInfo.getAntDistributableAmount(), pendingTransaction.getAnticipatedAmount(),addOrSubtract, updateAmounts));
        newAwardAmountInfo.setAnticipatedTotalAmount(processAmounts(awardAmountInfo.getAnticipatedTotalAmount(), pendingTransaction.getAnticipatedAmount(),addOrSubtract, updateAmounts));
        newAwardAmountInfo.setObligatedChange(pendingTransaction.getObligatedAmount());
        newAwardAmountInfo.setAnticipatedChange(pendingTransaction.getAnticipatedAmount());
        newAwardAmountInfo.setTimeAndMoneyDocumentNumber(timeAndMoneyDocumentNumber);
        
        newAwardAmountInfo.setTransactionId(pendingTransaction.getTransactionId());
        
        persistAwardAmountTransactionEntry(newAwardAmountInfo.getAwardNumber());
        //persistTransactionDetails();
        return newAwardAmountInfo;
    }
    
    private void persistAwardAmountTransactionEntry(String awardNumber) {
        TimeAndMoneyForm form = (TimeAndMoneyForm) GlobalVariables.getKualiForm();
        AwardAmountTransaction newAwardAmountTransaction = form.getTransactionBean().getNewAwardAmountTransaction();
        newAwardAmountTransaction.setAwardNumber(awardNumber);
        newAwardAmountTransaction.setDocumentNumber(form.getDocument().getDocumentNumber());
        businessObjectService.save(newAwardAmountTransaction);
    }

    protected KualiDecimal processAmounts(KualiDecimal value1, KualiDecimal value2, boolean addOrSubtract, boolean updateAmounts){
        KualiDecimal returnValue;
        if(updateAmounts && value1!=null){
            if(addOrSubtract){
                returnValue =  value1.add(value2);    
            }else{
                if(!(value1.subtract(value2)).isLessThan(new KualiDecimal(0))){
                    returnValue =  value1.subtract(value2);    
                }else{
                    throw new RuntimeException("Source Does Not Have Enough money");
                }
            }
            
        }else{
            returnValue = value2;
        }
        return returnValue;
    }
    
    protected Award fetchAwardWithMaximumSequence(List<Award> awardList){
        return awardList.get(0);
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
