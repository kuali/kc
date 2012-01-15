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


/**
 * This class contains constants.
 */
public final class KeyConstants {
    
    // Generic error messages and questions
    public static final String ERROR_MINLENGTH = "error.minLength";
    public static final String ERROR_MAXLENGTH = "error.maxLength";
    
    public static final String ERROR_INVALID_FORMAT_WITH_FORMAT = "error.invalidFormat.withFormat";
    public static final String ERROR_REQUIRED = "error.required";
    
    public static final String ERROR_REQUIRED_PROPOSAL_SPONSOR_ID = "error.required.proposalSponsorId";
    public static final String ERROR_NIH_SPONSOR_PROJECT_TITLE_LENGTH = "error.nih.sponsor.project.title.length";
    public static final String WARNING_EMPTY_DEADLINE_DATE = "warning.empty.deadline.date";
    public static final String ERROR_EMPTY_PRIME_SPONSOR_ID = "error.empty.primesponsorid";
    public static final String WARNING_PAST_DEADLINE_DATE = "warning.past.deadline.date";
    public static final String WARNING_ACTIVITY_TYPE_CHANGED = "warning.activityType.changed";
    public static final String ERROR_START_DATE_AFTER_END_DATE = "error.start.date.after.end.date";
    
    public static final String QUESTION_DELETE_CONFIRMATION = "document.question.delete.text";
    
    // Notification
    public static final String ERROR_NOTIFICATION_MODULE_CODE_ACTION_CODE_COMBINATION_EXISTS = "error.notification.moduleCode.actionCode.combination.exists";
    public static final String ERROR_NOTIFICATION_ROLE_NAME_EXISTS = "error.notification.roleNamespace.roleName.exists";
    public static final String ERROR_NOTIFICATION_EMPTY_NOTIFICATION_RECIPIENT = "error.notification.empty.notifcation.recipient";
    public static final String ERROR_NOTIFICATION_DUPLICATE_NOTIFICATION_RECIPIENT = "error.notification.duplicate.notification.recipient";
    public static final String ERROR_NOTIFICATION_RECIPIENTS_REQUIRED = "error.notification.recipients.required";
    
    // Special Review
    public static final String ERROR_SPECIAL_REVIEW_PROTOCOL_NUMBER_INVALID = "error.special.review.protocol.number.invalid";
    public static final String ERROR_SPECIAL_REVIEW_PROTOCOL_NUMBER_DUPLICATE = "error.special.review.protocol.number.duplicate";
    public static final String ERROR_SPECIAL_REVIEW_DATE_SAME_OR_LATER = "error.special.review.date.same.or.later";
    public static final String ERROR_SPECIAL_REVIEW_EMPTY_FOR_NOT_APPROVED = "error.special.review.empty.for.not.approved";
    public static final String ERROR_SPECIAL_REVIEW_REQUIRED_FOR_VALID = "error.special.review.required.for.valid";
    public static final String ERROR_SPECIAL_REVIEW_CANNOT_SELECT_EXEMPTION_FOR_VALID = "error.special.review.cannot.select.exemption.for.valid";
    public static final String QUESTION_SPECIAL_REVIEW_DELETE_CONFIRMATION = "question.special.review.delete.confirmation";
    public static final String ERROR_SPECIAL_REVIEW_PROTOCOL_LOCKED = "error.special.review.protocol.locked";
    
    // Abstracts and Attachments errors
    public static final String ERROR_PROPOSAL_ATTACHMENT_NOT_COMPLETE = "error.document.narrativestatuscode.not.complete";
    public static final String ERROR_PROPOSAL_ATTACHMENT_NOT_FOUND = "error.document.narrative.not.present";
    public static final String ERROR_PROPOSAL_MENTORINGPLAN_ATTACHMENT_NOT_FOUND = "error.document.mentoringplan.narrative.not.present";

    // Proposal Types System Parameter Names
    public static final String PROPOSALDEVELOPMENT_PROPOSALTYPE_NEW = "proposaldevelopment.proposaltype.new";
    public static final String PROPOSALDEVELOPMENT_PROPOSALTYPE_RENEWAL = "proposaldevelopment.proposaltype.renewal";
    public static final String PROPOSALDEVELOPMENT_PROPOSALTYPE_REVISION = "proposaldevelopment.proposaltype.revision";
    public static final String PROPOSALDEVELOPMENT_PROPOSALTYPE_CONTINUATION = "proposaldevelopment.proposaltype.continuation";
    public static final String PROPOSALDEVELOPMENT_PROPOSALTYPE_RESUBMISSION = "proposaldevelopment.proposaltype.resubmission";
    
    // Proposal Sites errors
    public static final String ERROR_PROPOSAL_SITES_STATE_CODE_INVALID = "error.document.accountMaintenance.stateCodeInvalid";
    public static final String ERROR_PROPOSAL_SITES_DISTRICT_INVALID_FORMAT = "error.congressionalDistrict.format";
    public static final String ERROR_PROPOSAL_SITES_DISTRICT_DUPLICATE = "error.congressionalDistrict.duplicate";
    public static final String ERROR_PROPOSAL_SITES_INDEX_INVALID_FORMAT = "error.integer";   // used for site indexes and district indexes
    public static final String ERROR_PROPOSAL_SITES_LOCATION_NAME_REQUIRED = "error.required.locationName";
    public static final String ERROR_PROPOSAL_SITES_ADDRESS_REQUIRED = "error.required.address";
    
    // Proposal Development Questions
    public static final String QUESTION_CONFIRM_CLEAR_DELIVERY_ADDRESS_INFO="document.question.clearDeliveryAddress.text";
    public static final String QUESTION_DELETE_PROPOSAL="document.question.deleteProposal.text";
    
    //Proposal Development Custome Attribute Date
    public static final String ERROR_DATE="error.date";
    
    // Key Personnel Mojo
    public static final String ERROR_INVESTIGATOR_UNITS_UPBOUND = "error.investigatorUnits.upbound";
    public static final String ERROR_INVESTIGATOR_UPBOUND = "error.principalInvestigators.upbound";
    public static final String ERROR_INVESTIGATOR_LOWBOUND = "error.principalInvestigators.lowbound";
    public static final String ERROR_MISSING_PERSON_ROLE = "error.missingPersonRole";
    public static final String ERROR_MISSING_CITIZENSHIP_TYPE = "error.missingcitizenship";
    public static final String ERROR_PROPOSAL_PERSON_EXISTS = "error.proposalPersonExists";
    public static final String ERROR_PROPOSAL_PERSON_EXISTS_WITH_ROLE = "error.proposalPersonExistsWithRole";
    public static final String ERROR_TOTAL_CREDIT_SPLIT_UPBOUND = "error.totalCreditSplit.upbound";
    public static final String ERROR_CREDIT_SPLIT_LOWBOUND = "error.creditSplit.lowbound";
    public static final String ERROR_CREDIT_SPLIT_UPBOUND = "error.creditSplit.upbound";
    public static final String ERROR_DELETE_LEAD_UNIT = "error.deleteLeadUnit";
    public static final String ERROR_ADD_EXISTING_UNIT = "error.addExistingUnit";
    public static final String ERROR_PROPOSAL_PERSON_CERTIFICATION_INCOMPLETE = "error.proposalperson.certfication.incomplete";
    public static final String ERROR_INVALID_YEAR = "error.invalid.year";
    public static final String ERROR_INVALID_UNIT = "error.invalid.unit";
    public static final String ERROR_SELECT_UNIT="error.select.unit";
    public static final String ERROR_ONE_UNIT="error.one.unit";
    public static final String ERROR_PERCENTAGE="error.percentage";
    
    public static final String ERROR_REQUIRED_FOR_FILE_NAME="error.required.for.fileName";
    public static final String ERROR_ABSTRACT_TYPE_NOT_SELECTED = "error.abstractType.notselected";
    public static final String ERROR_ABSTRACT_TYPE_INVALID = "error.abstractType.invalid";
    public static final String ERROR_ABSTRACT_TYPE_DUPLICATE = "error.abstractType.duplicate";
    public static final String QUESTION_DELETE_ABSTRACT_CONFIRMATION = "document.question.deleteAbstract.text";
    public static final String QUESTION_DELETE_ATTACHMENT_CONFIRMATION = "document.question.deleteAttachment.text";    
    public static final String QUESTION_DELETE_NOTE_CONFIRMATION = "document.question.deleteNote.text";
    public static final String ERROR_NARRATIVE_TYPE_DUPLICATE = "error.proposalAttachment.narrativeType.allowMulitple";
    public static final String ERROR_ATTACHMENT_TYPE_NOT_SELECTED = "error.proposalAttachment.narrativeType.notSelected";
    public static final String ERROR_ATTACHMENT_NOT_AUTHORIZED = "error.proposalAttachment.notAuthorized";
    public static final String ERROR_ATTACHMENT_STATUS_NOT_SELECTED = "error.proposalAttachment.narrativeStatus.notSelected";
    public static final String ERROR_NARRATIVE_TYPE_DESCRITPION_REQUIRED = "error.proposalAttachment.description.required";
    public static final String ERROR_PROTOCOL_ATTACHMENT_PERSONNEL_INVALID_TYPE = "error.protocol.attachment.personnel.invalid.type";
    public static final String ERROR_PROTOCOL_ATTACHMENT_PERSONNEL_DUPLICATE_TYPE = "error.protocol.attachment.personnel.duplicate.type";
    public static final String ERROR_PROTOCOL_ATTACHMENT_PERSONNEL_INVALID_DESCRIPTION = "error.protocol.attachment.personnel.invalid.description";
    public static final String ERROR_PROTOCOL_ATTACHMENT_PERSONNEL_EMPTY_FILE = "error.protocol.attachment.personnel.empty.file";
    public static final String ERROR_PERSONNEL_ATTACHMENT_DESCRIPTION_REQUIRED = "error.personnelAttachment.description.required";
    public static final String ERROR_PERSONNEL_ATTACHMENT_PERSON_REQUIRED = "error.personnelAttachment.person.required";
    public static final String QUESTION_DELETE_OPPORTUNITY_CONFIRMATION = "document.question.deleteOpportunity.text";
    public static final String QUESTION_RECALCULATE_BUDGET_CONFIRMATION = "document.question.recalculateBudget.text";
    public static final String ERROR_PERSONNEL_ATTACHMENT_PERSON_DUPLICATE = "error.personnelAttachment.person.duplicate";
    
    public static final String QUESTION_APPROVE_FUTURE_REQUESTS = "document.question.approve.text";
    
    public static final String ERROR_NARRATIVE_STATUS_INVALID = "error.proposalAttachment.moduleStatusCode.invalid";
    
    public static final String ERROR_MISSING = "error.missing";
    public static final String ERROR_INVALUD = "error.invalid";
    
    public static final String ERROR_PERSON_EDITABLE_FIELD_EXISTS = "error.person.editable.field.exists";
    public static final String ERROR_INVESTIGATOR_CREDIT_TYPE_EXISTS = "error.investigator.credit.type.exists";

    
    //AuthZ Errors
    public static final String ERROR_AUTHORIZATION_DOCUMENT_INITIATION = "error.authorization.document.initiation";
        
    // proposal ynq errors
    public static final String ERROR_REQUIRED_FOR_EXPLANATION = "error.required.for.explanation";
    public static final String ERROR_REQUIRED_FOR_REVIEW_DATE = "error.required.for.reviewDate";
    public static final String ERROR_REQUIRED_ANSWER = "error.required.answer";
    public static final String INVALID_DATE_REQUIRED_FOR = "error.invalid.date.required.for";
    public static final String INVALID_EXPLANATION_REQUIRED_FOR = "error.invalid.explanation.required.for";
        
    //proposal questionnaire errors
    public static final String ERROR_S2S_QUESTIONNAIRE_NOT_COMPLETE = "error.proposaldev.questionnaire.s2s.notcomplete";
    public static final String ERROR_QUESTIONNAIRE_NOT_COMPLETE = "error.proposaldev.questionnaire.notcomplete";
    // Budget Versions errors
    public static final String ERROR_BUDGET_NAME_MISSING = "error.budgetVersion.documentDescription.required";
    public static final String ERROR_NO_FINAL_BUDGET = "error.final.budget.required";
    public static final String CLEAR_AUDIT_ERRORS_BEFORE_CHANGE_STATUS_TO_COMPLETE = "clear.audit.error.before.change.status.to.complete";
    public static final String ERROR_MULTIPLE_FINAL_BUDGETS = "error.final.multiple";
    public static final String AUDIT_ERROR_NO_BUDGETVERSION_COMPLETE_AND_FINAL = "error.no.budgetVersion.completeAndFinal";
    public static final String ERROR_BUDGET_STATUS_COMPLETE_WHEN_NOT_MODULER = "error.budgetVersion.budgetStatusCompleteWhenNotModuler";
    public static final String ERROR_BUDGET_START_DATE_MISSING = "error.budgetVersion.startDate.required";
    public static final String ERROR_BUDGET_END_DATE_MISSING = "error.budgetVersion.endDate.required";
    public static final String ERROR_BUDGET_OBLIGATED_AMOUNT_INVALID = "error.award.invalidObligatedAmount";
    public static final String ERROR_AWARD_BUDGET_START_DATE_MISSING = "error.award.budgetVersion.startDate.required";
    public static final String ERROR_AWARD_BUDGET_END_DATE_MISSING = "error.award.budgetVersion.endDate.required";
    public static final String ERROR_DOCUMENT_STATUS_NOT_FINAL = "error.document.status.not.final";
    public static final String QUESTION_TOTALCOSTLIMIT_CHANGED = "document.question.totalCostLimit.changed";
    public static final String ERROR_BUDGET_REJECT_NO_REASON = "error.budget.reject.noReason";

