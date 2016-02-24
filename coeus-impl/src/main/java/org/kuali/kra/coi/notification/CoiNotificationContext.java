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
package org.kuali.kra.coi.notification;


import org.kuali.coeus.common.framework.mail.EmailAttachment;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.notification.impl.NotificationContextBase;
import org.kuali.coeus.common.notification.impl.NotificationRenderer;
import org.kuali.coeus.common.notification.impl.bo.NotificationTypeRecipient;
import org.kuali.coeus.common.notification.impl.exception.UnknownRoleException;
import org.kuali.coeus.common.notification.impl.service.KcNotificationModuleRoleService;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.CoiDisclosure;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * This class extends the notification context base and provides some helpful functions for
 * any IRB specific events.
 */
public class CoiNotificationContext extends NotificationContextBase {

    private static final long serialVersionUID = 6642334312368480034L;

    private String coiDisclosureDocumentNumber;
    private String actionTypeCode;
    private String contextName;
    private String forwardName;
    private CoiNotificationRoleQualifierService coiNotificationRoleQualifierService;
    
    /**
     * Constructs a COI notification context and sets the necessary services.
     * @param coiDisclosure
     * @param actionTypeCode
     * @param contextName
     */
    public CoiNotificationContext(CoiDisclosure coiDisclosure, String actionTypeCode, String contextName, NotificationRenderer renderer) {
        super(renderer);

        this.coiDisclosureDocumentNumber = coiDisclosure.getCoiDisclosureDocument().getDocumentNumber();
        this.actionTypeCode = actionTypeCode;
        this.contextName = contextName;
        
        setNotificationService(KcServiceLocator.getService(KcNotificationService.class));
        setNotificationModuleRoleService(KcServiceLocator.getService(KcNotificationModuleRoleService.class));
        setNotificationRoleQualifierService(KcServiceLocator.getService(CoiNotificationRoleQualifierService.class));
        ((CoiNotificationRoleQualifierService) getNotificationRoleQualifierService()).setCoiDisclosure(coiDisclosure);
    }
    
    @Override
    public String getModuleCode() {
        return CoeusModule.COI_DISCLOSURE_MODULE_CODE;
    }
    
    @Override
    public String getDocumentNumber() {
        return coiDisclosureDocumentNumber;
    }
    
    @Override
    public String getActionTypeCode() {
        return actionTypeCode;
    }
    
    @Override
    public String getContextName() {
        return contextName;
    }

    /**
     * This is a hook/hack for assign reviewer/submit for review, which has potential of multiple reviewers
     * reviewers are supposed to be processed separately, but for 'prompt user', it merged into one prompt.
     * 'reviewer role' are the same, but the role qualifiers' are different which is based on context.
     * the role qualifier are populated when we merge all recipients in to one list.
     * So, when sendnotification, it is just using the last 'context', so at this point, we don't want
     * rolequalifiers being populated again.  If it is populated again, all reviewer role will retrieve same reviewer because 
     * the context are the same at the point of 'send'.
     * Unless, there is better approach, we'll stick with this hack for now.
     * isPopulateRole is only 'true' for this case, so the other cases will stay the same as before this change.
     * @see org.kuali.coeus.common.notification.impl.NotificationContextBase#populateRoleQualifiers(org.kuali.coeus.common.notification.impl.bo.NotificationTypeRecipient)
     */
    @Override
    public void populateRoleQualifiers(NotificationTypeRecipient notificationRecipient) throws UnknownRoleException {
        if (CollectionUtils.isEmpty(notificationRecipient.getRoleQualifiers())) {
            super.populateRoleQualifiers(notificationRecipient);
        }
    }

    @Override
    public List<EmailAttachment> getEmailAttachments() {

        return null;
    }
 
    public CoiNotificationRoleQualifierService getCoiNotificationRoleQualifierService() {
        return coiNotificationRoleQualifierService;
    }
    
    public void setCoiNotificationRoleQualifierService(CoiNotificationRoleQualifierService coiNotificationRoleQualifierService) {
        this.coiNotificationRoleQualifierService = coiNotificationRoleQualifierService;
    }

    public String getForwardName() {
        return forwardName;
    }

    public void setForwardName(String forwardName) {
        this.forwardName = forwardName;
    }

    
}
