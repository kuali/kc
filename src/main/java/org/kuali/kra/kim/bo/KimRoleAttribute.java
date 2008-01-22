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
 * The KIM Role Attribute represents one attribute (name/value) pair
 * for a Role.  Role's can have zero or more attributes.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class KimRoleAttribute extends KimAttribute {

    private static final long serialVersionUID = -411609041830442521L;
    
    private Long roleId;
 
    /**
     * Get the Role ID this Attribute belongs to.
     * @return the Role ID this Attribute belongs to
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * Set the Role ID that this Attribute belongs to.
     * @param roleId the Role ID the attribute belongs to
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * @see org.kuali.kra.kim.bo.KimAttribute#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> map = super.toStringMapper();
        map.put("roleId", getRoleId());
        return map;
    }
}
