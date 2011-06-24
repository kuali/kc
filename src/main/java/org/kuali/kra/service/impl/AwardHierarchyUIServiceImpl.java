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
package org.kuali.kra.service.impl;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyService;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.service.AwardHierarchyUIService;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.service.ActivePendingTransactionsService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.KualiDecimal;
import org.kuali.rice.kns.web.format.Formatter;
import org.springframework.util.StringUtils;

public class AwardHierarchyUIServiceImpl implements AwardHierarchyUIService {
    
    private static final String FIELD_NAME_PARENT_AWARD_NUMBER = "parentAwardNumber";
    private static final String FIELD_NAME_AWARD_NUMBER = "awardNumber";
    private static final String DATE_FORMAT_MM_DD_YYYY = "MM/dd/yyyy";
    private static final String TAG_H3_END = "</h3>";
    private static final String TAG_H3_START = "<h3>";
    private static final String LAST_5_CHARS_OF_ROOT = "00001";
    private static final String COLUMN_CODE = "%3A";
    private static final String DOC_FINAL_STATUS_CODE = "F";
    private static final String DOC_NON_FINAL_STATUS_CODE = "N"; 
    
    private BusinessObjectService businessObjectService;    
    private ActivePendingTransactionsService activePendingTransactionsService;
    private AwardHierarchyService awardHierarchyService;
    private Map<String, AwardHierarchyNode> awardHierarchyNodes;
    
    AwardHierarchyUIServiceImpl(){
        awardHierarchyNodes = new HashMap<String, AwardHierarchyNode>();    
    }
    
     /**
     * 
     * @see org.kuali.kra.service.AwardHierarchyUIService#getRootAwardNode(java.lang.String)
     */
    public String getRootAwardNode(String awardNumber, String currentAwardNumber, String currentSequenceNumber) throws ParseException{
        AwardHierarchyNode aNode = getAwardHierarchyNodes(awardNumber, currentAwardNumber, currentSequenceNumber).get(awardNumber);        
        return TAG_H3_START + buildCompleteRecord(awardNumber, aNode) + TAG_H3_END; 
    }

    public AwardHierarchyNode getRootAwardNode(Award award) {
        String awardNumber = award.getAwardNumber();
        AwardHierarchyNode awardNode = getAwardHierarchyNodes(awardNumber, awardNumber, award.getSequenceNumber().toString()).get(awardNumber);
        return awardNode;
    }
    
