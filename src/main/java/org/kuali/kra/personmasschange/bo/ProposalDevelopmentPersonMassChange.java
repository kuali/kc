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

public class ProposalDevelopmentPersonMassChange extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = 9149269453548364402L;
    
    private long proposalDevelopmentPersonMassChangeId;
    private long personMassChangeId;
    private boolean investigator;
    private boolean mailingInformation;
    private boolean keyStudyPerson;
    private boolean budgetPerson;
    
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
    
    public boolean isBudgetPerson() {
        return budgetPerson;
    }
    
    public void setBudgetPerson(boolean budgetPerson) {
        this.budgetPerson = budgetPerson;
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
        propMap.put("proposalDevelopmentPersonMassChangeId", getProposalDevelopmentPersonMassChangeId());
        propMap.put("personMassChangeId", getPersonMassChangeId());
        propMap.put("investigator", isInvestigator());
        propMap.put("mailingInformation", isMailingInformation());
        propMap.put("keyStudyPerson", isKeyStudyPerson());
        propMap.put("budgetPerson", isBudgetPerson());
        return propMap;
    }
    
}