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
package org.kuali.coeus.propdev.impl.s2s;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "S2S_APP_ATTACHMENTS")
@IdClass(S2sAppAttachments.S2sAppAttachmentsId.class)
public class S2sAppAttachments extends KcPersistableBusinessObjectBase {

    @PortableSequenceGenerator(name = "SEQ_S2S_APP_ATTACHMENT_ID")
    @GeneratedValue(generator = "SEQ_S2S_APP_ATTACHMENT_ID")
    @Id
    @Column(name = "S2S_APP_ATTACHMENT_ID")
    private Long s2sAppAttachmentId;

    @Id
    @Column(name = "CONTENT_ID")
    private String contentId;

    @Id
    @Column(name = "PROPOSAL_NUMBER")
    private String proposalNumber;

    @Column(name = "CONTENT_TYPE")
    private String contentType;

    @Column(name = "HASH_CODE")
    private String hashCode;

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    /**
     * Gets the s2sAppAttachmentId attribute. 
     * @return Returns the s2sAppAttachmentId.
     */
    public Long getS2sAppAttachmentId() {
        return s2sAppAttachmentId;
    }

    /**
     * Sets the s2sAppAttachmentId attribute value.
     * @param appAttachmentId The s2sAppAttachmentId to set.
     */
    public void setS2sAppAttachmentId(Long appAttachmentId) {
        s2sAppAttachmentId = appAttachmentId;
    }

    public static final class S2sAppAttachmentsId implements Serializable, Comparable<S2sAppAttachmentsId> {

        private Long s2sAppAttachmentId;

        private String contentId;

        private String proposalNumber;

        public Long getS2sAppAttachmentId() {
            return this.s2sAppAttachmentId;
        }

        public void setS2sAppAttachmentId(Long s2sAppAttachmentId) {
            this.s2sAppAttachmentId = s2sAppAttachmentId;
        }

        public String getContentId() {
            return this.contentId;
        }

        public void setContentId(String contentId) {
            this.contentId = contentId;
        }

        public String getProposalNumber() {
            return this.proposalNumber;
        }

        public void setProposalNumber(String proposalNumber) {
            this.proposalNumber = proposalNumber;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("s2sAppAttachmentId", this.s2sAppAttachmentId).append("contentId", this.contentId).append("proposalNumber", this.proposalNumber).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final S2sAppAttachmentsId rhs = (S2sAppAttachmentsId) other;
            return new EqualsBuilder().append(this.s2sAppAttachmentId, rhs.s2sAppAttachmentId).append(this.contentId, rhs.contentId).append(this.proposalNumber, rhs.proposalNumber).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.s2sAppAttachmentId).append(this.contentId).append(this.proposalNumber).toHashCode();
        }

        @Override
        public int compareTo(S2sAppAttachmentsId other) {
            return new CompareToBuilder().append(this.s2sAppAttachmentId, other.s2sAppAttachmentId).append(this.contentId, other.contentId).append(this.proposalNumber, other.proposalNumber).toComparison();
        }
    }
}
