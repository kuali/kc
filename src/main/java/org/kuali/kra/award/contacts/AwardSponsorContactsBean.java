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

import java.util.List;

import org.kuali.kra.award.bo.ContactRole;
import org.kuali.kra.award.bo.ContactType;
import org.kuali.kra.award.web.struts.form.AwardForm;

/**
 * This class provides support for the Award Contacts Project Personnel panel
 */
public class AwardSponsorContactsBean extends AwardContactsBean {
    public AwardSponsorContactsBean(AwardForm awardForm) {
        super(awardForm);
    }

    public void addSponsorContact() {
        boolean success = new AwardSponsorContactAddRuleImpl().processAddAwardSponsorContactBusinessRules(getAward(), getSponsorContact());
        if(success){
            getAward().add(getSponsorContact());
            init();
        }
    }
    
    /**
     * @return
     */
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
    protected void init() {
        newAwardContact = new AwardSponsorContact();
    }

    @Override
    protected Class<? extends ContactRole> getContactRoleType() {
        return ContactType.class;
    }
}