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

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
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
