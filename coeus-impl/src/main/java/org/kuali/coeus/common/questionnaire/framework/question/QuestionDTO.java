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
package org.kuali.coeus.common.questionnaire.framework.question;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireQuestion;
import org.kuali.coeus.common.questionnaire.framework.answer.Answer;

public class QuestionDTO implements Serializable {

    private static final long serialVersionUID = 1493906565877782493L;
    
    private List<Answer> answers;
    private QuestionnaireQuestion questionnaireQuestion;
    private boolean isChildMatched;
    private boolean isRuleMatched;
    private List<Answer> parentAnswers;
    
    public QuestionDTO(QuestionnaireQuestion questionnaireQuestion) {
        this.questionnaireQuestion = questionnaireQuestion;
        this.answers = new ArrayList<Answer>();
        this.parentAnswers = new ArrayList<Answer>();
    }

    public List<Answer> getAnswers() {
        return answers;
    }
    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
    public QuestionnaireQuestion getQuestionnaireQuestion() {
        return questionnaireQuestion;
    }
    public void setQuestionnaireQuestion(QuestionnaireQuestion questionnaireQuestion) {
        this.questionnaireQuestion = questionnaireQuestion;
    }

    public boolean isChildMatched() {
        return isChildMatched;
    }

    public void setChildMatched(boolean isChildMatched) {
        this.isChildMatched = isChildMatched;
    }

    public boolean isRuleMatched() {
        return isRuleMatched;
    }

    public void setRuleMatched(boolean isRuleMatched) {
        this.isRuleMatched = isRuleMatched;
    }

    public List<Answer> getParentAnswers() {
        return parentAnswers;
    }

    public void setParentAnswers(List<Answer> parentAnswers) {
        this.parentAnswers = parentAnswers;
    }
    
    
}
