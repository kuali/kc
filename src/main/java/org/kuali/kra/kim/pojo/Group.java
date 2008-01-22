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
 * A Group contains zero or more other Groups and/or Persons.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class Group extends Pojo {

    private static final long serialVersionUID = 6219457463462351892L;

    private String name;
    private String description;
    private List<Attribute> attributes;

    /**
     * Get the Group's description.
     * @return the Group's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the Group's description.
     * @param description the Group's description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the Group's name.
     * @return the Group's name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the Group's name.
     * @param name the Group's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the Group's attributes.
     * @return the Group's attributes
     */
    public List<Attribute> getAttributes() {
        return attributes;
    }

    /**
     * Set the Group's attributes.
     * @param attributes the Group's attributes
     */
    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
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
        Group group = (Group) obj;
        return name.equals(group.name);
    }
}
