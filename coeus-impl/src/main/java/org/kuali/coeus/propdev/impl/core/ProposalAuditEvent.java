package org.kuali.coeus.propdev.impl.core;

public class ProposalAuditEvent {

	private ProposalDevelopmentDocument proposalDevelopmentDocument;

	public ProposalAuditEvent(ProposalDevelopmentDocument proposalDevelopmentDocument) {
		this.proposalDevelopmentDocument = proposalDevelopmentDocument;
	}

	public ProposalDevelopmentDocument getProposalDevelopmentDocument() {
		return proposalDevelopmentDocument;
	}

	public void setProposalDevelopmentDocument(
			ProposalDevelopmentDocument proposalDevelopmentDocument) {
		this.proposalDevelopmentDocument = proposalDevelopmentDocument;
	}
	
}
