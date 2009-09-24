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
package org.kuali.kra.service.impl;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyService;
import org.kuali.kra.service.AwardHierarchyUIService;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.service.ActivePendingTransactionsService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.springframework.util.StringUtils;

public class AwardHierarchyUIServiceImpl implements AwardHierarchyUIService {
    
    private static final String COLUMN_CODE = "%3A";
    
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
    public String getRootAwardNode(String awardNumber) throws ParseException{
        AwardHierarchyNode aNode = getAwardHierarchyNodes(awardNumber).get(awardNumber);        
        return "<h3>" + buildCompleteRecord(awardNumber, aNode) + "</h3>"; 
    }

    /*
     * This method builds a string record for a single hierarchy node. 
     * This string will be parsed by the java script in the UI for display.  
     *  
     */
    private String buildCompleteRecord(String awardNumber, AwardHierarchyNode aNode) throws ParseException {
        StringBuilder sb = new StringBuilder();
        
        if(aNode!=null){
            sb.append(awardNumber);
            appendString(aNode.getAccountNumber(), sb, COLUMN_CODE);
            appendString(aNode.getPrincipalInvestigatorName(), sb, ":");
            appendString(aNode.getLeadUnitName(), sb, ":");
            appendDate(aNode.getCurrentFundEffectiveDate(), sb);
            appendDate(aNode.getObligationExpirationDate(), sb);
            appendDate(aNode.getFinalExpirationDate(), sb);
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE).append(aNode.getAmountObligatedToDate());
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE).append(aNode.getAnticipatedTotalAmount());            
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE).append(aNode.getObliDistributableAmount());
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE).append(aNode.getAntDistributableAmount());
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE).append(aNode.getAmountObligatedToDate().subtract(aNode.getObliDistributableAmount()));
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE).append(aNode.getAnticipatedTotalAmount().subtract(aNode.getAntDistributableAmount()));
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE).append(aNode.getAwardStatusCode());            
            appendDate(aNode.getProjectStartDate(), sb);
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE).append(aNode.getTitle());
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE).append(aNode.getAwardId());
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE);    
        }
        return sb.toString();
    }

    /*
     * This method appends a date field to string buffer object for view.
     */
    private void appendString(String str, StringBuilder sb, String delimiter) {
        if(str!=null){
            sb.append(KNSConstants.BLANK_SPACE).append(delimiter).append(KNSConstants.BLANK_SPACE).append(str);
        }else{
            sb.append(KNSConstants.BLANK_SPACE).append(delimiter).append(KNSConstants.BLANK_SPACE).append("");
        }
    }

    /*
     * This method formats and appends a date field to stringbuffer object for view.
     */
    private void appendDate(Date date, StringBuilder sb) {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE);
        if(date!=null){
            sb.append(df.format(date));
        }else{
            sb.append("&nbsp;");
        }
    }

    public String getSubAwardHierarchiesForTreeView(String awardNumber) throws ParseException {
        String awardHierarchy = "<h3>";        
        for (AwardHierarchy ah : getChildrenNodes(awardNumber)) {
            AwardHierarchyNode aNode = getAwardHierarchyNodes(awardNumber).get(ah.getAwardNumber());
            awardHierarchy = awardHierarchy + buildCompleteRecord(ah.getAwardNumber(), aNode) + "</h3><h3>";
        }
        awardHierarchy = awardHierarchy.substring(0, awardHierarchy.length() - 4);        
        return awardHierarchy;        
    }
    
    /*
     * call businessobjectservice to get a list of children award nodes 'awardNumber'
     */
    private List<AwardHierarchy> getChildrenNodes(String awardNumber) {
        List<AwardHierarchy> awardHierarchyList = new ArrayList<AwardHierarchy>();
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("parentAwardNumber", awardNumber);
        awardHierarchyList.addAll(businessObjectService.findMatchingOrderBy(AwardHierarchy.class, fieldValues, "awardNumber", true));
        return awardHierarchyList;
    }
    
    private Map<String, AwardHierarchyNode> getAwardHierarchyNodes(String awardNumber){
        if(awardHierarchyNodes==null || awardHierarchyNodes.size()==0 || StringUtils.endsWithIgnoreCase("00001", awardNumber.substring(8))){            
            if(GlobalVariables.getUserSession().retrieveObject(GlobalVariables.getUserSession().getKualiSessionId())!=null){
                awardHierarchyNodes = ((TimeAndMoneyDocument)GlobalVariables.getUserSession().retrieveObject(
                        GlobalVariables.getUserSession().getKualiSessionId())).getAwardHierarchyNodes();            
            }else{                
                Map<String, AwardHierarchy> awardHierarchyItems = awardHierarchyService.getAwardHierarchy(awardNumber, new ArrayList<String>());
                awardHierarchyService.populateAwardHierarchyNodes(awardHierarchyItems, awardHierarchyNodes);
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
