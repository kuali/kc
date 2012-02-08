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

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class UnitAdministratorPersonMassChange extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = 8879854562611984676L;
    
    private long unitAdministratorPersonMassChangeId;
    private long personMassChangeId;
    private boolean administrativeOfficer;
    private boolean ospAdministrator;
    private boolean unitHead;
    private boolean deanVP;
    private boolean otherIndividualToNotify;
    private boolean administrator;
    
    private PersonMassChange personMassChange;

    public long getUnitAdministratorPersonMassChangeId() {
        return unitAdministratorPersonMassChangeId;
    }

    public void setUnitAdministratorPersonMassChangeId(long unitAdministratorPersonMassChangeId) {
        this.unitAdministratorPersonMassChangeId = unitAdministratorPersonMassChangeId;
    }

    public long getPersonMassChangeId() {
        return personMassChangeId;
    }

    public void setPersonMassChangeId(long personMassChangeId) {
        this.personMassChangeId = personMassChangeId;
    }

    public boolean isAdministrativeOfficer() {
        return administrativeOfficer;
    }

    public void setAdministrativeOfficer(boolean administrativeOfficer) {
        this.administrativeOfficer = administrativeOfficer;
    }

    public boolean isOspAdministrator() {
        return ospAdministrator;
    }

    public void setOspAdministrator(boolean ospAdministrator) {
        this.ospAdministrator = ospAdministrator;
    }

    public boolean isUnitHead() {
        return unitHead;
    }

    public void setUnitHead(boolean unitHead) {
        this.unitHead = unitHead;
    }

    public boolean isDeanVP() {
        return deanVP;
    }

    public void setDeanVP(boolean deanVP) {
        this.deanVP = deanVP;
    }

    public boolean isOtherIndividualToNotify() {
        return otherIndividualToNotify;
    }

    public void setOtherIndividualToNotify(boolean otherIndividualToNotify) {
        this.otherIndividualToNotify = otherIndividualToNotify;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }
    
    public PersonMassChange getPersonMassChange() {
        return personMassChange;
    }

    public void setPersonMassChange(PersonMassChange personMassChange) {
        this.personMassChange = personMassChange;
    }

}