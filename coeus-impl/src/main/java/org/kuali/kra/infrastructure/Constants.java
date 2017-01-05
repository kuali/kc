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

package org.kuali.kra.infrastructure;

public interface Constants {
    //Generic constants
    String COLON=":";
    String COMMA=",";
    String SEMI_COLON=";";

    String KC_CORE_SERVICE_NAMESPACE = "http://kc.kuali.org/core/v5_0";
    String FINANCIAL_INTEGRATION_KC_SERVICE_NAMESPACE = "http://kc.kuali.org/kc-kfs/v2_0";
    String FINANCIAL_SYSTEM_SERVICE_NAMESPACE = "http://kfs.kuali.org/kc-kfs/v2_0";
    String DATASOURCE = "kraDataSource";
    String DATA_DICTIONARY_SERVICE_NAME = "dataDictionaryService";
    String BUSINESS_OBJECT_DICTIONARY_SERVICE_NAME = "businessObjectDictionaryService";
    String DATE_TIME_SERVICE_NAME = "dateTimeService";
    String BUSINESS_OBJECT_DAO_NAME = "businessObjectDao";
    String MAINTENANCE_NEW_ACTION = "New";
    
    String KEY_PERSON_ROLE = "KP";
    String PRINCIPAL_INVESTIGATOR_ROLE = "PI";
    String ALL_INVESTIGATORS = "Investigators";
    String PRINCIPAL_INVESTIGATOR = "Principle Investigator";
    String MULTI_PI_ROLE ="MPI";
    String CO_INVESTIGATOR_ROLE = "COI";
    String MULTIPLE_VALUE = "multipleValues";
    String KEYWORD_PANEL_DISPLAY = "proposaldevelopment.displayKeywordPanel";

    String PHS_RESTRAININGPLAN_PILEADERSHIPPLAN_ATTACHMENT="121";
    String PHS_RESEARCHPLAN_MULTIPLEPILEADERSHIPPLAN="46";
    int MENTORING_PLAN_ATTACHMENT_TYPE_CODE=8;
    String MENTORING_PLAN_ATTACHMENT="Mentoring Plan.pdf";


    String MAPPING_BASIC = "basic";
    String MAPPING_CUSTOM_DATA = "customData";
    String MAPPING_PROPOSAL_ACTIONS = "actions";
    String MAPPING_PROTOCOL_ACTIONS = "protocolActions";
    String MAPPING_PROTOCOL_HISTORY = "protocolHistory";
    String MAPPING_PROTOCOL_ONLINE_REVIEW = "onlineReview";
    String MAPPING_PROPOSAL_MEDUSA_PAGE = "proposalMedusa";
    String MAPPING_PROPOSAL_APPROVER_PAGE = "approverView";
    String MAPPING_PROPOSAL_DISPLAY_INACTIVE = "displayInactive";
    String MAPPING_CLOSE_PAGE = "closePage";
    String MAPPING_CLOSE = "close";
    String MAPPING_NARRATIVE_ATTACHMENT_RIGHTS_PAGE = "attachmentRights";
    String MAPPING_INSTITUTE_ATTACHMENT_RIGHTS_PAGE = "instituteAttachmentRights";
    String MAPPING_RESUBMISSION_PROMPT = "resubmissionPrompt";

    String FORCE_HOLDING_PAGE_FOR_ACTION_LIST = "forceHoldingForActionList";
    
    String PROPOSAL_PERSON_BIOGRAPHY_DEFAULT_DOC_TYPE = "DEFAULT_BIOGRAPHY_DOCUMENT_TYPE_CODE";
    String CREDIT_SPLIT_ENABLED_RULE_NAME = "proposaldevelopment.creditsplit.enabled";
    String CREDIT_SPLIT_ENABLED_FLAG = "creditSplitEnabledFlag";

    String PROP_PERSON_BIO_NUMBER = "proposalDevelopment.proposalPersonBiography.biographyNumber";
    String PROPOSAL_LOCATION_SEQUENCE_NUMBER = "proposalDevelopment.proposalLocation.locationSequenceNumber";
    String PROPOSAL_SPECIALREVIEW_NUMBER = "proposalDevelopment.proposalSpecialReview.specialReviewNumber";
    String PROPOSAL_PERSON_DEGREE_SEQUENCE_NUMBER = "proposalDevelopment.proposalPerson.degree.degreeSequenceNumber";
    String PROPOSAL_PERSON_NUMBER = "proposalDevelopment.proposalPerson.proposalPersonNumber";
    String PROPOSAL_NARRATIVE_TYPE_GROUP = "proposalNarrativeTypeGroup";
    String INSTITUTE_NARRATIVE_TYPE_GROUP = "instituteNarrativeTypeGroup";
    String INSTITUTIONAL_ATTACHMENT_TYPE_NAME = "Institutional Attachment";
    String PERSONNEL_ATTACHMENT_TYPE_NAME = "Personnel Attachment";
    String PROPOSAL_ATTACHMENT_TYPE_NAME = "Proposal Attachment";
    String NEW_NARRATIVE_USER_RIGHTS_PROPERTY_KEY = "newNarrativeUserRight";
    String INVALID_FILE_NAME_ERROR_CODE = "2";
    String INVALID_FILE_NAME_CHECK_PARAMETER = "INVALID_FILE_NAME_CHECK";
    String RETURN_NARRATIVE_TYPE_CODE_PARAM = "returnNarrativeTypeCode";
    String APPROVE_NARRATIVE_TYPE_CODE_PARAM = "approveNarrativeTypeCode";
    String HIDE_AND_DEFAULT_PROP_DEV_DOC_DESC_PARAM = "HIDE_AND_DEFAULT_PROP_DEV_DOC_DESC";

    String PERSON_ROLE_PARAMETER_PREFIX = "personrole.";
    String NIH_SPONSOR_ACRONYM = "NIH";
    String NIH_SPONSOR_CODE = "000340";
    String LOCAL_PRINT_FORM_SPONSOR_CODE = "LOCAL_PRINT_FORM_SPONSOR_CODE";
    String SPONSOR_LEVEL_HIERARCHY = "sponsorLevelHierarchy";
    String SPONSOR_HIERARCHY_NAME = "sponsorGroupHierarchyName";

    String SPONSOR_HIERARCHY_ROUTING = "Routing";

    String MODULE_NAMESPACE_IRB = "KC-PROTOCOL";
    String MODULE_DOC_ROLE_NAMESPACE_PROTOCOL = "KC-PROTOCOL-DOC"; 
    String PARAMETER_MODULE_PROTOCOL_REFERENCEID1 = "irb.protocol.referenceID1";
    String PARAMETER_MODULE_PROTOCOL_REFERENCEID2 = "irb.protocol.referenceID2";
    String PARAMETER_MODULE_PROTOCOL_BILLABLE = "irb.protocol.billable";
    String PROTOCOL_DEVELOPMENT_PROPOSAL_LINKING_ENABLED_PARAMETER = "irb.protocol.development.proposal.linking.enabled";

    String PARAMETER_MODULE_IACUC_PROTOCOL_BILLABLE = "iacuc.protocol.billable";
    String MODULE_NAMESPACE_SYSTEM = "KC-SYS";
    String MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT = "KC-PD";
    String MODULE_NAMESPACE_AWARD = "KC-AWARD"; 
    String MODULE_NAMESPACE_NEGOTIATION = "KC-NEGOTIATION";
    String MODULE_NAMESPACE_SUBAWARD = "KC-SUBAWARD";
    String MODULE_NAMESPACE_GEN = "KC-GEN";
    String MODULE_NAMESPACE_IACUC = "KC-IACUC"; 
    String MODULE_NAMESPACE_INSITUTIONAL_PROPOSAL = "KC-IP";
    String MODULE_NAMESPACE_TIME_AND_MONEY = "KC-T";
    String MODULE_NAMESPACE_UNIT = "KC-UNT";
    String MODULE_NAMESPACE_MAINTENANCE = "KC-M";

    String PESSIMISTIC_LOCKING_EXPIRATION_AGE = "pessimisticLocking.expirationAge";
    
    String PARAMETER_COMPONENT_DOCUMENT = "Document";
    String INSTITUTE_NARRATIVE_TYPE_GROUP_CODE = "O";
    String PROPOSAL_NARRATIVE_TYPE_GROUP_CODE = "P";    
    String NARRATIVE_MODULE_STATUS_COMPLETE = "C";
    String NARRATIVE_MODULE_STATUS_INCOMPLETE = "I";

    String ABSTRACTS_PROPERTY_KEY = "newProposalAbstract";
    String DEADLINE_DATE_KEY = "document.developmentProposalList[0].deadlineDate";
    String PRIME_SPONSOR_KEY = "document.developmentProposalList[0].primeSponsorCode";
    String PROJECT_TITLE_KEY = "document.developmentProposalList[0].title";

    String AUDIT_ERRORS = "Error";
    String GRANTSGOV_ERROR_SEVIRITY_KEY = "Grants.Gov Error";
    String AUDIT_WARNINGS = "Warnings";
    String GRANTSGOV_ERRORS= "Grants.Gov Errors";
    String KNS_AUDIT_ERRORS = AUDIT_ERRORS;
    
