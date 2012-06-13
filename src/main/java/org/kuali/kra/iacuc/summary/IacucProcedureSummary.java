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
    private String  procedureCategory; 
    private String  procedureDescription; 
    private Integer painCategory; 
    private Integer count; 
    private String  countType;
    private String  personnel;
    
    private boolean procedureCategoryChanged;
    private boolean procedureDescriptionChanged;
    private boolean speciesChanged;
    private boolean painCategoryChanged;
    private boolean countChanged;
    private boolean countTypeChanged;
    private boolean personnelChanged;
    
    public IacucProcedureSummary(IacucProtocolStudyGroup studyGroup) { 
        procedureCode = studyGroup.getProcedureCode();
        procedureDescription = studyGroup.getIacucProcedure().getProcedureDescription();
        species = studyGroup.getIacucProtocolSpecies().getSpeciesName();
        procedureCategory = studyGroup.getIacucProcedureCategory().getProcedureCategory() + " - " + 
                            studyGroup.getIacucProcedure().getProcedureDescription();
        procedureDescription = studyGroup.getIacucProcedure().getProcedureDescription();
        painCategory = studyGroup.getPainCategoryCode();
        count = studyGroup.getCount();
        countType = studyGroup.getIacucProtocolSpecies().getCountTypeName();
        personnel = "";
        for (IacucProcedurePersonResponsible person: studyGroup.getIacucProcedurePersonsResponsible()) {
            if (personnel.length() > 0) {
                personnel += ", ";
            }
            personnel += person.getPersonName();
        }
        procedureCategoryChanged = false;
        procedureDescriptionChanged = false;
        speciesChanged = false;
        painCategoryChanged = false;
        countChanged = false;
        countTypeChanged = false;
        personnelChanged = false;
    } 
    
    public String getCountType() {
        return countType;
    }

    public void setCountType(String countType) {
        this.countType = countType;
    }

    public boolean isCountTypeChanged() {
        return countTypeChanged;
    }

    public void setCountTypeChanged(boolean countTypeChanged) {
        this.countTypeChanged = countTypeChanged;
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

    public Integer getPainCategory() {
        return painCategory;
    }

    public void setPainCategory(Integer painCategory) {
        this.painCategory = painCategory;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public boolean isSpeciesChanged() {
        return speciesChanged;
    }

    public void setSpeciesChanged(boolean speciesChanged) {
        this.speciesChanged = speciesChanged;
    }

    public boolean isPainCategoryChanged() {
        return painCategoryChanged;
    }

    public void setPainCategoryChanged(boolean painCategoryChanged) {
        this.painCategoryChanged = painCategoryChanged;
    }

    public boolean isCountChanged() {
        return countChanged;
    }

    public void setCountChanged(boolean countChanged) {
        this.countChanged = countChanged;
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
            countChanged = true;
            countTypeChanged = true;
            speciesChanged = true;
            painCategoryChanged = true;
            personnelChanged = true;
        } else {
            procedureCategoryChanged = !procedureCategory.equals(otherSummary.procedureCategory);
            procedureDescriptionChanged = !StringUtils.equals(procedureDescription, otherSummary.procedureDescription);
            countChanged = !count.equals(otherSummary.count);
            countTypeChanged = !StringUtils.equals(countType, otherSummary.countType);
            speciesChanged = !StringUtils.equals(species, otherSummary.species);
            painCategoryChanged = !painCategory.equals(otherSummary.painCategory);
            personnelChanged = !StringUtils.equals(personnel, otherSummary.personnel);
        }
    }

}