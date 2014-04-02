/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

import java.util.ArrayList;
import java.util.List;

/**
 * This class processes audit rules (warnings) for the Sponsor & Program Information related
 * data of the ProposalDevelopmenDocument.
 */
public class ProposalDevelopmentProposalRequiredFieldsAuditRule implements DocumentAuditRule {    
    
    private ParameterService parameterService;
    private ProposalDevelopmentService proposalDevelopmentService;

    @Override
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;

        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)document;
        DevelopmentProposal proposal = proposalDevelopmentDocument.getDevelopmentProposal();
        List<AuditError> auditErrors = new ArrayList<AuditError>();

        if (StringUtils.equalsIgnoreCase(proposal.getSponsorCode(),Constants.NIH_SPONSOR_CODE) && proposalDevelopmentDocument.getDevelopmentProposal().getTitle().length() > 81){
            valid = false;
            auditErrors.add(new AuditError(Constants.PROJECT_TITLE_KEY, KeyConstants.ERROR_NIH_SPONSOR_PROJECT_TITLE_LENGTH, Constants.PROPOSAL_PAGE + "." + Constants.REQUIRED_FIELDS_PANEL_ANCHOR));
        }
        InstitutionalProposal institutionalProposal = getProposalDevelopmentService().getProposalContinuedFromVersion(proposalDevelopmentDocument);
        String changeCorrectedType = getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, "s2s.submissiontype.changedCorrected");
        if (proposal.getS2sOpportunity() != null && isProposalTypeNew(proposal.getProposalTypeCode())
                && StringUtils.equals(proposal.getS2sOpportunity().getS2sSubmissionTypeCode(), changeCorrectedType)) {
            String ggTrackingId = null;
            if (institutionalProposal != null) {
                ggTrackingId = getProposalDevelopmentService().getGgTrackingIdFromProposal(institutionalProposal);
            }
            if (StringUtils.isBlank(proposal.getSponsorProposalNumber())
                    && StringUtils.isBlank(ggTrackingId)) {
                valid = false;
                auditErrors.add(new AuditError(Constants.ORIGINAL_PROPOSAL_ID_KEY,
                        KeyConstants.ERROR_PROPOSAL_REQUIRE_ID_CHANGE_APP, Constants.PROPOSAL_PAGE + "." + Constants.REQUIRED_FIELDS_PANEL_ANCHOR));
            }
        }
        
        if (auditErrors.size() > 0) {
            KNSGlobalVariables.getAuditErrorMap().put("requiredFieldsAuditErrors", new AuditCluster(Constants.REQUIRED_FIELDS_PANEL_NAME, auditErrors, Constants.AUDIT_ERRORS));
        }

        return valid;
    }
    
    /**
     * Is the Proposal Type set to New?
     * @param proposalTypeCode proposal type code
     * @return true or false
     */
    private boolean isProposalTypeNew(String proposalTypeCode) {
        String proposalTypeCodeNew = getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_NEW);
         
        return !StringUtils.isEmpty(proposalTypeCode) &&
               (proposalTypeCode.equals(proposalTypeCodeNew));
    }     
    
    
    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return this.parameterService;
    }   
    protected ProposalDevelopmentService getProposalDevelopmentService() {
        if (this.proposalDevelopmentService == null) {
            this.proposalDevelopmentService = KcServiceLocator.getService(ProposalDevelopmentService.class);
        }
        return this.proposalDevelopmentService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public void setProposalDevelopmentService(ProposalDevelopmentService proposalDevelopmentService) {
        this.proposalDevelopmentService = proposalDevelopmentService;
    }

}
