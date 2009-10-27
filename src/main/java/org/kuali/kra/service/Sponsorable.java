package org.kuali.kra.service;

import java.util.Map;

public interface Sponsorable {
    /**
     * 
     * @return
     */
    boolean isNih();

    /**
     * 
     * @return
     */
    String getSponsorCode();

    /**
     *
     * @param isNih
     */
    void setNih(boolean isNih);

    /**
     *
     * @param stringStringMap
     */
    void setNihDescription(Map<String, String> descriptionMap);


    /**
     * Return map as is structured as follows:
     *  key = Role code (i.e. PI, COI, KP)
     *  value = Description
     *  
     * @return
     */
    Map<String, String> getNihDescription();

    /**
     * 
     * @param sponsorCode
     */
    void setSponsorCode(String sponsorCode);
}
