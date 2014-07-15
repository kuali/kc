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
