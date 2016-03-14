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
package org.kuali.kra.protocol.actions;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.committee.impl.lookup.keyvalue.CommitteeIdByUnitValuesFinderService;
import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleMinuteBase;
import org.kuali.coeus.common.committee.impl.meeting.ProtocolVoteAbstaineeBase;
import org.kuali.coeus.common.committee.impl.meeting.ProtocolVoteRecusedBase;
import org.kuali.coeus.common.committee.impl.service.CommitteeScheduleServiceBase;
import org.kuali.coeus.common.framework.auth.SystemAuthorizationService;
import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.controller.DocHandlerService;
import org.kuali.coeus.sys.framework.keyvalue.KeyValueComparator;
import org.kuali.coeus.sys.framework.keyvalue.PrefixValuesFinder;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolFormBase;
import org.kuali.kra.protocol.ProtocolVersionService;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewService;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewalBase;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendmentBean;
import org.kuali.kra.protocol.actions.approve.ProtocolApproveBean;
import org.kuali.kra.protocol.actions.assignagenda.ProtocolAssignToAgendaBean;
import org.kuali.kra.protocol.actions.correction.AdminCorrectionBean;
import org.kuali.kra.protocol.actions.decision.CommitteeDecision;
import org.kuali.kra.protocol.actions.decision.CommitteeDecisionService;
import org.kuali.kra.protocol.actions.delete.ProtocolDeleteBean;
import org.kuali.kra.protocol.actions.followup.FollowupActionService;
import org.kuali.kra.protocol.actions.genericactions.ProtocolGenericActionBean;
import org.kuali.kra.protocol.actions.notifycommittee.ProtocolNotifyCommitteeBean;
import org.kuali.kra.protocol.actions.print.CorrespondencePrintOption;
import org.kuali.kra.protocol.actions.print.ProtocolQuestionnairePrintingService;
import org.kuali.kra.protocol.actions.print.ProtocolSummaryPrintOptions;
import org.kuali.kra.protocol.actions.print.QuestionnairePrintOption;
import org.kuali.kra.protocol.actions.request.ProtocolRequestBean;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsService;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewer;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmitAction;
import org.kuali.kra.protocol.actions.submit.ValidProtocolActionActionBase;
import org.kuali.kra.protocol.actions.undo.UndoLastActionBean;
import org.kuali.kra.protocol.actions.withdraw.ProtocolAdministrativelyIncompleteBean;
import org.kuali.kra.protocol.actions.withdraw.ProtocolAdministrativelyWithdrawBean;
import org.kuali.kra.protocol.actions.withdraw.ProtocolWithdrawBean;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondence;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondenceAuthorizationService;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;
import org.kuali.kra.protocol.onlinereview.ProtocolReviewAttachmentBase;
import org.kuali.kra.protocol.questionnaire.ProtocolModuleQuestionnaireBeanBase;
import org.kuali.kra.protocol.questionnaire.ProtocolSubmissionQuestionnaireHelper;
import org.kuali.kra.protocol.summary.ProtocolSummary;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;
import org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.ken.util.NotificationConstants;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.document.DocumentStatus;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

/**
 * The form helper class for the ProtocolBase Actions tab.
 */
@SuppressWarnings("serial")
public abstract class ActionHelperBase implements Serializable {

    protected static final String NAMESPACE = "KC-UNT";
    protected transient QuestionnaireAnswerService questionnaireAnswerService;

    private static final String DEFAULT_TAB = "Versions";
    private static final String ALTERNATE_OPEN_TAB = "Parameters";
    
    /**
     * Each Helper must contain a reference to its document form
     * so that it can access the document.
     */
    protected ProtocolFormBase form;
    
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
    protected boolean canNotifyCommittee = false;
    protected boolean canNotifyCommitteeUnavailable = false;
    protected boolean canWithdraw = false;
    protected boolean canWithdrawUnavailable = false;
    protected boolean canRequestClose = false;
    protected boolean canRequestCloseUnavailable = false;
    protected boolean canRequestSuspension = false;
    protected boolean canRequestSuspensionUnavailable = false;
    protected boolean canRequestTerminate = false;
    protected boolean canRequestTerminateUnavailable = false;
    protected boolean canWithdrawSubmission = false;
    protected boolean canWithdrawSubmissionUnavailable = false;
    protected boolean canDeleteProtocolAmendRenew = false;
    protected boolean canDeleteProtocolAmendRenewUnavailable = false;
    protected boolean canAssignToAgenda = false;
    protected boolean canAssignToAgendaUnavailable = false;
    protected boolean canAssignCmtSched = false;
    protected boolean canAssignCmtSchedUnavailable = false;
    protected boolean canAssignReviewers = false;
    protected boolean canAssignReviewersCmtSel = false;
    protected boolean canAssignReviewersUnavailable = false;
    protected boolean canApproveFull = false;
    protected boolean canApproveFullUnavailable = false;
    protected boolean canApproveResponse = false;
    protected boolean canApproveResponseUnavailable = false;
    protected boolean canDisapprove = false;
    protected boolean canDisapproveUnavailable = false;
    protected boolean canReturnForSMR = false;
    protected boolean canReturnForSMRUnavailable = false;
    protected boolean canReturnForSRR = false;
    protected boolean canReturnForSRRUnavailable = false;
    protected boolean canSuspend = false;
    protected boolean canSuspendUnavailable = false;
    protected boolean canClose = false;
    protected boolean canCloseUnavailable = false;
    protected boolean canExpire = false;
    protected boolean canExpireUnavailable = false;
    protected boolean canTerminate = false;
    protected boolean canTerminateUnavailable = false;
    protected boolean canMakeAdminCorrection = false;
    protected boolean canMakeAdminCorrectionUnavailable = false;
    protected boolean canRecordCommitteeDecision = false;
    protected boolean canRecordCommitteeDecisionUnavailable = false;
    protected boolean canUndoLastAction = false;
    protected boolean canUndoLastActionUnavailable = false;
    protected boolean canModifyProtocolSubmission = false;
    protected boolean canModifyProtocolSubmissionUnavailable = false;
    protected boolean canManageReviewComments = false;
    protected boolean canManageReviewCommentsUnavailable = false;
    protected boolean canApproveOther = false;
    protected boolean canManageNotes = false;
    protected boolean canManageNotesUnavailable = false;
    protected boolean canAbandon = false;

    protected List<? extends ValidProtocolActionActionBase> followupActionActions;
    
    protected boolean canViewOnlineReviewers;
    protected boolean canViewOnlineReviewerComments;
    
    protected boolean canAddCloseReviewerComments;

    protected boolean canAddSuspendReviewerComments;
    protected boolean canAddTerminateReviewerComments;    
    
    // authorization flags for protocol correspondences
    protected boolean allowedToViewProtocolCorrespondence;
    protected boolean allowedToUpdateProtocolCorrespondence;
    protected boolean allowedToRegenerateProtocolCorrespondence;
    
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
    protected List<KeyValue> submitActionCommitteeIdByUnitKeyValues;

    protected ProtocolWithdrawBean protocolWithdrawBean;
    protected ProtocolRequestBean protocolCloseRequestBean;
    protected ProtocolRequestBean protocolSuspendRequestBean;    
    protected ProtocolRequestBean protocolTerminateRequestBean;
    
    protected ProtocolRequestBean protocolWithdrawCloseRequestBean;
    protected ProtocolRequestBean protocolWithdrawSuspendRequestBean;    
    protected ProtocolRequestBean protocolWithdrawTerminateRequestBean;
    
    protected ProtocolNotifyCommitteeBean protocolNotifyCommitteeBean;
    private List<KeyValue> notifyCmtActionCommitteeIdByUnitKeyValues;
    
    protected ProtocolAmendmentBean protocolAmendmentBean;
    protected ProtocolAmendmentBean protocolRenewAmendmentBean;
    protected ProtocolAmendmentBean protocolAmendmentSummaryBean;
    protected ProtocolDeleteBean protocolDeleteBean;
    protected ProtocolAssignToAgendaBean assignToAgendaBean;   
    private ProtocolApproveBean protocolFullApprovalBean;
    
    private ProtocolApproveBean protocolAdminApprovalBean;
    private ProtocolAdministrativelyWithdrawBean protocolAdminWithdrawBean;
    private ProtocolAdministrativelyIncompleteBean protocolAdminIncompleteBean;
    
    protected ProtocolGenericActionBean protocolDisapproveBean;
    protected ProtocolGenericActionBean protocolSMRBean;
    protected ProtocolGenericActionBean protocolSRRBean;
    
    protected ProtocolGenericActionBean protocolSuspendBean;    
    protected ProtocolGenericActionBean protocolCloseBean;
    protected ProtocolGenericActionBean protocolExpireBean;
    protected ProtocolGenericActionBean protocolTerminateBean;
    
    protected ProtocolGenericActionBean protocolReturnToPIBean;
    protected AdminCorrectionBean protocolAdminCorrectionBean;
    protected UndoLastActionBean undoLastActionBean;
    protected CommitteeDecision<?> committeeDecision;
    protected ProtocolGenericActionBean protocolManageReviewCommentsBean;
    protected ProtocolGenericActionBean protocolAbandonBean;

