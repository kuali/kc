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
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.propdev.api.person.creditsplit.ProposalUnitCreditSplitContract;
import org.kuali.coeus.propdev.impl.person.ProposalPersonUnit;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.persistence.ScaleTwoDecimalConverter;

import javax.persistence.*;

import java.io.Serializable;

/**
 * Class representation of the Proposal Person <code>{@link org.kuali.rice.krad.bo.BusinessObject}</code>
 *
 * @author $Id: ProposalUnitCreditSplit.java,v 1.8 2008-07-28 14:48:12 vsoni Exp $
 * @version $Revision: 1.8 $
 */
@Entity
@Table(name = "EPS_PROP_UNIT_CREDIT_SPLIT")
@IdClass(ProposalUnitCreditSplit.ProposalUnitCreditSplitId.class)
public final class ProposalUnitCreditSplit extends KcPersistableBusinessObjectBase implements CreditSplit, ProposalUnitCreditSplitContract {

    @Id
    @ManyToOne
    @JoinColumns({ @JoinColumn(name = "PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER"), @JoinColumn(name = "PROP_PERSON_NUMBER", referencedColumnName = "PROP_PERSON_NUMBER"), @JoinColumn(name = "UNIT_NUMBER", referencedColumnName = "UNIT_NUMBER") })
    private ProposalPersonUnit proposalPersonUnit;

    @Id
    @Column(name = "INV_CREDIT_TYPE_CODE")
    private String invCreditTypeCode;

    @Column(name = "CREDIT")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal credit;

    @Transient
    private Unit unit;

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

    public String getInvCreditTypeCode() {
        return this.invCreditTypeCode;
    }

    @Override
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

    public void setUnit(Unit argUnit) {
        this.unit = argUnit;
    }

    public Unit getUnit() {
        return unit;
    }

    @Override
    public String getProposalNumber() {
        return getProposalPersonUnit().getProposalPerson().getDevelopmentProposal().getProposalNumber();
    }

    @Override
    public Integer getProposalPersonNumber() {
        return getProposalPersonUnit().getProposalPersonNumber();
    }

    @Override
    public String getUnitNumber() {
        return getProposalPersonUnit().getUnitNumber();
    }

    public static final class ProposalUnitCreditSplitId implements Serializable, Comparable<ProposalUnitCreditSplitId> {

        private ProposalPersonUnit.ProposalPersonUnitId proposalPersonUnit;

        private String invCreditTypeCode;
        
        public ProposalPersonUnit.ProposalPersonUnitId getProposalPersonUnit() {
			return proposalPersonUnit;
		}

		public void setProposalPersonUnit(ProposalPersonUnit.ProposalPersonUnitId proposalPersonUnit) {
			this.proposalPersonUnit = proposalPersonUnit;
		}

        public String getInvCreditTypeCode() {
            return this.invCreditTypeCode;
        }

        public void setInvCreditTypeCode(String invCreditTypeCode) {
            this.invCreditTypeCode = invCreditTypeCode;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("proposalPersonUnit", this.proposalPersonUnit).append("invCreditTypeCode", this.invCreditTypeCode).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final ProposalUnitCreditSplitId rhs = (ProposalUnitCreditSplitId) other;
            return new EqualsBuilder().append(this.proposalPersonUnit, rhs.proposalPersonUnit).append(this.invCreditTypeCode, rhs.invCreditTypeCode).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.proposalPersonUnit).append(this.invCreditTypeCode).toHashCode();
        }

        @Override
        public int compareTo(ProposalUnitCreditSplitId other) {
            return new CompareToBuilder().append(this.proposalPersonUnit, other.proposalPersonUnit).append(this.invCreditTypeCode, other.invCreditTypeCode).toComparison();
        }
    }

	public ProposalPersonUnit getProposalPersonUnit() {
		return proposalPersonUnit;
	}

	public void setProposalPersonUnit(ProposalPersonUnit proposalPersonUnit) {
		this.proposalPersonUnit = proposalPersonUnit;
	}
}
