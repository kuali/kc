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

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.common.notification.bo.KcNotification;
import org.kuali.kra.common.notification.bo.NotificationTypeRecipient;
import org.kuali.kra.common.notification.rule.event.AddNotificationRecipientEvent;
import org.kuali.kra.common.notification.rule.event.SendNotificationEvent;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.ProtocolAction;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;

public class ProtocolNotificationEditorAction extends ProtocolAction {
    
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
    public ActionForward sendNotification(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument document = protocolForm.getProtocolDocument();
        KcNotification notification = protocolForm.getNotificationHelper().getNotification();
        List<NotificationTypeRecipient> notificationRecipients = protocolForm.getNotificationHelper().getNotificationRecipients();
        
        if (applyRules(new SendNotificationEvent(document, notification, notificationRecipients))) {
            protocolForm.getNotificationHelper().sendNotification();
            String forwardName = protocolForm.getNotificationHelper().getNotificationContext().getForwardName();
            protocolForm.getNotificationHelper().setNotificationContext(null);
            if (StringUtils.isNotBlank(forwardName)) {
                if (StringUtils.startsWith(forwardName, "holdingPage")) {
                    String[] params = StringUtils.split(forwardName, ":");
                    return routeProtocolOLRToHoldingPage(mapping, protocolForm, params[1], params[2]);
                } else {
                    actionForward = mapping.findForward(forwardName);
                }
            } else {
                actionForward = mapping.findForward("protocolActions");
            }
        }
        
        return actionForward;
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
        Long routeHeaderId = Long.parseLong(protocolForm.getDocument().getDocumentNumber());
        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_PROTOCOL_ONLINE_REVIEW , "ProtocolDocument");
        // use this doc id for holding action to check if online review document is complete and return to online review tab
        returnLocation += "&" + "olrDocId=" + olrDocId + "&" + "olrEvent=" + olrEvent;
        ActionForward basicForward = mapping.findForward(KNSConstants.MAPPING_PORTAL);
        //ActionForward holdingPageForward = mapping.findForward(Constants.MAPPING_HOLDING_PAGE);
        ActionForward holdingPageForward = mapping.findForward(Constants.MAPPING_IRB_HOLDING_PAGE);
        GlobalVariables.getUserSession().addObject(Constants.HOLDING_PAGE_DOCUMENT_ID, (Object)olrDocId);
        
        return routeToHoldingPage(basicForward, basicForward, holdingPageForward, returnLocation);

    }

}