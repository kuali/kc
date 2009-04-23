/*
 * Copyright 2006-2008 The Kuali Foundation
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
 * This class contains constants
 */
public final class KeyConstants {
    
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
    public static final String ERROR_APPROVAL_DATE_BEFORE_APPLICATION_DATE_SPECIALREVIEW = "error.approvalDate.before.applicationDate.for.valid.specialReview";
    public static final String ERROR_START_DATE_AFTER_END_DATE = "error.start.date.after.end.date";

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
    public static final String ERROR_NARRATIVE_USER_RIGHT_NO_PERMISSION="error.narrative.no.permission";
    
    // Authorization
    public static final String AUTHORIZATION_VIOLATION = "error.authorization.violation";
    
    // Pessimistic Locking Cron Job
    public static final String PESSIMISTIC_LOCKING_CRON_EXPRESSION = "pessimisticLocking.cronExpression";
    public static final String PESSIMISTIC_LOCKING_EXPIRATION_AGE = "pessimisticLocking.expirationAge";
    
    // Grants.gov
    public static final String ERROR_IF_PROPOSALTYPE_IS_REVISION = "error.s2sopportunity.revisiontype";
    public static final String ERROR_IF_CFDANUMBER_AND_OPPORTUNITY_ID_IS_NULL = "error.s2sopportunity.cfdaNumber_opportunityId_null"; 
    public static final String ERROR_IF_REVISIONTYPE_IS_OTHER = "error.s2sopportunity.revisionTypeOther";
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
    public static final String ERROR_PERCENT_EFFORT_LESS_THAN_PERCENT_CHARGED = "error.percentEffort.lessThan.percentCharged";
    public static final String ERROR_COST_ELEMENT_NOT_SELECTED = "error.costElement.notSelected";
    public static final String ERROR_GLOBAL_MESSAGE = "error.global.application";
    public static final String WARNING_PERIOD_COST_LIMIT_EXCEEDED= "warning.periodCostLimit.exceeded";

    // special review enhancement
    public static final String ERROR_EXEMPT_NUMBER_SELECTED = "error.exempt.number.selected";
    public static final String ERROR_EXPIRATION_DATE_PAST = "error.expiration.date.past";
    public static final String AUDIT_ERROR_BUDGETPERIOD_START_BEFORE_PROJECT_START_DATE = "error.periodStartDate.before.projectStartDate";
    public static final String AUDIT_ERROR_BUDGETPERIOD_END_AFTER_PROJECT_END_DATE = "error.periodEndDate.after.projectEndDate";
    
    // Change Password
    public static final String ERROR_EMPTY_PASSWORD = "error.password.empty";
    public static final String ERROR_INVALID_PASSWORD = "error.password.invalid";
    public static final String ERROR_PASSWORD_MISMATCH = "error.password.mismatch";
    
    // Copy proposal
    public static final String ERROR_LEAD_UNIT_REQUIRED = "error.leadUnit.required";
    
    public static final String ERROR_RATE_TYPE_NOT_EXIST = "error.rateType.not.exist";
    
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

    public static final String ERROR_DELETE_PERSON_WITH_PERSONNEL_DETAIL = "error.delete.person.with.personnelDetails";
    public static final String ERROR_DELETE_LINE_ITEM = "error.delete.lineitem";
    public static final String ERROR_INSERT_BUDGET_PERIOD = "error.insert.budget.period";
    public static final String ERROR_APPLY_TO_LATER_PERIODS = "error.applyTo.later.periods";

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
    public static final String QUESTION_DEFAULT_BUDGET_PERIODs = "document.question.defaultBudgetPeriods.text";
    public static final String AUDIT_WARNING_RATE_OUT_OF_SYNC = "warning.rate.outofsync";
    public static final String ERROR_DUPLICATE_PERSON = "error.duplicate.person";

    // Budget Version
    public static final String BUDGET_VERSION_EXISTS = "error.budgetVersion.exists";
    
