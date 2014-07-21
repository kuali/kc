/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.budget.external.budget.service.impl;

import org.kuali.coeus.common.budget.framework.core.category.BudgetCategory;
import org.kuali.kra.external.HashMapElement;
import org.kuali.kra.budget.external.budget.service.BudgetCategoryDTO;
import org.kuali.kra.budget.external.budget.service.BudgetCategoryService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class implements the budget categories service.
 */
@Component("budgetCategoryService")
public class BudgetCategoryServiceImpl implements BudgetCategoryService {

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    /**
     * This method looks up the BudgetCategories bo.
     * @see org.kuali.kra.budget.external.budget.service.BudgetCategoryService#lookupBudgetCategories(java.util.List)
     */
    public List<BudgetCategoryDTO> lookupBudgetCategories(List<HashMapElement> criteria) {

        HashMap<String, String> searchCriteria =  new HashMap<String, String>();
        List<BudgetCategoryDTO> budgetCategoryDTO = new ArrayList<BudgetCategoryDTO>();
        List<BudgetCategory> budgetCategories = new ArrayList<BudgetCategory>();
       
        
        // if the criteria passed is null, then return all budget categories.
        if (ObjectUtils.isNull(criteria)) {
            budgetCategories =  new ArrayList<BudgetCategory>(businessObjectService.findAll(BudgetCategory.class));
        } else {
                // Reconstruct Hashmap from object list
            for (HashMapElement element : criteria) {
                searchCriteria.put(element.getKey(), element.getValue());  
            }
            budgetCategories =  new ArrayList<BudgetCategory>(businessObjectService.findMatching(BudgetCategory.class, searchCriteria));      
        }
       
        return budgetCategoryDTO;
    }

    /**
     * Sets the businessObjectService attribute value. Injected by Spring.
     * 
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}
