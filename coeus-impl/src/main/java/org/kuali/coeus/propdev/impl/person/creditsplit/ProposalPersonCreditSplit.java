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
package org.kuali.coeus.propdev.impl.person.creditsplit;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.common.framework.type.InvestigatorCreditType;
import org.kuali.coeus.propdev.api.person.creditsplit.ProposalPersonCreditSplitContract;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.persistence.ScaleTwoDecimalConverter;

import javax.persistence.*;

import java.io.Serializable;

/**
 * Class representation of the Proposal Person <code>{@link org.kuali.rice.krad.bo.BusinessObject}</code>
 *
 * @author $Author: vsoni $
 * @version $Revision: 1.9 $
 */
@Entity
@Table(name = "EPS_PROP_PER_CREDIT_SPLIT")
@IdClass(ProposalPersonCreditSplit.ProposalPersonCreditSplitId.class)
public final class ProposalPersonCreditSplit extends KcPersistableBusinessObjectBase implements CreditSplit, ProposalPersonCreditSplitContract {

    @Id
    @ManyToOne
    @JoinColumns({ @JoinColumn(name = "PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER"), @JoinColumn(name = "PROP_PERSON_NUMBER", referencedColumnName = "PROP_PERSON_NUMBER") })
    private ProposalPerson proposalPerson;

    @Id
    @Column(name = "INV_CREDIT_TYPE_CODE")
    private String invCreditTypeCode;

    @Column(name = "CREDIT")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal credit;

    @ManyToOne
    @JoinColumn(name = "INV_CREDIT_TYPE_CODE", referencedColumnName = "INV_CREDIT_TYPE_CODE", insertable = false, updatable = false)
    private InvestigatorCreditType investigatorCreditType;

    @Override
    public InvestigatorCreditType getInvestigatorCreditType() {
        return this.investigatorCreditType;
    }

    public void setInvestigatorCreditType(InvestigatorCreditType argInvCreditType) {
        this.investigatorCreditType = argInvCreditType;
    }

    @Override
    public String getProposalNumber() {
        return getProposalPerson().getDevelopmentProposal().getProposalNumber();
    }

    @Override
    public Integer getProposalPersonNumber() {
        return getProposalPerson().getProposalPersonNumber();
    }

    public String getInvCreditTypeCode() {
        return this.invCreditTypeCode;
    }

    public void setInvCreditTypeCode(String argInvCreditTypeCode) {
        this.invCreditTypeCode = argInvCreditTypeCode;
    }

    @Override
    public ScaleTwoDecimal getCredit() {
        return this.credit == null ? new ScaleTwoDecimal(0) : this.credit;
    }

    public void setCredit(ScaleTwoDecimal argCredit) {
        this.credit = argCredit;
    }

    public static final class ProposalPersonCreditSplitId implements Serializable, Comparable<ProposalPersonCreditSplitId> {

        private ProposalPerson.ProposalPersonId proposalPerson;

        private String invCreditTypeCode;

        public ProposalPerson.ProposalPersonId getProposalPerson() {
			return proposalPerson;
		}

		public void setProposalPerson(ProposalPerson.ProposalPersonId proposalPerson) {
			this.proposalPerson = proposalPerson;
		}

        public String getInvCreditTypeCode() {
            return this.invCreditTypeCode;
        }

        public void setInvCreditTypeCode(String invCreditTypeCode) {
            this.invCreditTypeCode = invCreditTypeCode;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("proposalPerson", this.proposalPerson).append("invCreditTypeCode", this.invCreditTypeCode).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final ProposalPersonCreditSplitId rhs = (ProposalPersonCreditSplitId) other;
            return new EqualsBuilder().append(this.proposalPerson, rhs.proposalPerson).append(this.invCreditTypeCode, rhs.invCreditTypeCode).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.proposalPerson).append(this.invCreditTypeCode).toHashCode();
        }

        @Override
        public int compareTo(ProposalPersonCreditSplitId other) {
            return new CompareToBuilder().append(this.proposalPerson, other.proposalPerson).append(this.invCreditTypeCode, other.invCreditTypeCode).toComparison();
        }
    }

	public ProposalPerson getProposalPerson() {
		return proposalPerson;
	}

	public void setProposalPerson(ProposalPerson proposalPerson) {
		this.proposalPerson = proposalPerson;
	}
}
