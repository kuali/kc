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
package org.kuali.kra.external.budget.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.kuali.kra.budget.core.BudgetCategory;
import org.kuali.kra.external.HashMapElement;
import org.kuali.kra.external.budget.service.BudgetCategoryDTO;
import org.kuali.kra.external.budget.service.BudgetCategoryService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;

/**
 * This class implements the budget categories service.
 */
public class BudgetCategoryServiceImpl implements BudgetCategoryService {

    private BusinessObjectService businessObjectService;

    /**
     * This method looks up the BudgetCategories bo.
     * @see org.kuali.kra.external.budget.service.BudgetCategoryService#lookupBudgetCategories(java.util.List)
     */
    public List<BudgetCategoryDTO> lookupBudgetCategories(List<HashMapElement> criteria) {
        // TODO Auto-generated method stub
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
        
        for (BudgetCategory budget : budgetCategories) {
            budgetCategoryDTO.add(budgetCategoryBoToDTO(budget));
            System.out.println("budget is " + budget.getBudgetCategoryCode() + budget.getAuthorPersonName() 
                    + budget.getBudgetCategoryTypeCode() + budget.getDescription() + budget.getBudgetCategoryType()
                    + budget.getAuthorPersonName());
           
        }
       
        return budgetCategoryDTO;
    }

    /**
     * Converting BO to DTO.
     * @param budget
     * @return
     */
    protected BudgetCategoryDTO budgetCategoryBoToDTO(BudgetCategory budget) {
        BudgetCategoryDTO budgetCategoryDTO = new BudgetCategoryDTO();
        budgetCategoryDTO.setAuthorPersonName(budget.getAuthorPersonName());
        budgetCategoryDTO.setBudgetCategoryCode(budget.getBudgetCategoryCode());
        // enough to send category type code
        budgetCategoryDTO.setBudgetCategoryTypeCode(budget.getBudgetCategoryType().getBudgetCategoryTypeCode());
        budgetCategoryDTO.setBudgetCategoryTypeDescription(budget.getBudgetCategoryType().getDescription());
        budgetCategoryDTO.setDescription(budget.getDescription());
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
