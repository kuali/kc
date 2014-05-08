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
package org.kuali.kra.krms;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.questionnaire.answer.Answer;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krms.api.engine.TermResolver;

import java.util.*;

public class QuestionResolver implements TermResolver<Object> {
    
    private String outputName;
    private Set<String> prereqs;
    private Set<String> params;
    
    public QuestionResolver(String outputName, Set<String> params) {
        this.outputName = outputName;
        this.prereqs = new HashSet<String>();
        prereqs.add("moduleCode");
        prereqs.add("moduleItemKey");
        if (params == null) {
            this.params = Collections.emptySet(); 
        } else {
            this.params = params;
        }
    }
    
    @Override
    public int getCost() { return 1; }
    
    @Override
    public String getOutput() { return outputName; }
    
    @Override
    public Set<String> getPrerequisites() {
        return this.prereqs;
    }
    
    @Override
    public Set<String> getParameterNames() {
        return params;
    }
    
    @Override
    public String resolve(Map<String, Object> resolvedPrereqs, Map<String, String> parameters) {
        String questionnaireId = parameters.get("Questionnaire ID");
        String questionId = parameters.get("Question ID");
        String moduleCode = (String) resolvedPrereqs.get("moduleCode");
        String moduleItemKey = (String) resolvedPrereqs.get("moduleItemKey");
        List<AnswerHeader> answerHeaders = getQuestionnaireAnswers(moduleCode, moduleItemKey);
        for (AnswerHeader answerHeader : answerHeaders) {
            if (answerHeader.getQuestionnaire().getQuestionnaireId().equals(questionnaireId)) {
                for (Answer answer : answerHeader.getAnswers()) {
                    if (answer.getQuestion().getQuestionId().equals(questionId)) {
                        return answer.getAnswer();
                    }
                }
            }
        }
        return "";
    }
    
    protected List<AnswerHeader> getQuestionnaireAnswers(String moduleCode, String moduleItemKey) {
        BusinessObjectService boService = KraServiceLocator.getService(BusinessObjectService.class);
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("moduleItemCode", moduleCode);
        fieldValues.put("moduleItemKey", moduleItemKey);
        return (List<AnswerHeader>) boService.findMatching(AnswerHeader.class, fieldValues);
    }

}
