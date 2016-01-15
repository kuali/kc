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
    
    public static final String ERROR_NIH_SPONSOR_PROJECT_TITLE_LENGTH = "error.nih.sponsor.project.title.length";
    public static final String WARNING_EMPTY_DEADLINE_DATE = "warning.empty.deadline.date";
    public static final String ERROR_EMPTY_PRIME_SPONSOR_ID = "error.empty.primesponsorid";
    public static final String WARNING_PAST_DEADLINE_DATE = "warning.past.deadline.date";
    public static final String WARNING_ACTIVITY_TYPE_CHANGED = "warning.activityType.changed";
    public static final String ERROR_START_DATE_AFTER_END_DATE = "error.start.date.after.end.date";
    public static final String ERROR_REQUIRED_GG_TRACKING_ID = "error.ggtrackingid.required";
    public static final String ERROR_REQUIRED_SPONSOR_DIV_CODE = "error.required.divisionCode";
    public static final String ERROR_REQUIRED_SPONSOR_PROGRAM_CODE = "error.required.programCode";


    public static final String QUESTION_DELETE_CONFIRMATION = "document.question.delete.text";
    public static final String INVALID_DEADLINE_TIME = "error.invalid.deadLineTime";
    
    // Notification
    public static final String ERROR_NOTIFICATION_MODULE_CODE_ACTION_CODE_COMBINATION_EXISTS = "error.notification.moduleCode.actionCode.combination.exists";
    public static final String ERROR_NOTIFICATION_ROLE_NAME_EXISTS = "error.notification.roleNamespace.roleName.exists";
    public static final String ERROR_NOTIFICATION_EMPTY_NOTIFICATION_RECIPIENT = "error.notification.empty.notifcation.recipient";
    public static final String ERROR_NOTIFICATION_DUPLICATE_NOTIFICATION_RECIPIENT = "error.notification.duplicate.notification.recipient";
    public static final String ERROR_NOTIFICATION_RECIPIENTS_REQUIRED = "error.notification.recipients.required";
    public static final String LOCKED_DOCUMENT_MESSAGE = "locked.document.message";
    public static final String INFO_NOTIFICATIONS_SENT = "info.notifications.sent";
    
    // Special Review
    public static final String ERROR_SPECIAL_REVIEW_PROTOCOL_NUMBER_INVALID = "error.special.review.protocol.number.invalid";
    public static final String ERROR_SPECIAL_REVIEW_PROTOCOL_NUMBER_DUPLICATE = "error.special.review.protocol.number.duplicate";
    public static final String ERROR_SPECIAL_REVIEW_DATE_SAME_OR_LATER = "error.special.review.date.same.or.later";
    public static final String ERROR_SPECIAL_REVIEW_REQUIRED_FOR_VALID = "error.special.review.required.for.valid";
    public static final String ERROR_SPECIAL_REVIEW_CANNOT_SELECT_EXEMPTION_FOR_VALID = "error.special.review.cannot.select.exemption.for.valid";
    public static final String QUESTION_SPECIAL_REVIEW_DELETE_CONFIRMATION = "question.special.review.delete.confirmation";
    public static final String ERROR_SPECIAL_REVIEW_PROTOCOL_LOCKED = "error.special.review.protocol.locked";
    
    // Abstracts and Attachments errors
    public static final String ERROR_PROPOSAL_ATTACHMENT_NOT_COMPLETE = "error.document.narrativestatuscode.not.complete";
    public static final String ERROR_INTERNAL_ATTACHMENT_NOT_COMPLETE = "error.document.internalattachment.statuscode.not.complete";
    public static final String ERROR_PROPOSAL_ATTACHMENT_NOT_FOUND = "error.document.narrative.not.present";
    public static final String ERROR_PROPOSAL_MENTORINGPLAN_ATTACHMENT_NOT_FOUND = "error.document.mentoringplan.narrative.not.present";

    // Proposal Types System Parameter Names
    public static final String PROPOSALDEVELOPMENT_PROPOSALTYPE_NEW = "proposaldevelopment.proposaltype.new";
    public static final String PROPOSALDEVELOPMENT_PROPOSALTYPE_RENEWAL = "proposaldevelopment.proposaltype.renewal";
    public static final String PROPOSALDEVELOPMENT_PROPOSALTYPE_REVISION = "proposaldevelopment.proposaltype.revision";
    public static final String PROPOSALDEVELOPMENT_PROPOSALTYPE_CONTINUATION = "proposaldevelopment.proposaltype.continuation";
    public static final String PROPOSALDEVELOPMENT_PROPOSALTYPE_RESUBMISSION = "proposaldevelopment.proposaltype.resubmission";
    
    // Proposal Sites errors
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

    //Proposal Development Delivery Info
    public static final String DELIVERY_INFO_NOT_NEEDED = "message.proposalDevelopment.delivery.info.not.needed";
    
    // Key Personnel Mojo
    public static final String ERROR_INVESTIGATOR_UNITS_UPBOUND = "error.investigatorUnits.upbound";
    public static final String ERROR_INVESTIGATOR_UPBOUND = "error.principalInvestigators.upbound";
    public static final String ERROR_INVESTIGATOR_LOWBOUND = "error.principalInvestigators.lowbound";
    public static final String ERROR_MISSING_PERSON_ROLE = "error.missingPersonRole";
    public static final String ERROR_MISSING_CITIZENSHIP_TYPE = "error.missingcitizenship";
    public static final String ERROR_PROPOSAL_PERSON_EXISTS_WITH_ROLE = "error.proposalPersonExistsWithRole";
    public static final String ERROR_TOTAL_CREDIT_SPLIT_UPBOUND = "error.totalCreditSplit.upbound";
    public static final String ERROR_CREDIT_SPLIT_UPBOUND = "error.creditSplit.upbound";
    public static final String ERROR_DELETE_LEAD_UNIT = "error.deleteLeadUnit";
    public static final String ERROR_ADD_EXISTING_UNIT = "error.addExistingUnit";
    public static final String ERROR_PROPOSAL_PERSON_CERTIFICATION_INCOMPLETE = "error.proposalperson.certfication.incomplete";
    public static final String ERROR_INVALID_YEAR = "error.invalid.year";
    public static final String ERROR_INVALID_UNIT = "error.invalid.unit";
    public static final String ERROR_SELECT_UNIT="error.select.unit";
    public static final String ERROR_ONE_UNIT="error.one.unit";
    public static final String ERROR_PERCENTAGE="error.percentage";
    
    public static final String ERROR_ERA_COMMON_USER_NAME="error.investigators.eraCommonUserName";
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

    public static final String ERROR_PERSON_EDITABLE_FIELD_EXISTS = "error.person.editable.field.exists";
    public static final String ERROR_INVESTIGATOR_CREDIT_TYPE_EXISTS = "error.investigator.credit.type.exists";

    public static final String INFO_PERSONNEL_INVALID_ROLE = "info.personnel.invalid.role";
    public static final String APPROVAL_CYCLE_COMPLETE = "info.proposal.approval.final";

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
    public static final String AUDIT_ERROR_NO_BUDGETVERSION_FINAL = "error.no.budgetVersion.noFinalBudget";
    public static final String ERROR_BUDGET_STATUS_COMPLETE_WHEN_NOT_MODULER = "error.budgetVersion.budgetStatusCompleteWhenNotModuler";
    public static final String ERROR_BUDGET_START_DATE_MISSING = "error.budgetVersion.startDate.required";
    public static final String ERROR_BUDGET_END_DATE_MISSING = "error.budgetVersion.endDate.required";
    public static final String ERROR_BUDGET_OBLIGATED_AMOUNT_INVALID = "error.award.invalidObligatedAmount";
    public static final String ERROR_AWARD_BUDGET_START_DATE_MISSING = "error.award.budgetVersion.startDate.required";
    public static final String ERROR_AWARD_BUDGET_END_DATE_MISSING = "error.award.budgetVersion.endDate.required";
    public static final String WARNING_AWARD_BUDGET_LINE_ITEM_DATES_UPDATED = "warn.award.budget.line.item.dates.updated";
    public static final String WARNING_AWARD_BUDGET_PERSON_EFFECTIVE_DATE = "warn.award.budget.effective.rate.start.date";
    public static final String QUESTION_TOTALCOSTLIMIT_CHANGED = "document.question.totalCostLimit.changed";
    public static final String ERROR_BUDGET_REJECT_NO_REASON = "error.budget.reject.noReason";
    public static final String ERROR_COMPLETE_BUDGET_LOCK = "error.completeBudget.locked";
    public static final String ERROR_BUDGET_DATES_NOT_MATCH_PROPOSAL_DATES = "error.budget.dates.not.match.proposal.dates";
    //Budget Version warnings
    public static final String WARNING_BUDGET_VERSION_MODULAR_INVALID_TDC = "warning.budgetVersion.Modular.InvalidTotalDirectCost";

    // Budget Personnel constants
    public static final String ERROR_DUPLICATE_BUDGET_PERSON = "error.budgetPerson.duplicate";
    public static final String QUESTION_DELETE_PERSON = "document.question.deletePerson.text";
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
    public static final String ERROR_UNKNOWN_USERNAME = "error.unknown.username";
    public static final String ERROR_DUPLICATE_PROPOSAL_USER = "error.duplicate.proposalUser";
    public static final String ERROR_LAST_AGGREGATOR = "error.last.aggregator";
    public static final String ERROR_EMPTY_USERNAME = "error.empty.username";
    public static final String ERROR_PROP_DEV_PERM_INITIATOR = "error.propdev.perm.initiator";
    public static final String ERROR_UNFINISHED_PERMISSIONS = "error.unsaved.permissions";

    public static final String ERROR_REQUIRE_ONE_NARRATIVE_MODIFY="error.narrative.one.modify";
    public static final String ERROR_REQUIRE_ONE_NARRATIVE_MODIFY_WITH_ARG="error.narrative.one.modify.with.arg";
    public static final String ERROR_NARRATIVE_USER_RIGHT_NO_PERMISSION="error.narrative.no.permission";
    public static final String ERROR_PERMISSION_VIEWER_ONLY_KEY = "error.viewer.only";
    
    // Authorization
    public static final String AUTHORIZATION_VIOLATION = "error.authorization.violation";
    
    // Pessimistic Locking Cron Job
    public static final String PESSIMISTIC_LOCKING_CRON_EXPRESSION = "pessimisticLocking.cronExpression";
    
    // Grants.gov
    public static final String ERROR_IF_PROPOSALTYPE_IS_REVISION = "error.s2sopportunity.revisiontype";
    public static final String ERROR_IF_CFDANUMBER_AND_OPPORTUNITY_ID_IS_NULL = "error.s2sopportunity.cfdaNumber_opportunityId_null";
    public static final String ERROR_S2S_PROVIDER_INVALID = "error.s2sopportunity.invalidProvider";
    public static final String ERROR_IF_REVISIONTYPE_IS_OTHER = "error.s2sopportunity.revisionTypeOther";
    public static final String ERROR_REQUIRED_REVISIONTYPE = "error.required.s2sopportunity.revisionType";
    public static final String ERROR_IF_CFDANUMBER_IS_INVALID = "error.s2sopportunity.cfdaNumberInvalid";
    public static final String ERROR_IF_OPPORTUNITY_ID_IS_INVALID = "error.s2sopportunity.opportunityIdInvalid";
    public static final String ERROR_OPPORTUNITY_ID_DIFFER = "error.opportunityId.differ";
    public static final String ERROR_OPPORTUNITY_TITLE_DELETED = "error.opportunityTitle.deleted";
    public static final String ERROR_CFDA_NUMBER_DIFFER = "error.cfdaNumber.differ";
    public static final String ERROR_IF_REVISIONTYPE_IS_NOT_OTHER_SPECIFY_NOT_BLANK = "error.s2sopportunity.revisionTypeNotOtherSpecifyNotBlank";
    public static final String VALIDATTION_ERRORS_BEFORE_GRANTS_GOV_SUBMISSION = "validation.errors.before.grantsGov.submission";
    public static final String QUESTION_SUMBMIT_OPPORTUNITY_WITH_WARNINGS_CONFIRMATION = "question.submitOpportunityWithWarnings.text";
    public static final String ERROR_ON_GRANTS_GOV_SUBMISSION = "error.on.grantsGov.submission";
    public static final String ERROR_RESUBMISSION_INVALID_PROPOSALTYPE_SUBMISSIONTYPE = "error.resubmission.invalidProposalTypeSubmissionType";
    public static final String ERROR_NOT_SELECTED_SUBMISSION_TYPE = "error.submissionType.notSelected";
    public static final String ERROR_IF_GRANTS_GOV_IS_DISABLED = "error.grantsgov.disabled";
    public static final String ERROR_GRANTSGOV_FORM_PARSING = "error.grantsgov.form.parsing";
    public static final String ERROR_GRANTSGOV_FORM_PARSING_SAXEXCEPTION = "error.grantsgov.form.xmlprocess";
    public static final String ERROR_GRANTSGOV_FORM_SCHEMA_NOT_FOUND = "error.grantsgov.form.schema.server.down";
    public static final String ERROR_GRANTSGOV_NO_FORM_ELEMENT = "error.grantsgov.no.form.element";
    
    public static final String ERROR_PRINTING_UNKNOWN = "error.printing.unknown";
    public static final String ERROR_IF_COMPETITION_ID_IS_INVALID="error.s2sopportunity.competitionIdInvalid";
   // Grants.gov System Parameters
    public static final String S2S_REVISIONTYPE_OTHER = "s2s.revisiontype.other";
    public static final String S2S_SUBMISSIONTYPE_CHANGEDCORRECTED = "s2s.submissiontype.changedCorrected";
    public static final String S2S_SUBMISSIONTYPE_APPLICATION = "s2s.submissiontype.application";

    public static final String SESSION_EXPIRED_IND = "sessionExpired";
    public static final String ERROR_INVALID_CUSTOM_ATT_ID = "error.invalid.customAttributeid";
    public static final String ERROR_WORKFLOW_SUBMISSION = "error.workflow.submission";

    //Budget Expense
    public static final String ERROR_BUDGET_PERIOD_NOT_SELECTED = "error.viewBudgetPeriod.notSelected";
    public static final String ERROR_LINEITEM_STARTDATE_BEFORE_PERIOD_STARTDATE = "error.lineItemStartDate.before.periodStartDate";
    public static final String ERROR_LINEITEM_ENDDATE_AFTER_PERIOD_ENDDATE = "error.lineItemEndDate.after.periodEndDate";
    public static final String ERROR_PERSONNELBUDGETLINEITEM_STARTDATE_BEFORE_LINEITEM_STARTDATE = "error.personnelBudgetLinelineItemStartDate.before.lineItemStartDate";
    public static final String ERROR_PERSONNELBUDGETLINEITEM_ENDDATE_AFTER_LINEITEM_ENDDATE = "error.personnelBudgetLineItemEndDate.after.lineItemEndDate";
    public static final String ERROR_PERCENT_EFFORT_LESS_THAN_PERCENT_CHARGED = "error.percentEffort.lessThan.percentCharged";
    public static final String ERROR_COST_ELEMENT_NOT_SELECTED = "error.costElement.notSelected";
    public static final String ERROR_BUDGET_PERSONNEL_NOT_SELECTED = "error.budgetPersonnel.notSelected";
    public static final String WARNING_TOTAL_COST_LIMIT_EXCEEDED= "warning.budgetCostLimit.exceeded";
    public static final String WARNING_PERIOD_COST_LIMIT_EXCEEDED= "warning.periodCostLimit.exceeded";
    public static final String WARNING_UNRECOVERED_FA_NEGATIVE= "warning.unrecoveredFA.negative";
    
    public static final String WARNING_TOTAL_DIRECT_COST_LIMIT_EXCEEDED= "warning.budgetDirectCostLimit.exceeded";
    public static final String WARNING_PERIOD_DIRECT_COST_LIMIT_EXCEEDED= "warning.periodDirectCostLimit.exceeded";

    public static final String AUDIT_ERROR_BUDGETPERIOD_START_BEFORE_PROJECT_START_DATE = "error.periodStartDate.before.projectStartDate";
    public static final String AUDIT_ERROR_BUDGETPERIOD_END_AFTER_PROJECT_END_DATE = "error.periodEndDate.after.projectEndDate";
    public static final String AUDIT_WARNING_BUDGETPERIOD_START_AFTER_PROJECT_START_DATE = "warning.budgetStartDate.after.projectStartDate";
    public static final String AUDIT_WARNING_BUDGETPERIOD_END_BEFORE_PROJECT_END_DATE = "warning.budgetEndDate.before.projectEndDate";

    public static final String AUDIT_ERROR_BUDGET_DISTRIBUTION_SOURCE_MISSING = "error.budget.distribution.sourceMissing";
    public static final String AUDIT_ERROR_BUDGET_DISTRIBUTION_FISCALYEAR_MISSING = "error.budget.distribution.fiscalYearMissing";
    public static final String AUDIT_ERROR_BUDGET_DISTRIBUTION_ONCAMPUS_FLAG_MISSING = "error.budget.distribution.onCampusFlagMissing";
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
    
    // parameter name to allow/disallow PD notes deletion
    public static final String ALLOW_PROPOSAL_DEVELOPMENT_NOTES_DELETION="allowProposalDevelopmentNotesDeletion";
    
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
    
    public static final String BUDGET_DATA_OVERRIDE_SAME_VALUE = "error.budgetData.override.samevalue";
    
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
    public static final String ERROR_PERSON_INVALID_JOBCODE_VALUE = "error.person.invalid.jobcodeValue";
    public static final String ERROR_DUPLICATE_PERSON = "error.duplicate.personnel";
    public static final String ERROR_JOBCODE_COST_ELEMENT_COMBO_INVALID = "error.jobCode.costElement.invalid";
    public static final String ERROR_SAVE_JOBCODE_COST_ELEMENT_COMBO_INVALID = "error.save.jobCode.costElement.invalid";

    // Award Approved Equipment
    public static final String ERROR_AWARD_APPROVED_EQUIPMENT_AMOUNT_REQUIRED = "error.required";
    public static final String ERROR_AWARD_APPROVED_EQUIPMENT_ITEM_REQUIRED = ERROR_AWARD_APPROVED_EQUIPMENT_AMOUNT_REQUIRED;
    public static final String ERROR_AWARD_APPROVED_EQUIPMENT_AMOUNT_VALID = "error.awardApprovedEquipment.amount.invalid";
    public static final String ERROR_AWARD_APPROVED_EQUIPMENT_ITEM_NOT_UNIQUE = "error.awardApprovedEquipment.notunique";
    
    //Award Payment Schedule    
    public static final String ERROR_AWARD_PAYMENT_SCHEDULE_ITEM_NOT_UNIQUE = "error.awardPaymentSchedule.notunique";
    //Award Payment Invoices
    public static final String ERROR_AWARD_INVALID_BASIS_OF_PAYMENT_FOR_AWARD_TYPE="error.award.paymentsAndInvoices.invalidBasisOfPaymentForAwardType";
    public static final String ERROR_AWARD_INVALID_BASIS_AND_METHOD_OF_PAYMENT = "error.award.paymentsAndInvoices.invalidBasisAndMethodOfPayment";
    
    
    //Time & Money - Transactions
    public static final String ERROR_TNM_PENDING_TRANSACTION_ITEM_NOT_UNIQUE = "error.awardPendingTransaction.notunique";
    public static final String ERROR_TNM_PENDING_TRANSACTION_SOURCE_AND_DESTINATION_AWARDS_ARE_SAME = "error.awardPendingTransaction.sourceAndDestinationAreSame";
    public static final String ERROR_TRANSACTION_AMOUNTS_NEGATIVE = "error.transaction.amounts.negative";
    
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
    public static final String ERROR_AWARD_EFFECTIVE_DATE_TOTAL = "error.award.effective.date.total";
    public static final String ERROR_OBLIGATION_EXPIRATION_DATE = "error.obligation.expiration.date";
    public static final String ERROR_OBLIGATION_EXPIRATION_DATE_TOTAL = "error.obligation.expiration.date.total";
    public static final String ERROR_INVALID_CFDA = "error.award.cfda.invalid";
    public static final String ERROR_AWARD_EFFECTIVE_DATE_TIME_AND_MONEY = "error.award.effective.date.timeAndMoney";
    public static final String ERROR_AWARD_SPONSOR_ID = "error.award.sponsorCode";
    public static final String ERROR_AWARD_PRIME_SPONSOR_ID = "error.award.primeSponsorCode";


    public static final String ERROR_ANTICIPATED_AMOUNT_FROM_TRANSACTIONS = "error.anticipated.amount.from.transactions";
    public static final String ERROR_AWARD_OBLIGATED_NEGATIVE_FROM_TRANSACTIONS = "error.award.obligated.negative.from.transactions";
    public static final String ERROR_AWARD_ANTICIPATED_NEGATIVE_FROM_TRANSACTIONS = "error.award.anticipated.negative.from.transactions";
    public static final String ERROR_AWARD_OBLIGATED_DATES_FROM_TRANSACTIONS = "error.award.obligated.dates.from.transactions";
    
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
    public static final String ERROR_ORGANIZATION_ID_IS_NULL = "error.awardApprovedSubaward.organization.is.null";
    public static final String ERROR_ORGANIZATION_ID_IS_INVALID = "error.awardApprovedSubaward.invalid.organization.name";
    //Award Cost Share Confirmation Questions
    public static final String QUESTION_DELETE_COST_SHARE = "document.question.deleteCostShare.text";
    
    //Award Cost Share Confirmation Questions
    public static final String QUESTION_SYNC_UNIT_CONTACTS = "document.question.syncUnitContacts.text";
    
    public static final String QUESTION_SYNC_PANEL = "document.question.syncPanel.text";
    public static final String QUESTION_SYNC_FULL = "document.question.syncAll.text";
    public static final String QUESTION_SYNC_PANEL_TO_EMPTY = "document.question.syncPanelToEmpty.text";
     
    public static final String QUESTION_VOID_ATTACHMENT = "document.question.voidAttachment.text";
    //Award Attachments
    public static final String QUESTION_DELETE_ATTACHMENT = "document.question.deleteAttachment.text";
    public static final String AWARD_ATTACHMENT_TYPE_CODE_REQUIRED = "error.awardNotesAndAttachments.attachment.typeRequired";
    public static final String AWARD_ATTACHMENT_FILE_REQUIRED = "error.awardNotesAndAttachments.attachment.fileRequired";
    
