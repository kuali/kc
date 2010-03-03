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
package org.kuali.kra.institutionalproposal.contacts;

import org.codehaus.plexus.util.StringUtils;
import org.kuali.kra.award.contacts.AwardUnitContact;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * This class...
 */
public class InstitutionalProposalUnitContactAddRuleImpl {

    public static final String INSTITUTIONAL_PROPOSAL_UNIT_CONTACT_LIST_ERROR_KEY = "unitContactsBean.newInstitutionalProposalContact";
    public static final String ERROR_INSTITUTIONAL_PROPOSAL_UNIT_CONTACT_EXISTS = "error.institutionalProposalUnitContact.person.exists";
    public static final String ERROR_INSTITUTIONAL_PROPOSAL_CONTACT_REQUIRED = "error.institutionalProposal.contact.person.required";
    public static final String ERROR_INSTITUTIONAL_PROPOSAL_CONTACT_ROLE_REQUIRED = "error.institutionalProposal.contact.person.role.required";
    
    /**
     * @param event
     * @return
     */
    public boolean processAddInstitutionalProposalUnitContactBusinessRules(InstitutionalProposal institutionalProposal, 
                                                                                InstitutionalProposalUnitContact newUnitContact) {
        return checkForSelectedContactAdministratorTypeCode(newUnitContact) && checkForDuplicatePerson(institutionalProposal, newUnitContact);
    }

    public boolean checkForSelectedContactAdministratorTypeCode(InstitutionalProposalUnitContact newContact) {
        InstitutionalProposalUnitContact institutionalProposalUnitContact = (InstitutionalProposalUnitContact) newContact;
        boolean valid = institutionalProposalUnitContact.getUnitAdministratorTypeCode() != null;

        if(!valid) {
            GlobalVariables.getMessageMap().putError(INSTITUTIONAL_PROPOSAL_UNIT_CONTACT_LIST_ERROR_KEY, ERROR_INSTITUTIONAL_PROPOSAL_CONTACT_ROLE_REQUIRED);
        }

        return valid;
    }
    
    boolean checkForDuplicatePerson(InstitutionalProposal institutionalProposal, InstitutionalProposalUnitContact newUnitContact) {
        boolean valid = true;
        for(InstitutionalProposalUnitContact unitContact: institutionalProposal.getInstitutionalProposalUnitContacts()) {
            // equal, but not both are null
            valid = !(StringUtils.equals(unitContact.getPersonId(),newUnitContact.getPersonId()) && unitContact.getPersonId()!= null);
            if(!valid) {
                registerError(newUnitContact);
                break;
            }
        }
        
        return valid;
    }
    
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
            GlobalVariables.getMessageMap().putError(errorPath, ERROR_INSTITUTIONAL_PROPOSAL_CONTACT_REQUIRED);
        }
        
        return valid;
    }

    /**
     * Verify a contact role is picked
     * @param newContact
     * @return
     */
    boolean checkForSelectedContactRole(InstitutionalProposalContact newContact, String errorPath) {
        InstitutionalProposalUnitContact ipuc = (InstitutionalProposalUnitContact) newContact; 
        boolean valid = ipuc.getUnitAdministratorTypeCode() != null;

        if(!valid) {
            GlobalVariables.getMessageMap().putError(errorPath, ERROR_INSTITUTIONAL_PROPOSAL_CONTACT_ROLE_REQUIRED);
        }

        return valid;
    }

    @SuppressWarnings("deprecation")
    private void registerError(InstitutionalProposalUnitContact newUnitContact) {
        GlobalVariables.getErrorMap().putError(INSTITUTIONAL_PROPOSAL_UNIT_CONTACT_LIST_ERROR_KEY, ERROR_INSTITUTIONAL_PROPOSAL_UNIT_CONTACT_EXISTS, 
                                                newUnitContact.getContact().getFullName());
    }
}
