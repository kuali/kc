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
 * The KIM Group represents a Group.  A Group can contain both Persons
 * and Groups.  All permissions in a group apply to the persons and groups
 * within that group.  Every Group within KIM has a unique name.
 *
 * Groups can also have zero or more Group Attributes.  These attributes
 * are not stored in the Group class for performance reasons.  In most
 * cases, KIM only needs to access the Group from the database, not it's
 * attributes.  Therefore the attributes are retrieved via a separate
 * query when needed.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class KimGroup extends PersistableBusinessObjectBase {

    private static final long serialVersionUID = 4974576362491778342L;

    private Long id;
    private String name;
    private String description;

    /**
     * Get the Group's ID.
     * @return the Group's ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the Group's ID.
     * @param id the Group's ID
     */
    public void setId(Long id) {
        this.id = id;
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
        // not implemented unless needed
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
        KimGroup group = (KimGroup) obj;
        return id.equals(group.id);
    }
}
