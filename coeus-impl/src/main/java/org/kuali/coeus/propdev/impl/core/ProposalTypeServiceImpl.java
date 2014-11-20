package org.kuali.coeus.propdev.impl.core;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("proposalTypeService")
public class ProposalTypeServiceImpl implements ProposalTypeService {
	
    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;
    
	@Override
	public String getResubmissionProposalTypeCode() {
		return getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT,
                ParameterConstants.DOCUMENT_COMPONENT, ProposalDevelopmentConstants.PropDevParameterConstants.PROPOSAL_TYPE_CODE_RESUBMISSION_PARM);
	}

	@Override
	public String getContinuationProposalTypeCode() {
		return getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT,
                ParameterConstants.DOCUMENT_COMPONENT, ProposalDevelopmentConstants.PropDevParameterConstants.PROPOSAL_TYPE_CODE_CONTINUATION_PARM);
	}

	@Override
	public String getRevisionProposalTypeCode() {
		return getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT,
                ParameterConstants.DOCUMENT_COMPONENT, ProposalDevelopmentConstants.PropDevParameterConstants.PROPOSAL_TYPE_CODE_REVISION_PARM);
	}
	
	@Override
	public String getS2SSubmissionChangeCorrectedCode() {
		return getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT,
                ParameterConstants.DOCUMENT_COMPONENT, ProposalDevelopmentConstants.PropDevParameterConstants.CHANGE_CORRECTED_CODE);
	}
	
	@Override
	public String getNewProposalTypeCode() {
		return getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT,
                ParameterConstants.DOCUMENT_COMPONENT, ProposalDevelopmentConstants.PropDevParameterConstants.PROPOSAL_TYPE_CODE_NEW_PARM);
	}
	
	@Override
	public String getRenewProposalTypeCode() {
		return getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT,
                ParameterConstants.DOCUMENT_COMPONENT, ProposalDevelopmentConstants.PropDevParameterConstants.PROPOSAL_TYPE_CODE_RENEWAL_PARM);
	}
	
	@Override
    public boolean isProposalTypeRenewalRevisionContinuation(String proposalTypeCode) {
         
        return !StringUtils.isEmpty(proposalTypeCode) &&
               (proposalTypeCode.equals(getRenewProposalTypeCode()) ||
                proposalTypeCode.equals(getRevisionProposalTypeCode()) ||
                proposalTypeCode.equals(getContinuationProposalTypeCode()));
    }

	public ParameterService getParameterService() {
		return parameterService;
	}

	public void setParameterService(ParameterService parameterService) {
		this.parameterService = parameterService;
	}

}
