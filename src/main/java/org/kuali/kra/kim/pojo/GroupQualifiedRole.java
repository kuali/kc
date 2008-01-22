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
 * A Group Qualified Role is an association of a Group with a Role with
 * a set of Qualified Role Attributes.
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public class GroupQualifiedRole extends QualifiedRole {

    private static final long serialVersionUID = 4204607464032056642L;

    private String groupName;

    /**
     * Get the Group's name.
     * @return the Group's name
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Set the Group's name.
     * @param groupName the Group's name
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * @see org.kuali.kra.kim.pojo.QualifiedRole#toStringMapper()
     */
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> map = super.toStringMapper();
        map.put("groupName", getGroupName());
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
        GroupQualifiedRole qualifiedRole = (GroupQualifiedRole) obj;
        return groupName.equals(qualifiedRole.groupName) && super.equals(obj);
    }
}