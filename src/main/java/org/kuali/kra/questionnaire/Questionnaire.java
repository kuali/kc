/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.struts.upload.FormFile;
import org.kuali.kra.SequenceOwner;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.rice.kns.util.TypedArrayList;

public class Questionnaire extends KraPersistableBusinessObjectBase implements Comparable<Questionnaire>,SequenceOwner<Questionnaire> { 
    
    private static final long serialVersionUID = 8679896046435777084L;
    private Long questionnaireRefId;
    private Integer questionnaireId; 
    private String name; 
    private String description; 
    private boolean isFinal; 
    private Integer sequenceNumber;
    private String documentNumber;
    private List<QuestionnaireQuestion> questionnaireQuestions;
    private List<QuestionnaireUsage> questionnaireUsages;
    private String fileName;
    private byte[] template; 
    
    private transient FormFile templateFile;

        
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

    public QuestionnaireQuestion getQuestionnaireQuestion(int index) {
        while (getQuestionnaireQuestions().size() <= index) {
            getQuestionnaireQuestions().add(new QuestionnaireQuestion());
        }
        return getQuestionnaireQuestions().get(index);
    }

    
    public List<QuestionnaireUsage> getQuestionnaireUsages() {
        return questionnaireUsages;
    }

    public void setQuestionnaireUsages(List<QuestionnaireUsage> questionnaireUsages) {
        this.questionnaireUsages = questionnaireUsages;
    }
    public QuestionnaireUsage getQuestionnaireUsage(int index) {
        while (getQuestionnaireUsages().size() <= index) {
            getQuestionnaireUsages().add(new QuestionnaireUsage());
        }
        return getQuestionnaireUsages().get(index);
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getTemplate() {
        return template;
    }

    public void setTemplate(byte[] template) {
        this.template = template;
    }

    public FormFile getTemplateFile() {
        return templateFile;
    }

    public void setTemplateFile(FormFile templateFile) {
        this.templateFile = templateFile;
    }
    
}
