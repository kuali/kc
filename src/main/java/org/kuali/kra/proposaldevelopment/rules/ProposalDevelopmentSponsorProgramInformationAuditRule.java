/*
 * Copyright 2006-2009 The Kuali Foundation
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

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.DocumentAuditRule;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * This class processes audit rules (warnings) for the Sponsor & Program Information related
 * data of the ProposalDevelopmenDocument.
 */
public class ProposalDevelopmentSponsorProgramInformationAuditRule implements DocumentAuditRule {    
    /**
     * @see org.kuali.rice.kns.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.rice.kns.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;

        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)document;
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        
        KualiConfigurationService kualiConfigurationService = ((KualiConfigurationService)KraServiceLocator.getService(KualiConfigurationService.class));

        //The Proposal Deadline Date should return a warning during validation for the
        //following conditions: a) if the date entered is older than the current date,
        //or b) if there is no data entered.
        if (proposalDevelopmentDocument.getDevelopmentProposal().getDeadlineDate() == null) {
            auditErrors.add(new AuditError(Constants.DEADLINE_DATE_KEY, KeyConstants.WARNING_EMPTY_DEADLINE_DATE, Constants.PROPOSAL_PAGE + "." + Constants.SPONSOR_PROGRAM_INFORMATION_PANEL_ANCHOR));
        } else if (proposalDevelopmentDocument.getDevelopmentProposal().getDeadlineDate().before(new Date(System.currentTimeMillis()))) {
            auditErrors.add(new AuditError(Constants.DEADLINE_DATE_KEY, KeyConstants.WARNING_PAST_DEADLINE_DATE, Constants.PROPOSAL_PAGE + "." + Constants.SPONSOR_PROGRAM_INFORMATION_PANEL_ANCHOR));
        }
        
        if (auditErrors.size() > 0) {
            GlobalVariables.getAuditErrorMap().put("sponsorProgramInformationAuditWarnings", new AuditCluster(Constants.SPONSOR_PROGRAM_INFORMATION_PANEL_NAME, auditErrors, Constants.AUDIT_WARNINGS));
        }
        
        auditErrors = new ArrayList<AuditError>();
        
		if (proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity() != null && proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity() != null) {
            if (proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getOpportunityId() != null && proposalDevelopmentDocument.getDevelopmentProposal().getProgramAnnouncementNumber() != null && !StringUtils.equalsIgnoreCase(proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getOpportunityId(), proposalDevelopmentDocument.getDevelopmentProposal().getProgramAnnouncementNumber())) {
                valid &= false;
                auditErrors.add(new AuditError(Constants.OPPORTUNITY_ID_KEY, KeyConstants.ERROR_OPPORTUNITY_ID_DIFFER , Constants.PROPOSAL_PAGE + "." + Constants.SPONSOR_PROGRAM_INFORMATION_PANEL_ANCHOR));
            }
            if (proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getCfdaNumber() != null && proposalDevelopmentDocument.getDevelopmentProposal().getCfdaNumber() != null && !StringUtils.equalsIgnoreCase(proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getCfdaNumber(), proposalDevelopmentDocument.getDevelopmentProposal().getCfdaNumber())) {
                valid &= false;
                auditErrors.add(new AuditError(Constants.CFDA_NUMBER_KEY, KeyConstants.ERROR_CFDA_NUMBER_DIFFER , Constants.PROPOSAL_PAGE + "." + Constants.SPONSOR_PROGRAM_INFORMATION_PANEL_ANCHOR));
            }
            if (proposalDevelopmentDocument.getDevelopmentProposal().getProgramAnnouncementTitle() == null || StringUtils.equalsIgnoreCase(proposalDevelopmentDocument.getDevelopmentProposal().getProgramAnnouncementTitle().trim(), "")) {
                valid &= false;
                auditErrors.add(new AuditError(Constants.OPPORTUNITY_TITLE_KEY, KeyConstants.ERROR_OPPORTUNITY_TITLE_DELETED , Constants.PROPOSAL_PAGE + "." + Constants.SPONSOR_PROGRAM_INFORMATION_PANEL_ANCHOR));
            }
        }
        
//        if(proposalDevelopmentDocument.getDevelopmentProposal().getProposalTypeCode()!=null && 
//                StringUtils.equalsIgnoreCase(proposalDevelopmentDocument.getDevelopmentProposal().getProposalTypeCode(),kualiConfigurationService.getParameter(Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_NEW).getParameterValue()) && 
//                proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity()!= null &&
//                proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getS2sSubmissionTypeCode()!= null &&
//                StringUtils.equalsIgnoreCase(proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getS2sSubmissionTypeCode().toString(), kualiConfigurationService.getParameter(Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, KeyConstants.S2S_SUBMISSIONTYPE_CHANGEDCORRECTED).getParameterValue())){             
//            auditErrors.add(new AuditError(Constants.ORIGINAL_PROPOSAL_ID_KEY, KeyConstants.ERROR_IF_PROPOSAL_TYPE_IS_NEW_AND_S2S_SUBMISSION_TYPE_IS_CHANGED_CORRECTED, Constants.PROPOSAL_PAGE + "." + Constants.SPONSOR_PROGRAM_INFORMATION_PANEL_ANCHOR));
//            valid &= false;
//        }
        
        if (auditErrors.size() > 0) {
            GlobalVariables.getAuditErrorMap().put("sponsorProgramInformationAuditErrors", new AuditCluster(Constants.SPONSOR_PROGRAM_INFORMATION_PANEL_NAME, auditErrors, Constants.GRANTSGOV_ERRORS));
        }

        return valid;
    }

}
