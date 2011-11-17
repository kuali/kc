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

package org.kuali.kra.infrastructure;

public interface Constants {
    //Generic constants
    public static final String COLON=":";
    public static final String COMMA=",";
    public static final String SEMI_COLON=";";

    public static final String BOOTSTRAP_SPRING_FILE = "kc.bootstrap.spring.file";
    
    public static final String KRA_SESSION_KEY = "kra.session";
    public static final String APP_CONTEXT_KEY = "app.context.name";
    public static final String DATASOURCE = "kraDataSource";
    public static final String DATA_DICTIONARY_SERVICE_NAME = "dataDictionaryService";
    public static final String BUSINESS_OBJECT_DICTIONARY_SERVICE_NAME = "businessObjectDictionaryService";
    public static final String DATE_TIME_SERVICE_NAME = "dateTimeService";
    public static final String BUSINESS_OBJECT_DAO_NAME = "businessObjectDao";
    public static final String HTML_FORM_ACTION = "htmlFormAction";
    public static final Integer APPROVAL_STATUS = 2;
    public static final String MAINTENANCE_NEW_ACTION = "New";
    
    public static final String LAST_ACTION_PRINCIPAL_ID = "lastActionPrincipalId";

    public static final String KEY_PERSON_ROLE = "KP";
    public static final String PRINCIPAL_INVESTIGATOR_ROLE = "PI";
    public static final String CO_INVESTIGATOR_ROLE = "COI";
    public static final String MULTIPLE_VALUE = "multipleValues";
    public static final String KEYWORD_PANEL_DISPLAY = "proposaldevelopment.displayKeywordPanel";
    public static final String BIOSKETCH_ATTACHMENT_TYPE_CODE = "1";

    public static final int PHS_RESTRAININGPLAN_PILEADERSHIPPLAN_ATTACHMENT=121;
    public static final int PHS_RESEARCHPLAN_MULTIPLEPILEADERSHIPPLAN=46;
    public static final int MENTORING_PLAN_ATTACHMENT_TYPE_CODE=8;
    public static final String MENTORING_PLAN_ATTACHMENT="Mentoring Plan.pdf";


    public static final String MAPPING_BASIC = "basic";
    public static final String MAPPING_ERROR = "error";
    public static final String MAPPING_PROPOSAL_ACTIONS = "actions";
    public static final String MAPPING_PROTOCOL_ACTIONS = "protocolActions";
    public static final String MAPPING_PROTOCOL_ONLINE_REVIEW = "onlineReview";
    public static final String MAPPING_PROPOSAL_MEDUSA_PAGE = "proposalMedusa";
    public static final String MAPPING_PROPOSAL_DISPLAY_INACTIVE = "displayInactive";
    public static final String NEW_PROPOSAL_PERSON_PROPERTY_NAME = "newProposalPerson";
    public static final String NEW_PERSON_LOOKUP_FLAG = "newPersonLookupFlag";
    public static final String MAPPING_CLOSE_PAGE = "closePage";
    public static final String MAPPING_NARRATIVE_ATTACHMENT_RIGHTS_PAGE = "attachmentRights";
    public static final String MAPPING_INSTITUTE_ATTACHMENT_RIGHTS_PAGE = "instituteAttachmentRights";
    public static final String MAPPING_RESUBMISSION_PROMPT = "resubmissionPrompt";
    public static final String MAPPING_HOLDING_PAGE = "kraHoldingPage";
    public static final String MAPPING_IRB_HOLDING_PAGE = "irbHoldingPage";
    public static final String MAPPING_LOOKUP_PAGE ="lookUpPage";
    
    public static final String HOLDING_PAGE_MESSAGES = "holdingPageMessages";
    public static final String HOLDING_PAGE_RETURN_LOCATION = "holdingPageReturnLocation";
    public static final String HOLDING_PAGE_DOCUMENT_ID = "holdingPageDocumentId";
    
    public static final String PROPOSAL_PERSON_BIOGRAPHY_DEFAULT_DOC_TYPE = "DEFAULT_BIOGRAPHY_DOCUMENT_TYPE_CODE";
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
    public static final String INVALID_FILE_NAME_ERROR_CODE = "2";
    public static final String INVALID_FILE_NAME_CHECK_PARAMETER = "INVALID_FILE_NAME_CHECK";
    public static final String INVALID_FILE_NAME_ERROR_TAB = "institutionalProposalNotepadBean.newInstitutionalProposalNotepad.invalidFileName";

    public static final String PERSON_ROLE_PARAMETER_PREFIX = "personrole.";
    public static final String NIH_SPONSOR_ACRONYM = "NIH";
    public static final String NIH_SPONSOR_CODE = "000340";
    public static final String SPONSOR_LEVEL_HIERARCHY = "sponsorLevelHierarchy";
    public static final String SPONSOR_HIERARCHY_NAME = "sponsorGroupHierarchyName";
    public static final String SPONSOR_HIERARCHY_NIH_MULT_PI = "NIH Multiple PI";
    public static final String SPONSOR_HIERARCHY_NIH_OSC = "NIH Other Significant Contributor";
    public static final String MODULE_NAMESPACE_PROTOCOL = "KC-PROTOCOL"; 
    public static final String PARAMETER_MODULE_PROTOCOL_REFERENCEID1 = "irb.protocol.referenceID1";
    public static final String PARAMETER_MODULE_PROTOCOL_REFERENCEID2 = "irb.protocol.referenceID2";
    public static final String PARAMETER_MODULE_PROTOCOL_BILLABLE = "irb.protocol.billable";
    public static final String MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT = "KC-PD";
    public static final String MODULE_NAMESPACE_INSTITUTIONAL_PROPOSAL = "KC-IP";
    public static final String MODULE_NAMESPACE_AWARD = "KC-AWARD"; 
    public static final String MODULE_NAMESPACE_NEGOTIATION = "KC-NEGOTIATION";
    
    public static final String PESSIMISTIC_LOCKING_EXPIRATION_AGE = "pessimisticLocking.expirationAge";
    
    public static final String PARAMETER_COMPONENT_DOCUMENT = "Document";
    public static final String INSTITUTE_NARRATIVE_TYPE_GROUP_CODE = "O";
    public static final String PROPOSAL_NARRATIVE_TYPE_GROUP_CODE = "P";    
    public static final String NARRATIVE_MODULE_STATUS_COMPLETE = "C";
    public static final String NARRATIVE_MODULE_STATUS_INCOMPLETE = "I";

    public static final String ABSTRACTS_PROPERTY_KEY = "newProposalAbstract";
    public static final String SPONSOR_PROPOSAL_NUMBER_PROPERTY_KEY = "sponsorProposalNumber";
    public static final String DEADLINE_DATE_KEY = "document.developmentProposalList[0].deadlineDate";
    public static final String PRIME_SPONSOR_KEY = "document.developmentProposalList[0].primeSponsorCode";
    public static final String PROJECT_TITLE_KEY = "document.developmentProposalList[0].title";
    public static final String SPONSOR_PROPOSAL_NUMBER_LABEL = "Sponsor Proposal ID";

    public static final String AUDIT_ERRORS = "Validation Errors";
    public static final String AUDIT_WARNINGS = "Warnings";
    public static final String GRANTSGOV_ERRORS= "Grants.Gov Errors";
    
