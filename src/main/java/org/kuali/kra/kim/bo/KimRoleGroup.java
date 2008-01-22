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
 * A KIM Role-Group is an association between a Role and a Group, i.e.
 * the Group is assigned to the Role.  The Group obtains all of the
 * permissions in the role.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class KimRoleGroup extends PersistableBusinessObjectBase {
    
    private static final long serialVersionUID = 6226604054905776213L;
    
    private Long id;
    private Long roleId;
    private Long groupId;

    /**
     * Get the Role-Group's ID.
     * @return the Role-Group's ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the Role-Group's ID.
     * @param id the Role-Group's ID
     */
    public void setId(Long id) {
        this.id = id;
    }
    
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
     * Get the Group's ID.
     * @return the Group's ID
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * Set the Group's ID.
     * @param groupId the Group's ID
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("id", getId());
        map.put("roleId", getRoleId());
        map.put("groupId", getGroupId());
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
        KimRoleGroup roleGroup = (KimRoleGroup) obj;
        return id.equals(roleGroup.id);
    }
}
