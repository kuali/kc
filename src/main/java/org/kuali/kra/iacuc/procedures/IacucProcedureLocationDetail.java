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

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class IacucProcedureLocationDetail extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 1L;

    private Integer iacucProcedureLocationDetailId; 
    private Integer iacucProtocolStudyGroupLocationId; 
    private Integer iacucProtocolStudyGroupSpeciesId; 
    private IacucProtocolStudyGroupSpecies iacucProtocolStudyGroupSpecies; 
    
    private IacucProtocolStudyGroupLocation iacucProtocolStudyGroupLocation;
    private List<IacucProtocolLocationProcedure> responsibleProcedures;
    
    public IacucProcedureLocationDetail() {
        setResponsibleProcedures(new ArrayList<IacucProtocolLocationProcedure>());
    } 
    
    public void resetPersistenceState() {
        this.setIacucProcedureLocationDetailId(null);        
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
        IacucProcedureLocationDetail other = (IacucProcedureLocationDetail) obj;
        if (this.iacucProtocolStudyGroupLocationId == null) {
            if (other.iacucProtocolStudyGroupLocationId != null) {
                return false;
            }
        } else if (!this.iacucProtocolStudyGroupLocationId.equals(other.iacucProtocolStudyGroupLocationId)) {
            return false;
        }
        if (this.iacucProtocolStudyGroupSpeciesId == null) {
            if (other.iacucProtocolStudyGroupSpeciesId != null) {
                return false;
            }
        } else if (!this.iacucProtocolStudyGroupSpeciesId.equals(other.iacucProtocolStudyGroupSpeciesId)) {
            return false;
        }
        return true;
    }

    public Integer getIacucProcedureLocationDetailId() {
        return iacucProcedureLocationDetailId;
    }


    public void setIacucProcedureLocationDetailId(Integer iacucProcedureLocationDetailId) {
        this.iacucProcedureLocationDetailId = iacucProcedureLocationDetailId;
    }


    public Integer getIacucProtocolStudyGroupLocationId() {
        return iacucProtocolStudyGroupLocationId;
    }


    public void setIacucProtocolStudyGroupLocationId(Integer iacucProtocolStudyGroupLocationId) {
        this.iacucProtocolStudyGroupLocationId = iacucProtocolStudyGroupLocationId;
    }


    public IacucProtocolStudyGroupLocation getIacucProtocolStudyGroupLocation() {
        if(iacucProtocolStudyGroupLocation == null) {
            refreshReferenceObject("iacucProtocolStudyGroupLocation");
        }
        return iacucProtocolStudyGroupLocation;
    }


    public void setIacucProtocolStudyGroupLocation(IacucProtocolStudyGroupLocation iacucProtocolStudyGroupLocation) {
        this.iacucProtocolStudyGroupLocation = iacucProtocolStudyGroupLocation;
    }

    public List<IacucProtocolLocationProcedure> getResponsibleProcedures() {
        return responsibleProcedures;
    }

    public void setResponsibleProcedures(List<IacucProtocolLocationProcedure> responsibleProcedures) {
        this.responsibleProcedures = responsibleProcedures;
    }

    public Integer getIacucProtocolStudyGroupSpeciesId() {
        return iacucProtocolStudyGroupSpeciesId;
    }

    public void setIacucProtocolStudyGroupSpeciesId(Integer iacucProtocolStudyGroupSpeciesId) {
        this.iacucProtocolStudyGroupSpeciesId = iacucProtocolStudyGroupSpeciesId;
    }

    public IacucProtocolStudyGroupSpecies getIacucProtocolStudyGroupSpecies() {
        if (iacucProtocolStudyGroupSpecies == null) {
            refreshReferenceObject("iacucProtocolStudyGroupSpecies");
        }
        return iacucProtocolStudyGroupSpecies;
    }

    public void setIacucProtocolStudyGroupSpecies(IacucProtocolStudyGroupSpecies iacucProtocolStudyGroupSpecies) {
        this.iacucProtocolStudyGroupSpecies = iacucProtocolStudyGroupSpecies;
    }

}
