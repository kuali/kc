package org.kuali.coeus.propdev.api.core;

public interface SubmissionInfoService {

    String getFederalId(String proposalNumber);

    String getGgTrackingIdFromProposal(Long proposalId);

    String getProposalCurrentAwardSponsorAwardNumber(String currentAwardNumber);

    String getProposalContinuedFromVersionSponsorProposalNumber(String continuedFromProposalNumber);

    Long getProposalContinuedFromVersionProposalId(String continuedFromProposalNumber);
}
