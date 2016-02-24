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

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.questionnaire.api.question.QuestionContract;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireConstants;
import org.kuali.coeus.common.framework.version.sequence.owner.SequenceOwner;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.datadictionary.BusinessObjectEntry;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.service.KNSServiceLocator;

import java.util.ArrayList;
import java.util.List;

public class Question extends KcPersistableBusinessObjectBase implements Comparable<Question>, SequenceOwner<Question>, QuestionContract {

    private static final long serialVersionUID = 1L;

    private static final String SEQUENCE_STATUS_CURRENT = "C";

    private String documentNumber;

    private Long id;

    private Integer questionSeqId;

    private Integer sequenceNumber;

    private String sequenceStatus;

    private String question;

    private String status;

    private Long categoryTypeCode;

    private Long questionTypeId;

    private String lookupClass;

    private String lookupReturn;

    private Integer displayedAnswers;

    private Integer maxAnswers;

    private Integer answerMaxLength;

    private QuestionCategory questionCategory;

    private QuestionType questionType;

    private List<QuestionExplanation> questionExplanations;
    private List<QuestionMultiChoice> questionMultiChoices;

    public Question() {
        this.setSequenceNumber(1);
        this.setSequenceStatus(SEQUENCE_STATUS_CURRENT);
        this.setQuestionExplanations(new ArrayList<QuestionExplanation>());
        this.setQuestionMultiChoices(new ArrayList<QuestionMultiChoice>());
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Integer getQuestionSeqId() {
        return questionSeqId;
    }

    public void setQuestionSeqId(Integer questionId) {
        this.questionSeqId = questionId;
    }

    @Override
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    @Override
    public String getSequenceStatus() {
        return this.sequenceStatus;
    }

    public void setSequenceStatus(String sequenceStatus) {
        this.sequenceStatus = sequenceStatus;
    }

    @Override
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCategoryTypeCode() {
        return categoryTypeCode;
    }

    public void setCategoryTypeCode(Long categoryTypeCode) {
        this.categoryTypeCode = categoryTypeCode;
    }

    public Long getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(Long questionTypeId) {
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
            DataDictionaryService dataDictionaryService = KNSServiceLocator.getDataDictionaryService();
            BusinessObjectEntry businessObjectEntry = (BusinessObjectEntry) dataDictionaryService.getDataDictionary().getBusinessObjectEntries().get(this.lookupClass);
            return StringUtils.removeEnd(businessObjectEntry.getLookupDefinition().getTitle().trim(), " Lookup");
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
            DataDictionaryService dataDictionaryService = KcServiceLocator.getService(DataDictionaryService.class);
            return dataDictionaryService.getAttributeLabel(this.lookupClass, this.lookupReturn);
        } else {
            return "";
        }
    }
    @Override
    public Integer getDisplayedAnswers() {
        return displayedAnswers;
    }

    public void setDisplayedAnswers(Integer displayedAnswers) {
        this.displayedAnswers = displayedAnswers;
    }

    @Override
    public Integer getMaxAnswers() {
        return maxAnswers;
    }

    public void setMaxAnswers(Integer maxAnswers) {
        this.maxAnswers = maxAnswers;
    }

    @Override
    public Integer getAnswerMaxLength() {
        return answerMaxLength;
    }

    public void setAnswerMaxLength(Integer answerMaxLength) {
        this.answerMaxLength = answerMaxLength;
    }

    @Override
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

    @Override
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

    @Override
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

    public String getAffirmativeStatementConversion() {
        return getExplanation(Constants.QUESTION_AFFIRMATIVE_QUESTION_CONVERSION);
    }

    public void setAffirmativeStatementConversion(String affirmativeStatementConversion) {
        setExplanation(affirmativeStatementConversion, Constants.QUESTION_AFFIRMATIVE_QUESTION_CONVERSION);
    }

    public String getNegativeStatementConversion() {
        return getExplanation(Constants.QUESTION_NEGATIVE_QUESTION_CONVERSION);
    }

    public void setNegativeStatementConversion(String negativeStatementConversion) {
        setExplanation(negativeStatementConversion, Constants.QUESTION_NEGATIVE_QUESTION_CONVERSION);
    }

    /**
     * This method returns the question explanation (explanation, policy, or regulation).
     * 
     * @param explanationType - use one of the QUESTION_EXPLANATION, QUESTION_POLICY, or QUESTION_REGULATION 
     *                          constants to return the proper explanation type.
     * @return The explanation or an empty string if none exists. 
     */
    private String getExplanation(String explanationType) {
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
            questionExplanation.setQuestionId(this.id);
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
        for (QuestionExplanation questionExplanation : getQuestionExplanations()) {
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
     *         a value less than 0 if this Question has a questionId &amp; sequenceNumber pair that is less
     *         than the argument Question;  and a value greater than 0 if this Question has a questionId
     *         &amp; sequenceNumber pair that is greater than the argument Question.
     */
    public int compareTo(Question argQuestion) {
        if (ObjectUtils.equals(this.getQuestionSeqId(), argQuestion.getQuestionSeqId())) {
            return this.getSequenceNumber().compareTo(argQuestion.getSequenceNumber());
        } else {
            return this.getQuestionSeqId().compareTo(argQuestion.getQuestionSeqId());
        }
    }

    public Integer getOwnerSequenceNumber() {
        return null;
    }

    public String getVersionNameField() {
        return QuestionnaireConstants.QUESTION_SEQEQUENCE_ID;
    }

    @Override
    public String getVersionNameFieldValue() {
        return questionSeqId.toString();
    }

    public void incrementSequenceNumber() {
        sequenceNumber++;
    }

    public Question getSequenceOwner() {
        return this;
    }

    public void setSequenceOwner(Question newlyVersionedOwner) {
    }

    public void resetPersistenceState() {
        this.id = null;
    }

    public List<QuestionMultiChoice> getQuestionMultiChoices() {
        return questionMultiChoices;
    }

    public void setQuestionMultiChoices(List<QuestionMultiChoice> questionMultiChoices) {
        this.questionMultiChoices = questionMultiChoices;
    }

    public boolean isRadioButton() {
        return getMaxAnswers() == 1 && getDisplayedAnswers() != 1;
    }
}
