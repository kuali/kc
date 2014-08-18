/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.coi;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.notification.impl.bo.KcNotification;
import org.kuali.coeus.common.notification.impl.bo.NotificationTypeRecipient;
import org.kuali.coeus.common.notification.impl.rule.event.AddNotificationRecipientEvent;
import org.kuali.coeus.common.notification.impl.rule.event.SendNotificationEvent;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.notification.CoiNotification;
import org.kuali.kra.infrastructure.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CoiNotificationEditorAction extends CoiAction {
    public static final String DISCLOSURE_ACTIONS_TAB = "disclosureActions";
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
        
        CoiDisclosureForm protocolForm = (CoiDisclosureForm) form;
        
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
        
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        CoiDisclosureDocument document = coiDisclosureForm.getCoiDisclosureDocument();
        NotificationTypeRecipient notificationRecipient = coiDisclosureForm.getNotificationHelper().getNewNotificationRecipient();
        List<NotificationTypeRecipient> notificationRecipients = coiDisclosureForm.getNotificationHelper().getNotificationRecipients();
        
        if (checkRule(new AddNotificationRecipientEvent(document, notificationRecipient, notificationRecipients))) {
            coiDisclosureForm.getNotificationHelper().getNotificationRecipients().add(notificationRecipient);
            coiDisclosureForm.getNotificationHelper().setNewNotificationRecipient(new NotificationTypeRecipient());
            coiDisclosureForm.getNotificationHelper().setNewRoleId(null);
            coiDisclosureForm.getNotificationHelper().setNewPersonId(null);
            coiDisclosureForm.getNotificationHelper().setNewRolodexId(null);
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

        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        
        coiDisclosureForm.getNotificationHelper().getNotificationRecipients().remove(getLineToDelete(request));
        
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
        
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        CoiDisclosureDocument document = coiDisclosureForm.getCoiDisclosureDocument();
        KcNotification notification = coiDisclosureForm.getNotificationHelper().getNotification();
        List<NotificationTypeRecipient> notificationRecipients = coiDisclosureForm.getNotificationHelper().getNotificationRecipients();
        
        if (checkRule(new SendNotificationEvent(document, notification, notificationRecipients))) {
            getKcNotificationService().sendNotificationAndPersist(coiDisclosureForm.getNotificationHelper().getNotificationContext(), 
                    new CoiNotification(), notificationRecipients, document.getCoiDisclosure());
            String forwardName = coiDisclosureForm.getNotificationHelper().getNotificationContext().getForwardName();
            coiDisclosureForm.getNotificationHelper().setNotificationContext(null);
            if (StringUtils.isNotBlank(forwardName)) {
                actionForward = mapping.findForward(forwardName);
            } else {
                actionForward = mapping.findForward(DISCLOSURE_ACTIONS_TAB);
            }
        }
        
        return actionForward;
    }

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
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        
        coiDisclosureForm.getNotificationHelper().setNotificationContext(null);
        
        return mapping.findForward(DISCLOSURE_ACTIONS_TAB);
    }

}