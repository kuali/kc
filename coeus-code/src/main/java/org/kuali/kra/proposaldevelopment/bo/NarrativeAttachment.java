/*
 * Copyright 2005-2013 The Kuali Foundation
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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "NARRATIVE_ATTACHMENT")
@IdClass(NarrativeAttachment.NarrativeAttachmentId.class)
public class NarrativeAttachment extends AttachmentDataSource {

    @Id
    @Column(name = "MODULE_NUMBER")
    private Integer moduleNumber;

    @Id
    @Column(name = "PROPOSAL_NUMBER")
    private String proposalNumber;

    //	private String fileName;  
    //	private String contentType;  
    @Column(name = "NARRATIVE_DATA")
    private byte[] narrativeData;

    public Integer getModuleNumber() {
        return moduleNumber;
    }

    public void setModuleNumber(Integer moduleNumber) {
        this.moduleNumber = moduleNumber;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public byte[] getNarrativeData() {
        return narrativeData;
    }

    public void setNarrativeData(byte[] narrativePdf) {
        this.narrativeData = narrativePdf;
    }

    public byte[] getContent() {
        return narrativeData;
    }

    public static final class NarrativeAttachmentId implements Serializable, Comparable<NarrativeAttachmentId> {

        private String proposalNumber;

        private Integer moduleNumber;

        public String getProposalNumber() {
            return this.proposalNumber;
        }

        public void setProposalNumber(String proposalNumber) {
            this.proposalNumber = proposalNumber;
        }

        public Integer getModuleNumber() {
            return this.moduleNumber;
        }

        public void setModuleNumber(Integer moduleNumber) {
            this.moduleNumber = moduleNumber;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("proposalNumber", this.proposalNumber).append("moduleNumber", this.moduleNumber).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final NarrativeAttachmentId rhs = (NarrativeAttachmentId) other;
            return new EqualsBuilder().append(this.proposalNumber, rhs.proposalNumber).append(this.moduleNumber, rhs.moduleNumber).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.proposalNumber).append(this.moduleNumber).toHashCode();
        }

        @Override
        public int compareTo(NarrativeAttachmentId other) {
            return new CompareToBuilder().append(this.proposalNumber, other.proposalNumber).append(this.moduleNumber, other.moduleNumber).toComparison();
        }
    }
}
