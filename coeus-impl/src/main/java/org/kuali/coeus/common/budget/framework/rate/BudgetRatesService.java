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
package org.kuali.coeus.common.budget.framework.rate;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetParent;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface BudgetRatesService <T extends BudgetParent>{
    public void getBudgetRates(List<RateClassType> rateClassTypes, Budget budget);

    /**
     * Searches for persisted {@link RateClass} instances based on the given <code>rateClassType</code>
     *
     * @param rateClassType to use for retrieving {@link RateClass} instances
     * @returns a List of {@link RateClass} instances
     */
    public Collection<RateClass> getBudgetRateClasses(String rateClassType);

    /**
     * Retrieves {@link RateClass} instances as a {@link Map} keyed from the <code>rateTypeCode</code>. This makes it easy for
     * classes (particularly in the UI) to grab {@link RateClass} information via rateTypeCode
     *
     * @param rateClassType to use for {@link RateClass} instances to be retrieved
     * @return a {@link Map} keyed on rateTypeCode containing {@link RateClass} instances
     */
    public Map<String, RateClass> getBudgetRateClassMap(String rateClassType);

    public void resetBudgetRatesForRateClassType(String rateClassType, Budget budget);
    public void syncBudgetRatesForRateClassType(String rateClassType, Budget budget);
    
    public void syncBudgetRateCollectionsToExistingRates(List<RateClassType> rateClassTypes, Budget budget);
    public void syncAllBudgetRates(Budget budget);
    public void resetAllBudgetRates(Budget budget);
    public void viewLocation(String viewLocation, Integer budgetPeriod, Budget budget);

    public void populateInstituteRates(Budget budget);

    public boolean performSyncFlag(Budget budget);
    public void syncParentDocumentRates(Budget budget);
    
    public double getUnitFormulatedCost(String unitNumber,String formulatedType);

    /**
     *
     * This method returns the saved Proposal Rates collection.
     * @param budget
     * @return Collection<BudgetRate>
     */

    public Collection<BudgetRate> getSavedBudgetRates(Budget budget);

    /**
     *
     * This method check if activity type changed, then display confirmation message on 'open' budget version.
     * @param budget
     * @return
     */
    public boolean checkActivityTypeChange(Budget budget);

    /**
     *
     * This method checks if activity type changed, then display confirmation message on 'open' budget version.
     * @param allPropRates
     * @param proposalActivityTypeCode
     * @return
     */
    public boolean checkActivityTypeChange(Collection<BudgetRate> allPropRates, String proposalActivityTypeCode);
}
