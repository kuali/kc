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
package org.kuali.coeus.common.framework.country;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;
import org.kuali.rice.location.api.country.Country;
import org.kuali.rice.location.api.country.CountryService;

import java.util.ArrayList;
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
