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

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.iacuc.IacucLocationName;
import org.kuali.kra.iacuc.IacucLocationType;

import java.util.ArrayList;
import java.util.List;

public class IacucProtocolStudyGroupLocation extends KcPersistableBusinessObjectBase {
    
    private static final long serialVersionUID = 1L;

    private Integer iacucProtocolStudyGroupLocationId; 
    private Integer iacucProtocolStudyGroupId; 
    private Integer studyGroupLocationId; 
    private Integer locationTypeCode; 
    private Integer locationId; 
    private String studyGroupLocationDescription; 
    private String locationRoom; 
    
    private IacucLocationType iacucLocationType; 
    private IacucLocationName iacucLocationName; 
    private IacucProtocolStudyGroup iacucProtocolStudyGroup; 
    
    /* 
     * List of protocol studies and related procedures grouped by species
     * This collection is populated during protocol procedure actions
     */
    private List<IacucProtocolSpeciesStudyGroup> procedureDetails;
    private boolean allProceduresSelected;
    
    public IacucProtocolStudyGroupLocation() { 
        setProcedureDetails(new ArrayList<IacucProtocolSpeciesStudyGroup>());
    } 
    
    public Integer getIacucProtocolStudyGroupLocationId() {
        return iacucProtocolStudyGroupLocationId;
    }

    public void setIacucProtocolStudyGroupLocationId(Integer iacucProtocolStudyGroupLocationId) {
        this.iacucProtocolStudyGroupLocationId = iacucProtocolStudyGroupLocationId;
    }

    public Integer getStudyGroupLocationId() {
        return studyGroupLocationId;
    }

    public void setStudyGroupLocationId(Integer studyGroupLocationId) {
        this.studyGroupLocationId = studyGroupLocationId;
    }

    public Integer getLocationTypeCode() {
        return locationTypeCode;
    }

    public void setLocationTypeCode(Integer locationTypeCode) {
        this.locationTypeCode = locationTypeCode;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getStudyGroupLocationDescription() {
        return studyGroupLocationDescription;
    }

    public void setStudyGroupLocationDescription(String studyGroupLocationDescription) {
        this.studyGroupLocationDescription = studyGroupLocationDescription;
    }

    public String getLocationRoom() {
        return locationRoom;
    }

    public void setLocationRoom(String locationRoom) {
        this.locationRoom = locationRoom;
    }

    public IacucLocationType getIacucLocationType() {
        if (iacucLocationType == null) {
            refreshReferenceObject("iacucLocationType");
        }
        return iacucLocationType;
    }

    public void setIacucLocationType(IacucLocationType iacucLocationType) {
        this.iacucLocationType = iacucLocationType;
    }

    public IacucLocationName getIacucLocationName() {
        if (iacucLocationName == null) {
            refreshReferenceObject("iacucLocationName");
        }
        return iacucLocationName;
    }

    public void setIacucLocationName(IacucLocationName iacucLocationName) {
        this.iacucLocationName = iacucLocationName;
    }

    public void resetPersistenceState() {
        this.setIacucProtocolStudyGroupLocationId(null);        
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
        IacucProtocolStudyGroupLocation other = (IacucProtocolStudyGroupLocation) obj;
        if (this.locationId == null) {
            if (other.locationId != null) {
                return false;
            }
        } else if (!this.locationId.equals(other.locationId)) {
            return false;
        }
        if (this.locationTypeCode == null) {
            if (other.locationTypeCode != null) {
                return false;
            }
        } else if (!this.locationTypeCode.equals(other.locationTypeCode)) {
            return false;
        }
        return true;
    }

    public List<IacucProtocolSpeciesStudyGroup> getProcedureDetails() {
        return procedureDetails;
    }

    public void setProcedureDetails(List<IacucProtocolSpeciesStudyGroup> procedureDetails) {
        this.procedureDetails = procedureDetails;
    }

    public IacucProtocolStudyGroup getIacucProtocolStudyGroup() {
        return iacucProtocolStudyGroup;
    }

    public void setIacucProtocolStudyGroup(IacucProtocolStudyGroup iacucProtocolStudyGroup) {
        this.iacucProtocolStudyGroup = iacucProtocolStudyGroup;
    }

    public Integer getIacucProtocolStudyGroupId() {
        return iacucProtocolStudyGroupId;
    }

    public void setIacucProtocolStudyGroupId(Integer iacucProtocolStudyGroupId) {
        this.iacucProtocolStudyGroupId = iacucProtocolStudyGroupId;
    }

    public boolean isAllProceduresSelected() {
        return allProceduresSelected;
    }

    public void setAllProceduresSelected(boolean allProceduresSelected) {
        this.allProceduresSelected = allProceduresSelected;
    }

}
