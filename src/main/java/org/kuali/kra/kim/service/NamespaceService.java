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
package org.kuali.kra.kim.service;

import java.util.List;

import org.kuali.kra.kim.pojo.Permission;

/**
 * Service API for accessing KIM Namespace services.  This contract should be used by all 
 * Kuali software which needs to leverage identity management features that require fine-grained
 * Namespace attributes. 
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public interface NamespaceService {
    /**
     * KIM Namespace service API method that returns all Permission objects associated 
     * with a given namespace.
     * 
     * @param   namespaceName        name identifying Namespace
     * @return                       List of Permission objects associated with the namespace
     * 
     */
    public List<Permission> getPermissions(String namespaceName);
    /**
     * KIM Namespace service API method that returns all Permission names associated 
     * with a given namespace.
     * 
     * @param   namespaceName        name identifying Namespace
     * @return                       List of Permission names associated with the namespace
     * 
     */
    public List<String> getPermissionNames(String namespaceName);

}
