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
package org.kuali.kra.proposaldevelopment.bo;

import java.io.Serializable;
import javax.persistence.*;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.bo.Ynq;

@Entity
@Table(name = "EPS_PROP_PERS_YNQ")
@IdClass(ProposalPersonYnq.ProposalPersonYnqId.class)
public class ProposalPersonYnq extends KcPersistableBusinessObjectBase{

	private static final long serialVersionUID = 5035080261199085203L;

    @Id
    @ManyToOne
    @JoinColumns({ @JoinColumn(name = "PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER"), @JoinColumn(name = "PROP_PERSON_NUMBER", referencedColumnName = "PROP_PERSON_NUMBER") })
    private ProposalPerson proposalPerson;

    @Id
    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "QUESTION_ID", referencedColumnName = "QUESTION_ID")
    private Ynq ynq;    

    @Column(name = "QUESTION_ID", insertable = false, updatable = false)
    private String questionId;

    @Column(name = "ANSWER")
    private String answer;

    @Transient
    private String dummyAnswer;

    public ProposalPersonYnq() {
        super();
    }
    
    public ProposalPerson getProposalPerson() {
		return proposalPerson;
	}

	public void setProposalPerson(ProposalPerson proposalPerson) {
		this.proposalPerson = proposalPerson;
	}

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Ynq getYnq() {
        return ynq;
    }

    public void setYnq(Ynq ynq) {
        this.ynq = ynq;
    }

    public String getDummyAnswer() {
        return dummyAnswer;
    }

    public void setDummyAnswer(String dummyAnswer) {
        this.dummyAnswer = dummyAnswer;
    }

    public static final class ProposalPersonYnqId implements Serializable, Comparable<ProposalPersonYnqId> {

        private ProposalPerson.ProposalPersonId proposalPerson;

        public ProposalPerson.ProposalPersonId getProposalPerson() {
			return proposalPerson;
		}

		public void setProposalPerson(ProposalPerson.ProposalPersonId proposalPerson) {
			this.proposalPerson = proposalPerson;
		}

		private String ynq;

        public String getYnq() {
            return this.ynq;
        }

        public void setYnq(String ynq) {
            this.ynq = ynq;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("proposalPerson", proposalPerson.toString()).append("ynq", this.ynq).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final ProposalPersonYnqId rhs = (ProposalPersonYnqId) other;
            return new EqualsBuilder().append(this.proposalPerson, rhs.proposalPerson).append(this.ynq, rhs.ynq).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.proposalPerson).append(this.ynq).toHashCode();
        }

        @Override
        public int compareTo(ProposalPersonYnqId other) {
            return new CompareToBuilder().append(this.proposalPerson, other.proposalPerson).append(this.ynq, other.ynq).toComparison();
        }
    }
}
