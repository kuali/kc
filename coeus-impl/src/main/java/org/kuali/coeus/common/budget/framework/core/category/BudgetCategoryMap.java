/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.common.budget.framework.core.category;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.common.budget.api.core.category.BudgetCategoryMapContract;

import java.util.ArrayList;
import java.util.List;

public class BudgetCategoryMap extends KcPersistableBusinessObjectBase implements BudgetCategoryMapContract {

    private String mappingName;

    private String targetCategoryCode;

    private String categoryType;

    private String description;

    private List<BudgetCategoryMapping> budgetCategoryMappings;

    public BudgetCategoryMap() {
        budgetCategoryMappings = new ArrayList<BudgetCategoryMapping>();
    }

    @Override
    public String getMappingName() {
        return mappingName;
    }

    public void setMappingName(String mappingName) {
        this.mappingName = mappingName;
    }

    @Override
    public String getTargetCategoryCode() {
        return targetCategoryCode;
    }

    public void setTargetCategoryCode(String targetCategoryCode) {
        this.targetCategoryCode = targetCategoryCode;
    }

    @Override
    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public List<BudgetCategoryMapping> getBudgetCategoryMappings() {
        return budgetCategoryMappings;
    }


    public void setBudgetCategoryMappings(List<BudgetCategoryMapping> budgetCategoryMappings) {
        this.budgetCategoryMappings = budgetCategoryMappings;
    }
}
