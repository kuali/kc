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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.plexus.util.StringUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.timeandmoney.TimeAndMoneyForm;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.service.ActivePendingTransactionsService;
import org.kuali.kra.timeandmoney.transactions.PendingTransaction;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KualiDecimal;

public class ActivePendingTransactionsServiceImpl implements ActivePendingTransactionsService {
    
    BusinessObjectService businessObjectService;

    public void approveTransactions() {
        TimeAndMoneyForm form = (TimeAndMoneyForm) GlobalVariables.getKualiForm();
        TimeAndMoneyDocument doc = (TimeAndMoneyDocument) form.getDocument();
        Map<String, String> fieldValues = new HashMap<String, String>();
        List<PendingTransaction> pendingTransactionsToBeDeleted = new ArrayList<PendingTransaction>();
        boolean addOrDelete = false;
        for(PendingTransaction pendingTransaction: doc.getPendingTransactions()){            
            for(Entry<String, AwardHierarchyNode> awardHierarchyNode: doc.getAwardHierarchyNodes().entrySet()){
                
                if(StringUtils.equalsIgnoreCase(awardHierarchyNode.getKey(), pendingTransaction.getSourceAwardNumber())){
                    fieldValues.put("awardNumber", pendingTransaction.getSourceAwardNumber());
                    addOrDelete = false;
                }else if(StringUtils.equalsIgnoreCase(awardHierarchyNode.getKey(), pendingTransaction.getDestinationAwardNumber())){
                    fieldValues.put("awardNumber", pendingTransaction.getDestinationAwardNumber());
                    addOrDelete = true;
                }
                
                List<Award> awardList = (List<Award>)businessObjectService.findMatching(Award.class, fieldValues);
                Award award = fetchAwardWithMaximumSequence(awardList);
                AwardAmountInfo awardAmountInfo = award.getAwardAmountInfos().get(0);                
                award.getAwardAmountInfos().add(getNewAwardAmountInfoEntry(addOrDelete, pendingTransaction, awardAmountInfo));
                businessObjectService.save(award);
                pendingTransactionsToBeDeleted.add(pendingTransaction);                
            }
        }
        
        for(PendingTransaction pendingTransaction: pendingTransactionsToBeDeleted){
            doc.getPendingTransactions().remove(pendingTransaction);
        }
    }

    /**
     * This method...
     * @param addOrDelete
     * @param pendingTransaction
     * @param awardAmountInfo
     * @return
     */
    private AwardAmountInfo getNewAwardAmountInfoEntry(boolean addOrDelete, PendingTransaction pendingTransaction, AwardAmountInfo awardAmountInfo) {
        AwardAmountInfo newAwardAmountInfo = new AwardAmountInfo();
        newAwardAmountInfo.setAwardNumber(awardAmountInfo.getAwardNumber());
        newAwardAmountInfo.setSequenceNumber(awardAmountInfo.getSequenceNumber());
        newAwardAmountInfo.setFinalExpirationDate(awardAmountInfo.getFinalExpirationDate());
        
        newAwardAmountInfo.setObliDistributableAmount(processAmounts(awardAmountInfo.getObliDistributableAmount(), pendingTransaction.getObligatedAmount(),addOrDelete));
        newAwardAmountInfo.setAmountObligatedToDate(processAmounts(awardAmountInfo.getAmountObligatedToDate(), pendingTransaction.getObligatedAmount(),addOrDelete));
        newAwardAmountInfo.setAntDistributableAmount(processAmounts(awardAmountInfo.getAntDistributableAmount(), pendingTransaction.getAnticipatedAmount(),addOrDelete));
        newAwardAmountInfo.setAnticipatedTotalAmount(processAmounts(awardAmountInfo.getAnticipatedTotalAmount(), pendingTransaction.getAnticipatedAmount(),addOrDelete));
         
        newAwardAmountInfo.setTransactionId(pendingTransaction.getTransactionId());
        return newAwardAmountInfo;
    }
    
    protected KualiDecimal processAmounts(KualiDecimal value1, KualiDecimal value2, boolean addOrDelete){
        KualiDecimal returnValue;
        if(value1!=null){
            if(addOrDelete){
                returnValue =  value1.add(value2);    
            }else{
                returnValue =  value1.subtract(value2);
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
