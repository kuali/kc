/*
 * Copyright 2005-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the License);
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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 
 * This bo for eps_prop_person_bio_attachment
 */
@Entity
@Table(name = "EPS_PROP_PERSON_BIO_ATTACHMENT")
@IdClass(ProposalPersonBiographyAttachment.ProposalPersonBiographyAttachmentId.class)
public class ProposalPersonBiographyAttachment extends AttachmentDataSource {

    @Id
    @Column(name = "PROP_PERSON_NUMBER")
    private Integer proposalPersonNumber;

    @Id
    @Column(name = "PROPOSAL_NUMBER")
    private String proposalNumber;

    @Id
    @Column(name = "BIO_NUMBER")
    private Integer biographyNumber;

    @Column(name = "BIO_DATA")
    private byte[] biographyData;

    //	private String fileName;  
    //    private String contentType;  
    public ProposalPersonBiographyAttachment() {
        super();
    }

    public Integer getProposalPersonNumber() {
        return proposalPersonNumber;
    }

    public void setProposalPersonNumber(Integer proposalPersonNumber) {
        this.proposalPersonNumber = proposalPersonNumber;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public byte[] getBiographyData() {
        return biographyData;
    }

    public void setBiographyData(byte[] biographyData) {
        this.biographyData = biographyData;
    }

    @Override
    public byte[] getContent() {
        return biographyData;
    }

    public Integer getBiographyNumber() {
        return biographyNumber;
    }

    public void setBiographyNumber(Integer biographyNumber) {
        this.biographyNumber = biographyNumber;
    }

    public static final class ProposalPersonBiographyAttachmentId implements Serializable, Comparable<ProposalPersonBiographyAttachmentId> {

        private Integer proposalPersonNumber;

        private Integer biographyNumber;

        private String proposalNumber;

        public Integer getProposalPersonNumber() {
            return this.proposalPersonNumber;
        }

        public void setProposalPersonNumber(Integer proposalPersonNumber) {
            this.proposalPersonNumber = proposalPersonNumber;
        }

        public Integer getBiographyNumber() {
            return this.biographyNumber;
        }

        public void setBiographyNumber(Integer biographyNumber) {
            this.biographyNumber = biographyNumber;
        }

        public String getProposalNumber() {
            return this.proposalNumber;
        }

        public void setProposalNumber(String proposalNumber) {
            this.proposalNumber = proposalNumber;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("proposalPersonNumber", this.proposalPersonNumber).append("biographyNumber", this.biographyNumber).append("proposalNumber", this.proposalNumber).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final ProposalPersonBiographyAttachmentId rhs = (ProposalPersonBiographyAttachmentId) other;
            return new EqualsBuilder().append(this.proposalPersonNumber, rhs.proposalPersonNumber).append(this.biographyNumber, rhs.biographyNumber).append(this.proposalNumber, rhs.proposalNumber).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.proposalPersonNumber).append(this.biographyNumber).append(this.proposalNumber).toHashCode();
        }

        @Override
        public int compareTo(ProposalPersonBiographyAttachmentId other) {
            return new CompareToBuilder().append(this.proposalPersonNumber, other.proposalPersonNumber).append(this.biographyNumber, other.biographyNumber).append(this.proposalNumber, other.proposalNumber).toComparison();
        }
    }
}
