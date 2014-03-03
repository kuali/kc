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
package org.kuali.kra.questionnaire;

import org.kuali.coeus.common.framework.sequence.associate.SequenceAssociate;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.questionnaire.question.Question;

public class QuestionnaireQuestion extends KcPersistableBusinessObjectBase implements SequenceAssociate<Questionnaire> {

    private static final long serialVersionUID = 1699439856326521334L;

    private Long questionnaireQuestionsId;

    private String questionnaireRefIdFk;

    private Long questionRefIdFk;

    private Integer questionNumber;

    private Integer parentQuestionNumber;

    private boolean conditionFlag;

    private String condition;

    private String conditionValue;

    private Integer questionSeqNumber;

    private String ruleId;

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    private Question question;

    private Questionnaire questionnaire;

    private Questionnaire sequenceOwner;

    private String deleted;
    private boolean isAllow; 

    public QuestionnaireQuestion() {
    }

    public Long getQuestionnaireQuestionsId() {
        return questionnaireQuestionsId;
    }

    public void setQuestionnaireQuestionsId(Long questionnaireQuestionsId) {
        this.questionnaireQuestionsId = questionnaireQuestionsId;
    }

    public String getQuestionnaireRefIdFk() {
        return questionnaireRefIdFk;
    }

    public void setQuestionnaireRefIdFk(String questionnaireRefIdFk) {
        this.questionnaireRefIdFk = questionnaireRefIdFk;
    }

    public Long getQuestionRefIdFk() {
        return questionRefIdFk;
    }

    public void setQuestionRefIdFk(Long questionRefIdFk) {
        this.questionRefIdFk = questionRefIdFk;
    }

    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(Integer questionNumber) {
        this.questionNumber = questionNumber;
    }

    public Integer getParentQuestionNumber() {
        return parentQuestionNumber;
    }

    public void setParentQuestionNumber(Integer parentQuestionNumber) {
        this.parentQuestionNumber = parentQuestionNumber;
    }

    public boolean getConditionFlag() {
        return conditionFlag;
    }

    public void setConditionFlag(boolean conditionFlag) {
        this.conditionFlag = conditionFlag;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getConditionValue() {
        return conditionValue;
    }

    public void setConditionValue(String conditionValue) {
        this.conditionValue = conditionValue;
    }

    public Integer getQuestionSeqNumber() {
        return questionSeqNumber;
    }

    public void setQuestionSeqNumber(Integer questionSeqNumber) {
        this.questionSeqNumber = questionSeqNumber;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public Questionnaire getSequenceOwner() {
        return this.getQuestionnaire();
    }

    public void setSequenceOwner(Questionnaire newlyVersionedOwner) {
        setQuestionnaire(newlyVersionedOwner);
    }

    /** {@inheritDoc} */
    public void resetPersistenceState() {
        this.setQuestionnaireQuestionsId(null);
    }

    public Integer getSequenceNumber() {
        return this.sequenceOwner.getSequenceNumber();
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public boolean isAllow() {
        return isAllow;
    }
    
    public void setAllow(boolean isAllow) {
        this.isAllow = isAllow;
    }
    
}
