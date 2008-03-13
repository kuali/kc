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
package org.kuali.kra.bo;

import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.core.bo.BusinessObjectBase;

/**
 * The RolePersons BO is simply a role name with a list of the
 * Persons in that role.
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class RolePersons extends BusinessObjectBase {
    
    private String roleName;
    private List<Person> persons;
    
    /**
     * Constructs a RolePersons.
     */
    public RolePersons() {
        this.roleName = null;
        this.persons = null;
    }
    
    /**
     * Get the role name.
     * @return the role name
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Set the role name.
     * @param roleName the role name
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Get the persons in the role.
     * @return the persons in the role
     */
    public List<Person> getPersons() {
        return persons;
    }

    /**
     * Set the persons in the role.
     * @param persons the persons in the role
     */
    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap map = new LinkedHashMap();
        map.put("roleName", this.getRoleName());
        map.put("persons", getPersons());
        return map;
    }

    public void refresh() {
        // do nothing
    }
}
