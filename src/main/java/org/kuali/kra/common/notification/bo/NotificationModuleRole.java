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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * 
 * This class allows a coeus module to be associated with specific role names.
 */
public class NotificationModuleRole extends KraPersistableBusinessObjectBase {

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

    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> propMap = new LinkedHashMap<String, Object>();
        propMap.put("moduleCode", getModuleCode());
        propMap.put("roleName", getRoleName());
        return propMap;
    }

}
