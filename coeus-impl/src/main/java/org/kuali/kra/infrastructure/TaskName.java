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

/**
 * Each task that a user can execute and which requires authorization is
 * enumerated here.  It must correspond to the values in the SpringBeans.xml.
 */
public interface TaskName {
    /*
     * Application Tasks.
     */
    public static final String CREATE_PROTOCOL = "createProtocol";
    public static final String CREATE_AWARD = "createAward";
    public static final String CREATE_TAMD = "createTimeAndMoney";

    /*
     * Protocol Tasks.
     */
    public static final String MODIFY_PROTOCOL = "modifyProtocol";
    public static final String VIEW_PROTOCOL = "viewProtocol";
    public static final String MODIFY_PROTOCOL_ROLES = "modifyProtocolRoles";
    public static final String ADD_PROTOCOL_NOTES = "addProtocolNotes";
    public static final String CREATE_PROTOCOL_AMMENDMENT = "createAmendment";
    public static final String CREATE_PROTOCOL_AMMENDMENT_UNAVAILABLE = "createAmendmentUnavailable";
    public static final String MODIFY_PROTOCOL_AMMENDMENT_SECTIONS = "modifyAmendmentSections";
    public static final String MODIFY_PROTOCOL_AMMENDMENT_SECTIONS_UNAVAILABLE = "modifyAmendmentSectionsUnavailable";
    public static final String CREATE_PROTOCOL_RENEWAL = "createRenewal";
    public static final String CREATE_PROTOCOL_RENEWAL_UNAVAILABLE = "createRenewalUnavailable";
    public static final String CREATE_PROTOCOL_CONTINUATION = "createContinuation";
    public static final String CREATE_PROTOCOL_CONTINUATION_UNAVAILABLE = "createContinuationUnavailable";
    public static final String MODIFY_PROTOCOL_BILLABLE = "modifyBillable";
    public static final String SUBMIT_PROTOCOL = "submitProtocol";
    public static final String SUBMIT_PROTOCOL_UNAVAILABLE = "submitProtocolUnavailable";
    public static final String MODIFY_PROTOCOL_GENERAL_INFO = "modifyProtocolGeneralInfo";
    public static final String MODIFY_PROTOCOL_FUNDING_SOURCE = "modifyProtocolFundingSource";
    public static final String MODIFY_PROTOCOL_REFERENCES = "modifyProtocolReferences";
    public static final String MODIFY_PROTOCOL_ORGANIZATIONS = "modifyProtocolOrganizations";
    public static final String MODIFY_PROTOCOL_SUBJECTS = "modifyProtocolSubjects";
    public static final String MODIFY_PROTOCOL_AREAS_OF_RESEARCH = "modifyProtocolAreasOfResearch";
    public static final String MODIFY_PROTOCOL_ATTACHMENTS = "modifyProtocolAttachments";
    public static final String MODIFY_PROTOCOL_SPECIAL_REVIEW = "modifyProtocolSpecialReview";
    public static final String MODIFY_PROTOCOL_PERSONNEL = "modifyProtocolPersonnel";
    public static final String MODIFY_PROTOCOL_OTHERS = "modifyProtocolOthers";
    public static final String NOTIFY_IRB = "protocolNotifyIrb";
    public static final String NOTIFY_IRB_UNAVAILABLE = "protocolNotifyIrbUnavailable";
    public static final String NOTIFY_COMMITTEE = "protocolNotifyCommittee";
    public static final String NOTIFY_COMMITTEE_UNAVAILABLE = "protocolNotifyCommitteeUnavailable";
    public static final String PROTOCOL_WITHDRAW = "protocolWithdraw";
    public static final String PROTOCOL_WITHDRAW_UNAVAILABLE = "protocolWithdrawUnavailable";
    public static final String PROTOCOL_REQUEST_CLOSE = "protocolRequestClose";
    public static final String PROTOCOL_REQUEST_CLOSE_UNAVAILABLE = "protocolRequestCloseUnavailable";
    public static final String PROTOCOL_REQUEST_SUSPENSION = "protocolRequestSuspension";
    public static final String PROTOCOL_REQUEST_SUSPENSION_UNAVAILABLE = "protocolRequestSuspensionUnavailable";
    public static final String PROTOCOL_REQUEST_CLOSE_ENROLLMENT = "protocolRequestCloseEnrollment";
    public static final String PROTOCOL_REQUEST_CLOSE_ENROLLMENT_UNAVAILABLE = "protocolRequestCloseEnrollmentUnavailable";
    public static final String PROTOCOL_REQUEST_REOPEN_ENROLLMENT = "protocolRequestReOpenEnrollment";
    public static final String PROTOCOL_REQUEST_REOPEN_ENROLLMENT_UNAVAILABLE = "protocolRequestReOpenEnrollmentUnavailable";
    public static final String PROTOCOL_REQUEST_DATA_ANALYSIS = "protocolRequestDataAnalysis";
    public static final String PROTOCOL_REQUEST_DATA_ANALYSIS_UNAVAILABLE = "protocolRequestDataAnalysisUnavailable";
    public static final String PROTOCOL_REQUEST_TERMINATE = "protocolRequestTerminate";
    public static final String PROTOCOL_REQUEST_TERMINATE_UNAVAILABLE = "protocolRequestTerminateUnavailable";
    public static final String PROTOCOL_WITHDRAW_SUBMISSION = "protocolWithdrawRequestSubmission";
    public static final String PROTOCOL_WITHDRAW_SUBMISSION_UNAVAILABLE = "protocolWithdrawRequestSubmissionUnavailable";
    public static final String PROTOCOL_AMEND_RENEW_DELETE = "protocolAmendRenewDelete";
    public static final String PROTOCOL_AMEND_RENEW_DELETE_UNAVAILABLE = "protocolAmendRenewDeleteUnavailable";
    public static final String ASSIGN_TO_AGENDA = "protocolAssignToAgenda";
    public static final String REMOVE_FROM_AGENDA = "protocolRemoveFromAgenda";
    public static final String ASSIGN_TO_AGENDA_UNAVAILABLE = "protocolAssignToAgendaUnavailable";
    public static final String ASSIGN_TO_COMMITTEE_SCHEDULE = "protocolAssignToCmtSched";
    public static final String ASSIGN_TO_COMMITTEE_SCHEDULE_UNAVAILABLE = "protocolAssignToCmtSchedUnavailable";
    public static final String ASSIGN_REVIEWERS = "protocolAssignReviewers";
    public static final String ASSIGN_REVIEWERS_UNAVAILABLE = "protocolAssignReviewersUnavailable";
    public static final String ASSIGN_REVIEWERS_CMT_SEL = "protocolAssignReviewersCmtSel";
    public static final String GRANT_EXEMPTION = "protocolGrantExemption";
    public static final String GRANT_EXEMPTION_UNAVAILABLE = "protocolGrantExemptionUnavailable";
    public static final String VIEW_RESTRICTED_NOTES = "viewRestrictedNotes";
    public static final String EXPEDITE_APPROVAL = "protocolExpediteApproval";
    public static final String EXPEDITE_APPROVAL_UNAVAILABLE = "protocolExpediteApprovalUnavailable";
    public static final String RESPONSE_APPROVAL = "protocolResponseApproval";
    public static final String RESPONSE_APPROVAL_UNAVAILABLE = "protocolResponseApprovalUnavailable";
    public static final String APPROVE_PROTOCOL = "protocolApprove";
    public static final String APPROVE_PROTOCOL_UNAVAILABLE = "protocolApproveUnavailable";
    public static final String DISAPPROVE_PROTOCOL = "protocolDisapprove";
    public static final String DISAPPROVE_PROTOCOL_UNAVAILABLE = "protocolDisapproveUnavailable";
    public static final String RETURN_FOR_SMR = "protocolReturnForSMR";
    public static final String RETURN_FOR_SMR_UNAVAILABLE = "protocolReturnForSMRUnavailable";
    public static final String RETURN_FOR_SRR = "protocolReturnForSRR";
    public static final String RETURN_FOR_SRR_UNAVAILABLE = "protocolReturnForSRRUnavailable";
    public static final String REOPEN_PROTOCOL = "protocolReopen";
    public static final String CLOSE_ENROLLMENT_PROTOCOL = "protocolCloseEnrollment";
    public static final String SUSPEND_PROTOCOL = "protocolSuspend";
    public static final String SUSPEND_PROTOCOL_BY_DSMB = "protocolSuspendByDsmb";
    public static final String CLOSE_PROTOCOL = "protocolClose";
    public static final String EXPIRE_PROTOCOL = "protocolExpire";
    public static final String TERMINATE_PROTOCOL = "protocolTerminate";
    public static final String PERMIT_DATA_ANALYSIS = "protocolPermitDataAnalysis";
    public static final String PROTOCOL_ADMIN_CORRECTION = "protocolAdminCorrection";
    public static final String PROTOCOL_ADMIN_CORRECTION_UNAVAILABLE = "protocolAdminCorrectionUnavailable";
    public static final String PROTOCOL_UNDO_LAST_ACTION = "protocolUndoLastAction";
    public static final String RECORD_COMMITTEE_DECISION = "protocolRecordCommitteeDecision";
    public static final String RECORD_COMMITTEE_DECISION_UNAVAILABLE = "protocolRecordCommitteeDecisionUnavailable";
    public static final String ENTER_RISK_LEVEL = "protocolEnterRiskLevel";
    public static final String IRB_ACKNOWLEDGEMENT = "irbAcknowledgement";
    public static final String IRB_ACKNOWLEDGEMENT_UNAVAILABLE = "irbAcknowledgementUnavailable";
    public static final String MODIFY_PROTOCOL_SUBMISSION = "modifyProtocolSubmisison";
    public static final String MODIFY_PROTOCOL_SUBMISSION_UNAVAILABLE = "modifyProtocolSubmisisonUnavailable";
    public static final String DEFER_PROTOCOL = "protocolDefer";
    public static final String DEFER_PROTOCOL_UNAVAILABLE = "protocolDeferUnavailable";
    public static final String PROTOCOL_REVIEW_NOT_REQUIRED = "protocolReviewNotRequired";
    public static final String PROTOCOL_REVIEW_NOT_REQUIRED_UNAVAILABLE = "protocolReviewNotRequiredUnavailable";
    public static final String PROTOCOL_MANAGE_REVIEW_COMMENTS = "protocolManageReviewComments";
    public static final String PROTOCOL_MANAGE_REVIEW_COMMENTS_UNAVAILABLE = "protocolManageReviewCommentsUnavailable";
    public static final String PROTOCOL_APPROVE_OTHER = "protocolApproveOther";
    public static final String PROTOCOL_MANAGE_NOTES = "protocolManageNotes";
    public static final String PROTOCOL_MANAGE_NOTES_UNAVAILABLE = "protocolManageNotesUnavailable";
    public static final String ABANDON_PROTOCOL = "protocolAbandon";
    public static final String RECALL_PROTOCOL = "recallProtocol";
    
