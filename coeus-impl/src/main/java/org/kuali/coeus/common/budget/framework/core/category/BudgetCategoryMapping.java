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
import org.kuali.coeus.common.budget.api.core.category.BudgetCategoryMappingContract;

public class BudgetCategoryMapping extends KcPersistableBusinessObjectBase implements BudgetCategoryMappingContract {

    private String budgetCategoryCode;

    private String mappingName;

    private String targetCategoryCode;

    private BudgetCategory budgetCategory;

    @Override
    public String getBudgetCategoryCode() {
        return budgetCategoryCode;
    }

    public void setBudgetCategoryCode(String budgetCategoryCode) {
        this.budgetCategoryCode = budgetCategoryCode;
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
    public BudgetCategory getBudgetCategory() {
        return budgetCategory;
    }

    public void setBudgetCategory(BudgetCategory budgetCategory) {
        this.budgetCategory = budgetCategory;
    }
}
