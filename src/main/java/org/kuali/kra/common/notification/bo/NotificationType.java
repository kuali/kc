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
package org.kuali.kra.common.notification.bo;

import java.util.List;

import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.springframework.util.AutoPopulatingList;

/**
 * Defines the template for Notifications.
 */
public class NotificationType extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1777340179839083316L;
    
    private Long notificationTypeId;
    private String moduleCode;
    private String actionCode;
    private String description;
    private String subject;
    private String message;
    private boolean promptUser;
    private boolean sendNotification;
    
    private CoeusModule coeusModule;
    
    @SuppressWarnings("unchecked")
    private List<NotificationTypeRecipient> notificationTypeRecipients = new AutoPopulatingList<NotificationTypeRecipient>(NotificationTypeRecipient.class);

    public Long getNotificationTypeId() {
        return notificationTypeId;
    }

    public void setNotificationTypeId(Long notificationTypeId) {
        this.notificationTypeId = notificationTypeId;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getPromptUser() {
        return promptUser;
    }

    public void setPromptUser(boolean promptUser) {
        this.promptUser = promptUser;
    }

    public boolean getSendNotification() {
        return sendNotification;
    }

    public void setSendNotification(boolean sendNotification) {
        this.sendNotification = sendNotification;
    }

    public CoeusModule getCoeusModule() {
        return coeusModule;
    }

    public void setCoeusModule(CoeusModule coeusModule) {
        this.coeusModule = coeusModule;
    }
    
    public List<NotificationTypeRecipient> getNotificationTypeRecipients() {
        return notificationTypeRecipients;
    }

    public void setNotificationTypeRecipients(List<NotificationTypeRecipient> notificationTypeRecipients) {
        this.notificationTypeRecipients = notificationTypeRecipients;
    }

    @Override
    public List buildListOfDeletionAwareLists() {
        List deleteAwareList = super.buildListOfDeletionAwareLists();
        deleteAwareList.add(getNotificationTypeRecipients());
        return deleteAwareList;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((actionCode == null) ? 0 : actionCode.hashCode());
        result = prime * result + ((moduleCode == null) ? 0 : moduleCode.hashCode());
        result = prime * result + ((notificationTypeId == null) ? 0 : notificationTypeId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        NotificationType other = (NotificationType) obj;
        if (actionCode == null) {
            if (other.actionCode != null) {
                return false;
            }
        } else if (!actionCode.equals(other.actionCode)) {
            return false;
        }
        if (moduleCode == null) {
            if (other.moduleCode != null) {
                return false;
            }
        } else if (!moduleCode.equals(other.moduleCode)) {
            return false;
        }
        if (notificationTypeId == null) {
            if (other.notificationTypeId != null) {
                return false;
            }
        } else if (!notificationTypeId.equals(other.notificationTypeId)) {
            return false;
        }
        return true;
    }

}