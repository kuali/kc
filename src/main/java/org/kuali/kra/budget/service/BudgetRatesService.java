/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.budget.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.RateClass;
import org.kuali.kra.budget.bo.RateClassType;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.rice.kns.service.BusinessObjectService;

public interface BudgetRatesService {
    public void getBudgetRates(List<RateClassType> rateClassTypes, BudgetDocument budgetDocument);

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

    public void resetBudgetRatesForRateClassType(String rateClassType, BudgetDocument budgetDocument);
    public void syncBudgetRatesForRateClassType(String rateClassType, BudgetDocument budgetDocument);
    public void syncAllBudgetRates(BudgetDocument budgetDocument);
    public void resetAllBudgetRates(BudgetDocument budgetDocument);
    public void viewLocation(String viewLocation, Integer budgetPeriod, BudgetDocument budgetDocument);
    public List<BudgetPeriod> getBudgetPeriods();
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService);
    
    public BusinessObjectService getBusinessObjectService();
    public boolean isOutOfSyncForRateAudit(BudgetDocument budgetDocument);

}
