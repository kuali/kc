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
    
    /*
     * Protocol Tasks.
     */
    public static final String MODIFY_PROTOCOL = "modifyProtocol";
    public static final String VIEW_PROTOCOL = "viewProtocol";
    public static final String MODIFY_PROTOCOL_ROLES = "modifyProtocolRoles";
    public static final String ADD_PROTOCOL_NOTES = "addProtocolNotes";
    public static final String CREATE_PROTOCOL_AMMENDMENT = "createAmendment";
    public static final String CREATE_PROTOCOL_RENEWAL = "createRenewal";
    public static final String MODIFY_PROTOCOL_BILLABLE = "modifyBillable";
    public static final String SUBMIT_PROTOCOL = "submitProtocol";
    public static final String MODIFY_PROTOCOL_GENERAL_INFO = "modifyProtocolGeneralInfo";
    public static final String MODIFY_PROTOCOL_FUNDING_SOURCE = "modifyProtocolFundingSource";
    public static final String MODIFY_PROTOCOL_REFERENCES = "modifyProtocolReferences";
    public static final String MODIFY_PROTOCOL_ORGANIZATIONS = "modifyProtocolOrganizations";
    public static final String MODIFY_PROTOCOL_SUBJECTS = "modifyProtocolSubjects";
    public static final String MODIFY_PROTOCOL_AREAS_OF_RESEARCH = "modifyProtocolAreasOfResearch";
    public static final String MODIFY_PROTOCOL_ATTACHMENTS = "modifyProtocolAttachments";
    public static final String MODIFY_PROTOCOL_NOTEPADS = "modifyProtocolNotepads";
    public static final String MODIFY_PROTOCOL_SPECIAL_REVIEW = "modifyProtocolSpecialReview";
    public static final String MODIFY_PROTOCOL_PERSONNEL = "modifyProtocolPersonnel";
    public static final String MODIFY_PROTOCOL_OTHERS = "modifyProtocolOthers";
    public static final String NOTIFY_IRB = "protocolNotifyIrb";
    public static final String PROTOCOL_WITHDRAW = "protocolWithdraw";
    public static final String PROTOCOL_REQUEST_CLOSE = "protocolRequestClose";
    public static final String PROTOCOL_REQUEST_SUSPENSION = "protocolRequestSuspension";
    public static final String PROTOCOL_REQUEST_CLOSE_ENROLLMENT = "protocolRequestCloseEnrollment";
    public static final String PROTOCOL_REQUEST_REOPEN_ENROLLMENT = "protocolRequestReOpenEnrollment";
    public static final String PROTOCOL_REQUEST_DATA_ANALYSIS = "protocolRequestDataAnalysis";
    public static final String PROTOCOL_REQUEST_TERMINATE = "protocolRequestTerminate";
    public static final String PROTOCOL_AMEND_RENEW_DELETE = "protocolAmendRenewDelete";
    public static final String ASSIGN_TO_AGENDA = "protocolAssignToAgenda";
    public static final String ASSIGN_TO_COMMITTEE_SCHEDULE = "protocolAssignToCmtSched";
    public static final String ASSIGN_REVIEWERS = "protocolAssignReviewers";
    public static final String GRANT_EXEMPTION = "protocolGrantExemption";
    public static final String VIEW_RESTRICTED_NOTES = "viewRestrictedNotes";
    public static final String EXPEDITE_APPROVAL = "protocolExpediteApproval";
    public static final String APPROVE_PROTOCOL = "protocolApprove";
    public static final String REOPEN_PROTOCOL = "protocolReopen";
    public static final String CLOSE_ENROLLMENT_PROTOCOL = "protocolCloseEnrollment";
    public static final String SUSPEND_PROTOCOL = "protocolSuspend";
    public static final String SUSPEND_PROTOCOL_BY_DSMB = "protocolSuspendByDsmb";
    public static final String CLOSE_PROTOCOL = "protocolClose";
    public static final String EXPIRE_PROTOCOL = "protocolExpire";
    public static final String TERMINATE_PROTOCOL = "protocolTerminate";
    public static final String PERMIT_DATA_ANALYSIS = "protocolPermitDataAnalysis";
    public static final String PROTOCOL_ADMIN_CORRECTION = "protocolAdminCorrection";
    public static final String PROTOCOL_UNDO_LAST_ACTION = "protocolUndoLastAction";
    public static final String RECORD_COMMITTEE_DECISION = "protocolRecordCommitteeDecision";
    public static final String ENTER_RISK_LEVEL = "protocolEnterRiskLevel";
    
    /**
     * Generic Action task
     */
    public static final String GENERIC_PROTOCOL_ACTION = "genericProtocolAction";
    
    /*
     * Committee Tasks.
     */
    public static final String ADD_COMMITTEE = "addCommittee";
    public static final String MODIFY_COMMITTEE = "modifyCommittee";
    public static final String VIEW_COMMITTEE = "viewCommittee";
    public static final String MODIFY_SCHEDULE = "modifySchedule";
    public static final String VIEW_SCHEDULE = "viewSchedule";
    
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

}
