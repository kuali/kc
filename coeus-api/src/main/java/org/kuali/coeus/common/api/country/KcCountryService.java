package org.kuali.coeus.common.api.country;


public interface KcCountryService {

    /**
     * This method retrieves a country by country code.  If the country is not found null is returned.
     * The country code cannot be blank.
     *
     * @param code the country code.  cannot be blank.
     * @return the country or null if not found.
     * @throws java.lang.IllegalArgumentException if the code is blank
     */
    CountryContract getCountry(String code);

    /**
     * This method retrieves a country by alternate country code.  The alternate country code is usually a three digit
     * code. If the country is not found null is returned. The alternate country code cannot be blank.
     *
     * @param alternateCode the alternate country code.  cannot be blank.
     * @return the country or null if not found.
     * @throws java.lang.IllegalArgumentException if the alternateCode is blank
     */
    CountryContract getCountryByAlternateCode(String alternateCode);
}
