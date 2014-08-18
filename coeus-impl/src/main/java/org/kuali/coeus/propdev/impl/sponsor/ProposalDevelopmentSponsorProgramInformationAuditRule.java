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
package org.kuali.coeus.propdev.impl.sponsor;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.propdev.api.core.SubmissionInfoService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class processes audit rules (warnings) for the Sponsor & Program Information related
 * data of the ProposalDevelopmenDocument.
 */
public class ProposalDevelopmentSponsorProgramInformationAuditRule implements DocumentAuditRule { 
    
    private ParameterService parameterService;
    private BusinessObjectService businessObjectService;
    private SubmissionInfoService submissionInfoService;
    
    @Override
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;

        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)document;
        DevelopmentProposal proposal = proposalDevelopmentDocument.getDevelopmentProposal();
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        

        //The Proposal Deadline Date should return a warning during validation for the
        //following conditions: a) if the date entered is older than the current date,
        //or b) if there is no data entered.
        if (proposal.getDeadlineDate() == null) {
            auditErrors.add(new AuditError(Constants.DEADLINE_DATE_KEY, KeyConstants.WARNING_EMPTY_DEADLINE_DATE, Constants.PROPOSAL_PAGE + "." + Constants.SPONSOR_PROGRAM_INFORMATION_PANEL_ANCHOR));
        } else if (proposal.getDeadlineDate().before(new Date(System.currentTimeMillis()))) {
            auditErrors.add(new AuditError(Constants.DEADLINE_DATE_KEY, KeyConstants.WARNING_PAST_DEADLINE_DATE, Constants.PROPOSAL_PAGE + "." + Constants.SPONSOR_PROGRAM_INFORMATION_PANEL_ANCHOR));
        }
        
        if (auditErrors.size() > 0) {
            KNSGlobalVariables.getAuditErrorMap().put("sponsorProgramInformationAuditWarnings", new AuditCluster(Constants.SPONSOR_PROGRAM_INFORMATION_PANEL_NAME, auditErrors, Constants.AUDIT_WARNINGS));
            valid &= false;
        }
        
        auditErrors = new ArrayList<AuditError>();
        
        if (proposal.getS2sOpportunity() != null) {
            if (proposal.getS2sOpportunity().getOpportunityId() != null && proposal.getProgramAnnouncementNumber() != null 
                    && !StringUtils.equalsIgnoreCase(proposal.getS2sOpportunity().getOpportunityId(), proposal.getProgramAnnouncementNumber())) {
                valid &= false;
                auditErrors.add(new AuditError(Constants.OPPORTUNITY_ID_KEY, KeyConstants.ERROR_OPPORTUNITY_ID_DIFFER , Constants.PROPOSAL_PAGE + "." + Constants.SPONSOR_PROGRAM_INFORMATION_PANEL_ANCHOR));
            }
            if (proposal.getS2sOpportunity().getCfdaNumber() != null && proposal.getCfdaNumber() != null 
                    && !StringUtils.equalsIgnoreCase(proposal.getS2sOpportunity().getCfdaNumber(), proposal.getCfdaNumber())) {
                valid &= false;
                auditErrors.add(new AuditError(Constants.CFDA_NUMBER_KEY, KeyConstants.ERROR_CFDA_NUMBER_DIFFER , Constants.PROPOSAL_PAGE + "." + Constants.SPONSOR_PROGRAM_INFORMATION_PANEL_ANCHOR));
            }
            if (proposal.getProgramAnnouncementTitle() == null 
                    || StringUtils.equalsIgnoreCase(proposal.getProgramAnnouncementTitle().trim(), "")) {
                valid &= false;
                auditErrors.add(new AuditError(Constants.OPPORTUNITY_TITLE_KEY, KeyConstants.ERROR_OPPORTUNITY_TITLE_DELETED , Constants.PROPOSAL_PAGE + "." + Constants.SPONSOR_PROGRAM_INFORMATION_PANEL_ANCHOR));
            }
            
            String federalIdComesFromAwardStr = null;
            try {
                federalIdComesFromAwardStr = getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, "FEDERAL_ID_COMES_FROM_CURRENT_AWARD");
            } catch (Exception e) { }
            Boolean federalIdComesFromAward = federalIdComesFromAwardStr != null 
                                            && federalIdComesFromAwardStr.equalsIgnoreCase("Y");
            String sponsorAwardNumber = null;
            if (StringUtils.isNotBlank(proposal.getCurrentAwardNumber())) {
                sponsorAwardNumber = getSubmissionInfoService().getProposalCurrentAwardSponsorAwardNumber(proposal.getCurrentAwardNumber());
            }
            if (isProposalTypeRenewalRevisionContinuation(proposal.getProposalTypeCode()) 
                    && !(StringUtils.isNotBlank(proposal.getSponsorProposalNumber())
                    || (StringUtils.isNotBlank(sponsorAwardNumber) && federalIdComesFromAward))) {
                valid = false;
                auditErrors.add(new AuditError(Constants.SPONSOR_PROPOSAL_KEY, KeyConstants.ERROR_PROPOSAL_REQUIRE_PRIOR_AWARD, Constants.PROPOSAL_PAGE + "." + Constants.SPONSOR_PROGRAM_INFORMATION_PANEL_ANCHOR));
            }
            String sponsorProposalNumber = null;
            if (StringUtils.isNotBlank(proposal.getContinuedFrom())) {
                sponsorProposalNumber = getSubmissionInfoService().getProposalContinuedFromVersionSponsorProposalNumber(proposal.getContinuedFrom());
            }
            if (isProposalTypeResubmission(proposal.getProposalTypeCode())
                    && StringUtils.isBlank(proposal.getSponsorProposalNumber())
                    && (StringUtils.isBlank(sponsorProposalNumber))) {
                valid = false;
                auditErrors.add(new AuditError(Constants.SPONSOR_PROPOSAL_KEY, KeyConstants.ERROR_PROPOSAL_REQUIRE_PRIOR_AWARD_FOR_RESUBMIT, Constants.PROPOSAL_PAGE + "." + Constants.SPONSOR_PROGRAM_INFORMATION_PANEL_ANCHOR));
            }            
        }
        
        if (auditErrors.size() > 0) {
            KNSGlobalVariables.getAuditErrorMap().put("sponsorProgramInformationAuditErrors", new AuditCluster(Constants.SPONSOR_PROGRAM_INFORMATION_PANEL_NAME, auditErrors, Constants.GRANTSGOV_ERRORS));
        }
        auditErrors = new ArrayList<AuditError>();
        
        if (!StringUtils.isEmpty(proposal.getPrimeSponsorCode())) {
            Map<String, String> primaryKeys = new HashMap<String, String>();
            primaryKeys.put("sponsorCode", proposal.getPrimeSponsorCode());
            Sponsor sp = (Sponsor) getBusinessObjectService().findByPrimaryKey(Sponsor.class, primaryKeys);
            if (sp == null) {
                auditErrors.add(new AuditError(Constants.PRIME_SPONSOR_KEY, KeyConstants.ERROR_EMPTY_PRIME_SPONSOR_ID, 
                        Constants.PROPOSAL_PAGE + "." + Constants.SPONSOR_PROGRAM_INFORMATION_PANEL_ANCHOR));
                valid &= false;
            }
        }
        
        if (auditErrors.size() > 0) {
            KNSGlobalVariables.getAuditErrorMap().put("sponsorProgramInformationAuditErrors", new AuditCluster(Constants.SPONSOR_PROGRAM_INFORMATION_PANEL_NAME, auditErrors, Constants.AUDIT_ERRORS));
            valid &= false;
        }

        return valid;
    }
    
    /**
     * Is the Proposal Type set to Renewal, Revision, or a Continuation?
     * @param proposalTypeCode proposal type code
     * @return true or false
     */
    private boolean isProposalTypeRenewalRevisionContinuation(String proposalTypeCode) {
        String proposalTypeCodeRenewal = getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_RENEWAL);
        String proposalTypeCodeRevision = getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_REVISION);
        String proposalTypeCodeContinuation = getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_CONTINUATION);
         
        return !StringUtils.isEmpty(proposalTypeCode) &&
               (proposalTypeCode.equals(proposalTypeCodeRenewal) ||
                proposalTypeCode.equals(proposalTypeCodeRevision) ||
                proposalTypeCode.equals(proposalTypeCodeContinuation));
    }
    
    /**
     * Is the Proposal Type set to Resubmission?
     * @param proposalTypeCode proposal type code
     * @return true or false
     */
    private boolean isProposalTypeResubmission(String proposalTypeCode) {
        String proposalTypeCodeResubmission = getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_RESUBMISSION);
         
        return !StringUtils.isEmpty(proposalTypeCode) &&
               (proposalTypeCode.equals(proposalTypeCodeResubmission));
    }

    protected SubmissionInfoService getSubmissionInfoService() {
        if (this.submissionInfoService == null) {
            this.submissionInfoService = KcServiceLocator.getService(SubmissionInfoService.class);
        }
        return this.submissionInfoService;
    }

    public void setSubmissionInfoService(SubmissionInfoService submissionInfoService) {
        this.submissionInfoService = submissionInfoService;
    }

    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return this.parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public BusinessObjectService getBusinessObjectService() {
        if (this.businessObjectService == null) {
            this.businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        }
        return this.businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}