    public static final String CURRENT_PENDING_REPORT_GROUP_NAME = "CURRENT_PENDING_REPORT_GROUP_NAME";
    //public static final String PROPOSAL_ACTIONS_PAGE = "proposalActions";
    //public static final String ABSTRACTS_AND_ATTACHMENTS_PAGE = "abstractsAttachments";
    public static final String PROPOSAL_PAGE = "proposal";
    public static final String CUSTOM_ATTRIBUTES_PAGE = "customData";
    public static final String QUESTIONS_PAGE = "questions";
    public static final String PERMISSIONS_PAGE = "permissions";
    public static final String PROPOSAL_ACTIONS_PAGE = "actions";
    public static final String HIERARCHY_PAGE = "hierarchy";
    public static final String ATTACHMENTS_PAGE = "abstractsAttachments";
    public static final String SPONSOR_PROGRAM_INFORMATION_PANEL_ANCHOR = "SponsorProgramInformation";
    public static final String SPONSOR_PROGRAM_INFORMATION_PANEL_NAME = "Sponsor & Program Information";
    public static final String REQUIRED_FIELDS_PANEL_ANCHOR = "RequiredFieldsforSavingDocument";
    public static final String REQUIRED_FIELDS_PANEL_NAME = "Required Fields for Saving Document ";
    
    public static final String BUDGET_PARAMETERS_PAGE_METHOD = "summary";
    public static final String BUDGET_PARAMETERS_OVERVIEW_PANEL_ANCHOR = "topOfForm";
    public static final String BUDGET_PARAMETERS_OVERVIEW_PANEL_NAME = "Budget Overview";
    public static final String BUDGET_PARAMETERS_TOTALS_PANEL_ANCHOR = "BudgetPeriodsTotals";
    public static final String BUDGET_PARAMETERS_TOTALS_PANEL_NAME = "Budget Periods & Totals";
    public static final String BUDGET_EXPENSES_PAGE_METHOD = "budgetExpenses";
    public static final String BUDGET_EXPENSES_OVERVIEW_PANEL_ANCHOR = "BudgetOvervieV";
    public static final String BUDGET_EXPENSES_OVERVIEW_PANEL_NAME = "Budget Overview (Period ";
    // Proposal Document Property Constants
    public static final String PROPOSAL_NUMBER = "proposalNumber";
    public static final String BUDGET_VERSION_PANEL_NAME = "Budget Versions";
    public static final String BUDGET_VERSION_OVERVIEWS = "budgetVersionOverviews";
    
    // Budget Document Property Constants
    public static final String BUDGET_VERSION_NUMBER = "budgetVersionNumber";

    // Key Personnel Mojo
    public static final String KEY_PERSONNEL_PAGE = "keyPersonnel";
    public static final String PROPOSAL_PERSON_INVESTIGATOR = "investigator";
    public static final String KEY_PERSONNEL_PANEL_ANCHOR = "KeyPersonnel";
    public static final String KEY_PERSONNEL_PANEL_NAME = "Key Personnel Information";
    public static final String PERSON_EDITABLE_FIELD_NAME_PROPERTY_KEY = "document.newMaintainableObject.fieldName";
    public static final String INVESTIGATOR_CREDIT_TYPE_CODE_PROPERTY_KEY = "invCreditTypeCode";
    public static final String EMPTY_STRING = "";
    public static final String PRINCIPAL_INVESTIGATOR_KEY = "newProposalPerson";
    public static final String CREDIT_SPLIT_KEY = "document.developmentProposalList[0].creditSplit";

   
    /* set values for ynq */
    public static final Integer ANSWER_YES_NO = 2;
    public static final Integer ANSWER_YES_NO_NA = 3;
    public static final String QUESTION_TYPE_PROPOSAL = "P";
    public static final String QUESTION_TYPE_INDIVIDUAL = "I"; // Investigator - Certification questions
    public static final String YNQ_EXPLANATION_REQUIRED = "Explanation required: if answer = ";
    public static final String YNQ_REVIEW_DATE_REQUIRED = "Date required: if answer = ";
	public static final String STATUS_ACTIVE = "A";
	public static final String STATUS_INACTIVE = "I";
    public static final String QUESTION_STATUS_ACTIVE = "A";
    public static final String DOCUMENT_SAVED = "S";
    public static final String DOCUMENT_INITIATED = "?";
    public static final String ANSWER_NA = "X";

    public static final String DEFAULT_DATE_FORMAT_PATTERN = "MM/dd/yyyy";
    public static final String DEFAULT_TIME_FORMAT_PATTERN = "h:mm a";
    public static final String MODULE_NAMESPACE_BUDGET = "KC-B"; 
    public static final String BUDGET_ALL_DETAIL_TYPE_CODE = "All";
    
    // Budget Versions Constants
    public static final String BUDGET_STATUS_COMPLETE_CODE = "budgetStatusCompleteCode";
    public static final String BUDGET_STATUS_INCOMPLETE_CODE = "budgetStatusIncompleteCode";
    public static final String PROPOSAL_BUDGET_VERSION_NUMBER = "proposalDevelopment.budget.versionNumber";
    public static final String BUDGET_DEFAULT_OVERHEAD_RATE_CODE = "defaultOverheadRateClassCode";
    public static final String BUDGET_DEFAULT_OVERHEAD_RATE_TYPE_CODE = "defaultOverheadRateTypeCode";
    public static final String BUDGET_DEFAULT_UNDERRECOVERY_RATE_CODE = "defaultUnderrecoveryRateClassCode";
    public static final String BUDGET_DEFAULT_MODULAR_FLAG = "defaultModularFlag";
    public static final String BUDGET_JOBCODE_VALIDATION_ENABLED = "JOBCODE_VALIDATION_ENABLED";    
    // Budget Distribution And Income
    public static final String BUDGET_CURRENT_FISCAL_YEAR = "budgetCurrentFiscalYear";
    public static final String BUDGET_COST_SHARING_APPLICABILITY_FLAG = "budgetCostSharingApplicabilityFlag";
    public static final String BUDGET_UNRECOVERED_F_AND_A_APPLICABILITY_FLAG = "budgetUnrecoveredFandAApplicabilityFlag";
    public static final String BUDGET_COST_SHARING_ENFORCEMENT_FLAG = "budgetCostSharingEnforcementFlag";
    public static final String BUDGET_UNRECOVERED_F_AND_A_ENFORCEMENT_FLAG = "budgetUnrecoveredFandAEnforcementFlag";
    
    //budget status codes
    public static final String BUDGET_STATUS_CODE_IN_PROGRESS = "1";
    public static final String BUDGET_STATUS_CODE_TO_BE_POSTED = "10";
    public static final String BUDGET_STATUS_CODE_ERROR_IN_POSTING = "11";
    public static final String BUDGET_STATUS_CODE_SUBMITTED = "5";
    public static final String BUDGET_STATUS_CODE_REJECTED = "8";
    public static final String BUDGET_STATUS_CODE_POSTED = "9";
    public static final String BUDGET_STATUS_CODE_CANCELLED = "14";
    public static final String BUDGET_STATUS_CODE_DISAPPROVED = "13";
    
    // Budget Personnel
    public static final String BUDGET_PERSON_DEFAULT_JOB_CODE = "budgetPersonDefaultJobCode";
    public static final String BUDGET_PERSON_DEFAULT_APPOINTMENT_TYPE = "budgetPersonDefaultAppointmentType";
    //public static final String BUDGET_PERSON_DEFAULT_PERIOD_TYPE = "budgetPersonDefaultPeriodType";
    public static final String BUDGET_PERSON_DEFAULT_CALCULATION_BASE = "budgetPersonDefaultCalculationBase";
    public static final String BUDGET_PERSON_DEFAULT_EFFECTIVE_DATE = "budgetPersonDefaultEffectiveDate";
    public static final String PERSON_SEQUENCE_NUMBER = "personSequenceNumber";
    public static final String BUDGET_PERSONNEL_PAGE = "personnel";
    public static final String JOB_CODE = "jobCode";
    public static final String BUDGET_CATEGORY_PERSONNEL = "P";
        
