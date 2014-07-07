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
