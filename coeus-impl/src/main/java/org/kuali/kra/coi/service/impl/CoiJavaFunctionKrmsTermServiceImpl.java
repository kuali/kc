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
package org.kuali.kra.coi.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.personfinancialentity.FinancialEntityService;
import org.kuali.kra.coi.personfinancialentity.PersonFinIntDisclosure;
import org.kuali.kra.coi.questionnaire.DisclosureModuleQuestionnaireBean;
import org.kuali.kra.coi.service.CoiJavaFunctionKrmsTermService;
import org.kuali.coeus.common.impl.krms.KcKrmsJavaFunctionTermServiceBase;
import org.kuali.coeus.common.questionnaire.framework.answer.Answer;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService;

import java.util.List;

public class CoiJavaFunctionKrmsTermServiceImpl extends KcKrmsJavaFunctionTermServiceBase implements CoiJavaFunctionKrmsTermService {

    private QuestionnaireAnswerService questionnaireAnswerService;
    private FinancialEntityService financialEntityService;

    @Override
    public Integer getScreeningQuestionYesAnswerCount(CoiDisclosure coiDisclosure) {
        List<AnswerHeader> answerHeaders = getQuestionnaireAnswerService().getQuestionnaireAnswer(new DisclosureModuleQuestionnaireBean(coiDisclosure, CoeusSubModule.COI_SCREENING_SUBMODULE));
        int count = 0;
        for (AnswerHeader answerHeader : answerHeaders) {
            for (Answer answer : answerHeader.getAnswers()) {
                if (StringUtils.equalsIgnoreCase(answer.getAnswer(), "Y")) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public Integer getReporterActiveFinancialEntityCount(CoiDisclosure coiDisclosure) {
        String personId = coiDisclosure.getPersonId();
        List<PersonFinIntDisclosure> finEnts = getFinancialEntityService().getFinancialEntities(personId, true);
        return finEnts != null ? finEnts.size() : 0;
    }
    
    public QuestionnaireAnswerService getQuestionnaireAnswerService() {
        return questionnaireAnswerService;
    }

    public void setQuestionnaireAnswerService(QuestionnaireAnswerService questionnaireAnswerService) {
        this.questionnaireAnswerService = questionnaireAnswerService;
    }

    public FinancialEntityService getFinancialEntityService() {
        return financialEntityService;
    }

    public void setFinancialEntityService(FinancialEntityService financialEntityService) {
        this.financialEntityService = financialEntityService;
    }
}
