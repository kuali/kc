/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
