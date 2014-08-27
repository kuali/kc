/*
 * Copyright 2005-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kuali.coeus.common.budget.framework.income;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.io.Serializable;

public class BudgetPeriodIncomeTotal implements Serializable {
    private Integer budgetPeriodNumber;
    private ScaleTwoDecimal projectIncomePeriodTotal;

    public BudgetPeriodIncomeTotal ( Integer budgetPeriodNumber, ScaleTwoDecimal projectIncomePeriodTotal){
        setBudgetPeriodNumber(budgetPeriodNumber);
        setProjectIncomePeriodTotal(projectIncomePeriodTotal);
    }

    public Integer getBudgetPeriodNumber() {
        return budgetPeriodNumber;
    }

    public void setBudgetPeriodNumber(Integer budgetPeriodNumber) {
        this.budgetPeriodNumber = budgetPeriodNumber;
    }

    public ScaleTwoDecimal getProjectIncomePeriodTotal() {
        return projectIncomePeriodTotal;
    }

    public void setProjectIncomePeriodTotal(ScaleTwoDecimal projectIncomePeriodTotal) {
        this.projectIncomePeriodTotal = projectIncomePeriodTotal;
    }
    
}
