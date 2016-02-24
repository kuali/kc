/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.coeus.propdev.impl.s2s;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.common.api.sponsor.hierarchy.SponsorHierarchyService;
import org.kuali.coeus.s2sgen.api.generate.FormValidationResult;
import org.kuali.coeus.s2sgen.api.generate.FormGeneratorService;
import org.kuali.coeus.s2sgen.api.core.AuditError;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

import java.util.ArrayList;
import java.util.List;

import static org.kuali.coeus.propdev.impl.datavalidation.ProposalDevelopmentDataValidationConstants.*;

public class ProposalDevelopmentGrantsGovAuditRule  implements DocumentAuditRule{

    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentGrantsGovAuditRule.class);

    private ParameterService parameterService;
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
    
    @Override
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;

        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)document;
		if (proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity() != null && (proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getS2sSubmissionTypeCode() == null || StringUtils.equalsIgnoreCase(proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getS2sSubmissionTypeCode().toString(), ""))) {            
            valid = false;            
            getAuditErrors(S2S_PAGE_NAME,S2S_OPPORTUNITY_SECTION_NAME,"").add(new org.kuali.rice.krad.util.AuditError(S2S_SUBMISSIONTYPE_CODE_KEY, KeyConstants.ERROR_NOT_SELECTED_SUBMISSION_TYPE, S2S_PAGE_ID+"."+S2S_OPPORTUNITY_SECTION_ID));
        }
        
        if (proposalDevelopmentDocument.getDevelopmentProposal().getProposalTypeCode() != null && proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity() != null && proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getOpportunityId() != null && proposalDevelopmentDocument.getDevelopmentProposal().getProposalTypeCode().equals(getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_REVISION)) && proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getRevisionCode() == null) {
            valid &= false;
            getAuditErrors(S2S_PAGE_NAME,S2S_OPPORTUNITY_SECTION_NAME,"").add(new org.kuali.rice.krad.util.AuditError(REVISION_CODE_KEY, KeyConstants.ERROR_IF_PROPOSALTYPE_IS_REVISION, S2S_PAGE_ID+"."+S2S_OPPORTUNITY_SECTION_ID));
        }
        if((getSponsorHierarchyService().isSponsorNihOsc(proposalDevelopmentDocument.getDevelopmentProposal().getSponsorCode())||
                    getSponsorHierarchyService().isSponsorNihMultiplePi(proposalDevelopmentDocument.getDevelopmentProposal().getSponsorCode()))&&
                    proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity()!=null &&
                    proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getCompetetionId()!=null &&
                    proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getCompetetionId().equals("ADOBE-FORMS-A")){
        	getAuditErrors(S2S_PAGE_NAME,S2S_OPPORTUNITY_SECTION_NAME,"").add(new org.kuali.rice.krad.util.AuditError(COMPETITION_ID, KeyConstants.ERROR_IF_COMPETITION_ID_IS_INVALID, S2S_PAGE_ID+"."+S2S_OPPORTUNITY_SECTION_ID));
        	valid= false;
        }

        if (proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity() != null){
            FormValidationResult result =  getS2sValidatorService().validateForms(proposalDevelopmentDocument);
            valid &= result.isValid();
            String provider = proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getS2sProvider().getDescription() + " ";
            setValidationErrorMessage(result.getErrors(),provider);

        }

        return valid;
    }

    protected void setValidationErrorMessage(List<AuditError> s2sErrors, String provider) {
        if (s2sErrors != null) {
            LOG.info("Error list size:" + s2sErrors.size() + s2sErrors.toString());

            for (AuditError error : s2sErrors) {
                if (StringUtils.equals(error.getLink(), ABSTRACTS_ATTACHMENTS)) {
                    getAuditErrors(ATTACHMENT_PAGE_NAME,ATTACHMENT_PROPOSAL_SECTION_NAME,provider).add(new org.kuali.rice.krad.util.AuditError(NARRATIVES_KEY,
                            Constants.GRANTS_GOV_GENERIC_ERROR_KEY, ATTACHMENT_PAGE_ID + "." + ATTACHMENT_PROPOSAL_SECTION_ID,
                            new String[]{error.getMessageKey()}));
                } else if (StringUtils.equals(error.getLink(), QUESTIONS)) {
                    getAuditErrors(QUESTIONNAIRE_PAGE_NAME,NO_SECTION_ID,provider).add(new org.kuali.rice.krad.util.AuditError(QUESTIONNAIRE_PAGE_ID,
                            Constants.GRANTS_GOV_GENERIC_ERROR_KEY, QUESTIONNAIRE_PAGE_ID,
                            new String[]{error.getMessageKey()}));
                } else if (StringUtils.equals(error.getLink(), KEY_PERSONNEL)) {
                    getAuditErrors(PERSONNEL_PAGE_NAME,NO_SECTION_ID,provider).add(new org.kuali.rice.krad.util.AuditError(PERSONNEL_PAGE_ID,
                            Constants.GRANTS_GOV_GENERIC_ERROR_KEY, PERSONNEL_PAGE_ID,
                            new String[]{error.getMessageKey()}));
                } else if (StringUtils.equals(error.getLink(), PROPOSAL_ORGANIZATION_LOCATION)){
                    getAuditErrors(ORGANIZATION_PAGE_NAME,APPLICANT_ORGANIZATION_SECTION_NAME, provider).add(new org.kuali.rice.krad.util.AuditError(ORGANIZATION_PAGE_ID,
                            Constants.GRANTS_GOV_GENERIC_ERROR_KEY, ORGANIZATION_PAGE_ID +"."+APPLICANT_ORGANIZATION_SECTION_ID,
                            new String[]{error.getMessageKey()}));
                } else {
                getAuditErrors(S2S_PAGE_NAME,S2S_OPPORTUNITY_SECTION_NAME,provider).add(new org.kuali.rice.krad.util.AuditError(S2S_PAGE_ID,
                        Constants.GRANTS_GOV_GENERIC_ERROR_KEY, S2S_PAGE_ID+"."+S2S_OPPORTUNITY_SECTION_ID,
                        new String[]{error.getMessageKey()}));
                }
            }
        }
    }

    private List<org.kuali.rice.krad.util.AuditError> getAuditErrors(String areaName, String sectionName, String provider) {
        List<org.kuali.rice.krad.util.AuditError> auditErrors = new ArrayList<org.kuali.rice.krad.util.AuditError>();
        String clusterKey = areaName + "." + sectionName;
        if (!GlobalVariables.getAuditErrorMap().containsKey(clusterKey+".s2s")) {
            GlobalVariables.getAuditErrorMap().put(clusterKey+".s2s", new AuditCluster(clusterKey, auditErrors, provider+AUDIT_ERRORS));
        }
        else {
            auditErrors = GlobalVariables.getAuditErrorMap().get(clusterKey+".s2s").getAuditErrorList();
        }

        return auditErrors;
    }

    private SponsorHierarchyService getSponsorHierarchyService() {
        return KcServiceLocator.getService(SponsorHierarchyService.class);
    }
    private FormGeneratorService getS2sValidatorService() {
        return KcServiceLocator.getService(FormGeneratorService.class);
    }
   
}
