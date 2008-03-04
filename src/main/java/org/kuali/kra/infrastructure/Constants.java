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

package org.kuali.kra.infrastructure;

public interface Constants {
    public static final String KRA_SESSION_KEY = "kra.session";
    public static final String DATASOURCE = "kraDataSource";
    public static final String DATA_DICTIONARY_SERVICE_NAME = "dataDictionaryService";
    public static final String BUSINESS_OBJECT_DICTIONARY_SERVICE_NAME = "businessObjectDictionaryService";
    public static final String DATE_TIME_SERVICE_NAME = "dateTimeService";
    public static final String BUSINESS_OBJECT_DAO_NAME = "businessObjectDao";
    public static final String HTML_FORM_ACTION = "htmlFormAction";
    public static final String TEXT_AREA_FIELD_NAME = "textAreaFieldName";
    public static final String TEXT_AREA_FIELD_LABEL = "textAreaFieldLabel";
    public static final String TEXT_AREA_FIELD_ANCHOR = "textAreaFieldAnchor";
    public static final Integer APPROVAL_STATUS = 2;
    public static final String MAINTENANCE_NEW_ACTION = "New";

    public static final String KEY_PERSON_ROLE = "KP";
    public static final String PRINCIPAL_INVESTIGATOR_ROLE = "PI";
    public static final String CO_INVESTIGATOR_ROLE = "COI";
    public static final String MULTIPLE_VALUE = "multipleValues";
    public static final String KEYWORD_PANEL_DISPLAY = "proposaldevelopment.displayKeywordPanel";

    public static final String MAPPING_BASIC = "basic";
    public static final String MAPPING_ERROR = "error";
    public static final String NEW_PROPOSAL_PERSON_PROPERTY_NAME = "newProposalPerson";
    public static final String NEW_PERSON_LOOKUP_FLAG = "newPersonLookupFlag";
    public static final String MAPPING_CLOSE_PAGE = "closePage";
    public static final String MAPPING_NARRATIVE_ATTACHMENT_RIGHTS_PAGE = "attachmentRights";
    public static final String MAPPING_INSTITUTE_ATTACHMENT_RIGHTS_PAGE = "instituteAttachmentRights";
 
    public static final String CREDIT_SPLIT_ENABLED_RULE_NAME = "proposaldevelopment.creditsplit.enabled";
    public static final String CREDIT_SPLIT_ENABLED_FLAG = "creditSplitEnabledFlag";

    public static final String NARRATIVE_MODULE_NUMBER = "proposalDevelopment.narrative.moduleNumber";
    public static final String NARRATIVE_MODULE_SEQUENCE_NUMBER = "proposalDevelopment.narrative.moduleSequenceNumber";
    public static final String PROP_PERSON_BIO_NUMBER = "proposalDevelopment.proposalPersonBiography.biographyNumber";
    public static final String PROPOSAL_LOCATION_SEQUENCE_NUMBER = "proposalDevelopment.proposalLocation.locationSequenceNumber";
    public static final String PROPOSAL_SPECIALREVIEW_NUMBER = "proposalDevelopment.proposalSpecialReview.specialReviewNumber";
    public static final String PROPOSAL_PERSON_DEGREE_SEQUENCE_NUMBER = "proposalDevelopment.proposalPerson.degree.degreeSequenceNumber";
    public static final String PROPOSAL_PERSON_NUMBER = "proposalDevelopment.proposalPerson.proposalPersonNumber";
    public static final String PROPOSAL_NARRATIVE_TYPE_GROUP = "proposalNarrativeTypeGroup";
    public static final String INSTITUTE_NARRATIVE_TYPE_GROUP = "instituteNarrativeTypeGroup";
    public static final String INSTITUTIONAL_ATTACHMENT_TYPE_NAME = "Institutional Attachment";
    public static final String PERSONNEL_ATTACHMENT_TYPE_NAME = "Personnel Attachment";
    public static final String PROPOSAL_ATTACHMENT_TYPE_NAME = "Proposal Attachment";
    public static final String NEW_NARRATIVE_USER_RIGHTS_PROPERTY_KEY = "newNarrativeUserRight";

    public static final String PROPOSAL_PERSON_ROLE_PARAMETER_PREFIX = "proposaldevelopment.personrole.";

    public static final String PARAMETER_MODULE_PROPOSAL_DEVELOPMENT = "KRA-PD";
    public static final String PARAMETER_COMPONENT_DOCUMENT = "D";
    public static final String INSTITUTE_NARRATIVE_TYPE_GROUP_CODE = "O";
    public static final String NARRATIVE_MODULE_STATUS_COMPLETE = "C";

    public static final String ABSTRACTS_PROPERTY_KEY = "newProposalAbstract";
    public static final String SPONSOR_PROPOSAL_NUMBER_PROPERTY_KEY = "sponsorProposalNumber";
    public static final String DEADLINE_DATE_KEY = "document.deadlineDate";
    public static final String SPONSOR_PROPOSAL_NUMBER_LABEL = "Sponsor Proposal ID";

