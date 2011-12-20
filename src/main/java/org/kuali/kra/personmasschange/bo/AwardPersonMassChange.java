/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class AwardPersonMassChange extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = 8844147765907909357L;
    
    private long awardPersonMassChangeId;
    private long personMassChangeId;
    private boolean investigator;
    private boolean contactPerson;
    private boolean foreignTrip;
    private boolean unitAdministrator;
    
    private PersonMassChange personMassChange;

    public long getAwardPersonMassChangeId() {
        return awardPersonMassChangeId;
    }

    public void setAwardPersonMassChangeId(long awardPersonMassChangeId) {
        this.awardPersonMassChangeId = awardPersonMassChangeId;
    }

    public long getPersonMassChangeId() {
        return personMassChangeId;
    }

    public void setPersonMassChangeId(long personMassChangeId) {
        this.personMassChangeId = personMassChangeId;
    }

    public boolean isInvestigator() {
        return investigator;
    }
    
    public void setInvestigator(boolean investigator) {
        this.investigator = investigator;
    }
    
    public boolean isContactPerson() {
        return contactPerson;
    }
    
    public void setContactPerson(boolean contactPerson) {
        this.contactPerson = contactPerson;
    }
    
    public boolean isForeignTrip() {
        return foreignTrip;
    }
    
    public void setForeignTrip(boolean foreignTrip) {
        this.foreignTrip = foreignTrip;
    }
    
    public boolean isUnitAdministrator() {
        return unitAdministrator;
    }
    
    public void setUnitAdministrator(boolean unitAdministrator) {
        this.unitAdministrator = unitAdministrator;
    }

    public PersonMassChange getPersonMassChange() {
        return personMassChange;
    }

    public void setPersonMassChange(PersonMassChange personMassChange) {
        this.personMassChange = personMassChange;
    }

    @Override
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> propMap = new LinkedHashMap<String, Object>();
        propMap.put("awardPersonMassChangeId", getAwardPersonMassChangeId());
        propMap.put("personMassChangeId", getPersonMassChangeId());
        propMap.put("investigator", isInvestigator());
        propMap.put("contactPerson", isContactPerson());
        propMap.put("foreignTrip", isForeignTrip());
        propMap.put("unitAdministrator", isUnitAdministrator());
        return propMap;
    }

}