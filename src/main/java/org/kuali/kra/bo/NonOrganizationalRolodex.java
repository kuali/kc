/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.bo;

import java.io.Serializable;

/**
 * Special Business Object for NonOrganizational types of <code>{@link Rolodex}</code> instances.
 * 
 */
public class NonOrganizationalRolodex extends Rolodex implements Contactable {
    private static final long serialVersionUID = -4699230471690515157L;

    /**
     * @see org.kuali.kra.bo.Contactable#getIdentifier()
     */
    public Serializable getIdentifier() {
        return getRolodexId();
    }

    /**
     * @see org.kuali.kra.bo.Contactable#setIdentifier(java.io.Serializable)
     */
    public void setIdentifier(Serializable identifier) {
        setRolodexId((Integer) identifier);
    }

    /**
     * @see org.kuali.kra.bo.Contactable#getContactOrganizationName()
     */
    public String getContactOrganizationName() {
        return getOrganization();
    }

    /**
     * @see org.kuali.kra.bo.Contactable#getOrganizationIdentifier()
     */
    public String getOrganizationIdentifier() {
        return getOwnedByUnit();
    }
}
