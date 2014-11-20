package org.kuali.coeus.propdev.impl.core;

public interface ProposalTypeService {
	
	public String getResubmissionProposalTypeCode();
	
	public String getContinuationProposalTypeCode();
	
	public String getRevisionProposalTypeCode();
	
	public String getS2SSubmissionChangeCorrectedCode();
	
	public String getNewProposalTypeCode();
	
	public String getRenewProposalTypeCode();
	
	public boolean isProposalTypeRenewalRevisionContinuation(String proposalTypeCode);

}