    // Award Approved Equipment
    public static final String ERROR_AWARD_APPROVED_EQUIPMENT_AMOUNT_REQUIRED = "error.required";
    public static final String ERROR_AWARD_APPROVED_EQUIPMENT_ITEM_REQUIRED = ERROR_AWARD_APPROVED_EQUIPMENT_AMOUNT_REQUIRED;
    public static final String ERROR_AWARD_APPROVED_EQUIPMENT_AMOUNT_VALID = "error.awardApprovedEquipment.amount.invalid";
    public static final String ERROR_AWARD_APPROVED_EQUIPMENT_ITEM_NOT_UNIQUE = "error.awardApprovedEquipment.notunique";
    
    //Award Payment Schedule    
    public static final String ERROR_AWARD_PAYMENT_SCHEDULE_DUE_DATE_REQUIRED = "error.required";
    public static final String ERROR_AWARD_PAYMENT_SCHEDULE_ITEM_NOT_UNIQUE = "error.awardPaymentSchedule.notunique";
    
    // Award Details and Dates
    public static final String ERROR_INVALID_AWARD_TRANSFERRING_SPONSOR = "error.sponsor.invalid";
    public static final String ERROR_DUPLICATE_AWARD_TRANSFERRING_SPONSOR = "error.sponsor.duplicate";
    
    //Award Cost Share
    public static final String ERROR_FISCAL_YEAR_RANGE = "error.awardCostShare.fiscalYear.range";
    public static final String ERROR_SOURCE_DESTINATION = "error.awardCostShare.source.destination";
    
    //Award Sponsor Term
    public static final String ERROR_DUPLICATE_SPONSOR_TERM = "error.awardSponsorTerm.duplicate.term";
    
    //Award Benefits Rates
    public static final String ERROR_BENEFITS_RATES = "error.awardBenefitsRates.invalid.rates";
    
    //Award Approved Subaward
    public static final String ERROR_AMOUNT_IS_ZERO = "error.awardApprovedSubaward.Amount.equals.zero";
    public static final String ERROR_DUPLICATE_ORGANIZATION_NAME = "error.awardApprovedSubaward.duplicate.organization.name";
    public static final String ERROR_ORGANIZATION_NAME_IS_NULL = "error.awardApprovedSubaward.organization.is.null";
    //Award Cost Share Confirmation Questions
    public static final String QUESTION_DELETE_COST_SHARE = "document.question.deleteCostShare.text";
    
    //Award Direc F and A Distribution
    public static final String ERROR_OVERLAPPING_EXISTING_DATES = "error.awardDirectFandADistribution.overlapping.existing.dates";
    public static final String ERROR_START_DATE_PRECEDES_END_DATE = "error.awardDirectFandADistribution.invalid.start.end.dates";
    public static final String ERROR_OVERLAPPING_DATE_RANGES = "error.awardDirectFandADistribution.date.ranges.overlap";
    public static final String ERROR_TARGET_START_DATE = "error.awardDirectFandADistribution.invalid.start.date";
    public static final String ERROR_TARGET_END_DATE = "error.awardDirectFandADistribution.invalid.end.date";
    
    //Award Indirect Cost Rate
    public static final String ERROR_REQUIRED_APPLICABLE_INDIRECT_COST_RATE = "error.required.applicable.indirect.cost.rate";
    public static final String ERROR_APPLICABLE_INDIRECT_COST_RATE_CAN_NOT_BE_NEGATIVE = "error.applicable.indirect.cost.rate.can.not.be.negative";
    public static final String ERROR_REQUIRED_INDIRECT_RATE_TYPE_CODE = "error.required.indirect.rate.type.code";
    public static final String ERROR_REQUIRED_FISCAL_YEAR = "error.required.fiscal.year";
    public static final String ERROR_REQUIRED_START_DATE= "error.required.start.date";
    public static final String ERROR_END_DATE_BEFORE_START_DATE_INDIRECT_COST_RATE = "error.endDate.before.startDate.for.valid.indirectCostRate";
    public static final String ERROR_FISCAL_YEAR_INCORRECT_FORMAT = "error.fiscalYear.IncorrectFormat";
    
