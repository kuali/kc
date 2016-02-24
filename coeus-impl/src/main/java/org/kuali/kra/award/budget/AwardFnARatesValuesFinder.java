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
package org.kuali.kra.award.budget;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.coeus.common.budget.framework.rate.RateType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.KeyValuesService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AwardFnARatesValuesFinder extends UifKeyValuesFinderBase {
    KeyValuesService keyValuesService = (KeyValuesService) KcServiceLocator.getService("keyValuesService");
    ParameterService parameterService = (ParameterService) KcServiceLocator.getService(ParameterService.class);
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
    @Override
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
