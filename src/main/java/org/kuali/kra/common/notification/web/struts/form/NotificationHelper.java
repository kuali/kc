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
package org.kuali.kra.common.notification.web.struts.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.common.notification.NotificationContext;
import org.kuali.kra.common.notification.bo.KcNotification;
import org.kuali.kra.common.notification.bo.NotificationType;
import org.kuali.kra.common.notification.bo.NotificationTypeRecipient;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.RolodexService;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;

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
    
    /**
     * Constructs a NotificationHelperBase.
     */
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
        
        setNotification(getNotificationService().createNotification(context));
        
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
            Rolodex rolodex = getRolodexService().getRolodex(Integer.valueOf(getNewRolodexId()));
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

    public KcNotificationService getNotificationService() {
        if (notificationService == null) {
            notificationService = KraServiceLocator.getService(KcNotificationService.class);
        }
        
        return notificationService;
    }

    public void setNotificationService(KcNotificationService notificationService) {
        this.notificationService = notificationService;
    }
    
    public KcPersonService getKcPersonService() {
        if (kcPersonService == null) {
            kcPersonService = KraServiceLocator.getService(KcPersonService.class);
        }
        
        return kcPersonService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    public RolodexService getRolodexService() {
        if (rolodexService == null) {
            rolodexService = KraServiceLocator.getService(RolodexService.class);
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