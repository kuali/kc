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
package org.kuali.coeus.common.budget.framework.period;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

@Entity
@Table(name = "BUDGET_PERIOD_TYPE")
public class BudgetPeriodType extends KcPersistableBusinessObjectBase {

    @Id
    @Column(name = "BUDGET_PERIOD_TYPE_CODE")
    private String budgetPeriodTypeCode;

    @Column(name = "DESCRIPTION")
    private String description;

    public String getBudgetPeriodTypeCode() {
        return budgetPeriodTypeCode;
    }

    public void setBudgetPeriodTypeCode(String budgetPeriodTypeCode) {
        this.budgetPeriodTypeCode = budgetPeriodTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
