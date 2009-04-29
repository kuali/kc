/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.contacts;

import org.kuali.rice.kns.util.GlobalVariables;

/**
 * Common rule processing for adds
 */
public class BaseAwardContactAddRule {
    public static final String ERROR_AWARD_CONTACT_ROLE_REQUIRED = "error.award.contact.person.role.required";
    
    /**
     * Verify a contact is slected and role picked
     * @param newContact
     * @return
     */
    boolean checkForSelectedContactAndRole(AwardContact newContact, String errorPath) {
        boolean valid = newContact.getContact() != null & newContact.getContactRole() != null;
        
        if(!valid) {
            GlobalVariables.getErrorMap().putError(errorPath, ERROR_AWARD_CONTACT_ROLE_REQUIRED);
        }
        
        return valid;
    }
}
