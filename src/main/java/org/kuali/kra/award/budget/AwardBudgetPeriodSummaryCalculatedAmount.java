/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.award.budget;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.CostElement;

public class AwardBudgetPeriodSummaryCalculatedAmount extends KraPersistableBusinessObjectBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8085114536868213976L;

    private Long awardBudgetPeriodSummaryCalculatedAmountId;

    private Long budgetPeriodId;

    private String costElement;

    private boolean onOffCampusFlag;

    private String rateClassType;

    private BudgetDecimal calculatedCost;

    private BudgetDecimal calculatedCostSharing;

    private CostElement costElementBO;

    public Long getAwardBudgetPeriodSummaryCalculatedAmountId() {
        return awardBudgetPeriodSummaryCalculatedAmountId;
    }

    public void setAwardBudgetPeriodSummaryCalculatedAmountId(Long awardBudgetPeriodSummaryCalculatedAmountId) {
        this.awardBudgetPeriodSummaryCalculatedAmountId = awardBudgetPeriodSummaryCalculatedAmountId;
    }

    public Long getBudgetPeriodId() {
        return budgetPeriodId;
    }

    public void setBudgetPeriodId(Long budgetPeriodId) {
        this.budgetPeriodId = budgetPeriodId;
    }

    public String getCostElement() {
        return costElement;
    }

    public void setCostElement(String costElement) {
        this.costElement = costElement;
    }

    public boolean getOnOffCampusFlag() {
        return onOffCampusFlag;
    }

    public void setOnOffCampusFlag(boolean onOffCampusFlag) {
        this.onOffCampusFlag = onOffCampusFlag;
    }

    public String getRateClassType() {
        return rateClassType;
    }

    public void setRateClassType(String rateClassType) {
        this.rateClassType = rateClassType;
    }

    public BudgetDecimal getCalculatedCost() {
        return calculatedCost;
    }

    public void setCalculatedCost(BudgetDecimal calculatedCost) {
        this.calculatedCost = calculatedCost;
    }

    public BudgetDecimal getCalculatedCostSharing() {
        return calculatedCostSharing;
    }

    public void setCalculatedCostSharing(BudgetDecimal calculatedCostSharing) {
        this.calculatedCostSharing = calculatedCostSharing;
    }

    public CostElement getCostElementBO() {
        return costElementBO;
    }

    public void setCostElementBO(CostElement costElementBO) {
        this.costElementBO = costElementBO;
    }
}
