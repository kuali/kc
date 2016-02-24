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

import java.util.HashMap;
import java.util.Map;

import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.award.home.ContactType;

/**
 * This class models the SponsorContact
 */
public class AwardSponsorContact extends AwardContact {
    private static final long serialVersionUID = 4554226190495156865L;
    
    private ContactType contactType;
    
    public AwardSponsorContact() {
        super();
    }
    
    public AwardSponsorContact(NonOrganizationalRolodex rolodex, ContactRole contactRole) {
        super(rolodex, contactRole);
    }

    public AwardSponsorContact(KcPerson person, ContactRole role) {
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

    @SuppressWarnings("unchecked")
    @Override
    protected Class getContactRoleType() {
        return ContactType.class;
    }

    @Override
    protected Map<String, Object> getContactRoleIdentifierMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("contactTypeCode", getRoleCode());
        return map;
     }

}
