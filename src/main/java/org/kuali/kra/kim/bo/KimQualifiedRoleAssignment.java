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

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.kuali.core.bo.PersistableBusinessObjectBase;

/**
 * A KIM Qualified Role Assignment represents an assignment between a Role
 * and a Person/Group.  Qualified Role Assignments have one or more
 * Qualified Role Attributes which set the scope of the Role.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class KimQualifiedRoleAssignment extends PersistableBusinessObjectBase {
    
    private Long id;
    private Long roleId;
   

    /**
     * Get the Qualified Role Assignment's ID.
     * @return the Qualified Role Assignment's ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the Qualified Role Assignment's ID.
     * @param id the Qualified Role Assignment's ID
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Get the ID of Qualified Role Assignment's Role.
     * @return the ID of Qualified Role Assignment's Role
     */
    public Long getRoleId() {
        return roleId;
    }
   
    /**
     * Set the ID of the Qualified Role Assignment's Role.
     * @param roleId the ID of the Qualified Role Assignment's Role
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    
    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("id", getId());
        map.put("roleId", getRoleId());
        return map;
    }
    
    /**
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#refresh()
     */
    public final void refresh() {
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
        KimQualifiedRoleAssignment roleGroup = (KimQualifiedRoleAssignment) obj;
        return roleId.equals(roleGroup.roleId);
    }
    
    /**
     * Get the set of Qualified Role Attributes in a Map.
     * @param qualifiedAttributes the list of Qualified Role Attributes
     * @return a Map of the Qualified Role Attributes.
     */
    protected Map<String, String> getQualifiedRoleAttributeMap(List qualifiedAttributes) {
        Map<String, String> map = new HashMap<String, String>();
        List<KimQualifiedRoleAttribute> qualifiedRoleAttributes = (List<KimQualifiedRoleAttribute>) qualifiedAttributes;
        for (KimQualifiedRoleAttribute qualifiedRoleAttribute : qualifiedRoleAttributes) {
            map.put(qualifiedRoleAttribute.getAttributeName(), qualifiedRoleAttribute.getAttributeValue());
        }
        return map;
    }
    
    /**
     * Is the given set of attributes contained within the Qualified Role Attributes?
     * It is valid for the Qualified Role Attributes to contain name/value pairs that
     * are not in the given set of attributes to look for.
     * @param qualifiedAttributes the list of Qualified Role Attributes
     * @param attributes the set of attributes to search for
     * @return true if the all of the attributes are in the Qualified Role Attributes; otherwise false
     */
    public boolean partialMatch(List qualifiedAttributes, Map<String, String> attributes) {
        if (attributes != null) {
            List<KimQualifiedRoleAttribute> qualifiedRoleAttributes = (List<KimQualifiedRoleAttribute>) qualifiedAttributes;
            Iterator<Entry<String, String>> iterator = attributes.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> entry = iterator.next();
                if (!contains(qualifiedRoleAttributes, entry.getKey(), entry.getValue())) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Does the given set of attributes exactly match the Qualified Role Attributes?
     * @param qualifiedAttributes the list of Qualified Role Attributes
     * @param attributes the set of attributes to compare against
     * @return true if the set of attribute name/value pairs exactly matches 
     *         the Qualified Role Attributes; otherwise false
     */
    public boolean matches(List qualifiedAttributes, Map<String, String> attributes) {
        if (attributes == null) {
            return false;
        }
        else if (attributes.size() != qualifiedAttributes.size()) {
            return false;
        }
        else {
            List<KimQualifiedRoleAttribute> qualifiedRoleAttributes = (List<KimQualifiedRoleAttribute>) qualifiedAttributes;
            Iterator<Entry<String, String>> iterator = attributes.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> entry = iterator.next();
                if (!contains(qualifiedRoleAttributes, entry.getKey(), entry.getValue())) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Does the Qualified Role Attributes contain the given name/value pair?
     * @param qualifiedRoleAttributes the list of Qualified Role Attributes
     * @param attrName the attribute name to look for
     * @param attrValue the attribute value to look for
     * @return true if the Qualified Role Attributes has this name/value pair; otherwise false
     */
    private boolean contains(List<KimQualifiedRoleAttribute> qualifiedRoleAttributes, String attrName, String attrValue) {
        for (KimQualifiedRoleAttribute qualifiedRoleAttribute : qualifiedRoleAttributes) {
            if (qualifiedRoleAttribute.matches(attrName, attrValue)) {
                return true;
            }
        }
        return false;
    }
}
