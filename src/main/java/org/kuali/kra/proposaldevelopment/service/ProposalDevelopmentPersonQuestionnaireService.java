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
package org.kuali.kra.proposaldevelopment.service;

import java.util.List;

import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.questionnaire.ProposalPersonModuleQuestionnaireBean;
import org.kuali.kra.questionnaire.Questionnaire;
import org.kuali.kra.questionnaire.answer.AnswerHeader;

/**
 * 
 * This class...
 */
public interface ProposalDevelopmentPersonQuestionnaireService {
    
    /**
     * 
     * This method returns a list of AnswerHeader object, if the person already has a collection in the DB, it returns that.
     * Otherwise a new list is returned.
     * @param proposalPerson
     * @return
     */
    List<AnswerHeader> getAnswerHeaders(ProposalPerson proposalPerson);

}
