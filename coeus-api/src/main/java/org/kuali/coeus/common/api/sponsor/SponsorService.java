package org.kuali.coeus.common.api.sponsor;

public interface SponsorService {

    /**
     * This method will retrieves a {@link SponsorContract} by sponsorCode.  The sponsorCode cannot be blank.
     * @param sponsorCode the sponsorCode.  Cannot be null.
     * @return the {@link SponsorContract} or null if not found.
     * @throws java.lang.IllegalArgumentException if the sponsorCode is blank
     */
    SponsorContract getSponsor(String sponsorCode);
}