    //Award Reports
    public static final String ERROR_REQUIRED_REPORT_CODE = "error.required.report.code";
    public static final String ERROR_REQUIRED_FREQUENCY_CODE = "error.required.frequency.code";
    public static final String ERROR_REQUIRED_FREQUENCY_BASE_CODE = "error.required.frequency.base.code";
    public static final String ERROR_REQUIRED_DISTRIBUTION_CODE = "error.required.distribution.code";
    public static final String ERROR_REQUIRED_DUE_DATE = "error.required.due.date";
    public static final String ERROR_REQUIRED_CONTACT_TYPE = "error.required.contact.type";
    public static final String ERROR_REQUIRED_ROLODEX_ID = "error.required.rolodex.id";
    public static final String ERROR_EMPTY_REPORT_TERMS = "error.empty.report.terms";
    public static final String ERROR_EMPTY_TERMS = "error.empty.terms";
    
    //Award System Parameters    
    public static final String MIT_IDC_VALIDATION_ENABLED = "mit.idc.validation.enabled";
    public static final String MIT_IDC_VALIDATION_ENABLED_VALUE_FOR_COMPARISON = "1";
    public static final String REPORT_CLASS_FOR_PAYMENTS_AND_INVOICES = "reportClassForPaymentsAndInvoices";
    public static final String PERIOD_IN_YEARS_WHEN_FREQUENCY_BASE_IS_FINAL_EXPIRATION_DATE
        = "scheduleGenerationPeriodInYearsWhenFrequencyBaseCodeIsFinalExpirationDate";
    public static final String CONTACT_TYPE_OTHER = "contactTypeOther";
    
    //Award Validation Error Messages
    public static final String INDIRECT_COST_RATE_NOT_IN_PAIR = "indirectCostRate.not.in.pair";
    public static final String INVALID_REPORT_CODE_FOR_REPORT_CLASS = "invalid.type.for.reportClass";
    public static final String INVALID_FREQUENCY_FOR_REPORT_CLASS_AND_TYPE = "invalid.frequency.for.reportClass.and.type";
    public static final String INVALID_FREQUENCY_BASE_FOR_FREQUENCY = "invalid.frequencyBase.for.frequency";
    
    // IRB Protocol Participant Types Validation Messages
    public static final String ERROR_PROTOCOL_PARTICIPANT_TYPE_NOT_SELECTED = "error.protocol.participantType.notselected";
    public static final String ERROR_PROTOCOL_PARTICIPANT_TYPE_INVALID = "error.protocol.participantType.invalid";
    public static final String ERROR_PROTOCOL_PARTICIPANT_TYPE_DUPLICATE = "error.protocol.participantType.duplicate";
    public static final String ERROR_PROTOCOL_PARTICIPANT_COUNT_INVALID = "error.protocol.participantCount.invalid";

    // IRB Protocol Location Validation Messages
    public static final String ERROR_PROTOCOL_LOCATION_ORGANIZATION_ID_NOT_FOUND = "error.protocolLocation.organizationId.notFound";
    public static final String ERROR_PROTOCOL_LOCATION_ORGANIZATION_ID_INVALID = "error.protocolLocation.organizationId.invalid";
    public static final String ERROR_PROTOCOL_LOCATION_ORGANIZATION_ID_DUPLICATE = "error.protocolLocation.organizationId.duplicate";
    public static final String ERROR_PROTOCOL_LOCATION_ORGANIZATION_TYPE_CODE_NOT_FOUND = "error.protocolLocation.organizationTypeCode.notFound";
    public static final String ERROR_PROTOCOL_LOCATION_SHOULD_EXIST = "error.protocolLocation.shouldExist";
    
    // IRB Protocol Required Fields Validation Messages
    public static final String ERROR_PROTOCOL_PRINCIPAL_INVESTIGATOR_ID_NOT_FOUND = "error.protocolRequiredFields.prinInvestigatorId.notFound";
    public static final String ERROR_PROTOCOL_PRINCIPAL_INVESTIGATOR_NAME_NOT_FOUND = "error.protocolRequiredFields.prinInvestigatorName.notFound";
    public static final String ERROR_PROTOCOL_LEAD_UNIT_NUM_NOT_FOUND = "error.protocolRequiredFields.leadUnitNum.notFound";
    public static final String ERROR_PROTOCOL_LEAD_UNIT_NUM_INVALID = "error.protocolRequiredFields.leadUnitNum.invalid";
    public static final String ERROR_PROTOCOL_LEAD_UNIT_NAME_NOT_FOUND = "error.protocolRequiredFields.leadUnitName.notFound";
    public static final String ERROR_PROTOCOL_TITLE_NOT_FOUND = "error.protocolRequiredFields.title.notFound";
    public static final String ERROR_PROTOCOL_TYPE_NOT_FOUND = "error.protocolRequiredFields.typeCode.notFound";

