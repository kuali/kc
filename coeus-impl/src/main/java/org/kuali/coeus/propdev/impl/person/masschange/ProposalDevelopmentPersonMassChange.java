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
package org.kuali.coeus.propdev.impl.person.masschange;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.personmasschange.bo.PersonMassChange;

/**
 * Defines the fields for a Proposal Development Person Mass Change.
 */
public class ProposalDevelopmentPersonMassChange extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 9149269453548364402L;
    
    private long proposalDevelopmentPersonMassChangeId;
    private long personMassChangeId;
    private boolean investigator;
    private boolean mailingInformation;
    private boolean keyStudyPerson;
    
    private PersonMassChange personMassChange;

    public long getProposalDevelopmentPersonMassChangeId() {
        return proposalDevelopmentPersonMassChangeId;
    }

    public void setProposalDevelopmentPersonMassChangeId(long proposalDevelopmentPersonMassChangeId) {
        this.proposalDevelopmentPersonMassChangeId = proposalDevelopmentPersonMassChangeId;
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

    public boolean isMailingInformation() {
        return mailingInformation;
    }
    
    public void setMailingInformation(boolean mailingInformation) {
        this.mailingInformation = mailingInformation;
    }
    
    public boolean isKeyStudyPerson() {
        return keyStudyPerson;
    }
    
    public void setKeyStudyPerson(boolean keyStudyPerson) {
        this.keyStudyPerson = keyStudyPerson;
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
        return investigator || mailingInformation || keyStudyPerson;
    }

}