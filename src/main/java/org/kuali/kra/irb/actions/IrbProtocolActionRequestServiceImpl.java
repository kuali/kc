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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
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
import org.kuali.kra.irb.actions.correspondence.ProtocolActionsCorrespondence;
import org.kuali.kra.irb.actions.expeditedapprove.ProtocolExpeditedApproveBean;
import org.kuali.kra.irb.actions.expeditedapprove.ProtocolExpeditedApproveEvent;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericActionBean;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericActionEvent;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericActionService;
import org.kuali.kra.irb.actions.grantexemption.ProtocolGrantExemptionBean;
import org.kuali.kra.irb.actions.grantexemption.ProtocolGrantExemptionEvent;
import org.kuali.kra.irb.actions.grantexemption.ProtocolGrantExemptionService;
import org.kuali.kra.irb.actions.notification.AssignReviewerNotificationRenderer;
import org.kuali.kra.irb.actions.notification.NotifyCommitteeNotificationRenderer;
import org.kuali.kra.irb.actions.notification.NotifyIrbNotificationRenderer;
import org.kuali.kra.irb.actions.notification.ProtocolClosedNotificationRenderer;
import org.kuali.kra.irb.actions.notification.ProtocolDisapprovedNotificationRenderer;
import org.kuali.kra.irb.actions.notification.ProtocolExpiredNotificationRenderer;
import org.kuali.kra.irb.actions.notification.ProtocolNotificationRequestBean;
import org.kuali.kra.irb.actions.notification.ProtocolSuspendedByDSMBNotificationRenderer;
import org.kuali.kra.irb.actions.notification.ProtocolSuspendedNotificationRenderer;
import org.kuali.kra.irb.actions.notification.ProtocolTerminatedNotificationRenderer;
import org.kuali.kra.irb.actions.request.ProtocolRequestBean;
import org.kuali.kra.irb.actions.request.ProtocolRequestEvent;
import org.kuali.kra.irb.actions.request.ProtocolRequestRule;
import org.kuali.kra.irb.actions.request.ProtocolRequestService;
import org.kuali.kra.irb.actions.submit.ProtocolReviewerBean;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitAction;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitActionService;
import org.kuali.kra.irb.actions.withdraw.ProtocolWithdrawService;
import org.kuali.kra.irb.auth.GenericProtocolAuthorizer;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondence;
import org.kuali.kra.irb.notification.IRBNotificationContext;
import org.kuali.kra.irb.notification.IRBNotificationRenderer;
import org.kuali.kra.irb.notification.IRBProtocolNotification;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.irb.questionnaire.ProtocolQuestionnaireAuditRule;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionRequestServiceImpl;
import org.kuali.kra.protocol.actions.ProtocolActionTypeBase;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionsCorrespondenceBase;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;
import org.kuali.rice.kns.document.authorization.DocumentAuthorizerBase;
import org.kuali.rice.kns.util.WebUtils;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.util.CollectionUtils;

public class IrbProtocolActionRequestServiceImpl extends ProtocolActionRequestServiceImpl implements IrbProtocolActionRequestService {
    private ProtocolAssignToAgendaService protocolAssignToAgendaService;
    private ProtocolAssignCmtSchedService protocolAssignCmtSchedService;
    private ProtocolApproveService protocolApproveService;
    private ProtocolSubmitActionService protocolSubmitActionService;
    private ProtocolAmendRenewService protocolAmendRenewService;
    private ProtocolWithdrawService protocolWithdrawService;
    private ProtocolRequestService protocolRequestService;
    private ProtocolGenericActionService protocolGenericActionService;
    private ProtocolGrantExemptionService protocolGrantExemptionService;
    
    private static final String ACTION_NAME_RENEWAL_WITH_AMENDMENT = "Create Renewal with Amendment";
    private static final String ACTION_NAME_AMENDMENT = "Create Amendment";
    private static final String ACTION_NAME_RENEWAL_WITHOUT_AMENDMENT = "Create Renewal without Amendment";
    private static final String ACTION_NAME_WITHDRAW = "Withdraw";
    private static final String ACTION_NAME_RESPONSE_APPROVAL = "Response Approval";
    private static final String ACTION_NAME_CLOSE_ENROLLMENT = "Close Enrollment";
    private static final String ACTION_NAME_DEFER = "Defer";
    private static final String ACTION_NAME_DISAPPROVE = "Disapprove";
    private static final String ACTION_NAME_EXPIRE = "Expire";
    private static final String ACTION_NAME_GRANT_EXEMPTION = "Grant Exemption";
    
    
    private static final String FORWARD_TO_CORRESPONDENCE = "correspondence";
    private static final String FORWARD_TO_NOTIFICATION_EDITOR = "protocolNotificationEditor";
    private static final String FORWARD_TO_HOLDING_PAGE = "holdingPage";
    
