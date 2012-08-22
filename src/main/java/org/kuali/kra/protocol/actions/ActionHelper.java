/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.protocol.actions;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.CoeusSubModule;
import org.kuali.kra.common.committee.bo.CommonCommitteeSchedule;
import org.kuali.kra.common.committee.meeting.CommitteeScheduleMinute;
import org.kuali.kra.common.committee.meeting.ProtocolVoteAbstainee;
import org.kuali.kra.common.committee.meeting.ProtocolVoteRecused;
import org.kuali.kra.common.committee.service.CommonCommitteeScheduleService;
import org.kuali.kra.common.committee.service.CommonCommitteeService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.ProtocolDocument;
import org.kuali.kra.protocol.ProtocolForm;
import org.kuali.kra.protocol.ProtocolVersionService;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewService;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewal;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendmentBean;
import org.kuali.kra.protocol.actions.approve.ProtocolApproveBean;
import org.kuali.kra.protocol.actions.assignagenda.ProtocolAssignToAgendaBean;
import org.kuali.kra.protocol.actions.assignreviewers.ProtocolAssignReviewersBean;
import org.kuali.kra.protocol.actions.correction.AdminCorrectionBean;
import org.kuali.kra.protocol.actions.decision.CommitteeDecision;
import org.kuali.kra.protocol.actions.decision.CommitteeDecisionService;
import org.kuali.kra.protocol.actions.delete.ProtocolDeleteBean;
import org.kuali.kra.protocol.actions.followup.FollowupActionService;
import org.kuali.kra.protocol.actions.genericactions.ProtocolGenericActionBean;
import org.kuali.kra.protocol.actions.notifycommittee.ProtocolNotifyCommitteeBean;
import org.kuali.kra.protocol.actions.print.ProtocolQuestionnairePrintingService;
import org.kuali.kra.protocol.actions.print.ProtocolSummaryPrintOptions;
import org.kuali.kra.protocol.actions.print.QuestionnairePrintOption;
import org.kuali.kra.protocol.actions.request.ProtocolRequestBean;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsService;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewer;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmission;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmitAction;
import org.kuali.kra.protocol.actions.submit.ValidProtocolActionAction;
import org.kuali.kra.protocol.actions.undo.UndoLastActionBean;
import org.kuali.kra.protocol.actions.withdraw.ProtocolAdministrativelyIncompleteBean;
import org.kuali.kra.protocol.actions.withdraw.ProtocolAdministrativelyWithdrawBean;
import org.kuali.kra.protocol.actions.withdraw.ProtocolWithdrawBean;
import org.kuali.kra.protocol.auth.ProtocolTask;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondence;
import org.kuali.kra.protocol.onlinereview.ProtocolReviewAttachment;
import org.kuali.kra.protocol.questionnaire.ProtocolModuleQuestionnaireBean;
import org.kuali.kra.protocol.summary.ProtocolSummary;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;
import org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.kra.util.DateUtils;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

/**
 * The form helper class for the Protocol Actions tab.
 */
@SuppressWarnings("serial")
public abstract class ActionHelper implements Serializable {

    protected static final long ONE_DAY = 1000L * 60L * 60L * 24L;
    protected static final String NAMESPACE = "KC-UNT";

// TODO *********commented the code below during IACUC refactoring*********     
//    private static final List<String> ACTION_TYPE_SUBMISSION_DOC;  
//    static {
//        final List<String> codes = new ArrayList<String>();     
//        codes.add(ProtocolActionType.NOTIFY_IRB);
//        codes.add(ProtocolActionType.REQUEST_TO_CLOSE);
//        codes.add(ProtocolActionType.REQUEST_FOR_DATA_ANALYSIS_ONLY);
//        codes.add(ProtocolActionType.REQUEST_FOR_SUSPENSION);
//        codes.add(ProtocolActionType.REQUEST_TO_REOPEN_ENROLLMENT);
//        codes.add(ProtocolActionType.REQUEST_FOR_TERMINATION);
//        codes.add(ProtocolActionType.REQUEST_TO_CLOSE_ENROLLMENT);
//        ACTION_TYPE_SUBMISSION_DOC = codes;
//    }

    /**
     * Each Helper must contain a reference to its document form
     * so that it can access the document.
     */
    protected ProtocolForm form;
    
    protected boolean canSubmitProtocol = false;
    protected boolean canSubmitProtocolUnavailable = false;
    protected String submissionConstraint;
    
    protected boolean canReturnToPI = false;
    protected boolean canReturnToPIUnavailable = false;
    protected boolean canCreateAmendment = false;
    protected boolean canCreateAmendmentUnavailable = false;
    protected boolean canModifyAmendmentSections = false;
    protected boolean canModifyAmendmentSectionsUnavailable = false;
    protected boolean canCreateRenewal = false;
    protected boolean canCreateRenewalUnavailable = false;
//TODO: Demote this to IRB
//    protected boolean canNotifyIrb = false;
//    protected boolean canNotifyIrbUnavailable = false;
    protected boolean canNotifyCommittee = false;
    protected boolean canNotifyCommitteeUnavailable = false;
    protected boolean canWithdraw = false;
    protected boolean canWithdrawUnavailable = false;
    protected boolean canRequestClose = false;
    protected boolean canRequestCloseUnavailable = false;
    protected boolean canRequestSuspension = false;
    protected boolean canRequestSuspensionUnavailable = false;
//TODO: Demote this to IRB
//    protected boolean canRequestCloseEnrollment = false;
//    protected boolean canRequestCloseEnrollmentUnavailable = false;
//    protected boolean canRequestReOpenEnrollment = false;
//    protected boolean canRequestReOpenEnrollmentUnavailable = false;

//TODO: Demote this to IRB
//    protected boolean canRequestDataAnalysis = false;
//    protected boolean canRequestDataAnalysisUnavailable = false;
    protected boolean canRequestTerminate = false;
    protected boolean canRequestTerminateUnavailable = false;
    protected boolean canDeleteProtocolAmendRenew = false;
    protected boolean canDeleteProtocolAmendRenewUnavailable = false;
    protected boolean canAssignToAgenda = false;
    protected boolean canAssignToAgendaUnavailable = false;
    protected boolean canAssignCmtSched = false;
    protected boolean canAssignCmtSchedUnavailable = false;
    protected boolean canRemoveFromToAgenda = false;
    protected boolean canRemoveFromAgendaUnavailable = false;
    protected boolean canAssignReviewers = false;
    protected boolean canAssignReviewersCmtSel = false;
    protected boolean canAssignReviewersUnavailable = false;
//TODO: Demote this to IRB
//    protected boolean canGrantExemption = false;
//    protected boolean canGrantExemptionUnavailable = false;
    protected boolean canApproveFull = false;
    protected boolean canApproveFullUnavailable = false;

//    protected boolean canApproveExpeditedUnavailable = false;
    protected boolean canApproveResponse = false;
    protected boolean canApproveResponseUnavailable = false;
    protected boolean canDisapprove = false;
    protected boolean canDisapproveUnavailable = false;
    protected boolean canReturnForSMR = false;
    protected boolean canReturnForSMRUnavailable = false;
    protected boolean canReturnForSRR = false;
    protected boolean canReturnForSRRUnavailable = false;
//    protected boolean canReopenEnrollment = false;
//    protected boolean canReopenEnrollmentUnavailable = false;
//    protected boolean canCloseEnrollment = false;
//    protected boolean canCloseEnrollmentUnavailable = false;
    protected boolean canSuspend = false;
    protected boolean canSuspendUnavailable = false;
//TODO: Demote this to IRB
//    protected boolean canSuspendByDsmb = false;
//    protected boolean canSuspendByDsmbUnavailable = false;
    protected boolean canClose = false;
    protected boolean canCloseUnavailable = false;
    protected boolean canExpire = false;
    protected boolean canExpireUnavailable = false;
    protected boolean canTerminate = false;
    protected boolean canTerminateUnavailable = false;
//    protected boolean canPermitDataAnalysis = false;
//    protected boolean canPermitDataAnalysisUnavailable = false;
    protected boolean canEnterRiskLevel = false;
    protected boolean canMakeAdminCorrection = false;
    protected boolean canMakeAdminCorrectionUnavailable = false;
    protected boolean canRecordCommitteeDecision = false;
    protected boolean canRecordCommitteeDecisionUnavailable = false;
    protected boolean canUndoLastAction = false;
    protected boolean canUndoLastActionUnavailable = false;
    protected boolean canModifyProtocolSubmission = false;
    protected boolean canModifyProtocolSubmissionUnavailable = false;
//TODO: Demote this to IRB
//    protected boolean canIrbAcknowledgement = false;
//    protected boolean canIrbAcknowledgementUnavailable = false;

//    protected boolean canDefer = false;
//    protected boolean canDeferUnavailable = false;
//    protected boolean canReviewNotRequired = false;
//    protected boolean canReviewNotRequiredUnavailable = false;
    protected boolean canManageReviewComments = false;
    protected boolean canManageReviewCommentsUnavailable = false;
    protected boolean canApproveOther = false;
    protected boolean canManageNotes = false;
    protected boolean canManageNotesUnavailable = false;
    protected boolean canAbandon = false;

    protected List<? extends ValidProtocolActionAction> followupActionActions;
    
    protected boolean canViewOnlineReviewers;
    protected boolean canViewOnlineReviewerComments;
    
    protected boolean canAddCloseReviewerComments;

//TODO: Demote this to IRB
//    protected boolean canAddCloseEnrollmentReviewerComments;
//    protected boolean canAddDataAnalysisReviewerComments;
//    protected boolean canAddReopenEnrollmentReviewerComments;

    protected boolean canAddSuspendReviewerComments;
    protected boolean canAddTerminateReviewerComments;    
    
    // new addition that needs to be backfitted to IRB as well
    private boolean canPerformAdminDetermination;
    private boolean canPerformAdminDeterminationUnavailable;
    private boolean canAdministrativelyApprove;
    private boolean canAdministrativelyApproveUnavailable;
    private boolean canAdministrativelyMarkIncomplete;
    private boolean canAdministrativelyMarkIncompleteUnavailable;
    private boolean canAdministrativelyWithdraw;
    private boolean canAdministrativelyWithdrawUnavailable;
    
    
    
    protected ProtocolSubmitAction protocolSubmitAction;
    protected ProtocolWithdrawBean protocolWithdrawBean;
    protected ProtocolRequestBean protocolCloseRequestBean;
    protected ProtocolRequestBean protocolSuspendRequestBean;
    protected ProtocolRequestBean protocolCloseEnrollmentRequestBean;
    protected ProtocolRequestBean protocolReOpenEnrollmentRequestBean;
    protected ProtocolRequestBean protocolDataAnalysisRequestBean;
    protected ProtocolRequestBean protocolTerminateRequestBean;
    protected ProtocolNotifyCommitteeBean protocolNotifyCommitteeBean;
    protected ProtocolAmendmentBean protocolAmendmentBean;
    protected ProtocolAmendmentBean protocolRenewAmendmentBean;
    protected ProtocolDeleteBean protocolDeleteBean;
    protected ProtocolAssignToAgendaBean assignToAgendaBean;
    protected ProtocolAssignReviewersBean protocolAssignReviewersBean;
//    protected ProtocolGrantExemptionBean protocolGrantExemptionBean;
    private ProtocolApproveBean protocolFullApprovalBean;
    protected ProtocolApproveBean protocolExpeditedApprovalBean;
    protected ProtocolApproveBean protocolResponseApprovalBean;
    
    private ProtocolApproveBean protocolAdminApprovalBean;
    private ProtocolAdministrativelyWithdrawBean protocolAdminWithdrawBean;
    private ProtocolAdministrativelyIncompleteBean protocolAdminIncompleteBean;
    
    protected ProtocolGenericActionBean protocolDisapproveBean;
    protected ProtocolGenericActionBean protocolSMRBean;
    protected ProtocolGenericActionBean protocolSRRBean;
    protected ProtocolGenericActionBean protocolReopenEnrollmentBean;
    protected ProtocolGenericActionBean protocolCloseEnrollmentBean;
    protected ProtocolGenericActionBean protocolSuspendBean;
    protected ProtocolGenericActionBean protocolSuspendByDsmbBean;
    protected ProtocolGenericActionBean protocolCloseBean;
    protected ProtocolGenericActionBean protocolExpireBean;
    protected ProtocolGenericActionBean protocolTerminateBean;
    protected ProtocolGenericActionBean protocolPermitDataAnalysisBean;
    protected ProtocolGenericActionBean protocolIrbAcknowledgementBean;
    protected ProtocolGenericActionBean protocolReturnToPIBean;
    protected AdminCorrectionBean protocolAdminCorrectionBean;
    protected UndoLastActionBean undoLastActionBean;
    protected CommitteeDecision<?> committeeDecision;
    protected ProtocolGenericActionBean protocolDeferBean;
//    protected ProtocolReviewNotRequiredBean protocolReviewNotRequiredBean;
    protected ProtocolGenericActionBean protocolManageReviewCommentsBean;
    protected ProtocolGenericActionBean protocolAbandonBean;

    protected String currentTaskName = "";
    protected boolean prevDisabled;
    protected boolean nextDisabled;
    protected transient ParameterService parameterService;
    protected transient TaskAuthorizationService taskAuthorizationService;
    protected transient ProtocolAmendRenewService protocolAmendRenewService;
    protected transient ProtocolVersionService protocolVersionService;
//    protected transient ProtocolSubmitActionService protocolSubmitActionService;
//    protected transient ProtocolActionService protocolActionService;
    protected boolean hasAmendments;
    protected boolean hasRenewals;
    protected boolean submissionHasNoAmendmentDetails;
//    /*
//     * Identifies the protocol "document" to print.
//     */
//    protected String printTag;
//    
//
    protected Boolean summaryReport;
    protected Boolean fullReport;
    protected Boolean historyReport;
    protected Boolean reviewCommentsReport;
    
    protected ProtocolSummary protocolSummary;
    protected ProtocolSummary prevProtocolSummary;
    protected int currentSequenceNumber = -1;
    
    protected String selectedHistoryItem;
    protected Date filteredHistoryStartDate;
    protected Date filteredHistoryEndDate;
    
    // additional properties for Submission Details
    protected ProtocolSubmission selectedSubmission;
    protected List<CommitteeScheduleMinute> reviewComments;        
    protected List<? extends ProtocolReviewAttachment> reviewAttachments;        
    protected List<ProtocolVoteAbstainee> abstainees;        
    protected List<ProtocolVoteRecused> recusers;        
    protected List<ProtocolReviewer> protocolReviewers;        
    protected int currentSubmissionNumber;
    protected String renewalSummary;
    
    // indicator for whether there is submission questionnaire answer exist.
    // ie, questionnaire has been saved for a request/notify irb action
    protected boolean submissionQuestionnaireExist;
    // check if there is submission questionnaire to answer
    protected boolean toAnswerSubmissionQuestionnaire;

    protected transient CommonCommitteeScheduleService committeeScheduleService;
    protected transient KcPersonService kcPersonService;
    protected transient KraAuthorizationService kraAuthorizationService;
    protected transient BusinessObjectService businessObjectService;
    protected transient FollowupActionService<?> followupActionService;
    
//    protected Map<String, ProtocolRequestBean>  actionTypeRequestBeanMap = new HashMap<String, ProtocolRequestBean>();
    
    protected Map<String,Boolean> followupActionMap = new HashMap<String,Boolean>();
    
    protected Map<String, ProtocolActionBean> actionBeanTaskMap = new HashMap<String, ProtocolActionBean>();    
    // protocol print
    protected ProtocolSummaryPrintOptions protocolSummaryPrintOptions;
    protected ProtocolSummaryPrintOptions protocolPrintOption = new ProtocolSummaryPrintOptions();
    protected List<QuestionnairePrintOption> questionnairesToPrints;
    // flag if versioned protocol questionnaire exist
    protected boolean summaryQuestionnaireExist;
    protected boolean hideReviewerName;
    protected boolean hideSubmissionReviewerName;
    protected boolean hideReviewerNameForAttachment;
    protected ProtocolCorrespondence protocolCorrespondence;
    
    
    
   

