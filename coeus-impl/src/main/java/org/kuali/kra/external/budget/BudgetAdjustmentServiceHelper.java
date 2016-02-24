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
package org.kuali.kra.external.budget;

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
