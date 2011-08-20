/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.external.budget;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.rates.RateType;

public interface BudgetAdjustmentServiceHelper {

    HashMap<String, BudgetDecimal> getNonPersonnelCost(Budget currentBudget, AwardBudgetExt previousBudget);
    
    SortedMap<RateType, BudgetDecimal> getNonPersonnelCalculatedDirectCost(Budget currentBudget, AwardBudgetExt previousBudget);
    
    Map<RateClassRateType, BudgetDecimal> getIndirectCost(Budget currentBudget, AwardBudgetExt previousBudget);
    
    Map<RateClassRateType, BudgetDecimal> getPersonnelCalculatedDirectCost(Budget currentBudget, AwardBudgetExt previousBudget);

    Map<RateClassRateType, BudgetDecimal> getPersonnelFringeCost(Budget currentBudget, AwardBudgetExt previousBudget);
    
    SortedMap<String, BudgetDecimal> getPersonnelSalaryCost(Budget currentBudget, AwardBudgetExt previousBudget) throws Exception;

}