    public static final String ADMIN_APPROVE_PROTOCOL = "adminApproveProtocol"; 
    public static final String ADMIN_APPROVE_PROTOCOL_UNAVAILABLE = "adminApproveProtocolUnavailable"; 
    public static final String ADMIN_INCOMPLETE_PROTOCOL = "adminMarkIncompleteProtocol"; 
    public static final String ADMIN_INCOMPLETE_PROTOCOL_UNAVAILABLE = "adminMarkIncompleteProtocolUnavailable"; 
    public static final String ADMIN_WITHDRAW_PROTOCOL = "adminWithdrawProtocol"; 
    public static final String ADMIN_WITHDRAW_PROTOCOL_UNAVAILABLE = "adminWithdrawProtocolUnavailable"; 
    
    /*
     * Protocol - Online Review Tasks
     */
    
    public static final String MAINTAIN_PROTOCOL_ONLINEREVIEWS = "maintainProtocolOnlineReviews";
    public static final String CREATE_PROTOCOL_ONLINEREVIEW = "createProtocolOnlineReview";
    public static final String MODIFY_PROTOCOL_ONLINEREVIEW = "modifyProtocolOnlineReview";
    public static final String VIEW_PROTOCOL_ONLINEREVIEW = "viewProtocolOnlineReview";

