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
 * A KIM Permission determines access to an object/operation.  Permissions are
 * assigned to Roles.  Roles are then assigned to Persons and Groups.  Persons (users)
 * are thus given the associated permissions.  Before executing an operation or
 * granting access to a object, applications first check with KIM to verify that
 * the user (person) has the necessary permission.
 * 
 * Permissions are also scoped to a Namespace.  For example, each application,
 * such as KFS and KRA, has its own set of Permissions.  The names of Permissions
 * must be unique within a given namespace.
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public class KimPermission extends PersistableBusinessObjectBase {

	private static final long serialVersionUID = -4520809944516623107L;
	
	private Long id;
	private Long namespaceId;
	private String name;
	private String description;
	
    /**
     * Get the Permission's ID.
     * @return the Permission's ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the Permission's ID.
     * @param id the Permission's ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the Permission's namespace.
     * @return the Permission's namespace ID
     */
    public Long getNamespaceId() {
        return namespaceId;
    }

    /**
     * Set the Permission's namespace.
     * @param namespaceId the ID of the namespace
     */
    public void setNamespaceId(Long namespaceId) {
        this.namespaceId = namespaceId;
    }
    
    /**
     * Get the Permission's name.
     * @return the Permission's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Set the Permission's name.
     * @param name the Permission's name
     */
    public void setName(String name) {
        this.name = name;
    }
    
	/**
	 * Get the Permission's description.
	 * @return the Permission's description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the Permission's description.
	 * @param description the Permission's description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
	 */
	protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("id", getId());
        map.put("namespaceId", getNamespaceId());
        map.put("name", getName());
        map.put("description", getDescription());
        return map;
	}

	/**
	 * @see org.kuali.core.bo.BusinessObject#refresh()
	 */
	public void refresh() {
		// not implementing unless necessary
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
        KimPermission permission = (KimPermission) obj;
        return id.equals(permission.id);
    }
}
