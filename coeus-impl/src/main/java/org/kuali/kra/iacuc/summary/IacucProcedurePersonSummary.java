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
