/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.framework.country;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;
import org.kuali.rice.location.api.country.Country;
import org.kuali.rice.location.api.country.CountryService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CountryCodeValuesFinder extends UifKeyValuesFinderBase {

    private CountryService countryService;

    @Override
    public List<KeyValue> getKeyValues() {
            final List<Country> countries = getCountryService().findAllCountries();
            final Country defaultCountry = getCountryService().getDefaultCountry();
            final List<KeyValue> keyValues = new ArrayList<KeyValue>();
            if (defaultCountry != null) {
                keyValues.add(new ConcreteKeyValue(defaultCountry.getAlternateCode(), defaultCountry.getName()));
            }
            for (Country country : countries) {
                keyValues.add(new ConcreteKeyValue(country.getAlternateCode(), country.getName()));
             }
            return keyValues;
        }

    public CountryService getCountryService() {
        if (countryService == null) {
            countryService = KcServiceLocator.getService(CountryService.class);
        }

        return countryService;
    }

    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }
}
