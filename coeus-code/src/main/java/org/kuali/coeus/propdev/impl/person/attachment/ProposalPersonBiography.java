/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.person.attachment;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.struts.upload.FormFile;
import org.kuali.coeus.propdev.api.person.attachment.ProposalPersonBiographyContract;
import org.kuali.coeus.sys.api.model.KcFile;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * This is bo for eps_prop_person_bio.
 */
@Entity
@Table(name = "EPS_PROP_PERSON_BIO")
@IdClass(ProposalPersonBiography.ProposalPersonBiographyId.class)
public class ProposalPersonBiography extends KcPersistableBusinessObjectBase implements ProposalPersonBiographyContract, KcFile {

    @Id
    @Column(name = "PROP_PERSON_NUMBER")
    private Integer proposalPersonNumber;

    @Column(name = "PERSON_ID")
    private String personId;

    @Id
    @Column(name = "PROPOSAL_NUMBER")
    private String proposalNumber;

    @Id
    @Column(name = "BIO_NUMBER")
    private Integer biographyNumber;

    @Column(name = "ROLODEX_ID")
    private Integer rolodexId;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DOCUMENT_TYPE_CODE")
    private String documentTypeCode;

    @Column(name = "FILE_NAME")
    private String name;

    @Column(name = "CONTENT_TYPE")
    private String type;

    @ManyToOne(targetEntity = PropPerDocType.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "DOCUMENT_TYPE_CODE", referencedColumnName = "DOCUMENT_TYPE_CODE", insertable = false, updatable = false)
    private PropPerDocType propPerDocType;

    @OneToOne(mappedBy = "proposalPersonBiography", cascade = CascadeType.ALL)
    private ProposalPersonBiographyAttachment personnelAttachment;

    @Transient
    private transient FormFile personnelAttachmentFile;

    @Transient
    private Timestamp timestampDisplay;

    @Transient
    private String uploadUserDisplay;

    @Transient
    private String uploadUserFullName;

    @Transient
    private transient int positionNumber;

    @Override
    public Integer getProposalPersonNumber() {
        return proposalPersonNumber;
    }

    public void setProposalPersonNumber(Integer proposalPersonNumber) {
        this.proposalPersonNumber = proposalPersonNumber;
    }

    @Override
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Override
    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDocumentTypeCode() {
        return documentTypeCode;
    }

    public void setDocumentTypeCode(String documentTypeCode) {
        this.documentTypeCode = documentTypeCode;
    }

    public FormFile getPersonnelAttachmentFile() {
        return personnelAttachmentFile;
    }

    public void setPersonnelAttachmentFile(FormFile personnelAttachmentFile) {
        this.personnelAttachmentFile = personnelAttachmentFile;
    }

    @Override
    public ProposalPersonBiographyAttachment getPersonnelAttachment() {
        return personnelAttachment;
    }

    public void setPersonnelAttachment(ProposalPersonBiographyAttachment personnelAttachment) {
        this.personnelAttachment = personnelAttachment;
    }

    @Override
    public PropPerDocType getPropPerDocType() {
        return propPerDocType;
    }

    public void setPropPerDocType(PropPerDocType propPerDocType) {
        this.propPerDocType = propPerDocType;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getRolodexId() {
        return rolodexId;
    }

    public void setRolodexId(Integer rolodexId) {
        this.rolodexId = rolodexId;
    }

    @Override
    public Integer getBiographyNumber() {
        return biographyNumber;
    }

    public void setBiographyNumber(Integer biographyNumber) {
        this.biographyNumber = biographyNumber;
    }

    public Timestamp getTimestampDisplay() {
        return timestampDisplay;
    }

    public void setTimestampDisplay(Timestamp timestampDisplay) {
        this.timestampDisplay = timestampDisplay;
    }

    public String getUploadUserDisplay() {
        return uploadUserDisplay;
    }

    public void setUploadUserDisplay(String uploadUserDisplay) {
        this.uploadUserDisplay = uploadUserDisplay;
    }

    public String getUploadUserFullName() {
        return uploadUserFullName;
    }

    public void setUploadUserFullName(String uploadUserFullName) {
        this.uploadUserFullName = uploadUserFullName;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        if (getPersonnelAttachment() == null) {
            return null;
        } else {
            return getPersonnelAttachment().getData();
        }
    }

    public int getPositionNumber() {
        return positionNumber;
    }

    public void setPositionNumber(int positionNumber) {
        this.positionNumber = positionNumber;
    }

    public void populateAttachment() {
        FormFile personnelFile = getPersonnelAttachmentFile();
        if (personnelFile == null)
            return;
        byte[] personnellFileData;
        try {
            personnellFileData = personnelFile.getFileData();
            if (personnellFileData.length > 0) {
                ProposalPersonBiographyAttachment personnelAttachment = getPersonnelAttachment();
                if (personnelAttachment == null) {
                    personnelAttachment = new ProposalPersonBiographyAttachment();
                    setPersonnelAttachment(personnelAttachment);
                }
                String fileName = personnelFile.getFileName();
                personnelAttachment.setName(fileName);
                personnelAttachment.setType(personnelFile.getContentType());
                personnelAttachment.setData(personnelFile.getFileData());
                personnelAttachment.setProposalNumber(getProposalNumber());
                setName(personnelAttachment.getName());
                setType(personnelAttachment.getType());
            } else {
                setPersonnelAttachment(null);
            }
        } catch (IOException e) {
            setPersonnelAttachment(null);
        }
    }

    public static final class ProposalPersonBiographyId implements Serializable, Comparable<ProposalPersonBiographyId> {

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
            final ProposalPersonBiographyId rhs = (ProposalPersonBiographyId) other;
            return new EqualsBuilder().append(this.proposalPersonNumber, rhs.proposalPersonNumber).append(this.biographyNumber, rhs.biographyNumber).append(this.proposalNumber, rhs.proposalNumber).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.proposalPersonNumber).append(this.biographyNumber).append(this.proposalNumber).toHashCode();
        }

        @Override
        public int compareTo(ProposalPersonBiographyId other) {
            return new CompareToBuilder().append(this.proposalPersonNumber, other.proposalPersonNumber).append(this.biographyNumber, other.biographyNumber).append(this.proposalNumber, other.proposalNumber).toComparison();
        }
    }
}
