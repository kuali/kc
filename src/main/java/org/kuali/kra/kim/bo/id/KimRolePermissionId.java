/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.kim.bo.id;

import java.io.Serializable;

import javax.persistence.Column;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Primary Key for the KimRolePermission BO.
 */
@SuppressWarnings("serial")
public class KimRolePermissionId implements Serializable {
    
    @Column(name="ROLE_ID")
    private Long roleId;

    @Column(name="PERMISSION_ID")
    private Long permissionId;
    
    public Long getRoleId() {
        return this.roleId;
    }
    
    public Long getPermissionId() {
        return this.permissionId;
    }
    
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof KimRolePermissionId)) return false;
        if (obj == null) return false;
        KimRolePermissionId other = (KimRolePermissionId) obj;
        return ObjectUtils.equals(roleId, other.roleId) &&
               ObjectUtils.equals(permissionId, other.permissionId);
    }
    
    public int hashCode() {
        return new HashCodeBuilder().append(roleId).append(permissionId).toHashCode();
    }
}
