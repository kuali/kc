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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
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
import org.kuali.kra.irb.actions.modifysubmission.ProtocolModifySubmissionBean;
import org.kuali.kra.irb.actions.noreview.ProtocolReviewNotRequiredBean;
import org.kuali.kra.irb.actions.notifyirb.ProtocolActionAttachment;
import org.kuali.kra.irb.actions.notifyirb.ProtocolNotifyIrbBean;
import org.kuali.kra.irb.actions.request.ProtocolRequestBean;
import org.kuali.kra.irb.actions.reviewcomments.ReviewerCommentsService;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitAction;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitActionService;
import org.kuali.kra.irb.actions.undo.UndoLastActionBean;
import org.kuali.kra.irb.actions.withdraw.ProtocolWithdrawBean;
import org.kuali.kra.irb.auth.GenericProtocolAuthorizer;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.irb.summary.ProtocolSummary;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.meeting.ProtocolVoteAbstainee;
import org.kuali.kra.meeting.ProtocolVoteRecused;
import org.kuali.kra.rules.ErrorReporter;
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
    private static final List<String> ACTION_TYPE_SUBMISSION_DOC;
    static {
        final List<String> codes = new ArrayList<String>();     
        codes.add(ProtocolActionType.NOTIFY_IRB);
        codes.add(ProtocolActionType.REQUEST_TO_CLOSE);
        codes.add(ProtocolActionType.REQUEST_FOR_DATA_ANALYSIS_ONLY);
        codes.add(ProtocolActionType.REQUEST_FOR_SUSPENSION);
        codes.add(ProtocolActionType.REQUEST_TO_REOPEN_ENROLLMENT);
        codes.add(ProtocolActionType.REQUEST_FOR_TERMINATION);
        codes.add(ProtocolActionType.REQUEST_TO_CLOSE_ENROLLMENT);
        ACTION_TYPE_SUBMISSION_DOC = codes;
    }

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
    private boolean canApproveResponse = false;
    private boolean canApprove = false;
    private boolean canDisapprove = false;
    private boolean canReturnForSMR = false;
    private boolean canReturnForSRR = false;
    private boolean canReopen = false;
    private boolean canCloseEnrollment = false;
    private boolean canSuspend = false;
    private boolean canSuspendByDsmb = false;
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
    private boolean canReviewNotRequired = false;
    private boolean canManageReviewComments = false;
    
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
    private ProtocolApproveBean protocolResponseApprovalBean;
    private ProtocolGenericActionBean protocolDisapproveBean;
    private ProtocolGenericActionBean protocolSMRBean;
    private ProtocolGenericActionBean protocolSRRBean;
    private ProtocolGenericActionBean protocolReopenBean;
    private ProtocolGenericActionBean protocolCloseEnrollmentBean;
    private ProtocolGenericActionBean protocolSuspendBean;
    private ProtocolGenericActionBean protocolSuspendByDsmbBean;
    private ProtocolGenericActionBean protocolCloseBean;
    private ProtocolGenericActionBean protocolExpireBean;
    private ProtocolGenericActionBean protocolTerminateBean;
    private ProtocolGenericActionBean protocolPermitDataAnalysisBean;
    private AdminCorrectionBean protocolAdminCorrectionBean;
    private UndoLastActionBean undoLastActionBean;
    private CommitteeDecision committeeDecision;
    private IrbAcknowledgementBean irbAcknowledgementBean;
    private ProtocolModifySubmissionBean protocolModifySubmissionBean;
    private ProtocolGenericActionBean protocolDeferBean;
    private ProtocolReviewNotRequiredBean protocolReviewNotRequiredBean;
    private ProtocolGenericActionBean protocolManageReviewCommentsBean;
    
    private boolean prevDisabled;
    private boolean nextDisabled;
    private transient ParameterService parameterService;
    private transient TaskAuthorizationService taskAuthorizationService;
    private transient ProtocolAmendRenewService protocolAmendRenewService;
    private transient ProtocolVersionService protocolVersionService;
    private transient ProtocolSubmitActionService protocolSubmitActionService;
    
    /*
     * Identifies the protocol "document" to print.
     */
    private String printTag;
    
    private ProtocolSummaryPrintOptions protocolSummaryPrintOptions;

    private Boolean summaryReport;
    private Boolean fullReport;
    private Boolean historyReport;
    private Boolean reviewCommentsReport;
    
    private ProtocolSummary protocolSummary;
    private ProtocolSummary prevProtocolSummary;
    private int currentSequenceNumber = -1;
    
    private String selectedHistoryItem;
    private Date filteredHistoryStartDate;
    private Date filteredHistoryEndDate;
    
    // additional properties for Submission Details
    private ProtocolSubmission selectedSubmission;
    private List<CommitteeScheduleMinute> reviewComments;        
    private List<ProtocolVoteAbstainee> abstainees;        
    private List<ProtocolVoteRecused> recusers;        
    private List<ProtocolReviewer> protocolReviewers;        
    private int currentSubmissionNumber;
    private String renewalSummary;
    private transient KcPersonService kcPersonService;
    private transient BusinessObjectService businessObjectService;
    private Map<String, ProtocolRequestBean>  actionTypeRequestBeanMap = new HashMap<String, ProtocolRequestBean>();
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
        initRequestBeanAndMap();
        protocolNotifyIrbBean = new ProtocolNotifyIrbBean();
        protocolAmendmentBean = createAmendmentBean();
        protocolRenewAmendmentBean = createAmendmentBean();
        protocolDeleteBean = new ProtocolDeleteBean();
        assignToAgendaBean = new ProtocolAssignToAgendaBean(this);
        assignCmtSchedBean = new ProtocolAssignCmtSchedBean(this);
        assignCmtSchedBean.init();
        protocolAssignReviewersBean = new ProtocolAssignReviewersBean(this);
        protocolGrantExemptionBean = new ProtocolGrantExemptionBean(this);
        protocolGrantExemptionBean.initComments();
        irbAcknowledgementBean = new IrbAcknowledgementBean(this);
        irbAcknowledgementBean.initComments();
        protocolExpediteApprovalBean = buildProtocolApproveBean(ProtocolActionType.EXPEDITE_APPROVAL, form.getProtocolDocument().getProtocol());
        protocolResponseApprovalBean = buildProtocolApproveBean(ProtocolActionType.RESPONSE_APPROVAL, form.getProtocolDocument().getProtocol());
        protocolApproveBean = buildProtocolApproveBean(ProtocolActionType.APPROVED, form.getProtocolDocument().getProtocol());
        protocolDisapproveBean = buildProtocolGenericActionBean(ProtocolActionType.DISAPPROVED, protocolActions, currentSubmission);
        protocolSMRBean = buildProtocolGenericActionBean(ProtocolActionType.SPECIFIC_MINOR_REVISIONS_REQUIRED, protocolActions, currentSubmission);
        protocolSRRBean = buildProtocolGenericActionBean(ProtocolActionType.SUBSTANTIVE_REVISIONS_REQUIRED, protocolActions, currentSubmission);
        protocolReopenBean = buildProtocolGenericActionBean(ProtocolActionType.REOPEN_ENROLLMENT, protocolActions, currentSubmission);
        protocolCloseEnrollmentBean = buildProtocolGenericActionBean(ProtocolActionType.CLOSED_FOR_ENROLLMENT, protocolActions, currentSubmission);
        protocolSuspendBean = buildProtocolGenericActionBean(ProtocolActionType.SUSPENDED, protocolActions, currentSubmission);
        protocolSuspendByDsmbBean = buildProtocolGenericActionBean(ProtocolActionType.SUSPENDED_BY_DSMB, protocolActions, currentSubmission);
        protocolCloseBean = buildProtocolGenericActionBean(ProtocolActionType.CLOSED_ADMINISTRATIVELY_CLOSED, protocolActions, currentSubmission);
        protocolExpireBean = buildProtocolGenericActionBean(ProtocolActionType.EXPIRED, protocolActions, currentSubmission);
        protocolTerminateBean = buildProtocolGenericActionBean(ProtocolActionType.TERMINATED, protocolActions, currentSubmission);
        protocolPermitDataAnalysisBean = buildProtocolGenericActionBean(ProtocolActionType.DATA_ANALYSIS_ONLY, protocolActions, currentSubmission);
        protocolAdminCorrectionBean = createAdminCorrectionBean();
        undoLastActionBean = createUndoLastActionBean(getProtocol());
        committeeDecision = new CommitteeDecision(this);
        committeeDecision.init();
        protocolModifySubmissionBean = new ProtocolModifySubmissionBean(this.getProtocol().getProtocolSubmission());
        protocolDeferBean = buildProtocolGenericActionBean(ProtocolActionType.DEFERRED, protocolActions, currentSubmission);
        protocolReviewNotRequiredBean = new ProtocolReviewNotRequiredBean();
        protocolManageReviewCommentsBean = buildProtocolGenericActionBean(ProtocolActionType.MANAGE_REVIEW_COMMENTS, protocolActions, currentSubmission);
        
        protocolSummaryPrintOptions = new ProtocolSummaryPrintOptions();
    }
    
    private void initRequestBeanAndMap() {
        protocolCloseRequestBean = new ProtocolRequestBean(ProtocolActionType.REQUEST_TO_CLOSE,
            ProtocolSubmissionType.REQUEST_TO_CLOSE, "protocolCloseRequestBean");
        protocolSuspendRequestBean = new ProtocolRequestBean(ProtocolActionType.REQUEST_FOR_SUSPENSION,
            ProtocolSubmissionType.REQUEST_FOR_SUSPENSION, "protocolSuspendRequestBean");
        protocolCloseEnrollmentRequestBean = new ProtocolRequestBean(ProtocolActionType.REQUEST_TO_CLOSE_ENROLLMENT,
            ProtocolSubmissionType.REQUEST_TO_CLOSE_ENROLLMENT, "protocolCloseEnrollmentRequestBean");
        protocolReOpenEnrollmentRequestBean = new ProtocolRequestBean(ProtocolActionType.REQUEST_TO_REOPEN_ENROLLMENT,
            ProtocolSubmissionType.REQUEST_TO_REOPEN_ENROLLMENT, "protocolReOpenEnrollmentRequestBean");
        protocolDataAnalysisRequestBean = new ProtocolRequestBean(ProtocolActionType.REQUEST_FOR_DATA_ANALYSIS_ONLY,
            ProtocolSubmissionType.REQUEST_FOR_DATA_ANALYSIS_ONLY, "protocolDataAnalysisRequestBean");
        protocolTerminateRequestBean = new ProtocolRequestBean(ProtocolActionType.REQUEST_FOR_TERMINATION,
            ProtocolSubmissionType.REQUEST_FOR_TERMINATION, "protocolTerminateRequestBean");
        actionTypeRequestBeanMap.put(ProtocolActionType.REQUEST_TO_CLOSE, protocolCloseRequestBean);
        actionTypeRequestBeanMap.put(ProtocolActionType.REQUEST_TO_CLOSE_ENROLLMENT, protocolCloseEnrollmentRequestBean);
        actionTypeRequestBeanMap.put(ProtocolActionType.REQUEST_TO_REOPEN_ENROLLMENT, protocolReOpenEnrollmentRequestBean);
        actionTypeRequestBeanMap.put(ProtocolActionType.REQUEST_FOR_DATA_ANALYSIS_ONLY, protocolDataAnalysisRequestBean);
        actionTypeRequestBeanMap.put(ProtocolActionType.REQUEST_FOR_SUSPENSION, protocolSuspendRequestBean);
        actionTypeRequestBeanMap.put(ProtocolActionType.REQUEST_FOR_TERMINATION, protocolTerminateRequestBean);

    }
    
    /**
     *     
     * This method builds a ProtocolGenericActionBean.  A number of different beans
     * in this object are of type ProtocolGenericActionBean, and all need to add
     * reviewer comments.  This encapsulates that.
     * @return a ProtocolGenericActionBean, and pre-populated with reviewer comments if any exist
     */
    private ProtocolGenericActionBean buildProtocolGenericActionBean(String actionTypeCode, List<ProtocolAction> protocolActions, ProtocolSubmission currentSubmission) throws Exception {
        ProtocolGenericActionBean bean = new ProtocolGenericActionBean(this);
        bean.initComments();
        ProtocolAction protocolAction = findProtocolAction(actionTypeCode, protocolActions, currentSubmission);
        if (protocolAction != null) {
            bean.setComments(protocolAction.getComments());
            java.sql.Date actionDate = new java.sql.Date(protocolAction.getActionDate().getYear(), protocolAction.getActionDate().getMonth(), 
                    protocolAction.getActionDate().getDay());
            bean.setActionDate(actionDate);
        }
        return bean;
    }
    
    private ProtocolApproveBean buildProtocolApproveBean(String actionTypeCode, Protocol protocol) throws Exception{
        ProtocolApproveBean bean = new ProtocolApproveBean(this);
        bean.initComments();
        ProtocolAction protocolAction = findProtocolAction(actionTypeCode, protocol.getProtocolActions(), protocol.getProtocolSubmission());
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

    private ProtocolAction findProtocolAction(String actionTypeCode, List<ProtocolAction> protocolActions, ProtocolSubmission currentSubmission) 
        throws Exception {

        for (ProtocolAction pa : protocolActions) {
            if (pa.getProtocolActionType().getProtocolActionTypeCode().equals(actionTypeCode)
                    && (pa.getProtocolSubmission() == null || pa.getProtocolSubmission().equals(currentSubmission))) {
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
        assignToAgendaBean.prepareView();
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
        canApproveResponse = hasResponseApprovalPermission();
        canApprove = hasApprovePermission();
        canDisapprove = hasDisapprovePermission();
        canReturnForSMR = hasReturnForSMRPermission();
        canReturnForSRR = hasReturnForSRRPermission();
        canReopen = hasReopenPermission();
        canCloseEnrollment = hasCloseEnrollmentPermission();
        canSuspend = hasSuspendPermission();
        canSuspendByDsmb = hasSuspendByDsmbPermission();
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
        canReviewNotRequired = hasReviewNotRequiredPermission();
        canManageReviewComments = hasManageReviewCommentsPermission();
        
        initSummaryDetails();
        initSubmissionDetails();
        initFilterDatesView();
    }
    
    /**
     * Refreshes the comments for all the beans from the database.  Use sparingly since this will erase non-persisted comments.
     */
    public void prepareCommentsView() {
        assignToAgendaBean.initComments();
        protocolGrantExemptionBean.initComments();
        irbAcknowledgementBean.initComments();
        protocolExpediteApprovalBean.initComments();
        protocolResponseApprovalBean.initComments();
        protocolApproveBean.initComments();
        protocolDisapproveBean.initComments();
        protocolSMRBean.initComments();
        protocolSRRBean.initComments();
        protocolReopenBean.initComments();
        protocolCloseEnrollmentBean.initComments();
        protocolSuspendBean.initComments();
        protocolSuspendByDsmbBean.initComments();
        protocolCloseBean.initComments();
        protocolExpireBean.initComments();
        protocolTerminateBean.initComments();
        protocolPermitDataAnalysisBean.initComments();
        committeeDecision.initComments();
        protocolDeferBean.initComments();
        protocolManageReviewCommentsBean.initComments();
    }
    
    private ProtocolVersionService getProtocolVersionService() {
        if (this.protocolVersionService == null) {
            this.protocolVersionService = KraServiceLocator.getService(ProtocolVersionService.class);        
        }
        return this.protocolVersionService;
    }
    
    private ProtocolSubmitActionService getProtocolSubmitActionService() {
        if (protocolSubmitActionService == null) {
            protocolSubmitActionService = KraServiceLocator.getService(ProtocolSubmitActionService.class);
        }
        return protocolSubmitActionService;
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
    
    private boolean hasResponseApprovalPermission() {
        return hasPermission(TaskName.RESPONSE_APPROVAL);
    }
    
    private boolean hasApprovePermission() {
        return hasPermission(TaskName.APPROVE_PROTOCOL);
    }
    
    private boolean hasDisapprovePermission() {
        return hasPermission(TaskName.DISAPPROVE_PROTOCOL);
    }
    
    private boolean hasReturnForSMRPermission() {
        return hasPermission(TaskName.RETURN_FOR_SMR);
    }
    
    private boolean hasReturnForSRRPermission() {
        return hasPermission(TaskName.RETURN_FOR_SRR);
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
    
    private boolean hasSuspendByDsmbPermission() {
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
    
    private boolean hasManageReviewCommentsPermission() {
        return hasPermission(TaskName.PROTOCOL_MANAGE_REVIEW_COMMENTS); 
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
    
    private boolean hasReviewNotRequiredPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REVIEW_NOT_REQUIRED, getProtocol());
        boolean retVal = getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
        return retVal;
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
    
    public ProtocolApproveBean getProtocolResponseApprovalBean() {
        return protocolResponseApprovalBean;
    }

    public ProtocolApproveBean getProtocolApproveBean() {
        return protocolApproveBean;
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
    
    public ProtocolGenericActionBean getProtocolReopenBean() {
        return protocolReopenBean;
    }
    
    public ProtocolGenericActionBean getProtocolCloseEnrollmentBean() {
        return protocolCloseEnrollmentBean;
    }
    
    public ProtocolGenericActionBean getProtocolSuspendBean() {
        return protocolSuspendBean;
    }
    
    public ProtocolGenericActionBean getProtocolSuspendByDsmbBean() {
        return protocolSuspendByDsmbBean;
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
    
    public ProtocolModifySubmissionBean getProtocolModifySubmissionBean() {
        return this.protocolModifySubmissionBean;
    }
    
    public ProtocolGenericActionBean getProtocolManageReviewCommentsBean() {
        return protocolManageReviewCommentsBean;
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
    
    public boolean getCanApproveResponse() {
        return canApproveResponse;
    }
    
    public boolean getCanApprove() {
        return canApprove;
    }
    
    public boolean getCanDisapprove() {
        return canDisapprove;
    }
    
    public boolean getCanReturnForSMR() {
        return canReturnForSMR;
    }
    
    public boolean getCanReturnForSRR() {
        return canReturnForSRR;
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
    
    public boolean getCanSuspendByDsmb() {
        return canSuspendByDsmb;
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
    
    public boolean getCanReviewNotRequired() {
        return this.canReviewNotRequired;
    }

    public boolean getCanManageReviewComments() {  
        return canManageReviewComments;
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
     * @param startDate
     * @param endDate
     */
    public void initFilterDatesView() {
        java.util.Date dayBeforeStartDate = null;
        java.util.Date dayAfterEndDate = null;
        
        if (filteredHistoryStartDate != null && filteredHistoryEndDate != null) {
            dayBeforeStartDate = DateUtils.addDays(filteredHistoryStartDate, -1);
            dayAfterEndDate = DateUtils.addDays(filteredHistoryStartDate, 1);
        }
        
        for (ProtocolAction protocolAction : getSortedProtocolActions()) {            
            Timestamp actionDate = protocolAction.getActionDate();
            if (dayBeforeStartDate != null && dayAfterEndDate != null) {
                protocolAction.setIsInFilterView(actionDate.after(dayBeforeStartDate) && actionDate.before(dayAfterEndDate));
            } else {
                protocolAction.setIsInFilterView(true);
            }
        }
    }
    
    /**
     * Prepares, sorts, and returns a list of protocol actions.
     * @return
     */
    public List<ProtocolAction> getSortedProtocolActions() {
        List<ProtocolAction> protocolActions = new ArrayList<ProtocolAction>();
        for (ProtocolAction protocolAction : form.getProtocolDocument().getProtocol().getProtocolActions()) {
            if (protocolAction.getSubmissionNumber() != null && ACTION_TYPE_SUBMISSION_DOC.contains(protocolAction.getProtocolActionTypeCode())) {
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
    
    @SuppressWarnings("unchecked")
    private Collection<ProtocolSubmissionDoc> getSubmissionDocs(ProtocolAction protocolAction) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("protocolNumber", protocolAction.getProtocolNumber());
        fieldValues.put("submissionNumber", protocolAction.getSubmissionNumber());
        return getBusinessObjectService().findMatchingOrderBy(ProtocolSubmissionDoc.class, fieldValues, "documentId", true);
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
        }
        
        return protocolSubmission;
    }
  
    private CommitteeService getCommitteeService() {
        return KraServiceLocator.getService(CommitteeService.class);
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
    
    private ReviewerCommentsService getReviewerCommentsService() {
        return KraServiceLocator.getService(ReviewerCommentsService.class);
    }
    
    private CommitteeDecisionService getCommitteeDecisionService() {
        return KraServiceLocator.getService("protocolCommitteeDecisionService");
    }
    
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
    
    public int getTotalSubmissions() {
        return getProtocolSubmitActionService().getTotalSubmissions(getProtocol());
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
        if (currentSubmissionNumber <= 0) {
            currentSubmissionNumber = getTotalSubmissions();
        }

        if (CollectionUtils.isNotEmpty(getProtocol().getProtocolSubmissions()) && getProtocol().getProtocolSubmissions().size() > 1) {
            setPrevNextFlag();
        } else {
            setPrevDisabled(true);
            setNextDisabled(true);
        }
        setReviewComments(getReviewerCommentsService().getReviewerComments(getProtocol().getProtocolNumber(),
                currentSubmissionNumber));
        setProtocolReviewers(getReviewerCommentsService().getProtocolReviewers(getProtocol().getProtocolNumber(),
                currentSubmissionNumber));
        setAbstainees(getCommitteeDecisionService().getAbstainers(getProtocol().getProtocolNumber(), currentSubmissionNumber));
        setRecusers(getCommitteeDecisionService().getRecusers(getProtocol().getProtocolNumber(), currentSubmissionNumber));

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
        int maxSubmissionNumber = 0;

        for (Integer submissionNumber : submissionNumbers) {
            if (submissionNumber > maxSubmissionNumber) {
                maxSubmissionNumber = submissionNumber;
            }
        }
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
    private List<Integer> getAvailableSubmissionNumbers() {
        List<Integer> submissionNumbers = new ArrayList<Integer>();
        for (ProtocolSubmission submission : getProtocol().getProtocolSubmissions()) {
            submissionNumbers.add(submission.getSubmissionNumber());
        }
        return submissionNumbers;
    }


    /*
     * utility method to set whether to display next or previous button on submission panel.
     */
    private void setPrevNextFlag() {
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

    public void addNotifyIrbAttachment() {
        getProtocolNotifyIrbBean().getActionAttachments().add(
                getProtocolNotifyIrbBean().getNewActionAttachment());
        getProtocolNotifyIrbBean().setNewActionAttachment(new ProtocolActionAttachment());
    }

    public void addRequestAttachment(String actionTypeCode) {
        getActionTypeRequestBeanMap(actionTypeCode).getActionAttachments().add(
                getActionTypeRequestBeanMap(actionTypeCode).getNewActionAttachment());
        getActionTypeRequestBeanMap(actionTypeCode).setNewActionAttachment(new ProtocolActionAttachment());
    }

    public boolean validFile(final ProtocolActionAttachment attachment, String propertyName) {
        
        boolean valid = true;
        
        //this got much more complex using anon keys
        if (attachment.getFile() == null || StringUtils.isBlank(attachment.getFile().getFileName())) {
            valid = false;
            new ErrorReporter().reportError("actionHelper." + propertyName + ".newActionAttachment.file",
                KeyConstants.ERROR_ATTACHMENT_REQUIRED);
        }
        
        return valid;
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

    
    public ProtocolReviewNotRequiredBean getProtocolReviewNotRequiredBean() {
        return this.protocolReviewNotRequiredBean;
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
    

    public ProtocolRequestBean getActionTypeRequestBeanMap(String actionTypeCode) {
        return actionTypeRequestBeanMap.get(actionTypeCode);
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

    
}
