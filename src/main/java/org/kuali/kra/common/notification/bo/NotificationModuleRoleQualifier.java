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

/**
 * 
 * This class allows a relationship between role names and role qualifiers to be defined.
 */
public class NotificationModuleRoleQualifier extends KraPersistableBusinessObjectBase {

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

    
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> propMap = new LinkedHashMap<String, Object>();
        propMap.put("notificationModuleRoleQualifierId", getNotificationModuleRoleQualifierId());
        propMap.put("notificationModuleRoleId", getNotificationModuleRoleId());        
        propMap.put("qualifier", getQualifier());
        return propMap;
    }
}
