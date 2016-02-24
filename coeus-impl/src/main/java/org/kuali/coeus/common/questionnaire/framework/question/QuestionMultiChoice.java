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
