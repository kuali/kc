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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.award.home.ContactType;
import org.kuali.kra.award.web.struts.form.AwardForm;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.bo.UnitContactType;

/**
 * This class provides support for the Award Contacts Project Personnel panel
 */
public class AwardUnitContactsBean extends AwardContactsBean {
    private static final long serialVersionUID = 1421235654899276682L;

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
        List<AwardUnitContact> unitContacts = new ArrayList<AwardUnitContact>();
        Unit unit = getAward().getLeadUnit();
        if(unit != null) {
            updateExistingLeadUnitContactsFromAward(unit);
        }
        unitContacts.addAll(findContactsForCategory(UnitContactType.CONTACT));
        return unitContacts;
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
     * Find the subset of unitContacts for a particular UnitContactType
     * @param contactType
     * @return
     */
    protected List<AwardUnitContact> findContactsForCategory(UnitContactType contactType) {
        List<AwardUnitContact> categorizedContacts = new ArrayList<AwardUnitContact>();
        for(AwardUnitContact contact: getAward().getAwardUnitContacts()) {
            UnitContactType foundType = contact.getUnitContactType();
            if(foundType == contactType) {
                categorizedContacts.add(contact);
            }
        }
        return categorizedContacts;
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
     * Add lead unit contacts
     */
    private void addLeadUnitContacts(List<AwardUnitContact> existingUnitContacts, List<Person> allLeadUnitPersonnel) {
        List<String> existingUnitContactPersonnel = new ArrayList<String>();
        for(AwardUnitContact contact: existingUnitContacts) {
            existingUnitContactPersonnel.add(contact.getPerson().getPersonId());
        }
        
        List<AwardUnitContact> adds = new ArrayList<AwardUnitContact>();
        for(Person person: allLeadUnitPersonnel) {
            if(!existingUnitContactPersonnel.contains(person.getPersonId())) {
                adds.add(createAwardContactForPerson(person));
            }
        }
        getAward().getAwardUnitContacts().addAll(adds);
    }
    
    /*
     * create an AwardUnitContact from a person
     */
    private AwardUnitContact createAwardContactForPerson(Person person) {
        AwardUnitContact awardUnitContact = new AwardUnitContact();
        awardUnitContact.setAward(getAward());
        awardUnitContact.setPersonId(person.getPersonId());
        awardUnitContact.setFullName(person.getFullName());
        awardUnitContact.setPerson(person);
        awardUnitContact.setUnitContactType(UnitContactType.CONTACT);
        return awardUnitContact;
    }

    /*
     * Find lead unit contacts
     */
    private List<AwardUnitContact> findAwardUnitContactsFromLeadUnit(Unit leadUnit) {
        List<AwardUnitContact> allUnitContacts = findContactsForCategory(UnitContactType.CONTACT);
        List<AwardUnitContact> existingLeadUnitContacts = new ArrayList<AwardUnitContact>(); 
        for(AwardUnitContact unitContact: allUnitContacts) {
            if(leadUnit.getUnitNumber().equals(unitContact.getPerson().getHomeUnit())) {
                existingLeadUnitContacts.add(unitContact);
            }
        }
        return existingLeadUnitContacts;
    }

    private List<Person> findAllLeadUnitPersons(String unitNumber) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("homeUnit", unitNumber);
        @SuppressWarnings("unchecked") List<Person> unitPeople = (List<Person>) getBusinessObjectService().findMatching(Person.class, fieldValues);
        return unitPeople;
    }

    /*
     * Remove lead unit contacts
     */
    private void removeLeadUnitContacts(List<AwardUnitContact> existingUnitContacts, List<Person> allLeadUnitPersonnel) {
        List<AwardUnitContact> removals = new ArrayList<AwardUnitContact>();
        for(AwardUnitContact existingContact: existingUnitContacts) {
            boolean found = false;
            for(Person p: allLeadUnitPersonnel) {
                found = p.getPersonId().equals(existingContact.getPersonId());
                if(found) {
                    break;
                }
            }
            if(!found) {
                removals.add(existingContact);
            }
        }
        if(removals.size() > 0) {
            getAward().getAwardUnitContacts().removeAll(removals);
        }
    }

    /*
     * Finds any unit contacts from Lead Unit and removes them from Award
     */
    private void updateExistingLeadUnitContactsFromAward(Unit leadUnit) {
        List<AwardUnitContact> existingLeadUnitContacts = findAwardUnitContactsFromLeadUnit(leadUnit);
        List<Person> allLeadUnitPersonnel = findAllLeadUnitPersons(leadUnit.getUnitNumber());
        removeLeadUnitContacts(existingLeadUnitContacts, allLeadUnitPersonnel);
        addLeadUnitContacts(getAward().getAwardUnitContacts(), allLeadUnitPersonnel);        
        
    }
}
