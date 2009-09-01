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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.service.AwardHierarchyUIService;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.timeandmoney.TimeAndMoneyForm;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;

public class AwardHierarchyUIServiceImpl implements AwardHierarchyUIService {
    
    private static final String SPACES = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
    private static final String SPACES2 = "&nbsp;&nbsp;";
    private static final String COLUMN_CODE = "%3A";
    
    private BusinessObjectService businessObjectService;
    private Map<String, AwardHierarchyNode> awardHierarchyNodes;
    
    public String getRootAwardNode(String awardNumber){
        AwardHierarchyNode aNode = getAwardHierarchyNodes().get(awardNumber);        
        return "<h3>" + buildCompleteRecord(awardNumber, aNode) + "</h3>"; 
    }

    /**
     * This method...
     * @param awardNumber
     * @param aNode
     * @return
     */
    private String buildCompleteRecord(String awardNumber, AwardHierarchyNode aNode) {
        StringBuilder sb = new StringBuilder();
        if(aNode!=null){
            sb.append(awardNumber); 
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE+KNSConstants.BLANK_SPACE).append(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT);
            sb.append(KNSConstants.BLANK_SPACE).append(aNode.getLeadUnitName());
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE).append(aNode.getCurrentFundEffectiveDate());
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE).append(aNode.getObligationExpirationDate());
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE).append(aNode.getFinalExpirationDate());
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE).append(aNode.getAmountObligatedToDate());
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE).append(aNode.getAnticipatedTotalAmount());
            sb.append(KNSConstants.BLANK_SPACE).append(COLUMN_CODE).append(KNSConstants.BLANK_SPACE);    
        }
        return sb.toString();
    }

    public String getSubAwardHierarchiesForTreeView(String awardNumber) {
        String awardHierarchy = "<h3>";        
        for (AwardHierarchy ah : getSubResearchAreas(awardNumber)) {
            AwardHierarchyNode aNode = getAwardHierarchyNodes().get(ah.getAwardNumber());
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
    
    private Map<String, AwardHierarchyNode> getAwardHierarchyNodes(){
        if(awardHierarchyNodes==null || awardHierarchyNodes.size()==0){
            awardHierarchyNodes = ((TimeAndMoneyForm)GlobalVariables.getKualiForm()).getTimeAndMoneyDocument().getAwardHierarchyNodes();    
        } 
        return awardHierarchyNodes;
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
