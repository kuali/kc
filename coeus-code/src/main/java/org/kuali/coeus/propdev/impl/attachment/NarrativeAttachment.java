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
package org.kuali.coeus.propdev.impl.attachment;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.coeus.propdev.api.attachment.NarrativeAttachmentContract;

@Entity
@Table(name = "NARRATIVE_ATTACHMENT")
@AttributeOverride(name = "data",  column = @Column(name = "NARRATIVE_DATA"))
@IdClass(NarrativeAttachment.NarrativeAttachmentId.class)
public class NarrativeAttachment extends AttachmentDataSource implements NarrativeAttachmentContract {

    @Id
    @Column(name = "MODULE_NUMBER")
    private Integer moduleNumber;

    @Id
    @Column(name = "PROPOSAL_NUMBER")
    private String proposalNumber;

    @OneToOne(cascade = { CascadeType.REFRESH })
    @JoinColumns({ @JoinColumn(name = "PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER", insertable = false, updatable = false), @JoinColumn(name = "MODULE_NUMBER", referencedColumnName = "MODULE_NUMBER", insertable = false, updatable = false) })
    private Narrative narrative;

    @Override
    public Integer getModuleNumber() {
        return moduleNumber;
    }

    public void setModuleNumber(Integer moduleNumber) {
        this.moduleNumber = moduleNumber;
    }

    @Override
    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public Narrative getNarrative() {
        return narrative;
    }

    public void setNarrative(Narrative narrative) {
        this.narrative = narrative;
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
