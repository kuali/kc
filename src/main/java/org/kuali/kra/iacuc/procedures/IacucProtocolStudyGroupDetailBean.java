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
package org.kuali.kra.iacuc.procedures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.iacuc.IacucPainCategory;

public class IacucProtocolStudyGroupDetailBean implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5919176798738325709L;

    private Integer speciesCode;
    
    /* These fields are for group display in tag */
    // list that holds species and groups description
    List<String> speciesAndGroupsText;
    // max pain category for selected species group
    String maxPainCategory;
    // sum of all species count for the category and species selected
    Integer totalSpeciesCount;
    
    Integer maxPainCategoryCode;

    Integer maxPainLevel;
    
    private IacucPainCategory maxIacucPainCategory;

    private List<IacucProtocolStudyGroup> iacucProtocolStudyGroups;
    private List<IacucProcedurePersonResponsible> iacucProcedurePersonsResponsible;
    private List<IacucProtocolStudyGroupLocation> iacucProtocolStudyGroupLocations;

    private IacucProcedurePersonResponsible newIacucProcedurePersonResponsible;
    private IacucProtocolStudyGroupLocation newIacucProtocolStudyGroupLocation;
    
    public IacucProtocolStudyGroupDetailBean() {
        setSpeciesAndGroupsText(new ArrayList<String>());
        setIacucProcedurePersonsResponsible(new ArrayList<IacucProcedurePersonResponsible>());
        setIacucProtocolStudyGroups(new ArrayList<IacucProtocolStudyGroup>());
        setIacucProtocolStudyGroupLocations(new ArrayList<IacucProtocolStudyGroupLocation>());
    }
    
    public List<String> getSpeciesAndGroupsText() {
        return speciesAndGroupsText;
    }

    public void setSpeciesAndGroupsText(List<String> speciesAndGroupsText) {
        this.speciesAndGroupsText = speciesAndGroupsText;
    }

    public String getMaxPainCategory() {
        return maxPainCategory;
    }

    public void setMaxPainCategory(String maxPainCategory) {
        this.maxPainCategory = maxPainCategory;
    }

    public Integer getTotalSpeciesCount() {
        return totalSpeciesCount;
    }

    public void setTotalSpeciesCount(Integer totalSpeciesCount) {
        this.totalSpeciesCount = totalSpeciesCount;
    }

    public List<IacucProtocolStudyGroup> getIacucProtocolStudyGroups() {
        return iacucProtocolStudyGroups;
    }

    public void setIacucProtocolStudyGroups(List<IacucProtocolStudyGroup> iacucProtocolStudyGroups) {
        this.iacucProtocolStudyGroups = iacucProtocolStudyGroups;
    }
    
    public List<IacucProcedurePersonResponsible> getIacucProcedurePersonsResponsible() {
        return iacucProcedurePersonsResponsible;
    }

    public void setIacucProcedurePersonsResponsible(List<IacucProcedurePersonResponsible> iacucProcedurePersonsResponsible) {
        this.iacucProcedurePersonsResponsible = iacucProcedurePersonsResponsible;
    }

    public List<String> getListOfPersonsResponsible() {
        List<String> formattedPersonsResponsible = new ArrayList<String>();
        for(IacucProcedurePersonResponsible personResponsible : getIacucProcedurePersonsResponsible()) {
            formattedPersonsResponsible.add(personResponsible.getPersonName());
        }
        return formattedPersonsResponsible;
    }

    public IacucProcedurePersonResponsible getNewIacucProcedurePersonResponsible() {
        return newIacucProcedurePersonResponsible;
    }

    public void setNewIacucProcedurePersonResponsible(IacucProcedurePersonResponsible newIacucProcedurePersonResponsible) {
        this.newIacucProcedurePersonResponsible = newIacucProcedurePersonResponsible;
    }

    public Integer getMaxPainCategoryCode() {
        return maxPainCategoryCode;
    }

    public void setMaxPainCategoryCode(Integer maxPainCategoryCode) {
        this.maxPainCategoryCode = maxPainCategoryCode;
    }

    public Integer getMaxPainLevel() {
        return maxPainLevel;
    }

    public void setMaxPainLevel(Integer maxPainLevel) {
        this.maxPainLevel = maxPainLevel;
    }

    public List<IacucProtocolStudyGroupLocation> getIacucProtocolStudyGroupLocations() {
        return iacucProtocolStudyGroupLocations;
    }

    public void setIacucProtocolStudyGroupLocations(List<IacucProtocolStudyGroupLocation> iacucProtocolStudyGroupLocations) {
        this.iacucProtocolStudyGroupLocations = iacucProtocolStudyGroupLocations;
    }

    public IacucProtocolStudyGroupLocation getNewIacucProtocolStudyGroupLocation() {
        return newIacucProtocolStudyGroupLocation;
    }

    public void setNewIacucProtocolStudyGroupLocation(IacucProtocolStudyGroupLocation newIacucProtocolStudyGroupLocation) {
        this.newIacucProtocolStudyGroupLocation = newIacucProtocolStudyGroupLocation;
    }

    public Integer getSpeciesCode() {
        return speciesCode;
    }

    public void setSpeciesCode(Integer speciesCode) {
        this.speciesCode = speciesCode;
    }

    public IacucPainCategory getMaxIacucPainCategory() {
        return maxIacucPainCategory;
    }

    public void setMaxIacucPainCategory(IacucPainCategory maxIacucPainCategory) {
        this.maxIacucPainCategory = maxIacucPainCategory;
    }
    
}
