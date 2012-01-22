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
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.CoreFrameworkServiceLocator;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.location.api.country.Country;
import org.kuali.rice.location.api.country.CountryService;

public class CountryCodeValuesFinder extends KeyValuesBase{

        private String getDefaultCountryCode() {
            String postalCountryCode = CoreFrameworkServiceLocator.getParameterService().getParameterValueAsString(KRADConstants.KNS_NAMESPACE,
            KRADConstants.DetailTypes.ALL_DETAIL_TYPE, KRADConstants.SystemGroupParameterNames.DEFAULT_COUNTRY);
            return postalCountryCode;
        }
        
        public List<KeyValue> getKeyValues() {
            CountryService countryService = KraServiceLocator.getService(CountryService.class);
            List<Country> countries = countryService.findAllCountries();
            Country defaultCountry = countryService.getCountry(getDefaultCountryCode());;
            List<KeyValue> keyValues = new ArrayList<KeyValue>();
            keyValues.add(new ConcreteKeyValue("", "select: "));
            if (defaultCountry != null) keyValues.add(new ConcreteKeyValue(defaultCountry.getAlternateCode(), defaultCountry.getName()));
            for (Iterator<Country> iter = countries.iterator(); iter.hasNext();) {
                Country country = (Country) iter.next();
                keyValues.add(new ConcreteKeyValue(country.getAlternateCode(), country.getName())); 
             }
            return keyValues;
            
            
        }
    

}