    // KIM Authorization Namespace for KRA
    public static final String KRA_NAMESPACE = "KRA";
    
    // Key Permissions Info
    public static final String CONFIRM_DELETE_PROPOSAL_USER_KEY = "confirmDeleteProposalUser";
    public static final String PERMISSION_PROPOSAL_USERS_PROPERTY_KEY = "newProposalUser";
    public static final String EDIT_ROLES_PROPERTY_KEY = "proposalUserEditRole";
    public static final String PERMISSIONS_EDIT_ROLES_PROPERTY_KEY = "permissionsUserEditRole";
    public static final String MAPPING_PERMISSIONS_ROLE_RIGHTS_PAGE = "permissionsRoleRights";
    public static final String MAPPING_PERMISSIONS_EDIT_ROLES_PAGE = "permissionsEditRoles";
    public static final String MAPPING_PERMISSIONS_CLOSE_EDIT_ROLES_PAGE = "permissionsCloseEditRoles";
    
    // Permission constants
    public static final String PERMISSION_USERS_PROPERTY_KEY = "newPermissionsUser";
    
    // Special Review constraints
    public static final Integer SPECIAL_REVIEW_COMMENT_LENGTH = 40;
    
    // Task Authorization
    public static final String TASK_AUTHORIZATION = "taskAuthorization";

    //Budget Rates
    public static final String ON_CAMUS_FLAG = "N";
    public static final String OFF_CAMUS_FLAG = "F";
    public static final String DEFALUT_CAMUS_FLAG = "D";
    public static final String RATE_CLASS_TYPE_FOR_INFLATION = "I";
    
    //Budget Expenses
    public static final String BUDGET_LINEITEM_NUMBER = "budget.budgetLineItem.lineItemNumber";
    public static final String BUDGET_EXPENSE_LOOKUP_MESSAGE1 = "budget.expense.lookup.message1";
    public static final String BUDGET_EXPENSE_LOOKUP_MESSAGE2 = "budget.expense.lookup.message2";
    public static final String PERCENT_EFFORT_FIELD = "Percent Effort";
    public static final String PERCENT_CHARGED_FIELD = "Percent Charged";
    public static final String BUDGET_PERSON_DETAILS_DEFAULT_PERIODTYPE = "budgetPersonDetailsDefaultPeriodType";
    
    //Budget Totals
    public static final int BUDGET_SUMMARY_PERIOD_GROUP_SIZE = 5;
    
    //Grants.gov
    public static final String S2S_SUBMISSIONTYPE_CODE_KEY="document.developmentProposalList[0].s2sOpportunity.s2sSubmissionTypeCode";
    public static final String GRANTS_GOV_PANEL_ANCHOR  = "Opportunity";
    public static final String GRANTS_GOV_OPPORTUNITY_PANEL = "GrantsGov";
    public static final String ABSTRACTS_AND_ATTACHMENTS_PANEL ="Abstracts and Attachments";
    public static final String OPPORTUNITY_ID_KEY="document.developmentProposalList[0].programAnnouncementNumber";
    public static final String OPPORTUNITY_TITLE_KEY="document.developmentProposalList[0].programAnnouncementTitle";
    public static final String CFDA_NUMBER_KEY="document.cfdaNumber";
    public static final String GRANTS_GOV_PAGE = "grantsGov";
    public static final String SPONSOR_PROPOSAL_KEY = "document.developmentProposalList[0].sponsorProposalNumber";
    public static final String ORIGINAL_PROPOSAL_ID_KEY = "document.developmentProposalList[0].continuedFrom";
    public static final String CFDA_NUMBER = "cfdaNumber";
    public static final String OPPORTUNITY_ID= "opportunityId";
    public static final String NO_FIELD= "noField";
    public static final String GRANTS_GOV_LINK="message.grantsgov.link";
    public static final String GRANTS_GOV_GENERIC_ERROR_KEY= "error.grantsgov.schemavalidation.generic.errorkey";
    public static final String GRANTS_GOV_SUBMISSION_SUCCESSFUL_MESSAGE = "message.grantsGov.submission.successful";
    
    
    // custom attribute
    public static final String CUSTOM_ATTRIBUTE_ID = "customAttributeId";
    public static final String DOCUMENT_NEWMAINTAINABLEOBJECT_ACTIVE = "document.newMaintainableObject.active";
    public static final String DOCUMENT_NEWMAINTAINABLEOBJECT_CUSTOM_ATTRIBUTE_ID = "document.newMaintainableObject.customAttributeId";
    public static final String DOCUMENT_NEWMAINTAINABLEOBJECT_LOOKUPRETURN = "document.newMaintainableObject.lookupReturn";
    public static final String LOOKUP_RETURN_FIELDS = "lookupReturnFields";
    public static final String LOOKUP_CLASS_NAME = "lookupClassName";

    public static final String MAPPING_PERSONNEL_BUDGET = "personnelBudget";
    public static final String MAPPING_EXPENSES_BUDGET = "expenses";
    public static final String BUDGET_PERSON_LINE_NUMBER = "budget.budgetPersonnelDetails.personNumber";
    public static final String BUDGET_PERSON_LINE_SEQUENCE_NUMBER = "budget.budgetPersonnelDetails.sequenceNumber";
    
    public static final String DATA_TYPE_STRING = "String - Any Character";
    public static final String DATA_TYPE_NUMBER = "Number - [0-9]";
    public static final String DATA_TYPE_DATE = "Date - [xx/xx/xxxx]";
    
    public static final String TRUE_FLAG = "Y";
    public static final String FALSE_FLAG = "N";
    public static final String PROPOSAL_SPECIAL_REVIEW_KEY = "document.developmentProposalList[0].proposalSpecialReview*";
    public static final String SPECIAL_REVIEW_PAGE = "specialReview";
    public static final String SPECIAL_REVIEW_PANEL_ANCHOR = "SpecialReview";
    public static final String SPECIAL_REVIEW_PANEL_NAME = "Special Review Information";
    public static final String BUDGET_PERIOD_PANEL_NAME = "Budget Period And Totals Information";
    public static final String BUDGET_RATE_PANEL_NAME = "Budget Rate";
    public static final String BUDGET_RATE_PAGE = "budgetRate";
    public static final String BUDGET_PERIOD_PAGE = "parameters";
    public static final String BUDGET_VERSIONS_PAGE = "versions"; 
    public static final String PD_BUDGET_VERSIONS_PAGE = "budgetVersions"; 
    public static final String BUDGET_EXPENSES_PAGE = "expenses";
    public static final String BUDGET_ACTIONS_PAGE = "budgetActions";
    public static final String BUDGET_RATES_PAGE = "rates";
    public static final String BUDGET_DIST_AND_INCOME_PAGE = "distributionAndIncome";
    public static final String BUDGET_MODULAR_PAGE = "modularBudget";    
    public static final String BUDGET_SUMMARY_TOTALS_PAGE = "summaryTotals";
    public static final String PROPOSAL_HIERARCHY_PAGE = "proposalHierarchy";
    