    String CURRENT_PENDING_REPORT_GROUP_NAME = "CURRENT_PENDING_REPORT_GROUP_NAME";
    String PROPOSAL_PAGE = "proposal";
    String CUSTOM_ATTRIBUTES_PAGE = "customData";
    String CERTIFICATION_PAGE = "PropDev-CertificationView-Page";
    String QUESTIONS_PAGE = "PropDev-QuestionnairePage";
    String PROP_DEV_PERMISSIONS_PAGE = "PropDev-PermissionsPage";
    String PERMISSIONS_PAGE = "permissions";
    String PROPOSAL_ACTIONS_PAGE = "actions";
    String HIERARCHY_PAGE = "hierarchy";
    String ATTACHMENTS_PAGE = "abstractsAttachments";
    String SPONSOR_PROGRAM_INFORMATION_PANEL_ANCHOR = "SponsorProgramInformation";
    String SPONSOR_PROGRAM_INFORMATION_PANEL_NAME = "Sponsor & Program Information";
    String REQUIRED_FIELDS_PANEL_ANCHOR = "RequiredFieldsforSavingDocument";
    String REQUIRED_FIELDS_PANEL_NAME = "Required Fields for Saving Document ";
    
    String BUDGET_PARAMETERS_PAGE_METHOD = "summary";
    String BUDGET_PARAMETERS_OVERVIEW_PANEL_ANCHOR = "topOfForm";
    String BUDGET_PARAMETERS_OVERVIEW_PANEL_NAME = "Budget Overview";
    String BUDGET_PARAMETERS_TOTALS_PANEL_ANCHOR = "BudgetPeriodsTotals";
    String BUDGET_PARAMETERS_TOTALS_PANEL_NAME = "Budget Periods & Totals";
    String BUDGET_EXPENSES_PAGE_METHOD = "budgetExpenses";
    String BUDGET_EXPENSES_OVERVIEW_PANEL_ANCHOR = "BudgetOvervieV";
    String INVALID_TIME = "Invalid Time";
    // Proposal Document Property Constants
    String PROPOSAL_NUMBER = "proposalNumber";
    String BUDGET_VERSION_PANEL_NAME = "Budget Versions";

    String PROPOSAL_DEVELOPMENT_CREATE_IACUC_PROTOCOL_ENABLED_PARAMETER = "ENABLE_CREATE_PROPOSAL_TO_IACUC_PROTOCOL";
    String PROPOSAL_DEVELOPMENT_CREATE_IRB_PROTOCOL_ENABLED_PARAMETER = "ENABLE_CREATE_PROPOSAL_TO_IRB_PROTOCOL";
    String ALTERNATE_NOTIFY_IRB_ACTION_PARAM = "ALTERNATE_NOTIFY_IRB_ACTION_PARAM";
    String ALTERNATE_NOTIFY_IACUC_ACTION_PARAM = "ALTERNATE_NOTIFY_IACUC_ACTION_PARAM";

    // Key Personnel Mojo
    String KEY_PERSONNEL_PAGE = "PropDev-PersonnelPage";
    String CREDIT_ALLOCATION_PAGE = "PropDev-CreditAllocationPage";
    String PROPOSAL_PERSON_INVESTIGATOR = "investigator";
    String KEY_PERSONNEL_PANEL_ANCHOR = "PropDev-PersonnelPage-Section";
    String KEY_PERSONNEL_PANEL_NAME = "Key Personnel";
    String PERSON_EDITABLE_FIELD_NAME_PROPERTY_KEY = "document.newMaintainableObject.fieldName";
    String INVESTIGATOR_CREDIT_TYPE_CODE_PROPERTY_KEY = "code";
    String EMPTY_STRING = "";
    String PRINCIPAL_INVESTIGATOR_KEY = "newProposalPerson";
    String CREDIT_SPLIT_KEY = "document.developmentProposalList[0].creditSplit";
    String PERSON_CERTIFICATE = "proposalPersonCertificate";
    String PERSON_COMMENT = "personComment";
    int ERA_COMMONS_USERNAME_MIN_LENGTH = 6;
    
    /*Proposal notification constants*/
    String DATA_OVERRIDE_NOTIFICATION_ACTION = "102";
    String DATA_OVERRIDE_CONTEXT = "Proposal Data Override";
    String PROPOSAL_DATA_OVVERRIDE_ACTION_TYPE_CODE = "103";
   
    /* set values for ynq */
    Integer ANSWER_YES_NO = 2;
    Integer ANSWER_YES_NO_NA = 3;
    String QUESTION_TYPE_PROPOSAL = "P";
    String QUESTION_TYPE_INDIVIDUAL = "I"; // Investigator - Certification questions
    String YNQ_EXPLANATION_REQUIRED = "Explanation required: if answer = ";
    String YNQ_REVIEW_DATE_REQUIRED = "Date required: if answer = ";
	String STATUS_ACTIVE = "A";
	String STATUS_INACTIVE = "I";
    String ANSWER_NA = "X";

    String DEFAULT_DATE_FORMAT_PATTERN = "MM/dd/yyyy";
    String DEFAULT_TIME_FORMAT_PATTERN = "h:mm a";
    String MODULE_NAMESPACE_BUDGET = "KC-B";
    
    // Budget Versions Constants
    String BUDGET_STATUS_COMPLETE_CODE = "budgetStatusCompleteCode";
    String BUDGET_STATUS_INCOMPLETE_CODE = "budgetStatusIncompleteCode";
    String BUDGET_DEFAULT_OVERHEAD_RATE_CODE = "defaultOverheadRateClassCode";
    String BUDGET_DEFAULT_OVERHEAD_RATE_TYPE_CODE = "defaultOverheadRateTypeCode";
    String BUDGET_DEFAULT_UNDERRECOVERY_RATE_CODE = "defaultUnderrecoveryRateClassCode";
    String BUDGET_DEFAULT_MODULAR_FLAG = "defaultModularFlag";
    String BUDGET_JOBCODE_VALIDATION_ENABLED = "JOBCODE_VALIDATION_ENABLED";    
    // Budget Distribution And Income
    String BUDGET_CURRENT_FISCAL_YEAR = "budgetCurrentFiscalYear";
    String BUDGET_COST_SHARING_APPLICABILITY_FLAG = "budgetCostSharingApplicabilityFlag";
    String BUDGET_UNRECOVERED_F_AND_A_APPLICABILITY_FLAG = "budgetUnrecoveredFandAApplicabilityFlag";
    String BUDGET_COST_SHARING_ENFORCEMENT_FLAG = "budgetCostSharingEnforcementFlag";
    String BUDGET_UNRECOVERED_F_AND_A_ENFORCEMENT_FLAG = "budgetUnrecoveredFandAEnforcementFlag";
    
    //budget status codes
    String BUDGET_STATUS_CODE_IN_PROGRESS = "1";
    String BUDGET_STATUS_CODE_TO_BE_POSTED = "10";
    String BUDGET_STATUS_CODE_SUBMITTED = "5";
    String BUDGET_STATUS_CODE_RETURNED = "8";
    String BUDGET_STATUS_CODE_CANCELLED = "14";
    String BUDGET_STATUS_CODE_DISAPPROVED = "13";
    
    // Budget Personnel
    String BUDGET_PERSON_DEFAULT_APPOINTMENT_TYPE = "budgetPersonDefaultAppointmentType";
    String BUDGET_PERSON_DEFAULT_CALCULATION_BASE = "budgetPersonDefaultCalculationBase";
    String PERSON_SEQUENCE_NUMBER = "personSequenceNumber";
    String BUDGET_PERSONNEL_PAGE = "personnel";
    String JOB_CODE = "jobCode";
    String BUDGET_CATEGORY_PERSONNEL = "P";
    String BUDGET_PERSON_DEFAULT_JOB_CODE_PARAMETER = "budgetPersonDefaultJobCode";
    String DEFAULT_INFLATION_RATE_FOR_SALARY = "DEFAULT_INFLATION_RATE_FOR_SALARY";
    
    // Key Permissions Info
    String CONFIRM_DELETE_PROPOSAL_USER_KEY = "confirmDeleteProposalUser";
    String PERMISSION_PROPOSAL_USERS_COLLECTION_PROPERTY_KEY = "permissionsHelper.workingUserRoles";
    String PERMISSION_PROPOSAL_USERS_PROPERTY_KEY = "newCollectionLines['" + PERMISSION_PROPOSAL_USERS_COLLECTION_PROPERTY_KEY + "']";
    String PERMISSION_PROPOSAL_USERS_COLLECTION_ID_KEY = "PropDev-PermissionsPage-UserTable";
    String EDIT_ROLES_PROPERTY_KEY = "proposalUserEditRole";
    String PERMISSIONS_EDIT_ROLES_PROPERTY_KEY = "permissionsUserEditRole";
    String MAPPING_PERMISSIONS_ROLE_RIGHTS_PAGE = "permissionsRoleRights";
    String MAPPING_PERMISSIONS_EDIT_ROLES_PAGE = "permissionsEditRoles";
    String MAPPING_PERMISSIONS_CLOSE_EDIT_ROLES_PAGE = "permissionsCloseEditRoles";
    
    // Permission constants
    String PERMISSION_USERS_PROPERTY_KEY = "newPermissionsUser";
    
