package org.kuali.coeus.propdev.impl.core;

public interface ProposalDevelopmentViewHelperService {
	
	public boolean requiresResubmissionPrompt(DevelopmentProposal developmentProposal, String resubmissionOption);
    public void setupLockRegions(ProposalDevelopmentDocumentForm form);

}
