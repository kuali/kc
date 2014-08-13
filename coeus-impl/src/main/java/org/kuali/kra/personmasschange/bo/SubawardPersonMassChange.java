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