    // Special Review constraints
    Integer SPECIAL_REVIEW_COMMENT_LENGTH = 40;
    
    // Task Authorization
    String TASK_AUTHORIZATION = "taskAuthorization";

    //Budget Rates
    String ON_CAMUS_FLAG = "N";
    String OFF_CAMUS_FLAG = "F";
    String RATE_CLASS_TYPE_FOR_INFLATION = "I";
    
    //Budget Expenses
    String BUDGET_LINEITEM_NUMBER = "budget.budgetLineItem.lineItemNumber";
    String BUDGET_EXPENSE_LOOKUP_MESSAGE1 = "budget.expense.lookup.message1";
    String PERCENT_EFFORT_FIELD = "Percent Effort";
    String PERCENT_CHARGED_FIELD = "Percent Charged";
    String BUDGET_PERSON_DETAILS_DEFAULT_PERIODTYPE = "budgetPersonDetailsDefaultPeriodType";
    
    //Budget Totals
    int BUDGET_SUMMARY_PERIOD_GROUP_SIZE = 5;
    
    //Subawards
    String SUBCONTRACTOR_F_AND_A_GT_25K_PARAM = "SUBCONTRACTOR_F_AND_A_GT_25K";
    String SUBCONTRACTOR_F_AND_A_LT_25K_PARAM = "SUBCONTRACTOR_F_AND_A_LT_25K";
    String SUBCONTRACTOR_DIRECT_GT_25K_PARAM = "SUBCONTRACTOR_DIRECT_GT_25K";
    String SUBCONTRACTOR_DIRECT_LT_25K_PARAM = "SUBCONTRACTOR_DIRECT_LT_25K";
    
    // research.gov 
    String RESEARCH_GOV_SERVICE_HOST = "research.gov.s2s.host";
    //Grants.gov
    String S2S_SUBMISSIONTYPE_CODE_KEY="document.developmentProposalList[0].s2sOpportunity.s2sSubmissionTypeCode";
    String GRANTS_GOV_PANEL_ANCHOR  = "Opportunity";
    String GRANTS_GOV_OPPORTUNITY_PANEL = "GrantsGov";
    String ABSTRACTS_AND_ATTACHMENTS_PANEL ="Abstracts and Attachments";
    String OPPORTUNITY_ID_KEY="document.developmentProposalList[0].programAnnouncementNumber";
    String OPPORTUNITY_TITLE_KEY="document.developmentProposalList[0].programAnnouncementTitle";
    String CFDA_NUMBER_KEY="document.cfdaNumber";
    String GRANTS_GOV_PAGE = "grantsGov";
    String SPONSOR_PROPOSAL_KEY = "document.developmentProposalList[0].sponsorProposalNumber";
    String ORIGINAL_PROPOSAL_ID_KEY = "document.developmentProposalList[0].continuedFrom";
    String CFDA_NUMBER = "cfdaNumber";
    String OPPORTUNITY_ID= "opportunityId";
    String PROVIDER_CODE = "providerCode";
    String NO_FIELD= "noField";
    String GRANTS_GOV_LINK="message.grantsgov.link";
    String GRANTS_GOV_GENERIC_ERROR_KEY= "error.grantsgov.schemavalidation.generic.errorkey";

    // custom attribute
    String CUSTOM_ATTRIBUTE_ID = "id";
    String DOCUMENT_NEWMAINTAINABLEOBJECT_CUSTOM_ATTRIBUTE_ID = "document.newMaintainableObject.id";
    String DOCUMENT_NEWMAINTAINABLEOBJECT_LOOKUPRETURN = "document.newMaintainableObject.lookupReturn";
    String LOOKUP_RETURN_FIELDS = "lookupReturnFields";
    String LOOKUP_CLASS_NAME = "lookupClassName";

    String MAPPING_PERSONNEL_BUDGET = "personnelBudget";
    String BUDGET_PERSON_LINE_NUMBER = "budget.budgetPersonnelDetails.personNumber";
    String BUDGET_PERSON_LINE_SEQUENCE_NUMBER = "budget.budgetPersonnelDetails.sequenceNumber";
    
    String DATA_TYPE_STRING = "String - Any Character";
    String DATA_TYPE_NUMBER = "Number - [0-9]";
    String DATA_TYPE_DATE = "Date - [xx/xx/xxxx]";
    
    String TRUE_FLAG = "Y";
    String FALSE_FLAG = "N";
    String SPECIAL_REVIEW_PAGE = "specialReview";
    String BUDGET_PERIOD_PANEL_NAME = "Budget Period And Totals Information";
    String BUDGET_RATE_PANEL_NAME = "Budget Rate";
    String BUDGET_RATE_PAGE = "budgetRate";
    String BUDGET_PERIOD_PAGE = "parameters";
    String BUDGET_VERSIONS_PAGE = "versions"; 
    String PD_BUDGET_VERSIONS_PAGE = "budgetVersions"; 
    String BUDGET_EXPENSES_PAGE = "expenses";
    String BUDGET_ACTIONS_PAGE = "budgetActions";
    String BUDGET_RATES_PAGE = "rates";
    String BUDGET_DIST_AND_INCOME_PAGE = "distributionAndIncome";
    String BUDGET_MODULAR_PAGE = "modularBudget";    
    String BUDGET_SUMMARY_TOTALS_PAGE = "summaryTotals";
    String PROPOSAL_HIERARCHY_PAGE = "proposalHierarchy";
    
    String BUDGET_PERIOD_PANEL_ANCHOR = "BudgetPeriodsAmpTotals";
    String BUDGET_VERSIONS_PANEL_ANCHOR = "BudgetVersions";
    String BUDGET_DISTRIBUTION_AND_INCOME_PAGE = "budgetDistributionAndIncome";
    String BUDGET_UNRECOVERED_F_AND_A_PANEL_ANCHOR = "budgetUnrecoveredFandA";
    String BUDGET_UNRECOVERED_F_AND_A_PANEL_NAME = "Budget Unrecovered F and A";
    String BUDGET_COST_SHARE_PANEL_ANCHOR = "budgetCostSharing";
    String BUDGET_COST_SHARE_PANEL_NAME = "Budget Cost Sharing";
    String BUDGET_OVERVIEW_PANEL_NAME = "Budget Overview";
    
    //Approver View
    String MAPPING_COPY_PROPOSAL_PAGE = "copyProposal";
    String MAPPING_PROPOSAL_SUMMARY_PAGE = "proposalDevelopmentSummary";
    String THIRD_PARTY_UNIT_NO="3RD-PRTY";
    
    String HEADER_TAB = "headerTab";

    String ON_OFF_CAMPUS_FLAG = "onOffCampusFlag";

    // Budget Rates
    String APPLICABLE_RATE_LIMIT = "999.99";
    
    // Modular Budget
    String PARAMETER_FNA_COST_ELEMENTS = "consortiumFnaCostElements";
    String PARAMETER_FNA_RATE_CLASS_TYPE = "fnaRateClassTypeCode";

    String PROPOSALDATA_CHANGED_VAL_KEY = "newProposalChangedData.changedValue";
    String PROPOSALDATA_COMMENTS_KEY = "newProposalChangedData.comments";
    
    //Budget Change Data
    String BUDGETDATA_CHANGED_VAL_KEY = "newBudgetChangedData.changedValue";
    String BUDGETDATA_COMMENTS_KEY = "newBudgetChangedData.comments";

    String PERSONNEL_BUDGET_PANEL_NAME = "Personnel Budget";
    String INITIAL_UNIT_HIERARCHY_LOAD_DEPTH = "initialUnitLoadDepth";
    String NUMBER_PER_SPONSOR_HIERARCHY_GROUP = "numberPerSponsorHierarchyGroup";
    
    String PROPOSAL_EDITABLECOLUMN_DATATYPE = "document.newMaintainableObject.dataType";
    String PROPOSAL_EDITABLECOLUMN_DATALENGTH = "document.newMaintainableObject.dataLength";
    String PROPOSAL_EDITABLECOLUMN_LOOKUPRETURN = "document.newMaintainableObject.lookupReturn";

    String PDF_REPORT_CONTENT_TYPE = "application/pdf";
    String PDF_FILE_EXTENSION = ".pdf";
    String GENERIC_SPONSOR_CODE = "GENERIC_SPONSOR_CODE";
    
    /**
     * Subaward budget information
     */
    String SUBAWARD_FILE_FIELD_NAME = "newSubAwardFile";
    String SUBAWARD_FILE_REQUIERED = "newSubAward.subAwardFile.required";
    String SUBAWARD_ORG_NAME_REQUIERED = "newSubAward.organizationName.required";
    String SUBAWARD_ORG_NAME_INVALID = "newSubAward.organizationName.invalid";
    String SUBAWARD_FILE_SPECIAL_CHARECTOR = "newSubAward.subAwardFile.special.character";
    String SUBAWARD_FILE_PERIOD_NOT_FOUND = "newSubAward.subAwardFile.periodNotFound";
    String SUBAWARD_FILE_DETAILS_UPDATED = "newSubAward.subAwardFile.detailsUpdated";
    