    /**
     * Constructs an ActionHelper.
     * @param form the protocol form
     * @throws Exception 
     */
    public ActionHelper(ProtocolForm form) throws Exception {
        this.form = form;

        protocolSubmitAction = getNewProtocolSubmitActionInstanceHook(this);
        protocolWithdrawBean = getNewProtocolWithdrawBeanInstanceHook(this);

        createAmendmentBean();
        
        //protocolAmendmentBean = getNewProtocolAmendmentBeanInstanceHook(this);
        //protocolRenewAmendmentBean = getNewProtocolAmendmentBeanInstanceHook(this);
        
        
// TODO *********commented the code below during IACUC refactoring*********            
//        protocolNotifyIrbBean = new ProtocolNotifyIrbBean(this);
//        // setting the attachment here so new files can be attached to newActionAttachment
//        protocolNotifyIrbBean.setNewActionAttachment(new ProtocolActionAttachment());
        
        protocolNotifyCommitteeBean = getNewProtocolNotifyCommitteeBeanInstanceHook(this);

                
        protocolDeleteBean = getNewProtocolDeleteBeanInstanceHook(this);      
        assignToAgendaBean = getNewProtocolAssignToAgendaBeanInstanceHook(this);         
        assignToAgendaBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        
// TODO *********commented the code below during IACUC refactoring*********         
//        assignCmtSchedBean = new ProtocolAssignCmtSchedBean(this);
//        assignCmtSchedBean.init();
//        protocolAssignReviewersBean = new ProtocolAssignReviewersBean(this);
//        protocolGrantExemptionBean = new ProtocolGrantExemptionBean(this);
//        protocolGrantExemptionBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        
        
        protocolFullApprovalBean = buildProtocolApproveBean(getFullApprovalProtocolActionTypeHook(), Constants.PROTOCOL_FULL_APPROVAL_ACTION_PROPERTY_KEY);

// TODO *********commented the code below during IACUC refactoring*********         
//        protocolExpeditedApprovalBean = buildProtocolApproveBean(ProtocolActionType.EXPEDITE_APPROVAL, 
//                Constants.PROTOCOL_EXPEDITED_APPROVAL_ACTION_PROPERTY_KEY);
//        protocolResponseApprovalBean = buildProtocolApproveBean(ProtocolActionType.RESPONSE_APPROVAL, 
//                Constants.PROTOCOL_RESPONSE_APPROVAL_ACTION_PROPERTY_KEY);
            
        protocolAdminApprovalBean = buildProtocolApproveBean(getAdminApprovalProtocolActionTypeHook(), Constants.PROTOCOL_ADMIN_APPROVAL_ACTION_PROPERTY_KEY);
        protocolAdminWithdrawBean = getNewProtocolAdminWithdrawBeanInstanceHook(this);
        protocolAdminIncompleteBean = getNewProtocolAdminIncompleteBeanInstanceHook(this);
         
        protocolDisapproveBean = buildProtocolGenericActionBean(getDisapprovedProtocolActionTypeHook(), Constants.PROTOCOL_DISAPPROVE_ACTION_PROPERTY_KEY);
        protocolSMRBean = buildProtocolGenericActionBean(getSMRProtocolActionTypeHook(), Constants.PROTOCOL_SMR_ACTION_PROPERTY_KEY);  
        protocolSRRBean = buildProtocolGenericActionBean(getSRRProtocolActionTypeHook(), Constants.PROTOCOL_SRR_ACTION_PROPERTY_KEY);
        protocolReturnToPIBean = buildProtocolGenericActionBean(getReturnToPIActionTypeHook(), Constants.PROTOCOL_RETURN_TO_PI_PROPERTY_KEY);
        
// TODO *********commented the code below during IACUC refactoring*********         
//        protocolReopenEnrollmentBean = buildProtocolGenericActionBean(ProtocolActionType.REOPEN_ENROLLMENT, 
//                Constants.PROTOCOL_REOPEN_ENROLLMENT_ACTION_PROPERTY_KEY);
//        protocolCloseEnrollmentBean = buildProtocolGenericActionBean(ProtocolActionType.CLOSED_FOR_ENROLLMENT, 
//                Constants.PROTOCOL_CLOSE_ENROLLMENT_ACTION_PROPERTY_KEY);
        protocolSuspendBean = buildProtocolGenericActionBean(getSuspendKeyHook(), Constants.PROTOCOL_SUSPEND_ACTION_PROPERTY_KEY);
//        protocolSuspendByDsmbBean = buildProtocolGenericActionBean(ProtocolActionType.SUSPENDED_BY_DSMB, 
//                Constants.PROTOCOL_SUSPEND_BY_DSMB_ACTION_PROPERTY_KEY);
//        protocolCloseBean = buildProtocolGenericActionBean(ProtocolActionType.CLOSED_ADMINISTRATIVELY_CLOSED, 
//                Constants.PROTOCOL_CLOSE_ACTION_PROPERTY_KEY);
        protocolExpireBean = buildProtocolGenericActionBean(getExpireKeyHook(), Constants.PROTOCOL_EXPIRE_ACTION_PROPERTY_KEY);
        protocolTerminateBean = buildProtocolGenericActionBean(getTerminateKeyHook(), Constants.PROTOCOL_TERMINATE_ACTION_PROPERTY_KEY);
//        protocolPermitDataAnalysisBean = buildProtocolGenericActionBean(ProtocolActionType.DATA_ANALYSIS_ONLY, 
//                Constants.PROTOCOL_PERMIT_DATA_ANALYSIS_ACTION_PROPERTY_KEY);
//        protocolIrbAcknowledgementBean = buildProtocolGenericActionBean(ProtocolActionType.IRB_ACKNOWLEDGEMENT, 
//                Constants.PROTOCOL_IRB_ACKNOWLEDGEMENT_ACTION_PROPERTY_KEY);
        
        protocolAbandonBean = buildProtocolGenericActionBean(getAbandonActionTypeHook(), getAbandonPropertyKeyHook());//buildProtocolAbandonBeanHook();
        
 
// TODO *********commented the code below during IACUC refactoring*********         
        protocolAdminCorrectionBean = createAdminCorrectionBean();
        undoLastActionBean = getNewUndoLastActionBeanInstanceHook();
        
        committeeDecision = getNewCommitteeDecisionInstanceHook(this);
        committeeDecision.init();
        committeeDecision.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());

////        committeeDecision.getReviewCommentsBean().setHideReviewerName(getReviewCommentsService().setHideReviewerName(committeeDecision.getReviewCommentsBean().getReviewComments())); 
//        protocolModifySubmissionBean = new ProtocolModifySubmissionBean(this);
//        protocolDeferBean = buildProtocolGenericActionBean(ProtocolActionType.DEFERRED, 
//                Constants.PROTOCOL_DEFER_ACTION_PROPERTY_KEY);
//        protocolReviewNotRequiredBean = new ProtocolReviewNotRequiredBean(this);
        
        
        protocolManageReviewCommentsBean = buildProtocolGenericActionBean(getProtocolActionTypeCodeForManageReviewCommentsHook(), 
                Constants.PROTOCOL_MANAGE_REVIEW_COMMENTS_KEY);
        getProtocol().getProtocolSubmission().refreshReferenceObject("reviewAttachments");
        protocolManageReviewCommentsBean.getReviewAttachmentsBean().setReviewAttachments(getProtocol().getProtocolSubmission().getReviewAttachments());
        if (CollectionUtils.isNotEmpty(protocolManageReviewCommentsBean.getReviewAttachmentsBean().getReviewAttachments())) {
            protocolManageReviewCommentsBean.getReviewAttachmentsBean().setHideReviewerName(getReviewerCommentsService().setHideReviewerName(protocolManageReviewCommentsBean.getReviewAttachmentsBean().getReviewAttachments()));
        }
        
        
//        protocolCloseRequestBean = new ProtocolRequestBean(this, ProtocolActionType.REQUEST_TO_CLOSE, 
//                ProtocolSubmissionType.REQUEST_TO_CLOSE, "protocolCloseRequestBean");
//        protocolSuspendRequestBean = new ProtocolRequestBean(this, ProtocolActionType.REQUEST_FOR_SUSPENSION, 
//                ProtocolSubmissionType.REQUEST_FOR_SUSPENSION, "protocolSuspendRequestBean");
//        protocolCloseEnrollmentRequestBean = new ProtocolRequestBean(this, ProtocolActionType.REQUEST_TO_CLOSE_ENROLLMENT, 
//                ProtocolSubmissionType.REQUEST_TO_CLOSE_ENROLLMENT, "protocolCloseEnrollmentRequestBean");
//        protocolReOpenEnrollmentRequestBean = new ProtocolRequestBean(this, ProtocolActionType.REQUEST_TO_REOPEN_ENROLLMENT,
//                ProtocolSubmissionType.REQUEST_TO_REOPEN_ENROLLMENT, "protocolReOpenEnrollmentRequestBean");
//        protocolDataAnalysisRequestBean = new ProtocolRequestBean(this, ProtocolActionType.REQUEST_FOR_DATA_ANALYSIS_ONLY,
//                ProtocolSubmissionType.REQUEST_FOR_DATA_ANALYSIS_ONLY, "protocolDataAnalysisRequestBean");
//        protocolTerminateRequestBean = new ProtocolRequestBean(this, ProtocolActionType.REQUEST_FOR_TERMINATION,
//                ProtocolSubmissionType.REQUEST_FOR_TERMINATION, "protocolTerminateRequestBean");
//        
        
        initActionBeanTaskMap();
        
// TODO *********commented the code below during IACUC refactoring*********         
//        
//        protocolSummaryPrintOptions = new ProtocolSummaryPrintOptions();
//        toAnswerSubmissionQuestionnaire = hasSubmissionQuestionnaire();
//        protocolPrintOption = new ProtocolSummaryPrintOptions();
//        initPrintQuestionnaire();
    }
    
    
    protected abstract String getSRRProtocolActionTypeHook();

    protected abstract String getSMRProtocolActionTypeHook();
    
    protected abstract String getReturnToPIActionTypeHook();

    protected abstract String getDisapprovedProtocolActionTypeHook();

    protected abstract String getProtocolActionTypeCodeForManageReviewCommentsHook();    
    
    protected abstract CommitteeDecision<?> getNewCommitteeDecisionInstanceHook(ActionHelper actionHelper);    

    protected abstract ProtocolAssignToAgendaBean getNewProtocolAssignToAgendaBeanInstanceHook(ActionHelper actionHelper);

    protected abstract ProtocolAdministrativelyWithdrawBean getNewProtocolAdminWithdrawBeanInstanceHook(ActionHelper actionHelper);
    
    protected abstract ProtocolAdministrativelyIncompleteBean getNewProtocolAdminIncompleteBeanInstanceHook(ActionHelper actionHelper);
    
    protected abstract String getAdminApprovalProtocolActionTypeHook();
    
    protected abstract String getFullApprovalProtocolActionTypeHook();

    protected abstract ProtocolWithdrawBean getNewProtocolWithdrawBeanInstanceHook(ActionHelper actionHelper);

    protected abstract ProtocolAmendmentBean getNewProtocolAmendmentBeanInstanceHook(ActionHelper actionHelper);
    
    protected abstract ProtocolNotifyCommitteeBean getNewProtocolNotifyCommitteeBeanInstanceHook(ActionHelper actionHelper);

    protected abstract ProtocolSubmitAction getNewProtocolSubmitActionInstanceHook(ActionHelper actionHelper);
    
    protected abstract ProtocolDeleteBean getNewProtocolDeleteBeanInstanceHook(ActionHelper actionHelper);

    protected abstract AdminCorrectionBean getNewAdminCorrectionBeanInstanceHook(ActionHelper actionHelper);
    
    protected abstract UndoLastActionBean getNewUndoLastActionBeanInstanceHook();
   

    /**
     * Initializes the mapping between the task names and the beans.  This is used to get the bean associated to the task name passed in from the tag file.
     * The reason TaskName (a text code) is used and ProtocolActionType (a number code) is not is because not every task is mapped to a ProtocolActionType.
     */
    protected void initActionBeanTaskMap() {
        
        actionBeanTaskMap.put(TaskName.PROTOCOL_ADMIN_CORRECTION, protocolAdminCorrectionBean);
        actionBeanTaskMap.put(TaskName.CREATE_PROTOCOL_AMMENDMENT, protocolAmendmentBean);
        actionBeanTaskMap.put(TaskName.CREATE_PROTOCOL_RENEWAL, protocolRenewAmendmentBean);
        
        actionBeanTaskMap.put(TaskName.APPROVE_PROTOCOL, protocolFullApprovalBean);
        
// TODO *********commented the code below during IACUC refactoring*********         
//        actionBeanTaskMap.put(TaskName.ASSIGN_TO_COMMITTEE_SCHEDULE, assignCmtSchedBean);
//        actionBeanTaskMap.put(TaskName.ASSIGN_REVIEWERS, protocolAssignReviewersBean);
        
        actionBeanTaskMap.put(TaskName.ASSIGN_TO_AGENDA, assignToAgendaBean);
        
// TODO *********commented the code below during IACUC refactoring*********         
//        actionBeanTaskMap.put(TaskName.CLOSE_PROTOCOL, protocolCloseBean);
//        actionBeanTaskMap.put(TaskName.CLOSE_ENROLLMENT_PROTOCOL, protocolCloseEnrollmentBean);
//        actionBeanTaskMap.put(TaskName.PROTOCOL_REQUEST_CLOSE_ENROLLMENT, protocolCloseEnrollmentRequestBean);
//        actionBeanTaskMap.put(TaskName.PROTOCOL_REQUEST_CLOSE, protocolCloseRequestBean);
        
        actionBeanTaskMap.put(TaskName.RECORD_COMMITTEE_DECISION, committeeDecision);
        
// TODO *********commented the code below during IACUC refactoring*********         
//        actionBeanTaskMap.put(TaskName.PERMIT_DATA_ANALYSIS, protocolPermitDataAnalysisBean);
//        actionBeanTaskMap.put(TaskName.PROTOCOL_REQUEST_DATA_ANALYSIS, protocolDataAnalysisRequestBean);
        
        actionBeanTaskMap.put(TaskName.PROTOCOL_AMEND_RENEW_DELETE, protocolDeleteBean);
 
// TODO *********commented the code below during IACUC refactoring*********         
//        actionBeanTaskMap.put(TaskName.DEFER_PROTOCOL, protocolDeferBean);
        
        
        actionBeanTaskMap.put(TaskName.DISAPPROVE_PROTOCOL, protocolDisapproveBean);
        
// TODO *********commented the code below during IACUC refactoring*********         
//        actionBeanTaskMap.put(TaskName.EXPEDITE_APPROVAL, protocolExpeditedApprovalBean);
        actionBeanTaskMap.put(TaskName.EXPIRE_PROTOCOL, protocolExpireBean);
//        actionBeanTaskMap.put(TaskName.GRANT_EXEMPTION, protocolGrantExemptionBean);
        
        actionBeanTaskMap.put(TaskName.PROTOCOL_MANAGE_REVIEW_COMMENTS, protocolManageReviewCommentsBean);

// TODO *********commented the code below during IACUC refactoring*********         
//        actionBeanTaskMap.put(TaskName.MODIFY_PROTOCOL_SUBMISSION, protocolModifySubmissionBean);
//        actionBeanTaskMap.put(TaskName.NOTIFY_IRB, protocolNotifyIrbBean);
//        actionBeanTaskMap.put(TaskName.NOTIFY_COMMITTEE, protocolNotifyCommitteeBean);
//        actionBeanTaskMap.put(TaskName.REOPEN_PROTOCOL, protocolReopenEnrollmentBean);
//        actionBeanTaskMap.put(TaskName.PROTOCOL_REQUEST_REOPEN_ENROLLMENT, protocolReOpenEnrollmentRequestBean);
//        actionBeanTaskMap.put(TaskName.RESPONSE_APPROVAL, protocolResponseApprovalBean);
//        actionBeanTaskMap.put(TaskName.PROTOCOL_REQUEST_CLOSE, protocolCloseRequestBean);
//        actionBeanTaskMap.put(TaskName.PROTOCOL_REQUEST_CLOSE_ENROLLMENT, protocolCloseEnrollmentRequestBean);
//        actionBeanTaskMap.put(TaskName.PROTOCOL_REQUEST_REOPEN_ENROLLMENT, protocolReOpenEnrollmentRequestBean);
//        actionBeanTaskMap.put(TaskName.PROTOCOL_REQUEST_DATA_ANALYSIS, protocolDataAnalysisRequestBean);
//        actionBeanTaskMap.put(TaskName.PROTOCOL_REQUEST_SUSPENSION, protocolSuspendRequestBean);
//        actionBeanTaskMap.put(TaskName.PROTOCOL_REQUEST_TERMINATE, protocolTerminateRequestBean);
//        actionBeanTaskMap.put(TaskName.PROTOCOL_REVIEW_NOT_REQUIRED, protocolReviewNotRequiredBean);
        
        actionBeanTaskMap.put(TaskName.RETURN_FOR_SMR, protocolSMRBean);
        actionBeanTaskMap.put(TaskName.RETURN_FOR_SRR, protocolSRRBean);
        actionBeanTaskMap.put(TaskName.RETURN_TO_PI_PROTOCOL, protocolReturnToPIBean);
         
        actionBeanTaskMap.put(TaskName.SUBMIT_PROTOCOL, protocolSubmitAction);
   
// TODO *********commented the code below during IACUC refactoring*********         
        actionBeanTaskMap.put(TaskName.SUSPEND_PROTOCOL, protocolSuspendBean);
//        actionBeanTaskMap.put(TaskName.SUSPEND_PROTOCOL_BY_DSMB, protocolSuspendByDsmbBean);
//        actionBeanTaskMap.put(TaskName.PROTOCOL_REQUEST_SUSPENSION, protocolSuspendRequestBean);
        actionBeanTaskMap.put(TaskName.TERMINATE_PROTOCOL, protocolTerminateBean);
//        actionBeanTaskMap.put(TaskName.PROTOCOL_REQUEST_TERMINATE, protocolTerminateRequestBean);
        actionBeanTaskMap.put(TaskName.PROTOCOL_UNDO_LAST_ACTION, undoLastActionBean);
        
        actionBeanTaskMap.put(TaskName.PROTOCOL_WITHDRAW, protocolWithdrawBean);
        
        actionBeanTaskMap.put(TaskName.ADMIN_APPROVE_PROTOCOL, protocolAdminApprovalBean);
        actionBeanTaskMap.put(TaskName.ADMIN_WITHDRAW_PROTOCOL, protocolAdminWithdrawBean);
        actionBeanTaskMap.put(TaskName.ADMIN_INCOMPLETE_PROTOCOL, protocolAdminIncompleteBean);
    }
    
    
        protected abstract String getAbandonActionTypeHook();
        
        protected abstract String getAbandonPropertyKeyHook();
        
        protected abstract String getExpireKeyHook();
        
        protected abstract String getTerminateKeyHook();
        
        protected abstract String getSuspendKeyHook();
               
        protected abstract ProtocolGenericActionBean buildProtocolGenericActionBean(String actionTypeCode, String errorPropertyKey);
        
        
