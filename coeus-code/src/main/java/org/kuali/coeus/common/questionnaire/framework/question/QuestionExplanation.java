/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.questionnaire.framework.question;

import org.apache.commons.lang3.ObjectUtils;
import org.kuali.coeus.common.questionnaire.api.question.QuestionExplanationContract;
import org.kuali.coeus.common.framework.version.sequence.associate.SequenceAssociate;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class QuestionExplanation extends KcPersistableBusinessObjectBase implements SequenceAssociate<Question>, QuestionExplanationContract {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long questionId;

    private String explanationType;

    private String explanation;

    private Question sequenceOwner;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @Override
    public String getExplanationType() {
        return explanationType;
    }

    public void setExplanationType(String explanationType) {
        this.explanationType = explanationType;
    }

    @Override
    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
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
        QuestionExplanation questionExplanation = (QuestionExplanation) obj;
        if (ObjectUtils.equals(this.questionId, questionExplanation.questionId) && ObjectUtils.equals(this.explanationType, questionExplanation.explanationType)) {
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
        result = PRIME * result + (this.explanationType == null ? 0 : this.explanationType.hashCode());
        return result;
    }

    public Question getSequenceOwner() {
        return this.sequenceOwner;
    }

    public void setSequenceOwner(Question newlyVersionedOwner) {
        this.sequenceOwner = newlyVersionedOwner;
    }

    public Integer getSequenceNumber() {
        return this.sequenceOwner.getSequenceNumber();
    }

    public void resetPersistenceState() {
        this.id = null;
    }
}
