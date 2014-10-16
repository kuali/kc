package org.kuali.coeus.propdev.impl.core;

public class SubmitToSponsorEvent {
	
	public SubmitToSponsorEvent(ProposalDevelopmentDocument proposalDevelopmentDocument) {
		this.proposalDevelopmentDocument = proposalDevelopmentDocument;
	}
	
	private ProposalDevelopmentDocument proposalDevelopmentDocument;

	public ProposalDevelopmentDocument getProposalDevelopmentDocument() {
		return proposalDevelopmentDocument;
	}

	public void setProposalDevelopmentDocument(ProposalDevelopmentDocument proposalDevelopmentDocument) {
		this.proposalDevelopmentDocument = proposalDevelopmentDocument;
	}

}
