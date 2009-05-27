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
package org.kuali.kra.questionnaire.question;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.Constants;

public class Question extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 1L;

    private Integer questionId; 
    private String question;
    private String status;
    private Integer categoryTypeCode; 
    private Integer maxAnswers;
    private Integer displayedAnswers;
    private String validAnswer; 
    private String lookupName; 
    private Integer questionTypeId; 
    private Integer answerMaxLength; 
    private String lookupGui; 
    
    private QuestionCategory questionCategory;
    private QuestionType questionType; 
    private List<QuestionExplanation> questionExplanations;
    
    
    public Question() { 
        setQuestionExplanations(new ArrayList<QuestionExplanation>());
    } 

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCategoryTypeCode() {
        return categoryTypeCode;
    }

    public void setCategoryTypeCode(Integer categoryTypeCode) {
        this.categoryTypeCode = categoryTypeCode;
    }

    public Integer getMaxAnswers() {
        return maxAnswers;
    }

    public void setMaxAnswers(Integer maxAnswers) {
        this.maxAnswers = maxAnswers;
    }

    public void setDisplayedAnswers(Integer displayedAnswers) {
        this.displayedAnswers = displayedAnswers;
    }

    public Integer getDisplayedAnswers() {
        return displayedAnswers;
    }

    public String getValidAnswer() {
        return validAnswer;
    }

    public void setValidAnswer(String validAnswer) {
        this.validAnswer = validAnswer;
    }

    public String getLookupName() {
        return lookupName;
    }

    public void setLookupName(String lookupName) {
        this.lookupName = lookupName;
    }

    public Integer getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(Integer questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public Integer getAnswerMaxLength() {
        return answerMaxLength;
    }

    public void setAnswerMaxLength(Integer answerMaxLength) {
        this.answerMaxLength = answerMaxLength;
    }

    public String getLookupGui() {
        return lookupGui;
    }

    public void setLookupGui(String lookupGui) {
        this.lookupGui = lookupGui;
    }

    public QuestionCategory getQuestionCategory() {
        return questionCategory;
    }

    public void setQuestionCategory(QuestionCategory questionCategory) {
        this.questionCategory = questionCategory;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public List<QuestionExplanation> getQuestionExplanations() {
        return questionExplanations;
    }

    public void setQuestionExplanations(List<QuestionExplanation> questionExplanations) {
        this.questionExplanations = questionExplanations;
    }

    public String getExplanation() {
        return getExplanation(Constants.QUESTION_EXPLANATION);
    }
    
    public void setExplanation(String explanation) {
        setExplanation(explanation, Constants.QUESTION_EXPLANATION);
    }
    
    public String getPolicy() {
        return getExplanation(Constants.QUESTION_POLICY);
    }
    
    public void setPolicy(String policy) {
        setExplanation(policy, Constants.QUESTION_POLICY);
    }
    
    public String getRegulation() {
        return getExplanation(Constants.QUESTION_REGULATION);
    }
    
    public void setRegulation(String regulation) {
        setExplanation(regulation, Constants.QUESTION_REGULATION);
    }
    
    /**
     * This method returns the question explanation (explanation, policy, or regulation).
     * 
     * @param explanationType - use one of the QUESTION_EXPLANATION, QUESTION_POLICY, or QUESTION_REGULATION 
     *                          constants to return the proper explanation type.
     * @return The explanation or an empty string if none exists. 
     */
    private String getExplanation(String explanationType){ 
        int index = getExplanationObjectIndex(explanationType);
        if (index < 0) {
            return "";
        } else {
            return questionExplanations.get(index).getExplanation();
        }
    }
    
    /**
     * This method sets the question explanation (explanation, policy, or regulation).
     * 
     * @param explanation     - the new text of the explanation.
     * @param explanationType - use one of the QUESTION_EXPLANATION, QUESTION_POLICY, or QUESTION_REGULATION 
     *                          constants to set the proper explanation type.
     */
    private void setExplanation(String explanation, String explanationType) {
        int index = getExplanationObjectIndex(explanationType);
        if (index < 0) {
            QuestionExplanation questionExplanation = new QuestionExplanation();
            questionExplanation.setQuestionId(this.questionId);
            questionExplanation.setExplanationType(explanationType);
            questionExplanation.setExplanation(explanation);
            this.questionExplanations.add(questionExplanation);
        } else {
            this.questionExplanations.get(index).setExplanation(explanation);
        }
    }

    /**
     * This method returns the index position of a question explanation object (explanation, policy, and regulation).
     * 
     * @param explanationType - use one of the QUESTION_EXPLANATION, QUESTION_POLICY, or QUESTION_REGULATION 
     *                          constants to search for the proper type.
     * @return Index of object containing the question policy. -1 if not found. 
     */
    private int getExplanationObjectIndex(String explanationType) {
        for (QuestionExplanation questionExplanation: questionExplanations) {
            if (questionExplanation.getExplanationType().equals(explanationType)) {
                return questionExplanations.indexOf(questionExplanation);
            }
        }
        return -1;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("questionId", this.getQuestionId());
        hashMap.put("question", this.getQuestion());
        hashMap.put("status", this.getStatus());
        hashMap.put("categoryTypeCode", this.getCategoryTypeCode());
        hashMap.put("maxAnswers", this.getMaxAnswers());
        hashMap.put("displayedAnswers", this.getDisplayedAnswers());
        hashMap.put("validAnswer", this.getValidAnswer());
        hashMap.put("lookupName", this.getLookupName());
        hashMap.put("questionTypeId", this.getQuestionTypeId());
        hashMap.put("answerMaxLength", this.getAnswerMaxLength());
        hashMap.put("lookupGui", this.getLookupGui());
        return hashMap;
    }

}