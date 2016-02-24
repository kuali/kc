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
package org.kuali.coeus.propdev.impl.s2s;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.propdev.api.s2s.S2sAppAttachmentsContract;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "S2S_APP_ATTACHMENTS")
@IdClass(S2sAppAttachments.S2sAppAttachmentsId.class)
public class S2sAppAttachments extends KcPersistableBusinessObjectBase implements S2sAppAttachmentsContract {

    @PortableSequenceGenerator(name = "SEQ_S2S_APP_ATTACHMENT_ID")
    @GeneratedValue(generator = "SEQ_S2S_APP_ATTACHMENT_ID")
    @Id
    @Column(name = "S2S_APP_ATTACHMENT_ID")
    private Long id;

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

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long appAttachmentId) {
        id = appAttachmentId;
    }

    @Override
    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    @Override
    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public static final class S2sAppAttachmentsId implements Serializable, Comparable<S2sAppAttachmentsId> {

        private Long id;

        private String contentId;

        private String proposalNumber;

        public Long getId() {
            return this.id;
        }

        public void setId(Long id) {
            this.id = id;
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
            return new ToStringBuilder(this).append("id", this.id).append("contentId", this.contentId).append("proposalNumber", this.proposalNumber).toString();
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
            return new EqualsBuilder().append(this.id, rhs.id).append(this.contentId, rhs.contentId).append(this.proposalNumber, rhs.proposalNumber).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.id).append(this.contentId).append(this.proposalNumber).toHashCode();
        }

        @Override
        public int compareTo(S2sAppAttachmentsId other) {
            return new CompareToBuilder().append(this.id, other.id).append(this.contentId, other.contentId).append(this.proposalNumber, other.proposalNumber).toComparison();
        }
    }
}