    /**
     * Generic Action task
     */
    public static final String GENERIC_PROTOCOL_ACTION = "genericProtocolAction";
    public static final String GENERIC_PROTOCOL_ACTION_UNAVAILABLE = "genericProtocolActionUnavailable";
    
    /*
     * Committee Tasks.
     */
    public static final String ADD_COMMITTEE = "addCommittee";
    public static final String ADD_IACUC_COMMITTEE = "addIacucCommittee";
    public static final String MODIFY_COMMITTEE = "modifyCommittee";
    public static final String VIEW_COMMITTEE = "viewCommittee";
    public static final String MODIFY_SCHEDULE = "modifySchedule";
    public static final String VIEW_SCHEDULE = "viewSchedule";
    public static final String PERFORM_COMMITTEE_ACTIONS = "performCommitteeActions";
    
    /*
     * Time and Money Tasks
     */
    public static final String MODIFY_TIME_AND_MONEY = "modifyTimeAndMoney";
    public static final String VIEW_TIME_AND_MONEY = "viewTimeAndMoney";
    public static final String MODIFY_TIME_AND_MONEY_ROLES = "modifyTimeAndMoneyRoles";

    /*
     * Award Tasks
     */
    public static final String ANSWER_PROTOCOL_QUESTIONNAIRE = "answerProtocolQuestionnaire";
    public static final String APPROVE_AWARD_BUDGET = "approveAwardBudget";
    public static final String DISAPPROVE_AWARD_BUDGET = "disapproveAwardBudget";
    public static final String POST_AWARD_BUDGET = "postAwardBudget";
    public static final String TOGGLE_AWARD_BUDGET_STATUS = "toggleAwardBudgetStatus";
    public static final String SUBMIT_TO_WORKFLOW = "submitToWorkflow";
    public static final String ADD_BUDGET = "addBudget";
    public static final String OPEN_BUDGETS = "openBudgets";
    public static final String MODIFY_BUDGET = "modifyBudget";
    public static final String VIEW_BUDGET = "viewBudget";
    public static final String VIEW_SALARIES = "viewSalaries";

