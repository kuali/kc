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
 * A Person represents a single user in the system.  Each person
 * is identified by a unique username.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class Person extends Pojo {

    private static final long serialVersionUID = 540542908319745679L;
    
	private String username;
	private String password;
	private List<PersonAttribute> attributes;
	
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
	 * Get the Person's attributes.
	 * @return the Person's attributes
	 */
	public List<PersonAttribute> getAttributes() {
        return attributes;
    }

    /**
     * Set the Person's attributes.
     * @param attributes the Person's attributes
     */
    public void setAttributes(List<PersonAttribute> attributes) {
        this.attributes = attributes;
    }

    /**
     * @see org.kuali.kra.kim.pojo.Pojo#toStringMapper()
     */
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("username", getUsername());
        map.put("password", getPassword());
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
        Person person = (Person) obj;
        return username.equals(person.username) &&
               password.equals(person.password);
    }
}
