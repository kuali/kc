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
package org.kuali.kra.irb.actions;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.ProtocolVersionService;
import org.kuali.kra.irb.actions.acknowledgement.IrbAcknowledgementBean;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewService;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendmentBean;
import org.kuali.kra.irb.actions.amendrenew.ProtocolModule;
import org.kuali.kra.irb.actions.approve.ProtocolApproveBean;
import org.kuali.kra.irb.actions.assignagenda.ProtocolAssignToAgendaBean;
import org.kuali.kra.irb.actions.assigncmtsched.ProtocolAssignCmtSchedBean;
import org.kuali.kra.irb.actions.assignreviewers.ProtocolAssignReviewersBean;
import org.kuali.kra.irb.actions.correction.AdminCorrectionBean;
import org.kuali.kra.irb.actions.decision.CommitteeDecision;
import org.kuali.kra.irb.actions.decision.CommitteeDecisionService;
import org.kuali.kra.irb.actions.delete.ProtocolDeleteBean;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericActionBean;
import org.kuali.kra.irb.actions.grantexemption.ProtocolGrantExemptionBean;
import org.kuali.kra.irb.actions.history.DateRangeFilter;
import org.kuali.kra.irb.actions.modifysubmission.ProtocolModifySubmissionAction;
import org.kuali.kra.irb.actions.notifyirb.ProtocolNotifyIrbBean;
import org.kuali.kra.irb.actions.request.ProtocolRequestBean;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitAction;
import org.kuali.kra.irb.actions.undo.UndoLastActionBean;
import org.kuali.kra.irb.actions.withdraw.ProtocolWithdrawBean;
import org.kuali.kra.irb.auth.GenericProtocolAuthorizer;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.irb.summary.ProtocolSummary;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.meeting.MinuteEntryType;
import org.kuali.kra.meeting.ProtocolMeetingVoter;
import org.kuali.kra.meeting.ProtocolVoteAbstainee;
import org.kuali.kra.meeting.ProtocolVoteRecused;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.ParameterService;
import org.kuali.rice.kns.util.DateUtils;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * The form helper class for the Protocol Actions tab.
 */
@SuppressWarnings("serial")
public class ActionHelper implements Serializable {

    private static final long ONE_DAY = 1000L * 60L * 60L * 24L;
    
    /**
     * These private integers will be used in a switch statement when building ProtocolGenericActionBean to pull existing data
     */
    private static final int EXPEDITE_APPROVAL_BEAN_TYPE = 1;
    private static final int APPROVE_BEAN_TYPE = 10;
    private static final int REOPEN_BEAN_TYPE = 2;
    private static final int CLOSE_ENROLLMENT_BEAN_TYPE = 3;
    private static final int SUSPEND_BEAN_TYPE = 4;
    private static final int SUSPEND_BY_DSMB_BEAN_TYPE = 5;
    private static final int CLOSE_BEAN_TYPE = 6;
    private static final int EXPIRE_BEAN_TYPE = 7;
    private static final int TERMINATE_BEAN_TYPE = 8;
    private static final int PERMIT_DATA_ANALYSIS_BEAN_TYPE = 9;
    private static final int DEFER_BEAN_TYPE = 10;
    
    /**
     * Each Helper must contain a reference to its document form
     * so that it can access the document.
     */
    private ProtocolForm form;
    
    private boolean canSubmitProtocol = false;
    private String submissionConstraint;
    
    private boolean canCreateAmendment = false;
    private boolean canCreateRenewal = false;
    private boolean canNotifyIrb = false;
    private boolean canWithdraw = false;
    private boolean canRequestClose = false;
    private boolean canRequestSuspension = false;
    private boolean canRequestCloseEnrollment = false;
    private boolean canRequestReOpenEnrollment = false;
    private boolean canRequestDataAnalysis = false;
    private boolean canRequestTerminate = false;
    private boolean canDeleteProtocolAmendRenew = false;
    private boolean canAssignToAgenda = false;
    private boolean canAssignCmtSched = false;
    private boolean canAssignReviewers = false;
    private boolean canGrantExemption = false;
    private boolean canExpediteApproval = false;
    private boolean canApprove = false;
    private boolean canReopen = false;
    private boolean canCloseEnrollment = false;
    private boolean canSuspend = false;
    private boolean canSuspendByDmsb = false;
    private boolean canClose = false;
    private boolean canExpire = false;
    private boolean canTerminate = false;
    private boolean canPermitDataAnalysis = false;
    private boolean canEnterRiskLevel = false;
    private boolean canMakeAdminCorrection = false;
    private boolean canRecordCommitteeDecision = false;
    private boolean canUndoLastAction = false;
    private boolean canModifyProtocolSubmission = false;
    private boolean canIrbAcknowledgement = false;
    private boolean canDefer = false;
    
