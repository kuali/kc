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
package org.kuali.kra.kim.pojo;

import java.util.LinkedHashMap;

/**
 * Permissions control access to objects and operations.  A person (user) must have the
 * necessary permission in order to perform a given task.  Each permission has a unique
 * name in its respective Namespace.
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public class Permission extends Pojo {
	
    private static final long serialVersionUID = 3269194497201523682L;
    
	private String name;
	private String description;
	private String namespaceName;
	
	/**
	 * Get the Permission's name.
	 * @return the permission's name
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
	 * Get the name of the Permission's namespace.
	 * @return the name of the Permission's namespace
	 */
	public String getNamespaceName() {
        return this.namespaceName;
    }

    /**
     * Set the name of the Permission's namespace.
     * @param namespaceName the name of the Permission's namespace
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }
    
	/**
	 * @see org.kuali.kra.kim.pojo.Pojo#toStringMapper()
	 */
	protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("name", getName());
        map.put("description", getDescription());
        map.put("namespaceName", getNamespaceName());
        return map;
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
        Permission permission = (Permission) obj;
        return name.equals(permission.name) &&
               namespaceName.equals(permission.namespaceName);
    }
}
