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

import org.kuali.core.bo.PersistableBusinessObjectBase;

/**
 * A KIM Qualified Role Attribute is one name/value pair qualification
 * on an association between a Role and a Person/Group.  For example,
 * Person "Bob" might be assigned the Role "Fiscal Officer" with the
 * qualification [Account, XYZ], where "Account" is the attribute name
 * and "XYZ" is the attribute value.  As can be seen, qualifications
 * define the scope where the role is applicable.
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public abstract class KimQualifiedRoleAttribute extends PersistableBusinessObjectBase {

    private static final long serialVersionUID = -5155126090045426553L;

    private Long id;
    private String attributeName;
    private String attributeValue;

    /**
     * Get the Qualified Role Attribute's ID.
     * @return the Qualified Role Attribute's ID
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Set the Qualified Role Attribute's ID.
     * @param id the Qualified Role Attribute's ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the Qualified Role Attribute's name.
     * @return the Qualified Role Attribute's name
     */
    public String getAttributeName() {
        return this.attributeName;
    }

    /**
     * Set the Qualified Role Attribute's name.
     * @param attributeName the Qualified Role Attribute's name
     */
    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    /**
     * Get the Qualified Role Attribute's value.
     * @return the Qualified Role Attribute's value
     */
    public String getAttributeValue() {
        return this.attributeValue;
    }

    /**
     * Set the Qualified Role Attribute's value.
     * @param attributeValue the Qualified Role Attribute's value
     */
    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> propMap = new LinkedHashMap<String, Object>();
        propMap.put("id", getId());
        propMap.put("attributeName", getAttributeName());
        propMap.put("attributeValue", getAttributeValue());
        return propMap;
    }

    /**
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#refresh()
     */
    public final void refresh() {
        // not going to implement unless needed
    }

    /**
     * Does this Qualified Role Attribute match the given name/value pair?
     * @param attrName the attribute's name
     * @param attrValue the attribute's value
     * @return true if the name/value pair matches; otherwise false
     */
    public boolean matches(String attrName, String attrValue) {
        return this.attributeName.equals(attrName) && this.attributeValue.equals(attrValue);
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public final boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (!obj.getClass().equals(this.getClass()))
            return false;
        KimQualifiedRoleAttribute qualifiedRole = (KimQualifiedRoleAttribute) obj;
        return getId().equals(qualifiedRole.getId());
    }
}
