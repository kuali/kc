/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.common.budget.framework.rate;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetParent;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface BudgetRatesService <T extends BudgetParent>{
    public void getBudgetRates(List<RateClassType> rateClassTypes, Budget budget);

    /**
     * Searches for persisted {@link RateClass} instances based on the given <code>rateClassType</code>
     *
     * @param rateClassType to use for retrieving {@link RateClass} instances
     * @return a List of {@link RateClass} instances
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
    
    public ScaleTwoDecimal getUnitFormulatedCost(String unitNumber,String formulatedType);

    /**
     *
     * This method returns the saved Proposal Rates collection.
     * @param budget
     * @return Collection&lt;BudgetRate&gt;
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
