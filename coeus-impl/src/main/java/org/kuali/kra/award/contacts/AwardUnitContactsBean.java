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

import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.unit.UnitContactType;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministrator;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.award.home.ContactType;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides support for the Award Contacts Project Personnel panel
 */
public class AwardUnitContactsBean extends AwardContactsBean {
    private static final long serialVersionUID = 1421235654899276682L;
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
                newAwardUnitContact.setDefaultUnitContact(true);
                getAward().add(newAwardUnitContact);
            }
        }
    }
    
    
    public UnitService getUnitService() {
        return (UnitService) KcServiceLocator.getService(UnitService.class);
    }
    
    public KcPersonService getKcPersonService() {
        return (KcPersonService) KcServiceLocator.getService(KcPersonService.class);
    }


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

    @Override
    protected Class<? extends ContactRole> getContactRoleType() {
        return ContactType.class;
    }

    @Override
    protected AwardContact createNewContact() {
        return new AwardUnitContact(UnitContactType.CONTACT);
    }
}
