/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
import java.util.Collection;
import java.util.List;

import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.bo.YnqExplanationType;
import org.kuali.kra.service.YnqService;

public class YnqServiceImpl implements YnqService {

    private BusinessObjectService businessObjectService;
    
    public List<YnqExplanationType> getYnqExplanationTypes() {
        Collection<YnqExplanationType> allTypes = new ArrayList();
        allTypes = businessObjectService.findAll(YnqExplanationType.class);
        List<YnqExplanationType> ynqExplanationTypes = new ArrayList();
        for(YnqExplanationType type: allTypes) {
            ynqExplanationTypes.add(type);
        } 
        return ynqExplanationTypes;
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
