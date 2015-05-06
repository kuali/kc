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
package org.kuali.coeus.propdev.impl.datavalidation;

public final class ProposalDevelopmentDataValidationConstants {
    public static final String AUDIT_ERRORS = "Error";
    public static final String AUDIT_WARNINGS = "Warnings";

    public static final String NO_SECTION_ID = "";

    public static final String DETAILS_PAGE_ID = "PropDev-DetailsPage";
    public static final String DETAILS_PAGE_NAME = "Proposal Details";

    public static final String S2S_PAGE_ID = "PropDev-OpportunityPage";
    public static final String S2S_PAGE_NAME = "S2S Opportunity Search";
    public static final String S2S_OPPORTUNITY_SECTION_ID = "PropDev-OpportunityPage-General";
    public static final String S2S_OPPORTUNITY_SECTION_NAME = "Opportunity";
    public static final String REVISION_CODE_KEY = "document.developmentProposal.s2sOpportunity.revisionCode";
    public static final String S2S_SUBMISSIONTYPE_CODE_KEY="document.developmentProposal.s2sOpportunity.s2sSubmissionTypeCode";
    public static final String COMPETITION_ID = "document.developmentProposal.s2sOpportunity.competetionId";

    public static final String SPONSOR_PROGRAM_INFO_PAGE_ID = "PropDev-SponsorProgramInfoPage";
    public static final String SPONSOR_PROGRAM_INFO_PAGE_NAME = "Sponsor & Program Info";
    public static final String DEADLINE_DATE_KEY = "document.developmentProposal.deadlineDate";
    public static final String OPPORTUNITY_ID_KEY="document.developmentProposal.programAnnouncementNumber";
    public static final String CFDA_NUMBER_KEY = "document.developmentProposal.cfdaNumber";
    public static final String OPPORTUNITY_TITLE_KEY="document.developmentProposal.programAnnouncementTitle";
    public static final String SPONSOR_PROPOSAL_KEY = "document.developmentProposal.sponsorProposalNumber";
    public static final String PRIME_SPONSOR_KEY = "document.developmentProposal.primeSponsorCode";
    public static final String PREV_GG_TRACKING_ID_KEY = "document.developmentProposal.prevGrantsGovTrackingID";
    public static final String SPONSOR_DIV_CODE_KEY = "document.developmentProposal.agencyDivisionCode";
    public static final String SPONSOR_PROGRAM_CODE_KEY = "document.developmentProposal.agencyProgramCode";

    public static final String ORGANIZATION_PAGE_ID = "PropDev-OrganizationLocationsPage";
    public static final String ORGANIZATION_PAGE_NAME = "Organizations & Locations";
    public static final String APPLICANT_ORGANIZATION_SECTION_ID = "PropDev-ApplicantOrganizationPage-Section";
    public static final String APPLICANT_ORGANIZATION_SECTION_NAME = "Applicant Organization";
    public static final String ORIGINAL_PROPOSAL_ID_KEY = "document.developmentProposal.continuedFrom";

    public static final String PERSONNEL_PAGE_ID = "PropDev-PersonnelPage";
    public static final String PERSONNEL_PAGE_NAME = "Personnel";
    public static final String PERSONNEL_DETAIL_SECTION_NAME = "Details";
    public static final String PERSONNEL_UNIT_SECTION_NAME = "Unit Details";

    public static final String CREDIT_ALLOCATION_PAGE_ID = "PropDev-CreditAllocationPage";
    public static final String CREDIT_ALLOCATION_PAGE_NAME = "Credit Allocation";

    public static final String ATTACHMENT_PAGE_ID = "PropDev-AttachmentsPage";
    public static final String ATTACHMENT_PAGE_NAME = "Attachments";
    public static final String ATTACHMENT_PROPOSAL_SECTION_ID = "PropDev-AttachmentsPage-ProposalSection";
    public static final String ATTACHMENT_PROPOSAL_SECTION_NAME = "Proposal Attachments";
    public static final String ATTACHMENT_PERSONNEL_SECTION_ID = "PropDev-AttachmentsPage-PersonnelSection";
    public static final String ATTACHMENT_PERSONNEL_SECTION_NAME = "Personnel Attachments";
    public static final String ATTACHMENT_INTERNAL_SECTION_ID =  "PropDev-AttachmentsPage-InternalSection";
    public static final String ATTACHMENT_INTERNAL_SECTION_NAME = "Internal Attachments";
    public static final String NARRATIVES_KEY = "document.developmentProposal.narratives";
    public static final String NARRATIVES_STATUS_KEY = "document.developmentProposal.narratives[%d].moduleStatusCode";
    public static final String INSTITUTE_ATTACHMENTS_STATUS_KEY = "document.developmentProposal.instituteAttachments[%d].moduleStatusCode";
    public static final String BIOGRAPHIES_KEY = "document.developmentProposal.propPersonBios";
    public static final String BIOGRAPHY_TYPE_KEY = "document.developmentProposal.propPersonBios[%d].documentTypeCode";
    public static final String BIOGRAPHY_PERSON_KEY = "document.developmentProposal.propPersonBios[%d].proposalPersonNumberString";

    public static final String QUESTIONNAIRE_PAGE_ID = "PropDev-QuestionnairePage";
    public static final String QUESTIONNAIRE_PAGE_NAME = "Questionnaire";

    public static final String BUDGET_PAGE_ID = "PropDev-BudgetPage";
    public static final String BUDGET_PAGE_NAME = "Budget";

    public static final String SUPPLEMENTAL_PAGE_ID = "PropDev-SupplementalPage";
    public static final String SUPPLEMENTAL_PAGE_NAME = "Supplemental Information";

    public static final String GENERIC_ERROR="error.generic";
    public static final String ABSTRACTS_ATTACHMENTS = "abstractsAttachments";
    public static final String QUESTIONS = "questions";
    public static final String KEY_PERSONNEL = "keyPersonnel";
    public static final String PROPOSAL_ORGANIZATION_LOCATION = "proposal.Organization/Location";

    private ProposalDevelopmentDataValidationConstants() {
        throw new UnsupportedOperationException("do not call");
    }
}
