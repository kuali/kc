package org.kuali.coeus.propdev.api.core;

public interface SubmissionInfoService {

    /**
     * This method retrieves the federal id.  Bases on certain conditions the federal id can come from a variety
     * of places.
     *
     * The logic is as follows:
     *
     * If the proposal type is a Renewal, Revision, or Continuation and the DevelopmentProposal
     * contains a non-blank sponsor proposal number then that is the federal id.
     *
     * If the proposal type is a Renewal, Revision, or Continuation and the DevelopmentProposal
     * contains a blank sponsor proposal number and an award exists and the system is configured to
     * get the federal id from the award and the contains a non-blank sponsor award number then
     * that is the federal id .
     *
     * If the proposal type is a Renewal, Revision, or Continuation under any other conditions than what
     * is listed above the federal id is null
     *
     * If the proposal type is a Resubmission and the DevelopmentProposal
     * contains a non-blank sponsor proposal number then that is the federal id.
     *
     * If the proposal type is a Resubmission and the DevelopmentProposal contains a blank sponsor
     * proposal number and an institutional proposal and the institutional proposal contains a non-blank
     * sponsor proposal number then that is the federal id.
     *
     * If the proposal type is a Resubmission and the DevelopmentProposal contains a NSF sponsor then
     * the federal id is null.
     *
     * @param proposalNumber the proposal number.  cannot be blank.
     * @return the federal id or null
     * @throws java.lang.IllegalArgumentException if the proposal number is blank
     */
    String getFederalId(String proposalNumber);

    /**
     * Retrieves the grants.gov tracking id from an app submission.  The app submission is
     * retrieves from the development proposal object that is associated with an institutional
     * proposal.  The institutional proposal is retrieved from the passed in proposalId.
     *
     * @param proposalId the institutional proposal's id.  cannot be null
     * @return grants.gov tracking id or null
     * @throws java.lang.IllegalArgumentException if the proposalId is null
     */
    String getGgTrackingIdFromProposal(Long proposalId);

    /**
     * Gets the sponsor award number from a current award matching the currentAwardNumber.
     *
     * @param currentAwardNumber the current award number.  cannot be blank.
     * @return sponsor award number or null
     * @throws java.lang.IllegalArgumentException if the currentAwardNumber is blank
     */
    String getProposalCurrentAwardSponsorAwardNumber(String currentAwardNumber);

    /**
     * Gets the sponsor proposal number from the continued from institutional proposal.
     *
     * @param continuedFromProposalNumber the continued from proposal number. cannot be blank.
     * @return sponsor proposal number or null
     * @throws java.lang.IllegalArgumentException if the continuedFromProposalNumber is blank
     */
    String getProposalContinuedFromVersionSponsorProposalNumber(String continuedFromProposalNumber);

    /**
     * Gets the institutional proposal id from the continued from institutional proposal.
     *
     * @param continuedFromProposalNumber the continued from proposal number. cannot be blank.
     * @return institutional proposal id or null
     * @throws java.lang.IllegalArgumentException if the continuedFromProposalNumber is blank
     */
    Long getProposalContinuedFromVersionProposalId(String continuedFromProposalNumber);
}
