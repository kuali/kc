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

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.award.home.ContactType;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.UnitAdministrator;
import org.kuali.kra.bo.UnitContactType;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.UnitService;

/**
 * This class...
 */
public class InstitutionalProposalUnitContactsBean extends InstitutionalProposalContactsBean {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -1563581536920498669L;
    private static final int OSP_ADMINISTRATOR_TYPE_CODE = 2;
    private static final String DEFAULT_GROUP_CODE_FOR_UNIT_CONTACTS = "U";

    public InstitutionalProposalUnitContactsBean(InstitutionalProposalForm institutionalProposalForm) {
        super(institutionalProposalForm);
    }

    public void addUnitContact() {
        boolean success = new InstitutionalProposalUnitContactAddRuleImpl().
                processAddInstitutionalProposalUnitContactBusinessRules(getInstitutionalProposal(), getUnitContact());
        if(success){
            getInstitutionalProposal().add(getUnitContact());
            init();
            }
    }
    
    /**
     * Delete a Unit Contact
     * @param lineToDelete
     */
    public void deleteUnitContact(int lineToDelete) {
        deleteUnitContact(getUnitContacts(), lineToDelete);                
    }
    
    public void syncInstitutionalProposalUnitContactsToLeadUnitContacts() {
        getInstitutionalProposal().setInstitutionalProposalUnitContacts(new ArrayList<InstitutionalProposalUnitContact>()); //delete all current unit contacts
        List<UnitAdministrator> unitAdministrators = 
            getUnitService().retrieveUnitAdministratorsByUnitNumber(institutionalProposalForm.getInstitutionalProposalDocument().getInstitutionalProposal().getUnitNumber());
        for(UnitAdministrator unitAdministrator : unitAdministrators) {
            if(unitAdministrator.getUnitAdministratorType().getDefaultGroupFlag().equals(DEFAULT_GROUP_CODE_FOR_UNIT_CONTACTS)) {
                KcPerson person = getKcPersonService().getKcPersonByPersonId(unitAdministrator.getPersonId());
                InstitutionalProposalUnitContact newInstitutionalProposalUnitContact = new InstitutionalProposalUnitContact(UnitContactType.CONTACT);
                newInstitutionalProposalUnitContact.setPerson(person);
                newInstitutionalProposalUnitContact.setInstitutionalProposal(institutionalProposalForm.getInstitutionalProposalDocument().getInstitutionalProposal());
                newInstitutionalProposalUnitContact.setUnitAdministratorType(unitAdministrator.getUnitAdministratorType());
                newInstitutionalProposalUnitContact.setUnitAdministratorTypeCode(unitAdministrator.getUnitAdministratorTypeCode());
                newInstitutionalProposalUnitContact.setFullName(person.getFullName());
                getInstitutionalProposal().add(newInstitutionalProposalUnitContact);
            }
        }
    }
    
    
    public UnitService getUnitService() {
        return (UnitService) KraServiceLocator.getService(UnitService.class);
    }
    
    public KcPersonService getKcPersonService() {
        return (KcPersonService) KraServiceLocator.getService(KcPersonService.class);
    }

    /**
     * @return
     */
    public InstitutionalProposalUnitContact getUnitContact() {
       return (InstitutionalProposalUnitContact) newInstitutionalProposalContact; 
    }
    
    /**
     * This method finds the count of InstitutionalProposalContacts in the "Unit Contacts" category
     * @return The list; may be empty
     */
    public List<InstitutionalProposalUnitContact> getUnitContacts() {
        return institutionalProposalForm.getInstitutionalProposalDocument().getInstitutionalProposal().getInstitutionalProposalUnitContacts();
    }
    
    /**
     * This method finds the count of InstitutionalProposalContacts in the "Unit Contacts" category
     * @return The count; may be 0
     */
    public int getUnitContactsCount() {
        return getUnitContacts().size();
    }
    
    /**
     * Find a unit contact in a collection of UnitContact types and remove it from 
     * the main InstitutionalProposal collection of Unit Contacts
     * @param contacts
     * @param lineToDelete
     */
    protected void deleteUnitContact(List<InstitutionalProposalUnitContact> contacts, int lineToDelete) {
        if(contacts.size() > lineToDelete) {
            InstitutionalProposalUnitContact foundContact = contacts.get(lineToDelete);
            getInstitutionalProposal().getInstitutionalProposalUnitContacts().remove(foundContact);
        }
    }

    /**
     * @see org.kuali.kra.award.contacts.AwardContactsBean#getContactRoleType()
     */
    @Override
    protected Class<? extends ContactRole> getContactRoleType() {
        return ContactType.class;
    }

    /**
     * @see org.kuali.kra.award.contacts.AwardContactsBean#init()
     */
    @Override
    protected InstitutionalProposalContact createNewContact() {
        return new InstitutionalProposalUnitContact(UnitContactType.CONTACT);
    }
    
    
    /*
     * create an InstitutionalProposalUnitContact from a person
     */
    private InstitutionalProposalUnitContact createInstitutionalProposalContactForPerson(UnitAdministrator unitAdministrator) {
        InstitutionalProposalUnitContact institutionalProposalUnitContact = new InstitutionalProposalUnitContact();
        institutionalProposalUnitContact.setInstitutionalProposal(getInstitutionalProposal());
        institutionalProposalUnitContact.setPersonId(unitAdministrator.getPerson().getPersonId());
        institutionalProposalUnitContact.setFullName(unitAdministrator.getPerson().getFullName());
        institutionalProposalUnitContact.setPerson(unitAdministrator.getPerson());
        institutionalProposalUnitContact.setUnitContactType(UnitContactType.CONTACT);
        institutionalProposalUnitContact.setUnitAdministratorType(unitAdministrator.getUnitAdministratorType());
        return institutionalProposalUnitContact;
    }

}
