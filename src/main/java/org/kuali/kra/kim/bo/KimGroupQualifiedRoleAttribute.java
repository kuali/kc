/*
 * Copyright 2007 The Kuali Foundation
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

/**
 * The KIM Group Qualified Role Attribute represents one Qualified Role Attribute
 * on a Role-Group association.
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public class KimGroupQualifiedRoleAttribute extends KimQualifiedRoleAttribute {

    private static final long serialVersionUID = 6701917498866245651L;

    private Long roleGroupId;
    
    /**
     * Get the Role-Group's ID.
     * @return the Role-Group's ID
     */
    public Long getRoleGroupId() {
        return roleGroupId;
    }
    
    /**
     * Set the Role-Group's ID.
     * @param roleGroupId the Role-Group's ID
     */
    public void setRoleGroupId(Long roleGroupId) {
        this.roleGroupId = roleGroupId;
    }
    
    /**
     * @see org.kuali.kra.kim.bo.KimQualifiedRoleAttribute#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> propMap = super.toStringMapper();
        propMap.put("roleGroupId", getRoleGroupId());
        return propMap;
    }
}