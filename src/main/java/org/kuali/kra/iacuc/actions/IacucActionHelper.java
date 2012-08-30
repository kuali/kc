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
package org.kuali.kra.iacuc.actions;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.bo.CoeusSubModule;
import org.kuali.kra.common.committee.bo.CommonCommitteeSchedule;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.IacucProtocolVersionService;
import org.kuali.kra.iacuc.actions.amendrenew.IacucProtocolAmendRenewService;
import org.kuali.kra.iacuc.actions.amendrenew.IacucProtocolAmendRenewal;
import org.kuali.kra.iacuc.actions.amendrenew.IacucProtocolAmendmentBean;
import org.kuali.kra.iacuc.actions.amendrenew.IacucProtocolModule;
import org.kuali.kra.iacuc.actions.approve.IacucProtocolApproveBean;
import org.kuali.kra.iacuc.actions.assignCmt.IacucProtocolAssignCmtBean;
import org.kuali.kra.iacuc.actions.assignagenda.IacucProtocolAssignToAgendaBean;
import org.kuali.kra.iacuc.actions.correction.IacucAdminCorrectionBean;
import org.kuali.kra.iacuc.actions.decision.IacucCommitteeDecision;
import org.kuali.kra.iacuc.actions.decision.IacucCommitteeDecisionService;
import org.kuali.kra.iacuc.actions.delete.IacucProtocolDeleteBean;
import org.kuali.kra.iacuc.actions.followup.IacucFollowupActionService;
import org.kuali.kra.iacuc.actions.genericactions.IacucProtocolGenericActionBean;
import org.kuali.kra.iacuc.actions.modifysubmission.IacucProtocolModifySubmissionBean;
import org.kuali.kra.iacuc.actions.noreview.IacucProtocolReviewNotRequiredBean;
import org.kuali.kra.iacuc.actions.notifycommittee.IacucProtocolNotifyCommitteeBean;
import org.kuali.kra.iacuc.actions.notifyiacuc.IacucProtocolNotifyIacucBean;
import org.kuali.kra.iacuc.actions.print.IacucProtocolQuestionnairePrintingService;
import org.kuali.kra.iacuc.actions.request.IacucProtocolRequestBean;
import org.kuali.kra.iacuc.actions.reviewcomments.IacucReviewCommentsService;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmitAction;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmitActionService;
import org.kuali.kra.iacuc.actions.table.IacucProtocolTableBean;
import org.kuali.kra.iacuc.actions.undo.IacucProtocolUndoLastActionBean;
import org.kuali.kra.iacuc.actions.withdraw.IacucProtocolAdministrativelyIncompleteBean;
import org.kuali.kra.iacuc.actions.withdraw.IacucProtocolAdministrativelyWithdrawBean;
import org.kuali.kra.iacuc.actions.withdraw.IacucProtocolWithdrawBean;
import org.kuali.kra.iacuc.auth.IacucGenericProtocolAuthorizer;
import org.kuali.kra.iacuc.auth.IacucProtocolTask;
import org.kuali.kra.iacuc.onlinereview.IacucProtocolOnlineReview;
import org.kuali.kra.iacuc.onlinereview.IacucProtocolOnlineReviewService;
import org.kuali.kra.iacuc.questionnaire.IacucProtocolModuleQuestionnaireBean;
import org.kuali.kra.iacuc.summary.IacucProtocolSummary;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.actions.noreview.ProtocolReviewNotRequiredBean;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.ProtocolForm;
import org.kuali.kra.protocol.ProtocolOnlineReviewDocument;
import org.kuali.kra.protocol.ProtocolVersionService;
import org.kuali.kra.protocol.actions.ActionHelper;
import org.kuali.kra.protocol.actions.ProtocolAction;
import org.kuali.kra.protocol.actions.ProtocolActionBean;
import org.kuali.kra.protocol.actions.ProtocolEditableBean;
import org.kuali.kra.protocol.actions.ProtocolSubmissionDoc;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewModule;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewService;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewal;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendmentBean;
import org.kuali.kra.protocol.actions.approve.ProtocolApproveBean;
import org.kuali.kra.protocol.actions.assignagenda.ProtocolAssignToAgendaBean;
import org.kuali.kra.protocol.actions.correction.AdminCorrectionBean;
import org.kuali.kra.protocol.actions.decision.CommitteeDecision;
import org.kuali.kra.protocol.actions.decision.CommitteeDecisionService;
import org.kuali.kra.protocol.actions.decision.CommitteePerson;
import org.kuali.kra.protocol.actions.delete.ProtocolDeleteBean;
import org.kuali.kra.protocol.actions.followup.FollowupActionService;
import org.kuali.kra.protocol.actions.notify.ProtocolActionAttachment;
import org.kuali.kra.protocol.actions.notifycommittee.ProtocolNotifyCommitteeBean;
import org.kuali.kra.protocol.actions.print.ProtocolQuestionnairePrintingService;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsService;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmitAction;
import org.kuali.kra.protocol.actions.submit.ValidProtocolActionAction;
import org.kuali.kra.protocol.actions.undo.UndoLastActionBean;
import org.kuali.kra.protocol.actions.withdraw.ProtocolAdministrativelyIncompleteBean;
import org.kuali.kra.protocol.actions.withdraw.ProtocolAdministrativelyWithdrawBean;
import org.kuali.kra.protocol.actions.withdraw.ProtocolWithdrawBean;
import org.kuali.kra.protocol.auth.ProtocolTask;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondence;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewService;
import org.kuali.kra.protocol.questionnaire.ProtocolModuleQuestionnaireBean;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;
import org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService;
import org.kuali.kra.rules.ErrorReporter;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.kra.util.DateUtils;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

/**
 * The form helper class for the Protocol Actions tab.
 */
public class IacucActionHelper extends ActionHelper {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 777750088765246427L;
    
    
    /**
     * Each Helper must contain a reference to its document form
     * so that it can access the document.
     */
    private boolean canDeleteIacucProtocol;
    private boolean canDeleteIacucProtocolUnavailable;
    
    private boolean canReviewNotRequired;
    private boolean canReviewNotRequiredUnavailable;
    private boolean canNotifyIacuc = false;
    private boolean canNotifyIacucUnavailable = false;
    private boolean canDesignatedMemberApproval = false;
    private boolean canDesignatedMemberApprovalUnavailable = false;
    private boolean canHold = false;
    private boolean canHoldUnavailable = false;
    private boolean canLiftHold = false;
    private boolean canLiftHoldUnavailable = false;
    private boolean canRequestToLiftHold = false;
    private boolean canRequestToLiftHoldUnavailable = false;
    private boolean canRequestSuspend = false;
    private boolean canRequestSuspendUnavailable = false;
    private boolean canTable = false;
    private boolean canTableUnavailable = false;
    private boolean canIacucAcknowledge = false;
    private boolean canIacucAcknowledgeUnavailable = false;
    private boolean canIacucDeactivate = false;
    private boolean canIacucDeactivateUnavailable = false;
    private boolean canIacucRequestDeactivate = false;
    private boolean canIacucRequestDeactivateUnavailable = false;
    private boolean canAddDeactivateReviewerComments = false;
    private boolean canRemoveFromAgenda = false;

    private boolean canAssignCmt = false;
    private boolean canAssignCmtUnavailable = false;

    // indicator for whether there is submission questionnaire answer exist.
    // ie, questionnaire has been saved for a request/notify irb action
    private boolean submissionQuestionnaireExist;
    // check if there is submission questionnaire to answer
    private boolean toAnswerSubmissionQuestionnaire;

    // action beans that are specific to IACUC
    protected IacucProtocolTableBean iacucProtocolTableBean;
    protected IacucProtocolAssignCmtBean protocolAssignCmtBean;
    protected IacucProtocolModifySubmissionBean iacucProtocolModifySubmissionBean;
    protected IacucProtocolNotifyIacucBean iacucProtocolNotifyIacucBean;
    protected IacucProtocolGenericActionBean iacucProtocolDeactivateBean;
    protected IacucProtocolGenericActionBean iacucAcknowledgeBean;
    protected IacucProtocolGenericActionBean iacucProtocolHoldBean;
    protected IacucProtocolGenericActionBean iacucProtocolLiftHoldBean;
    protected IacucProtocolGenericActionBean iacucProtocolRemoveFromAgendaBean;
    protected ProtocolReviewNotRequiredBean iacucProtocolReviewNotRequiredBean;
    
