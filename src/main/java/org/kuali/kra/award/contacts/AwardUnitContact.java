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

import org.kuali.kra.award.bo.ContactRole;
import org.kuali.kra.award.bo.ContactType;
import org.kuali.kra.bo.NonOrganizationalRolodex;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.UnitContactType;

/**
 * This class models a Unit Contact
 */
public class AwardUnitContact extends AwardContact {
    private static final long serialVersionUID = -9168956728201616266L;
    
    private UnitContactType unitContactType;

    public AwardUnitContact() {
        super();
    }
    public AwardUnitContact(NonOrganizationalRolodex rolodex, ContactRole role, UnitContactType unitContactType) {
        super(rolodex, role);
        this.unitContactType = unitContactType;
    }

    public AwardUnitContact(Person person, ContactRole role, UnitContactType unitContactType) {
        super(person, role);
        this.unitContactType = unitContactType;
    }

    AwardUnitContact(UnitContactType unitContactType) {
        this.unitContactType = unitContactType;
    }
    
    public UnitContactType getUnitContactType() {
        return unitContactType;
    }

    public void setUnitContactType(UnitContactType contactType) {
        this.unitContactType = contactType;
    }

    /**
     * @see org.kuali.kra.award.contacts.AwardContact#getContactRoleType()
     */
    @Override
    protected Class<?extends ContactRole> getContactRoleType() {
        return ContactType.class;
    }
    /**
     * @see org.kuali.kra.award.contacts.AwardContact#getContactRoleTypeIdentifier()
     */
    @Override
    protected String getContactRoleTypeIdentifier() {
        return "CONTACT_TYPE_CODE";
    }
}
