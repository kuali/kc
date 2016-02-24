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
package org.kuali.coeus.common.budget.framework.rate;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.kuali.coeus.common.budget.api.rate.BudgetRateContract;
import org.kuali.coeus.common.budget.framework.core.DateSortable;

@Entity
@Table(name = "EPS_PROP_RATES")
public class BudgetRate extends AbstractBudgetRate implements DateSortable, BudgetRateContract {

    private static final long serialVersionUID = 6843344277997293690L;

    @Column(name = "ACTIVITY_TYPE_CODE")
    private String activityTypeCode;

    public BudgetRate() {
        super();
    }

    public BudgetRate(String unitNumber, InstituteRate instituteRate) {
        super(unitNumber, instituteRate);
        setActivityTypeCode(instituteRate.getActivityTypeCode());
    }

    @Override
    public String getActivityTypeCode() {
        return activityTypeCode;
    }

    public void setActivityTypeCode(String activityTypeCode) {
        this.activityTypeCode = activityTypeCode;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((activityTypeCode == null) ? 0 : activityTypeCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        BudgetRate other = (BudgetRate) obj;
        if (activityTypeCode == null) {
            if (other.activityTypeCode != null)
                return false;
        } else if (!activityTypeCode.equals(other.activityTypeCode))
            return false;
        return true;
    }

    @Override
    public Date getSortableDate() {
        return getStartDate();
    }
}
