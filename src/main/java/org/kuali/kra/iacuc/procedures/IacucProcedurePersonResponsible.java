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

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.bo.PersonTraining;
import org.kuali.kra.iacuc.IacucPersonTraining;
import org.kuali.kra.protocol.ProtocolAssociate;

public class IacucProcedurePersonResponsible extends ProtocolAssociate { 
    
    private static final long serialVersionUID = 1L;

    private Integer iacucProcedurePersonResponsibleId; 
    private Integer iacucProtocolStudyGroupId; 
    private String personId;
    private String personName;
    private String personResponsibleDescription;
    private List<String> trainingDetails;
    
    private List<String> protocolPersonsResponsible;
    
    private IacucProtocolStudyGroup iacucProtocolStudyGroup; 
    
    private List<IacucPersonTraining> trainings;

    public IacucProcedurePersonResponsible() { 
        setProtocolPersonsResponsible(new ArrayList<String>());

    } 
    
    public Integer getIacucProcedurePersonResponsibleId() {
        return iacucProcedurePersonResponsibleId;
    }

    public void setIacucProcedurePersonResponsibleId(Integer iacucProcedurePersonResponsibleId) {
        this.iacucProcedurePersonResponsibleId = iacucProcedurePersonResponsibleId;
    }

    public Integer getIacucProtocolStudyGroupId() {
        return iacucProtocolStudyGroupId;
    }

    public void setIacucProtocolStudyGroupId(Integer iacucProtocolStudyGroupId) {
        this.iacucProtocolStudyGroupId = iacucProtocolStudyGroupId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public IacucProtocolStudyGroup getIacucProtocolStudyGroup() {
        return iacucProtocolStudyGroup;
    }

    public void setIacucProtocolStudyGroup(IacucProtocolStudyGroup iacucProtocolStudyGroup) {
        this.iacucProtocolStudyGroup = iacucProtocolStudyGroup;
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
        if (personResponsibleDescription == null) {
            refreshReferenceObject("personResponsibleDescription");
        }
        return personResponsibleDescription;
    }

    public void setPersonResponsibleDescription(String personResponsibleDescription) {
        this.personResponsibleDescription = personResponsibleDescription;
    }

    public List<String> getProtocolPersonsResponsible() {
        return protocolPersonsResponsible;
    }

    public void setProtocolPersonsResponsible(List<String> protocolPersonsResponsible) {
        this.protocolPersonsResponsible = protocolPersonsResponsible;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
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
        if (this.iacucProtocolStudyGroupId == null) {
            if (other.iacucProtocolStudyGroupId != null) {
                return false;
            }
        } else if (!this.iacucProtocolStudyGroupId.equals(other.iacucProtocolStudyGroupId)) {
            return false;
        }
        if (this.personId == null) {
            if (other.personId != null) {
                return false;
            }
        } else if (!this.personId.equals(other.personId)) {
            return false;
        }
        return true;
    }

}