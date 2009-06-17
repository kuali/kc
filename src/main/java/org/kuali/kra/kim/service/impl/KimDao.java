/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.kim.service.impl;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.kuali.kra.kim.bo.KimQualifiedRolePerson;

/**
 * The KIM DAO has specialized methods for querying the database.
 * The KIM DAO was added in order to improve performance.
 */
public interface KimDao {
    
    /**
     * Gets a list of the qualified roles for a person based upon the given qualified attributes.
     * Actually, this is really a hack. As long as a qualified role has the first qualified attribute
     * entry in the map, the role is returned.
     * @param personId the person's ID
     * @param qualifiedRoleAttributes the qualified attributes (only the first is used)
     * @return the list of qualified roles having the first qualified attribute
     */
    public Collection<KimQualifiedRolePerson> getPersonQualifiedRoles(Long personId, Map<String, String> qualifiedRoleAttributes);

    /**
     * Gets a list of the persons (IDs) of a specified role based upon the given qualified attributes.
     * As long as a qualified role has the first qualified attribute
     * entry in the map, the role is returned.
     * @param roleId the Role ID
     * @param qualifiedRoleAttributes the qualified attributes (only the first is used)
     * @return the list of persons (IDs) having the first qualified attribute
     */
    public Set<Long> getQualifiedRolePersonIds(Long roleId, Map<String, String> qualifiedRoleAttributes);

    /**
     * Do any of the given roles have the given permission?
     * @param roleIds the set of roles
     * @param permissionId the permission to look for
     * @return true if one or more of the roles has the given permission; otherwise false
     */
    public boolean hasPermission(Collection<Long> roleIds, Long permissionId);
}
