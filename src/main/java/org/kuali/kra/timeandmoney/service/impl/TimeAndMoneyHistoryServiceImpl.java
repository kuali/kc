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

import org.codehaus.plexus.util.StringUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.history.TransactionDetail;
import org.kuali.kra.timeandmoney.service.TimeAndMoneyHistoryService;
import org.kuali.kra.timeandmoney.transactions.AwardAmountTransaction;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.service.BusinessObjectService;

public class TimeAndMoneyHistoryServiceImpl implements TimeAndMoneyHistoryService {
    
    BusinessObjectService businessObjectService; 

    public void getTimeAndMoneyHistory(String awardNumber, Map<Object, Object> timeAndMoneyHistory, List<Integer> columnSpan) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        Map<String, Object> fieldValues1 = new HashMap<String, Object>();
        Map<String, Object> fieldValues2 = new HashMap<String, Object>();
        Map<String, Object> fieldValues3 = new HashMap<String, Object>();        
        
        AwardAmountTransaction awardAmountTransaction = null;
        timeAndMoneyHistory.clear();
        fieldValues.put("awardNumber", awardNumber);
        List<Award> awards = (List<Award>)businessObjectService.findMatchingOrderBy(Award.class, fieldValues, "sequenceNumber", true);        
        List<TimeAndMoneyDocument> docs = null;
        int key = 150;
        
        for(Award award : awards){
            award.refreshReferenceObject("awardDocument");
            timeAndMoneyHistory.put(buildDocumentUrl(award.getAwardDocument().getDocumentNumber()), "url for award document");
            fieldValues1.put("rootAwardNumber", award.getAwardNumber());
            
            docs = (List<TimeAndMoneyDocument>)businessObjectService.findMatchingOrderBy(TimeAndMoneyDocument.class, fieldValues1, "documentNumber", true);
            for(TimeAndMoneyDocument doc: docs){
                fieldValues2.put("awardNumber", award.getAwardNumber());
                fieldValues2.put("documentNumber", doc.getDocumentNumber());
                List<AwardAmountTransaction> awardAmountTransactions = ((List<AwardAmountTransaction>)businessObjectService.findMatching(AwardAmountTransaction.class, fieldValues2));
                if(awardAmountTransactions.size()>0){
                    awardAmountTransaction = awardAmountTransactions.get(0);
                    timeAndMoneyHistory.put(buildDocumentUrl(doc.getDocumentNumber()), awardAmountTransaction.getComments());
                }    
                
                for(AwardAmountInfo awardAmountInfo : award.getAwardAmountInfos()){
                    if(StringUtils.equalsIgnoreCase(doc.getDocumentNumber(),awardAmountInfo.getTimeAndMoneyDocumentNumber())){
                        timeAndMoneyHistory.put(awardAmountInfo.getTransactionId(), awardAmountInfo);
                        fieldValues3.put("awardNumber", awardAmountInfo.getAwardNumber());
                        fieldValues3.put("sequenceNumber", awardAmountInfo.getSequenceNumber());
                        fieldValues3.put("transactionId", awardAmountInfo.getTransactionId());
                        fieldValues3.put("timeAndMoneyDocumentNumber", awardAmountInfo.getTimeAndMoneyDocumentNumber());
                        List<TransactionDetail> transactionDetails = ((List<TransactionDetail>)businessObjectService.findMatchingOrderBy(TransactionDetail.class, fieldValues3, "sourceAwardNumber", true));
                        int i = 0;
                        for(TransactionDetail transactionDetail : transactionDetails){
                            timeAndMoneyHistory.put(key, transactionDetail);
                            key++;
                            i++;
                        }
                        columnSpan.add(i);
                    }
                }
            }
            
            
        }        
    }
    
    private String buildDocumentUrl(String documentNumber){
        StringBuilder sb = new StringBuilder();
        sb.append("<a href=\"");
        sb.append("kew/");
        sb.append(KEWConstants.DOC_HANDLER_REDIRECT_PAGE);
        sb.append("?");
        sb.append(KEWConstants.COMMAND_PARAMETER);
        sb.append("=");
        sb.append(KEWConstants.DOCSEARCH_COMMAND);
        sb.append("&");
        sb.append(KEWConstants.ROUTEHEADER_ID_PARAMETER);
        sb.append("=");
        sb.append(documentNumber);
        sb.append("\"");
        sb.append(">");
        sb.append(documentNumber);
        sb.append("</a>");
        return sb.toString();
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
