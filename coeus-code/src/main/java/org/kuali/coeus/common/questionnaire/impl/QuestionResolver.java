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
package org.kuali.coeus.common.questionnaire.impl;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.questionnaire.framework.answer.Answer;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krms.api.engine.TermResolver;

import java.util.*;

public class QuestionResolver implements TermResolver<Object> {

    public static final String MODULE_CODE = "moduleCode";
    public static final String MODULE_ITEM_KEY = "moduleItemKey";
    public static final String QUESTIONNAIRE_REF_ID = "Questionnaire Ref ID";
    public static final String QUESTION_SEQ_ID = "Question Seq ID";
    public static final String MODULE_ITEM_CODE = "moduleItemCode";
    private String outputName;
    private Set<String> prereqs;
    private Set<String> params;
    
    public QuestionResolver(String outputName, Set<String> params) {
        this.outputName = outputName;
        this.prereqs = new HashSet<String>();
        prereqs.add(MODULE_CODE);
        prereqs.add(MODULE_ITEM_KEY);
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
        String questionnaireId = parameters.get(QUESTIONNAIRE_REF_ID);
        String questionId = parameters.get(QUESTION_SEQ_ID);
        String moduleCode = (String) resolvedPrereqs.get(MODULE_CODE);
        String moduleItemKey = (String) resolvedPrereqs.get(MODULE_ITEM_KEY);
        List<AnswerHeader> answerHeaders = getQuestionnaireAnswers(moduleCode, moduleItemKey);
        for (AnswerHeader answerHeader : answerHeaders) {
            if (answerHeader.getQuestionnaireId().equals(questionnaireId)) {
                for (Answer answer : answerHeader.getAnswers()) {
                    if (answer.getQuestion().getQuestionSeqId().equals(questionId)) {
                        return answer.getAnswer();
                    }
                }
            }
        }
        return "";
    }
    
    protected List<AnswerHeader> getQuestionnaireAnswers(String moduleCode, String moduleItemKey) {
        BusinessObjectService boService = KcServiceLocator.getService(BusinessObjectService.class);
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(MODULE_ITEM_CODE, moduleCode);
        fieldValues.put(MODULE_ITEM_KEY, moduleItemKey);
        return (List<AnswerHeader>) boService.findMatching(AnswerHeader.class, fieldValues);
    }

}
