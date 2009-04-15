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
import java.util.List;

import org.kuali.kra.award.bo.ContactRole;
import org.kuali.kra.award.web.struts.form.AwardForm;
import org.kuali.kra.bo.UnitContactType;

/**
 * This class provides support for the Award Contacts Project Personnel panel
 */
public class AwardUnitContactsBean extends AwardContactsBean {
    public AwardUnitContactsBean(AwardForm awardForm) {
        super(awardForm);
    }

    public void addUnitContact() {
//        AddAwardApprovedEquipmentRuleEvent event = generateAddEvent();
//        boolean success = getRuleService().applyRules(event);
//        if(success){
            getAward().add(getUnitContact());
            init();
//        }
    }

    /**
     * This method clears the new contact entry
     */
    public void clearNewUnitContact() {
        init();
    }
    
    /**
     * Delete a Unit Contact
     * @param lineToDelete
     */
    public void deleteUnitContact(int lineToDelete) {
        deleteUnitContact(getUnitContacts(), lineToDelete);                
    }
    
    @Override
    public List<ContactRole> getContactRoles() {
        if(contactRoles == null) {
            contactRoles = new ArrayList<ContactRole>();
            contactRoles.add(ContactRoleFixtureFactory.MOCK_ACCOUNTANT);
            contactRoles.add(ContactRoleFixtureFactory.MOCK_ADMIN_ASSISTANT);
            contactRoles.add(ContactRoleFixtureFactory.MOCK_BUSINESS_MANAGER);
        }
        return contactRoles;
//        return getContactRoles(ContactCategory.UNIT_CONTACTS);
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
        return getAward().getUnitContacts();
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
            getAward().getUnitContacts().remove(foundContact);
        }
    }
    
    /**
     * Find the subset of unitContacts for a particular UnitContactType
     * @param contactType
     * @return
     */
    protected List<AwardUnitContact> findContactsForCategory(UnitContactType contactType) {
        List<AwardUnitContact> categorizedContacts = new ArrayList<AwardUnitContact>();
        for(AwardUnitContact contact: getAward().getUnitContacts()) {
            UnitContactType foundType = contact.getUnitContactType();
            if(foundType == contactType) {
                categorizedContacts.add(contact);
            }
        }
        return categorizedContacts;
    }

    @Override
    protected void init() {
        newAwardContact = new AwardUnitContact(UnitContactType.CONTACT);
    }
}