    public static final String AUDIT_ERRORS = "Errors";
    public static final String AUDIT_WARNINGS = "Warnings";
    
    public static final String PROPOSAL_PAGE = "proposal";
    public static final String CUSTOM_ATTRIBUTES_PAGE = "customData";
    public static final String SPONSOR_PROGRAM_INFORMATION_PANEL_ANCHOR = "SponsorProgramInformation";
    public static final String SPONSOR_PROGRAM_INFORMATION_PANEL_NAME = "Sponsor & Program Information";

    // Key Personnel Mojo
    public static final String KEY_PERSONNEL_PAGE = "keyPersonnel";
    public static final String PROPOSAL_PERSON_INVESTIGATOR = "investigator";
    public static final String KEY_PERSONNEL_PANEL_ANCHOR = "KeyPersonnel";
    public static final String KEY_PERSONNEL_PANEL_NAME = "Key Personnel Information";
    public static final String PERSON_EDITABLE_FIELD_NAME_PROPERTY_KEY = "fieldName";
    public static final String INVESTIGATOR_CREDIT_TYPE_CODE_PROPERTY_KEY = "invCreditTypeCode";
    public static final String EMPTY_STRING = "";
    public static final String PROPOSAL_PERSON_KEY = "document.propoosalPerson*";
    public static final String CREDIT_SPLIT_KEY = "document.creditSplit";

   
    /* set values for ynq */
    public static final Integer ANSWER_YES_NO = 2;
    public static final Integer ANSWER_YES_NO_NA = 3;
    public static final String QUESTION_TYPE_PROPOSAL = "P";
    public static final String QUESTION_TYPE_INDIVIDUAL = "I"; // Investigator - Certification questions
    public static final String YNQ_EXPLANATION_REQUIRED = "Explanation required: if answer = ";
    public static final String YNQ_REVIEW_DATE_REQUIRED = "Date required: if answer = ";
    public static final String QUESTION_STATUS_ACTIVE = "A";

    public static final String DEFAULT_DATE_FORMAT_PATTERN = "MM/dd/yyyy";
    public static final String PARAMETER_MODULE_BUDGET = "KRA-B";
    
    // Budget Versions Constants
    public static final String BUDGET_STATUS_COMPLETE_CODE = "budgetStatusCompleteCode";
    public static final String BUDGET_STATUS_INCOMPLETE_CODE = "budgetStatusIncompleteCode";
    public static final String PROPOSAL_BUDGET_VERSION_NUMBER = "proposalDevelopment.budget.versionNumber";
    public static final String BUDGET_DEFAULT_OVERHEAD_RATE_CODE = "defaultOverheadRateClassCode";
    public static final String BUDGET_DEFAULT_UNDERRECOVERY_RATE_CODE = "defaultUnderrecoveryRateClassCode";
    public static final String BUDGET_DEFAULT_MODULAR_FLAG = "defaultModularFlag";
    
    // KIM Authorization Namespace for KRA
    public static final String KRA_NAMESPACE = "KRA";
    
    // Key Permissions Info
    public static final String CONFIRM_DELETE_PROPOSAL_USER_KEY = "confirmDeleteProposalUser";
    public static final String PERMISSION_USERS_PROPERTY_KEY = "newProposalUser";
    public static final String EDIT_ROLES_PROPERTY_KEY = "proposalUserEditRole";
    public static final String MAPPING_PERMISSIONS_ROLE_RIGHTS_PAGE = "permissionsRoleRights";
    public static final String MAPPING_PERMISSIONS_EDIT_ROLES_PAGE = "permissionsEditRoles";
    public static final String MAPPING_PERMISSIONS_CLOSE_EDIT_ROLES_PAGE = "permissionsCloseEditRoles";
    
    // Task Authorization
    public static final String TASK_AUTHORIZATION = "taskAuthorization";

    //Budget Rates
    public static final String ON_CAMUS_FLAG = "N";
    public static final String OFF_CAMUS_FLAG = "F";

    
    //Grants.gov
    public static final String S2S_SUBMISSIONTYPE_CODE_KEY="document.s2sOpportunity.s2sSubmissionTypeCode";
    public static final String GRANTS_GOV_PANEL_ANCHOR  = "Opportunity";
    public static final String GRANTS_GOV_OPPORTUNITY_PANEL = "GrantsGov";
    public static final String OPPORTUNITY_ID_KEY="document.programAnnouncementNumber";
    public static final String OPPORTUNITY_TITLE_KEY="document.programAnnouncementTitle";
    public static final String CFDA_NUMBER_KEY="document.cfdaNumber";
    public static final String GRANTS_GOV_PAGE = "grantsGov";
    public static final String ORIGINAL_PROPOSAL_ID_KEY = "document.sponsorProposalNumber";
    public static final String CFDA_NUMBER = "cfdaNumber";
    public static final String OPPORTUNITY_ID= "opportunityId";
}