    private ProtocolSubmitAction protocolSubmitAction;
    private ProtocolWithdrawBean protocolWithdrawBean;
    private ProtocolRequestBean protocolCloseRequestBean;
    private ProtocolRequestBean protocolSuspendRequestBean;
    private ProtocolRequestBean protocolCloseEnrollmentRequestBean;
    private ProtocolRequestBean protocolReOpenEnrollmentRequestBean;
    private ProtocolRequestBean protocolDataAnalysisRequestBean;
    private ProtocolRequestBean protocolTerminateRequestBean;
    private ProtocolNotifyIrbBean protocolNotifyIrbBean;
    private ProtocolAmendmentBean protocolAmendmentBean;
    private ProtocolAmendmentBean protocolRenewAmendmentBean;
    private ProtocolDeleteBean protocolDeleteBean;
    private ProtocolAssignToAgendaBean assignToAgendaBean;
    private ProtocolAssignCmtSchedBean assignCmtSchedBean;
    private ProtocolAssignReviewersBean protocolAssignReviewersBean;
    private ProtocolGrantExemptionBean protocolGrantExemptionBean;
    private ProtocolApproveBean protocolApproveBean;
    private ProtocolApproveBean protocolExpediteApprovalBean;
    private ProtocolGenericActionBean protocolReopenBean;
    private ProtocolGenericActionBean protocolCloseEnrollmentBean;
    private ProtocolGenericActionBean protocolSuspendBean;
    private ProtocolGenericActionBean protocolSuspendByDmsbBean;
    private ProtocolGenericActionBean protocolCloseBean;
    private ProtocolGenericActionBean protocolExpireBean;
    private ProtocolGenericActionBean protocolTerminateBean;
    private ProtocolGenericActionBean protocolPermitDataAnalysisBean;
    private AdminCorrectionBean protocolAdminCorrectionBean;
    private UndoLastActionBean undoLastActionBean;
    private CommitteeDecision committeeDecision;
    private IrbAcknowledgementBean irbAcknowledgementBean;
    private ProtocolModifySubmissionAction protocolModifySubmissionAction;
    private ProtocolGenericActionBean protocolDeferBean;
    
    private transient ParameterService parameterService;
    private transient TaskAuthorizationService taskAuthorizationService;
    private transient ProtocolAmendRenewService protocolAmendRenewService;
    private transient ProtocolVersionService protocolVersionService;
    
    /*
     * Identifies the protocol "document" to print.
     */
    private String printTag;
    
    private ProtocolSummary protocolSummary;
    private ProtocolSummary prevProtocolSummary;
    private int currentSequenceNumber = -1;
    
    private String selectedHistoryItem;
    private DateRangeFilter historyDateRangeFilter = new DateRangeFilter();
    
    // additional properties for Submission Details
    private ProtocolSubmission selectedSubmission;
    private List<CommitteeScheduleMinute> reviewComments;        
    private List<ProtocolVoteAbstainee> abstainees;        
    private List<ProtocolVoteRecused> recusers;        
    private int currentSubmissionNumber = -1;
    private transient KcPersonService kcPersonService;

