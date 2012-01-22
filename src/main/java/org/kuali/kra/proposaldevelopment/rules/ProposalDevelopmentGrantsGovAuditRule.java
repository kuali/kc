/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.rules;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.service.S2SService;
import org.kuali.kra.service.SponsorService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;
public class ProposalDevelopmentGrantsGovAuditRule  implements DocumentAuditRule{
	private static final Log LOG = LogFactory.getLog(ProposalDevelopmentGrantsGovAuditRule.class);
    private ParameterService parameterService;
    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KraServiceLocator.getService(ParameterService.class);        
        }
        return this.parameterService;
    }
    
    /**
     * @see org.kuali.rice.krad.rules.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.rice.krad.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;

        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)document;
        List<AuditError> auditErrors = new ArrayList<AuditError>();
		if (proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity() != null && (proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getS2sSubmissionTypeCode() == null || StringUtils.equalsIgnoreCase(proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getS2sSubmissionTypeCode().toString(), ""))) {            
            valid = false;            
            auditErrors.add(new AuditError(Constants.S2S_SUBMISSIONTYPE_CODE_KEY, KeyConstants.ERROR_NOT_SELECTED_SUBMISSION_TYPE, Constants.GRANTS_GOV_PAGE + "." + Constants.GRANTS_GOV_PANEL_ANCHOR));            
        }
        
        if (proposalDevelopmentDocument.getDevelopmentProposal().getProposalTypeCode() != null && proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity() != null && proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getOpportunityId() != null && proposalDevelopmentDocument.getDevelopmentProposal().getProposalTypeCode().equals(getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_REVISION)) && proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getRevisionCode() == null) {
            valid &= false;
            auditErrors.add(new AuditError("document.developmentProposalList[0].s2sOpportunity.revisionCode", KeyConstants.ERROR_IF_PROPOSALTYPE_IS_REVISION, Constants.GRANTS_GOV_PAGE + "." + Constants.GRANTS_GOV_PANEL_ANCHOR));
        }
        if((getSponsorService().isSponsorNihOsc(proposalDevelopmentDocument.getDevelopmentProposal())|| 
                    getSponsorService().isSponsorNihMultiplePi(proposalDevelopmentDocument.getDevelopmentProposal()))&& 
                    proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity()!=null &&
                    proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getCompetetionId()!=null &&
                    proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getCompetetionId().equals("ADOBE-FORMS-A")){
        	auditErrors.add(new AuditError("document.developmentProposalList[0].s2sOpportunity.competetionId", KeyConstants.ERROR_IF_COMPETITION_ID_IS_INVALID, Constants.GRANTS_GOV_PAGE + "." + Constants.GRANTS_GOV_PANEL_ANCHOR));
        	valid= false;
        }
        if (auditErrors.size() > 0) {
            KNSGlobalVariables.getAuditErrorMap().put("grantsGovAuditWarnings", new AuditCluster(Constants.GRANTS_GOV_OPPORTUNITY_PANEL, auditErrors, Constants.AUDIT_ERRORS));
        }
        
        if (proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity() != null){
            try {
                valid &= getS2sValidatorService().validateApplication(proposalDevelopmentDocument,auditErrors);
            }catch (S2SException e) {
                valid = false;
                LOG.error("Unknown error while validating grants.gov data", e);
                throw new RuntimeException(e);
            }
        }
        return valid;
    }
    private SponsorService getSponsorService() {
        return KraServiceLocator.getService(SponsorService.class);
    }
    private S2SService getS2sValidatorService() {
        return KraServiceLocator.getService(S2SService.class);
    }
   
}
