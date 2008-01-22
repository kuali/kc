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
 * This class defines the concept of an attribute type. Given KIM's need for dynamic lists of attributes attached to various
 * entities in the system, this class organizes or categorizes the type of attribute. For example, one instance of attribute type
 * could be "text" or "radio button". Every attribute attached to a person, group, etc in KIM will need to have an attribute type
 * associated with it.
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public class AttributeType extends PersistableBusinessObjectBase {

    private static final long serialVersionUID = -3856630570406063764L;
    private Long id;
    private String attributeTypeName;
    private String description;

    /**
     * This method retrieves the attribute type name.
     * 
     * @return String
     */
    public String getAttributeTypeName() {
        return attributeTypeName;
    }

    /**
     * This method sets the attribute type name.
     * 
     * @param attributeTypeName
     */
    public void setAttributeTypeName(String attributeTypeName) {
        this.attributeTypeName = attributeTypeName;
    }

    /**
     * This method retrieves the unique id (primary key) for an attribute type instance.
     * 
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * This method sets the unique id (primary key) for an attribute type instance.
     * 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> propMap = new LinkedHashMap<String, Object>();
        propMap.put("id", getId());
        propMap.put("attributeTypeName", getAttributeTypeName());
        return propMap;
    }

    /**
     * @see org.kuali.core.bo.BusinessObject#refresh()
     */
    public void refresh() {
        // not doing this unless needed
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
        AttributeType attrType = (AttributeType) obj;
        return id.equals(attrType.id);
    }
}
