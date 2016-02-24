/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.common.permissions.impl.web.bean;

import org.kuali.rice.kim.api.permission.Permission;

import java.io.Serializable;
import java.util.List;

/**
 * A role which consists of its unique name, its display name, and
 * its set of permissions.  In some cases, the display name is different
 * from the role's name.  For example, the "Protocol Aggregator" role
 * name is displayed as "Aggregator".
 */
public class Role implements Serializable {

    private String name;
    private String displayName;
    private transient List<Permission> permissions;
    
    public Role(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }
    
    public Role(String name, String displayName, List<Permission> permissions) {
        this(name, displayName);
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }
    
    public List<Permission> getPermissions() {
        return permissions;
    }
}
