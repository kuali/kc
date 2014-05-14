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
package org.kuali.coeus.propdev.impl.person.creditsplit;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.common.framework.type.InvestigatorCreditType;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
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
public final class ProposalUnitCreditSplit extends KcPersistableBusinessObjectBase implements CreditSplit {

    @Id
    @ManyToOne
    @JoinColumns({ @JoinColumn(name = "PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER"), @JoinColumn(name = "PROP_PERSON_NUMBER", referencedColumnName = "PROP_PERSON_NUMBER") })
    private ProposalPerson proposalPerson;

    @Id
    @Column(name = "UNIT_NUMBER")
    private String unitNumber;

    @Id
    @Column(name = "INV_CREDIT_TYPE_CODE")
    private String invCreditTypeCode;

    @Column(name = "CREDIT")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal credit;

    @Transient
    private Unit unit;

    @ManyToOne(targetEntity = InvestigatorCreditType.class)
    @JoinColumn(name = "INV_CREDIT_TYPE_CODE", referencedColumnName = "INV_CREDIT_TYPE_CODE", insertable = false, updatable = false)
    private InvestigatorCreditType investigatorCreditType;

    /**
     * Gets the value of invCreditType
     *
     * @return the value of invCreditType
     */
    public InvestigatorCreditType getInvestigatorCreditType() {
        return this.investigatorCreditType;
    }

    /**
     * Sets the value of invCreditType
     *
     * @param argInvCreditType Value to assign to this.invCreditType
     */
    public void setInvestigatorCreditType(InvestigatorCreditType argInvCreditType) {
        this.investigatorCreditType = argInvCreditType;
    }

    /**
     * Gets the value of unitNumber
     *
     * @return the value of unitNumber
     */
    public String getUnitNumber() {
        return this.unitNumber;
    }

    /**
     * Sets the value of unitNumber
     *
     * @param argUnitNumber Value to assign to this.unitNumber
     */
    public void setUnitNumber(String argUnitNumber) {
        this.unitNumber = argUnitNumber;
    }

    /**
     * Gets the value of invCreditTypeCode
     *
     * @return the value of invCreditTypeCode
     */
    public String getInvCreditTypeCode() {
        return this.invCreditTypeCode;
    }

    /**
     * Sets the value of invCreditTypeCode
     *
     * @param argInvCreditTypeCode Value to assign to this.invCreditTypeCode
     */
    public void setInvCreditTypeCode(String argInvCreditTypeCode) {
        this.invCreditTypeCode = argInvCreditTypeCode;
    }

    /**
     * Gets the value of credit
     *
     * @return the value of credit
     */
    public ScaleTwoDecimal getCredit() {
        return this.credit == null ? new ScaleTwoDecimal(0) : this.credit;
    }

    /**
     * Sets the value of credit
     *
     * @param argCredit Value to assign to this.credit
     */
    public void setCredit(ScaleTwoDecimal argCredit) {
        this.credit = argCredit;
    }

    /**
     * Sets the value of unit
     *
     * @param argUnit Value to assign to this.unit
     */
    public void setUnit(Unit argUnit) {
        this.unit = argUnit;
    }

    public Unit getUnit() {
        return unit;
    }

    public static final class ProposalUnitCreditSplitId implements Serializable, Comparable<ProposalUnitCreditSplitId> {

        private ProposalPerson.ProposalPersonId proposalPerson;

        private String invCreditTypeCode;

        private String unitNumber;
        
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

        public String getUnitNumber() {
            return this.unitNumber;
        }

        public void setUnitNumber(String unitNumber) {
            this.unitNumber = unitNumber;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("proposalPerson", this.proposalPerson).append("invCreditTypeCode", this.invCreditTypeCode).append("unitNumber", this.unitNumber).toString();
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
            return new EqualsBuilder().append(this.proposalPerson, rhs.proposalPerson).append(this.invCreditTypeCode, rhs.invCreditTypeCode).append(this.unitNumber, rhs.unitNumber).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.proposalPerson).append(this.invCreditTypeCode).append(this.unitNumber).toHashCode();
        }

        @Override
        public int compareTo(ProposalUnitCreditSplitId other) {
            return new CompareToBuilder().append(this.proposalPerson, other.proposalPerson).append(this.invCreditTypeCode, other.invCreditTypeCode).append(this.unitNumber, other.unitNumber).toComparison();
        }
    }

	public ProposalPerson getProposalPerson() {
		return proposalPerson;
	}

	public void setProposalPerson(ProposalPerson proposalPerson) {
		this.proposalPerson = proposalPerson;
	}
}
