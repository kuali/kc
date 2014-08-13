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
package org.kuali.coeus.propdev.impl.s2s.question;

import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireUsage;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;

import java.util.List;

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
    List<QuestionnaireUsage> getQuestionnaireUsages(String oppNameSpace, String formName, DevelopmentProposal proposal);
    
    /**
     * Get the AnswerHeaders for a specific proposal's form. 
     * @param proposal  The proposal you are interested in
     * @param oppNameSpace the name space of the opportunity form
     * @param formName the name of the form
     * @return
     */
    List<AnswerHeader> getProposalAnswerHeaderForForm(DevelopmentProposal proposal, String oppNameSpace, String formName);
}
