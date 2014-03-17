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
package org.kuali.kra.budget.rates;

import org.kuali.kra.bo.InstituteRate;
import org.kuali.kra.budget.calculator.DateSortable;

import java.sql.Date;

public class BudgetRate extends AbstractBudgetRate implements DateSortable {


    private static final long serialVersionUID = 6843344277997293690L;
    private String activityTypeCode;

    public BudgetRate() {
        super();
    }

    public BudgetRate(String unitNumber, InstituteRate instituteRate) {
        super(unitNumber, instituteRate);
        setActivityTypeCode(instituteRate.getActivityTypeCode());
    }

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
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        BudgetRate other = (BudgetRate) obj;
        if (activityTypeCode == null) {
            if (other.activityTypeCode != null) return false;
        } else if (!activityTypeCode.equals(other.activityTypeCode)) return false;
        return true;
    }

    @Override
    public Date getSortableDate() {
        return getStartDate();
    }
}