    //Budget Version warnings
    public static final String WARNING_BUDGET_VERSION_MODULAR_INVALID_TDC = "warning.budgetVersion.Modular.InvalidTotalDirectCost";

    // Budget Personnel constants
    public static final String ERROR_DUPLICATE_BUDGET_PERSON = "error.budgetPerson.duplicate";
    public static final String QUESTION_DELETE_PERSON = "document.question.deletePerson.text";
    public static final String ERROR_NO_BUDGET_PERSON = "error.no.budget.person";
    public static final String WARNING_PERSONNEL_OTHER_SIGNIFICANT_CONTRIBUTOR = "warning.personnel.osc";

    // Budget Modular Constants
    public static final String QUESTION_SYNC_BUDGET_MODULAR = "document.question.syncBudgetModular.text";
    public static final String ERROR_NO_DETAILED_BUDGET = "error.modular.noDetail";
    
    // Budget Rates Constants
    public static final String QUESTION_SYNC_RATES = "document.question.syncRates.text";
    public static final String QUESTION_SYNC_ALL_RATES = "document.question.syncAllRates.text";
    public static final String QUESTION_RESET_RATES = "document.question.resetRates.text";
    public static final String QUESTION_RESET_ALL_RATES = "document.question.resetAllRates.text";
    public static final String QUESTION_RATE_CHANGED = "document.question.saveRates.text";
    
    // KRA Proposal Permission 
    public static final String QUESTION_DELETE_PROPOSAL_USER_CONFIRMATION = "document.question.deleteProposalUser.text";
    public static final String ERROR_NO_PERMISSION = "error.no.permission";
    public static final String ERROR_UNKNOWN_USERNAME = "error.unknown.username";
    public static final String ERROR_DUPLICATE_PROPOSAL_USER = "error.duplicate.proposalUser";
    public static final String ERROR_AGGREGATOR_INCLUSIVE = "error.aggregator.inclusive";
    public static final String ERROR_LAST_AGGREGATOR = "error.last.aggregator";
    
    public static final String ERROR_REQUIRE_ONE_NARRATIVE_MODIFY="error.narrative.one.modify";
    public static final String ERROR_REQUIRE_ONE_NARRATIVE_MODIFY_WITH_ARG="error.narrative.one.modify.with.arg";
    public static final String ERROR_NARRATIVE_USER_RIGHT_NO_PERMISSION="error.narrative.no.permission";
    public static final String ERROR_ONE_AGGREGATOR_MODFIY="error.one.aggregator.modify";
    public static final String ERROR_ONE_AGGREGATOR_MODIFY_WITH_ARG="error.one.aggregator.modify.with.arg";
    public static final String ERROR_PERMISSION_VIEWER_ONLY_KEY = "error.viewer.only";
    
    // Authorization
    public static final String AUTHORIZATION_VIOLATION = "error.authorization.violation";
    
    // Pessimistic Locking Cron Job
    public static final String PESSIMISTIC_LOCKING_CRON_EXPRESSION = "pessimisticLocking.cronExpression";
    
    // Grants.gov
    public static final String ERROR_IF_PROPOSALTYPE_IS_REVISION = "error.s2sopportunity.revisiontype";
    public static final String ERROR_IF_CFDANUMBER_AND_OPPORTUNITY_ID_IS_NULL = "error.s2sopportunity.cfdaNumber_opportunityId_null"; 
    public static final String ERROR_IF_REVISIONTYPE_IS_OTHER = "error.s2sopportunity.revisionTypeOther";
    public static final String ERROR_REQUIRED_REVISIONTYPE = "error.required.s2sopportunity.revisionType";
    public static final String ERROR_IF_CFDANUMBER_IS_INVALID = "error.s2sopportunity.cfdaNumberInvalid";
    public static final String ERROR_IF_OPPORTUNITY_ID_IS_INVALID = "error.s2sopportunity.opportunityIdInvalid";
    public static final String ERROR_IF_PROPOSAL_TYPE_IS_NEW_AND_S2S_SUBMISSION_TYPE_IS_CHANGED_CORRECTED = "error.s2sopportunity.s2ssubmissiontype";    
    public static final String ERROR_OPPORTUNITY_ID_DIFFER = "error.opportunityId.differ";
    public static final String ERROR_OPPORTUNITY_TITLE_DELETED = "error.opportunityTitle.deleted";
    public static final String ERROR_CFDA_NUMBER_DIFFER = "error.cfdaNumber.differ";
    public static final String ERROR_IF_REVISIONTYPE_IS_NOT_OTHER_SPECIFY_NOT_BLANK = "error.s2sopportunity.revisionTypeNotOtherSpecifyNotBlank";
    public static final String MESSAGE_IF_SEARCH_ON_ONLY_CFDA_NUMBER= "message.s2sopportunity.searchOnOnlyCfdaNumber";
    public static final String VALIDATTION_ERRORS_BEFORE_GRANTS_GOV_SUBMISSION = "validation.errors.before.grantsGov.submission";    
    public static final String QUESTION_SUMBMIT_OPPORTUNITY_WITH_WARNINGS_CONFIRMATION = "question.submitOpportunityWithWarnings.text";
    public static final String ERROR_ON_GRANTS_GOV_SUBMISSION = "error.on.grantsGov.submission";
    public static final String ERROR_MANDATORY_FORM_NOT_AVAILABLE = "error.mandatory.form.not.available";
    public static final String ERROR_S2SOPPORTUNITY_NOTSELECTED = "error.s2sOpportunity.notSelected";
    public static final String ERROR_RESUBMISSION_PROPOSALTYPE_IS_NEW = "error.resubmission.proposalType.is.new";
    public static final String ERROR_RESUBMISSION_INVALID_PROPOSALTYPE_SUBMISSIONTYPE = "error.resubmission.invalidProposalTypeSubmissionType";
    public static final String ERROR_RESUBMISSION_OPPORTUNITY_IS_APPLICATION = "error.resubmission.s2sOpportunity.isApplication";
    public static final String ERROR_NOT_SELECTED_SUBMISSION_TYPE = "error.submissionType.notSelected";
    public static final String ERROR_IF_GRANTS_GOV_IS_DISABLED = "error.grantsgov.disabled";
    public static final String ERROR_GRANTSGOV_FORM_PARSING = "error.grantsgov.form.parsing";
    public static final String ERROR_GRANTSGOV_FORM_PARSING_SAXEXCEPTION = "error.grantsgov.form.xmlprocess";
    public static final String ERROR_GRANTSGOV_FORM_SCHEMA_NOT_FOUND = "error.grantsgov.form.schema.server.down";
    
    public static final String ERROR_PRINTING_UNKNOWN = "error.printing.unknown";
    public static final String ERROR_IF_COMPETITION_ID_IS_INVALID="error.s2sopportunity.competitionIdInvalid";
   // Grants.gov System Parameters
    public static final String S2S_REVISIONTYPE_OTHER = "s2s.revisiontype.other";
    public static final String S2S_SUBMISSIONTYPE_CHANGEDCORRECTED = "s2s.submissiontype.changedCorrected";
    public static final String S2S_SUBMISSIONTYPE_APPLICATION = "s2s.submissiontype.application";
    
    public static final String PESSIMISTIC_LOCK_MESSAGE = "error.document.pessimisticLockMessage";
    public static final String SESSION_TIMEOUT_MESSAGE = "error.session.timeout";
    public static final String SESSION_EXPIRED_IND = "sessionExpired";
    public static final String ERROR_INACTIVE_CUSTOM_ATT_DOC = "error.inactive.customAttributeDocument";
    public static final String ERROR_INVALID_CUSTOM_ATT_ID = "error.invalid.customAttributeid";
    public static final String ERROR_WORKFLOW_SUBMISSION = "error.workflow.submission";
    public static final String ERROR_ADMIN_CORRECTION_SUBMISSION = "error.admin.correction.submission";
    
    //Budget Expense
    public static final String ERROR_BUDGET_PERIOD_NOT_SELECTED = "error.viewBudgetPeriod.notSelected";
    public static final String ERROR_LINEITEM_STARTDATE_BEFORE_PERIOD_STARTDATE = "error.lineItemStartDate.before.periodStartDate";
    public static final String ERROR_LINEITEM_ENDDATE_AFTER_PERIOD_ENDDATE = "error.lineItemEndDate.after.periodEndDate";
    public static final String ERROR_PERSONNELBUDGETLINEITEM_STARTDATE_BEFORE_LINEITEM_STARTDATE = "error.personnelBudgetLinelineItemStartDate.before.lineItemStartDate";
    public static final String ERROR_PERSONNELBUDGETLINEITEM_ENDDATE_AFTER_LINEITEM_ENDDATE = "error.personnelBudgetLineItemEndDate.after.lineItemEndDate";
    public static final String ERROR_PERCENT_EFFORT_INVALID = "error.percentEffortInvalid";
    public static final String ERROR_PERCENT_CHARGED_INVALID = "error.percentChargedInvalid";
    public static final String ERROR_PERCENT_EFFORT_LESS_THAN_PERCENT_CHARGED = "error.percentEffort.lessThan.percentCharged";
    public static final String ERROR_COST_ELEMENT_NOT_SELECTED = "error.costElement.notSelected";
    public static final String ERROR_BUDGET_PERSONNEL_NOT_SELECTED = "error.budgetPersonnel.notSelected";
    public static final String ERROR_BUDGET_PERSONNEL_INCOMPLETE = "error.budgetPersonnel.incomplete";
    public static final String ERROR_GLOBAL_MESSAGE = "error.global.application";
    public static final String WARNING_TOTAL_COST_LIMIT_EXCEEDED= "warning.budgetCostLimit.exceeded";
    public static final String WARNING_PERIOD_COST_LIMIT_EXCEEDED= "warning.periodCostLimit.exceeded";
    public static final String WARNING_UNRECOVERED_FA_NEGATIVE= "warning.unrecoveredFA.negative";

    public static final String AUDIT_ERROR_BUDGETPERIOD_START_BEFORE_PROJECT_START_DATE = "error.periodStartDate.before.projectStartDate";
    public static final String AUDIT_ERROR_BUDGETPERIOD_END_AFTER_PROJECT_END_DATE = "error.periodEndDate.after.projectEndDate";
    public static final String AUDIT_WARNING_BUDGETPERIOD_START_AFTER_PROJECT_START_DATE = "warning.budgetStartDate.after.projectStartDate";
    public static final String AUDIT_WARNING_BUDGETPERIOD_END_BEFORE_PROJECT_END_DATE = "warning.budgetEndDate.before.projectEndDate";

    public static final String AUDIT_ERROR_BUDGET_DISTRIBUTION_SOURCE_MISSING = "error.budget.distribution.sourceMissing";
    public static final String AUDIT_ERROR_BUDGET_DISTRIBUTION_FISCALYEAR_MISSING = "error.budget.distribution.fiscalYearMissing";
    public static final String AUDIT_ERROR_BUDGET_DISTRIBUTION_FISCALYEAR_INVALID = "error.budget.distribution.fiscalYearInvalid";
    public static final String AUDIT_ERROR_BUDGET_DISTRIBUTION_UNALLOCATED_NOT_ZERO = "error.budget.distribution.unallocatedMustBeZero";
    public static final String AUDIT_WARNING_BUDGET_DISTRIBUTION_FISCALYEAR_INCONSISTENT = "warning.budget.distribution.fiscalYearInconsistent";
    public static final String AUDIT_ERROR_BUDGET_DISTRIBUTION_DUPLICATE_UNRECOVERED_FA = "error.budget.distribution.duplicate.unrecoveredFA";
    public static final String AUDIT_ERROR_COMMENTS_REQUIRED_FOR_REBUDGET = "error.budget.comments.required.for.rebudget";
    public static final String AUDIT_ERROR_TOTAL_COST = "error.budget.total.cost";
    public static final String AUDIT_ERROR_COST_LIMIT = "error.budget.cost.limit";
    public static final String AUDIT_ERROR_COST_LIMITS_CHANGED = "error.budget.cost.limits.changed";
    public static final String AUDIT_ERROR_SPECIFIC_COST_LIMITS_CHANGED = "error.budget.specific.cost.limit.changed";

    // Copy proposal
    public static final String ERROR_LEAD_UNIT_REQUIRED = "error.leadUnit.required";
    
    public static final String ERROR_RATE_TYPE_NOT_EXIST = "error.rateType.not.exist";
    public static final String ERROR_RATE_TYPE_NOT_VALID_FOR_TYPE = "error.rateType.not.valid.for.type";
    public static final String ERROR_RATE_CLASS_NOT_VALID_FOR_TYPE = "error.rateClass.not.valid.for.type";
    
    public static final String ERROR_EFFECTIVE_DATE_OUT_OF_RANGE = "error.effective.date.out.of.range";
    public static final String ERROR_IMCOMPLETE_PERSON_ENTRIES = "error.incomplete.person.entries";
    
