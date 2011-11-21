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
 * Each task that a user can execute and which requires authorization is
 * enumerated here.  It must correspond to the values in the SpringBeans.xml.
 */
public interface TaskName {
    /*
     * Application Tasks.
     */
    public static final String CREATE_PROPOSAL = "createProposal";
    public static final String CREATE_PROTOCOL = "createProtocol";
    public static final String CREATE_AWARD = "createAward";
    public static final String CREATE_TAMD = "createTimeAndMoney";
    
    /*
     * Proposal Tasks.
     */
    public static final String MODIFY_PROPOSAL = "modifyProposal";
    public static final String VIEW_PROPOSAL = "viewProposal";
    public static final String PRINT_PROPOSAL = "printProposal";
    public static final String SUBMIT_TO_SPONSOR = "submitToSponsor";
    public static final String ADD_BUDGET = "addBudget";
    public static final String OPEN_BUDGETS = "openBudgets";
    public static final String MODIFY_PROPOSAL_ROLES = "modifyProposalRoles";
    public static final String ADD_NARRATIVE = "addNarrative";
    public static final String CERTIFY = "certify";
    public static final String ALTER_PROPOSAL_DATA = "alterProposalData";
    public static final String SHOW_ALTER_PROPOSAL_DATA = "showAlterProposalData";
    public static final String SUBMIT_TO_WORKFLOW = "submitToWorkflow";
    public static final String MAINTAIN_PROPOSAL_HIERARCHY = "maintainProposalHierarchy";
    public static final String PROPOSAL_HIERARCHY_CHILD_WORKFLOW_ACTION="hierarchyChildWorkflowAction";
    public static final String PROPOSAL_HIERARCHY_CHILD_ACKNOWLEDGE_ACTION="proposal.hierarchyChildAcknowledgeAction";
    public static final String PROPOSAL_ADD_NOTE_ATTACHMENT = "proposalAddNoteAttachment";
    public static final String ANSWER_PROPOSAL_QUESTIONNAIRE = "answerProposalQuestionnaire";
    public static final String DELETE_PROPOSAL = "deleteProposal";
    
    /*
     * Narrative Tasks.
     */
    public static final String MODIFY_NARRATIVE_RIGHTS = "modifyNarrativeRights";
    public static final String DOWNLOAD_NARRATIVE = "downloadNarrative";
    public static final String DELETE_NARRATIVE = "deleteNarrative";
    public static final String REPLACE_NARRATIVE = "replaceNarrative";
    
    /*
     * Budget Tasks.
     */
    public static final String MODIFY_BUDGET = "modifyBudget";
    public static final String VIEW_BUDGET = "viewBudget"; 
    public static final String MODIFY_PROPOSAL_BUDGET = "modifyProposalBudget";
    public static final String MODIFY_PROPOSAL_RATE = "modifyProposalBudgetRates";
    
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
    public static final String PROTOCOL_AMEND_RENEW_DELETE = "protocolAmendRenewDelete";
    public static final String PROTOCOL_AMEND_RENEW_DELETE_UNAVAILABLE = "protocolAmendRenewDeleteUnavailable";
    public static final String ASSIGN_TO_AGENDA = "protocolAssignToAgenda";
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
    
    public static final String ANSWER_PROTOCOL_QUESTIONNAIRE = "answerProtocolQuestionnaire";
    public static final String APPROVE_AWARD_BUDGET = "approveAwardBudget";
    public static final String DISAPPROVE_AWARD_BUDGET = "disapproveAwardBudget";
    public static final String POST_AWARD_BUDGET = "postAwardBudget";
    public static final String TOGGLE_AWARD_BUDGET_STATUS = "toggleAwardBudgetStatus";

    /*
     * COI tasks
     */
    public static final String CREATE_COI_DISCLOSURE = "createCoiDisclosure";
    public static final String MODIFY_COI_DISCLOSURE = "modifyCoiDisclosure";
    public static final String VIEW_COI_DISCLOSURE = "viewCoiDisclosure";
    
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
}
