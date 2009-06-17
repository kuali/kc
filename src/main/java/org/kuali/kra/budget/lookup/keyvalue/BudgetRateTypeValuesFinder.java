/*
 * Copyright 2006-2009 The Kuali Foundation
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
import org.kuali.kra.budget.bo.RateClass;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.lookup.keyvalue.KeyValueFinderService;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

public class BudgetRateTypeValuesFinder extends KeyValuesBase {
    
    public List getKeyValues() {
        KeyValueFinderService keyValueFinderService= (KeyValueFinderService)KraServiceLocator.getService("keyValueFinderService");
        Map<String,String> queryMap = new HashMap<String,String>();
        queryMap.put("rateClassType", "O");
        List<KeyLabelPair> keyValueList = keyValueFinderService.getKeyValues(RateClass.class, "rateClassCode", "description", queryMap);
        KeyLabelPair keyLabelPairSelect = new KeyLabelPair("", "select");
        for (KeyLabelPair keyLabelPair : keyValueList) {
            if (StringUtils.isBlank(keyLabelPair.getKey().toString())) {
                keyLabelPairSelect = keyLabelPair;
            }            
        }
        keyValueList.remove(keyLabelPairSelect);
        return keyValueList;

        /*
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        keyValues.add(new KeyLabelPair("1", "MTDC"));
        keyValues.add(new KeyLabelPair("2", "TDC"));
        return keyValues;
        */
        
    }
}