    public static final String BUDGET_PERIOD_PANEL_ANCHOR = "BudgetPeriodsAmpTotals";
    public static final String BUDGET_RATE_PANEL_ANCHOR = "BudgetRate";
    public static final String BUDGET_VERSIONS_PANEL_ANCHOR = "BudgetVersions";
    public static final String BUDGET_PERIOD_KEY = "document.budgetPeriod*";
    public static final String BUDGET_DISTRIBUTION_AND_INCOME_PAGE = "budgetDistributionAndIncome";
    public static final String BUDGET_UNRECOVERED_F_AND_A_PANEL_ANCHOR = "budgetUnrecoveredFandA";
    public static final String BUDGET_UNRECOVERED_F_AND_A_PANEL_NAME = "Budget Unrecovered F and A";
    public static final String BUDGET_COST_SHARE_PANEL_ANCHOR = "budgetCostSharing";
    public static final String BUDGET_COST_SHARE_PANEL_NAME = "Budget Cost Sharing";
    public static final String BUDGET_OVERVIEW_PANEL_NAME = "Budget Overview";
    
    // Copy proposal
    public static final String COPY_PROPOSAL_PROPERTY_KEY = "copyProposal";
    
    //Approver View
    public static final String MAPPING_COPY_PROPOSAL_PAGE = "copyProposal";
    public static final String MAPPING_PROPOSAL_SUMMARY_PAGE = "proposalDevelopmentSummary";
    public static final String THIRD_PARTY_UNIT_NO="3RD-PRTY";
    
    public static final String MAPPING_PROPOSAL_APPROVER_VIEW_PAGE = "approverView";
    public static final String HEADER_TAB = "headerTab";
    
    public static final String ON_OFF_CAMPUS_FLAG = "onOffCampusFlag";

    // Budget Rates
    public static final int APPLICABLE_RATE_PRECISION = 3;
    public static final int APPLICABLE_RATE_SCALE = 2;
    public static final String APPLICABLE_RATE_LIMIT = "999.99";
    public static final String APPLICABLE_RATE_DECIMAL_CHAR = ".";
    
    // Modular Budget
    public static final String PARAMETER_FNA_COST_ELEMENTS = "consortiumFnaCostElements";
    public static final String PARAMETER_FNA_RATE_CLASS_TYPE = "fnaRateClassTypeCode";
    
    public static final String PROPOSALDATA_OVERRIDE_PROPERTY_KEY = "newProposalChangedData";
    public static final String PROPOSALDATA_CHANGED_VAL_KEY = "newProposalChangedData.changedValue";
    public static final String PROPOSALDATA_DISPLAY_VAL_KEY = "newProposalChangedData.displayValue";
    public static final String PROPOSALDATA_CURRENT_DISPLAY_KEY = "newProposalChangedData.oldDisplayValue";
    public static final String PROPOSALDATA_COMMENTS_KEY = "newProposalChangedData.comments";

    public static final String BUDGET_SALARY_REPORT = "ProposalBudget/Salaries";
    public static final String PERSONNEL_BUDGET_PANEL_NAME = "Personnel Budget";
    public static final String INITIAL_UNIT_HIERARCHY_LOAD_DEPTH = "initialUnitLoadDepth";
    public static final String NUMBER_PER_SPONSOR_HIERARCHY_GROUP = "numberPerSponsorHierarchyGroup";
    
    public static final String PROPOSAL_EDITABLECOLUMN_DATATYPE = "document.newMaintainableObject.dataType";
    public static final String PROPOSAL_EDITABLECOLUMN_DATALENGTH = "document.newMaintainableObject.dataLength";
    public static final String PROPOSAL_EDITABLECOLUMN_LOOKUPRETURN = "document.newMaintainableObject.lookupReturn";

    public static final String PDF_REPORT_CONTENT_TYPE = "application/pdf";
    public static final String PDF_FILE_EXTENSION = ".pdf";
    public static final String GENERIC_SPONSOR_CODE = "GENERIC_SPONSOR_CODE";
    public static final String CUSTOM_ERROR = "error.custom";
    public static final String SUBAWARD_FILE_REQUIERED = "newSubAward.subAwardFile.required";
    public static final String SUBAWARD_FILE = "newSubAward.subAwardFile";
    public static final String SUBAWARD_FILE_INVALID = "newSubAward.subAwardFile.inValid";
    public static final String SUBAWARD_FILE_NOT_POPULATED = "newSubAward.subAwardFile.notExtracted";
    public static final String SUBAWARD_ORG_NAME = "newSubAward.organizationName";
    public static final String SUBAWARD_ORG_NAME_REQUIERED = "newSubAward.organizationName.required";
    public static final String SUBAWARD_ORG_NAME_INVALID = "newSubAward.organizationName.invalid";
    
    // sponsor hierarchy
    public static final String HIERARCHY_NAME = "hierarchyName";
    public static final String SPONSOR_CODE = "sponsorCode";
    public static final String SPONSOR_HIERARCHY_SEPARATOR_C1C = ";1;";
    public static final String SPONSOR_HIERARCHY_SEPARATOR_P1P = "#1#";
    public static final String SPONSOR_HIERARCHY_PRINTING_NAME_PARAM = "SPONSOR_HIERARCHY_FOR_PRINTING";
    
    public static final String SUBAWARD_FILE_NOT_EXTRACTED = "newSubAward.subAwardFile.notExtracted";
    
    public static final boolean GRANTS_GOV_LINK_TARGET_POPUP = true;
    public static final String BUDGET_CATEGORY_TYPE_PERSONNEL = "budgetCategoryType.personnel";
    
    public static final String KIM_QUAL_ATTR_PROPOSAL_KEY = "proposal";

    //Award
    public static final String MAPPING_AWARD_BASIC = "basic";
    public static final String MAPPING_AWARD_HOME_PAGE = "home";
    public static final String MAPPING_AWARD_CONTACTS_PAGE = "contacts";
    public static final String MAPPING_AWARD_COMMITMENTS_PAGE = "commitments";
    public static final String MAPPING_AWARD_TIME_AND_MONEY_PAGE = "timeAndMoney";
    public static final String MAPPING_AWARD_PAYMENT_REPORTS_AND_TERMS_PAGE = "paymentReportsAndTerms";
    public static final String MAPPING_AWARD_SPECIAL_REVIEW_PAGE = "specialReview";
    public static final String MAPPING_AWARD_CUSTOM_DATA_PAGE = "customData";
    public static final String MAPPING_AWARD_QUESTIONS_PAGE = "questions";
    public static final String MAPPING_AWARD_PERMISSIONS_PAGE = "permissions";
    public static final String MAPPING_AWARD_NOTES_AND_ATTACHMENTS_PAGE = "notesAndAttachments";
    public static final String MAPPING_AWARD_ACTIONS_PAGE = "awardActions";
    public static final String MAPPING_AWARD_MEDUSA_PAGE = "medusa";
    public static final String MAPPING_REPORTS_TAB = "reports";
    public static final String MAPPING_AWARD_BUDGET_VERSIONS_PAGE = "budgets";
    
    //COI
    public static final String MAPPING_COI_EDIT_LIST = "editList";
    public static final String FINANCIAL_ENTITY_STATUS_ACTIVE = "active";
    public static final String FINANCIAL_ENTITY_STATUS_INACTIVE = "inactive";
    public static final String ENTITY_OWNERSHIP_TYPE_CODE_PUBLIC = "P";
    public static final String ENTITY_OWNERSHIP_TYPE_CODE_PRIVATE = "V";
    
    public static final String AWARD_SEQUENCE_AWARD_NUMBER = "SEQ_AWARD_AWARD_NUMBER";
    
    public static final String LINKED_FUNDING_PROPOSALS_KEY = "linkedProposals";
    public static final String DEFAULT_TXN_TYPE_COPIED_AWARD = "TXN_TYPE_DEF_COPIED_AWARD";
    public static final String DEF_CURRENT_ACTION_COMMENT_COPIED_AWARD = "Copied Award" ;
    