    protected IacucProtocolRequestBean iacucProtocolDeactivateRequestBean;
    protected IacucProtocolRequestBean iacucProtocolLiftHoldRequestBean;
    protected IacucProtocolRequestBean iacucProtocolSuspendRequestBean;
    
    protected boolean canCreateContinuation = false;
    protected boolean canCreateContinuationUnavailable = false;
    protected boolean hasContinuations;
    protected String continuationSummary;
    protected ProtocolAmendmentBean protocolContinuationAmendmentBean;
    

    /**
     * Constructs an ActionHelper.
     * @param form the protocol form
     * @throws Exception 
     */
    public IacucActionHelper(ProtocolForm form) throws Exception {
        super(form);
        
        protocolAssignCmtBean = new IacucProtocolAssignCmtBean(this);
        iacucProtocolTableBean = new IacucProtocolTableBean(this);
        iacucProtocolModifySubmissionBean = new IacucProtocolModifySubmissionBean(this);
        iacucProtocolNotifyIacucBean = new IacucProtocolNotifyIacucBean(this);
        iacucProtocolDeactivateBean = this.buildProtocolGenericActionBean(IacucProtocolActionType.DEACTIVATED, Constants.IACUC_DEACTIVATE_ACTION_PROPERTY_KEY);
        iacucAcknowledgeBean = new IacucProtocolGenericActionBean(this, "actionHelper.iacucAcknowledgeBean");
        iacucProtocolHoldBean = new IacucProtocolGenericActionBean(this, "actionHelper.iacucProtocolHoldBean");
        iacucProtocolLiftHoldBean = new IacucProtocolGenericActionBean(this, "actionHelper.iacucProtocolLiftHoldBean");
        iacucProtocolDeactivateRequestBean = new IacucProtocolRequestBean(this, IacucProtocolActionType.REQUEST_DEACTIVATE,
                IacucProtocolSubmissionType.REQUEST_TO_DEACTIVATE, "iacucProtocolDeactivateRequestBean");
        iacucProtocolLiftHoldRequestBean = new IacucProtocolRequestBean(this, IacucProtocolActionType.REQUEST_LIFT_HOLD,
                IacucProtocolSubmissionType.REQUEST_TO_LIFT_HOLD, "iacucProtocolLiftHoldRequestBean");
        iacucProtocolSuspendRequestBean = new IacucProtocolRequestBean(this, IacucProtocolActionType.IACUC_REQUEST_SUSPEND,
                IacucProtocolSubmissionType.REQUEST_SUSPEND, "iacucProtocolSuspendRequestBean");
        iacucProtocolRemoveFromAgendaBean = new IacucProtocolGenericActionBean(this, "actionHelper.iacucProtocolRemoveFromAgendaBean");
        iacucProtocolReviewNotRequiredBean = new IacucProtocolReviewNotRequiredBean(this);
        initIacucSpecificActionBeanTaskMap();
   }
    

