package org.kuali.coeus.s2sgen.impl.location;

import org.kuali.coeus.common.api.country.CountryContract;
import org.kuali.coeus.common.api.country.KcCountryService;
import org.kuali.coeus.common.api.state.KcStateService;
import org.kuali.coeus.common.api.state.StateContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("s2SLocationService")
public class S2SLocationServiceImpl implements S2SLocationService {

    @Autowired
    @Qualifier("kcCountryService")
    private KcCountryService kcCountryService;

    @Autowired
    @Qualifier("kcStateService")
    private KcStateService kcStateService;

    /**
     * This method is to get a Country object from the country code
     *
     * @param countryCode country code for the country.
     * @return Country object matching the code
     */
    @Override
    public CountryContract getCountryFromCode(String countryCode) {
        if(countryCode==null) return null;
        CountryContract country = getKcCountryService().getCountryByAlternateCode(countryCode);
        if(country==null){
            country = getKcCountryService().getCountry(countryCode);
        }
        return country;
    }



    /**
     * This method is to get a State object from the state name
     *
     * @param stateName Name of the state
     * @return State object matching the name.
     */
    @Override
    public StateContract getStateFromName(String countryAlternateCode, String stateName) {
        CountryContract country = getCountryFromCode(countryAlternateCode);

        StateContract state = getKcStateService().getState(country.getCode(), stateName);
        return state;
    }

    public KcStateService getKcStateService() {
        return kcStateService;
    }

    public void setKcStateService(KcStateService kcStateService) {
        this.kcStateService = kcStateService;
    }

    public KcCountryService getKcCountryService() {
        return kcCountryService;
    }

    public void setKcCountryService(KcCountryService kcCountryService) {
        this.kcCountryService = kcCountryService;
    }
}
