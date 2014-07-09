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
import org.kuali.coeus.common.budget.api.core.category.BudgetCategoryTypeContract;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class representation of the BudgetCategory Type Business Object
 */
@Entity
@Table(name = "BUDGET_CATEGORY_TYPE")
public class BudgetCategoryType extends KcPersistableBusinessObjectBase implements Comparable<BudgetCategoryType>, BudgetCategoryTypeContract {

    @Id
    @Column(name = "BUDGET_CATEGORY_TYPE_CODE")
    private String code;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "SORT_ID")
    private Integer sortId;

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    /**
     * This is for totals page 
     */
    @Override
    public int compareTo(BudgetCategoryType o) {
        return this.sortId.compareTo(o.sortId);
    }
}
