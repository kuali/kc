/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.institutionalproposal.contacts;

import org.kuali.rice.krad.util.GlobalVariables;


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
