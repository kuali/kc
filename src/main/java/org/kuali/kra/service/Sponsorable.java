package org.kuali.kra.service;

import java.util.Map;

public interface Sponsorable {
    /**
     * 
     * @return
     */
    boolean isSponsorNihMultiplePi();

    /**
     * 
     * @return
     */
    String getSponsorCode();

    /**
     * 
     * @param sponsorCode
     */
    void setSponsorCode(String sponsorCode);
}
