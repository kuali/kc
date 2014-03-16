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
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Represents the Proposal Investigator Certification <code>{@link org.kuali.rice.krad.bo.BusinessObject}</code>
 *
 * @see org.kuali.rice.krad.bo.BusinessObject
 * @see org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument
 * @author $Author: gmcgrego $
 * @version $Revision: 1.4 $
 */
@Entity
@Table(name = "PROPOSAL_INV_CERTIFICATION")
@IdClass(ProposalInvestigatorCertification.ProposalInvestigatorCertificationId.class)
public class ProposalInvestigatorCertification extends KcPersistableBusinessObjectBase {

    @Id
    @Column(name = "PROP_PERSON_NUMBER")
    private Integer proposalPersonNumber;

    @Id
    @Column(name = "PROPOSAL_NUMBER")
    private String proposalNumber;

    @Column(name = "CERTIFIED_FLAG")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean certified;

    @Column(name = "DATE_CERTIFIED")
    private Date dateCertified;

    @Column(name = "DATE_RECEIVED_BY_OSP")
    private Date dateReceivedByOsp;

    /**
     * Gets the value of proposalPersonNumber
     *
     * @return the value of proposalPersonNumber
     */
    public final Integer getProposalPersonNumber() {
        return this.proposalPersonNumber;
    }

    /**
     * Sets the value of proposalPersonNumber
     *
     * @param argProposalPersonNumber Value to assign to this.proposalPersonNumber
     */
    public final void setProposalPersonNumber(Integer argProposalPersonNumber) {
        this.proposalPersonNumber = argProposalPersonNumber;
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
     * Gets the value of certified
     *
     * @return the value of certified
     */
    public final Boolean isCertified() {
        return this.certified;
    }

    /**
     * Sets the value of certified
     *
     * @param argCertified Value to assign to this.certified
     */
    public final void setCertified(Boolean argCertified) {
        this.certified = argCertified;
    }

    /**
     * Gets the value of dateCertified
     *
     * @return the value of dateCertified
     */
    public final Date getDateCertified() {
        return this.dateCertified;
    }

    /**
     * Sets the value of dateCertified
     *
     * @param argDateCertified Value to assign to this.dateCertified
     */
    public final void setDateCertified(Date argDateCertified) {
        this.dateCertified = argDateCertified;
    }

    /**
     * Gets the value of dateReceivedByOsp
     *
     * @return the value of dateReceivedByOsp
     */
    public final Date getDateReceivedByOsp() {
        return this.dateReceivedByOsp;
    }

    /**
     * Sets the value of dateReceivedByOsp
     *
     * @param argDateReceivedByOsp Value to assign to this.dateReceivedByOsp
     */
    public final void setDateReceivedByOsp(Date argDateReceivedByOsp) {
        this.dateReceivedByOsp = argDateReceivedByOsp;
    }

    public static final class ProposalInvestigatorCertificationId implements Serializable, Comparable<ProposalInvestigatorCertificationId> {

        private Integer proposalPersonNumber;

        private String proposalNumber;

        public Integer getProposalPersonNumber() {
            return this.proposalPersonNumber;
        }

        public void setProposalPersonNumber(Integer proposalPersonNumber) {
            this.proposalPersonNumber = proposalPersonNumber;
        }

        public String getProposalNumber() {
            return this.proposalNumber;
        }

        public void setProposalNumber(String proposalNumber) {
            this.proposalNumber = proposalNumber;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("proposalPersonNumber", this.proposalPersonNumber).append("proposalNumber", this.proposalNumber).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final ProposalInvestigatorCertificationId rhs = (ProposalInvestigatorCertificationId) other;
            return new EqualsBuilder().append(this.proposalPersonNumber, rhs.proposalPersonNumber).append(this.proposalNumber, rhs.proposalNumber).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.proposalPersonNumber).append(this.proposalNumber).toHashCode();
        }

        @Override
        public int compareTo(ProposalInvestigatorCertificationId other) {
            return new CompareToBuilder().append(this.proposalPersonNumber, other.proposalPersonNumber).append(this.proposalNumber, other.proposalNumber).toComparison();
        }
    }
}
