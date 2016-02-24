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
 * Defines the fields for a Unit Administrator Person Mass Change.
 */
public class UnitAdministratorPersonMassChange extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = -5284508183458878623L;
    
    private long unitAdministratorPersonMassChangeId;
    private long personMassChangeId;
    private boolean administrativeOfficer;
    private boolean ospAdministrator;
    private boolean unitHead;
    private boolean deanVP;
    private boolean otherIndividualToNotify;
    private boolean administrativeContact;
    private boolean financialContact;
    
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

    public boolean isAdministrativeContact() {
        return administrativeContact;
    }

    public void setAdministrativeContact(boolean administrativeContact) {
        this.administrativeContact = administrativeContact;
    }

    public boolean isFinancialContact() {
        return financialContact;
    }

    public void setFinancialContact(boolean financialContact) {
        this.financialContact = financialContact;
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
        return administrativeOfficer || ospAdministrator || unitHead || deanVP || otherIndividualToNotify || administrativeContact || financialContact;
    }

}
