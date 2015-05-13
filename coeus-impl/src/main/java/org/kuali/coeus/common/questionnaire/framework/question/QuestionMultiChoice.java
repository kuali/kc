package org.kuali.coeus.common.questionnaire.framework.question;

import org.apache.commons.lang3.ObjectUtils;
import org.kuali.coeus.common.framework.version.sequence.associate.SequenceAssociate;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class QuestionMultiChoice extends KcPersistableBusinessObjectBase implements SequenceAssociate<Question> {

    private Long id;
    private Long questionId;
    private String prompt;
    private String description;
    private Question sequenceOwner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        QuestionMultiChoice questionMultiChoice = (QuestionMultiChoice) obj;
        if (ObjectUtils.equals(this.questionId, questionMultiChoice.questionId) && ObjectUtils.equals(this.prompt, questionMultiChoice.prompt) && ObjectUtils.equals(this.description, questionMultiChoice.description)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + (this.questionId == null ? 0 : this.questionId.hashCode());
        result = PRIME * result + (this.prompt == null ? 0 : this.prompt.hashCode());
        result = PRIME * result + (this.description == null ? 0 : this.description.hashCode());
        return result;
    }

    @Override
    public Question getSequenceOwner() {
        return this.sequenceOwner;
    }

    @Override
    public void setSequenceOwner(Question newlyVersionedOwner) {
        this.sequenceOwner = newlyVersionedOwner;
    }

    @Override
    public Integer getSequenceNumber() {
        return this.sequenceOwner.getSequenceNumber();
    }

    @Override
    public void resetPersistenceState() {
        this.id = null;
    }
}
