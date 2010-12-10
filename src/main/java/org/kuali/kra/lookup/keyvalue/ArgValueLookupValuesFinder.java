/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.lookup.keyvalue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.ArgValueLookup;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.core.util.KeyLabelPair;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.service.BusinessObjectService;

public class ArgValueLookupValuesFinder extends KeyValuesBase {

    private String argName;

    /**
     * @see org.kuali.core.lookup.keyvalues.KeyValuesBase#getKeyValues()
     */
    public List<KeyLabelPair> getKeyValues() {

        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("argumentName", argName);
        Collection<ArgValueLookup> argValueLookups = (Collection<ArgValueLookup>) KraServiceLocator.getService(BusinessObjectService.class).findMatching(ArgValueLookup.class, fieldValues);
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        for (ArgValueLookup argValueLookup : argValueLookups) {
            keyValues.add(new KeyLabelPair(argValueLookup.getValue(), StringUtils.isNotBlank(argValueLookup.getDescription()) ? argValueLookup.getDescription() : argValueLookup.getValue()));
        }
        keyValues.add(0, new KeyLabelPair("", "select"));
        return keyValues;
    }

    public String getArgName() {
        return argName;
    }

    public void setArgName(String argName) {
        this.argName = argName;
    }

}
