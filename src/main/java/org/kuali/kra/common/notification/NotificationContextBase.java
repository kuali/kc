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
package org.kuali.kra.common.notification;


import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.kra.common.notification.bo.KcNotification;
import org.kuali.kra.common.notification.bo.NotificationModuleRole;
import org.kuali.kra.common.notification.bo.NotificationModuleRoleQualifier;
import org.kuali.kra.common.notification.bo.NotificationTypeRecipient;
import org.kuali.kra.common.notification.exception.UnknownRoleException;
import org.kuali.kra.common.notification.service.KcNotificationModuleRoleService;
import org.kuali.kra.common.notification.service.KcNotificationRenderingService;
import org.kuali.kra.common.notification.service.KcNotificationRoleQualifierService;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.rice.kim.bo.types.dto.AttributeSet;

/**
 * 
 * This class provides a class for all notifications to extend.  Its main purpose is to
 * provide a holding place for the required services, as well as define a few methods
 * that should be implemented by any notification.
 */
public abstract class NotificationContextBase implements NotificationContext {
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(NotificationContextBase.class);

    private KcNotificationService notificationService;
    private KcNotificationRenderingService notificationRenderingService;
    private KcNotificationModuleRoleService notificationModuleRoleService;
    private KcNotificationRoleQualifierService notificationRoleQualifierService;
        
    /**
     * 
     * This method returns the associated coeus module as defined in the
     * CoeusModule object.
     * @return the coeus module code
     * @see org.kuali.kra.bo.CoeusModule
     */
    public abstract String getModuleCode();
    
    /**
     * 
     * This method returns the action type code needed to send notifications.
     * by the KcNotificationService
     * @return the action type code
     */
    public abstract String getActionTypeCode();
    
    /**
     * 
     * This method returns the document number for the associated document.
     * @return the document number
     */
    public abstract String getDocumentNumber();
    
    /**
     * 
     * This method defines a label used for the given context.
     * @return the context name
     */
    public abstract String getContextName();
        
    /**
     * 
     * This method sends the notifications.
     */
    public void sendNotification() {
        LOG.info("Sending Notification [" + getContextName() + "]");
        List<KcNotification> notifications = getNotificationService().createNotifications(getDocumentNumber(), getModuleCode(), getActionTypeCode(), this);
        getNotificationService().sendNotifications(notifications, this);
    }
    
    /**
     * This method replaces the context variables using the default parameters.
     * @see org.kuali.kra.common.notification.NotificationContext#replaceContextVariables(java.lang.String)
     */
    public String replaceContextVariables(String text) {
        return getNotificationRenderingService().render(text);
    }
    
    /**
     * 
     * This method populates the role qualifiers for use in KIM lookups by finding the associated notification
     * module roles and using the role qualifier service to find the values.
     * @param notificationRecipient The recipient of the notification, it represents a KIM role
     * @throws UnknownRoleException
     */
    public void populateRoleQualifiers(NotificationTypeRecipient notificationRecipient) throws UnknownRoleException { 
        List<NotificationModuleRole> moduleRoles = 
            getNotificationModuleRoleService().getNotificationModuleRolesForKimRole(getModuleCode(), notificationRecipient.getRoleName());
        
        
        if (CollectionUtils.isNotEmpty(moduleRoles)) {
            if (notificationRecipient.getRoleQualifiers() == null) {
                notificationRecipient.setRoleQualifiers(new AttributeSet());
            }
            for (NotificationModuleRole mRole : moduleRoles) {
                List<NotificationModuleRoleQualifier> moduleQualifiers = mRole.getRoleQualifiers();
                if (CollectionUtils.isNotEmpty(moduleQualifiers)) {
                    for (NotificationModuleRoleQualifier mQualifier : moduleQualifiers) {
                        notificationRecipient.getRoleQualifiers().put(mQualifier.getQualifier(), 
                                getNotificationRoleQualifierService().getRoleQualifierValue(mQualifier));
                    }
                }
            }
        } else {
            throw new UnknownRoleException(notificationRecipient.getRoleName(), getContextName());
        }
    }    
    
    public KcNotificationService getNotificationService() {
        return notificationService;
    }

    public void setNotificationService(KcNotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public KcNotificationRenderingService getNotificationRenderingService() {
        return notificationRenderingService;
    }

    public void setNotificationRenderingService(KcNotificationRenderingService notificationRenderingService) {
        this.notificationRenderingService = notificationRenderingService;
    }

    public KcNotificationModuleRoleService getNotificationModuleRoleService() {
        return notificationModuleRoleService;
    }

    public void setNotificationModuleRoleService(KcNotificationModuleRoleService notificationModuleRoleService) {
        this.notificationModuleRoleService = notificationModuleRoleService;
    }

    public KcNotificationRoleQualifierService getNotificationRoleQualifierService() {
        return notificationRoleQualifierService;
    }

    public void setNotificationRoleQualifierService(KcNotificationRoleQualifierService notificationRoleQualifierService) {
        this.notificationRoleQualifierService = notificationRoleQualifierService;
    }



    

}
