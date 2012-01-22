/*
 * Copyright 2005-2010 The Kuali Foundation
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.ResearchDocumentService;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.kra.timeandmoney.AwardAmountInfoHistory;
import org.kuali.kra.timeandmoney.AwardVersionHistory;
import org.kuali.kra.timeandmoney.TimeAndMoneyDocumentHistory;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.history.TransactionDetail;
import org.kuali.kra.timeandmoney.history.TransactionDetailType;
import org.kuali.kra.timeandmoney.history.TransactionType;
import org.kuali.kra.timeandmoney.service.TimeAndMoneyHistoryService;
import org.kuali.kra.timeandmoney.transactions.AwardAmountTransaction;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.ken.util.NotificationConstants;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;

public class TimeAndMoneyHistoryServiceImpl implements TimeAndMoneyHistoryService {
    
    private static final int NUMBER_30 = 30;
    BusinessObjectService businessObjectService; 
    DocumentService documentService;
    private String DASH = "-";
    private static final String DEFAULT_TAB = "Versions";
    private static final String ALTERNATE_OPEN_TAB = "Parameters";

    @SuppressWarnings("unchecked")
    public void getTimeAndMoneyHistory(String awardNumber, Map<Object, Object> timeAndMoneyHistory, List<Integer> columnSpan) throws WorkflowException {
        Map<String, Object> fieldValues1 = new HashMap<String, Object>();
        Map<String, Object> fieldValues3 = new HashMap<String, Object>();
        Map<String, Object> fieldValues3a = new HashMap<String, Object>();
        Map<String, Object> fieldValues5 = new HashMap<String, Object>();


        Map<String, Object> fieldValues4 = new HashMap<String, Object>(); 
        
        AwardAmountTransaction awardAmountTransaction = null;
        timeAndMoneyHistory.clear();
        Award award = getWorkingAwardVersion(awardNumber);       
        List<TimeAndMoneyDocument> docs = null;
        int key = 150;
        int j = -1;
        
            award.refreshReferenceObject("awardDocument");
            //to get all docs, we must pass the root award number for the subject award.
            fieldValues1.put("rootAwardNumber", getRootAwardNumberForDocumentSearch(award.getAwardNumber()));
            docs = (List<TimeAndMoneyDocument>)businessObjectService.findMatchingOrderBy(TimeAndMoneyDocument.class, fieldValues1, "documentNumber", true);
            timeAndMoneyHistory.put(buildForwardUrl(award.getAwardDocument().getDocumentNumber()), buildAwardDescriptionLine(award, null, docs.get(docs.size() -1)));
            for(TimeAndMoneyDocument tempDoc: docs){
                TimeAndMoneyDocument doc = (TimeAndMoneyDocument) documentService.getByDocumentHeaderId(tempDoc.getDocumentNumber());
                List<AwardAmountTransaction> awardAmountTransactions = doc.getAwardAmountTransactions();
                //we don't want canceled docs in history.
                if(doc.getDocumentHeader().hasWorkflowDocument()) {
                    if(!doc.getDocumentHeader().getWorkflowDocument().isCanceled()) {
                        //capture initial transaction
                        //we only want display this once.
                        for(AwardAmountInfo awardAmountInfo : award.getAwardAmountInfos()){
                            if(!(awardAmountInfo.getTimeAndMoneyDocumentNumber() == null)) {
                                if(awardAmountInfo.getTimeAndMoneyDocumentNumber().equals(doc.getDocumentNumber())) {
                                    if(StringUtils.equalsIgnoreCase(doc.getDocumentNumber(),awardAmountInfo.getTimeAndMoneyDocumentNumber())){ 
                                        if(awardAmountTransactions.size()>0){
                                            awardAmountTransaction = awardAmountTransactions.get(0);
                                            timeAndMoneyHistory.put(buildForwardUrl(doc.getDocumentNumber()), 
                                                    buildAwardDescriptionLine(award, awardAmountInfo, docs.get(docs.size() -1)) + " comments: " + awardAmountTransaction.getComments());
                                        } 
                                        fieldValues5.put("destinationAwardNumber", awardAmountInfo.getAwardNumber());
                                        fieldValues5.put("transactionId", 0);
                                        fieldValues5.put("timeAndMoneyDocumentNumber", awardAmountInfo.getTimeAndMoneyDocumentNumber());
                                        fieldValues5.put("transactionDetailType", TransactionDetailType.PRIMARY.toString());
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
                            }
                        }
                        
                        //capture money transactions
                        for(AwardAmountInfo awardAmountInfo : award.getAwardAmountInfos()){
                            if(!(awardAmountInfo.getTimeAndMoneyDocumentNumber() == null)) {
                                if(awardAmountInfo.getTimeAndMoneyDocumentNumber().equals(doc.getDocumentNumber())) {
                                    if(StringUtils.equalsIgnoreCase(doc.getDocumentNumber(),awardAmountInfo.getTimeAndMoneyDocumentNumber())){ 
                                        if(awardAmountTransactions.size()>0){
                                            awardAmountTransaction = awardAmountTransactions.get(0);
                                            timeAndMoneyHistory.put(buildForwardUrl(doc.getDocumentNumber()),
                                                    buildAwardDescriptionLine(award, awardAmountInfo, docs.get(docs.size() -1)) + " comments: " + awardAmountTransaction.getComments());
                                        } 
                                        List<TransactionDetail> transactionDetails = new ArrayList<TransactionDetail>();
                                        //get all Transaction Details for a node.  It can be the source or a destination of the transaction.
                                        fieldValues3.put("sourceAwardNumber", awardAmountInfo.getAwardNumber());
                                        fieldValues3.put("transactionId", awardAmountInfo.getTransactionId());
                                        fieldValues3.put("timeAndMoneyDocumentNumber", awardAmountInfo.getTimeAndMoneyDocumentNumber());
                                        fieldValues5.put("transactionDetailType", TransactionDetailType.PRIMARY.toString());

                                        
                                        fieldValues3a.put("destinationAwardNumber", awardAmountInfo.getAwardNumber());
                                        fieldValues3a.put("transactionId", awardAmountInfo.getTransactionId());
                                        fieldValues3a.put("timeAndMoneyDocumentNumber", awardAmountInfo.getTimeAndMoneyDocumentNumber());
                                        fieldValues5.put("transactionDetailType", TransactionDetailType.PRIMARY.toString());

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
                    }
                }
                 //capture date transactions       
                for(AwardAmountInfo awardAmountInfo : award.getAwardAmountInfos()){  
                    if(!(awardAmountInfo.getTimeAndMoneyDocumentNumber() == null)) {
                        if(awardAmountInfo.getTimeAndMoneyDocumentNumber().equals(doc.getDocumentNumber())) {
                            if(StringUtils.equalsIgnoreCase(doc.getDocumentNumber(),awardAmountInfo.getTimeAndMoneyDocumentNumber())){ 
                                if(awardAmountInfo.getTransactionId() == null) {
                                    if(awardAmountTransactions.size()>0){
                                        awardAmountTransaction = awardAmountTransactions.get(0);
                                        timeAndMoneyHistory.put(buildForwardUrl(doc.getDocumentNumber()),
                                                buildAwardDescriptionLine(award, awardAmountInfo, docs.get(docs.size() -1)) + " comments: " + awardAmountTransaction.getComments());
                                    } 
                                    fieldValues4.put("sourceAwardNumber", awardAmountInfo.getAwardNumber());
                                    fieldValues4.put("transactionId", "-1");
                                    fieldValues4.put("timeAndMoneyDocumentNumber", awardAmountInfo.getTimeAndMoneyDocumentNumber());
                                    fieldValues5.put("transactionDetailType", TransactionDetailType.PRIMARY.toString());

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
                    }
                }
               
            }
           }
    
    @SuppressWarnings("unchecked")
    public void buildTimeAndMoneyHistoryObjects(String awardNumber, List<AwardVersionHistory> awardVersionHistoryCollection) throws WorkflowException {
        List<Award> awardVersionList = (List<Award>)businessObjectService.findMatchingOrderBy(Award.class, getHashMapToFindActiveAward(awardNumber), "sequenceNumber", true);
        //we want the list in reverse chronological order.
        Collections.reverse(awardVersionList);       
        List<TimeAndMoneyDocument> docs = null;
        Map<String, Object> fieldValues1 = new HashMap<String, Object>();
        //get the root award number.
        fieldValues1.put("rootAwardNumber", getRootAwardNumberForDocumentSearch(awardVersionList.get(0).getAwardNumber()));
        docs = (List<TimeAndMoneyDocument>)businessObjectService.findMatchingOrderBy(TimeAndMoneyDocument.class, fieldValues1, "documentNumber", true);
        //we don't want canceled docs to show in history.
        removeCanceledDocs(docs);
        for(Award award : awardVersionList) {
            AwardVersionHistory awardVersionHistory = new AwardVersionHistory();
            awardVersionHistory.setDocumentUrl(buildForwardUrl(award.getAwardDocument().getDocumentNumber()));
            awardVersionHistory.setAwardDescriptionLine(buildNewAwardDescriptionLine(award));
            awardVersionHistory.setTimeAndMoneyDocumentHistoryList(getDocHistoryAndValidInfosAssociatedWithAwardVersion(docs, award.getAwardAmountInfos(), award));
            
            awardVersionHistoryCollection.add(awardVersionHistory);
        }  
    }
        
    protected List<TimeAndMoneyDocumentHistory> getDocHistoryAndValidInfosAssociatedWithAwardVersion(List<TimeAndMoneyDocument> docs,
            List<AwardAmountInfo> awardAmountInfos, Award award) throws WorkflowException {
        List<TimeAndMoneyDocumentHistory> timeAndMoneyDocumentHistoryList = new ArrayList<TimeAndMoneyDocumentHistory>();
        List<AwardAmountInfo> validInfos = getValidAwardAmountInfosAssociatedWithAwardVersion(awardAmountInfos, award);
        List<TimeAndMoneyDocument> awardVersionDocs = getValidDocumentsCreatedForAwardVersion(docs, validInfos);
        //we want the list in reverse chronological order.
        Collections.reverse(awardVersionDocs);
        for(TimeAndMoneyDocument doc : awardVersionDocs) {
            TimeAndMoneyDocumentHistory docHistory = new TimeAndMoneyDocumentHistory();
            docHistory.setTimeAndMoneyDocument(doc);
            docHistory.setDocumentUrl(buildForwardUrl(doc.getDocumentNumber()));
            docHistory.setTimeAndMoneyDocumentDescriptionLine(buildNewTimeAndMoneyDescriptionLine(doc));
            docHistory.setValidAwardAmountInfoHistoryList(retrieveAwardAmountInfosFromPrimaryTransactions(doc, validInfos));
            timeAndMoneyDocumentHistoryList.add(docHistory);
        }
        
        return timeAndMoneyDocumentHistoryList;
    }
    
    protected List<AwardAmountInfoHistory> retrieveAwardAmountInfosFromPrimaryTransactions(TimeAndMoneyDocument doc, List<AwardAmountInfo> validInfos) {
        List <AwardAmountInfoHistory> primaryInfos = new ArrayList<AwardAmountInfoHistory>();
        primaryInfos.addAll(captureMoneyInfos(doc, validInfos));
        primaryInfos.addAll(captureDateInfos(doc, validInfos));
        primaryInfos.addAll(captureInitialTransactionInfo(doc, validInfos));
        return primaryInfos;
    }
    
    @SuppressWarnings("unchecked")
    protected List<AwardAmountInfoHistory> captureMoneyInfos(TimeAndMoneyDocument doc, List<AwardAmountInfo> validInfos) {
        List <AwardAmountInfoHistory> moneyInfoHistoryList = new ArrayList<AwardAmountInfoHistory>();
        Map<String, Object> fieldValues1 = new HashMap<String, Object>();
        Map<String, Object> fieldValues1a = new HashMap<String, Object>();
        Map<String, Object> fieldValues2 = new HashMap<String, Object>();

        for(AwardAmountInfo awardAmountInfo : validInfos){
            if(!(awardAmountInfo.getTimeAndMoneyDocumentNumber() == null)) {
                    if(StringUtils.equalsIgnoreCase(doc.getDocumentNumber(),awardAmountInfo.getTimeAndMoneyDocumentNumber())){ 
                        //get all Transaction Details for a node.  It can be the source or a destination of the transaction.
                        fieldValues1.put("sourceAwardNumber", awardAmountInfo.getAwardNumber());
                        fieldValues1.put("transactionId", awardAmountInfo.getTransactionId());
                        fieldValues1.put("transactionDetailType", TransactionDetailType.PRIMARY.toString());
                        fieldValues1a.put("destinationAwardNumber", awardAmountInfo.getAwardNumber());
                        fieldValues1a.put("transactionId", awardAmountInfo.getTransactionId());
                        fieldValues1a.put("transactionDetailType", TransactionDetailType.PRIMARY.toString());
                        fieldValues2.put("transactionId", awardAmountInfo.getTransactionId());
                        fieldValues2.put("transactionDetailType", TransactionDetailType.INTERMEDIATE.toString());
                        List<TransactionDetail> transactionDetails = 
                            ((List<TransactionDetail>)businessObjectService.findMatchingOrderBy(TransactionDetail.class, fieldValues1, "transactionDetailId", true));
                        List<TransactionDetail> transactionDetailsA = 
                            ((List<TransactionDetail>)businessObjectService.findMatchingOrderBy(TransactionDetail.class, fieldValues1a, "transactionDetailId", true));
                        //we do a join on this list, but there can only be one possible since we are searching by Award Amount Info and there can only be
                        //one transaction associated.
                        transactionDetails.addAll(transactionDetailsA);
                        List<TransactionDetail> transactionDetailsB = 
                            ((List<TransactionDetail>)businessObjectService.findMatchingOrderBy(TransactionDetail.class, fieldValues2, "transactionDetailId", true));
                        if (transactionDetails.size() > 0) {
                            AwardAmountInfoHistory awardAmountInfoHistory = new AwardAmountInfoHistory();
                            awardAmountInfoHistory.setAwardAmountInfo(awardAmountInfo);
                            awardAmountInfoHistory.setTransactionType(TransactionType.MONEY.toString());
                            awardAmountInfoHistory.setPrimaryDetail(transactionDetails.get(0));
                            awardAmountInfoHistory.setIntermediateDetails(transactionDetailsB);
                            moneyInfoHistoryList.add(awardAmountInfoHistory);
                        }
                    }
                }
            }
            return moneyInfoHistoryList;
        }

        
    
    @SuppressWarnings("unchecked")
    protected List<AwardAmountInfoHistory> captureDateInfos(TimeAndMoneyDocument doc, List<AwardAmountInfo> validInfos) {
        List <AwardAmountInfoHistory> dateInfoHistoryList = new ArrayList<AwardAmountInfoHistory>();
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        for(AwardAmountInfo awardAmountInfo : validInfos){  
            if(!(awardAmountInfo.getTimeAndMoneyDocumentNumber() == null)) {
                    if(StringUtils.equalsIgnoreCase(doc.getDocumentNumber(),awardAmountInfo.getTimeAndMoneyDocumentNumber())){ 
                        if(awardAmountInfo.getTransactionId() == null) {
                            fieldValues.put("sourceAwardNumber", awardAmountInfo.getAwardNumber());
                            fieldValues.put("transactionId", "-1");
                            fieldValues.put("timeAndMoneyDocumentNumber", awardAmountInfo.getTimeAndMoneyDocumentNumber());
                            List<TransactionDetail> dateTransactionDetails = 
                                ((List<TransactionDetail>)businessObjectService.findMatchingOrderBy(TransactionDetail.class, fieldValues, "sourceAwardNumber", true));
                            if (dateTransactionDetails.size() > 0) {
                                AwardAmountInfoHistory awardAmountInfoHistory = new AwardAmountInfoHistory();
                                awardAmountInfoHistory.setAwardAmountInfo(awardAmountInfo);
                                awardAmountInfoHistory.setTransactionType(TransactionType.DATE.toString());
                                dateInfoHistoryList.add(awardAmountInfoHistory);
                            }
                        }
                    }
            }
        }
        return dateInfoHistoryList;
    }
    

    @SuppressWarnings("unchecked")
    protected List<AwardAmountInfoHistory> captureInitialTransactionInfo(TimeAndMoneyDocument doc, List<AwardAmountInfo> validInfos) {
        List <AwardAmountInfoHistory> initialInfoHistoryList = new ArrayList<AwardAmountInfoHistory>();
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        for(AwardAmountInfo awardAmountInfo : validInfos){
            if(!(awardAmountInfo.getTimeAndMoneyDocumentNumber() == null)) {
                    if(StringUtils.equalsIgnoreCase(doc.getDocumentNumber(),awardAmountInfo.getTimeAndMoneyDocumentNumber())){ 
                        fieldValues.put("destinationAwardNumber", awardAmountInfo.getAwardNumber());
                        fieldValues.put("transactionId", 0);
                        fieldValues.put("timeAndMoneyDocumentNumber", awardAmountInfo.getTimeAndMoneyDocumentNumber());
                        fieldValues.put("transactionDetailType", TransactionDetailType.PRIMARY.toString());
                        List<TransactionDetail> transactionDetailsA = 
                            ((List<TransactionDetail>)businessObjectService.findMatchingOrderBy(TransactionDetail.class, fieldValues, "sourceAwardNumber", true));
                        if(transactionDetailsA.size() > 0) {
                            AwardAmountInfoHistory awardAmountInfoHistory = new AwardAmountInfoHistory();
                            awardAmountInfoHistory.setAwardAmountInfo(awardAmountInfo);
                            awardAmountInfoHistory.setTransactionType(TransactionType.INITIAL.toString());
                            initialInfoHistoryList.add(awardAmountInfoHistory);
                            break;
                        }else {
                            break;
                        }
                    }
            }
        }
        return initialInfoHistoryList;  
    }
    
    protected String buildNewTimeAndMoneyDescriptionLine(TimeAndMoneyDocument doc) {
        AwardAmountTransaction aat = doc.getAwardAmountTransactions().get(0);
        String noticeDate;
        String transactionTypeDescription;
        
        if(!(aat.getNoticeDate() == null)) {
            noticeDate = aat.getNoticeDate().toString();
        }else {
            noticeDate = "empty";
        }
        if(!(aat.getAwardTransactionType() == null)) {
            transactionTypeDescription = aat.getAwardTransactionType().getDescription();
        }else {
            transactionTypeDescription = "empty";
        }
        return "Time And Money Document: " + transactionTypeDescription + 
                    ": notice date: " + noticeDate + ", updated : " + getUpdateTimeAndUser(doc) + " Comments: " + aat.getComments();
    }
    
    protected List<AwardAmountInfo> getValidAwardAmountInfosAssociatedWithAwardVersion(List<AwardAmountInfo> awardAmountInfos, Award award) {
        List<AwardAmountInfo> validInfos = new ArrayList<AwardAmountInfo>();
        for(AwardAmountInfo awardAmountInfo : awardAmountInfos) {
            if(!(awardAmountInfo.getOriginatingAwardVersion() == null)) {
                if(awardAmountInfo.getOriginatingAwardVersion().equals(award.getSequenceNumber())) {
                    validInfos.add(awardAmountInfo);
                }
            }
        }
        return validInfos;
    }
    
    protected List<TimeAndMoneyDocument> getValidDocumentsCreatedForAwardVersion(List<TimeAndMoneyDocument> docs, List<AwardAmountInfo> validInfos) {
        List<TimeAndMoneyDocument> validDocs = new ArrayList<TimeAndMoneyDocument>();
            for(TimeAndMoneyDocument doc : docs) {
                if(isInValidInfosCollection(doc, validInfos)) {
                    validDocs.add(doc);
                }
            }
        return validDocs;
    }
    
    protected Boolean isInValidInfosCollection(TimeAndMoneyDocument doc, List<AwardAmountInfo> validInfos) {
        Boolean valid = false;
        for(AwardAmountInfo awardAmountInfo : validInfos) {
            if(!(awardAmountInfo.getTimeAndMoneyDocumentNumber() == null)){
                if(awardAmountInfo.getTimeAndMoneyDocumentNumber().equals(doc.getDocumentNumber())) {
                    valid = true;
                    break;
                }
            }
        }
        return valid;
    }
    
    protected void removeCanceledDocs(List<TimeAndMoneyDocument> docs) {
        List<TimeAndMoneyDocument> tempCanceledDocs = new ArrayList<TimeAndMoneyDocument>();
        for(TimeAndMoneyDocument doc : docs) {
            if(doc.getDocumentHeader().hasWorkflowDocument()) {
                if(doc.getDocumentHeader().getWorkflowDocument().isCanceled()) {
                   tempCanceledDocs.add(doc); 
                }
            }
        }
        docs.removeAll(tempCanceledDocs);
    }
                     
    
    
    public Award getWorkingAwardVersion(String goToAwardNumber) {
        Award award = null;
        award = getPendingAwardVersion(goToAwardNumber);
        if (award == null) {
            award = getActiveAwardVersion(goToAwardNumber);
        }
        return award;
    }
    
    /*
     * This method retrieves the pending award version.
     * 
     * @param doc
     * @param goToAwardNumber
     */
    @SuppressWarnings("unchecked")
    public Award getPendingAwardVersion(String goToAwardNumber) {
        
        Award award = null;
        BusinessObjectService businessObjectService =  KraServiceLocator.getService(BusinessObjectService.class);
        List<Award> awards = (List<Award>)businessObjectService.findMatchingOrderBy(Award.class, getHashMapToFindActiveAward(goToAwardNumber), "sequenceNumber", true);
        if(!(awards.size() == 0)) {
            award = awards.get(awards.size() - 1);
        }
      
        return award;
    }
    
   
    protected Award getActiveAwardVersion(String goToAwardNumber) {
        VersionHistoryService vhs = KraServiceLocator.getService(VersionHistoryService.class);  
        VersionHistory vh = vhs.findActiveVersion(Award.class, goToAwardNumber);
        Award award = null;
        
        if(vh!=null){
            award = (Award) vh.getSequenceOwner();
        }else{
            BusinessObjectService businessObjectService =  KraServiceLocator.getService(BusinessObjectService.class);
            award = ((List<Award>)businessObjectService.findMatching(Award.class, getHashMapToFindActiveAward(goToAwardNumber))).get(0);              
        }
        return award;
    }
    protected Map<String, String> getHashMapToFindActiveAward(String goToAwardNumber) {
        Map<String, String> map = new HashMap<String,String>();
        map.put("awardNumber", goToAwardNumber);
        return map;
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
    
    protected String buildAwardDescriptionLine(Award award, AwardAmountInfo awardAmountInfo, TimeAndMoneyDocument timeAndMoneyDocument) {
        AwardAmountTransaction aat = timeAndMoneyDocument.getAwardAmountTransactions().get(0);
        String noticeDate;
        String transactionTypeDescription;
        String versionNumber;
        if(awardAmountInfo == null || awardAmountInfo.getOriginatingAwardVersion() == null) {
            versionNumber = award.getSequenceNumber().toString();
        }else {
            versionNumber = awardAmountInfo.getOriginatingAwardVersion().toString();
        }
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
        return "Award Version " + versionNumber + ": " + transactionTypeDescription + 
                    ": notice date : " + noticeDate + ", updated : " + getUpdateTimeAndUser(award); 
    }
    
    protected String buildNewAwardDescriptionLine(Award award) {
        String noticeDate;
        String transactionTypeDescription;
        String versionNumber;
        
        versionNumber = award.getSequenceNumber().toString();
   
        if(!(award.getNoticeDate() == null)) {
            noticeDate = award.getNoticeDate().toString();
        }else {
            noticeDate = "empty";
        }
        if(!(award.getAwardTransactionType() == null)) {
            transactionTypeDescription = award.getAwardTransactionType().getDescription();
        }else {
            transactionTypeDescription = "empty";
        }
        return "Award Version " + versionNumber + ": " + transactionTypeDescription + 
                    ": notice date : " + noticeDate + ", updated : " + getUpdateTimeAndUser(award) 
                    + " Comments:" + award.getAwardCurrentActionComments().getComments();
    }
    
    protected String getUpdateTimeAndUser(Award award) {
        String createDateStr = null;
        String updateUser = null;
        if (award.getUpdateTimestamp() != null) {
            createDateStr = CoreApiServiceLocator.getDateTimeService().toString(award.getUpdateTimestamp(), "MM/dd/yy");
            updateUser = award.getUpdateUser().length() > NUMBER_30 ? award.getUpdateUser().substring(0, NUMBER_30) : award.getUpdateUser(); 
        }
        return createDateStr + ", " + updateUser;
    }
    
    protected String getUpdateTimeAndUser(TimeAndMoneyDocument doc) {
        String createDateStr = null;
        String updateUser = null;
        if (doc.getUpdateTimestamp() != null) {
            createDateStr = CoreApiServiceLocator.getDateTimeService().toString(doc.getUpdateTimestamp(), "MM/dd/yy");
            updateUser = doc.getUpdateUser().length() > NUMBER_30 ? doc.getUpdateUser().substring(0, NUMBER_30) : doc.getUpdateUser(); 
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
    
    /**
     * Takes a routeHeaderId for a particular document and constructs the URL to forward to that document
     * 
     * @param routeHeaderId
     * @return String
     */
    protected String buildForwardUrl(String documentNumber) {
        ResearchDocumentService researchDocumentService = KraServiceLocator.getService(ResearchDocumentService.class);
        String forward = researchDocumentService.getDocHandlerUrl(documentNumber);
        forward = forward.replaceFirst(DEFAULT_TAB, ALTERNATE_OPEN_TAB);
        if (forward.indexOf("?") == -1) {
            forward += "?";
        }
        else {
            forward += "&";
        }
        forward += KewApiConstants.DOCUMENT_ID_PARAMETER + "=" + documentNumber;
        forward += "&" + KewApiConstants.COMMAND_PARAMETER + "=" + NotificationConstants.NOTIFICATION_DETAIL_VIEWS.DOC_SEARCH_VIEW;
        if (GlobalVariables.getUserSession().isBackdoorInUse()) {
            forward += "&" + KewApiConstants.BACKDOOR_ID_PARAMETER + "=" + GlobalVariables.getUserSession().getPrincipalName();
        }
        
        String returnVal = "<a href=\"" + forward + "\"target=\"_blank\">" + documentNumber + "</a>";
        return returnVal;
    }

}
