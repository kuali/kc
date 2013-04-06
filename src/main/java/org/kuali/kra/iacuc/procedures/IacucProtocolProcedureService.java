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

import java.util.HashMap;
import java.util.List;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.species.IacucProtocolSpecies;


/**
 * This class...
 */
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
     * This method is to add procedure responsible person.
     * This method is invoked from person responsible section
     * @param newIacucProcedurePersonResponsible
     * @param selectedProcedureDetailBean
     * @param selectedProtocolStudyGroupBean
     */
    public void addProcedurePersonResponsible(IacucProcedurePersonResponsible newIacucProcedurePersonResponsible, 
            IacucProtocolStudyGroupDetailBean selectedProcedureDetailBean, IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean);
    
    /**
     * This method is to format procedure study group data, group by categories for display
     * @param iacucProtocol
     * @param allProcedures
     * @return
     */
    public List<IacucProtocolStudyGroupBean> getRevisedStudyGroupBeans(IacucProtocol iacucProtocol, List<IacucProcedure> allProcedures);

    /**
     * This method is to remove a selected study group section
     * @param selectedProtocolStudyGroupBean
     * @param selectedProcedureDetailBean
     * @param iacucProtocol
     */
    public void deleteProtocolStudyGroup(IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean, 
            IacucProtocolStudyGroupDetailBean selectedProcedureDetailBean, IacucProtocol iacucProtocol);
    
    /**
     * This method is to deleted selected person responsible
     * @param selectedProcedureDetailBean
     * @param selectedPersonResponsible
     * @param iacucProtocol
     */
    public void deleteProcedurePersonResponsible(IacucProtocolStudyGroupDetailBean selectedProcedureDetailBean, 
            IacucProcedurePersonResponsible selectedPersonResponsible, IacucProtocol iacucProtocol);
    
    /**
     * This method is to deleted selected group location
     * @param selectedProcedureDetailBean
     * @param selectedStudyGroupLocation
     * @param iacucProtocol
     */
    public void deleteStudyGroupLocation(IacucProtocolStudyGroupDetailBean selectedProcedureDetailBean, 
            IacucProtocolStudyGroupLocation selectedStudyGroupLocation, IacucProtocol iacucProtocol);
    
    /**
     * This method is to update original iacuc protocol study group records
     * based on grouped beans
     * @param protocol
     */
    public void updateIacucProtocolStudyGroup(IacucProtocol protocol);
    
    /**
     * This method is to set references for protocol study group
     * @param protocol
     */
    public void setIacucProtocolStudyGroupReferences(IacucProtocol protocol);
    
    
    /**
     * This method is to add location to study group
     * Since it is grouped, we have to add the same location to list of locations
     * under each study group linked to the study group detail bean (this is where it is grouped)
     * @param newIacucProtocolStudyGroupLocation
     * @param selectedProcedureDetailBean
     * @param protocol
     */
    public void addProcedureLocation(IacucProtocolStudyGroupLocation newIacucProtocolStudyGroupLocation, IacucProtocolStudyGroupDetailBean selectedProcedureDetailBean, 
            IacucProtocol protocol);

    /**
     * This method is to create a new set of study groups based on existing list
     * This method is invoked during copy protocol so that we a new instance of the study group is created
     * and all its corresponding collections are set with appropriate reference.
     * @param iacucProtocol
     * @param sourceStudyGroupBeans
     * @param protocolSpeciesIdMapping
     */
    public void createNewStudyGroups(IacucProtocol iacucProtocol, List<IacucProtocolStudyGroupBean> sourceStudyGroupBeans, 
            HashMap<Integer, Integer> protocolSpeciesIdMapping);

    
    /**
     * This method is to reset the persistent state in procedure panel.
     * There are issues when extended from protocol associate since there is no relation with protocol for
     * some of the sub panels used in procedure. Since these sub panels are extended from persistable business object
     * we are forcing the reset here.
     * @param protocol
     */
    public void resetProcedurePanel(IacucProtocol protocol);
    
}
