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
