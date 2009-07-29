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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.dao.ResearchAreaDao;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.ResearchAreasService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DateTimeService;
import org.kuali.rice.kns.util.KNSConstants;

public class ResearchAreasServiceImpl implements ResearchAreasService {
    private BusinessObjectService businessObjectService;
    private static final String COLUMN = ":";
    private static final String SEPARATOR = ";1;";
    private ResearchAreaDao researchAreaDao;
    
    public String getInitialResearchAreasList() {
        List<ResearchArea> researchAreasList = getSubResearchAreas("000000");
        if (CollectionUtils.isEmpty(researchAreasList)) {
            return Constants.EMPTY_STRING;
        }
        ResearchArea topResearcgArea = researchAreasList.get(0);
        String initialResearchAreas = "<h3>"+topResearcgArea.getResearchAreaCode() +KNSConstants.BLANK_SPACE+COLUMN+KNSConstants.BLANK_SPACE+topResearcgArea.getDescription()+"</h3><h3>";
        for (ResearchArea researcgArea : getSubResearchAreas(topResearcgArea.getResearchAreaCode())) {
            // TODO : limit this for testing
            if (researcgArea.getResearchAreaCode().startsWith("21.")) {
            initialResearchAreas = initialResearchAreas + researcgArea.getResearchAreaCode() +KNSConstants.BLANK_SPACE+COLUMN+KNSConstants.BLANK_SPACE+researcgArea.getDescription()+"</h3><h3>";
            }
        }
        initialResearchAreas = initialResearchAreas + KraServiceLocator.getService(DateTimeService.class).getCurrentTimestamp().toString()+"</h3>";
       // initialResearchAreas = initialResearchAreas.substring(0, initialResearchAreas.length() - 4);

        return initialResearchAreas;
        
    }

    public String getSubResearchAreasForTreeView(String researchAreaCode) {
        String researchAreas = "<h3>";
        for (ResearchArea researcgArea : getSubResearchAreas(researchAreaCode)) {
            researchAreas = researchAreas + researcgArea.getResearchAreaCode() +KNSConstants.BLANK_SPACE+COLUMN+KNSConstants.BLANK_SPACE+researcgArea.getDescription()+"</h3><h3>";
        }
        researchAreas = researchAreas.substring(0, researchAreas.length() - 4);        
        return researchAreas;
        
    }
   
    public String getAscendantList(String researchAreaCode) {
        
        String retStr = Constants.EMPTY_STRING;
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("researchAreaCode", researchAreaCode);
        // what if not found, can it be casted? is it return a pbo or null
        ResearchArea researchArea = (ResearchArea)businessObjectService.findByPrimaryKey(ResearchArea.class, fieldValues);

        while (researchArea != null && !researchArea.getParentResearchAreaCode().equals("000000")) {
            fieldValues.put("researchAreaCode", researchArea.getParentResearchAreaCode());
            researchArea = (ResearchArea)businessObjectService.findByPrimaryKey(ResearchArea.class, fieldValues);            
            if (researchArea != null) {
                if (retStr.equals(Constants.EMPTY_STRING)) {
                    retStr = researchArea.getResearchAreaCode();
                } else {
                    retStr = researchArea.getResearchAreaCode()+ SEPARATOR + retStr;
                    
                }
                
            }
        }
        return retStr;
        
    }

    private List<ResearchArea> getSubResearchAreas(String researchAreaCode) {
        List<ResearchArea> researchAreasList = new ArrayList<ResearchArea>();
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("parentResearchAreaCode", researchAreaCode);
        researchAreasList.addAll(businessObjectService.findMatchingOrderBy(ResearchArea.class, fieldValues, "researchAreaCode", true));
        return researchAreasList;
    }

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
