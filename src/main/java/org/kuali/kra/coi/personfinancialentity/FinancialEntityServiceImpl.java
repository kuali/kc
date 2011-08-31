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
package org.kuali.kra.coi.personfinancialentity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.rice.kns.service.BusinessObjectService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * This class is to implement the detail of methods declared in FinancialENtityService
 */
@Transactional
public class FinancialEntityServiceImpl implements FinancialEntityService {

    private BusinessObjectService businessObjectService;

    /**
     * 
     * @see org.kuali.kra.coi.personfinancialentity.FinancialEntityService#getFinancialEntities(java.lang.String, boolean)
     */
    public List<PersonFinIntDisclosure> getFinancialEntities(String personId, boolean active) {
        Map fieldValues = new HashMap();
        fieldValues.put("personId", personId);
        if (active) {
            fieldValues.put("statusCode", "1");     
        } else {
            fieldValues.put("statusCode", "2");     
        }
        fieldValues.put("currentFlag", "Y");     

        return (List<PersonFinIntDisclosure>)businessObjectService.findMatching(PersonFinIntDisclosure.class, fieldValues);
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}