    // financial system integration
    public static final String FIN_SYSTEM_INTEGRATION_ON_OFF_PARAMETER = "FIN_SYSTEM_INTEGRATION_ON";
    public static final String FIN_SYSTEM_INTEGRATION_ON = "ON";
    //CFDA
    public static final int MAX_ALLOWABLE_CFDA_PGM_TITLE_NAME = 300;
    public static final String CFDA_MAINT_TYP_ID_AUTOMATIC = "Automatic";
    public static final String CFDA_MAINT_TYP_ID_MANUAL = "Manual";
    public static final String CFDA_GOV_URL_PARAMETER = "CFDA_GOV_URL";
    public static final String CFDA_GOV_LOGIN_USERNAME = "anonymous";
    public static final String CFDA_CRON_START_TIME_PARAMETER = "CFDA_BATCH_JOB_CRON_START_TIME";
    public static final String CFDA_BATCH_JOB_CRON_EXPRESSION_PARAMETER = "CFDA_BATCH_JOB_CRON_EXPRESSION";
    public static final String DEFAULT_CRON_EXPRESSION = "0 0 5 * * ?";
    public static final String CFDA_BATCH_NOTIFICATION_RECIPIENT_PARAMETER = "CFDA_BATCH_NOTIFICATION_RECIPIENT";



    //Award Hierarchy Sync
    public static final String AWARD_APPLY_SYNC_NODE_NAME = "ApplySync";
    public static final String AWARD_SYNC_HAS_SYNC_NODE_NAME = "hasSync";
    public static final String AWARD_SYNC_VALIDATION_NODE_NAME = "SyncValidationApproval";
    public static final String AWARD_SYNC_NOT_APPLICABLE = "Not applicable";
    
  //Institutional Proposal
    public static final String MAPPING_INSTITUTIONAL_PROPOSAL_BASIC = "basic";
    public static final String MAPPING_INSTITUTIONAL_PROPOSAL_HOME_PAGE = "home";
    public static final String MAPPING_INSTITUTIONAL_PROPOSAL_CONTACTS_PAGE = "contacts";
    public static final String MAPPING_INSTITUTIONAL_PROPOSAL_SPECIAL_REVIEW_PAGE = "specialReview";
    public static final String MAPPING_INSTITUTIONAL_PROPOSAL_CUSTOM_DATA_PAGE = "customData";
    public static final String MAPPING_INSTITUTIONAL_PROPOSAL_INTELLECTUAL_PROPERTY_REVIEW_PAGE = "intellectualPropertyReview";
    public static final String MAPPING_INSTITUTIONAL_PROPOSAL_DISTRIBUTION_PAGE = "distribution";
    public static final String MAPPING_INSTITUTIONAL_PROPOSAL_ACTIONS_PAGE = "institutionalProposalActions";
    public static final String MAPPING_INSTITUTIONAL_PROPOSAL_MEDUSA_PAGE = "medusa";
    
    public static final String INSTITUTIONAL_PROPSAL_PROPSAL_NUMBER_SEQUENCE = "SEQ_PROPOSAL_PROPOSAL_ID";
    
    public static final String COST_SHARE_COMMENT_TYPE_CODE = "9";
    public static final String FANDA_RATE_COMMENT_TYPE_CODE = "8";
    public static final String PAYMENT_AND_INVOICES_COMMENT_TYPE_CODE = "1";
    public static final String PREAWARD_SPONSOR_AUTHORIZATION_COMMENT_TYPE_CODE = "18";
    public static final String PREAWARD_INSTITUTIONAL_AUTHORIZATION_COMMENT_TYPE_CODE = "19";
    public static final String BENEFITS_RATES_COMMENT_TYPE_CODE = "20";
    public static final String GENERAL_COMMENT_TYPE_CODE = "2";
    public static final String FISCAL_REPORT_COMMENT_TYPE_CODE = "3";
    public static final String INTELLECTUAL_PROPERTY_COMMENT_TYPE_CODE = "4";
    public static final String PROCUREMENT_COMMENT_TYPE_CODE = "5";
    public static final String PROPERTY_COMMENT_TYPE_CODE = "6";
    public static final String SPECIAL_RATE_COMMENT_TYPE_CODE = "7";
    public static final String SPECIAL_REVIEW_COMMENT_TYPE_CODE = "10";
    public static final String PROPOSAL_SUMMARY_COMMENT_TYPE_CODE = "12";
    public static final String PROPOSAL_COMMENT_TYPE_CODE = "13";
    public static final String PROPOSAL_IP_REVIEW_COMMENT_TYPE_CODE = "16";
    public static final String CURRENT_ACTION_COMMENT_TYPE_CODE = "21";
    public static final boolean AWARD_COMMENT_INCLUDE_IN_CHECKLIST = true;
    public static final boolean AWARD_COMMENT_EXCLUDE_FROM_CHECKLIST = false;
    
   
    public static final Integer MIN_FISCAL_YEAR = 1900;
    public static final Integer MAX_FISCAL_YEAR = 2499;
    
    public static final String PARAMETER_MODULE_AWARD = "KC-AWARD";
    public static final String SPECIAL_REVIEW_NUMBER = "SPECIAL_REVIEW_NUMBER";
    public static final String LEFT_SQUARE_BRACKET = "[";
    public static final String RIGHT_SQUARE_BRACKET = "]";
    
    public static final String REPORT_CLASSES_KEY_FOR_INITIALIZE_OBJECTS = "reportClasses";
    public static final String NEW_AWARD_REPORT_TERMS_LIST_KEY_FOR_INITIALIZE_OBJECTS = "newAwardReportTermList";
    public static final String NEW_AWARD_REPORT_TERM_RECIPIENTS_LIST_KEY_FOR_INITIALIZE_OBJECTS = "newAwardReportTermRecipientsList";
    public static final String REPORT_CLASS_FOR_PAYMENTS_AND_INVOICES_PANEL = "reportClassForPaymentsAndInvoicesPanel";
    
    public static final String UNIT_CONTACTS_DEFAULT_GROUP_FLAG = "U";
    
    //Award Audit Rules
    public static final String CONTACT_AUDIT_RULES_ERROR_KEY = "document.contactAuditRules";
    public static final String REPORT_TERMS_AUDIT_RULES_ERROR_KEY = "document.reportTermsAuditRules";
    public static final String TERMS_AUDIT_RULES_ERROR_KEY = "document.termsAuditRules";
    public static final String PAYMENT_AND_INVOICES_AUDIT_RULES_ERROR_KEY = "document.paymentsAuditRules";
    public static final String SUBAWARD_AUDIT_RULES_ERROR_KEY = "document.subawardAuditRules";
    public static final String AWARD_PAGE = "award";
    public static final String COST_SHARE_PANEL_ANCHOR = "CostShare";
    public static final String COST_SHARE_PANEL_NAME = "Cost Share";
    public static final String FANDA_RATES_PANEL_ANCHOR = "Rates";
    public static final String FANDA_RATES_PANEL_NAME = "Rates";
    public static final String REPORTS_PANEL_ANCHOR = "Reports";
    public static final String REPORTS_PANEL_NAME = "Reports";
    public static final String CONTACTS_PANEL_NAME = "Contacts";
    public static final String CONTACTS_PANEL_ANCHOR = "Contacts";
    public static final String TERMS_PANEL_ANCHOR = "Terms";
    public static final String TERMS_PANEL_NAME = "Terms";
    public static final String PAYMENT_AND_INVOICES_PANEL_ANCHOR = "Payments";
    public static final String PAYMENT_AND_INVOICES_PANEL_NAME = "Payments";
    public static final String SUBAWARD_PANEL_NAME = "Subaward";
    public static final String SUBAWARD_PANEL_ANCHOR = "Subaward";



