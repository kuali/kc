package org.kuali.coeus.s2sgen.impl.location;

import org.kuali.coeus.common.api.country.CountryContract;
import org.kuali.coeus.common.api.state.StateContract;

public interface S2SLocationService {

    /**
     * Finds a Country object from the country code
     *
     * @param countryCode
     *            Country name
     * @return Country object matching the code
     */
    CountryContract getCountryFromCode(String countryCode);

    /**
     * Finds a State object from the state name
     * @param countryAlternateCode country 3-character code
     * @param stateName
     *            Name of the state (two-letter state code)
     * @return State object matching the name.
     */
    StateContract getStateFromName(String countryAlternateCode, String stateName);
}
