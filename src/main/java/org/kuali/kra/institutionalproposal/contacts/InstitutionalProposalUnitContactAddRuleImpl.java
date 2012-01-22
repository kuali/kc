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

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.UnitAdministratorType;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * This class...
 */
public class InstitutionalProposalUnitContactAddRuleImpl {

    public static final String INSTITUTIONAL_PROPOSAL_UNIT_CONTACT_LIST_ERROR_KEY = "unitContactsBean.unitContact.unitAdministratorTypeCode";
    public static final String ERROR_INSTITUTIONAL_PROPOSAL_UNIT_CONTACT_EXISTS = "error.institutionalProposalUnitContact.person.exists";
    public static final String ERROR_INSTITUTIONAL_PROPOSAL_CONTACT_REQUIRED = "error.institutionalProposal.contact.person.required";
    public static final String ERROR_INSTITUTIONAL_PROPOSAL_CONTACT_ROLE_REQUIRED = "error.institutionalProposal.contact.person.role.required";
    private static final String PERSON_ERROR_KEY = "unitContactsBean.newInstitutionalProposalContact.fullName";
    
    /**
     * @param event
     * @return
     */
    public boolean processAddInstitutionalProposalUnitContactBusinessRules(InstitutionalProposal institutionalProposal, 
                                                                                InstitutionalProposalUnitContact newUnitContact) {
        boolean valid = checkForSelectedContactAdministratorTypeCode(newUnitContact);
        valid &= checkForDuplicatePerson(institutionalProposal, newUnitContact);
        valid &= checkForSelectedPerson(newUnitContact);
        return  valid; 
    }

    public boolean checkForSelectedContactAdministratorTypeCode(InstitutionalProposalUnitContact newContact) {
        InstitutionalProposalUnitContact institutionalProposalUnitContact = (InstitutionalProposalUnitContact) newContact;
        boolean valid = institutionalProposalUnitContact.getUnitAdministratorTypeCode() != null;

        if(!valid) {
            GlobalVariables.getMessageMap().putError(INSTITUTIONAL_PROPOSAL_UNIT_CONTACT_LIST_ERROR_KEY, ERROR_INSTITUTIONAL_PROPOSAL_CONTACT_ROLE_REQUIRED);
        }

        return valid;
    }
    
    private boolean checkForSelectedPerson(InstitutionalProposalUnitContact newContact) {
        boolean valid = true;

        if (StringUtils.isBlank(newContact.getPersonId())) {
            if (StringUtils.isBlank(newContact.getFullName())) {
                GlobalVariables.getMessageMap().putError(PERSON_ERROR_KEY, KeyConstants.ERROR_MISSING_UNITCONTACT_PERSON);
            }
            else {
                GlobalVariables.getMessageMap().putError(PERSON_ERROR_KEY, KeyConstants.ERROR_INVALID_UNITCONTACT_PERSON);
            }
            valid = false;
        }

        return valid;
    }

    boolean checkForDuplicatePerson(InstitutionalProposal institutionalProposal, InstitutionalProposalUnitContact newUnitContact) {
        boolean valid = true;
        for(InstitutionalProposalUnitContact unitContact: institutionalProposal.getInstitutionalProposalUnitContacts()) {
            // equal, but not both are null
            valid = !(StringUtils.equals(unitContact.getPersonId(),newUnitContact.getPersonId()) 
                        && StringUtils.equals(unitContact.getUnitAdministratorTypeCode(),newUnitContact.getUnitAdministratorTypeCode()));
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
        String roleDescription = getRoleDescription(newUnitContact);
        GlobalVariables.getMessageMap().putError(PERSON_ERROR_KEY, ERROR_INSTITUTIONAL_PROPOSAL_UNIT_CONTACT_EXISTS, 
                                                newUnitContact.getContact().getFullName(), roleDescription);
    }

    private String getRoleDescription(InstitutionalProposalUnitContact newUnitContact) {
        String roleDescription = "";
        BusinessObjectService boService = KraServiceLocator.getService(BusinessObjectService.class);
        UnitAdministratorType aType = boService.findBySinglePrimaryKey(UnitAdministratorType.class, newUnitContact.getUnitAdministratorTypeCode());
        if (aType != null) {
            roleDescription = aType.getDescription();
        }
        return roleDescription;
    }
}
