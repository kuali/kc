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

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.propdev.api.person.ProposalPersonUnitContract;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.propdev.impl.person.creditsplit.CreditSplitable;
import org.kuali.coeus.propdev.impl.person.creditsplit.ProposalUnitCreditSplit;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Represents the Proposal Person Unit <code>{@link org.kuali.rice.krad.bo.BusinessObject}</code>
 *
 * @see org.kuali.rice.krad.bo.BusinessObject
 * @see org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument
 * @author $Author: gmcgrego $
 * @version $Revision: 1.14 $
 */
@Entity
@Table(name = "EPS_PROP_PERSON_UNITS")
@IdClass(ProposalPersonUnit.ProposalPersonUnitId.class)
public class ProposalPersonUnit extends KcPersistableBusinessObjectBase implements CreditSplitable, ProposalPersonUnitContract {

    @Id
    @ManyToOne
    @JoinColumns({ @JoinColumn(name = "PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER"), @JoinColumn(name = "PROP_PERSON_NUMBER", referencedColumnName = "PROP_PERSON_NUMBER") })
    private ProposalPerson proposalPerson;

    @Id
    @Column(name = "UNIT_NUMBER")
    private String unitNumber;

    @Column(name = "LEAD_UNIT_FLAG")
    @Convert(converter = BooleanYNConverter.class)
    private boolean leadUnit;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "UNIT_NUMBER", referencedColumnName = "UNIT_NUMBER", insertable = false, updatable = false)
    private Unit unit;

    @OneToMany(mappedBy="proposalPersonUnit", orphanRemoval = true, cascade = { CascadeType.ALL })
    private List<ProposalUnitCreditSplit> creditSplits;

    @Transient
    private boolean delete;


    public ProposalPersonUnit() {
        creditSplits = new ArrayList<ProposalUnitCreditSplit>();
    }

    @Override
    public List<ProposalUnitCreditSplit> getCreditSplits() {
        return this.creditSplits;
    }

    public void setCreditSplits(List<ProposalUnitCreditSplit> argCreditSplits) {
        this.creditSplits = argCreditSplits;
    }

    @Override
    public final String getUnitNumber() {
        return this.unitNumber;
    }

    public final void setUnitNumber(String argUnitNumber) {
        this.unitNumber = argUnitNumber;
        if (isBlank(unitNumber)) {
            unit = null;
        } else {
            refreshReferenceObject("unit");
        }
    }

    @Override
    public final boolean isLeadUnit() {
        return this.leadUnit;
    }

    public final void setLeadUnit(boolean argLeadUnit) {
        this.leadUnit = argLeadUnit;
    }

    public final Unit getUnit() {
        return unit;
    }

    public final void setUnit(Unit u) {
        unit = u;
    }

    public ProposalUnitCreditSplit getCreditSplit(int index) {
        while (getCreditSplits().size() <= index) {
            getCreditSplits().add(new ProposalUnitCreditSplit());
        }
        return (ProposalUnitCreditSplit) getCreditSplits().get(index);
    }

    public String getName() {
        return getUnitNumber();
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    @Override
    public String getProposalNumber() {
        return getProposalPerson().getDevelopmentProposal().getProposalNumber();
    }

    @Override
    public Integer getProposalPersonNumber() {
        return getProposalPerson().getProposalPersonNumber();
    }

    public static final class ProposalPersonUnitId implements Serializable, Comparable<ProposalPersonUnitId> {

        private ProposalPerson.ProposalPersonId proposalPerson;

        private String unitNumber;

        public ProposalPerson.ProposalPersonId getProposalPerson() {
			return proposalPerson;
		}

		public void setProposalPerson(ProposalPerson.ProposalPersonId proposalPerson) {
			this.proposalPerson = proposalPerson;
		}

        public String getUnitNumber() {
            return this.unitNumber;
        }

        public void setUnitNumber(String unitNumber) {
            this.unitNumber = unitNumber;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("proposalPerson", this.proposalPerson).append("unitNumber", this.unitNumber).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final ProposalPersonUnitId rhs = (ProposalPersonUnitId) other;
            return new EqualsBuilder().append(this.proposalPerson, rhs.proposalPerson).append(this.unitNumber, rhs.unitNumber).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.proposalPerson).append(this.unitNumber).toHashCode();
        }

        @Override
        public int compareTo(ProposalPersonUnitId other) {
            return new CompareToBuilder().append(this.proposalPerson, other.proposalPerson).append(this.unitNumber, other.unitNumber).toComparison();
        }
    }

	public ProposalPerson getProposalPerson() {
		return proposalPerson;
	}

	public void setProposalPerson(ProposalPerson proposalPerson) {
		this.proposalPerson = proposalPerson;
	}
}
