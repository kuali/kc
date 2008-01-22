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
package org.kuali.kra.kim.pojo;

import java.util.LinkedHashMap;

/**
 * A Person Qualified Role is an association between a Person and a Role
 * with a set of Qualified Role Attributes.
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public class PersonQualifiedRole extends QualifiedRole {

    private static final long serialVersionUID = -5342309199310249713L;

    private String username;

    /**
     * Get the Person's username.
     * @return the Person's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the Person's username.
     * @param username the Person's username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @see org.kuali.kra.kim.pojo.QualifiedRole#toStringMapper()
     */
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> map = super.toStringMapper();
        map.put("username", getUsername());
        return map;
    }
    
    /**
     * @see org.kuali.kra.kim.pojo.QualifiedRole#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (!obj.getClass().equals(this.getClass()))
            return false;
        PersonQualifiedRole qualifiedRole = (PersonQualifiedRole) obj;
        return username.equals(qualifiedRole.username) && super.equals(obj);
    }
}
