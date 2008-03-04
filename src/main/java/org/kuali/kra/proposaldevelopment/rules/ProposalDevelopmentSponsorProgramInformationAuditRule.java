/*
 * Copyright 2007 The Kuali Foundation.
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
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
import org.kuali.core.document.Document;
import org.kuali.core.rule.DocumentAuditRule;
import org.kuali.core.util.AuditCluster;
import org.kuali.core.util.AuditError;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

/**
 * This class processes audit rules (warnings) for the Sponsor & Program Information related
 * data of the ProposalDevelopmenDocument.
 */
public class ProposalDevelopmentSponsorProgramInformationAuditRule implements DocumentAuditRule {

    /**
     * @see org.kuali.core.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.core.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;

        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)document;
        List<AuditError> auditErrors = new ArrayList<AuditError>();

        //The Proposal Deadline Date should return a warning during validation for the
        //following conditions: a) if the date entered is older than the current date,
        //or b) if there is no data entered.
        if (proposalDevelopmentDocument.getDeadlineDate() == null) {
            valid = false;
            auditErrors.add(new AuditError(Constants.DEADLINE_DATE_KEY, KeyConstants.WARNING_EMPTY_DEADLINE_DATE, Constants.PROPOSAL_PAGE + "." + Constants.SPONSOR_PROGRAM_INFORMATION_PANEL_ANCHOR));
        } else if (proposalDevelopmentDocument.getDeadlineDate().before(new Date(System.currentTimeMillis()))) {
            valid = false;
            auditErrors.add(new AuditError(Constants.DEADLINE_DATE_KEY, KeyConstants.WARNING_PAST_DEADLINE_DATE, Constants.PROPOSAL_PAGE + "." + Constants.SPONSOR_PROGRAM_INFORMATION_PANEL_ANCHOR));
        }
        
        if (auditErrors.size() > 0) {
            GlobalVariables.getAuditErrorMap().put("sponsorProgramInformationAuditWarnings", new AuditCluster(Constants.SPONSOR_PROGRAM_INFORMATION_PANEL_NAME, auditErrors, Constants.AUDIT_WARNINGS));
        }
        
        auditErrors = new ArrayList<AuditError>();
        
        if(proposalDevelopmentDocument.getS2sOpportunity()!=null && proposalDevelopmentDocument.getS2sOpportunity()!=null){
            if(proposalDevelopmentDocument.getS2sOpportunity().getOpportunityId() != null && proposalDevelopmentDocument.getProgramAnnouncementNumber() != null && !StringUtils.equalsIgnoreCase(proposalDevelopmentDocument.getS2sOpportunity().getOpportunityId(),proposalDevelopmentDocument.getProgramAnnouncementNumber())){
                valid &= false;
                auditErrors.add(new AuditError(Constants.OPPORTUNITY_ID_KEY, KeyConstants.ERROR_OPPORTUNITY_ID_DIFFER , Constants.PROPOSAL_PAGE + "." + Constants.SPONSOR_PROGRAM_INFORMATION_PANEL_ANCHOR));
            }
            if(proposalDevelopmentDocument.getS2sOpportunity().getCfdaNumber() != null && proposalDevelopmentDocument.getCfdaNumber() != null && !StringUtils.equalsIgnoreCase(proposalDevelopmentDocument.getS2sOpportunity().getCfdaNumber(),proposalDevelopmentDocument.getCfdaNumber())){
                valid &= false;
                auditErrors.add(new AuditError(Constants.CFDA_NUMBER_KEY, KeyConstants.ERROR_CFDA_NUMBER_DIFFER , Constants.PROPOSAL_PAGE + "." + Constants.SPONSOR_PROGRAM_INFORMATION_PANEL_ANCHOR));
            }
            if(proposalDevelopmentDocument.getProgramAnnouncementTitle() == null || StringUtils.equalsIgnoreCase(proposalDevelopmentDocument.getProgramAnnouncementTitle().trim(),"")){
                valid &= false;
                auditErrors.add(new AuditError(Constants.OPPORTUNITY_TITLE_KEY, KeyConstants.ERROR_OPPORTUNITY_TITLE_DELETED , Constants.PROPOSAL_PAGE + "." + Constants.SPONSOR_PROGRAM_INFORMATION_PANEL_ANCHOR));
            }
        }
        
        if(proposalDevelopmentDocument.getProposalTypeCode()!=null && 
                proposalDevelopmentDocument.getProposalTypeCode().equals("1") && 
                proposalDevelopmentDocument.getS2sOpportunity()!=null &&
                proposalDevelopmentDocument.getS2sOpportunity().getS2sSubmissionTypeCode()!=null &&
                StringUtils.equalsIgnoreCase(proposalDevelopmentDocument.getS2sOpportunity().getS2sSubmissionTypeCode().toString(), "3")){            
            auditErrors.add(new AuditError(Constants.ORIGINAL_PROPOSAL_ID_KEY, KeyConstants.ERROR_IF_PROPOSAL_TYPE_IS_NEW_AND_S2S_SUBMISSION_TYPE_IS_CHANGED_CORRECTED, Constants.PROPOSAL_PAGE + "." + Constants.SPONSOR_PROGRAM_INFORMATION_PANEL_ANCHOR));
            valid &= false;
        }
        
        if (auditErrors.size() > 0) {
            GlobalVariables.getAuditErrorMap().put("sponsorProgramInformationAuditErrors", new AuditCluster(Constants.SPONSOR_PROGRAM_INFORMATION_PANEL_NAME, auditErrors, Constants.AUDIT_ERRORS));
        }

        return valid;
    }

}
