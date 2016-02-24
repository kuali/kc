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
package org.kuali.kra.personmasschange.bo;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * Defines the fields for a Subaward Person Mass Change.
 */
public class SubawardPersonMassChange extends KcPersistableBusinessObjectBase {
    
    private static final long serialVersionUID = -3975738713871095626L;
    
    private long subawardPersonMassChangeId;
    private long personMassChangeId;
    private boolean requisitioner;
    private boolean contact;
    
    private PersonMassChange personMassChange;

    public long getSubawardPersonMassChangeId() {
        return subawardPersonMassChangeId;
    }

    public void setSubawardPersonMassChangeId(long subawardPersonMassChangeId) {
        this.subawardPersonMassChangeId = subawardPersonMassChangeId;
    }

    public long getPersonMassChangeId() {
        return personMassChangeId;
    }

    public void setPersonMassChangeId(long personMassChangeId) {
        this.personMassChangeId = personMassChangeId;
    }

    public boolean isContact() {
        return contact;
    }
    
    public void setContact(boolean contact) {
        this.contact = contact;
    }
    
    public boolean isRequisitioner() {
        return requisitioner;
    }
    
    public void setRequisitioner(boolean requisitioner) {
        this.requisitioner = requisitioner;
    }
    
    public PersonMassChange getPersonMassChange() {
        return personMassChange;
    }

    public void setPersonMassChange(PersonMassChange personMassChange) {
        this.personMassChange = personMassChange;
    }
    
    /**
     * Determines whether this Person Mass Change is required.
     * 
     * @return true if any of the fields are true, false otherwise
     */
    public boolean requiresChange() {
        return requisitioner || contact;
    }

}
