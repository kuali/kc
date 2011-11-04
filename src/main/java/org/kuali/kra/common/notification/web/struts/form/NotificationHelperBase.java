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
import org.kuali.rice.kim.bo.Role;
import org.kuali.rice.kim.service.KIMServiceLocator;
import org.kuali.rice.kim.service.RoleService;

public abstract class NotificationHelperBase<T extends NotificationContext> implements Serializable {
    
    private static final long serialVersionUID = 5566270961613461008L;
        
    private NotificationTypeRecipient newNotificationRecipient;
    private List<NotificationTypeRecipient> notificationRecipients;
    private KcNotification notification;
    
    private String newRoleId;
    private String newPersonId;
    private String newRolodexId;
    
    private transient KcNotificationService notificationService;
    private transient RoleService roleService;
    private transient KcPersonService kcPersonService;
    private transient RolodexService rolodexService;
    
    public NotificationHelperBase() {
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

    public String getNewRoleId() {
        return newRoleId;
    }

    public void setNewRoleId(String newRoleId) {
        this.newRoleId = newRoleId;
        this.newPersonId = null;
        this.newRolodexId = null;
    }

    public String getNewPersonId() {
        return newPersonId;
    }

    public void setNewPersonId(String newPersonId) {
        this.newRoleId = null;
        this.newPersonId = newPersonId;
        this.newRolodexId = null;
    }

    public String getNewRolodexId() {
        return newRolodexId;
    }

    public void setNewRolodexId(String newRolodexId) {
        this.newRoleId = null;
        this.newPersonId = null;
        this.newRolodexId = newRolodexId;
    }

    public abstract T getContext();
    
    /**
     * Prepares the user view from the context.
     */
    public void prepareView() {        
        if (StringUtils.isNotBlank(getNewRoleId())) {
            Role role = getRoleService().getRole(getNewRoleId());
            String roleName = role.getNamespaceCode() + ":" + role.getRoleName();
            getNewNotificationRecipient().setRoleName(roleName);
            getNewNotificationRecipient().setFullName(roleName);
        } else if (StringUtils.isNotBlank(getNewPersonId())) {
            KcPerson person = getKcPersonService().getKcPersonByPersonId(getNewPersonId());
            getNewNotificationRecipient().setPersonId(person.getPersonId());
            getNewNotificationRecipient().setFullName(person.getFullName());
        } else if (StringUtils.isNotBlank(getNewRolodexId())) {
            Rolodex rolodex = getRolodexService().getRolodex(Integer.valueOf(getNewRolodexId()));
            getNewNotificationRecipient().setRolodexId(rolodex.getRolodexId().toString());
            getNewNotificationRecipient().setFullName(rolodex.getFullName());
        }
    
        NotificationType notificationType = getNotificationService().getNotificationType(getContext());
        if (notificationType != null) {
            for (NotificationTypeRecipient notificationRecipient : notificationType.getNotificationTypeRecipients()) {
                if (!getNotificationRecipients().contains(notificationRecipient)) {
                    notificationRecipient.setFullName(notificationRecipient.getRoleName());
                    getNotificationRecipients().add(notificationRecipient);
                }
            }
        }
        
        setNotification(getNotificationService().createNotification(getContext()));
    }

    public boolean getPromptUserForNotificationEditor() {
        boolean promptUser = false;
        
        NotificationType notificationType = getNotificationService().getNotificationType(getContext());
        if (notificationType != null && notificationType.getPromptUser()) {
            promptUser = true;
        }
        
        return promptUser;
    }
    
    public void sendNotification() {
        getNotificationService().sendNotification(notification, notificationRecipients, getContext());
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
            roleService = KIMServiceLocator.getRoleService();
        }
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

}