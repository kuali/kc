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

import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.questionnaire.QuestionnaireUsage;
import org.kuali.kra.questionnaire.answer.AnswerHeader;

/**
 * This service is responsible for getting the questionnaires and answer headers
 * for a proposal based on the proposals gg opportunity.
 * 
 */
public interface ProposalDevelopmentS2sQuestionnaireService {
    
    /**
     * Get AnswerHeaders for all of the questionnaires associated with the development proposals opportunity.
     * @param developmentProposal
     * @return
     */
    List<AnswerHeader> getProposalS2sAnswerHeaders(DevelopmentProposal developmentProposal);
    
    /**
     * Get the QuestionnaireUsage records that match a form.
     * @param oppNameSpace the nameSpace of the opportunity form
     * @param formName the name of the form
     * @return
     */
    List<QuestionnaireUsage> getQuestionnaireUsages(String oppNameSpace, String formName);
    
    /**
     * Get the AnswerHeaders for a specific proposal's form. 
     * @param proposal  The proposal you are interested in
     * @param oppNameSpace the name space of the opportunity form
     * @param formName the name of the form
     * @return
     */
    List<AnswerHeader> getProposalAnswerHeaderForForm(DevelopmentProposal proposal, String oppNameSpace, String formName);
}
