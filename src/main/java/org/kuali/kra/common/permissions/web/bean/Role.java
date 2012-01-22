/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.common.permissions.web.bean;

import java.io.Serializable;
import java.util.List;

import org.kuali.rice.kim.api.permission.Permission;

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