    // Submit to Sponsor
    public static final String MESSAGE_INSTITUTIONAL_PROPOSAL_CREATED = "message.institutionalproposal.created";
    public static final String MESSAGE_INSTITUTIONAL_PROPOSAL_VERSIONED = "message.institutionalproposal.versioned";
    public static final String MESSAGE_INSTITUTIONAL_PROPOSAL_NOT_CREATED = "message.institutionalproposal.not.created";
    public static final String AUTOGENERATE_INSTITUTIONAL_PROPOSAL_PARAM = "proposaldevelopment.autogenerate.institutionalproposal";
    public static final String ERROR_SUBMIT_TO_SPONSOR_PERMISSONS = "error.proposalDevelopment.permission.submitToSponsor";
    public static final String MESSAGE_INSTITUTIONAL_PROPOSAL_NOT_CREATED_INROUTE = "message.institutionalproposal.not.created.inroute";
    public static final String ERROR_PROPOSAL_DEVELOPMENT_RESUBMISSION_PROMPT_OPTION_REQUIRED = "error.proposalDevelopment.resubmission.prompt.option.required";
    
    // delete proposal
    public static final String ERROR_DELETE_PROPOSAL_IN_HIERARCHY="error.proposalDevelopment.deleteproposal.inhierarchy";
    
    // budget rates
    public static final String ERROR_REQUIRED_APPLICABLE_RATE = "error.required.applicableRate";
    public static final String ERROR_APPLICABLE_RATE_NOT_NUMERIC = "error.applicableRate.notNumeric";
    public static final String ERROR_APPLICABLE_RATE_LIMIT= "error.applicableRate.limit";
    public static final String ERROR_APPLICABLE_RATE_NEGATIVE = "error.applicableRate.negative";
    
    public static final String ERROR_SPONSOR_HIERARCHY_EXISTS = "error.sponsorHierarchyExists";

    public static final String QUESTION_SAVE_BUDGET_SUMMARY = "document.question.saveBudgetSummary.text";
    public static final String QUESTION_SAVE_BUDGET_SUMMARY_FOR_RATE_AND_DATE_CHANGE = "document.question.saveBudgetSummary.forRateAndDateChange.text";
    public static final String QUESTION_DELETE_BUDGET_PERIOD = "document.question.deleteBudgetPeriod.text";
    public static final String QUESTION_SYNCH_BUDGET_RATE = "document.question.synchBudgetRate.text";
    public static final String QUESTION_SYNCH_AWARD_RATE ="document.question.synchAwardRates.text";
    public static final String QUESTION_NO_RATES_ATTEMPT_SYNCH = "document.question.noRatesSynch.text";

    public static final String ERROR_DELETE_PERSON_WITH_PERSONNEL_DETAIL = "error.delete.person.with.personnelDetails";
    public static final String ERROR_DELETE_LINE_ITEM = "error.delete.lineitem";
    public static final String ERROR_INSERT_BUDGET_PERIOD = "error.insert.budget.period";
    public static final String ERROR_APPLY_TO_LATER_PERIODS = "error.applyTo.later.periods";
    public static final String ERROR_PERSONNELLINEITEM_APPLY_TO_LATER_PERIODS = "error.personnelLineItem.applyTo.later.periods";

    public static final String PROPOSAL_DATA_OVERRIDE_SAME_VALUE = "error.proposalData.override.samevalue";
    public static final String PROPOSAL_EDITABLECOLUMN_DATATYPE_MISMATCH = "error.column.datatype.mismatch";
    public static final String PROPOSAL_EDITABLECOLUMN_DATALENGTH_MISMATCH = "error.column.dataLength.mismatch";
    
    public static final String ERROR_LINE_ITEM_DATES = "error.line.item.dates";
    public static final String ERROR_NEGATIVE_AMOUNT = "error.negativeAmount";
    public static final String WARNING_EFFDT_AFTER_PERIOD_START_DATE = "warning.effdt.after.periodStartDate";
    public static final String WARNING_BASE_SALARY_ZERO = "warning..base.salary.zero";

    public static final String ERROR_COST_SHARE_PERCENTAGE = "error.costSharing.percentage";
    public static final String ERROR_COST_SHARE_DUPLICATE = "error.costSharing.duplicate";
    public static final String ERROR_NO_FIELD_TO_EDIT = "error.alterproposaldata.nofieldtoedit";
    public static final String ERROR_PERSONNEL_DETAIL_DATES = "error.personnel.detail.dates";
    public static final String ERROR_PERSONNEL_DETAIL_END_DATE = "error.personnel.detail.end.date";
    public static final String ERROR_PERSONNEL_DETAIL_START_DATE = "error.personnel.detail.start.date";
    public static final String ERROR_LINE_ITEM_END_DATE = "error.line.item.end.date";
    public static final String ERROR_LINE_ITEM_START_DATE = "error.line.item.start.date";
    public static final String QUESTION_DEFAULT_BUDGET_PERIODS = "document.question.defaultBudgetPeriods.text";
    public static final String AUDIT_WARNING_RATE_OUT_OF_SYNC = "warning.rate.outofsync";
    //public static final String ERROR_DUPLICATE_PERSON = "error.duplicate.person";

    // Budget Version
    public static final String BUDGET_VERSION_EXISTS = "error.budgetVersion.exists";
    public static final String MOVE_UNIT_OWN_DESCENDANTS = "error_move_unit";
    public static final String UNIT_ADMINISTRATOR_MULTIPLE_TYPES_NOT_ALLOWED = "error.unitAdministrator.multiple.types.not.allowed";

    public static final String PERSONNEL_CATEGORY = "P";
    public static final String CANNOT_SYNC_TO_ZERO_LIMIT = "error.sync.to.zero.limit";
    public static final String TOTAL_COST_ALREADY_IN_SYNC = "total.cost.already.sync";
    public static final String INSUFFICIENT_AMOUNT_TO_SYNC = "insufficient.amount.to.sync";
    public static final String PERSONNEL_LINE_ITEM_EXISTS = "personnelLineItem.exists";
    
    // budget personnel details
    public static final String ERROR_REQUIRED_PERIOD_TYPE = "error.required.periodTypeCode";
    public static final String ERROR_PERSONNEL_EXISTS = "error.personnelExists";
    public static final String ERROR_SUMMARY_LINEITEM_EXISTS = "error.summaryLineItemExists";
    public static final String ERROR_SUMMARY_LINEITEM_EXISTS_ADD_PERSON = "error.summaryLineItemExists.addPerson";
    public static final String ERROR_PERSON_JOBCODE_CHANGE = "error.person.jobcodeChange";
    public static final String ERROR_PERSON_JOBCODE_VALUE = "error.person.jobcodeValue";
    public static final String ERROR_DUPLICATE_PERSON = "error.duplicate.personnel";
    public static final String ERROR_JOBCODE_COST_ELEMENT_COMBO_INVALID = "error.jobCode.costElement.invalid";
    public static final String ERROR_SAVE_JOBCODE_COST_ELEMENT_COMBO_INVALID = "error.save.jobCode.costElement.invalid";
   
    public static final String ERROR_AWARD_LEAD_UNIT_NOT_AUTHORIZED = "error.award.leadunit.invalid";
    // Award Approved Equipment
    public static final String ERROR_AWARD_APPROVED_EQUIPMENT_AMOUNT_REQUIRED = "error.required";
    public static final String ERROR_AWARD_APPROVED_EQUIPMENT_ITEM_REQUIRED = ERROR_AWARD_APPROVED_EQUIPMENT_AMOUNT_REQUIRED;
    public static final String ERROR_AWARD_APPROVED_EQUIPMENT_AMOUNT_VALID = "error.awardApprovedEquipment.amount.invalid";
    public static final String ERROR_AWARD_APPROVED_EQUIPMENT_ITEM_NOT_UNIQUE = "error.awardApprovedEquipment.notunique";
    
    //Award Payment Schedule    
    public static final String ERROR_AWARD_PAYMENT_SCHEDULE_DUE_DATE_REQUIRED = "error.required";
    public static final String ERROR_AWARD_PAYMENT_SCHEDULE_ITEM_NOT_UNIQUE = "error.awardPaymentSchedule.notunique";
    public static final String ERROR_SCHEDULE_GENERATION_FREQ_BASE_IS_NULL = "error.schedule.generation.freq.base.is.null";
    //Award Payment Invoices
    public static final String ERROR_AWARD_INVALID_BASIS_OF_PAYMENT_FOR_AWARD_TYPE="error.award.paymentsAndInvoices.invalidBasisOfPaymentForAwardType";
    public static final String ERROR_AWARD_INVALID_BASIS_AND_METHOD_OF_PAYMENT = "error.award.paymentsAndInvoices.invalidBasisAndMethodOfPayment";
    
    
    //Time & Money - Transactions
    public static final String ERROR_TNM_PENDING_TRANSACTION_ITEM_NOT_UNIQUE = "error.awardPendingTransaction.notunique";
    public static final String ERROR_TNM_PENDING_TRANSACTION_SOURCE_AND_DESTINATION_AWARDS_ARE_SAME = "error.awardPendingTransaction.sourceAndDestinationAreSame";
    
    //Award Closeout
    public static final String ERROR_AWARD_CLOSEOUT_REPORT_NAME_REQUIRED = "error.required";
    public static final String ERROR_AWARD_CLOSEOUT_ITEM_NOT_UNIQUE = "error.awardCloseout.notunique";
    
    
    // Award Details and Dates
    public static final String ERROR_INVALID_AWARD_TRANSFERRING_SPONSOR = "error.sponsor.invalid";
    public static final String ERROR_ANTICIPATED_AMOUNT = "error.anticipated.amount";
    public static final String ERROR_OBLIGATED_AMOUNT_NEGATIVE = "error.award.obligated.negative";
    public static final String ERROR_ANTICIPATED_AMOUNT_NEGATIVE = "error.award.anticipated.negative";
    public static final String ERROR_DUPLICATE_AWARD_TRANSFERRING_SPONSOR = "error.sponsor.duplicate";
    public static final String ERROR_AWARD_EFFECTIVE_DATE = "error.award.effective.date";
    public static final String ERROR_OBLIGATION_EXPIRATION_DATE = "error.obligation.expiration.date";
    public static final String ERROR_INVALID_CFDA = "error.award.cfda.invalid";
    
    //Award Budget
    public static final String WARNING_AWARD_BUDGET_COSTLIMIT_NOTEQUAL_OBLIGATED = "warning.awardbudget.limitNotEqual.obligated";
    
    //Award Cost Share
    public static final String ERROR_FISCAL_YEAR_RANGE = "error.awardCostShare.fiscalYear.range";
    public static final String ERROR_PROJECT_PERIOD_RANGE = "error.awardCostShare.projectPeriod.range";
    public static final String ERROR_FISCAL_YEAR_REQUIRED = "error.awardCostShare.fiscalYear.required";
    public static final String ERROR_SOURCE_DESTINATION = "error.awardCostShare.source.destination";
    public static final String ERROR_DUPLICATE_ENTRY = "error.awardCostShare.duplicate.entry";
    public static final String ERROR_COST_SHARE_PERCENTAGE_RANGE = "error.awardCostShare.percentage.range";
    public static final String ERROR_COST_SHARE_TYPE_REQUIRED = "error.awardCostShare.costShareTypeCode.required";
    public static final String ERROR_COST_SHARE_TYPE_INVALID = "error.awardCostShare.costShareTypeCode.invalid";
    public static final String ERROR_COST_SHARE_COMMITMENT_AMOUNT_REQUIRED = "error.awardCostShare.commitmentAmount.required";
    public static final String ERROR_COST_SHARE_COMMITMENT_AMOUNT_INVALID = "error.awardCostShare.commitmentAmount.invalid";
    public static final String ERROR_COST_SHARE_MET_INVALID = "error.awardCostShare.costShareMet.invalid";
    
    //Award Sponsor Term
    public static final String ERROR_DUPLICATE_SPONSOR_TERM = "error.awardSponsorTerm.duplicate.term";
    public static final String ERROR_NO_SPONSOR_TERM = "error.awardSponsorTerm.no.term";
    public static final String ERROR_INVALID_SPONSOR_TERM = "error.awardSponsorTerm.invalid.term";
    
    //Award Benefits Rates
    public static final String ERROR_BENEFITS_RATES = "error.awardBenefitsRates.invalid.rates";
    
    //Award Approved Subaward
    public static final String ERROR_AMOUNT_IS_ZERO = "error.awardApprovedSubaward.Amount.equals.zero";
    public static final String ERROR_DUPLICATE_ORGANIZATION_NAME = "error.awardApprovedSubaward.duplicate.organization.name";
    public static final String ERROR_ORGANIZATION_NAME_IS_NULL = "error.awardApprovedSubaward.organization.is.null";
    public static final String ERROR_ORGANIZATION_NAME_IS_INVALID = "error.awardApprovedSubaward.invalid.organization.name";
    //Award Cost Share Confirmation Questions
    public static final String QUESTION_DELETE_COST_SHARE = "document.question.deleteCostShare.text";
    
    //Award Cost Share Confirmation Questions
    public static final String QUESTION_SYNC_UNIT_CONTACTS = "document.question.syncUnitContacts.text";
    public static final String QUESTION_SYNC_UNIT_DETAILS = "document.question.syncUnitDetails.text";
    
