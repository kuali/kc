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

import org.kuali.kra.iacuc.IacucPersonTraining;
import org.kuali.kra.iacuc.personnel.IacucProtocolPerson;
import org.kuali.kra.protocol.ProtocolAssociateBase;

public class IacucProcedurePersonResponsible extends ProtocolAssociateBase { 
    
    private static final long serialVersionUID = 1L;

    private Integer iacucProcedurePersonResponsibleId; 
    private Integer iacucProtocolStudyGroupSpeciesId; 
    private Integer protocolPersonId;
    
    private IacucProtocolPerson protocolPerson;
    private List<String> trainingDetails;
    
    private IacucProtocolStudyGroupSpecies iacucProtocolStudyGroupSpecies; 
    
    private List<IacucPersonTraining> trainings;
    private List<IacucPersonProcedureDetail> responsibleProcedures;

    public IacucProcedurePersonResponsible() { 
        setResponsibleProcedures(new ArrayList<IacucPersonProcedureDetail>());
    } 
    
    public Integer getIacucProcedurePersonResponsibleId() {
        return iacucProcedurePersonResponsibleId;
    }

    public void setIacucProcedurePersonResponsibleId(Integer iacucProcedurePersonResponsibleId) {
        this.iacucProcedurePersonResponsibleId = iacucProcedurePersonResponsibleId;
    }

    public String getPersonId() {
        return getProtocolPerson().getPersonId();
    }

    @Override
    public void resetPersistenceState() {
        this.setIacucProcedurePersonResponsibleId(null);        
    }

    public List<IacucPersonTraining> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<IacucPersonTraining> trainings) {
        this.trainings = trainings;
    }
    
    public String getTrainingDetailsString() {
        String details = new String();
        boolean first = true;
        for(String detail: getTrainingDetails()) {
            if (!first) {
                details += "<br/>";
            }
            details += detail;
            first = false;
        }
        return details;
    }
    
    public List<String> getTrainingDetails() {
        this.trainingDetails = new ArrayList<String>();
        if (getTrainings() != null) {
            for(IacucPersonTraining iacucPersonTraining : getTrainings()) {
                StringBuffer trainingInfo = new StringBuffer();
                trainingInfo.append("Training : " + iacucPersonTraining.getPersonTraining().getTraining().getDescription());
                trainingInfo.append("\r\nSpecies : " + iacucPersonTraining.getIacucSpecies().getSpeciesName());
                trainingInfo.append("\r\nProcedure : " + iacucPersonTraining.getIacucProcedure().getProcedureDescription());
                trainingInfo.append("\r\n");
                this.trainingDetails.add(trainingInfo.toString());
            }
        }
        return trainingDetails;
    }

    public String getPersonResponsibleDescription() {
        return getProtocolPerson().getProcedureQualificationDescription();
    }

    public String getPersonName() {
        return getProtocolPerson().getPersonName();
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
        IacucProcedurePersonResponsible other = (IacucProcedurePersonResponsible) obj;
        if (this.iacucProtocolStudyGroupSpeciesId == null) {
            if (other.iacucProtocolStudyGroupSpeciesId != null) {
                return false;
            }
        } else if (!this.iacucProtocolStudyGroupSpeciesId.equals(other.iacucProtocolStudyGroupSpeciesId)) {
            return false;
        }
        if (this.protocolPersonId == null) {
            if (other.protocolPersonId != null) {
                return false;
            }
        } else if (!this.protocolPersonId.equals(other.protocolPersonId)) {
            return false;
        }
        return true;
    }

    public Integer getProtocolPersonId() {
        return protocolPersonId;
    }

    public void setProtocolPersonId(Integer protocolPersonId) {
        this.protocolPersonId = protocolPersonId;
    }

    public IacucProtocolPerson getProtocolPerson() {
        if (this.protocolPerson == null) {
            refreshReferenceObject("protocolPerson");
        }
        return protocolPerson;
    }

    public void setProtocolPerson(IacucProtocolPerson protocolPerson) {
        this.protocolPerson = protocolPerson;
    }

    public List<IacucPersonProcedureDetail> getResponsibleProcedures() {
        return responsibleProcedures;
    }

    public void setResponsibleProcedures(List<IacucPersonProcedureDetail> responsibleProcedures) {
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
