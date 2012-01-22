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

public class ProtocolPersonMassChange extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = -1385372093731302793L;
    
    private long protocolPersonMassChangeId;
    private long personMassChangeId;
    private boolean investigator;
    private boolean keyStudyPerson;
    private boolean correspondents;
    private boolean reviewer;
    
    private PersonMassChange personMassChange;

    public long getProtocolPersonMassChangeId() {
        return protocolPersonMassChangeId;
    }

    public void setProtocolPersonMassChangeId(long protocolPersonMassChangeId) {
        this.protocolPersonMassChangeId = protocolPersonMassChangeId;
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
    
    public boolean isCorrespondents() {
        return correspondents;
    }
    
    public void setCorrespondents(boolean correspondents) {
        this.correspondents = correspondents;
    }
    
    public boolean isReviewer() {
        return reviewer;
    }
    
    public void setReviewer(boolean reviewer) {
        this.reviewer = reviewer;
    }
    
    public PersonMassChange getPersonMassChange() {
        return personMassChange;
    }

    public void setPersonMassChange(PersonMassChange personMassChange) {
        this.personMassChange = personMassChange;
    }

}