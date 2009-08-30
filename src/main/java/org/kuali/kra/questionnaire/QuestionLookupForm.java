/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.questionnaire;

import org.kuali.rice.kns.web.struts.form.KualiForm;

/**
 * 
 * This class is used for questionnaire maintenance to do question lookup.
 */
public class QuestionLookupForm extends KualiForm {
    private static final long serialVersionUID = 6262867553384550677L;
    private Integer newQuestionId;
    private Integer newQuestionTypeId;
    private String newQuestion;
    private Integer nodeIndex;
    private String lookupResultsBOClassName;
    private String lookedUpCollectionName;
    private String selectedQuestions;
    private String newLookupClass;
    private String newLookupReturn;
    private Integer newDisplayedAnswers;
    private Integer newMaxAnswers;
    private Integer newAnswerMaxLength;
    private Integer newQuestionSequence;
    /**
     * Used to indicate which result set we're using when refreshing/returning from a multi-value lookup
     */
    private String lookupResultsSequenceNumber;


    public QuestionLookupForm() {
        super();
    }

    public Integer getNewQuestionId() {
        return newQuestionId;
    }

    public void setNewQuestionId(Integer newQuestionId) {
        this.newQuestionId = newQuestionId;
    }

    public String getNewQuestion() {
        return newQuestion;
    }

    public void setNewQuestion(String newQuestion) {
        this.newQuestion = newQuestion;
    }

    public Integer getNodeIndex() {
        return nodeIndex;
    }

    public void setNodeIndex(Integer nodeIndex) {
        this.nodeIndex = nodeIndex;
    }

    public Integer getNewQuestionTypeId() {
        return newQuestionTypeId;
    }

    public void setNewQuestionTypeId(Integer newQuestionTypeId) {
        this.newQuestionTypeId = newQuestionTypeId;
    }

    public String getLookupResultsBOClassName() {
        return lookupResultsBOClassName;
    }

    public void setLookupResultsBOClassName(String lookupResultsBOClassName) {
        this.lookupResultsBOClassName = lookupResultsBOClassName;
    }

    public String getLookedUpCollectionName() {
        return lookedUpCollectionName;
    }

    public void setLookedUpCollectionName(String lookedUpCollectionName) {
        this.lookedUpCollectionName = lookedUpCollectionName;
    }


    public String getLookupResultsSequenceNumber() {
        return lookupResultsSequenceNumber;
    }

    public void setLookupResultsSequenceNumber(String lookupResultsSequenceNumber) {
        this.lookupResultsSequenceNumber = lookupResultsSequenceNumber;
    }

    public String getSelectedQuestions() {
        return selectedQuestions;
    }

    public void setSelectedQuestions(String selectedQuestions) {
        this.selectedQuestions = selectedQuestions;
    }

    public String getNewLookupClass() {
        return newLookupClass;
    }

    public void setNewLookupClass(String newLookupClass) {
        this.newLookupClass = newLookupClass;
    }

    public String getNewLookupReturn() {
        return newLookupReturn;
    }

    public void setNewLookupReturn(String newLookupReturn) {
        this.newLookupReturn = newLookupReturn;
    }

    public Integer getNewDisplayedAnswers() {
        return newDisplayedAnswers;
    }

    public void setNewDisplayedAnswers(Integer newDisplayedAnswers) {
        this.newDisplayedAnswers = newDisplayedAnswers;
    }

    public Integer getNewMaxAnswers() {
        return newMaxAnswers;
    }

    public void setNewMaxAnswers(Integer newMaxAnswers) {
        this.newMaxAnswers = newMaxAnswers;
    }

    public Integer getNewAnswerMaxLength() {
        return newAnswerMaxLength;
    }

    public void setNewAnswerMaxLength(Integer newAnswerMaxLength) {
        this.newAnswerMaxLength = newAnswerMaxLength;
    }

    public Integer getNewQuestionSequence() {
        return newQuestionSequence;
    }

    public void setNewQuestionSequence(Integer newQuestionSequence) {
        this.newQuestionSequence = newQuestionSequence;
    }

}
