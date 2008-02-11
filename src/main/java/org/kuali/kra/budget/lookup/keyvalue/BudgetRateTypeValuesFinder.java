/*
 * Copyright 2007 The Kuali Foundation.
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.core.lookup.keyvalues.KeyValuesBase;
import org.kuali.kra.budget.bo.RateClass;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.lookup.keyvalue.KeyValueFinderService;

public class BudgetRateTypeValuesFinder extends KeyValuesBase {
    
    public List getKeyValues() {
        KeyValueFinderService keyValueFinderService= (KeyValueFinderService)KraServiceLocator.getService("keyValueFinderService");
        Map<String,String> queryMap = new HashMap<String,String>();
        queryMap.put("rateClassType", "O");
        return keyValueFinderService.getKeyValues(RateClass.class, "rateClassCode", "description", queryMap);

        /*
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        keyValues.add(new KeyLabelPair("1", "MTDC"));
        keyValues.add(new KeyLabelPair("2", "TDC"));
        return keyValues;
        */
        
    }
}
