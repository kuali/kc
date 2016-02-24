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
package org.kuali.coeus.common.framework.contact;

import java.io.Serializable;

import org.kuali.coeus.common.framework.unit.Unit;

/**
 * This interface defines behaviors for identifying an associated contact 
 */
public interface Contactable {
    /**
     * This method returns the serializable identifier of the contact. May return null if no identifier has yet been assigned.
     * @return
     */
    Serializable getIdentifier();

    /**
     * This method returns the full name of the contact. May be null
     * @return
     */
    String getFullName();
    
    /**
     * This method returns the associated unit
     * @return
     */
    Unit getUnit();
    
    /**
     * This method returns the contact's organization name. May be the unit name or something different. Also, may return null.
     * @return
     */
    String getContactOrganizationName();
    
    /**
     * This method returns the contact's associated unit number.  This is commonly referred to as homeUnit is many places in KC.
     * @return
     */
    String getOrganizationIdentifier();
    
    /**
     * This method returns the contact's e-mail address. May return null.
     * @return
     */
    String getEmailAddress();
    
    /**
     * This method returns the contact's phone number. May return null.
     * @return
     */
    String getPhoneNumber();


    String getFirstName();


    String getLastName();
}