    // sponsor hierarchy
    String HIERARCHY_NAME = "hierarchyName";
    String SPONSOR_CODE = "sponsorCode";
    String SPONSOR_HIERARCHY_SEPARATOR_C1C = ";1;";
    String SPONSOR_HIERARCHY_SEPARATOR_P1P = "#1#";
    String SPONSOR_HIERARCHY_PRINTING_NAME_PARAM = "SPONSOR_HIERARCHY_FOR_PRINTING";
    
    String SUBAWARD_FILE_NOT_EXTRACTED = "newSubAward.subAwardFile.notExtracted";

    String BUDGET_CATEGORY_TYPE_PERSONNEL = "budgetCategoryType.personnel";

    //Award
    String MAPPING_AWARD_BASIC = "basic";
    String MAPPING_AWARD_HOME_PAGE = "home";
    String MAPPING_AWARD_CONTACTS_PAGE = "contacts";
    String MAPPING_AWARD_COMMITMENTS_PAGE = "commitments";
    String MAPPING_AWARD_TIME_AND_MONEY_PAGE = "timeAndMoney";
    String MAPPING_AWARD_PAYMENT_REPORTS_AND_TERMS_PAGE = "paymentReportsAndTerms";
    String MAPPING_AWARD_SPECIAL_REVIEW_PAGE = "specialReview";
    String MAPPING_AWARD_CUSTOM_DATA_PAGE = "customData";
    String MAPPING_AWARD_QUESTIONS_PAGE = "questions";
    String MAPPING_AWARD_PERMISSIONS_PAGE = "permissions";
    String MAPPING_AWARD_NOTES_AND_ATTACHMENTS_PAGE = "notesAndAttachments";
    String MAPPING_AWARD_ACTIONS_PAGE = "awardActions";
    String MAPPING_AWARD_MEDUSA_PAGE = "medusa";
    String MAPPING_AWARD_BUDGET_VERSIONS_PAGE = "budgets";
    String MAPPING_ICR_RATE_CODE_PROMPT = "icrRateCodePrompt";
    
    
    //COI
    String MAPPING_COI_EDIT_LIST = "editList";
    String FINANCIAL_ENTITY_STATUS_INACTIVE = "inactive";
    String ENTITY_OWNERSHIP_TYPE_CODE_PRIVATE = "V";
    String COI_WORK_IN_PROGRESS_REVIEW_STATUS_PARM = "WORK_IN_PROGRESS_REVIEW_STATUSES";
    String COI_SCREENING_QUESTIONNAIRE_KRMS_RULE = "SCREENING_QUESTIONNAIRE_KRMS_RULE";
    
    String AWARD_SEQUENCE_AWARD_NUMBER = "SEQ_AWARD_AWARD_NUMBER";
    
    String LINKED_FUNDING_PROPOSALS_KEY = "linkedProposals";
    String DEFAULT_TXN_TYPE_COPIED_AWARD = "TXN_TYPE_DEF_COPIED_AWARD";
    String DEF_CURRENT_ACTION_COMMENT_COPIED_AWARD = "Copied Award" ;
    
    // financial system integration
    String FIN_SYSTEM_INTEGRATION_ON_OFF_PARAMETER = "FIN_SYSTEM_INTEGRATION_ON";
    String FIN_SYSTEM_INTEGRATION_ON = "ON";
    String FIN_SYSTEM_INTEGRATION_SERVICE_URL = "fin.system.integration.service.url";
    String GET_FIN_SYSTEM_URL_FROM_WSDL = "GET_FIN_SYSTEM_URL_FROM_WSDL";
    //CFDA
    int MAX_ALLOWABLE_CFDA_PGM_TITLE_NAME = 300;
    String CFDA_MAINT_TYP_ID_AUTOMATIC = "AUTOMATIC";
    String CFDA_MAINT_TYP_ID_MANUAL = "MANUAL";
    String CFDA_GOV_URL_PARAMETER = "CFDA_GOV_URL";
    String CFDA_GOV_LOGIN_USERNAME = "anonymous";
    String DEFAULT_CRON_EXPRESSION = "0 0 5 * * ?";
    String CFDA_BATCH_NOTIFICATION_RECIPIENT_PARAMETER = "CFDA_BATCH_NOTIFICATION_RECIPIENT";



    //Award Hierarchy Sync
    String AWARD_APPLY_SYNC_NODE_NAME = "ApplySync";
    String AWARD_SYNC_HAS_SYNC_NODE_NAME = "hasSync";
    String AWARD_SYNC_VALIDATION_NODE_NAME = "SyncValidationApproval";
    String AWARD_SYNC_NOT_APPLICABLE = "Not applicable";

    // Award financial rest api constants
    String AWARD_POST_ENABLED = "AWARD_POST_ENABLED";
    String TM_POST_ENABLED = "TM_POST_ENABLED";
    String DISPLAY_ACCOUNT_BALANCES = "DISPLAY_ACCOUNT_BALANCES";

    //Institutional Proposal
    String MAPPING_INSTITUTIONAL_PROPOSAL_HOME_PAGE = "home";
    String MAPPING_INSTITUTIONAL_PROPOSAL_CONTACTS_PAGE = "contacts";
    String MAPPING_INSTITUTIONAL_PROPOSAL_SPECIAL_REVIEW_PAGE = "specialReview";
    String MAPPING_INSTITUTIONAL_PROPOSAL_CUSTOM_DATA_PAGE = "customData";
    String MAPPING_INSTITUTIONAL_PROPOSAL_INTELLECTUAL_PROPERTY_REVIEW_PAGE = "intellectualPropertyReview";
    String MAPPING_INSTITUTIONAL_PROPOSAL_DISTRIBUTION_PAGE = "distribution";
    String MAPPING_INSTITUTIONAL_PROPOSAL_ACTIONS_PAGE = "institutionalProposalActions";
    String MAPPING_INSTITUTIONAL_PROPOSAL_ATTACHMENTS_PAGE = "attachments";
    String MAPPING_INSTITUTIONAL_PROPOSAL_MEDUSA_PAGE = "medusa";

    String INSTITUTIONAL_PROPOSAL_IP_PANEL_ANCHOR = "institutionalProposal";


    String PARAMETER_IP_REVIEW_TAB_ENABLED = "IP_INTELLECTUAL_PROPERTY_REVIEW_TAB_ENABLED";

    String INSTITUTIONAL_PROPSAL_PROPSAL_NUMBER_SEQUENCE = "SEQ_PROPOSAL_PROPOSAL_ID";
    String IP_NUMBER_SEQUENCE_FY_BASED = "SEQ_IP_FY_BASED_ID";
    String FISCAL_YEAR_BASED_IP = "GENERATE_IP_BASED_ON_FY";
    String COST_SHARE_COMMENT_TYPE_CODE = "9";
    String FANDA_RATE_COMMENT_TYPE_CODE = "8";
    String PAYMENT_AND_INVOICES_COMMENT_TYPE_CODE = "1";
    String PREAWARD_SPONSOR_AUTHORIZATION_COMMENT_TYPE_CODE = "18";
    String PREAWARD_INSTITUTIONAL_AUTHORIZATION_COMMENT_TYPE_CODE = "19";
    String BENEFITS_RATES_COMMENT_TYPE_CODE = "20";
    String GENERAL_COMMENT_TYPE_CODE = "2";
    String FISCAL_REPORT_COMMENT_TYPE_CODE = "3";
    String INTELLECTUAL_PROPERTY_COMMENT_TYPE_CODE = "4";
    String PROCUREMENT_COMMENT_TYPE_CODE = "5";
    String PROPERTY_COMMENT_TYPE_CODE = "6";
    String SPECIAL_RATE_COMMENT_TYPE_CODE = "7";
    String SPECIAL_REVIEW_COMMENT_TYPE_CODE = "10";
    String PROPOSAL_SUMMARY_COMMENT_TYPE_CODE = "12";
    String PROPOSAL_COMMENT_TYPE_CODE = "13";
    String PROPOSAL_IP_REVIEW_COMMENT_TYPE_CODE = "16";
    String CURRENT_ACTION_COMMENT_TYPE_CODE = "21";
    boolean AWARD_COMMENT_INCLUDE_IN_CHECKLIST = true;
    boolean AWARD_COMMENT_EXCLUDE_FROM_CHECKLIST = false;
   
    Integer MIN_FISCAL_YEAR = 1900;
    Integer MAX_FISCAL_YEAR = 2499;
    
    String PARAMETER_MODULE_AWARD = "KC-AWARD";
    String PARAMETER_TIME_MONEY = "KC-T";

    String SPECIAL_REVIEW_NUMBER = "SPECIAL_REVIEW_NUMBER";
    String LEFT_SQUARE_BRACKET = "[";
    String RIGHT_SQUARE_BRACKET = "]";
    
    String REPORT_CLASSES_KEY_FOR_INITIALIZE_OBJECTS = "reportClasses";
    String NEW_AWARD_REPORT_TERMS_LIST_KEY_FOR_INITIALIZE_OBJECTS = "newAwardReportTermList";
    String NEW_AWARD_REPORT_TERM_RECIPIENTS_LIST_KEY_FOR_INITIALIZE_OBJECTS = "newAwardReportTermRecipientsList";
    String REPORT_CLASS_FOR_PAYMENTS_AND_INVOICES_PANEL = "reportClassForPaymentsAndInvoicesPanel";
    
