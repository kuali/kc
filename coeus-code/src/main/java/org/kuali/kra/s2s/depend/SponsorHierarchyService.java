package org.kuali.kra.s2s.depend;


public interface SponsorHierarchyService {

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
}
