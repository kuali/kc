package org.kuali.coeus.common.api.state;

public interface KcStateService {

    /**
     * This method retrieves a state by country code and state code.  If the state is not found null is returned.
     * Both the country code and state code cannot be blank.
     *
     * @param countryCode the country code.  cannot be blank.
     * @param code the state code.  cannot be blank.
     * @return the state or null if not found.
     * @throws java.lang.IllegalArgumentException if the countryCode, code is blank
     */
    StateContract getState(String countryCode, String code);
}
