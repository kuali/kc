/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.service;

import java.util.List;

public interface ProposalPersonRoleService {
    
    
    
    /**
     * This method returns a List of Proposal Investigators for the given document Id
     *  customAttributeId.
     * @param document Id of the document
     * @return a List of Proposal Investigators for this document
     */
    
    public List<String> getProposalInvestigator(String docId);
     

     
    /**
     * This method returns a List of CO-Investigators for the given document Id
     *  customAttributeId.
     * @param document Id of the document
     * @return a List of CO-Investigators for this document
     */
    public List<String> getProposalCoInvestigators(String docId); 
       
    
    /**
     * This method returns a List of Proposal Investigators and CO-Investigators for the given document Id
     *  customAttributeId.
     * @param document Id of the document
     * @return a List of PI and CO-Is for this document
     */
    public List<String> getProposalInvestigators(String docId);
     

    /**
     * This method returns a List of Proposal KeyPersons for the given document Id
     *  customAttributeId.
     * @param document Id of the document
     * @return a List of Proposal keyPersons for this document
     */
    
    public List<String> getProposalKeyPersons(String docId);
     

    /**
     * This method returns a List of Proposal Persons(PI, CO-I's and Key Persons) for the given document Id
     *  customAttributeId.
     * @param document Id of the document
     * @return a List of Proposal Persons for this document
     */
    public List<String> getProposalPersons(String docId);
     

    

}