    /*
     * This method builds a string record for a single hierarchy node. 
     * This string will be parsed by the java script in the UI for display.  
     *  
     */
    protected String buildCompleteRecord(String awardNumber, AwardHierarchyNode aNode) throws ParseException {
        StringBuilder sb = new StringBuilder();
        
        if(aNode!=null){
            sb.append(awardNumber);
            appendString(aNode.getAccountNumber(), sb, COLUMN_CODE);
            appendString(aNode.getPrincipalInvestigatorName(), sb, Constants.COLON);
            appendString(aNode.getLeadUnitName(), sb, Constants.COLON);
            appendDate(aNode.getCurrentFundEffectiveDate(), sb);
            appendDate(aNode.getObligationExpirationDate(), sb);
            appendDate(aNode.getFinalExpirationDate(), sb);
            Formatter formatter = Formatter.getFormatter(KualiDecimal.class);
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE).append(formatter.format(aNode.getAmountObligatedToDate()));//5
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE).append(formatter.format(aNode.getAnticipatedTotalAmount()));  //6         
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE).append(formatter.format(aNode.getObliDistributableAmount()));//Distributable amount needs to be updated
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE).append(formatter.format(aNode.getAntDistributableAmount()));
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE).append(formatter.format(aNode.getAmountObligatedToDate().subtract(aNode.getObliDistributableAmount())));//9
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE).append(formatter.format(aNode.getAnticipatedTotalAmount().subtract(aNode.getAntDistributableAmount())));//10

            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE).append(aNode.getAwardStatusCode()); //11
            
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE).append(formatter.format(aNode.getObligatedTotalDirect()));//12
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE).append(formatter.format(aNode.getObligatedTotalIndirect()));//13
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE).append(formatter.format(aNode.getAnticipatedTotalDirect()));//14
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE).append(formatter.format(aNode.getAnticipatedTotalIndirect()));//15
            appendDate(aNode.getProjectStartDate(), sb);//16
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE).append(aNode.getTitle());//17
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE).append(aNode.getAwardId());//18
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE).append(aNode.getAwardDocumentNumber());//19
            if(aNode.isAwardDocumentFinalStatus()) {
                sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE).append(DOC_FINAL_STATUS_CODE);
            } else {
                sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE).append(DOC_NON_FINAL_STATUS_CODE);
            }
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE);
        }
        return sb.toString();
    }
    
    /**
     * 
     * @see org.kuali.kra.service.AwardHierarchyUIService#getAwardRecord(org.kuali.kra.award.home.Award)
     */
    public String getAwardRecord(Award award) throws ParseException{
        
        String awardNumber = award.getAwardNumber();
        return buildCompleteRecord(awardNumber, getAwardHierarchyNodes(award.getAwardNumber(), award.getAwardNumber(), award.getSequenceNumber().toString()).get(awardNumber));
    }

   
    
    /*
     * This method appends a date field to string buffer object for view.
     */
    protected void appendString(String str, StringBuilder sb, String delimiter) {
        if(str!=null){
            sb.append(KNSConstants.BLANK_SPACE).append(delimiter).append(KNSConstants.BLANK_SPACE).append(str);
        }else{
            sb.append(KNSConstants.BLANK_SPACE).append(delimiter).append(KNSConstants.BLANK_SPACE).append(KNSConstants.EMPTY_STRING);
        }
    }

    /*
     * This method formats and appends a date field to stringbuffer object for view.
     */
    protected void appendDate(Date date, StringBuilder sb) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_MM_DD_YYYY);
        sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE);
        if(date!=null){
            sb.append(df.format(date));
        }else{
            sb.append("&nbsp;");
        }
    }
    
     public String getSubAwardHierarchiesForTreeView(String awardNumber, String currentAwardNumber, String currentSequenceNumber) throws ParseException {
        String awardHierarchy = TAG_H3_START;        
        for (AwardHierarchy ah : getChildrenNodes(awardNumber)) {
            AwardHierarchyNode aNode = getAwardHierarchyNodes(awardNumber, currentAwardNumber, currentSequenceNumber).get(ah.getAwardNumber());
            awardHierarchy = awardHierarchy + buildCompleteRecord(ah.getAwardNumber(), aNode) + TAG_H3_END + TAG_H3_START;
        }
        awardHierarchy = awardHierarchy.substring(0, awardHierarchy.length() - 4);        
        return awardHierarchy;        
    }
    
    /*
     * call businessobjectservice to get a list of children award nodes 'awardNumber'
     */
    protected List<AwardHierarchy> getChildrenNodes(String awardNumber) {
        List<AwardHierarchy> awardHierarchyList = new ArrayList<AwardHierarchy>();
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(FIELD_NAME_PARENT_AWARD_NUMBER, awardNumber);
        fieldValues.put("active", Boolean.TRUE);
        awardHierarchyList.addAll(businessObjectService.findMatchingOrderBy(AwardHierarchy.class, fieldValues, FIELD_NAME_AWARD_NUMBER, true));
        return awardHierarchyList;
    }
    
    protected boolean canUseExistingTMSessionObject(String awardNumber) {
        String sessionObjectKey = GlobalVariables.getUserSession().getKualiSessionId() + Constants.TIME_AND_MONEY_DOCUMENT_STRING_FOR_SESSION;
        String prefix = null;
        
        TimeAndMoneyDocument tmDocFromSession = (TimeAndMoneyDocument) GlobalVariables.getUserSession().retrieveObject(sessionObjectKey);
        if(tmDocFromSession == null) {
            return false;  
        }
        Map<String, AwardHierarchyNode> tmpNodes = tmDocFromSession.getAwardHierarchyNodes();
        if(tmpNodes != null && CollectionUtils.isNotEmpty(tmpNodes.keySet())) {
            for(String tempAwardNumber : tmpNodes.keySet()){
                prefix = tempAwardNumber.substring(0, tempAwardNumber.indexOf("-"));
                if(!StringUtils.startsWithIgnoreCase(awardNumber, prefix)) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    protected Map<String, AwardHierarchyNode> getAwardHierarchyNodes(String awardNumber, String currentAwardNumber, String currentSequenceNumber){
        if(awardHierarchyNodes==null || awardHierarchyNodes.size()==0 || StringUtils.endsWithIgnoreCase(LAST_5_CHARS_OF_ROOT, awardNumber.substring(8))){            
            if(canUseExistingTMSessionObject(awardNumber)){ 
                awardHierarchyNodes = ((TimeAndMoneyDocument)GlobalVariables.getUserSession().retrieveObject(
                        GlobalVariables.getUserSession().getKualiSessionId() + Constants.TIME_AND_MONEY_DOCUMENT_STRING_FOR_SESSION)).getAwardHierarchyNodes();            
            }else{                
                Map<String, AwardHierarchy> awardHierarchyItems = awardHierarchyService.getAwardHierarchy(awardNumber, new ArrayList<String>());
                awardHierarchyService.populateAwardHierarchyNodes(awardHierarchyItems, awardHierarchyNodes, currentAwardNumber, currentSequenceNumber);
            }
        }
        return awardHierarchyNodes;
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
     * Sets the awardHierarchyNodes attribute value.
     * @param awardHierarchyNodes The awardHierarchyNodes to set.
     */
    public void setAwardHierarchyNodes(Map<String, AwardHierarchyNode> awardHierarchyNodes) {
        this.awardHierarchyNodes = awardHierarchyNodes;
    }

    /**
     * Gets the activePendingTransactionsService attribute. 
     * @return Returns the activePendingTransactionsService.
     */
    public ActivePendingTransactionsService getActivePendingTransactionsService() {
        return activePendingTransactionsService;
    }

    /**
     * Sets the activePendingTransactionsService attribute value.
     * @param activePendingTransactionsService The activePendingTransactionsService to set.
     */
    public void setActivePendingTransactionsService(ActivePendingTransactionsService activePendingTransactionsService) {
        this.activePendingTransactionsService = activePendingTransactionsService;
    }

    /**
     * Gets the awardHierarchyService attribute. 
     * @return Returns the awardHierarchyService.
     */
    public AwardHierarchyService getAwardHierarchyService() {
        return awardHierarchyService;
    }

    /**
     * Sets the awardHierarchyService attribute value.
     * @param awardHierarchyService The awardHierarchyService to set.
     */
    public void setAwardHierarchyService(AwardHierarchyService awardHierarchyService) {
        this.awardHierarchyService = awardHierarchyService;
    }

    
    


}
