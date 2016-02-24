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
package org.kuali.kra.external.budget.service.impl;

import org.kuali.coeus.common.budget.framework.core.category.BudgetCategory;
import org.kuali.kra.external.HashMapElement;
import org.kuali.kra.external.budget.service.BudgetCategoryDTO;
import org.kuali.kra.external.budget.service.BudgetCategoryService;
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
     * @see org.kuali.kra.external.budget.service.BudgetCategoryService#lookupBudgetCategories(java.util.List)
     */
    public List<BudgetCategoryDTO> lookupBudgetCategories(List<HashMapElement> criteria) {

        HashMap<String, String> searchCriteria =  new HashMap<String, String>();
        List<BudgetCategoryDTO> budgetCategoryDTOs = new ArrayList<BudgetCategoryDTO>();
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
        if(budgetCategories!= null && budgetCategories.size() > 0) {
        	for(BudgetCategory budgetCategory : budgetCategories) {
        		BudgetCategoryDTO budgetCategoryDTO = new BudgetCategoryDTO();
        		budgetCategoryDTO.setBudgetCategoryCode(budgetCategory.getCode());
        		budgetCategoryDTO.setAuthorPersonName(budgetCategory.getUpdateUser());
        		budgetCategoryDTO.setBudgetCategoryTypeCode(budgetCategory.getBudgetCategoryTypeCode());
        		budgetCategoryDTO.setBudgetCategoryTypeDescription(budgetCategory.getBudgetCategoryType().getDescription());
        		budgetCategoryDTO.setDescription(budgetCategory.getDescription());
        		budgetCategoryDTOs.add(budgetCategoryDTO);
        	}
        }
       
        return budgetCategoryDTOs;
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
