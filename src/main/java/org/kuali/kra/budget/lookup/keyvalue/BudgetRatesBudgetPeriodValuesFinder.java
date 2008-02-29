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
package org.kuali.kra.budget.lookup.keyvalue;

import java.util.ArrayList;
import java.util.List;

import org.kuali.core.lookup.keyvalues.KeyValuesBase;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.RateClass;
import org.kuali.kra.budget.service.BudgetRatesService;
import org.kuali.kra.infrastructure.KraServiceLocator;

public class BudgetRatesBudgetPeriodValuesFinder  extends KeyValuesBase{

    public List getKeyValues() {
        BudgetRatesService budgetRatesService = KraServiceLocator.getService(BudgetRatesService.class);
        List<BudgetPeriod> budgetPeriods = budgetRatesService.getBudgetPeriods();
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        keyValues.add(new KeyLabelPair("", "View All"));
        for(BudgetPeriod budgetPeriod: budgetPeriods) {
            keyValues.add(new KeyLabelPair(budgetPeriod.getBudgetPeriod(), budgetPeriod.getLabel()));
        }
        return keyValues;
    }
}