    /**
     * Initializes the mapping between the task names and the beans.  This is used to get the bean associated to the task name passed in from the tag file.
     * The reason TaskName (a text code) is used and ProtocolActionType (a number code) is not is because not every task is mapped to a ProtocolActionType.
     */
    private void initIacucSpecificActionBeanTaskMap() {
        actionBeanTaskMap.put(TaskName.IACUC_MODIFY_PROTOCOL_SUBMISSION, iacucProtocolModifySubmissionBean);
        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_TABLE, iacucProtocolTableBean);
        actionBeanTaskMap.put(TaskName.IACUC_ASSIGN_TO_COMMITTEE, protocolAssignCmtBean);
        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_TABLE, iacucProtocolTableBean);
        actionBeanTaskMap.put(TaskName.IACUC_NOTIFY_IACUC, iacucProtocolNotifyIacucBean);
        actionBeanTaskMap.put(TaskName.IACUC_ACKNOWLEDGEMENT, iacucAcknowledgeBean);
        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_DEACTIVATE, iacucProtocolDeactivateBean);
        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_REQUEST_DEACTIVATE, iacucProtocolDeactivateRequestBean);
        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_REQUEST_LIFT_HOLD, iacucProtocolLiftHoldRequestBean);
        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_REQUEST_SUSPENSION, iacucProtocolSuspendRequestBean);
        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_HOLD, iacucProtocolHoldBean);
        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_LIFT_HOLD, iacucProtocolLiftHoldBean);
        actionBeanTaskMap.put(TaskName.REMOVE_FROM_AGENDA, iacucProtocolRemoveFromAgendaBean);
        actionBeanTaskMap.put(TaskName.CREATE_PROTOCOL_CONTINUATION, protocolContinuationAmendmentBean);
}

        
    public IacucProtocolAssignCmtBean getProtocolAssignCmtBean() {
        return protocolAssignCmtBean;
    }

    public void setProtocolAssignCmtBean(IacucProtocolAssignCmtBean protocolAssignCmtBean) {
        this.protocolAssignCmtBean = protocolAssignCmtBean;
    }

    public IacucProtocolModifySubmissionBean getIacucProtocolModifySubmissionBean() {
        return iacucProtocolModifySubmissionBean;
    }

    public void setIacucProtocolModifySubmissionBean(IacucProtocolModifySubmissionBean iacucProtocolModifySubmissionBean) {
        this.iacucProtocolModifySubmissionBean = iacucProtocolModifySubmissionBean;
    }
    
    public IacucProtocolTableBean getIacucProtocolTableBean() {
        return iacucProtocolTableBean;
    }

    public void setIacucProtocolTableBean(IacucProtocolTableBean iacucProtocolTableBean) {
        this.iacucProtocolTableBean = iacucProtocolTableBean;
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
    @Override
    protected Date buildApprovalDate(Protocol protocol) {
        Date approvalDate = protocol.getApprovalDate();
        
        if (approvalDate == null || protocol.isNew() || protocol.isRenewal() || ((IacucProtocol)protocol).isContinuation()) {
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
    @Override
    protected Date buildExpirationDate(Protocol protocol, Date approvalDate) {
        Date expirationDate = protocol.getExpirationDate();
        
        if (expirationDate == null || protocol.isNew() || protocol.isRenewal() || ((IacucProtocol)protocol).isContinuation()) {
            java.util.Date newExpirationDate = DateUtils.addYears(approvalDate, 1);
            newExpirationDate = DateUtils.addDays(newExpirationDate, -1);
            expirationDate = DateUtils.convertToSqlDate(newExpirationDate);
        }
        
        return expirationDate;
    }

    private ProtocolAction findProtocolAction(String actionTypeCode, List<ProtocolAction> protocolActions, IacucProtocolSubmission currentSubmission) {

        for (ProtocolAction pa : protocolActions) {
            if (pa.getProtocolActionType().getProtocolActionTypeCode().equals(actionTypeCode)
                    && (pa.getProtocolSubmission() == null || pa.getProtocolSubmission().equals(currentSubmission))) {
                return pa;
            }
        }
        return null;
    }

  
    public void prepareView() throws Exception {
        protocolSubmitAction.prepareView();
        super.prepareView();
        IacucProtocolForm iacucProtocolForm = (IacucProtocolForm)form;
        iacucProtocolModifySubmissionBean.prepareView();

        submissionConstraint = getParameterValue(Constants.PARAMETER_IACUC_COMM_SELECTION_DURING_SUBMISSION);

        canSubmitProtocol = hasPermission(TaskName.SUBMIT_IACUC_PROTOCOL);
        canSubmitProtocolUnavailable = hasPermission(TaskName.SUBMIT_IACUC_PROTOCOL_UNAVAILABLE);
       
        // IACUC-specific actions
        canNotifyIacuc = hasPermission(TaskName.IACUC_NOTIFY_IACUC);
        canNotifyIacucUnavailable = hasPermission(TaskName.IACUC_NOTIFY_IACUC_UNAVAILABLE);
        canNotifyCommittee = hasPermission(TaskName.IACUC_NOTIFY_COMMITTEE);
        canHold = hasPermission(TaskName.IACUC_PROTOCOL_HOLD);
        canHoldUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_HOLD_UNAVAILABLE);
        canLiftHold = hasPermission(TaskName.IACUC_PROTOCOL_LIFT_HOLD);
        canLiftHoldUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_LIFT_HOLD_UNAVAILABLE);
        canRequestToLiftHold = hasPermission(TaskName.IACUC_PROTOCOL_REQUEST_LIFT_HOLD);
        canRequestToLiftHoldUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_REQUEST_LIFT_HOLD_UNAVAILABLE);
        canRequestSuspend = hasPermission(TaskName.IACUC_PROTOCOL_REQUEST_SUSPENSION);
        canRequestSuspendUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_REQUEST_SUSPENSION_UNAVAILABLE);
        canIacucAcknowledge = hasPermission(TaskName.IACUC_ACKNOWLEDGEMENT);
        canIacucAcknowledgeUnavailable = hasPermission(TaskName.IACUC_ACKNOWLEDGEMENT_UNAVAILABLE);
        canIacucDeactivate = hasPermission(TaskName.IACUC_PROTOCOL_DEACTIVATE);
        canIacucDeactivateUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_DEACTIVATE_UNAVAILABLE);
        canIacucRequestDeactivate = hasPermission(TaskName.IACUC_PROTOCOL_REQUEST_DEACTIVATE);
        canIacucRequestDeactivateUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_REQUEST_DEACTIVATE_UNAVAILABLE);
        
        canDesignatedMemberApproval = hasPermission(TaskName.IACUC_PROTOCOL_DESIGNATED_MEMBER_APPROVAL);
        canDesignatedMemberApprovalUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_DESIGNATED_MEMBER_APPROVAL_UNAVAILABLE);
        canLiftHold = hasPermission(TaskName.IACUC_PROTOCOL_LIFT_HOLD);
        canLiftHoldUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_LIFT_HOLD_UNAVAILABLE);
        canRequestToLiftHold = hasPermission(TaskName.IACUC_PROTOCOL_REQUEST_LIFT_HOLD);
        canRequestToLiftHoldUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_REQUEST_LIFT_HOLD_UNAVAILABLE);
        canReturnToPI = hasPermission(TaskName.RETURN_TO_PI_PROTOCOL);
        canReturnToPIUnavailable = hasPermission(TaskName.RETURN_TO_PI_PROTOCOL_UNAVAILABLE);
        canReviewNotRequired = hasPermission(TaskName.REVIEW_NOT_REQUIRED_IACUC_PROTOCOL);
        canReviewNotRequiredUnavailable = hasPermission(TaskName.REVIEW_NOT_REQUIRED_IACUC_PROTOCOL_UNAVAILABLE);
        canTable = hasPermission(TaskName.IACUC_PROTOCOL_TABLE);
        canTableUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_TABLE_UNAVAILABLE);
        canAssignCmt = hasPermission(TaskName.IACUC_ASSIGN_TO_COMMITTEE);
        canAssignCmtUnavailable = hasPermission(TaskName.IACUC_ASSIGN_TO_COMMITTEE_UNAVAILABLE);
        canAddDeactivateReviewerComments = hasDeactivateRequestLastAction();
        
        canRemoveFromAgenda = hasPermission(TaskName.REMOVE_FROM_AGENDA);
        
        canCreateContinuation = hasCreateContinuationPermission();
        canCreateContinuationUnavailable = hasCreateContinuationUnavailablePermission();
        

        initSummaryDetails();

    }
 
    
    /**
     * Refreshes the comments for all the beans from the database.  Use sparingly since this will erase non-persisted comments.
     */
    public void prepareCommentsView() {
        super.prepareCommentsView();
        iacucProtocolDeactivateBean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
    }


    public static boolean hasAssignCmtSchedPermission(String userId, String protocolNumber) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("protocolNumber", protocolNumber);
        BusinessObjectService bos = KraServiceLocator.getService(BusinessObjectService.class);
        IacucProtocol protocol = ((List<IacucProtocol>) bos.findMatching(IacucProtocol.class, fieldValues)).get(0);
        IacucProtocolTask task = new IacucProtocolTask(TaskName.MODIFY_IACUC_PROTOCOL, protocol);
        TaskAuthorizationService tas = KraServiceLocator.getService(TaskAuthorizationService.class);        
        return tas.isAuthorized(userId, task);
    }
    
    protected boolean hasPermission(String taskName) {
        IacucProtocolTask task = new IacucProtocolTask(taskName, getIacucProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected boolean hasGenericPermission(String genericActionName) {
        IacucProtocolTask task = new IacucProtocolTask(TaskName.GENERIC_IACUC_PROTOCOL_ACTION, getIacucProtocol(), genericActionName);
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected boolean hasGenericUnavailablePermission(String genericActionName) {
        IacucProtocolTask task = new IacucProtocolTask(TaskName.GENERIC_IACUC_PROTOCOL_ACTION_UNAVAILABLE, getIacucProtocol(), genericActionName);
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    protected boolean hasFollowupAction(String actionCode) {
        return false;
    }
    
    public IacucProtocolSubmitAction getIacucProtocolSubmitAction() {
        return (IacucProtocolSubmitAction)protocolSubmitAction;
    }

    public ProtocolForm getProtocolForm() {
        return form;
    }
    
    public IacucProtocol getProtocol() {
        return (IacucProtocol) form.getProtocolDocument().getProtocol();
    }

    public IacucProtocolGenericActionBean getIacucProtocolDeactivateBean() {
        return iacucProtocolDeactivateBean;
    }

    public void setIacucProtocolDeactivateBean(IacucProtocolGenericActionBean iacucProtocolDeactivateBean) {
        this.iacucProtocolDeactivateBean = iacucProtocolDeactivateBean;
    }

    /*
     * This will check whether there is submission questionnaire.
     * When business rule is implemented, this will become more complicated because
     * each request action may have different set of questionnaire, so this has to be changed.
     */
    private boolean hasSubmissionQuestionnaire() {
        ModuleQuestionnaireBean moduleQuestionnaireBean = new ModuleQuestionnaireBean(CoeusModule.IACUC_PROTOCOL_MODULE_CODE, this.getProtocolForm().getProtocolDocument().getProtocol().getProtocolNumber() + "T", CoeusSubModule.PROTOCOL_SUBMISSION, "999", false);
        return CollectionUtils.isNotEmpty(getQuestionnaireAnswerService().getQuestionnaireAnswer(moduleQuestionnaireBean));
    }

    private QuestionnaireAnswerService getQuestionnaireAnswerService() {
        return KraServiceLocator.getService(QuestionnaireAnswerService.class);
    }


    public ProtocolActionBean getActionBean(String taskName) {
        return actionBeanTaskMap.get(taskName);
    }

    public boolean isCanAbandon() {
        return canAbandon;
    }

    public ProtocolCorrespondence getProtocolCorrespondence() {
        return protocolCorrespondence;
    }

    public void setProtocolCorrespondence(ProtocolCorrespondence protocolCorrespondence) {
        this.protocolCorrespondence = protocolCorrespondence;
    }

    public boolean getIsApproveOpenForFollowup() {
        return hasFollowupAction(IacucProtocolActionType.IACUC_APPROVED);
    }

    public boolean getIsDisapproveOpenForFollowup() {
        return hasFollowupAction(IacucProtocolActionType.IACUC_DISAPPROVED);
    }

    public boolean getIsReturnForSMROpenForFollowup() {
        return hasFollowupAction(IacucProtocolActionType.IACUC_MINOR_REVISIONS_REQUIRED);
    }

    public boolean getIsReturnForSRROpenForFollowup() {
        return hasFollowupAction(IacucProtocolActionType.IACUC_MAJOR_REVISIONS_REQUIRED);
    }

    public boolean isOpenForFollowup() {
        return getIsApproveOpenForFollowup() || getIsDisapproveOpenForFollowup() ||
               getIsReturnForSMROpenForFollowup() || getIsReturnForSRROpenForFollowup();
    }

    public boolean isCanDesignatedMemberApproval() {
        return canDesignatedMemberApproval;
    }

    public boolean isCanDesignatedMemberApprovalUnavailable() {
        return canDesignatedMemberApprovalUnavailable;
    }

    public boolean isCanHold() {
        return canHold;
    }

    public boolean isCanHoldUnavailable() {
        return canHoldUnavailable;
    }

    public boolean isCanLiftHold() {
        return canLiftHold;
    }

    public boolean isCanLiftHoldUnavailable() {
        return canLiftHoldUnavailable;
    }

    public boolean isCanRequestToLiftHold() {
        return canRequestToLiftHold;
    }

    public boolean isCanRequestToLiftHoldUnavailable() {
        return canRequestToLiftHoldUnavailable;
    }
    
    public boolean isCanRequestSuspend() {
        return canRequestSuspend;
    }

    public boolean isCanRequestSuspendUnavailable() {
        return canRequestSuspendUnavailable;
    }

    public boolean isCanTable() {
        return canTable;
    }

    public boolean isCanTableUnavailable() {
        return canTableUnavailable;
    }
    
    public boolean isCanNotifyIacuc() {
        return canNotifyIacuc;
    }
    
    public boolean isCanNotifyIacucUnavailable() {
        return canNotifyIacucUnavailable;
    }

    public boolean isCanDeleteIacucProtocol() {
        return canDeleteIacucProtocol;
    }
    
    public boolean isCanDeleteIacucProtocolUnavailable() {
        return canDeleteIacucProtocolUnavailable;
    }
    
    public boolean isCanReviewNotRequired() {
        return canReviewNotRequired;
    }
    
    public boolean isCanReviewNotRequiredUnavailable() {
        return canReviewNotRequiredUnavailable;
    }

    public boolean isCanIacucRequestDeactivate() {
        return canIacucRequestDeactivate;
    }
    
    public boolean isCanIacucRequestDeactivateUnavailable() {
        return canIacucRequestDeactivateUnavailable;
    }

    protected String getParameterValue(String parameterName) {
        String result = getParameterService().getParameterValueAsString(IacucProtocolDocument.class, parameterName);
        if (result == null) {
            result = super.getParameterValue(parameterName);
        }
        return result;
    }

    protected IacucProtocol getIacucProtocol() {
        return (IacucProtocol)getProtocol();
    }
    
    public boolean getCanAssignCmt() {
        return canAssignCmt;
    }

    public void setCanAssignCmt(boolean canAssignCmt) {
        this.canAssignCmt = canAssignCmt;
    }

    public boolean getCanAssignCmtUnavailable() {
        return canAssignCmtUnavailable;
    }

    public void setCanAssignCmtUnavailable(boolean canAssignCmtUnavailable) {
        this.canAssignCmtUnavailable = canAssignCmtUnavailable;
    }

    @Override
    protected IacucProtocolTask createNewAmendRenewDeleteTaskInstanceHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.PROTOCOL_AMEND_RENEW_DELETE, (IacucProtocol) protocol);
    }
    

    @Override
    protected IacucProtocolTask createNewAmendRenewDeleteUnavailableTaskInstanceHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.PROTOCOL_AMEND_RENEW_DELETE_UNAVAILABLE, (IacucProtocol) protocol);
    }

    @Override
    protected ProtocolDeleteBean getNewProtocolDeleteBeanInstanceHook(ActionHelper actionHelper) {
        return new IacucProtocolDeleteBean((IacucActionHelper)actionHelper);
    }

    
    protected Class<? extends ReviewCommentsService> getReviewCommentsServiceClassHook() {
        return IacucReviewCommentsService.class;
    }
    
    protected IacucProtocolGenericActionBean buildProtocolGenericActionBean(String actionTypeCode, String errorPropertyKey) {
        IacucProtocolGenericActionBean bean = new IacucProtocolGenericActionBean(this, errorPropertyKey);
        
        bean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        bean.getReviewCommentsBean().setHideReviewerName(getReviewCommentsService().setHideReviewerName(bean.getReviewCommentsBean().getReviewComments()));            
        IacucProtocolAction protocolAction = (IacucProtocolAction) findProtocolAction(actionTypeCode, getProtocol().getProtocolActions(), getProtocol().getProtocolSubmission());
        if (protocolAction != null) {
            bean.setComments(protocolAction.getComments());
            bean.setActionDate(new Date(protocolAction.getActionDate().getTime()));
        }
        
        return bean;
    }
    
    public ProtocolOnlineReviewService getOnlineReviewService() {
        return KraServiceLocator.getService(IacucProtocolOnlineReviewService.class);
    }
    
    public List<String> getReviewRecommendations() {
        List<String> recommendations = new ArrayList<String>();
        List<ProtocolOnlineReviewDocument> reviewDocs = getOnlineReviewService().getProtocolReviewDocumentsForCurrentSubmission(getProtocol());
        for (ProtocolOnlineReviewDocument doc : reviewDocs) {
            IacucProtocolOnlineReview review = (IacucProtocolOnlineReview) doc.getProtocolOnlineReview();
            if (ObjectUtils.isNotNull(review) && ObjectUtils.isNotNull(review.getDeterminationReviewTypeCode())) {
                recommendations.add(review.getProtocolReviewer().getFullName() + "--" + getReviewType(review.getDeterminationReviewTypeCode()));
            }
        }
        return recommendations;        
    }
    
    protected String getReviewType(String code) {
        Map<String, String> criteria = new HashMap<String, String>();
        criteria.put("reviewTypeCode", code);
        List<IacucProtocolReviewType> types = (List<IacucProtocolReviewType>) getBusinessObjectService().findMatching(IacucProtocolReviewType.class, criteria);
        return !types.isEmpty() ? types.get(0).getDescription() : "";        
    }
    
    @Override
    protected ProtocolTask createNewAbandonTaskInstanceHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.IACUC_ABANDON_PROTOCOL, (IacucProtocol) protocol);
    }

    @Override
    protected String getAbandonActionTypeHook() {    
        return IacucProtocolActionType.IACUC_ABANDON;
    }

    @Override
    protected String getAbandonPropertyKeyHook() {
        return Constants.PROTOCOL_ABANDON_ACTION_PROPERTY_KEY;
    }
    
    @Override
    protected String getExpireKeyHook() {
        return IacucProtocolActionType.EXPIRED;
    }
    
    @Override
    protected String getTerminateKeyHook() {
        return IacucProtocolActionType.TERMINATED;
    }
    
    @Override 
    protected String getSuspendKeyHook() {
        return IacucProtocolActionType.SUSPENDED;
    }

    @Override
    protected ProtocolSubmitAction getNewProtocolSubmitActionInstanceHook(ActionHelper actionHelper) {
       return new IacucProtocolSubmitAction((IacucActionHelper) actionHelper);
    }

    @Override
    protected ProtocolNotifyCommitteeBean getNewProtocolNotifyCommitteeBeanInstanceHook(ActionHelper actionHelper) {
        return new IacucProtocolNotifyCommitteeBean((IacucActionHelper) actionHelper);
    }


    @Override
    protected ProtocolModuleQuestionnaireBean getNewProtocolModuleQuestionnaireBeanInstanceHook(Protocol protocol) {
        return new IacucProtocolModuleQuestionnaireBean((IacucProtocol)protocol);
    }

    @Override
    protected ProtocolTask getModifySubmissionAvailableTaskHook() {
        return new IacucProtocolTask(TaskName.IACUC_MODIFY_PROTOCOL_SUBMISSION, (IacucProtocol) getProtocol());

    }

    @Override
    protected ProtocolTask getModifySubmissionUnavailableTaskHook() {
        return new IacucProtocolTask(TaskName.IACUC_MODIFY_PROTOCOL_SUBMISSION_UNAVAILABLE, (IacucProtocol) getProtocol());
    }
    
    @Override
    protected ProtocolTask getNewSubmitProtocolTaskInstanceHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.SUBMIT_IACUC_PROTOCOL, (IacucProtocol) protocol);
    }

    @Override
    protected ProtocolTask getNewSubmitProtocolUnavailableTaskInstanceHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.SUBMIT_IACUC_PROTOCOL_UNAVAILABLE, (IacucProtocol) protocol);
    }


    @Override
    protected ProtocolWithdrawBean getNewProtocolWithdrawBeanInstanceHook(ActionHelper actionHelper) {
        return new IacucProtocolWithdrawBean((IacucActionHelper) actionHelper);
    }
    
    @Override
    protected ProtocolAdministrativelyWithdrawBean getNewProtocolAdminWithdrawBeanInstanceHook(ActionHelper actionHelper) {
        return new IacucProtocolAdministrativelyWithdrawBean((IacucActionHelper) actionHelper);
    }
    
    @Override
    protected ProtocolAdministrativelyIncompleteBean getNewProtocolAdminIncompleteBeanInstanceHook(ActionHelper actionHelper) {
        return new IacucProtocolAdministrativelyIncompleteBean((IacucActionHelper) actionHelper);
    }

    @Override
    protected ProtocolAmendmentBean getNewProtocolAmendmentBeanInstanceHook(ActionHelper actionHelper) {
        return new IacucProtocolAmendmentBean((IacucActionHelper) actionHelper);
    }
    

    @Override
    protected ProtocolTask getNewAmendmentProtocolTaskInstanceHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.CREATE_IACUC_PROTOCOL_AMENDMENT, (IacucProtocol) protocol);
    }

    @Override
    protected ProtocolTask getNewAmendmentProtocolUnavailableTaskInstanceHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.CREATE_IACUC_PROTOCOL_AMENDMENT_UNAVAILABLE, (IacucProtocol) protocol);
    }
    
    @Override
    protected ProtocolTask getNewWithdrawProtocolTaskInstanceHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.PROTOCOL_WITHDRAW, (IacucProtocol) protocol);
    }

    @Override
    protected ProtocolTask getNewWithdrawProtocolUnavailableTaskInstanceHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.PROTOCOL_WITHDRAW_UNAVAILABLE, (IacucProtocol) protocol);
    }

    @Override
    protected ProtocolVersionService getProtocolVersionService() {
        if (this.protocolVersionService == null) {
            this.protocolVersionService = KraServiceLocator.getService(IacucProtocolVersionService.class);        
        }
        return this.protocolVersionService;
    }

    @Override
    protected String getCoeusModule() {
        return CoeusModule.IRB_MODULE_CODE;
    }

    /**
     * Sets up the summary details subpanel.
     * @throws Exception 
     */
    @Override
    public void initSummaryDetails() throws Exception {
        if (currentSequenceNumber == -1) {
            currentSequenceNumber = getProtocol().getSequenceNumber();
        } else if (currentSequenceNumber > getProtocol().getSequenceNumber()) {
            currentSequenceNumber = getProtocol().getSequenceNumber();
        }
        
        IacucProtocolSummary iacucProtocolSummary =  null;
        String protocolNumber = getProtocol().getProtocolNumber();
        IacucProtocol protocol = (IacucProtocol)getProtocolVersionService().getProtocolVersion(protocolNumber, currentSequenceNumber);
        if (protocol != null) {
            iacucProtocolSummary = (IacucProtocolSummary)protocol.getProtocolSummary();
        }
        
        IacucProtocolSummary iacucPrevProtocolSummary = null;
        if (currentSequenceNumber > 0) {
            protocol = (IacucProtocol) getProtocolVersionService().getProtocolVersion(protocolNumber, currentSequenceNumber - 1);
            if (protocol != null) {
                iacucPrevProtocolSummary = (IacucProtocolSummary)protocol.getProtocolSummary();
            }
        }
        
        if (iacucProtocolSummary != null && iacucPrevProtocolSummary != null) {
            iacucProtocolSummary.compare(iacucPrevProtocolSummary);
            iacucPrevProtocolSummary.compare(iacucProtocolSummary);
        }
        protocolSummary = iacucProtocolSummary;
        prevProtocolSummary = iacucPrevProtocolSummary;

        setSummaryQuestionnaireExist(hasAnsweredQuestionnaire((protocol.isAmendment() || protocol.isRenewal() || protocol.isContinuation()) ? CoeusSubModule.AMENDMENT_RENEWAL : CoeusSubModule.ZERO_SUBMODULE, protocol.getSequenceNumber().toString()));
    }

    public boolean isIacucAdmin() {
        return getKraAuthorizationService().hasRole(GlobalVariables.getUserSession().getPrincipalId(), NAMESPACE, RoleConstants.IACUC_ADMINISTRATOR);
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
        if (!currentTaskName.equalsIgnoreCase(TaskName.MODIFY_IACUC_PROTOCOL_AMENDMENT_SECTIONS)) {
            IacucProtocolAmendmentBean amendmentBean = (IacucProtocolAmendmentBean)getProtocolAmendmentBean();
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

            IacucProtocolAmendRenewal correctAmendment = getCorrectAmendment(protocols);
            if (ObjectUtils.isNotNull(correctAmendment)) {
                setSubmissionHasNoAmendmentDetails(false);
                amendmentBean.setSummary(correctAmendment.getSummary());
                amendmentBean.setGeneralInfo((correctAmendment.hasModule(IacucProtocolModule.GENERAL_INFO)) ? true : false);
                amendmentBean.setProtocolPersonnel((correctAmendment.hasModule(IacucProtocolModule.PROTOCOL_PERSONNEL)) ? true : false);
                amendmentBean.setAreasOfResearch((correctAmendment.hasModule(IacucProtocolModule.AREAS_OF_RESEARCH)) ? true : false);
                amendmentBean.setAddModifyAttachments((correctAmendment.hasModule(IacucProtocolModule.ADD_MODIFY_ATTACHMENTS)) ? true : false);
                amendmentBean.setFundingSource((correctAmendment.hasModule(IacucProtocolModule.FUNDING_SOURCE)) ? true : false);
                amendmentBean.setOthers((correctAmendment.hasModule(IacucProtocolModule.OTHERS)) ? true : false);
                amendmentBean.setProtocolOrganizations((correctAmendment.hasModule(IacucProtocolModule.PROTOCOL_ORGANIZATIONS)) ? true : false);
                amendmentBean.setProtocolPermissions((correctAmendment.hasModule(IacucProtocolModule.PROTOCOL_PERMISSIONS)) ? true : false);
                amendmentBean.setProtocolReferencesAndOtherIdentifiers((correctAmendment.hasModule(IacucProtocolModule.PROTOCOL_REFERENCES)) ? true : false);
                amendmentBean.setQuestionnaire((correctAmendment.hasModule(IacucProtocolModule.QUESTIONNAIRE)) ? true : false);
                amendmentBean.setSpecialReview((correctAmendment.hasModule(IacucProtocolModule.SPECIAL_REVIEW)) ? true : false);
                amendmentBean.setSubjects((correctAmendment.hasModule(IacucProtocolModule.SUBJECTS)) ? true : false);
                amendmentBean.setThreers((correctAmendment.hasModule(IacucProtocolModule.THREE_RS)) ? true : false);
                amendmentBean.setSpeciesAndGroups((correctAmendment.hasModule(IacucProtocolModule.SPECIES_GROUPS)) ? true : false);
                amendmentBean.setProcedures((correctAmendment.hasModule(IacucProtocolModule.PROCEDURES)) ? true : false);
                amendmentBean.setProtocolExceptions((correctAmendment.hasModule(IacucProtocolModule.EXCEPTIONS)) ? true : false);

            } else {
                setSubmissionHasNoAmendmentDetails(true);
            }
        }
    }

    /**
     * This method returns the amendRenewal bean with the current submission number. 
     * @param protocols
     * @return
     */
    protected IacucProtocolAmendRenewal getCorrectAmendment(List<Protocol> protocols) {
        for (Protocol protocol : protocols) {
            // There should always be an amendment with the current submission number.
            if (protocol.isAmendment() && ObjectUtils.isNotNull(protocol.getProtocolSubmission().getSubmissionNumber()) 
                && protocol.getProtocolSubmission().getSubmissionNumber() == currentSubmissionNumber) {
                IacucProtocol iacucProtocol = (IacucProtocol)protocol;
                return (IacucProtocolAmendRenewal) iacucProtocol.getProtocolAmendRenewal();
            }
        }
        return null;
    }
    


    @Override
    protected String getAdminApprovalProtocolActionTypeHook() {
        return IacucProtocolActionType.ADMINISTRATIVE_APPROVAL;
    }

    @Override
    protected ProtocolApproveBean getNewProtocolApproveBeanInstanceHook(ActionHelper actionHelper, String errorPropertyKey) {
        return new IacucProtocolApproveBean((IacucActionHelper) actionHelper, errorPropertyKey);
    }

    @Override
    protected ProtocolTask getNewAdminApproveProtocolTaskInstanceHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.ADMIN_APPROVE_PROTOCOL, (IacucProtocol) protocol);
    }

    @Override
    protected ProtocolTask getNewAdminApproveUnavailableProtocolTaskInstanceHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.ADMIN_APPROVE_PROTOCOL_UNAVAILABLE, (IacucProtocol) protocol);
    }
    
    @Override
    protected ProtocolTask getExpireTaskInstanceHook(Protocol protocol) {
        IacucProtocolTask task = new IacucProtocolTask(IacucGenericProtocolAuthorizer.EXPIRE_PROTOCOL, (IacucProtocol)protocol);
        return task;
    }
    
    @Override
    protected ProtocolTask getExpireUnavailableTaskInstanceHook(Protocol protocol) {
        IacucProtocolTask task = new IacucProtocolTask(IacucGenericProtocolAuthorizer.EXPIRE_UNAVAILABLE_PROTOCOL, (IacucProtocol)protocol);
        return task;
    }
    
    @Override
    protected ProtocolTask getTerminateTaskInstanceHook(Protocol protocol) {
        IacucProtocolTask task = new IacucProtocolTask(IacucGenericProtocolAuthorizer.TERMINATE_PROTOCOL, (IacucProtocol)protocol);
        return task;
    }
    
    @Override
    protected ProtocolTask getTerminateUnavailableTaskInstanceHook(Protocol protocol) {
        IacucProtocolTask task = new IacucProtocolTask(IacucGenericProtocolAuthorizer.TERMINATE_UNAVAILBLE_PROTOCOL, (IacucProtocol)protocol);
        return task;
    }
    
    @Override
    protected ProtocolTask getSuspendTaskInstanceHook(Protocol protocol) {
        IacucProtocolTask task = new IacucProtocolTask(IacucGenericProtocolAuthorizer.SUSPEND_PROTOCOL, (IacucProtocol)protocol);
        return task;
    }
    
    @Override
    protected ProtocolTask getSuspendUnavailableTaskInstanceHook(Protocol protocol) {
        IacucProtocolTask task = new IacucProtocolTask(IacucGenericProtocolAuthorizer.SUSPEND_UNAVAILABLE_PROTOCOL, (IacucProtocol)protocol);
        return task;
    }

    @Override
    protected ProtocolTask getNewAdminWithdrawProtocolTaskInstanceHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.ADMIN_WITHDRAW_PROTOCOL, (IacucProtocol) protocol);
    }

    @Override
    protected ProtocolTask getNewAdminWithdrawUnavailableProtocolTaskInstanceHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.ADMIN_WITHDRAW_PROTOCOL_UNAVAILABLE, (IacucProtocol) protocol);
    }

    @Override
    protected ProtocolTask getNewAdminMarkIncompleteProtocolTaskInstanceHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.ADMIN_INCOMPLETE_PROTOCOL, (IacucProtocol) protocol);
    }

    @Override
    protected ProtocolTask getNewAdminMarkIncompleteUnavailableProtocolTaskInstanceHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.ADMIN_INCOMPLETE_PROTOCOL_UNAVAILABLE, (IacucProtocol) protocol);
    }

    @Override
    protected ProtocolAssignToAgendaBean getNewProtocolAssignToAgendaBeanInstanceHook(ActionHelper actionHelper) {
        return new IacucProtocolAssignToAgendaBean((IacucActionHelper) actionHelper);
    }

    @Override
    protected ProtocolTask createNewAssignToAgendaTaskInstanceHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.ASSIGN_TO_AGENDA, (IacucProtocol) protocol);
    }

    @Override
    protected ProtocolTask createNewAssignToAgendaUnavailableTaskInstanceHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.ASSIGN_TO_AGENDA_UNAVAILABLE, (IacucProtocol) protocol);
    }
    
    public IacucProtocolNotifyIacucBean getIacucProtocolNotifyIacucBean() {
        return iacucProtocolNotifyIacucBean;
    }

    public void setIacucProtocolNotifyIacucBean(IacucProtocolNotifyIacucBean iacucProtocolNotifyIacucBean) {
        this.iacucProtocolNotifyIacucBean = iacucProtocolNotifyIacucBean;
    }

    public void addNotifyIacucAttachment() {
        getIacucProtocolNotifyIacucBean().getActionAttachments().add(
                getIacucProtocolNotifyIacucBean().getNewActionAttachment());
        getIacucProtocolNotifyIacucBean().setNewActionAttachment(new ProtocolActionAttachment());
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


    @Override
    protected CommitteeDecisionService<? extends CommitteeDecision<? extends CommitteePerson>> getCommitteeDecisionService() {
        return KraServiceLocator.getService(IacucCommitteeDecisionService.class);
    }

    @Override
    public int getTotalSubmissions() {
        return getProtocolSubmitActionService().getTotalSubmissions(getProtocol());
    }
    
    private IacucProtocolSubmitActionService getProtocolSubmitActionService() {
        return KraServiceLocator.getService(IacucProtocolSubmitActionService.class);
    }

    @Override
    protected IacucCommitteeDecision getNewCommitteeDecisionInstanceHook(ActionHelper actionHelper) {
        return new IacucCommitteeDecision((IacucActionHelper) actionHelper);
    }

    @Override
    protected Class<? extends ProtocolSubmissionDoc> getProtocolSubmissionDocClassHook() {
        return IacucProtocolSubmissionDoc.class;
    }

    @Override
    protected Class<? extends FollowupActionService<? extends ValidProtocolActionAction>> getFollowupActionServiceClassHook() {
        return IacucFollowupActionService.class;
    }

    @Override
    protected String getProtocolActionTypeCodeForManageReviewCommentsHook() {
        return IacucProtocolActionType.MANAGE_REVIEW_COMMENTS;
    }

    @Override
    protected String getFullApprovalProtocolActionTypeHook() {
        return IacucProtocolActionType.IACUC_APPROVED;
    }

    @Override
    protected String getSMRProtocolActionTypeHook() {
        return IacucProtocolActionType.IACUC_MINOR_REVISIONS_REQUIRED;
    }

    @Override
    protected String getSRRProtocolActionTypeHook() {
        return IacucProtocolActionType.IACUC_MAJOR_REVISIONS_REQUIRED;
    }
    
    @Override
    protected String getReturnToPIActionTypeHook() {
        return IacucProtocolActionType.RETURNED_TO_PI;
    }

    @Override
    protected String getDisapprovedProtocolActionTypeHook() {
        return IacucProtocolActionType.IACUC_DISAPPROVED;
    }

    public IacucProtocolGenericActionBean getIacucAcknowledgeBean() {
        return iacucAcknowledgeBean;
    }

    public void setIacucAcknowledgeBean(IacucProtocolGenericActionBean iacucAcknowledgeBean) {
        this.iacucAcknowledgeBean = iacucAcknowledgeBean;
    }

    public boolean isCanIacucAcknowledge() {
        return canIacucAcknowledge;
    }

    public boolean isCanIacucAcknowledgeUnavailable() {
        return canIacucAcknowledgeUnavailable;
    }
    
    @Override
    protected void populateExistingAmendmentBean(ProtocolAmendmentBean amendmentBean, List<String> moduleTypeCodes) {
        IacucProtocolAmendRenewal protocolAmendRenewal = (IacucProtocolAmendRenewal)getProtocol().getProtocolAmendRenewal();
        amendmentBean.setSummary(protocolAmendRenewal.getSummary());
        for (ProtocolAmendRenewModule module : protocolAmendRenewal.getModules()) {
            moduleTypeCodes.add(module.getProtocolModuleTypeCode());
            if (StringUtils.equals(IacucProtocolModule.GENERAL_INFO, module.getProtocolModuleTypeCode())) {
                amendmentBean.setGeneralInfo(true);
            } 
            else if (StringUtils.equals(IacucProtocolModule.ADD_MODIFY_ATTACHMENTS, module.getProtocolModuleTypeCode())) {
                amendmentBean.setAddModifyAttachments(true);
            }
            else if (StringUtils.equals(IacucProtocolModule.AREAS_OF_RESEARCH, module.getProtocolModuleTypeCode())) {
                amendmentBean.setAreasOfResearch(true);
            }
            else if (StringUtils.equals(IacucProtocolModule.FUNDING_SOURCE, module.getProtocolModuleTypeCode())) {
                amendmentBean.setFundingSource(true);
            }
            else if (StringUtils.equals(IacucProtocolModule.OTHERS, module.getProtocolModuleTypeCode())) {
                amendmentBean.setOthers(true);
            }
            else if (StringUtils.equals(IacucProtocolModule.PROTOCOL_ORGANIZATIONS, module.getProtocolModuleTypeCode())) {
                amendmentBean.setProtocolOrganizations(true);
            }
            else if (StringUtils.equals(IacucProtocolModule.PROTOCOL_PERSONNEL, module.getProtocolModuleTypeCode())) {
                amendmentBean.setProtocolPersonnel(true);
            }
            else if (StringUtils.equals(IacucProtocolModule.PROTOCOL_REFERENCES, module.getProtocolModuleTypeCode())) {
                amendmentBean.setProtocolReferencesAndOtherIdentifiers(true);
            }
            else if (StringUtils.equals(IacucProtocolModule.SPECIAL_REVIEW, module.getProtocolModuleTypeCode())) {
                amendmentBean.setSpecialReview(true);
            }
            else if (StringUtils.equals(IacucProtocolModule.SUBJECTS, module.getProtocolModuleTypeCode())) {
                amendmentBean.setSubjects(true);
            }
            else if (StringUtils.equals(IacucProtocolModule.PROTOCOL_PERMISSIONS, module.getProtocolModuleTypeCode())) {
                amendmentBean.setProtocolPermissions(true);
            }
            else if (StringUtils.equals(IacucProtocolModule.QUESTIONNAIRE, module.getProtocolModuleTypeCode())) {
                amendmentBean.setQuestionnaire(true);
            }
            else if (StringUtils.equals(IacucProtocolModule.THREE_RS, module.getProtocolModuleTypeCode())) {
                ((IacucProtocolAmendmentBean)amendmentBean).setThreers(true);
            }
            else if (StringUtils.equals(IacucProtocolModule.SPECIES_GROUPS, module.getProtocolModuleTypeCode())) {
                ((IacucProtocolAmendmentBean)amendmentBean).setSpeciesAndGroups(true);
            }
            else if (StringUtils.equals(IacucProtocolModule.PROCEDURES, module.getProtocolModuleTypeCode())) {
                ((IacucProtocolAmendmentBean)amendmentBean).setProcedures(true);
            }
            else if (StringUtils.equals(IacucProtocolModule.EXCEPTIONS, module.getProtocolModuleTypeCode())) {
                ((IacucProtocolAmendmentBean)amendmentBean).setProtocolExceptions(true);
            }
            
        }
    }

    @Override
    protected ProtocolAmendRenewService getProtocolAmendRenewServiceHook() {
        return KraServiceLocator.getService(IacucProtocolAmendRenewService.class);        
    }

    @Override
    protected void enableModuleOption(String moduleTypeCode, ProtocolEditableBean amendmentBean) {
        if (StringUtils.equals(IacucProtocolModule.GENERAL_INFO, moduleTypeCode)) {
            amendmentBean.setGeneralInfoEnabled(true);
        } 
        else if (StringUtils.equals(IacucProtocolModule.ADD_MODIFY_ATTACHMENTS, moduleTypeCode)) {
            amendmentBean.setAddModifyAttachmentsEnabled(true);
        }
        else if (StringUtils.equals(IacucProtocolModule.AREAS_OF_RESEARCH, moduleTypeCode)) {
            amendmentBean.setAreasOfResearchEnabled(true);
        }
        else if (StringUtils.equals(IacucProtocolModule.FUNDING_SOURCE, moduleTypeCode)) {
            amendmentBean.setFundingSourceEnabled(true);
        }
        else if (StringUtils.equals(IacucProtocolModule.OTHERS, moduleTypeCode)) {
            amendmentBean.setOthersEnabled(true);
        }
        else if (StringUtils.equals(IacucProtocolModule.PROTOCOL_ORGANIZATIONS, moduleTypeCode)) {
            amendmentBean.setProtocolOrganizationsEnabled(true);
        }
        else if (StringUtils.equals(IacucProtocolModule.PROTOCOL_PERSONNEL, moduleTypeCode)) {
            amendmentBean.setProtocolPersonnelEnabled(true);
        }
        else if (StringUtils.equals(IacucProtocolModule.PROTOCOL_REFERENCES, moduleTypeCode)) {
            amendmentBean.setProtocolReferencesEnabled(true);
        }
        else if (StringUtils.equals(IacucProtocolModule.SPECIAL_REVIEW, moduleTypeCode)) {
            amendmentBean.setSpecialReviewEnabled(true);
        }
        else if (StringUtils.equals(IacucProtocolModule.SUBJECTS,moduleTypeCode)) {
            amendmentBean.setSubjectsEnabled(true);
        }
        else if (StringUtils.equals(IacucProtocolModule.PROTOCOL_PERMISSIONS,moduleTypeCode)) {
            amendmentBean.setProtocolPermissionsEnabled(true);
        }
        else if (StringUtils.equals(IacucProtocolModule.QUESTIONNAIRE,moduleTypeCode)) {
            amendmentBean.setQuestionnaireEnabled(true);
        }
        else if (StringUtils.equals(IacucProtocolModule.THREE_RS,moduleTypeCode)) {
            ((IacucProtocolEditableBean)amendmentBean).setThreersEnabled(true);
        }
        else if (StringUtils.equals(IacucProtocolModule.SPECIES_GROUPS,moduleTypeCode)) {
            ((IacucProtocolEditableBean)amendmentBean).setSpeciesAndGroupsEnabled(true);
        }
        else if (StringUtils.equals(IacucProtocolModule.PROCEDURES,moduleTypeCode)) {
            ((IacucProtocolEditableBean)amendmentBean).setProceduresEnabled(true);
        }
        else if (StringUtils.equals(IacucProtocolModule.EXCEPTIONS,moduleTypeCode)) {
            ((IacucProtocolEditableBean)amendmentBean).setProtocolExceptionsEnabled(true);
        }
    
    }
    
    @Override
    protected void enableModuleOption(ProtocolAmendmentBean amendmentBean, ProtocolAmendRenewal correctAmendment) {
        amendmentBean.setGeneralInfo((correctAmendment.hasModule(IacucProtocolModule.GENERAL_INFO)) ? true : false);
        amendmentBean.setProtocolPersonnel((correctAmendment.hasModule(IacucProtocolModule.PROTOCOL_PERSONNEL)) ? true : false);
        amendmentBean.setAreasOfResearch((correctAmendment.hasModule(IacucProtocolModule.AREAS_OF_RESEARCH)) ? true : false);
        amendmentBean.setAddModifyAttachments((correctAmendment.hasModule(IacucProtocolModule.ADD_MODIFY_ATTACHMENTS)) ? true : false);
        amendmentBean.setFundingSource((correctAmendment.hasModule(IacucProtocolModule.FUNDING_SOURCE)) ? true : false);
        amendmentBean.setOthers((correctAmendment.hasModule(IacucProtocolModule.OTHERS)) ? true : false);
        amendmentBean.setProtocolOrganizations((correctAmendment.hasModule(IacucProtocolModule.PROTOCOL_ORGANIZATIONS)) ? true : false);
        amendmentBean.setProtocolPermissions((correctAmendment.hasModule(IacucProtocolModule.PROTOCOL_PERMISSIONS)) ? true : false);
        amendmentBean.setProtocolReferencesAndOtherIdentifiers((correctAmendment.hasModule(IacucProtocolModule.PROTOCOL_REFERENCES)) ? true : false);
        amendmentBean.setQuestionnaire((correctAmendment.hasModule(IacucProtocolModule.QUESTIONNAIRE)) ? true : false);
        amendmentBean.setSpecialReview((correctAmendment.hasModule(IacucProtocolModule.SPECIAL_REVIEW)) ? true : false);
        amendmentBean.setSubjects((correctAmendment.hasModule(IacucProtocolModule.SUBJECTS)) ? true : false);
        ((IacucProtocolAmendmentBean)amendmentBean).setThreers((correctAmendment.hasModule(IacucProtocolModule.THREE_RS)) ? true : false);
        ((IacucProtocolAmendmentBean)amendmentBean).setSpecialReview((correctAmendment.hasModule(IacucProtocolModule.SPECIES_GROUPS)) ? true : false);
        ((IacucProtocolAmendmentBean)amendmentBean).setProcedures((correctAmendment.hasModule(IacucProtocolModule.PROCEDURES)) ? true : false);
        ((IacucProtocolAmendmentBean)amendmentBean).setProtocolExceptions((correctAmendment.hasModule(IacucProtocolModule.EXCEPTIONS)) ? true : false);
    
    }
    
    @Override
    protected ProtocolTask getModifyAmendmentSectionsProtocolTaskInstanceHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.MODIFY_IACUC_PROTOCOL_AMENDMENT_SECTIONS, (IacucProtocol) protocol);
    }

    @Override
    protected ProtocolTask getModifyAmendmentSectionsUnavailableProtocolUnavailableTaskInstanceHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.MODIFY_IACUC_PROTOCOL_AMENDMENT_SECTIONS_UNAVAILABLE, (IacucProtocol) protocol);
    }

    private boolean hasDeactivateRequestLastAction() {
        return IacucProtocolActionType.REQUEST_DEACTIVATE.equals(getLastPerformedAction().getProtocolActionTypeCode());
    }


    public boolean isCanIacucDeactivate() {
        return canIacucDeactivate;
    }


    public void setCanIacucDeactivate(boolean canIacucDeactivate) {
        this.canIacucDeactivate = canIacucDeactivate;
    }


    public boolean isCanIacucDeactivateUnavailable() {
        return canIacucDeactivateUnavailable;
    }


    public void setCanIacucDeactivateUnavailable(boolean canIacucDeactivateUnavailable) {
        this.canIacucDeactivateUnavailable = canIacucDeactivateUnavailable;
    }


    public boolean isCanAddDeactivateReviewerComments() {
        return canAddDeactivateReviewerComments;
    }


    public void setCanAddDeactivateReviewerComments(boolean canAddDeactivateReviewerComments) {
        this.canAddDeactivateReviewerComments = canAddDeactivateReviewerComments;
    }

    public IacucProtocolRequestBean getIacucProtocolDeactivateRequestBean() {
        return iacucProtocolDeactivateRequestBean;
    }


    public void setIacucProtocolDeactivateRequestBean(IacucProtocolRequestBean iacucProtocolDeactivateRequestBean) {
        this.iacucProtocolDeactivateRequestBean = iacucProtocolDeactivateRequestBean;
    }

    public IacucProtocolRequestBean getIacucProtocolLiftHoldRequestBean() {
        return iacucProtocolLiftHoldRequestBean;
    }

    public void setIacucProtocolLiftHoldRequestBean(IacucProtocolRequestBean iacucProtocolLiftHoldRequestBean) {
        this.iacucProtocolLiftHoldRequestBean = iacucProtocolLiftHoldRequestBean;
    }

    public void setCanIacucRequestDeactivate(boolean canIacucRequestDeactivate) {
        this.canIacucRequestDeactivate = canIacucRequestDeactivate;
    }

    public void setCanIacucRequestDeactivateUnavailable(boolean canIacucRequestDeactivateUnavailable) {
        this.canIacucRequestDeactivateUnavailable = canIacucRequestDeactivateUnavailable;
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


    public IacucProtocolGenericActionBean getIacucProtocolHoldBean() {
        return iacucProtocolHoldBean;
    }


    public void setIacucProtocolHoldBean(IacucProtocolGenericActionBean iacucProtocolHoldBean) {
        this.iacucProtocolHoldBean = iacucProtocolHoldBean;
    }


    public IacucProtocolGenericActionBean getIacucProtocolLiftHoldBean() {
        return iacucProtocolLiftHoldBean;
    }


    @Override
    protected ProtocolTask getAdminCorrectionProtocolTaskInstanceHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.IACUC_PROTOCOL_ADMIN_CORRECTION, (IacucProtocol) protocol);
    }


    @Override
    protected ProtocolTask getAdminCorrectionUnavailableProtocolTaskInstanceHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.IACUC_PROTOCOL_ADMIN_CORRECTION_UNAVAILABLE, (IacucProtocol) protocol);
    }


    @Override
    protected AdminCorrectionBean getNewAdminCorrectionBeanInstanceHook(ActionHelper actionHelper) {
        return new IacucAdminCorrectionBean((IacucActionHelper) actionHelper);
    }


    public IacucProtocolRequestBean getIacucProtocolSuspendRequestBean() {
        return iacucProtocolSuspendRequestBean;
    }


    public void setIacucProtocolSuspendRequestBean(IacucProtocolRequestBean iacucProtocolSuspendRequestBean) {
        this.iacucProtocolSuspendRequestBean = iacucProtocolSuspendRequestBean;
    }


    @Override
    protected UndoLastActionBean getNewUndoLastActionBeanInstanceHook() {
        return new IacucProtocolUndoLastActionBean(this, "actionHelper.undoLastActionBean");
    }
    

    @Override
    protected ProtocolQuestionnairePrintingService getProtocolQuestionnairePrintingServiceHook() {
        return KraServiceLocator.getService(IacucProtocolQuestionnairePrintingService.class);
    }


    public boolean getCanRemoveFromAgenda() {
        return canRemoveFromAgenda;
    }


    public void setCanRemoveFromAgenda(boolean canRemoveFromAgenda) {
        this.canRemoveFromAgenda = canRemoveFromAgenda;
    }


    public IacucProtocolGenericActionBean getIacucProtocolRemoveFromAgendaBean() {
        return iacucProtocolRemoveFromAgendaBean;
    }


    public void setIacucProtocolRemoveFromAgendaBean(IacucProtocolGenericActionBean iacucProtocolRemoveFromAgendaBean) {
        this.iacucProtocolRemoveFromAgendaBean = iacucProtocolRemoveFromAgendaBean;
    }

    public ProtocolReviewNotRequiredBean getProtocolReviewNotRequiredBean() {
        return iacucProtocolReviewNotRequiredBean;
    }


    public void setProtocolReviewNotRequiredBean(ProtocolReviewNotRequiredBean iacucProtocolReviewNotRequiredBean) {
        this.iacucProtocolReviewNotRequiredBean = iacucProtocolReviewNotRequiredBean;
    }


    @Override
    protected ProtocolTask getNewRenewalProtocolTaskInstanceHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.CREATE_IACUC_PROTOCOL_RENEWAL, (IacucProtocol) protocol);
    }


    @Override
    protected ProtocolTask getNewRenewalProtocolUnavailableTaskInstanceHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.CREATE_IACUC_PROTOCOL_RENEWAL_UNAVAILABLE, (IacucProtocol) protocol);
    }

    @Override
    public void initAmendmentBeans(boolean forceReset) throws Exception {
        super.initAmendmentBeans(forceReset);
        if (protocolContinuationAmendmentBean == null || forceReset) {
            protocolContinuationAmendmentBean = createAmendmentBean();
        }
    }
    
    @Override
    protected ProtocolAmendmentBean createAmendmentBean() throws Exception {
        protocolAmendmentBean = super.createAmendmentBean();
        protocolContinuationAmendmentBean = getNewProtocolAmendmentBeanInstanceHook(this);
        configureAmendmentBean(protocolContinuationAmendmentBean);
        return protocolAmendmentBean;
    }

    @Override
    protected ProtocolAmendmentBean configureAmendmentBean(ProtocolAmendmentBean amendmentBean) throws Exception {
        List<String> moduleTypeCodes;

        if (StringUtils.isNotEmpty(getProtocol().getProtocolNumber()) && (getProtocol().isAmendment() || getProtocol().isRenewal() || 
                getProtocol().isContinuation())) {
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

    @Override
    protected boolean hasModifyAmendmentSectionsPermission() {
        ProtocolTask task = getModifyAmendmentSectionsProtocolTaskInstanceHook(getProtocol());
        return ((!getProtocol().isRenewalWithoutAmendment() && !getProtocol().isContinuationWithoutAmendment())&&(getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task)));
    }

    protected boolean hasCreateContinuationPermission() {
        ProtocolTask task = getNewContinuationProtocolTaskInstance(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected boolean hasCreateContinuationUnavailablePermission() {
        ProtocolTask task = getNewContinuationProtocolUnavailableTaskInstance(getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    protected ProtocolTask getNewContinuationProtocolTaskInstance(Protocol protocol) {
        return new IacucProtocolTask(TaskName.CREATE_IACUC_PROTOCOL_CONTINUATION, (IacucProtocol) protocol);
    }

    protected ProtocolTask getNewContinuationProtocolUnavailableTaskInstance(Protocol protocol) {
        return new IacucProtocolTask(TaskName.CREATE_IACUC_PROTOCOL_CONTINUATION_UNAVAILABLE, (IacucProtocol) protocol);
    }

    public boolean getCanCreateContinuation() {
        return canCreateContinuation;
    }

    public boolean getCanCreateContinuationUnavailable() {
        return canCreateContinuationUnavailable;
    }

    public boolean getHasContinuations() throws Exception {
        if (getProtocol().isContinuation()) {
            hasContinuations = true;
        } else {
            List<IacucProtocol> protocols = (List<IacucProtocol>) ((IacucProtocolAmendRenewService)getProtocolAmendRenewServiceHook()).getContinuations(getProtocol().getProtocolNumber());
            hasContinuations = protocols.isEmpty() ? false : true;
        }
        return hasContinuations;
    }

    public String getContinuationSummary() {
        return continuationSummary;
    }


    public void setContinuationSummary(String continuationSummary) {
        this.continuationSummary = continuationSummary;
    }


    public ProtocolAmendmentBean getProtocolContinuationAmendmentBean() {
        return protocolContinuationAmendmentBean;
    }


    public void setProtocolContinuationAmendmentBean(ProtocolAmendmentBean protocolContinuationAmendmentBean) {
        this.protocolContinuationAmendmentBean = protocolContinuationAmendmentBean;
    }

}

