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
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.SequenceOwner;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.datadictionary.BusinessObjectEntry;
import org.kuali.rice.kns.service.DataDictionaryService;

public class Question extends KraPersistableBusinessObjectBase implements Comparable<Question>, SequenceOwner<Question> { 
    
    private static final long serialVersionUID = 1L;

    private Long questionRefId;
    private Integer questionId;
    private Integer sequenceNumber;
    private String question;
    private String status;
    private Integer categoryTypeCode; 
    private Integer questionTypeId;  
    private String lookupClass; 
    private String lookupReturn; 
    private Integer displayedAnswers;
    private Integer maxAnswers;
    private Integer answerMaxLength; 
    
    private QuestionCategory questionCategory;
    private QuestionType questionType; 
    private List<QuestionExplanation> questionExplanations;
    
    
    public Question() { 
        this.setSequenceNumber(1);
        this.setQuestionExplanations(new ArrayList<QuestionExplanation>());
    } 

    public Long getQuestionRefId() {
        return questionRefId;
    }

    public void setQuestionRefId(Long questionRefId) {
        this.questionRefId = questionRefId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
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

    public Integer getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(Integer questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public String getLookupClass() {
        return lookupClass;
    }

    public void setLookupClass(String lookupClass) {
        this.lookupClass = lookupClass;
    }
    
    /**
     * 
     * This method returns the descriptive text of the lookupClass
     * @return descriptive text
     */
    public String getLookupClassDescription() {
        if (this.lookupClass != null) {
            DataDictionaryService dataDictionaryService = KraServiceLocator.getService(DataDictionaryService.class);
            Map<String, BusinessObjectEntry> businessObjectEntries = dataDictionaryService.getDataDictionary().getBusinessObjectEntries();
            return StringUtils.removeEnd(businessObjectEntries.get(this.lookupClass).getLookupDefinition().getTitle().trim()," Lookup");
        } else {
            return "";
        }
    }

    public String getLookupReturn() {
        return lookupReturn;
    }

    public void setLookupReturn(String lookupReturn) {
        this.lookupReturn = lookupReturn;
    }
    
    /**
     * 
     * This method returns the descriptive text of the lookupReturn
     * @return descriptive text
     */
    public String getLookupReturnDescription() {
        if ((this.lookupClass != null) && (this.lookupReturn != null)) {
            DataDictionaryService dataDictionaryService = KraServiceLocator.getService(DataDictionaryService.class);
            return dataDictionaryService.getAttributeLabel(this.lookupClass,this.lookupReturn);
        } else {
            return "";
        }
    }

    public Integer getDisplayedAnswers() {
        return displayedAnswers;
    }

    public void setDisplayedAnswers(Integer displayedAnswers) {
        this.displayedAnswers = displayedAnswers;
    }

    public Integer getMaxAnswers() {
        return maxAnswers;
    }

    public void setMaxAnswers(Integer maxAnswers) {
        this.maxAnswers = maxAnswers;
    }

    public Integer getAnswerMaxLength() {
        return answerMaxLength;
    }

    public void setAnswerMaxLength(Integer answerMaxLength) {
        this.answerMaxLength = answerMaxLength;
    }

    public QuestionCategory getQuestionCategory() {
        // Refresh of the reference object is needed so that the category name is displayed
        // after a save or refresh.  Otherwise the category type code is displayed.
        if (this.questionCategory == null) {
            refreshReferenceObject("questionCategory");
        }
        return questionCategory;
    }

    public void setQuestionCategory(QuestionCategory questionCategory) {
        this.questionCategory = questionCategory;
    }

    public QuestionType getQuestionType() {
        // Refresh of the reference object is needed so that the question type name is available
        // after a save or refresh.  Otherwise the proper question type can not be determined and
        // the response values are not being displayed.
        if (this.questionType == null) {
            refreshReferenceObject("questionType");
        }
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
            questionExplanation.setQuestionRefIdFk(this.questionRefId);
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
        // Refresh of the reference object is needed so that the explanations are displayed
        // after a save or refresh.
        if (this.questionExplanations.isEmpty()) {
            refreshReferenceObject("questionExplanations");
        }

        for (QuestionExplanation questionExplanation: getQuestionExplanations()) {
            if (questionExplanation.getExplanationType().equals(explanationType)) {
                return getQuestionExplanations().indexOf(questionExplanation);
            }
        }
        return -1;
    }

    /**
     * The default comparator goes by the order of questionId, sequenceNumber.
     * @param argQuestion the Question to be compared.
     * @return the value 0 if this Question is equal to the argument Question;
     *         a value less than 0 if this Question has a questionId & sequenceNumber pair that is less
     *         than the argument Question;  and a value greater than 0 if this Question has a questionId
     *         & sequenceNumber pair that is greater than the argument Question.
     */
    public int compareTo(Question argQuestion) {
        if (ObjectUtils.equals(this.getQuestionId(), argQuestion.getQuestionId())) {
            return this.getSequenceNumber().compareTo(argQuestion.getSequenceNumber());
        } else {
            return this.getQuestionId().compareTo(argQuestion.getQuestionId());
        }
    }
    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("questionRefId", this.getQuestionRefId());
        hashMap.put("questionId", this.getQuestionId());
        hashMap.put("sequenceNumber", this.getSequenceNumber());
        hashMap.put("question", this.getQuestion());
        hashMap.put("status", this.getStatus());
        hashMap.put("categoryTypeCode", this.getCategoryTypeCode());
        hashMap.put("questionTypeId", this.getQuestionTypeId());
        hashMap.put("lookupClass", this.getLookupClass());
        hashMap.put("lookupReturn", this.getLookupReturn());
        hashMap.put("displayedAnswers", this.getDisplayedAnswers());
        hashMap.put("maxAnswers", this.getMaxAnswers());
        hashMap.put("answerMaxLength", this.getAnswerMaxLength());
        return hashMap;
    }

    public Integer getOwnerSequenceNumber() {
        return null;
    }

    public String getVersionNameField() {
        return "questionId";
    }

    public void incrementSequenceNumber() {
        sequenceNumber++;
    }

    public Question getSequenceOwner() {
        return this;
    }

    public void setSequenceOwner(Question newlyVersionedOwner) {
        // do nothing - this is root sequence association
    }

    public void resetPersistenceState() {
        this.questionRefId = null;        
    }

}