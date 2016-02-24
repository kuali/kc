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

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.common.budget.api.core.category.BudgetCategoryTypeContract;
import org.kuali.coeus.common.budget.framework.core.BudgetConstants;

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
    	return new CompareToBuilder().append(this.sortId, o.sortId).toComparison();
    }

    public boolean isCategoryParticipantSupport() {
    	return getCode().equalsIgnoreCase(BudgetConstants.BUDGET_CATEGORY_TYPE_PARTICIPANT_SUPPORT);
    }
    
}
