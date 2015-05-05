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

import java.util.ArrayList;
import java.util.List;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRule;
import org.kuali.coeus.common.framework.ruleengine.KcEventMethod;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.kuali.coeus.propdev.impl.datavalidation.ProposalDevelopmentDataValidationConstants.*;
import static org.kuali.coeus.propdev.impl.datavalidation.ProposalDevelopmentDataValidationConstants.ATTACHMENT_PAGE_ID;
import static org.kuali.coeus.propdev.impl.datavalidation.ProposalDevelopmentDataValidationConstants.ATTACHMENT_PROPOSAL_SECTION_ID;

@KcBusinessRule("submitToSponsorRule")
public class SubmitToSponsorRule {
	
	@Autowired
	@Qualifier("parameterService")
	ParameterService parameterService;
	
	@Autowired
	@Qualifier("globalVariableService")
	GlobalVariableService globalVariableService;
	
	@KcEventMethod
    public Boolean validateIncompleteAttachments(SubmitToSponsorEvent submitToSponsorEvent) {
		Boolean valid = true;
        Boolean auditIncompleteAttachments = getParameterService().getParameterValueAsBoolean(ProposalDevelopmentDocument.class, ProposalDevelopmentUtils.AUDIT_INCOMPLETE_PROPOSAL_ATTATCHMENTS_PARM);
        if(auditIncompleteAttachments == null) {
            throw new RuntimeException("System parameter AUDIT_INCOMPLETE_PROPOSAL_ATTACHMENTS is missing or invalid"); 
        }
        
        if(!auditIncompleteAttachments) {
			valid &= validateAttachments(submitToSponsorEvent.getProposalDevelopmentDocument().getDevelopmentProposal().getNarratives(),
					ATTACHMENT_PROPOSAL_SECTION_NAME, NARRATIVES_STATUS_KEY,KeyConstants.ERROR_PROPOSAL_ATTACHMENT_NOT_COMPLETE,ATTACHMENT_PROPOSAL_SECTION_ID);
			valid &= validateAttachments(submitToSponsorEvent.getProposalDevelopmentDocument().getDevelopmentProposal().getInstituteAttachments(),
					ATTACHMENT_INTERNAL_SECTION_NAME, INSTITUTE_ATTACHMENTS_STATUS_KEY,KeyConstants.ERROR_INTERNAL_ATTACHMENT_NOT_COMPLETE,ATTACHMENT_INTERNAL_SECTION_ID);
        }

        return valid;
    }

	protected Boolean validateAttachments(List<Narrative> narratives, String sectionName, String errorKey, String messageKey, String sectionId) {
		int index = 0;
		for(Narrative attachment : narratives) {
            if(attachment.getModuleStatusCode().equals(Constants.NARRATIVE_MODULE_STATUS_INCOMPLETE)) {
                getAuditErrors(sectionName).add(new AuditError(String.format(errorKey,index),
                        messageKey, ATTACHMENT_PAGE_ID+"."+sectionId));
                return false;
            }
            index++;
        }
		return true;
	}

	protected List<AuditError> getAuditErrors(String sectionName ) {
		List<AuditError> auditErrors = new ArrayList<AuditError>();
		String clusterKey = ATTACHMENT_PAGE_NAME + "." + sectionName;
		if (!getGlobalVariableService().getAuditErrorMap().containsKey(clusterKey)) {
			getGlobalVariableService().getAuditErrorMap().put(clusterKey, new AuditCluster(clusterKey, auditErrors, AUDIT_ERRORS));
		}
		else {
			auditErrors = getGlobalVariableService().getAuditErrorMap().get(clusterKey).getAuditErrorList();
		}

		return auditErrors;
	}

	public ParameterService getParameterService() {
		return parameterService;
	}

	public void setParameterService(ParameterService parameterService) {
		this.parameterService = parameterService;
	}

	public GlobalVariableService getGlobalVariableService() {
		return globalVariableService;
	}

	public void setGlobalVariableService(GlobalVariableService globalVariableService) {
		this.globalVariableService = globalVariableService;
	}

}
