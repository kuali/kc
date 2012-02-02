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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;
import org.kuali.kra.s2s.service.S2SUtilService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;
import org.kuali.rice.krms.api.KrmsApiServiceLocator;
import org.kuali.rice.krms.api.engine.Engine;
import org.kuali.rice.krms.api.engine.EngineResults;
import org.kuali.rice.krms.api.engine.Facts;
import org.kuali.rice.krms.api.engine.SelectionCriteria;
import org.kuali.rice.krms.framework.type.ValidationActionTypeService;

/**
 * This class processes audit rules (warnings) for the Sponsor & Program Information related
 * data of the ProposalDevelopmenDocument.
 */
public class ProposalDevelopmentProposalRequiredFieldsAuditRule implements DocumentAuditRule {    
    
    private ParameterService parameterService;
    private ProposalDevelopmentService proposalDevelopmentService;
    private S2SUtilService s2sUtilService;
    
    /**
     * @see org.kuali.rice.krad.rules.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.rice.krad.document.Document)
     */
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
                ggTrackingId = getS2sUtilService().getGgTrackingIdFromProposal(institutionalProposal);
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
        
        //valid &= validateKrmsRules(proposalDevelopmentDocument);

        return valid;
    }
    
    protected boolean validateKrmsRules(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        Map<String, String> contextQualifiers = new HashMap<String, String>();
        contextQualifiers.put("namespaceCode", "KC-PD");
        contextQualifiers.put("name", "Proposal Development Validation");
        SelectionCriteria selectionCriteria = SelectionCriteria.createCriteria(null, contextQualifiers,
                Collections.singletonMap("unitNumber", proposalDevelopmentDocument.getLeadUnitNumber()));

        Facts.Builder factsBuilder = Facts.Builder.create();

        // Probably will want to come up w/something more generic here
        factsBuilder.addFact("Sponsor Code", proposalDevelopmentDocument.getDevelopmentProposal().getSponsorCode());

        Engine engine = KrmsApiServiceLocator.getEngine();
        EngineResults results = engine.execute(selectionCriteria, factsBuilder.build(), null);
        // Translate these into audit errors
        
        // comma-delimited list of error & warning messages
        String errors = (String) results.getAttribute(ValidationActionTypeService.VALIDATIONS_ACTION_ATTRIBUTE);
        
        
        return StringUtils.isEmpty(errors);
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
            this.parameterService = KraServiceLocator.getService(ParameterService.class);        
        }
        return this.parameterService;
    }   
    protected ProposalDevelopmentService getProposalDevelopmentService() {
        if (this.proposalDevelopmentService == null) {
            this.proposalDevelopmentService = KraServiceLocator.getService(ProposalDevelopmentService.class);
        }
        return this.proposalDevelopmentService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public void setProposalDevelopmentService(ProposalDevelopmentService proposalDevelopmentService) {
        this.proposalDevelopmentService = proposalDevelopmentService;
    }

    protected S2SUtilService getS2sUtilService() {
        if (s2sUtilService == null) {
            s2sUtilService = KraServiceLocator.getService(S2SUtilService.class);
        }
        return s2sUtilService;
    }

    public void setS2sUtilService(S2SUtilService s2sUtilService) {
        this.s2sUtilService = s2sUtilService;
    }

}
