/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.budget.modular;

import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.BudgetAssociate;
import org.kuali.kra.budget.rates.RateClass;

public class BudgetModularIdc extends BudgetAssociate {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 9162516694202776979L;

    private Long budgetPeriodId;

    private Integer budgetPeriod;

    private Integer rateNumber;

    private String description;

    private BudgetDecimal idcRate;

    private BudgetDecimal idcBase;

    private BudgetDecimal fundsRequested;

    private RateClass rateClass;

    public BudgetModularIdc() {
        idcRate = new BudgetDecimal(0.0);
        idcBase = new BudgetDecimal(0.0);
        fundsRequested = new BudgetDecimal(0.0);
    }

    public Integer getBudgetPeriod() {
        return budgetPeriod;
    }

    public void setBudgetPeriod(Integer budgetPeriod) {
        this.budgetPeriod = budgetPeriod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BudgetDecimal getFundsRequested() {
        return fundsRequested;
    }

    public void setFundsRequested(BudgetDecimal fundsRequested) {
        this.fundsRequested = fundsRequested;
    }

    public BudgetDecimal getIdcBase() {
        return idcBase;
    }

    public void setIdcBase(BudgetDecimal idcBase) {
        this.idcBase = idcBase;
    }

    public BudgetDecimal getIdcRate() {
        return idcRate;
    }

    public void setIdcRate(BudgetDecimal idcRate) {
        this.idcRate = idcRate;
    }

    public Integer getRateNumber() {
        return rateNumber;
    }

    public void setRateNumber(Integer rateNumber) {
        this.rateNumber = rateNumber;
    }

    public RateClass getRateClass() {
        return rateClass;
    }

    public void setRateClass(RateClass rateClass) {
        this.rateClass = rateClass;
    }

    public void calculateFundsRequested() {
        BudgetDecimal fundsRequested = new BudgetDecimal(0);
        if (this.getIdcBase() != null && this.getIdcRate() != null) {
            fundsRequested = this.getIdcBase().percentage(this.getIdcRate());
        }
        this.setFundsRequested(fundsRequested);
    }

    public Long getBudgetPeriodId() {
        return budgetPeriodId;
    }

    public void setBudgetPeriodId(Long budgetPeriodId) {
        this.budgetPeriodId = budgetPeriodId;
    }
}