    String UNIT_CONTACTS_DEFAULT_GROUP_FLAG = "U";
    
    //Award Audit Rules
    String REPORT_TERMS_AUDIT_RULES_ERROR_KEY = "document.reportTermsAuditRules";
    String TERMS_AUDIT_RULES_ERROR_KEY = "document.termsAuditRules";
    String PAYMENT_AND_INVOICES_AUDIT_RULES_ERROR_KEY = "document.paymentsAuditRules";
    String SUBAWARD_AUDIT_RULES_ERROR_KEY = "document.subawardAuditRules";
    String AWARD_PAGE = "award";
    String COST_SHARE_PANEL_ANCHOR = "CostShare";
    String COST_SHARE_PANEL_NAME = "Cost Share";
    String FANDA_RATES_PANEL_ANCHOR = "Rates";
    String FANDA_RATES_PANEL_NAME = "Rates";
    String REPORTS_PANEL_ANCHOR = "Reports";
    String REPORTS_PANEL_NAME = "Reports";
    String CONTACTS_PANEL_NAME = "Contacts";
    String CONTACTS_PANEL_ANCHOR = "Contacts";
    String CONTACTS_AUDIT_ERROR_KEY_NAME = "contactsAuditErrors";
    String TERMS_PANEL_ANCHOR = "Terms";
    String TERMS_PANEL_NAME = "Terms";
    String PAYMENT_AND_INVOICES_PANEL_ANCHOR = "Payments";
    String PAYMENT_AND_INVOICES_PANEL_NAME = "Payments";
    String SUBAWARD_PANEL_NAME = "Subaward";
    String SUBAWARD_PANEL_ANCHOR = "Subaward";
    String MAPPING_AWARD_HOME_DETAILS_AND_DATES_PAGE_ANCHOR = "anchorDetailsDates";
    String MAPPING_AWARD_HOME_DETAILS_AND_DATES_PAGE_NAME = "Details & Dates";
    
    // IRB
    String DEFAULT_PROTOCOL_ORGANIZATION_TYPE_CODE = "1";
    String DEFAULT_PROTOCOL_ORGANIZATION_ID = "000001";
    String DEFAULT_PROTOCOL_STATUS_CODE = "100";
    String PROPERTY_PROTOCOL_STATUS = "protocolStatus";
    String PARAMETER_PROTOCOL_PERSON_TRAINING_SECTION = "protocolPersonTrainingSectionRequired";
    String PARAMETER_PROTOCOL_ATTACHMENT_DEFAULT_SORT = "protocolAttachmentDefaultSort";
    Integer PROTOCOL_RISK_LEVEL_COMMENT_LENGTH = 40;
    Integer PROTOCOL_REFERENCE_COMMENT_LENGTH = 250;
    String ACTIVE_STATUS_LITERAL = "Active";
    String INACTIVE_STATUS_LITERAL = "Inactive";
    String PARAMETER_IRB_COMM_SELECTION_DURING_SUBMISSION = "IRB_COMM_SELECTION_DURING_SUBMISSION";
    String PARAMETER_IRB_DISPLAY_REVIEWER_NAME = "IRB_DISPLAY_REVIEWER_NAME";
    String PARAMETER_IRB_DISPLAY_REVIEWER_NAME_TO_PROTOCOL_PERSONNEL = "IRB_DISPLAY_REVIEWER_NAME_TO_PROTOCOL_PERSONNEL";
    String PARAMETER_IRB_DISPLAY_REVIEWER_NAME_TO_PI = "IRB_DISPLAY_REVIEWER_NAME_TO_PI";
    String PARAMETER_IRB_DISPLAY_REVIEWER_NAME_TO_OTHER_PROTOCOL_PERSONNEL = "IRB_DISPLAY_REVIEWER_NAME_TO_OTHER_PROTOCOL_PERSONNEL";
    String PARAMETER_IRB_DISPLAY_REVIEWER_NAME_TO_REVIEWERS = "IRB_DISPLAY_REVIEWER_NAME_TO_REVIEWERS";
    String PARAMETER_IRB_DISPLAY_REVIEWER_NAME_TO_ACTIVE_COMMITTEE_MEMBERS = "IRB_DISPLAY_REVIEWER_NAME_TO_ACTIVE_COMMITTEE_MEMBERS";
    
    String PROTOCOL_FUNDING_SOURCE_TYPE_CODE_FIELD = "protocolHelper.newFundingSource.fundingSourceTypeCode";
    String PROTOCOL_FUNDING_SOURCE_ID_FIELD = "protocolHelper.newFundingSource.fundingSource";
    String PROTOCOL_FUNDING_SOURCE_NUMBER_FIELD = "protocolHelper.newFundingSource.fundingSourceNumber";
    String PROTOCOL_FUNDING_SOURCE_NAME_FIELD = "protocolHelper.newFundingSource.fundingSourceName";
    String PROTOCOL_FUNDING_SOURCE_TITLE_FIELD = "protocolHelper.newFundingSource.fundingSourceTitle";
    
    Integer AFFILIATION_FACULTY_SUPERVISOR_TYPE = 5;
    Integer AFFILIATION_STUDENT_INVESTIGATOR_TYPE = 4;
    
    String PROTOCOL_CREATE_AMENDMENT_KEY = "actionHelper.protocolCreateAmendment";
    String PROTOCOL_MODIFY_AMENDMENT_KEY = "actionHelper.protocolModifyAmendment";
    String PROTOCOL_CREATE_RENEWAL_WITH_AMENDMENT_KEY = "actionHelper.protocolCreateRenewalWithAmendment";
    String PROTOCOL_CREATE_RENEWAL_SUMMARY_KEY = "actionHelper.renewalSummary";

    String PROTOCOL_CREATE_CONTINUATION_SUMMARY_KEY = "actionHelper.continuationSummary";
    String PROTOCOL_CREATE_CONTINUATION_WITH_AMENDMENT_KEY = "actionHelper.protocolCreateContinuationWithAmendment";
    String PROTOCOL_ATTACHMENT_PREFIX = "Protocol: ";
    
    // Protocol edit modes
    String CAN_VIEW_REVIEW_COMMENTS = "viewReviewComments";

    // Risk Level
    String PROTOCOL_UPDATE_RISK_LEVEL_KEY = "document.protocol.protocolRiskLevels";
    
    // Protocol protocol page audit rules
    String PROTOCOL_PROTOCOL_PAGE = "protocol";
    String PROTOCOL_PROTOCOL_PANEL_NAME = "Protocol";
    
    // Protocol Research Area Audit Rules
    String PROTOCOL_PROTOCOL_RESEARCH_AREA_PANEL_ANCHOR = "Area of Research";
    String PROTOCOL_FROM_DOCUMENT = "document.protocolList[0]";
    String PROTOCOL_RESEARCH_AREA_KEY = PROTOCOL_FROM_DOCUMENT + ".newDescription";
    
    // Protocol Funding Source Audit Rules
    String PROTOCOL_PROTOCOL_FUNDING_SRC_PANEL_ANCHOR = "Funding Source";
    String PROTOCOL_FUNDING_SRC_KEY = "document.title";
    
    //Protocol Personnel Audit Rules
    String PROTOCOL_PERSONNEL_PAGE = "personnel";
    String PROTOCOL_PERSONNEL_PANEL_ANCHOR = "Add Personnel:";
    String PROTOCOL_PERSONNEL_PANEL_NAME = "Personnel";

    String VIEW_ONLY = "viewOnly";
    