    // IRB Protocol Actions Required Fields Validation Messages
    public static final String ERROR_PROTOCOL_SUBMISSION_TYPE_NOT_SELECTED = "error.protocolRequiredFields.submissionTypeCode.notFound";
    public static final String ERROR_PROTOCOL_SUBMISSION_TYPE_INVALID = "error.protocolRequiredFields.submissionTypeCode.invalid";
    public static final String ERROR_PROTOCOL_REVIEW_TYPE_NOT_SELECTED = "error.protocolRequiredFields.reviewTypeCode.notFound";
    public static final String ERROR_PROTOCOL_REVIEW_TYPE_INVALID = "error.protocolRequiredFields.reviewTypeCode.invalid";
    public static final String ERROR_PROTOCOL_REVIEWER_NO_TYPE_BUT_REVIEWER_CHECKED = "error.protocolRequiredFields.reviewerTypeCode.notFoundButReviewerChecked";
    public static final String ERROR_PROTOCOL_REVIEWER_NOT_CHECKED_BUT_TYPE_SELECTED = "error.protocolRequiredFields.reviewerTypeCode.selectedButReviewerUnchecked";
    public static final String ERROR_PROTOCOL_REVIEWER_TYPE_INVALID = "error.protocolRequiredFields.reviewerTypeCode.invalid";

    //  Committee Messages
    public static final String ERROR_COMMITTEE_DUPLICATE_ID = "error.committee.duplicateId";
    
    //CommitteeMembership Messages
    public static final String ERROR_COMMITTEE_MEMBERSHIP_PERSON_NOT_SPECIFIED = "error.committee.membership.person.not.specified";
    public static final String ERROR_COMMITTEE_MEMBERSHIP_PERSON_DUPLICATE = "error.committee.membership.person.duplicate";
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

    
    public static final String SOFT_ERRORS_KEY = "SOFT_ERRORS_KEY";

    // IRB Protocol Personnel Validation Messages
    public static final String ERROR_PROTOCOL_PERSONNEL_MULTIPLE_PI = "error.protocolPersonnel.pi.already.exists";
    public static final String ERROR_PROTOCOL_PERSONNEL_ROLE_MANDATORY = "error.protocolPersonnel.role.mandatory";
    public static final String ERROR_DUPLICATE_PROTOCOL_PERSONNEL = "error.duplicate.protocolPersonnel";
    public static final String ERROR_ROLE_CHANGE_NOT_PERMITTED = "error.role.change.notPermitted";
    public static final String ERROR_PRINCIPAL_INVESTIGATOR_NOT_FOUND = "error.investigator.notFound";
    public static final String ERROR_PROTOCOL_UNIT_NOT_FOUND = "error.protocolUnit.notFound";
    public static final String ERROR_PROTOCOL_LEAD_UNIT_NOT_DEFINED = "error.protocolLeadUnit.notDefined";
    public static final String ERROR_PROTOCOL_UNIT_INVALID = "error.protocolUnit.invalid";
    public static final String ERROR_PROTOCOL_UNIT_DUPLICATE = "error.protocolUnit.duplicate";
    public static final String ERROR_PROTOCOL_INVESTIGATOR_INVALID = "error.protocol.investigator.invalid";
    
    //IRB Protocol Note And Attachment Validation Messages
    public static final String ERROR_PROTOCOL_ATTACHMENT_MISSING_TYPE = "error.protocolAttachment.missing.type";
    public static final String ERROR_PROTOCOL_ATTACHMENT_MISSING_DESC = "error.protocolAttachment.description.required";
    public static final String ERROR_PROTOCOL_ATTACHMENT_MISSING_FILE = "error.protocolAttachment.missing.file";
    public static final String ERROR_PROTOCOL_ATTACHMENT_MISSING_PERSON = "error.protocolAttachment.missing.person";
    public static final String ERROR_PROTOCOL_ATTACHMENT_DUPLICATE_TYPE = "error.protocolAttachment.duplicate.type";
    public static final String ERROR_PROTOCOL_ATTACHMENT_INVALID_PERSON = "error.protocolAttachment.invalid.person";
    public static final String ERROR_PROTOCOL_ATTACHMENT_INVALID_TYPE = "error.protocolAttachment.invalid.type";
    public static final String ERROR_PROTOCOL_ATTACHMENT_INVALID_STATUS = "error.protocolAttachment.invalid.status";
    public static final String AUDIT_ERROR_PROTOCOL_ATTACHMENT_STATUS_COMPLETE = "error.protocolAttachment.not.complete";
    
