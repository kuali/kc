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
import java.util.List;

/**
 * A Role is a set of Permissions.  Persons and Groups can be assigned to
 * Roles in order to obtain it's permissions.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class Role extends Pojo {

    private static final long serialVersionUID = -6181783409793865322L;
    
	private String name;
	private String description;
	private List<Attribute> attributes;

    /**
     * Get the Role's name.
     * @return the Role's name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the Role's name.
     * @param name the Role's name
     */
    public void setName(String name) {
        this.name = name;
    }

	/**
	 * Get the Role's description.
	 * @return the Role's description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the Role's description.
	 * @param description the Role's description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Get the Role's attributes.
	 * @return the Role's attributes
	 */
	public List<Attribute> getAttributes() {
        return attributes;
    }

    /**
     * Set the Role's attributes.
     * @param attributes the Role's attributes
     */
    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    /**
     * @see org.kuali.kra.kim.pojo.Pojo#toStringMapper()
     */
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> propMap = new LinkedHashMap<String, Object>();
        propMap.put("name", getName());
        propMap.put("description", getDescription());
        return propMap;
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
        Role role = (Role) obj;
        return name.equals(role.name);
    }
}
