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

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.history.TransactionDetail;
import org.kuali.kra.timeandmoney.service.TimeAndMoneyHistoryService;
import org.kuali.kra.timeandmoney.transactions.AwardAmountTransaction;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.web.ui.HeaderField;

public class TimeAndMoneyHistoryServiceImpl implements TimeAndMoneyHistoryService {
    
    private static final int NUMBER_30 = 30;
    BusinessObjectService businessObjectService; 
    DocumentService documentService;
    private String DASH = "-";

    @SuppressWarnings("unchecked")
    public void getTimeAndMoneyHistory(String awardNumber, Map<Object, Object> timeAndMoneyHistory, List<Integer> columnSpan) throws WorkflowException {
        Map<String, String> fieldValues = new HashMap<String, String>();
        Map<String, Object> fieldValues1 = new HashMap<String, Object>();
        Map<String, Object> fieldValues2 = new HashMap<String, Object>();
        Map<String, Object> fieldValues3 = new HashMap<String, Object>();
        Map<String, Object> fieldValues3a = new HashMap<String, Object>();
        Map<String, Object> fieldValues5 = new HashMap<String, Object>();


        Map<String, Object> fieldValues4 = new HashMap<String, Object>(); 
        
        AwardAmountTransaction awardAmountTransaction = null;
        timeAndMoneyHistory.clear();
        fieldValues.put("awardNumber", awardNumber);
        List<Award> awards = (List<Award>)businessObjectService.findMatchingOrderBy(Award.class, fieldValues, "sequenceNumber", true);        
        List<TimeAndMoneyDocument> docs = null;
        int key = 150;
        int j = -1;
        
        for(Award award : awards){//this is all versions of the selected Award in the hierarchy.
            award.refreshReferenceObject("awardDocument");
            //timeAndMoneyHistory.put(buildDocumentUrl(award.getAwardDocument().getDocumentNumber()), "url for award document");
            //timeAndMoneyHistory.put(buildDocumentUrl(award.getAwardDocument().getDocumentNumber()), buildAwardDescriptionLine(award));
            //to get all docs, we must pass the root award number for the subject award.
            fieldValues1.put("rootAwardNumber", getRootAwardNumberForDocumentSearch(award.getAwardNumber()));
            docs = (List<TimeAndMoneyDocument>)businessObjectService.findMatchingOrderBy(TimeAndMoneyDocument.class, fieldValues1, "documentNumber", true);
            timeAndMoneyHistory.put(buildDocumentUrl(award.getAwardDocument().getDocumentNumber()), buildAwardDescriptionLine(award, docs.get(docs.size() -1)));
            for(TimeAndMoneyDocument tempDoc: docs){
                TimeAndMoneyDocument doc = (TimeAndMoneyDocument) documentService.getByDocumentHeaderId(tempDoc.getDocumentNumber());
                //we don't want canceled docs in history.
                if(doc.getDocumentHeader().hasWorkflowDocument()) {
                    if(!doc.getDocumentHeader().getWorkflowDocument().stateIsCanceled()) {
                        fieldValues2.put("awardNumber", award.getAwardNumber());
                        fieldValues2.put("documentNumber", doc.getDocumentNumber());
                        //List<AwardAmountTransaction> awardAmountTransactions = ((List<AwardAmountTransaction>)businessObjectService.findMatching(AwardAmountTransaction.class, fieldValues2));
                        List<AwardAmountTransaction> awardAmountTransactions = doc.getAwardAmountTransactions();
        
                        if(awardAmountTransactions.size()>0){
                            awardAmountTransaction = awardAmountTransactions.get(0);
                            timeAndMoneyHistory.put(buildDocumentUrl(doc.getDocumentNumber()), awardAmountTransaction.getComments());
                        } 
                        
                        //capture initial transaction
                        //we only want display this once.
                        for(AwardAmountInfo awardAmountInfo : award.getAwardAmountInfos()){
                            if(StringUtils.equalsIgnoreCase(doc.getDocumentNumber(),awardAmountInfo.getTimeAndMoneyDocumentNumber())){ 
                                fieldValues5.put("sourceAwardNumber", awardAmountInfo.getAwardNumber());
                                fieldValues5.put("sequenceNumber", awardAmountInfo.getSequenceNumber());
                                fieldValues5.put("transactionId", 0);
                                fieldValues5.put("timeAndMoneyDocumentNumber", awardAmountInfo.getTimeAndMoneyDocumentNumber());
                                List<TransactionDetail> transactionDetailsA = 
                                    ((List<TransactionDetail>)businessObjectService.findMatchingOrderBy(TransactionDetail.class, fieldValues5, "sourceAwardNumber", true));
                                if(transactionDetailsA.size() > 0) {
                                    TransactionDetail transactionDetail = transactionDetailsA.get(0);
                                    timeAndMoneyHistory.put(0, awardAmountInfo);
                                    timeAndMoneyHistory.put(key, transactionDetail);
                                    key++;
                                    break;
                                }else {
                                    break;
                                }
                            }
                        }
                        
                        //capture money transactions
                        for(AwardAmountInfo awardAmountInfo : award.getAwardAmountInfos()){
                            if(StringUtils.equalsIgnoreCase(doc.getDocumentNumber(),awardAmountInfo.getTimeAndMoneyDocumentNumber())){ 
                                List<TransactionDetail> transactionDetails = new ArrayList<TransactionDetail>();
                                //get all Transaction Details for a node.  It can be the source or a destination of the transaction.
                                fieldValues3.put("sourceAwardNumber", awardAmountInfo.getAwardNumber());
                                fieldValues3.put("sequenceNumber", awardAmountInfo.getSequenceNumber());
                                fieldValues3.put("transactionId", awardAmountInfo.getTransactionId());
                                fieldValues3.put("timeAndMoneyDocumentNumber", awardAmountInfo.getTimeAndMoneyDocumentNumber());
                                
                                fieldValues3a.put("destinationAwardNumber", awardAmountInfo.getAwardNumber());
                                fieldValues3a.put("sequenceNumber", awardAmountInfo.getSequenceNumber());
                                fieldValues3a.put("transactionId", awardAmountInfo.getTransactionId());
                                fieldValues3a.put("timeAndMoneyDocumentNumber", awardAmountInfo.getTimeAndMoneyDocumentNumber());
                                //should only return one transaction detail because we are making transaction ID unique when we set
                                //to Pending Transaction ID in ActivePendingTransactionService.
                                List<TransactionDetail> transactionDetailsA = 
                                    ((List<TransactionDetail>)businessObjectService.findMatchingOrderBy(TransactionDetail.class, fieldValues3, "sourceAwardNumber", true));
                                List<TransactionDetail> transactionDetailsB = 
                                    ((List<TransactionDetail>)businessObjectService.findMatchingOrderBy(TransactionDetail.class, fieldValues3a, "sourceAwardNumber", true));
                                transactionDetails.addAll(transactionDetailsA);
                                transactionDetails.addAll(transactionDetailsB);
                                int i = 0;
                                for(TransactionDetail transactionDetail : transactionDetails){
                                    timeAndMoneyHistory.put(awardAmountInfo.getTransactionId(), awardAmountInfo);
                                    timeAndMoneyHistory.put(key, transactionDetail);
                                    key++;
                                    i++;
                                }
                                columnSpan.add(i);
                            }
                        }
                    }
                }
                 //capture date transactions       
                for(AwardAmountInfo awardAmountInfo : award.getAwardAmountInfos()){  
                    if(StringUtils.equalsIgnoreCase(doc.getDocumentNumber(),awardAmountInfo.getTimeAndMoneyDocumentNumber())){ 
                        if(awardAmountInfo.getTransactionId() == null) {
                            fieldValues4.put("sourceAwardNumber", awardAmountInfo.getAwardNumber());
                            fieldValues4.put("sequenceNumber", awardAmountInfo.getSequenceNumber());
                            fieldValues4.put("transactionId", "-1");
                            fieldValues4.put("timeAndMoneyDocumentNumber", awardAmountInfo.getTimeAndMoneyDocumentNumber());
                            //Can return multiple transaction details because we are defaulting the transaction ID to -1
                            //in Date change transactions.
                            List<TransactionDetail> dateTransactionDetails = 
                                ((List<TransactionDetail>)businessObjectService.findMatchingOrderBy(TransactionDetail.class, fieldValues4, "sourceAwardNumber", true));
                            int i = 0;
                            for(TransactionDetail transactionDetail : dateTransactionDetails){
                                timeAndMoneyHistory.put(j, awardAmountInfo);//this is just for display only.
                                timeAndMoneyHistory.put(key, transactionDetail);
                                key++;
                                i++;
                                j--;//Map enforces unique key when adding to timeAndMoneyHistory.  Must be negative so it does not conflict with transaction ID of money transactions.
                            }
                            columnSpan.add(i);
                            break;
                        }
                    }
                }
                        
//                        fieldValues4.put("awardNumber", awardAmountInfo.getAwardNumber());
//                        fieldValues4.put("sequenceNumber", awardAmountInfo.getSequenceNumber());
//                        fieldValues4.put("transactionId", "-1");
//                        fieldValues4.put("timeAndMoneyDocumentNumber", awardAmountInfo.getTimeAndMoneyDocumentNumber());
//                        List<TransactionDetail> dateTransactionDetails = ((List<TransactionDetail>)businessObjectService.findMatchingOrderBy(TransactionDetail.class, fieldValues4, "sourceAwardNumber", true));
//                        timeAndMoneyHistory.put(awardAmountInfo.getTransactionId(), awardAmountInfo);
//                        for(TransactionDetail transactionDetail : dateTransactionDetails){
//                            timeAndMoneyHistory.put(key, transactionDetail);
//                            key++;
//                            i++;
//                        }
                    }
                }
                
            }  
    
    
    /**
     * This method searches generates the next Award Node Number in Sequence.
     * @param awardNumber
     * @return
     */
    public String getRootAwardNumberForDocumentSearch(String awardNumber) {
        String[] splitAwardNumber = awardNumber.split(DASH);
        StringBuilder returnString = new StringBuilder(12);
        returnString.append(splitAwardNumber[0]);
        returnString.append(DASH);
        returnString.append("00001");  
        return returnString.toString();
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
        sb.append("target=\"_blank\"");//open url in new window for inspection.
        sb.append(">");
        sb.append(documentNumber);
        sb.append("</a>");
        return sb.toString();
    }
    
