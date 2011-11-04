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

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.rice.kim.bo.types.dto.AttributeSet;

/**
 * Defines the recipients for a {@code NotificationType}.
 */
public class NotificationTypeRecipient extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = -1455607096529901879L;
    
    private Long notificationTypeRecipientId;
    private Long notificationTypeId;
    private String roleName;
    private String toOrCC;
    
    // Non-persistent field for tracking the qualifier value.
    private AttributeSet roleQualifiers;
    
    // Fields for ad-hoc notifications
    private String personId;
    private String rolodexId;
    private String fullName;

    public Long getNotificationTypeRecipientId() {
        return notificationTypeRecipientId;
    }

    public void setNotificationTypeRecipientId(Long notificationTypeRecipientId) {
        this.notificationTypeRecipientId = notificationTypeRecipientId;
    }

    public Long getNotificationTypeId() {
        return notificationTypeId;
    }

    public void setNotificationTypeId(Long notificationTypeId) {
        this.notificationTypeId = notificationTypeId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getToOrCC() {
        return toOrCC;
    }

    public void setToOrCC(String toOrCC) {
        this.toOrCC = toOrCC;
    }

    public AttributeSet getRoleQualifiers() {
        return roleQualifiers;
    }

    public void setRoleQualifiers(AttributeSet roleQualifiers) {
        this.roleQualifiers = roleQualifiers;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getRolodexId() {
        return rolodexId;
    }

    public void setRolodexId(String rolodexId) {
        this.rolodexId = rolodexId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> propMap = new LinkedHashMap<String, Object>();
        propMap.put("notificationTypeRecipientId", getNotificationTypeRecipientId());
        propMap.put("notificationTypeId", getNotificationTypeId());
        propMap.put("roleName", getRoleName());
        propMap.put("toOrCC", getToOrCC());
        return propMap;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((notificationTypeId == null) ? 0 : notificationTypeId.hashCode());
        result = prime * result + ((notificationTypeRecipientId == null) ? 0 : notificationTypeRecipientId.hashCode());
        result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
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
        NotificationTypeRecipient other = (NotificationTypeRecipient) obj;
        if (notificationTypeId == null) {
            if (other.notificationTypeId != null) {
                return false;
            }
        } else if (!notificationTypeId.equals(other.notificationTypeId)) {
            return false;
        }
        if (notificationTypeRecipientId == null) {
            if (other.notificationTypeRecipientId != null) {
                return false;
            }
        } else if (!notificationTypeRecipientId.equals(other.notificationTypeRecipientId)) {
            return false;
        }
        if (roleName == null) {
            if (other.roleName != null) {
                return false;
            }
        } else if (!roleName.equals(other.roleName)) {
            return false;
        }

        return true;
    }
    
}