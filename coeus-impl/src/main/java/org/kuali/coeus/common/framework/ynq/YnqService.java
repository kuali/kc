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
package org.kuali.coeus.common.framework.ynq;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.ynq.ProposalYnq;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;

import java.util.List;

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
     * This method is used to populate questions.
     * 
     */
    public void populateProposalQuestions(List<ProposalYnq> proposalYnqs, List<YnqGroupName> ynqGroupNames, ProposalDevelopmentDocument document);
    
    public ProposalPerson getPersonYNQ(ProposalPerson proposalPerson, ProposalDevelopmentDocument document);

}
