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
package org.kuali.kra.award.budget.calculator;

import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;

public interface AwardBudgetCalculationService extends BudgetCalculationService {
    
    /**
     * Calculates budget totals for budget and optionally aggregates all previous
     * budget summaries as well (for all previous budgets)
     * @param budget
     * @param includePrevious
     */
    void calculateBudgetSummaryTotals(AwardBudgetExt budget, boolean includePrevious);
}
