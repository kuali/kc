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
package org.kuali.kra.infrastructure;

public final class KeyConstants {
    
    public static final String ERROR_INVALID_FORMAT_WITH_FORMAT = "error.invalidFormat.withFormat";
    public static final String ERROR_REQUIRED = "error.required";
    
    public static final String ERROR_REQUIRED_FOR_APPROVED_SPECIALREVIEW = "error.required.for.approved.specialReview";
    public static final String ERROR_NOT_APPROVED_SPECIALREVIEW = "error.not.approved.specialReview";
    public static final String ERROR_REQUIRED_SELECT_APPROVAL_STATUS = "error.required.select.approval.status";
    public static final String ERROR_REQUIRED_SELECT_SPECIAL_REVIEW_CODE = "error.required.select.special.review.code";

    public static final String ERROR_REQUIRED_FOR_VALID_SPECIALREVIEW = "error.required.for.valid.specialReview";
    public static final String ERROR_REQUIRED_FOR_PROPLOCATION = "error.required.for.propLocation";
    public static final String ERROR_REQUIRED_FOR_PROPLOCATION_NAME = "error.required.for.locationName";
    public static final String ERROR_REQUIRED_FOR_PROPOSALTYPE_NOTNEW = "error.required.for.proposalType.notNew";
    public static final String ERROR_REQUIRED_PROPOSAL_SPONSOR_ID = "error.required.proposalSponsorId";
    public static final String ERROR_NIH_SPONSOR_PROJECT_TITLE_LENGTH = "error.nih.sponsor.project.title.length";
    public static final String WARNING_EMPTY_DEADLINE_DATE = "warning.empty.deadline.date";
    public static final String WARNING_PAST_DEADLINE_DATE = "warning.past.deadline.date";
    public static final String ERROR_SPECIAL_REVIEW_DATE_ORDERING = "error.special.review.date.ordering";
    public static final String ERROR_START_DATE_AFTER_END_DATE = "error.start.date.after.end.date";
    
    // Abstracts and Attachments errors
    public static final String ERROR_PROPOSAL_ATTACHMENT_NOT_COMPLETE = "error.document.narrativestatuscode.not.complete";

    // Proposal Types System Parameter Names
    public static final String PROPOSALDEVELOPMENT_PROPOSALTYPE_NEW = "proposaldevelopment.proposaltype.new";
    public static final String PROPOSALDEVELOPMENT_PROPOSALTYPE_RENEWAL = "proposaldevelopment.proposaltype.renewal";
    public static final String PROPOSALDEVELOPMENT_PROPOSALTYPE_REVISION = "proposaldevelopment.proposaltype.revision";
    public static final String PROPOSALDEVELOPMENT_PROPOSALTYPE_CONTINUATION = "proposaldevelopment.proposaltype.continuation";
    
    // Key Personnel Mojo
    public static final String ERROR_INVESTIGATOR_UNITS_UPBOUND = "error.investigatorUnits.upbound";
    public static final String ERROR_INVESTIGATOR_UPBOUND = "error.principalInvestigators.upbound";
    public static final String ERROR_INVESTIGATOR_LOWBOUND = "error.principalInvestigators.lowbound";
    public static final String ERROR_MISSING_PERSON_ROLE = "error.missingPersonRole";
    public static final String ERROR_PROPOSAL_PERSON_EXISTS = "error.proposalPersonExists";
    public static final String ERROR_TOTAL_CREDIT_SPLIT_UPBOUND = "error.totalCreditSplit.upbound";
    public static final String ERROR_CREDIT_SPLIT_LOWBOUND = "error.creditSplit.lowbound";
    public static final String ERROR_CREDIT_SPLIT_UPBOUND = "error.creditSplit.upbound";
    public static final String ERROR_DELETE_LEAD_UNIT = "error.deleteLeadUnit";
    public static final String ERROR_ADD_EXISTING_UNIT = "error.addExistingUnit";
    public static final String ERROR_YNQ_INCOMPLETE = "error.ynqIncomplete";
    public static final String ERROR_INVALID_YEAR = "error.invalid.year";
    public static final String ERROR_INVALID_UNIT = "error.invalid.unit";
    public static final String ERROR_SELECT_UNIT="error.select.unit";
    public static final String ERROR_ONE_UNIT="error.one.unit";
    public static final String ERROR_PERCENTAGE="error.percentage";
    public static final String ERROR_MINLENGTH="error.minlength";
    
    public static final String ERROR_REQUIRED_FOR_FILE_NAME="error.required.for.fileName";
    public static final String ERROR_ABSTRACT_TYPE_NOT_SELECTED = "error.abstractType.notselected";
    public static final String ERROR_ABSTRACT_TYPE_INVALID = "error.abstractType.invalid";
    public static final String ERROR_ABSTRACT_TYPE_DUPLICATE = "error.abstractType.duplicate";
    public static final String QUESTION_DELETE_ABSTRACT_CONFIRMATION = "document.question.deleteAbstract.text";
    public static final String QUESTION_DELETE_ATTACHMENT_CONFIRMATION = "document.question.deleteAttachment.text";    
    public static final String ERROR_NARRATIVE_TYPE_DUPLICATE = "error.proposalAttachment.narrativeType.allowMulitple";
    public static final String ERROR_ATTACHMENT_TYPE_NOT_SELECTED = "error.proposalAttachment.narrativeType.notSelected";
    public static final String ERROR_ATTACHMENT_NOT_AUTHORIZED = "error.proposalAttachment.notAuthorized";
    public static final String ERROR_ATTACHMENT_STATUS_NOT_SELECTED = "error.proposalAttachment.narrativeStatus.notSelected";
    public static final String ERROR_NARRATIVE_TYPE_DESCRITPION_REQUIRED = "error.proposalAttachment.description.required";
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
    public static final String DATE_FORMAT = "MM/dd/yyyy";
    public static final String INVALID_DATE_FORMAT = "error.invalidDate";
    public static final String INVALID_DATE_REQUIRED_FOR = "error.invalid.date.required.for";
    public static final String INVALID_EXPLANATION_REQUIRED_FOR = "error.invalid.explanation.required.for";
        
