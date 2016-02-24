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
package org.kuali.coeus.common.impl.country;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.api.country.CountryContract;
import org.kuali.coeus.common.api.country.KcCountryService;
import org.kuali.coeus.common.impl.country.CountryDto;
import org.kuali.rice.location.api.country.Country;
import org.kuali.rice.location.api.country.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("kcCountryService")
public class KcCountryServiceImpl implements KcCountryService {

    @Autowired
    @Qualifier("countryService")
    private CountryService countryService;

    @Override
    public CountryContract getCountry(String code) {
        if (StringUtils.isBlank(code)) {
            throw new IllegalArgumentException("code is blank");
        }

        return toDto(countryService.getCountry(code));
    }

    @Override
    public CountryContract getCountryByAlternateCode(String alternateCode) {
        if (StringUtils.isBlank(alternateCode)) {
            throw new IllegalArgumentException("alternateCode is blank");
        }

        return toDto(countryService.getCountryByAlternateCode(alternateCode));
    }

    protected CountryDto toDto(Country country) {
        if (country != null) {
            final CountryDto dto = new CountryDto();
            dto.setName(country.getName());
            dto.setCode(country.getCode());
            dto.setAlternateCode(country.getAlternateCode());
            return dto;
        }
        return null;
    }

    public CountryService getCountryService() {
        return countryService;
    }

    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }
}
