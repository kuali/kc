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
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.iacuc.IacucLocationName;
import org.kuali.kra.iacuc.procedures.IacucProcedure;
import org.kuali.kra.iacuc.procedures.IacucProcedurePersonResponsible;
import org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroup;
import org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupLocation;

public class IacucProcedureSummary implements Serializable { 
    
    private static final long serialVersionUID = 4139399374995243748L;
    
    private Integer procedureCode; 
    private String  species;
    private String  speciesStrain;
    private String  procedureCategory; 
    private String  painCategory;
    private List<IacucProcedureLocationSummary>locationSummaries;
    private List<IacucProcedurePersonSummary>personsResponsible;
    private boolean personnelListChanged;
    private int     count;
    
    private boolean procedureCategoryChanged;
    private boolean speciesChanged;
    private boolean speciesStrainChanged;
    private boolean personnelChanged;
    private boolean painCategoryChanged;
    private boolean countChanged;
    
    public IacucProcedureSummary(IacucProtocolStudyGroup studyGroup) { 
        procedureCode = studyGroup.getProcedureCode();
        species = studyGroup.getIacucProtocolSpecies().getSpeciesName();
        speciesStrain = studyGroup.getIacucProtocolSpecies().getStrain();
        painCategory = studyGroup.getPainCategory();
        procedureCategory = studyGroup.getIacucProcedureCategory().getProcedureCategory() + " - " + 
                            studyGroup.getIacucProcedure().getProcedureDescription();
        personsResponsible = new ArrayList<IacucProcedurePersonSummary>();  
        for (IacucProcedurePersonResponsible person: studyGroup.getIacucProcedurePersonsResponsible()) {
            personsResponsible.add(new IacucProcedurePersonSummary(person));
        }
        locationSummaries = new ArrayList<IacucProcedureLocationSummary>();
        for (IacucProtocolStudyGroupLocation location: studyGroup.getIacucProtocolStudyGroupLocations()) {
            locationSummaries.add(new IacucProcedureLocationSummary(location));
        }
        count = studyGroup.getCount().intValue();
        procedureCategoryChanged = false;
        speciesChanged = false;
        speciesStrainChanged = false;
        personnelChanged = false;
        painCategoryChanged = false;
        countChanged = false;
    } 
    
    public boolean isProcedureCategoryChanged() {
        return procedureCategoryChanged;
    }

    public void setProcedureCategoryChanged(boolean procedureCategoryChanged) {
        this.procedureCategoryChanged = procedureCategoryChanged;
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

    public boolean isPersonnelChanged() {
        return personnelChanged;
    }

    public void setPersonnelChanged(boolean personnelChanged) {
        this.personnelChanged = personnelChanged;
    }

    public List<IacucProcedureLocationSummary> getLocations() {
        return locationSummaries;
    }

    public void setLocations(List<IacucProcedureLocationSummary> locations) {
        this.locationSummaries = locations;
    }

    public String getPainCategory() {
        return painCategory;
    }

    public void setPainCategory(String painCategory) {
        this.painCategory = painCategory;
    }

    public boolean isSpeciesStrainChanged() {
        return speciesStrainChanged;
    }

    public void setSpeciesStrainChanged(boolean speciesStrainChanged) {
        this.speciesStrainChanged = speciesStrainChanged;
    }

    public boolean isPainCategoryChanged() {
        return painCategoryChanged;
    }

    public void setPainCategoryChanged(boolean painCategoryChanged) {
        this.painCategoryChanged = painCategoryChanged;
    }

    public List<IacucProcedurePersonSummary> getPersonsResponsible() {
        return personsResponsible;
    }

    public void setPersonsResponsible(List<IacucProcedurePersonSummary> personsResponsible) {
        this.personsResponsible = personsResponsible;
    }

    public String getPersonnelList() {
        String results = "";
        if (personsResponsible != null) {
            for (IacucProcedurePersonSummary person: personsResponsible) {
                if (results.length() > 0) {
                    results += "<br/>";
                }
                results += person.getPersonName();
            }
        }
        return results;
    }
    public boolean isPersonnelListChanged() {
        return personnelListChanged;
    }
 
    
    public List<IacucProcedureLocationSummary> getLocationSummaries() {
        return locationSummaries;
    }

    public void setLocationSummaries(List<IacucProcedureLocationSummary> locationSummaries) {
        this.locationSummaries = locationSummaries;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isCountChanged() {
        return countChanged;
    }

    public void setCountChanged(boolean countChanged) {
        this.countChanged = countChanged;
    }

    public void setPersonnelListChanged(boolean personnelListChanged) {
        this.personnelListChanged = personnelListChanged;
    }

    public void compare(IacucProtocolSummary other) {
        IacucProcedureSummary otherSummary = other.findProcedureSummary(procedureCode);
        if (otherSummary == null) {
            procedureCategoryChanged = true;
            speciesChanged = true;
            personnelChanged = true;
            speciesStrainChanged = false;
            painCategoryChanged = false;
       } else {
            procedureCategoryChanged = !procedureCategory.equals(otherSummary.procedureCategory);
            speciesChanged = !StringUtils.equals(species, otherSummary.species);
            speciesStrainChanged = !StringUtils.equals(speciesStrain, otherSummary.speciesStrain);
            painCategoryChanged = !StringUtils.equals(painCategory, otherSummary.painCategory);
        }
    }

}