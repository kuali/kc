/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.kra.award.contacts;

import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.award.home.ContactType;

import java.util.List;

/**
 * This class provides support for the Award Contacts Project Personnel panel
 */
public class AwardSponsorContactsBean extends AwardContactsBean {
    private static final long serialVersionUID = -5443573805950047573L;

    public AwardSponsorContactsBean(AwardForm awardForm) {
        super(awardForm);
    }

    public AwardSponsorContact addSponsorContact() {
        boolean success = new AwardSponsorContactAddRuleImpl().processAddAwardSponsorContactBusinessRules(getAward(), getSponsorContact());
        if(success){
            AwardSponsorContact contact = getSponsorContact();
            getAward().add(contact);
            init();
            return contact;
        }
        return null;
    }
    

    public AwardSponsorContact getSponsorContact() {
        return (AwardSponsorContact) newAwardContact;
    }
    
    public void deleteSponsorContact(int lineToDelete) {
        List<AwardSponsorContact> awardSponsorContacts = getSponsorContacts();
        if(awardSponsorContacts.size() > lineToDelete) {
            awardSponsorContacts.remove(lineToDelete);
        }        
    }

    /**
     * This method finds the count of AwardContacts in the "Sponsor Contacts" category
     * @return The list; may be empty
     */
    public List<AwardSponsorContact> getSponsorContacts() {
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
    protected AwardContact createNewContact() {
        return new AwardSponsorContact();
    }

    @Override
    protected Class<? extends ContactRole> getContactRoleType() {
        return ContactType.class;
    }
}
