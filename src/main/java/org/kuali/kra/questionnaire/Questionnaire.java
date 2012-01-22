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

import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.struts.upload.FormFile;
import org.kuali.kra.SequenceOwner;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.springframework.util.AutoPopulatingList;

public class Questionnaire extends KraPersistableBusinessObjectBase implements Comparable<Questionnaire>, SequenceOwner<Questionnaire> {

    private static final long serialVersionUID = 8679896046435777084L;

    private String questionnaireRefId;

    private String questionnaireId;

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
        questionnaireQuestions = new AutoPopulatingList<QuestionnaireQuestion>(QuestionnaireQuestion.class);
        questionnaireUsages = new AutoPopulatingList<QuestionnaireUsage>(QuestionnaireUsage.class);
    }

    public String getQuestionnaireId() {
        return questionnaireId;
    }

    public Integer getQuestionnaireIdAsInteger() {
        Integer retVal = null;
        if (this.questionnaireId != null) {
            retVal = Integer.valueOf(this.questionnaireId);
        }
        return retVal;
    }

    public void setQuestionnaireId(String questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public void setQuestionnaireIdFromInteger(Integer questionnaireIdAsInteger) {
        if (questionnaireIdAsInteger != null) {
            this.questionnaireId = questionnaireIdAsInteger.toString();
        } else {
            this.questionnaireId = null;
        }
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
    }

    public void resetPersistenceState() {
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Long getQuestionnaireRefIdAsLong() {
        Long retVal = null;
        if (this.questionnaireRefId != null) {
            retVal = Long.valueOf(this.questionnaireRefId);
        }
        return retVal;
    }

    public String getQuestionnaireRefId() {
        return questionnaireRefId;
    }

    public void setQuestionnaireRefId(String questionnaireRefId) {
        this.questionnaireRefId = questionnaireRefId;
    }

    public void setQuestionnaireRefIdFromLong(Long questionnaireRefIdAsLong) {
        if (questionnaireRefIdAsLong != null) {
            this.questionnaireRefId = questionnaireRefIdAsLong.toString();
        } else {
            this.questionnaireRefId = null;
        }
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
            return this.getQuestionnaireIdAsInteger().compareTo(argQuestionnaire.getQuestionnaireIdAsInteger());
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

    public boolean hasUsageFor(String moduleItemCode, String moduleSubItemCode) {
        boolean retVal = false;
        for (QuestionnaireUsage usage : questionnaireUsages) {
            if (usage.getModuleItemCode().equals(moduleItemCode) && usage.getModuleSubItemCode().equals(moduleSubItemCode)) {
                retVal = true;
                break;
            }
        }
        return retVal;
    }

    public QuestionnaireUsage getHighestVersionUsageFor(String moduleItemCode, String moduleSubItemCode) {
        QuestionnaireUsage retVal = null;
        int version = 0;
        for (QuestionnaireUsage usage : questionnaireUsages) {
            if ((usage.getModuleItemCode().equals(moduleItemCode)) && (usage.getModuleSubItemCode().equals(moduleSubItemCode)) && (usage.getQuestionnaireSequenceNumber() > version)) {
                version = usage.getQuestionnaireSequenceNumber();
                retVal = usage;
            }
        }
        return retVal;
    }
}
