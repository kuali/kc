package org.kuali.coeus.instprop.api.sponsor;

public interface InstPropSponsorService {

    /**
     * This method updates the sponsor proposal number on an institutional proposal only if the institutional proposal
     * does not currently have an sponsor proposal number set. If the institutional proposal is not found then nothing
     * is updated.
     *
     * @param institutionalProposalId the institutional proposal id.  cannot be null.
     * @param sponsorProposalNumber the sponsor proposal number to set.  cannot be blank.
     * @throws java.lang.IllegalArgumentException if the institutionalProposalNumber is null or sponsorProposalNumber is blank
     */
    void updateSponsorProposalNumber(Long institutionalProposalId, String sponsorProposalNumber);
}
