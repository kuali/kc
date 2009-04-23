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
package org.kuali.kra.bo;

import java.io.Serializable;

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
     * This method sets the identifer on the contact
     * @param identifier
     */
    void setIdentifier(Serializable identifier);
    
    /**
     * This method returns the full name of the contact. May be null
     * @return
     */
    String getFullName();
    
    /**
     * This method returns the associated unit
     * @return
     */
    public Unit getUnit();
    
    /**
     * This method returns the contact's organization name. May be the unit name or something different. Also, may return null.
     * @return
     */
    String getContactOrganizationName();
    
    /**
     * This method returns the contact's associated unit number
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
}
