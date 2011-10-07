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
package org.kuali.kra.irb.notification;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.common.notification.NotificationContext;
import org.kuali.kra.common.notification.NotificationContextBase;
import org.kuali.kra.common.notification.bo.KcNotification;
import org.kuali.kra.common.notification.bo.NotificationModuleRole;
import org.kuali.kra.common.notification.bo.NotificationModuleRoleQualifier;
import org.kuali.kra.common.notification.bo.NotificationTypeRecipient;
import org.kuali.kra.common.notification.exception.UnknownRoleException;
import org.kuali.kra.common.notification.service.KcNotificationModuleRoleService;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.rice.kim.bo.types.dto.AttributeSet;

/**
 * 
 * This class extends the notification context base and provides some helpful functions for
 * any IRB specific events.
 */
public abstract class IRBNotificationContext extends NotificationContextBase {
    
    protected Protocol protocol;

    /**
     * 
     * Constructs a IRBNotificationContext.java and sets the necessary services
     * @param protocol
     */
    public IRBNotificationContext(Protocol protocol) {
        setProtocol(protocol);
        setNotificationModuleRuleService(KraServiceLocator.getService(KcNotificationModuleRoleService.class));
        setNotificationRenderingService(new IRBNotificationRenderingServiceImpl(getProtocol()));
        setNotificationRoleQualifierService(new IRBNotificationRoleQualifierServiceImpl(getProtocol()));
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }
    
    /**
     * 
     * @see org.kuali.kra.common.notification.NotificationContextBase#getModuleCode()
     */
    public String getModuleCode() {
        return CoeusModule.IRB_MODULE_CODE;
    }
    
    /**
     * 
     * This method sends a notification context with IRB settings
     * @param context the notification context to send
     */
    public void sendNotification(NotificationContext context) {
        KcNotificationService kcNotificationService = KraServiceLocator.getService(KcNotificationService.class);
        List<KcNotification> notifications = kcNotificationService.createNotifications(getProtocol().getProtocolDocument().getDocumentNumber(), Integer.toString(CoeusModule.IRB_MODULE_CODE_INT), getActionTypeCode(), context);
        kcNotificationService.sendNotifications(notifications, context);
    }
    
    /**
     * 
     * This method populates the role qualifiers for use in KIM lookups by finding the associated notification
     * module roles and using the role qualifier service to find the values
     * @param notificationRecipient The recipient of the notification, it represents a KIM role
     * @param contextName The name of the calling event
     * @throws UnknownRoleException
     */
    public void populateRoleQualifiers(NotificationTypeRecipient notificationRecipient, String contextName) throws UnknownRoleException { 
        List<NotificationModuleRole> moduleRoles = 
            getNotificationModuleRuleService().getNotificationModuleRolesForKimRole(getModuleCode(), notificationRecipient.getRoleName());
        
        
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
            throw new UnknownRoleException(notificationRecipient.getRoleName(), contextName);
        }
    }

}
