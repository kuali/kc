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

import java.util.List;

import org.kuali.kra.iacuc.IacucPersonTraining;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.personnel.IacucProtocolPerson;
import org.kuali.kra.iacuc.species.IacucProtocolSpecies;



public interface IacucProtocolProcedureService {

    /**
     * This method is to fetch all procedures which is sorted by category code
     * @return
     */
    public List<IacucProcedure> getAllProcedures();
    
    /**
     * This method is to fetch all procedure categories
     * @return
     */
    public List<IacucProcedureCategory> getAllProcedureCategories();
    
    /**
     * This method is to fetch all protocol species
     * @return
     */
    public List<IacucProtocolSpecies> getProtocolSpecies();
    
    /**
     * This method is to add protocol study group
     * @param selectedProtocolStudyGroupBean
     * @param iacucProtocol
     */
    public void addProtocolStudyGroup(IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean, IacucProtocol iacucProtocol);
    
    /**
     * This method is to format procedure study group data, group by categories for display
     * @param iacucProtocol
     * @param allProcedures
     * @return
     */
    public List<IacucProtocolStudyGroupBean> getRevisedStudyGroupBeans(IacucProtocol iacucProtocol, List<IacucProcedure> allProcedures);

    /**
     * This method is to remove a selected protocol study group
     * Update the study group species usage count
     * @param selectedProtocolStudyGroupBean
     * @param deletedIacucProtocolStudyGroup
     * @param iacucProtocol
     */
    public void deleteProtocolStudyGroup(IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean, 
            IacucProtocolStudyGroup deletedIacucProtocolStudyGroup, IacucProtocol iacucProtocol);
    
    /**
     * This method is to remove selected procedure that are grouped by species
     * @param selectedProtocolStudyGroupBean
     * @param deletedIacucProtocolStudyGroup
     */
    public void deleteProtocolStudyGroup(IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean, IacucProtocolSpeciesStudyGroup deletedIacucProtocolStudyGroup);
    
    /**
     * This method is to add a new location to study group
     * @param newStudyGroupLocation
     * @param protocol
     */
    public void addProcedureLocation(IacucProtocolStudyGroupLocation newStudyGroupLocation, IacucProtocol protocol);
    
    /**
     * This method is to set procedure summary tab details
     * @param protocol
     */
    public void setProcedureSummaryGroupedBySpecies(IacucProtocol protocol);

    /**
     * This method is to set training details for all protocol persons 
     * @param protocol
     */
    public void setTrainingDetails(IacucProtocol protocol);
    
    /**
     * This method is to add responsible procedures for each protocol person
     * @param protocol
     */
    public void addPersonResponsibleProcedures(IacucProtocol protocol);
    
    /**
     * This method is to set procedure summary tab details
     * group studies by protocol species group
     * @param protocol
     */
    public void setProcedureSummaryBySpeciesGroup(IacucProtocol protocol);
    
    /**
     * This method is to fetch iacuc person training details for a person
     * @param personId
     * @return
     */
    public List<IacucPersonTraining> getIacucPersonTrainingDetails(String personId);
    

    /**
     * This method is to create a new set of study groups based on existing list
     * This method is invoked during copy protocol so that we a new instance of the study group is created
     * and all its corresponding collections are set with appropriate reference.
     * @param sourceProtocol
     * @param destProtocol
     */
    public void createNewProtocolStudyProcedures(IacucProtocol sourceProtocol, IacucProtocol destProtocol); 

    /**
     * This method is to update study procedures during protocol version
     * We need to reset those that are not a direct reference to protocol associate and
     * map appropriate references for procedures
     * @param iacucProtocol
     */
    public void resetAllProtocolStudyProcedures(IacucProtocol iacucProtocol);
    
    /**
     * This method is to merge protocol species during protocol amend/renew
     * @param sourceProtocol
     * @param destProtocol
     */
    public void mergeProtocolSpecies(IacucProtocol sourceProtocol, IacucProtocol destProtocol);
    
    /**
     * This method is to merge protocol procedures during protocol amend/renew
     * This includes all functions related to the procedure panel
     * @param sourceProtocol
     * @param destProtocol
     */
    public void mergeProtocolProcedures(IacucProtocol sourceProtocol, IacucProtocol destProtocol);
    
    /**
     * This method is to merge protocol procedure personnel during protocol amend/renew
     * @param destProtocol
     */
    public void mergeProtocolProcedurePersonnel(IacucProtocol destProtocol);

    
    /**
     * This method is to synchronize study details that are grouped based on species
     * @param iacucProtocol
     */
    public void synchronizeProtocolStudyGroups(IacucProtocol iacucProtocol);
    
    /**
     * This method is to rearrange person procedures and group them by species
     * @param iacucProtocol
     */
    public void populateIacucSpeciesPersonProcedures(IacucProtocol iacucProtocol);
    
    /**
     * This method is to rearrange location procedures and group them by species
     * @param iacucProtocol
     */
    public void populateIacucSpeciesLocationProcedures(IacucProtocol iacucProtocol);

    /**
     * This method is to assign procedures to each location
     * @param protocol
     * @param iacucProtocolStudyGroupLocation
     */
    public void addLocationResponsibleProcedures(IacucProtocol protocol);    

    /**
     * This method is to identify whether procedure is viewed by species or group.
     * If not by species, then procedure panel is displayed by group
     * Identified through system parameter configuration.
     * @return
     */
    public boolean isProcedureViewedBySpecies();
    
    /**
     * This method is to remove location from study group details
     * @param deletedIacucProtocolStudyGroupLocation
     * @param iacucProtocol
     */
    public void deleteProcedureLocation(IacucProtocolStudyGroupLocation deletedIacucProtocolStudyGroupLocation, IacucProtocol iacucProtocol);
    
    /**
     * This method is to remove selected person from procedure study group
     * @param selectedProtocolStudyGroup
     * @param deletedProcedurePersonResponsible
     * @param iacucProtocol
     */
    public void deleteProcedureGroupPersonResponsible(IacucProtocolStudyGroup selectedProtocolStudyGroup, IacucProcedurePersonResponsible deletedProcedurePersonResponsible, 
            IacucProtocol iacucProtocol);
    
    /**
     * This method is to remove selected location from procedure study group
     * @param selectedProtocolStudyGroup
     * @param deletedProtocolStudyGroupLocation
     * @param iacucProtocol
     */
    public void deleteProcedureGroupLocation(IacucProtocolStudyGroup selectedProtocolStudyGroup, IacucProtocolStudyGroupLocation deletedProtocolStudyGroupLocation, 
            IacucProtocol iacucProtocol);
    

    /**
     * This method is to add a new location to a specific study group
     * @param newStudyGroupLocation
     * @param selectedStudyGroup
     * @param protocol
     */
    public void addProcedureGroupLocation(IacucProtocolStudyGroupLocation newStudyGroupLocation, IacucProtocolStudyGroup selectedStudyGroup, IacucProtocol protocol);

}
