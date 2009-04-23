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
        boolean success = new AwardUnitContactAddRuleImpl().processAddAwardUnitContactBusinessRules(getAward(), getCentralAdminContact());
        if(success){
            getAward().add(getUnitContact());
            init();
        }
    }

    /**
     * @return
     */
    public AwardUnitContact getCentralAdminContact() {
        return (AwardUnitContact) newAwardContact;
    }
    
    /**
     * Remove a Central Admin unit contact
     * @param lineToDelete
     */
    public void deleteContact(int lineToDelete) {
        super.deleteUnitContact(getCentralAdminContacts(), lineToDelete);
    }

    /**
     * This method finds the count of AwardContacts in the "Central Administrator" category
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