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
 * An Unknown Permission Name Exception occurs when KIM request is made
 * and KIM does not recognize the permission name.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class UnknownPermissionNameException extends RuntimeException {

    private static final long serialVersionUID = 2403168471530448301L;

    /**
     * Constructs a UnknownPermissionNameException.
     * @param permissionName the name of the permission
     */
    public UnknownPermissionNameException(String permissionName) {
        super("Unknown Permission name: " + permissionName);
    }
}
