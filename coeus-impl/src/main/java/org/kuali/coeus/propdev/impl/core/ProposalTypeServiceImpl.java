/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.propdev.impl.core;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("proposalTypeService")
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

	@Override
	public String getNewChangedOrCorrectedProposalTypeCode() {
		return getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT,
				ParameterConstants.DOCUMENT_COMPONENT, ProposalDevelopmentConstants.PropDevParameterConstants.PROPOSAL_TYPE_CODE_NEW_CHANGE_CORRECTED_PARM);
	}

	@Override
	public String getResubmissionChangedOrCorrectedProposalTypeCode() {
		return getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT,
				ParameterConstants.DOCUMENT_COMPONENT, ProposalDevelopmentConstants.PropDevParameterConstants.PROPOSAL_TYPE_CODE_RESUBMISSION_CHANGE_CORRECTED_PARM);
	}

	@Override
	public String getBudgetSowUpdateProposalTypeCode() {
		return getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT,
				ParameterConstants.DOCUMENT_COMPONENT, ProposalDevelopmentConstants.PropDevParameterConstants.PROPOSAL_TYPE_CODE_BUDGET_SOW_UPDATE_PARM);
	}

	@Override
	public String getRenewalChangedOrCorrectedProposalTypeCode() {
		return getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT,
				ParameterConstants.DOCUMENT_COMPONENT, ProposalDevelopmentConstants.PropDevParameterConstants.PROPOSAL_TYPE_CODE_RENEWAL_CHANGE_CORRECTED_PARM);
	}

	@Override
	public String getSupplementChangedOrCorrectedProposalTypeCode() {
		return getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT,
				ParameterConstants.DOCUMENT_COMPONENT, ProposalDevelopmentConstants.PropDevParameterConstants.PROPOSAL_TYPE_CODE_SUPPLEMENT_CHANGE_CORRECTED_PARM);
	}

	@Override
	public String getDefaultSubmissionTypeCode(String proposalTypeCode) {
		String defaultS2sSubmissionTypeCode = getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, KeyConstants.S2S_SUBMISSIONTYPE_APPLICATION);
		if(StringUtils.equals(proposalTypeCode,getNewChangedOrCorrectedProposalTypeCode())
				|| StringUtils.equals(proposalTypeCode,getResubmissionChangedOrCorrectedProposalTypeCode())
				|| StringUtils.equals(proposalTypeCode,getSupplementChangedOrCorrectedProposalTypeCode())
				|| StringUtils.equals(proposalTypeCode,getRenewalChangedOrCorrectedProposalTypeCode())) {
			defaultS2sSubmissionTypeCode = getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT,
					ParameterConstants.DOCUMENT_COMPONENT, ProposalDevelopmentConstants.PropDevParameterConstants.CHANGE_CORRECTED_CODE);
		} else if(StringUtils.equals(proposalTypeCode,getPreProposalProposalTypeCode())) {
			defaultS2sSubmissionTypeCode = getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT,
					ParameterConstants.ALL_COMPONENT, ProposalDevelopmentConstants.PropDevParameterConstants.S2S_SUBMISSION_TYPE_CODE_PREAPPLICATION);
		}
		return defaultS2sSubmissionTypeCode;
	}

    @Override
    public String getPreProposalProposalTypeCode() {
        return getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT,
                ParameterConstants.DOCUMENT_COMPONENT, ProposalDevelopmentConstants.PropDevParameterConstants.PROPOSAL_TYPE_CODE_PRE_PROPOSAL_PARM);
    }

    public ParameterService getParameterService() {
		return parameterService;
	}

	public void setParameterService(ParameterService parameterService) {
		this.parameterService = parameterService;
	}

}