    /*
     * COI tasks
     */
    public static final String CREATE_COI_DISCLOSURE = "createCoiDisclosure";
    public static final String LOOKUP_COI_DISCLOSURES = "lookupCoiDisclosures";
    public static final String MODIFY_COI_DISCLOSURE = "modifyCoiDisclosure";
    public static final String ANSWER_COI_DISCLOSURE_QUESTIONNAIRE = "answerCoiDisclosureQuestionnaire";
    public static final String VIEW_COI_DISCLOSURE = "viewCoiDisclosure";
    public static final String VIEW_COI_DISCLOSURE_RESTRICTED_NOTES = "viewCoiRestrictedNotes";
    public static final String APPROVE_COI_DISCLOSURE = "approveCoiDisclosure";
    public static final String DISAPPROVE_COI_DISCLOSURE = "disapproveCoiDisclosure";
    public static final String ADD_COI_DISCLOSURE_NOTES = "addCoiDisclosureNotes";
    public static final String ADD_COI_DISCLOSURE_ATTACHMENTS = "addCoiDisclosureAttachments";
    public static final String MAINTAIN_COI_DISCLOSURE_NOTES = "maintainCoiDisclosureNotes";
    public static final String MAINTAIN_COI_DISCLOSURE_ATTACHMENTS = "maintainCoiDisclosureAttachments";
    public static final String MAINTAIN_COI_REVIEWERS = "maintainCoiReviewers";
    public static final String PERFORM_COI_DISCLOSURE_ACTIONS = "performCoiDisclosureAction";
    public static final String VIEW_COI_DISCLOSURE_ACTIONS = "viewCoiDisclosureAction";
    public static final String DELETE_UPDATE_NOTE = "deleteUpdateNote";
    public static final String DELETE_UPDATE_ATTACHMENT = "deleteUpdateAttachment";
    public static final String UPDATE_FE_STATUS_ADMIN = "updateFEStatusAdmin";
    
    /**
     * Negotiation Tasks
     * 
     */
    public static final String NEGOTIATION_CREATE_NEGOTIATION = "createNegotiation";
    public static final String NEGOTIATION_MODIFIY_NEGOTIATION = "modifyNegotiation";
    public static final String NEGOTIATION_CREATE_ACTIVITIES = "createNegotiationActivities";
    public static final String NEGOTIATION_MODIFY_ACTIVITIES = "modifyNegotiationActivities";
    public static final String NEGOTIATION_VIEW_NEGOTIATION = "viewNegotiation";
    public static final String NEGOTIATION_VIEW_NEGOTIATION_UNRESTRICTED = "viewNegotiationUnRestricted";
    
    /*
     * Subaward Tasks
     */
    public static final String CREATE_SUBAWARD = "createSubaward";
    public static final String MODIFY_SUBAWARD = "modifySubaward";
    public static final String VIEW_SUBAWARD = "viewSubaward";
    public static final String ADD_INVOICE_SUBAWARD = "addInvoiceSubAward";
    