    // Protocol Action Rules
    String PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY = "actionHelper.protocolSubmitAction";
    String PROTOCOL_CLOSE_REQUEST_PROPERTY_KEY = "actionHelper.protocolCloseReqeust";
    String PROTOCOL_SUSPEND_REQUEST_PROPERTY_KEY = "actionHelper.protocolSuspendRequest";
    String PROTOCOL_CLOSE_ENROLLMENT_REQUEST_PROPERTY_KEY = "actionHelper.protocolCloseEnrollmentRequest";
    String PROTOCOL_REOPEN_ENROLLMENT_REQUEST_PROPERTY_KEY = "actionHelper.protocolReOpenEnrollmentRequest";
    String PROTOCOL_DATA_ANALYSIS_REQUEST_PROPERTY_KEY = "actionHelper.protocolDataAnalysisRequest";
    String PROTOCOL_TERMINATE_REQUEST_PROPERTY_KEY = "actionHelper.protocolTerminateRequest";
    String PROTOCOL_ASSIGN_CMT_SCHED_ACTION_PROPERTY_KEY = "actionHelper.assignCmtSchedBean";
    String PROTOCOL_ASSIGN_TO_AGENDA_ACTION_PROPERTY_KEY = "actionHelper.assignToAgendaBean";
    String PROTOCOL_ASSIGN_REVIEWERS_PROPERTY_KEY = "actionHelper.protocolAssignReviewersBean";
    String PROTOCOL_ADMIN_CORRECTION_PROPERTY_KEY = "actionHelper.protocolAdminCorrectionBean";
    String PROTOCOL_MANAGE_REVIEW_COMMENTS_KEY = "actionHelper.protocolManageReviewCommentsBean";
    String PROTOCOL_COMMITTEE_DECISION_ACTION_PROPERTY_KEY = "actionHelper.committeeDecision";
    String PROTOCOL_GRANT_EXEMPTION_ACTION_PROPERTY_KEY = "actionHelper.protocolGrantExemptionBean";
    String PROTOCOL_FULL_APPROVAL_ACTION_PROPERTY_KEY = "actionHelper.protocolFullApprovalBean";
    String PROTOCOL_EXPEDITED_APPROVAL_ACTION_PROPERTY_KEY = "actionHelper.protocolExpeditedApprovalBean";
    String PROTOCOL_RESPONSE_APPROVAL_ACTION_PROPERTY_KEY = "actionHelper.protocolResponseApprovalBean";
    String PROTOCOL_ADMIN_APPROVAL_ACTION_PROPERTY_KEY = "actionHelper.protocolAdminApprovalBean";    
    String PROTOCOL_CLOSE_ACTION_PROPERTY_KEY = "actionHelper.protocolCloseBean";
    String PROTOCOL_CLOSE_ENROLLMENT_ACTION_PROPERTY_KEY = "actionHelper.protocolCloseEnrollmentBean";
    String PROTOCOL_DEFER_ACTION_PROPERTY_KEY = "actionHelper.protocolDeferBean";
    String PROTOCOL_DISAPPROVE_ACTION_PROPERTY_KEY = "actionHelper.protocolDisapproveBean";
    String PROTOCOL_EXPIRE_ACTION_PROPERTY_KEY = "actionHelper.protocolExpireBean";
    String PROTOCOL_IRB_ACKNOWLEDGEMENT_ACTION_PROPERTY_KEY = "actionHelper.protocolIrbAcknowledgementBean";
    String PROTOCOL_PERMIT_DATA_ANALYSIS_ACTION_PROPERTY_KEY = "actionHelper.protocolPermitDataAnalysisBean";
    String PROTOCOL_REOPEN_ENROLLMENT_ACTION_PROPERTY_KEY = "actionHelper.protocolReopenEnrollmentBean";
    String PROTOCOL_SMR_ACTION_PROPERTY_KEY = "actionHelper.protocolSMRBean";
    String PROTOCOL_SRR_ACTION_PROPERTY_KEY = "actionHelper.protocolSRRBean";
    String PROTOCOL_RETURN_TO_PI_PROPERTY_KEY = "actionHelper.protocolReturnToPIBean";
    String PROTOCOL_SUSPEND_ACTION_PROPERTY_KEY = "actionHelper.protocolSuspendBean";
    String PROTOCOL_SUSPEND_BY_DSMB_ACTION_PROPERTY_KEY = "actionHelper.protocolSuspendByDsmbBean";
    String PROTOCOL_TERMINATE_ACTION_PROPERTY_KEY = "actionHelper.protocolTerminateBean";
    String PROTOCOL_UNDO_LASTACTION_PROPERTY_KEY = "actionHelper.undoLastActionBean";
    String PROTOCOL_MODIFY_SUBMISSION_KEY = "actionHelper.protocolModifySubmissionBean";
    String PROTOCOL_ABANDON_ACTION_PROPERTY_KEY = "actionHelper.protocolAbandonBean";
    String PROTOCOL_WITHDRAW_SUBMISSION_PROPERTY_KEY = "actionHelper.protocolWithdrawSubmissionBean";
    
    //Online Review
    String ONLINE_REVIEW_ROUTE_NODE_ADMIN_INITIAL_REVIEW="IRBAdminInitialReview";
    String ONLINE_REVIEW_ROUTE_NODE_ONLINE_REVIEWER="OnlineReviewer";
    String ONLINE_REVIEW_ROUTE_NODE_ADMIN_REVIEW="IRBAdminReview";
    
    //Protocol History View
    String PROTOCOL_HISTORY_DATE_RANGE_FILTER_START_DATE_KEY = "actionHelper.filteredHistoryStartDate";
    String PROTOCOL_HISTORY_DATE_RANGE_FILTER_END_DATE_KEY = "actionHelper.filteredHistoryEndDate";
    
    //Protocol Attachment Notification
    Integer PROTOCOL_ATTACHMENT_NOTIFICATION_COMMENTS = 250;
    
    //Protocol Workflow
    String PROTOCOL_INITIATED_ROUTE_NODE_NAME = "Initiated";
    String PROTOCOL_IRBREVIEW_ROUTE_NODE_NAME = "IRBReview";
    String PROTOCOL_APPROVAL_NODE_NAME = "DepartmentReview";
    String PROTOCOL_UNDO_APPROVE_ANNOTATION = "Undoing Approve Action";
    // IACUC
    String IACUC_PROTOCOL_IACUCREVIEW_ROUTE_NODE_NAME = "IACUCReview";

    // Committee
    String COMMITTEE_AGENDA_NOTIFICATION = "213";
    String COMMITTEE_MINUTES_NOTIFICATION = "215";
    
    String CONFIRM_DELETE_PERMISSIONS_USER_KEY = "confirmDeletePermissionsUser";
    
    // Correspondence
    String DEFAULT_CORRESPONDENCE_TEMPLATE = "DEFAULT";
    String CORRESPONDENCE_TEMPLATE_CONTENT_TYPE_1 = "text/xml";
    String CORRESPONDENCE_TEMPLATE_CONTENT_TYPE_2 = "application/xml";
    String CORRESPONDENCE_TEMPLATE_CONTENT_TYPE_3 = "application/xslt+xml";
    String PROTOCOL_RENEWAL_REMINDERS = "1";
    String REMINDER_TO_IRB_NOTIFICATIONS = "2";
    String REMINDER_TO_IACUC_NOTIFICATIONS = "2";
    String PROTOCOL_ACTION_TYPE_CODE_RENEWAL_REMINDER_GENERATED = "111";
    String PROTOCOL_ACTION_TYPE_CODE_IRB_NOTIFICATION_GENERATED = "112";
    String IACUC_PROTOCOL_ACTION_TYPE_CODE_RENEWAL_REMINDER_GENERATED = "111";
    String IACUC_PROTOCOL_ACTION_TYPE_CODE_IACUC_REMINDER_GENERATED = "112";

    // Protocol Linking  System Parameters 
    String ENABLE_PROTOCOL_TO_AWARD_LINK  = "irb.protocol.award.linking.enabled";
    String ENABLE_PROTOCOL_TO_DEV_PROPOSAL_LINK = "irb.protocol.development.proposal.linking.enabled";
    String ENABLE_PROTOCOL_TO_PROPOSAL_LINK = "irb.protocol.institute.proposal.linking.enabled";
    
    String PROTOCOL_TYPE_CODE_EXEMPT = "irb.protocol.protocoltype.exempt";
    String IRB_PROTOCOL_DUPLICATE_PERSON_ENABLED = "irb.protocol.duplicatePerson.enabled";
    String IACUC_PROTOCOL_DUPLICATE_PERSON_ENABLED = "iacuc.protocol.duplicatePerson.enabled";
    
    // Questionnaire
    String QUESTION_EXPLANATION = "E";
    String QUESTION_POLICY = "P";
    String QUESTION_REGULATION = "R";
    String QUESTION_AFFIRMATIVE_QUESTION_CONVERSION = "A";
    String QUESTION_NEGATIVE_QUESTION_CONVERSION = "N";
    
    long QUESTION_RESPONSE_TYPE_YES_NO = 1L;
    long QUESTION_RESPONSE_TYPE_YES_NO_NA = 2L;
    long QUESTION_RESPONSE_TYPE_NUMBER = 3L;
    long QUESTION_RESPONSE_TYPE_DATE = 4L;
    long QUESTION_RESPONSE_TYPE_TEXT = 5L;
    long QUESTION_RESPONSE_TYPE_LOOKUP = 6L;
    long QUESTION_RESPONSE_TYPE_MULTIPLE_CHOICE = 100L;
    
    String QUESTION_DOCUMENT_FIELD_QUESTION_TYPE_ID = "document.newMaintainableObject.businessObject.questionTypeId";
    String QUESTION_DOCUMENT_FIELD_STATUS = "document.newMaintainableObject.businessObject.status";
    String QUESTION_DOCUMENT_FIELD_DISPLAYED_ANSWERS = "document.newMaintainableObject.businessObject.displayedAnswers";
    String QUESTION_DOCUMENT_FIELD_ANSWER_MAX_LENGTH = "document.newMaintainableObject.businessObject.answerMaxLength";
    String QUESTION_DOCUMENT_FIELD_MAX_ANSWERS = "document.newMaintainableObject.businessObject.maxAnswers";
    String QUESTION_DOCUMENT_FIELD_LOOKUP_CLASS = "document.newMaintainableObject.businessObject.lookupClass";
    String QUESTION_DOCUMENT_FIELD_LOOKUP_RETURN = "document.newMaintainableObject.businessObject.lookupReturn";
    
    //Institutional Proposal Rules
    String IP_UNRECOVERED_FNA_ACTION_PROPERTY_KEY = "newInstitutionalProposalUnrecoveredFandA";
    
    //Award Hierarchy - Time And Money Constants
    String AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT = "000000-00000";
    String LAST_NODE_NEXT_VALUE = "lastNodeNextValue";
    String ALLOW_TM_WHEN_PENDING_AWARD_PARAM = "ALLOW_TM_WHEN_PENDING_AWARD_EXISTS";
    
