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
package org.kuali.kra.lookup.keyvalue;

import java.util.ArrayList;
import java.util.List;

import org.kuali.core.lookup.keyvalues.KeyValuesBase;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.bo.Country;
import org.kuali.kra.bo.SponsorType;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.service.KeyValueFinderService;

public class CountryValuesFinder extends KeyValuesBase {
    
//    public List<KeyLabelPair> getKeyValues() {
//        // TODO Use reference table
//        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
//        keyValues.add(new KeyLabelPair("", "select:"));
//        keyValues.add(new KeyLabelPair("US", "United States of America"));
//        keyValues.add(new KeyLabelPair("UK", "United Kigdom"));
//        return keyValues;
//    }
    KeyValueFinderService keyValueFinderService= (KeyValueFinderService)KraServiceLocator.getService("keyValueFinderService");
    public List<KeyLabelPair> getKeyValues() {
        return keyValueFinderService.getKeyValues(Country.class, "countryCode", "countryName");
    }
    
}
