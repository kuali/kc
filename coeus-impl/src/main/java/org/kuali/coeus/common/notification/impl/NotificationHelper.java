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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.common.api.rolodex.RolodexService;
import org.kuali.coeus.common.notification.impl.NotificationContext;
import org.kuali.coeus.common.notification.impl.bo.KcNotification;
import org.kuali.coeus.common.notification.impl.bo.NotificationType;
import org.kuali.coeus.common.notification.impl.bo.NotificationTypeRecipient;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines the base helper for sending ad-hoc notifications.
 * 
 * @param <T> the notification context
 */
public class NotificationHelper<T extends NotificationContext> implements Serializable {

    private static final long serialVersionUID = -3405035537522284062L;
    
    private NotificationTypeRecipient newNotificationRecipient;
    private List<NotificationTypeRecipient> notificationRecipients;
    private KcNotification notification;
    
    private T notificationContext;
    
    private String newRoleId;
    private String newPersonId;
    private String newRolodexId;
    
    private transient KcNotificationService notificationService;
    private transient RoleService roleService;
    private transient KcPersonService kcPersonService;
    private transient RolodexService rolodexService;
    

    public NotificationHelper() {
        this.setNewNotificationRecipient(new NotificationTypeRecipient());
        this.setNotificationRecipients(new ArrayList<NotificationTypeRecipient>());
        this.setNotification(new KcNotification());
    }

    public NotificationTypeRecipient getNewNotificationRecipient() {
        return newNotificationRecipient;
    }

    public void setNewNotificationRecipient(NotificationTypeRecipient newNotificationRecipient) {
        this.newNotificationRecipient = newNotificationRecipient;
    }

    public List<NotificationTypeRecipient> getNotificationRecipients() {
        return notificationRecipients;
    }

    public void setNotificationRecipients(List<NotificationTypeRecipient> notificationRecipients) {
        this.notificationRecipients = notificationRecipients;
    }

    public KcNotification getNotification() {
        return notification;
    }
    
    public void setNotification(KcNotification notification) {
        this.notification = notification;
    }
    
    public T getNotificationContext() {
        return notificationContext;
    }
    
    public void setNotificationContext(T notificationContext) {
        this.notificationContext = notificationContext;
    }

    public String getNewRoleId() {
        return newRoleId;
    }

    /**
     * Sets the {@code newRoleId} while nulling the other id values.
     * 
     * @param newRoleId the new role id
     */
    public void setNewRoleId(String newRoleId) {
        this.newRoleId = newRoleId;
        this.newPersonId = null;
        this.newRolodexId = null;
    }

    public String getNewPersonId() {
        return newPersonId;
    }

    /**
     * Sets the {@code newPersonId} while nulling the other id values.
     * 
     * @param newPersonId the new person id
     */
    public void setNewPersonId(String newPersonId) {
        this.newRoleId = null;
        this.newPersonId = newPersonId;
        this.newRolodexId = null;
    }

    public String getNewRolodexId() {
        return newRolodexId;
    }

    /**
     * Sets the {@code newRolodexId} while nulling the other id values.
     * 
     * @param newRolodexId the new rolodex id
     */
    public void setNewRolodexId(String newRolodexId) {
        this.newRoleId = null;
        this.newPersonId = null;
        this.newRolodexId = newRolodexId;
    }
    
    /**
     * Initializes the helper with the default values from the Notification Type.
     * 
     * @param context the notification context
     */
    public void initializeDefaultValues(T context) {
        getNotificationRecipients().clear();
        NotificationType notificationType = getNotificationService().getNotificationType(context);
        if (notificationType != null) {
            for (NotificationTypeRecipient notificationRecipient : notificationType.getNotificationTypeRecipients()) {
                notificationRecipient.setFullName(notificationRecipient.getRoleName());
                getNotificationRecipients().add(notificationRecipient);
            }
        }
        
        setNotification(getNotificationService().createNotificationObject(context));
        
        setNotificationContext(context);
    }
    
    /**
     * Prepares the user view from the context.
     */
    public void prepareView() {        
        if (StringUtils.isNotBlank(getNewRoleId())) {
            Role role = getRoleService().getRole(getNewRoleId());
            String roleName = role.getNamespaceCode() + ":" + role.getName();
            getNewNotificationRecipient().setRoleName(roleName);
            getNewNotificationRecipient().setPersonId(null);
            getNewNotificationRecipient().setRolodexId(null);
            getNewNotificationRecipient().setFullName(roleName);
        } else if (StringUtils.isNotBlank(getNewPersonId())) {
            getNewNotificationRecipient().setRoleName(null);
            KcPerson person = getKcPersonService().getKcPersonByPersonId(getNewPersonId());
            getNewNotificationRecipient().setPersonId(person.getPersonId());
            getNewNotificationRecipient().setFullName(person.getFullName());
            getNewNotificationRecipient().setRolodexId(null);
        } else if (StringUtils.isNotBlank(getNewRolodexId())) {
            getNewNotificationRecipient().setRoleName(null);
            getNewNotificationRecipient().setPersonId(null);
            RolodexContract rolodex = getRolodexService().getRolodex(Integer.valueOf(getNewRolodexId()));
            getNewNotificationRecipient().setRolodexId(rolodex.getRolodexId().toString());
            getNewNotificationRecipient().setFullName(rolodex.getFullName());
        }
    }

    /**
     * Determines whether the ad-hoc notification editor for this context should be shown.
     * 
     * @param context the notification context
     * 
     * @return true if the ad-hoc notification editor should be shown, false otherwise
     */
    public boolean getPromptUserForNotificationEditor(T context) {
        boolean promptUser = false;
        
        NotificationType notificationType = getNotificationService().getNotificationType(context);
        if (notificationType != null && notificationType.isActive() && notificationType.getPromptUser()) {
            promptUser = true;
        }
        
        return promptUser;
    }
    
    /**
     * Sends the ad-hoc notification.
     */
    public void sendNotification() {
        getNotificationService().sendNotification(notificationContext, notification, notificationRecipients);
    }
    public void sendNotificationAndPersist(KcNotification notification, KcPersistableBusinessObjectBase object) {
        notification.setMessage(getNotification().getMessage());
        notification.setSubject(getNotification().getSubject());
        getNotificationService().sendNotificationAndPersist(notificationContext, notification, notificationRecipients, object);
    }

    public KcNotificationService getNotificationService() {
        if (notificationService == null) {
            notificationService = KcServiceLocator.getService(KcNotificationService.class);
        }
        
        return notificationService;
    }

    public void setNotificationService(KcNotificationService notificationService) {
        this.notificationService = notificationService;
    }
    
    public KcPersonService getKcPersonService() {
        if (kcPersonService == null) {
            kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        
        return kcPersonService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    public RolodexService getRolodexService() {
        if (rolodexService == null) {
            rolodexService = KcServiceLocator.getService(RolodexService.class);
        }
        
        return rolodexService;
    }
    
    public void setRolodexService(RolodexService rolodexService) {
        this.rolodexService = rolodexService;
    }

    public RoleService getRoleService() {
        if (roleService == null) {
            roleService = KimApiServiceLocator.getRoleService();
        }
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

}
