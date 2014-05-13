package org.kuali.coeus.propdev.api.s2s;


public interface S2sApplicationService {

    /**
     * This method retrieves and S2S Application by proposal number. The proposal number cannot be blank.
     * Will return null if non is found.
     *
     * @param proposalNumber the proposal number.  Cannot be blank.
     * @return the S2sApplication or null.
     * @throws java.lang.IllegalArgumentException if proposal number is blank
     */
    S2sApplicationContract findS2sApplicationByProposalNumber(String proposalNumber);
}
