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
package org.kuali.kra.irb.actions;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.notification.impl.bo.NotificationTypeRecipient;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.actions.abandon.ProtocolAbandonService;
import org.kuali.kra.irb.actions.amendrenew.CreateAmendmentEvent;
import org.kuali.kra.irb.actions.amendrenew.CreateRenewalEvent;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewService;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendmentBean;
import org.kuali.kra.irb.actions.approve.ProtocolApproveBean;
import org.kuali.kra.irb.actions.approve.ProtocolApproveEvent;
import org.kuali.kra.irb.actions.approve.ProtocolApproveService;
import org.kuali.kra.irb.actions.assignagenda.ProtocolAssignToAgendaBean;
import org.kuali.kra.irb.actions.assignagenda.ProtocolAssignToAgendaEvent;
import org.kuali.kra.irb.actions.assignagenda.ProtocolAssignToAgendaService;
import org.kuali.kra.irb.actions.assigncmtsched.ProtocolAssignCmtSchedBean;
import org.kuali.kra.irb.actions.assigncmtsched.ProtocolAssignCmtSchedService;
import org.kuali.kra.irb.actions.assignreviewers.ProtocolAssignReviewersBean;
import org.kuali.kra.irb.actions.assignreviewers.ProtocolAssignReviewersEvent;
import org.kuali.kra.irb.actions.assignreviewers.ProtocolAssignReviewersService;
import org.kuali.kra.irb.actions.correction.AdminCorrectionBean;
import org.kuali.kra.irb.actions.correction.ProtocolAdminCorrectionEvent;
import org.kuali.kra.irb.actions.correspondence.ProtocolActionsCorrespondence;
import org.kuali.kra.irb.actions.decision.CommitteeDecision;
import org.kuali.kra.irb.actions.decision.CommitteeDecisionEvent;
import org.kuali.kra.irb.actions.decision.CommitteeDecisionService;
import org.kuali.kra.irb.actions.expeditedapprove.ProtocolExpeditedApproveBean;
import org.kuali.kra.irb.actions.expeditedapprove.ProtocolExpeditedApproveEvent;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericActionBean;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericActionEvent;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericActionService;
import org.kuali.kra.irb.actions.grantexemption.ProtocolGrantExemptionBean;
import org.kuali.kra.irb.actions.grantexemption.ProtocolGrantExemptionEvent;
import org.kuali.kra.irb.actions.grantexemption.ProtocolGrantExemptionService;
import org.kuali.kra.irb.actions.noreview.ProtocolReviewNotRequiredBean;
import org.kuali.kra.irb.actions.noreview.ProtocolReviewNotRequiredEvent;
import org.kuali.kra.irb.actions.noreview.ProtocolReviewNotRequiredService;
import org.kuali.kra.irb.actions.notification.*;
import org.kuali.kra.irb.actions.notifycommittee.ProtocolNotifyCommitteeBean;
import org.kuali.kra.irb.actions.notifycommittee.ProtocolNotifyCommitteeService;
import org.kuali.kra.irb.actions.notifyirb.ProtocolNotifyIrbService;
import org.kuali.kra.irb.actions.request.ProtocolRequestBean;
import org.kuali.kra.irb.actions.request.ProtocolRequestEvent;
import org.kuali.kra.irb.actions.request.ProtocolRequestRule;
import org.kuali.kra.irb.actions.request.ProtocolRequestService;
import org.kuali.kra.irb.actions.submit.*;
import org.kuali.kra.irb.actions.withdraw.ProtocolWithdrawService;
import org.kuali.kra.irb.auth.GenericProtocolAuthorizer;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondence;
import org.kuali.kra.irb.infrastructure.IrbConstants;
import org.kuali.kra.irb.notification.IRBNotificationContext;
import org.kuali.kra.irb.notification.IRBNotificationRenderer;
import org.kuali.kra.irb.notification.IRBProtocolNotification;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.irb.questionnaire.ProtocolQuestionnaireAuditRule;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolFormBase;
import org.kuali.kra.protocol.actions.ProtocolActionBean;
import org.kuali.kra.protocol.actions.ProtocolActionRequestServiceImpl;
import org.kuali.kra.protocol.actions.ProtocolActionTypeBase;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionsCorrespondenceBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;
import org.kuali.kra.protocol.notification.ProtocolNotification;
import org.kuali.kra.protocol.notification.ProtocolNotificationContextBase;
import org.kuali.kra.protocol.notification.ProtocolNotificationRequestBeanBase;
import org.kuali.kra.protocol.questionnaire.ProtocolQuestionnaireAuditRuleBase;
import org.kuali.rice.kns.document.authorization.DocumentAuthorizerBase;
import org.kuali.rice.kns.util.WebUtils;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.util.*;

public class IrbProtocolActionRequestServiceImpl extends ProtocolActionRequestServiceImpl implements IrbProtocolActionRequestService {
    private static final Log LOG = LogFactory.getLog(IrbProtocolActionRequestServiceImpl.class);

    private ProtocolAssignToAgendaService protocolAssignToAgendaService;
    private ProtocolAssignCmtSchedService protocolAssignCmtSchedService;
    private ProtocolApproveService protocolApproveService;
    private ProtocolSubmitActionService protocolSubmitActionService;
    private ProtocolAmendRenewService protocolAmendRenewService;
    private ProtocolWithdrawService protocolWithdrawService;
    private ProtocolRequestService protocolRequestService;
    private ProtocolGenericActionService protocolGenericActionService;
    private ProtocolGrantExemptionService protocolGrantExemptionService;
    private CommitteeDecisionService committeeDecisionService;
    private ProtocolAbandonService protocolAbandonService;
    private ProtocolNotifyIrbService protocolNotifyIrbService;
    private ProtocolNotifyCommitteeService protocolNotifyCommitteeService;
    private CommitteeService committeeService;
    private ProtocolReviewNotRequiredService protocolReviewNotRequiredService;
    private ProtocolAssignReviewersService protocolAssignReviewersService;
    private ProtocolActionService protocolActionService;
    
    private static final String ACTION_NAME_RESPONSE_APPROVAL = "Response Approval";
    private static final String ACTION_NAME_CLOSE_ENROLLMENT = "Close Enrollment";
    private static final String ACTION_NAME_DEFER = "Defer";
    private static final String ACTION_NAME_GRANT_EXEMPTION = "Grant Exemption";
    private static final String ACTION_NAME_CLOSE = "Close";
    private static final String ACTION_NAME_IRB_ACKNOWLEDGEMENT = "IRB Acknowledgement";
    private static final String ACTION_NAME_DATA_ANALYSIS_ONLY = "Permit Data Analysis Only";
    private static final String ACTION_NAME_REOPEN_ENROLLMENT = "Re-open Enrollment";
    private static final String ACTION_NAME_SUSPEND_BY_DSMB = "Suspend by DSMB";
    private static final String ACTION_NAME_MANAGE_REVIEW_COMMENTS = "Manage Review Comments";
    private static final String ACTION_NAME_NOTIFY_IRB = "Notify IRB";
    private static final String ACTION_NAME_NOTIFY_COMMITTEE = "Notify Committee";
    
    private static final String FORWARD_TO_HOLDING_PAGE = "holdingPage";
    
    // map to decide the followup action page to open.  "value" part is the action tab "title"
    private static Map<String, String> motionTypeMap = new HashMap<String, String>() {
        {
            put("1", "Approve Action");
            put("2", "Disapprove");
            put("3", "Return for Specific Minor Revisions");
            put("4", "Return for Substantive Revisions Required");
        }
    };

    protected static List <String> requestSubmissionTypes = Arrays.asList(new String[] {ProtocolSubmissionType.REQUEST_FOR_SUSPENSION,
                                                                                        ProtocolSubmissionType.REQUEST_FOR_TERMINATION,
                                                                                        ProtocolSubmissionType.REQUEST_TO_CLOSE,
                                                                                        ProtocolSubmissionType.REQUEST_TO_CLOSE_ENROLLMENT,
                                                                                        ProtocolSubmissionType.REQUEST_TO_REOPEN_ENROLLMENT,
                                                                                        ProtocolSubmissionType.REQUEST_FOR_DATA_ANALYSIS_ONLY});

    @Override
    public boolean isExpeditedApprovalAuthorized(ProtocolForm protocolForm) {
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        ProtocolExpeditedApproveBean expeditedActionBean = (ProtocolExpeditedApproveBean) ((ActionHelper) protocolForm.getActionHelper()).getProtocolExpeditedApprovalBean();
        boolean requestAuthorized = false;
        if (hasPermission(TaskName.EXPEDITE_APPROVAL, (Protocol) document.getProtocol())) {
            requestAuthorized = applyRules(new ProtocolExpeditedApproveEvent(document, expeditedActionBean));
        }
        return requestAuthorized;
    }

    @Override
    public boolean isFullApprovalAuthorized(ProtocolForm protocolForm) {
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        ProtocolApproveBean protocolApproveBean = (ProtocolApproveBean) protocolForm.getActionHelper().getProtocolFullApprovalBean();
        boolean requestAuthorized = false;
        if (hasPermission(TaskName.APPROVE_PROTOCOL, (Protocol) document.getProtocol())) {
            requestAuthorized = applyRules(new ProtocolApproveEvent(document, protocolApproveBean));
        }
        return requestAuthorized;
    }
    
    @Override
    public boolean isCreateRenewalAuthorized(ProtocolForm protocolForm) {
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        boolean requestAuthorized = false;
        String renewalSummary = protocolForm.getActionHelper().getRenewalSummary();
        if (hasPermission(TaskName.CREATE_PROTOCOL_RENEWAL, (Protocol) document.getProtocol())) {
            requestAuthorized = applyRules(new CreateRenewalEvent(document, Constants.PROTOCOL_CREATE_RENEWAL_SUMMARY_KEY, renewalSummary));
        }
        return requestAuthorized;
    }

