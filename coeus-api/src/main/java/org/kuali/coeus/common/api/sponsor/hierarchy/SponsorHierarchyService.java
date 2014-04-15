package org.kuali.coeus.common.api.sponsor.hierarchy;
        
        
public interface SponsorHierarchyService {
    
    public static final String SPONSOR_HIERARCHY_NIH_MULT_PI = "NIH Multiple PI";
    public static final String SPONSOR_HIERARCHY_NIH_OSC = "NIH Other Significant Contributor";
    
    /**
      * Checks if a given sponsor is in the hierarchy for a specific level.
      * @param sponsorCode the sponsor code.  Cannot be blank.
      * @param hierarchyName the hierarchy name.  Cannot be blank.
      * @param level the hierarchy level.  Must be between 1 and 10 inclusive.
      * @param levelName the level name.  Cannot be blank.
      * @return returns true if the sponsor is in the hierarchy
      * @throws IllegalArgumentException if the sponsorCode, hierarchyName, or levelName is blank
      * or if level is not between 1 and 10 inclusive
      */
    public boolean isSponsorInHierarchy(String sponsorCode, String hierarchyName, int level, String levelName);
    
    /**
      * Checks if a given sponsor is in the hierarchy for any level.
      * @param sponsorCode the sponsor code.  Cannot be blank.
      * @param hierarchyName the hierarchy name.  Cannot be blank.
      * @return returns true if the sponsor is in the hierarchy
      * @throws IllegalArgumentException if the sponsorCode, hierarchyName is blank
      */
    public boolean isSponsorInHierarchy(String sponsorCode, String hierarchyName);

    /**
      * Checks if a given sponsor is in the NIH Multiple PI hierarchy for any level.
      * @param sponsorCode the sponsor code.  Cannot be blank.
      * @return returns true if the sponsor code is a "NIH Multiple PI" type
      * @throws IllegalArgumentException if the sponsorCode is blank
      */
    public boolean isSponsorNihMultiplePi(String sponsorCode);
    
    /**
      * Checks if the sponsor code is a "NIH Other Significant Contributor" type.
      * @param sponsorCode the sponsor code.  Cannot be blank.
      * @return returns true if the sponsor code is a "NIH Other Significant Contributor" type
      * @throws IllegalArgumentException if the sponsorCode is blank
      */
    public boolean isSponsorNihOsc(String sponsorCode);
}