    public static final String QUESTION_SYNC_PANEL = "document.question.syncPanel.text";
    public static final String QUESTION_SYNC_FULL = "document.question.syncAll.text";
    public static final String QUESTION_SYNC_PANEL_TO_EMPTY = "document.question.syncPanelToEmpty.text";
      
    //Award Attachments
    public static final String QUESTION_DELETE_ATTACHMENT = "document.question.deleteAttachment.text";
    public static final String AWARD_ATTACHMENT_TYPE_CODE_REQUIRED = "error.awardNotesAndAttachments.attachment.typeRequired";
    public static final String AWARD_ATTACHMENT_FILE_REQUIRED = "error.awardNotesAndAttachments.attachment.fileRequired";
    
    //Award Budget
    public static final String ERROR_AWARD_OR_MONEY_DOC_NOT_FINAL = "error.budget.award.not.final";
    public static final String ERROR_AWARD_OBLIGATED_FUNDS = "error.award.obligated.funds";
    public static final String ERROR_AWARD_UNFINALIZED_BUDGET_EXISTS = "error.budget.unfinalized.exist";

    
    //Award Direc F and A Distribution
    public static final String ERROR_AWARD_FANDA_INVALID_RTAES = "error.awardDirectFandADistribution.invalid.rate";
    public static final String ERROR_AWARD_FANDA_INVALID_RTAES_FOR_SINGLE_RATE = "error.awardDirectFandADistribution.invalid.rate.for.single.rate";
    public static final String ERROR_AWARD_FANDA_DISTRIB_START_DATE_REQUIRED = "error.awardDirectFandADistribution.start.date.required";
    public static final String ERROR_AWARD_FANDA_DISTRIB_END_DATE_REQUIRED = "error.awardDirectFandADistribution.end.date.required";
    public static final String ERROR_AWARD_FANDA_DISTRIB_DIRECT_COST_REQUIRED = "error.awardDirectFandADistribution.direct.cost.required";
    public static final String ERROR_AWARD_FANDA_DISTRIB_DIRECT_COST_POSITIVE = "error.awardDirectFandADistribution.direct.cost.positive";
    public static final String ERROR_AWARD_FANDA_DISTRIB_DIRECT_COST_NEGATIVE = "error.awardDirectFandADistribution.direct.cost.negative";
    public static final String ERROR_AWARD_FANDA_DISTRIB_INDIRECT_COST_REQUIRED = "error.awardDirectFandADistribution.indirect.cost.required";
    public static final String ERROR_AWARD_FANDA_DISTRIB_INDIRECT_COST_POSITIVE = "error.awardDirectFandADistribution.indirect.cost.positive";
    public static final String ERROR_OVERLAPPING_EXISTING_DATES = "error.awardDirectFandADistribution.overlapping.existing.dates";
    public static final String ERROR_AWARD_FANDA_DISTRIB_START_BEFORE_END_DATE = "error.awardDirectFandADistribution.invalid.start.before.end.dates";
    public static final String ERROR_START_DATE_PRECEDES_END_DATE = "error.awardDirectFandADistribution.invalid.start.end.dates";
    public static final String ERROR_OVERLAPPING_DATE_RANGES = "error.awardDirectFandADistribution.date.ranges.overlap";
    public static final String ERROR_TARGET_START_DATE = "error.awardDirectFandADistribution.invalid.start.date";
    public static final String ERROR_TARGET_END_DATE = "error.awardDirectFandADistribution.invalid.end.date";
    public static final String WARNING_AWARD_FANDA_DISTRIB_LIMITNOTEQUAL_ANTICIPATED = "warning.awardDirectFandADistribution.limitNotEqual.anticipated";
    public static final String WARNING_AWARD_FANDA_DISTRIB_BREAKS = "warning.awardDirectFandADistribution.break.existing.dates";
    public static final String WARNING_AWARD_FANDA_DISTRIB_EXISTS = "warning.awardDirectFandADistribution.exists";
    //Award Indirect Cost Rate
    public static final String ERROR_REQUIRED_APPLICABLE_INDIRECT_COST_RATE = "error.required.applicable.indirect.cost.rate";
    public static final String ERROR_APPLICABLE_INDIRECT_COST_RATE_CAN_NOT_BE_NEGATIVE = "error.applicable.indirect.cost.rate.can.not.be.negative";
    public static final String ERROR_APPLICABLE_INDIRECT_COST_RATE_OUT_OF_RANGE = "error.applicable.indirect.cost.rate.out.of.range";
    public static final String ERROR_REQUIRED_INDIRECT_RATE_TYPE_CODE = "error.required.indirect.rate.type.code";
    public static final String ERROR_REQUIRED_FISCAL_YEAR = "error.required.fiscal.year";
    public static final String ERROR_REQUIRED_START_DATE= "error.required.start.date";
    public static final String ERROR_END_DATE_BEFORE_START_DATE_INDIRECT_COST_RATE = "error.endDate.before.startDate.for.valid.indirectCostRate";
    public static final String ERROR_FISCAL_YEAR_INCORRECT_FORMAT = "error.fiscalYear.IncorrectFormat";
    
    //Award Reports   
    public static final String ERROR_REQUIRED_CONTACT_TYPE = "error.required.contact.type";
    public static final String ERROR_REQUIRED_ROLODEX_ID = "error.required.rolodex.id";
    public static final String ERROR_EMPTY_REPORT_TERMS = "error.empty.report.terms";
    public static final String ERROR_EMPTY_TERMS = "error.empty.terms";
    public static final String ERROR_AWARD_REPORT_TERM_ITEM_NOT_UNIQUE = "error.awardReportTermItem.notunique";
    public static final String ERROR_AWARD_REPORT_TERM_ITEM_OSP_REQUIRED = "error.awardReportTermItem.ospRequired";
    public static final String ERROR_AWARD_REPORT_TERM_ITEM_FREQUENCY_REQUIRED = "error.awardReportTermItem.frequencyRequired";
    public static final String ERROR_AWARD_REPORT_TERM_ITEM_NO_PI = "error.awardReportTermItem.noPI";
    public static final String ERROR_AWARD_REPORT_TERM_RECIPIENT_ITEM_NOT_UNIQUE = "error.awardReportTermRecipientItem.notunique";
    public static final String ERROR_BOTH_SPONSOR_AND_ROLODEX_ARE_SELECTED = "error.both.sponsor.and.rolodex.are.selected";
    public static final String ERROR_BOTH_SPONSOR_AND_ROLODEX_ARE_NOT_SELECTED = "error.both.sponsor.and.rolodex.are.not.selected";
    public static final String ERROR_REQUIRED_ORGANIZATION_FIELD = "error.organizasion.required";
    //Award System Parameters    
    public static final String ENABLE_AWARD_FNA_VALIDATION = "enable.award.FnA.validation";
    public static final String OPTION_WARNING_ERROR_AWARD_FANDA_VALIDATION = "option.warning.error.award.FnA.validation";
    public static final String ENABLED_PARAMETER_VALUE_ONE = "1";
    public static final String ENABLED_PARAMETER_VALUE_TWO = "2";
    public static final String WARNING = "W";
    public static final String ERROR = "E";
    public static final String MIT_IDC_VALIDATION_ENABLED = "mit.idc.validation.enabled";
    public static final String MIT_IDC_VALIDATION_ENABLED_VALUE_FOR_COMPARISON = "1";
    public static final String REPORT_CLASS_FOR_PAYMENTS_AND_INVOICES = "reportClassForPaymentsAndInvoices";
    public static final String PERIOD_IN_YEARS_WHEN_FREQUENCY_BASE_IS_FINAL_EXPIRATION_DATE
        = "scheduleGenerationPeriodInYearsWhenFrequencyBaseCodeIsFinalExpirationDate";
    public static final String CONTACT_TYPE_OTHER = "contactTypeOther";
    public static final String CLOSE_OUT_REPORT_TYPE_USER_DEFINED = "closeoutReportTypeUserDefined";
    public static final String CLOSE_OUT_REPORT_TYPE_FINANCIAL_REPORT = "closeoutReportTypeFinancialReport";
    public static final String CLOSE_OUT_REPORT_TYPE_TECHNICAL = "closeoutReportTypeTechnical";
    public static final String CLOSE_OUT_REPORT_TYPE_PATENT = "closeoutReportTypePatent";
    public static final String CLOSE_OUT_REPORT_TYPE_PROPERTY = "closeoutReportTypeProperty";
    public static final String CLOSE_OUT_REPORT_TYPE_INVOICE = "closeoutReportTypeInvoice";
    public static final String AWARD_ACTIVE_STATUS_CODES_PARM = "AWARD_ACTIVE_STATUS_CODES";
    public static final String AWARD_COST_SHARING_PARM = "AWARD_COST_SHARING";
    public static final String AWARD_FABRICATED_EQUPIMENT_PARM = "AWARD_FABRICATED_EQUIPMENT";
    
    //Award Validation Error Messages
    public static final String INDIRECT_COST_RATE_NOT_IN_PAIR = "indirectCostRate.not.in.pair";
    public static final String INVALID_REPORT_CODE_FOR_REPORT_CLASS = "error.invalid.type.for.reportClass";
    public static final String INVALID_FREQUENCY_FOR_REPORT_CLASS_AND_TYPE = "error.invalid.frequency.for.reportClass.and.type";
    public static final String INVALID_FREQUENCY_BASE_FOR_FREQUENCY = "error.invalid.frequencyBase.for.frequency";
    public static final String ERROR_REQUIRED_ORGANIZATION = "error.required.organization";
    public static final String ERROR_PAYMENT_AND_INVOICE_COMMENT_NOT_ALLOWED = "error.invalid.award.payment.invoice.comment.comment.not.allowed";
    public static final String ERROR_PAYMENT_AND_INVOICE_COMMENT_REQUIRED = "error.invalid.award.payment.invoice.comment.required";
    public static final String ERROR_ACCOUNT_NUMBER_ALREADY_EXISTS = "error.award.duplicateAccountNumber";
    
    //Award account creation validation error messages
    public static final String AWARD_NO_VALID_EFFECTIVE_DATE = "error.award.createAccount.invalid.effectiveDate";
    public static final String AWARD_ID_NOT_SPECIFIED = "error.award.createAccount.invalid.awardId";
    public static final String AWARD_END_DATE_NOT_SPECIFIED = "error.award.createAccount.invalid.endDate";
    public static final String AWARD_ACTIVITY_TYPE_CODE = "error.award.createAccount.invalid.activityTypeCode";
    public static final String AWARD_PAYMENT_BASIS_NOT_SPECIFIED = "error.award.createAccount.invalid.paymentBasis";
    public static final String AWARD_PAYMENT_METHOD_NOT_SPECIFIED = "error.award.createAccount.invalid.paymentMethod";
    public static final String CREATE_ACCOUNT_SERVICE_ERRORS = "error.award.createAccount.serviceErrors";
    public static final String AWARD_ADDRESS_NOT_COMPLETE = "error.award.createAccount.invalid.piAddress";
    public static final String AWARD_PI_NOT_SPECIFIED = "error.award.createAccount.invalid.pi";
    public static final String AWARD_F_AND_A_RATE_NOT_SPECIFIED = "error.award.createAccount.invalid.rate";
    public static final String AWARD_ICR_RATE_TYPE_CODE_EMPTY = "error.award.createAccount.icr.rate.type.code.empty";
    public static final String AWARD_ICR_RATE_CODE_EMPTY = "error.award.createAccount.icr.rate.code.empty";
    public static final String ACCOUNT_ALREADY_CREATED = "error.award.createAccount.account.already.created";
    public static final String AWARD_ACCOUNT_INVALID_WSDL_URL = "error.award.createAccount.invalid.wsdlurl";
    public static final String AWARD_ACCOUNT_NUMER_NOT_VALID = "error.award.account.number.invalid";
    public static final String AWARD_CHART_OF_ACCOUNTS_CODE_NOT_VALID = "error.award.chart.of.accounts.code.invalid";
    public static final String AWARD_ACCOUNT_NUMBER_NOT_SPECIFIED = "error.award.createAccount.invalid.accountNumber";
    public static final String BOTH_ACCOUNT_AND_CHART_REQUIRED = "error.award.account.number.and.chart.required";    
    public static final String CANNOT_CONNECT_TO_SERVICE = "error.award.createAccount.cannotConnect";
    public static final String CURRENT_RATE_NOT_SPECIFIED = "error.award.createAccount.invalid.currentRate";
    public static final String DOCUMENT_NUMBER_NULL = "error.award.createAccount.nullDocumentNumber";
    public static final String DOCUMENT_SAVED_WITH_ERRORS = "error.award.createAccount.saved.with.errors";
    public static final String NO_PERMISSION_TO_LINK_ACCOUNT = "error.award.link.account.no.permission";
    public static final String NO_PERMISSION_TO_CREATE_ACCOUNT = "error.award.createAccount.noPermission";
    public static final String VALIDATION_DID_NOT_OCCUR = "error.award.account.number.not.validated"; 
    
