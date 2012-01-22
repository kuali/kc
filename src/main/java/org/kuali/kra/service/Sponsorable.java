package org.kuali.kra.service;


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