//InstitutionalProposal Attachments
    
    public static final String INSTITUTIONAL_PROPOSAL_ATTACHMENT_TYPE_CODE_REQUIRED = "error.institutionalProposalAttachments.attachment.typeRequired";
    public static final String INSTITUTIONAL_PROPOSAL_ATTACHMENT_FILE_REQUIRED = "error.institutionalProposalAttachments.attachment.fileRequired";
    
    //Award Budget
    public static final String ERROR_AWARD_OR_MONEY_DOC_NOT_FINAL = "error.budget.award.not.final";
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
    public static final String ERROR_EMPTY_REPORT_TERMS = "error.empty.report.terms";
    public static final String ERROR_EMPTY_TERMS = "error.empty.terms";
    public static final String ERROR_AWARD_REPORT_TERM_ITEM_NOT_UNIQUE = "error.awardReportTermItem.notunique";
    public static final String ERROR_AWARD_REPORT_TERM_ITEM_OSP_REQUIRED = "error.awardReportTermItem.ospRequired";
    public static final String ERROR_AWARD_REPORT_TERM_ITEM_FREQUENCY_REQUIRED = "error.awardReportTermItem.frequencyRequired";
    public static final String ERROR_AWARD_REPORT_TERM_ITEM_NO_PI = "error.awardReportTermItem.noPI";
    public static final String ERROR_AWARD_REPORT_TERM_ITEM_NO_REPORT_TRACKING= "error.awardReportTermItem.noReportTracking";
    public static final String ERROR_AWARD_REPORT_TERM_RECIPIENT_ITEM_NOT_UNIQUE = "error.awardReportTermRecipientItem.notunique";
    public static final String ERROR_BOTH_SPONSOR_AND_ROLODEX_ARE_SELECTED = "error.both.sponsor.and.rolodex.are.selected";
    public static final String ERROR_REQUIRED_ORGANIZATION_FIELD = "error.organizasion.required";
    public static final String ERROR_AWARD_NOT_FOUND = "error.award.notFound";
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
    public static final String AUTO_GENERATE_TIME_MONEY_FUNDS_DIST_PERIODS = "AUTO_GENERATE_TIME_AND_MONEY_FUNDS_DIST_PERIODS";
    
    //Award Validation Error Messages
    public static final String INDIRECT_COST_RATE_NOT_IN_PAIR = "indirectCostRate.not.in.pair";
    public static final String INVALID_REPORT_CODE_FOR_REPORT_CLASS = "error.invalid.type.for.reportClass";
    public static final String INVALID_FREQUENCY_FOR_REPORT_CLASS_AND_TYPE = "error.invalid.frequency.for.reportClass.and.type";
    public static final String INVALID_FREQUENCY_BASE_FOR_FREQUENCY = "error.invalid.frequencyBase.for.frequency";
    public static final String ERROR_REQUIRED_ORGANIZATION = "error.required.organization";
    public static final String ERROR_PAYMENT_AND_INVOICE_COMMENT_NOT_ALLOWED = "error.invalid.award.payment.invoice.comment.comment.not.allowed";
    public static final String ERROR_PAYMENT_AND_INVOICE_COMMENT_REQUIRED = "error.invalid.award.payment.invoice.comment.required";
    
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
    public static final String AWARD_CHART_OF_ACCOUNTS_CODE_NOT_VALID = "error.award.chart.of.accounts.code.invalid";
    public static final String BOTH_ACCOUNT_AND_CHART_REQUIRED = "error.award.account.number.and.chart.required";
    public static final String CANNOT_CONNECT_TO_SERVICE = "error.award.createAccount.cannotConnect";
    public static final String DOCUMENT_NUMBER_NULL = "error.award.createAccount.nullDocumentNumber";
    public static final String DOCUMENT_SAVED_WITH_ERRORS = "error.award.createAccount.saved.with.errors";
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
    public static final String BUDGET_DOCUMENT_SAVED_WITH_ERRORS = "error.budget.adjustment.saved.with.errors";

    
    //Award template validation errors
    public static final String ERROR_NO_TEMPLATE_CODE = "error.award.templateCode.notFound";
    public static final String ERROR_NO_SPONSOR_TEMPLATE_FOUND = "error.award.sponsor.template.notFound";
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
    
    //Award sub-contracting errors
    public static final String SUB_PLAN_AWARD_NOT_FOUND = "error.award.subcontracting.goalsAndExpenditures.awardNotFound";
    public static final String EXPENDITURES_RANGE_START_DATE_AFTER_END_DATE = "error.award.subcontracting.reporting.startDateAfterEndDate";
    
    //Award sub-contracting messages
    public static final String AWARD_GOALS_RELOADED = "message.award.subcontracting.goalsAndExpenditures.goalsReloaded";
    public static final String AWARD_GOALS_CLEARED = "message.award.subcontracting.goalsAndExpenditures.goalsCleared";
    public static final String AWARD_GOALS_SAVED = "message.award.subcontracting.goalsAndExpenditures.goalsSaved";
    
    public static final String EXPENDITURE_DATA_REGENERATED_ALL_DATES = "message.award.subcontracting.reporting.allDateDataRegenerated";
    public static final String EXPENDITURE_DATA_REGENERATED_IN_RANGE = "message.award.subcontracting.reporting.dataRegeneratedInDateRange";
    
    //COI Required Fields validation messages
    public static final String ERROR_COI_ATTACHMENT_MISSING_FILE = "error.coi.attachment.missing.file";
    public static final String ERROR_COI_INVALID_DISCLOSURE_STATUS_CODE = "error.coi.invalid.disclosure.status.code";
    public static final String ERROR_COI_DUPLICATE_DISPOSITION_CODE = "error.coi.duplicate.disposition.code";
    public static final String ERROR_COI_DUPLICATE_DISPOSITION_DESCRIPTION = "error.coi.duplicate.disposition.description";
    public static final String MESSAGE_COI_CERT_SUBMITTED = "message.coi.cert.submitted";
    public static final String ERROR_COI_NO_PERMISSION_APPROVE = "error.coi.nopermission.approve";
    public static final String MESSAGE_COI_DISCLOSURE_QUICK_APPROVED = "message.coi.quick.approved";
    public static final String ERROR_COI_VALIDATION="error.coi.validation";
    
    // IRB Protocol Research Area Validation messages
    public static final String ERROR_PROTOCOL_RESEARCH_AREA_REQUIRED = "error.protocol.researchArea.required";
    public static final String ERROR_PROTOCOL_RESEARCH_AREA_NOT_ACTIVE = "error.protocol.researchArea.notactive";
    // IRB Protocol Funding Source Validation messages
    public static final String ERROR_PROTOCOL_FUNDING_SOURCE_REQUIRED = "error.protocol.fundingSource.required";
    public static final String QUESTION_PROTOCOL_FUNDING_SOURCE_DELETE_CONFIRMATION = "question.protocol.fundingSource.delete.confirmation";
    public static final String ERROR_PROTOCOL_FUNDING_SOURCE_AWARD_LOCKED = "error.protocol.fundingSource.award.locked";
    public static final String ERROR_PROTOCOL_FUNDING_SOURCE_INSTITUTIONAL_PROPOSAL_LOCKED = "error.protocol.fundingSource.institutional.proposal.locked";
    
    //IRB Protocol Search Screen Error Messages
    public static final String ERROR_PROTOCOL_SEARCH_INVALID_DATE = "error.search.invalid.date";
    
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
    public static final String ERROR_PROTOCOL_LEAD_UNIT_NUM_INVALID = "error.protocolRequiredFields.leadUnitNum.invalid";

    // IRB Protocol Actions Required Fields Validation Messages
    public static final String ERROR_PROTOCOL_SUBMISSION_TYPE_NOT_SELECTED = "error.protocol.submissionTypeCode.notFound";
    public static final String ERROR_PROTOCOL_SUBMISSION_EXPEDITED_CHECKBOX_REQ="error.protocol.submission.expeditedCheckbox";
    public static final String ERROR_PROTOCOL_SUBMISSION_EXEMPT_CHECKBOX_REQ="error.protocol.submission.exemptCheckbox";
    public static final String ERROR_PROTOCOL_REVIEW_TYPE_NOT_SELECTED = "error.protocol.reviewTypeCode.notFound";
    public static final String ERROR_PROTOCOL_REVIEW_TYPE_INVALID = "error.protocol.reviewTypeCode.invalid";
    public static final String ERROR_PROTOCOL_REVIEWER_CANNOT_REMOVE_REVIEW_FINAL = "warning.protocol.reviewer.removeReview.cannotRemoveFinalReview";
    public static final String ERROR_PROTOCOL_REVIEWER_CANNOT_REMOVE_REVIEW_EXISTING_COMMENTS = "warning.protocol.reviewer.removeReview.cannotRemoveReviewWithComments";
    public static final String ERROR_PROTOCOL_REVIEWER_CANNOT_REMOVE_FINAL_REVIEW = "warning.protocol.reviewer.removeReview.cannotRemoveFinalReview";
    public static final String MESSAGE_REMOVE_REVIEWERS_WITH_COMMENTS="message.protocol.reviewer.removeReview.removeReviewWithComments";
    public static final String ERROR_PROTOCOL_REVIEWER_TYPE_INVALID = "error.protocol.reviewerTypeCode.invalid";
    public static final String ERROR_PROTOCOL_REVIEWER_NOT_AVAILABLE = "error.protocol.reviewer.unavailable";
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
    public static final String ERROR_PROTOCOL_RECORD_COMMITEE_NO_YES_VOTES = "error.protocol.recordcommittee.no.yescount";
    public static final String ERROR_PROTOCOL_RECORD_COMMITEE_INVALID_VOTE_COUNT = "error.protocol.recordcommittee.votecount.invalid";
    public static final String ERROR_PROTOCOL_RECORD_COMMITEE_VOTE_YES_NOT_GREATER = "error.protocol.recordcommittee.vote.count.yesCount.notGreater";
    public static final String ERROR_PROTOCOL_RECORD_COMMITEE_YES_VOTES_NOT_POSITIVE = "error.protocol.recordcommittee.vote.count.yesCount.notPositive";
    public static final String ERROR_PROTOCOL_RECORD_COMMITEE_NO_VOTES_NOT_NONNEGATIVE = "error.protocol.recordcommittee.vote.count.noCount.notNonNegative";    
    public static final String ERROR_PROTOCOL_RECORD_COMMITEE_NO_SMR_SRR_DISAPPROVE_REVIEWER_COMMENTS = "error.protocol.recordcommittee.no.smrsrrdisapprove.reviewer.comments";
    public static final String ERROR_PROTOCOL_RECORD_COMMITEE_NO_MINOR_MAJOR_DISAPPROVE_REVIEWER_COMMENTS = "error.protocol.recordcommittee.no.minormajordisapprove.reviewer.comments";
    public static final String ERROR_PROTOCOL_ASSIGN_TO_AGENDA_NO_ACTION_DATE = "error.protocol.actiondate.required";
    public static final String ERROR_PROTOCOL_DOCUMENT_STATE_CHANGED = "error.protocol.document.state.changed";
    public static final String ERROR_COMMITTEE_TYPE_NOT_EXIST = "error.committeeType.not.exist";

    public static final String ERROR_PROTOCOL_REVIEW_TYPE_REVIEWER_MISMATCH = "error.protocol.reviewTypeCode.reviewer.mismatch";
    
    public static final String MESSAGE_PROTOCOL_ACTION_SUCCESSFULLY_COMPLETED = "message.protocol.action.successfully.completed";
    public static final String MESSAGE_ONLINE_REVIEW_ACTION_SUCCESSFULLY_COMPLETED = "message.protocol.onlinereview.document.action.successfully.completed";
    
    //IRB Protocol Online Review Required Fields Validation Messages
    public static final String ERROR_ONLINE_REVIEW_COMMENT_REQUIRED = "error.protocol.onlinereview.comment.required";
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
    public static final String ERROR_PROTOCOL_ATTACHMENT_INVALID_TYPE = "error.protocolAttachment.invalid.type";
    public static final String AUDIT_ERROR_PROTOCOL_ATTACHMENT_STATUS_COMPLETE = "error.protocolAttachment.not.complete";
    
    // IRB Protocol Funding Sources Validation Messages
    public static final String ERROR_PROTOCOL_FUNDING_SOURCE_TYPE_NOT_FOUND = "error.protocolFundingSource.fundingSourceType.notFound";
    public static final String ERROR_PROTOCOL_FUNDING_SOURCE_NUMBER_NOT_FOUND = "error.protocolFundingSource.fundingSourceNumber.notFound";
    public static final String ERROR_PROTOCOL_FUNDING_SOURCE_NUMBER_INVALID_FOR_TYPE = "error.protocolFundingSource.fundingSourceNumber.invalidForType";
    public static final String ERROR_PROTOCOL_FUNDING_SOURCE_NAME_NOT_FOUND = "error.protocolFundingSource.fundingSourceName.notFound";
    public static final String ERROR_PROTOCOL_FUNDING_SOURCE_DUPLICATE = "error.protocolFundingSource.fundingSource.duplicate";
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
    public static final String ERROR_INVALID_SPONSOR_CODE = "error.invalid.sponsorCode";
    public static final String ERROR_INVALID_PRIME_SPONSOR_CODE = "error.invalid.primeSponsorCode";
    public static final String ERROR_INVALID_AWARD_ID = "error.invalid.awardId";
    public static final String ERROR_INVALID_ROLODEX_ID = "error.invalid.rolodexId";
    public static final String WARNING_INSTITUTIONALPROPOSAL_INACTIVE_SPONSOR = "warning.institutionalproposal.inactive.sponsor";
    public static final String WARNING_INSTITUTIONALPROPOSAL_INACTIVE_PRIMESPONSOR = "warning.institutionalproposal.inactive.primesponsor";

    //Institutional Proposal Financial Validation
    public static final String ERROR_FINANCIAL_DATES = "error.financial.dates";
    public static final String ERROR_FINANCIAL_COSTS = "error.financial.costs";

    
    //Institutional Proposal Cost Share
    public static final String ERROR_IP_COST_SHARE_TYPE_REQUIRED = "error.institutionalProposalCostShare.costShareTypeCode.required";
    public static final String ERROR_IP_COST_SHARE_TYPE_INVALID = "error.institutionalProposalCostShare.costShareTypeCode.invalid";
    public static final String ERROR_IP_COST_SHARE_COMMITMENT_AMOUNT_REQUIRED = "error.institutionalProposalCostShare.amount.required";
    public static final String ERROR_IP_COST_SHARE_COMMITMENT_AMOUNT_INVALID = "error.institutionalProposalCostShare.amount.invalid";
    public static final String ERROR_IP_COST_SHARE_SOURCE_ACCOUNT_REQUIRED = "error.institutionalProposalCostShare.sourceAccount.required";

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
    public static final String INVALID_FILE_NAME = "error.institutionalProposal.notesAndAttachments.fileName.invalid";
    public static final String INVALID_FILE_TYPE = "error.s2s.fileType.invalid";

    // Institutional Proposal Actions
    public static final String QUESTION_UNLOCK_FUNDED_AWARDS = "question.unlock.fundedAwards";
    public static final String ERROR_VALIDATION_ERRORS_EXIST = "error.institutionalproposal.validationFailed";

    //CommitteeSchedule Messages
    public static final String ERROR_COMMITTEESCHEDULE_STARTDATE_BLANK = "error.committeeSchedule.start.date.blank";
    public static final String ERROR_COMMITTEESCHEDULE_STARTANDENDDATE_EQUAL = "error.committeeSchedule.start.and.endDate.equal";
    public static final String ERROR_COMMITTEESCHEDULE_STARTANDENDDATE = "error.committeeSchedule.start.and.endDate";
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
    public static final String ERROR_COMMITTEESCHEDULEATTACHMENTS_ATTACHMENTTYPE = "error.committeeScheduleAttachments.attachmentsTypeCode.blank";
    public static final String ERROR_COMMITTEESCHEDULEATTACHMENTS_FILENAME = "error.committeeScheduleAttachments.meeting.file.blank";
    
    // Committee Actions Messages
    public static final String ERROR_COMMITTEE_ACTION_GENERATE_BATCH_CORRESPONDENCE_TYPE_CODE_NOT_SPECIFIED = "error.committee.action.generate.batch.correspondence.type.code.not.specified";
    public static final String ERROR_COMMITTEE_ACTION_GENERATE_MISSING_TEMPLATES = "error.committee.action.generate.missing.templates";
    public static final String ERROR_COMMITTEE_ACTION_GENERATE_START_DATE_NOT_SPECIFIED = "error.committee.action.generate.start.date.not.specified";
    public static final String ERROR_COMMITTEE_ACTION_GENERATE_END_DATE_NOT_SPECIFIED = "error.committee.action.generate.end.date.not.specified";
    public static final String ERROR_COMMITTEE_ACTION_GENERATE_END_DATE_BEFORE_START_DATE = "error.committee.action.generate.end.date.before.start.date";
    public static final String ERROR_COMMITTEE_ACTION_GENERATE_VIEW_NOT_SPECIFIED = "error.committee.action.generate.view.not.specified";
    public static final String ERROR_COMMITTEE_ACTION_HISTORY_BATCH_CORRESPONDENCE_TYPE_CODE_NOT_SPECIFIED = "error.committee.action.history.batch.correspondence.type.code.not.specified";
    public static final String ERROR_COMMITTEE_ACTION_HISTORY_END_DATE_BEFORE_START_DATE = "error.committee.action.history.end.date.before.start.date";
    public static final String ERROR_COMMITTEE_ACTION_HISTORY_VIEW_NOT_SPECIFIED = "error.committee.action.history.view.not.specified";
    public static final String ERROR_COMMITTEE_ACTION_PRINT_REPORT_NOT_SPECIFIED = "error.committee.action.print.report.not.specified";
    
    //Committee Management Questions
    public static final String QUESTION_COMMITTEE_MANAGEMENT_DELETE_MINUTE_CONFIRMATION = "document.question.committee.management.delete.minute";
    public static final String QUESTION_COMMITTEE_MANAGEMENT_DELETE_OTHER_ACTION_CONFIRMATION = "document.question.committee.management.delete.otherAction";
    
    // IRB Questions
    public static final String QUESTION_PROTOCOL_CONFIRM_SUBMIT_FOR_REVIEW = "question.protocol.confirmExceedMaxProtocols";
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
    public static final String ERROR_QUESTION_MAX_ANSWERS_INVALID_ANSWERS_CHECKBOXES = "error.question.max.answers.invalid.answers.checkboxes";
    public static final String ERROR_QUESTION_MAX_ANSWERS_INVALID_RETURNS = "error.question.max.answers.invalid.returns";
    public static final String ERROR_QUESTION_LOOKUP_CLASS_NOT_SPECIFIED = "error.question.lookup.class.not.specified";
    public static final String ERROR_QUESTION_LOOKUP_RETURN_NOT_SPECIFIED = "error.question.lookup.return.not.specified";
    public static final String ERROR_QUESTION_LOOKUP_RETURN_INVALID = "error.question.lookup.return.invalid";
    public static final String ERROR_QUESTIONNAIRE_NAME_EXIST = "error.questionnaire.name.exist";
    public static final String ERROR_QUESTIONNAIRE_FILENAME_INVALID = "error.questionnaire.filename.invalid";    
    public static final String ERROR_QUESTIONNAIRE_QUESTION_INACTIVE = "error.questionnaire.question_inactive";
    public static final String ERROR_QUESTIONNAIRE_DUPLICATE_USAGE = "error.questionnaire.duplicate.usage";
    
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
    public static final String ERROR_NET_TOTALS_TRANSACTION = "error.net.totals.transaction";
    public static final String WARNING_TRANSACTION_OBLI_LESS_THAN_BUDGET_LIMIT = "warning.transaction.obligated.less.than.limit";
    public static final String CREATE_TIME_AND_MONEY_PENDING_AWARD_EXISTS_ERROR = "error.timeandmoney.create.pending.award.exists";

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
    public static final String AWARD_BUDGET_POST_ENABLED = "AWARD_BUDGET_POST_ENABLED";
    public static final String AWARD_BUDGET_TYPE_NEW = "awardBudgetTypeNew";
    public static final String AWARD_BUDGET_TYPE_REBUDGET = "awardBudgetTypeRebudget";
    
    public static final String ERROR_START_DATE_ON_OR_BEFORE = "error.start.date.on.or.before";
    public static final String ERROR_INVALID_UNITCONTACT_PERSON = "error.unitContact.invalid.person";
    public static final String ERROR_MISSING_UNITCONTACT_PERSON = "error.unitContact.missing.person";
    public static final String WARNING_AWARD_PROJECT_START_DATE_NULL = "warn.awardProjectStartDate.null";
   
    //Organization
    public static final String ERROR_ORGANIZATION_QUESTIONYNQ_ANSWER_REQUIRED = "error.organization.questionYnq.answerRequired";
    public static final String ERROR_ORGANIZATION_QUESTIONYNQ_EXPLANATION_REQUIRED = "error.organization.questionYnq.explanationRequired";
    public static final String ERROR_ORGANIZATION_QUESTIONYNQ_DATE_REQUIRED = "error.organization.questionYnq.reviewDateRequired";
    public static final String ERROR_INVALID_ROLODEX_ENTRY = "error.organization.rolodexEntry.invalid";
    
    public static final String ERROR_AWARD_BASIS_EXIST = "error.awardBasis.exist";
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
    public static final String ERROR_WATERMARK_TEXT_REQUIRED = "error.watermark.text.required";
    
    public static final String ERROR_DUPLICATE_PROPERTY = "error.duplicate.property";
    public static final String ERROR_DATA_GROUP_NOT_EXIST = "error.datagroup.not.exist";
    
  //Subaward    
    public static final String ERROR_REQUIRED_SUBRECIPIENT_ID = "error.required.subrecipient.id";
    public static final String ERROR_REQUIRED_STATUS = "error.required.statuscode";
    public static final String ERROR_REQUIRED_SUBAWARD_TYPE = "error.required.subaward.typecode";
    public static final String ERROR_REQUIRED_REQUISITIONER = "error.required.requisitioner";
    public static final String ERROR_REQUIRED_REQUISITIONER_UNIT = "error.required.requisitioner.unit";
    public static final String ERROR_REQUIRED_PURCHASE_ORDER_NUM = "error.required.purchase.order.num";
    public static final String ERROR_REQUIRED_SUBAWARD_CONTACT_ROLODEX_ID = "error.required.subaward.contact.rolodex.id";
    public static final String ERROR_REQUIRED_SUBAWARD_CONTACT_TYPE_CODE = "error.required.subaward.contact.contact.type.code";
    public static final String ERROR_REQUIRED_SUBAWARD_CLOSEOUT_TYPE_CODE = "error.required.subaward.closeout.closeout.type.code";
    public static final String ERROR_REQUIRED_SUBAWARD_DATE_REQUESTED = "error.required.subaward.closeout.date.requested";
    public static final String ERROR_REQUIRED_SUBAWARD_DATE_FOLLOWUP = "error.required.subaward.closeout.date.followup";
    public static final String ERROR_REQUIRED_SUBAWARD_FUNDING_SOURCE_AWARD_NUMBER= "error.required.subaward.funding.source.award.number";
    public static final String ERROR_REQUIRED_SUBAWARD_CONTACT_PERSON_EXIST= "error.subAwardSponsorContact.person.exists";
    public static final String ERROR_REQUIRED_SUBAWARD_TEMPLATE_INFO_CARRY_FORWARD_REQUESTS_SENT_TO= "error.required.subaward.templateinfo.carryForwardRequestsSentTo";
    public static final String ERROR_REQUIRED_SUBAWARD_FUNDING_SOURCE_AWARD_NUMBER_DUPLICATE= "error.required.subaward.funding.source.award.number.duplicate";
    public static final String SUBAWARD_ERROR_END_DATE_GREATER_THAN_START = "subaward.error.end.date.greater.than.start";
    public static final String ERROR_AMOUNT_INFO_OBLIGATED_AMOUNT_GREATER_THAN_ANTICIPATED_AMOUNT = "subaward.error.obligated.amount.greater.than.anticipated.amount";
    public static final String ERROR_AMOUNT_INFO_OBLIGATED_AMOUNT_NEGATIVE = "subaward.error.obligated.amount.less.than.zero";
    public static final String ERROR_AMOUNT_INFO_OBLIGATED_AMOUNT_ZERO = "subaward.error.obligated.amount.equal.to.zero";
    public static final String ERROR_AMOUNT_INFO_ANTICIPATED_AMOUNT_NEGATIVE = "subaward.error.anticipated.amount.less.than.zero";
    public static final String ERROR_AMOUNT_INFO_ANTICIPATED_AMOUNT_ZERO = "subaward.error.anticipated.amount.equal.to.zero";
    public static final String ERROR_SUBAWARD_CONTACT = "subaward.contact.not.added";
    public static final String ERROR_SUBAWARD_AMOUNT_RELEASED_GREATER_OBLIGATED_AMOUNT ="subaward.error.amount.released.greater.than.obligated.amount";
    public static final String ERROR_SUBAWARD_OBLIGATED_AMOUNT_SHOULD_BE_GREATER_AMOUNT_RELEASED ="subaward.error.oblig.amount.greater.than.amount.released";
    public static final String ERROR_SUBAWARD_OBLIGATED_AMOUNT_IS_GREATER_AMOUNT_RELEASED ="subaward.error.oblig.amount.is.greater.than.amount.released";
    public static final String ERROR_INVALID_SUBRECIPIENT_ID = "error.invalid.subrecipient.id";
    public static final String ERROR_INVALID_SITEINVESTIGATOR_ID = "error.invalid.siteinvestigator.id";
    public static final String ERROR_INVALID_REQUISITIONER = "error.invalid.requisitioner";
    public static final String ERROR_SUBAWARD_INVOICE_AMOUNT_RELEASED_ZERO = "subaward.error.amount.released.zero";
    public static final String ERROR_PERIOD_OF_PERFORMANCE_START_DATE_SHOULD_BE_GREATER_THAN_ERROR_PERIOD_OF_PERFORMANCE_END_DATE = "subaward.error.period.of.performance.startdate.should.greater.than.period.of.performance.enddate";
    public static final String SUBAWARD_ATTACHMENT_TYPE_CODE_REQUIRED = "error.Subaward.attachment.typeRequired";
    public static final String SUBAWARD_ATTACHMENT_FILE_REQUIRED = "error.Subaward.attachment.fileRequired";
    public static final String SUBAWARD_ATTACHMENT_DESCRIPTION_REQUIRED = "error.Subaward.attachment.descriptionRequired";
    public static final String ERROR_REQUIRED_SUBAWARD_REPORT_TYPE_CODE = "error.required.subaward.report.type.code";
    public static final String ERROR_CONTACT_TYPE_CODE_FOREIGN_KEY_EXISTS = "error.contact.type.code.foreign.key.exists";
    public static final String ERROR_CONTACT_TYPE_NOT_EXISTS = "error.contact.type.not.exists";
    public static final String ERROR_MODULE_NOT_EXISTS = "error.module.not.exists";
    public static final String ERROR_CONTACT_TYPE_MODULE_EXISTS = "error.contacttypemodule.exists";
    public static final String SUBAWARD_ERROR_INVALID_FILE_TYPE = "error.subaward.invalid.file.type";

    
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
    public static final String NEGOTIATION_ACTIVITY_END_AFTER_NEGOTIATION = "negotiation.error.activity.end.after.negotiation.end";
    public static final String NEGOTIATION_ACTIVITY_START_BEFORE_END = "negotiation.error.activity.start.before.end";
    public static final String NEGOTIATION_CLOSE_PENDING_ACTIVITIES = "negotiation.message.closePendingActivities";
    public static final String NEGOTIATION_DUPLICATE_LINKING = "negotiation.message.duplicateNegotiationLinked";
    public static final String NEGOTIATION_MULTIPLE_PI = "negotiation.error.multiplePi";
    public static final String NEGOTIATION_DELETE_ACTIVITY = "negotiation.message.deleteActivity";
    
    public static final String MESSAGE_FINANCIAL_ENTITY_ACTION_COMPLETE = "message.financial.entity.action.successfully.completed";
    public static final String ERROR_DEACTIVATE_FINANCIAL_ENTITY_REASON_REQUIRED = "error.deactivate.financial.entity";
    public static final String ERROR_COI_FINANCIAL_ENTITY_STATUS_REQUIRED = "error.coi.financialentity.status.required";
    public static final String ERROR_COI_PROJECT_REQUIRED = "error.coi.project.required";
    public static final String ERROR_COI_DUPLICATE_PROJECT_ID = "error.coi.duplicate.projectid";
    public static final String ERROR_COI_DISPOSITON_STATUS_REQUIRED = "error.coi.disposition.status.required";
    public static final String ERROR_COI_DISCLOSURE_STATUS_REQUIRED = "error.coi.disclosure.status.required";
    public static final String ERROR_COI_DISCLOSURE_STATUS_INVALID = "error.coi.disclosure.status.invalid";
    public static final String ERROR_COI_QUESTIONNAIRE_MANDATORY = "error.coi.questionnaire.mandatory";
    public static final String ERROR_COI_QUESTIONNAIRE_NOTUPDATED = "error.coi.questionnaire.notupdated";
    
    public static final String REPORT_TRACKING_WARNING_UPDATE_FROM_DATE_CHANGE = "warning.reportTracking.updateFromDateChange";
    
    // Person Mass Change
    public static final String ERROR_PERSON_MASS_CHANGE_REPLACEE_EMPTY = "error.personMassChange.replacee.empty";
    public static final String ERROR_PERSON_MASS_CHANGE_REPLACER_EMPTY = "error.personMassChange.replacer.empty";
    public static final String ERROR_PERSON_MASS_CHANGE_REPLACEE_SELECTION = "error.personMassChange.replacee.selection";
    public static final String ERROR_PERSON_MASS_CHANGE_REPLACER_SELECTION = "error.personMassChange.replacer.selection";
    public static final String ERROR_PERSON_MASS_CHANGE_DOCUMENT_LOCKED = "error.personMassChange.document.locked";
    public static final String ERROR_PERSON_MASS_CHANGE_SAME_PERSONS = "error.personMassChange.person.same";
    public static final String ERROR_PERSON_MASS_CHANGE_REPLACER_EXISTS = "error.personMassChange.replacer.exists";
    
    // IACUC protocol species
    public static final String QUESTION_PROTOCOL_SPECIES_DELETE_CONFIRMATION = "question.iacuc.protocol.species.delete.confirmation";
    public static final String QUESTION_PROTOCOL_EXCEPTION_DELETE_CONFIRMATION = "question.iacuc.protocol.exception.delete.confirmation";
    public static final String IACUC_PROTOCOL_SPECIES_DEPENDENCY_EXISTS = "error.iacuc.protocol.species.dependency.exists";
    public static final String IACUC_PROTOCOL_PERSON_DEPENDENCY_EXISTS = "error.iacuc.protocol.person.dependency.exists";
    
    // IACUC protocol species
    public static final String IACUC_PROTOCOL_ALT_SEARCH_QUESTION_NOT_ANSWERED = "error.iacuc.alt.search.question.not.answered";
    public static final String IACUC_PROTOCOL_ALT_SEARCH_DATA_NOT_ENTERED = "error.iacuc.alt.search.data.not.entered";
    
    // IACUC error messages
    public static final String ERROR_IACUC_VALIDATION_ALTERNATE_SEARCH = "error.iacuc.validation.alternate.search";
    public static final String ERROR_IACUC_VALIDATION_SEARCHDATE_AFTER_CURRENTDATE = "error.iacuc.validation.searchdate.after.currentdate";


    // IACUC protocol procedures
    public static final String ERROR_IACUC_VALIDATION_PERSON_RESPONSIBLE_VALID = "error.iacuc.validation.personResponsible.valid";
    public static final String ERROR_IACUC_VALIDATION_DUPLICATE_PERSON_RESPONSIBLE = "error.iacuc.validation.duplicate.personResponsible";
    public static final String ERROR_IACUC_VALIDATION_DUPLICATE_STUDY_GROUP_LOCATION = "error.iacuc.validation.duplicate.studyGroup.location";
    public static final String ERROR_IACUC_VALIDATION_STUDY_GROUP_VALID = "error.iacuc.validation.studyGroup.valid";
    public static final String ERROR_IACUC_VALIDATION_DUPLICATE_STUDY_GROUP = "error.iacuc.validation.duplicate.studyGroup";
    public static final String QUESTION_PROCEDURE_LOCATION_DELETE_CONFIRMATION = "question.iacuc.procedure.location.delete.confirmation";
    public static final String QUESTION_PROCEDURE_GROUP_LOCATION_DELETE_CONFIRMATION = "question.iacuc.procedure.group.location.delete.confirmation";
    
    public static final String QUESTION_PROCEDURE_STUDY_GROUP_DELETE_CONFIRMATION = "question.iacuc.procedure.study.group.delete.confirmation";
    public static final String IACUC_PROCEDURE_STUDY_GROUP_SPECIES_PAIN_CATEGORY_REQUIRED = "error.iacuc.procedure.study.group.species.painCategory.required";
    public static final String IACUC_PROCEDURE_STUDY_GROUP_SPECIES_COUNT_REQUIRED = "error.iacuc.procedure.study.group.species.count.required";

    // IACUC person training
    public static final String ERROR_IACUC_VALIDATION_INVALID_PERSON_TRAINING = "error.iacuc.validation.invalid.person.training";
    public static final String ERROR_IACUC_VALIDATION_MISMATCH_PERSON_TRAINING = "error.iacuc.validation.mismatch.person.training";
    
    // no reason given string
    public static final String NO_REASON_GIVEN="message.none.given";
    
    // Disclosure and Financial Entity errors
    public static final String MESSAGE_CANCEL_FE = "message.cancel.fe";
    
    public static final String ERROR_FORMULATED_UNIT_COST = "error.invalid.budget.formulated.unit.cost";
    public static final String ERROR_FORMULATED_CALCULATED_EXPENSES = "error.invalid.budget.formulated.calculated.expense";
    public static final String ERROR_FORUMLATED_COST_DUPLICATE = "error.invalid.budget.formulated.duplicate";
    
    public static final String WARNING_DOCUMENT_RELOAD_CONFIRMATION = "warning.document.reload.confirmation";
    
    // Birt Errors
    public static final String ERROR_BIRT_REPORT_INPUT_MISSING = "error.missing.report.inputparameters";
    
    public static final String REPORT_INPUT_PARAMETER_DATE_TYPE = "error.invalid.report.date";
        
    public static final String INVALID_BIRT_REPORT = "error.invalid.report";
    
    public static final String REPORT_INPUT_PARAMETER_MISSING = "error.missing.report.inputparameters";

    public static final String ERROR_SEARCH_INVALID_DATE = "error.search.invalid.date";
    public static final String S2S_DUPLICATE_USER_ATTACHED_FORM = "error.s2s.userattachedform.namespace.duplicate";
    public static final String S2S_USER_ATTACHED_FORM_EMPTY = "error.s2s.userattachedform.file.empty";
    public static final String S2S_USER_ATTACHED_FORM_WRONG_FILE_TYPE = "error.s2s.userattachedform.wrong.filetype";
    public static final String S2S_USER_ATTACHED_FORM_NOT_VALID = "error.s2s.userattachedform.invalid";
    public static final String S2S_USER_ATTACHED_FORM_NOT_PDF = "error.s2s.userattachedform.not.pdf";
    public static final String ERROR_DELETION_BLOCKED = "error.deletion.blocked";

    public static final String AUDIT_WARNING_PROPOSAL_WITHNO_BUDGET = "warning.proposal.withno.budget";
    public static final String ERROR_ATTACHMENT_FILE_REQURIED = "error.attachment.file.required";
    
    /**
     * private utility class ctor.
     * @throws UnsupportedOperationException if called.
     */
    private KeyConstants() {
        throw new UnsupportedOperationException("do not call me");
    }
}
