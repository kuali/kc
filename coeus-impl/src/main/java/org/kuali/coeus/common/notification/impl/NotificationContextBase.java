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
package org.kuali.coeus.common.notification.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.notification.impl.bo.NotificationModuleRole;
import org.kuali.coeus.common.notification.impl.bo.NotificationModuleRoleQualifier;
import org.kuali.coeus.common.notification.impl.bo.NotificationTypeRecipient;
import org.kuali.coeus.common.notification.impl.exception.UnknownRoleException;
import org.kuali.coeus.common.notification.impl.service.KcNotificationModuleRoleService;
import org.kuali.coeus.common.notification.impl.service.KcNotificationRoleQualifierService;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.kim.bo.KcKimAttributes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Provides a base class for all notifications to extend.  It defines a holding place for the required services as well as a few methods that should be 
 * implemented by any notification.
 */
public abstract class NotificationContextBase implements NotificationContext, Serializable {

    private static final long serialVersionUID = -6135354658158890714L;

    private transient NotificationRenderer renderer;
    
    private transient KcNotificationService notificationService;
    private transient KcNotificationModuleRoleService notificationModuleRoleService;
    private transient KcNotificationRoleQualifierService notificationRoleQualifierService;
    
    /**
     * Constructs a Notification Context base class.
     * 
     * @param renderer the text renderer for this context
     */
    public NotificationContextBase(NotificationRenderer renderer) {
        this.renderer = renderer;
    }
    
    /**
     * This method replaces the context variables using the default parameters.
     * @see org.kuali.coeus.common.notification.impl.NotificationContext#replaceContextVariables(java.lang.String)
     */
    public String replaceContextVariables(String text) {
        return renderer.render(text);
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
                notificationRecipient.setRoleQualifiers(new HashMap<String,String>());
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
            if (StringUtils.isNotBlank(notificationRecipient.getRoleSubQualifier())) {
                notificationRecipient.getRoleQualifiers().put(KcKimAttributes.SUB_QUALIFIER, notificationRecipient.getRoleSubQualifier());
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

    public KcNotificationModuleRoleService getNotificationModuleRoleService() {
        if (notificationModuleRoleService == null) {
            notificationModuleRoleService = KcServiceLocator.getService(KcNotificationModuleRoleService.class);
        }
        return notificationModuleRoleService;
    }

    public void setNotificationModuleRoleService(KcNotificationModuleRoleService notificationModuleRoleService) {
        this.notificationModuleRoleService = notificationModuleRoleService;
    }

    public KcNotificationRoleQualifierService getNotificationRoleQualifierService() {
        if (notificationRoleQualifierService == null) {
            notificationRoleQualifierService = KcServiceLocator.getService(KcNotificationRoleQualifierService.class);
        }
        return notificationRoleQualifierService;
    }

    public void setNotificationRoleQualifierService(KcNotificationRoleQualifierService notificationRoleQualifierService) {
        this.notificationRoleQualifierService = notificationRoleQualifierService;
    }

    public NotificationRenderer getRenderer() {
        return renderer;
    }

    public void setRenderer(NotificationRenderer renderer) {
        this.renderer = renderer;
    }

}
