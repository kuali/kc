/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.common.questionnaire.framework.core;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.struts.upload.FormFile;
import org.kuali.coeus.common.questionnaire.api.core.QuestionnaireContract;
import org.kuali.coeus.common.framework.version.sequence.owner.SequenceOwner;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.bo.PersistableAttachment;
import org.springframework.util.AutoPopulatingList;

import java.util.List;

public class Questionnaire extends KcPersistableBusinessObjectBase implements Comparable<Questionnaire>, SequenceOwner<Questionnaire>, MutableInactivatable, PersistableAttachment, QuestionnaireContract {

    private static final long serialVersionUID = 8679896046435777084L;

    private Long id;

    private String questionnaireSeqId;

    private String name;

    private String description;

    private boolean active;

    private Integer sequenceNumber;

    private String documentNumber;

    private String fileName;

    private byte[] template;

    private List<QuestionnaireQuestion> questionnaireQuestions;

    private List<QuestionnaireUsage> questionnaireUsages;

    private transient FormFile templateFile;

    public Questionnaire() {
        super();
        questionnaireQuestions = new AutoPopulatingList<QuestionnaireQuestion>(QuestionnaireQuestion.class);
        questionnaireUsages = new AutoPopulatingList<QuestionnaireUsage>(QuestionnaireUsage.class);
    }

    @Override
    public String getQuestionnaireSeqId() {
        return questionnaireSeqId;
    }

    public Integer getQuestionnaireSeqIdAsInteger() {
        Integer retVal = null;
        if (this.questionnaireSeqId != null) {
            retVal = Integer.valueOf(this.questionnaireSeqId);
        }
        return retVal;
    }

    public void setQuestionnaireSeqId(String questionnaireSeqId) {
        this.questionnaireSeqId = questionnaireSeqId;
    }

    public void setQuestionnaireSeqIdFromInteger(Integer questionnaireSeqIdAsInteger) {
        if (questionnaireSeqIdAsInteger != null) {
            this.questionnaireSeqId = questionnaireSeqIdAsInteger.toString();
        } else {
            this.questionnaireSeqId = null;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
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

    @Override
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
        return QuestionnaireConstants.QUESTIONNAIRE_SEQUENCE_ID_PARAMETER_NAME;
    }

    @Override
    public String getVersionNameFieldValue() {
        return questionnaireSeqId;
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
        if (this.id != null) {
            retVal = Long.valueOf(this.id);
        }
        return retVal;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuestionnaireRefIdFromLong(Long questionnaireRefIdAsLong) {
        if (questionnaireRefIdAsLong != null) {
            this.id = questionnaireRefIdAsLong;
        } else {
            this.id = null;
        }
    }

    @Override
    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public int compareTo(Questionnaire argQuestionnaire) {
        if (ObjectUtils.equals(this.getId(), argQuestionnaire.getId())) {
            return this.getSequenceNumber().compareTo(argQuestionnaire.getSequenceNumber());
        } else {
            return this.getQuestionnaireSeqIdAsInteger().compareTo(argQuestionnaire.getQuestionnaireSeqIdAsInteger());
        }
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
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
    
    // TODO: Added temporarily, pending official fix in KCINFR-579

    @Override
    public byte[] getAttachmentContent() {
        return template;
    }
    
    @Override
    public void setAttachmentContent(byte[] attachmentContent) {
        this.template = attachmentContent;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public void setContentType(String arg0) {
        // do nothing
    }
}
