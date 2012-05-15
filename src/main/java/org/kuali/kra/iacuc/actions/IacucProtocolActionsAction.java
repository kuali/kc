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

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.authorization.ApplicationTask;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolAction;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.actions.copy.IacucProtocolCopyService;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmitAction;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmitActionEvent;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmitActionService;
import org.kuali.kra.iacuc.actions.withdraw.IacucProtocolWithdrawBean;
import org.kuali.kra.iacuc.actions.withdraw.IacucProtocolWithdrawService;
import org.kuali.kra.iacuc.auth.IacucProtocolTask;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.actions.notification.ProtocolNotificationRequestBean;
import org.kuali.kra.protocol.ProtocolForm;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

public class IacucProtocolActionsAction extends IacucProtocolAction {

    private static final String PROTOCOL_TAB = "iacucProtocol";
    private static final String PROTOCOL_ACTIONS_TAB = "iacucProtocolActions";
    private static final String CORRESPONDENCE = "correspondence";

    /**
     * Submit a protocol for review.
     * 
     * @param mapping the mapping associated with this action.
     * @param form the Protocol form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the name of the HTML page to display
     * @throws Exception
     */
    public ActionForward submitForReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);

        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolDocument protocolDocument = (IacucProtocolDocument)protocolForm.getProtocolDocument();
        protocolForm.setAuditActivated(true);
        IacucProtocolTask task = new IacucProtocolTask(TaskName.SUBMIT_IACUC_PROTOCOL, protocolDocument.getIacucProtocol());
        if (isAuthorized(task)) {
            IacucProtocolSubmitAction submitAction = (IacucProtocolSubmitAction) protocolForm.getActionHelper().getProtocolSubmitAction();            
            if (applyRules(new IacucProtocolSubmitActionEvent(protocolDocument, submitAction))) {
                AuditActionHelper auditActionHelper = new AuditActionHelper();
                if (auditActionHelper.auditUnconditionally(protocolDocument)) {
//TODO: Must implement for IACUC
//                    if (isCommitteeMeetingAssignedMaxProtocols(submitAction.getNewCommitteeId(), submitAction.getNewScheduleId())) {
//                        forward = confirm(buildSubmitForReviewConfirmationQuestion(mapping, form, request, response), CONFIRM_SUBMIT_FOR_REVIEW_KEY, "");
//                    } else {
                        forward = submitForReviewAndRedirect(mapping, form, request, response);
//                    }
                } else {
                    GlobalVariables.getMessageMap().clearErrorMessages();
                    GlobalVariables.getMessageMap().putError("datavalidation", KeyConstants.ERROR_WORKFLOW_SUBMISSION,  new String[] {});
                }
            }
        }

        return forward;
    }
    
    /**
     * Submits the Protocol for review and calculates the redirect back to the portal page, adding in the proper parameters for displaying a message to the
     * user upon successful submission.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The Protocol form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the destination
     * @throws Exception
     */
    private ActionForward submitForReviewAndRedirect(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolDocument protocolDocument = protocolForm.getIacucProtocolDocument();
        IacucProtocol protocol = protocolDocument.getIacucProtocol();
        IacucProtocolSubmitAction submitAction = (IacucProtocolSubmitAction) protocolForm.getActionHelper().getProtocolSubmitAction();
        
        getProtocolSubmitActionService().submitToIacucForReview(protocol, submitAction);
//TODO for IACUC        protocolForm.getActionHelper().getAssignCmtSchedBean().init();
        
        super.route(mapping, protocolForm, request, response);
//TODO for IACUC
//        IACUCNotificationRenderer submitRenderer = new IACUCNotificationRenderer(protocol);
//        IACUCNotificationContext submitContext = new IACUCNotificationContext(protocol, null, 
//                                                    ProtocolActionType.SUBMIT_TO_IRB_NOTIFICATION, "Submit", submitRenderer);
//        getNotificationService().sendNotification(submitContext);
//        AssignReviewerNotificationRenderer renderer = new AssignReviewerNotificationRenderer(protocolForm.getProtocolDocument().getProtocol(), "added");
//        List<ProtocolNotificationRequestBean> addReviewerNotificationBeans = getNotificationRequestBeans(submitAction.getReviewers(),ProtocolReviewerBean.CREATE);
//        if (!CollectionUtils.isEmpty(addReviewerNotificationBeans)) {
//            ProtocolNotificationRequestBean notificationBean = addReviewerNotificationBeans.get(0);
//            IACUCNotificationContext context = new IACUCNotificationContext(notificationBean.getProtocol(),
//                    notificationBean.getProtocolOnlineReview(), notificationBean.getActionType(),
//                    notificationBean.getDescription(), renderer);
//            if (protocolForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
//                return checkToSendNotification(mapping, null, protocolForm, renderer, addReviewerNotificationBeans);
//            }
//        }
        return routeProtocolToHoldingPage(mapping, protocolForm);
    }
    
    /**
     * Withdraw a previously submitted protocol.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward withdrawProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocol protocol = protocolForm.getIacucProtocolDocument().getIacucProtocol();
        IacucProtocolTask task = new IacucProtocolTask(TaskName.IACUC_PROTOCOL_WITHDRAW, protocol);
        
        if (!hasDocumentStateChanged(protocolForm)) {
            if (isAuthorized(task)) {
                IacucProtocolDocument pd = getProtocolWithdrawService().withdraw(protocol, (IacucProtocolWithdrawBean)protocolForm.getActionHelper().getProtocolWithdrawBean());
    
                protocolForm.setDocId(pd.getDocumentNumber());
                loadDocument(protocolForm);
                protocolForm.getProtocolHelper().prepareView();
//TODO:IACUC                protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, PROTOCOL_TAB, new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(), ProtocolActionType.WITHDRAWN, "Withdrawn"), false));
                recordProtocolActionSuccess("Withdraw");
    
//TODO:IACUC                if (protocolForm.getActionHelper().getProtocolCorrespondence() != null) {
//TODO:IACUC                    return mapping.findForward(CORRESPONDENCE);
//TODO:IACUC                } else {
//TODO:IACUC                    return checkToSendNotification(mapping, mapping.findForward(PROTOCOL_TAB), protocolForm, new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(), ProtocolActionType.WITHDRAWN, "Withdrawn"));
return checkToSendNotification(mapping, mapping.findForward(PROTOCOL_TAB), protocolForm, null);
//TODO:IACUC                }
            }
        } else {
            GlobalVariables.getMessageMap().clearErrorMessages();
            GlobalVariables.getMessageMap().putError("documentstatechanged", KeyConstants.ERROR_PROTOCOL_DOCUMENT_STATE_CHANGED,  new String[] {}); 
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward assignCommitteeSchedule(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        IacucProtocol protocol = (IacucProtocol) protocolForm.getProtocolDocument().getProtocol();
        final String callerString = "assignCommitteeSchedule";
        //ProtocolTask task = new ProtocolTask(TaskName.ASSIGN_TO_COMMITTEE_SCHEDULE, protocolForm.getProtocolDocument().getProtocol());
        
        /*if (!hasDocumentStateChanged(protocolForm)) {
        
        } else {
            
        }*/
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

