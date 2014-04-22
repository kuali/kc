package org.kuali.coeus.common.api.sponsor;

public interface SponsorService {

    /**
     * This method will retrieves a {@link SponsorContract} by sponsorCode.  The sponsorCode cannot be blank.
     * @param sponsorCode the sponsorCode.  Cannot be null.
     * @return the {@link SponsorContract} or null if not found.
     * @throws java.lang.IllegalArgumentException if the sponsorCode is blank
     */
    SponsorContract getSponsor(String sponsorCode);

    /**
     * This method will retrieves a sponsor name by sponsorCode.  The sponsorCode cannot be blank.
     * @param sponsorCode the sponsorCode.  Cannot be null.
     * @return the sponsor name or null if not found.
     * @throws java.lang.IllegalArgumentException if the sponsorCode is blank
     */
    String getSponsorName(String sponsorCode);

    /**
     * This method determines whether a sponsor object is valid to be a sponsor.  A valid sponsor
     * is a non-null active sponsor.  The sponsor argument can be null.
     *
     * @param sponsor the sponsor to check
     * @return true if valid, false if not valid
     */
    boolean isValidSponsor(SponsorContract sponsor);
}
