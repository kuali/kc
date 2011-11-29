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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.common.notification.bo.NotificationTypeRecipient;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.ProtocolAction;
import org.kuali.kra.irb.ProtocolForm;

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
        NotificationTypeRecipient notificationRecipient = protocolForm.getNotificationHelper().getNewNotificationRecipient();
        
        protocolForm.getNotificationHelper().getNotificationRecipients().add(notificationRecipient);
        protocolForm.getNotificationHelper().setNewNotificationRecipient(new NotificationTypeRecipient());
        protocolForm.getNotificationHelper().setNewRoleId(null);
        protocolForm.getNotificationHelper().setNewPersonId(null);
        protocolForm.getNotificationHelper().setNewRolodexId(null);
        
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

        ProtocolForm protocolForm = (ProtocolForm) form;
        
        protocolForm.getNotificationHelper().getNotificationRecipients().remove(getLineToDelete(request));
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward sendNotification(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        
        protocolForm.getNotificationHelper().sendNotification();
        
        return mapping.findForward("protocolActions");
    }
    
    public ActionForward cancelNotification(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward("protocolActions");
    }
    
}