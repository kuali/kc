/*
 * Copyright 2005-2014 The Kuali Foundation
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