    /**
     * @throws Exception 
     * Constructs an ActionHelper.
     * @param form the protocol form
     * @throws  
     */
    public ActionHelper(ProtocolForm form) throws Exception {
        this.form = form;
        
        List<ProtocolAction> protocolActions = getProtocol().getProtocolActions();
        ProtocolSubmission currentSubmission = getProtocol().getProtocolSubmission();
        
        protocolSubmitAction = new ProtocolSubmitAction(this);
        protocolWithdrawBean = new ProtocolWithdrawBean();
        protocolCloseRequestBean = new ProtocolRequestBean(ProtocolActionType.REQUEST_TO_CLOSE, 
                                                           ProtocolSubmissionType.REQUEST_TO_CLOSE);
        protocolSuspendRequestBean = new ProtocolRequestBean(ProtocolActionType.REQUEST_FOR_SUSPENSION, 
                                                             ProtocolSubmissionType.REQUEST_FOR_SUSPENSION);
        protocolCloseEnrollmentRequestBean = new ProtocolRequestBean(ProtocolActionType.REQUEST_TO_CLOSE_ENROLLMENT, 
                                                                     ProtocolSubmissionType.REQUEST_TO_CLOSE_ENROLLMENT);
        protocolReOpenEnrollmentRequestBean = new ProtocolRequestBean(ProtocolActionType.REQUEST_TO_REOPEN_ENROLLMENT, 
                                                                      ProtocolSubmissionType.REQUEST_TO_REOPEN_ENROLLMENT);
        protocolDataAnalysisRequestBean = new ProtocolRequestBean(ProtocolActionType.REQUEST_FOR_DATA_ANALYSIS_ONLY, 
                                                                  ProtocolSubmissionType.REQUEST_FOR_DATA_ANALYSIS_ONLY);
        protocolTerminateRequestBean = new ProtocolRequestBean(ProtocolActionType.REQUEST_FOR_TERMINATION, ProtocolSubmissionType.REQUEST_FOR_TERMINATION);
        protocolNotifyIrbBean = new ProtocolNotifyIrbBean();
        protocolAmendmentBean = createAmendmentBean();
        protocolRenewAmendmentBean = createAmendmentBean();
        protocolDeleteBean = new ProtocolDeleteBean();
        assignToAgendaBean = new ProtocolAssignToAgendaBean(this);
        assignToAgendaBean.init();
        assignCmtSchedBean = new ProtocolAssignCmtSchedBean(this);
        assignCmtSchedBean.init();
        protocolAssignReviewersBean = new ProtocolAssignReviewersBean(this);
        protocolGrantExemptionBean = new ProtocolGrantExemptionBean(this);
        protocolGrantExemptionBean.initComments();
        irbAcknowledgementBean = new IrbAcknowledgementBean(this);
        irbAcknowledgementBean.initComments();
        protocolExpediteApprovalBean = buildProtocolApproveBean(this.form.getProtocolDocument().getProtocol(), EXPEDITE_APPROVAL_BEAN_TYPE);
        protocolApproveBean = buildProtocolApproveBean(this.form.getProtocolDocument().getProtocol(), APPROVE_BEAN_TYPE);
        protocolReopenBean = buildProtocolGenericActionBean(REOPEN_BEAN_TYPE, protocolActions, currentSubmission);
        protocolCloseEnrollmentBean = buildProtocolGenericActionBean(CLOSE_ENROLLMENT_BEAN_TYPE, protocolActions, currentSubmission);
        protocolSuspendBean = buildProtocolGenericActionBean(SUSPEND_BEAN_TYPE, protocolActions, currentSubmission);
        protocolSuspendByDmsbBean = buildProtocolGenericActionBean(SUSPEND_BY_DSMB_BEAN_TYPE, protocolActions, currentSubmission);
        protocolCloseBean = buildProtocolGenericActionBean(CLOSE_BEAN_TYPE, protocolActions, currentSubmission);
        protocolExpireBean = buildProtocolGenericActionBean(EXPIRE_BEAN_TYPE, protocolActions, currentSubmission);
        protocolTerminateBean = buildProtocolGenericActionBean(TERMINATE_BEAN_TYPE, protocolActions, currentSubmission);
        protocolPermitDataAnalysisBean = buildProtocolGenericActionBean(PERMIT_DATA_ANALYSIS_BEAN_TYPE, protocolActions, currentSubmission);
        protocolAdminCorrectionBean = createAdminCorrectionBean();
        undoLastActionBean = createUndoLastActionBean(getProtocol());
        committeeDecision = new CommitteeDecision(this);
        committeeDecision.init();
        protocolModifySubmissionAction = new ProtocolModifySubmissionAction(this.getProtocol().getProtocolSubmission());
        protocolDeferBean = buildProtocolGenericActionBean(DEFER_BEAN_TYPE, protocolActions, currentSubmission);
    }
    
    
    /**
     *     
     * This method builds a ProtocolGenericActionBean.  A number of different beans
     * in this object are of type ProtocolGenericActionBean, and all need to add
     * reviewer comments.  This encapsulates that.
     * @return a ProtocolGenericActionBean, and pre-populated with reviewer comments if any exist
     */
    private ProtocolGenericActionBean buildProtocolGenericActionBean(int beanType, List<ProtocolAction> protocolActions, ProtocolSubmission currentSubmission) throws Exception {
        ProtocolGenericActionBean bean = new ProtocolGenericActionBean(this);
        bean.initComments();
        ProtocolAction protocolAction = findProtocolAction(beanType, protocolActions, currentSubmission);
        if (protocolAction != null) {
            bean.setComments(protocolAction.getComments());
            java.sql.Date actionDate = new java.sql.Date(protocolAction.getActionDate().getYear(), protocolAction.getActionDate().getMonth(), 
                    protocolAction.getActionDate().getDay());
            bean.setActionDate(actionDate);
        }
        return bean;
    }
    
    private ProtocolApproveBean buildProtocolApproveBean(Protocol protocol, int beanType) throws Exception{
        ProtocolApproveBean bean = new ProtocolApproveBean(this);
        bean.initComments();
        ProtocolAction protocolAction = findProtocolAction(beanType, protocol.getProtocolActions(), protocol.getProtocolSubmission());
        if (protocolAction != null) {
            bean.setComments(protocolAction.getComments());
            java.sql.Date actionDate = new java.sql.Date(protocolAction.getActionDate().getYear(), protocolAction.getActionDate().getMonth(), 
                    protocolAction.getActionDate().getDay());
            bean.setActionDate(actionDate);
        }
        bean.setApprovalDate(buildApprovalDate(protocol));
        bean.setExpirationDate(buildExpirationDate(protocol, bean.getApprovalDate()));
        return bean;
    }
    
