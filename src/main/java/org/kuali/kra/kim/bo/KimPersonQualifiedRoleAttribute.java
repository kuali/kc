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
 * The KIM Person Qualified Role Attribute represents one Qualified Role Attribute
 * on a Role-Person association.
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public class KimPersonQualifiedRoleAttribute extends KimQualifiedRoleAttribute {

    private static final long serialVersionUID = -3834313283054550673L;

    private Long rolePersonId;

    /**
     * Get the Role-Person's ID.
     * @return the Role-Person's ID
     */
    public Long getRolePersonId() {
        return rolePersonId;
    }

    /**
     * Set the Role-Person's ID
     * @param rolePersonId the Role-Person's ID
     */
    public void setRolePersonId(Long rolePersonId) {
        this.rolePersonId = rolePersonId;
    }

    /**
     * @see org.kuali.kra.kim.bo.KimQualifiedRoleAttribute#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> propMap = super.toStringMapper();
        propMap.put("rolePersonId", getRolePersonId());
        return propMap;
    }
}