    @Override
    public boolean isAssignToAgendaAuthorized(ProtocolForm protocolForm) {
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        boolean requestAuthorized = false;
        ProtocolAssignToAgendaBean actionBean = (ProtocolAssignToAgendaBean) protocolForm.getActionHelper().getAssignToAgendaBean();
        if (!hasDocumentStateChanged(protocolForm)) {
            if (hasPermission(TaskName.ASSIGN_TO_AGENDA, (Protocol) document.getProtocol())) {
                requestAuthorized = applyRules(new ProtocolAssignToAgendaEvent(document, actionBean));
            }
        } else {
            updateDocumentStatusChangedMessage();
        }
        return requestAuthorized;
    }
    
    @Override
    public boolean isCreateAmendmentAuthorized(ProtocolForm protocolForm) {
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        ProtocolAmendmentBean protocolAmendmentBean = (ProtocolAmendmentBean) protocolForm.getActionHelper().getProtocolAmendmentBean();
        boolean requestAuthorized = false;
        if (hasPermission(TaskName.CREATE_PROTOCOL_AMMENDMENT, (Protocol) document.getProtocol())) {
            requestAuthorized = applyRules(new CreateAmendmentEvent(document, Constants.PROTOCOL_CREATE_AMENDMENT_KEY, protocolAmendmentBean));
        }
        return requestAuthorized;
    }

    @Override
    public boolean isCreateRenewalWithAmendmentAuthorized(ProtocolForm protocolForm) {
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        ProtocolAmendmentBean protocolAmendmentBean = (ProtocolAmendmentBean) protocolForm.getActionHelper().getProtocolRenewAmendmentBean();
        boolean requestAuthorized = false;
        if (hasPermission(TaskName.CREATE_PROTOCOL_RENEWAL, (Protocol) document.getProtocol())) {
            requestAuthorized = applyRules(new CreateAmendmentEvent(document, Constants.PROTOCOL_CREATE_RENEWAL_WITH_AMENDMENT_KEY, protocolAmendmentBean));
        }
        return requestAuthorized;
    }
    
    @Override
    public boolean isWithdrawProtocolAuthorized(ProtocolForm protocolForm) {
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        boolean requestAuthorized = false;
        if (!hasDocumentStateChanged(protocolForm)) {
            requestAuthorized = hasPermission(TaskName.PROTOCOL_WITHDRAW, (Protocol) document.getProtocol());
        } else {
            updateDocumentStatusChangedMessage();
        }
        return requestAuthorized;
    }
    
    @Override
    public boolean isResponseApprovalAuthorized(ProtocolForm protocolForm) {
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        ProtocolApproveBean actionBean = ((ActionHelper) protocolForm.getActionHelper()).getProtocolResponseApprovalBean();
        boolean requestAuthorized = false;
        if (hasPermission(TaskName.RESPONSE_APPROVAL, (Protocol) document.getProtocol())) {
            requestAuthorized = applyRules(new ProtocolApproveEvent(document, actionBean));
        }
        return requestAuthorized;
    }
    /**

     * @see org.kuali.kra.irb.actions.IrbProtocolActionRequestService#isRequestActionAuthorized(org.kuali.kra.irb.ProtocolForm, java.lang.String)
     */
    public boolean isRequestActionAuthorized(ProtocolForm protocolForm, String taskName) {
        boolean requestAuthorized = false;
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        if (StringUtils.isNotBlank(taskName)) {
            requestAuthorized = hasPermission(taskName, (Protocol) document.getProtocol());
        }
        return requestAuthorized;
    }
    
