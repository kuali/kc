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
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.iacuc.procedures.IacucProcedure;
import org.kuali.kra.iacuc.procedures.IacucProcedurePersonResponsible;
import org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroup;

public class IacucProcedureSummary implements Serializable { 
    
    private static final long serialVersionUID = 4139399374995243748L;
    
    private Integer procedureCode; 
    private String  species;
    private String  speciesStrain;
    private String  procedureCategory; 
    private String  procedureDescription; 
    private String  personnel;
    
    private boolean procedureCategoryChanged;
    private boolean procedureDescriptionChanged;
    private boolean speciesChanged;
    private boolean personnelChanged;
    
    public IacucProcedureSummary(IacucProtocolStudyGroup studyGroup) { 
        procedureCode = studyGroup.getProcedureCode();
        species = studyGroup.getIacucProtocolSpecies().getSpeciesName();
        procedureCategory = studyGroup.getIacucProcedureCategory().getProcedureCategory() + " - " + 
                            studyGroup.getIacucProcedure().getProcedureDescription();
        procedureDescription = studyGroup.getIacucProcedure().getProcedureDescription();
        StringBuffer personStringBuffer = new StringBuffer();
        StringBuffer procedureStringBuffer = new StringBuffer();
        boolean showPrefix = studyGroup.getIacucProcedurePersonsResponsible().size() > 1;  
        for (IacucProcedurePersonResponsible person: studyGroup.getIacucProcedurePersonsResponsible()) {
            if (personStringBuffer.length() > 0) {
                personStringBuffer.append(", ");
            }
            personStringBuffer.append(person.getPersonName());
            if (showPrefix) {
                procedureStringBuffer.append(person.getPersonName() + ": ");
            }
            procedureStringBuffer.append(person.getPersonResponsibleDescription());
            if (showPrefix) {
                procedureStringBuffer.append("<br/>");
            }
        }
        personnel = personStringBuffer.toString();
        procedureDescription = procedureStringBuffer.toString();
        procedureCategoryChanged = false;
        procedureDescriptionChanged = false;
        speciesChanged = false;
        personnelChanged = false;
    } 
    
    public boolean isProcedureCategoryChanged() {
        return procedureCategoryChanged;
    }

    public void setProcedureCategoryChanged(boolean procedureCategoryChanged) {
        this.procedureCategoryChanged = procedureCategoryChanged;
    }

    public boolean isProcedureDescriptionChanged() {
        return procedureDescriptionChanged;
    }

    public void setProcedureDescriptionChanged(boolean procedureDescriptionChanged) {
        this.procedureDescriptionChanged = procedureDescriptionChanged;
    }

    public Integer getProcedureCode() {
        return procedureCode;
    }

    public void setProcedureCode(Integer procedureCode) {
        this.procedureCode = procedureCode;
    }

    public String getProcedureCategory() {
        return procedureCategory;
    }

    public void setProcedureCategory(String procedureCategory) {
        this.procedureCategory = procedureCategory;
    }

    public String getProcedureDescription() {
        return procedureDescription;
    }

    public void setProcedureDescription(String procedureDescription) {
        this.procedureDescription = procedureDescription;
    }
 
    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getSpeciesStrain() {
        return speciesStrain;
    }

    public void setSpeciesStrain(String speciesStrain) {
        this.speciesStrain = speciesStrain;
    }

    public boolean isSpeciesChanged() {
        return speciesChanged;
    }

    public void setSpeciesChanged(boolean speciesChanged) {
        this.speciesChanged = speciesChanged;
    }

    public String getPersonnel() {
        return personnel;
    }

    public void setPersonnel(String personnel) {
        this.personnel = personnel;
    }

    public boolean isPersonnelChanged() {
        return personnelChanged;
    }

    public void setPersonnelChanged(boolean personnelChanged) {
        this.personnelChanged = personnelChanged;
    }

    public void compare(IacucProtocolSummary other) {
        IacucProcedureSummary otherSummary = other.findProcedureSummary(procedureCode);
        if (otherSummary == null) {
            procedureCategoryChanged = true;
            procedureDescriptionChanged = true;
            speciesChanged = true;
            personnelChanged = true;
        } else {
            procedureCategoryChanged = !procedureCategory.equals(otherSummary.procedureCategory);
            procedureDescriptionChanged = !StringUtils.equals(procedureDescription, otherSummary.procedureDescription);
            speciesChanged = !StringUtils.equals(species, otherSummary.species);
            personnelChanged = !StringUtils.equals(personnel, otherSummary.personnel);
        }
    }

}