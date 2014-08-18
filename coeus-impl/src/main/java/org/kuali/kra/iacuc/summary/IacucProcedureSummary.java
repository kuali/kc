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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.iacuc.procedures.IacucProcedure;
import org.kuali.kra.iacuc.procedures.IacucProcedureCategory;
import org.kuali.kra.iacuc.procedures.IacucProcedurePersonResponsible;
import org.kuali.kra.iacuc.procedures.IacucProtocolStudyCustomData;
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
    private List<IacucProcedurePersonSummary>personSummaries;
    private List<IacucProcedureCustomDataSummary>customDataSummaries;
    private int     count;
    
    private boolean procedureCategoryChanged;
    private boolean speciesChanged;
    private boolean speciesStrainChanged;
    private boolean painCategoryChanged;
    private boolean countChanged;
    
    public IacucProcedureSummary(IacucProtocolStudyGroup studyGroup, IacucProcedureCategory iacucProcedureCategory, 
            IacucProcedure iacucProcedure) { 
        procedureCode = iacucProcedure.getProcedureCode();
        species = studyGroup.getIacucProtocolSpecies() == null ? "None" : studyGroup.getIacucProtocolSpecies().getSpeciesName();
        speciesStrain = studyGroup.getIacucProtocolSpecies() == null ? "None" : studyGroup.getIacucProtocolSpecies().getStrain();
        painCategory = studyGroup.getIacucPainCategory() == null ? "None" : studyGroup.getIacucPainCategory().getPainCategory();
        
        procedureCategory = iacucProcedureCategory.getProcedureCategory() + " - " + iacucProcedure.getProcedureDescription();
        
        personSummaries = new ArrayList<IacucProcedurePersonSummary>();  
        for (IacucProcedurePersonResponsible person: studyGroup.getIacucProcedurePersonResponsibleList()) {
            personSummaries.add(new IacucProcedurePersonSummary(person));
        }
        locationSummaries = new ArrayList<IacucProcedureLocationSummary>();
        for (IacucProtocolStudyGroupLocation location: studyGroup.getIacucProcedureLocationResponsibleList()) {
            locationSummaries.add(new IacucProcedureLocationSummary(location));
        }
        customDataSummaries = new ArrayList<IacucProcedureCustomDataSummary>();
        for (IacucProtocolStudyCustomData customDataItem: studyGroup.getIacucProtocolStudyCustomDataList()) {
            customDataSummaries.add(new IacucProcedureCustomDataSummary(customDataItem));
        }
        count = studyGroup.getCount().intValue();
        procedureCategoryChanged = false;
        speciesChanged = false;
        speciesStrainChanged = false;
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

    public List<IacucProcedurePersonSummary> getPersonSummaries() {
        return personSummaries;
    }

    public void setPersonSummaries(List<IacucProcedurePersonSummary> personsResponsible) {
        this.personSummaries = personsResponsible;
    }

    public String getPersonnelList() {
        String results = "";
        if (personSummaries != null) {
            for (IacucProcedurePersonSummary person: personSummaries) {
                if (results.length() > 0) {
                    results += "<br/>";
                }
                results += person.getPersonName();
            }
        }
        return results;
    }
    
    public List<IacucProcedureLocationSummary> getLocationSummaries() {
        return locationSummaries;
    }

    public void setLocationSummaries(List<IacucProcedureLocationSummary> locationSummaries) {
        this.locationSummaries = locationSummaries;
    }

    
    public List<IacucProcedureCustomDataSummary> getCustomDataSummaries() {
        return customDataSummaries;
    }

    public void setCustomDataSummaries(List<IacucProcedureCustomDataSummary> customDataSummaries) {
        this.customDataSummaries = customDataSummaries;
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

    public void compare(IacucProtocolSummary other) {
        IacucProcedureSummary otherSummary = (other == null) ? null : other.findProcedureSummary(procedureCode);
        if (otherSummary == null) {
            procedureCategoryChanged = true;
            speciesChanged = true;
            speciesStrainChanged = false;
            painCategoryChanged = false;
        } else {
            procedureCategoryChanged = !procedureCategory.equals(otherSummary.procedureCategory);
            speciesChanged = !StringUtils.equals(species, otherSummary.species);
            speciesStrainChanged = !StringUtils.equals(speciesStrain, otherSummary.speciesStrain);
            painCategoryChanged = !StringUtils.equals(painCategory, otherSummary.painCategory);
        }
        compareProcedureCustomDataSummaries(otherSummary);
        compareProcedurePersonSummaries(otherSummary);
        compareProcedureLocationSummaries(otherSummary);
    }

    public void compareProcedureCustomDataSummaries(IacucProcedureSummary other) {
        for (IacucProcedureCustomDataSummary mySummary : customDataSummaries) {
            mySummary.compare(other);
        }
    }
    
    public IacucProcedureCustomDataSummary findProcedureCustomDataSummary(Long id) {
        for (IacucProcedureCustomDataSummary customDataSummary : customDataSummaries) {
            if (customDataSummary.getId().equals(id)) {
                return customDataSummary;
            }
        }
        return null;
    }

    public void compareProcedureLocationSummaries(IacucProcedureSummary other) {
        for (IacucProcedureLocationSummary mySummary : locationSummaries) {
            mySummary.compare(other);
        }
    }
    
    public IacucProcedureLocationSummary findProcedureLocationSummary(Integer id) {
        for (IacucProcedureLocationSummary locationSummary : locationSummaries) {
            if (locationSummary.getId().equals(id)) {
                return locationSummary;
            }
        }
        return null;
    }

    public void compareProcedurePersonSummaries(IacucProcedureSummary other) {
        for (IacucProcedurePersonSummary mySummary : personSummaries) {
            mySummary.compare(other);
        }
    }
    
    public IacucProcedurePersonSummary findProcedurePersonSummary(String id) {
        for (IacucProcedurePersonSummary personSummary : personSummaries) {
            if (personSummary.getPersonId().equals(id)) {
                return personSummary;
            }
        }
        return null;
    }

    public boolean isPersonnelListChanged() {
        for (IacucProcedurePersonSummary personSummary : personSummaries) {
            if (personSummary.isPersonNameChanged()) {
                return true;
            }
        }
        return false;        
    }
}