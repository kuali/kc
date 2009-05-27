/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.questionnaire.answer;

import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class QuestionnaireAnswerHeader extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 1L;

    private Long questionnaireAnswerHeaderId;
    private Integer moduleItemCode; 
    private String moduleItemKey; 
    private Integer moduleSubItemCode; 
    private String moduleSubItemKey;
    private Integer questionnaireId;
    //private Questionnaire questionnaire 
    private boolean questionnaireCompletedFlag; 
    
    private List<QuestionnaireAnswer> questionnaireAnswers; 
    
    /**
     * Gets the moduleItemCode attribute. 
     * @return Returns the moduleItemCode.
     */
    public Integer getModuleItemCode() {
        return this.moduleItemCode;
    }

    /**
     * Sets the moduleItemCode attribute value.
     * @param moduleItemCode The moduleItemCode to set.
     */
    public void setModuleItemCode(Integer moduleItemCode) {
        this.moduleItemCode = moduleItemCode;
    }

    /**
     * Gets the moduleItemKey attribute. 
     * @return Returns the moduleItemKey.
     */
    public String getModuleItemKey() {
        return this.moduleItemKey;
    }

    /**
     * Sets the moduleItemKey attribute value.
     * @param moduleItemKey The moduleItemKey to set.
     */
    public void setModuleItemKey(String moduleItemKey) {
        this.moduleItemKey = moduleItemKey;
    }

    /**
     * Gets the moduleSubItemCode attribute. 
     * @return Returns the moduleSubItemCode.
     */
    public Integer getModuleSubItemCode() {
        return this.moduleSubItemCode;
    }

    /**
     * Sets the moduleSubItemCode attribute value.
     * @param moduleSubItemCode The moduleSubItemCode to set.
     */
    public void setModuleSubItemCode(Integer moduleSubItemCode) {
        this.moduleSubItemCode = moduleSubItemCode;
    }

    /**
     * Gets the moduleSubItemKey attribute. 
     * @return Returns the moduleSubItemKey.
     */
    public String getModuleSubItemKey() {
        return this.moduleSubItemKey;
    }

    /**
     * Sets the moduleSubItemKey attribute value.
     * @param moduleSubItemKey The moduleSubItemKey to set.
     */
    public void setModuleSubItemKey(String moduleSubItemKey) {
        this.moduleSubItemKey = moduleSubItemKey;
    }

    /**
     * Gets the questionnaireId attribute. 
     * @return Returns the questionnaireId.
     */
    public Integer getQuestionnaireId() {
        return this.questionnaireId;
    }

    /**
     * Sets the questionnaireId attribute value.
     * @param questionnaireId The questionnaireId to set.
     */
    public void setQuestionnaireId(Integer questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

//    /**
//     * Gets the questionnaire attribute. 
//     * @return Returns the questionnaire.
//     */
//    public Questionnaire getQuestionnaire() {
//        return questionnaire;
//    }
//
//    /**
//     * Sets the questionnaire attribute value.
//     * @param questionnaire The questionnaire to set.
//     */
//    public void setQuestionnaire(Questionnaire questionnaire) {
//        this.questionnaire = questionnaire;
//    }

    /**
     * Gets the questionnaireCompletionId attribute. 
     * @return Returns the questionnaireCompletionId.
     */
    public Long getQuestionnaireAnswerHeaderId() {
        return this.questionnaireAnswerHeaderId;
    }

    /**
     * Sets the questionnaireAnswerHeaderId attribute value.
     * @param questionnaireAnswerHeaderId The questionnaireAnswerHeaderId to set.
     */
    public void setQuestionnaireAnswerHeaderId(Long questionnaireAnswerHeaderId) {
        this.questionnaireAnswerHeaderId = questionnaireAnswerHeaderId;
    }

    /**
     * Gets the questionnaireCompletedFlag attribute. 
     * @return Returns the questionnaireCompletedFlag.
     */
    public boolean getQuestionnaireCompletedFlag() {
        return this.questionnaireCompletedFlag;
    }

    /**
     * Sets the questionnaireCompletedFlag attribute value.
     * @param questionnaireCompletedFlag The questionnaireCompletedFlag to set.
     */
    public void setQuestionnaireCompletedFlag(boolean questionnaireCompletedFlag) {
        this.questionnaireCompletedFlag = questionnaireCompletedFlag;
    }

    /**
     * Gets the questionnaireAnswers attribute. 
     * @return Returns the questionnaireAnswers.
     */
    public List<QuestionnaireAnswer> getQuestionnaireAnswers() {
        return this.questionnaireAnswers;
    }

    /**
     * Sets the questionnaireAnswers attribute value.
     * @param questionnaireAnswers The questionnaireAnswers to set.
     */
    public void setQuestionnaireAnswers(List<QuestionnaireAnswer> questionnaireAnswers) {
        this.questionnaireAnswers = questionnaireAnswers;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("moduleItemCode", this.getModuleItemCode());
        hashMap.put("moduleItemKey", this.getModuleItemKey());
        hashMap.put("moduleSubItemCode", this.getModuleSubItemCode());
        hashMap.put("moduleSubItemKey", this.getModuleSubItemKey());
        hashMap.put("questionnaireId", this.getQuestionnaireId());
        hashMap.put("questionnaireAnswerHeaderId", this.getQuestionnaireAnswerHeaderId());
        hashMap.put("questionnaireCompletedFlag", Boolean.valueOf(this.getQuestionnaireCompletedFlag()));
        return hashMap;
    }
}
