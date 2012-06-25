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
package org.kuali.kra.iacuc.summary;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.iacuc.procedures.IacucProcedurePersonResponsible;

public class IacucProcedurePersonSummary implements Serializable {

    private Integer personId;
    private String personName;
    private String personTraining;
    private String description;

    private boolean personNameChanged;
    private boolean personTrainingChanged;
    private boolean descriptionChanged;
    
    public IacucProcedurePersonSummary(IacucProcedurePersonResponsible personResponsible) {
        this.personId = personResponsible.getIacucProcedurePersonResponsibleId();
        this.personName = personResponsible.getPersonName();
        this.personTraining = personResponsible.getTrainingDetailsString();
        this.description = personResponsible.getPersonResponsibleDescription();
        this.personNameChanged = false;
        this.personTrainingChanged = false;
        this.descriptionChanged = false;
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
    
    public void compare(IacucProcedurePersonSummary other) {
        personNameChanged = !StringUtils.equals(personName, other.personName);
        personTrainingChanged = !StringUtils.equals(personTraining, other.personTraining);
        descriptionChanged = !StringUtils.equals(description, other.description);
    }
}