    @Override
    public boolean isCloseProtocolAuthorized(ProtocolForm protocolForm) {
        ProtocolGenericActionBean actionBean = (ProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolCloseBean();
        return isGenericProtocolActionAuthorized(GenericProtocolAuthorizer.CLOSE_PROTOCOL, protocolForm, actionBean);
    }
    
    @Override
    public boolean isCloseEnrollmentAuthorized(ProtocolForm protocolForm) {
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolCloseEnrollmentBean();
        return isGenericProtocolActionAuthorized(GenericProtocolAuthorizer.CLOSE_ENROLLMENT_PROTOCOL, protocolForm, actionBean);
    }
    
    private boolean isGenericProtocolActionAuthorized(String genericActionName, ProtocolForm protocolForm, ProtocolGenericActionBean actionBean) {
        boolean requestAuthorized = false;
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        if (hasGenericPermission(genericActionName, (Protocol) document.getProtocol())) {
            requestAuthorized = applyRules(new ProtocolGenericActionEvent(document, actionBean));
        }
        return requestAuthorized;
    }

    @Override
    public boolean isDeferProtocolAuthorized(ProtocolForm protocolForm) {
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolDeferBean();
        boolean requestAuthorized = false;
        if (!hasDocumentStateChanged(protocolForm)) {
            if(hasPermission(TaskName.DEFER_PROTOCOL, (Protocol) document.getProtocol())) {
                requestAuthorized = applyRules(new ProtocolGenericActionEvent(document, actionBean));
            }
        } else {
            updateDocumentStatusChangedMessage();
        }
        return requestAuthorized;
    }
    
    @Override
    public boolean isDisapproveProtocolAuthorized(ProtocolForm protocolForm) {
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        ProtocolGenericActionBean actionBean = (ProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolDisapproveBean();
        boolean requestAuthorized = false;
        if (hasPermission(TaskName.DISAPPROVE_PROTOCOL, (Protocol) document.getProtocol())) {
            requestAuthorized = applyRules(new ProtocolGenericActionEvent(document, actionBean));
        }
        return requestAuthorized;
    }
    
    @Override
    public boolean isExpireProtocolAuthorized(ProtocolForm protocolForm) {
        ProtocolGenericActionBean actionBean = (ProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolExpireBean();
        return isGenericProtocolActionAuthorized(GenericProtocolAuthorizer.EXPIRE_PROTOCOL, protocolForm, actionBean);
    }
    
    @Override
    public boolean isGrantExemptionAuthorized(ProtocolForm protocolForm) {
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        ProtocolGrantExemptionBean actionBean = ((ActionHelper) protocolForm.getActionHelper()).getProtocolGrantExemptionBean();
        boolean requestAuthorized = false;
        if (hasPermission(TaskName.GRANT_EXEMPTION, (Protocol) document.getProtocol())) {
            requestAuthorized = applyRules(new ProtocolGrantExemptionEvent(document, actionBean));
        }
        return requestAuthorized;
    }
    
    @Override
    public boolean isIrbAcknowledgementAuthorized(ProtocolForm protocolForm) {
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolIrbAcknowledgementBean();
        boolean requestAuthorized = false;
        if (hasPermission(TaskName.IRB_ACKNOWLEDGEMENT, (Protocol) document.getProtocol())) {
            requestAuthorized = applyRules(new ProtocolGenericActionEvent(document, actionBean));
        }
        return requestAuthorized;
    }
    
    @Override
    public boolean isPermitDataAnalysisAuthorized(ProtocolForm protocolForm) {
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolPermitDataAnalysisBean();
        return isGenericProtocolActionAuthorized(GenericProtocolAuthorizer.PERMIT_DATA_ANALYSIS, protocolForm, actionBean);
    }
    
    @Override
    public boolean isReopenEnrollmentAuthorized(ProtocolForm protocolForm) {
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolReopenEnrollmentBean();
        return isGenericProtocolActionAuthorized(GenericProtocolAuthorizer.REOPEN_PROTOCOL, protocolForm, actionBean);
    }
    
    @Override
    public boolean isReturnForSMRAuthorized(ProtocolForm protocolForm) {
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        ProtocolGenericActionBean actionBean = (ProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolSMRBean();
        boolean requestAuthorized = false;
        if (hasPermission(TaskName.RETURN_FOR_SMR, (Protocol) document.getProtocol())) {
            requestAuthorized = applyRules(new ProtocolGenericActionEvent(document, actionBean));
        }
        return requestAuthorized;
    }
    
    @Override
    public boolean isReturnForSRRAuthorized(ProtocolForm protocolForm) {
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        ProtocolGenericActionBean actionBean = (ProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolSRRBean();
        boolean requestAuthorized = false;
        if (hasPermission(TaskName.RETURN_FOR_SRR, (Protocol) document.getProtocol())) {
            requestAuthorized = applyRules(new ProtocolGenericActionEvent(document, actionBean));
        }
        return requestAuthorized;
    }

    @Override
    public boolean isReturnToPIAuthorized(ProtocolForm protocolForm) {
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        ProtocolGenericActionBean actionBean = (ProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolReturnToPIBean();
        boolean requestAuthorized = false;
        if (hasPermission(TaskName.RETURN_TO_PI_PROTOCOL, (Protocol) document.getProtocol())) {
            requestAuthorized = applyRules(new ProtocolGenericActionEvent(document, actionBean));
        }
        return requestAuthorized;
    }
    
    @Override
    public boolean isSuspendAuthorized(ProtocolForm protocolForm) {
        ProtocolGenericActionBean actionBean = (ProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolSuspendBean();
        return isGenericProtocolActionAuthorized(GenericProtocolAuthorizer.SUSPEND_PROTOCOL, protocolForm, actionBean);
    }

    @Override
    public boolean isSuspendByDsmbAuthorized(ProtocolForm protocolForm) {
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolSuspendByDsmbBean();
        return isGenericProtocolActionAuthorized(GenericProtocolAuthorizer.SUSPEND_PROTOCOL_BY_DSMB, protocolForm, actionBean);
    }
    
    @Override
    public boolean isTerminateAuthorized(ProtocolForm protocolForm) {
        ProtocolGenericActionBean actionBean = (ProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolTerminateBean();
        return isGenericProtocolActionAuthorized(GenericProtocolAuthorizer.TERMINATE_PROTOCOL, protocolForm, actionBean);
    }
    
    @Override
    public boolean isManageCommentsAuthorized(ProtocolForm protocolForm) {
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        boolean requestAuthorized = false;
        if (!hasDocumentStateChanged(protocolForm)) {
            requestAuthorized = hasPermission(TaskName.PROTOCOL_MANAGE_REVIEW_COMMENTS, (Protocol) document.getProtocol());
        } else {
            updateDocumentStatusChangedMessage();
        }
        return requestAuthorized;
    }
    
    @Override
    public boolean isOpenProtocolForAdminCorrectionAuthorized(ProtocolForm protocolForm) {
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        boolean requestAuthorized = false;
        AdminCorrectionBean actionBean = (AdminCorrectionBean) protocolForm.getActionHelper().getProtocolAdminCorrectionBean();
        if (!hasDocumentStateChanged(protocolForm)) {
            if (hasPermission(TaskName.PROTOCOL_ADMIN_CORRECTION, (Protocol) document.getProtocol())) {
                requestAuthorized = applyRules(new ProtocolAdminCorrectionEvent(document, actionBean));
            }
        } else {
            updateDocumentStatusChangedMessage();
        }
        return requestAuthorized;
    }
    
    @Override
    public boolean isSubmitCommitteeDecisionAuthorized(ProtocolForm protocolForm) {
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        boolean requestAuthorized = false;
        CommitteeDecision actionBean = (CommitteeDecision) protocolForm.getActionHelper().getCommitteeDecision();
        if (!hasDocumentStateChanged(protocolForm)) {
            requestAuthorized = applyRules(new CommitteeDecisionEvent(document, actionBean));
        } else {
            updateDocumentStatusChangedMessage();
        }
        return requestAuthorized;
    }
    
    @Override
    public boolean isAbandonAuthorized(ProtocolForm protocolForm) {
        boolean requestAuthorized = false;
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        requestAuthorized = hasPermission(TaskName.ABANDON_PROTOCOL, (Protocol) document.getProtocol());
        return requestAuthorized;
    }
    
    @Override
    public boolean isProtocolReviewNotRequiredAuthorized(ProtocolForm protocolForm) {
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        ProtocolReviewNotRequiredBean actionBean = ((ActionHelper) protocolForm.getActionHelper()).getProtocolReviewNotRequiredBean();
        boolean requestAuthorized = false;
        if (hasPermission(TaskName.PROTOCOL_REVIEW_NOT_REQUIRED, (Protocol) document.getProtocol())) {
            requestAuthorized = applyRules(new ProtocolReviewNotRequiredEvent(document, actionBean));
        }
        return requestAuthorized;
    }
    
    @Override
    public boolean isAssignReviewersAuthorized(ProtocolForm protocolForm) {
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        boolean requestAuthorized = false;
        ProtocolAssignReviewersBean actionBean = protocolForm.getActionHelper().getProtocolAssignReviewersBean();
        if (!hasDocumentStateChanged(protocolForm)) {
            if (hasPermission(TaskName.ASSIGN_REVIEWERS, (Protocol) document.getProtocol())) {
                requestAuthorized = applyRules(new ProtocolAssignReviewersEvent(document, actionBean));
            }
        } else {
            updateDocumentStatusChangedMessage();
        }
        return requestAuthorized;
    }
    
    @Override
    public boolean isWithdrawRequestActionAuthorized(ProtocolForm protocolForm) {
        return hasPermission(TaskName.PROTOCOL_WITHDRAW_SUBMISSION, (Protocol) protocolForm.getProtocolDocument().getProtocol());
    }
    
    @Override
    @SuppressWarnings("deprecation")
    public void grantExpeditedApproval(ProtocolForm protocolForm) throws Exception {
        // set the task name to prevent entered data from being overwritten (in case of user errors) due to bean refresh in the action helper's prepare view 
        protocolForm.getActionHelper().setCurrentTask(TaskName.EXPEDITE_APPROVAL);
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        ProtocolExpeditedApproveBean expeditedActionBean = (ProtocolExpeditedApproveBean) ((ActionHelper) protocolForm.getActionHelper()).getProtocolExpeditedApprovalBean();
        
        if (expeditedActionBean.isAssignToAgenda()) {
            ProtocolAssignCmtSchedBean cmtAssignBean = protocolForm.getActionHelper().getAssignCmtSchedBean();
            cmtAssignBean.setScheduleId(expeditedActionBean.getScheduleId());
            boolean alreadyAssignedToAgenda = getProtocolAssignToAgendaService().isAssignedToAgenda(document.getProtocol());
            // call the appropriate schedule assingment service based on whether the protocol was already assigned to agenda 
            if(alreadyAssignedToAgenda) {
                if(cmtAssignBean.scheduleHasChanged()) {
                    getProtocolAssignCmtSchedService().assignToCommitteeAndSchedulePostAgendaAssignment(
                        protocolForm.getProtocolDocument().getProtocol(), cmtAssignBean);
                }
            }
            else {
                getProtocolAssignCmtSchedService().assignToCommitteeAndSchedule(protocolForm.getProtocolDocument().getProtocol(), cmtAssignBean);
                ProtocolAssignToAgendaBean agendaBean = (ProtocolAssignToAgendaBean) protocolForm.getActionHelper().getAssignToAgendaBean();
                agendaBean.setScheduleDate(getProtocolAssignToAgendaService().getAssignedScheduleDate(protocolForm.getProtocolDocument().getProtocol()));
                agendaBean.setActionDate(expeditedActionBean.getActionDate());
            }  
            
            // assign to agenda only if not already assigned or if a different schedule has been selected
            if(!alreadyAssignedToAgenda || cmtAssignBean.scheduleHasChanged()) {
                getProtocolAssignToAgendaService().assignToAgenda(protocolForm.getProtocolDocument().getProtocol(), expeditedActionBean);
                recordProtocolActionSuccess("Assign to Agenda");
                generateActionCorrespondence(ProtocolActionType.ASSIGN_TO_AGENDA, protocolForm.getProtocolDocument().getProtocol());
            }
        }
        saveReviewComments(protocolForm, expeditedActionBean.getReviewCommentsBean());
        getProtocolApproveService().grantExpeditedApproval(protocolForm.getProtocolDocument().getProtocol(), expeditedActionBean);
        generateActionCorrespondence(ProtocolActionType.EXPEDITE_APPROVAL, protocolForm.getProtocolDocument().getProtocol());
        recordProtocolActionSuccess("Expedited Approval");
        protocolForm.getTabStates().put(":" + WebUtils.generateTabKey("Assign to Agenda"), "OPEN");
        
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(), ProtocolActionType.EXPEDITE_APPROVAL, "Expedited Approval Granted");
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, IrbConstants.PROTOCOL_TAB, notificationBean, false));
        if (protocolForm.getActionHelper().getProtocolCorrespondence() != null) {
            GlobalVariables.getUserSession().addObject("approvalComplCorrespondence", GlobalVariables.getUserSession().retrieveObject(DocumentAuthorizerBase.USER_SESSION_METHOD_TO_CALL_COMPLETE_OBJECT_KEY));
            // temporarily remove this key which is generated by super.approve
            GlobalVariables.getUserSession().removeObject(DocumentAuthorizerBase.USER_SESSION_METHOD_TO_CALL_COMPLETE_OBJECT_KEY);
        } else {
            IRBNotificationRenderer renderer = new IRBNotificationRenderer(document.getProtocol());
            IRBNotificationContext context = new IRBNotificationContext(document.getProtocol(), ProtocolActionType.EXPEDITE_APPROVAL, "Expedite Approved", renderer);
            getNotificationService().sendNotificationAndPersist(context, new IRBProtocolNotification(), document.getProtocol());                     
        }
    }
    
    @Override
    @SuppressWarnings("deprecation")
    public void grantFullApproval(ProtocolForm protocolForm) throws Exception {
        ProtocolApproveBean protocolApproveBean = (ProtocolApproveBean) protocolForm.getActionHelper().getProtocolFullApprovalBean();
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        saveReviewComments(protocolForm, protocolApproveBean.getReviewCommentsBean());
        getProtocolApproveService().grantFullApproval((Protocol) document.getProtocol(), protocolApproveBean);
        generateActionCorrespondence(ProtocolActionType.APPROVED, protocolForm.getProtocolDocument().getProtocol());
        recordProtocolActionSuccess("Full Approval");
        protocolForm.getProtocolHelper().prepareView();
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(), ProtocolActionType.APPROVED, "Approved");
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, IrbConstants.PROTOCOL_TAB, notificationBean, false));
        if (protocolForm.getActionHelper().getProtocolCorrespondence() != null) {
            GlobalVariables.getUserSession().addObject("approvalComplCorrespondence",GlobalVariables.getUserSession().retrieveObject(DocumentAuthorizerBase.USER_SESSION_METHOD_TO_CALL_COMPLETE_OBJECT_KEY));
            // temporarily remove this key which is generated by super.approve
            GlobalVariables.getUserSession().removeObject(DocumentAuthorizerBase.USER_SESSION_METHOD_TO_CALL_COMPLETE_OBJECT_KEY);
        } else {
            IRBNotificationRenderer renderer = new IRBNotificationRenderer(document.getProtocol());
            IRBNotificationContext context = new IRBNotificationContext(document.getProtocol(), ProtocolActionType.APPROVED, "Approved", renderer);
            getNotificationService().sendNotificationAndPersist(context, new IRBProtocolNotification(), document.getProtocol());                     
        }
    }
    
    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public boolean submitForReviewAndPromptToNotifyUser(ProtocolForm protocolForm, boolean sendNotification) throws Exception {
        ProtocolDocument protocolDocument = (ProtocolDocument) protocolForm.getProtocolDocument();
        Protocol protocol = (Protocol) protocolDocument.getProtocol();
        ProtocolSubmitAction submitAction = (ProtocolSubmitAction) protocolForm.getActionHelper().getProtocolSubmitAction();
        
        getProtocolSubmitActionService().submitToIrbForReview(protocol, submitAction);
        protocolForm.getActionHelper().getAssignCmtSchedBean().init();
        generateActionCorrespondence(ProtocolActionType.SUBMIT_TO_IRB, protocolForm.getProtocolDocument().getProtocol());

        if (sendNotification) {
            return sendSubmitForReviewNotification(protocolForm, protocol, submitAction);
        } else {
            return false;
        }
    }

    public boolean sendSubmitForReviewNotification(ProtocolForm protocolForm, Protocol protocol, ProtocolSubmitAction submitAction) {
        AssignReviewerNotificationRenderer renderer1 = new AssignReviewerNotificationRenderer(protocolForm.getProtocolDocument().getProtocol(), "added");
        List<ProtocolNotificationRequestBean> addReviewerNotificationBeans = getNotificationRequestBeans((List) submitAction.getReviewers(), ProtocolReviewerBean.CREATE);
        if (!CollectionUtils.isEmpty(addReviewerNotificationBeans)) {
            ProtocolNotificationRequestBean notificationBean1 = addReviewerNotificationBeans.get(0);
            IRBNotificationContext context1 = new IRBNotificationContext((Protocol) notificationBean1.getProtocol(),
                    (ProtocolOnlineReview) notificationBean1.getProtocolOnlineReview(), notificationBean1.getActionType(),
                    notificationBean1.getDescription(), renderer1);
            getNotificationService().sendNotificationAndPersist(context1, new IRBProtocolNotification(), protocol);

        }
        ProtocolNotificationRequestBean notificationBean2 = new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(), ProtocolActionType.SUBMIT_TO_IRB_NOTIFICATION, "Submit");
        IRBNotificationRenderer renderer2 = new IRBNotificationRenderer((Protocol) notificationBean2.getProtocol());
        IRBNotificationContext context2 = new IRBNotificationContext(protocol, notificationBean2.getActionType(), notificationBean2.getDescription(), renderer2);

        if (protocolForm.getNotificationHelper().getPromptUserForNotificationEditor(context2)) {
            context2.setForwardName(FORWARD_TO_HOLDING_PAGE);
            protocolForm.getNotificationHelper().initializeDefaultValues(context2);
            return true;
        } else {
            getNotificationService().sendNotificationAndPersist(context2, new IRBProtocolNotification(), protocol);
            return false;
        }
    }

    @Override
    public String createRenewal(ProtocolForm protocolForm) throws Exception {
        String newDocId = getProtocolAmendRenewService().createRenewal(protocolForm.getProtocolDocument(), protocolForm.getActionHelper().getRenewalSummary());
        generateActionCorrespondence(ProtocolActionType.RENEWAL_CREATED, protocolForm.getProtocolDocument().getProtocol());
        refreshAfterProtocolAction(protocolForm, newDocId, ACTION_NAME_RENEWAL_WITHOUT_AMENDMENT, true);
        // Form fields copy needed to support modifyAmendmentSections
        protocolForm.getActionHelper().getProtocolAmendmentBean().setSummary(protocolForm.getActionHelper().getRenewalSummary());
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(), ProtocolActionType.RENEWAL_CREATED_NOTIFICATION, "Renewal Created");
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, IrbConstants.PROTOCOL_TAB, notificationBean, false));
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, IrbConstants.PROTOCOL_TAB);
    }
    
    @Override
    public String assignToAgenda(ProtocolForm protocolForm) throws Exception {
        ProtocolAssignToAgendaBean actionBean = (ProtocolAssignToAgendaBean) protocolForm.getActionHelper().getAssignToAgendaBean();
        saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
        getProtocolAssignToAgendaService().assignToAgenda(protocolForm.getProtocolDocument().getProtocol(), actionBean);
        generateActionCorrespondence(ProtocolActionType.ASSIGN_TO_AGENDA, protocolForm.getProtocolDocument().getProtocol());
        recordProtocolActionSuccess(ACTION_NAME_ASSIGN_TO_AGENDA);
        
        Protocol protocol = (Protocol) protocolForm.getProtocolDocument().getProtocol();
        org.kuali.kra.irb.actions.ProtocolAction lastAction = (org.kuali.kra.irb.actions.ProtocolAction) protocolForm.getProtocolDocument().getProtocol().getLastProtocolAction();
        ProtocolActionType lastActionType = (ProtocolActionType) lastAction.getProtocolActionType();
        String description = lastActionType.getDescription();

        IRBNotificationRenderer renderer = new IRBNotificationRenderer(protocol);
        IRBNotificationContext context = new IRBNotificationContext(protocol, ProtocolActionType.ASSIGN_TO_AGENDA, description, renderer);
        
        if (protocolForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
            protocolForm.getNotificationHelper().initializeDefaultValues(context);
            return IrbConstants.PROTOCOL_NOTIFICATION_EDITOR;
        } else {
            getNotificationService().sendNotificationAndPersist(context, new IRBProtocolNotification(), protocol);
            return Constants.MAPPING_BASIC;
        }
    }
    
    @Override
    public String createAmendment(ProtocolForm protocolForm) throws Exception {
        String newDocId = getProtocolAmendRenewService().createAmendment(protocolForm.getProtocolDocument(),
                protocolForm.getActionHelper().getProtocolAmendmentBean());
        generateActionCorrespondence(ProtocolActionType.AMENDMENT_CREATED, protocolForm.getProtocolDocument().getProtocol());
        refreshAfterProtocolAction(protocolForm, newDocId, ACTION_NAME_AMENDMENT, true);
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(), ProtocolActionType.AMENDMENT_CREATED_NOTIFICATION, "Amendment Created");
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, IrbConstants.PROTOCOL_TAB, notificationBean, false));
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, IrbConstants.PROTOCOL_TAB);
    }
    
    @Override
    public String createRenewalWithAmendment(ProtocolForm protocolForm) throws Exception {
        String newDocId = getProtocolAmendRenewService().createRenewalWithAmendment(protocolForm.getProtocolDocument(),
                protocolForm.getActionHelper().getProtocolRenewAmendmentBean());
        generateActionCorrespondence(ProtocolActionType.RENEWAL_WITH_AMENDMENT_CREATED, protocolForm.getProtocolDocument().getProtocol());
        refreshAfterProtocolAction(protocolForm, newDocId, ACTION_NAME_RENEWAL_WITH_AMENDMENT, true);
        // Form fields copy needed to support modifyAmendmentSections
        protocolForm.getActionHelper().setProtocolAmendmentBean(protocolForm.getActionHelper().getProtocolRenewAmendmentBean());
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(), ProtocolActionType.RENEWAL_WITH_AMENDMENT_CREATED, "Renewal With Amendment Created");
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, IrbConstants.PROTOCOL_TAB, notificationBean, false));
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, IrbConstants.PROTOCOL_TAB);
    }
    
    @Override
    public String withdrawProtocol(ProtocolForm protocolForm) throws Exception {
        ProtocolBase previousProtocol = protocolForm.getProtocolDocument().getProtocol();
        boolean isVersion = ProtocolStatus.IN_PROGRESS.equals(previousProtocol.getProtocolStatusCode()) || 
        ProtocolStatus.SUBMITTED_TO_IRB.equals(previousProtocol.getProtocolStatusCode());
        ProtocolDocument pd = (ProtocolDocument) getProtocolWithdrawService().withdraw(protocolForm.getProtocolDocument().getProtocol(),
                protocolForm.getActionHelper().getProtocolWithdrawBean());
        Protocol protocol = pd.getProtocol();
        generateActionCorrespondence(ProtocolActionType.WITHDRAWN, protocol);
        refreshAfterProtocolAction(protocolForm, pd.getDocumentNumber(), ACTION_NAME_WITHDRAW, false);
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(protocol, ProtocolActionType.WITHDRAWN, "Withdrawn");
        ProtocolCorrespondence protocolCorrespondence = (ProtocolCorrespondence)getProtocolCorrespondence(protocol, IrbConstants.PROTOCOL_TAB, notificationBean, false);
        protocolForm.getActionHelper().setProtocolCorrespondence(protocolCorrespondence);
        if(isVersion) {
            ProtocolNotificationContextBase context = getProtocolNotificationContextHook(notificationBean, protocolForm);
            ProtocolBase notificationProtocol = null;
            if(protocolCorrespondence != null) {
                getProtocolActionCorrespondenceGenerationService().attachProtocolCorrespondence(previousProtocol, protocolCorrespondence.getCorrespondence(), 
                        protocolCorrespondence.getProtoCorrespTypeCode());
                notificationProtocol = previousProtocol;
            }else {
                notificationProtocol = protocol;
            }
            getNotificationService().sendNotificationAndPersist(context, getProtocolNotificationInstanceHook(), notificationProtocol);
        }
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, IrbConstants.PROTOCOL_TAB);
    }
    
    @Override
    public String grantResponseApproval(ProtocolForm protocolForm) throws Exception {
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        ProtocolApproveBean actionBean = ((ActionHelper) protocolForm.getActionHelper()).getProtocolResponseApprovalBean();
        saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
        getProtocolApproveService().grantResponseApproval(document.getProtocol(), actionBean);
        generateActionCorrespondence(ProtocolActionType.RESPONSE_APPROVAL, protocolForm.getProtocolDocument().getProtocol());
        recordProtocolActionSuccess(ACTION_NAME_RESPONSE_APPROVAL);
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(document.getProtocol(), ProtocolActionType.RESPONSE_APPROVAL, "Response Approval");
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, IrbConstants.PROTOCOL_TAB, notificationBean, false));
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, IrbConstants.PROTOCOL_TAB);
    }
    
    @Override
    public String performRequestAction(ProtocolForm protocolForm, String taskName) throws Exception {
        ProtocolDocument document = protocolForm.getProtocolDocument();
        protocolForm.getActionHelper().preSaveSubmissionQuestionnaires();
        ProtocolRequestAction requestAction = ProtocolRequestAction.valueOfTaskName(taskName);
        ProtocolRequestBean requestBean = getProtocolRequestBean(protocolForm, taskName);
        if (requestBean != null) {
            boolean valid = applyRules(new ProtocolRequestEvent<ProtocolRequestRule>(document, requestAction.getErrorPath(), requestBean));
            List<AnswerHeader> answerHeaders = requestBean.getQuestionnaireHelper().getAnswerHeaders();
            valid &= isMandatoryQuestionnaireComplete(answerHeaders, "actionHelper." + requestAction.getBeanName() + ".datavalidation");
            if (valid) {
                getProtocolRequestService().submitRequest(protocolForm.getProtocolDocument().getProtocol(), requestBean);            
                
                //Assign to Committee and Schedule sub-panel Committee drop down needs to reflect the Committee value just selected
                protocolForm.getProtocolDocument().getProtocol().refreshReferenceObject("protocolSubmissions");
                ProtocolAssignCmtSchedBean cmtAssignBean = protocolForm.getActionHelper().getAssignCmtSchedBean();
                cmtAssignBean.setCommitteeId(requestBean.getCommitteeId());
                cmtAssignBean.setScheduleId("");
                cmtAssignBean.prepareView();

                generateActionCorrespondence(requestBean.getProtocolActionTypeCode(), protocolForm.getProtocolDocument().getProtocol());
                recordProtocolActionSuccess(requestAction.getActionName());
                return sendRequestNotification(protocolForm, requestBean.getProtocolActionTypeCode(), requestBean.getReason(), IrbConstants.PROTOCOL_ACTIONS_TAB);
            }
        }
        return Constants.MAPPING_BASIC;
    }
    
    @Override
    public String withdrawRequestAction(ProtocolForm protocolForm) throws Exception {
        ProtocolDocument document = protocolForm.getProtocolDocument();
        ProtocolRequestAction requestAction = ProtocolRequestAction.valueOfTaskName(TaskName.PROTOCOL_WITHDRAW_SUBMISSION);
        ProtocolRequestBean requestBean = getProtocolRequestBean(protocolForm, TaskName.PROTOCOL_WITHDRAW_SUBMISSION);
        if (requestBean != null) {
            boolean valid = applyRules(new ProtocolRequestEvent<ProtocolRequestRule>(document, requestAction.getErrorPath(), requestBean));
            if (valid) {
                // find recently submitted action request and complete it
                List<ProtocolSubmissionBase> submissions = document.getProtocol().getProtocolSubmissions();
                ProtocolSubmissionBase submission = null;
                for (ProtocolSubmissionBase sub: submissions) {
                    if (requestSubmissionTypes.contains(sub.getSubmissionTypeCode())) {
                        submission = sub;
                    }
                }
                if (submission != null) {
                    submission.setSubmissionStatusCode(ProtocolSubmissionStatus.WITHDRAWN);
                    ProtocolAction protocolAction = new ProtocolAction(document.getProtocol(), null, ProtocolActionType.WITHDRAW_SUBMISSION);
                    protocolAction.setComments(requestBean.getReason());
                    protocolAction.setActionDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
                    protocolAction.setSubmissionIdFk(submission.getSubmissionId());
                    protocolAction.setSubmissionNumber(submission.getSubmissionNumber());
                    document.getProtocol().getProtocolActions().add(protocolAction);
                    getProtocolActionService().updateProtocolStatus(protocolAction, document.getProtocol());
                    getBusinessObjectService().save(submission);
                    recordProtocolActionSuccess(requestAction.getActionName());
                    return sendRequestNotification(protocolForm, requestBean.getProtocolActionTypeCode(), requestBean.getReason(), IrbConstants.PROTOCOL_ACTIONS_TAB);
                }
            }
        }
        return Constants.MAPPING_BASIC;
    }
    
    @Override
    public String closeProtocol(ProtocolForm protocolForm) throws Exception {
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGenericActionBean actionBean = (ProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolCloseBean();
        saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
        getProtocolGenericActionService().close(protocol, actionBean);
        generateActionCorrespondence(ProtocolActionType.CLOSED_ADMINISTRATIVELY_CLOSED, protocolForm.getProtocolDocument().getProtocol());
        recordProtocolActionSuccess(ACTION_NAME_CLOSE);
        ProtocolNotificationRequestBean notificationBean = null;
        if (ProtocolStatus.CLOSED_ADMINISTRATIVELY.equals(protocol.getProtocolStatus())) {
            notificationBean = new ProtocolNotificationRequestBean(protocol, ProtocolActionType.CLOSED_ADMINISTRATIVELY_CLOSED, "Closed By Administrator");
        } else {
            notificationBean = new ProtocolNotificationRequestBean(protocol, ProtocolActionType.CLOSED_ADMINISTRATIVELY_CLOSED, "Closed By Investigator");
        }
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, IrbConstants.PROTOCOL_TAB, notificationBean, false));
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, IrbConstants.PROTOCOL_TAB);
    }
    
    @Override
    public String closeEnrollment(ProtocolForm protocolForm) throws Exception {
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolCloseEnrollmentBean();
        saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
        getProtocolGenericActionService().closeEnrollment(protocol, actionBean);
        generateActionCorrespondence(ProtocolActionType.CLOSED_FOR_ENROLLMENT, protocolForm.getProtocolDocument().getProtocol());
        recordProtocolActionSuccess(ACTION_NAME_CLOSE_ENROLLMENT);
        return Constants.MAPPING_BASIC;
    }
    
    @Override
    public String deferProtocol(ProtocolForm protocolForm) throws Exception {
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolDeferBean();
        saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
        getProtocolGenericActionService().defer(protocol, actionBean);
        generateActionCorrespondence(ProtocolActionType.DEFERRED, protocolForm.getProtocolDocument().getProtocol());
        ProtocolDocument newDocument = getProtocolGenericActionService().getDeferredVersionedDocument(protocol);
        refreshAfterProtocolAction(protocolForm, newDocument.getDocumentNumber(), ACTION_NAME_DEFER, false);
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(),ProtocolActionType.DEFERRED, "Deferred");
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, IrbConstants.PROTOCOL_TAB, notificationBean, false));
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, IrbConstants.PROTOCOL_TAB);
    }

    @Override
    public String disapproveProtocol(ProtocolForm protocolForm) throws Exception {
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGenericActionBean actionBean = (ProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolDisapproveBean();
        saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
        getProtocolGenericActionService().disapprove(protocol, actionBean);
        generateActionCorrespondence(ProtocolActionType.DISAPPROVED, protocolForm.getProtocolDocument().getProtocol());
        recordProtocolActionSuccess(ACTION_NAME_DISAPPROVE);
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(), ProtocolActionType.DISAPPROVED, "Disapproved");
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, IrbConstants.PROTOCOL_TAB, notificationBean, false));
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, IrbConstants.PROTOCOL_TAB);
    }
    
    @Override
    public String expireProtocol(ProtocolForm protocolForm) throws Exception {
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGenericActionBean actionBean = (ProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolExpireBean();
        saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
        getProtocolGenericActionService().expire(protocol, actionBean);
        generateActionCorrespondence(ProtocolActionType.EXPIRED, protocolForm.getProtocolDocument().getProtocol());
        recordProtocolActionSuccess(ACTION_NAME_EXPIRE);
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(), ProtocolActionType.EXPIRED, "Expired");
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, IrbConstants.PROTOCOL_TAB, notificationBean, false));
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, IrbConstants.PROTOCOL_TAB);
    }
    
    @Override
    public String grantExemption(ProtocolForm protocolForm) throws Exception {
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        Protocol protocol = (Protocol) document.getProtocol();
        ProtocolGrantExemptionBean actionBean = ((ActionHelper) protocolForm.getActionHelper()).getProtocolGrantExemptionBean();
        saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
        getProtocolGrantExemptionService().grantExemption(protocol, actionBean);
        generateActionCorrespondence(ProtocolActionType.GRANT_EXEMPTION, protocolForm.getProtocolDocument().getProtocol());
        recordProtocolActionSuccess(ACTION_NAME_GRANT_EXEMPTION);
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(), ProtocolActionType.GRANT_EXEMPTION, "Exemption Granted");
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, IrbConstants.PROTOCOL_TAB, notificationBean, false));
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, IrbConstants.PROTOCOL_TAB);
    }
    
    @Override
    public String irbAcknowledgement(ProtocolForm protocolForm) throws Exception {
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolIrbAcknowledgementBean();
        saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
        getProtocolGenericActionService().irbAcknowledgement(protocol, actionBean);
        generateActionCorrespondence(ProtocolActionType.IRB_ACKNOWLEDGEMENT, protocolForm.getProtocolDocument().getProtocol());
        recordProtocolActionSuccess(ACTION_NAME_IRB_ACKNOWLEDGEMENT);
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(),ProtocolActionType.IRB_ACKNOWLEDGEMENT, "IRB Acknowledgement");
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, IrbConstants.PROTOCOL_ACTIONS_TAB);
    }
    
    @Override
    public String permitDataAnalysis(ProtocolForm protocolForm) throws Exception {
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolPermitDataAnalysisBean();
        saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
        getProtocolGenericActionService().permitDataAnalysis(protocol, actionBean);
        generateActionCorrespondence(ProtocolActionType.DATA_ANALYSIS_ONLY, protocolForm.getProtocolDocument().getProtocol());
        recordProtocolActionSuccess(ACTION_NAME_DATA_ANALYSIS_ONLY);
        return Constants.MAPPING_BASIC;
    }
    
    @Override
    public String reopenEnrollment(ProtocolForm protocolForm) throws Exception {
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolReopenEnrollmentBean();
        saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
        getProtocolGenericActionService().reopenEnrollment(protocol, actionBean);
        generateActionCorrespondence(ProtocolActionType.REOPEN_ENROLLMENT, protocolForm.getProtocolDocument().getProtocol());
        recordProtocolActionSuccess(ACTION_NAME_REOPEN_ENROLLMENT);
        return Constants.MAPPING_BASIC;
    }
    
    @Override
    public String returnForSMR(ProtocolForm protocolForm) throws Exception {
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGenericActionBean actionBean = (ProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolSMRBean();
        ProtocolDocument newDocument = (ProtocolDocument) getProtocolGenericActionService().returnForSMR(protocol, actionBean);
        saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
        generateActionCorrespondence(ProtocolActionType.SPECIFIC_MINOR_REVISIONS_REQUIRED, newDocument.getProtocol());
        refreshAfterProtocolAction(protocolForm, newDocument.getDocumentNumber(), ACTION_NAME_SMR, false);
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, IrbConstants.PROTOCOL_TAB, new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(), ProtocolActionType.SPECIFIC_MINOR_REVISIONS_REQUIRED, "Specific Minor Revisions Required"), false));
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(),ProtocolActionType.SPECIFIC_MINOR_REVISIONS_REQUIRED, "Specific Minor Revisions Required");
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, IrbConstants.PROTOCOL_TAB);
    }

    @Override
    public String returnForSRR(ProtocolForm protocolForm) throws Exception {
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGenericActionBean actionBean = (ProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolSRRBean();
        ProtocolDocument newDocument = (ProtocolDocument) getProtocolGenericActionService().returnForSRR(protocol, actionBean);
        saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
        generateActionCorrespondence(ProtocolActionType.SUBSTANTIVE_REVISIONS_REQUIRED, newDocument.getProtocol());
        refreshAfterProtocolAction(protocolForm, newDocument.getDocumentNumber(), ACTION_NAME_SRR, false);
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, IrbConstants.PROTOCOL_TAB, new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(), ProtocolActionType.SUBSTANTIVE_REVISIONS_REQUIRED, "Substantive Revisions Required"), false));
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(),ProtocolActionType.SUBSTANTIVE_REVISIONS_REQUIRED, "Substantive Revisions Required");
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, IrbConstants.PROTOCOL_TAB);
    }
    
    @Override
    public String returnToPI(ProtocolForm protocolForm) throws Exception {
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGenericActionBean actionBean = (ProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolReturnToPIBean();
        ProtocolDocument newDocument = (ProtocolDocument) getProtocolGenericActionService().returnToPI(protocol, actionBean);
        saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
        generateActionCorrespondence(ProtocolActionType.RETURNED_TO_PI, newDocument.getProtocol());
        refreshAfterProtocolAction(protocolForm, newDocument.getDocumentNumber(), ACTION_NAME_RETURN_TO_PI, false);
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, IrbConstants.PROTOCOL_TAB, new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(), ProtocolActionType.RETURNED_TO_PI, "Return To PI"), false));
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(newDocument.getProtocol(),ProtocolActionType.RETURNED_TO_PI, "Returned To PI");
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, IrbConstants.PROTOCOL_TAB);
    }
    
    @Override
    public String suspend(ProtocolForm protocolForm) throws Exception {
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGenericActionBean actionBean = (ProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolSuspendBean();
        saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
        getProtocolGenericActionService().suspend(protocol, actionBean);
        generateActionCorrespondence(ProtocolActionType.SUSPENDED, protocolForm.getProtocolDocument().getProtocol());
        recordProtocolActionSuccess(ACTION_NAME_SUSPEND);
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(), ProtocolActionType.SUSPENDED, "Suspended");
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, IrbConstants.PROTOCOL_TAB, notificationBean, false));
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, IrbConstants.PROTOCOL_TAB);
    }
    
    @Override
    public String suspendByDsmb(ProtocolForm protocolForm) throws Exception {
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolSuspendByDsmbBean();
        saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
        getProtocolGenericActionService().suspendByDsmb(protocol, actionBean);
        generateActionCorrespondence(ProtocolActionType.SUSPENDED_BY_DSMB, protocolForm.getProtocolDocument().getProtocol());
        recordProtocolActionSuccess(ACTION_NAME_SUSPEND_BY_DSMB);
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(), ProtocolActionType.SUSPENDED_BY_DSMB, "Suspended by DSMB");
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, IrbConstants.PROTOCOL_TAB, notificationBean, false));
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, IrbConstants.PROTOCOL_TAB);
    }
    
    @Override
    public String terminate(ProtocolForm protocolForm) throws Exception {
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGenericActionBean actionBean = (ProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolTerminateBean();
        saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
        getProtocolGenericActionService().terminate(protocol, actionBean);
        generateActionCorrespondence(ProtocolActionType.TERMINATED, protocolForm.getProtocolDocument().getProtocol());
        recordProtocolActionSuccess(ACTION_NAME_TERMINATE);
        protocolForm.getProtocolHelper().prepareView();
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(), ProtocolActionType.TERMINATED, "Terminated");
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, IrbConstants.PROTOCOL_TAB, notificationBean, false));
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, IrbConstants.PROTOCOL_TAB);
    }
    
    @Override
    public String manageComments(ProtocolForm protocolForm) throws Exception {
        ProtocolGenericActionBean actionBean = (ProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolManageReviewCommentsBean();
        saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
        generateActionCorrespondence(ProtocolActionType.MANAGE_REVIEW_COMMENTS, protocolForm.getProtocolDocument().getProtocol());
        recordProtocolActionSuccess(ACTION_NAME_MANAGE_REVIEW_COMMENTS);
        return Constants.MAPPING_BASIC;
    }
    
    @Override
    public String openProtocolForAdminCorrection(ProtocolForm protocolForm) throws Exception {
        ProtocolDocument protocolDocument = protocolForm.getProtocolDocument();
        protocolDocument.getProtocol().setCorrectionMode(true); 
        protocolForm.getProtocolHelper().prepareView();
        AdminCorrectionBean adminCorrectionBean = (AdminCorrectionBean) protocolForm.getActionHelper().getProtocolAdminCorrectionBean();
        protocolDocument.updateProtocolStatus(ProtocolActionType.ADMINISTRATIVE_CORRECTION, adminCorrectionBean.getComments());
        generateActionCorrespondence(ProtocolActionType.ADMINISTRATIVE_CORRECTION, protocolForm.getProtocolDocument().getProtocol());
        recordProtocolActionSuccess(ACTION_NAME_MANAGE_ADMINISTRATIVE_CORRECTION);
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(protocolDocument.getProtocol(), ProtocolActionType.ADMINISTRATIVE_CORRECTION, "Administrative Correction");
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, IrbConstants.PROTOCOL_TAB, notificationBean, false));
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, IrbConstants.PROTOCOL_TAB);
    }
    
    @Override
    @SuppressWarnings("deprecation")
    public String submitCommitteeDecision(ProtocolForm protocolForm) throws Exception {
        CommitteeDecision actionBean = (CommitteeDecision) protocolForm.getActionHelper().getCommitteeDecision();
        saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
        getCommitteeDecisionService().processCommitteeDecision(protocolForm.getProtocolDocument().getProtocol(), actionBean);
        generateActionCorrespondence(ProtocolActionType.RECORD_COMMITTEE_DECISION, protocolForm.getProtocolDocument().getProtocol());
        protocolForm.getTabStates().put(":" + WebUtils.generateTabKey(motionTypeMap.get(actionBean.getMotionTypeCode())), "OPEN");
        recordProtocolActionSuccess(ACTION_NAME_RECORD_COMMITTEE_DECISION);
        return Constants.MAPPING_BASIC;
    }
    
    @Override
    public String abandon(ProtocolForm protocolForm) throws Exception {
        getProtocolAbandonService().abandonProtocol(protocolForm.getProtocolDocument().getProtocol(),
                protocolForm.getActionHelper().getProtocolAbandonBean());
        generateActionCorrespondence(ProtocolActionType.ABANDON_PROTOCOL, protocolForm.getProtocolDocument().getProtocol());
        protocolForm.getProtocolHelper().prepareView();
        recordProtocolActionSuccess(ACTION_NAME_RECORD_ABANDON);
        protocolForm.getProtocolHelper().prepareView();
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, IrbConstants.PROTOCOL_ACTIONS_TAB, new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(), ProtocolActionType.ABANDON_PROTOCOL, "Abandon"), false));
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(), ProtocolActionType.ABANDON_PROTOCOL, "Abandon");
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, IrbConstants.PROTOCOL_TAB);
    }

    @Override
    public String notifyIrbProtocol(ProtocolForm protocolForm) throws Exception {
        String returnPath = Constants.MAPPING_BASIC;
        protocolForm.getActionHelper().preSaveSubmissionQuestionnaires();
        List<AnswerHeader> answerHeaders = protocolForm.getActionHelper().getProtocolNotifyIrbBean().getQuestionnaireHelper().getAnswerHeaders();
        if (isMandatoryQuestionnaireComplete(answerHeaders, "actionHelper.protocolNotifyIrbBean.datavalidation")) {
            String returnTab = IrbConstants.PROTOCOL_ACTIONS_TAB;
            if (protocolForm.getActionHelper().isUseAlternateNotifyAction()) {
                String newDocId = getProtocolAmendRenewService().createFYI(protocolForm.getProtocolDocument(), protocolForm.getActionHelper().getProtocolNotifyIrbBean());
                protocolForm.getQuestionnaireHelper().setAnswerHeaders(new ArrayList<>());
                generateActionCorrespondence(ProtocolActionType.NOTIFY_IRB, protocolForm.getProtocolDocument().getProtocol());
                refreshAfterProtocolAction(protocolForm, newDocId, ACTION_NAME_FYI, true);
                returnTab = IrbConstants.PROTOCOL_TAB;
            }
            else {
                getProtocolNotifyIrbService().submitIrbNotification(protocolForm.getProtocolDocument().getProtocol(), protocolForm.getActionHelper().getProtocolNotifyIrbBean());
                protocolForm.getQuestionnaireHelper().setAnswerHeaders(new ArrayList<>());
                protocolForm.setReinitializeModifySubmissionFields(true);
                LOG.info("notifyIrbProtocol " + protocolForm.getProtocolDocument().getDocumentNumber());
                generateActionCorrespondence(ProtocolActionType.NOTIFY_IRB, protocolForm.getProtocolDocument().getProtocol());
                recordProtocolActionSuccess(ACTION_NAME_NOTIFY_IRB);
            }
            ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(), ProtocolActionType.NOTIFY_IRB, "Notify IRB");
            protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, returnTab, notificationBean, false));
            returnPath = getRedirectPathAfterProtocolAction(protocolForm, notificationBean, returnTab);
        }
        return returnPath;
    }
    
    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String assignReviewers(ProtocolForm protocolForm) throws Exception {
        String returnPath = Constants.MAPPING_BASIC;
        ProtocolAssignReviewersBean actionBean = protocolForm.getActionHelper().getProtocolAssignReviewersBean();
        ProtocolSubmission submission = (ProtocolSubmission) protocolForm.getProtocolDocument().getProtocol().getProtocolSubmission();
        List<ProtocolReviewerBean> beans = (List)actionBean.getReviewers();
        getProtocolAssignReviewersService().assignReviewers(submission, (List) beans);
        //clear the warnings before rendering the page.
        GlobalVariables.getMessageMap().getWarningMessages().clear();
        
        recordProtocolActionSuccess("Assign Reviewers");
        AssignReviewerNotificationRenderer renderer = new AssignReviewerNotificationRenderer(protocolForm
                .getProtocolDocument().getProtocol(), "added");
        List<ProtocolNotificationRequestBean> addReviewerNotificationBeans = getNotificationRequestBeans(beans,
                ProtocolReviewerBean.CREATE);
        List<ProtocolNotificationRequestBean> removeReviewerNotificationBeans = getNotificationRequestBeans(beans,
                ProtocolReviewerBean.REMOVE);
        if (!CollectionUtils.isEmpty(addReviewerNotificationBeans)) {
            ProtocolNotificationRequestBean notificationBean = addReviewerNotificationBeans.get(0);
            IRBNotificationContext context = new IRBNotificationContext((Protocol) notificationBean.getProtocol(),
                (ProtocolOnlineReview) notificationBean.getProtocolOnlineReview(), notificationBean.getActionType(),
                notificationBean.getDescription(), renderer);
            if (protocolForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
                boolean sendNotification = checkToSendNotification(protocolForm, renderer, addReviewerNotificationBeans, IrbConstants.PROTOCOL_ACTIONS_TAB); 
                returnPath = sendNotification ? IrbConstants.PROTOCOL_NOTIFICATION_EDITOR : IrbConstants.PROTOCOL_ACTIONS_TAB;
                if (!CollectionUtils.isEmpty(removeReviewerNotificationBeans)) {
                    GlobalVariables.getUserSession().addObject("removeReviewer", removeReviewerNotificationBeans);
                }
            }
        }else {
            if (!CollectionUtils.isEmpty(removeReviewerNotificationBeans)) {
                renderer = new AssignReviewerNotificationRenderer(protocolForm.getProtocolDocument().getProtocol(),
                    "removed");
                ProtocolNotificationRequestBean notificationBean = removeReviewerNotificationBeans.get(0);
                IRBNotificationContext context = new IRBNotificationContext( (Protocol) notificationBean.getProtocol(),
                    (ProtocolOnlineReview) notificationBean.getProtocolOnlineReview(), notificationBean.getActionType(),
                    notificationBean.getDescription(), renderer);
                if (protocolForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
                    boolean sendNotification = checkToSendNotification(protocolForm, renderer, removeReviewerNotificationBeans, IrbConstants.PROTOCOL_ACTIONS_TAB); 
                    returnPath = sendNotification ? IrbConstants.PROTOCOL_NOTIFICATION_EDITOR : IrbConstants.PROTOCOL_ACTIONS_TAB;
                }
            }
        }
        generateActionCorrespondence(ProtocolActionType.ASSIGN_REVIEWER, protocolForm.getProtocolDocument().getProtocol());
        return returnPath;
    }
    
    @Override
    public String notifyCommitteeProtocol(ProtocolForm protocolForm) throws Exception {
        // set the task name to prevent entered data from being overwritten (in case of user errors) due to bean refresh in the action helper's prepare view 
        protocolForm.getActionHelper().setCurrentTask(TaskName.NOTIFY_COMMITTEE);
        Protocol protocol = (Protocol) protocolForm.getProtocolDocument().getProtocol();
        ActionHelper actionHelper = (ActionHelper) protocolForm.getActionHelper();
        getProtocolNotifyCommitteeService().submitCommitteeNotification(protocol, (ProtocolNotifyCommitteeBean) actionHelper.getProtocolNotifyCommitteeBean());
        generateActionCorrespondence(ProtocolActionType.NOTIFIED_COMMITTEE, protocolForm.getProtocolDocument().getProtocol());
        recordProtocolActionSuccess(ACTION_NAME_NOTIFY_COMMITTEE);
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(protocol, ProtocolActionType.NOTIFIED_COMMITTEE, "Notify Committee");
        // get committee name
        Committee committee = getCommitteeService().getCommitteeById(actionHelper.getProtocolNotifyCommitteeBean().getCommitteeId());
        if (committee != null) {
            notificationBean.setCommitteeName(committee.getCommitteeName());
        }
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, IrbConstants.PROTOCOL_ACTIONS_TAB, notificationBean, false));
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, IrbConstants.PROTOCOL_ACTIONS_TAB);
    }
    
    @Override
    public String protocolReviewNotRequired(ProtocolForm protocolForm) throws Exception {
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        ProtocolReviewNotRequiredBean actionBean = ((ActionHelper) protocolForm.getActionHelper()).getProtocolReviewNotRequiredBean();
        getProtocolReviewNotRequiredService().reviewNotRequired(document, actionBean);
        generateActionCorrespondence(ProtocolActionType.IRB_REVIEW_NOT_REQUIRED, protocolForm.getProtocolDocument().getProtocol());
        recordProtocolActionSuccess(ACTION_NAME_REVIEW_NOT_REQUIRED);
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(document.getProtocol(), ProtocolActionType.IRB_REVIEW_NOT_REQUIRED, "Review Not Required");
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, IrbConstants.PROTOCOL_TAB, notificationBean, false));
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, IrbConstants.PROTOCOL_TAB);
    }
    
    @Override
    public void assignedReviewComplete(ProtocolForm protocolForm) throws Exception {
        generateActionCorrespondence(ProtocolActionType.REVIEW_COMPLETE, protocolForm.getProtocolDocument().getProtocol());
    }
    
    @Override
    public void assignedReviewRejected(ProtocolForm protocolForm) throws Exception {
        generateActionCorrespondence(ProtocolActionType.REVIEW_REJECTED, protocolForm.getProtocolDocument().getProtocol());
    }
    
    @Override
    public void assignedReviewDeleted(ProtocolForm protocolForm) throws Exception {
        generateActionCorrespondence(ProtocolActionType.REVIEW_DELETED, protocolForm.getProtocolDocument().getProtocol());
    }
    
    @Override
    public void generateFundingSource(ProtocolForm protocolForm) throws Exception {
        generateActionCorrespondence(ProtocolActionType.FUNDING_SOURCE, protocolForm.getProtocolDocument().getProtocol());
    }
    
    private ProtocolRequestBean getProtocolRequestBean(ProtocolForm protocolForm, String taskName) {
        ProtocolRequestBean protocolRequestBean = null;
        ProtocolActionBean protocolActionBean = getActionBean(protocolForm, taskName);
        if (protocolActionBean != null && protocolActionBean instanceof ProtocolRequestBean) {
            protocolRequestBean = (ProtocolRequestBean) protocolActionBean;
        }
        return protocolRequestBean;
    }
    
    private List<ProtocolNotificationRequestBean> getNotificationRequestBeans(List<ProtocolReviewerBean> beans, String actionFlag) {
        List<ProtocolNotificationRequestBean> notificationRequestBeans = new ArrayList<ProtocolNotificationRequestBean>();
        for (ProtocolReviewerBean bean : beans) {
            if (StringUtils.equals(actionFlag, bean.getActionFlag())) {
                notificationRequestBeans.add((ProtocolNotificationRequestBean) bean.getNotificationRequestBean());
            }
        }
        return notificationRequestBeans;
    }
    
    /*
     * This is for assign reviewer and submit for review.  The notificationRequestBeans contains all 'added' or 'removed'
     * reviewers.  All the roles recipient will be merged, then forward to protocolnotificationeditor for ad hoc notification 
     * process.
     */
    private boolean checkToSendNotification(ProtocolForm protocolForm, IRBNotificationRenderer renderer, List<ProtocolNotificationRequestBean> notificationRequestBeans, String promptAfterNotification) {
        IRBNotificationContext context = new IRBNotificationContext((Protocol) notificationRequestBeans.get(0).getProtocol(),
            (ProtocolOnlineReview) notificationRequestBeans.get(0).getProtocolOnlineReview(), notificationRequestBeans.get(0).getActionType(),
            notificationRequestBeans.get(0).getDescription(), renderer);
        context.setPopulateRole(true);
        if (protocolForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
            protocolForm.getNotificationHelper().initializeDefaultValues(context);
            List<NotificationTypeRecipient> notificationRecipients = protocolForm.getNotificationHelper()
                    .getNotificationRecipients();
            List<NotificationTypeRecipient> allRecipients = new ArrayList<NotificationTypeRecipient>();
            for (NotificationTypeRecipient recipient : notificationRecipients) {
                try {
                    NotificationTypeRecipient copiedRecipient = (NotificationTypeRecipient) ObjectUtils.deepCopy(recipient);
                    // populate role qualifier with proper context
                    context.populateRoleQualifiers(copiedRecipient);
                    allRecipients.add(copiedRecipient);
                }
                catch (Exception e) {
                    LOG.error(e.getMessage(), e);
                }
            }
            int i = 1;
            // add all new reviewer to recipients
            while (notificationRequestBeans.size() > i) {
                context = new IRBNotificationContext( (Protocol) notificationRequestBeans.get(i).getProtocol(), 
                                                     (ProtocolOnlineReview) notificationRequestBeans.get(i).getProtocolOnlineReview(), 
                                                     notificationRequestBeans.get(i).getActionType(), 
                                                     notificationRequestBeans.get(i).getDescription(), renderer);
                context.setPopulateRole(true);
                protocolForm.getNotificationHelper().initializeDefaultValues(context);
                List<NotificationTypeRecipient> recipients = protocolForm.getNotificationHelper().getNotificationRecipients();

                for (NotificationTypeRecipient recipient : recipients) {
                    try {
                        /* NOTE : need to deepcopy here. If I don't do that, then all reviewer role will have same
                         * notification recipient object returned from service call 
                         */
                        NotificationTypeRecipient copiedRecipient = (NotificationTypeRecipient) ObjectUtils.deepCopy(recipient);
                        context.populateRoleQualifiers(copiedRecipient);
                        allRecipients.add(copiedRecipient);
                    }
                    catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                    }
                }
                i++;
            }
            protocolForm.getNotificationHelper().setNotificationRecipients(allRecipients);
            if (promptAfterNotification == null) {
                context.setForwardName(RETURN_TO_HOLDING_PAGE);
            } else {
                context.setForwardName(promptAfterNotification);
            }
            return true;
        }else {
            return false;
        }
    }
    
    public ProtocolAssignToAgendaService getProtocolAssignToAgendaService() {
        return protocolAssignToAgendaService;
    }

    public void setProtocolAssignToAgendaService(ProtocolAssignToAgendaService protocolAssignToAgendaService) {
        this.protocolAssignToAgendaService = protocolAssignToAgendaService;
    }

    public ProtocolAssignCmtSchedService getProtocolAssignCmtSchedService() {
        return protocolAssignCmtSchedService;
    }

    public void setProtocolAssignCmtSchedService(ProtocolAssignCmtSchedService protocolAssignCmtSchedService) {
        this.protocolAssignCmtSchedService = protocolAssignCmtSchedService;
    }

    public ProtocolApproveService getProtocolApproveService() {
        return protocolApproveService;
    }

    public void setProtocolApproveService(ProtocolApproveService protocolApproveService) {
        this.protocolApproveService = protocolApproveService;
    }

    @Override
    protected ProtocolTaskBase getProtocolTaskInstanceHook(String taskName, ProtocolBase protocol) {
        ProtocolTask task = new ProtocolTask(taskName, (Protocol)protocol);
        return task;
    }

    public ProtocolActionService getProtocolActionService() {
        return protocolActionService;
    }

    public void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }

    @Override
    protected ProtocolActionsCorrespondenceBase getNewProtocolActionsCorrespondence(String protocolActionTypeCode) {
        return new ProtocolActionsCorrespondence(protocolActionTypeCode);
    }

    @Override
    protected Class<? extends ProtocolActionTypeBase> getProtocolActionTypeBOClassHook() {
        return ProtocolActionType.class;
    }

    @Override
    protected String getProtocolCreatedActionTypeHook() {
        return ProtocolActionType.PROTOCOL_CREATED;
    }

    public ProtocolSubmitActionService getProtocolSubmitActionService() {
        return protocolSubmitActionService;
    }

    public void setProtocolSubmitActionService(ProtocolSubmitActionService protocolSubmitActionService) {
        this.protocolSubmitActionService = protocolSubmitActionService;
    }

    public ProtocolAmendRenewService getProtocolAmendRenewService() {
        return protocolAmendRenewService;
    }

    public void setProtocolAmendRenewService(ProtocolAmendRenewService protocolAmendRenewService) {
        this.protocolAmendRenewService = protocolAmendRenewService;
    }

    @Override
    protected Class<? extends ProtocolBase> getProtocolBOClassHook() {
        return Protocol.class;
    }

    public ProtocolWithdrawService getProtocolWithdrawService() {
        return protocolWithdrawService;
    }

    public void setProtocolWithdrawService(ProtocolWithdrawService protocolWithdrawService) {
        this.protocolWithdrawService = protocolWithdrawService;
    }

    public ProtocolRequestService getProtocolRequestService() {
        return protocolRequestService;
    }

    public void setProtocolRequestService(ProtocolRequestService protocolRequestService) {
        this.protocolRequestService = protocolRequestService;
    }

    public ProtocolGenericActionService getProtocolGenericActionService() {
        return protocolGenericActionService;
    }

    public void setProtocolGenericActionService(ProtocolGenericActionService protocolGenericActionService) {
        this.protocolGenericActionService = protocolGenericActionService;
    }

    @Override
    protected ProtocolTaskBase getProtocolGenericActionTaskInstanceHook(String genericActionName,
            ProtocolBase protocol) {
        ProtocolTask task = new ProtocolTask(TaskName.GENERIC_PROTOCOL_ACTION, (Protocol)protocol, genericActionName);
        return task;
    }

    public ProtocolGrantExemptionService getProtocolGrantExemptionService() {
        return protocolGrantExemptionService;
    }

    public void setProtocolGrantExemptionService(ProtocolGrantExemptionService protocolGrantExemptionService) {
        this.protocolGrantExemptionService = protocolGrantExemptionService;
    }

    public CommitteeDecisionService getCommitteeDecisionService() {
        return committeeDecisionService;
    }

    public void setCommitteeDecisionService(CommitteeDecisionService committeeDecisionService) {
        this.committeeDecisionService = committeeDecisionService;
    }

    public ProtocolAbandonService getProtocolAbandonService() {
        return protocolAbandonService;
    }

    public void setProtocolAbandonService(ProtocolAbandonService protocolAbandonService) {
        this.protocolAbandonService = protocolAbandonService;
    }

    public ProtocolNotifyIrbService getProtocolNotifyIrbService() {
        return protocolNotifyIrbService;
    }

    public void setProtocolNotifyIrbService(ProtocolNotifyIrbService protocolNotifyIrbService) {
        this.protocolNotifyIrbService = protocolNotifyIrbService;
    }

    public ProtocolNotifyCommitteeService getProtocolNotifyCommitteeService() {
        return protocolNotifyCommitteeService;
    }

    public void setProtocolNotifyCommitteeService(ProtocolNotifyCommitteeService protocolNotifyCommitteeService) {
        this.protocolNotifyCommitteeService = protocolNotifyCommitteeService;
    }

    public CommitteeService getCommitteeService() {
        return committeeService;
    }

    public void setCommitteeService(CommitteeService committeeService) {
        this.committeeService = committeeService;
    }

    public ProtocolReviewNotRequiredService getProtocolReviewNotRequiredService() {
        return protocolReviewNotRequiredService;
    }

    public void setProtocolReviewNotRequiredService(ProtocolReviewNotRequiredService protocolReviewNotRequiredService) {
        this.protocolReviewNotRequiredService = protocolReviewNotRequiredService;
    }

    @Override
    protected String getProtocolRejectedInRoutingActionTypeHook() {
        return ProtocolActionType.REJECTED_IN_ROUTING;
    }

    @Override
    protected String getProtocolRecalledInRoutingActionTypeHook() {
        return ProtocolActionType.RECALLED_IN_ROUTING;
    }

    public ProtocolAssignReviewersService getProtocolAssignReviewersService() {
        return protocolAssignReviewersService;
    }

    public void setProtocolAssignReviewersService(ProtocolAssignReviewersService protocolAssignReviewersService) {
        this.protocolAssignReviewersService = protocolAssignReviewersService;
    }

    @Override
    protected ProtocolNotificationContextBase getProtocolNotificationContextHook(ProtocolNotificationRequestBeanBase notificationRequestBean, ProtocolFormBase protocolForm) {
        IRBNotificationRenderer renderer = null;
        if (StringUtils.equals(ProtocolActionType.NOTIFY_IRB, notificationRequestBean.getActionType())) {
            renderer = new NotifyIrbNotificationRenderer((Protocol) notificationRequestBean.getProtocol(), ((ProtocolForm)protocolForm).getActionHelper().getProtocolNotifyIrbBean().getComment());
        } else if (StringUtils.equals(ProtocolActionType.NOTIFIED_COMMITTEE, notificationRequestBean.getActionType())) {
            renderer = new NotifyCommitteeNotificationRenderer((Protocol) notificationRequestBean.getProtocol(), 
                                notificationRequestBean.getCommitteeName(), 
                                protocolForm.getActionHelper().getProtocolNotifyCommitteeBean().getComment(), 
                                protocolForm.getActionHelper().getProtocolNotifyCommitteeBean().getActionDate());
        } else if (StringUtils.equals(ProtocolActionType.TERMINATED, notificationRequestBean.getActionType())) {
            renderer = new ProtocolTerminatedNotificationRenderer((Protocol) notificationRequestBean.getProtocol(), protocolForm.getActionHelper().getProtocolTerminateRequestBean().getReason());
        } else if (StringUtils.equals(ProtocolActionType.EXPIRED, notificationRequestBean.getActionType())) {
            renderer = new ProtocolExpiredNotificationRenderer((Protocol) notificationRequestBean.getProtocol());
        } else if (StringUtils.equals(ProtocolActionType.DISAPPROVED, notificationRequestBean.getActionType())) {
            renderer = new ProtocolDisapprovedNotificationRenderer((Protocol) notificationRequestBean.getProtocol());
        } else if (StringUtils.equals(ProtocolActionType.SUSPENDED, notificationRequestBean.getActionType())) {
            renderer = new ProtocolSuspendedNotificationRenderer((Protocol) notificationRequestBean.getProtocol());
        } else if (StringUtils.equals(ProtocolActionType.SUSPENDED_BY_DSMB, notificationRequestBean.getActionType())) {
            renderer = new ProtocolSuspendedByDSMBNotificationRenderer((Protocol) notificationRequestBean.getProtocol());
        } else if (StringUtils.equals(ProtocolActionType.CLOSED_ADMINISTRATIVELY_CLOSED, notificationRequestBean.getActionType())) {
            renderer = new ProtocolClosedNotificationRenderer((Protocol) notificationRequestBean.getProtocol(), (ProtocolNotificationRequestBean)notificationRequestBean);
        } 
        else if (StringUtils.equals(ProtocolActionType.WITHDRAWN, notificationRequestBean.getActionType())) {
            renderer = new ProtocolWithdrawnNotificationRenderer((Protocol) notificationRequestBean.getProtocol(), 
                                                                 protocolForm.getActionHelper().getProtocolWithdrawBean().getReason(),
                                                                 protocolForm.getActionHelper().getProtocolWithdrawBean().getActionDate());
        } 
	else {
            renderer = new IRBNotificationRenderer((Protocol) notificationRequestBean.getProtocol());
        }
        IRBNotificationContext context = new IRBNotificationContext((Protocol) notificationRequestBean.getProtocol(), notificationRequestBean.getActionType(), notificationRequestBean.getDescription(), renderer);
        return context;
    }

    @Override
    protected String getNotificationEditorHook() {
        return IrbConstants.PROTOCOL_NOTIFICATION_EDITOR;
    }

    @Override
    protected ProtocolNotification getProtocolNotificationInstanceHook() {
        return new IRBProtocolNotification();
    }

    @Override
    protected Class<? extends org.kuali.kra.protocol.correspondence.ProtocolCorrespondence> getProtocolCorrespondenceBOClassHook() {
        return ProtocolCorrespondence.class;
    }

    @Override
    protected ProtocolNotificationRequestBeanBase getRequestActionNotificationBeanInstanceHook(ProtocolBase protocol,
            String protocolActionTypeCode, String description, String reason) {
        return new ProtocolNotificationRequestBean((Protocol)protocol, protocolActionTypeCode, description);
    }

    @Override
    protected ProtocolQuestionnaireAuditRuleBase getProtocolQuestionnaireAuditRuleInstanceHook() {
        return new ProtocolQuestionnaireAuditRule();
    }

    @Override
    protected ModuleQuestionnaireBean getProtocolModuleQuestionnaireBeanInstanceHook(ProtocolFormBase protocolForm,
            String actionTypeCode) {
        // Not used at this point
        return null;
    }


}
