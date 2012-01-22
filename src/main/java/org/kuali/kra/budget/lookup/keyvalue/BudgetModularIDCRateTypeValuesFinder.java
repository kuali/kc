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
package org.kuali.kra.budget.lookup.keyvalue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.budget.rates.RateClass;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.lookup.keyvalue.KeyValueFinderService;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

public class BudgetModularIDCRateTypeValuesFinder extends KeyValuesBase {
    
    public List getKeyValues() {
        KeyValueFinderService keyValueFinderService= (KeyValueFinderService)KraServiceLocator.getService("keyValueFinderService");
        Map<String,String> queryMap = new HashMap<String,String>();
        queryMap.put("rateClassType", "O");
        List<KeyValue> keyValueList = keyValueFinderService.getKeyValues(RateClass.class, "description", "description", queryMap);
        KeyValue KeyValueSelect = new ConcreteKeyValue("", "select");
        for (KeyValue KeyValue : keyValueList) {
            if (StringUtils.isBlank(KeyValue.getKey().toString())) {
                KeyValueSelect = KeyValue;
            }            
        }
        keyValueList.remove(KeyValueSelect);
        return keyValueList;

        /*
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("1", "MTDC"));
        keyValues.add(new ConcreteKeyValue("2", "TDC"));
        return keyValues;
        */
        
    }
}
