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

import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.bo.ContactRole;
import org.kuali.kra.award.web.struts.form.AwardForm;

/**
 * This class provides support for the Award Contacts Project Personnel panel
 */
public class AwardSponsorContactsBean extends AwardContactsBean {
    public AwardSponsorContactsBean(AwardForm awardForm) {
        super(awardForm);
    }

    public void addSponsorContact() {
//        AddAwardApprovedEquipmentRuleEvent event = generateAddEvent();
//        boolean success = getRuleService().applyRules(event);
//        if(success){
            getAward().addSponsorContact(getSponsorContact());
            init();
//        }
    }
    
    /**
     * @return
     */
    public SponsorContact getSponsorContact() {
        return (SponsorContact) newAwardContact;
    }

    /**
     * This method clears the new contact entry
     */
    public void clearNewSponsorContact() {
        init();
    }
    
    public void deleteSponsorContact(int lineToDelete) {
        List<SponsorContact> sponsorContacts = getSponsorContacts();
        if(sponsorContacts.size() > lineToDelete) {
            sponsorContacts.remove(lineToDelete);
        }        
    }

    @Override
    public List<ContactRole> getContactRoles() {
        if(contactRoles == null) {
            contactRoles = new ArrayList<ContactRole>();
            contactRoles.add(ContactRoleFixtureFactory.MOCK_ADMINISTRATIVE_CONTACT1);
            contactRoles.add(ContactRoleFixtureFactory.MOCK_ADMINISTRATIVE_CONTACT2);
            contactRoles.add(ContactRoleFixtureFactory.MOCK_AWARD_OFFICE_CONTACT1);
            contactRoles.add(ContactRoleFixtureFactory.MOCK_AWARD_OFFICE_CONTACT2);
            contactRoles.add(ContactRoleFixtureFactory.MOCK_CLOSEOUT_CONTACT);
        }
        return contactRoles;
//        return getContactRoles(ContactCategory.UNIT_CONTACTS);
    }

    /**
     * This method finds the count of AwardContacts in the "Sponsor Contacts" category
     * @return The list; may be empty
     */
    public List<SponsorContact> getSponsorContacts() {
        return getAward().getSponsorContacts();
    }
    
    /**
     * This method finds the count of AwardContacts in the "Unit Contacts" category
     * @return The count; may be 0
     */
    public int getSponsorContactsCount() {
        return getSponsorContacts().size();
    }

    @Override
    protected void init() {
        newAwardContact = new SponsorContact();
    }
}
