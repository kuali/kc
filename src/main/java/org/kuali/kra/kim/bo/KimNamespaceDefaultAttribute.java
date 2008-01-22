/* Copyright 2007 The Kuali Foundation
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
 * The KIM Namespace Default Attribute is an attribute that is applied
 * to a Person when that Person is created.  When a Person is created, it
 * is given a set of Person Attributes that is a combination of all of
 * the Default Attributes from all of the Namespaces.  Values for the
 * attributes are set when the Person is created.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class KimNamespaceDefaultAttribute extends PersistableBusinessObjectBase {
    
    private static final long serialVersionUID = -8332284694172302250L;
    	
	private Long id;
	private Long namespaceId;
	private Long attributeTypeId;
	private String attributeName;
	private String description;
	
	/**
	 * Get the Default Attribute's ID.
	 * @return the Default Attribute's ID
	 */
	public Long getId() {
        return id;
    }

    /**
     * Set the Default Attribute's ID.
     * @param id the Default Attribute's ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the Namespace's ID.
     * @return the Namespace's ID
     */
    public Long getNamespaceId() {
        return this.namespaceId;
    }

    /**
     * Set the Namespace's ID.
     * @param namespaceId the Namespace's ID
     */
    public void setNamespaceId(Long namespaceId) {
        this.namespaceId = namespaceId;
    }
    
    /**
     * Get the Default Attribute's Type ID.
     * @return the Default Attribute's Type ID
     */
    public Long getAttributeTypeId() {
        return attributeTypeId;
    }

    /**
     * Set the Default Attribute's Type ID.
     * @param attributeTypeId the Default Attribute's Type ID
     */
    public void setAttributeTypeId(Long attributeTypeId) {
        this.attributeTypeId = attributeTypeId;
    }
    
	/**
	 * Get the Default Attribute's name.
	 * @return the Default Attribute's name
	 */
	public String getAttributeName() {
		return attributeName;
	}

	/**
	 * Set the Default Attribute's name.
	 * @param attributeName the Default Attribute's name
	 */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	/**
	 * Get the Default Attribute's description.
	 * @return the Default Attribute's description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Get the Default Attribute's description.
	 * @param description the Default Attribute's description
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
        propMap.put("namespaceId", getNamespaceId());
        propMap.put("attributeTypeId", getAttributeTypeId());
        propMap.put("attributeName", getAttributeName());
        propMap.put("description", getDescription());
        return propMap;
	}

	/**
	 * @see org.kuali.core.bo.PersistableBusinessObjectBase#refresh()
	 */
	public void refresh() {
		// not going to add unless needed
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
        KimNamespaceDefaultAttribute attr = (KimNamespaceDefaultAttribute) obj;
        return id.equals(attr.id);
    }
}