    // award budget
    public static final String BUDGET_POSTED = "message.award.budget.adjustment.budget.posted";
    public static final String BUDGET_ADJUSTMENT_DOCUMENT_NOT_CREATED = "error.award.budget.adjustment.document.not.created";
    public static final String BUDGET_ADJUSTMENT_DOC_EXISTS = "error.award.budget.adjustment.document.exists";
    public static final String EMPTY_ACCOUNTING_LINES = "error.award.budget.adjustment.empty.accounting.lines";
    public static final String FINANCIAL_OBJECT_CODE_MAPPING_NOT_FOUND = "error.award.budget.adjustment.financial.objectCode.mapping.not.found";
    public static final String FINANCIAL_OBJECT_CODE_MAPPING_EXISTS = "error.award.budget.financial.objectCode.mapping.exists";
    public static final String ICR_TYPE_CODE_MAPPING_EXISTS = "error.award.budget.icr.type.code.mapping.exists";
    
    //Award template validation errors
    public static final String ERROR_NO_TEMPLATE_CODE = "error.award.templateCode.notFound";
    public static final String INVALID_REPORT_FREQUENCY = "error.invalid.award.awardTemplate.reportTerm.reportFreqency";
    public static final String INVALID_BASIS_PAYMENT = "error.invalid.award.awardTemplate.awardBasisPayment";
    public static final String INVALID_METHOD_PAYMENT = "error.invalid.award.awardTemplate.awardMethodPayment";
    public static final String ERROR_CAN_NOT_SELECT_BOTH_FIELDS = "error.award.awardTemplateRecipient.canNotSelectBothFields";
    public static final String ERROR_ONE_FIELD_MUST_BE_SELECTED = "error.award.awardTemplateRecipient.oneFieldMustBeSelected";
    public static final String ERROR_DUPLICATE_ROLODEX_ID = "error.award.awardTemplateRecipient.duplicateRolodexId";
    
    //Award Sponsor Template Errors
    public static final String ERROR_TERM_REQUIRED = "error.award.sponsorTemplate.term.required";
    //Award hierarchy sync validation errors
    public static final String ERROR_SYNC_DESCENDANT_BLANK = "error.award.awardhierarchy.sync.whichdescendants";
    public static final String ERROR_SYNC_AWARD_STATUS = "error.award.awardhierarchy.sync.invalidstatus";
    
    //COI Required Fields validation messages
    public static final String ERROR_COI_ATTACHMENT_MISSING_DESC = "error.coi.attachment.description.required";
    public static final String ERROR_COI_ATTACHMENT_MISSING_FILE = "error.coi.attachment.missing.file";
    
    // IRB Protocol Research Area Validation messages
    public static final String ERROR_PROTOCOL_RESEARCH_AREA_REQUIRED = "error.protocol.researchArea.required";
    public static final String ERROR_PROTOCOL_RESEARCH_AREA_NOT_ACTIVE = "error.protocol.researchArea.notactive";
    // IRB Protocol Funding Source Validation messages
    public static final String ERROR_PROTOCOL_FUNDING_SOURCE_REQUIRED = "error.protocol.fundingSource.required";
    public static final String QUESTION_PROTOCOL_FUNDING_SOURCE_DELETE_CONFIRMATION = "question.protocol.fundingSource.delete.confirmation";
    public static final String ERROR_PROTOCOL_FUNDING_SOURCE_AWARD_LOCKED = "error.protocol.fundingSource.award.locked";
    public static final String ERROR_PROTOCOL_FUNDING_SOURCE_INSTITUTIONAL_PROPOSAL_LOCKED = "error.protocol.fundingSource.institutional.proposal.locked";
    
    //IRB Protocol Search Screen Error Messages
    public static final String ERROR_PROTOCOL_SEARCH_INVALID_DATE = "error.protocol.search.invalid.date";
    
    //IRB Protocol Online Review Error Messages
    public static final String ERROR_PROTOCOL_ONLINE_REVIEW_INVALID_ONE_PERSON_ONLY="error.protocol.onlinereview.search.onlyoneperson";
    
    // IRB Protocol Participant Types Validation Messages
    public static final String ERROR_PROTOCOL_PARTICIPANT_TYPE_DUPLICATE = "error.protocol.participantType.duplicate";

    // IRB Protocol Location Validation Messages
    public static final String ERROR_PROTOCOL_LOCATION_ORGANIZATION_ID_NOT_FOUND = "error.protocolLocation.organizationId.notFound";
    public static final String ERROR_PROTOCOL_LOCATION_ORGANIZATION_ID_INVALID = "error.protocolLocation.organizationId.invalid";
    public static final String ERROR_PROTOCOL_LOCATION_ORGANIZATION_ID_DUPLICATE = "error.protocolLocation.organizationId.duplicate";
    public static final String ERROR_PROTOCOL_LOCATION_ORGANIZATION_TYPE_CODE_NOT_FOUND = "error.protocolLocation.organizationTypeCode.notFound";
    public static final String ERROR_PROTOCOL_LOCATION_SHOULD_EXIST = "error.protocolLocation.shouldExist";
    
    // IRB Protocol Required Fields Validation Messages
    public static final String ERROR_PROTOCOL_PRINCIPAL_INVESTIGATOR_ID_NOT_FOUND = "error.protocolRequiredFields.prinInvestigatorId.notFound";
    public static final String ERROR_PROTOCOL_PRINCIPAL_INVESTIGATOR_NAME_NOT_FOUND = "error.protocolRequiredFields.prinInvestigatorName.notFound";
    public static final String ERROR_PROTOCOL_LEAD_UNIT_NUM_INVALID = "error.protocolRequiredFields.leadUnitNum.invalid";
    public static final String ERROR_PROTOCOL_TITLE_NOT_FOUND = "error.protocolRequiredFields.title.notFound";
    public static final String ERROR_PROTOCOL_TYPE_NOT_FOUND = "error.protocolRequiredFields.typeCode.notFound";

    // IRB Protocol Actions Required Fields Validation Messages
    public static final String ERROR_PROTOCOL_SUBMISSION_TYPE_NOT_SELECTED = "error.protocol.submissionTypeCode.notFound";
    public static final String ERROR_PROTOCOL_SUBMISSION_QUALIFIER_NOT_SELECTED = "error.protocol.submissionQualifer.notFound";
    public static final String ERROR_PROTOCOL_SUBMISSION_EXPEDITED_CHECKBOX_REQ="error.protocol.submission.expeditedCheckbox";
    public static final String ERROR_PROTOCOL_SUBMISSION_EXEMPT_CHECKBOX_REQ="error.protocol.submission.exemptCheckbox";
    public static final String ERROR_PROTOCOL_REVIEW_TYPE_NOT_SELECTED = "error.protocol.reviewTypeCode.notFound";
    public static final String ERROR_PROTOCOL_REVIEW_TYPE_INVALID = "error.protocol.reviewTypeCode.invalid";
    public static final String ERROR_PROTOCOL_REVIEW_TYPE_MUST_BE_FYI = "error.protocol.reviewTypeCode.mustbe.fyi";
    public static final String ERROR_PROTOCOL_REVIEWER_CANNOT_REMOVE_REVIEW_FINAL = "warning.protocol.reviewer.removeReview.cannotRemoveFinalReview";
    public static final String ERROR_PROTOCOL_REVIEWER_CANNOT_REMOVE_REVIEW_EXISTING_COMMENTS = "warning.protocol.reviewer.removeReview.cannotRemoveReviewWithComments";
    public static final String ERROR_PROTOCOL_REVIEWER_CANNOT_REMOVE_FINAL_REVIEW = "warning.protocol.reviewer.removeReview.cannotRemoveFinalReview";
    public static final String MESSAGE_REMOVE_REVIEWERS_WITH_COMMENTS="message.protocol.reviewer.removeReview.removeReviewWithComments";
    public static final String ERROR_PROTOCOL_REVIEWER_TYPE_INVALID = "error.protocol.reviewerTypeCode.invalid";
    public static final String ERROR_PROTOCOL_REVIEWER_NOT_AVAILABLE = "error.protocol.reviewer.unavailable";
    public static final String ERROR_PROTOCOL_REVIEWER_TYPE_ALREADY_USED = "error.protocol.reviewerTypeCode.used";
    public static final String ERROR_PROTOCOL_AT_LEAST_ONE_CHECKLIST_ITEM = "error.protocol.checkList.one";
    public static final String ERROR_PROTOCOL_COMMITTEE_NOT_SELECTED = "error.protocol.submission.committee.required";
    public static final String ERROR_PROTOCOL_SCHEDULE_NOT_SELECTED = "error.protocol.submission.schedule.required";
    public static final String ERROR_PROTOCOL_ADMIN_CORRECTION_COMMENTS_REQUIRED = "error.protocol.correction.comments.required";
    public static final String ERROR_PROTOCOL_APPROVAL_DATE_REQUIRED = "error.protocol.approve.approveDate.required";
    public static final String ERROR_PROTOCOL_APPROVAL_EXPIRATION_DATE_REQUIRED = "error.protocol.approve.expirationDate.required";
    public static final String ERROR_PROTOCOL_GENERIC_ACTION_DATE_REQUIRED = "error.protocol.generic.actionDate.required";
    public static final String ERROR_PROTOCOL_UNDO_LASTACTION_COMMENTS_REQUIRED = "error.protocol.undolastaction.comments.required";
    public static final String ERROR_PROTOCOL_RECORD_COMMITTEE_ABSTAIN_RECUSED_ALREADY_EXISTS = "error.protocol.recordcommittee.abstainer.already.used";
    public static final String ERROR_PROTOCOL_RECORD_COMMITEE_NO_MOTION = "error.protocol.recordcommittee.motion.not.selected";
    public static final String ERROR_PROTOCOL_RECORD_COMMITEE_NO_NO_VOTES = "error.protocol.recordcommittee.no.nocount";
    public static final String ERROR_PROTOCOL_RECORD_COMMITEE_NO_YES_VOTES = "error.protocol.recordcommittee.no.yescount";
    public static final String ERROR_PROTOCOL_RECORD_COMMITEE_INVALID_VOTE_COUNT = "error.protocol.recordcommittee.votecount.invalid";
    public static final String ERROR_PROTOCOL_RECORD_COMMITEE_VOTE_MOTION_MISMATCH = "error.protocol.recordcommittee.vote.count.motion.mismatch";
    public static final String ERROR_PROTOCOL_RECORD_COMMITEE_NO_SMR_SRR_REVIEWER_COMMENTS = "error.protocol.recordcommittee.no.smrssr.reviewer.comments";
    public static final String ERROR_PROTOCOL_ASSIGN_TO_AGENDA_NO_ACTION_DATE = "error.protocol.actiondate.required";
    public static final String ERROR_PROTOCOL_DOCUMENT_STATE_CHANGED = "error.protocol.document.state.changed";
    
    public static final String MESSAGE_PROTOCOL_ACTION_SUCCESSFULLY_COMPLETED = "message.protocol.action.successfully.completed";
    public static final String MESSAGE_ONLINE_REVIEW_ACTION_SUCCESSFULLY_COMPLETED = "message.protocol.onlinereview.document.action.successfully.completed";
    
    //IRB Protocol Online Review Required Fields Validation Messages
    public static final String ERROR_ONLINE_REVIEW_COMMENT_REQUIRED = "error.protocol.onlinereview.comment.required";
    public static final String ERROR_ONLINE_REVIEW_COMMENTS_FINAL_AFTER_REVIEWER_ROUTE = "error.protocol.onlinereview.comments.final.after.reviewer.route";
    public static final String ERROR_ONLINE_REVIEW_ALL_COMMENTS_NOT_FINAL = "error.protocol.onlinereview.all.comments.not.final";
    public static final String ERROR_ONLINE_REVIEW_STATUS_REQUIRED = "error.protocol.onlinereview.status.required";
    public static final String ERROR_ONLINE_REVIEW_REJECTED_REASON_REQUIRED = "error.protocol.onlinereview.comment.rejected";
    public static final String ERROR_ONLINE_REVIEW_ATTACHMENT_DESCRIPTION_REQUIRED = "error.protocol.onlinereview.attachment.description.required";
    
    //  Committee Messages
    public static final String ERROR_COMMITTEE_DUPLICATE_ID = "error.committee.duplicateId";
    public static final String ERROR_COMMITTEE_DUPLICATE_NAME = "error.committee.duplicateName";
    public static final String ERROR_COMMITTEE_INVALID_ID = "error.committee.invalidId";
    public static final String ERROR_COMMITTEE_RESEARCH_AREA_INACTIVE = "error.committee.researchArea.inactive";
    public static final String ERROR_COMMITTEE_REVIEW_TYPE_REQUIRED = "error.committee.reviewType.required";
    
