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

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class allows a coeus module to be associated with specific role names.
 */
public class NotificationModuleRole extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = -2991839907693163026L;

    private Long notificationModuleRoleId;

    private String moduleCode;

    private String roleName;

    private List<NotificationModuleRoleQualifier> roleQualifiers = new ArrayList<NotificationModuleRoleQualifier>();

    public Long getNotificationModuleRoleId() {
        return notificationModuleRoleId;
    }

    public void setNotificationModuleRoleId(Long notificationModuleRoleId) {
        this.notificationModuleRoleId = notificationModuleRoleId;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<NotificationModuleRoleQualifier> getRoleQualifiers() {
        return roleQualifiers;
    }

    public void setRoleQualifiers(List<NotificationModuleRoleQualifier> roleQualifiers) {
        this.roleQualifiers = roleQualifiers;
    }

    @Override
    public List buildListOfDeletionAwareLists() {
        List deleteAwareList = super.buildListOfDeletionAwareLists();
        deleteAwareList.add(getRoleQualifiers());
        
        return deleteAwareList;
    }    
}
