/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.award.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyService;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.award.service.AwardHierarchyUIService;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.core.web.format.Formatter;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AwardHierarchyUIServiceImpl implements AwardHierarchyUIService {
    
    private static final String FIELD_NAME_PARENT_AWARD_NUMBER = "parentAwardNumber";
    private static final String FIELD_NAME_AWARD_NUMBER = "awardNumber";
    private static final String DATE_FORMAT_MM_DD_YYYY = "MM/dd/yyyy";
    private static final String LAST_5_CHARS_OF_ROOT = "00001";
    private static final String COLUMN_CODE = "%3A";
    private static final String DOC_FINAL_STATUS_CODE = "F";
    private static final String DOC_NON_FINAL_STATUS_CODE = "N"; 
    
    private BusinessObjectService businessObjectService;    
    private AwardHierarchyService awardHierarchyService;
    private Map<String, AwardHierarchyNode> awardHierarchyNodes;
    
    AwardHierarchyUIServiceImpl(){
        awardHierarchyNodes = new HashMap<String, AwardHierarchyNode>();    
    }
    
     @Override
    public String getRootAwardNode(String awardNumber, String currentAwardNumber, String currentSequenceNumber) throws ParseException{
        AwardHierarchyNode awardNode;
        if(canUseExistingTMSessionObject(awardNumber)){ 
            awardHierarchyNodes = ((TimeAndMoneyDocument)GlobalVariables.getUserSession().retrieveObject(
                    GlobalVariables.getUserSession().getKualiSessionId() + Constants.TIME_AND_MONEY_DOCUMENT_STRING_FOR_SESSION)).getAwardHierarchyNodes();
            awardNode = awardHierarchyNodes.get(awardNumber);
        }else{
            AwardHierarchy hierarchy = awardHierarchyService.loadAwardHierarchy(awardNumber);
            if (hierarchy == null) {
                // create temp new hierarchy
                hierarchy = AwardHierarchy.createRootNode(currentAwardNumber);
            }
            awardNode = awardHierarchyService.createAwardHierarchyNode(hierarchy, currentAwardNumber, currentSequenceNumber); 
        }
        return "[" + buildJavascriptRecord(awardNumber, awardNode) + "]"; 
    }

    public AwardHierarchyNode getRootAwardNode(Award award) {
        String awardNumber = award.getAwardNumber();
        AwardHierarchy hierarchy = awardHierarchyService.loadAwardHierarchy(awardNumber);
        AwardHierarchyNode awardNode = awardHierarchyService.createAwardHierarchyNode(hierarchy, null, null);
        return awardNode;
    }
    
    protected String buildJavascriptRecord(String awardNumber, AwardHierarchyNode aNode) {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_MM_DD_YYYY);
        Formatter formatter = Formatter.getFormatter(ScaleTwoDecimal.class);
        sb.append("{");
        appendJson(sb, "awardNumber", awardNumber);
        sb.append(",");
        appendJson(sb, "accountNumber", aNode.getAccountNumber());
        sb.append(",");
        appendJson(sb, "principalInvestigatorName", aNode.getPrincipalInvestigatorName());
        sb.append(",");
        appendJson(sb, "leadUnitName", aNode.getLeadUnitName());
        sb.append(","); 
        
        //remove any and all carriage returns, and add leading and trailing spaces to '<' & '>'
        String titleCleaned = aNode.getTitle().replaceAll("[\\r\\n]", " ");   
        titleCleaned = titleCleaned.replaceAll(">", " > ");
        titleCleaned = titleCleaned.replaceAll("<", " < ");
        appendJson(sb, "title", titleCleaned);
        
        sb.append(",");
        appendJson(sb, "awardId", aNode.getAwardId().toString());
        sb.append(",");
        appendJson(sb, "awardDocumentNumber", aNode.getAwardDocumentNumber());
        sb.append(",");
        appendJson(sb, "awardDocumentFinalStatus", aNode.isAwardDocumentFinalStatus());
        sb.append(",");            
        appendJson(sb, "projectStartDate", aNode.getProjectStartDate(), df);
        sb.append(",");
        appendJson(sb, "currentFundEffectiveDate", aNode.getCurrentFundEffectiveDate(), df);
        sb.append(",");
        appendJson(sb, "obligationExpirationDate", aNode.getObligationExpirationDate(), df);
        sb.append(",");
        appendJson(sb, "finalExpirationDate", aNode.getFinalExpirationDate(), df);
        sb.append(",");
        appendJson(sb, "amountObligatedToDate", (String)formatter.format(aNode.getAmountObligatedToDate()));
        sb.append(",");
        appendJson(sb, "anticipatedTotalAmount", (String)formatter.format(aNode.getAnticipatedTotalAmount()));
        sb.append(",");
        appendJson(sb, "obliDistributableAmount", (String)formatter.format(aNode.getObliDistributableAmount()));
        sb.append(",");
        appendJson(sb, "antDistributableAmount", (String)formatter.format(aNode.getAntDistributableAmount()));
        sb.append(",");
        appendJson(sb, "distributedObligatedAmount", (String)formatter.format(aNode.getAmountObligatedToDate().subtract(aNode.getObliDistributableAmount())));
        sb.append(",");
        appendJson(sb, "distributedAnticipatedAmount", (String)formatter.format(aNode.getAnticipatedTotalAmount().subtract(aNode.getAntDistributableAmount())));
        sb.append(",");
        appendJson(sb, "awardStatusCode", aNode.getAwardStatusCode().toString());
        sb.append(",");
        appendJson(sb, "obligatedTotalDirect", (String)formatter.format(aNode.getObligatedTotalDirect()));
        sb.append(",");
        appendJson(sb, "obligatedTotalIndirect", (String)formatter.format(aNode.getObligatedTotalIndirect()));
        sb.append(",");
        appendJson(sb, "anticipatedTotalDirect", (String)formatter.format(aNode.getAnticipatedTotalDirect()));
        sb.append(",");
        appendJson(sb, "anticipatedTotalIndirect", (String)formatter.format(aNode.getAnticipatedTotalIndirect()));
        sb.append(",");
        appendJson(sb, "hasChildren", aNode.getHasChildren());
        sb.append("}");
        return sb.toString();
    }
    
    /*
     * This method builds a string record for a single hierarchy node. 
     * This string will be parsed by the java script in the UI for display.  
     *  
     */
    protected String buildCompleteRecord(String awardNumber, AwardHierarchyNode aNode) throws ParseException {
        StringBuilder sb = new StringBuilder();
        
        if(aNode!=null){
            SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_MM_DD_YYYY);
            Formatter formatter = Formatter.getFormatter(ScaleTwoDecimal.class);
            
            sb.append(awardNumber);
            appendString(aNode.getAccountNumber(), sb, COLUMN_CODE);
            appendString(aNode.getPrincipalInvestigatorName(), sb, Constants.COLON);
            appendString(aNode.getLeadUnitName(), sb, Constants.COLON);
            appendDate(aNode.getCurrentFundEffectiveDate(), sb);
            appendDate(aNode.getObligationExpirationDate(), sb);
            appendDate(aNode.getFinalExpirationDate(), sb);

            sb.append(KRADConstants.BLANK_SPACE).append(COLUMN_CODE).append(KRADConstants.BLANK_SPACE).append(formatter.format(aNode.getAmountObligatedToDate()));//5
            sb.append(KRADConstants.BLANK_SPACE).append(COLUMN_CODE).append(KRADConstants.BLANK_SPACE).append(formatter.format(aNode.getAnticipatedTotalAmount()));  //6         
            sb.append(KRADConstants.BLANK_SPACE).append(COLUMN_CODE).append(KRADConstants.BLANK_SPACE).append(formatter.format(aNode.getObliDistributableAmount()));//Distributable amount needs to be updated
            sb.append(KRADConstants.BLANK_SPACE).append(COLUMN_CODE).append(KRADConstants.BLANK_SPACE).append(formatter.format(aNode.getAntDistributableAmount()));
            sb.append(KRADConstants.BLANK_SPACE).append(COLUMN_CODE).append(KRADConstants.BLANK_SPACE).append(formatter.format(aNode.getAmountObligatedToDate().subtract(aNode.getObliDistributableAmount())));//9
            sb.append(KRADConstants.BLANK_SPACE).append(COLUMN_CODE).append(KRADConstants.BLANK_SPACE).append(formatter.format(aNode.getAnticipatedTotalAmount().subtract(aNode.getAntDistributableAmount())));//10

            sb.append(KRADConstants.BLANK_SPACE).append(COLUMN_CODE).append(KRADConstants.BLANK_SPACE).append(aNode.getAwardStatusCode()); //11
            
            sb.append(KRADConstants.BLANK_SPACE).append(COLUMN_CODE).append(KRADConstants.BLANK_SPACE).append(formatter.format(aNode.getObligatedTotalDirect()));//12
            sb.append(KRADConstants.BLANK_SPACE).append(COLUMN_CODE).append(KRADConstants.BLANK_SPACE).append(formatter.format(aNode.getObligatedTotalIndirect()));//13
            sb.append(KRADConstants.BLANK_SPACE).append(COLUMN_CODE).append(KRADConstants.BLANK_SPACE).append(formatter.format(aNode.getAnticipatedTotalDirect()));//14
            sb.append(KRADConstants.BLANK_SPACE).append(COLUMN_CODE).append(KRADConstants.BLANK_SPACE).append(formatter.format(aNode.getAnticipatedTotalIndirect()));//15
            appendDate(aNode.getProjectStartDate(), sb);//16
            sb.append(KRADConstants.BLANK_SPACE).append(COLUMN_CODE).append(KRADConstants.BLANK_SPACE).append(aNode.getTitle());//17
            sb.append(KRADConstants.BLANK_SPACE).append(COLUMN_CODE).append(KRADConstants.BLANK_SPACE).append(aNode.getAwardId());//18
            sb.append(KRADConstants.BLANK_SPACE).append(COLUMN_CODE).append(KRADConstants.BLANK_SPACE).append(aNode.getAwardDocumentNumber());//19
            if(aNode.isAwardDocumentFinalStatus()) {
                sb.append(KRADConstants.BLANK_SPACE).append(COLUMN_CODE).append(KRADConstants.BLANK_SPACE).append(DOC_FINAL_STATUS_CODE);
            } else {
                sb.append(KRADConstants.BLANK_SPACE).append(COLUMN_CODE).append(KRADConstants.BLANK_SPACE).append(DOC_NON_FINAL_STATUS_CODE);
            }
            sb.append(KRADConstants.BLANK_SPACE).append(COLUMN_CODE).append(KRADConstants.BLANK_SPACE);
        }
        return sb.toString();
    }
    
    @Override
    public String getAwardRecord(Award award) throws ParseException{
        
        String awardNumber = award.getAwardNumber();
        return buildCompleteRecord(awardNumber, getAwardHierarchyNodes(award.getAwardNumber(), award.getAwardNumber(), award.getSequenceNumber().toString()).get(awardNumber));
    }

   
    
    /*
     * This method appends a date field to string buffer object for view.
     */
    protected void appendString(String str, StringBuilder sb, String delimiter) {
        if(str!=null){
            sb.append(KRADConstants.BLANK_SPACE).append(delimiter).append(KRADConstants.BLANK_SPACE).append(str);
        }else{
            sb.append(KRADConstants.BLANK_SPACE).append(delimiter).append(KRADConstants.BLANK_SPACE).append(KRADConstants.EMPTY_STRING);
        }
    }
    
    protected void appendJson(StringBuilder sb, String key, String value) {
        sb.append("\"");
        sb.append(key);
        sb.append("\":\"");
        if (value == null) {
            sb.append("");
        } else {
            sb.append(escapeJsonString(value));
        }
        sb.append("\"");
    }
    
    protected String escapeJsonString(String input) {
        if (input != null) {
            input = input.replaceAll("\\\\", "\\\\\\\\");
            input = input.replaceAll("\"", "\\\\\"");
        }
        return input;
    }    
    
    protected void appendJson(StringBuilder sb, String key, Boolean value) {
        sb.append("\"");
        sb.append(key);
        sb.append("\":");
        if (value == null) {
            sb.append(Boolean.FALSE.toString());
        } else {
            sb.append(value.toString());
        }
    }
    
    protected void appendJson(StringBuilder sb, String key, Date value, SimpleDateFormat formater) {
        sb.append("\"");
        sb.append(key);
        sb.append("\":\"");
        if (value != null) {
            sb.append(formater.format(value));
        }
        sb.append("\"");
    }
    

    /*
     * This method formats and appends a date field to stringbuffer object for view.
     */
    protected void appendDate(Date date, StringBuilder sb) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_MM_DD_YYYY);
        sb.append(KRADConstants.BLANK_SPACE).append(COLUMN_CODE).append(KRADConstants.BLANK_SPACE);
        if(date!=null){
            sb.append(df.format(date));
        }else{
            sb.append("&nbsp;");
        }
    }
    
    public String getSubAwardHierarchiesForTreeView(String awardNumber, String currentAwardNumber, String currentSequenceNumber) throws ParseException {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (AwardHierarchy ah : getChildrenNodes(awardNumber)) {
            AwardHierarchyNode aNode = getAwardHierarchyNode(ah.getAwardNumber(), currentAwardNumber, currentSequenceNumber, ah);
            sb.append(buildJavascriptRecord(ah.getAwardNumber(), aNode));
            sb.append(",");
        }
        //removing trailing ,
        if (sb.charAt(sb.length() - 1) == ',') {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();      
    }
     
    public String getSubAwardHierarchiesForTreeViewTandM(String awardNumber, String currentAwardNumber, String currentSequenceNumber) throws ParseException {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Map<String, AwardHierarchyNode> awarHierarchyNodes = getAwardHierarchyNodes(awardNumber, currentAwardNumber, currentSequenceNumber);
        for (AwardHierarchy ah : getChildrenNodes(awardNumber)) {
            AwardHierarchyNode aNode = awarHierarchyNodes.get(ah.getAwardNumber());
            sb.append(buildJavascriptRecord(ah.getAwardNumber(), aNode));
            sb.append(",");
        }
        //removing trailing ,
        if (sb.charAt(sb.length() - 1) == ',') {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
        
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
    
    protected AwardHierarchyNode getAwardHierarchyNode(String awardNumber, String currentAwardNumber, String currentSequenceNumber,AwardHierarchy hierarchy) {
        final AwardHierarchyNode returnVal;
        if(canUseExistingTMSessionObject(awardNumber)){
            awardHierarchyNodes = ((TimeAndMoneyDocument)GlobalVariables.getUserSession().retrieveObject(
                    GlobalVariables.getUserSession().getKualiSessionId() + Constants.TIME_AND_MONEY_DOCUMENT_STRING_FOR_SESSION)).getAwardHierarchyNodes();   
            returnVal = awardHierarchyNodes.get(awardNumber);
        }else{ 
            returnVal = awardHierarchyService.createAwardHierarchyNode(hierarchy, currentAwardNumber, currentSequenceNumber);
        }
            return returnVal;
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

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public void setAwardHierarchyNodes(Map<String, AwardHierarchyNode> awardHierarchyNodes) {
        this.awardHierarchyNodes = awardHierarchyNodes;
    }

    public AwardHierarchyService getAwardHierarchyService() {
        return awardHierarchyService;
    }

    public void setAwardHierarchyService(AwardHierarchyService awardHierarchyService) {
        this.awardHierarchyService = awardHierarchyService;
    }

    
    


}
