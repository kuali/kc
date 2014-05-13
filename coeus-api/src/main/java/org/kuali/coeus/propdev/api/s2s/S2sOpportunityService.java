package org.kuali.coeus.propdev.api.s2s;


public interface S2sOpportunityService {

    /**
     * This method retrieves and S2S Opportunity by proposal number. The proposal number cannot be blank.
     * Will return null if non is found.
     *
     * @param proposalNumber the proposal number.  Cannot be blank.
     * @return the S2sOpportunity or null.
     * @throws java.lang.IllegalArgumentException if proposal number is blank
     */
    S2sOpportunityContract findS2SOpportunityByProposalNumber(String proposalNumber);
}
