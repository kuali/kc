package org.kuali.coeus.propdev.api.location;

public interface ProposalSiteService {

    /**
     * This method retrieves duns number from the proposal's Applicant Organization from the proposal number.
     * If the proposal is not found this method will return null.
     *
     * @param proposalNumber of the proposal to search for.  cannot be blank.
     * @return the duns number or null.
     * @throws java.lang.IllegalArgumentException if the proposalNumber is blank
     */
    String getProposalDunsNumber(String proposalNumber);
}
