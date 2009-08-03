/*
 * Copyright 2006-2009 The Kuali Foundation
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

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.dao.ResearchAreaDao;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.service.ResearchAreasService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.KNSConstants;

public class ResearchAreasServiceImpl implements ResearchAreasService {
    private static final String COLUMN_CODE = "%3A";
    private BusinessObjectService businessObjectService;
    private ResearchAreaDao researchAreaDao;
    
    /**
     * 
     * @see org.kuali.kra.service.ResearchAreasService#getSubResearchAreasForTreeView(java.lang.String)
     */
    public String getSubResearchAreasForTreeView(String researchAreaCode) {
        String researchAreas = "<h3>";
        for (ResearchArea researcgArea : getSubResearchAreas(researchAreaCode)) {
            researchAreas = researchAreas + researcgArea.getResearchAreaCode() +KNSConstants.BLANK_SPACE+COLUMN_CODE+KNSConstants.BLANK_SPACE+researcgArea.getDescription()+"</h3><h3>";
        }
        researchAreas = researchAreas.substring(0, researchAreas.length() - 4);        
        return researchAreas;
        
    }
   
    /*
     * call businessobjectservice to get a list of sub research areas of 'researchareacode'
     */
    private List<ResearchArea> getSubResearchAreas(String researchAreaCode) {
        List<ResearchArea> researchAreasList = new ArrayList<ResearchArea>();
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("parentResearchAreaCode", researchAreaCode);
        researchAreasList.addAll(businessObjectService.findMatchingOrderBy(ResearchArea.class, fieldValues, "researchAreaCode", true));
        return researchAreasList;
    }

    /**
     * 
     * @see org.kuali.kra.service.ResearchAreasService#isResearchAreaExist(java.lang.String, java.lang.String)
     */
    public boolean isResearchAreaExist(String researchAreaCode, String researchAreas) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("researchAreaCode", researchAreaCode);
        boolean isExist = businessObjectService.findByPrimaryKey(ResearchArea.class, fieldValues) != null;
        if (isExist && StringUtils.isNotBlank(researchAreas)) {
            for (String raCode : researchAreas.split(";")) {
                if (raCode.equals(researchAreaCode)) {
                    isExist = false;
                    break;
                } else {
                    if (isExistInDeletedChildren(researchAreaCode, raCode)) {
                        isExist = false;
                        break;                        
                    }
                }
            }
            
        }
        return isExist;
    }

    /*
     * This method is a recursive call to check whether the new 'researchAreaCode' matched
     * raCode's decendants' code
     */
    private boolean isExistInDeletedChildren(String researchAreaCode, String raCode) {
        boolean isExist = false;
        for (ResearchArea researchArea : getSubResearchAreas(raCode)) {
            if (researchAreaCode.equals(researchArea.getResearchAreaCode())) {
                isExist = true;
                break;
            } else {
                isExist = isExistInDeletedChildren(researchAreaCode, researchArea.getResearchAreaCode());
            }
        }
        return isExist;
    }
    
    /**
     * 
     * @see org.kuali.kra.service.ResearchAreasService#saveResearchAreas(java.lang.String)
     */
    public void saveResearchAreas(String sqlScripts) {

        //researchAreaDao.runScripts(sqlScripts.split("#;#"));
        researchAreaDao.runScripts(sqlScripts.split(";;;"));
        
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }


    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public void setResearchAreaDao(ResearchAreaDao researchAreaDao) {
        this.researchAreaDao = researchAreaDao;
    }

}
