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
package org.kuali.kra.iacuc.summary;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.iacuc.procedures.IacucProcedurePersonResponsible;

import java.io.Serializable;

public class IacucProcedurePersonSummary implements Serializable {

    private String personId;
    private String personName;
    private String personTraining;
    private String description;

    private boolean personNameChanged;
    private boolean personTrainingChanged;
    private boolean descriptionChanged;
    
    public IacucProcedurePersonSummary(IacucProcedurePersonResponsible personResponsible) {
        this.personId = personResponsible.getPersonId();
        this.personName = personResponsible.getPersonName();
        this.personTraining = personResponsible.getTrainingDetailsString();
        this.description = personResponsible.getPersonResponsibleDescription();
        this.personNameChanged = false;
        this.personTrainingChanged = false;
        this.descriptionChanged = false;
    }
        
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }


    public String getPersonName() {
        return personName;
    }
    public void setPersonName(String personName) {
        this.personName = personName;
    }
    public String getPersonTraining() {
        return personTraining;
    }
    public void setPersonTraining(String personTraining) {
        this.personTraining = personTraining;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isPersonNameChanged() {
        return personNameChanged;
    }
    public void setPersonNameChanged(boolean personNameChanged) {
        this.personNameChanged = personNameChanged;
    }
    public boolean isPersonTrainingChanged() {
        return personTrainingChanged;
    }
    public void setPersonTrainingChanged(boolean personTrainingChanged) {
        this.personTrainingChanged = personTrainingChanged;
    }
    public boolean isDescriptionChanged() {
        return descriptionChanged;
    }
    public void setDescriptionChanged(boolean descriptionChanged) {
        this.descriptionChanged = descriptionChanged;
    }    
    
    public void compare(IacucProcedureSummary other) {
        IacucProcedurePersonSummary otherSummary = (other == null) ? null : other.findProcedurePersonSummary(personId);
        if (otherSummary == null) {
            personNameChanged = true;
            personTrainingChanged = true;
            descriptionChanged = true;
        } else {
            personNameChanged = !StringUtils.equals(this.personName, otherSummary.personName);
            personTrainingChanged = !StringUtils.equals(this.personTraining, otherSummary.personTraining);
            descriptionChanged = !StringUtils.equals(this.description, otherSummary.description);
        }
    }

    public void compare(IacucProcedurePersonSummary other) {
        personNameChanged = !StringUtils.equals(personName, other.personName);
        personTrainingChanged = !StringUtils.equals(personTraining, other.personTraining);
        descriptionChanged = !StringUtils.equals(description, other.description);
    }
}
