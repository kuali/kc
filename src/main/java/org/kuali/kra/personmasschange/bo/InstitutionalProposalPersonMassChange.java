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

public class InstitutionalProposalPersonMassChange extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1612664382231281828L;
    
    private long institutionalProposalPersonMassChangeId;
    private long personMassChangeId;
    private boolean investigator;
    private boolean keyStudyPerson;
    private boolean unitAdministrator;
    private boolean mailingInformation;
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

    public boolean isUnitAdministrator() {
        return unitAdministrator;
    }
    
    public void setUnitAdministrator(boolean unitAdministrator) {
        this.unitAdministrator = unitAdministrator;
    }
    
    public boolean isMailingInformation() {
        return mailingInformation;
    }
    
    public void setMailingInformation(boolean mailingInformation) {
        this.mailingInformation = mailingInformation;
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


}