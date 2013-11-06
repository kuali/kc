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
import java.util.Collection;
import java.util.List;

import org.kuali.kra.iacuc.IacucLocationName;
import org.kuali.kra.iacuc.IacucLocationType;
import org.kuali.kra.protocol.ProtocolAssociateBase;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

public class IacucProtocolStudyGroupLocation extends ProtocolAssociateBase { 
    
    private static final long serialVersionUID = 1L;

    private Integer iacucProtocolStudyGroupLocationId; 
    private Integer studyGroupLocationId; 
    private Integer locationTypeCode; 
    private Integer locationId; 
    private String studyGroupLocationDescription; 
    private String locationRoom; 
    
    private IacucLocationType iacucLocationType; 
    private IacucLocationName iacucLocationName; 
    
    private List<IacucProcedureLocationDetail> procedureDetails;
    
    public IacucProtocolStudyGroupLocation() { 
        setProcedureDetails(new ArrayList<IacucProcedureLocationDetail>());
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

    @Override
    public void resetPersistenceState() {
        this.setIacucProtocolStudyGroupLocationId(null);        
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

    public List<IacucProcedureLocationDetail> getProcedureDetails() {
        return procedureDetails;
    }

    public void setProcedureDetails(List<IacucProcedureLocationDetail> procedureDetails) {
        this.procedureDetails = procedureDetails;
    }

    @Override
    public List<Collection<PersistableBusinessObject>> buildListOfDeletionAwareLists() {      
        List<Collection<PersistableBusinessObject>> deleteAwareList = super.buildListOfDeletionAwareLists();
        deleteAwareList.add((Collection) getProcedureDetails());
        return deleteAwareList;
    }
}