    protected String currentTaskName = "";
    protected boolean prevDisabled;
    protected boolean nextDisabled;
    protected transient ParameterService parameterService;
    protected transient TaskAuthorizationService taskAuthorizationService;
    protected transient ProtocolAmendRenewService protocolAmendRenewService;
    protected transient ProtocolVersionService protocolVersionService;
    protected boolean hasAmendments;
    protected boolean hasRenewals;
    protected boolean submissionHasNoAmendmentDetails;
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
    protected List<CommitteeScheduleMinuteBase> reviewComments;        
    protected List<? extends ProtocolReviewAttachmentBase> reviewAttachments;        
    protected List<ProtocolVoteAbstaineeBase> abstainees;        
    protected List<ProtocolVoteRecusedBase> recusers;
    
    // the reviewers for the currently selected submission
    protected List<ProtocolReviewer> currentReviewers = new ArrayList<ProtocolReviewer>();
    // mapping for current online reviews keyed by the current reviewer id
    private HashMap<String, ProtocolOnlineReviewBase> onlineReviewsMap = new HashMap<String, ProtocolOnlineReviewBase>(); 
    
    protected int currentSubmissionNumber;
    protected String renewalSummary;

    @SuppressWarnings("rawtypes")
    protected transient CommitteeScheduleServiceBase committeeScheduleService;
    protected transient KcPersonService kcPersonService;
    protected transient SystemAuthorizationService systemAuthorizationService;
    protected transient BusinessObjectService businessObjectService;
    protected transient FollowupActionService<?> followupActionService;
        
    protected Map<String,Boolean> followupActionMap = new HashMap<String,Boolean>();
    
    protected Map<String, ProtocolActionBean> actionBeanTaskMap = new HashMap<String, ProtocolActionBean>();    
    // protocol print
    protected ProtocolSummaryPrintOptions protocolSummaryPrintOptions;
    // TODO ProtocolSummaryPrintOptions should be refactored into IRB and IACUC subclasses and the below constructor should be replaced with a hook invocation.
    protected ProtocolSummaryPrintOptions protocolPrintOption = new ProtocolSummaryPrintOptions();
    protected List<QuestionnairePrintOption> questionnairesToPrints;
    protected List<CorrespondencePrintOption> correspondencesToPrint;
    
    // flag if versioned protocol questionnaire exist
    protected boolean summaryQuestionnaireExist;
    protected boolean hideReviewerName;
    protected boolean hideSubmissionReviewerName;
    protected boolean hideReviewerNameForAttachment;
    //flag to hide private final columns/attachments from PI when they are public
    protected boolean hidePrivateFinalFlagsForPublicCommentsAttachments; 
    protected ProtocolCorrespondence protocolCorrespondence;
    
    // indicator for whether there is submission questionnaire answer exist.
    // ie, questionnaire has been saved for a request/notify irb action
    protected boolean submissionQuestionnaireExist;
    // check if there is submission questionnaire to answer
    protected boolean toAnswerSubmissionQuestionnaire;
    protected ProtocolSubmissionQuestionnaireHelper protocolSubmissionQuestionnaireHelper;

    /**
     * Constructs an ActionHelperBase.
     * @param form the protocol form
     * @throws Exception 
     */
    public ActionHelperBase(ProtocolFormBase form) throws Exception {
        this.form = form;
    }
    
    
    public void initializeProtocolActions() throws Exception {
        protocolSubmitAction = getNewProtocolSubmitActionInstanceHook(this);
        protocolWithdrawBean = getNewProtocolWithdrawBeanInstanceHook(this);

        createAmendmentBean();
        
        protocolNotifyCommitteeBean = getNewProtocolNotifyCommitteeBeanInstanceHook(this);

                
        protocolDeleteBean = getNewProtocolDeleteBeanInstanceHook(this);      
        assignToAgendaBean = getNewProtocolAssignToAgendaBeanInstanceHook(this);         
        assignToAgendaBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());        
        protocolFullApprovalBean = buildProtocolApproveBean(getFullApprovalProtocolActionTypeHook(), Constants.PROTOCOL_FULL_APPROVAL_ACTION_PROPERTY_KEY);

        protocolAdminApprovalBean = buildProtocolApproveBean(getAdminApprovalProtocolActionTypeHook(), Constants.PROTOCOL_ADMIN_APPROVAL_ACTION_PROPERTY_KEY);
        protocolAdminWithdrawBean = getNewProtocolAdminWithdrawBeanInstanceHook(this);
        protocolAdminIncompleteBean = getNewProtocolAdminIncompleteBeanInstanceHook(this);
         
        protocolDisapproveBean = buildProtocolGenericActionBean(getDisapprovedProtocolActionTypeHook(), Constants.PROTOCOL_DISAPPROVE_ACTION_PROPERTY_KEY);
        protocolSMRBean = buildProtocolGenericActionBean(getSMRProtocolActionTypeHook(), Constants.PROTOCOL_SMR_ACTION_PROPERTY_KEY);  
        protocolSRRBean = buildProtocolGenericActionBean(getSRRProtocolActionTypeHook(), Constants.PROTOCOL_SRR_ACTION_PROPERTY_KEY);
        protocolReturnToPIBean = buildProtocolGenericActionBean(getReturnToPIActionTypeHook(), Constants.PROTOCOL_RETURN_TO_PI_PROPERTY_KEY);
        protocolSuspendBean = buildProtocolGenericActionBean(getSuspendKeyHook(), Constants.PROTOCOL_SUSPEND_ACTION_PROPERTY_KEY);
        protocolExpireBean = buildProtocolGenericActionBean(getExpireKeyHook(), Constants.PROTOCOL_EXPIRE_ACTION_PROPERTY_KEY);
        protocolTerminateBean = buildProtocolGenericActionBean(getTerminateKeyHook(), Constants.PROTOCOL_TERMINATE_ACTION_PROPERTY_KEY);        
        protocolAbandonBean = buildProtocolGenericActionBean(getAbandonActionTypeHook(), getAbandonPropertyKeyHook());
        
          
        protocolAdminCorrectionBean = createAdminCorrectionBean();
        undoLastActionBean = getNewUndoLastActionBeanInstanceHook();
        
