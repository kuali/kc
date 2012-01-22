/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.questionnaire.question;

import org.apache.commons.lang.ObjectUtils;
import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class QuestionExplanation extends KraPersistableBusinessObjectBase implements SequenceAssociate<Question> {

    private static final long serialVersionUID = 1L;

    private Long questionExplanationId;

    private Long questionRefIdFk;

    private String explanationType;

    private String explanation;

    private Question sequenceOwner;

    public QuestionExplanation() {
    }

    public Long getQuestionExplanationId() {
        return questionExplanationId;
    }

    public void setQuestionExplanationId(Long questionExplanationId) {
        this.questionExplanationId = questionExplanationId;
    }

    public Long getQuestionRefIdFk() {
        return questionRefIdFk;
    }

    public void setQuestionRefIdFk(Long questionRefIdFk) {
        this.questionRefIdFk = questionRefIdFk;
    }

    public String getExplanationType() {
        return explanationType;
    }

    public void setExplanationType(String explanationType) {
        this.explanationType = explanationType;
    }

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
        if (ObjectUtils.equals(this.questionRefIdFk, questionExplanation.questionRefIdFk) && ObjectUtils.equals(this.explanationType, questionExplanation.explanationType)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + (this.questionRefIdFk == null ? 0 : this.questionRefIdFk.hashCode());
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
        this.questionExplanationId = null;
    }
}
