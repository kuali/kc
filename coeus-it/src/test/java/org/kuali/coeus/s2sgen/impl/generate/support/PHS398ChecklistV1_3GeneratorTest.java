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
package org.kuali.coeus.s2sgen.impl.generate.support;

import org.kuali.coeus.common.questionnaire.framework.answer.Answer;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;
import org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.questionnaire.ProposalDevelopmentModuleQuestionnaireBean;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.List;

public class PHS398ChecklistV1_3GeneratorTest extends PHS398ChecklistBaseGeneratorTest {

    @Override
    protected void prepareData(ProposalDevelopmentDocument document) throws Exception {
        super.prepareData(document);

        ModuleQuestionnaireBean moduleQuestionnaireBean = new ProposalDevelopmentModuleQuestionnaireBean(document.getDevelopmentProposal());
        final List<AnswerHeader> answerHeaders = KcServiceLocator.getService(QuestionnaireAnswerService.class).getQuestionnaireAnswer(moduleQuestionnaireBean);

        for(AnswerHeader answerHeader : answerHeaders){
            if(answerHeader!=null){
                List<Answer> answerDetails = answerHeader.getAnswers();
                for(Answer answers : answerDetails){
                    if(Integer.valueOf(121).equals(answers.getQuestion().getQuestionSeqId()) ){
                        answers.setAnswer("Y");
                    }
                }
            }
        }
        KcServiceLocator.getService(BusinessObjectService.class).save(answerHeaders);
    }

    @Override
    protected String getFormGeneratorName() {
        return PHS398ChecklistV1_3Generator.class.getSimpleName();
    }
}
