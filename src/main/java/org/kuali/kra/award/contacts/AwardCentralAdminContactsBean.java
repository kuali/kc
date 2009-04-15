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
public class AwardCentralAdminContactsBean extends AwardUnitContactsBean {
    public AwardCentralAdminContactsBean(AwardForm awardForm) {
        super(awardForm);
    }

    public void addCentralAdminContact() {
//        AddAwardApprovedEquipmentRuleEvent event = generateAddEvent();
//        boolean success = getRuleService().applyRules(event);
//        if(success){
            getAward().add(getCentralAdminContact());
            init();
//        }
    }

    /**
     * @return
     */
    public AwardUnitContact getCentralAdminContact() {
        return (AwardUnitContact) newAwardContact;
    }
    
    /**
     * This method clears the new contact entry
     */
    public void clearNewCentralAdminContact() {
        init();
    }
    
    /**
     * Remove a Central Admin unit contact
     * @param lineToDelete
     */
    public void deleteCentralAdminContact(int lineToDelete) {
        super.deleteUnitContact(getCentralAdminContacts(), lineToDelete);
    }

    @Override
    public List<ContactRole> getContactRoles() {
        if(contactRoles == null) {
            contactRoles = new ArrayList<ContactRole>();
            contactRoles.add(ContactRoleFixtureFactory.MOCK_AUDITOR);
            contactRoles.add(ContactRoleFixtureFactory.MOCK_FUND_ACCOUNTANT);
            contactRoles.add(ContactRoleFixtureFactory.MOCK_SENIOR_ACCOUNTANT);            
        }
        return contactRoles;
//        return getContactRoles(ContactCategory.UNIT_CONTACTS);
    }

    /**
     * This method finds the count of AwardContacts in the "Unit Contacts" category
     * @return The list; may be empty
     */
    public List<AwardUnitContact> getCentralAdminContacts() {
        return findContactsForCategory(UnitContactType.ADMINISTRATOR);
    }
    
    /**
     * This method finds the count of AwardContacts in the "Unit Contacts" category
     * @return The count; may be 0
     */
    public int getCentralAdminContactsCount() {
        return getCentralAdminContacts().size();
    }

    @Override
    protected void init() {
        newAwardContact = new AwardUnitContact(UnitContactType.ADMINISTRATOR);
    }
}