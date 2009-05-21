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
        return "To be implemented: getExplanation()";
    }
    
    public void setExplanation(String explanation) {
        
    }
    
    public String getPolicy() {
        return "To be implemented: getPolicy()";
    }
    
    public void setPolicy(String policy) {
        
    }
    
    public String getRegulation() {
        return "To be implemented: getRegulation()";
    }
    
    public void setRegulation(String regulation) {
        
    }
    
//    public String getPolicy() {
//        QuestionExplanation questionPolicy = findExplanationObject(Constants.QUESTION_POLICY);
//        if (questionPolicy == null) {
//            return "";
//        } else {
//            return questionPolicy.getExplanation();
//        }
//    }
//    
//    public void setPolicy(String policy) {
//        QuestionExplanation questionPolicy = findExplanationObject(Constants.QUESTION_POLICY);
//        if (questionPolicy == null) {
//            return "";
//        
//    }
//
//    /**
//     * This method finds and returns a question explanation object (explanation, policy, and regulation).  If non is found null is returned.
//     * 
//     * @param explanationType - use one of the QUESTION_EXPLANATION, QUESTION_POLICY, or QUESTION_REGULATION 
//     *                          constants to search for the proper type.
//     * @return QuestionExplanation object containing the question policy.
//     */
//    private QuestionExplanation findExplanationObject(String explanationType) {
//        for (QuestionExplanation questionExplanation: questionExplanations) {
//            if (questionExplanation.getExplanationType() == explanationType) {
//                return questionExplanation;
//            }
//        }
//        return null;
//    }

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