/*
 * Copyright 2007 The Kuali Foundation.
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
package org.kuali.kra.service;

import java.util.List;

import org.kuali.kra.bo.Ynq;
import org.kuali.kra.bo.YnqExplanationType;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.bo.YnqGroupName;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

public interface YnqService {
    /**
     * This method returns list of YNQ Explanation Types.
     * @return List of explanation types.
     */
    public List<YnqExplanationType> getYnqExplanationTypes();

    /**
     * This method returns list of YNQ - filter by question type.
     * @return List of questions.
     */
    public List<Ynq> getYnq(String questionType);

    /**
     * This method returns list of Proposal Persons.
     * @return List of proposal persons.
     */
    public List<ProposalPerson> getProposalPerson();

    /**
     * This method is used to populate questions.
     * 
     */
    public void populateProposalQuestions(List<ProposalYnq> proposalYnqs, List<YnqGroupName> ynqGroupNames);
    
    public ProposalPerson getPersonYNQ(ProposalPerson proposalPerson);

}
