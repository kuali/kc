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
package org.kuali.kra.institutionalproposal.contacts;

import org.kuali.rice.krad.util.GlobalVariables;

/**
 * This class...
 */
public class BaseInstitutionalProposalContactAddRule {

    public static final String ERROR_CONTACT_REQUIRED = "error.award.contact.person.required";
    public static final String ERROR_CONTACT_ROLE_REQUIRED = "error.award.contact.person.role.required";

    /**
     * Verify contact AND role are selected
     * @param newContact
     * @return
     */
    boolean checkForSelectedContactAndRole(InstitutionalProposalContact newContact, String errorPath) {
        return checkForSelectedContact(newContact, errorPath) & checkForSelectedContactRole(newContact, errorPath);
    }

    /**
     * Verify a contact is selected
     * @param newContact
     * @return
     */
    boolean checkForSelectedContact(InstitutionalProposalContact newContact, String errorPath) {
        boolean valid = newContact.getContact() != null;
        
        if(!valid) {
            GlobalVariables.getMessageMap().putError(errorPath, ERROR_CONTACT_REQUIRED);
        }
        
        return valid;
    }

    /**
     * Verify a contact role is picked
     * @param newContact
     * @return
     */
    boolean checkForSelectedContactRole(InstitutionalProposalContact newContact, String errorPath) {
        boolean valid = newContact.getContactRole() != null;

        if(!valid) {
            GlobalVariables.getMessageMap().putError(errorPath, ERROR_CONTACT_ROLE_REQUIRED);
        }

        return valid;
    }
}
