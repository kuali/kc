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

/**
 * This class models the SponsorContact
 */
public class SponsorContact extends AwardContact {
    private static final long serialVersionUID = 4554226190495156865L;
    
    private ContactType contactType;
    
    public SponsorContact() {
        super();
    }
    
    public SponsorContact(NonOrganizationalRolodex rolodex, ContactRole contactRole) {
        super(rolodex, contactRole);
    }

    public SponsorContact(Person person, ContactRole role) {
        super(person, role);
    }

    /**
     * Gets the contactType attribute. 
     * @return Returns the contactType.
     */
    public ContactType getContactType() {
        return contactType;
    }
 
    @Override
    public void setContactRole(ContactRole contactRole) {
        super.setContactRole(contactRole);
        setContactType((ContactType) contactRole);
    }

    /**
     * Sets the contactType attribute value.
     * @param contactType The contactType to set.
     */
    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    /**
     * @see org.kuali.kra.award.contacts.AwardContact#getContactRoleType()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected Class getContactRoleType() {
        return ContactType.class;
    }
    /**
     * @see org.kuali.kra.award.contacts.AwardContact#getContactRoleTypeIdentifier()
     */
    @Override
    protected String getContactRoleTypeIdentifier() {
        return "contactTypeCode";
    }
}