    //Award Template Constants
    String AWARD_TEMP_RECPNT_CONTACT_TYPE_CODE_ROLODEX_ID_SEPARATOR = "***";
    
    // Questionnaire
    String PARAMETER_MODULE_QUESTIONNAIRE = "KC-QUESTIONNAIRE";
    String PARAMETER_COMPONENT_PERMISSION = "P";
    
    String TIME_AND_MONEY_DOCUMENT_STRING_FOR_SESSION = "TIME_AND_MONEY";
    String AWARD_DOCUMENT_STRING_FOR_SESSION = "returnToAwardDocument";
    String KRA_EXTERNALIZABLE_IMAGES_URI_KEY = "kra.externalizable.images.url";
    String KR_EXTERNALIZABLE_IMAGES_URI_KEY = "kr.externalizable.images.url";
    
    //
    String KC_GENERIC_PARAMETER_NAMESPACE = "KC-GEN";
    String KC_S2S_PARAMETER_NAMESPACE = "KC-S2S";
    String KC_ALL_PARAMETER_DETAIL_TYPE_CODE = "All";
    String MODULE_NAMESPACE_AWARD_BUDGET = "KC-AB";
    
    String CUSTOM_ATTRIBUTE_DOCUMENT_DETAIL_TYPE_CODE = "DocumentType";
    String CUSTOM_ATTRIBUTE_DOCUMENT_PARAM_NAME = "customAttributeDocumentType";

    Object PROPOSAL_BUDGET = "proposalBudget";
    Object AWARD_BUDGET = "awardBudget";
    String ACTIVITY_TYPE_KEY = "document.developmentProposalList[0].activityTypeCode";
    String FEDERAL_SPONSOR_TYPE_CODE = "FEDERAL_SPONSOR_TYPE_CODE";
    
    String INSTITUTIONAL_PROPOSAL_NAMESPACE = "KC-IP";

    // Maintenance documents
    String VALID_SPECIAL_REVIEW_APPROVAL_TYPE_CODE_KEY = "document.newMaintainableObject.approvalTypeCode";
    
    //WHITE SPACE 
    char SPACE_SEPARATOR = 32;
    
    String CAN_CREATE_AWARD_KEY = "canCreateAward";
    
    // Protocol questionnaire page audit rules
    String PROTOCOL_QUESTIONNAIRE_PAGE = "questionnaire";
    String PROTOCOL_QUESTIONNAIRE_PANEL_ANCHOR = "Questionnaire";

    String PRINT_LOGGING_ENABLE = "print.logging.enable";
    String PRINT_LOGGING_DIRECTORY = "print.logging.directory";
    String PRINT_PDF_LOGGING_ENABLE = "print.pdf.logging.enable";

    String ENABLE_COST_SHARE_SUBMIT = "ENABLE_COST_SHARE_SUBMIT";
    String INSTITUTE_PROPOSAL_OTHER_GROUP = "INSTITUTE_PROPOSAL_OTHER_GROUP";

    //award budget rates
    String AWARD_BUDGET_DEFAULT_FNA_RATE_CLASS_CODE = "awardBudgetDefaultFnARateClassCode";
    String AWARD_BUDGET_DEFAULT_UNDERRECOVERY_RATE_CLASS_CODE = "awardBudgetDefaultUnderrecoveryRateClassCode";
    String AWARD_BUDGET_EB_RATE_CLASS_CODE = "awardBudgetEbRateClassCode";
    String AWARD_BUDGET_EB_RATE_TYPE_CODE = "awardBudgetEbRateTypeCode";

    
    //Subaward
    String MAPPING_SUBAWARD_PAGE = "subAward";
    String MAPPING_FUNDING_SOURCE_PAGE = "fundingSource";
    String MAPPING_FINANCIAL_PAGE ="financial";
    String MAPPING_TEMPLATE_PAGE ="templateInformation";
    String MAPPING_AMOUNT_RELEASED_PAGE="amountReleased";
    String MAPPING_CONTACTS_PAGE="contacts";
    String MAPPING_CLOSEOUT_PAGE="closeout";
    String MAPPING_SUBAWARD_ACTION_PAGE="subAwardActions";
    String SUBAWARD_SEQUENCE_SUBAWARD_CODE = "SUBAWARD_CODE_S";
    String SUBAWARD_AUDIT_RULE_ERROR_KEY = "newSubAwardContact.rolodex.fullName";
    String SUBAWARD_CONTACTS_PANEL_NAME = "Contacts";
    String SUBAWARD_CONTACTS_PANEL_ANCHOR = "Contacts";
    String SUBAWARD_FINANCIAL_PANEL ="Financial";
    String SUBAWARD_FINANCIAL_PANEL_NAME ="Financial";
    String SUBAWARD_FINANCIAL_ANTICIPATED_AMOUNT = "document.subAwardList[0].totalAnticipatedAmount";
    String SUBAWARD_FINANCIAL_OBLIGATED_AMOUNT = "document.subAwardList[0].totalObligatedAmount";
    String PARAMETER_FDP_PRIME_ADMINISTRATIVE_CONTACT_CODE = "FDP_Prime_Administrative_Contact_Code";
    String PARAMETER_FDP_PRIME_AUTHORIZED_OFFICIAL_CODE = "FDP_Prime_Authorized_Official_Code";
    String PARAMETER_FDP_PRIME_FINANCIAL_CONTACT_CODE = "FDP_Prime_Financial_Contact_Code";
    String PARAMETER_FDP_SUB_ADMINISTRATIVE_CONTACT_CODE = "FDP_Sub_Administrative_Contact_Code";
    String PARAMETER_FDP_SUB_AUTHORIZED_CONTACT_CODE = "FDP_Sub_Authorized_Official_Code";
    String PARAMETER_FDP_SUB_FINANCIAL_CONTACT_CODE = "FDP_Sub_Financial_Contact_Code";
    String PARAMETER_FDP_SUBAWARD_ATTACHMENT_3B = "Subaward_FDP_Attachment_3B_Form_ID";
    String PARAMETER_FDP_SUBAWARD_ATTACHMENT_4 = "Subaward_FDP_Attachment_4_Form_ID";
    String PARAMETER_FDP_SUBAWARD_ATTACHMENT_5 = "Subaward_FDP_Attachment_5_Form_ID";
    String PARAMETER_PRINT_ATTACHMENT_TYPE_INCLUSION = "Subaward_Print_Attachment_Type_Inclusion";
    
    // coi
    String MODULE_NAMESPACE_COIDISCLOSURE = "KC-COIDISCLOSURE";
    String PARAMETER_COI_ATTACHMENT_DEFAULT_SORT = "coiAttachmentDefaultSort";
    String MAPPING_COI_DISCLOSURE_ACTIONS_PAGE = "coiDisclosureActions";
    
    //Negotiations
    String NEGOTIATION_SEQUENCE_NAME = "NEGOTIATION_S";
    String NEGOTIATION_HOME_PAGE = "Negotiation";
    String NEGOTIATION_LOST_PLACE_PAGE = "lostPlaceMessagePage";

    // Disclosure disclosure page audit rules
    String COI_DISCLOSURE_DISCLOSURE_PAGE = "coiDisclosure";
    String COI_DISCLOSURE_DISCLOSURE_PANEL_NAME = "Disclosure";
    String DISCLOSURE_FINANCIAL_ENTITY_PANEL_ANCHOR = "Financial Entity";
    String DISCLOSURE_FINANCIAL_ENTITY_KEY2 = "document.coiDisclosureList[0].coiDisclProjects[0].coiDiscDetails[%s].entityStatusCode";
    String DISCLOSURE_MANUAL_DISPOSITION_STATUS = "document.coiDisclosureList[0].coiDisclProjects[0].disclosureDispositionCode";

    String DISCLOSURE_MANUAL_FINANCIAL_ENTITY_KEY = "document.coiDisclosureList[0].coiDisclProjects[0].coiDiscDetails[%s].entityStatusCode";
    String DISCLOSURE_ANNUAL_FINANCIAL_ENTITY_KEY2 = "document.coiDisclosureList[0].coiDisclProjects[%s].coiDiscDetails[%s].entityStatusCode";

    String DISCLOSURE_UPDATE_FINANCIAL_ENTITY_KEY = "disclosureHelper.masterDisclosureBean.%s[%s].coiDisclProject.coiDiscDetails[%s].entityStatusCode";
    String DISCLOSURE_CERTIFIED_NOTIFICATION = "214"; 
    
    // Person Mass Change
    String MAPPING_PMC_HOME_PAGE = "home";
    String MAPPING_PMC_VIEW_PAGE = "view";
    String ENABLE_SALARY_INFLATION_ANNIV_DATE = "enableSalaryInflationAnniversaryDate";
    

    // Iacuc - procedureSummary length
    Integer IACUC_PROCEDURE_SUMMARY_LENGTH = 40;
    // Iacuc - exceptionDescription length
    Integer IACUC_PROTOCOL_EXCEPTION_DESC_LENGTH = 40;
    String MAPPING_IACUC_PROTOCOL_ACTIONS = "iacucProtocolActions";
    String MAPPING_IACUC_PROTOCOL_HISTORY = "iacucProtocolHistory";
 
