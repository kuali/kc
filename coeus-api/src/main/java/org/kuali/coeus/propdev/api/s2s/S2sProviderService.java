package org.kuali.coeus.propdev.api.s2s;

public interface S2sProviderService {
    /**
     * This method retrieves and S2S Provider by code. The code cannot be blank.
     * Will return null if non is found.
     *
     * @param code the provider code.  Cannot be blank.
     * @return the S2sProvider or null.
     * @throws java.lang.IllegalArgumentException if code is blank
     */
    S2sProviderContract findS2SProviderByCode(String code);
}