    //CommitteeMembership Messages
    public static final String ERROR_COMMITTEE_MEMBERSHIP_PERSON_NOT_SPECIFIED = "error.committee.membership.person.not.specified";
    public static final String ERROR_COMMITTEE_MEMBERSHIP_PERSON_DUPLICATE = "error.committee.membership.person.duplicate";
    public static final String ERROR_COMMITTEE_MEMBERHSIP_PERSON_NO_NAME = "error.committee.membership.person.no.name";
    public static final String ERROR_COMMITTEE_MEMBERSHIP_TERM_END_DATE_BEFORE_TERM_START_DATE = "error.committee.membership.termEndDate.before.termStartDate";
    public static final String ERROR_COMMITTEE_MEMBERSHIP_ROLE_NOT_SPECIFIED = "error.committee.membership.role.not.specified";
    public static final String ERROR_COMMITTEE_MEMBERSHIP_ROLE_START_DATE_NOT_SPECIFIED = "error.committee.membership.roleStartDate.not.specified";
    public static final String ERROR_COMMITTEE_MEMBERSHIP_ROLE_END_DATE_NOT_SPECIFIED = "error.committee.membership.roleEndDate.not.specified";
    public static final String ERROR_COMMITTEE_MEMBERSHIP_ROLE_END_DATE_BEFORE_ROLE_START_DATE = "error.committee.membership.roleEndDate.before.roleStartDate";
    public static final String ERROR_COMMITTEE_MEMBERSHIP_ROLE_START_DATE_OUTSIDE_TERM = "error.committee.membership.roleStartDate.outside.term";
    public static final String ERROR_COMMITTEE_MEMBERSHIP_ROLE_END_DATE_OUTSIDE_TERM = "error.committee.membership.roleEndDate.outside.term";
    public static final String ERROR_COMMITTEE_MEMBERSHIP_ROLE_MISSING = "error.committee.membership.role.missing";
    public static final String ERROR_COMMITTEE_MEMBERSHIP_ROLE_DUPLICATE = "error.committee.membership.role.duplicate";
    public static final String ERROR_COMMITTEE_MEMBERSHIP_EXPERTISE_MISSING = "error.committee.membership.expertise.missing";
    public static final String ERROR_COMMITTEE_MEMBERSHIP_EXPERTISE_INACTIVE = "error.committee.membership.expertise.inactive";
    
    
    public static final String SOFT_ERRORS_KEY = "SOFT_ERRORS_KEY";

    // IRB Protocol Personnel Validation Messages
    public static final String ERROR_PROTOCOL_PERSONNEL_MULTIPLE_PI = "error.protocolPersonnel.pi.already.exists";
    public static final String ERROR_PROTOCOL_PERSONNEL_PI_SAMEAS_COI = "error.protocolPersonnel.pi.sameas.coi";
    public static final String ERROR_PROTOCOL_PERSONNEL_ROLE_MANDATORY = "error.protocolPersonnel.role.mandatory";
    public static final String ERROR_DUPLICATE_PROTOCOL_PERSONNEL = "error.duplicate.protocolPersonnel";
    public static final String ERROR_ROLE_CHANGE_NOT_PERMITTED = "error.role.change.notPermitted";
    public static final String ERROR_PRINCIPAL_INVESTIGATOR_NOT_FOUND = "error.investigator.notFound";
    public static final String ERROR_PROTOCOL_UNIT_NOT_FOUND = "error.protocolUnit.notFound";
    public static final String ERROR_PROTOCOL_LEAD_UNIT_NOT_DEFINED = "error.protocolLeadUnit.notDefined";
    public static final String ERROR_PROTOCOL_UNIT_INVALID = "error.protocolUnit.invalid";
    public static final String ERROR_PROTOCOL_UNIT_DUPLICATE = "error.protocolUnit.duplicate";
    public static final String ERROR_PROTOCOL_INVESTIGATOR_INVALID = "error.protocol.investigator.invalid";
    public static final String QUESTION_DELETE_PROTOCOL_CONFIRMATION = "document.question.deleteProtocol.text";
    
    //IRB Protocol Note And Attachment Validation Messages
    public static final String ERROR_PROTOCOL_ATTACHMENT_MISSING_TYPE = "error.protocolAttachment.missing.type";
    public static final String ERROR_PROTOCOL_ATTACHMENT_MISSING_DESC = "error.protocolAttachment.description.required";
    public static final String ERROR_PROTOCOL_ATTACHMENT_MISSING_FILE = "error.protocolAttachment.missing.file";
    public static final String ERROR_PROTOCOL_ATTACHMENT_MISSING_PERSON = "error.protocolAttachment.missing.person";
    public static final String ERROR_PROTOCOL_ATTACHMENT_DUPLICATE_TYPE = "error.protocolAttachment.duplicate.type";
    public static final String ERROR_PROTOCOL_ATTACHMENT_INVALID_PERSON = "error.protocolAttachment.invalid.person";
    public static final String ERROR_PROTOCOL_ATTACHMENT_INVALID_TYPE = "error.protocolAttachment.invalid.type";
    public static final String AUDIT_ERROR_PROTOCOL_ATTACHMENT_STATUS_COMPLETE = "error.protocolAttachment.not.complete";
    
    // IRB Protocol Funding Sources Validation Messages
    public static final String ERROR_PROTOCOL_FUNDING_SOURCE_TYPE_NOT_FOUND = "error.protocolFundingSource.fundingSourceType.notFound";
    public static final String ERROR_PROTOCOL_FUNDING_SOURCE_NUMBER_NOT_FOUND = "error.protocolFundingSource.fundingSourceNumber.notFound";
    public static final String ERROR_PROTOCOL_FUNDING_SOURCE_NUMBER_INVALID_FOR_TYPE = "error.protocolFundingSource.fundingSourceNumber.invalidForType";
    public static final String ERROR_PROTOCOL_FUNDING_SOURCE_NAME_NOT_FOUND = "error.protocolFundingSource.fundingSourceName.notFound";
    public static final String ERROR_PROTOCOL_FUNDING_SOURCE_DUPLICATE = "error.protocolFundingSource.fundingSource.duplicate";
    public static final String ERROR_FUNDING_LOOKUPTEMP_UNAVAIL = "error.protocolFundingSource.fundingSourceLookupTemp.invalid";
    public static final String ERROR_FUNDING_LOOKUP_UNAVAIL = "error.protocolFundingSource.fundingSourceLookup.invalid";
    public static final String ERROR_FUNDING_LOOKUP_NOT_FOUND = "error.protocolFundingSource.fundingSourceLookup.notFound";
    
    // IRB Protocol Research Area Validation Messages
    public static final String ERROR_PROTOCOL_RESEARCH_AREA_INACTIVE = "error.protocol.researchArea.inactive";
    
    // Permissions
    public static final String ERROR_DUPLICATE_PERMISSIONS_USER = "error.duplicate.permissionsUser";
    public static final String ERROR_PERMISSIONS_LAST_ADMINSTRATOR = "error.permissions.last.administrator";
    public static final String ERROR_PERMISSIONS_ADMINSTRATOR_INCLUSIVE = "error.permissions.administrator.inclusive";
        
    //IRB Additional Information Fields
    public static final String ERROR_PROTOCOLREFERENCE_PROTOCOLREFERENCETYPECODE = "error.required.for.protocolReference.protocolReferenceTypeCode";
    public static final String ERROR_PROTOCOLREFERENCE_PROTOCOLREFERENCEKEY = "error.required.for.protocolReference.protocolReferenceKey";
    public static final String ERROR_PROTOCOLREFERENCE_INVALID_DATE = "error.protocolReferences.date";
    
    public static final String ERROR_PROTOCOL_SUMMARY_IS_REQUIRED = "error.amendment.required.summary";
    public static final String ERROR_PROTOCOL_SELECT_MODULE = "error.amendment.select.module";
    
    // IRB Protocol Risk Level
    public static final String ERROR_PROTOCOL_DUPLICATE_RISK_LEVEL = "error.duplicate.risk.level";
    public static final String ERROR_PROTOCOL_DATE_INACTIVATED_REQUIRED = "error.date.inactivated.required";
    
    //Institutional Proposal Validation
    public static final String ERROR_EMPTY_GRADUATE_STUDENT_DATA = "error.empty.graduate.student.data";
    public static final String ERROR_INVALID_SPONSOR_CODE = "error.invalid.sponsorCode";
    public static final String ERROR_INVALID_PRIME_SPONSOR_CODE = "error.invalid.primeSponsorCode";
    public static final String ERROR_INVALID_AWARD_ID = "error.invalid.awardId";
    public static final String ERROR_INVALID_ROLODEX_ID = "error.invalid.rolodexId";
    
    //Institutional Proposal Financial Validation
    public static final String ERROR_FINANCIAL_DATES = "error.financial.dates";
    public static final String ERROR_FINANCIAL_COSTS = "error.financial.costs";

    
    //Institutional Proposal Cost Share
    public static final String ERROR_IP_FISCAL_YEAR_RANGE = "error.institutionalProposalCostShare.fiscalYear.range";
    public static final String ERROR_IP_FISCAL_YEAR_REQUIRED = "error.institutionalProposalCostShare.fiscalYear.required";
    public static final String ERROR_IP_COST_SHARE_TYPE_REQUIRED = "error.institutionalProposalCostShare.costShareTypeCode.required";
    public static final String ERROR_IP_COST_SHARE_TYPE_INVALID = "error.institutionalProposalCostShare.costShareTypeCode.invalid";
    public static final String ERROR_IP_COST_SHARE_COMMITMENT_AMOUNT_REQUIRED = "error.institutionalProposalCostShare.amount.required";
    public static final String ERROR_IP_COST_SHARE_COMMITMENT_AMOUNT_INVALID = "error.institutionalProposalCostShare.amount.invalid";
    public static final String ERROR_IP_COST_SHARE_SOURCE_ACCOUNT_REQUIRED = "error.institutionalProposalCostShare.sourceAccount.required";
    public static final String ERROR_IP_FISCAL_YEAR_INCORRECT_FORMAT = "error.fiscalYear.IncorrectFormat";
    
    //Institutional Proposal Unrecovered FandA
    public static final String QUESTION_DELETE_UNRECOVERED_FNA = "document.question.deleteUnrecoveredFandA.text";
    public static final String ERROR_PROPOSAL_UFNA_AMOUNT_REQUIRED = "error.institutionalProposalUnrecoveredFandA.amount.required";
    public static final String ERROR_PROPOSAL_UFNA_FISCAL_YEAR_RANGE = "error.institutionalProposalUnrecoveredFandA.fiscalYear.range";
    public static final String ERROR_PROPOSAL_UFNA_FISCAL_YEAR_REQUIRED = "error.institutionalProposalUnrecoveredFandA.fiscalYear.required";
    public static final String ERROR_PROPOSAL_UFNA_RATE_TYPE_CODE_REQUIRED = "error.institutionalProposalUnrecoveredFandA.rateTypeCode.required";
    public static final String ERROR_PROPOSAL_UFNA_RATE_TYPE_CODE_INVALID = "error.institutionalProposalUnrecoveredFandA.rateTypeCode.invalid";
    public static final String ERROR_PROPOSAL_UFNA_AMOUNT_INVALID = "error.institutionalProposalUnrecoveredFandA.amount.invalid";
    public static final String ERROR_PROPOSAL_UFNA_FISCAL_YEAR_FORMAT = "error.institutionalProposalUnrecoveredFandA.fiscalYear.IncorrectFormat";
    public static final String ERROR_PROPOSAL_UFNA_APPLICABLE_RATE_INVALID = "error.institutionalProposalUnrecoveredFandA.percentage.range";
    public static final String ERROR_PROPOSAL_UFNA_SOURCE_ACCOUNT_REQUIRED = "error.institutionalProposalUnrecoveredFandA.sourceAccount.required";
    public static final String ERROR_PROPOSAL_UFNA_DUPLICATE_ROW = "error.institutionalProposalUnrecoveredFandA.duplicate.row";

    // Institutional Proposal - Proposal Log
    public static final String ERROR_MULTIPLE_PRINCIPAL_INVESTIGATORS = "error.proposalLog.multiplePi";
    public static final String ERROR_INVALID_STATUS_CHANGE = "error.proposalLog.invalidStatusChange";
    public static final String ERROR_INVALID_LEADUNIT = "error.proposalLog.invalidLeadUnit";
    public static final String ERROR_UNAUTHORIZED_LEAD_UNIT = "error.proposalLog.unauthorizedLeadUnit";
    public static final String ERROR_MISSING_PRINCIPAL_INVESTIGATOR = "error.proposalLog.noPi";
    public static final String ERROR_INVALID_PI = "error.proposalLog.invalidPi";
    public static final String ERROR_MISSING_SPONSOR_CODE = "error.proposalLog.noSponsorCode";
    public static final String INVALID_FILE_NAME = "error.institutionalProposal.notesAndAttachments.fileName.invalid";
    public static final String INVALID_TEXT = "error.institutionalProposal.notesAndAttachments.description.invalid";

    // Institutional Proposal Actions
    public static final String QUESTION_UNLOCK_FUNDED_AWARDS = "question.unlock.fundedAwards";
    public static final String ERROR_VALIDATION_ERRORS_EXIST = "error.institutionalproposal.validationFailed";

    //CommitteeSchedule Messages
    public static final String ERROR_COMMITTEESCHEDULE_STARTDATE_BLANK = "error.committeeSchedule.start.date.blank";
    public static final String ERROR_COMMITTEESCHEDULE_STARTANDENDDATE_EQUAL = "error.committeeSchedule.start.and.endDate.equal";
    public static final String ERROR_COMMITTEESCHEDULE_STARTANDENDDATE = "error.committeeSchedule.start.and.endDate";
    public static final String ERROR_COMMITTEESCHEDULE_BLANK = "error.committeeSchedule.blank";    
    public static final String ERROR_COMMITTEESCHEDULE_DATE_CONFLICT = "error.committeeSchedule.date.conflict"; 
    public static final String ERROR_COMMITTEESCHEDULE_DATES_SKIPPED = "error.committeeSchedule.dates.skipped";
    public static final String ERROR_COMMITTEESCHEDULE_FILTER_DATE = "error.committeeSchedule.filter.date";
    public static final String ERROR_COMMITTEESCHEDULE_DEADLINE = "error.committeeSchedule.dealine";
    public static final String ERROR_COMMITTEESCHEDULE_VIEWTIME = "error.committeeSchedule.viewtime";
    public static final String ERROR_COMMITTEESCHEDULE_VIEWTIME_BLANK = "error.committeeSchedule.viewTime.blank";
    public static final String ERROR_COMMITTEESCHEDULE_VIEWTIME_FORMATTING = "error.committeeSchedule.viewTime.formatting";
    public static final String ERROR_COMMITTEESCHEDULE_ENDTIME_BEFORE_STARTTIME = "error.committeeSchedule.endTime.before.startTime";
    public static final String ERROR_COMMITTEESCHEDULE_DAY = "error.committeeSchedule.day";
    public static final String ERROR_COMMITTEESCHEDULE_WEEKDAY = "error.committeeSchedule.weekday";
    public static final String ERROR_COMMITTEESCHEDULE_DELETE = "error.committeeSchedule.delete";
    public static final String ERROR_COMMITTEEMEMBER_DELETE = "error.committeeMember.delete";
    
