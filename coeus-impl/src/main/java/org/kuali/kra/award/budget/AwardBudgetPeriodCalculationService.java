/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General License for more details.
 * 
 * You should have received a copy of the GNU Affero General License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.award.budget;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetFormulatedCostDetail;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

public interface AwardBudgetPeriodCalculationService {

    public void calculateBudgetPeriod(boolean forceCalculation, Budget budget, BudgetPeriod budgetPeriod);

    public void calculateBudgetPeriod(Budget budget, BudgetPeriod budgetPeriod);

    public void recalculateBudgetPeriod(Budget budget, BudgetPeriod budgetPeriod);

    public void calculateAndUpdateFormulatedCost(BudgetLineItem budgetLineItem);

    public void calculateBudgetFormulatedCost( BudgetFormulatedCostDetail budgetFormulatedCost);

    public ScaleTwoDecimal getFormulatedCostsTotal(BudgetLineItem budgetLineItem);

}
