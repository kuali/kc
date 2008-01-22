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
import java.util.List;
import java.util.Map;

/**
 * A KIM Qualified Role Person represents an assignment between a Role
 * and a Person.  A Qualified Role-Person has a set of Qualified Role
 * Attributes which set of the scope of the Role.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class KimQualifiedRolePerson extends KimQualifiedRoleAssignment {
    
    private static final long serialVersionUID = -13700594521231518L;
    
    private Long personId;
    private List<KimPersonQualifiedRoleAttribute> qualifiedRoleAttributes;

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
     * Get the Qualified Role Assignment's Qualified Role Attributes.
     * @return the Qualified Role Attributes
     */
    public List<KimPersonQualifiedRoleAttribute> getQualifiedRoleAttributes() {
        return qualifiedRoleAttributes;
    }
    
    /**
     * Set the Qualified Role Assignment's Qualified Role Attributes.
     * @param qualifiedRoleAttributes the Qualified Role Attributes
     */
    public void setQualifiedRoleAttributes(List<KimPersonQualifiedRoleAttribute> qualifiedRoleAttributes) {
        this.qualifiedRoleAttributes = qualifiedRoleAttributes;
    }

    /**
     * Get the set of Qualified Role Attributes in a Map.
     * @return a Map of the Qualified Role Attributes.
     */
    public Map<String, String> getQualifiedRoleAttributeMap() {
        return getQualifiedRoleAttributeMap(qualifiedRoleAttributes);
    }
    
    /**
     * Is the given set of attributes contained within the Qualified Role Attributes?
     * It is valid for the Qualified Role Attributes to contain name/value pairs that
     * are not in the given set of attributes to look for.
     * @param attributes the set of attributes to search for
     * @return true if the all of the attributes are in the Qualified Role Attributes; otherwise false
     */
    public boolean partialMatch(Map<String, String> attributes) {
        return partialMatch(qualifiedRoleAttributes, attributes);
    }
    
    /**
     * Does the given set of attributes exactly match the Qualified Role Attributes?
     * @param attributes the set of attributes to compare against
     * @return true if the set of attribute name/value pairs exactly matches 
     *         the Qualified Role Attributes; otherwise false
     */
    public boolean matches(Map<String, String> attributes) {
        return matches(qualifiedRoleAttributes, attributes);
    }
    
    /**
     * @see org.kuali.kra.kim.bo.KimQualifiedRoleAssignment#toStringMapper()
     */
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> map = super.toStringMapper();
        map.put("personId", getPersonId());
        return map;
    }
    
    /**
     * @see org.kuali.kra.kim.bo.KimQualifiedRoleAssignment#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        KimQualifiedRolePerson rolePerson = (KimQualifiedRolePerson) obj;
        return personId.equals(rolePerson.personId);
    }
}
