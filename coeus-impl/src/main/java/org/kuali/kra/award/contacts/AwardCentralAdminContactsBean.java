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

import org.apache.commons.collections4.CollectionUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministrator;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardForm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides support for the Award Contacts Project Personnel panel
 */
public class AwardCentralAdminContactsBean implements Serializable{
    private static final long serialVersionUID = 7614119508539116964L;
    protected AwardForm awardForm;
    private List<AwardUnitContact> centralAdminContacts;
    private static final String DEFAULT_GROUP_CODE_FOR_CENTRAL_ADMIN_CONTACTS = "C";
    
    
    public AwardCentralAdminContactsBean(AwardForm awardForm) {
        this.awardForm = awardForm;
    }
    
    public void initCentralAdminContacts() {
        centralAdminContacts = new ArrayList<AwardUnitContact>();
        List<UnitAdministrator> unitAdministrators = getUnitService().retrieveUnitAdministratorsByUnitNumber(awardForm.getAwardDocument().getAward().getUnitNumber());
        for(UnitAdministrator unitAdministrator : unitAdministrators) {
            if(unitAdministrator.getUnitAdministratorType().getDefaultGroupFlag().equals(DEFAULT_GROUP_CODE_FOR_CENTRAL_ADMIN_CONTACTS)) {
                KcPerson person = getKcPersonService().getKcPersonByPersonId(unitAdministrator.getPersonId());
                AwardUnitContact newAwardUnitContact = new AwardUnitContact();
                newAwardUnitContact.setAward(awardForm.getAwardDocument().getAward());
                newAwardUnitContact.setPerson(person);
                newAwardUnitContact.setUnitAdministratorType(unitAdministrator.getUnitAdministratorType());
                newAwardUnitContact.setFullName(person.getFullName());
                centralAdminContacts.add(newAwardUnitContact);
            }
        }
    }
    
    public UnitService getUnitService() {
        return (UnitService) KcServiceLocator.getService(UnitService.class);
    }
    
    public KcPersonService getKcPersonService() {
        return (KcPersonService) KcServiceLocator.getService(KcPersonService.class);
    }


    /**
     * This method finds the count of AwardContacts in the "Central Administrator" category
     * @return The list; may be empty
     */
    public List<AwardUnitContact> getCentralAdminContacts() {
        return centralAdminContacts;
    }
    
    /**
     * This method finds the count of AwardContacts in the "Unit Contacts" category
     * @return The count; may be 0
     */
    public int getCentralAdminContactsCount() {
        if(CollectionUtils.isEmpty(getCentralAdminContacts()))
            return 0;
        return getCentralAdminContacts().size();
    }

}
