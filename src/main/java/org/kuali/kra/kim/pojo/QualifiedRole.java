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
import java.util.Map;

/**
 * A Qualfied Role is an association between a Role and a Person or Group with
 * a set of Qualified Role Attributes.
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public class QualifiedRole extends Pojo {

    private static final long serialVersionUID = -8494746786964987675L;
    
    private String roleName = null;
    private Map<String, String> qualifiedAttributes = null;

    /**
     * Get the Role's name.
     * @return the Role's name
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Set the Role's name.
     * @param roleName the Role's name
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Get the Qualified Role Attributes.
     * @return the Qualified Role Attributes
     */
    public Map<String, String> getQualifiedRoleAttributes() {
        return qualifiedAttributes;
    }

    /**
     * Set the Qualified Role Attributes.
     * @param qualifiedRoleAttributes the Qualified Role Attributes.
     */
    public void setQualifiedRoleAttributes(Map<String, String> qualifiedRoleAttributes) {
        this.qualifiedAttributes = qualifiedRoleAttributes;
    }

    /**
     * @see org.kuali.kra.kim.pojo.Pojo#toStringMapper()
     */
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("roleName", getRoleName());
        return map;
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
        QualifiedRole qualifiedRole = (QualifiedRole) obj;
        
        if (!roleName.equals(qualifiedRole.roleName)) {
            return false;
        }
        
        if (qualifiedAttributes.size() != qualifiedRole.qualifiedAttributes.size()) {
            return false;
        }
        
        return qualifiedAttributes.equals(qualifiedRole.qualifiedAttributes);
    }
}