    private String buildAwardDescriptionLine(Award award, TimeAndMoneyDocument timeAndMoneyDocument) {
        AwardAmountTransaction aat = timeAndMoneyDocument.getAwardAmountTransactions().get(0);
        String noticeDate;
        String transactionTypeDescription;
        if(!(aat.getNoticeDate() == null)) {
            noticeDate = aat.getNoticeDate().toString();
        }else {
            noticeDate = "empty";
        }
        if(!(award.getAwardTransactionType() == null)) {
            transactionTypeDescription = award.getAwardTransactionType().getDescription();
        }else {
            transactionTypeDescription = "empty";
        }
        return "Award Version " + award.getSequenceNumber().toString() + ": " + transactionTypeDescription + 
                    ": notice date : " + noticeDate + ", updated : " + getUpdateTimeAndUser(award); 
    }
    
    private String getUpdateTimeAndUser(Award award) {
        String createDateStr = null;
        String updateUser = null;
        if (award.getUpdateTimestamp() != null) {
            createDateStr = KNSServiceLocator.getDateTimeService().toString(award.getUpdateTimestamp(), "MM/dd/yy");
            updateUser = award.getUpdateUser().length() > NUMBER_30 ? award.getUpdateUser().substring(0, NUMBER_30) : award.getUpdateUser(); 
        }
        return createDateStr + ", " + updateUser;
    }
    
    /**
     * Gets the documentService attribute. 
     * @return Returns the documentService.
     */
    public DocumentService getDocumentService() {
        return documentService;
    }

    /**
     * Sets the documentService attribute value.
     * @param documentService The documentService to set.
     */
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
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
