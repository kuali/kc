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
package org.kuali.kra.award.contacts;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.award.home.ContactType;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.UnitAdministrator;
import org.kuali.kra.bo.UnitContactType;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.UnitService;

/**
 * This class provides support for the Award Contacts Project Personnel panel
 */
public class AwardUnitContactsBean extends AwardContactsBean {
    private static final long serialVersionUID = 1421235654899276682L;
    private static final int OSP_ADMINISTRATOR_TYPE_CODE = 2;
    private static final String DEFAULT_GROUP_CODE_FOR_UNIT_CONTACTS = "U";

    public AwardUnitContactsBean(AwardForm awardForm) {
        super(awardForm);
    }

    public void addUnitContact() {
        boolean success = new AwardUnitContactAddRuleImpl().processAddAwardUnitContactBusinessRules(getAward(), getUnitContact());
        if(success){
            getAward().add(getUnitContact());
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
    
    public void syncAwardUnitContactsToLeadUnitContacts() {
        getAward().setAwardUnitContacts(new ArrayList<AwardUnitContact>()); //delete all current unit contacts
        List<UnitAdministrator> unitAdministrators = getUnitService().retrieveUnitAdministratorsByUnitNumber(awardForm.getAwardDocument().getAward().getUnitNumber());
        for(UnitAdministrator unitAdministrator : unitAdministrators) {
            if(unitAdministrator.getUnitAdministratorType().getDefaultGroupFlag().equals(DEFAULT_GROUP_CODE_FOR_UNIT_CONTACTS)) {
                KcPerson person = getKcPersonService().getKcPersonByPersonId(unitAdministrator.getPersonId());
                AwardUnitContact newAwardUnitContact = new AwardUnitContact(UnitContactType.CONTACT);
                newAwardUnitContact.setPerson(person);
                newAwardUnitContact.setUnitAdministratorUnitNumber(unitAdministrator.getUnitNumber());
                newAwardUnitContact.setAward(awardForm.getAwardDocument().getAward());
                newAwardUnitContact.setUnitAdministratorType(unitAdministrator.getUnitAdministratorType());
                newAwardUnitContact.setUnitAdministratorTypeCode(unitAdministrator.getUnitAdministratorTypeCode());
                newAwardUnitContact.setFullName(person.getFullName());
                getAward().add(newAwardUnitContact);
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
    public AwardUnitContact getUnitContact() {
       return (AwardUnitContact) newAwardContact; 
    }
    
    /**
     * This method finds the count of AwardContacts in the "Unit Contacts" category
     * @return The list; may be empty
     */
    public List<AwardUnitContact> getUnitContacts() {
        return awardForm.getAwardDocument().getAward().getAwardUnitContacts();
    }
    
    /**
     * This method finds the count of AwardContacts in the "Unit Contacts" category
     * @return The count; may be 0
     */
    public int getUnitContactsCount() {
        return getUnitContacts().size();
    }
    
    /**
     * Find a unit contact in a collection of UnitContact types and remove it from 
     * the main Award collection of Unit Contacts
     * @param contacts
     * @param lineToDelete
     */
    protected void deleteUnitContact(List<AwardUnitContact> contacts, int lineToDelete) {
        if(contacts.size() > lineToDelete) {
            AwardUnitContact foundContact = contacts.get(lineToDelete);
            getAward().getAwardUnitContacts().remove(foundContact);
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
    protected AwardContact createNewContact() {
        return new AwardUnitContact(UnitContactType.CONTACT);
    }
    
    
    /*
     * create an AwardUnitContact from a person
     */
    private AwardUnitContact createAwardContactForPerson(UnitAdministrator unitAdministrator) {
        AwardUnitContact awardUnitContact = new AwardUnitContact();
        awardUnitContact.setAward(getAward());
        awardUnitContact.setPersonId(unitAdministrator.getPerson().getPersonId());
        awardUnitContact.setFullName(unitAdministrator.getPerson().getFullName());
        awardUnitContact.setPerson(unitAdministrator.getPerson());
        awardUnitContact.setUnitContactType(UnitContactType.CONTACT);
        awardUnitContact.setUnitAdministratorType(unitAdministrator.getUnitAdministratorType());
        return awardUnitContact;
    }

}
