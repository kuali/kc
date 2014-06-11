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
package org.kuali.coeus.common.framework.ynq;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.common.api.ynq.YnqExplanationContract;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "YNQ_EXPLANATION")
@IdClass(YnqExplanation.YnqExplanationId.class)
public class YnqExplanation extends KcPersistableBusinessObjectBase implements YnqExplanationContract {

    @Id
    @Column(name = "EXPLANATION_TYPE")
    private String explanationType;

    @Id
    @Column(name = "QUESTION_ID")
    private String questionId;

    @Column(name = "EXPLANATION")
    @Lob
    private String explanation;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "EXPLANATION_TYPE", referencedColumnName = "EXPLANATION_TYPE", insertable = false, updatable = false)
    private YnqExplanationType ynqExplanationType;

    public String getExplanationType() {
        return explanationType;
    }

    public void setExplanationType(String explanationType) {
        this.explanationType = explanationType;
    }

    @Override
    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    @Override
    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    @Override
    public YnqExplanationType getYnqExplanationType() {
        return ynqExplanationType;
    }

    public void setYnqExplanationType(YnqExplanationType ynqExplanationType) {
        this.ynqExplanationType = ynqExplanationType;
    }

    public static final class YnqExplanationId implements Serializable, Comparable<YnqExplanationId> {

        private String explanationType;

        private String questionId;

        public String getExplanationType() {
            return this.explanationType;
        }

        public void setExplanationType(String explanationType) {
            this.explanationType = explanationType;
        }

        public String getQuestionId() {
            return this.questionId;
        }

        public void setQuestionId(String questionId) {
            this.questionId = questionId;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("explanationType", this.explanationType).append("questionId", this.questionId).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final YnqExplanationId rhs = (YnqExplanationId) other;
            return new EqualsBuilder().append(this.explanationType, rhs.explanationType).append(this.questionId, rhs.questionId).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.explanationType).append(this.questionId).toHashCode();
        }

        @Override
        public int compareTo(YnqExplanationId other) {
            return new CompareToBuilder().append(this.explanationType, other.explanationType).append(this.questionId, other.questionId).toComparison();
        }
    }
}