        committeeDecision = getNewCommitteeDecisionInstanceHook(this);
        committeeDecision.init();
        committeeDecision.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());

        protocolManageReviewCommentsBean = buildProtocolGenericActionBean(getProtocolActionTypeCodeForManageReviewCommentsHook(), 
                Constants.PROTOCOL_MANAGE_REVIEW_COMMENTS_KEY);
        getProtocol().getProtocolSubmission().refreshReferenceObject("reviewAttachments");
        protocolManageReviewCommentsBean.getReviewAttachmentsBean().setReviewAttachments(getProtocol().getProtocolSubmission().getReviewAttachments());
        if (CollectionUtils.isNotEmpty(protocolManageReviewCommentsBean.getReviewAttachmentsBean().getReviewAttachments())) {
            protocolManageReviewCommentsBean.getReviewAttachmentsBean().setHideReviewerName(getReviewerCommentsService().setHideReviewerName(protocolManageReviewCommentsBean.getReviewAttachmentsBean().getReviewAttachments()));
        }
        
        initActionBeanTaskMap();        
    }
    

    protected abstract String getSRRProtocolActionTypeHook();

    protected abstract String getSMRProtocolActionTypeHook();
    
    protected abstract String getReturnToPIActionTypeHook();

    protected abstract String getDisapprovedProtocolActionTypeHook();

    protected abstract String getProtocolActionTypeCodeForManageReviewCommentsHook();    
    
    protected abstract CommitteeDecision<?> getNewCommitteeDecisionInstanceHook(ActionHelperBase actionHelper);    

    protected abstract ProtocolAssignToAgendaBean getNewProtocolAssignToAgendaBeanInstanceHook(ActionHelperBase actionHelper);

    protected abstract ProtocolAdministrativelyWithdrawBean getNewProtocolAdminWithdrawBeanInstanceHook(ActionHelperBase actionHelper);
    
    protected abstract ProtocolAdministrativelyIncompleteBean getNewProtocolAdminIncompleteBeanInstanceHook(ActionHelperBase actionHelper);
    
    protected abstract String getAdminApprovalProtocolActionTypeHook();
    
    protected abstract String getFullApprovalProtocolActionTypeHook();

    protected abstract ProtocolWithdrawBean getNewProtocolWithdrawBeanInstanceHook(ActionHelperBase actionHelper);

    protected abstract ProtocolAmendmentBean getNewProtocolAmendmentBeanInstanceHook(ActionHelperBase actionHelper);
    
    protected abstract ProtocolNotifyCommitteeBean getNewProtocolNotifyCommitteeBeanInstanceHook(ActionHelperBase actionHelper);

    protected abstract ProtocolSubmitAction getNewProtocolSubmitActionInstanceHook(ActionHelperBase actionHelper);
    
    protected abstract ProtocolDeleteBean getNewProtocolDeleteBeanInstanceHook(ActionHelperBase actionHelper);

    protected abstract AdminCorrectionBean getNewAdminCorrectionBeanInstanceHook(ActionHelperBase actionHelper);
    
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
        actionBeanTaskMap.put(TaskName.ASSIGN_TO_AGENDA, assignToAgendaBean);
        actionBeanTaskMap.put(TaskName.RECORD_COMMITTEE_DECISION, committeeDecision);
        actionBeanTaskMap.put(TaskName.PROTOCOL_AMEND_RENEW_DELETE, protocolDeleteBean);
        actionBeanTaskMap.put(TaskName.DISAPPROVE_PROTOCOL, protocolDisapproveBean);
        actionBeanTaskMap.put(TaskName.EXPIRE_PROTOCOL, protocolExpireBean);
        actionBeanTaskMap.put(TaskName.PROTOCOL_MANAGE_REVIEW_COMMENTS, protocolManageReviewCommentsBean);
        actionBeanTaskMap.put(TaskName.RETURN_FOR_SMR, protocolSMRBean);
        actionBeanTaskMap.put(TaskName.RETURN_FOR_SRR, protocolSRRBean);
        actionBeanTaskMap.put(TaskName.RETURN_TO_PI_PROTOCOL, protocolReturnToPIBean);
         
        actionBeanTaskMap.put(TaskName.SUBMIT_PROTOCOL, protocolSubmitAction);
        actionBeanTaskMap.put(TaskName.NOTIFY_COMMITTEE, protocolNotifyCommitteeBean);
        actionBeanTaskMap.put(TaskName.SUSPEND_PROTOCOL, protocolSuspendBean);
        actionBeanTaskMap.put(TaskName.TERMINATE_PROTOCOL, protocolTerminateBean);
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
        
        
        protected ReviewCommentsService getReviewCommentsService() {
            return KcServiceLocator.getService(getReviewCommentsServiceClassHook());
        }   
        
        protected abstract Class<? extends ReviewCommentsService> getReviewCommentsServiceClassHook();  

        
 
    protected ProtocolApproveBean buildProtocolApproveBean(String actionTypeCode, String errorPropertyKey) throws Exception {        
        ProtocolApproveBean bean = getNewProtocolApproveBeanInstanceHook(this, errorPropertyKey);       
        bean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        
        ProtocolActionBase protocolAction = findProtocolAction(actionTypeCode, getProtocol().getProtocolActions(), getProtocol().getProtocolSubmission());
        if (protocolAction != null) {
            bean.setComments(protocolAction.getComments());
            bean.setActionDate(new Date(protocolAction.getActionDate().getTime()));
        }
        bean.setApprovalDate(buildApprovalDate(getProtocol()));
        bean.setExpirationDate(buildExpirationDate(getProtocol(), bean.getApprovalDate()));
        bean.setDefaultExpirationDateDifference(this.getDefaultExpirationDateDifference());
        return bean;
    }

        
    protected abstract ProtocolApproveBean getNewProtocolApproveBeanInstanceHook(ActionHelperBase actionHelper, String errorPropertyKey);

    /**
     * Builds an approval date, defaulting to the approval date from the protocol.
     * 
     * If the approval date from the protocol is null, or if the protocol is new or a renewal, then if the committee has scheduled a meeting to approve the 
     * protocol, sets to the scheduled approval date; otherwise, sets to the current date.
     * 
     * @param protocol
     * @return a non-null approval date
     */
    protected Date buildApprovalDate(ProtocolBase protocol) {
        Date approvalDate = protocol.getApprovalDate();
        
        if (approvalDate == null || protocol.isNew() || protocol.isRenewal()) {
            CommitteeScheduleBase committeeSchedule = protocol.getProtocolSubmission().getCommitteeSchedule();
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
    protected Date buildExpirationDate(ProtocolBase protocol, Date approvalDate) {
        Date expirationDate = protocol.getExpirationDate();
        
        if (expirationDate == null || protocol.isNew() || protocol.isRenewal()) {
            java.util.Date newExpirationDate = DateUtils.addYears(approvalDate, getDefaultExpirationDateDifference());
            newExpirationDate = DateUtils.addDays(newExpirationDate, -1);
            expirationDate = org.kuali.coeus.sys.framework.util.DateUtils.convertToSqlDate(newExpirationDate);
        }
        
        return expirationDate;
    }

    protected ProtocolActionBase findProtocolAction(String actionTypeCode, List<ProtocolActionBase> protocolActions, ProtocolSubmissionBase currentSubmission) {

        for (ProtocolActionBase pa : protocolActions) {
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
        protocolAmendmentSummaryBean = getNewProtocolAmendmentBeanInstanceHook(this);
        configureAmendmentBean(protocolAmendmentBean);
        configureAmendmentBean(protocolRenewAmendmentBean);
        configureAmendmentBean(protocolAmendmentSummaryBean);
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
        List<String> moduleTypeCodes = getProtocolAmendRenewServiceHook().getAvailableModules(getProtocol().getProtocolNumber());
        
        for (String moduleTypeCode : moduleTypeCodes) {
            enableModuleOption(moduleTypeCode, adminCorrectionBean);
        }
        
        return adminCorrectionBean;
    }    

    public void prepareView() throws Exception {
        initializeSubmissionConstraintHook();
        prepareProtocolSubmitActionView();
        prepareNotifyCommitteeActionView();
                  
        assignToAgendaBean.prepareView();        
        canCreateAmendment = hasCreateAmendmentPermission();
        canCreateAmendmentUnavailable = hasCreateAmendmentUnavailablePermission();
        canModifyAmendmentSections = hasModifyAmendmentSectionsPermission();
        canModifyAmendmentSectionsUnavailable = hasModifyAmendmentSectionsUnavailablePermission();
        canCreateRenewal = hasCreateRenewalPermission();
        canCreateRenewalUnavailable = hasCreateRenewalUnavailablePermission();
        canWithdraw = hasWithdrawPermission();            
        canWithdrawUnavailable = hasWithdrawUnavailablePermission();
        canDeleteProtocolAmendRenew = hasDeleteProtocolAmendRenewPermission();
        canDeleteProtocolAmendRenewUnavailable = hasDeleteProtocolAmendRenewUnavailablePermission();
               
        canAssignToAgenda = hasAssignToAgendaPermission();
        canAssignToAgendaUnavailable = hasAssignToAgendaUnavailablePermission();
        canApproveFull = hasFullApprovePermission();
        canApproveFullUnavailable = hasFullApproveUnavailablePermission();
        canDisapprove = hasDisapprovePermission();
        canDisapproveUnavailable = hasDisapproveUnavailablePermission();
        
        canApproveOther = hasApproveOtherPermission();
                
        canReturnForSMR = hasReturnForSMRPermission();
        canReturnForSMRUnavailable = hasReturnForSMRUnavailablePermission();
        canReturnForSRR = hasReturnForSRRPermission();
        canReturnForSRRUnavailable = hasReturnForSRRUnavailablePermission();
        canSuspend = hasSuspendPermission();
        canSuspendUnavailable = hasSuspendUnavailablePermission();
        canExpire = hasExpirePermission();
        canExpireUnavailable = hasExpireUnavailablePermission();
        canTerminate = hasTerminatePermission();
        canTerminateUnavailable = hasTerminateUnavailablePermission();

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
        canUndoLastAction = hasUndoLastActionPermission();
        canUndoLastActionUnavailable = hasUndoLastActionUnavailablePermission();
        canModifyProtocolSubmission = hasCanModifySubmissionPermission();
        canModifyProtocolSubmissionUnavailable = hasCanModifySubmissionUnavailablePermission();
        canManageReviewComments = hasManageReviewCommentsPermission();
        canManageReviewCommentsUnavailable = hasManageReviewCommentsUnavailablePermission();
        canAbandon = hasAbandonProtocolPermission();
        
        followupActionActions = getFollowupActionService().getFollowupsForProtocol(form.getProtocolDocument().getProtocol());

        
        canViewOnlineReviewers = hasCanViewOnlineReviewersPermission();
        canViewOnlineReviewerComments = hasCanViewOnlineReviewerCommentsPermission();        
        canAddSuspendReviewerComments = hasSuspendPermission();
        canAddTerminateReviewerComments = hasTerminatePermission();
        
        initProtocolCorrespondenceAuthorizationFlags();
        
        hidePrivateFinalFlagsForPublicCommentsAttachments = checkToHidePrivateFinalFlagsForPublicCommentsAttachments();
        initSubmissionDetails();
        
        initAmendmentBeans(false);
        initPrintQuestionnaire();
        initPrintCorrespondence();
    }
    
            
    protected abstract void initializeSubmissionConstraintHook();

    
    protected List<KeyValue> getKeyValuesForCommitteeSelection(Collection<? extends CommitteeBase<?, ?, ?>> committees) {
        List<KeyValue> retVal = new ArrayList<KeyValue>();
        for(CommitteeBase<?, ?, ?> committee : committees) {
            retVal.add(new ConcreteKeyValue(committee.getCommitteeId(), committee.getCommitteeName()));
        }
        Collections.sort(retVal, new KeyValueComparator());
        retVal.add(0, new ConcreteKeyValue(PrefixValuesFinder.getPrefixKey(), PrefixValuesFinder.getDefaultPrefixValue()));
        return retVal;
    }


    private void prepareProtocolSubmitActionView() {        
        canSubmitProtocol = hasSubmitProtocolPermission();
        canSubmitProtocolUnavailable = hasSubmitProtocolUnavailablePermission();
        if(protocolSubmitAction == null) {
        	protocolSubmitAction = getNewProtocolSubmitActionInstanceHook(this);
        }
        protocolSubmitAction.prepareView();
        // Initialize the submit committee key values (expensive call) only after checking the conditions for the display of the committee selection
        if(canSubmitProtocol && isShowCommittee()) {            
            // pass in the lead unit of the protocol and the doc route status to the committee finder service
            Collection<? extends CommitteeBase<?, ?, ?>> committees = 
                getCommitteeIdByUnitValuesFinderService().getAssignmentCommittees(getProtocol().getLeadUnitNumber(), getDocRouteStatus(), null);
            submitActionCommitteeIdByUnitKeyValues = getKeyValuesForCommitteeSelection(committees);
        }
    }
    
    protected boolean hasSubmitProtocolUnavailablePermission() {
        ProtocolTaskBase task = getNewSubmitProtocolUnavailableTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
      

    private void prepareNotifyCommitteeActionView() {
        protocolNotifyCommitteeBean.prepareView();
        canNotifyCommittee = hasNotifyCommitteePermission();
        canNotifyCommitteeUnavailable = hasNotifyCommitteeUnavailablePermission();
        // Initialize the notify cmt key values only after checking the conditions for the display of the committee selection
        if(canNotifyCommittee && isShowCommittee()) {            
            // pass in the current committee id and the doc route status to the committee finder service
            Collection<? extends CommitteeBase<?, ?, ?>> committees = 
                getCommitteeIdByUnitValuesFinderService().getAssignmentCommittees(null, getDocRouteStatus(), protocolNotifyCommitteeBean.getCommitteeId());
            notifyCmtActionCommitteeIdByUnitKeyValues = getKeyValuesForCommitteeSelection(committees);
        }
    }
       
    protected boolean hasNotifyCommitteePermission() {
        ProtocolTaskBase task = getNewNotifyCommitteeTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTaskBase getNewNotifyCommitteeTaskInstanceHook(ProtocolBase protocol);

    
    private boolean hasNotifyCommitteeUnavailablePermission() {
        ProtocolTaskBase task = getNewNotifyCommitteeUnavailableTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTaskBase getNewNotifyCommitteeUnavailableTaskInstanceHook(ProtocolBase protocol);
    

    protected String getDocRouteStatus() {
        String retVal = "";
        DocumentHeader docHeader = form.getProtocolDocument().getDocumentHeader(); 
        if( docHeader != null) {
            WorkflowDocument workflowDoc = docHeader.getWorkflowDocument();
            if(workflowDoc != null) {
                DocumentStatus docStatus = workflowDoc.getStatus();
                if(docStatus != null) {
                    retVal = docStatus.getCode();
                }
            }
        }
        return retVal;
    }


    protected abstract boolean hasApproveOtherPermission();


    @SuppressWarnings("unchecked")
    protected void populateReviewersAndOnlineReviewsMap() {
        String protocolNum = getProtocol().getProtocolNumber();
        // populate the current submission's reviewers
        this.currentReviewers = getReviewerCommentsService().getProtocolReviewers(protocolNum, currentSubmissionNumber);
        // populate the online reviews map
        List<ProtocolOnlineReviewBase> activeReviews = getReviewerCommentsService().getProtocolOnlineReviews(protocolNum, currentSubmissionNumber);
        for (ProtocolOnlineReviewBase review : activeReviews) {
            this.getOnlineReviewsMap().put(review.getProtocolReviewer().getProtocolReviewerId().toString(), review);
        }
    }

    private boolean hasAdministrativelyApprovePermission() {
        ProtocolTaskBase task = getNewAdminApproveProtocolTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTaskBase getNewAdminApproveProtocolTaskInstanceHook(ProtocolBase protocol);
    
    private boolean hasAdministrativelyApproveUnavailablePermission() {
        ProtocolTaskBase task = getNewAdminApproveUnavailableProtocolTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    protected abstract ProtocolTaskBase getNewAdminApproveUnavailableProtocolTaskInstanceHook(ProtocolBase protocol);
    
    
    protected boolean hasExpirePermission() {
        ProtocolTaskBase task = getExpireTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    protected abstract ProtocolTaskBase getExpireTaskInstanceHook(ProtocolBase protocol);
    
    protected boolean hasExpireUnavailablePermission() {
        ProtocolTaskBase task = getExpireUnavailableTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    protected abstract ProtocolTaskBase getExpireUnavailableTaskInstanceHook(ProtocolBase protocol);
    
    private boolean hasAdministrativelyWithdrawPermission() {
        ProtocolTaskBase task = getNewAdminWithdrawProtocolTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    protected abstract ProtocolTaskBase getNewAdminWithdrawProtocolTaskInstanceHook(ProtocolBase protocol);
    
    private boolean hasAdministrativelyWithdrawUnavailablePermission() {
        ProtocolTaskBase task = getNewAdminWithdrawUnavailableProtocolTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    protected abstract ProtocolTaskBase getNewAdminWithdrawUnavailableProtocolTaskInstanceHook(ProtocolBase protocol);
    
    
    
    
    private boolean hasAdministrativelyMarkIncompletePermission() {
        ProtocolTaskBase task = getNewAdminMarkIncompleteProtocolTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    protected abstract ProtocolTaskBase getNewAdminMarkIncompleteProtocolTaskInstanceHook(ProtocolBase protocol);
    
    private boolean hasAdministrativelyMarkIncompleteUnavailablePermission() {
        ProtocolTaskBase task = getNewAdminMarkIncompleteUnavailableProtocolTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    protected abstract ProtocolTaskBase getNewAdminMarkIncompleteUnavailableProtocolTaskInstanceHook(ProtocolBase protocol);
    
    
    
   protected void initProtocolCorrespondenceAuthorizationFlags() {
       ProtocolCorrespondenceAuthorizationService correspondenceAuthorizationService = getProtocolCorrespondenceAuthorizationService(); 
       this.allowedToViewProtocolCorrespondence = correspondenceAuthorizationService.isAllowedToViewProtocolCorrespondence(getProtocol());
       this.allowedToUpdateProtocolCorrespondence = correspondenceAuthorizationService.isAllowedToUpdateProtocolCorrespondence(getProtocol());
       this.allowedToRegenerateProtocolCorrespondence = correspondenceAuthorizationService.isAllowedToRegenerateProtocolCorrespondence(getProtocol());
   }

    /**
     * Refreshes the comments for all the beans from the database.  Use sparingly since this will erase non-persisted comments.
     */
    @SuppressWarnings("unchecked")
    public void prepareCommentsView() {
        
        protocolAdminApprovalBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
             
        assignToAgendaBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        
        protocolFullApprovalBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        
        protocolDisapproveBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());         
        protocolSMRBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        protocolSRRBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        protocolSuspendBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        protocolExpireBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        protocolTerminateBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        committeeDecision.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());        
        protocolManageReviewCommentsBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        getProtocol().getProtocolSubmission().refreshReferenceObject("reviewAttachments");
        protocolManageReviewCommentsBean.getReviewAttachmentsBean().setReviewAttachments(getProtocol().getProtocolSubmission().getReviewAttachments());
        if (CollectionUtils.isNotEmpty(protocolManageReviewCommentsBean.getReviewAttachmentsBean().getReviewAttachments())) {
            protocolManageReviewCommentsBean.getReviewAttachmentsBean().setHideReviewerName(getReviewerCommentsService().setHideReviewerName(protocolManageReviewCommentsBean.getReviewAttachmentsBean().getReviewAttachments()));
        }
        
        protocolReturnToPIBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
    }
    
    @SuppressWarnings({ "rawtypes" })
    protected List<CommitteeScheduleMinuteBase> getCopiedReviewComments() {
        List<CommitteeScheduleMinuteBase> minutes = getReviewCommentsUsingScheduleOrSubmission();
        return cloneReviewComments(minutes);        
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected List<CommitteeScheduleMinuteBase> getReviewCommentsUsingScheduleOrSubmission() {
        List<CommitteeScheduleMinuteBase> minutes;
        Long scheduleIdFk = getProtocol().getProtocolSubmission().getScheduleIdFk();
        // if the schedule has not yet been selected, then use the review comment service to get the reviews, 
        if(scheduleIdFk == null) {
            minutes = getReviewerCommentsService().getReviewerComments(getProtocol().getProtocolNumber(), currentSubmissionNumber);
            // sort the minutes by entry number, so that all review comments beans show the same ordered listing
            Collections.sort(minutes, new Comparator<CommitteeScheduleMinuteBase>() {

                @Override
                public int compare(CommitteeScheduleMinuteBase csm1, CommitteeScheduleMinuteBase csm2) {
                    int retVal = 0;
                    if( (csm1 != null) && (csm2 != null) && (csm1.getEntryNumber() != null) && (csm2.getEntryNumber() != null) ) {
                        retVal = csm1.getEntryNumber().compareTo(csm2.getEntryNumber());
                    }
                    return retVal;
                }
                
            });
        }
        // otherwise just use the committeesSchedule service to get the reviews for the selected schedule
        else {
            minutes = getCommitteeScheduleService().getMinutesBySchedule(scheduleIdFk);
        }
        return minutes;
    }
    
    @SuppressWarnings("rawtypes")
    protected List<CommitteeScheduleMinuteBase> cloneReviewComments(List<CommitteeScheduleMinuteBase> minutes) {
        List<CommitteeScheduleMinuteBase> clonedMinutes = new ArrayList<CommitteeScheduleMinuteBase>();
        if (CollectionUtils.isNotEmpty(minutes)) {
            for (CommitteeScheduleMinuteBase minute : minutes) {
                clonedMinutes.add(minute.getCopy());
            }
        }        
        return clonedMinutes;
    }
    
       
    @SuppressWarnings("rawtypes")
    private CommitteeScheduleServiceBase getCommitteeScheduleService() {
        if (committeeScheduleService == null) {
            committeeScheduleService = KcServiceLocator.getService(getCommitteeScheduleServiceClassHook());
        }
        return committeeScheduleService;
    }
    
    
    @SuppressWarnings("rawtypes")
    protected abstract Class<? extends CommitteeScheduleServiceBase> getCommitteeScheduleServiceClassHook();


    protected abstract ProtocolVersionService getProtocolVersionService();
    
    protected String getParameterValue(String parameterName) {        
        return this.getParameterService().getParameterValueAsString(getProtocolDocumentBOClassHook(), parameterName);      
    }
    
    protected abstract Class<? extends ProtocolDocumentBase> getProtocolDocumentBOClassHook();
  
  
    protected boolean hasSubmitProtocolPermission() {
        ProtocolTaskBase task = getNewSubmitProtocolTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTaskBase getNewSubmitProtocolTaskInstanceHook(ProtocolBase protocol);
    
  
    protected abstract ProtocolTaskBase getNewSubmitProtocolUnavailableTaskInstanceHook(ProtocolBase protocol);

    
    
    protected boolean hasCreateAmendmentPermission() {
        ProtocolTaskBase task = getNewAmendmentProtocolTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    
    }
    
    protected boolean hasCreateAmendmentUnavailablePermission() {
        ProtocolTaskBase task = getNewAmendmentProtocolUnavailableTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    
    }

    protected abstract ProtocolTaskBase getNewAmendmentProtocolTaskInstanceHook(ProtocolBase protocol);
    protected abstract ProtocolTaskBase getNewAmendmentProtocolUnavailableTaskInstanceHook(ProtocolBase protocol);
    
    protected boolean hasModifyAmendmentSectionsPermission() {
        ProtocolTaskBase task = getModifyAmendmentSectionsProtocolTaskInstanceHook(getProtocol());
        return ((!getProtocol().isRenewalWithoutAmendment())&&(getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task)));
    }
    
    protected boolean hasModifyAmendmentSectionsUnavailablePermission() {
        ProtocolTaskBase task = getModifyAmendmentSectionsUnavailableProtocolUnavailableTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    protected abstract ProtocolTaskBase getModifyAmendmentSectionsProtocolTaskInstanceHook(ProtocolBase protocol);
    protected abstract ProtocolTaskBase getModifyAmendmentSectionsUnavailableProtocolUnavailableTaskInstanceHook(ProtocolBase protocol);
    
    
    protected boolean hasCreateRenewalPermission() {
        ProtocolTaskBase task = getNewRenewalProtocolTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected boolean hasCreateRenewalUnavailablePermission() {
        ProtocolTaskBase task = getNewRenewalProtocolUnavailableTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    protected abstract ProtocolTaskBase getNewRenewalProtocolTaskInstanceHook(ProtocolBase protocol);
    protected abstract ProtocolTaskBase getNewRenewalProtocolUnavailableTaskInstanceHook(ProtocolBase protocol);
        
    protected boolean hasWithdrawPermission() {
        ProtocolTaskBase task = getNewWithdrawProtocolTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTaskBase getNewWithdrawProtocolTaskInstanceHook(ProtocolBase protocol);

    
    protected boolean hasWithdrawUnavailablePermission() {
        ProtocolTaskBase task = getNewWithdrawProtocolUnavailableTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTaskBase getNewWithdrawProtocolUnavailableTaskInstanceHook(ProtocolBase protocol);
    
    protected boolean hasDeleteProtocolAmendRenewPermission() {
        ProtocolTaskBase task = createNewAmendRenewDeleteTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    
    protected abstract ProtocolTaskBase createNewAmendRenewDeleteTaskInstanceHook(ProtocolBase protocol);
    
    
    protected boolean hasDeleteProtocolAmendRenewUnavailablePermission() {
        ProtocolTaskBase task = createNewAmendRenewDeleteUnavailableTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTaskBase createNewAmendRenewDeleteUnavailableTaskInstanceHook(ProtocolBase protocol);
    
    
    protected boolean hasAssignToAgendaPermission() {
        ProtocolTaskBase task = createNewAssignToAgendaTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTaskBase createNewAssignToAgendaTaskInstanceHook(ProtocolBase protocol);

    
    protected boolean hasAssignToAgendaUnavailablePermission() {
        ProtocolTaskBase task = createNewAssignToAgendaUnavailableTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTaskBase createNewAssignToAgendaUnavailableTaskInstanceHook(ProtocolBase protocol);
    
    protected boolean hasFullApprovePermission() {
        return hasPermission(TaskName.APPROVE_PROTOCOL);
    }
    
    protected boolean hasFullApproveUnavailablePermission() {
        return hasPermission(TaskName.APPROVE_PROTOCOL_UNAVAILABLE);
    }
    
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

    protected boolean hasSuspendPermission() {
        ProtocolTaskBase task = getSuspendTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    protected abstract ProtocolTaskBase getSuspendTaskInstanceHook(ProtocolBase protocol);
    
    protected boolean hasSuspendUnavailablePermission() {
        ProtocolTaskBase task = getSuspendUnavailableTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    protected abstract ProtocolTaskBase getSuspendUnavailableTaskInstanceHook(ProtocolBase protocol);

    protected boolean hasTerminatePermission() {
        ProtocolTaskBase task = getTerminateTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    protected abstract ProtocolTaskBase getTerminateTaskInstanceHook(ProtocolBase protocol);
    
    protected boolean hasTerminateUnavailablePermission() {
        ProtocolTaskBase task = getTerminateUnavailableTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    protected abstract ProtocolTaskBase getTerminateUnavailableTaskInstanceHook(ProtocolBase protocol);

    protected boolean hasAdminCorrectionPermission() {
        ProtocolTaskBase task = getAdminCorrectionProtocolTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected boolean hasAdminCorrectionUnavailablePermission() {
        ProtocolTaskBase task = getAdminCorrectionUnavailableProtocolTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTaskBase getAdminCorrectionProtocolTaskInstanceHook(ProtocolBase protocol);
    protected abstract ProtocolTaskBase getAdminCorrectionUnavailableProtocolTaskInstanceHook(ProtocolBase protocol);
    
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
        
    protected boolean hasManageReviewCommentsPermission() {
        return hasPermission(TaskName.PROTOCOL_MANAGE_REVIEW_COMMENTS); 
    }
    
    protected boolean hasManageReviewCommentsUnavailablePermission() {
        return hasPermission(TaskName.PROTOCOL_MANAGE_REVIEW_COMMENTS_UNAVAILABLE); 
    }
    
    /*
     * check this 
     */
    protected boolean hasAbandonProtocolPermission() {
        ProtocolTaskBase task = createNewAbandonTaskInstanceHook(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTaskBase createNewAbandonTaskInstanceHook(ProtocolBase protocol);
    
    
    
    // This method should be used for all authorizations going through the task authorization framework, 
    // it makes a downcall to a hook for subclasses to supply the appropriate task using which the 
    // task authorization framework will locate the appropriate spring-wired authorizer.
    protected boolean hasPermission(String taskName) {
        ProtocolTaskBase task = getNewProtocolTaskInstanceHook(taskName);
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTaskBase getNewProtocolTaskInstanceHook(String taskName);
  
    
    
    
    protected boolean hasFollowupAction(String actionCode) {
        if (followupActionActions == null) {
            return false;
        }
        for (ValidProtocolActionActionBase action : followupActionActions) {
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

    protected TaskAuthorizationService getTaskAuthorizationService() {
        if (this.taskAuthorizationService == null) {
            this.taskAuthorizationService = KcServiceLocator.getService(TaskAuthorizationService.class);
        }
        return this.taskAuthorizationService;
    }
    
    public ProtocolSubmitAction getProtocolSubmitAction() {
        return protocolSubmitAction;
    }

    public ProtocolFormBase getProtocolForm() {
        return form;
    }
    
    public ProtocolBase getProtocol() {
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
    
    public ProtocolRequestBean getProtocolTerminateRequestBean(){
        return this.protocolTerminateRequestBean;
    }
    
    public ProtocolRequestBean getProtocolWithdrawCloseRequestBean() {
        return protocolWithdrawCloseRequestBean;
    }

    public void setProtocolWithdrawCloseRequestBean(ProtocolRequestBean protocolWithdrawCloseRequestBean) {
        this.protocolWithdrawCloseRequestBean = protocolWithdrawCloseRequestBean;
    }

    public ProtocolRequestBean getProtocolWithdrawSuspendRequestBean() {
        return protocolWithdrawSuspendRequestBean;
    }

    public void setProtocolWithdrawSuspendRequestBean(ProtocolRequestBean protocolWithdrawSuspendRequestBean) {
        this.protocolWithdrawSuspendRequestBean = protocolWithdrawSuspendRequestBean;
    }




    public ProtocolRequestBean getProtocolWithdrawTerminateRequestBean() {
        return protocolWithdrawTerminateRequestBean;
    }




    public void setProtocolWithdrawTerminateRequestBean(ProtocolRequestBean protocolWithdrawTerminateRequestBean) {
        this.protocolWithdrawTerminateRequestBean = protocolWithdrawTerminateRequestBean;
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
    
    public ProtocolAmendmentBean getProtocolAmendmentSummaryBean() {
        return protocolAmendmentSummaryBean;
    }
    
    public ProtocolDeleteBean getProtocolDeleteBean() {
        return protocolDeleteBean;
    }
    
    public ProtocolAssignToAgendaBean getAssignToAgendaBean(){
        return this.assignToAgendaBean;
    }
    
    public ProtocolApproveBean getProtocolFullApprovalBean() {
        return protocolFullApprovalBean;
    }
    
    public ProtocolGenericActionBean getProtocolDisapproveBean() {
        return protocolDisapproveBean;
    }
    
    public ProtocolGenericActionBean getProtocolSMRBean() {
        return protocolSMRBean;
    }
    
    public ProtocolGenericActionBean getProtocolSRRBean() {
        return protocolSRRBean;
    }

    public ProtocolGenericActionBean getProtocolSuspendBean() {
        return protocolSuspendBean;
    }
    
    public ProtocolGenericActionBean getProtocolCloseBean() {
        return protocolCloseBean;
    }
    
    public ProtocolGenericActionBean getProtocolExpireBean() {
        return protocolExpireBean;
    }
    
    public ProtocolGenericActionBean getProtocolTerminateBean() {
        return protocolTerminateBean;
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
    
    protected abstract ProtocolTaskBase getModifySubmissionAvailableTaskHook();
    
    protected abstract ProtocolTaskBase getModifySubmissionUnavailableTaskHook();
    
    protected boolean hasCanModifySubmissionPermission() {
        ProtocolTaskBase task = getModifySubmissionAvailableTaskHook();
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected boolean hasCanModifySubmissionUnavailablePermission() {
        ProtocolTaskBase task = getModifySubmissionUnavailableTaskHook();
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
    
    public boolean getCanRequestSuspension() {
        return canRequestSuspension;
    }
    
    public boolean getCanRequestSuspensionUnavailable() {
        return canRequestSuspensionUnavailable;
    }
        
    public boolean getcanRequestTerminate(){
        return this.canRequestTerminate;
    }
    
    public boolean getcanRequestTerminateUnavailable(){
        return this.canRequestTerminateUnavailable;
    }
    
    public boolean getCanWithdrawSubmission(){
        return this.canWithdrawSubmission;
    }
    
    public boolean getCanWithdrawSubmissionUnavailable(){
        return this.canWithdrawSubmissionUnavailable;
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
    
    public boolean getCanApproveFull() {
        return canApproveFull;
    }
    
    public boolean getCanApproveFullUnavailable() {
        return canApproveFullUnavailable;
    }
    
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
    
    public boolean getCanSuspend() {
        return canSuspend;
    }
    
    public boolean getCanSuspendUnavailable() {
        return canSuspendUnavailable;
    }

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
    
    public boolean getCanAddSuspendReviewerComments() {
        return canAddSuspendReviewerComments;
    }

    public boolean getCanAddTerminateReviewerComments() {
        return canAddTerminateReviewerComments;
    }

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
         
    public ProtocolActionBase getLastPerformedAction() {
        List<ProtocolActionBase> protocolActions = form.getProtocolDocument().getProtocol().getProtocolActions();
        Collections.sort(protocolActions, new Comparator<ProtocolActionBase>() {
            public int compare(ProtocolActionBase action1, ProtocolActionBase action2) {
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

        for (ProtocolActionBase protocolAction : getSortedProtocolActions()) {
            Timestamp actionDate = protocolAction.getActionDate();
            if (dayBeforeStartDate != null && dayAfterEndDate != null) {
                protocolAction.setIsInFilterView(actionDate.after(dayBeforeStartDate) && actionDate.before(dayAfterEndDate));
            }
            else {
                protocolAction.setIsInFilterView(true);
            }
            if (protocolAction.getIsInFilterView()) {
                
            }
        }
    }
    
    /**
     * Prepares, sorts, and returns a list of protocol actions.
     * @return
     */
    public List<ProtocolActionBase> getSortedProtocolActions() {
        List<ProtocolActionBase> protocolActions = new ArrayList<ProtocolActionBase>();
        for (ProtocolActionBase protocolAction : form.getProtocolDocument().getProtocol().getProtocolActions()) {       
           if (protocolAction.getSubmissionNumber() != null) {
               protocolAction.setProtocolSubmissionDocs(new ArrayList<ProtocolSubmissionDocBase>(getSubmissionDocs(protocolAction)));
           }
            protocolActions.add(protocolAction);
        }
        
        Collections.sort(protocolActions, new Comparator<ProtocolActionBase>() {
            public int compare(ProtocolActionBase action1, ProtocolActionBase action2) {
                return action2.getActualActionDate().compareTo(action1.getActualActionDate());
            }
        });
     
        return protocolActions;
    }

    protected Collection<? extends ProtocolSubmissionDocBase> getSubmissionDocs(ProtocolActionBase protocolAction) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("protocolNumber", protocolAction.getProtocolNumber());
        fieldValues.put("submissionNumber", protocolAction.getSubmissionNumber());
        return getBusinessObjectService().findMatchingOrderBy(getProtocolSubmissionDocClassHook(), fieldValues, "documentId", true);
    }

    
    
    protected abstract Class<? extends ProtocolSubmissionDocBase> getProtocolSubmissionDocClassHook();

    
    
    public ProtocolActionBase getSelectedProtocolAction() {
        for (ProtocolActionBase action : getProtocol().getProtocolActions()) {
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
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
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
    public ProtocolSubmissionBase getSelectedSubmission() {
        ProtocolSubmissionBase protocolSubmission = null;
        
        if (currentSubmissionNumber <= 0) {
            protocolSubmission = getProtocol().getProtocolSubmission();
        } else if (currentSubmissionNumber > 0) {
            for (ProtocolSubmissionBase submission : getProtocol().getProtocolSubmissions()) {
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


    public List<CommitteeScheduleMinuteBase> getReviewComments() {
        return reviewComments;
    }

    // create getter for sorted comments to preserve old functionality
    public List<CommitteeScheduleMinuteBase> getSortedReviewComments() {
        List<CommitteeScheduleMinuteBase>results = new ArrayList<CommitteeScheduleMinuteBase>();
        results.addAll(reviewComments);
        Collections.sort(results, new Comparator<CommitteeScheduleMinuteBase>() {
            @Override
            public int compare(CommitteeScheduleMinuteBase o1, CommitteeScheduleMinuteBase o2) {
                return o1.getEntryNumber().compareTo(o2.getEntryNumber());
            }
        });
        return results;
    }

    protected void setReviewComments(List<CommitteeScheduleMinuteBase> reviewComments) {
        this.reviewComments = reviewComments;
    }

    public List<ProtocolVoteAbstaineeBase> getAbstainees() {
        return abstainees;
    }

    public void setAbstainees(List<ProtocolVoteAbstaineeBase> abstainees) {
        this.abstainees = abstainees;
    }
    
    public BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }
    
    public FollowupActionService<?> getFollowupActionService() {
        if (followupActionService == null) {
            followupActionService = KcServiceLocator.getService(getFollowupActionServiceClassHook());
        }
        return followupActionService;
    }
    
    
    protected abstract Class<? extends FollowupActionService<?>> getFollowupActionServiceClassHook();
    

    protected ReviewCommentsService getReviewerCommentsService() {
        return getReviewCommentsService();
    }
    
    protected abstract CommitteeDecisionService<? extends CommitteeDecision<?> > getCommitteeDecisionService();
    
    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        
        return this.kcPersonService;
    }
    
    public int getCurrentSubmissionNumber() {
        return currentSubmissionNumber;
    }

    public void setCurrentSubmissionNumber(int currentSubmissionNumber) {
        this.currentSubmissionNumber = currentSubmissionNumber;
    }
    
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
        ProtocolBase protocol = getProtocolVersionService().getProtocolVersion(protocolNumber, currentSequenceNumber);
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
                List<CommitteeScheduleMinuteBase> reviewComments = getReviewerCommentsService().getReviewerComments(getProtocol().getProtocolNumber(), currentSubmissionNumber);
                Collections.sort(reviewComments, new Comparator<CommitteeScheduleMinuteBase>() {

                    @Override
                    public int compare(CommitteeScheduleMinuteBase csm1, CommitteeScheduleMinuteBase csm2) {
                        int retVal = 0;
                        if( (csm1 != null) && (csm2 != null) && (csm1.getEntryNumber() != null) && (csm2.getEntryNumber() != null) ) {
                            retVal = csm1.getEntryNumber().compareTo(csm2.getEntryNumber());
                        }
                        return retVal;
                    }
                    
                });
                protocolManageReviewCommentsBean.getReviewCommentsBean().setReviewComments(reviewComments);
                getReviewerCommentsService().setHideReviewerName(reviewComments);
                setHidePrivateFinalFlagsForPublicCommentsAttachments(getReviewerCommentsService().isHidePrivateFinalFlagsForPI(reviewComments));
            }
            getReviewerCommentsService().setHideReviewerName(getReviewComments());
            setHidePrivateFinalFlagsForPublicCommentsAttachments(getReviewerCommentsService().isHidePrivateFinalFlagsForPI(reviewComments));            
        }
        setReviewAttachments(getReviewerCommentsService().getReviewerAttachments(getProtocol().getProtocolNumber(), currentSubmissionNumber));
        if (CollectionUtils.isNotEmpty(getReviewAttachments())) {
            hideReviewerNameForAttachment = getReviewerCommentsService().setHideReviewerName(getReviewAttachments());
            getReviewerCommentsService().setHideViewButton(getReviewAttachments());
        }
        getProtocol().getProtocolSubmission().refreshReferenceObject("reviewAttachments");
        hideSubmissionReviewerName = checkToHideSubmissionReviewerName();

        populateReviewersAndOnlineReviewsMap();
        
        setAbstainees(getCommitteeDecisionService().getAbstainers(getProtocol().getProtocolNumber(), currentSubmissionNumber));
        setRecusers(getCommitteeDecisionService().getRecusers(getProtocol().getProtocolNumber(), currentSubmissionNumber));

        protocolSubmissionQuestionnaireHelper = getProtocolSubmissionQuestionnaireHelperHook(this.getProtocol(), null, Integer.toString(currentSubmissionNumber), true);
        protocolSubmissionQuestionnaireHelper.populateAnswers();
        setSubmissionQuestionnaireExist(!protocolSubmissionQuestionnaireHelper.getAnswerHeaders().isEmpty());
    }
    
    protected abstract ProtocolSubmissionQuestionnaireHelper getProtocolSubmissionQuestionnaireHelperHook(ProtocolBase protocol, String actionTypeCode, String submissionNumber, boolean finalDoc);
    
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
            List<ProtocolBase> protocols = getProtocolAmendRenewServiceHook().getAmendmentAndRenewals(originalProtocolNumber);

            ProtocolAmendRenewalBase correctAmendment = getCorrectAmendment(protocols);
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
    protected ProtocolAmendRenewalBase getCorrectAmendment(List<ProtocolBase> protocols) {
        for (ProtocolBase protocol : protocols) {
            // There should always be an amendment with the current submission number.
            if ((protocol.isAmendment() || protocol.isRenewalWithAmendment()) && ObjectUtils.isNotNull(protocol.getProtocolSubmission().getSubmissionNumber()) 
                && protocol.getProtocolSubmission().getSubmissionNumber() == currentSubmissionNumber) {
                return protocol.getProtocolAmendRenewal();
            }
        }
        return null;
    }
    
    protected boolean hasAnsweredQuestionnaire(String moduleSubItemCode, String moduleSubItemKey) {
        return getAnswerHeaderCount(moduleSubItemCode, moduleSubItemKey) > 0;
    }

    public int getAnswerHeaderCount(String moduleSubItemCode, String moduleSubItemKey) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("moduleItemCode", getCoeusModule());
        fieldValues.put("moduleItemKey", getProtocol().getProtocolNumber());
        fieldValues.put("moduleSubItemCode", moduleSubItemCode);
        fieldValues.put("moduleSubItemKey", moduleSubItemKey);
        return getBusinessObjectService().countMatching(AnswerHeader.class, fieldValues);
        
    }
    
    protected abstract String getCoeusModule();
    
    protected abstract ModuleQuestionnaireBean getQuestionnaireBean(String moduleCode, String moduleKey, String subModuleCode, String subModuleKey, boolean finalDoc);
    
    /*
     * This will check whether there is submission questionnaire.
     * When business rule is implemented, this will become more complicated because
     * each request action may have different set of questionnaire, so this has to be changed.
     */
    protected boolean hasSubmissionQuestionnaire() {
        ModuleQuestionnaireBean moduleQuestionnaireBean = getQuestionnaireBean(getCoeusModule(), this.getProtocolForm().getProtocolDocument().getProtocol().getProtocolNumber(), CoeusSubModule.PROTOCOL_SUBMISSION, "999", true);
        return CollectionUtils.isNotEmpty(getQuestionnaireAnswerService().getQuestionnaireAnswer(moduleQuestionnaireBean));
    }

    protected QuestionnaireAnswerService getQuestionnaireAnswerService() {
        if (questionnaireAnswerService == null) {
            questionnaireAnswerService = KcServiceLocator.getService(QuestionnaireAnswerService.class);
        } 
        return questionnaireAnswerService;
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
        for (ProtocolSubmissionBase submission : getProtocol().getProtocolSubmissions()) {
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

        for (ProtocolSubmissionBase submission : getProtocol().getProtocolSubmissions()) {
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
            List<ProtocolBase> protocols = (List<ProtocolBase>) getProtocolAmendRenewServiceHook().getAmendments(getProtocol().getProtocolNumber());
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
            List<ProtocolBase> protocols = (List<ProtocolBase>) getProtocolAmendRenewServiceHook().getRenewals(getProtocol().getProtocolNumber());
            hasRenewals = protocols.isEmpty() ? false : true;
        }
        return hasRenewals;
    }
    
    public List<ProtocolVoteRecusedBase> getRecusers() {
        return recusers;
    }

    public void setRecusers(List<ProtocolVoteRecusedBase> recusers) {
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
    public void setProtocolSummaryPrintOptions(ProtocolSummaryPrintOptions protocolSummaryPrintOptions) {
        this.protocolSummaryPrintOptions = protocolSummaryPrintOptions;
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
        List<AnswerHeader> printAnswerHeaders  = getQuestionnaireAnswerService().getPrintAnswerHeadersForProtocol(moduleQuestionnaireBean, getProtocol().getProtocolNumber(), form.getQuestionnaireHelper());
        setupQnPrintOption(printAnswerHeaders);
    }
    
    protected abstract void initPrintCorrespondence();

    public void setCorrespondencesToPrint(List<CorrespondencePrintOption> printOptions) {
        this.correspondencesToPrint = printOptions;
    }

    public List<CorrespondencePrintOption> getCorrespondencesToPrint() {
        return correspondencesToPrint;
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
    
    public boolean isHidePrivateFinalFlagsForPublicCommentsAttachments() {
        return hidePrivateFinalFlagsForPublicCommentsAttachments;
    }

    public void setHidePrivateFinalFlagsForPublicCommentsAttachments(boolean hidePrivateFinalFlagsForPublicCommentsAttachments) {
        this.hidePrivateFinalFlagsForPublicCommentsAttachments = hidePrivateFinalFlagsForPublicCommentsAttachments;
    }
   
    /*
     * check whether to display submission details comment (private/final) and attachment (protocol personnel can view) flags.
     */
    protected boolean checkToHidePrivateFinalFlagsForPublicCommentsAttachments() {
        List<CommitteeScheduleMinuteBase> reviewComments = getReviewComments();
        if (reviewComments == null) {
            return false;
        } else {
            return getReviewCommentsService().isHidePrivateFinalFlagsForPI(reviewComments);
        }
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
    protected boolean checkToHideSubmissionReviewerName() {
        boolean isHide = true;
        for (CommitteeScheduleMinuteBase reviewComment : getReviewComments()) {
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
    protected boolean checkToHideReviewName() {
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

    public List<? extends ProtocolReviewAttachmentBase> getReviewAttachments() {
        return reviewAttachments;
    }

    public void setReviewAttachments(List<? extends ProtocolReviewAttachmentBase> reviewAttachments) {
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

    protected SystemAuthorizationService getSystemAuthorizationService() {
        if (this.systemAuthorizationService == null) {
            this.systemAuthorizationService = KcServiceLocator.getService(SystemAuthorizationService.class);
        }

        return this.systemAuthorizationService;
    }
    
    protected abstract ProtocolModuleQuestionnaireBeanBase getNewProtocolModuleQuestionnaireBeanInstanceHook(ProtocolBase protocol);
    
    protected abstract ProtocolAmendRenewService getProtocolAmendRenewServiceHook();
    
    /**
     * This method copies the settings from the ProtocolAmendRenewalBase bo to the amendmentBean and enables the
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
    protected abstract void enableModuleOption(ProtocolAmendmentBean amendmentBean, ProtocolAmendRenewalBase correctAmendment);


    public ProtocolGenericActionBean getProtocolReturnToPIBean() {
        return protocolReturnToPIBean;
    }


    public boolean isCanReturnToPI() {
        return canReturnToPI;
    }


    public boolean isCanReturnToPIUnavailable() {
        return canReturnToPIUnavailable;
    }
    
    /**
     * 
     * This method returns the number of years to add for the default expiration date.
     * @return
     */
    public int getDefaultExpirationDateDifference() {
        return 1;
    }


    public void setQuestionnaireAnswerService(QuestionnaireAnswerService questionnaireAnswerService) {
        this.questionnaireAnswerService = questionnaireAnswerService;
    }


    public boolean isSubmissionQuestionnaireExist() {
        return submissionQuestionnaireExist;
    }


    public void setSubmissionQuestionnaireExist(boolean submissionQuestionnaireExist) {
        this.submissionQuestionnaireExist = submissionQuestionnaireExist;
    }


    public boolean isToAnswerSubmissionQuestionnaire() {
        return toAnswerSubmissionQuestionnaire;
    }


    public void setToAnswerSubmissionQuestionnaire(boolean toAnswerSubmissionQuestionnaire) {
        this.toAnswerSubmissionQuestionnaire = toAnswerSubmissionQuestionnaire;
    }


    public ProtocolSubmissionQuestionnaireHelper getProtocolSubmissionQuestionnaireHelper() {
        return protocolSubmissionQuestionnaireHelper;
    }


    public void setProtocolSubmissionQuestionnaireHelper(ProtocolSubmissionQuestionnaireHelper protocolSubmissionQuestionnaireHelper) {
        this.protocolSubmissionQuestionnaireHelper = protocolSubmissionQuestionnaireHelper;
    }
    
    public void populateSubmissionQuestionnaires() {
        for (Map.Entry<String, ProtocolActionBean> entry: actionBeanTaskMap.entrySet()) {
            if (entry.getValue() instanceof ProtocolRequestBean) {
                ProtocolRequestBean bean = (ProtocolRequestBean) entry.getValue();
                //set the protocol in case the request bean had an old protocol without the protocol number
                bean.getQuestionnaireHelper().setProtocol(getProtocol());
                bean.getQuestionnaireHelper().populateAnswers();
                bean.getQuestionnaireHelper().resetHeaderLabels();
            }
        }
    }
    
    public void preSaveSubmissionQuestionnaires() {
        for (Map.Entry<String, ProtocolActionBean> entry: actionBeanTaskMap.entrySet()) {
            if (entry.getValue() instanceof ProtocolRequestBean) {
                ProtocolRequestBean bean = (ProtocolRequestBean) entry.getValue();
                bean.getQuestionnaireHelper().preSave();
                getBusinessObjectService().save(bean.getQuestionnaireHelper().getAnswerHeaders());
            }
        }
    }


    public List<ProtocolReviewer> getCurrentReviewers() {
        return currentReviewers;
    }

    public HashMap<String, ProtocolOnlineReviewBase> getOnlineReviewsMap() {
        return onlineReviewsMap;
    }    

    protected CommitteeIdByUnitValuesFinderService<?> getCommitteeIdByUnitValuesFinderService() {
        return KcServiceLocator.getService(getCommitteeIdByUnitValuesFinderServiceClassHook());
    }
    
    protected abstract Class<? extends CommitteeIdByUnitValuesFinderService<?>> getCommitteeIdByUnitValuesFinderServiceClassHook();
    
    public List<KeyValue> getSubmitActionCommitteeIdByUnitKeyValues() {
        return submitActionCommitteeIdByUnitKeyValues;
    }
    
    public List<KeyValue> getNotifyCmtActionCommitteeIdByUnitKeyValues() {
        return notifyCmtActionCommitteeIdByUnitKeyValues;
    }    

    private ProtocolCorrespondenceAuthorizationService getProtocolCorrespondenceAuthorizationService() {
        return KcServiceLocator.getService(getProtocolCorrespondenceAuthorizationServiceClassHook());
    }
    
    protected abstract Class<? extends ProtocolCorrespondenceAuthorizationService> getProtocolCorrespondenceAuthorizationServiceClassHook();

    public boolean isAllowedToViewProtocolCorrespondence() {
        return this.allowedToViewProtocolCorrespondence;
    }
    
    public boolean isAllowedToUpdateProtocolCorrespondence() {
        return this.allowedToUpdateProtocolCorrespondence;
    }
    
    public boolean isAllowedToRegenerateProtocolCorrespondence() {
        return this.allowedToRegenerateProtocolCorrespondence;
    }
    
    public class AmendmentSummary {
        private String amendmentType;

        private String protocolNumber;
        private String versionNumber;
        private String versionNumberUrl;
        private String description;
        private String status;
        private String createDate;
        private ProtocolBase amendRenewProtocol;
        private List <AnswerHeader> answerHeaders;
        
        public String getAmendmentType() {
            return amendmentType;
        }
        public String getProtocolNumber() {
            return protocolNumber;
        }
        public String getVersionNumber() {
            return versionNumber;
        }
        public String getVersionNumberUrl() {
            return versionNumberUrl;
        }
        public String getDescription() {
            return description;
        }
        public String getStatus() {
            return status;
        }
        public String getCreateDate() {
            return createDate;
        }
        public ProtocolBase getAmendRenewProtocol() {
            return amendRenewProtocol;
        }
        public List <AnswerHeader> getAnswerHeaders() {
            return answerHeaders;
        }
        
        public AmendmentSummary(ProtocolBase protocol) {
            amendmentType = protocol.isRenewalWithoutAmendment() ? "Renewal" : protocol.isRenewal() ? "Renewal with Amendment" : protocol.isAmendment() ? "Amendment" : "New";
            protocolNumber = protocol.getProtocolNumber();
            versionNumber = protocol.getProtocolNumber().substring(protocol.getProtocolNumber().length() - 3);
            versionNumberUrl = buildForwardUrl(protocol.getProtocolDocument().getDocumentNumber());
            if (protocol.isAmendment() || protocol.isRenewal()) {
                ProtocolAmendRenewalBase correctAmendment = protocol.getProtocolAmendRenewal();
                if (correctAmendment != null) {
                    description = correctAmendment.getSummary();
                    versionNumber = String.valueOf(protocol.getSequenceNumber());
                    versionNumberUrl = buildForwardUrl(protocol.getProtocolDocument().getDocumentNumber());
                } else {
                    description = "";
                    versionNumber = "";
                    versionNumberUrl = "";
                }
            }
            status = protocol.getProtocolStatus().getDescription();
            try {
                ProtocolDocumentBase protocolDoc = (ProtocolDocumentBase)KcServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(protocol.getProtocolDocument().getDocumentNumber());
                Date docDate = new Date(protocolDoc.getDocumentHeader().getWorkflowDocument().getDateCreated().getMillis());
                createDate = new SimpleDateFormat("MM/dd/yyyy").format(docDate);
                amendRenewProtocol = protocolDoc.getProtocol();
                answerHeaders = amendRenewProtocol.getAnswerHeaderForProtocol(amendRenewProtocol);
            } catch (Exception e) {
                createDate = "";
                amendRenewProtocol = null;
                answerHeaders = new ArrayList<AnswerHeader>();
            }
        }
    }
    
    public List<AmendmentSummary> getAmendmentSummaries() throws Exception {
        List<AmendmentSummary> results = new ArrayList<AmendmentSummary>();
        // only list amendments if this protocol is not one
        if (getProtocol().isNew()) {
            // Amendment details needs to be displayed even after the amendment has been merged with the protocol.
            String originalProtocolNumber = getProtocol().getProtocolNumber();
            List<ProtocolBase> protocols = getProtocolAmendRenewServiceHook().getAmendmentAndRenewals(originalProtocolNumber);
            for (ProtocolBase protocol: protocols) {
                results.add(new AmendmentSummary(protocol));
            }
        }
        return results;
    }
    protected String buildForwardUrl(String routeHeaderId) {
    	DocHandlerService researchDocumentService = KcServiceLocator.getService(DocHandlerService.class);
        String forward = researchDocumentService.getDocHandlerUrl(routeHeaderId);
        forward = forward.replaceFirst(DEFAULT_TAB, ALTERNATE_OPEN_TAB);
        if (forward.indexOf("?") == -1) {
            forward += "?";
        }
        else {
            forward += "&";
        }
        forward += KewApiConstants.DOCUMENT_ID_PARAMETER + "=" + routeHeaderId;
        forward += "&" + KewApiConstants.COMMAND_PARAMETER + "=" + NotificationConstants.NOTIFICATION_DETAIL_VIEWS.DOC_SEARCH_VIEW;
        if (GlobalVariables.getUserSession().isBackdoorInUse()) {
            forward += "&" + KewApiConstants.BACKDOOR_ID_PARAMETER + "=" + GlobalVariables.getUserSession().getPrincipalName();
        }
        return forward;
    }
}
