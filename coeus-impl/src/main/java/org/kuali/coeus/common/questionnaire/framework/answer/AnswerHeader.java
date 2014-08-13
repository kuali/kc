/*
 * Copyright 2005-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.common.questionnaire.framework.answer;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.common.questionnaire.framework.core.Questionnaire;
import org.kuali.coeus.common.questionnaire.framework.question.QuestionDTO;
import org.kuali.coeus.common.questionnaire.api.answer.AnswerHeaderContract;
import org.kuali.rice.core.api.mo.common.active.Inactivatable;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds additional information related to a series of {@link org.kuali.coeus.common.questionnaire.framework.answer.Answer Answers}.
 */
public class AnswerHeader extends KcPersistableBusinessObjectBase implements Inactivatable, AnswerHeaderContract {

    private static final long serialVersionUID = 1L;

    private static final String NOT_SHOW_ANSWER = "N";

    private Long id;

    private String moduleItemCode;

    private String moduleItemKey;

    private String moduleSubItemCode;

    private String moduleSubItemKey;

    private Long questionnaireId;

    private boolean completed = false;

    private Questionnaire questionnaire;

    private List<Answer> answers;
    
    private List<QuestionDTO> questions;

    // Transient properties for questionnaire answer   
    private boolean newerVersionPublished = false;

    private String updateOption;

    private String showQuestions;
    
    private boolean hasVisibleQuestion = true;

    private boolean notUpdated = false;

    private boolean activeQuestionnaire = true;
    
    private String label;

    public AnswerHeader() {
        super();
        showQuestions = NOT_SHOW_ANSWER;
    }

    public AnswerHeader(ModuleQuestionnaireBean moduleQuestionnaireBean, Long questionnaireId) {
        this.moduleItemCode = moduleQuestionnaireBean.getModuleItemCode();
        this.moduleSubItemCode = moduleQuestionnaireBean.getModuleSubItemCode();
        this.moduleItemKey = moduleQuestionnaireBean.getModuleItemKey();
        this.moduleSubItemKey = moduleQuestionnaireBean.getModuleSubItemKey();
        this.questionnaireId = questionnaireId;
        answers = new ArrayList<Answer>();
        questions = new ArrayList<QuestionDTO>();
        showQuestions = NOT_SHOW_ANSWER;
    }

    public void setNewModuleQuestionnaireBeanReferenceData(ModuleQuestionnaireBean newModuleQuestionnaireBean) {
        this.moduleItemCode = newModuleQuestionnaireBean.getModuleItemCode();
        this.moduleItemKey = newModuleQuestionnaireBean.getModuleItemKey();
        this.moduleSubItemCode = newModuleQuestionnaireBean.getModuleSubItemCode();
        this.moduleSubItemKey = newModuleQuestionnaireBean.getModuleSubItemKey();
    }

    @Override
    public String getModuleItemCode() {
        return this.moduleItemCode;
    }

    public void setModuleItemCode(String moduleItemCode) {
        this.moduleItemCode = moduleItemCode;
    }

    @Override
    public String getModuleItemKey() {
        return this.moduleItemKey;
    }

    public void setModuleItemKey(String moduleItemKey) {
        this.moduleItemKey = moduleItemKey;
    }

    @Override
    public String getModuleSubItemCode() {
        return this.moduleSubItemCode;
    }

    public void setModuleSubItemCode(String moduleSubItemCode) {
        this.moduleSubItemCode = moduleSubItemCode;
    }

    @Override
    public String getModuleSubItemKey() {
        return this.moduleSubItemKey;
    }

    public void setModuleSubItemKey(String moduleSubItemKey) {
        this.moduleSubItemKey = moduleSubItemKey;
    }

    @Override
    public Long getQuestionnaireId() {
        return this.questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    @Override
    public boolean isCompleted() {
        return this.completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public List<Answer> getAnswers() {
        return this.answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isNewerVersionPublished() {
        return newerVersionPublished;
    }

    public void setNewerVersionPublished(boolean newerVersionPublished) {
        this.newerVersionPublished = newerVersionPublished;
    }

    public String getUpdateOption() {
        return updateOption;
    }

    public void setUpdateOption(String updateOption) {
        this.updateOption = updateOption;
    }

    public String getShowQuestions() {
        return showQuestions;
    }

    public void setShowQuestions(String showQuestions) {
        this.showQuestions = showQuestions;
    }

    public boolean isNotUpdated() {
        return notUpdated;
    }

    public void setNotUpdated(boolean notUpdated) {
        this.notUpdated = notUpdated;
    }

    public boolean isActiveQuestionnaire() {
        return activeQuestionnaire;
    }

    public void setActiveQuestionnaire(boolean activeQuestionnaire) {
        this.activeQuestionnaire = activeQuestionnaire;
    }

    /**
     * 
     * This method programatically checks to make sure all the answers have a value in the answer string.
     * @return
     */
    public boolean getAllQuestionsAnswered() {
        for (Answer answer : getAnswers()) {
            if (StringUtils.isEmpty(answer.getAnswer())) {
                return false;
            }
        }
        return true;
    }

    public boolean isHasVisibleQuestion() {
        return hasVisibleQuestion;
    }

    public void setHasVisibleQuestion(boolean hasVisibleQuestion) {
        this.hasVisibleQuestion = hasVisibleQuestion;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    @Override
    public boolean isActive() {
        return this.activeQuestionnaire && this.hasVisibleQuestion;
    }
}
