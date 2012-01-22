/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.award.budget;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.budget.rates.RateType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;
import org.kuali.rice.krad.service.KeyValuesService;

public class AwardFnARatesValuesFinder extends KeyValuesBase{
    KeyValuesService keyValuesService = (KeyValuesService) KraServiceLocator.getService("keyValuesService");
    ParameterService parameterService = (ParameterService)KraServiceLocator.getService(ParameterService.class);
    /**
     * Constructs the list of Budget Periods.  Each entry
     * in the list is a &lt;key, value&gt; pair, where the "key" is the unique
     * status code and the "value" is the textual description that is viewed
     * by a user.  The list is obtained from the BudgetDocument if any are defined there. 
     * Otherwise, it is obtained from a lookup of the BUDGET_PERIOD database table
     * via the "KeyValueFinderService".
     * 
     * @return the list of &lt;key, value&gt; pairs of abstract types.  The first entry
     * is always &lt;"", "select:"&gt;.
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyValue> getKeyValues() {
        List<KeyValue> matchingAwardFnARateTypes = filterRateTypes();
        matchingAwardFnARateTypes.add(0, new ConcreteKeyValue("", "select"));
        return matchingAwardFnARateTypes;
    }
    
    private List<KeyValue> filterRateTypes() {
        Collection<RateType> awardFnARateTypes= keyValuesService.findAll(RateType.class);
        String fnaRateClassCode = parameterService.getParameterValueAsString(AwardBudgetDocument.class, Constants.AWARD_BUDGET_DEFAULT_FNA_RATE_CLASS_CODE);
        List<KeyValue> keyValues = new ArrayList<KeyValue>();        
        for (RateType rateType : awardFnARateTypes) {
            if(rateType.getRateClassCode().equals(fnaRateClassCode)){
                keyValues.add(new ConcreteKeyValue(rateType.getRateTypeCode(),rateType.getDescription()));
            }
        }
        return keyValues;
    }

}
