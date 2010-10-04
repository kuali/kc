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
import java.util.Iterator;
import java.util.List;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.bo.Country;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.service.CountryService;
import org.kuali.rice.core.util.KeyLabelPair;


public class CountryCodeValuesFinder extends KeyValuesBase{

    public List<KeyLabelPair> getKeyValues() {
        CountryService countryService = KraServiceLocator.getService(CountryService.class);
        List<Country> countries = countryService.findAllCountries();
        Country defaultCountry = countryService.getDefaultCountry();
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        keyValues.add(new KeyLabelPair("", "select: "));
        if (defaultCountry != null) keyValues.add(new KeyLabelPair(defaultCountry.getAlternatePostalCountryCode(), defaultCountry.getPostalCountryName()));
        for (Iterator<Country> iter = countries.iterator(); iter.hasNext();) {
            Country country = (Country) iter.next();
            keyValues.add(new KeyLabelPair(country.getAlternatePostalCountryCode(), country.getPostalCountryName())); 
         }
        return keyValues;
        
        
    }

    

}
