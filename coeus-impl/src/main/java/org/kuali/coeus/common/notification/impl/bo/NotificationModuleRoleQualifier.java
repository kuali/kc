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
package org.kuali.coeus.common.notification.impl.bo;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * 
 * This class allows a relationship between role names and role qualifiers to be defined.
 */
public class NotificationModuleRoleQualifier extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 19728350917756271L;

    private Long notificationModuleRoleQualifierId;

    private Long notificationModuleRoleId;

    private String qualifier;

    public Long getNotificationModuleRoleQualifierId() {
        return notificationModuleRoleQualifierId;
    }

    public void setNotificationModuleRoleQualifierId(Long notificationModuleRoleQualifierId) {
        this.notificationModuleRoleQualifierId = notificationModuleRoleQualifierId;
    }

    public Long getNotificationModuleRoleId() {
        return notificationModuleRoleId;
    }

    public void setNotificationModuleRoleId(Long notificationModuleRoleId) {
        this.notificationModuleRoleId = notificationModuleRoleId;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }
}