    String IACUC_PROCEDURE_PERSON_RESPONSIBLE_DELIMITER = "|";

    int IACUC_PROCEDURE_CUSTOM_DATA_SMALL_STRING_MAX_LENGTH = 1000;
    
    String PARAMETER_MODULE_IACUC_PROTOCOL_REFERENCEID1 = "iacuc.protocol.referenceID1";;
    String PARAMETER_MODULE_IACUC_PROTOCOL_REFERENCEID2 = "iacuc.protocol.referenceID2";;
    String IACUC_DEACTIVATE_ACTION_PROPERTY_KEY = "actionHelper.iacucProtocolDeactivateBean";
    String IACUC_REQUEST_LIFT_HOLD_ACTION_PROPERTY_KEY = "actionHelper.iacucProtocolRequestLiftHoldBean";
    String IACUC_WITHDRAW_SUBMISSION_PROPERTY_KEY = "actionHelper.iacucWithdrawSubmission";
    
    String PARAMETER_IACUC_COMM_SELECTION_DURING_SUBMISSION = "IACUC_COMM_SELECTION_DURING_SUBMISSION";
    String PARAMETER_IACUC_DISPLAY_REVIEWER_NAME = "IACUC_DISPLAY_REVIEWER_NAME";
    String PARAMETER_IACUC_DISPLAY_REVIEWER_NAME_TO_PROTOCOL_PERSONNEL = "IACUC_DISPLAY_REVIEWER_NAME_TO_PROTOCOL_PERSONNEL";
    String PARAMETER_IACUC_DISPLAY_REVIEWER_NAME_TO_PI = "IACUC_DISPLAY_REVIEWER_NAME_TO_PI";
    String PARAMETER_IACUC_DISPLAY_REVIEWER_NAME_TO_OTHER_PROTOCOL_PERSONNEL = "IACUC_DISPLAY_REVIEWER_NAME_TO_OTHER_PROTOCOL_PERSONNEL";
    String PARAMETER_IACUC_DISPLAY_REVIEWER_NAME_TO_REVIEWERS = "IACUC_DISPLAY_REVIEWER_NAME_TO_REVIEWERS";
    String PARAMETER_IACUC_DISPLAY_REVIEWER_NAME_TO_ACTIVE_COMMITTEE_MEMBERS = "IACUC_DISPLAY_REVIEWER_NAME_TO_ACTIVE_COMMITTEE_MEMBERS";
    String PARAMETER_IACUC_PROTOCOL_PERSON_TRAINING_SECTION = "IACUC_PROTOCOL_PERSON_TRAINING_SECTION";
    String PARAMETER_IACUC_PROTOCOL_ATTACHMENT_DEFAULT_SORT = "IACUC_PROTOCOL_ATTACHMENT_DEFAULT_SORT";
    String IACUC_PROTOCOL_PROPOSAL_DEVELOPMENT_LINKING_ENABLED_PARAMETER = "iacuc.protocol.proposal.development.linking.enabled";
    String IACUC_PROTOCOL_INSTITUTE_PROPOSAL_LINKING_ENABLED_PARAMETER = "iacuc.protocol.institute.proposal.linking.enabled";
    String IACUC_PROTOCOL_AWARD_LINKING_ENABLED_PARAMETER = "iacuc.protocol.award.linking.enabled";
    String IACUC_PROTOCOL_DEFAULT_EXIPIRATION_TIME_DIFFERENCE_PARAMTETER = "PROTCOL_DEFAULT_EXPIRATION_DATE_DIFFERENCE";
    String IACUC_PROTOCOL_MODIFY_SUBMISSION_KEY = "actionHelper.iacucProtocolModifySubmissionBean";


    // IACUC workflow
    String PROTOCOL_IACUCREVIEW_ROUTE_NODE_NAME = "IACUCReview";
    //IACUC Online Review
    String IACUC_ONLINE_REVIEW_ROUTE_NODE_ADMIN_INITIAL_REVIEW="IACUCAdminInitialReview";
    String IACUC_ONLINE_REVIEW_ROUTE_NODE_ONLINE_REVIEWER="IACUCOnlineReviewer";
    String IACUC_ONLINE_REVIEW_ROUTE_NODE_ADMIN_REVIEW="IACUCAdminReview";

    //Award report tracking notifications
    String REPORT_TRACKING_NOTIFICATIONS_BATCH_RECIPIENT = "REPORT_TRACKING_NOTIFICATIONS_BATCH_RECIPIENT";
    String REPORT_TRACKING_NOTIFICATIONS_BATCH_ENABLED = "REPORT_TRACKING_NOTIFICATIONS_BATCH_ENABLED";
    
    String FORMULATED_COST_ELEMENTS = "formulatedCostElements";
    String BUDGET_FORMULATED_NUMBER = "BUDGET_FORMULATED_NUMBER";
    String FORMULATED_COST_ENABLED = "enableFormulatedCostCalculation";
    
    String YES_FLAG = "Y";
    String NO_FLAG = "N";
    
    //Award property constants
    String AWARD_ID = "awardId";

    String KC_CONFIRMATION_QUESTION = "kcConfirmationQuestion";
    String NON_CANCELLING_RECALL_QUESTION = "nonCancellingRecallQuestion";
    String NON_CANCELLING_RECALL_QUESTION_TEXT_KEY = "nonCancelling.recall.question.text";
    
    //Birt Constants
    
    int TYPE_TEXT = 1;
    int STRING_TYPE = 4;
    int DATE_TIME_TYPE = 5;
    
    String HTML_REPORT_CONTENT_TYPE = "application/html";
    String EXCEL_REPORT_CONTENT_TYPE = "application/xls";
    String REPORT_FORMAT_PDF = "pdf";
    String REPORT_FORMAT_HTML = "html";
    String REPORT_FORMAT_EXCEL = "excel";
    String REPORT_FORMAT_EXCEL_EXTENSION = ".xls";
    String REPORT_FORMAT_EXCEL_EXT = "xls";
    String REPORT_FORMAT_HTML_EXTENSION = ".html";
    String BIRT_DATA_SOURCE = "Data Source";


    String COI_NOTEPAD_DISCLOSURE_REVIEWER_SECTION_ID = "RDA"; // COI reviewer reviewer disclosure action

    // Constants used in Medusa
    String AWARD_MODULE = "award";
    String INSTITUTIONAL_PROPOSAL_MODULE = "IP";
    String DEVELOPMENT_PROPOSAL_MODULE = "DP";    
    String NEGOTIATION_MODULE = "neg";
    String SUBAWARD_MODULE = "subaward";
    String IRB_MODULE = "irb";
    String IACUC_MODULE = "iacuc";
    
    String PD_INITIATED_ROUTE_NODE_NAME = "Initiated";

    //Prop Dev Parameters
    String SPONSOR_HIERACHY_REQ_DIV_PROG_CODES = "SPONSOR_HIERACHY_REQ_DIV_PROG_CODES";
    String PROP_PERSON_COI_STATUS_FLAG = "PROP_PERSON_COI_STATUS_FLAG";
    String ENABLE_DISCLOSURE_STATUS_FROM_COI_MODULE = "ENABLE_DISCLOSURE_STATUS_FROM_COI_MODULE";
    String PROJECT_STATUS_FEATURE_FLAG = "COI_PROJECT_STATUS_FEATURE";
    String ENABLE_DISCLOSURE_DISPOSITION_STATUS_FROM_COI_MODULE = "ENABLE_DISCLOSURE_DISPOSITION_STATUS_FROM_COI_MODULE";


    String MM_DD_YYYY_HH_MM_A_DATE_FORMAT = "MM/dd/yyyy hh:mm a";
    String CAN_MAINTAIN_IP_ATTACHMENTS = "CAN_MAINTAIN_IP_ATTACHMENTS";
    String CAN_VIEW_IP_ATTACHMENTS = "CAN_VIEW_IP_ATTACHMENTS";

    // award actions
    String AWARD_BUDGET_PARAMETERS_ACTION = "awardBudgetParameters.do?";
    String AWARD_BUDGET_VERSIONS_ACTION = "awardBudgetVersions.do?";
    
    int PROTOCOL_DEFAULT_SUBMISSION_NUMBER = -1;

    String MAKE_AWD_CUM_ANTICIPATED_OBL_EDITABLE = "MAKE_AWD_CUM_ANTICIPATED_OBL_EDITABLE";
    String COI_PROJECTS_ENABLED = "coi.projects.enabled";
    String COI_PROJECTS = "coi.projects";
    String CONTENT_TYPE = "Content-Type";
    String APPLICATION_JSON = "application/json";
    String COI_PROJECTS_DISCLOSURE_STATUS_URL = "coi.projects.disclosure.status.url";
    String AUTHORIZATION_HEADER = "Authorization";
    String BEARER_TOKEN = "Bearer ";
    String SUB_AWARD_CODE = "subAwardCode";
    String CORRECTION_MODE = "correctionMode";
    String ALL_SPONSOR_HIERARCHY_NIH_MULTI_PI = "ALL_SPONSOR_HIERARCHY_NIH_MULTI_PI";
    String KC_SYS = "KC-SYS";
    String PENDING = "PENDING";
}

