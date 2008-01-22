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
 * A KIM Person represents a user (person).  Each person has a username,
 * password, and a set of attributes.  For database performance reasons,
 * the attributes are not within this class.  They can be queried from 
 * the database when necessary.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class KimPerson extends PersistableBusinessObjectBase {

	private static final long serialVersionUID = -1207463934478758540L;
	
	private Long id;
	private String username;
	private String password;
	
	/**
	 * Get the Person's ID.
	 * @return the Person's ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set the Person's ID.
	 * @param id the Person's ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

    /**
     * Get the Person's username.
     * @return the Person's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the Person's username.
     * @param username the Person's username
     */
    public void setUsername(String username) {
        this.username = username;
    }

	/**
	 * Get the Person's password.
	 * @return the Person's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set the Person's password.
	 * @param password the Person's password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
	 */
	protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> propMap = new LinkedHashMap<String, Object>();
        propMap.put("id", getId());
        propMap.put("username", getUsername());
        propMap.put("password", getPassword());
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
        KimPerson person = (KimPerson) obj;
        return id.equals(person.id);
    }
}