    // Award Rules
    public static final String COST_SHARE_ADD_ACTION_PROPERTY_KEY = "newAwardCostShare";
    
    // IRB
    public static final String DEFAULT_PROTOCOL_ORGANIZATION_TYPE_CODE = "1";
    public static final String DEFAULT_PROTOCOL_ORGANIZATION_ID = "000001";
    public static final String DEFAULT_PROTOCOL_STATUS_CODE = "100";
    public static final String PROPERTY_PROTOCOL_STATUS = "protocolStatus";
    public static final String PARAMETER_PROTOCOL_PERSON_TRAINING_SECTION = "protocolPersonTrainingSectionRequired";
    public static final String PARAMETER_PROTOCOL_ATTACHMENT_DEFAULT_SORT = "protocolAttachmentDefaultSort";
    public static final Integer PROTOCOL_RISK_LEVEL_COMMENT_LENGTH = 40;
    public static final Integer PROTOCOL_REFERENCE_COMMENT_LENGTH = 250;
    public static final String ACTIVE_STATUS_LITERAL = "Active";
    public static final String INACTIVE_STATUS_LITERAL = "Inactive";
    public static final String CONFIRM_DELETE_PROTOCOL_USER_KEY = "confirmDeleteProtocolUser";
    public static final String PROPERTY_PROTOCOL_NUMBER = "protocolNumber";
    public static final String PARAMETER_IRB_COMM_SELECTION_DURING_SUBMISSION = "IRB_COMM_SELECTION_DURING_SUBMISSION";
    public static final String PARAMETER_IRB_DISPLAY_REVIEWER_NAME = "IRB_DISPLAY_REVIEWER_NAME";
    public static final String PARAMETER_IRB_DISPLAY_REVIEWER_NAME_TO_PROTOCOL_PERSONNEL = "IRB_DISPLAY_REVIEWER_NAME_TO_PROTOCOL_PERSONNEL";
    public static final String PARAMETER_IRB_DISPLAY_REVIEWER_NAME_TO_PI = "IRB_DISPLAY_REVIEWER_NAME_TO_PI";
    public static final String PARAMETER_IRB_DISPLAY_REVIEWER_NAME_TO_OTHER_PROTOCOL_PERSONNEL = "IRB_DISPLAY_REVIEWER_NAME_TO_OTHER_PROTOCOL_PERSONNEL";
    public static final String PARAMETER_IRB_DISPLAY_REVIEWER_NAME_TO_REVIEWERS = "IRB_DISPLAY_REVIEWER_NAME_TO_REVIEWERS";
    public static final String PARAMETER_IRB_DISPLAY_REVIEWER_NAME_TO_ACTIVE_COMMITTEE_MEMBERS = "IRB_DISPLAY_REVIEWER_NAME_TO_ACTIVE_COMMITTEE_MEMBERS";
    
    public static final String PROTOCOL_FUNDING_SOURCE_TYPE_CODE_FIELD = "protocolHelper.newFundingSource.fundingSourceTypeCode";
    public static final String PROTOCOL_FUNDING_SOURCE_ID_FIELD = "protocolHelper.newFundingSource.fundingSource";
    public static final String PROTOCOL_FUNDING_SOURCE_NUMBER_FIELD = "protocolHelper.newFundingSource.fundingSourceNumber";
    public static final String PROTOCOL_FUNDING_SOURCE_NAME_FIELD = "protocolHelper.newFundingSource.fundingSourceName";
    public static final String PROTOCOL_FUNDING_SOURCE_TITLE_FIELD = "protocolHelper.newFundingSource.fundingSourceTitle";
    
    public static final Integer AFFILIATION_FACULTY_SUPERVISOR_TYPE = 5;
    public static final Integer AFFILIATION_STUDENT_INVESTIGATOR_TYPE = 4;
    
    public static final String PROTOCOL_CREATE_AMENDMENT_KEY = "actionHelper.protocolCreateAmendment";
    public static final String PROTOCOL_MODIFY_AMENDMENT_KEY = "actionHelper.protocolModifyAmendment";
    public static final String PROTOCOL_CREATE_RENEWAL_WITH_AMENDMENT_KEY = "actionHelper.protocolCreateRenewalWithAmendment";
    public static final String PROTOCOL_CREATE_RENEWAL_SUMMARY_KEY = "actionHelper.renewalSummary";
    
    // Protocol edit modes
    public static final String CAN_VIEW_REVIEW_COMMENTS = "viewReviewComments";
    // Risk Level
    public static final String PROTOCOL_UPDATE_RISK_LEVEL_KEY = "document.protocol.protocolRiskLevels";
    
    // Protocol protocol page audit rules
    public static final String PROTOCOL_PROTOCOL_PAGE = "protocol";
    public static final String PROTOCOL_PROTOCOL_PANEL_NAME = "Protocol";
    
    // Protocol Research Area Audit Rules
    public static final String PROTOCOL_PROTOCOL_RESEARCH_AREA_PANEL_ANCHOR = "Area of Research";
    public static final String PROTOCOL_RESEARCH_AREA_KEY = "document.protocolList[0].newDescription";
    
    // Protocol Funding Source Audit Rules
    public static final String PROTOCOL_PROTOCOL_FUNDING_SRC_PANEL_ANCHOR = "Funding Source";
    public static final String PROTOCOL_FUNDING_SRC_KEY = "document.title";
    
    //Protocol Personnel Audit Rules
    public static final String PROTOCOL_PERSONNEL_PAGE = "personnel";
    public static final String PROTOCOL_PERSONNEL_PANEL_ANCHOR = "Add Personnel:";
    public static final String PROTOCOL_PERSONNEL_PANEL_NAME = "Personnel";
    public static final String PROTOCOL_PRINCIPAL_INVESTIGATOR_KEY = "newProtocolPerson*";

    public static final String VIEW_ONLY = "viewOnly";
    public static final String POPUP_VIEW_ONLY = "popupViewOnly";
    