    /**
     * @see org.kuali.kra.irb.actions.IrbProtocolActionRequestService#isExpeditedApprovalAuthorized(org.kuali.kra.irb.ProtocolForm)
     */
    public boolean isExpeditedApprovalAuthorized(ProtocolForm protocolForm) {
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        ProtocolExpeditedApproveBean expeditedActionBean = (ProtocolExpeditedApproveBean) ((ActionHelper) protocolForm.getActionHelper()).getProtocolExpeditedApprovalBean();
        boolean requestAuthorized = false;
        if (hasPermission(TaskName.EXPEDITE_APPROVAL, (Protocol) document.getProtocol())) {
            requestAuthorized = applyRules(new ProtocolExpeditedApproveEvent(document, expeditedActionBean));
        }
        return requestAuthorized;
    }

    /**
     * @see org.kuali.kra.irb.actions.IrbProtocolActionRequestService#isFullApprovalAuthorized(org.kuali.kra.irb.ProtocolForm)
     */
    public boolean isFullApprovalAuthorized(ProtocolForm protocolForm) {
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        ProtocolApproveBean protocolApproveBean = (ProtocolApproveBean) protocolForm.getActionHelper().getProtocolFullApprovalBean();
        boolean requestAuthorized = false;
        if (hasPermission(TaskName.APPROVE_PROTOCOL, (Protocol) document.getProtocol())) {
            requestAuthorized = applyRules(new ProtocolApproveEvent(document, protocolApproveBean));
        }
        return requestAuthorized;
    }
    
    /**
     * @see org.kuali.kra.irb.actions.IrbProtocolActionRequestService#isCreateRenewalAuthorized(org.kuali.kra.irb.ProtocolForm)
     */
    public boolean isCreateRenewalAuthorized(ProtocolForm protocolForm) {
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        boolean requestAuthorized = false;
        String renewalSummary = protocolForm.getActionHelper().getRenewalSummary();
        if (hasPermission(TaskName.CREATE_PROTOCOL_RENEWAL, (Protocol) document.getProtocol())) {
            requestAuthorized = applyRules(new CreateRenewalEvent(document, Constants.PROTOCOL_CREATE_RENEWAL_SUMMARY_KEY, renewalSummary));
        }
        return requestAuthorized;
    }

    /**
     * @see org.kuali.kra.irb.actions.IrbProtocolActionRequestService#isAssignToAgendaAuthorized(org.kuali.kra.irb.ProtocolForm)
     */
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
    
    /**
     * @see org.kuali.kra.irb.actions.IrbProtocolActionRequestService#isCreateAmendmentAuthorized(org.kuali.kra.irb.ProtocolForm)
     */
    public boolean isCreateAmendmentAuthorized(ProtocolForm protocolForm) {
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        ProtocolAmendmentBean protocolAmendmentBean = (ProtocolAmendmentBean) protocolForm.getActionHelper().getProtocolAmendmentBean();
        boolean requestAuthorized = false;
        if (hasPermission(TaskName.CREATE_PROTOCOL_AMMENDMENT, (Protocol) document.getProtocol())) {
            requestAuthorized = applyRules(new CreateAmendmentEvent(document, Constants.PROTOCOL_CREATE_AMENDMENT_KEY, protocolAmendmentBean));
        }
        return requestAuthorized;
    }

    /**
     * @see org.kuali.kra.irb.actions.IrbProtocolActionRequestService#isCreateRenewalWithAmendmentAuthorized(org.kuali.kra.irb.ProtocolForm)
     */
    public boolean isCreateRenewalWithAmendmentAuthorized(ProtocolForm protocolForm) {
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        ProtocolAmendmentBean protocolAmendmentBean = (ProtocolAmendmentBean) protocolForm.getActionHelper().getProtocolRenewAmendmentBean();
        boolean requestAuthorized = false;
        if (hasPermission(TaskName.CREATE_PROTOCOL_RENEWAL, (Protocol) document.getProtocol())) {
            requestAuthorized = applyRules(new CreateAmendmentEvent(document, Constants.PROTOCOL_CREATE_RENEWAL_WITH_AMENDMENT_KEY, protocolAmendmentBean));
        }
        return requestAuthorized;
    }
    
    /**
     * @see org.kuali.kra.irb.actions.IrbProtocolActionRequestService#isWithdrawProtocolAuthorized(org.kuali.kra.irb.ProtocolForm)
     */
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
    
