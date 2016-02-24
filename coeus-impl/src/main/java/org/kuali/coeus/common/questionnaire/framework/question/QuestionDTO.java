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
