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
package org.kuali.kra.questionnaire;

import java.util.LinkedHashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.questionnaire.question.Question;

@Entity 
@Table(name="QUESTIONNAIRE_QUESTIONS")
public class QuestionnaireQuestion extends KraPersistableBusinessObjectBase implements SequenceAssociate<Questionnaire> { 
    
    private static final long serialVersionUID = 1699439856326521334L;
    @Id 
    @Column(name="QUESTIONNAIRE_QUESTIONS_ID")
    private Long questionnaireQuestionsId; 
    @Column(name="QUESTIONNAIRE_REF_ID_FK")
    private Long questionnaireRefIdFk; 
    @Column(name="QUESTION_REF_ID_FK")
    private Long questionRefIdFk; 
    @Column(name="QUESTION_NUMBER")
    private Integer questionNumber; 
    @Column(name="PARENT_QUESTION_NUMBER")
    private Integer parentQuestionNumber; 
    @Type(type="yes_no")
    @Column(name="CONDITION_FLAG")
    private boolean conditionFlag; 
    @Column(name="CONDITION")
    private String condition; 
    @Column(name="CONDITION_VALUE")
    private String conditionValue; 
    @Column(name="QUESTION_SEQ_NUMBER")
    private Integer questionSeqNumber; 
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="QUESTION_REF_ID_FK", insertable=false, updatable=false)
    private Question question;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="QUESTIONNAIRE_REF_ID_FK", insertable=false, updatable=false)
    private Questionnaire questionnaire;
        
    private Questionnaire sequenceOwner;

    @Transient
    private String deleted;

    public QuestionnaireQuestion() { 

    } 
    
    public Long getQuestionnaireQuestionsId() {
        return questionnaireQuestionsId;
    }

    public void setQuestionnaireQuestionsId(Long questionnaireQuestionsId) {
        this.questionnaireQuestionsId = questionnaireQuestionsId;
    }

    public Long getQuestionnaireRefIdFk() {
        return questionnaireRefIdFk;
    }

    public void setQuestionnaireRefIdFk(Long questionnaireRefIdFk) {
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

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("questionnaireQuestionsId", this.getQuestionnaireQuestionsId());
        hashMap.put("questionnaireRefIdFk", this.getQuestionnaireRefIdFk());
        hashMap.put("questionRefIdFk", this.getQuestionRefIdFk());
        hashMap.put("questionNumber", this.getQuestionNumber());
        hashMap.put("parentQuestionNumber", this.getParentQuestionNumber());
        hashMap.put("conditionFlag", this.getConditionFlag());
        hashMap.put("condition", this.getCondition());
        hashMap.put("conditionValue", this.getConditionValue());
        hashMap.put("questionSeqNumber", this.getQuestionSeqNumber());
        hashMap.put("versionNumber", this.getVersionNumber());
        return hashMap;
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

    
}