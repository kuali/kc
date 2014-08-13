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
package org.kuali.kra.iacuc.procedures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.kuali.kra.iacuc.IacucSpecies;
import org.kuali.kra.iacuc.species.IacucProtocolSpecies;

public class IacucProtocolSpeciesStudyGroup implements Serializable {

    private static final long serialVersionUID = -4597595947309461496L;

    private Integer speciesCode; 
    private IacucSpecies iacucSpecies;
    private Integer iacucProtocolSpeciesId; 
    private IacucProtocolSpecies iacucProtocolSpecies;

    private IacucProtocolStudyGroup iacucProtocolStudyGroup;

    private List<IacucProtocolStudyGroup> iacucProtocolStudyGroups;
    private List<IacucProtocolStudyGroupBean> responsibleProcedures;
    private Integer totalSpeciesCount = 0;

    private boolean allProceduresSelected;
    
    public IacucProtocolSpeciesStudyGroup() { 
        setIacucProtocolStudyGroups(new ArrayList<IacucProtocolStudyGroup>());
        setResponsibleProcedures(new ArrayList<IacucProtocolStudyGroupBean>());
    } 

    public Integer getSpeciesCode() {
        return speciesCode;
    }

    public void setSpeciesCode(Integer speciesCode) {
        this.speciesCode = speciesCode;
    }

    public IacucSpecies getIacucSpecies() {
        return iacucSpecies;
    }

    public void setIacucSpecies(IacucSpecies iacucSpecies) {
        this.iacucSpecies = iacucSpecies;
    }

    public String getGroupAndSpecies() {
        StringBuilder groupAndSpecies = new StringBuilder();
        HashSet<String> procedureGroups = new HashSet<String>();
        for(IacucProtocolStudyGroup iacucProtocolStudyGroup : getIacucProtocolStudyGroups()) {
            procedureGroups.add(iacucProtocolStudyGroup.getIacucProtocolSpecies().getSpeciesGroup());
        }
        groupAndSpecies.append(procedureGroups.toString());
        groupAndSpecies.append(" : ");
        groupAndSpecies.append(getIacucSpecies().getSpeciesName());
        return groupAndSpecies.toString();
    }

    public IacucProtocolStudyGroup getIacucProtocolStudyGroup() {
        return iacucProtocolStudyGroup;
    }

    public void setIacucProtocolStudyGroup(IacucProtocolStudyGroup iacucProtocolStudyGroup) {
        this.iacucProtocolStudyGroup = iacucProtocolStudyGroup;
    }

    public List<IacucProtocolStudyGroup> getIacucProtocolStudyGroups() {
        return iacucProtocolStudyGroups;
    }

    public void setIacucProtocolStudyGroups(List<IacucProtocolStudyGroup> iacucProtocolStudyGroups) {
        this.iacucProtocolStudyGroups = iacucProtocolStudyGroups;
    }

    public List<IacucProtocolStudyGroupBean> getResponsibleProcedures() {
        return responsibleProcedures;
    }

    public void setResponsibleProcedures(List<IacucProtocolStudyGroupBean> responsibleProcedures) {
        this.responsibleProcedures = responsibleProcedures;
    }

    public Integer getTotalSpeciesCount() {
        return totalSpeciesCount;
    }

    public void setTotalSpeciesCount(Integer totalSpeciesCount) {
        this.totalSpeciesCount = totalSpeciesCount;
    }

    public Integer getIacucProtocolSpeciesId() {
        return iacucProtocolSpeciesId;
    }

    public void setIacucProtocolSpeciesId(Integer iacucProtocolSpeciesId) {
        this.iacucProtocolSpeciesId = iacucProtocolSpeciesId;
    }

    public IacucProtocolSpecies getIacucProtocolSpecies() {
        return iacucProtocolSpecies;
    }

    public void setIacucProtocolSpecies(IacucProtocolSpecies iacucProtocolSpecies) {
        this.iacucProtocolSpecies = iacucProtocolSpecies;
    }

    public boolean isAllProceduresSelected() {
        return allProceduresSelected;
    }

    public void setAllProceduresSelected(boolean allProceduresSelected) {
        this.allProceduresSelected = allProceduresSelected;
    }

    public void addSpeciesCount(Integer speciesCount) {
        this.totalSpeciesCount = this.totalSpeciesCount + speciesCount;
    }

}

