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
 * The KIM Attribute is the base class for all attributes in KIM.
 * An attribute is a name/value pair.  The type identifies the type
 * of data stored in the value.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class KimAttribute extends PersistableBusinessObjectBase {

	private static final long serialVersionUID = -2255690191635455239L;
	
	private Long id;
	private Long attributeTypeId;
	private String attributeName;
	private String attributeValue;
	
	/**
	 * Get the Group Attribute's ID.
	 * @return the Groups Attribute's ID
	 */
	public Long getId() {
        return id;
    }

    /**
     * Set the Group Attribute's ID.
     * @param id the Group Attribute's ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the Group Attribute's Type ID.
     * @return the Group Attribute's Type ID
     */
    public Long getAttributeTypeId() {
        return attributeTypeId;
    }

    /**
     * Set the Group Attribute's Type ID.
     * @param attributeTypeId the Group Attribute's Type ID
     */
    public void setAttributeTypeId(Long attributeTypeId) {
        this.attributeTypeId = attributeTypeId;
    }
    
	/**
	 * Get the Group Attribute's name.
	 * @return the Group Attribute's name
	 */
	public String getAttributeName() {
		return attributeName;
	}

	/**
	 * Set the Group Attribute's name.
	 * @param attributeName the Group Attribute's name
	 */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	/**
	 * Get the Group Attribute's value.
	 * @return the Group Attribute's value.
	 */
	public String getAttributeValue() {
		return attributeValue;
	}

	/**
	 * Set the Group Attribute's value.
	 * @param attributeValue the Group Attribute's value
	 */
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	/**
	 * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
	 */
	protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("id", getId());
        map.put("attributeTypeId", getAttributeTypeId());
        map.put("attributeName", getAttributeName());
        map.put("attributeValue", getAttributeValue());
        return map;
	}

	/**
	 * @see org.kuali.core.bo.PersistableBusinessObjectBase#refresh()
	 */
	public final void refresh() {
		// not going to add unless needed
	}

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public final boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (!this.getClass().equals(obj.getClass())) return false;
        KimAttribute attr = (KimAttribute)obj;
        return id.equals(attr.id);
    }
}