//TODO: The following should be promoted up to protocol package (add 3rd parm to pass document type)    
    private ActionForward routeProtocolToHoldingPage(ActionMapping mapping, ProtocolForm protocolForm) {
        String routeHeaderId = protocolForm.getProtocolDocument().getDocumentNumber();
        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_IACUC_PROTOCOL_ACTIONS, "IacucProtocolDocument");
        
        ActionForward basicForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
        ActionForward holdingPageForward = mapping.findForward(Constants.MAPPING_HOLDING_PAGE);
        return routeToHoldingPage(basicForward, basicForward, holdingPageForward, returnLocation);
    }

    /**
     * 
     * This method checks the document state to see if something has changed between the time the user
     * loaded the document to when they clicked on an action.
     * @param protocolForm
     */
    private boolean hasDocumentStateChanged(IacucProtocolForm protocolForm) {
        boolean result = false;
        
        Map<String,Object> primaryKeys = new HashMap<String, Object>();
        primaryKeys.put("protocolId", protocolForm.getProtocolDocument().getProtocol().getProtocolId());
        IacucProtocol dbProtocol = (IacucProtocol)getBusinessObjectService().findByPrimaryKey(IacucProtocol.class, primaryKeys);
        
        //First lets check the protocol status & submission status
        if (dbProtocol != null) {
            if (!StringUtils.equals(dbProtocol.getProtocolStatusCode(), 
                    protocolForm.getProtocolDocument().getProtocol().getProtocolStatusCode())) {
                result = true;
            }
            
            if (dbProtocol.getProtocolSubmission() != null && 
                    protocolForm.getProtocolDocument().getProtocol().getProtocolSubmission().getSubmissionStatusCode() != null) {
                if (!StringUtils.equals(dbProtocol.getProtocolSubmission().getSubmissionStatusCode(), 
                        protocolForm.getProtocolDocument().getProtocol().getProtocolSubmission().getSubmissionStatusCode())) {
                    result = true;
                }
            }
        }
        
        //If no changes in the protocol, lets check the document for workflow changes
        if (!result) {
           result = !isDocumentPostprocessingComplete(protocolForm.getIacucProtocolDocument());
        }
        
        return result;
    }
    
    private boolean isDocumentPostprocessingComplete(IacucProtocolDocument document) {
        return document.getDocumentHeader().hasWorkflowDocument() && !isPessimisticallyLocked(document);
    }
    
    private boolean isPessimisticallyLocked(Document document) {
        boolean isPessimisticallyLocked = false;
        
        Person pessimisticLockHolder = getPersonService().getPersonByPrincipalName(KewApiConstants.SYSTEM_USER);
        for (PessimisticLock pessimisticLock : document.getPessimisticLocks()) {
            if (pessimisticLock.isOwnedByUser(pessimisticLockHolder)) {
                isPessimisticallyLocked = true;
                break;
            }
        }
        
        return isPessimisticallyLocked;
    }
    
    private ActionForward checkToSendNotification(ActionMapping mapping, ActionForward forward, ProtocolForm protocolForm, ProtocolNotificationRequestBean notificationRequestBean) {
        
//TODO: Implement for IACUC
//        IACUCNotificationRenderer renderer = null;
//        if (StringUtils.equals(IacucProtocolActionType.NOTIFY_IACUC, notificationRequestBean.getActionType())) {
//            renderer = new NotifyIacucNotificationRenderer(notificationRequestBean.getProtocol(), protocolForm.getActionHelper().getProtocolNotifyIrbBean().getComment());
//        } else if (StringUtils.equals(IacucProtocolActionType.NOTIFIED_COMMITTEE, notificationRequestBean.getActionType())) {
//            renderer = new NotifyCommitteeNotificationRenderer(notificationRequestBean.getProtocol(), 
//                    protocolForm.getActionHelper().getProtocolNotifyCommitteeBean().getCommitteeName(), 
//                    protocolForm.getActionHelper().getProtocolNotifyCommitteeBean().getComment(), 
//                    protocolForm.getActionHelper().getProtocolNotifyCommitteeBean().getActionDate());
//        } else if (StringUtils.equals(IacucProtocolActionType.TERMINATED, notificationRequestBean.getActionType())) {
//            renderer = new ProtocolTerminatedNotificationRenderer(notificationRequestBean.getProtocol(), protocolForm.getActionHelper().getProtocolTerminateRequestBean().getReason());
//        } else if (StringUtils.equals(IacucProtocolActionType.EXPIRED, notificationRequestBean.getActionType())) {
//            renderer = new ProtocolExpiredNotificationRenderer(notificationRequestBean.getProtocol());
//        } else if (StringUtils.equals(IacucProtocolActionType.IACUC_DISAPPROVED, notificationRequestBean.getActionType())) {
//            renderer = new ProtocolDisapprovedNotificationRenderer(notificationRequestBean.getProtocol());
//        } else if (StringUtils.equals(IacucProtocolActionType.SUSPENDED, notificationRequestBean.getActionType())) {
//            renderer = new ProtocolSuspendedNotificationRenderer(notificationRequestBean.getProtocol());
//        } else if (StringUtils.equals(IacucProtocolActionType.CLOSED_ADMINISTRATIVELY_CLOSED, notificationRequestBean.getActionType())) {
//            renderer = new ProtocolClosedNotificationRenderer(notificationRequestBean.getProtocol(), notificationRequestBean);
//        } else {
//            renderer = new IACUCNotificationRenderer(notificationRequestBean.getProtocol());
//        }
//        IACUCNotificationContext context = new IACUCNotificationContext(notificationRequestBean.getProtocol(), notificationRequestBean.getActionType(), notificationRequestBean.getDescription(), renderer);
//        
//        if (protocolForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
//            context.setForwardName(forward.getName());
//            protocolForm.getNotificationHelper().initializeDefaultValues(context);
//            return mapping.findForward("iacucProtocolNotificationEditor");
//        } else {
//            getNotificationService().sendNotification(context);
            return forward;
//        }
    }
    

    /**
     * Invoked when the "copy protocol" button is clicked.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward copyProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {


        IacucProtocolForm protocolForm = (IacucProtocolForm) form;

        ApplicationTask task = new ApplicationTask(TaskName.CREATE_IACUC_PROTOCOL);
        if (isAuthorized(task)) {
            String newDocId = getIacucProtocolCopyService().copyProtocol(protocolForm.getIacucProtocolDocument()).getDocumentNumber();

            // Switch over to the new protocol document and
            // go to the Protocol tab web page.

            protocolForm.setDocId(newDocId);
            protocolForm.setViewOnly(false);
            loadDocument(protocolForm);
            protocolForm.getIacucProtocolDocument().setViewOnly(protocolForm.isViewOnly());
            protocolForm.getActionHelper().setCurrentSubmissionNumber(-1);
            protocolForm.getProtocolHelper().prepareView();
            
// TODO *********commented the code below during IACUC refactoring*********             
//            protocolForm.getActionHelper().prepareCommentsView();

            return mapping.findForward(PROTOCOL_TAB);
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private void recordProtocolActionSuccess(String protocolActionName) {
        KNSGlobalVariables.getMessageList().add(KeyConstants.MESSAGE_PROTOCOL_ACTION_SUCCESSFULLY_COMPLETED, protocolActionName);
    }

    private IacucProtocolWithdrawService getProtocolWithdrawService() {
        return KraServiceLocator.getService(IacucProtocolWithdrawService.class);
    }
    
    protected PersonService getPersonService() {
        return KraServiceLocator.getService(PersonService.class);
    }
    
    protected KcNotificationService getNotificationService() {
        return KraServiceLocator.getService(KcNotificationService.class);
    }

    private IacucProtocolSubmitActionService getProtocolSubmitActionService() {
        return KraServiceLocator.getService(IacucProtocolSubmitActionService.class);
    }

    public IacucProtocolCopyService getIacucProtocolCopyService() {
        return KraServiceLocator.getService(IacucProtocolCopyService.class);
    }

}
