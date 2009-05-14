/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.kim.exception;

/**
 * A Duplicate Role Name Exception occurs when KIM request is made
 * and particular role has multiple entries.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class DuplicateRoleNameException extends RuntimeException {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -7412437386721912514L;

    /**
     * Constructs a DuplicateRoleNameException.
     * @param roleName the name of the role
     */
    public DuplicateRoleNameException(String roleName) {
        super("Duplicate Role name: " + roleName);
    }
}
