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
 * Defines the fields for an Institutional Proposal Person Mass Change.
 */
public class InstitutionalProposalPersonMassChange extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = -8946117068743211033L;
    
    private long institutionalProposalPersonMassChangeId;
    private long personMassChangeId;
    private boolean investigator;
    private boolean keyStudyPerson;
    private boolean mailingInformation;
    private boolean unitContact;
    private boolean ipReviewer;
    
    private PersonMassChange personMassChange;

    public long getInstitutionalProposalPersonMassChangeId() {
        return institutionalProposalPersonMassChangeId;
    }

    public void setInstitutionalProposalPersonMassChangeId(long institutionalProposalPersonMassChangeId) {
        this.institutionalProposalPersonMassChangeId = institutionalProposalPersonMassChangeId;
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

    public boolean isKeyStudyPerson() {
        return keyStudyPerson;
    }

    public void setKeyStudyPerson(boolean keyStudyPerson) {
        this.keyStudyPerson = keyStudyPerson;
    }

    public boolean isMailingInformation() {
        return mailingInformation;
    }
    
    public void setMailingInformation(boolean mailingInformation) {
        this.mailingInformation = mailingInformation;
    }

    public boolean isUnitContact() {
        return unitContact;
    }

    public void setUnitContact(boolean unitContact) {
        this.unitContact = unitContact;
    }

    public boolean isIpReviewer() {
        return ipReviewer;
    }
    
    public void setIpReviewer(boolean ipReviewer) {
        this.ipReviewer = ipReviewer;
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
        return investigator || keyStudyPerson || mailingInformation || unitContact || ipReviewer;
    }

}