    /*
     * Iacuc Tasks
     */
    
    public static final String CREATE_IACUC_PROTOCOL = "createIacucProtocol"; 
    public static final String MODIFY_IACUC_PROTOCOL = "modifyIacucProtocol"; 
    public static final String RETURN_TO_PI_PROTOCOL = "protocolReturnToPI";
    public static final String RETURN_TO_PI_PROTOCOL_UNAVAILABLE = "protocolReturnToPIUnavailable";
    public static final String REVIEW_NOT_REQUIRED_IACUC_PROTOCOL = "reviewNotRequiredIacucProtocol"; 
    public static final String REVIEW_NOT_REQUIRED_IACUC_PROTOCOL_UNAVAILABLE = "reviewNotRequiredIacucProtocolUnavailable"; 
    public static final String VIEW_IACUC_PROTOCOL = "viewIacucProtocol";
    public static final String SUBMIT_IACUC_PROTOCOL = "submitIacucProtocol"; 
    public static final String SUBMIT_IACUC_PROTOCOL_UNAVAILABLE = "submitIacucProtocolUnavailable"; 
    public static final String MODIFY_IACUC_PROTOCOL_SPECIAL_REVIEW = "modifyIacucProtocolSpecialReview";
    public static final String MODIFY_IACUC_PROTOCOL_OTHERS = "modifyIacucProtocolOthers";
    public static final String MODIFY_IACUC_PROTOCOL_GENERAL_INFO = "modifyIacucProtocolGeneralInfo";
    public static final String MODIFY_IACUC_PROTOCOL_ROLES = "modifyIacucProtocolRoles";
    public static final String ADD_IACUC_PROTOCOL_NOTES = "addIacucProtocolNotes";
    public static final String CREATE_IACUC_PROTOCOL_AMENDMENT = "createIacucAmendment"; 
    public static final String CREATE_IACUC_PROTOCOL_AMENDMENT_UNAVAILABLE = "createIacucAmendmentUnavailable"; 
    public static final String MODIFY_IACUC_PROTOCOL_AMENDMENT_SECTIONS = "modifyIacucAmendmentSections"; 
    public static final String MODIFY_IACUC_PROTOCOL_AMENDMENT_SECTIONS_UNAVAILABLE = "modifyIacucAmendmentSectionsUnavailable"; 
    public static final String CREATE_IACUC_PROTOCOL_RENEWAL = "createIacucRenewal"; 
    public static final String CREATE_IACUC_PROTOCOL_RENEWAL_UNAVAILABLE = "createIacucRenewalUnavailable"; 
    public static final String CREATE_IACUC_PROTOCOL_CONTINUATION = "createIacucContinuation"; 
    public static final String CREATE_IACUC_PROTOCOL_CONTINUATION_UNAVAILABLE = "createIacucContinuationUnavailable"; 
    public static final String MODIFY_IACUC_PROTOCOL_BILLABLE = "modifyIacucBillable"; //?
    public static final String MODIFY_IACUC_PROTOCOL_FUNDING_SOURCE = "modifyIacucProtocolFundingSource"; 
    public static final String MODIFY_IACUC_PROTOCOL_REFERENCES = "modifyIacucProtocolReferences";
    public static final String MODIFY_IACUC_PROTOCOL_ORGANIZATIONS = "modifyIacucProtocolOrganizations";
    public static final String MODIFY_IACUC_PROTOCOL_SUBJECTS = "modifyIacucProtocolSubjects";
    public static final String MODIFY_IACUC_PROTOCOL_AREAS_OF_RESEARCH = "modifyIacucProtocolAreasOfResearch";
    public static final String MODIFY_IACUC_PROTOCOL_ATTACHMENTS = "modifyIacucProtocolAttachments";
    public static final String MODIFY_IACUC_PROTOCOL_PERSONNEL = "modifyIacucProtocolPersonnel";
    public static final String MODIFY_IACUC_PROTOCOL_QUESTIONNAIRE = "modifyIacucProtocolQuestionnaire";
    public static final String MODIFY_IACUC_PROTOCOL_THREE_RS = "modifyIacucProtocolThreeRs";    
    public static final String MODIFY_IACUC_PROTOCOL_SPECIES = "modifyIacucProtocolSpecies";    
    public static final String MODIFY_IACUC_PROTOCOL_EXCEPTION = "modifyIacucProtocolException";    
    public static final String MODIFY_IACUC_PROTOCOL_PROCEDURES = "modifyIacucProtocolProcedures";    
    public static final String IACUC_NOTIFY_COMMITTEE_UNAVAILABLE = "iacucProtocolNotifyCommitteeUnavailable";
    public static final String IACUC_NOTIFY_IACUC = "iacucProtocolNotifyIacuc";
    public static final String IACUC_NOTIFY_IACUC_UNAVAILABLE = "iacucProtocolNotifyIacucUnavailable";
    public static final String IACUC_ACKNOWLEDGEMENT = "iacucAcknowledgement"; 
    public static final String IACUC_ACKNOWLEDGEMENT_UNAVAILABLE = "iacucAcknowledgementUnavailable"; 
    public static final String IACUC_PROTOCOL_REQUEST_CLOSE = "iacucProtocolRequestClose"; //?
    public static final String IACUC_PROTOCOL_REQUEST_CLOSE_UNAVAILABLE = "iacucProtocolRequestCloseUnavailable"; //?
    public static final String IACUC_PROTOCOL_REQUEST_SUSPENSION = "iacucProtocolRequestSuspension"; //?
    public static final String IACUC_PROTOCOL_REQUEST_SUSPENSION_UNAVAILABLE = "iacucProtocolRequestSuspensionUnavailable"; //?
    public static final String IACUC_PROTOCOL_REQUEST_TERMINATE = "iacucProtocolRequestTerminate"; //?
    public static final String IACUC_PROTOCOL_REQUEST_TERMINATE_UNAVAILABLE = "iacucProtocolRequestTerminateUnavailable"; //?
    public static final String IACUC_ASSIGN_TO_COMMITTEE = "iacucProtocolAssignToCmt"; 
    public static final String IACUC_ASSIGN_TO_COMMITTEE_UNAVAILABLE = "iacucProtocolAssignToCmtUnavailable";
    public static final String IACUC_ASSIGN_TO_COMMITTEE_SCHEDULE_UNAVAILABLE = "iacucProtocolAssignToCmtSchedUnavailable"; 
    public static final String IACUC_ASSIGN_REVIEWERS = "iacucProtocolAssignReviewers"; 
    public static final String IACUC_ASSIGN_REVIEWERS_UNAVAILABLE = "iacucProtocolAssignReviewersUnavailable"; 
    public static final String IACUC_ASSIGN_REVIEWERS_CMT_SEL = "iacucProtocolAssignReviewersCmtSel"; 
    public static final String IACUC_VIEW_RESTRICTED_NOTES = "iacucViewRestrictedNotes";
    public static final String IACUC_RESPONSE_APPROVAL = "iacucProtocolResponseApproval"; 
    public static final String IACUC_RESPONSE_APPROVAL_UNAVAILABLE = "iacucProtocolResponseApprovalUnavailable"; 
    public static final String MODIFY_IACUC_PROTOCOL_RESEARCH_AREAS = "modifyIacucProtocolResearchAreas";
    public static final String IACUC_REOPEN_PROTOCOL = "iacucProtocolReopen";
    public static final String IACUC_SUSPEND_PROTOCOL = "iacucProtocolSuspend"; 
    public static final String IACUC_CLOSE_PROTOCOL = "iacucProtocolClose";
    public static final String IACUC_EXPIRE_PROTOCOL = "iacucProtocolExpire"; 
    public static final String IACUC_TERMINATE_PROTOCOL = "iacucProtocolTerminate"; 
    public static final String IACUC_PROTOCOL_ADMIN_CORRECTION = "iacucProtocolAdminCorrection"; 
    public static final String IACUC_PROTOCOL_ADMIN_CORRECTION_UNAVAILABLE = "iacucProtocolAdminCorrectionUnavailable"; 
    public static final String IACUC_PROTOCOL_UNDO_LAST_ACTION = "iacucProtocolUndoLastAction"; 
    public static final String IACUC_RECORD_COMMITTEE_DECISION = "iacucProtocolRecordCommitteeDecision";
    public static final String IACUC_RECORD_COMMITTEE_DECISION_UNAVAILABLE = "iacucProtocolRecordCommitteeDecisionUnavailable";
    public static final String IACUC_MODIFY_PROTOCOL_SUBMISSION = "modifyIacucProtocolSubmission";
    public static final String IACUC_MODIFY_PROTOCOL_SUBMISSION_UNAVAILABLE = "modifyIacucProtocolSubmissionUnavailable";
    public static final String IACUC_PROTOCOL_TABLE = "iacucProtocolTable"; 
    public static final String IACUC_PROTOCOL_TABLE_UNAVAILABLE = "iacucProtocolTableUnavailable"; 
    public static final String IACUC_PROTOCOL_APPROVE_OTHER = "iacucProtocolApproveOther"; 
    public static final String IACUC_PROTOCOL_MANAGE_NOTES = "iacucProtocolManageNotes";
    public static final String IACUC_PROTOCOL_MANAGE_NOTES_UNAVAILABLE = "iacucProtocolManageNotesUnavailable";
    public static final String IACUC_ABANDON_PROTOCOL = "iacucProtocolAbandon"; 
    public static final String IACUC_ABANDON_PROTOCOL_UNAVAILABLE = "iacucProtocolAbandonUnavailable"; 
    public static final String IACUC_PROTOCOL_DESIGNATED_MEMBER_APPROVAL = "iacucProtocolDesignatedApproval";  
    public static final String IACUC_PROTOCOL_DESIGNATED_MEMBER_APPROVAL_UNAVAILABLE = "iacucProtocolDesignatedApprovalUnavailable"; 
    public static final String IACUC_PROTOCOL_HOLD = "iacucProtocolHold"; 
    public static final String IACUC_PROTOCOL_HOLD_UNAVAILABLE = "iacucProtocolHoldUnavailable"; 
    public static final String IACUC_PROTOCOL_LIFT_HOLD = "iacucProtocolLiftHold"; 
    public static final String IACUC_PROTOCOL_LIFT_HOLD_UNAVAILABLE = "iacucProtocolLiftHoldUnavailable"; 
    public static final String IACUC_PROTOCOL_REQUEST_LIFT_HOLD = "iacucProtocolRequestLiftHold"; 
    public static final String IACUC_PROTOCOL_REQUEST_LIFT_HOLD_UNAVAILABLE = "iacucProtocolRequestLiftHoldUnavailable"; 
    public static final String IACUC_PROTOCOL_DEACTIVATE = "iacucProtocolDeactivate"; 
    public static final String IACUC_PROTOCOL_DEACTIVATE_UNAVAILABLE = "iacucProtocolDeactivateUnavailable"; 
    public static final String IACUC_PROTOCOL_REQUEST_DEACTIVATE = "iacucProtocolRequestDeactivate"; 
    public static final String IACUC_PROTOCOL_REQUEST_DEACTIVATE_UNAVAILABLE = "iacucProtocolRequestDeactivateUnavailable"; 
    public static final String MAINTAIN_IACUC_PROTOCOL_ONLINEREVIEWS = "maintainIacucProtocolOnlineReviews";
    public static final String CREATE_IACUC_PROTOCOL_ONLINEREVIEW = "createIacucProtocolOnlineReview";
    public static final String MODIFY_IACUC_PROTOCOL_ONLINEREVIEW = "modifyIacucProtocolOnlineReview";
    public static final String VIEW_IACUC_PROTOCOL_ONLINEREVIEW = "viewIacucProtocolOnlineReview";
    public static final String MODIFY_IACUC_PROTOCOL_ONLINEREVIEW_TYPE = "editIacucProtocolOnlineReviewType";
    public static final String MODIFY_IACUC_PROTOCOL_ONLINEREVIEW_DETERMINATION = "editIacucProtocolOnlineReviewDetermination";
    
    public static final String IACUC_WITHDRAW_SUBMISSION = "iacucWithdrawSubmission";
    public static final String IACUC_WITHDRAW_SUBMISSION_UNAVAILABLE = "iacucWithdrawSubmissionUnavailable";
    
    /**
     * Generic IACUC Action task
     */
    public static final String GENERIC_IACUC_PROTOCOL_ACTION = "iacucGenericProtocolAction";
    public static final String GENERIC_IACUC_PROTOCOL_ACTION_UNAVAILABLE = "iacucGenericProtocolActionUnavailable";
    
    /* Research area task */
    public static final String MAINTAIN_RESEARCH_AREAS = "maintainResearchAreas";
    public static final String MAINTAIN_IACUC_RESEARCH_AREAS = "maintainIacucResearchAreas";
    
    /* COI Undisclosed events */
    public static final String VIEW_COI_UNDISCLOSED_EVENTS = "viewCoiUndisclosedEvents";
}
