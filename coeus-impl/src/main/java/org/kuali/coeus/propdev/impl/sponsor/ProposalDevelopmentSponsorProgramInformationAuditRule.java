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
package org.kuali.coeus.propdev.impl.sponsor;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.propdev.api.core.SubmissionInfoService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalTypeService;
import org.kuali.coeus.propdev.impl.s2s.S2sSubmissionType;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.kuali.coeus.propdev.impl.datavalidation.ProposalDevelopmentDataValidationConstants.*;

/**
 * This class processes audit rules (warnings) for the Sponsor &amp; Program Information related
 * data of the ProposalDevelopmenDocument.
 */
public class ProposalDevelopmentSponsorProgramInformationAuditRule implements DocumentAuditRule { 
    
    private ParameterService parameterService;
    private DataObjectService dataObjectService;
    private SubmissionInfoService submissionInfoService;
    private ProposalTypeService proposalTypeService;
    
    @Override
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;

        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)document;
        DevelopmentProposal proposal = proposalDevelopmentDocument.getDevelopmentProposal();

        //The Proposal Deadline Date should return a warning during validation for the
        //following conditions: a) if the date entered is older than the current date,
        //or b) if there is no data entered.
        if (proposal.getDeadlineDate() == null) {
            valid = false;
            getAuditErrors(SPONSOR_PROGRAM_INFO_PAGE_NAME,AUDIT_WARNINGS).add(new AuditError(DEADLINE_DATE_KEY, KeyConstants.WARNING_EMPTY_DEADLINE_DATE, SPONSOR_PROGRAM_INFO_PAGE_ID));
        } else if (isDeadlineDateBeforeCurrentDate(proposal)) {
            valid = false;
            getAuditErrors(SPONSOR_PROGRAM_INFO_PAGE_NAME,AUDIT_WARNINGS).add(new AuditError(DEADLINE_DATE_KEY, KeyConstants.WARNING_PAST_DEADLINE_DATE, SPONSOR_PROGRAM_INFO_PAGE_ID));
        }
        
        if (proposal.getS2sOpportunity() != null) {
            if (proposal.getS2sOpportunity().getOpportunityId() != null && proposal.getProgramAnnouncementNumber() != null 
                    && !StringUtils.equalsIgnoreCase(proposal.getS2sOpportunity().getOpportunityId(), proposal.getProgramAnnouncementNumber())) {
                valid &= false;
                getAuditErrors(SPONSOR_PROGRAM_INFO_PAGE_NAME,AUDIT_ERRORS).add(new AuditError(OPPORTUNITY_ID_KEY, KeyConstants.ERROR_OPPORTUNITY_ID_DIFFER, SPONSOR_PROGRAM_INFO_PAGE_ID));
            }
            if (proposal.getS2sOpportunity().getCfdaNumber() != null && proposal.getCfdaNumber() != null 
                    && !StringUtils.equalsIgnoreCase(proposal.getS2sOpportunity().getCfdaNumber(), proposal.getCfdaNumber())) {
                valid &= false;
                getAuditErrors(SPONSOR_PROGRAM_INFO_PAGE_NAME,AUDIT_ERRORS).add(new AuditError(CFDA_NUMBER_KEY, KeyConstants.ERROR_CFDA_NUMBER_DIFFER, SPONSOR_PROGRAM_INFO_PAGE_ID));
            }
            if (proposal.getProgramAnnouncementTitle() == null 
                    || StringUtils.equalsIgnoreCase(proposal.getProgramAnnouncementTitle().trim(), "")) {
                valid &= false;
                getAuditErrors(SPONSOR_PROGRAM_INFO_PAGE_NAME,AUDIT_ERRORS).add(new AuditError(OPPORTUNITY_TITLE_KEY, KeyConstants.ERROR_OPPORTUNITY_TITLE_DELETED, SPONSOR_PROGRAM_INFO_PAGE_ID));
            }

            if (proposal.isSponsorProgramAndDivCodeRequired()) {
                if (StringUtils.isEmpty(proposal.getAgencyDivisionCode())) {
                    valid &= false;
                    getAuditErrors(SPONSOR_PROGRAM_INFO_PAGE_NAME,AUDIT_ERRORS).add(new AuditError(SPONSOR_DIV_CODE_KEY, KeyConstants.ERROR_REQUIRED_SPONSOR_DIV_CODE, SPONSOR_PROGRAM_INFO_PAGE_ID, new String[] {proposal.getSponsorName()}));
                }
                if (StringUtils.isEmpty(proposal.getAgencyProgramCode())) {
                    getAuditErrors(SPONSOR_PROGRAM_INFO_PAGE_NAME,AUDIT_ERRORS).add(new AuditError(SPONSOR_PROGRAM_CODE_KEY, KeyConstants.ERROR_REQUIRED_SPONSOR_PROGRAM_CODE, SPONSOR_PROGRAM_INFO_PAGE_ID,new String[] {proposal.getSponsorName()}));
                }
            }

            if ( StringUtils.equals(proposal.getS2sOpportunity().getS2sSubmissionTypeCode(), S2sSubmissionType.CHANGE_CORRECTED_CODE) &&
                    StringUtils.equals(proposal.getProposalTypeCode(),getProposalTypeService().getNewProposalTypeCode()) &&
                    StringUtils.isEmpty(proposal.getPrevGrantsGovTrackingID())) {
                getAuditErrors(SPONSOR_PROGRAM_INFO_PAGE_NAME,AUDIT_ERRORS).add(new AuditError(PREV_GG_TRACKING_ID_KEY, KeyConstants.ERROR_REQUIRED_GG_TRACKING_ID, SPONSOR_PROGRAM_INFO_PAGE_ID));
                valid = false;
            }
            
            String federalIdComesFromAwardStr = null;
            try {
                federalIdComesFromAwardStr = getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, "FEDERAL_ID_COMES_FROM_CURRENT_AWARD");
            } catch (Exception e) {
                throw new RuntimeException("error retrieving FEDERAL_ID_COMES_FROM_CURRENT_AWARD parameter",e);
            }
            Boolean federalIdComesFromAward = federalIdComesFromAwardStr != null 
                                            && federalIdComesFromAwardStr.equalsIgnoreCase("Y");
            String sponsorAwardNumber = null;
            if (StringUtils.isNotBlank(proposal.getCurrentAwardNumber())) {
                sponsorAwardNumber = getSubmissionInfoService().getProposalCurrentAwardSponsorAwardNumber(proposal.getCurrentAwardNumber());
            }
            if (getProposalTypeService().isProposalTypeRenewalRevisionContinuation(proposal.getProposalTypeCode()) 
                    && !(StringUtils.isNotBlank(proposal.getSponsorProposalNumber())
                    || (StringUtils.isNotBlank(sponsorAwardNumber) && federalIdComesFromAward))) {
                valid = false;
                getAuditErrors(SPONSOR_PROGRAM_INFO_PAGE_NAME,AUDIT_ERRORS).add(new AuditError(SPONSOR_PROPOSAL_KEY, KeyConstants.ERROR_PROPOSAL_REQUIRE_PRIOR_AWARD, SPONSOR_PROGRAM_INFO_PAGE_ID));
            }
            String sponsorProposalNumber = null;
            if (StringUtils.isNotBlank(proposal.getContinuedFrom())) {
                sponsorProposalNumber = getSubmissionInfoService().getProposalContinuedFromVersionSponsorProposalNumber(proposal.getContinuedFrom());
            }
            if (isProposalTypeResubmission(proposal.getProposalTypeCode())
                    && StringUtils.isBlank(proposal.getSponsorProposalNumber())
                    && (StringUtils.isBlank(sponsorProposalNumber))) {
                valid = false;
                getAuditErrors(SPONSOR_PROGRAM_INFO_PAGE_NAME,AUDIT_ERRORS).add(new AuditError(SPONSOR_PROPOSAL_KEY, KeyConstants.ERROR_PROPOSAL_REQUIRE_PRIOR_AWARD_FOR_RESUBMIT, SPONSOR_PROGRAM_INFO_PAGE_ID));
            }            
        }

        if (!StringUtils.isEmpty(proposal.getPrimeSponsorCode())) {
            Sponsor sp = (Sponsor) getDataObjectService().find(Sponsor.class, proposal.getPrimeSponsorCode());
            if (sp == null) {
                getAuditErrors(DETAILS_PAGE_NAME,AUDIT_ERRORS).add(new AuditError(PRIME_SPONSOR_KEY, KeyConstants.ERROR_EMPTY_PRIME_SPONSOR_ID, DETAILS_PAGE_ID));
                valid &= false;
            }
        }


        return valid;
    }
    
    protected boolean isDeadlineDateBeforeCurrentDate(DevelopmentProposal proposal) {
    	DateTime deadlineDate = new DateTime(proposal.getDeadlineDate());
    	DateTime currentDate = new DateTime();
    	return DateTimeComparator.getDateOnlyInstance().compare(currentDate, deadlineDate) > 0;
    }
    
    /**
     * Is the Proposal Type set to Resubmission?
     * @param proposalTypeCode proposal type code
     * @return true or false
     */
    private boolean isProposalTypeResubmission(String proposalTypeCode) {
         
        return !StringUtils.isEmpty(proposalTypeCode) &&
               (proposalTypeCode.equals(getProposalTypeService().getResubmissionProposalTypeCode()));
    }


    private List<AuditError> getAuditErrors(String areaName, String severity) {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        String clusterKey = areaName;
        if (!GlobalVariables.getAuditErrorMap().containsKey(clusterKey+severity)) {
            GlobalVariables.getAuditErrorMap().put(clusterKey+severity, new AuditCluster(clusterKey, auditErrors,severity));
        }
        else {
            auditErrors = GlobalVariables.getAuditErrorMap().get(clusterKey+severity).getAuditErrorList();
        }

        return auditErrors;
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

    public DataObjectService getDataObjectService() {
        if (this.dataObjectService == null) {
            this.dataObjectService = KcServiceLocator.getService(DataObjectService.class);
        }
        return this.dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

    public ProposalTypeService getProposalTypeService() {
        if (proposalTypeService == null) {
            proposalTypeService = KcServiceLocator.getService(ProposalTypeService.class);
        }
        return proposalTypeService;
    }

	public void setProposalTypeService(ProposalTypeService proposalTypeService) {
		this.proposalTypeService = proposalTypeService;
	}

}
