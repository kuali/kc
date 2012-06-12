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
     * This method is to get procedure category and related list of procedures
     * @return
     */
    public HashMap<Integer, List<IacucProcedure>> getProcedureCategoryAndRelatedProcedures();
    
    
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
     * @param procedureDetailBean
     */
    public void addProcedurePersonResponsible(IacucProcedurePersonResponsible newIacucProcedurePersonResponsible, IacucProtocolStudyGroupDetailBean procedureDetailBean);
    
    /**
     * This method is to format procedure study group data, group by categories for display
     * @param studyGroupBeans
     * @return
     */
    public List<IacucProtocolStudyGroupBean> getRevisedStudyGroupBeans(List<IacucProtocolStudyGroupBean> studyGroupBeans);

    /**
     * This method is to remove a selected study group section
     * @param selectedProtocolStudyGroupBean
     * @param selectedProcedureDetailBean
     */
    public void deleteProtocolStudyGroup(IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean, IacucProtocolStudyGroupDetailBean selectedProcedureDetailBean);
    
    
    /**
     * This method is to update original iacuc protocol study group records
     * based on grouped beans
     * @param protocol
     */
    public void updateIacucProtocolStudyGroup(IacucProtocol protocol);

}
