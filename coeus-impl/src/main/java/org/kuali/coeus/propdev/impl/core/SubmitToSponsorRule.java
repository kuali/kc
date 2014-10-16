package org.kuali.coeus.propdev.impl.core;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRule;
import org.kuali.coeus.common.framework.ruleengine.KcEventMethod;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.coreservice.api.parameter.Parameter;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

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
            //incomplete attachments were allowed to enter workflow, but must be set to complete before submit to sponsor.
            List<Narrative> attachments = submitToSponsorEvent.getProposalDevelopmentDocument().getDevelopmentProposal().getInstituteAttachments();
            for(Narrative attachment : attachments) {
                if(attachment.getModuleStatusCode().equals(Constants.NARRATIVE_MODULE_STATUS_INCOMPLETE)) {
                    valid &= false;
                    break;
                }
            }
        }

        return valid;
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