    // Protocol Action Rules
    public static final String PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY = "actionHelper.protocolSubmitAction";
    public static final String PROTOCOL_CLOSE_REQUEST_PROPERTY_KEY = "actionHelper.protocolCloseReqeust";
    public static final String PROTOCOL_SUSPEND_REQUEST_PROPERTY_KEY = "actionHelper.protocolSuspendRequest";
    public static final String PROTOCOL_CLOSE_ENROLLMENT_REQUEST_PROPERTY_KEY = "actionHelper.protocolCloseEnrollmentRequest";
    public static final String PROTOCOL_REOPEN_ENROLLMENT_REQUEST_PROPERTY_KEY = "actionHelper.protocolReOpenEnrollmentRequest";
    public static final String PROTOCOL_DATA_ANALYSIS_REQUEST_PROPERTY_KEY = "actionHelper.protocolDataAnalysisRequest";
    public static final String PROTOCOL_TERMINATE_REQUEST_PROPERTY_KEY = "actionHelper.protocolTerminateRequest";
    public static final String PROTOCOL_ASSIGN_CMT_SCHED_ACTION_PROPERTY_KEY = "actionHelper.assignCmtSchedBean";
    public static final String PROTOCOL_ASSIGN_TO_AGENDA_ACTION_PROPERTY_KEY = "actionHelper.assignToAgendaBean";
    public static final String PROTOCOL_ASSIGN_REVIEWERS_PROPERTY_KEY = "actionHelper.protocolAssignReviewersBean";
    public static final String PROTOCOL_ADMIN_CORRECTION_PROPERTY_KEY = "actionHelper.protocolAdminCorrectionBean";
    public static final String PROTOCOL_MANAGE_REVIEW_COMMENTS_KEY = "actionHelper.protocolManageReviewCommentsBean";
    public static final String PROTOCOL_COMMITTEE_DECISION_ACTION_PROPERTY_KEY = "actionHelper.committeeDecision";
    public static final String PROTOCOL_GRANT_EXEMPTION_ACTION_PROPERTY_KEY = "actionHelper.protocolGrantExemptionBean";
    public static final String PROTOCOL_FULL_APPROVAL_ACTION_PROPERTY_KEY = "actionHelper.protocolFullApprovalBean";
    public static final String PROTOCOL_EXPEDITED_APPROVAL_ACTION_PROPERTY_KEY = "actionHelper.protocolExpeditedApprovalBean";
    public static final String PROTOCOL_RESPONSE_APPROVAL_ACTION_PROPERTY_KEY = "actionHelper.protocolResponseApprovalBean";
    public static final String PROTOCOL_CLOSE_ACTION_PROPERTY_KEY = "actionHelper.protocolCloseBean";
    public static final String PROTOCOL_CLOSE_ENROLLMENT_ACTION_PROPERTY_KEY = "actionHelper.protocolCloseEnrollmentBean";
    public static final String PROTOCOL_DEFER_ACTION_PROPERTY_KEY = "actionHelper.protocolDeferBean";
    public static final String PROTOCOL_DISAPPROVE_ACTION_PROPERTY_KEY = "actionHelper.protocolDisapproveBean";
    public static final String PROTOCOL_EXPIRE_ACTION_PROPERTY_KEY = "actionHelper.protocolExpireBean";
    public static final String PROTOCOL_IRB_ACKNOWLEDGEMENT_ACTION_PROPERTY_KEY = "actionHelper.protocolIrbAcknowledgementBean";
    public static final String PROTOCOL_PERMIT_DATA_ANALYSIS_ACTION_PROPERTY_KEY = "actionHelper.protocolPermitDataAnalysisBean";
    public static final String PROTOCOL_REOPEN_ENROLLMENT_ACTION_PROPERTY_KEY = "actionHelper.protocolReopenEnrollmentBean";
    public static final String PROTOCOL_SMR_ACTION_PROPERTY_KEY = "actionHelper.protocolSMRBean";
    public static final String PROTOCOL_SRR_ACTION_PROPERTY_KEY = "actionHelper.protocolSRRBean";
    public static final String PROTOCOL_SUSPEND_ACTION_PROPERTY_KEY = "actionHelper.protocolSuspendBean";
    public static final String PROTOCOL_SUSPEND_BY_DSMB_ACTION_PROPERTY_KEY = "actionHelper.protocolSuspendByDsmbBean";
    public static final String PROTOCOL_TERMINATE_ACTION_PROPERTY_KEY = "actionHelper.protocolTerminateBean";
    public static final String PROTOCOL_UNDO_LASTACTION_PROPERTY_KEY = "actionHelper.undoLastActionBean";
    public static final String PROTOCOL_MODIFY_SUBMISSION_KEY = "actionHelper.protocolModifySubmissionBean";
    public static final String PROTOCOL_ABANDON_ACTION_PROPERTY_KEY = "actionHelper.protocolAbandonBean";
    
    //Online Review
    public static final String PROTOCOL_ONLINE_REVIEW_KEY="onlineReviewsActionHelper.protocolOnlineReviewsReviewCommentsList[%s]";
    public static final String ONLINE_REVIEW_ROUTE_NODE_ADMIN_INITIAL_REVIEW="IRBAdminInitialReview";
    public static final String ONLINE_REVIEW_ROUTE_NODE_ONLINE_REVIEWER="OnlineReviewer";
    public static final String ONLINE_REVIEW_ROUTE_NODE_ADMIN_REVIEW="IRBAdminReview";
    
    //Protocol Summary Print View
    public static final Integer PROTOCOL_SUMMARY_VOTINGCOMMENTS = 250;
    
    //Protocol History View
    public static final String PROTOCOL_HISTORY_DATE_RANGE_FILTER_START_DATE_KEY = "actionHelper.filteredHistoryStartDate";
    public static final String PROTOCOL_HISTORY_DATE_RANGE_FILTER_END_DATE_KEY = "actionHelper.filteredHistoryEndDate";
    
    //Protocol Attachment Notification
    public static final Integer PROTOCOL_ATTACHMENT_NOTIFICATION_COMMENTS = 250;
    
    //Protocol Workflow
    public static final String PROTOCOL_INITIATED_ROUTE_NODE_NAME = "Initiated";
    public static final String PROTOCOL_IRBREVIEW_ROUTE_NODE_NAME = "IRBReview";
    public static final String PROTOCOL_APPROVAL_NODE_NAME = "DepartmentReview";
    public static final String PROTOCOL_UNDO_APPROVE_ANNOTATION = "Undoing Approve Action";
    
    // Committee
    public static final String COMMITTEE_PROPERTY_KEY = "committee";
    public static final String COMMITTEE_VOTING_MEMBERSHIP_TYPE_CODE = "COMMITTEE_VOTING_MEMBERSHIP_TYPE_CODE";
    
    public static final String CONFIRM_DELETE_PERMISSIONS_USER_KEY = "confirmDeletePermissionsUser";
   
    public static final String MAPPING_CHECKLIST_ITEM_DESCRIPITION = "checkListItemDescription";
    
    // Correspondence
    public static final String DEFAULT_CORRESPONDENCE_TEMPLATE = "DEFAULT";
    public static final String CORRESPONDENCE_TEMPLATE_CONTENT_TYPE_1 = "text/xml";
    public static final String CORRESPONDENCE_TEMPLATE_CONTENT_TYPE_2 = "application/xml";
    public static final String PROTOCOL_RENEWAL_REMINDERS = "1";
    public static final String REMINDER_TO_IRB_NOTIFICATIONS = "2";
    public static final String PROTOCOL_ACTION_TYPE_CODE_RENEWAL_REMINDER_GENERATED = "111";
    public static final String PROTOCOL_ACTION_TYPE_CODE_IRB_NOTIFICATION_GENERATED = "112";
    

    // Protocol Linking  System Parameters 
    public static final String ENABLE_PROTOCOL_TO_AWARD_LINK  = "irb.protocol.award.linking.enabled";
    public static final String ENABLE_PROTOCOL_TO_DEV_PROPOSAL_LINK = "irb.protocol.development.proposal.linking.enabled";
    public static final String ENABLE_PROTOCOL_TO_PROPOSAL_LINK = "irb.protocol.institute.proposal.linking.enabled";
    
    public static final String PROTOCOL_TYPE_CODE_EXEMPT = "irb.protocol.protocoltype.exempt";
    
    // Questionnaire
    public static final String QUESTION_EXPLANATION = "E";
    public static final String QUESTION_POLICY = "P";
    public static final String QUESTION_REGULATION = "R";
    public static final String QUESTION_AFFIRMATIVE_QUESTION_CONVERSION = "A";
    public static final String QUESTION_NEGATIVE_QUESTION_CONVERSION = "N";
    
    public static final int QUESTION_RESPONSE_TYPE_YES_NO = 1;
    public static final int QUESTION_RESPONSE_TYPE_YES_NO_NA = 2;
    public static final int QUESTION_RESPONSE_TYPE_NUMBER = 3;
    public static final int QUESTION_RESPONSE_TYPE_DATE = 4;
    public static final int QUESTION_RESPONSE_TYPE_TEXT = 5;
    public static final int QUESTION_RESPONSE_TYPE_LOOKUP = 6;
    
