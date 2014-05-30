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
package org.kuali.coeus.propdev.impl.budget.modular;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.keyvalue.KeyValueFinderService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.budget.framework.rate.RateClass;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BudgetModularIDCRateTypeValuesFinder extends UifKeyValuesFinderBase {
    @Override
    public List<KeyValue> getKeyValues() {
        KeyValueFinderService keyValueFinderService= (KeyValueFinderService) KcServiceLocator.getService("keyValueFinderService");
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
        
    }
}
