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
import org.kuali.coeus.common.budget.api.core.category.BudgetCategoryContract;

import javax.persistence.*;

@Entity
@Table(name = "BUDGET_CATEGORY")
public class BudgetCategory extends KcPersistableBusinessObjectBase implements BudgetCategoryContract {

    @Id
    @Column(name = "BUDGET_CATEGORY_CODE")
    private String code;

    @Column(name = "CATEGORY_TYPE")
    private String budgetCategoryTypeCode;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "CATEGORY_TYPE", referencedColumnName = "BUDGET_CATEGORY_TYPE_CODE", insertable = false, updatable = false)
    private BudgetCategoryType budgetCategoryType;

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBudgetCategoryTypeCode() {
        return budgetCategoryTypeCode;
    }

    public void setBudgetCategoryTypeCode(String budgetCategoryTypeCode) {
        this.budgetCategoryTypeCode = budgetCategoryTypeCode;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public BudgetCategoryType getBudgetCategoryType() {
        return budgetCategoryType;
    }

    public void setBudgetCategoryType(BudgetCategoryType budgetCategoryType) {
        this.budgetCategoryType = budgetCategoryType;
    }
}