    /**
     * Builds an approval date, defaulting to the approval date from the protocol.
     * 
     * If the approval date from the protocol is null, or if the protocol is new or a renewal, then if the committee has scheduled a meeting to approve the 
     * protocol, sets to the scheduled approval date; otherwise, sets to the current date.
     * 
     * @param protocol
     * @return a non-null approval date
     */
    private Date buildApprovalDate(Protocol protocol) {
        Date approvalDate = protocol.getApprovalDate();
        
        if (approvalDate == null || protocol.isNew() || protocol.isRenewal()) {
            CommitteeSchedule committeeSchedule = protocol.getProtocolSubmission().getCommitteeSchedule();
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
    private Date buildExpirationDate(Protocol protocol, Date approvalDate) {
        Date expirationDate = protocol.getExpirationDate();
        
        if (expirationDate == null || protocol.isNew() || protocol.isRenewal()) {
            java.util.Date newExpirationDate = DateUtils.addYears(approvalDate, 1);
            newExpirationDate = DateUtils.addDays(newExpirationDate, -1);
            expirationDate = DateUtils.convertToSqlDate(newExpirationDate);
        }
        
        return expirationDate;
    }

    private ProtocolAction findProtocolAction(int beanType, List<ProtocolAction> protocolActions, ProtocolSubmission currentSubmission) throws Exception {
        String actionTypeCode;
        switch(beanType) {
            case EXPEDITE_APPROVAL_BEAN_TYPE:
                actionTypeCode = ProtocolActionType.EXPEDITE_APPROVAL;
                break;
            case APPROVE_BEAN_TYPE:
                actionTypeCode =  ProtocolActionType.APPROVED;
                break;
            case REOPEN_BEAN_TYPE:
                actionTypeCode =  ProtocolActionType.REOPEN_ENROLLMENT;
                break;
            case CLOSE_ENROLLMENT_BEAN_TYPE:
                actionTypeCode =  ProtocolActionType.CLOSED_FOR_ENROLLMENT;
                break;
            case SUSPEND_BEAN_TYPE:
                actionTypeCode =  ProtocolActionType.SUSPENDED;
                break;
            case SUSPEND_BY_DSMB_BEAN_TYPE:
                actionTypeCode =  ProtocolActionType.SUSPENDED_BY_DSMB;
                break;
            case CLOSE_BEAN_TYPE:
                actionTypeCode =  ProtocolActionType.CLOSED_ADMINISTRATIVELY_CLOSED;
                break;
            case EXPIRE_BEAN_TYPE:
                actionTypeCode =  ProtocolActionType.EXPIRED;
                break;
            case TERMINATE_BEAN_TYPE:
                actionTypeCode =  ProtocolActionType.TERMINATED;
                break;
            case PERMIT_DATA_ANALYSIS_BEAN_TYPE:
                actionTypeCode =  ProtocolActionType.DATA_ANALYSIS_ONLY;
                break;
            default:
                //should never get here
                throw new Exception("Invalid bean type provided");
        }
        for (ProtocolAction pa : protocolActions) {
            if (pa.getProtocolActionType().getProtocolActionTypeCode().equals(actionTypeCode)
                    && ( pa.getProtocolSubmission() == null || pa.getProtocolSubmission().equals(currentSubmission))) {
                return pa;
            }
        }
        return null;
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
    private ProtocolAmendmentBean createAmendmentBean() throws Exception {
        ProtocolAmendmentBean amendmentBean = new ProtocolAmendmentBean();
     
        ProtocolAmendRenewService protocolAmendRenewService = getProtocolAmendRenewService();
        List<String> moduleTypeCodes = protocolAmendRenewService.getAvailableModules(getProtocol().getProtocolNumber());
        
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
        AdminCorrectionBean adminCorrectionBean = new AdminCorrectionBean();
        List<String> moduleTypeCodes = getProtocolAmendRenewService().getAvailableModules(getProtocol().getProtocolNumber());
        
        for (String moduleTypeCode : moduleTypeCodes) {
            enableModuleOption(moduleTypeCode, adminCorrectionBean);
        }
        
        return adminCorrectionBean;
    }
    
    private UndoLastActionBean createUndoLastActionBean(Protocol protocol) throws Exception {
        undoLastActionBean = new UndoLastActionBean();
        undoLastActionBean.setProtocol(protocol);
        Collections.sort(protocol.getProtocolActions(), new Comparator<ProtocolAction>() {
            public int compare(ProtocolAction action1, ProtocolAction action2) {
                return action2.getActualActionDate().compareTo(action1.getActualActionDate());
            }
        });
        undoLastActionBean.setActionsPerformed(protocol.getProtocolActions());
        return undoLastActionBean;
    }
    
    /**
     * Enable a module for selection by a user by setting its corresponding enabled
     * flag to true in the amendment bean.
     * @param moduleTypeCode
     * @param amendmentBean
     */
    private void enableModuleOption(String moduleTypeCode, ProtocolEditableBean amendmentBean) {
        if (StringUtils.equals(ProtocolModule.GENERAL_INFO, moduleTypeCode)) {
            amendmentBean.setGeneralInfoEnabled(true);
        } 
        else if (StringUtils.equals(ProtocolModule.ADD_MODIFY_ATTACHMENTS, moduleTypeCode)) {
            amendmentBean.setAddModifyAttachmentsEnabled(true);
        }
        else if (StringUtils.equals(ProtocolModule.AREAS_OF_RESEARCH, moduleTypeCode)) {
            amendmentBean.setAreasOfResearchEnabled(true);
        }
        else if (StringUtils.equals(ProtocolModule.FUNDING_SOURCE, moduleTypeCode)) {
            amendmentBean.setFundingSourceEnabled(true);
        }
        else if (StringUtils.equals(ProtocolModule.OTHERS, moduleTypeCode)) {
            amendmentBean.setOthersEnabled(true);
        }
        else if (StringUtils.equals(ProtocolModule.PROTOCOL_ORGANIZATIONS, moduleTypeCode)) {
            amendmentBean.setProtocolOrganizationsEnabled(true);
        }
        else if (StringUtils.equals(ProtocolModule.PROTOCOL_PERSONNEL, moduleTypeCode)) {
            amendmentBean.setProtocolPersonnelEnabled(true);
        }
        else if (StringUtils.equals(ProtocolModule.PROTOCOL_REFERENCES, moduleTypeCode)) {
            amendmentBean.setProtocolReferencesEnabled(true);
        }
        else if (StringUtils.equals(ProtocolModule.SPECIAL_REVIEW, moduleTypeCode)) {
            amendmentBean.setSpecialReviewEnabled(true);
        }
        else if (StringUtils.equals(ProtocolModule.SUBJECTS,moduleTypeCode)) {
            amendmentBean.setSubjectsEnabled(true);
        }
    }

    private ProtocolAmendRenewService getProtocolAmendRenewService() {
        if (this.protocolAmendRenewService == null) {
            this.protocolAmendRenewService = KraServiceLocator.getService(ProtocolAmendRenewService.class);        
        }
        return this.protocolAmendRenewService;
    }

    public void prepareView() {
        protocolSubmitAction.prepareView();
        canSubmitProtocol = hasSubmitProtocolPermission();
        assignCmtSchedBean.prepareView();
        protocolAssignReviewersBean.prepareView();
        submissionConstraint = getParameterValue(Constants.PARAMETER_IRB_COMM_SELECTION_DURING_SUBMISSION);
        
        canCreateAmendment = hasCreateAmendmentPermission();
        canCreateRenewal = hasCreateRenewalPermission();
        canNotifyIrb = hasNotifyIrbPermission();
        canWithdraw = hasWithdrawPermission();
        canRequestClose = hasRequestClosePermission();
        canRequestSuspension = hasRequestSuspensionPermission();
        canRequestCloseEnrollment = hasRequestCloseEnrollmentPermission();
        canRequestReOpenEnrollment = hasRequestReOpenEnrollmentPermission();
        canRequestDataAnalysis = hasRequestDataAnalysisPermission();
        canRequestTerminate = hasRequestTerminatePermission();
        canDeleteProtocolAmendRenew = hasDeleteProtocolAmendRenewPermission();
        canAssignToAgenda = hasAssignToAgendaPermission();
        canAssignCmtSched = hasAssignCmtSchedPermission();
        canAssignReviewers = hasAssignReviewersPermission();
        canGrantExemption = hasGrantExemptionPermission();
        canExpediteApproval = hasExpediteApprovalPermission();
        canApprove = hasApprovePermission();
        canReopen = hasReopenPermission();
        canCloseEnrollment = hasCloseEnrollmentPermission();
        canSuspend = hasSuspendPermission();
        canSuspendByDmsb = hasSuspendByDmsbPermission();
        canClose = hasClosePermission();
        canExpire = hasExpirePermission();
        canTerminate = hasTerminatePermission();
        canPermitDataAnalysis = hasPermitDataAnalysisPermission();
        canMakeAdminCorrection = hasAdminCorrectionPermission();
        canRecordCommitteeDecision = hasRecordCommitteeDecisionPermission();
        canEnterRiskLevel = hasEnterRiskLevelPermission();
        canUndoLastAction = hasUndoLastActionPermission();
        canIrbAcknowledgement = hasIrbAcknowledgementPermission();
        canDefer = hasDeferPermission();
        canModifyProtocolSubmission = hasCanModifySubmissionPermission();
        
        initSummaryDetails();
        
        initSubmissionDetails();
    }
    
    public void prepareCommentsView() {
        assignToAgendaBean.initComments();
        protocolGrantExemptionBean.initComments();
        irbAcknowledgementBean.initComments();
        protocolExpediteApprovalBean.initComments();
        protocolApproveBean.initComments();
        protocolReopenBean.initComments();
        protocolCloseEnrollmentBean.initComments();
        protocolSuspendBean.initComments();
        protocolSuspendByDmsbBean.initComments();
        protocolCloseBean.initComments();
        protocolExpireBean.initComments();
        protocolTerminateBean.initComments();
        protocolPermitDataAnalysisBean.initComments();
        committeeDecision.initComments();
        protocolDeferBean.initComments();
    }
    
    private ProtocolVersionService getProtocolVersionService() {
        if (this.protocolVersionService == null) {
            this.protocolVersionService = KraServiceLocator.getService(ProtocolVersionService.class);        
        }
        return this.protocolVersionService;
    }

    private String getParameterValue(String parameterName) {
        return this.getParameterService().getParameterValue(ProtocolDocument.class, parameterName);      
    }
    
    private boolean hasSubmitProtocolPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.SUBMIT_PROTOCOL, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasCreateAmendmentPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.CREATE_PROTOCOL_AMMENDMENT, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasCreateRenewalPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.CREATE_PROTOCOL_RENEWAL, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasNotifyIrbPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.NOTIFY_IRB, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasWithdrawPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_WITHDRAW, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasRequestClosePermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_CLOSE, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasRequestSuspensionPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_SUSPENSION, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasRequestCloseEnrollmentPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_CLOSE_ENROLLMENT, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasRequestReOpenEnrollmentPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_REOPEN_ENROLLMENT, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasRequestDataAnalysisPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_DATA_ANALYSIS, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasRequestTerminatePermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_TERMINATE, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasDeleteProtocolAmendRenewPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_AMEND_RENEW_DELETE, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasAssignToAgendaPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.ASSIGN_TO_AGENDA, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasAssignCmtSchedPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.ASSIGN_TO_COMMITTEE_SCHEDULE, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasAssignReviewersPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.ASSIGN_REVIEWERS, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasGrantExemptionPermission() {
        return hasPermission(TaskName.GRANT_EXEMPTION);
    }
    
    private boolean hasExpediteApprovalPermission() {
        return hasPermission(TaskName.EXPEDITE_APPROVAL);
    }
    
    private boolean hasApprovePermission() {
        return hasPermission(TaskName.APPROVE_PROTOCOL);
    }
    
    private boolean hasReopenPermission() {
        return hasGenericPermission(GenericProtocolAuthorizer.REOPEN_PROTOCOL);
    }
    
    private boolean hasCloseEnrollmentPermission() {
        return hasGenericPermission(GenericProtocolAuthorizer.CLOSE_ENROLLMENT_PROTOCOL);
    }
    
    private boolean hasSuspendPermission() {
        return hasGenericPermission(GenericProtocolAuthorizer.SUSPEND_PROTOCOL);
    }
    
    private boolean hasSuspendByDmsbPermission() {
        return hasGenericPermission(GenericProtocolAuthorizer.SUSPEND_PROTOCOL_BY_DSMB);
    }
    
    private boolean hasClosePermission() {
        return hasGenericPermission(GenericProtocolAuthorizer.CLOSE_PROTOCOL);
    }
    
    private boolean hasExpirePermission() {
        return hasGenericPermission(GenericProtocolAuthorizer.EXPIRE_PROTOCOL);
    }
    
    private boolean hasTerminatePermission() {
        return hasGenericPermission(GenericProtocolAuthorizer.TERMINATE_PROTOCOL);
    }
    
    private boolean hasPermitDataAnalysisPermission() {
        return hasGenericPermission(GenericProtocolAuthorizer.PERMIT_DATA_ANALYSIS);
    }
    
    private boolean hasAdminCorrectionPermission() {
        return hasPermission(TaskName.PROTOCOL_ADMIN_CORRECTION);
    }
    
    private boolean hasUndoLastActionPermission() {
        return hasPermission(TaskName.PROTOCOL_UNDO_LAST_ACTION) && undoLastActionBean.canUndoLastAction();
    }
    
    private boolean hasRecordCommitteeDecisionPermission() {
        return hasPermission(TaskName.RECORD_COMMITTEE_DECISION);
    }
    
    private boolean hasEnterRiskLevelPermission() {
        return hasPermission(TaskName.ENTER_RISK_LEVEL);
    }
    
    private boolean hasDeferPermission() {
        return hasPermission(TaskName.DEFER_PROTOCOL);
    }
    
    private boolean hasPermission(String taskName) {
        ProtocolTask task = new ProtocolTask(taskName, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasGenericPermission(String genericActionName) {
        ProtocolTask task = new ProtocolTask(TaskName.GENERIC_PROTOCOL_ACTION, getProtocol(), genericActionName);
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasIrbAcknowledgementPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.IRB_ACKNOWLEDGEMENT, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private boolean hasCanModifySubmissionPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL_SUBMISSION, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    private TaskAuthorizationService getTaskAuthorizationService() {
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
    
    /**
     * Get the userName of the user for the current session.
     * @return the current session's userName
     */
    private String getUserIdentifier() {
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
    
    public ProtocolRequestBean getProtocolCloseEnrollmentRequestBean() {
        return protocolCloseEnrollmentRequestBean;
    }

    public ProtocolRequestBean getProtocolReOpenEnrollmentRequestBean() {
        return protocolReOpenEnrollmentRequestBean;
    }
    
    public ProtocolRequestBean getProtocolDataAnalysisRequestBean() {
        return protocolDataAnalysisRequestBean;
    }
    
    public ProtocolRequestBean getProtocolTerminateRequestBean(){
        return this.protocolTerminateRequestBean;
    }
    
    public ProtocolNotifyIrbBean getProtocolNotifyIrbBean() {
        return protocolNotifyIrbBean;
    }
    
    public ProtocolAmendmentBean getProtocolAmendmentBean() {
        return protocolAmendmentBean;
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
    
    public ProtocolAssignCmtSchedBean getAssignCmtSchedBean() {
        return assignCmtSchedBean;
    }
    
    public ProtocolAssignReviewersBean getProtocolAssignReviewersBean() {
        return protocolAssignReviewersBean;
    }
                           
    public ProtocolGrantExemptionBean getProtocolGrantExemptionBean() {
        return protocolGrantExemptionBean;
    }
    
    public IrbAcknowledgementBean getIrbAcknowledgementBean() {
        return irbAcknowledgementBean;
    }
    

    public ProtocolApproveBean getProtocolExpediteApprovalBean() {
        return protocolExpediteApprovalBean;
    }

    public ProtocolApproveBean getProtocolApproveBean() {
        return protocolApproveBean;
    }
    
    public ProtocolGenericActionBean getProtocolReopenBean() {
        return protocolReopenBean;
    }
    
    public ProtocolGenericActionBean getProtocolCloseEnrollmentBean() {
        return protocolCloseEnrollmentBean;
    }
    
    public ProtocolGenericActionBean getProtocolSuspendBean() {
        return protocolSuspendBean;
    }
    
    public ProtocolGenericActionBean getProtocolSuspendByDmsbBean() {
        return protocolSuspendByDmsbBean;
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
    
    public ProtocolGenericActionBean getProtocolPermitDataAnalysisBean() {
        return protocolPermitDataAnalysisBean;
    }
    
    public AdminCorrectionBean getProtocolAdminCorrectionBean() {
        return protocolAdminCorrectionBean;
    }
    
    public UndoLastActionBean getUndoLastActionBean() {
        return undoLastActionBean;
    }

    public CommitteeDecision getCommitteeDecision() {
        return committeeDecision;
    }
    
    public ProtocolModifySubmissionAction getProtocolModifySubmissionAction() {
        return this.protocolModifySubmissionAction;
    }

    public boolean getCanCreateAmendment() {
        return canCreateAmendment;
    }

    public boolean getCanCreateRenewal() {
        return canCreateRenewal;
    }
    
    public boolean getCanNotifyIrb() {
        return canNotifyIrb;
    }
    
    public boolean getCanWithdraw() {
        return canWithdraw;
    }
    
    public boolean getCanRequestClose() {
        return canRequestClose;
    }
    
    public boolean getCanRequestSuspension() {
        return canRequestSuspension;
    }
    
    public boolean getCanRequestCloseEnrollment() {
        return canRequestCloseEnrollment;
    }
    
    public boolean getCanRequestReOpenEnrollment() {
        return canRequestReOpenEnrollment;
    }
    
    public boolean getCanRequestDataAnalysis() {
        return canRequestDataAnalysis;
    }
    
    public boolean getcanRequestTerminate(){
        return this.canRequestTerminate;
    }
    
    public boolean getCanDeleteProtocolAmendRenew() {
        return canDeleteProtocolAmendRenew;
    }
    
    public boolean getCanAssignToAgenda() {
        return canAssignToAgenda;
    }
    
    public boolean getCanAssignCmtSched() {
        return canAssignCmtSched;
    }
    
    public boolean getCanAssignReviewers() {
        return canAssignReviewers;
    }
    
    public boolean getCanGrantExemption() {
        return canGrantExemption;
    }
    
    public boolean getCanExpediteApproval() {
        return canExpediteApproval;
    }
    
    public boolean getCanApprove() {
        return canApprove;
    }
    
    public boolean getCanReopen() {
        return canReopen;
    }
    
    public boolean getCanCloseEnrollment() {
        return canCloseEnrollment;
    }
    
    public boolean getCanSuspend() {
        return canSuspend;
    }
    
    public boolean getCanSuspendByDmsb() {
        return canSuspendByDmsb;
    }
    
    public boolean getCanClose() {
        return canClose;
    }
    
    public boolean getCanExpire() {
        return canExpire;
    }
    
    public boolean getCanTerminate() {
        return canTerminate;
    }
    
    public boolean getCanPermitDataAnalysis() {
        return canPermitDataAnalysis;
    }
    
    public boolean getCanEnterRiskLevel() {
        return canEnterRiskLevel;
    }
    
    public boolean getCanMakeAdminCorrection() {
        return canMakeAdminCorrection;
    }
    
    public boolean getCanUndoLastAction() {
        return canUndoLastAction;
    }
    
    public boolean getCanRecordCommitteeDecision() {
        return canRecordCommitteeDecision;
    }
    
    public boolean getCanModifyProtocolSubmission() {
        return this.canModifyProtocolSubmission;
    }
    
    public boolean getCanIrbAcknowledgement() {
        return canIrbAcknowledgement;
    }
    
    public boolean getCanDefer() {
        return canDefer;
    }

    public void setPrintTag(String printTag) {
        this.printTag = printTag;
    }
    
    public String getPrintTag() {
        return printTag;
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

    public void setHistoryDateRangeFilter(DateRangeFilter historyDateRangeFilter) {
        this.historyDateRangeFilter = historyDateRangeFilter;
    }

    public DateRangeFilter getHistoryDateRangeFilter() {
        return historyDateRangeFilter;
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
     * Get the filtered list of protocol actions sorted by the Actual Action Date.
     * The list is filtered based upon the current Date Range Filter.  Protocol actions
     * that don't fall with the given range are not returned.
     * @return the filtered list of protocol actions
     */
    public List<ProtocolAction> getFilteredProtocolActions() {
        List<ProtocolAction> filteredProtocolActions = new ArrayList<ProtocolAction>();
        List<ProtocolAction> protocolActions = form.getProtocolDocument().getProtocol().getProtocolActions();
        for (ProtocolAction protocolAction : protocolActions) {
            if (inDateRange(protocolAction)) {
                filteredProtocolActions.add(protocolAction);
            }
        }
        Collections.sort(filteredProtocolActions, new Comparator<ProtocolAction>() {
            public int compare(ProtocolAction action1, ProtocolAction action2) {
                return action2.getActualActionDate().compareTo(action1.getActualActionDate());
            }
        });
     
        return filteredProtocolActions;
    }

    /**
     * Is the given Protocol Action within the range of the Date Range Filter?
     * @param protocolAction the protocol action
     * @return true if in the range; otherwise false
     */
    private boolean inDateRange(ProtocolAction protocolAction) {
        Date beginningOn = historyDateRangeFilter.getBeginningOn();
        if (beginningOn != null) {
            Timestamp startTimestamp = new Timestamp(beginningOn.getTime());
            if (protocolAction.getActionDate().before(startTimestamp)) {
                return false;
            }
        }
        Date endingOn = historyDateRangeFilter.getEndingOn();
        if (endingOn != null) {
            Timestamp endTimestamp = new Timestamp(endingOn.getTime() + ONE_DAY - 1);
            if (protocolAction.getActionDate().after(endTimestamp)) {
                return false;
            }
        }
        return true;
    }
    
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

    public ProtocolSubmission getSelectedSubmission() {
        return selectedSubmission;
        
    }

    public void setSelectedSubmission(ProtocolSubmission selectedSubmission) {
        this.selectedSubmission = selectedSubmission;

    }
    
    /*
     * retrieve reviewer's full name from member data
     */
//    private void setupReviewerName() {
//        if (CollectionUtils.isNotEmpty(selectedSubmission.getProtocolReviewers())) {
//            List<CommitteeMembership> members = getCommitteeService().getAvailableMembers(selectedSubmission.getCommitteeId(),
//                    selectedSubmission.getCommitteeSchedule().getScheduleId());
//            for (CommitteeMembership member : members) {
//                for (ProtocolReviewer reviewer : selectedSubmission.getProtocolReviewers()) {
//                    if (isReviewerMatchedMember(member, reviewer)) {
//                        //reviewer.setFullName(member.getPersonName());
//                        break;
//                    }
//                }
//
//            }
//        }
//    }    
  
    private CommitteeService getCommitteeService() {
        return KraServiceLocator.getService(CommitteeService.class);
    }


    public List<CommitteeScheduleMinute> getReviewComments() {
        return reviewComments;
    }


    /*
     * Retrieve review comments from committee schedule minutes
     */
    private void setReviewComments() {
        reviewComments = new ArrayList<CommitteeScheduleMinute>();
        if (selectedSubmission != null && CollectionUtils.isNotEmpty(selectedSubmission.getCommitteeSchedule().getCommitteeScheduleMinutes())) {
            for (CommitteeScheduleMinute minute : selectedSubmission.getCommitteeSchedule().getCommitteeScheduleMinutes()) {
                String minuteEntryTypeCode = minute.getMinuteEntryTypeCode();
                String protocolNumber = minute.getProtocol().getProtocolNumber();
                if (MinuteEntryType.PROTOCOL.equals(minuteEntryTypeCode) && selectedSubmission.getProtocol().getProtocolNumber().equals(protocolNumber)) {
                    reviewComments.add(minute);
                }

            }
        }
    }


    public List<ProtocolVoteAbstainee> getAbstainees() {
        return abstainees;
    }


    public void setAbstainees(List<ProtocolVoteAbstainee> abstainees) {
        this.abstainees = abstainees;
    }
    
    private CommitteeDecisionService getCommitteeDecisionService() {
        return KraServiceLocator.getService("protocolCommitteeDecisionService");
    }
    
    /*
     * This is to set up the full name of Abstainer & Recused based on matched kcperson or rolodex.
     */
    private void setupVoterName() {
        for (ProtocolVoteAbstainee abstainee : abstainees) {
            abstainee.setFullName(getVoterName(abstainee));
        }
        for (ProtocolVoteRecused recuser : recusers) {
            recuser.setFullName(getVoterName(recuser));
        }
    }

    /*
     * Utility method to check if reviewer matched committee member
     */
   private boolean isReviewerMatchedMember(CommitteeMembership member, ProtocolReviewer reviewer) {
       if( reviewer == null || member == null ) return false;
       return reviewer.isProtocolReviewerFromCommitteeMembership(member);
    }
    /*
     * Utility method to get voter name
     */
    private String getVoterName(ProtocolMeetingVoter voter) {
        String voterName = "Person " + voter.getPersonId() + " not found";
        if (!voter.getNonEmployeeFlag()) {
            KcPerson kcPerson = getKcPersonService().getKcPersonByPersonId(voter.getPersonId());
            if (kcPerson != null) {
                voterName = kcPerson.getFullName();
            }
        } else {
            Map primaryKeys = new HashMap();
            primaryKeys.put("rolodexId", voter.getPersonId());
            Rolodex rolodex = (Rolodex) getBusinessObjectService().findByPrimaryKey(Rolodex.class, primaryKeys);
            if (rolodex != null) {
                voterName = rolodex.getFullName();
            }
        }
        return voterName;
    }


    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KraServiceLocator.getService(KcPersonService.class);
        }
        
        return this.kcPersonService;
    }

    private BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);    
    }
    
    public int getCurrentSubmissionNumber() {
        return currentSubmissionNumber;
    }


    public void setCurrentSubmissionNumber(int currentSubmissionNumber) {
        this.currentSubmissionNumber = currentSubmissionNumber;
    }
    
    /**
     * Sets up the summary details subpanel.
     */
    public void initSummaryDetails() {
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
    }

    /**
     * Sets up dates for the submission details subpanel.
     */
    public void initSubmissionDetails() {
        if (currentSubmissionNumber == -1 && CollectionUtils.isNotEmpty(getProtocol().getProtocolSubmissions())) {
            currentSubmissionNumber = getProtocol().getProtocolSubmissions().size() - 1;
        }    
        if (currentSubmissionNumber > -1) {
            setSelectedSubmission(getProtocol().getProtocolSubmissions().get(currentSubmissionNumber));
        } else if (currentSubmissionNumber == -1) {
            setSelectedSubmission(getProtocol().getProtocolSubmission());

        }
        selectedSubmission.refreshReferenceObject("committeeSchedule");
        //setupReviewerName(); 

        if (selectedSubmission.getCommitteeSchedule() != null) {
            setReviewComments();
            setAbstainees((List<ProtocolVoteAbstainee>)getCommitteeDecisionService().getMeetingVoters(selectedSubmission.getProtocolId(),
                    selectedSubmission.getSubmissionId(), ProtocolVoteAbstainee.class));
            setRecusers((List<ProtocolVoteRecused>)getCommitteeDecisionService().getMeetingVoters(selectedSubmission.getProtocolId(),
                    selectedSubmission.getSubmissionId(), ProtocolVoteRecused.class));
            setupVoterName();
        } else {
            reviewComments = new ArrayList<CommitteeScheduleMinute>();
            abstainees = new ArrayList<ProtocolVoteAbstainee>();
        }

    }


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

    public ProtocolGenericActionBean getProtocolDeferBean() {
        return protocolDeferBean;
    }
}
