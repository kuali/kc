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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolAction;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmitAction;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmitActionEvent;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmitActionService;
import org.kuali.kra.iacuc.auth.IacucProtocolTask;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.ProtocolForm;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

public class IacucProtocolActionsAction extends IacucProtocolAction {

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
//        IRBNotificationRenderer submitRenderer = new IRBNotificationRenderer(protocol);
//        IRBNotificationContext submitContext = new IRBNotificationContext(protocol, null, 
//                                                    ProtocolActionType.SUBMIT_TO_IRB_NOTIFICATION, "Submit", submitRenderer);
//        getNotificationService().sendNotification(submitContext);
//        AssignReviewerNotificationRenderer renderer = new AssignReviewerNotificationRenderer(protocolForm.getProtocolDocument().getProtocol(), "added");
//        List<ProtocolNotificationRequestBean> addReviewerNotificationBeans = getNotificationRequestBeans(submitAction.getReviewers(),ProtocolReviewerBean.CREATE);
//        if (!CollectionUtils.isEmpty(addReviewerNotificationBeans)) {
//            ProtocolNotificationRequestBean notificationBean = addReviewerNotificationBeans.get(0);
//            IRBNotificationContext context = new IRBNotificationContext(notificationBean.getProtocol(),
//                    notificationBean.getProtocolOnlineReview(), notificationBean.getActionType(),
//                    notificationBean.getDescription(), renderer);
//            if (protocolForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
//                return checkToSendNotification(mapping, null, protocolForm, renderer, addReviewerNotificationBeans);
//            }
//        }
        return routeProtocolToHoldingPage(mapping, protocolForm);
    }

//TODO: The following should be promoted up to protocol package (add 3rd parm to pass document type)    
    private ActionForward routeProtocolToHoldingPage(ActionMapping mapping, ProtocolForm protocolForm) {
        String routeHeaderId = protocolForm.getProtocolDocument().getDocumentNumber();
        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_IACUC_PROTOCOL_ACTIONS, "IacucProtocolDocument");
        
        ActionForward basicForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
        ActionForward holdingPageForward = mapping.findForward(Constants.MAPPING_HOLDING_PAGE);
        return routeToHoldingPage(basicForward, basicForward, holdingPageForward, returnLocation);
    }
    
    private IacucProtocolSubmitActionService getProtocolSubmitActionService() {
        return KraServiceLocator.getService(IacucProtocolSubmitActionService.class);
    }


}
