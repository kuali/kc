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

import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.award.home.ContactType;
import org.kuali.kra.bo.NonOrganizationalRolodex;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.UnitAdministratorType;
import org.kuali.kra.bo.UnitContactType;

/**
 * This class models a Unit Contact
 */
public class AwardUnitContact extends AwardContact {
    private static final String OSP_ADMINISTRATOR = "OSP_ADMINISTRATOR";
    private static final String UNIT_ADMINISTRATOR_TYPE_CODE = "UNIT_ADMINISTRATOR_TYPE_CODE";
    private static final String CONTACT_TYPE_CODE = "CONTACT_TYPE_CODE";
    private static final long serialVersionUID = -9168956728201616266L;
    private UnitContactType unitContactType;

    /**
     * Constructs a AwardUnitContact.java.
     */
    public AwardUnitContact() {
        super();
    }
    
    /**
     * Constructs a AwardUnitContact.java.
     * @param rolodex
     * @param role
     * @param unitContactType
     */
    public AwardUnitContact(NonOrganizationalRolodex rolodex, ContactRole role, UnitContactType unitContactType) {
        super(rolodex, role);
        this.unitContactType = unitContactType;
    }

    /**
     * Constructs a AwardUnitContact.java.
     * @param person
     * @param role
     * @param unitContactType
     */
    public AwardUnitContact(Person person, ContactRole role, UnitContactType unitContactType) {
        super(person, role);
        this.unitContactType = unitContactType;
    }

    /**
     * Constructs a AwardUnitContact.java.
     * @param unitContactType
     */
    AwardUnitContact(UnitContactType unitContactType) {
        this.unitContactType = unitContactType;
    }
    
    /**
     * @return
     */
    public UnitContactType getUnitContactType() {
        return unitContactType;
    }

    /**
     * This method determines if unit contact is an OSP Admin
     * @return
     */
    public boolean isOspAdministrator() {
        boolean ospAdmin;
        if(getUnitContactType() == UnitContactType.ADMINISTRATOR && roleCode != null && getContactRole() == null) {
            ospAdmin = OSP_ADMINISTRATOR.equals(refreshContactRole().getRoleDescription());
        } else {
            ospAdmin = false;
        }
        return ospAdmin;
    }
    
    public void setUnitContactType(UnitContactType contactType) {
        this.unitContactType = contactType;
    }

    /**
     * @see org.kuali.kra.award.contacts.AwardContact#getContactRoleType()
     */
    @Override
    protected Class<?extends ContactRole> getContactRoleType() {
        return getUnitContactType() == UnitContactType.ADMINISTRATOR ? UnitAdministratorType.class : ContactType.class;
    }
    /**
     * @see org.kuali.kra.award.contacts.AwardContact#getContactRoleTypeIdentifier()
     */
    @Override
    protected String getContactRoleTypeIdentifier() {
        return  getUnitContactType() == UnitContactType.ADMINISTRATOR ? UNIT_ADMINISTRATOR_TYPE_CODE : CONTACT_TYPE_CODE;
    }
}
