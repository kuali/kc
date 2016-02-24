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
package org.kuali.kra.iacuc.actions;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.kuali.coeus.common.notification.impl.bo.KcNotification;
import org.kuali.coeus.common.notification.impl.bo.NotificationTypeRecipient;
import org.kuali.coeus.common.notification.impl.rule.event.AddNotificationRecipientEvent;
import org.kuali.coeus.common.notification.impl.rule.event.SendNotificationEvent;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.sys.framework.controller.KcHoldingPageConstants;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucProtocolAction;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.notification.IacucProtocolNotification;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@SuppressWarnings("deprecation")
public class IacucProtocolNotificationEditorAction extends IacucProtocolAction {
    private static final String PROTOCOL_ACTIONS_TAB = "iacucProtocolActions";
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
        
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        
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
        
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolDocument document = (IacucProtocolDocument)protocolForm.getProtocolDocument();
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

        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        
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
    public ActionForward sendNotification(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolDocument document = protocolForm.getIacucProtocolDocument();
        KcNotification notification = protocolForm.getNotificationHelper().getNotification();
        List<NotificationTypeRecipient> notificationRecipients = protocolForm.getNotificationHelper().getNotificationRecipients();
        
        if (applyRules(new SendNotificationEvent(document, notification, notificationRecipients))) {
            protocolForm.getNotificationHelper().sendNotificationAndPersist(new IacucProtocolNotification(), document.getProtocol());
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
                actionForward = mapping.findForward(PROTOCOL_ACTIONS_TAB);
            }
        }
        @SuppressWarnings("unused")
        Object notificationRequestBeans = GlobalVariables.getUserSession().retrieveObject("removeReviewer");
        return actionForward;
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
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        
        protocolForm.getNotificationHelper().setNotificationContext(null);
        
        return mapping.findForward(PROTOCOL_ACTIONS_TAB);
    }
    
    private ActionForward routeProtocolOLRToHoldingPage(ActionMapping mapping, IacucProtocolForm protocolForm, String olrDocId, String olrEvent) {
        String routeHeaderId = protocolForm.getDocument().getDocumentNumber();
        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_PROTOCOL_ONLINE_REVIEW , "IacucProtocolDocument");
        // use this doc id for holding action to check if online review document is complete and return to online review tab
        returnLocation += "&" + "olrDocId=" + olrDocId + "&" + "olrEvent=" + olrEvent;
        ActionForward basicForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
        ActionRedirect holdingPageForward = new ActionRedirect(mapping.findForward(KcHoldingPageConstants.MAPPING_HOLDING_PAGE));
        holdingPageForward.addParameter(KcHoldingPageConstants.HOLDING_PAGE_DOCUMENT_ID, routeHeaderId);
        
        return routeToHoldingPage(basicForward, basicForward, holdingPageForward, returnLocation, olrDocId);

    }

    private ActionForward routeProtocolToHoldingPage(ActionMapping mapping, IacucProtocolForm protocolForm) {
        String routeHeaderId = protocolForm.getDocument().getDocumentNumber();
        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_PROTOCOL_ACTIONS, "IacucProtocolDocument");
        
        ActionForward basicForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
        ActionRedirect holdingPageForward = new ActionRedirect(mapping.findForward(KcHoldingPageConstants.MAPPING_HOLDING_PAGE));
        holdingPageForward.addParameter(KcHoldingPageConstants.HOLDING_PAGE_DOCUMENT_ID, routeHeaderId);
        return routeToHoldingPage(basicForward, basicForward, holdingPageForward, returnLocation, routeHeaderId);

    }
 
    private void recordProtocolActionSuccess(String protocolActionName) {
        KNSGlobalVariables.getMessageList().add(KeyConstants.MESSAGE_PROTOCOL_ACTION_SUCCESSFULLY_COMPLETED, protocolActionName);
    }

}
