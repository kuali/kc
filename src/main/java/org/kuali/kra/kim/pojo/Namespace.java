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
 * A Namespace are used to scope permissions and person attributes.  For example,
 * different applications, such as KFS and KRA, each have a different
 * set of permissions.  In other words, the namespace identifies the 
 * application.  Each namespace has a unique name, e.g. "KRA, "KFS", etc.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class Namespace extends Pojo  {
    	
    private static final long serialVersionUID = 3810422593019557223L;
    
	private String name;
	private String description;

	/**
	 * Get the Namespace's name.
	 * @return the Namespace's name.
	 */
	public String getName() {
	    return this.name;
	}

	/**
	 * Set the Namespace's name.
	 * @param name the Namespace's name
	 */
	public void setName(String name) {
	    this.name = name;
	}

	/**
	 * Get the Namespace's description.
	 * @return the Namespace's description
	 */
	public String getDescription() {
	    return this.description;
	}

	/**
	 * Set the Namespace's description.
	 * @param description the Namespace's description
	 */
	public void setDescription(String description) {
	    this.description = description;
	}

	/**
	 * @see org.kuali.kra.kim.pojo.Pojo#toStringMapper()
	 */
	protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("name", getName());
        map.put("description", getDescription());
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
        Namespace namespace = (Namespace) obj;
        return name.equals(namespace.name);
    }
}
