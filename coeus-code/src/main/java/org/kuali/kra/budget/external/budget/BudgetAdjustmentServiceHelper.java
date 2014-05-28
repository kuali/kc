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
package org.kuali.kra.budget.external.budget;

import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.rate.RateType;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

public interface BudgetAdjustmentServiceHelper {

    HashMap<String, ScaleTwoDecimal> getNonPersonnelCost(Budget currentBudget, AwardBudgetExt previousBudget);
    
    SortedMap<RateType, ScaleTwoDecimal> getNonPersonnelCalculatedDirectCost(Budget currentBudget, AwardBudgetExt previousBudget);
    
    Map<RateClassRateType, ScaleTwoDecimal> getIndirectCost(Budget currentBudget, AwardBudgetExt previousBudget);
    
    Map<RateClassRateType, ScaleTwoDecimal> getPersonnelCalculatedDirectCost(Budget currentBudget, AwardBudgetExt previousBudget);

    Map<RateClassRateType, ScaleTwoDecimal> getPersonnelFringeCost(Budget currentBudget, AwardBudgetExt previousBudget);
    
    SortedMap<String, ScaleTwoDecimal> getPersonnelSalaryCost(Budget currentBudget, AwardBudgetExt previousBudget) throws Exception;

}
