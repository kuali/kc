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
 * The KIM Namespace represents a single Namespace.  KIM uses 
 * namespaces to scope permissions and person attributes.  For example,
 * different applications, such as KFS and KRA, each have a different
 * set of permissions.  In other words, the namespace identifies the 
 * application.  Each namespace has a unique name, e.g. "KRA, "KFS", etc.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class KimNamespace extends PersistableBusinessObjectBase {
    
    private static final long serialVersionUID = 9118112248900436184L;
    
	private Long id;
	private String name;
	private String description;
	
	/**
	 * Get the Namespace's ID.
	 * @return the Namespace's ID
	 */
	public Long getId() {
	    return this.id;
	}

	/**
	 * Set the Namespace's ID.
	 * @param id the Namespace's ID
	 */
	public void setId(Long id) {
	    this.id = id;
	}

	/**
	 * Get the Namespace's name.
	 * @return the Namespace's name
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
	 * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
	 */
	protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> propMap = new LinkedHashMap<String, Object>();
        propMap.put("id", getId());
        propMap.put("name", getName());
        propMap.put("description", getDescription());
        return propMap;
	}

	/**
	 * @see org.kuali.core.bo.PersistableBusinessObjectBase#refresh()
	 */
	public void refresh() {
		// not doing this unless we need it
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
        KimNamespace namespace = (KimNamespace) obj;
        return id.equals(namespace.id);
    }
}
