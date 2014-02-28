/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.framework.rolodex.nonorg;

import java.io.Serializable;

import org.kuali.coeus.common.framework.contactable.Contactable;
import org.kuali.coeus.common.framework.rolodex.Rolodex;

/**
 * Special Business Object for NonOrganizational types of <code>{@link Rolodex}</code> instances.
 * 
 */
public class NonOrganizationalRolodex extends Rolodex implements Contactable {
    private static final long serialVersionUID = -4699230471690515157L;

    /**
     * @see org.kuali.coeus.common.framework.contactable.Contactable#getIdentifier()
     */
    public Serializable getIdentifier() {
        return getRolodexId();
    }

    /**
     * @see org.kuali.coeus.common.framework.contactable.Contactable#setIdentifier(java.io.Serializable)
     */
    public void setIdentifier(Serializable identifier) {
        setRolodexId((Integer) identifier);
    }

    /**
     * @see org.kuali.coeus.common.framework.contactable.Contactable#getContactOrganizationName()
     */
    public String getContactOrganizationName() {
        return getOrganization();
    }

    /**
     * @see org.kuali.coeus.common.framework.contactable.Contactable#getOrganizationIdentifier()
     */
    public String getOrganizationIdentifier() {
        return getOwnedByUnit();
    }
}
