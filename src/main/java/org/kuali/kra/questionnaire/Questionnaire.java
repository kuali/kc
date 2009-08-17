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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.ObjectUtils;
import org.hibernate.annotations.Type;
import org.kuali.kra.SequenceOwner;
import org.kuali.kra.award.home.AwardTemplateContact;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.proposaldevelopment.bo.YnqGroupName;
import org.kuali.kra.questionnaire.question.Question;
import org.kuali.rice.kns.util.TypedArrayList;

@Entity 
@Table(name="QUESTIONNAIRE")
public class Questionnaire extends KraPersistableBusinessObjectBase implements Comparable<Questionnaire>,SequenceOwner<Questionnaire>{ 
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "QUESTIONNAIRE_REF_ID")
    private Long questionnaireRefId;
    @Column(name="QUESTIONNAIRE_ID")
    private Integer questionnaireId; 
    @Column(name="NAME")
    private String name; 
    @Column(name="DESCRIPTION")
    private String description; 
    @Type(type="yes_no")
    @Column(name="IS_FINAL")
    private boolean isFinal; 
    
    @Column(name = "SEQUENCE_NUMBER")
    private Integer sequenceNumber;
    @Column(name="DOCUMENT_NUMBER")
    private String documentNumber;

    
    @OneToMany(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="QUESTIONNAIRE_ID", insertable=false, updatable=false)
    private List<QuestionnaireQuestion> questionnaireQuestions;
    
    @OneToMany(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="QUESTIONNAIRE_ID", insertable=false, updatable=false)
    private List<QuestionnaireUsage> questionnaireUsages;
        
    public Questionnaire() { 
        super();
        questionnaireQuestions = new TypedArrayList(QuestionnaireQuestion.class); 
        questionnaireUsages = new TypedArrayList(QuestionnaireUsage.class); 

    } 
    
    public Integer getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Integer questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsFinal() {
        return isFinal;
    }

    public void setIsFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }

    public List<QuestionnaireQuestion> getQuestionnaireQuestions() {
        return questionnaireQuestions;
    }

    public void setQuestionnaireQuestions(List<QuestionnaireQuestion> questionnaireQuestions) {
        this.questionnaireQuestions = questionnaireQuestions;
    }

    
    
    public List<QuestionnaireUsage> getQuestionnaireUsages() {
        return questionnaireUsages;
    }

    public void setQuestionnaireUsages(List<QuestionnaireUsage> questionnaireUsages) {
        this.questionnaireUsages = questionnaireUsages;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("questionnaireRefId", this.getQuestionnaireRefId());
        hashMap.put("questionnaireId", this.getQuestionnaireId());
        hashMap.put("name", this.getName());
        hashMap.put("sequenceNumber", this.getSequenceNumber());
        hashMap.put("documentNumber", this.getDocumentNumber());
        hashMap.put("description", this.getDescription());
        hashMap.put("isFinal", this.getIsFinal());
        return hashMap;
    }

    public Integer getOwnerSequenceNumber() {
        return null;
    }

    public String getVersionNameField() {
        return "questionnaireId";
    }

    public void incrementSequenceNumber() {
        sequenceNumber++;
    }

    public Questionnaire getSequenceOwner() {
        return this;
    }

    public void setSequenceOwner(Questionnaire newlyVersionedOwner) {
        // do nothing - this is root sequence association
    }


    public void resetPersistenceState() {
        // TODO Auto-generated method stub
        
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Long getQuestionnaireRefId() {
        return questionnaireRefId;
    }

    public void setQuestionnaireRefId(Long questionnaireRefId) {
        this.questionnaireRefId = questionnaireRefId;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public int compareTo(Questionnaire argQuestionnaire) {
        if (ObjectUtils.equals(this.getQuestionnaireId(), argQuestionnaire.getQuestionnaireId())) {
            return this.getSequenceNumber().compareTo(argQuestionnaire.getSequenceNumber());
        } else {
            return this.getQuestionnaireId().compareTo(argQuestionnaire.getQuestionnaireId());
        }
    }
    
}