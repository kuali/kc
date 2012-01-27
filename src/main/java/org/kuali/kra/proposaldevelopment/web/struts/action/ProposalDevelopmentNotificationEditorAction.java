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
package org.kuali.kra.proposaldevelopment.web.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.common.notification.bo.KcNotification;
import org.kuali.kra.common.notification.bo.NotificationTypeRecipient;
import org.kuali.kra.common.notification.rule.event.AddNotificationRecipientEvent;
import org.kuali.kra.common.notification.rule.event.SendNotificationEvent;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;

public class ProposalDevelopmentNotificationEditorAction extends ProposalDevelopmentAction {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
        
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        
        proposalDevelopmentForm.getNotificationHelper().prepareView();
        
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
        
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = proposalDevelopmentForm.getDocument();
        NotificationTypeRecipient notificationRecipient = proposalDevelopmentForm.getNotificationHelper().getNewNotificationRecipient();
        List<NotificationTypeRecipient> notificationRecipients = proposalDevelopmentForm.getNotificationHelper().getNotificationRecipients();
        
        if (applyRules(new AddNotificationRecipientEvent(document, notificationRecipient, notificationRecipients))) {
            proposalDevelopmentForm.getNotificationHelper().getNotificationRecipients().add(notificationRecipient);
            proposalDevelopmentForm.getNotificationHelper().setNewNotificationRecipient(new NotificationTypeRecipient());
            proposalDevelopmentForm.getNotificationHelper().setNewRoleId(null);
            proposalDevelopmentForm.getNotificationHelper().setNewPersonId(null);
            proposalDevelopmentForm.getNotificationHelper().setNewRolodexId(null);
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

        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        
        proposalDevelopmentForm.getNotificationHelper().getNotificationRecipients().remove(getLineToDelete(request));
        
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
        
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = proposalDevelopmentForm.getDocument();
        KcNotification notification = proposalDevelopmentForm.getNotificationHelper().getNotification();
        List<NotificationTypeRecipient> notificationRecipients = proposalDevelopmentForm.getNotificationHelper().getNotificationRecipients();
        
        if (applyRules(new SendNotificationEvent(document, notification, notificationRecipients))) {
            proposalDevelopmentForm.getNotificationHelper().sendNotification();
            
            actionForward = mapping.findForward(Constants.PROPOSAL_ACTIONS_PAGE);
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
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        
        proposalDevelopmentForm.getNotificationHelper().setNotificationContext(null);
        
        return mapping.findForward(Constants.PROPOSAL_ACTIONS_PAGE);
    }
    
}