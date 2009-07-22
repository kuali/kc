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

public class ActivePendingTransactionsServiceImpl implements ActivePendingTransactionsService {
    
    BusinessObjectService businessObjectService;

    public void approveTransactions() {
        
        TimeAndMoneyForm form = (TimeAndMoneyForm) GlobalVariables.getKualiForm();
        TimeAndMoneyDocument doc = (TimeAndMoneyDocument) form.getDocument();
        Map<String, String> fieldValues = new HashMap<String, String>();
        List<PendingTransaction> pendingTransactionsToBeDeleted = new ArrayList<PendingTransaction>();
        
        for(PendingTransaction pendingTransaction: doc.getPendingTransactions()){
            
            for(Entry<String, AwardHierarchyNode> awardHierarchyNode: doc.getAwardHierarchyNodes().entrySet()){
                if(StringUtils.equalsIgnoreCase(awardHierarchyNode.getKey(), pendingTransaction.getSourceAwardNumber())){
                    fieldValues.put("awardNumber", pendingTransaction.getSourceAwardNumber());
                    List<Award> awardList = (List<Award>)businessObjectService.findMatching(Award.class, fieldValues);
                    Award award = fetchAwardWithMaximumSequence(awardList);
                    AwardAmountInfo awardAmountInfo = award.getAwardAmountInfos().get(0);
                    AwardAmountInfo newAwardAmountInfo = new AwardAmountInfo();
                    newAwardAmountInfo.setAwardNumber(awardAmountInfo.getAwardNumber());
                    newAwardAmountInfo.setSequenceNumber(awardAmountInfo.getSequenceNumber());
                    newAwardAmountInfo.setFinalExpirationDate(awardAmountInfo.getFinalExpirationDate());                    
                    if(awardAmountInfo.getObliDistributableAmount()!=null){
                        newAwardAmountInfo.setObliDistributableAmount(awardAmountInfo.getObliDistributableAmount().subtract(pendingTransaction.getObligatedAmount()));    
                    }else{
                        newAwardAmountInfo.setObliDistributableAmount(pendingTransaction.getObligatedAmount());
                    }
                    if(awardAmountInfo.getAmountObligatedToDate()!=null){
                        newAwardAmountInfo.setAmountObligatedToDate(awardAmountInfo.getAmountObligatedToDate().subtract(pendingTransaction.getObligatedAmount()));
                    }else{
                        newAwardAmountInfo.setAmountObligatedToDate(pendingTransaction.getObligatedAmount());
                    }
                    if(awardAmountInfo.getAntDistributableAmount()!=null){
                        newAwardAmountInfo.setAntDistributableAmount(awardAmountInfo.getAntDistributableAmount().subtract(pendingTransaction.getAnticipatedAmount()));
                    }else{
                        newAwardAmountInfo.setAntDistributableAmount(pendingTransaction.getAnticipatedAmount());                        
                    }
                    if(awardAmountInfo.getAnticipatedTotalAmount()!=null){
                        newAwardAmountInfo.setAnticipatedTotalAmount(awardAmountInfo.getAnticipatedTotalAmount().subtract(pendingTransaction.getAnticipatedAmount()));
                    }else{
                        newAwardAmountInfo.setAnticipatedTotalAmount(pendingTransaction.getAnticipatedAmount());    
                    }                    
                    newAwardAmountInfo.setTransactionId(pendingTransaction.getTransactionId());
                    award.getAwardAmountInfos().add(newAwardAmountInfo);
                    businessObjectService.save(award);
                    pendingTransactionsToBeDeleted.add(pendingTransaction);
                }
                if(StringUtils.equalsIgnoreCase(awardHierarchyNode.getKey(), pendingTransaction.getDestinationAwardNumber())){
                    fieldValues.put("awardNumber", pendingTransaction.getDestinationAwardNumber());
                    List<Award> awardList = (List<Award>)businessObjectService.findMatching(Award.class, fieldValues);
                    Award award = fetchAwardWithMaximumSequence(awardList);
                    AwardAmountInfo awardAmountInfo = award.getAwardAmountInfos().get(0);
                    AwardAmountInfo newAwardAmountInfo = new AwardAmountInfo();
                    newAwardAmountInfo.setAwardNumber(awardAmountInfo.getAwardNumber());
                    newAwardAmountInfo.setSequenceNumber(awardAmountInfo.getSequenceNumber());
                    newAwardAmountInfo.setFinalExpirationDate(awardAmountInfo.getFinalExpirationDate());
                    if(awardAmountInfo.getObliDistributableAmount()!=null){
                        newAwardAmountInfo.setObliDistributableAmount(awardAmountInfo.getObliDistributableAmount().add(pendingTransaction.getObligatedAmount()));    
                    }else{
                        newAwardAmountInfo.setObliDistributableAmount(pendingTransaction.getObligatedAmount());
                    }
                    if(awardAmountInfo.getAmountObligatedToDate()!=null){
                        newAwardAmountInfo.setAmountObligatedToDate(awardAmountInfo.getAmountObligatedToDate().add(pendingTransaction.getObligatedAmount()));
                    }else{
                        newAwardAmountInfo.setAmountObligatedToDate(pendingTransaction.getObligatedAmount());
                    }
                    if(awardAmountInfo.getAntDistributableAmount()!=null){
                        newAwardAmountInfo.setAntDistributableAmount(awardAmountInfo.getAntDistributableAmount().add(pendingTransaction.getAnticipatedAmount()));
                    }else{
                        newAwardAmountInfo.setAntDistributableAmount(pendingTransaction.getAnticipatedAmount());                        
                    }
                    if(awardAmountInfo.getAnticipatedTotalAmount()!=null){
                        newAwardAmountInfo.setAnticipatedTotalAmount(awardAmountInfo.getAnticipatedTotalAmount().add(pendingTransaction.getAnticipatedAmount()));
                    }else{
                        newAwardAmountInfo.setAnticipatedTotalAmount(pendingTransaction.getAnticipatedAmount());    
                    }
                                        
                    newAwardAmountInfo.setTransactionId(pendingTransaction.getTransactionId());
                    award.getAwardAmountInfos().add(newAwardAmountInfo);
                    businessObjectService.save(award);
                    pendingTransactionsToBeDeleted.add(pendingTransaction);
                }
                
            }
        }
        
        for(PendingTransaction pendingTransaction: pendingTransactionsToBeDeleted){
            doc.getPendingTransactions().remove(pendingTransaction);
        }
        

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
