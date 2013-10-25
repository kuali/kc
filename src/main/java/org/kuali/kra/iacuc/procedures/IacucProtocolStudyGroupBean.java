/*
 * Copyright 2005-2013 The Kuali Foundation
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
import java.util.List;

import org.kuali.kra.protocol.ProtocolAssociateBase;

public class IacucProtocolStudyGroupBean extends ProtocolAssociateBase {


    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 112662320812476906L;

    private Integer iacucProtocolStudyGroupHeaderId; 
    private Integer procedureCategoryCode; 
    private Integer procedureCode; 

    // these fields are for header display in tag
    private List<String> protocolSpeciesAndGroups;

    private IacucProcedureCategory iacucProcedureCategory;
    private IacucProcedure iacucProcedure;

    private List<IacucProtocolStudyGroup> iacucProtocolStudyGroups;

    // used for summary
    private List<IacucProcedureLocationDetail> iacucProtocolStudyGroupLocations;
    private List<IacucProcedurePersonResponsible> iacucProtocolStudyGroupPersons;
    private List<IacucProtocolStudyCustomData> iacucProtocolStudyCustomDataList;
    
    public IacucProtocolStudyGroupBean() {
        setIacucProtocolStudyGroups(new ArrayList<IacucProtocolStudyGroup>());
        setIacucProtocolStudyGroupPersons(new ArrayList<IacucProcedurePersonResponsible>());
        setIacucProtocolStudyGroupLocations(new ArrayList<IacucProcedureLocationDetail>());
        setIacucProtocolStudyCustomDataList(new ArrayList<IacucProtocolStudyCustomData>());
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

    /**  {@inheritDoc} */
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

    public List<IacucProcedureLocationDetail> getIacucProtocolStudyGroupLocations() {
        return iacucProtocolStudyGroupLocations;
    }

    public void setIacucProtocolStudyGroupLocations(List<IacucProcedureLocationDetail> iacucProtocolStudyGroupLocations) {
        this.iacucProtocolStudyGroupLocations = iacucProtocolStudyGroupLocations;
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

}
