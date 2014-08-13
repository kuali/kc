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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kuali.kra.protocol.ProtocolAssociateBase;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

public class IacucProtocolStudyGroupBean extends ProtocolAssociateBase {



    private static final long serialVersionUID = 112662320812476906L;

    private Integer iacucProtocolStudyGroupHeaderId; 
    private Integer procedureCategoryCode; 
    private Integer procedureCode; 

    // these fields are for header display in tag
    private List<String> protocolSpeciesAndGroups;

    private IacucProcedureCategory iacucProcedureCategory;
    private IacucProcedure iacucProcedure;

    private List<IacucProtocolStudyGroup> iacucProtocolStudyGroups;

    // Below list is grouped by species
    private List<IacucProtocolSpeciesStudyGroup> iacucProtocolSpeciesStudyGroups;
    
    // used for summary
    private List<IacucProtocolStudyGroupLocation> iacucProtocolStudyGroupLocations;
    private List<IacucProcedurePersonResponsible> iacucProtocolStudyGroupPersons;
    private List<IacucProtocolStudyCustomData> iacucProtocolStudyCustomDataList;
    private Integer speciesCount = 0;

    private boolean procedureSelected;
    private boolean newProcedure;
    
    public IacucProtocolStudyGroupBean() {
        setIacucProtocolStudyGroups(new ArrayList<IacucProtocolStudyGroup>());
        setIacucProtocolStudyGroupPersons(new ArrayList<IacucProcedurePersonResponsible>());
        setIacucProtocolStudyGroupLocations(new ArrayList<IacucProtocolStudyGroupLocation>());
        setIacucProtocolStudyCustomDataList(new ArrayList<IacucProtocolStudyCustomData>());
        setIacucProtocolSpeciesStudyGroups(new ArrayList<IacucProtocolSpeciesStudyGroup>());
        resetStudyGroupItems();
    }
    
    public void resetStudyGroupItems() {
        initializeStudyGroupItems();
    }
    
    public void initializeStudyGroupItems() {
        setProtocolSpeciesAndGroups(new ArrayList<String>());
    }
    
    public List<String> getProtocolSpeciesAndGroups() {
        return protocolSpeciesAndGroups;
    }
    
    public void setProtocolSpeciesAndGroups(List<String> protocolSpeciesAndGroups) {
        this.protocolSpeciesAndGroups = protocolSpeciesAndGroups;
    }
    
    public Integer getProcedureCategoryCode() {
        return procedureCategoryCode;
    }

    public void setProcedureCategoryCode(Integer procedureCategoryCode) {
        this.procedureCategoryCode = procedureCategoryCode;
    }

    public Integer getProcedureCode() {
        return procedureCode;
    }


    public void setProcedureCode(Integer procedureCode) {
        this.procedureCode = procedureCode;
    }


    public String getProcedureDescription() {
        return getIacucProcedure().getProcedureDescription();
    }

    public String getProcedureCategory() {
        return getIacucProcedureCategory().getProcedureCategory();
    }

    public Integer getIacucProtocolStudyGroupHeaderId() {
        return iacucProtocolStudyGroupHeaderId;
    }

    public void setIacucProtocolStudyGroupHeaderId(Integer iacucProtocolStudyGroupHeaderId) {
        this.iacucProtocolStudyGroupHeaderId = iacucProtocolStudyGroupHeaderId;
    }

    @Override
    public void resetPersistenceState() {
        setIacucProtocolStudyGroupHeaderId(null);
    }

    public IacucProcedureCategory getIacucProcedureCategory() {
        if (iacucProcedureCategory == null) {
            refreshReferenceObject("iacucProcedureCategory");
        }
        return iacucProcedureCategory;
    }

    public void setIacucProcedureCategory(IacucProcedureCategory iacucProcedureCategory) {
        this.iacucProcedureCategory = iacucProcedureCategory;
    }

    public IacucProcedure getIacucProcedure() {
        if (iacucProcedure == null) {
            refreshReferenceObject("iacucProcedure");
        }
        return iacucProcedure;
    }