    // Committee Actions Messages
    public static final String ERROR_COMMITTEE_ACTION_GENERATE_BATCH_CORRESPONDENCE_TYPE_CODE_NOT_SPECIFIED = "error.committee.action.generate.batch.correspondence.type.code.not.specified";
    public static final String ERROR_COMMITTEE_ACTION_GENERATE_MISSING_TEMPLATES = "error.committee.action.generate.missing.templates";
    public static final String ERROR_COMMITTEE_ACTION_GENERATE_START_DATE_NOT_SPECIFIED = "error.committee.action.generate.start.date.not.specified";
    public static final String ERROR_COMMITTEE_ACTION_GENERATE_END_DATE_NOT_SPECIFIED = "error.committee.action.generate.end.date.not.specified";
    public static final String ERROR_COMMITTEE_ACTION_GENERATE_END_DATE_BEFORE_START_DATE = "error.committee.action.generate.end.date.before.start.date";
    public static final String ERROR_COMMITTEE_ACTION_GENERATE_VIEW_NOT_SPECIFIED = "error.committee.action.generate.view.not.specified";
    public static final String ERROR_COMMITTEE_ACTION_HISTORY_BATCH_CORRESPONDENCE_TYPE_CODE_NOT_SPECIFIED = "error.committee.action.history.batch.correspondence.type.code.not.specified";
    public static final String ERROR_COMMITTEE_ACTION_HISTORY_START_DATE_NOT_SPECIFIED = "error.committee.action.history.start.date.not.specified";
    public static final String ERROR_COMMITTEE_ACTION_HISTORY_END_DATE_NOT_SPECIFIED = "error.committee.action.history.end.date.not.specified";
    public static final String ERROR_COMMITTEE_ACTION_HISTORY_END_DATE_BEFORE_START_DATE = "error.committee.action.history.end.date.before.start.date";
    public static final String ERROR_COMMITTEE_ACTION_HISTORY_VIEW_NOT_SPECIFIED = "error.committee.action.history.view.not.specified";
    public static final String ERROR_COMMITTEE_ACTION_PRINT_REPORT_NOT_SPECIFIED = "error.committee.action.print.report.not.specified";
    
    //Committee Management Questions
    public static final String QUESTION_COMMITTEE_MANAGEMENT_DELETE_MINUTE_CONFIRMATION = "document.question.committee.management.delete.minute";
    public static final String QUESTION_COMMITTEE_MANAGEMENT_DELETE_OTHER_ACTION_CONFIRMATION = "document.question.committee.management.delete.otherAction";
    
    // IRB Questions
    public static final String QUESTION_PROTOCOL_CONFIRM_SUBMIT_FOR_REVIEW = "question.protocol.confirmExceedMaxProtocols";
    public static final String QUESTION_CONFIRM_SCHEDULE_CHANGE_REMOVE_EXISTING_REVIEWS = "question.protocol.confirmRemoveExistingReviews";
    public static final String QUESTION_CONFIRM_UPDATE_REVIEW_TO_FINAL="question.protocol.onlineReview.confirmUpdateToFinal";
    public static final String QUESTION_PROTOCOL_CONFIRM_FOLLOWUP_ACTION = "question.protocol.confirmFollowupAction";

    // Questionnaire
    public static final String QUESTION_EDIT_QUESTION_OF_ACTIVE_QUESTIONNAIRE = "document.question.editQuestionOfActiveQuestionnaire.text";
    public static final String ERROR_QUESTION_STATUS_IN_USE = "error.question.status.in.use";
    public static final String ERROR_QUESTION_RESPONSE_TYPE_NOT_SPECIFIED = "error.question.response.type.not.specified";
    public static final String ERROR_QUESTION_RESPONSE_TYPE_INVALID = "error.question.response.type.invalid";
    public static final String ERROR_QUESTION_DISPLAYED_ANSWERS_INVALID_BOXES = "error.question.displayed.answers.invalid.boxes";
    public static final String ERROR_QUESTION_DISPLAYED_ANSWERS_INVALID_AREAS = "error.question.displayed.answers.invalid.areas";
    public static final String ERROR_QUESTION_ANSWER_MAX_LENGTH_INVALID = "error.question.answer.max.length.invalid";
    public static final String ERROR_QUESTION_ANSWER_MAX_LENGTH_VALUE_TOO_LARGE = "error.question.answer.max.length.value.too.large";
    public static final String ERROR_QUESTION_MAX_ANSWERS_INVALID_ANSWERS_BOXES = "error.question.max.answers.invalid.answers.boxes";
    public static final String ERROR_QUESTION_MAX_ANSWERS_INVALID_ANSWERS_AREAS = "error.question.max.answers.invalid.answers.areas";
    public static final String ERROR_QUESTION_MAX_ANSWERS_INVALID_RETURNS = "error.question.max.answers.invalid.returns";
    public static final String ERROR_QUESTION_LOOKUP_CLASS_NOT_SPECIFIED = "error.question.lookup.class.not.specified";
    public static final String ERROR_QUESTION_LOOKUP_RETURN_NOT_SPECIFIED = "error.question.lookup.return.not.specified";
    public static final String ERROR_QUESTION_LOOKUP_RETURN_INVALID = "error.question.lookup.return.invalid";
    public static final String ERROR_QUESTIONNAIRE_NAME_EXIST = "error.questionnaire.name.exist";
    public static final String ERROR_QUESTIONNAIRE_FILENAME_INVALID = "error.questionnaire.filename.invalid";    
    public static final String ERROR_QUESTIONNAIRE_QUESTION_INACTIVE = "error.questionnaire.question_inactive";
    
    // meeting
    public static final String ERROR_ADD_MEMBER_PRESENT = "error.add.member.present";
    public static final String ERROR_PRESENT_MEMBER_ABSENT = "error.presnt.member.absent";
    public static final String ERROR_EMPTY_PERSON = "error.empty.person";
    public static final String ERROR_DUPLICATE_ALTERNATE_FOR = "error.duplicate.alternateFor";
    public static final String ERROR_EMPTY_ATTENDANCE = "error.empty.attendance";
    public static final String ERROR_EMPTY_PROTOCOL = "error.empty.protocol";
    public static final String ERROR_EMPTY_PROTOCOL_CONTINGENCY = "error.empty.protocol.contingency";
    public static final String ERROR_EMPTY_ACTION_ITEMS = "error.empty.action.items";
    public static final String ERROR_EMPTY_ACTION_ITEMS_DESCRIPTION = "error.empty.action.items.description";
    public static final String ERROR_CANNOT_DELETE_ACTION_ITEM_IN_USE = "error.cannot.delete.action.item.in.use";

    
    //Time And Money
    public static final String ERROR_TRANSACTION_TYPE_CODE_REQUIRED = "error.transaction.type.code.required";
    public static final String ERROR_OBLIGATED_AMOUNT_INVALID = "error.obligated.amount.invalid";
    public static final String ERROR_ANTICIPATED_AMOUNT_INVALID = "error.anticipated.amount.invalid";
    public static final String ERROR_TOTAL_AMOUNT_INVALID = "error.total.amount.invalid";
    public static final String ERROR_DATE_NOT_SET = "error.date.not.set";
    public static final String ERROR_DATE_NULL = "error.date.null";
    public static final String ERROR_OBLIGATED_DATES_INVALID = "error.obligated.dates.invalid";
    public static final String ERROR_OBLIGATED_END_DATE = "error.obligated.end.date";
    public static final String ERROR_NET_TOTALS_TRANSACTION = "error.net.totals.transaction";
    public static final String ERROR_DIRECT_INDIRECT_TRANSACTION = "error.direct.indirect.transaction";
    public static final String WARNING_TRANSACTION_OBLI_LESS_THAN_BUDGET_LIMIT = "warning.transaction.obligated.less.than.limit";

    //Award Hierarchy
    public static final String ERROR_CREATE_NEW_CHILD_OTHER_AWARD_NOT_SELECTED = "error.create.new.child.other.award.not.selected";
    public static final String ERROR_CREATE_NEW_CHILD_NO_OPTION_SELECTED = "error.create.new.child.no.option.selected";
    public static final String ERROR_COPY_AWARD_CHILDOF_AWARD_NOT_SELECTED = "error.copy.award.childOf.award.not.selected";
    public static final String ERROR_COPY_AWARD_NO_OPTION_SELECTED = "error.copy.award.no.option.selected";
    
    //grants.gov server errors
    public static final String ERROR_GRANTSGOV_OPP_SER_UNAVAILABLE = "error.grantsgov.opportunity.server.unavailable";
    public static final String ERROR_GRANTSGOV_SERVER_STATUS_REFRESH = "error.grantsgov.server.status.refresh";
    public static final String ERROR_GRANTSGOV_SERVER_APPLICATION_LIST_REFRESH = "error.grantsgov.server.applicationList.refresh";
    public static final String ERROR_GRANTSGOV_SERVER_SUBMIT_APPLICATION = "error.grantsgov.server.submit.application";
    public static final String ERROR_KEYSTORE_CONFIG = "error.s2s.keystore.config";
    public static final String ERROR_KEYSTORE_CONFIG_SECURITY = "error.s2s.keystore.config.security";
    public static final String ERROR_S2S_UNKNOWN = "error.s2s.unknown";
    public static final String ERROR_PROPOSAL_REQUIRE_PRIOR_AWARD = "error.proposal.require.priorAward";
    public static final String ERROR_PROPOSAL_REQUIRE_ID_CHANGE_APP = "error.proposal.require.id.changeapp";
    public static final String ERROR_PROPOSAL_REQUIRE_PRIOR_AWARD_FOR_RESUBMIT = "error.proposal.require.priorAwardResubmit";
    public static final String ERROR_AWARD_CANNOT_BE_VERSIONED = "error.award.cannot.be.versioned";
    
    public static final String ERROR_S2S_KEYSTORE_CREATION = "error.s2s.keystore.creation";
    public static final String ERROR_S2S_KEYSTORE_NO_ALGORITHM = "error.s2s.keystore.no.algorithm";
    public static final String ERROR_S2S_KEYSTORE_BAD_CERTIFICATE = "error.s2s.keystore.badcertificate";
    public static final String ERROR_S2S_KEYSTORE_NOT_FOUND = "error.s2s.keystore.not.found";
    public static final String ERROR_S2S_KEYSTORE_CANNOT_READ = "error.s2s.keystore.cannot.read";

    public static final String ERROR_S2S_TRUSTSTORE_CREATION = "error.s2s.truststore.creation";
    public static final String ERROR_S2S_TRUSTSTORE_NO_ALGORITHM = "error.s2s.truststore.no.algorithm";
    public static final String ERROR_S2S_TRUSTSTORE_BAD_CERTIFICATE = "error.s2s.truststore.badcertificate";
    public static final String ERROR_S2S_TRUSTSTORE_NOT_FOUND = "error.s2s.truststore.not.found";
    public static final String ERROR_S2S_TRUSTSTORE_CANNOT_READ = "error.s2s.truststore.cannot.read";

    // Correspondence
    public static final String ERROR_CORRESPONDENCE_TEMPLATE_COMMITTEE_NOT_SPECIFIED = "error.correspondence.template.committee.not.specified";
    public static final String ERROR_CORRESPONDENCE_TEMPLATE_COMMITTEE_DUPLICATE = "error.correspondence.template.committee.duplicate";
    public static final String ERROR_CORRESPONDENCE_TEMPLATE_EMPTY_FILE = "error.correspondence.template.empty.file";
    public static final String ERROR_CORRESPONDENCE_TEMPLATE_INVALID_FILE_TYPE = "error.correspondence.template.invalid.file.type";
    public static final String ERROR_CORRESPONDENCE_TEMPLATE_INVALID_FILE = "error.correspondence.template.invalid.file";
    public static final String ERROR_BATCH_CORRESPONDENCE_DAYS_TO_EVENT_NOT_SPECIFIED = "error.batch.correspondence.days.to.event.not.specified";
    public static final String ERROR_BATCH_CORRESPONDENCE_DAYS_TO_EVENT_INVALID = "error.batch.correspondence.days.to.event.invalid";
    public static final String ERROR_BATCH_CORRESPONDENCE_DAYS_TO_EVENT_NEGATIVE = "error.batch.correspondence.days.to.event.negative";
    public static final String ERROR_BATCH_CORRESPONDENCE_DAYS_TO_EVENT_DUPLICATE = "error.batch.correspondence.days.to.event.duplicate";
    public static final String ERROR_BATCH_CORRESPONDENCE_PROTO_CORRESP_TYPE_CODE_NOT_SPECIFIED = "error.batch.correspondence.proto.corresp.type.code.not.specified";
    public static final String ERROR_BATCH_CORRESPONDENCE_FINAL_ACTION_DAY_NOT_SPECIFIED = "error.batch.correspondence.final.action.day.not.specified";
    public static final String ERROR_BATCH_CORRESPONDENCE_FINAL_ACTION_DAY_INVALID = "error.batch.correspondence.final.action.day.invalid";
    public static final String ERROR_BATCH_CORRESPONDENCE_FINAL_ACTION_TYPE_CODE_NOT_SPECIFIED = "error.batch.correspondence.final.action.type.code.not.specified";
    public static final String ERROR_BATCH_CORRESPONDENCE_FINAL_ACTION_CORRESP_TYPE_NOT_SPECIFIED = "error.batch.correspondence.final.action.corresp.type.not.specified";
    