    // Budget Versions errors
    public static final String ERROR_BUDGET_NAME_MISSING = "error.budgetVersion.documentDescription.required";
    public static final String ERROR_NO_FINAL_BUDGET = "error.final.budget.required";
    public static final String CLEAR_AUDIT_ERRORS_BEFORE_CHANGE_STATUS_TO_COMPLETE = "clear.audit.error.before.change.status.to.complete";
    public static final String ERROR_MULTIPLE_FINAL_BUDGETS = "error.final.multiple";
    public static final String AUDIT_ERROR_NO_BUDGETVERSION_COMPLETE_AND_FINAL = "error.no.budgetVersion.completeAndFinal";
    public static final String ERROR_BUDGET_STATUS_COMPLETE_WHEN_NOT_MODULER = "error.budgetVersion.budgetStatusCompleteWhenNotModuler";

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
    public static final String PESSIMISTIC_LOCKING_EXPIRATION_AGE = "pessimisticLocking.expirationAge";
    
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
    // Grants.gov System Parameters
    public static final String S2S_REVISIONTYPE_OTHER = "s2s.revisiontype.other";
    public static final String S2S_SUBMISSIONTYPE_CHANGEDCORRECTED = "s2s.submissiontype.changedCorrected";
    public static final String S2S_SUBMISSIONTYPE_APPLICATION = "s2s.submissiontype.application";
    
    public static final String PESSIMISTIC_LOCK_MESSAGE = "error.document.pessimisticLockMessage";
    public static final String SESSION_TIMEOUT_MESSAGE = "error.session.timeout";
    public static final String SESSION_EXPIRED_IND = "sessionExpired";
    public static final String ERROR_INACTIVE_CUSTOM_ATT_DOC = "error.inactive.customAttributeDocument";
    public static final String ERROR_WORKFLOW_SUBMISSION = "error.workflow.submission";
    
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
    public static final String WARNING_PERIOD_COST_LIMIT_EXCEEDED= "warning.periodCostLimit.exceeded";
    public static final String WARNING_UNRECOVERED_FA_NEGATIVE= "warning.unrecoveredFA.negative";

    // special review enhancement
    public static final String ERROR_EXEMPT_NUMBER_SELECTED = "error.exempt.number.selected";
    public static final String ERROR_EXPIRATION_DATE_PAST = "error.expiration.date.past";
    public static final String AUDIT_ERROR_BUDGETPERIOD_START_BEFORE_PROJECT_START_DATE = "error.periodStartDate.before.projectStartDate";
    public static final String AUDIT_ERROR_BUDGETPERIOD_END_AFTER_PROJECT_END_DATE = "error.periodEndDate.after.projectEndDate";
    public static final String AUDIT_WARNING_BUDGETPERIOD_START_AFTER_PROJECT_START_DATE = "warning.budgetStartDate.after.projectStartDate";
    public static final String AUDIT_WARNING_BUDGETPERIOD_END_BEFORE_PROJECT_END_DATE = "warning.budgetEndDate.before.projectEndDate";

    public static final String AUDIT_ERROR_BUDGET_DISTRIBUTION_SOURCE_MISSING = "error.budget.distribution.sourceMissing";
    public static final String AUDIT_ERROR_BUDGET_DISTRIBUTION_FISCALYEAR_MISSING = "error.budget.distribution.fiscalYearMissing";
    public static final String AUDIT_ERROR_BUDGET_DISTRIBUTION_UNALLOCATED_NOT_ZERO = "error.budget.distribution.unallocatedMustBeZero";
    public static final String AUDIT_WARNING_BUDGET_DISTRIBUTION_FISCALYEAR_INCONSISTENT = "warning.budget.distribution.fiscalYearInconsistent";
    public static final String AUDIT_ERROR_BUDGET_DISTRIBUTION_DUPLICATE_UNRECOVERED_FA = "error.budget.distribution.duplicate.unrecoveredFA";
    
    // Change Password
    public static final String ERROR_EMPTY_PASSWORD = "error.password.empty";
    public static final String ERROR_INVALID_PASSWORD = "error.password.invalid";
    public static final String ERROR_PASSWORD_MISMATCH = "error.password.mismatch";
    
    // Copy proposal
    public static final String ERROR_LEAD_UNIT_REQUIRED = "error.leadUnit.required";
    
    public static final String ERROR_RATE_TYPE_NOT_EXIST = "error.rateType.not.exist";
    public static final String ERROR_RATE_TYPE_NOT_VALID_FOR_TYPE = "error.rateType.not.valid.for.type";
    public static final String ERROR_RATE_CLASS_NOT_VALID_FOR_TYPE = "error.rateClass.not.valid.for.type";
    
    public static final String ERROR_EFFECTIVE_DATE_OUT_OF_RANGE = "error.effective.date.out.of.range";
    public static final String ERROR_IMCOMPLETE_PERSON_ENTRIES = "error.incomplete.person.entries";

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
    
    /**
     * private utility class ctor.
     * @throws UnsupportedOperationException if called.
     */
    private KeyConstants() {
        throw new UnsupportedOperationException("do not call me");
    }
}