    public void setIacucProcedure(IacucProcedure iacucProcedure) {
        this.iacucProcedure = iacucProcedure;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((iacucProtocolStudyGroupHeaderId == null) ? 0 : iacucProtocolStudyGroupHeaderId.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        IacucProtocolStudyGroupBean other = (IacucProtocolStudyGroupBean) obj;
        if (this.iacucProtocolStudyGroupHeaderId == null) {
            if (other.iacucProtocolStudyGroupHeaderId != null) {
                return false;
            }
        } else if (!this.iacucProtocolStudyGroupHeaderId.equals(other.iacucProtocolStudyGroupHeaderId)) {
            return false;
        }
        if (this.procedureCode == null) {
            if (other.procedureCode != null) {
                return false;
            }
        } else if (!this.procedureCode.equals(other.procedureCode)) {
            return false;
        }
        if (this.procedureCategoryCode == null) {
            if (other.procedureCategoryCode != null) {
                return false;
            }
        } else if (!this.procedureCategoryCode.equals(other.procedureCategoryCode)) {
            return false;
        }
        return true;
    }

    public List<IacucProtocolStudyGroup> getIacucProtocolStudyGroups() {
        return iacucProtocolStudyGroups;
    }

    public void setIacucProtocolStudyGroups(List<IacucProtocolStudyGroup> iacucProtocolStudyGroups) {
        this.iacucProtocolStudyGroups = iacucProtocolStudyGroups;
    }

    public List<IacucProcedurePersonResponsible> getIacucProtocolStudyGroupPersons() {
        return iacucProtocolStudyGroupPersons;
    }

    public void setIacucProtocolStudyGroupPersons(List<IacucProcedurePersonResponsible> iacucProtocolStudyGroupPersons) {
        this.iacucProtocolStudyGroupPersons = iacucProtocolStudyGroupPersons;
    }

    public List<IacucProtocolStudyCustomData> getIacucProtocolStudyCustomDataList() {
        return iacucProtocolStudyCustomDataList;
    }

    public void setIacucProtocolStudyCustomDataList(List<IacucProtocolStudyCustomData> iacucProtocolStudyCustomDataList) {
        this.iacucProtocolStudyCustomDataList = iacucProtocolStudyCustomDataList;
    }

    public Integer getSpeciesCount() {
        return speciesCount;
    }

    public void setSpeciesCount(Integer speciesCount) {
        this.speciesCount = speciesCount;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Collection<PersistableBusinessObject>> buildListOfDeletionAwareLists() {      
        List<Collection<PersistableBusinessObject>> deleteAwareList = super.buildListOfDeletionAwareLists();
        deleteAwareList.add((Collection) getIacucProtocolStudyGroups());
        return deleteAwareList;
    }

    public List<IacucProtocolSpeciesStudyGroup> getIacucProtocolSpeciesStudyGroups() {
        return iacucProtocolSpeciesStudyGroups;
    }

    public void setIacucProtocolSpeciesStudyGroups(List<IacucProtocolSpeciesStudyGroup> iacucProtocolSpeciesStudyGroups) {
        this.iacucProtocolSpeciesStudyGroups = iacucProtocolSpeciesStudyGroups;
    }

    public boolean isProcedureSelected() {
        return procedureSelected;
    }

    public void setProcedureSelected(boolean procedureSelected) {
        this.procedureSelected = procedureSelected;
    }

    public boolean isNewProcedure() {
        return newProcedure;
    }

    public void setNewProcedure(boolean newProcedure) {
        this.newProcedure = newProcedure;
    }

    public List<IacucProtocolStudyGroupLocation> getIacucProtocolStudyGroupLocations() {
        return iacucProtocolStudyGroupLocations;
    }

    public void setIacucProtocolStudyGroupLocations(List<IacucProtocolStudyGroupLocation> iacucProtocolStudyGroupLocations) {
        this.iacucProtocolStudyGroupLocations = iacucProtocolStudyGroupLocations;
    }
    
}
