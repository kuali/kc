/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