    public static final String QUESTION_DOCUMENT_FIELD_QUESTION_TYPE_ID = "document.newMaintainableObject.businessObject.questionTypeId";
    public static final String QUESTION_DOCUMENT_FIELD_STATUS = "document.newMaintainableObject.businessObject.status";
    public static final String QUESTION_DOCUMENT_FIELD_DISPLAYED_ANSWERS = "document.newMaintainableObject.businessObject.displayedAnswers";
    public static final String QUESTION_DOCUMENT_FIELD_ANSWER_MAX_LENGTH = "document.newMaintainableObject.businessObject.answerMaxLength";
    public static final String QUESTION_DOCUMENT_FIELD_MAX_ANSWERS = "document.newMaintainableObject.businessObject.maxAnswers";
    public static final String QUESTION_DOCUMENT_FIELD_LOOKUP_CLASS = "document.newMaintainableObject.businessObject.lookupClass";
    public static final String QUESTION_DOCUMENT_FIELD_LOOKUP_RETURN = "document.newMaintainableObject.businessObject.lookupReturn";
    
    //Institutional Proposal Rules
    public static final String IP_COST_SHARE_ADD_ACTION_PROPERTY_KEY = "institutionalProposalCostShareBean.newInstitutionalProposalCostShare";
    public static final String IP_UNRECOVERED_FNA_ACTION_PROPERTY_KEY = "newInstitutionalProposalUnrecoveredFandA";
    
    //Institutional Proposal Audit Rules 
    public static final String GRADUATE_STUDENT_PANEL_ANCHOR = "GraduateStudent";
    public static final String GRADUATE_STUDENT_PANEL_NAME = "GraduateStudent";
    public static final String GRADUATE_STUDENT_AUDIT_RULES_ERROR_KEY = "document.graduateStudentAuditRules";
    
    //Award Hierarchy - Time And Money Constants
    public static final String AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT = "000000-00000";
    public static final String LAST_NODE_NEXT_VALUE = "lastNodeNextValue";
    
    //Award Template Constants
    public static final String AWARD_TEMP_RECPNT_CONTACT_TYPE_CODE_ROLODEX_ID_SEPARATOR = "***";
    
    // Questionnaire
    public static final String PARAMETER_MODULE_QUESTIONNAIRE = "KC-QUESTIONNAIRE";
    public static final String PARAMETER_COMPONENT_PERMISSION = "P";
    
    public static final String TIME_AND_MONEY_DOCUMENT_STRING_FOR_SESSION = "TIME_AND_MONEY";    
    public static final String KRA_EXTERNALIZABLE_IMAGES_URI_KEY = "kra.externalizable.images.url";
    public static final String KR_EXTERNALIZABLE_IMAGES_URI_KEY = "kr.externalizable.images.url";
    
    //
    public static final String KC_GENERIC_PARAMETER_NAMESPACE = "KC-GEN";
    public static final String KC_ALL_PARAMETER_DETAIL_TYPE_CODE = "All";
    public static final String MODULE_NAMESPACE_AWARD_BUDGET = "KC-AB";
    
    public static final String CUSTOM_ATTRIBUTE_DOCUMENT_DETAIL_TYPE_CODE = "DocumentType";
    public static final String CUSTOM_ATTRIBUTE_DOCUMENT_PARAM_NAME = "customAttributeDocumentType";

    public static final Object PROPOSAL_BUDGET = "proposalBudget";
    public static final Object AWARD_BUDGET = "awardBudget";
    public static final String ACTIVITY_TYPE_KEY = "document.developmentProposalList[0].activityTypeCode";
    public static final String FEDERAL_SPONSOR_TYPE_CODE = "FEDERAL_SPONSOR_TYPE_CODE";
    
    public static final String INSTITUTIONAL_PROPOSAL_NAMESPACE = "KC-IP";

    // Maintenance documents
    public static final String VALID_SPECIAL_REVIEW_APPROVAL_TYPE_CODE_KEY = "document.newMaintainableObject.approvalTypeCode";
    public static final String VALID_SPECIAL_REVIEW_APPROVAL_REVIEW_CODE_KEY = "document.newMaintainableObject.specialReviewCode";
    
    //WHITE SPACE 
    public char SPACE_SEPARATOR = 32;
    
    public static String CAN_CREATE_AWARD_KEY = "canCreateAward";
    
    // Protocol questionnaire page audit rules
    public static final String PROTOCOL_QUESTIONNAIRE_PAGE = "questionnaire";
    public static final String PROTOCOL_QUESTIONNAIRE_PANEL_NAME = "Questionnaire";
    public static final String PROTOCOL_QUESTIONNAIRE_PANEL_ANCHOR = "Questionnaire";
    public static final String PROTOCOL_QUESTIONNAIRE_KEY = "questionnaireHelper.answerHeaders[0].answers[0].answer";

    public static final String PRINT_LOGGING_ENABLE = "print.logging.enable";
    public static final String PRINT_LOGGING_DIRECTORY = "print.logging.directory";
    
    // Multi-Campus
    public static final String USER_CAMPUS_CODE_KEY = "userCampusCode";
    public static final String PARAMETER_MULTI_CAMPUS_ENABLED = "MULTI_CAMPUS_ENABLED";
    public static final String ENABLE_COST_SHARE_SUBMIT = "ENABLE_COST_SHARE_SUBMIT";
    public static final String INSTITUTE_PROPOSAL_OTHER_GROUP = "INSTITUTE_PROPOSAL_OTHER_GROUP";
    public static final String AWARD_BUDGET_DEFAULT_FNA_RATE_CLASS_CODE = "awardBudgetDefaultFnARateClassCode";
    public static final String AWARD_BUDGET_DEFAULT_UNDERRECOVERY_RATE_CLASS_CODE = "awardBudgetDefaultUnderrecoveryRateClassCode";
    //Subcontract
    public static final String MAPPING_SUBAWARD_PAGE = "subAward";
    public static final String MAPPING_FUNDING_SOURCE_PAGE = "fundingSource";
    public static final String MAPPING_AMOUNT_INFO_PAGE ="amountInfo";
    public static final String MAPPING_AMOUNT_RELEASED_PAGE="amountReleased";
    public static final String MAPPING_CONTACTS_PAGE="contacts";
    public static final String MAPPING_CLOSEOUT_PAGE="closeout";
    public static final String MAPPING_SUBAWARD_ACTION_PAGE="subAwardActions";
    // coi
    public static final String MODULE_NAMESPACE_COIDISCLOSURE = "KC-COIDISCLOSURE";
    
    //Negotiations
    public static final String NEGOTIATION_SEQUENCE_NAME = "NEGOTIATION_S";

    // Disclosure disclosure page audit rules
    public static final String COI_DISCLOSURE_DISCLOSURE_PAGE = "disclosure";
    public static final String COI_DISCLOSURE_DISCLOSURE_PANEL_NAME = "Disclosure";
    public static final String DISCLOSURE_FINANCIAL_ENTITY_PANEL_ANCHOR = "Financial Entity";
    public static final String DISCLOSURE_FINANCIAL_ENTITY_KEY = "document.coiDisclosureList[0].coiDiscDetails[%s].entityStatusCode";
    public static final String DISCLOSURE_MANUAL_FINANCIAL_ENTITY_KEY = "document.coiDisclosureList[0].coiDisclProjects[0].coiDiscDetails[%s].entityStatusCode";
    public static final String DISCLOSURE_ANNUAL_FINANCIAL_ENTITY_KEY = "document.coiDisclosureList[0].coiDisclEventProjects[%s].coiDiscDetails[%s].entityStatusCode";
    
    

}