    public static final String AWARD_BUDGET_STATUS_SUBMITTED = "awardBudgetStatusSubmitted";
    public static final String AWARD_BUDGET_STATUS_IN_PROGRESS = "awardBudgetStatusInProgress";
    public static final String AWARD_BUDGET_STATUS_REJECTED = "awardBudgetStatusRejected";
    public static final String AWARD_BUDGET_STATUS_POSTED = "awardBudgetStatusPosted";
    public static final String AWARD_BUDGET_STATUS_TO_BE_POSTED = "awardBudgetStatusToBePosted";
    public static final String AWARD_BUDGET_STATUS_DO_NOT_POST = "awardBudgetStatusDoNotPost";
    public static final String AWARD_BUDGET_STATUS_ERROR_IN_POSTING = "awardBudgetStatusErrorInPosting";
    public static final String AWARD_BUDGET_POST_ENABLED = "AWARD_BUDGET_POST_ENABLED";
    public static final String AWARD_BUDGET_TYPE_NEW = "awardBudgetTypeNew";
    public static final String AWARD_BUDGET_TYPE_REBUDGET = "awardBudgetTypeRebudget";
    
    public static final String ERROR_END_DATE_PRIOR_START_DATE = "error.end.date.prior.start.date";
    public static final String ERROR_START_DATE_ON_OR_BEFORE = "error.start.date.on.or.before";
    public static final String ERROR_START_DATE_ON_OR_AFTER = "error.start.date.on.or.after";
    public static final String ERROR_INVALID_UNITCONTACT_PERSON = "error.unitContact.invalid.person";
    public static final String ERROR_MISSING_UNITCONTACT_PERSON = "error.unitContact.missing.person";
   
    //Organization
    public static final String ERROR_ORGANIZATION_QUESTIONYNQ_ANSWER_REQUIRED = "error.organization.questionYnq.answerRequired";
    public static final String ERROR_ORGANIZATION_QUESTIONYNQ_EXPLANATION_REQUIRED = "error.organization.questionYnq.explanationRequired";
    public static final String ERROR_ORGANIZATION_QUESTIONYNQ_DATE_REQUIRED = "error.organization.questionYnq.reviewDateRequired";
    public static final String ERROR_INVALID_ROLODEX_ENTRY = "error.organization.rolodexEntry.invalid";
    
    public static final String ERROR_PENDING_PROPOSAL_LOG_ONLY = "error.pending.proposallog.only";
    public static final String ERROR_AWARD_BASIS_EXIST = "error.awardBasis.exist";
    public static final String ERROR_SCHEDULE_START_DATE_PRECEDES_END_DATE = "error.reportingSchedule.invalid.start.end.dates";
    public static final String ERROR_PERIOD_COST_LIMIT_EXCEED_OBLIGATED_TOTAL = "error.period.costlimit.exceed.obligatedtotal";
    public static final String ERROR_PROTO_CORRESPONDENCE_TEMPL_NOT_SET = "error.protocol.correspondence.template.not.set";
    public static final String PERSON_TRAINING_EXISTS = "error.persontraining.exists";
    
    public static final String ERROR_SUBMISSION_REVIEW_TYPE_EXISTS = "error.submreviewtype.exists";
    public static final String ERROR_SUBMISSION_TYPE_NOT_EXISTS = "error.submtype.notexists";
    public static final String ERROR_REVIEW_TYPE_NOT_EXISTS = "error.reviewtype.notexists";
    public static final String ERROR_SUBMISSION_TYPE_QUALIFIER_EXISTS = "error.submtypequal.exists";
    public static final String ERROR_TYPE_QUALIFIER_NOT_EXISTS = "error.typequal.notexists";
    public static final String ERROR_FOLLOWUP_USER_PROMPT_REQUIRED = "error.protocol.actions.followUp.userPromptRequired";
    public static final String ERROR_FOLLOWUP_USER_PROMPT_REQUIRED_EMPTY = "error.protocol.actions.followUp.userPromptRequiredEmpty";
    public static final String INVALID_SUBMISSION_REVIEW_TYPE = "invalid.submreviewtype";
    public static final String INVALID_SUBMISSION_TYPE_QUALIFIER = "invalid.submtypequal";
    public static final String ERROR_MANDATORY_QUESTIONNAIRE = "error.mandatory.questionnaire";
    public static final String ERROR_QUESTIONNAIRE_NOT_UPDATED = "error.questionnaire.notUpdated";
    
    public static final String ERROR_ATTACHMENT_REQUIRED = "error.attachment.required";
    public static final String PRINCIPALID_NOT_EXIST = "error.person.principalId.notExist";
    
    public static final String NSF_SPONSOR_CODE = "NSF_SPONSOR_CODE";
    
    public static final String PROJECT_START_END_DATE_CHANGED = "error.project.start.end.date.changed";
    public static final String ERROR_WATERMARK_STATUS_CODE_EXIST = "error.watermark.statuscode.exists";
    public static final String INSUFFICIENT_AMOUNT_TO_PERIOD_DIRECT_COST_LIMIT_SYNC = "insufficient.amount.to.period.direct.cost.limit.sync";
    public static final String TOTAL_DIRECT_COST_ALREADY_IN_SYNC = "total.direct.cost.already.sync";
    
    public static final String ERROR_DUPLICATE_PROPERTY = "error.duplicate.property";
    public static final String ERROR_DATA_GROUP_NOT_EXIST = "error.datagroup.not.exist";
    
  //Subaward    
    public static final String ERROR_REQUIRED_SUBRECIPIENT_ID = "error.required.subrecipient.id";
    public static final String ERROR_REQUIRED_STATUS = "error.required.statuscode";
    public static final String ERROR_REQUIRED_SUBAWARD_TYPE = "error.required.subaward.typecode";
    public static final String ERROR_REQUIRED_REQUISITIONER = "error.required.requisitioner";
    public static final String ERROR_REQUIRED_PURCHASE_ORDER_NUM = "error.required.purchase.order.num";
    public static final String ERROR_REQUIRED_EFFECTIVE_DATE = "error.required.effective.date";
    public static final String ERROR_REQUIRED_INVOICE_NUMBER = "error.required.invoice.number";
    public static final String ERROR_REQUIRED_AMOUNT_RELEASED_START_DATE = "error.required.amount.released.start.date";
    public static final String ERROR_REQUIRED_AMOUNT_RELEASED_END_DATE = "error.required.amount.released.end.date";
    public static final String ERROR_REQUIRED_AMOUNT_RELEASED_EFFECTIVE_DATE = "error.required.amount.released.effective.date";
    public static final String ERROR_REQUIRED_AMOUNT_RELEASED = "error.required.amount.released";
    public static final String ERROR_REQUIRED_SUBAWARD_CONTACT_ROLODEX_ID = "error.required.subaward.contact.rolodex.id";
    public static final String ERROR_REQUIRED_SUBAWARD_CONTACT_TYPE_CODE = "error.required.subaward.contact.contact.type.code";
    public static final String ERROR_REQUIRED_SUBAWARD_CLOSEOUT_TYPE_CODE = "error.required.subaward.closeout.closeout.type.code";
    public static final String ERROR_REQUIRED_SUBAWARD_FUNDING_SOURCE_AWARD_NUMBER= "error.required.subaward.funding.source.award.number";
    public static final String ERROR_REQUIRED_SUBAWARD_FUNDING_SOURCE_AWARD_NUMBER_DUPLICATE= "error.required.subaward.funding.source.award.number.duplicate";
    public static final String SUBAWARD_ERROR_END_DATE_GREATER_THAN_START = "subaward.error.end.date.greater.than.start";
    public static final String ERROR_AMOUNT_INFO_OBLIGATED_AMOUNT = "subaward.error.obligated.amount.greater.than.anticipated.amount";
    public static final String ERROR_AMOUNT_INFO_OBLIGATED_AMOUNT_NEGATIVE = "subaward.error.obligated.amount.less.than.zero";
    public static final String ERROR_AMOUNT_INFO_OBLIGATED_AMOUNT_ZERO = "subaward.error.obligated.amount.equal.to.zero";
    public static final String ERROR_AMOUNT_INFO_ANTICIPATED_AMOUNT_NEGATIVE = "subaward.error.anticipated.amount.less.than.zero";
    public static final String ERROR_AMOUNT_INFO_ANTICIPATED_AMOUNT_ZERO = "subaward.error.anticipated.amount.equal.to.zero";
    public static final String ERROR_SUBAWARD_CONTACT = "subaward.contact.not.added";
    public static final String ERROR_SUBAWARD_AMOUNT_RELEASED_GREATER_OBLIGATED_AMOUNT ="subaward.error.amount.released.greater.than.obligated.amount";
    
    //negotiations
    public static final String NEGOTIATION_STATUS_USED = "error.negotiations.codeAlreadyUsed";
    public static final String NEGOTIATION_CHANGE_ASSOCIATION_TYPE_MESSAGE="negotiation.message.changeAssociationType";
    public static final String ERROR_UNIT_NUMBER_REQUIRED = "error.unitnumber.required";
    public static final String NEGOTIATION_ERROR_END_DATE_GREATER_THAN_START = "negotiation.error.end.date.greater.than.start";
    public static final String NEGOTIATION_ERROR_INPROGRESS_END_DATE = "negotiation.error.inprogress.end.date";
    public static final String NEGOTIATION_ERROR_COMPLETED_END_DATE = "negotiation.error.completed.end.date";
    public static final String NEGOTIATION_ERROR_NEGOTIATOR = "negotiation.error.negotiator";
    public static final String NEGOTIATION_WARNING_ASSOCIATEDID_NOT_SET = "negotiation.warning.associatedid.not.set";
    public static final String NEGOTIATION_ACTIVITY_START_BEFORE_NEGOTIATION = "negotiation.error.activity.start.before.negotiation.start";
    public static final String NEGOTIATION_ACTIVITY_FOLLOWUP_BEFORE_TODAY = "negotiation.error.followup.date.before.today";
    public static final String NEGOTIATION_ACTIVITY_END_AFTER_NEGOTIATION = "negotiation.error.activity.end.after.negotiation.end";
    public static final String NEGOTIATION_ACTIVITY_START_BEFORE_END = "negotiation.error.activity.start.before.end";
    public static final String NEGOTIATION_CLOSE_PENDING_ACTIVITIES = "negotiation.message.closePendingActivities";
    public static final String NEGOTIATION_DUPLICATE_LINKING = "negotiation.message.duplicateNegotiationLinked";
    public static final String NEGOTIATION_MULTIPLE_PI = "negotiation.error.multiplePi";
    
    public static final String MESSAGE_FINANCIAL_ENTITY_ACTION_COMPLETE = "message.financial.entity.action.successfully.completed";
    public static final String ERROR_DEACTIVATE_FINANCIAL_ENTITY_REASON_REQUIRED = "error.deactivate.financial.entity";
    public static final String ERROR_COI_FINANCIAL_ENTITY_STATUS_REQUIRED = "error.coi.financialentity.status.required";
    public static final String ERROR_COI_PROJECT_REQUIRED = "error.coi.project.required";
    public static final String ERROR_COI_DUPLICATE_PROJECT_ID = "error.coi.duplicate.projectid";
    public static final String ERROR_COI_DISCLOSURE_STATUS_REQUIRED = "error.coi.disclosure.status.required";
    public static final String ERROR_FINANCIAL_ENTITY_STATUS_INCOMPLETE = "error.coi.financialentity.status.incomplete";
    
    
    public static final String REPORT_TRACKING_WARNING_UPDATE_FROM_DATE_CHANGE = "warning.reportTracking.updateFromDateChange";
    
    // Person Mass Change
    public static final String ERROR_PERSON_MASS_CHANGE_REPLACEE_EMPTY = "error.personMassChange.replacee.empty";
    public static final String ERROR_PERSON_MASS_CHANGE_REPLACER_EMPTY = "error.personMassChange.replacer.empty";
    public static final String ERROR_PERSON_MASS_CHANGE_REPLACEE_SELECTION = "error.personMassChange.replacee.selection";
    public static final String ERROR_PERSON_MASS_CHANGE_REPLACER_SELECTION = "error.personMassChange.replacer.selection";
    
    /**
     * private utility class ctor.
     * @throws UnsupportedOperationException if called.
     */
    private KeyConstants() {
        throw new UnsupportedOperationException("do not call me");
    }
}
