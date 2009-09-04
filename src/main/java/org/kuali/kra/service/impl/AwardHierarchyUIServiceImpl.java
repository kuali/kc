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
import java.util.Map.Entry;

import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyService;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.AwardHierarchyUIService;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.timeandmoney.service.ActivePendingTransactionsService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.springframework.util.StringUtils;

public class AwardHierarchyUIServiceImpl implements AwardHierarchyUIService {
    
    private static final String COLUMN_CODE = "%3A";
    
    private BusinessObjectService businessObjectService;
    private Map<String, AwardHierarchyNode> awardHierarchyNodes;
    
    AwardHierarchyUIServiceImpl(){
        awardHierarchyNodes = new HashMap<String, AwardHierarchyNode>();    
    }
    
    public String getRootAwardNode(String awardNumber) throws ParseException{
        AwardHierarchyNode aNode = getAwardHierarchyNodes(awardNumber).get(awardNumber);        
        return "<h3>" + buildCompleteRecord(awardNumber, aNode) + "</h3>"; 
    }

    /**
     * This method...
     * @param awardNumber
     * @param aNode
     * @return
     * @throws ParseException 
     */
    private String buildCompleteRecord(String awardNumber, AwardHierarchyNode aNode) throws ParseException {
        StringBuilder sb = new StringBuilder();
        
        if(aNode!=null){
            sb.append(awardNumber); 
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE+KNSConstants.BLANK_SPACE).append(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT);
            sb.append(KNSConstants.BLANK_SPACE).append(aNode.getLeadUnitName());
            appendDate(aNode.getCurrentFundEffectiveDate(), sb);
            appendDate(aNode.getObligationExpirationDate(), sb);
            appendDate(aNode.getFinalExpirationDate(), sb);
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE).append(aNode.getAmountObligatedToDate());
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE).append(aNode.getAnticipatedTotalAmount());
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE);    
        }
        return sb.toString();
    }

    /**
     * This method...
     * @param aNode
     * @param sb
     * @param df
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
        for (AwardHierarchy ah : getSubResearchAreas(awardNumber)) {
            AwardHierarchyNode aNode = getAwardHierarchyNodes(awardNumber).get(ah.getAwardNumber());
            awardHierarchy = awardHierarchy + buildCompleteRecord(ah.getAwardNumber(), aNode) + "</h3><h3>";
        }
        awardHierarchy = awardHierarchy.substring(0, awardHierarchy.length() - 4);        
        return awardHierarchy;        
    }

    public boolean doesAwardHierarchyExist(String researchAreaCode, String researchAreas) {
        // TODO Auto-generated method stub
        return false;
    }
    
    /*
     * call businessobjectservice to get a list of sub research areas of 'researchareacode'
     */
    private List<AwardHierarchy> getSubResearchAreas(String awardNumber) {
        List<AwardHierarchy> awardHierarchyList = new ArrayList<AwardHierarchy>();
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("parentAwardNumber", awardNumber);
        awardHierarchyList.addAll(businessObjectService.findMatchingOrderBy(AwardHierarchy.class, fieldValues, "awardNumber", true));
        return awardHierarchyList;
    }
    
    private Map<String, AwardHierarchyNode> getAwardHierarchyNodes(String awardNumber){
        if(awardHierarchyNodes==null || awardHierarchyNodes.size()==0 || StringUtils.endsWithIgnoreCase("00001", awardNumber.substring(8))){
            List<String> order = new ArrayList<String>();
            Map<String, AwardHierarchy> awardHierarchyItems = getAwardHierarchyService().getAwardHierarchy(awardNumber, order );
            AwardHierarchyNode awardHierarchyNode = new AwardHierarchyNode();
            ActivePendingTransactionsService aptService = KraServiceLocator.getService(ActivePendingTransactionsService.class);
            
            for(Entry<String, AwardHierarchy> awardHierarchy:awardHierarchyItems.entrySet()){
                awardHierarchyNode = new AwardHierarchyNode();
                awardHierarchyNode.setAwardNumber(awardHierarchy.getValue().getAwardNumber());
                awardHierarchyNode.setParentAwardNumber(awardHierarchy.getValue().getParentAwardNumber());
                awardHierarchyNode.setRootAwardNumber(awardHierarchy.getValue().getRootAwardNumber());
                
                Award award = aptService.getActiveAwardVersion(awardHierarchy.getValue().getAwardNumber());
                AwardAmountInfo awardAmountInfo = aptService.fetchAwardAmountInfoWithHighestTransactionId(award.getAwardAmountInfos());            
                
                awardHierarchyNode.setFinalExpirationDate(award.getProjectEndDate());
                awardHierarchyNode.setLeadUnitName(award.getUnitName());
                awardHierarchyNode.setPrincipalInvestigatorName(award.getPrincipalInvestigatorName());
                awardHierarchyNode.setObliDistributableAmount(awardAmountInfo.getObliDistributableAmount());
                awardHierarchyNode.setAmountObligatedToDate(awardAmountInfo.getAmountObligatedToDate());
                awardHierarchyNode.setAnticipatedTotalAmount(awardAmountInfo.getAnticipatedTotalAmount());
                awardHierarchyNode.setAntDistributableAmount(awardAmountInfo.getAntDistributableAmount());
                awardHierarchyNodes.put(awardHierarchyNode.getAwardNumber(), awardHierarchyNode);
            }   
        } 
        return awardHierarchyNodes;
    }
    
    public AwardHierarchyService getAwardHierarchyService(){        
        return (AwardHierarchyService) KraServiceLocator.getService(AwardHierarchyService.class);
    }
    
    private Map<String, AwardHierarchy> getAwardHierarchies(){
        return ((AwardForm)GlobalVariables.getKualiForm()).getAwardHierarchyNodes();
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


}
