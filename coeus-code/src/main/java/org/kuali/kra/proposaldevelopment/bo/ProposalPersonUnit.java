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

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
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
public class ProposalPersonUnit extends KcPersistableBusinessObjectBase implements CreditSplitable {

    @Id
    @Column(name = "PROPOSAL_NUMBER")
    private String proposalNumber;

    @Id
    @Column(name = "PROP_PERSON_NUMBER")
    private Integer proposalPersonNumber;

    @Id
    @Column(name = "UNIT_NUMBER")
    private String unitNumber;

    @Column(name = "LEAD_UNIT_FLAG")
    @Convert(converter = BooleanYNConverter.class)
    private boolean leadUnit;

    @ManyToOne(targetEntity = Unit.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "UNIT_NUMBER", referencedColumnName = "UNIT_NUMBER", insertable = false, updatable = false)
    private Unit unit;

    @OneToMany(targetEntity = ProposalUnitCreditSplit.class, fetch = FetchType.LAZY, orphanRemoval = true, cascade = { CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.PERSIST })

    @JoinColumns({ @JoinColumn(name = "PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER", insertable = false, updatable = false), @JoinColumn(name = "PROP_PERSON_NUMBER", referencedColumnName = "PROP_PERSON_NUMBER", insertable = false, updatable = false), @JoinColumn(name = "UNIT_NUMBER", referencedColumnName = "UNIT_NUMBER", insertable = false, updatable = false) })
    private List<ProposalUnitCreditSplit> creditSplits;

    @Transient
    private boolean delete;


    public ProposalPersonUnit() {
        creditSplits = new ArrayList<ProposalUnitCreditSplit>();
    }

    /**
     * Gets the value of creditSplits
     *
     * @return the value of creditSplits
     */
    public List<ProposalUnitCreditSplit> getCreditSplits() {
        return this.creditSplits;
    }

    /**
     * Sets the value of creditSplits
     *
     * @param argCreditSplits Value to assign to this.creditSplits
     */
    public void setCreditSplits(List<ProposalUnitCreditSplit> argCreditSplits) {
        this.creditSplits = argCreditSplits;
    }

    /**
     * Gets the value of proposalNumber
     *
     * @return the value of proposalNumber
     */
    public final String getProposalNumber() {
        return this.proposalNumber;
    }

    /**
     * Sets the value of proposalNumber
     *
     * @param argProposalNumber Value to assign to this.proposalNumber
     */
    public final void setProposalNumber(String argProposalNumber) {
        this.proposalNumber = argProposalNumber;
    }

    /**
     * Gets the value of propPersonNumber
     *
     * @return the value of propPersonNumber
     */
    public final Integer getProposalPersonNumber() {
        return this.proposalPersonNumber;
    }

    /**
     * Sets the value of propPersonNumber
     *
     * @param argPropPersonNumber Value to assign to this.propPersonNumber
     */
    public final void setProposalPersonNumber(Integer argPropPersonNumber) {
        this.proposalPersonNumber = argPropPersonNumber;
    }

    /**
     * Gets the value of unitNumber
     *
     * @return the value of unitNumber
     */
    public final String getUnitNumber() {
        return this.unitNumber;
    }

    /**
     * Sets the value of unitNumber
     *
     * @param argUnitNumber Value to assign to this.unitNumber
     */
    public final void setUnitNumber(String argUnitNumber) {
        this.unitNumber = argUnitNumber;
        if (isBlank(unitNumber)) {
            unit = null;
        } else {
            refreshReferenceObject("unit");
        }
    }

    /**
     * Gets the value of leadUnit
     *
     * @return the value of leadUnit
     */
    public final boolean isLeadUnit() {
        return this.leadUnit;
    }

    /**
     * Sets the value of leadUnit
     *
     * @param argLeadUnit Value to assign to this.leadUnit
     */
    public final void setLeadUnit(boolean argLeadUnit) {
        this.leadUnit = argLeadUnit;
    }

    /**
     * Gets a unit that this object refers to
     *
     * @return Unit
     */
    public final Unit getUnit() {
        return unit;
    }

    /**
     * Assigns a reference to a <code>{@link Unit}</code> instance
     *
     * @param u to refer to
     */
    public final void setUnit(Unit u) {
        unit = u;
    }

    /**
     * Gets index i from the creditSplits list.
     * 
     * @param index
     * @return Question at index i
     */
    public ProposalUnitCreditSplit getCreditSplit(int index) {
        while (getCreditSplits().size() <= index) {
            getCreditSplits().add(new ProposalUnitCreditSplit());
        }
        return (ProposalUnitCreditSplit) getCreditSplits().get(index);
    }

    public String getName() {
        return getUnitNumber();
    }

    /**
     * Flag to tag for deletion
     * 
     * @return true or false
     */
    public boolean isDelete() {
        return delete;
    }

    /**
     * Flag to tag for deletion
     * 
     * @param delete
     */
    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public static final class ProposalPersonUnitId implements Serializable, Comparable<ProposalPersonUnitId> {

        private String proposalNumber;

        private Integer proposalPersonNumber;

        private String unitNumber;

        public String getProposalNumber() {
            return this.proposalNumber;
        }

        public void setProposalNumber(String proposalNumber) {
            this.proposalNumber = proposalNumber;
        }

        public Integer getProposalPersonNumber() {
            return this.proposalPersonNumber;
        }

        public void setProposalPersonNumber(Integer proposalPersonNumber) {
            this.proposalPersonNumber = proposalPersonNumber;
        }

        public String getUnitNumber() {
            return this.unitNumber;
        }

        public void setUnitNumber(String unitNumber) {
            this.unitNumber = unitNumber;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("proposalNumber", this.proposalNumber).append("proposalPersonNumber", this.proposalPersonNumber).append("unitNumber", this.unitNumber).toString();
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
            return new EqualsBuilder().append(this.proposalNumber, rhs.proposalNumber).append(this.proposalPersonNumber, rhs.proposalPersonNumber).append(this.unitNumber, rhs.unitNumber).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.proposalNumber).append(this.proposalPersonNumber).append(this.unitNumber).toHashCode();
        }

        @Override
        public int compareTo(ProposalPersonUnitId other) {
            return new CompareToBuilder().append(this.proposalNumber, other.proposalNumber).append(this.proposalPersonNumber, other.proposalPersonNumber).append(this.unitNumber, other.unitNumber).toComparison();
        }
    }
}