    // IRB Protocol Funding Sources Validation Messages
    public static final String ERROR_PROTOCOL_FUNDING_TYPE_NOT_FOUND = "error.protocolFundingSource.fundingType.notFound";
    public static final String ERROR_PROTOCOL_FUNDING_ID_NOT_FOUND = "error.protocolFundingSource.fundingId.notFound";
    public static final String ERROR_PROTOCOL_FUNDING_ID_INVALID_FOR_TYPE = "error.protocolFundingSource.fundingId.invalidForType";
    public static final String ERROR_PROTOCOL_FUNDING_NAME_NOT_FOUND = "error.protocolFundingSource.fundingSourceName.notFound";
    public static final String ERROR_PROTOCOL_FUNDING_DUPLICATE = "error.protocolFundingSource.fundingSource.duplicate";
    public static final String ERROR_PROTOCOL_FUNDING_NAME_INVALID = "error.protocolFundingSource.fundingSourceName.invalid";
    //public static final String ERROR_PROTOCOL_FUNDING_TITLE_NOT_FOUND = "error.protocolFundingSource.fundingSourceTitle.notFound";
    
    public static final String ERROR_FUNDING_LOOKUPTEMP_UNAVAIL = "error.protocolFundingSource.fundingSourceLookupTemp.invalid";
    public static final String ERROR_FUNDING_LOOKUP_UNAVAIL = "error.protocolFundingSource.fundingSourceLookup.invalid";
    public static final String ERROR_FUNDING_LOOKUP_NOT_FOUND = "error.protocolFundingSource.fundingSourceLookup.notFound";
    
    // Permissions
    public static final String ERROR_DUPLICATE_PERMISSIONS_USER = "error.duplicate.permissionsUser";
    public static final String ERROR_PERMISSIONS_LAST_ADMINSTRATOR = "error.permissions.last.administrator";
    public static final String ERROR_PERMISSIONS_ADMINSTRATOR_INCLUSIVE = "error.permissions.administrator.inclusive";
        
    //IRB Additional Information Fields
    public static final String ERROR_PROTOCOLREFERENCE_PROTOCOLREFERENCETYPECODE = "error.required.for.protocolReference.protocolReferenceTypeCode";
    public static final String ERROR_PROTOCOLREFERENCE_PROTOCOLREFERENCEKEY = "error.required.for.protocolReference.protocolReferenceKey";
    
    //CommitteeSchedule Messages
    public static final String ERROR_COMMITTEESCHEDULE_STARTANDENDDATE_EQUAL = "error.committeeSchedule.start.and.endDate.equal";
    public static final String ERROR_COMMITTEESCHEDULE_STARTANDENDDATE = "error.committeeSchedule.start.and.endDate";
    public static final String ERROR_COMMITTEESCHEDULE_BLANK = "error.committeeSchedule.blank";    
    public static final String ERROR_COMMITTEESCHEDULE_DATE_CONFLICT = "error.committeeSchedule.date.conflict"; 
    public static final String ERROR_COMMITTEESCHEDULE_DATES_SKIPPED = "error.committeeSchedule.dates.skipped";
    public static final String ERROR_COMMITTEESCHEDULE_FILTER_DATE = "error.committeeSchedule.filter.date";
    public static final String ERROR_COMMITTEESCHEDULE_DEADLINE = "error.committeeSchedule.dealine";
    public static final String ERROR_COMMITTEESCHEDULE_VIEWTIME = "error.committeeSchedule.viewtime";
    public static final String ERROR_COMMITTEESCHEDULE_DAY = "error.committeeSchedule.day";
    public static final String ERROR_COMMITTEESCHEDULE_WEEKDAY = "error.committeeSchedule.weekday";
    
    private KeyConstants() {}
}
