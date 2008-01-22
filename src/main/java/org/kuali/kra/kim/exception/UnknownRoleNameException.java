/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.kra.kim.exception;

/**
 * An Unknown Role Name Exception occurs when KIM request is made
 * and KIM does not recognize the role name.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class UnknownRoleNameException extends RuntimeException {

    private static final long serialVersionUID = 5982596675956589141L;

    /**
     * Constructs a UnknownRoleNameException.
     * @param roleName the name of the role
     */
    public UnknownRoleNameException(String roleName) {
        super("Unknown Role name: " + roleName);
    }
}
