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

