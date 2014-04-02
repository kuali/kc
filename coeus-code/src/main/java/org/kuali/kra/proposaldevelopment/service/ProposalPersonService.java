/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.service;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;

import java.util.List;

public interface ProposalPersonService {
    public String getPersonName(ProposalDevelopmentDocument doc, String userId);

    public List<ProposalPerson> getProposalKeyPersonnel(String proposalNumber);
    

    /**
     * This method is to get division name using the 4th level node on the Unit hierarchy
     * 
     * @param proposalPerson Proposal person.
     * @return divisionName based on the 4th level node on the Unit hierarchy.
     */
    public String getProposalPersonDivisionName(ProposalPerson proposalPerson);
}
