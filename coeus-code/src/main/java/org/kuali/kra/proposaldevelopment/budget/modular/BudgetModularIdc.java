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
package org.kuali.kra.proposaldevelopment.budget.modular;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.kra.budget.core.BudgetAssociate;
import org.kuali.kra.budget.rates.RateClass;

public class BudgetModularIdc extends BudgetAssociate {


    private static final long serialVersionUID = 9162516694202776979L;

    private Long budgetPeriodId;

    private Integer budgetPeriod;

    private Integer rateNumber;

    private String description;

    private ScaleTwoDecimal idcRate;

    private ScaleTwoDecimal idcBase;

    private ScaleTwoDecimal fundsRequested;

    private RateClass rateClass;

    public BudgetModularIdc() {
        idcRate = new ScaleTwoDecimal(0.0);
        idcBase = new ScaleTwoDecimal(0.0);
        fundsRequested = new ScaleTwoDecimal(0.0);
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

    public ScaleTwoDecimal getFundsRequested() {
        return fundsRequested;
    }

    public void setFundsRequested(ScaleTwoDecimal fundsRequested) {
        this.fundsRequested = fundsRequested;
    }

    public ScaleTwoDecimal getIdcBase() {
        return idcBase;
    }

    public void setIdcBase(ScaleTwoDecimal idcBase) {
        this.idcBase = idcBase;
    }

    public ScaleTwoDecimal getIdcRate() {
        return idcRate;
    }

    public void setIdcRate(ScaleTwoDecimal idcRate) {
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
        ScaleTwoDecimal fundsRequested = new ScaleTwoDecimal(0);
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
