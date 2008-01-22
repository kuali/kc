/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.kim.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.PersistableBusinessObjectBase;

/**
 * A KIM Role-Permission is the assignment of a Permission to Role.
 * Roles can have zero or more permissions.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class KimRolePermission extends PersistableBusinessObjectBase {
    
    private static final long serialVersionUID = 6226604054905776213L;
    
    private Long roleId;
    private Long permissionId;

    /**
     * Get the Role's ID.
     * @return the Role's ID
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * Set the Role's ID.
     * @param roleId the Role's ID
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * Get the Permission's ID.
     * @return the Permission's ID
     */
    public Long getPermissionId() {
        return permissionId;
    }

    /**
     * Set the Permission's ID.
     * @param permissionId the Permission's ID
     */
    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("roleId", getRoleId());
        map.put("permissionId", getPermissionId());
        return map;
    }
    
    /**
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#refresh()
     */
    public void refresh() {
        // not going to implement unless necessary
    }
    
    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (!obj.getClass().equals(this.getClass()))
            return false;
        KimRolePermission rolePermission = (KimRolePermission) obj;
        return roleId.equals(rolePermission.roleId) &&
               permissionId.equals(rolePermission.permissionId);
    }
}
