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
package org.kuali.kra.negotiations.web.struts.action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.notification.impl.bo.KcNotification;
import org.kuali.coeus.common.notification.impl.bo.NotificationTypeRecipient;
import org.kuali.coeus.common.notification.impl.rule.event.AddNotificationRecipientEvent;
import org.kuali.coeus.common.notification.impl.rule.event.SendNotificationEvent;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.kra.negotiations.notifications.NegotiationNotification;
import org.kuali.kra.negotiations.web.struts.form.NegotiationForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class NegotiationNotificationAction extends NegotiationAction {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
        
        NegotiationForm negotiationForm = (NegotiationForm) form;
        
        negotiationForm.getNotificationHelper().prepareView();
        
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
        
        NegotiationForm negotiationForm = (NegotiationForm) form;
        NegotiationDocument document = negotiationForm.getNegotiationDocument();
        NotificationTypeRecipient notificationRecipient = negotiationForm.getNotificationHelper().getNewNotificationRecipient();
        List<NotificationTypeRecipient> notificationRecipients = negotiationForm.getNotificationHelper().getNotificationRecipients();
        
        if (applyRules(new AddNotificationRecipientEvent(document, notificationRecipient, notificationRecipients))) {
            negotiationForm.getNotificationHelper().getNotificationRecipients().add(notificationRecipient);
            negotiationForm.getNotificationHelper().setNewNotificationRecipient(new NotificationTypeRecipient());
            negotiationForm.getNotificationHelper().setNewRoleId(null);
            negotiationForm.getNotificationHelper().setNewPersonId(null);
            negotiationForm.getNotificationHelper().setNewRolodexId(null);
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
     * @throws Exception if unable to delete the special review
     */
    public ActionForward deleteNotificationRecipient(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {

        NegotiationForm negotiationForm = (NegotiationForm) form;
        
        negotiationForm.getNotificationHelper().getNotificationRecipients().remove(getLineToDelete(request));
        
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
        
        NegotiationForm negotiationForm = (NegotiationForm) form;
        NegotiationDocument document = negotiationForm.getNegotiationDocument();
        KcNotification notification = negotiationForm.getNotificationHelper().getNotification();
        List<NotificationTypeRecipient> notificationRecipients = negotiationForm.getNotificationHelper().getNotificationRecipients();
        
        if (applyRules(new SendNotificationEvent(document, notification, notificationRecipients))) {
            negotiationForm.getNotificationHelper().sendNotificationAndPersist(new NegotiationNotification(), document.getNegotiation());
            negotiationForm.getNotificationHelper().setNotificationContext(null);
            
            actionForward = mapping.findForward("negotiation");
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
        NegotiationForm negotiationForm = (NegotiationForm) form;
        
        negotiationForm.getNotificationHelper().setNotificationContext(null);
        
        return mapping.findForward("negotiation");
    }
    
}
