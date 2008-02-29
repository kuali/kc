/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.budget.service;

import java.util.HashMap;
import java.util.List;

import org.kuali.kra.bo.InstituteRate;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetProposalRate;
import org.kuali.kra.budget.bo.RateClass;
import org.kuali.kra.budget.bo.RateClassType;
import org.kuali.kra.budget.document.BudgetDocument;

import edu.iu.uis.eden.exception.WorkflowException;

public interface BudgetRatesService {
    public void getBudgetRates(List<RateClassType> rateClassTypes, BudgetDocument budgetDocument);
    public void resetBudgetRatesForRateClassType(String rateClassType, BudgetDocument budgetDocument);
    public void syncBudgetRatesForRateClassType(String rateClassType, BudgetDocument budgetDocument);
    public void syncAllBudgetRates(BudgetDocument budgetDocument);
    public void resetAllBudgetRates(BudgetDocument budgetDocument);
    public void viewLocation(String viewLocation, Integer budgetPeriod, BudgetDocument budgetDocument);
    public List<BudgetPeriod> getBudgetPeriods();
}
