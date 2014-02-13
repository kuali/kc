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

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.struts.upload.FormFile;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.KcAttachment;
import org.kuali.kra.bo.PropPerDocType;
import org.kuali.kra.service.KcAttachmentService;

import javax.persistence.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This is bo for eps_prop_person_bio.
 */
@Entity
@Table(name = "EPS_PROP_PERSON_BIO")
@IdClass(ProposalPersonBiography.ProposalPersonBiographyId.class)
public class ProposalPersonBiography extends KcPersistableBusinessObjectBase implements KcAttachment {

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
    private String fileName;

    @Column(name = "CONTENT_TYPE")
    private String contentType;

    @Transient
    private transient FormFile personnelAttachmentFile;

    @OneToMany(targetEntity = ProposalPersonBiographyAttachment.class, fetch = FetchType.LAZY, orphanRemoval = true, cascade = { CascadeType.REMOVE, CascadeType.PERSIST })

    @JoinColumns({ @JoinColumn(name = "PROP_PERSON_NUMBER", referencedColumnName = "PROP_PERSON_NUMBER", insertable = false, updatable = false), @JoinColumn(name = "BIO_NUMBER", referencedColumnName = "BIO_NUMBER", insertable = false, updatable = false), @JoinColumn(name = "PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER", insertable = false, updatable = false) })
    private List<ProposalPersonBiographyAttachment> personnelAttachmentList;

    @ManyToOne(targetEntity = PropPerDocType.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "DOCUMENT_TYPE_CODE", referencedColumnName = "DOCUMENT_TYPE_CODE", insertable = false, updatable = false)
    private PropPerDocType propPerDocType;

    @Transient
    private Timestamp timestampDisplay;

    @Transient
    private String uploadUserDisplay;

    @Transient
    private String uploadUserFullName;

    @Transient
    private transient int positionNumber;

    public ProposalPersonBiography() {
        super();
        personnelAttachmentList = new ArrayList<ProposalPersonBiographyAttachment>(1);
    }

    public Integer getProposalPersonNumber() {
        return proposalPersonNumber;
    }

    public void setProposalPersonNumber(Integer proposalPersonNumber) {
        this.proposalPersonNumber = proposalPersonNumber;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

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

    public List<ProposalPersonBiographyAttachment> getPersonnelAttachmentList() {
        return personnelAttachmentList;
    }

    public void setPersonnelAttachmentList(List<ProposalPersonBiographyAttachment> personnelAttachmentList) {
        this.personnelAttachmentList = personnelAttachmentList;
    }

    public PropPerDocType getPropPerDocType() {
        return propPerDocType;
    }

    public void setPropPerDocType(PropPerDocType propPerDocType) {
        this.propPerDocType = propPerDocType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getRolodexId() {
        return rolodexId;
    }

    public void setRolodexId(Integer rolodexId) {
        this.rolodexId = rolodexId;
    }

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

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getData() {
        if (getPersonnelAttachmentList().isEmpty()) {
            return null;
        } else {
            return getPersonnelAttachmentList().get(0).getContent();
        }
    }

    public String getName() {
        return getFileName();
    }

    public String getType() {
        return getContentType();
    }

    public String getIconPath() {
        return KcServiceLocator.getService(KcAttachmentService.class).getFileTypeIcon(this);
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
                ProposalPersonBiographyAttachment personnelAttachment;
                if (getPersonnelAttachmentList().isEmpty()) {
                    personnelAttachment = new ProposalPersonBiographyAttachment();
                    getPersonnelAttachmentList().add(personnelAttachment);
                } else {
                    personnelAttachment = getPersonnelAttachmentList().get(0);
                    if (personnelAttachment == null) {
                        personnelAttachment = new ProposalPersonBiographyAttachment();
                        getPersonnelAttachmentList().set(0, personnelAttachment);
                    }
                }
                String fileName = personnelFile.getFileName();
                personnelAttachment.setFileName(fileName);
                personnelAttachment.setContentType(personnelFile.getContentType());
                personnelAttachment.setBiographyData(personnelFile.getFileData());
                personnelAttachment.setProposalNumber(getProposalNumber());
                //personnelAttachment.setPositionNumber(getPositionNumber()); 
                setFileName(personnelAttachment.getFileName());
                setContentType(personnelAttachment.getContentType());
            } else {
                getPersonnelAttachmentList().clear();
            }
        } catch (FileNotFoundException e) {
            getPersonnelAttachmentList().clear();
        } catch (IOException e) {
            getPersonnelAttachmentList().clear();
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
