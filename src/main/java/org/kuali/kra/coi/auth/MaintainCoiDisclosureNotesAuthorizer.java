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
package org.kuali.kra.coi.auth;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;

public class MaintainCoiDisclosureNotesAuthorizer extends CoiDisclosureAuthorizer {

    @Override
    public boolean isAuthorized(String userId, CoiDisclosureTask task) {
        boolean hasPermission = true;
        // check if the user has the explicit permissions for maintaining notes like administrators and
        // reviewers
        if(hasPermission(userId, task.getCoiDisclosure(), PermissionConstants.MAINTAIN_COI_DISCLOSURE_NOTES)) {
            hasPermission = true;
        }
        else {
            // check if the user is creating a new disclosure and has the explicit report disclosure permissions
            CoiDisclosure coiDisclosure = task.getCoiDisclosure();
            if (coiDisclosure == null || coiDisclosure.getCoiDisclosureId() == null) {
                hasPermission = hasUnitPermission(userId, Constants.MODULE_NAMESPACE_COIDISCLOSURE, PermissionConstants.REPORT_COI_DISCLOSURE);
            } 
            else {
                // check if the user is the original reporter for the saved disclosure and that the disclosure is editable, 
                // and that it is not yet submitted (certified)
                hasPermission = StringUtils.equals(userId, coiDisclosure.getPersonId())
                                && isDisclosureEditable(coiDisclosure) 
                                && !coiDisclosure.isSubmitted();
            }
        }
        return hasPermission;  
    }
    
}