/*
 * Copyright 2005-2013 The Kuali Foundation
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

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.common.notification.bo.KcNotification;
import org.kuali.kra.common.notification.bo.NotificationTypeRecipient;
import org.kuali.kra.common.notification.rule.event.AddNotificationRecipientEvent;
import org.kuali.kra.common.notification.rule.event.SendNotificationEvent;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolAction;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.actions.notification.AssignReviewerNotificationRenderer;
import org.kuali.kra.irb.actions.notification.ProtocolNotificationRequestBean;
import org.kuali.kra.irb.notification.IRBNotificationContext;
import org.kuali.kra.irb.notification.IRBProtocolNotification;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.protocol.notification.ProtocolNotification;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class ProtocolNotificationEditorAction extends ProtocolAction {
    private static final String PROTOCOL_ACTIONS_TAB = "protocolActions";
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        
        protocolForm.getNotificationHelper().prepareView();
        
        return actionForward;
    }
    
    /**
     * Adds a Notification Recipient.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward addNotificationRecipient(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument document = protocolForm.getProtocolDocument();
        NotificationTypeRecipient notificationRecipient = protocolForm.getNotificationHelper().getNewNotificationRecipient();
        List<NotificationTypeRecipient> notificationRecipients = protocolForm.getNotificationHelper().getNotificationRecipients();
        
        if (applyRules(new AddNotificationRecipientEvent(document, notificationRecipient, notificationRecipients))) {
            protocolForm.getNotificationHelper().getNotificationRecipients().add(notificationRecipient);
            protocolForm.getNotificationHelper().setNewNotificationRecipient(new NotificationTypeRecipient());
            protocolForm.getNotificationHelper().setNewRoleId(null);
            protocolForm.getNotificationHelper().setNewPersonId(null);
            protocolForm.getNotificationHelper().setNewRolodexId(null);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Deletes a Notification Recipient.
     * 
     * @param mapping the action mapping
     * @param form the action form
     * @param request the request
     * @param response the response
     * @return the action forward
     * @throws Exception
     */
    public ActionForward deleteNotificationRecipient(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {

        ProtocolForm protocolForm = (ProtocolForm) form;
        
        protocolForm.getNotificationHelper().getNotificationRecipients().remove(getLineToDelete(request));
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Sends a Notification.
     * 
     * @param mapping the action mapping
     * @param form the action form
     * @param request the request
     * @param response the response
     * @return the action forward
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward sendNotification(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument document = protocolForm.getProtocolDocument();
        KcNotification notification = protocolForm.getNotificationHelper().getNotification();
        List<NotificationTypeRecipient> notificationRecipients = protocolForm.getNotificationHelper().getNotificationRecipients();
        
        if (applyRules(new SendNotificationEvent(document, notification, notificationRecipients))) {
            protocolForm.getNotificationHelper().sendNotificationAndPersist(new IRBProtocolNotification(), document.getProtocol());                 
            String forwardName = protocolForm.getNotificationHelper().getNotificationContext().getForwardName();
            protocolForm.getNotificationHelper().setNotificationContext(null);
            if (StringUtils.isNotBlank(forwardName)) {
                if (StringUtils.startsWith(forwardName, "holdingPage")) {
                    if (StringUtils.equals(forwardName, "holdingPage")) {
                        return routeProtocolToHoldingPage(mapping, protocolForm);
                    } else {
                       String[] params = StringUtils.split(forwardName, ":");
                        return routeProtocolOLRToHoldingPage(mapping, protocolForm, params[1], params[2]);
                    }
                } else {
                    actionForward = mapping.findForward(forwardName);
                }
            } else {
                recordProtocolActionSuccess("Send Notification");
                actionForward = mapping.findForward("protocolActions");
            }
        }
        Object notificationRequestBeans = GlobalVariables.getUserSession().retrieveObject("removeReviewer");
        if (notificationRequestBeans != null) {
            // This is specifically for 'assign reviewers" where it is possible that add/remove in the same action
            GlobalVariables.getUserSession().removeObject("removeReviewer");
            actionForward = checkToSendNotification(mapping, mapping.findForward(PROTOCOL_ACTIONS_TAB), protocolForm,  (List<ProtocolNotificationRequestBean>)notificationRequestBeans);                            
        }
        return actionForward;
    }

    
    /*
     * This is for assign reviewer (remove portion).  The notificationRequestBeans contains all 'added' or 'removed'
     * reviewers.  All the roles recipient will be merged, then forward to protocolnotificationeditor for ad hoc notification 
     * process. This is needed if 'add' and remove in the same submit, but specifically for 'remove'; add is done in protocolprotocolactionsaction
     */
    private ActionForward checkToSendNotification(ActionMapping mapping, ActionForward forward, ProtocolForm protocolForm,
            List<ProtocolNotificationRequestBean> notificationRequestBeans) {

        AssignReviewerNotificationRenderer renderer = new AssignReviewerNotificationRenderer((Protocol)notificationRequestBeans.get(0)
                .getProtocol(), "removed");
        IRBNotificationContext context = new IRBNotificationContext((Protocol)notificationRequestBeans.get(0).getProtocol(),
            (ProtocolOnlineReview)notificationRequestBeans.get(0).getProtocolOnlineReview(), notificationRequestBeans.get(0).getActionType(),
            notificationRequestBeans.get(0).getDescription(), renderer);

        if (protocolForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
            context.setForwardName(forward.getName());
            context.setPopulateRole(true);
           protocolForm.getNotificationHelper().initializeDefaultValues(context);
            List<NotificationTypeRecipient> notificationRecipients = protocolForm.getNotificationHelper()
                    .getNotificationRecipients();
            List<NotificationTypeRecipient> allRecipients = new ArrayList<NotificationTypeRecipient>();
            for (NotificationTypeRecipient recipient : notificationRecipients) {
                try {
                    NotificationTypeRecipient copiedRecipient = (NotificationTypeRecipient) ObjectUtils.deepCopy(recipient);
                    context.populateRoleQualifiers(copiedRecipient);
                    allRecipients.add(copiedRecipient);
                }
                catch (Exception e) {
                    // TODO
                }
            }
            int i = 1;
            // add all new reviewer to recipients
            while (notificationRequestBeans.size() > i) {
                context = new IRBNotificationContext((Protocol)notificationRequestBeans.get(i).getProtocol(), (ProtocolOnlineReview)notificationRequestBeans.get(i)
                        .getProtocolOnlineReview(), notificationRequestBeans.get(i).getActionType(), notificationRequestBeans
                        .get(i).getDescription(), renderer);
                // protocolForm.getNotificationHelper().setNotificationRecipients(new ArrayList<NotificationTypeRecipient>());
                context.setPopulateRole(true);
                protocolForm.getNotificationHelper().initializeDefaultValues(context);
                List<NotificationTypeRecipient> recipients = protocolForm.getNotificationHelper().getNotificationRecipients();

                for (NotificationTypeRecipient recipient : recipients) {
                    try {
                        // note : need to deepcopy here. If I don't do that, then all reviewer role will have same
                        // notificationrecipient object returned from service call
                        // probably the object service/ojb has a cache ?
                        NotificationTypeRecipient copiedRecipient = (NotificationTypeRecipient) ObjectUtils.deepCopy(recipient);
                        context.populateRoleQualifiers(copiedRecipient);
                        allRecipients.add(copiedRecipient);
                    }
                    catch (Exception e) {
                        // TODO
                    }
                }
                i++;
            }
            protocolForm.getNotificationHelper().setNotificationRecipients(allRecipients);
            return mapping.findForward("protocolNotificationEditor");
        }
        else {
            // should not need this 'no prompt' here because it is processed in service.
            @SuppressWarnings("unused")
            int i = 0;
            return forward;
        }
    }
     
    @SuppressWarnings("unused")
    private KcNotificationService getKcNotificationService() {
        return KcServiceLocator.getService(KcNotificationService.class);
    }    
    
    /**
     * Cancels a Notification.
     * 
     * @param mapping the action mapping
     * @param form the action form
     * @param request the request
     * @param response the response
     * @return the action forward
     * @throws Exception
     */
    public ActionForward cancelNotification(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        
        protocolForm.getNotificationHelper().setNotificationContext(null);
        
        return mapping.findForward("protocolActions");
    }
    
    private ActionForward routeProtocolOLRToHoldingPage(ActionMapping mapping, ProtocolForm protocolForm, String olrDocId, String olrEvent) {
        String routeHeaderId = protocolForm.getDocument().getDocumentNumber();
        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_PROTOCOL_ONLINE_REVIEW , "ProtocolDocument");
        // use this doc id for holding action to check if online review document is complete and return to online review tab
        returnLocation += "&" + "olrDocId=" + olrDocId + "&" + "olrEvent=" + olrEvent;
        ActionForward basicForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
        ActionForward holdingPageForward = mapping.findForward(Constants.MAPPING_HOLDING_PAGE);
        GlobalVariables.getUserSession().addObject(Constants.HOLDING_PAGE_DOCUMENT_ID, (Object)olrDocId);
        // add that alternate session key to the session (for double indirection later in the holding page action)
        GlobalVariables.getUserSession().addObject(Constants.ALTERNATE_DOC_ID_SESSION_KEY, (Object)Constants.HOLDING_PAGE_DOCUMENT_ID);
        
        return routeToHoldingPage(basicForward, basicForward, holdingPageForward, returnLocation);

    }

    private ActionForward routeProtocolToHoldingPage(ActionMapping mapping, ProtocolForm protocolForm) {
        String routeHeaderId = protocolForm.getDocument().getDocumentNumber();
        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_PROTOCOL_ACTIONS, "ProtocolDocument");
        
        ActionForward basicForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
        ActionForward holdingPageForward = mapping.findForward(Constants.MAPPING_HOLDING_PAGE);
        return routeToHoldingPage(basicForward, basicForward, holdingPageForward, returnLocation);

    }
 
    private void recordProtocolActionSuccess(String protocolActionName) {
        KNSGlobalVariables.getMessageList().add(KeyConstants.MESSAGE_PROTOCOL_ACTION_SUCCESSFULLY_COMPLETED, protocolActionName);
    }

    @Override
    protected ProtocolNotification getProtocolNotificationHook() {
        // TODO Auto-generated method stub
        return null;
    }
    

}