    /**
     * @see org.kuali.kra.irb.actions.IrbProtocolActionRequestService#isResponseApprovalAuthorized(org.kuali.kra.irb.ProtocolForm)
     */
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
    
    /**
     * @see org.kuali.kra.irb.actions.IrbProtocolActionRequestService#isCloseProtocolAuthorized(org.kuali.kra.irb.ProtocolForm)
     */
    public boolean isCloseProtocolAuthorized(ProtocolForm protocolForm) {
        ProtocolGenericActionBean actionBean = (ProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolCloseBean();
        return isGenericProtocolActionAuthorized(GenericProtocolAuthorizer.CLOSE_PROTOCOL, protocolForm, actionBean);
    }
    
    /**
     * @see org.kuali.kra.irb.actions.IrbProtocolActionRequestService#isCloseEnrollmentAuthorized(org.kuali.kra.irb.ProtocolForm)
     */
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

    /**
     * @see org.kuali.kra.irb.actions.IrbProtocolActionRequestService#isDeferProtocolAuthorized(org.kuali.kra.irb.ProtocolForm)
     */
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
    
    /**
     * @see org.kuali.kra.irb.actions.IrbProtocolActionRequestService#isDisapproveProtocolAuthorized(org.kuali.kra.irb.ProtocolForm)
     */
    public boolean isDisapproveProtocolAuthorized(ProtocolForm protocolForm) {
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        ProtocolGenericActionBean actionBean = (ProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolDisapproveBean();
        boolean requestAuthorized = false;
        if (hasPermission(TaskName.DISAPPROVE_PROTOCOL, (Protocol) document.getProtocol())) {
            requestAuthorized = applyRules(new ProtocolGenericActionEvent(document, actionBean));
        }
        return requestAuthorized;
    }
    
    /**
     * @see org.kuali.kra.irb.actions.IrbProtocolActionRequestService#isExpireProtocolAuthorized(org.kuali.kra.irb.ProtocolForm)
     */
    public boolean isExpireProtocolAuthorized(ProtocolForm protocolForm) {
        ProtocolGenericActionBean actionBean = (ProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolExpireBean();
        return isGenericProtocolActionAuthorized(GenericProtocolAuthorizer.EXPIRE_PROTOCOL, protocolForm, actionBean);
    }
    
    /**
     * @see org.kuali.kra.irb.actions.IrbProtocolActionRequestService#isGrantExemptionAuthorized(org.kuali.kra.irb.ProtocolForm)
     */
    public boolean isGrantExemptionAuthorized(ProtocolForm protocolForm) {
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        ProtocolGrantExemptionBean actionBean = ((ActionHelper) protocolForm.getActionHelper()).getProtocolGrantExemptionBean();
        boolean requestAuthorized = false;
        if (hasPermission(TaskName.GRANT_EXEMPTION, (Protocol) document.getProtocol())) {
            requestAuthorized = applyRules(new ProtocolGrantExemptionEvent(document, actionBean));
        }
        return requestAuthorized;
    }
    
    /**
     * @see org.kuali.kra.irb.actions.IrbProtocolActionRequestService#grantExpeditedApproval(org.kuali.kra.irb.ProtocolForm)
     */
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
        getProtocolApproveService().grantExpeditedApproval(protocolForm.getProtocolDocument().getProtocol(), expeditedActionBean);
        generateActionCorrespondence(ProtocolActionType.EXPEDITE_APPROVAL, protocolForm.getProtocolDocument().getProtocol());
        saveReviewComments(protocolForm, expeditedActionBean.getReviewCommentsBean());
        recordProtocolActionSuccess("Expedited Approval");
        protocolForm.getTabStates().put(":" + WebUtils.generateTabKey("Assign to Agenda"), "OPEN");
        
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(), ProtocolActionType.EXPEDITE_APPROVAL, "Expedited Approval Granted");
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, Constants.IRB_PROTOCOL_TAB, notificationBean, false));
    }
    
    /**
     * @see org.kuali.kra.irb.actions.IrbProtocolActionRequestService#grantFullApproval(org.kuali.kra.irb.ProtocolForm)
     */
    public void grantFullApproval(ProtocolForm protocolForm) throws Exception {
        ProtocolApproveBean protocolApproveBean = (ProtocolApproveBean) protocolForm.getActionHelper().getProtocolFullApprovalBean();
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        getProtocolApproveService().grantFullApproval((Protocol) document.getProtocol(), protocolApproveBean);
        generateActionCorrespondence(ProtocolActionType.APPROVED, protocolForm.getProtocolDocument().getProtocol());
        saveReviewComments(protocolForm, protocolApproveBean.getReviewCommentsBean());
        recordProtocolActionSuccess("Full Approval");
        // issue : protocolcorrespondence is reset after loading correspondence ? more work
        // somehow docforkey is not in session for this case ?
        // hack this for now
        protocolForm.getProtocolHelper().prepareView();
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(), ProtocolActionType.APPROVED, "Approved");
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, Constants.IRB_PROTOCOL_TAB, notificationBean, false));
        if (protocolForm.getActionHelper().getProtocolCorrespondence() != null) {
            // TODO : this is hack
            // may need to add it back when save/close corr ?
            GlobalVariables.getUserSession().addObject("approvalComplCorrespondence",GlobalVariables.getUserSession().retrieveObject(DocumentAuthorizerBase.USER_SESSION_METHOD_TO_CALL_COMPLETE_OBJECT_KEY));
            // temporarily remove this key which is generated by super.approve
            GlobalVariables.getUserSession().removeObject(DocumentAuthorizerBase.USER_SESSION_METHOD_TO_CALL_COMPLETE_OBJECT_KEY);
        } else {
            IRBNotificationRenderer renderer = new IRBNotificationRenderer(document.getProtocol());
            IRBNotificationContext context = new IRBNotificationContext(document.getProtocol(), ProtocolActionType.APPROVED, "Approved", renderer);
            getNotificationService().sendNotificationAndPersist(context, new IRBProtocolNotification(), document.getProtocol());                     
        }
    }
    
    /**
     * @see org.kuali.kra.irb.actions.IrbProtocolActionRequestService#submitForReviewAndNotifyUser(org.kuali.kra.irb.ProtocolForm)
     */
    public boolean submitForReviewAndPromptToNotifyUser(ProtocolForm protocolForm) throws Exception {
        ProtocolDocument protocolDocument = (ProtocolDocument) protocolForm.getProtocolDocument();
        Protocol protocol = (Protocol) protocolDocument.getProtocol();
        ProtocolSubmitAction submitAction = (ProtocolSubmitAction) protocolForm.getActionHelper().getProtocolSubmitAction();
        
        getProtocolSubmitActionService().submitToIrbForReview(protocol, submitAction);
        protocolForm.getActionHelper().getAssignCmtSchedBean().init();
        generateActionCorrespondence(ProtocolActionType.SUBMIT_TO_IRB, protocolForm.getProtocolDocument().getProtocol());
        
        AssignReviewerNotificationRenderer renderer1 = new AssignReviewerNotificationRenderer((Protocol) protocolForm.getProtocolDocument().getProtocol(), "added");
        List<ProtocolNotificationRequestBean> addReviewerNotificationBeans = getNotificationRequestBeans((List) submitAction.getReviewers(),ProtocolReviewerBean.CREATE);
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
    
    /**
     * @see org.kuali.kra.irb.actions.IrbProtocolActionRequestService#createRenewalAndPromptToNotifyUser(org.kuali.kra.irb.ProtocolForm, java.lang.String)
     */
    public String createRenewal(ProtocolForm protocolForm) throws Exception {
        String newDocId = getProtocolAmendRenewService().createRenewal(protocolForm.getProtocolDocument(), protocolForm.getActionHelper().getRenewalSummary());
        generateActionCorrespondence(ProtocolActionType.RENEWAL_CREATED, protocolForm.getProtocolDocument().getProtocol());
        refreshAfterProtocolAction(protocolForm, newDocId, ACTION_NAME_RENEWAL_WITHOUT_AMENDMENT, true);
        // Form fields copy needed to support modifyAmendmentSections
        protocolForm.getActionHelper().getProtocolAmendmentBean().setSummary(protocolForm.getActionHelper().getRenewalSummary());
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(), ProtocolActionType.RENEWAL_CREATED_NOTIFICATION, "Renewal Created");
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, Constants.IRB_PROTOCOL_TAB, notificationBean, false));
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, Constants.IRB_PROTOCOL_TAB);
    }
    
    /**
     * @see org.kuali.kra.irb.actions.IrbProtocolActionRequestService#assignToAgenda(org.kuali.kra.irb.ProtocolForm)
     */
    public String assignToAgenda(ProtocolForm protocolForm) throws Exception {
        ProtocolAssignToAgendaBean actionBean = (ProtocolAssignToAgendaBean) protocolForm.getActionHelper().getAssignToAgendaBean();
        getProtocolAssignToAgendaService().assignToAgenda(protocolForm.getProtocolDocument().getProtocol(), actionBean);
        generateActionCorrespondence(ProtocolActionType.ASSIGN_TO_AGENDA, protocolForm.getProtocolDocument().getProtocol());
        saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
        recordProtocolActionSuccess("Assign to Agenda");
        
        Protocol protocol = (Protocol) protocolForm.getProtocolDocument().getProtocol();
        org.kuali.kra.irb.actions.ProtocolAction lastAction = (org.kuali.kra.irb.actions.ProtocolAction) protocolForm.getProtocolDocument().getProtocol().getLastProtocolAction();
        ProtocolActionType lastActionType = (ProtocolActionType) lastAction.getProtocolActionType();
        String description = lastActionType.getDescription();
        
        IRBNotificationRenderer renderer = new IRBNotificationRenderer(protocol);
        IRBNotificationContext context = new IRBNotificationContext(protocol, ProtocolActionType.ASSIGN_TO_AGENDA, description, renderer);
        
        if (protocolForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
            protocolForm.getNotificationHelper().initializeDefaultValues(context);
            return FORWARD_TO_NOTIFICATION_EDITOR;
        } else {
            getNotificationService().sendNotificationAndPersist(context, new IRBProtocolNotification(), protocol);
            return Constants.MAPPING_BASIC;
        }
    }
    
    /**
     * @see org.kuali.kra.irb.actions.IrbProtocolActionRequestService#createAmendment(org.kuali.kra.irb.ProtocolForm)
     */
    public String createAmendment(ProtocolForm protocolForm) throws Exception {
        String newDocId = getProtocolAmendRenewService().createAmendment(protocolForm.getProtocolDocument(),
                protocolForm.getActionHelper().getProtocolAmendmentBean());
        generateActionCorrespondence(ProtocolActionType.AMENDMENT_CREATED, protocolForm.getProtocolDocument().getProtocol());
        refreshAfterProtocolAction(protocolForm, newDocId, ACTION_NAME_AMENDMENT, true);
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(), ProtocolActionType.AMENDMENT_CREATED_NOTIFICATION, "Amendment Created");
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, Constants.IRB_PROTOCOL_TAB, notificationBean, false));
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, Constants.IRB_PROTOCOL_TAB);
    }
    
    /**
     * @see org.kuali.kra.irb.actions.IrbProtocolActionRequestService#createRenewalWithAmendment(org.kuali.kra.irb.ProtocolForm)
     */
    public String createRenewalWithAmendment(ProtocolForm protocolForm) throws Exception {
        String newDocId = getProtocolAmendRenewService().createRenewalWithAmendment(protocolForm.getProtocolDocument(),
                protocolForm.getActionHelper().getProtocolRenewAmendmentBean());
        generateActionCorrespondence(ProtocolActionType.RENEWAL_WITH_AMENDMENT_CREATED, protocolForm.getProtocolDocument().getProtocol());
        refreshAfterProtocolAction(protocolForm, newDocId, ACTION_NAME_RENEWAL_WITH_AMENDMENT, true);
        // Form fields copy needed to support modifyAmendmentSections
        protocolForm.getActionHelper().setProtocolAmendmentBean(protocolForm.getActionHelper().getProtocolRenewAmendmentBean());
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(), ProtocolActionType.RENEWAL_WITH_AMENDMENT_CREATED, "Renewal With Amendment Created");
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, Constants.IRB_PROTOCOL_TAB, notificationBean, false));
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, Constants.IRB_PROTOCOL_TAB);
    }
    
    /**
     * @see org.kuali.kra.irb.actions.IrbProtocolActionRequestService#withdrawProtocol(org.kuali.kra.irb.ProtocolForm)
     */
    public String withdrawProtocol(ProtocolForm protocolForm) throws Exception {
        ProtocolDocument pd = (ProtocolDocument) getProtocolWithdrawService().withdraw(protocolForm.getProtocolDocument().getProtocol(),
                protocolForm.getActionHelper().getProtocolWithdrawBean());
        generateActionCorrespondence(ProtocolActionType.WITHDRAWN, protocolForm.getProtocolDocument().getProtocol());
        refreshAfterProtocolAction(protocolForm, pd.getDocumentNumber(), ACTION_NAME_WITHDRAW, false);
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(), ProtocolActionType.WITHDRAWN, "Withdrawn");
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, Constants.IRB_PROTOCOL_TAB, notificationBean, false));
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, Constants.IRB_PROTOCOL_TAB);
    }
    
    /**
     * @see org.kuali.kra.irb.actions.IrbProtocolActionRequestService#grantResponseApproval(org.kuali.kra.irb.ProtocolForm)
     */
    public String grantResponseApproval(ProtocolForm protocolForm) throws Exception {
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        ProtocolApproveBean actionBean = ((ActionHelper) protocolForm.getActionHelper()).getProtocolResponseApprovalBean();
        getProtocolApproveService().grantResponseApproval(document.getProtocol(), actionBean);
        generateActionCorrespondence(ProtocolActionType.RESPONSE_APPROVAL, protocolForm.getProtocolDocument().getProtocol());
        saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
        recordProtocolActionSuccess(ACTION_NAME_RESPONSE_APPROVAL);
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(document.getProtocol(), ProtocolActionType.RESPONSE_APPROVAL, "Response Approval");
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, Constants.IRB_PROTOCOL_TAB, notificationBean, false));
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, Constants.IRB_PROTOCOL_TAB);
    }
    
    /**
     * @see org.kuali.kra.irb.actions.IrbProtocolActionRequestService#performRequestAction(org.kuali.kra.irb.ProtocolForm, java.lang.String)
     */
    public String performRequestAction(ProtocolForm protocolForm, String taskName) throws Exception {
        ProtocolDocument document = protocolForm.getProtocolDocument();
        protocolForm.getActionHelper().preSaveSubmissionQuestionnaires();
        ProtocolRequestAction requestAction = ProtocolRequestAction.valueOfTaskName(taskName);
        ProtocolRequestBean requestBean = getProtocolRequestBean(protocolForm, taskName);
        if (requestBean != null) {
            boolean valid = applyRules(new ProtocolRequestEvent<ProtocolRequestRule>(document, requestAction.getErrorPath(), requestBean));
            valid &= isMandatoryQuestionnaireComplete(requestBean, "actionHelper." + requestAction.getBeanName() + ".datavalidation");
            if (valid) {
                getProtocolRequestService().submitRequest(protocolForm.getProtocolDocument().getProtocol(), requestBean);            
                generateActionCorrespondence(requestBean.getProtocolActionTypeCode(), protocolForm.getProtocolDocument().getProtocol());
                recordProtocolActionSuccess(requestAction.getActionName());
                return sendRequestNotification(protocolForm, requestBean);
            }
        }
        return Constants.MAPPING_BASIC;
    }
    
    /**
     * @see org.kuali.kra.irb.actions.IrbProtocolActionRequestService#closeProtocol(org.kuali.kra.irb.ProtocolForm)
     */
    public String closeProtocol(ProtocolForm protocolForm) throws Exception {
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGenericActionBean actionBean = (ProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolCloseBean();
        getProtocolGenericActionService().close(protocol, actionBean);
        generateActionCorrespondence(ProtocolActionType.CLOSED_ADMINISTRATIVELY_CLOSED, protocolForm.getProtocolDocument().getProtocol());
        saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
        recordProtocolActionSuccess("Close");
        ProtocolNotificationRequestBean notificationBean = null;
        if (ProtocolStatus.CLOSED_ADMINISTRATIVELY.equals(protocol.getProtocolStatus())) {
            notificationBean = new ProtocolNotificationRequestBean(protocol, ProtocolActionType.CLOSED_ADMINISTRATIVELY_CLOSED, "Closed By Administrator");
        } else {
            notificationBean = new ProtocolNotificationRequestBean(protocol, ProtocolActionType.CLOSED_ADMINISTRATIVELY_CLOSED, "Closed By Investigator");
        }
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, Constants.IRB_PROTOCOL_TAB, notificationBean, false));
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, Constants.IRB_PROTOCOL_TAB);
    }
    
    /**
     * @see org.kuali.kra.irb.actions.IrbProtocolActionRequestService#closeEnrollment(org.kuali.kra.irb.ProtocolForm)
     */
    public String closeEnrollment(ProtocolForm protocolForm) throws Exception {
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolCloseEnrollmentBean();
        getProtocolGenericActionService().closeEnrollment(protocol, actionBean);
        generateActionCorrespondence(ProtocolActionType.CLOSED_FOR_ENROLLMENT, protocolForm.getProtocolDocument().getProtocol());
        saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
        recordProtocolActionSuccess(ACTION_NAME_CLOSE_ENROLLMENT);
        return Constants.MAPPING_BASIC;
    }
    
    /**
     * @see org.kuali.kra.irb.actions.IrbProtocolActionRequestService#deferProtocol(org.kuali.kra.irb.ProtocolForm)
     */
    public String deferProtocol(ProtocolForm protocolForm) throws Exception {
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolDeferBean();
        ProtocolDocument newDocument = getProtocolGenericActionService().defer(protocol, actionBean);
        saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
        generateActionCorrespondence(ProtocolActionType.DEFERRED, protocolForm.getProtocolDocument().getProtocol());
        refreshAfterProtocolAction(protocolForm, newDocument.getDocumentNumber(), ACTION_NAME_DEFER, false);
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(),ProtocolActionType.DEFERRED, "Deferred");
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, Constants.IRB_PROTOCOL_TAB, notificationBean, false));
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, Constants.IRB_PROTOCOL_TAB);
    }

    /**
     * @see org.kuali.kra.irb.actions.IrbProtocolActionRequestService#disapproveProtocol(org.kuali.kra.irb.ProtocolForm)
     */
    public String disapproveProtocol(ProtocolForm protocolForm) throws Exception {
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGenericActionBean actionBean = (ProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolDisapproveBean();
        getProtocolGenericActionService().disapprove(protocol, actionBean);
        generateActionCorrespondence(ProtocolActionType.DISAPPROVED, protocolForm.getProtocolDocument().getProtocol());
        saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
        recordProtocolActionSuccess(ACTION_NAME_DISAPPROVE);
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(), ProtocolActionType.DISAPPROVED, "Disapproved");
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, Constants.IRB_PROTOCOL_TAB, notificationBean, false));
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, Constants.IRB_PROTOCOL_TAB);
    }
    
    /**
     * @see org.kuali.kra.irb.actions.IrbProtocolActionRequestService#expireProtocol(org.kuali.kra.irb.ProtocolForm)
     */
    public String expireProtocol(ProtocolForm protocolForm) throws Exception {
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGenericActionBean actionBean = (ProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolExpireBean();
        getProtocolGenericActionService().expire(protocol, actionBean);
        generateActionCorrespondence(ProtocolActionType.EXPIRED, protocolForm.getProtocolDocument().getProtocol());
        saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
        recordProtocolActionSuccess(ACTION_NAME_EXPIRE);
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(), ProtocolActionType.EXPIRED, "Expired");
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, Constants.IRB_PROTOCOL_TAB, notificationBean, false));
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, Constants.IRB_PROTOCOL_TAB);
    }
    
    /**
     * @see org.kuali.kra.irb.actions.IrbProtocolActionRequestService#grantExemption(org.kuali.kra.irb.ProtocolForm)
     */
    public String grantExemption(ProtocolForm protocolForm) throws Exception {
        ProtocolDocument document = (ProtocolDocument) protocolForm.getProtocolDocument();
        Protocol protocol = (Protocol) document.getProtocol();
        ProtocolGrantExemptionBean actionBean = ((ActionHelper) protocolForm.getActionHelper()).getProtocolGrantExemptionBean();
        getProtocolGrantExemptionService().grantExemption(protocol, actionBean);
        generateActionCorrespondence(ProtocolActionType.GRANT_EXEMPTION, protocolForm.getProtocolDocument().getProtocol());
        saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());                
        recordProtocolActionSuccess(ACTION_NAME_GRANT_EXEMPTION);
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(), ProtocolActionType.GRANT_EXEMPTION, "Exemption Granted");
        protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, Constants.IRB_PROTOCOL_TAB, notificationBean, false));
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, Constants.IRB_PROTOCOL_TAB);
    }
    
    private String sendRequestNotification(ProtocolForm protocolForm, ProtocolRequestBean requestBean) throws Exception {
        ProtocolActionType protocolActionType = getBusinessObjectService().findBySinglePrimaryKey(ProtocolActionType.class, requestBean.getProtocolActionTypeCode());
        String protocolActionTypeCode = protocolActionType.getProtocolActionTypeCode();
        String description = protocolActionType.getDescription();
        ProtocolNotificationRequestBean notificationBean = new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(), protocolActionTypeCode, description);
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, Constants.IRB_PROTOCOL_ACTIONS_TAB);
    }
    
    /**
     * This method is to check if the mandatory submission questionnaire is complete 
     * before submit a request/notify irb action
     * @param submissionBean
     * @param errorKey
     * @return
     */
    private boolean isMandatoryQuestionnaireComplete(ProtocolRequestBean submissionBean, String errorKey) {
        boolean valid = true;
        ProtocolQuestionnaireAuditRule auditRule = new ProtocolQuestionnaireAuditRule();
        if (!auditRule.isMandatorySubmissionQuestionnaireComplete(submissionBean.getQuestionnaireHelper().getAnswerHeaders())) {
            GlobalVariables.getMessageMap().clearErrorMessages();
            GlobalVariables.getMessageMap().putError(errorKey, KeyConstants.ERROR_MANDATORY_QUESTIONNAIRE);
            valid = false;
        }
        return valid;
    }
    
    private ProtocolRequestBean getProtocolRequestBean(ProtocolForm protocolForm, String taskName) {
        ProtocolRequestBean protocolRequestBean = null;
        ProtocolActionBean protocolActionBean = getActionBean(protocolForm, taskName);
        if (protocolActionBean != null && protocolActionBean instanceof ProtocolRequestBean) {
            protocolRequestBean = (ProtocolRequestBean) protocolActionBean;
        }
        return protocolRequestBean;
    }
    
    private ProtocolActionBean getActionBean(ProtocolForm protocolForm, String taskName) {
        ProtocolActionBean protocolActionBean = null;
        if (StringUtils.isNotBlank(taskName)) {
            protocolActionBean = (ProtocolActionBean) protocolForm.getActionHelper().getActionBean(taskName);
        }
        return protocolActionBean;
    }
    
    /**
     * This method is to get the path where user is send to performing protocol action
     * @param protocolForm
     * @param notificationBean
     * @return
     */
    private String getRedirectPathAfterProtocolAction(ProtocolForm protocolForm, ProtocolNotificationRequestBean notificationBean, String promptAfterNotification) {
        if (protocolForm.getActionHelper().getProtocolCorrespondence() != null) {
            return FORWARD_TO_CORRESPONDENCE;
        } else {
            boolean sendNotification = checkToSendNotification(protocolForm, notificationBean, promptAfterNotification); 
            return sendNotification ? FORWARD_TO_NOTIFICATION_EDITOR : promptAfterNotification;
        }
    }
    
    /**
     * This method is to set the parameters required for the new document after performing
     * an action
     * @param protocolForm
     * @param newDocId
     * @param protocolActionName
     */
    private void refreshAfterProtocolAction(ProtocolForm protocolForm, String newDocId, String protocolActionName, boolean resetCurrentSubmission) {
        protocolForm.setDocId(newDocId);
        if(resetCurrentSubmission) {
            protocolForm.getActionHelper().setCurrentSubmissionNumber(-1);
        }
        //protocolForm.getProtocolHelper().prepareView();
        recordProtocolActionSuccess(protocolActionName);
    }
    
    private ProtocolCorrespondence getProtocolCorrespondence (ProtocolForm protocolForm, String forwardName, ProtocolNotificationRequestBean notificationRequestBean, boolean holdingPage) {
        Map<String,Object> keyValues = new HashMap<String, Object>();
        // actionid <-> action.actionid  actionidfk<->action.protocolactionid
        keyValues.put("actionIdFk", protocolForm.getProtocolDocument().getProtocol().getLastProtocolAction().getProtocolActionId());
        List<ProtocolCorrespondence> correspondences = (List<ProtocolCorrespondence>)getBusinessObjectService().findMatching(ProtocolCorrespondence.class, keyValues);
        if (correspondences.isEmpty()) {
            return null;
        } else {
            ProtocolCorrespondence correspondence = correspondences.get(0);
            correspondence.setForwardName(forwardName);
            correspondence.setNotificationRequestBean(notificationRequestBean);
            correspondence.setHoldingPage(holdingPage);
            return correspondence;
        }
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
    
    private boolean checkToSendNotification(ProtocolForm protocolForm, ProtocolNotificationRequestBean notificationRequestBean, String promptAfterNotification ) {
        
        IRBNotificationRenderer renderer = null;
        if (StringUtils.equals(ProtocolActionType.NOTIFY_IRB, notificationRequestBean.getActionType())) {
            renderer = new NotifyIrbNotificationRenderer((Protocol) notificationRequestBean.getProtocol(), protocolForm.getActionHelper().getProtocolNotifyIrbBean().getComment());
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
            renderer = new ProtocolClosedNotificationRenderer((Protocol) notificationRequestBean.getProtocol(), notificationRequestBean);
        } else {
            renderer = new IRBNotificationRenderer((Protocol) notificationRequestBean.getProtocol());
        }

        IRBNotificationContext context = new IRBNotificationContext((Protocol) notificationRequestBean.getProtocol(), notificationRequestBean.getActionType(), notificationRequestBean.getDescription(), renderer);
        
        if (protocolForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
            context.setForwardName(promptAfterNotification);
            protocolForm.getNotificationHelper().initializeDefaultValues(context);
            return true;
        } else {
            getNotificationService().sendNotificationAndPersist(context, new IRBProtocolNotification(), protocolForm.getProtocolDocument().getProtocol());
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
    protected ProtocolTaskBase getProtocolGenericActionTaskInstanceHook(String taskName, String genericActionName,
            ProtocolBase protocol) {
        ProtocolTask task = new ProtocolTask(taskName, (Protocol)protocol, genericActionName);
        return task;
    }

    public ProtocolGrantExemptionService getProtocolGrantExemptionService() {
        return protocolGrantExemptionService;
    }

    public void setProtocolGrantExemptionService(ProtocolGrantExemptionService protocolGrantExemptionService) {
        this.protocolGrantExemptionService = protocolGrantExemptionService;
    }


}
