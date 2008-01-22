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
 * A KIM Role-Person is an association between a Role and a Person, i.e.
 * the Person is assigned to the Role.  The Person obtains all of the
 * permissions in the role.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class KimRolePerson extends PersistableBusinessObjectBase {
    
    private static final long serialVersionUID = -13700594521231518L;
    
    private Long id;
    private Long roleId;
    private Long personId;

    /**
     * Get the Role-Person's ID.
     * @return the Role-Person's ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the Role-Person's ID.
     * @param id the Role-Person's ID
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
     * Get the Person's ID.
     * @return the Person's ID
     */
    public Long getPersonId() {
        return personId;
    }

    /**
     * Set the Person's ID.
     * @param personId the Person's ID
     */
    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("id", getId());
        map.put("roleId", getRoleId());
        map.put("personId", getPersonId());
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
        KimRolePerson rolePerson = (KimRolePerson) obj;
        return id.equals(rolePerson.id);
    }
}