// TODO *********commented the code below during IACUC refactoring********* 
// This method demoted to subclasses
//    /**
//     *     
//     * This method builds a ProtocolGenericActionBean.  A number of different beans
//     * in this object are of type ProtocolGenericActionBean, and all need to add
//     * reviewer comments.  This encapsulates that.
//     * @return a ProtocolGenericActionBean, and pre-populated with reviewer comments if any exist
//     */
//    protected ProtocolGenericActionBean buildProtocolGenericActionBean(String actionTypeCode, String errorPropertyKey) {
//        ProtocolGenericActionBean bean = new ProtocolGenericActionBean(this, errorPropertyKey);
//        
//        bean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
//        bean.getReviewCommentsBean().setHideReviewerName(getReviewCommentsService().setHideReviewerName(bean.getReviewCommentsBean().getReviewComments()));            
//        ProtocolAction protocolAction = findProtocolAction(actionTypeCode, getProtocol().getProtocolActions(), getProtocol().getProtocolSubmission());
//        if (protocolAction != null) {
//            bean.setComments(protocolAction.getComments());
//            bean.setActionDate(new Date(protocolAction.getActionDate().getTime()));
//        }
//        
//        return bean;
//    }
//    
        protected ReviewCommentsService getReviewCommentsService() {
            return KraServiceLocator.getService(getReviewCommentsServiceClassHook());
        }   
        
        protected abstract Class<? extends ReviewCommentsService> getReviewCommentsServiceClassHook();  

        
 
    protected ProtocolApproveBean buildProtocolApproveBean(String actionTypeCode, String errorPropertyKey) throws Exception {        
        ProtocolApproveBean bean = getNewProtocolApproveBeanInstanceHook(this, errorPropertyKey);       
        bean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        
        ProtocolAction protocolAction = findProtocolAction(actionTypeCode, getProtocol().getProtocolActions(), getProtocol().getProtocolSubmission());
        if (protocolAction != null) {
            bean.setComments(protocolAction.getComments());
            bean.setActionDate(new Date(protocolAction.getActionDate().getTime()));
        }
        bean.setApprovalDate(buildApprovalDate(getProtocol()));
        bean.setExpirationDate(buildExpirationDate(getProtocol(), bean.getApprovalDate()));
        return bean;
    }

        
    protected abstract ProtocolApproveBean getNewProtocolApproveBeanInstanceHook(ActionHelper actionHelper, String errorPropertyKey);

    /**
     * Builds an approval date, defaulting to the approval date from the protocol.
     * 
     * If the approval date from the protocol is null, or if the protocol is new or a renewal, then if the committee has scheduled a meeting to approve the 
     * protocol, sets to the scheduled approval date; otherwise, sets to the current date.
     * 
     * @param protocol
     * @return a non-null approval date
     */
    protected Date buildApprovalDate(Protocol protocol) {
        Date approvalDate = protocol.getApprovalDate();
        
        if (approvalDate == null || protocol.isNew() || protocol.isRenewal()) {
            CommonCommitteeSchedule committeeSchedule = protocol.getProtocolSubmission().getCommitteeSchedule();
            if (committeeSchedule != null) {
                approvalDate = committeeSchedule.getScheduledDate();
            } else {
                approvalDate = new Date(System.currentTimeMillis());
            }
        }
        
        return approvalDate;
    }
    
    /**
     * Builds an expiration date, defaulting to the expiration date from the protocol.  
     * 
     * If the expiration date from the protocol is null, or if the protocol is new or a renewal, creates an expiration date exactly one year ahead and one day 
     * less than the approval date.
     * 
     * @param protocol
     * @param approvalDate
     * @return a non-null expiration date
     */
    protected Date buildExpirationDate(Protocol protocol, Date approvalDate) {
        Date expirationDate = protocol.getExpirationDate();
        
        if (expirationDate == null || protocol.isNew() || protocol.isRenewal()) {
            java.util.Date newExpirationDate = DateUtils.addYears(approvalDate, 1);
            newExpirationDate = DateUtils.addDays(newExpirationDate, -1);
            expirationDate = DateUtils.convertToSqlDate(newExpirationDate);
        }
        
        return expirationDate;
    }

    protected ProtocolAction findProtocolAction(String actionTypeCode, List<ProtocolAction> protocolActions, ProtocolSubmission currentSubmission) {

        for (ProtocolAction pa : protocolActions) {
            if (pa.getProtocolActionType().getProtocolActionTypeCode().equals(actionTypeCode)
                    && (pa.getProtocolSubmission() == null || pa.getProtocolSubmission().equals(currentSubmission))) {
                return pa;
            }
        }
        return null;
    }

    /**
     * This method is to reinitialize amendment beans, otherwise a second pass thru prepareView() will show same
     * amendment creation options as previous passes
     * @throws Exception
     */
    public void initAmendmentBeans(boolean forceReset) throws Exception {
        if (protocolAmendmentBean == null || forceReset) {
            protocolAmendmentBean = createAmendmentBean();
        }
        if (protocolRenewAmendmentBean == null || forceReset) {
            protocolRenewAmendmentBean = createAmendmentBean();
        }
    }
    
    protected ProtocolAmendmentBean createAmendmentBean() throws Exception {
        protocolAmendmentBean = getNewProtocolAmendmentBeanInstanceHook(this);
        protocolRenewAmendmentBean = getNewProtocolAmendmentBeanInstanceHook(this);
        configureAmendmentBean(protocolAmendmentBean);
        configureAmendmentBean(protocolRenewAmendmentBean);
        return protocolAmendmentBean;
    }
    

    /**
     * Create an Amendment Bean.  The modules that can be selected depends upon the
     * current outstanding amendments.  If a module is currently being modified by a
     * previous amendment, it cannot be modified by another amendment.  Once the 
     * previous amendment has completed (approved, disapproved, etc), then a new
     * amendment can modify the same module.
     * @return
     * @throws Exception 
     */
    protected ProtocolAmendmentBean configureAmendmentBean(ProtocolAmendmentBean amendmentBean) throws Exception {
        //ProtocolAmendmentBean amendmentBean = new ProtocolAmendmentBean(this);
        List<String> moduleTypeCodes;

        if (StringUtils.isNotEmpty(getProtocol().getProtocolNumber()) && (getProtocol().isAmendment() || getProtocol().isRenewal())) {
            moduleTypeCodes = getProtocolAmendRenewServiceHook().getAvailableModules(getProtocol().getAmendedProtocolNumber());
            populateExistingAmendmentBean(amendmentBean, moduleTypeCodes);
        } else {
            moduleTypeCodes = getProtocolAmendRenewServiceHook().getAvailableModules(getProtocol().getProtocolNumber());
        }
        
        for (String moduleTypeCode : moduleTypeCodes) {
            enableModuleOption(moduleTypeCode, amendmentBean);
        }
        
        return amendmentBean;
    }

    


    /**
     * Create an AdminCorrection Bean.  The modules that can be edited (or corrected) depends upon the
     * current outstanding amendments.  If a module is currently being modified by a
     * an amendment, it cannot be corrected through Administrative Correction.  
     * @return
     * @throws Exception 
     */
    private AdminCorrectionBean createAdminCorrectionBean() throws Exception {
        AdminCorrectionBean adminCorrectionBean = getNewAdminCorrectionBeanInstanceHook(this); 
        //new AdminCorrectionBean(this);
        List<String> moduleTypeCodes = getProtocolAmendRenewServiceHook().getAvailableModules(getProtocol().getProtocolNumber());
        
        for (String moduleTypeCode : moduleTypeCodes) {
            enableModuleOption(moduleTypeCode, adminCorrectionBean);
        }
        
        return adminCorrectionBean;
    }    

    public void prepareView() throws Exception {
        protocolSubmitAction.prepareView();
        canSubmitProtocol = hasSubmitProtocolPermission();
        canSubmitProtocolUnavailable = hasSubmitProtocolUnavailablePermission();          
        assignToAgendaBean.prepareView();
        
// TODO *********commented the code below during IACUC refactoring*********         
//        assignCmtSchedBean.prepareView();
//        protocolAssignReviewersBean.prepareView();
//        submissionConstraint = getParameterValue(Constants.PARAMETER_IACUC_COMM_SELECTION_DURING_SUBMISSION);
        
        canCreateAmendment = hasCreateAmendmentPermission();
        canCreateAmendmentUnavailable = hasCreateAmendmentUnavailablePermission();
        canModifyAmendmentSections = hasModifyAmendmentSectionsPermission();
        canModifyAmendmentSectionsUnavailable = hasModifyAmendmentSectionsUnavailablePermission();
        canCreateRenewal = hasCreateRenewalPermission();
        canCreateRenewalUnavailable = hasCreateRenewalUnavailablePermission();
//        canNotifyIrb = hasNotifyIrbPermission();
//        canNotifyIrbUnavailable = hasNotifyIrbUnavailablePermission();
//        canNotifyCommittee = hasNotifyCommitteePermission();
//        canNotifyCommitteeUnavailable = hasNotifyCommitteeUnavailablePermission();
        
        canWithdraw = hasWithdrawPermission();            
        canWithdrawUnavailable = hasWithdrawUnavailablePermission();
        
// TODO *********commented the code below during IACUC refactoring*********           
//        canRequestClose = hasRequestClosePermission();
//        canRequestCloseUnavailable = hasRequestCloseUnavailablePermission();
//        canRequestSuspension = hasRequestSuspensionPermission();
//        canRequestSuspensionUnavailable = hasRequestSuspensionUnavailablePermission();
//        canRequestCloseEnrollment = hasRequestCloseEnrollmentPermission();
//        canRequestCloseEnrollmentUnavailable = hasRequestCloseEnrollmentUnavailablePermission();
//        canRequestReOpenEnrollment = hasRequestReOpenEnrollmentPermission();
//        canRequestReOpenEnrollmentUnavailable = hasRequestReOpenEnrollmentUnavailablePermission();
//        canRequestDataAnalysis = hasRequestDataAnalysisPermission();
//        canRequestDataAnalysisUnavailable = hasRequestDataAnalysisUnavailablePermission();
//        canRequestTerminate = hasRequestTerminatePermission();
//        canRequestTerminateUnavailable = hasRequestTerminateUnavailablePermission();
        
        canDeleteProtocolAmendRenew = hasDeleteProtocolAmendRenewPermission();
        canDeleteProtocolAmendRenewUnavailable = hasDeleteProtocolAmendRenewUnavailablePermission();
               
        canAssignToAgenda = hasAssignToAgendaPermission();
        canAssignToAgendaUnavailable = hasAssignToAgendaUnavailablePermission();
        
        
// TODO *********commented the code below during IACUC refactoring*********         
//        canAssignCmtSched = hasAssignCmtSchedPermission();
//        canAssignCmtSchedUnavailable = hasAssignCmtSchedUnavailablePermission();
//        canAssignReviewers = hasAssignReviewersPermission();
//        canAssignReviewersCmtSel = hasAssignReviewersCmtSel();
//        canAssignReviewersUnavailable = hasAssignReviewersUnavailablePermission();
//        canGrantExemption = hasGrantExemptionPermission();
//        canGrantExemptionUnavailable = hasGrantExemptionUnavailablePermission();
        
        canApproveFull = hasFullApprovePermission();
        canApproveFullUnavailable = hasFullApproveUnavailablePermission();
        
// TODO *********commented the code below during IACUC refactoring*********         
//        canApproveExpedited = hasExpeditedApprovalPermission();
//        canApproveExpeditedUnavailable = hasExpeditedApprovalUnavailablePermission();
//        canApproveResponse = hasResponseApprovalPermission();
//        canApproveResponseUnavailable = hasResponseApprovalUnavailablePermission();
        
        
        canDisapprove = hasDisapprovePermission();
        canDisapproveUnavailable = hasDisapproveUnavailablePermission();
        
        
        canReturnForSMR = hasReturnForSMRPermission();
        canReturnForSMRUnavailable = hasReturnForSMRUnavailablePermission();
        canReturnForSRR = hasReturnForSRRPermission();
        canReturnForSRRUnavailable = hasReturnForSRRUnavailablePermission();
        
// TODO *********commented the code below during IACUC refactoring*********         
//        canReopenEnrollment = hasReopenEnrollmentPermission();
//        canReopenEnrollmentUnavailable = hasReopenEnrollmentUnavailablePermission();
//        canCloseEnrollment = hasCloseEnrollmentPermission();
//        canCloseEnrollmentUnavailable = hasCloseEnrollmentUnavailablePermission();
        canSuspend = hasSuspendPermission();
        canSuspendUnavailable = hasSuspendUnavailablePermission();
//        canSuspendByDsmb = hasSuspendByDsmbPermission();
//        canSuspendByDsmbUnavailable = hasSuspendByDsmbUnavailablePermission();
//        canClose = hasClosePermission();
//        canCloseUnavailable = hasCloseUnavailablePermission();
        canExpire = hasExpirePermission();
        canExpireUnavailable = hasExpireUnavailablePermission();
        canTerminate = hasTerminatePermission();
        canTerminateUnavailable = hasTerminateUnavailablePermission();
//        canPermitDataAnalysis = hasPermitDataAnalysisPermission();
//        canPermitDataAnalysisUnavailable = hasPermitDataAnalysisUnavailablePermission();

        canMakeAdminCorrection = hasAdminCorrectionPermission();
        canMakeAdminCorrectionUnavailable = hasAdminCorrectionUnavailablePermission();
        
        canAdministrativelyApprove = hasAdministrativelyApprovePermission();
        canAdministrativelyApproveUnavailable = hasAdministrativelyApproveUnavailablePermission();
        canAdministrativelyWithdraw = hasAdministrativelyWithdrawPermission();
        canAdministrativelyWithdrawUnavailable = hasAdministrativelyWithdrawUnavailablePermission();
        canAdministrativelyMarkIncomplete = hasAdministrativelyMarkIncompletePermission();
        canAdministrativelyMarkIncompleteUnavailable = hasAdministrativelyMarkIncompleteUnavailablePermission();
        
 
        canRecordCommitteeDecision = hasRecordCommitteeDecisionPermission();
        canRecordCommitteeDecisionUnavailable = hasRecordCommitteeDecisionUnavailablePermission();
        
// TODO *********commented the code below during IACUC refactoring*********         
//        canEnterRiskLevel = hasEnterRiskLevelPermission();
        canUndoLastAction = hasUndoLastActionPermission();
        canUndoLastActionUnavailable = hasUndoLastActionUnavailablePermission();
//        canIrbAcknowledgement = hasIrbAcknowledgementPermission();
//        canIrbAcknowledgementUnavailable = hasIrbAcknowledgementUnavailablePermission();
//        canDefer = hasDeferPermission();
//        canDeferUnavailable = hasDeferUnavailablePermission();
        canModifyProtocolSubmission = hasCanModifySubmissionPermission();
        canModifyProtocolSubmissionUnavailable = hasCanModifySubmissionUnavailablePermission();
//        canReviewNotRequired = hasReviewNotRequiredPermission();
//        canReviewNotRequiredUnavailable = hasReviewNotRequiredUnavailablePermission();
        
        canManageReviewComments = hasManageReviewCommentsPermission();
        canManageReviewCommentsUnavailable = hasManageReviewCommentsUnavailablePermission();
        
// TODO *********commented the code below during IACUC refactoring*********         
//        canApproveOther = hasApproveOtherPermission();
//        canManageNotes = hasManageNotesPermission();
//        canManageNotesUnavailable = hasManageNotesUnavailablePermission();
        
        canAbandon = hasAbandonProtocolPermission();
        
        followupActionActions = getFollowupActionService().getFollowupsForProtocol(form.getProtocolDocument().getProtocol());

        
        canViewOnlineReviewers = hasCanViewOnlineReviewersPermission();
        canViewOnlineReviewerComments = hasCanViewOnlineReviewerCommentsPermission();
        
//        
//        canAddCloseReviewerComments = hasCloseRequestLastAction();
//        canAddCloseEnrollmentReviewerComments = hasCloseEnrollmentRequestLastAction();
//        canAddDataAnalysisReviewerComments = hasDataAnalysisRequestLastAction();
//        canAddReopenEnrollmentReviewerComments = hasReopenEnrollmentRequestLastAction();
        //canAddSuspendReviewerComments = hasSuspendRequestLastAction();
        canAddSuspendReviewerComments = hasSuspendPermission();
        //canAddTerminateReviewerComments = hasTerminateRequestLastAction();
        canAddTerminateReviewerComments = hasTerminatePermission();
//        hideReviewerName = checkToHideReviewName();
//       
//        initSummaryDetails();
        
        initSubmissionDetails();
        
// TODO *********commented the code below during IACUC refactoring*********         
//        setAmendmentDetails();
//        initFilterDatesView();
        initAmendmentBeans(false);
        initPrintQuestionnaire();
    }
    
    
    private boolean hasAdministrativelyApprovePermission() {
        ProtocolTask task = getNewAdminApproveProtocolTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTask getNewAdminApproveProtocolTaskInstanceHook(Protocol protocol);
    
    private boolean hasAdministrativelyApproveUnavailablePermission() {
        ProtocolTask task = getNewAdminApproveUnavailableProtocolTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    protected abstract ProtocolTask getNewAdminApproveUnavailableProtocolTaskInstanceHook(Protocol protocol);
    
    
    protected boolean hasExpirePermission() {
        ProtocolTask task = getExpireTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    protected abstract ProtocolTask getExpireTaskInstanceHook(Protocol protocol);
    
    protected boolean hasExpireUnavailablePermission() {
        //return !hasExpirePermission();
        ProtocolTask task = getExpireUnavailableTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    protected abstract ProtocolTask getExpireUnavailableTaskInstanceHook(Protocol protocol);
    
    private boolean hasAdministrativelyWithdrawPermission() {
        ProtocolTask task = getNewAdminWithdrawProtocolTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    protected abstract ProtocolTask getNewAdminWithdrawProtocolTaskInstanceHook(Protocol protocol);
    
    private boolean hasAdministrativelyWithdrawUnavailablePermission() {
        ProtocolTask task = getNewAdminWithdrawUnavailableProtocolTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    protected abstract ProtocolTask getNewAdminWithdrawUnavailableProtocolTaskInstanceHook(Protocol protocol);
    
    
    
    
    private boolean hasAdministrativelyMarkIncompletePermission() {
        ProtocolTask task = getNewAdminMarkIncompleteProtocolTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    protected abstract ProtocolTask getNewAdminMarkIncompleteProtocolTaskInstanceHook(Protocol protocol);
    
    private boolean hasAdministrativelyMarkIncompleteUnavailablePermission() {
        ProtocolTask task = getNewAdminMarkIncompleteUnavailableProtocolTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    protected abstract ProtocolTask getNewAdminMarkIncompleteUnavailableProtocolTaskInstanceHook(Protocol protocol);
    
    
     
    /**
     * Refreshes the comments for all the beans from the database.  Use sparingly since this will erase non-persisted comments.
     */
    public void prepareCommentsView() {
        
        protocolAdminApprovalBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
             
        assignToAgendaBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        
// TODO *********commented the code below during IACUC refactoring********* 
//        protocolGrantExemptionBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
//        protocolIrbAcknowledgementBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        
        protocolFullApprovalBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        
// TODO *********commented the code below during IACUC refactoring*********         
//        protocolExpeditedApprovalBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
//        protocolResponseApprovalBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        
        protocolDisapproveBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());         
        protocolSMRBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        protocolSRRBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        
// TODO *********commented the code below during IACUC refactoring*********         
//        protocolReopenEnrollmentBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
//        protocolCloseEnrollmentBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        protocolSuspendBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
//        protocolSuspendByDsmbBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
//        protocolCloseBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        protocolExpireBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        protocolTerminateBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
//        protocolPermitDataAnalysisBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        
        committeeDecision.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        
// TODO *********commented the code below during IACUC refactoring*********         
////        committeeDecision.getReviewCommentsBean().setHideReviewerName(getReviewCommentsService().setHideReviewerName(committeeDecision.getReviewCommentsBean().getReviewComments()));            
//        protocolDeferBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        
// TODO *********commented the code below during IACUC refactoring********* 
        protocolManageReviewCommentsBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        getProtocol().getProtocolSubmission().refreshReferenceObject("reviewAttachments");
        protocolManageReviewCommentsBean.getReviewAttachmentsBean().setReviewAttachments(getProtocol().getProtocolSubmission().getReviewAttachments());
        if (CollectionUtils.isNotEmpty(protocolManageReviewCommentsBean.getReviewAttachmentsBean().getReviewAttachments())) {
            protocolManageReviewCommentsBean.getReviewAttachmentsBean().setHideReviewerName(getReviewerCommentsService().setHideReviewerName(protocolManageReviewCommentsBean.getReviewAttachmentsBean().getReviewAttachments()));
        }
    }
    
    protected List<CommitteeScheduleMinute> getCopiedReviewComments() {       
        List<CommitteeScheduleMinute> clonedMinutes = new ArrayList<CommitteeScheduleMinute>();
        Long scheduleIdFk = getProtocol().getProtocolSubmission().getScheduleIdFk();
        // TODO OPTIMIZATION perhaps the minutes list can be an instance variable and call to the committeeScheduleService need only be made once
        List<CommitteeScheduleMinute> minutes = getCommitteeScheduleService().getMinutesBySchedule(scheduleIdFk);
        if (CollectionUtils.isNotEmpty(minutes)) {
            for (CommitteeScheduleMinute minute : minutes) {
                clonedMinutes.add(minute.getCopy());
            }
        }
        
        return clonedMinutes;
    }
       
    private CommonCommitteeScheduleService getCommitteeScheduleService() {
        if (committeeScheduleService == null) {
            committeeScheduleService = KraServiceLocator.getService(CommonCommitteeScheduleService.class);        
        }
        return committeeScheduleService;
    }
    
    protected ProtocolVersionService getProtocolVersionService() {
        if (this.protocolVersionService == null) {
            this.protocolVersionService = KraServiceLocator.getService(ProtocolVersionService.class);        
        }
        return this.protocolVersionService;
    }
    
//    private ProtocolSubmitActionService getProtocolSubmitActionService() {
//        if (protocolSubmitActionService == null) {
//            protocolSubmitActionService = KraServiceLocator.getService(ProtocolSubmitActionService.class);
//        }
//        return protocolSubmitActionService;
//    }
//    
//    private ProtocolActionService getProtocolActionService() {
//        if (protocolActionService == null) {
//            protocolActionService = KraServiceLocator.getService(ProtocolActionService.class);
//        }
//        return protocolActionService;
//    }

    protected String getParameterValue(String parameterName) {
        return this.getParameterService().getParameterValueAsString(ProtocolDocument.class, parameterName);      
    }
  
  
    protected boolean hasSubmitProtocolPermission() {
        ProtocolTask task = getNewSubmitProtocolTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTask getNewSubmitProtocolTaskInstanceHook(Protocol protocol);
    
    protected boolean hasSubmitProtocolUnavailablePermission() {
      ProtocolTask task = getNewSubmitProtocolUnavailableTaskInstanceHook(getProtocol());
      return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTask getNewSubmitProtocolUnavailableTaskInstanceHook(Protocol protocol);

    
    
    protected boolean hasCreateAmendmentPermission() {
        ProtocolTask task = getNewAmendmentProtocolTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    
    }
    
    protected boolean hasCreateAmendmentUnavailablePermission() {
        ProtocolTask task = getNewAmendmentProtocolUnavailableTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    
    }

    protected abstract ProtocolTask getNewAmendmentProtocolTaskInstanceHook(Protocol protocol);
    protected abstract ProtocolTask getNewAmendmentProtocolUnavailableTaskInstanceHook(Protocol protocol);
    
    protected boolean hasModifyAmendmentSectionsPermission() {
        ProtocolTask task = getModifyAmendmentSectionsProtocolTaskInstanceHook(getProtocol());
        return ((!getProtocol().isRenewalWithoutAmendment())&&(getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task)));
    }
    
    protected boolean hasModifyAmendmentSectionsUnavailablePermission() {
        ProtocolTask task = getModifyAmendmentSectionsUnavailableProtocolUnavailableTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    protected abstract ProtocolTask getModifyAmendmentSectionsProtocolTaskInstanceHook(Protocol protocol);
    protected abstract ProtocolTask getModifyAmendmentSectionsUnavailableProtocolUnavailableTaskInstanceHook(Protocol protocol);
    
    
    protected boolean hasCreateRenewalPermission() {
        ProtocolTask task = getNewRenewalProtocolTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected boolean hasCreateRenewalUnavailablePermission() {
        ProtocolTask task = getNewRenewalProtocolUnavailableTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    protected abstract ProtocolTask getNewRenewalProtocolTaskInstanceHook(Protocol protocol);
    protected abstract ProtocolTask getNewRenewalProtocolUnavailableTaskInstanceHook(Protocol protocol);
    
//    protected boolean hasNotifyIrbPermission() {
//        ProtocolTask task = new ProtocolTask(TaskName.NOTIFY_IRB, getProtocol());
//        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
//    }
//    
//    protected boolean hasNotifyIrbUnavailablePermission() {
//        ProtocolTask task = new ProtocolTask(TaskName.NOTIFY_IRB_UNAVAILABLE, getProtocol());
//        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
//    }
//    
//    protected boolean hasNotifyCommitteePermission() {
//        ProtocolTask task = new ProtocolTask(TaskName.NOTIFY_COMMITTEE, getProtocol());
//        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
//    }
//    
//    protected boolean hasNotifyCommitteeUnavailablePermission() {
//        ProtocolTask task = new ProtocolTask(TaskName.NOTIFY_COMMITTEE_UNAVAILABLE, getProtocol());
//        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
//    }
//  
    
    
    protected boolean hasWithdrawPermission() {
        ProtocolTask task = getNewWithdrawProtocolTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTask getNewWithdrawProtocolTaskInstanceHook(Protocol protocol);

    
    protected boolean hasWithdrawUnavailablePermission() {
        ProtocolTask task = getNewWithdrawProtocolUnavailableTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTask getNewWithdrawProtocolUnavailableTaskInstanceHook(Protocol protocol);
    
    
// TODO *********commented the code below during IACUC refactoring********* 
//    
//    protected boolean hasRequestClosePermission() {
//        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_CLOSE, getProtocol());
//        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
//    }
//    
//    protected boolean hasRequestCloseUnavailablePermission() {
//        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_CLOSE_UNAVAILABLE, getProtocol());
//        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
//    }
//    
//    protected boolean hasRequestSuspensionPermission() {
//        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_SUSPENSION, getProtocol());
//        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
//    }
//    
//    protected boolean hasRequestSuspensionUnavailablePermission() {
//        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_SUSPENSION_UNAVAILABLE, getProtocol());
//        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
//    }
//      protected boolean hasRequestCloseEnrollmentPermission() {
//        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_CLOSE_ENROLLMENT, getProtocol());
//        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
//    }
//    
//    protected boolean hasRequestCloseEnrollmentUnavailablePermission() {
//        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_CLOSE_ENROLLMENT_UNAVAILABLE, getProtocol());
//        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
//    }
//    
//    protected boolean hasRequestReOpenEnrollmentPermission() {
//        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_REOPEN_ENROLLMENT, getProtocol());
//        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
//    }
//    
//    protected boolean hasRequestReOpenEnrollmentUnavailablePermission() {
//        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_REOPEN_ENROLLMENT_UNAVAILABLE, getProtocol());
//        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
//    }
//    
//    protected boolean hasRequestDataAnalysisPermission() {
//        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_DATA_ANALYSIS, getProtocol());
//        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
//    }
//    
//    protected boolean hasRequestDataAnalysisUnavailablePermission() {
//        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_DATA_ANALYSIS_UNAVAILABLE, getProtocol());
//        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
//    }
//    
//    protected boolean hasRequestTerminatePermission() {
//        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_TERMINATE, getProtocol());
//        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
//    }
//    
//    protected boolean hasRequestTerminateUnavailablePermission() {
//        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_TERMINATE_UNAVAILABLE, getProtocol());
//        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
//    }
//    
    
    protected boolean hasDeleteProtocolAmendRenewPermission() {
        ProtocolTask task = createNewAmendRenewDeleteTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    
    protected abstract ProtocolTask createNewAmendRenewDeleteTaskInstanceHook(Protocol protocol);
    
    
    protected boolean hasDeleteProtocolAmendRenewUnavailablePermission() {
        ProtocolTask task = createNewAmendRenewDeleteUnavailableTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTask createNewAmendRenewDeleteUnavailableTaskInstanceHook(Protocol protocol);
    
    
    protected boolean hasAssignToAgendaPermission() {
        ProtocolTask task = createNewAssignToAgendaTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTask createNewAssignToAgendaTaskInstanceHook(Protocol protocol);

    
    protected boolean hasAssignToAgendaUnavailablePermission() {
        ProtocolTask task = createNewAssignToAgendaUnavailableTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTask createNewAssignToAgendaUnavailableTaskInstanceHook(Protocol protocol);
    

//    protected boolean hasAssignCmtSchedPermission() {
//        ProtocolTask task = new ProtocolTask(TaskName.ASSIGN_TO_COMMITTEE_SCHEDULE, getProtocol());
//        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
//    }
//    
//    public static boolean hasAssignCmtSchedPermission(String userId, String protocolNumber) {
//        Map<String, String> fieldValues = new HashMap<String, String>();
//        fieldValues.put("protocolNumber", protocolNumber);
//        BusinessObjectService bos = KraServiceLocator.getService(BusinessObjectService.class);
//        Protocol protocol = ((List<Protocol>) bos.findMatching(Protocol.class, fieldValues)).get(0);
//        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROPOSAL, protocol);
//        TaskAuthorizationService tas = KraServiceLocator.getService(TaskAuthorizationService.class);        
//        return tas.isAuthorized(userId, task);
//    }
//    
//    protected boolean hasAssignCmtSchedUnavailablePermission() {
//        ProtocolTask task = new ProtocolTask(TaskName.ASSIGN_TO_COMMITTEE_SCHEDULE_UNAVAILABLE, getProtocol());
//        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
//    }
//    
//    protected boolean hasAssignReviewersPermission() {
//        ProtocolTask task = new ProtocolTask(TaskName.ASSIGN_REVIEWERS, getProtocol());
//        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
//    }
//    
//    protected boolean hasAssignReviewersUnavailablePermission() {
//        ProtocolTask task = new ProtocolTask(TaskName.ASSIGN_REVIEWERS_UNAVAILABLE, getProtocol());
//        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
//    }
//    
//    protected boolean hasAssignReviewersCmtSel() {
//        ProtocolTask task = new ProtocolTask(TaskName.ASSIGN_REVIEWERS_CMT_SEL, getProtocol());
//        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
//    }
//    
//    protected boolean hasGrantExemptionPermission() {
//        return hasPermission(TaskName.GRANT_EXEMPTION);
//    }
//    
//    protected boolean hasGrantExemptionUnavailablePermission() {
//        return hasPermission(TaskName.GRANT_EXEMPTION_UNAVAILABLE);
//    }
    
    protected boolean hasFullApprovePermission() {
        return hasPermission(TaskName.APPROVE_PROTOCOL);
    }
    
    protected boolean hasFullApproveUnavailablePermission() {
        return hasPermission(TaskName.APPROVE_PROTOCOL_UNAVAILABLE);
    }
    
//    protected boolean hasExpeditedApprovalPermission() {
//        return hasPermission(TaskName.EXPEDITE_APPROVAL);
//    }
//    
//    protected boolean hasExpeditedApprovalUnavailablePermission() {
//        return hasPermission(TaskName.EXPEDITE_APPROVAL_UNAVAILABLE);
//    }
//    
//    protected boolean hasResponseApprovalPermission() {
//        return hasPermission(TaskName.RESPONSE_APPROVAL);
//    }
//    
//    protected boolean hasResponseApprovalUnavailablePermission() {
//        return hasPermission(TaskName.RESPONSE_APPROVAL_UNAVAILABLE);
//    }
    
    
    protected boolean hasDisapprovePermission() {
        return hasPermission(TaskName.DISAPPROVE_PROTOCOL);
    }
    
    protected boolean hasDisapproveUnavailablePermission() {
        return hasPermission(TaskName.DISAPPROVE_PROTOCOL_UNAVAILABLE);
    }   

    protected boolean hasReturnForSMRPermission() {
        return hasPermission(TaskName.RETURN_FOR_SMR);
    }
    
    protected boolean hasReturnForSMRUnavailablePermission() {
        return hasPermission(TaskName.RETURN_FOR_SMR_UNAVAILABLE);
    }
    
    protected boolean hasReturnForSRRPermission() {
        return hasPermission(TaskName.RETURN_FOR_SRR);
    }
    
    protected boolean hasReturnForSRRUnavailablePermission() {
        return hasPermission(TaskName.RETURN_FOR_SRR_UNAVAILABLE);
    }

    
// TODO *********commented the code below during IACUC refactoring*********         
//    protected boolean hasReopenEnrollmentPermission() {
//        return hasGenericPermission(GenericProtocolAuthorizer.REOPEN_PROTOCOL);
//    }
//    
//    protected boolean hasReopenEnrollmentUnavailablePermission() {
//        return hasGenericUnavailablePermission(GenericProtocolAuthorizer.REOPEN_PROTOCOL);
//    }
//    
//    protected boolean hasCloseEnrollmentPermission() {
//        return hasGenericPermission(GenericProtocolAuthorizer.CLOSE_ENROLLMENT_PROTOCOL);
//    }
//    
//    protected boolean hasCloseEnrollmentUnavailablePermission() {
//        return hasGenericUnavailablePermission(GenericProtocolAuthorizer.CLOSE_ENROLLMENT_PROTOCOL);
//    }
    
    protected boolean hasSuspendPermission() {
        ProtocolTask task = getSuspendTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    protected abstract ProtocolTask getSuspendTaskInstanceHook(Protocol protocol);
    
    protected boolean hasSuspendUnavailablePermission() {
        ProtocolTask task = getSuspendUnavailableTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    protected abstract ProtocolTask getSuspendUnavailableTaskInstanceHook(Protocol protocol);
    
//    protected boolean hasSuspendByDsmbPermission() {
//        return hasGenericPermission(GenericProtocolAuthorizer.SUSPEND_PROTOCOL_BY_DSMB);
//    }
//    
//    protected boolean hasSuspendByDsmbUnavailablePermission() {
//        return hasGenericUnavailablePermission(GenericProtocolAuthorizer.SUSPEND_PROTOCOL_BY_DSMB);
//    }
//    
//    protected boolean hasClosePermission() {
//        return hasGenericPermission(GenericProtocolAuthorizer.CLOSE_PROTOCOL);
//    }
//    
//    protected boolean hasCloseUnavailablePermission() {
//        return hasGenericUnavailablePermission(GenericProtocolAuthorizer.CLOSE_PROTOCOL);
//    }
//    
//    protected boolean hasExpirePermission() {
//        return hasGenericPermission(GenericProtocolAuthorizer.EXPIRE_PROTOCOL);
//    }
//    
//    protected boolean hasExpireUnavailablePermission() {
//        return hasGenericUnavailablePermission(GenericProtocolAuthorizer.EXPIRE_PROTOCOL);
//    }
//    
    protected boolean hasTerminatePermission() {
        ProtocolTask task = getTerminateTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    protected abstract ProtocolTask getTerminateTaskInstanceHook(Protocol protocol);
    
    protected boolean hasTerminateUnavailablePermission() {
        ProtocolTask task = getTerminateUnavailableTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    protected abstract ProtocolTask getTerminateUnavailableTaskInstanceHook(Protocol protocol);
    
//    protected boolean hasPermitDataAnalysisPermission() {
//        return hasGenericPermission(GenericProtocolAuthorizer.PERMIT_DATA_ANALYSIS);
//    }
//    
//    protected boolean hasPermitDataAnalysisUnavailablePermission() {
//        return hasGenericUnavailablePermission(GenericProtocolAuthorizer.PERMIT_DATA_ANALYSIS);
//    }
//    
    protected boolean hasAdminCorrectionPermission() {
        ProtocolTask task = getAdminCorrectionProtocolTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected boolean hasAdminCorrectionUnavailablePermission() {
        ProtocolTask task = getAdminCorrectionUnavailableProtocolTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTask getAdminCorrectionProtocolTaskInstanceHook(Protocol protocol);
    protected abstract ProtocolTask getAdminCorrectionUnavailableProtocolTaskInstanceHook(Protocol protocol);
    
    protected boolean hasUndoLastActionPermission() {
        return hasPermission(TaskName.PROTOCOL_UNDO_LAST_ACTION) && undoLastActionBean.canUndoLastAction();
    }
    
    protected boolean hasUndoLastActionUnavailablePermission() {
        return hasPermission(TaskName.PROTOCOL_UNDO_LAST_ACTION) && !undoLastActionBean.canUndoLastAction();
    }
    
    protected boolean hasRecordCommitteeDecisionPermission() {
        return hasPermission(TaskName.RECORD_COMMITTEE_DECISION);
    }
    
    protected boolean hasRecordCommitteeDecisionUnavailablePermission() {
        return hasPermission(TaskName.RECORD_COMMITTEE_DECISION_UNAVAILABLE);
    }
    
//    protected boolean hasEnterRiskLevelPermission() {
//        return hasPermission(TaskName.ENTER_RISK_LEVEL);
//    }
//    
//    protected boolean hasDeferPermission() {
//        return hasPermission(TaskName.DEFER_PROTOCOL);
//    }
//    
//    protected boolean hasDeferUnavailablePermission() {
//        return hasPermission(TaskName.DEFER_PROTOCOL_UNAVAILABLE);
//    }
    
    protected boolean hasManageReviewCommentsPermission() {
        return hasPermission(TaskName.PROTOCOL_MANAGE_REVIEW_COMMENTS); 
    }
    
    protected boolean hasManageReviewCommentsUnavailablePermission() {
        return hasPermission(TaskName.PROTOCOL_MANAGE_REVIEW_COMMENTS_UNAVAILABLE); 
    }
    
//    protected boolean hasApproveOtherPermission() {
//        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_APPROVE_OTHER, getProtocol());
//        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
//    }
//    
//    protected boolean hasManageNotesPermission() {
//        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_MANAGE_NOTES, getProtocol());
//        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
//    }
//    
//    protected boolean hasManageNotesUnavailablePermission() {
//        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_MANAGE_NOTES_UNAVAILABLE, getProtocol());
//        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
//    }
  
    
    
    /*
     * check this 
     */
    protected boolean hasAbandonProtocolPermission() {
        ProtocolTask task = createNewAbandonTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTask createNewAbandonTaskInstanceHook(Protocol protocol);
    
    
    protected abstract boolean hasPermission(String taskName);
  
// TODO *********commented the code below during IACUC refactoring*********     
//    protected boolean hasGenericPermission(String genericActionName) {
//        ProtocolTask task = new ProtocolTask(TaskName.GENERIC_PROTOCOL_ACTION, getProtocol(), genericActionName);
//        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
//    }
//    
//    protected boolean hasGenericUnavailablePermission(String genericActionName) {
//        ProtocolTask task = new ProtocolTask(TaskName.GENERIC_PROTOCOL_ACTION_UNAVAILABLE, getProtocol(), genericActionName);
//        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
//    }
//    
//    protected boolean hasIrbAcknowledgementPermission() {
//        ProtocolTask task = new ProtocolTask(TaskName.IRB_ACKNOWLEDGEMENT, getProtocol());
//        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
//    }
//    
//    protected boolean hasIrbAcknowledgementUnavailablePermission() {
//        ProtocolTask task = new ProtocolTask(TaskName.IRB_ACKNOWLEDGEMENT_UNAVAILABLE, getProtocol());
//        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
//    }
    
    
//    protected boolean hasReviewNotRequiredPermission() {
//        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REVIEW_NOT_REQUIRED, getProtocol());
//        boolean retVal = getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
//        return retVal;
//    }
//    
//    protected boolean hasReviewNotRequiredUnavailablePermission() {
//        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REVIEW_NOT_REQUIRED_UNAVAILABLE, getProtocol());
//        boolean retVal = getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
//        return retVal;
//    }
    
   

    protected boolean hasFollowupAction(String actionCode) {
        for (ValidProtocolActionAction action : followupActionActions) {
            if (StringUtils.equals(action.getFollowupActionCode(),actionCode)) {
                return true;
            }
        }
        return false;
    }
    
     
	protected boolean hasCanViewOnlineReviewersPermission() {
        return getReviewerCommentsService().canViewOnlineReviewers(getUserIdentifier(), getSelectedSubmission());
    }
    
    protected boolean hasCanViewOnlineReviewerCommentsPermission() {
        return getReviewerCommentsService().canViewOnlineReviewerComments(getUserIdentifier(), getSelectedSubmission());
    }

    
// TODO *********commented the code below during IACUC refactoring*********    
//    protected boolean hasCloseRequestLastAction() {
//        return ProtocolActionType.REQUEST_TO_CLOSE.equals(getLastPerformedAction().getProtocolActionTypeCode());
//    }
//    
//    protected boolean hasCloseEnrollmentRequestLastAction() {
//        return ProtocolActionType.REQUEST_TO_CLOSE_ENROLLMENT.equals(getLastPerformedAction().getProtocolActionTypeCode());
//    }
//    
//    protected boolean hasDataAnalysisRequestLastAction() {
//        return ProtocolActionType.REQUEST_FOR_DATA_ANALYSIS_ONLY.equals(getLastPerformedAction().getProtocolActionTypeCode());
//    }
//    
//    protected boolean hasReopenEnrollmentRequestLastAction() {
//        return ProtocolActionType.REQUEST_TO_REOPEN_ENROLLMENT.equals(getLastPerformedAction().getProtocolActionTypeCode());
//    }
//    
//    protected boolean hasSuspendRequestLastAction() {
//        return ProtocolActionType.REQUEST_FOR_SUSPENSION.equals(getLastPerformedAction().getProtocolActionTypeCode());
//    }
    
//    protected boolean hasTerminateRequestLastAction() {
//        return ProtocolActionType.REQUEST_FOR_TERMINATION.equals(getLastPerformedAction().getProtocolActionTypeCode());
//    }

    protected TaskAuthorizationService getTaskAuthorizationService() {
        if (this.taskAuthorizationService == null) {
            this.taskAuthorizationService = KraServiceLocator.getService(TaskAuthorizationService.class);        
        }
        return this.taskAuthorizationService;
    }
    
    public ProtocolSubmitAction getProtocolSubmitAction() {
        return protocolSubmitAction;
    }

    public ProtocolForm getProtocolForm() {
        return form;
    }
    
    public Protocol getProtocol() {
        return form.getProtocolDocument().getProtocol();
    }

    public boolean getCanSubmitProtocol() {
        return canSubmitProtocol;
    }
    
    public boolean getCanSubmitProtocolUnavailable() {
        return canSubmitProtocolUnavailable;
    }
    /**
     * Get the userName of the user for the current session.
     * @return the current session's userName
     */
    protected String getUserIdentifier() {
         return GlobalVariables.getUserSession().getPrincipalId();
    }

    public String getSubmissionConstraint() {
        return submissionConstraint;
    }

    public ProtocolWithdrawBean getProtocolWithdrawBean() {
        return protocolWithdrawBean;
    }
    
    public ProtocolRequestBean getProtocolCloseRequestBean() {
        return protocolCloseRequestBean;
    }

    public ProtocolRequestBean getProtocolSuspendRequestBean() {
        return protocolSuspendRequestBean;
    }
    
// TODO *********commented the code below during IACUC refactoring*********   
//    public ProtocolRequestBean getProtocolCloseEnrollmentRequestBean() {
//        return protocolCloseEnrollmentRequestBean;
//    }
//
//    public ProtocolRequestBean getProtocolReOpenEnrollmentRequestBean() {
//        return protocolReOpenEnrollmentRequestBean;
//    }
//    
//    public ProtocolRequestBean getProtocolDataAnalysisRequestBean() {
//        return protocolDataAnalysisRequestBean;
//    }
    
    public ProtocolRequestBean getProtocolTerminateRequestBean(){
        return this.protocolTerminateRequestBean;
    }
    
    public ProtocolNotifyCommitteeBean getProtocolNotifyCommitteeBean() {
        return protocolNotifyCommitteeBean;
    }
    
    public ProtocolAmendmentBean getProtocolAmendmentBean() {
        return protocolAmendmentBean;
    }
    
    public void setProtocolAmendmentBean(ProtocolAmendmentBean protocolAmendmentBean) {
        this.protocolAmendmentBean = protocolAmendmentBean;
    }
    
    public ProtocolAmendmentBean getProtocolRenewAmendmentBean() {
        return protocolRenewAmendmentBean;
    }
    
    public ProtocolDeleteBean getProtocolDeleteBean() {
        return protocolDeleteBean;
    }
    
    public ProtocolAssignToAgendaBean getAssignToAgendaBean(){
        return this.assignToAgendaBean;
    }
    
    public ProtocolAssignReviewersBean getProtocolAssignReviewersBean() {
        return protocolAssignReviewersBean;
    }
    
// TODO *********commented the code below during IACUC refactoring*********                            
//    public ProtocolGrantExemptionBean getProtocolGrantExemptionBean() {
//        return protocolGrantExemptionBean;
//    }

    public ProtocolApproveBean getProtocolFullApprovalBean() {
        return protocolFullApprovalBean;
    }
    
// TODO *********commented the code below during IACUC refactoring********* 
//    public ProtocolApproveBean getProtocolExpeditedApprovalBean() {
//        return protocolExpeditedApprovalBean;
//    }
//    
//    public ProtocolApproveBean getProtocolResponseApprovalBean() {
//        return protocolResponseApprovalBean;
//    }
    
    
    public ProtocolGenericActionBean getProtocolDisapproveBean() {
        return protocolDisapproveBean;
    }
    
    public ProtocolGenericActionBean getProtocolSMRBean() {
        return protocolSMRBean;
    }
    
    public ProtocolGenericActionBean getProtocolSRRBean() {
        return protocolSRRBean;
    }
 
// TODO *********commented the code below during IACUC refactoring*********     
//    public ProtocolGenericActionBean getProtocolReopenEnrollmentBean() {
//        return protocolReopenEnrollmentBean;
//    }
//    
//    public ProtocolGenericActionBean getProtocolCloseEnrollmentBean() {
//        return protocolCloseEnrollmentBean;
//    }
    
    public ProtocolGenericActionBean getProtocolSuspendBean() {
        return protocolSuspendBean;
    }
    
// TODO *********commented the code below during IACUC refactoring*********     
//    public ProtocolGenericActionBean getProtocolSuspendByDsmbBean() {
//        return protocolSuspendByDsmbBean;
//    }
    
    public ProtocolGenericActionBean getProtocolCloseBean() {
        return protocolCloseBean;
    }
    
    public ProtocolGenericActionBean getProtocolExpireBean() {
        return protocolExpireBean;
    }
    
    public ProtocolGenericActionBean getProtocolTerminateBean() {
        return protocolTerminateBean;
    }
    
// TODO *********commented the code below during IACUC refactoring*********     
//    public ProtocolGenericActionBean getProtocolPermitDataAnalysisBean() {
//        return protocolPermitDataAnalysisBean;
//    }
    
    public ProtocolGenericActionBean getProtocolIacucAcknowledgementBean() {
        return protocolIrbAcknowledgementBean;
    }
    
    public AdminCorrectionBean getProtocolAdminCorrectionBean() {
        return protocolAdminCorrectionBean;
    }
    
    public UndoLastActionBean getUndoLastActionBean() {
        return undoLastActionBean;
    }

    public CommitteeDecision<?> getCommitteeDecision() {
        return committeeDecision;
    }
    
    protected abstract ProtocolTask getModifySubmissionAvailableTaskHook();
    
    protected abstract ProtocolTask getModifySubmissionUnavailableTaskHook();
    
    protected boolean hasCanModifySubmissionPermission() {
        ProtocolTask task = getModifySubmissionAvailableTaskHook();
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected boolean hasCanModifySubmissionUnavailablePermission() {
        ProtocolTask task = getModifySubmissionUnavailableTaskHook();
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
  
    public ProtocolGenericActionBean getProtocolManageReviewCommentsBean() {
        return protocolManageReviewCommentsBean;
    }

    public boolean getCanCreateAmendment() {
        return canCreateAmendment;
    }
    
    public boolean getCanCreateAmendmentUnavailable() {
        return canCreateAmendmentUnavailable;
    }
    
    public boolean getCanModifyAmendmentSections() {
        return canModifyAmendmentSections;
    }

    public boolean getCanModifyAmendmentSectionsUnavailable() {
        return canModifyAmendmentSectionsUnavailable;
    }

    public boolean getCanCreateRenewal() {
        return canCreateRenewal;
    }

    public boolean getCanCreateRenewalUnavailable() {
        return canCreateRenewalUnavailable;
    }

// TODO *********commented the code below during IACUC refactoring*********     
//    public boolean getCanNotifyIrb() {
//        return canNotifyIrb;
//    }
//    
//    public boolean getCanNotifyIrbUnavailable() {
//        return canNotifyIrbUnavailable;
//    }
    
    public boolean getCanNotifyCommittee() {
        return canNotifyCommittee;
    }
    
    public boolean getCanNotifyCommitteeUnavailable() {
        return canNotifyCommitteeUnavailable;
    }
    
    public boolean getCanWithdraw() {
        return canWithdraw;
    }
    
    public boolean getCanWithdrawUnavailable() {
        return canWithdrawUnavailable;
    }
    
// TODO *********commented the code below during IACUC refactoring*********     
//    public boolean getCanRequestClose() {
//        return canRequestClose;
//    }
//    
//    public boolean getCanRequestCloseUnavailable() {
//        return canRequestCloseUnavailable;
//    }
    
    public boolean getCanRequestSuspension() {
        return canRequestSuspension;
    }
    
    public boolean getCanRequestSuspensionUnavailable() {
        return canRequestSuspensionUnavailable;
    }
    
// TODO *********commented the code below during IACUC refactoring*********     
//    public boolean getCanRequestCloseEnrollment() {
//        return canRequestCloseEnrollment;
//    }
//    
//    public boolean getCanRequestCloseEnrollmentUnavailable() {
//        return canRequestCloseEnrollmentUnavailable;
//    }
//    
//    public boolean getCanRequestReOpenEnrollment() {
//        return canRequestReOpenEnrollment;
//    }
//    
//    public boolean getCanRequestReOpenEnrollmentUnavailable() {
//        return canRequestReOpenEnrollmentUnavailable;
//    }
//    
//    public boolean getCanRequestDataAnalysis() {
//        return canRequestDataAnalysis;
//    }
//    
//    public boolean getCanRequestDataAnalysisUnavailable() {
//        return canRequestDataAnalysisUnavailable;
//    }
    
    public boolean getcanRequestTerminate(){
        return this.canRequestTerminate;
    }
    
    public boolean getcanRequestTerminateUnavailable(){
        return this.canRequestTerminateUnavailable;
    }
    
    public boolean getCanDeleteProtocolAmendRenew() {
        return canDeleteProtocolAmendRenew;
    }
    
    public boolean getCanDeleteProtocolAmendRenewUnavailable() {
        return canDeleteProtocolAmendRenewUnavailable;
    }
    
    public boolean getCanAssignToAgenda() {
        return canAssignToAgenda;
    }
    
    public boolean getCanAssignToAgendaUnavailable() {
        return canAssignToAgendaUnavailable;
    }
    
    public boolean getCanAssignCmtSched() {
        return canAssignCmtSched;
    }
    
    public boolean getCanAssignCmtSchedUnavailable() {
        return canAssignCmtSchedUnavailable;
    }
    
    public boolean getCanAssignReviewers() {
        return canAssignReviewers;
    }
    
    public boolean getCanAssignReviewersUnavailable() {
        return canAssignReviewersUnavailable;
    }
    
    public boolean getCanAssignReviewersCmtSel() {
        return canAssignReviewersCmtSel;
    }
    
    
// TODO *********commented the code below during IACUC refactoring*********     
//    public boolean getCanGrantExemption() {
//        return canGrantExemption;
//    }
//    
//    public boolean getCanGrantExemptionUnavailable() {
//        return canGrantExemptionUnavailable;
//    }
    
    public boolean getCanApproveFull() {
        return canApproveFull;
    }
    
    public boolean getCanApproveFullUnavailable() {
        return canApproveFullUnavailable;
    }
    
// TODO *********commented the code below during IACUC refactoring*********     
//    public boolean getCanApproveExpedited() {
//        return canApproveExpedited;
//    }
//    
//    public boolean getCanApproveExpeditedUnavailable() {
//        return canApproveExpeditedUnavailable;
//    }
    
    public boolean getCanApproveResponse() {
        return canApproveResponse;
    }
    
    public boolean getCanApproveResponseUnavailable() {
        return canApproveResponseUnavailable;
    }
    
    public boolean getCanDisapprove() {
        return canDisapprove;
    }
    
    public boolean getCanDisapproveUnavailable() {
        return canDisapproveUnavailable;
    }
    
    public boolean getCanReturnForSMR() {
        return canReturnForSMR;
    }
    
    public boolean getCanReturnForSMRUnavailable() {
        return canReturnForSMRUnavailable;
    }
    
    public boolean getCanReturnForSRR() {
        return canReturnForSRR;
    }
    
    public boolean getCanReturnForSRRUnavailable() {
        return canReturnForSRRUnavailable;
    }
    
    
// TODO *********commented the code below during IACUC refactoring*********     
//    public boolean getCanReopenEnrollment() {
//        return canReopenEnrollment;
//    }
//    
//    public boolean getCanReopenEnrollmentUnavailable() {
//        return canReopenEnrollmentUnavailable;
//    }
//    
//    public boolean getCanCloseEnrollment() {
//        return canCloseEnrollment;
//    }
//    
//    public boolean getCanCloseEnrollmentUnavailable() {
//        return canCloseEnrollmentUnavailable;
//    }
    
    public boolean getCanSuspend() {
        return canSuspend;
    }
    
    public boolean getCanSuspendUnavailable() {
        return canSuspendUnavailable;
    }
    
// TODO *********commented the code below during IACUC refactoring*********     
//    public boolean getCanSuspendByDsmb() {
//        return canSuspendByDsmb;
//    }
//    
//    public boolean getCanSuspendByDsmbUnavailable() {
//        return canSuspendByDsmbUnavailable;
//    }
    
    public boolean getCanClose() {
        return canClose;
    }
    
    public boolean getCanCloseUnavailable() {
        return canCloseUnavailable;
    }
    
    public boolean getCanExpire() {
        return canExpire;
    }
    
    public boolean getCanExpireUnavailable() {
        return canExpireUnavailable;
    }
    
    public boolean getCanTerminate() {
        return canTerminate;
    }
    
    public boolean getCanTerminateUnavailable() {
        return canTerminateUnavailable;
    }
    
// TODO *********commented the code below during IACUC refactoring*********     
//    public boolean getCanPermitDataAnalysis() {
//        return canPermitDataAnalysis;
//    }
//    
//    public boolean getCanPermitDataAnalysisUnavailable() {
//        return canPermitDataAnalysisUnavailable;
//    }
//    
//    public boolean getCanEnterRiskLevel() {
//        return canEnterRiskLevel;
//    }
    
    
  

    public boolean getCanPerformAdminDetermination() {
        return canPerformAdminDetermination;
    }

    public void setCanPerformAdminDetermination(boolean canPerformAdminDetermination) {
        this.canPerformAdminDetermination = canPerformAdminDetermination;
    }

    public boolean getCanPerformAdminDeterminationUnavailable() {
        return canPerformAdminDeterminationUnavailable;
    }

    public void setCanPerformAdminDeterminationUnavailable(boolean canPerformAdminDeterminationUnavailable) {
        this.canPerformAdminDeterminationUnavailable = canPerformAdminDeterminationUnavailable;
    }

    public boolean getCanAdministrativelyApprove() {
        return canAdministrativelyApprove;
    }

    public void setCanAdministrativelyApprove(boolean canAdministrativelyApprove) {
        this.canAdministrativelyApprove = canAdministrativelyApprove;
    }

    public boolean getCanAdministrativelyApproveUnavailable() {
        return canAdministrativelyApproveUnavailable;
    }

    public void setCanAdministrativelyApproveUnavailable(boolean canAdministrativelyApproveUnavailable) {
        this.canAdministrativelyApproveUnavailable = canAdministrativelyApproveUnavailable;
    }

    public boolean getCanAdministrativelyMarkIncomplete() {
        return canAdministrativelyMarkIncomplete;
    }

    public void setCanAdministrativelyMarkIncomplete(boolean canAdministrativelyMarkIncomplete) {
        this.canAdministrativelyMarkIncomplete = canAdministrativelyMarkIncomplete;
    }

    public boolean getCanAdministrativelyMarkIncompleteUnavailable() {
        return canAdministrativelyMarkIncompleteUnavailable;
    }

    public void setCanAdministrativelyMarkIncompleteUnavailable(boolean canAdministrativelyMarkIncompleteUnavailable) {
        this.canAdministrativelyMarkIncompleteUnavailable = canAdministrativelyMarkIncompleteUnavailable;
    }

    public boolean getCanAdministrativelyWithdraw() {
        return canAdministrativelyWithdraw;
    }

    public void setCanAdministrativelyWithdraw(boolean canAdministrativelyWithdraw) {
        this.canAdministrativelyWithdraw = canAdministrativelyWithdraw;
    }

    public boolean getCanAdministrativelyWithdrawUnavailable() {
        return canAdministrativelyWithdrawUnavailable;
    }

    public void setCanAdministrativelyWithdrawUnavailable(boolean canAdministrativelyWithdrawUnavailable) {
        this.canAdministrativelyWithdrawUnavailable = canAdministrativelyWithdrawUnavailable;
    }
    
    
    

    public void setProtocolAdminWithdrawBean(ProtocolAdministrativelyWithdrawBean protocolAdminWithdrawBean) {
        this.protocolAdminWithdrawBean = protocolAdminWithdrawBean;
    }

    public ProtocolAdministrativelyWithdrawBean getProtocolAdminWithdrawBean() {
        return protocolAdminWithdrawBean;
    }

    public void setProtocolAdminApprovalBean(ProtocolApproveBean protocolAdminApprovalBean) {
        this.protocolAdminApprovalBean = protocolAdminApprovalBean;
    }

    public ProtocolApproveBean getProtocolAdminApprovalBean() {
        return protocolAdminApprovalBean;
    }

    public void setProtocolAdminIncompleteBean(ProtocolAdministrativelyIncompleteBean protocolAdminIncompleteBean) {
        this.protocolAdminIncompleteBean = protocolAdminIncompleteBean;
    }

    public ProtocolAdministrativelyIncompleteBean getProtocolAdminIncompleteBean() {
        return protocolAdminIncompleteBean;
    }

    public boolean getCanMakeAdminCorrection() {
        return canMakeAdminCorrection;
    }
    
    public boolean getCanMakeAdminCorrectionUnavailable() {
        return canMakeAdminCorrectionUnavailable;
    }
    
    public boolean getCanUndoLastAction() {
        return canUndoLastAction;
    }
    
    public boolean getCanUndoLastActionUnavailable() {
        return canUndoLastActionUnavailable;
    }
    
    public boolean getCanRecordCommitteeDecision() {
        return canRecordCommitteeDecision;
    }
    
    public boolean getCanRecordCommitteeDecisionUnavailable() {
        return canRecordCommitteeDecisionUnavailable;
    }
    
    public boolean getCanModifyProtocolSubmission() {
        return this.canModifyProtocolSubmission;
    }
    
    public boolean getCanModifyProtocolSubmissionUnavailable() {
        return this.canModifyProtocolSubmissionUnavailable;
    }
    
    
// TODO *********commented the code below during IACUC refactoring*********     
//    public boolean getCanIrbAcknowledgement() {
//        return canIrbAcknowledgement;
//    }
//    
//    public boolean getCanIrbAcknowledgementUnavailable() {
//        return canIrbAcknowledgementUnavailable;
//    }
//    
//    public boolean getCanDefer() {
//        return canDefer;
//    }
//    
//    public boolean getCanDeferUnavailable() {
//        return canDeferUnavailable;
//    }
//    
//    public boolean getCanReviewNotRequired() {
//        return this.canReviewNotRequired;
//    }
//
//    public boolean getCanReviewNotRequiredUnavailable() {
//        return this.canReviewNotRequiredUnavailable;
//    }

    public boolean getCanManageReviewComments() {  
        return canManageReviewComments;
    }
    
    public boolean getCanManageReviewCommentsUnavailable() {  
        return canManageReviewCommentsUnavailable;
    }
    
    public boolean getCanApproveOther() {
        return canApproveOther;
    }
    
    public boolean getCanManageNotes() {
        return canManageNotes;
    }

    public boolean getCanManageNotesUnavailable() {
        return canManageNotesUnavailable;
    }

     
    public boolean getIsApproveOpenForFollowup() {
        return hasFollowupAction(getFullApprovalProtocolActionTypeHook());
    }

    
    public boolean getIsDisapproveOpenForFollowup() {
        return hasFollowupAction(getDisapprovedProtocolActionTypeHook());
    }
    

    public boolean getIsReturnForSMROpenForFollowup() {
        return hasFollowupAction(getSMRProtocolActionTypeHook());
    }

    public boolean getIsReturnForSRROpenForFollowup() {
        return hasFollowupAction(getSRRProtocolActionTypeHook());
    }
    
    /**
     * 
     * This method is one of the criteria to decide if there is a followup action for requested action panel to open
     * @return
     */
    public boolean isOpenForFollowup() {
        return getIsApproveOpenForFollowup() || getIsDisapproveOpenForFollowup() || getIsReturnForSMROpenForFollowup() || getIsReturnForSRROpenForFollowup();
    }
    
    public Map<String,Boolean> getFollowupActionMap() {
        return followupActionMap;
    }
    
    public boolean getCanViewOnlineReviewers() {
        return canViewOnlineReviewers;
    }
    
    public boolean getCanViewOnlineReviewerComments() {
        return canViewOnlineReviewerComments;
    }
    
    
// TODO *********commented the code below during IACUC refactoring********* 
//    public boolean getCanAddCloseReviewerComments() {
//        return canAddCloseReviewerComments;
//    }
//
//    public boolean getCanAddCloseEnrollmentReviewerComments() {
//        return canAddCloseEnrollmentReviewerComments;
//    }
//
//    public boolean getCanAddDataAnalysisReviewerComments() {
//        return canAddDataAnalysisReviewerComments;
//    }
//
//    public boolean getCanAddReopenEnrollmentReviewerComments() {
//        return canAddReopenEnrollmentReviewerComments;
//    }

    public boolean getCanAddSuspendReviewerComments() {
        return canAddSuspendReviewerComments;
    }

    public boolean getCanAddTerminateReviewerComments() {
        return canAddTerminateReviewerComments;
    }

//    public void setPrintTag(String printTag) {
//        this.printTag = printTag;
//    }
//    
//    public String getPrintTag() {
//        return printTag;
//    }
    
    public ProtocolSummary getProtocolSummary() {
        return protocolSummary;
    }
    
    public ProtocolSummary getPrevProtocolSummary() {
        return prevProtocolSummary;
    }
    
    public void setSelectedHistoryItem(String selectedHistoryItem) {
        this.selectedHistoryItem = selectedHistoryItem;
    }
    
    public String getSelectedHistoryItem() {
        return selectedHistoryItem;
    }
    
    public Date getFilteredHistoryStartDate() {
        return filteredHistoryStartDate;
    }
    
    public void setFilteredHistoryStartDate(Date filteredHistoryStartDate) {
        this.filteredHistoryStartDate = filteredHistoryStartDate;
    }
    
    public Date getFilteredHistoryEndDate() {
        return filteredHistoryEndDate;
    }
    
    public void setFilteredHistoryEndDate(Date filteredHistoryEndDate) {
        this.filteredHistoryEndDate = filteredHistoryEndDate;
    }
         
    public ProtocolAction getLastPerformedAction() {
        List<ProtocolAction> protocolActions = form.getProtocolDocument().getProtocol().getProtocolActions();
        Collections.sort(protocolActions, new Comparator<ProtocolAction>() {
            public int compare(ProtocolAction action1, ProtocolAction action2) {
                return action2.getActualActionDate().compareTo(action1.getActualActionDate());
            }
        });
     
        return protocolActions.size() > 0 ? protocolActions.get(0) : null;
    }
    
    /**
     * Prepares all protocol actions for being filtered by setting their isInFilterView attribute.
     */
    public void initFilterDatesView() {
        java.util.Date dayBeforeStartDate = null;
        java.util.Date dayAfterEndDate = null;

        if (filteredHistoryStartDate != null && filteredHistoryEndDate != null) {
            dayBeforeStartDate = DateUtils.addDays(filteredHistoryStartDate, -1);
            dayAfterEndDate = DateUtils.addDays(filteredHistoryEndDate, 1);
        }

        for (ProtocolAction protocolAction : getSortedProtocolActions()) {
            Timestamp actionDate = protocolAction.getActionDate();
            if (dayBeforeStartDate != null && dayAfterEndDate != null) {
                protocolAction.setIsInFilterView(actionDate.after(dayBeforeStartDate) && actionDate.before(dayAfterEndDate));
            }
            else {
                protocolAction.setIsInFilterView(true);
            }
            if (protocolAction.getIsInFilterView()) {
//TODO                protocolAction.setQuestionnairePrintOptionFromHelper(this);
            }
        }
    }
    
//    // All the following methods were refactored into the ProtocolAction, where they belong
//    
//    /*
//     * check if this particular ProtocolAction has any questionnaire associated with it.
//     */
///*    private void checkQuestionnaire(ProtocolAction protocolAction) {
//        if (protocolAction.getSubmissionNumber() != null
//                && !ProtocolActionType.SUBMIT_TO_IRB.equals(protocolAction.getProtocolActionTypeCode())) {
//            protocolAction.setAnswerHeadersCount(getAnswerHeaderCount(CoeusSubModule.PROTOCOL_SUBMISSION, Integer
//                    .toString(protocolAction.getSubmissionNumber())));
//             if (protocolAction.getAnswerHeadersCount() > 0) {
//                protocolAction.setQuestionnairePrintOption(getQnPrintOptionForAction(protocolAction.getProtocolNumber(),
//                        protocolAction.getSubmissionNumber().toString(), CoeusSubModule.PROTOCOL_SUBMISSION, protocolAction));
//            }
//        } else if (ProtocolActionType.SUBMIT_TO_IRB.equals(protocolAction.getProtocolActionTypeCode())
//                && ("Submitted to IRB").equals(protocolAction.getComments())) {
//            if (protocolAction.getProtocol().isAmendment() || protocolAction.getProtocol().isRenewal()) {
//                protocolAction.setQuestionnairePrintOption(getQnPrintOptionForAction(protocolAction.getProtocolNumber(),
//                        protocolAction.getSequenceNumber().toString(), CoeusSubModule.AMENDMENT_RENEWAL, protocolAction));
//
//            } else {
//                protocolAction.setQuestionnairePrintOption(getQnPrintOptionForAction(protocolAction.getProtocolNumber(),
//                        getInitialSequence(protocolAction, ""), CoeusSubModule.ZERO_SUBMODULE, protocolAction));
//            }
//        } else if (ProtocolActionType.SUBMIT_TO_IRB.equals(protocolAction.getProtocolActionTypeCode())
//                && StringUtils.isNotBlank(protocolAction.getComments())
//                && (protocolAction.getComments().startsWith("Amendment-") || protocolAction.getComments().startsWith("Renewal-"))) {
//            String amendmentRenewalNumber = getAmendmentRenewalNumber(protocolAction.getComments());
//            protocolAction.setQuestionnairePrintOption(getQnPrintOptionForAction(protocolAction.getProtocolNumber()
//                    + amendmentRenewalNumber, getInitialSequence(protocolAction, amendmentRenewalNumber),
//                    CoeusSubModule.AMENDMENT_RENEWAL, protocolAction));
//         }
//    }
//*/
//
//    /*
//     * set up questionnaire option for UI view button
//     */
///*    QuestionnairePrintOption getQnPrintOptionForAction(String itemKey, String subItemKey, String subItemCode, ProtocolAction protocolAction) {
//
//        protocolAction.setAnswerHeadersCount(getAnswerHeaderCount(subItemCode, itemKey, subItemKey));
//        if (protocolAction.getAnswerHeadersCount() > 0) {
//            QuestionnairePrintOption qnPrintOption = new QuestionnairePrintOption();
//            qnPrintOption.setItemKey(itemKey);
//            qnPrintOption.setSubItemCode(subItemCode);
//            qnPrintOption.setSubItemKey(subItemKey);
//            return qnPrintOption;
//        } else {
//            return null;
//        }
//    }
//    
//    public int getAnswerHeaderCount(String moduleSubItemCode, String moduleItemKey, String moduleSubItemKey) {
//        Map<String, String> fieldValues = new HashMap<String, String>();
//        fieldValues.put("moduleItemCode", CoeusModule.IRB_MODULE_CODE);
//        fieldValues.put("moduleItemKey", moduleItemKey);
//        if (!moduleItemKey.contains("A") && !moduleItemKey.contains("R") && !getProtocol().isAmendment() && !getProtocol().isRenewal()) {
//            fieldValues.put("moduleSubItemCode", moduleSubItemCode);
//        }
//        fieldValues.put("moduleSubItemKey", moduleSubItemKey);
//        return getBusinessObjectService().countMatching(AnswerHeader.class, fieldValues);
//        
//    }
//*/
//    
//    /*
//     * utility method to get amend or renewal number
//     */
///*  private String getAmendmentRenewalNumber(String comment) {
//        String retVal="";
//        if (comment.startsWith("Amendment-")) {
//            retVal = "A" + comment.substring(10, 13);
//        } else {
//            retVal = "R" + comment.substring(8, 11);
//                     
//        }
//        return retVal;
//    }
//*/    
//
//    /*
//     * get the sequence number of the protocol that the action initially created
//     */
///*  private String getInitialSequence(ProtocolAction protocolAction, String amendmentRenewalNumber) {
//        Map<String, String> fieldValues = new HashMap<String, String>();
//        fieldValues.put("protocolNumber", protocolAction.getProtocolNumber() + amendmentRenewalNumber);
//        if (StringUtils.isBlank(amendmentRenewalNumber)) {
//            fieldValues.put("actionId", protocolAction.getActionId().toString());
//        }
//        else {
//            fieldValues.put("submissionNumber", protocolAction.getSubmissionNumber().toString());
//        }
//        fieldValues.put("protocolActionTypeCode", ProtocolActionType.SUBMIT_TO_IRB);
//        return ((List<ProtocolAction>) getBusinessObjectService().findMatchingOrderBy(ProtocolAction.class, fieldValues,
//                "protocolActionId", true)).get(0).getProtocol().getSequenceNumber().toString();
//    }
//*/

    /**
     * Prepares, sorts, and returns a list of protocol actions.
     * @return
     */
    public List<ProtocolAction> getSortedProtocolActions() {
        List<ProtocolAction> protocolActions = new ArrayList<ProtocolAction>();
        for (ProtocolAction protocolAction : form.getProtocolDocument().getProtocol().getProtocolActions()) {       
           if (protocolAction.getSubmissionNumber() != null) {
               protocolAction.setProtocolSubmissionDocs(new ArrayList<ProtocolSubmissionDoc>(getSubmissionDocs(protocolAction)));
           }
            protocolActions.add(protocolAction);
        }
        
        Collections.sort(protocolActions, new Comparator<ProtocolAction>() {
            public int compare(ProtocolAction action1, ProtocolAction action2) {
                return action2.getActualActionDate().compareTo(action1.getActualActionDate());
            }
        });
     
        return protocolActions;
    }

    private Collection<? extends ProtocolSubmissionDoc> getSubmissionDocs(ProtocolAction protocolAction) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("protocolNumber", protocolAction.getProtocolNumber());
        fieldValues.put("submissionNumber", protocolAction.getSubmissionNumber());
        return getBusinessObjectService().findMatchingOrderBy(getProtocolSubmissionDocClassHook(), fieldValues, "documentId", true);
    }

    
    
    protected abstract Class<? extends ProtocolSubmissionDoc> getProtocolSubmissionDocClassHook();

    
    
    public ProtocolAction getSelectedProtocolAction() {
        for (ProtocolAction action : getProtocol().getProtocolActions()) {
            if (StringUtils.equals(action.getProtocolActionId().toString(), selectedHistoryItem)) {
                return action;
            }
        }
        return null;
    }
    
    public void setCurrentSequenceNumber(int currentSequenceNumber) {
        this.currentSequenceNumber = currentSequenceNumber;
    }
    
    public int getCurrentSequenceNumber() {
        return currentSequenceNumber;
    }
    
    public int getSequenceCount() {
        return getProtocol().getSequenceNumber()  + 1;
    }
    
    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KraServiceLocator.getService(ParameterService.class);        
        }
        return this.parameterService;
    }

    /**
     * Finds and returns the current selection based on the currentSubmissionNumber.
     * 
     * If the currentSubmissionNumber is invalid, it will return the current protocol's latest submission (which is always non-null); otherwise, it will get
     * the submission from the protocol based on the currentSubmissionNumber.
     * @return the currently selected submission
     */
    public ProtocolSubmission getSelectedSubmission() {
        ProtocolSubmission protocolSubmission = null;
        
        if (currentSubmissionNumber <= 0) {
            protocolSubmission = getProtocol().getProtocolSubmission();
        } else if (currentSubmissionNumber > 0) {
            // For amendment/renewal, the submission number are not starting at 1
            //protocolSubmission = getProtocol().getProtocolSubmissions().get(currentSubmissionNumber - 1);
            for (ProtocolSubmission submission : getProtocol().getProtocolSubmissions()) {
                if (submission.getSubmissionNumber().intValue() == currentSubmissionNumber) {
                    protocolSubmission = submission;
                    break;
                }
            }
            if (protocolSubmission == null) {
                // undo last action may remove the last submission; so it can't be found
                protocolSubmission = getProtocol().getProtocolSubmission();
                currentSubmissionNumber = protocolSubmission.getSubmissionNumber();
            }
        }
        
        return protocolSubmission;
    }
  
    private CommonCommitteeService getCommitteeService() {
        return KraServiceLocator.getService(CommonCommitteeService.class);
    }


    public List<CommitteeScheduleMinute> getReviewComments() {
        return reviewComments;
    }

    private void setReviewComments(List<CommitteeScheduleMinute> reviewComments) {
        this.reviewComments = reviewComments;
    }

    public List<ProtocolVoteAbstainee> getAbstainees() {
        return abstainees;
    }

    public void setAbstainees(List<ProtocolVoteAbstainee> abstainees) {
        this.abstainees = abstainees;
    }
    
    public BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }
    
    public FollowupActionService<?> getFollowupActionService() {
        if (followupActionService == null) {
            followupActionService = KraServiceLocator.getService(getFollowupActionServiceClassHook());
        }
        return followupActionService;
    }
    
    
    protected abstract Class<? extends FollowupActionService<?>> getFollowupActionServiceClassHook();
    

    private ReviewCommentsService getReviewerCommentsService() {
        return getReviewCommentsService();
    }

    
    
// TODO *********commented the code below during IACUC refactoring*********     
//    private CommitteeDecisionService getCommitteeDecisionService() {
//        return KraServiceLocator.getService("protocolCommitteeDecisionService");
//    }
    
    protected abstract CommitteeDecisionService<? extends CommitteeDecision<?> > getCommitteeDecisionService();
    
    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KraServiceLocator.getService(KcPersonService.class);
        }
        
        return this.kcPersonService;
    }
    
    public int getCurrentSubmissionNumber() {
        return currentSubmissionNumber;
    }

    public void setCurrentSubmissionNumber(int currentSubmissionNumber) {
        this.currentSubmissionNumber = currentSubmissionNumber;
    }

    

// TODO *********commented the code below during IACUC refactoring*********     
//    public int getTotalSubmissions() {
//        return getProtocolSubmitActionService().getTotalSubmissions(getProtocol());
//    }
    
    public abstract int getTotalSubmissions();
    
    /**
     * Sets up the summary details subpanel.
     * @throws Exception 
     */
    public void initSummaryDetails() throws Exception {
        if (currentSequenceNumber == -1) {
            currentSequenceNumber = getProtocol().getSequenceNumber();
        } else if (currentSequenceNumber > getProtocol().getSequenceNumber()) {
            currentSequenceNumber = getProtocol().getSequenceNumber();
        }
        
        protocolSummary =  null;
        String protocolNumber = getProtocol().getProtocolNumber();
        Protocol protocol = getProtocolVersionService().getProtocolVersion(protocolNumber, currentSequenceNumber);
        if (protocol != null) {
            protocolSummary = protocol.getProtocolSummary();
        }
        
        prevProtocolSummary = null;
        if (currentSequenceNumber > 0) {
            protocol = getProtocolVersionService().getProtocolVersion(protocolNumber, currentSequenceNumber - 1);
            if (protocol != null) {
                prevProtocolSummary = protocol.getProtocolSummary();
            }
        }
        
        if (protocolSummary != null && prevProtocolSummary != null) {
            protocolSummary.compare(prevProtocolSummary);
            prevProtocolSummary.compare(protocolSummary);
        }

        setSummaryQuestionnaireExist(hasAnsweredQuestionnaire((protocol.isAmendment() || protocol.isRenewal()) ? CoeusSubModule.AMENDMENT_RENEWAL : CoeusSubModule.ZERO_SUBMODULE, protocol.getSequenceNumber().toString()));
    }

    /**
     * Sets up dates for the submission details subpanel.
     */
    public void initSubmissionDetails() {
        if (currentSubmissionNumber <= 0) {
            currentSubmissionNumber = getTotalSubmissions();
        }

        if (CollectionUtils.isNotEmpty(getProtocol().getProtocolSubmissions()) && getProtocol().getProtocolSubmissions().size() > 1) {
            setPrevNextFlag();
        } else {
            setPrevDisabled(true);
            setNextDisabled(true);
        }
      
        setReviewComments(getReviewerCommentsService().getReviewerComments(getProtocol().getProtocolNumber(), currentSubmissionNumber));
        if (CollectionUtils.isNotEmpty(getReviewComments())) {
            // check if our comments bean has empty list of review comments, this can happen if the submission has no schedule assigned
            // also check that the list of deleted comments is empty, because deletion of comments can also lead to an empty list of review comments.
            if( (protocolManageReviewCommentsBean.getReviewCommentsBean().getReviewComments().size() == 0) 
                    && 
                (protocolManageReviewCommentsBean.getReviewCommentsBean().getDeletedReviewComments().size() == 0) ) {
                // TODO OPTIMIZATION perhaps the call below is not needed, can simply use getReviewComments since the review comments have been set above 
                List<CommitteeScheduleMinute> reviewComments = getReviewerCommentsService().getReviewerComments(getProtocol().getProtocolNumber(), currentSubmissionNumber);
                Collections.sort(reviewComments, new Comparator<CommitteeScheduleMinute>() {

                    @Override
                    public int compare(CommitteeScheduleMinute csm1, CommitteeScheduleMinute csm2) {
                        int retVal = 0;
                        if( (csm1 != null) && (csm2 != null) && (csm1.getEntryNumber() != null) && (csm2.getEntryNumber() != null) ) {
                            retVal = csm1.getEntryNumber().compareTo(csm2.getEntryNumber());
                        }
                        return retVal;
                    }
                    
                });
                protocolManageReviewCommentsBean.getReviewCommentsBean().setReviewComments(reviewComments);
                getReviewerCommentsService().setHideReviewerName(reviewComments);
            }
            getReviewerCommentsService().setHideReviewerName(getReviewComments());
        }
        setReviewAttachments(getReviewerCommentsService().getReviewerAttachments(getProtocol().getProtocolNumber(), currentSubmissionNumber));
        if (CollectionUtils.isNotEmpty(getReviewAttachments())) {
            hideReviewerNameForAttachment = getReviewerCommentsService().setHideReviewerName(getReviewAttachments());
            getReviewerCommentsService().setHideViewButton(getReviewAttachments());
        }
        getProtocol().getProtocolSubmission().refreshReferenceObject("reviewAttachments");
//        setReviewAttachments(getProtocol().getProtocolSubmission().getReviewAttachments());
        hideSubmissionReviewerName = checkToHideSubmissionReviewerName();

        setProtocolReviewers(getReviewerCommentsService().getProtocolReviewers(getProtocol().getProtocolNumber(), currentSubmissionNumber));
        setAbstainees(getCommitteeDecisionService().getAbstainers(getProtocol().getProtocolNumber(), currentSubmissionNumber));
        setRecusers(getCommitteeDecisionService().getRecusers(getProtocol().getProtocolNumber(), currentSubmissionNumber));

// TODO *********commented the code below during IACUC refactoring*********         
//        setSubmissionQuestionnaireExist(hasAnsweredQuestionnaire(CoeusSubModule.PROTOCOL_SUBMISSION, Integer.toString(currentSubmissionNumber)));
    }
    
    
    public void setCurrentTask(String currentTaskName) {
        this.currentTaskName = currentTaskName;
    }
    
    public String getCurrentTask() {
        return currentTaskName;
    }
    
    
    /**
     * This method populates the protocolAmendmentBean with the amendment details from the 
     * current submission.
     * @throws Exception
     */
    protected void setAmendmentDetails() throws Exception {
        /*
         * Check if the user is trying to modify amendment sections, if so, do not setAmendmentDetials.
         * If you set it, the user's data gets refreshed and the amendment details from the currentSubmission
         * will be populated in the protocolAmendmentBean.
         */
        if (!currentTaskName.equalsIgnoreCase(TaskName.MODIFY_PROTOCOL_AMMENDMENT_SECTIONS)) {
            ProtocolAmendmentBean amendmentBean = getProtocolAmendmentBean();
            String originalProtocolNumber;
            // Use the submission number to get the correct amendment details
            if (getProtocol().isAmendment()) {
                originalProtocolNumber = getProtocol().getProtocolAmendRenewal().getProtocolNumber();           
            } else {
                // We want to display amendment details even if the document is not an amendment.
                // Amendment details needs to be displayed even after the amendment has been merged with the protocol.
                originalProtocolNumber = getProtocol().getProtocolNumber();
            }
            List<Protocol> protocols = getProtocolAmendRenewServiceHook().getAmendmentAndRenewals(originalProtocolNumber);

            ProtocolAmendRenewal correctAmendment = getCorrectAmendment(protocols);
            if (ObjectUtils.isNotNull(correctAmendment)) {
                setSubmissionHasNoAmendmentDetails(false);
                amendmentBean.setSummary(correctAmendment.getSummary());
                enableModuleOption(amendmentBean, correctAmendment);
            } else {
                setSubmissionHasNoAmendmentDetails(true);
            }
        }
    }
    
    public void setSubmissionHasNoAmendmentDetails(boolean submissionHasNoAmendmentDetails) {
        this.submissionHasNoAmendmentDetails = submissionHasNoAmendmentDetails;
    }
    
    public boolean getSubmissionHasNoAmendmentDetails() {
        return submissionHasNoAmendmentDetails;
    }
    
    /**
     * This method returns the amendRenewal bean with the current submission number. 
     * @param protocols
     * @return
     */
    protected ProtocolAmendRenewal getCorrectAmendment(List<Protocol> protocols) {
        for (Protocol protocol : protocols) {
            // There should always be an amendment with the current submission number.
            if (protocol.isAmendment() && ObjectUtils.isNotNull(protocol.getProtocolSubmission().getSubmissionNumber()) 
                && protocol.getProtocolSubmission().getSubmissionNumber() == currentSubmissionNumber) {
                return protocol.getProtocolAmendRenewal();
            }
        }
        return null;
    }
    
    
    
    protected boolean hasAnsweredQuestionnaire(String moduleSubItemCode, String moduleSubItemKey) {
        return getAnswerHeaderCount(moduleSubItemCode, moduleSubItemKey) > 0;
    }

    protected int getAnswerHeaderCount(String moduleSubItemCode, String moduleSubItemKey) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("moduleItemCode", getCoeusModule());
        fieldValues.put("moduleItemKey", getProtocol().getProtocolNumber());
        fieldValues.put("moduleSubItemCode", moduleSubItemCode);
        fieldValues.put("moduleSubItemKey", moduleSubItemKey);
        return getBusinessObjectService().countMatching(AnswerHeader.class, fieldValues);
        
    }
    
    protected abstract String getCoeusModule();
    
//    /*
//     * This will check whether there is submission questionnaire.
//     * When business rule is implemented, this will become more complicated because
//     * each request action may have different set of questionnaire, so this has to be changed.
//     */
//    private boolean hasSubmissionQuestionnaire() {
//        ModuleQuestionnaireBean moduleQuestionnaireBean = new ModuleQuestionnaireBean(CoeusModule.IRB_MODULE_CODE, this.getProtocolForm().getProtocolDocument().getProtocol().getProtocolNumber() + "T", CoeusSubModule.PROTOCOL_SUBMISSION, "999", false);
//        return CollectionUtils.isNotEmpty(getQuestionnaireAnswerService().getQuestionnaireAnswer(moduleQuestionnaireBean));
//    }

    private QuestionnaireAnswerService getQuestionnaireAnswerService() {
        return KraServiceLocator.getService(QuestionnaireAnswerService.class);
    }

    /**
     * 
     * This method is to get previous submission number.  Current implementation is based on submission number in sequence.
     * If multiple amendment/renewal are submitted, but approved not according to submission order.  Then we may have gaping submission number.
     * @return
     */
    public int getPrevSubmissionNumber() {
        List<Integer> submissionNumbers = getAvailableSubmissionNumbers();
        Integer submissionNumber = currentSubmissionNumber - 1;
        if (!submissionNumbers.contains(submissionNumber)) {
            for (int i = currentSubmissionNumber - 1; i > 0; i--) {
                if (submissionNumbers.contains(i)) {
                    submissionNumber = i;
                    break;
                }
            }
        }
        return submissionNumber;

    }
    
    /**
     * 
     * This method is to get next submissionnumber
     * @return
     */
    public int getNextSubmissionNumber() {
        List<Integer> submissionNumbers = getAvailableSubmissionNumbers();
        int maxSubmissionNumber = getMaxSubmissionNumber();

        Integer submissionNumber = currentSubmissionNumber + 1;
        if (!submissionNumbers.contains(submissionNumber)) {
            for (int i = currentSubmissionNumber + 1; i <= maxSubmissionNumber; i++) {
                if (submissionNumbers.contains(i)) {
                    submissionNumber = i;
                    break;
                }
            }
        }
        return submissionNumber;

    }
    
    /*
     * this returns a list of submission numbers for a protocol.
     */
    protected List<Integer> getAvailableSubmissionNumbers() {
        List<Integer> submissionNumbers = new ArrayList<Integer>();
        for (ProtocolSubmission submission : getProtocol().getProtocolSubmissions()) {
            submissionNumbers.add(submission.getSubmissionNumber());
        }
        return submissionNumbers;
    }    

    protected int getMaxSubmissionNumber() {
        int maxSubmissionNumber = 0;

        for (Integer submissionNumber : getAvailableSubmissionNumbers()) {
            if (submissionNumber > maxSubmissionNumber) {
                maxSubmissionNumber = submissionNumber;
            }
        }
        return maxSubmissionNumber;
    }

  

    /*
     * utility method to set whether to display next or previous button on submission panel.
     */
    protected void setPrevNextFlag() {
        int maxSubmissionNumber = 0;
        int minSubmissionNumber = 0;
        setPrevDisabled(false);
        setNextDisabled(false);

        for (ProtocolSubmission submission : getProtocol().getProtocolSubmissions()) {
            if (submission.getSubmissionNumber() > maxSubmissionNumber) {
                maxSubmissionNumber = submission.getSubmissionNumber();
            }
            if (submission.getSubmissionNumber() < minSubmissionNumber || minSubmissionNumber == 0) {
                minSubmissionNumber = submission.getSubmissionNumber();
            }
        }
        if (currentSubmissionNumber == minSubmissionNumber) {
            setPrevDisabled(true);
        }
        if (currentSubmissionNumber == maxSubmissionNumber) {
            setNextDisabled(true);
        }
    }

    /**
     * This method returns true if a protocol has amendments
     * @return
     * @throws Exception
     */
    public boolean getHasAmendments() throws Exception {
        if (getProtocol().isAmendment()) {
            hasAmendments = true;
        } else {
            List<Protocol> protocols = (List<Protocol>) getProtocolAmendRenewServiceHook().getAmendments(getProtocol().getProtocolNumber());
            hasAmendments = protocols.isEmpty() ? false : true;
        } 
        return hasAmendments;
    }
    
    
    /**
     * This method returns true if a protocol has renewals.
     * @return
     * @throws Exception
     */
    public boolean getHasRenewals() throws Exception {
        if (getProtocol().isRenewal()) {
            hasRenewals = true;
        } else {
            List<Protocol> protocols = (List<Protocol>) getProtocolAmendRenewServiceHook().getRenewals(getProtocol().getProtocolNumber());
            hasRenewals = protocols.isEmpty() ? false : true;
        }
        return hasRenewals;
    }
    
// TODO *********commented the code below during IACUC refactoring*********         
//    public void addNotifyIrbAttachment() {
//        getProtocolNotifyIrbBean().getActionAttachments().add(
//                getProtocolNotifyIrbBean().getNewActionAttachment());
//        getProtocolNotifyIrbBean().setNewActionAttachment(new ProtocolActionAttachment());
//    }
//
//    public boolean validFile(final ProtocolActionAttachment attachment, String propertyName) {
//        
//        boolean valid = true;
//        
//        //this got much more complex using anon keys
//        if (attachment.getFile() == null || StringUtils.isBlank(attachment.getFile().getFileName())) {
//            valid = false;
//            new ErrorReporter().reportError("actionHelper." + propertyName + ".newActionAttachment.file",
//                KeyConstants.ERROR_ATTACHMENT_REQUIRED);
//        }
//        
//        return valid;
//    }

    public List<ProtocolVoteRecused> getRecusers() {
        return recusers;
    }

    public void setRecusers(List<ProtocolVoteRecused> recusers) {
        this.recusers = recusers;
    }
    
    /**
     * 
     * This method determines whether the committee select list should be displayed or not.
     * @return
     */
    public boolean isShowCommittee() { 
        return "O".equals(this.getSubmissionConstraint()) || "M".equals(this.getSubmissionConstraint());
    }

// TODO *********commented the code below during IACUC refactoring*********         
//    public ProtocolGenericActionBean getProtocolDeferBean() {
//        return protocolDeferBean;
//    }
//    
//    public ProtocolReviewNotRequiredBean getProtocolReviewNotRequiredBean() {
//        return this.protocolReviewNotRequiredBean;
//    }

    public boolean isPrevDisabled() {
        return prevDisabled;
    }

    public void setPrevDisabled(boolean prevDisabled) {
        this.prevDisabled = prevDisabled;
    }

    public boolean isNextDisabled() {
        return nextDisabled;
    }

    public void setNextDisabled(boolean nextDisabled) {
        this.nextDisabled = nextDisabled;
    }

    public List<ProtocolReviewer> getProtocolReviewers() {
        return protocolReviewers;
    }

    public void setProtocolReviewers(List<ProtocolReviewer> protocolReviewers) {
        this.protocolReviewers = protocolReviewers;
    }

  
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }


    public String getRenewalSummary() {
        return renewalSummary;
    }


    public void setRenewalSummary(String renewalSummary) {
        this.renewalSummary = renewalSummary;
    }


    /**
     * Sets the protocolSummaryPrintOptions attribute value.
     * @param protocolSummaryPrintOptions The protocolSummaryPrintOptions to set.
     */
    public void setProtocolSummaryPrintOptions(ProtocolSummaryPrintOptions protocolSumamryPrintOptions) {
        this.protocolSummaryPrintOptions = protocolSumamryPrintOptions;
    }


    /**
     * Gets the protocolSummaryPrintOptions attribute. 
     * @return Returns the protocolSummaryPrintOptions.
     */
    public ProtocolSummaryPrintOptions getProtocolSummaryPrintOptions() {
        return protocolSummaryPrintOptions;
    }
    
    public ProtocolActionBean getActionBean(String taskName) {
        return actionBeanTaskMap.get(taskName);
    }

// TODO *********commented the code below during IACUC refactoring*********     
//    public ProtocolRequestBean getRequestBean(String actionTypeCode) {
//        ProtocolRequestBean protocolRequestBean = null;
//        
//        ProtocolRequestAction action = ProtocolRequestAction.valueOfActionTypeCode(actionTypeCode);
//        if (action != null) {
//            ProtocolActionBean bean = actionBeanTaskMap.get(action.getTaskName());
//            if (bean instanceof ProtocolRequestBean) {
//                protocolRequestBean = (ProtocolRequestBean) bean;
//            }
//        }
//        
//        return protocolRequestBean;
//    }

    public Boolean getSummaryReport() {
        return summaryReport;
    }

    public void setSummaryReport(Boolean summaryReport) {
        this.summaryReport = summaryReport;
    }

    public Boolean getFullReport() {
        return fullReport;
    }

    public void setFullReport(Boolean fullReport) {
        this.fullReport = fullReport;
    }

    public Boolean getHistoryReport() {
        return historyReport;
    }

    public void setHistoryReport(Boolean historyReport) {
        this.historyReport = historyReport;
    }

    public Boolean getReviewCommentsReport() {
        return reviewCommentsReport;
    }

    public void setReviewCommentsReport(Boolean reviewCommentsReport) {
        this.reviewCommentsReport = reviewCommentsReport;
    }

//    public boolean isSubmissionQuestionnaireExist() {
//        return submissionQuestionnaireExist;
//    }
//
//    public void setSubmissionQuestionnaireExist(boolean submissionQuestionnaireExist) {
//        this.submissionQuestionnaireExist = submissionQuestionnaireExist;
//    }
//
//    public boolean isToAnswerSubmissionQuestionnaire() {
//        return toAnswerSubmissionQuestionnaire;
//    }
//
//    public void setToAnswerSubmissionQuestionnaire(boolean toAnswerSubmissionQuestionnaire) {
//        this.toAnswerSubmissionQuestionnaire = toAnswerSubmissionQuestionnaire;
//    }
//
    public ProtocolSummaryPrintOptions getProtocolPrintOption() {
        return protocolPrintOption;
    }

    public void setProtocolPrintOption(ProtocolSummaryPrintOptions protocolPrintOption) {
        this.protocolPrintOption = protocolPrintOption;
    }

    public List<QuestionnairePrintOption> getQuestionnairesToPrints() {
        return questionnairesToPrints;
    }

    public void setQuestionnairesToPrints(List<QuestionnairePrintOption> questionnairesToPrints) {
        this.questionnairesToPrints = questionnairesToPrints;
    }

    private void initPrintQuestionnaire() {
        setQuestionnairesToPrints(new ArrayList<QuestionnairePrintOption>());
        ModuleQuestionnaireBean moduleQuestionnaireBean = getNewProtocolModuleQuestionnaireBeanInstanceHook(getProtocol());
        //answerHeaders = getQuestionnaireAnswerService().getQuestionnaireAnswer(moduleQuestionnaireBean);
        List<AnswerHeader> answerHeaders  = getQuestionnaireAnswerService().getAnswerHeadersForProtocol(moduleQuestionnaireBean, getProtocol().getProtocolNumber());
        setupQnPrintOption(answerHeaders);
    }

    /*
     * This method is to set up the questionnaire print list.  Then sorted it.
     */
    private void setupQnPrintOption(List<AnswerHeader> answerHeaders) {
        getProtocolQuestionnairePrintingServiceHook().setupQnPrintOption(answerHeaders, getProtocol(), getQuestionnairesToPrints());
    }
    
    protected abstract ProtocolQuestionnairePrintingService getProtocolQuestionnairePrintingServiceHook();
    
    public boolean isSummaryQuestionnaireExist() {
        return summaryQuestionnaireExist;
    }

    public void setSummaryQuestionnaireExist(boolean summaryQuestionnaireExist) {
        this.summaryQuestionnaireExist = summaryQuestionnaireExist;
    }

    public boolean isCanAbandon() {
        return canAbandon;
    }

    public void setCanAbandon(boolean canAbandon) {
        this.canAbandon = canAbandon;
    }

    public ProtocolGenericActionBean getProtocolAbandonBean() {
        return protocolAbandonBean;
    }

    public void setProtocolAbandonBean(ProtocolGenericActionBean protocolAbandonBean) {
        this.protocolAbandonBean = protocolAbandonBean;
    }

    public boolean isHideReviewerName() {
        return hideReviewerName;
    }

    public void setHideReviewerName(boolean hideReviewerName) {
        this.hideReviewerName = hideReviewerName;
    }
    
    /*
     * check if to display reviewer name for any of the review comments of the submission selected in submission details..
     */
    private boolean checkToHideSubmissionReviewerName() {
        boolean isHide = true;
        for (CommitteeScheduleMinute reviewComment : getReviewComments()) {
            if (reviewComment.isDisplayReviewerName()) {
                isHide = false;
                break;
            }
        }
        return isHide;
    }
    
    /*
     * check if to display reviewer name for any of the review comments of current submission.
     */
    private boolean checkToHideReviewName() {
        boolean isHide = true;
        if (getProtocol().getProtocolSubmission().getSubmissionId() != null) {
            isHide = getReviewerCommentsService().setHideReviewerName(getProtocol(), getProtocol().getProtocolSubmission().getSubmissionNumber());
        }
        return isHide;
    }

    public boolean isHideSubmissionReviewerName() {
        return hideSubmissionReviewerName;
    }

    public void setHideSubmissionReviewerName(boolean hideSubmissionReviewerName) {
        this.hideSubmissionReviewerName = hideSubmissionReviewerName;
    }

    public List<? extends ProtocolReviewAttachment> getReviewAttachments() {
        return reviewAttachments;
    }

    public void setReviewAttachments(List<? extends ProtocolReviewAttachment> reviewAttachments) {
        this.reviewAttachments = reviewAttachments;
    }

    public boolean isHideReviewerNameForAttachment() {
        return hideReviewerNameForAttachment;
    }

    public void setHideReviewerNameForAttachment(boolean hideReviewerNameForAttachment) {
        this.hideReviewerNameForAttachment = hideReviewerNameForAttachment;
    }

    public ProtocolCorrespondence getProtocolCorrespondence() {
        return protocolCorrespondence;
    }

    public void setProtocolCorrespondence(ProtocolCorrespondence protocolCorrespondence) {
        this.protocolCorrespondence = protocolCorrespondence;
    }

// TODO *********commented the code below during IACUC refactoring*********     
//    public boolean isIrbAdmin() {
//        return getKraAuthorizationService().hasRole(GlobalVariables.getUserSession().getPrincipalId(), NAMESPACE, RoleConstants.IRB_ADMINISTRATOR);
//    }
    
    protected KraAuthorizationService getKraAuthorizationService() {
        if (this.kraAuthorizationService == null) {
            this.kraAuthorizationService = KraServiceLocator.getService(KraAuthorizationService.class);
        }
        
        return this.kraAuthorizationService;
    }

    
    protected abstract ProtocolModuleQuestionnaireBean getNewProtocolModuleQuestionnaireBeanInstanceHook(Protocol protocol);
    
    protected abstract ProtocolAmendRenewService getProtocolAmendRenewServiceHook();
    
    /**
     * This method copies the settings from the ProtocolAmendRenewal bo to the amendmentBean and enables the
     * corresponding modules. 
     * Set appropriate details in subclass
     * @param amendmentBean
     * @param moduleTypeCodes
     */
    protected abstract void populateExistingAmendmentBean(ProtocolAmendmentBean amendmentBean, List<String> moduleTypeCodes);
    
    /**
     * Enable a module for selection by a user by setting its corresponding enabled
     * flag to true in the amendment bean.
     * Set appropriate details in subclass
     * This method...
     * @param moduleTypeCode
     * @param amendmentBean
     */
    protected abstract void enableModuleOption(String moduleTypeCode, ProtocolEditableBean amendmentBean);
    
    /**
     * Enable module option while populating the protocolAmendmentBean with the amendment details from the 
     * current submission.
     * Set appropriate details in subclass
     * @param amendmentBean
     * @param correctAmendment
     */
    protected abstract void enableModuleOption(ProtocolAmendmentBean amendmentBean, ProtocolAmendRenewal correctAmendment);


    public ProtocolGenericActionBean getProtocolReturnToPIBean() {
        return protocolReturnToPIBean;
    }


    public boolean isCanReturnToPI() {
        return canReturnToPI;
    }


    public boolean isCanReturnToPIUnavailable() {
        return canReturnToPIUnavailable;
    }
}
