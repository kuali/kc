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
package org.kuali.coeus.propdev.impl.person;

import java.io.Serializable;
import javax.persistence.*;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.common.framework.ynq.Ynq;
import org.kuali.coeus.propdev.api.person.ProposalPersonYnqContract;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

@Entity
@Table(name = "EPS_PROP_PERS_YNQ")
@IdClass(ProposalPersonYnq.ProposalPersonYnqId.class)
public class ProposalPersonYnq extends KcPersistableBusinessObjectBase implements ProposalPersonYnqContract {

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

    @Override
    public String getProposalNumber() {
        return getProposalPerson().getDevelopmentProposal().getProposalNumber();
    }

    @Override
    public Integer getProposalPersonNumber() {
        return getProposalPerson().getProposalPersonNumber();
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
