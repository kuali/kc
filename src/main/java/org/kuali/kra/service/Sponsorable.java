package org.kuali.kra.service;

import org.kuali.rice.krad.bo.PersistableBusinessObject;


public interface Sponsorable extends PersistableBusinessObject {
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
