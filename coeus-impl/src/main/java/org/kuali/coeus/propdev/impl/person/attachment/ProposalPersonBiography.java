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
package org.kuali.coeus.propdev.impl.person.attachment;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.common.framework.attachment.KcAttachmentDataDao;
import org.kuali.coeus.common.framework.attachment.KcAttachmentService;
import org.kuali.coeus.propdev.api.person.attachment.ProposalPersonBiographyContract;
import org.kuali.coeus.propdev.impl.attachment.ProposalDevelopmentAttachment;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.sys.api.model.KcFile;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.CoreConstants;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.file.FileMeta;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 
 * This is bo for eps_prop_person_bio.
 */
@Entity
@Table(name = "EPS_PROP_PERSON_BIO")
@IdClass(ProposalPersonBiography.ProposalPersonBiographyId.class)
public class ProposalPersonBiography extends KcPersistableBusinessObjectBase implements ProposalPersonBiographyContract, KcFile, FileMeta, ProposalDevelopmentAttachment {

    @Id
    @Column(name = "PROP_PERSON_NUMBER")
    private Integer proposalPersonNumber;

    @Column(name = "PERSON_ID")
    private String personId;

    @Id
    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "PROPOSAL_NUMBER")
    private DevelopmentProposal developmentProposal;

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

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "DOCUMENT_TYPE_CODE", referencedColumnName = "DOCUMENT_TYPE_CODE", insertable = false, updatable = false)
    private PropPerDocType propPerDocType;

    @OneToOne(mappedBy = "proposalPersonBiography", cascade = { CascadeType.ALL })
    private ProposalPersonBiographyAttachment personnelAttachment;

    @Transient
    private String proposalPersonNumberString;

    @Transient
    private String uploadUserFullName;

    @Transient
    private transient int positionNumber;

    @Transient
    private String id;

    @Transient
    private Long size;

    @Transient
    private Date dateUploaded;

    @Transient
    private String url;
    
    @Transient
    private transient DateTimeService dateTimeService;

    @Transient
    private transient KcAttachmentService kcAttachmentService;

    @Transient
    private transient MultipartFile multipartFile;

    @Override
    public void init(MultipartFile multipartFile) throws Exception {
        this.name = multipartFile.getOriginalFilename();
        this.size = multipartFile.getSize();


        ProposalPersonBiographyAttachment attachment = new ProposalPersonBiographyAttachment();
        attachment.setType(multipartFile.getContentType());
        attachment.setData(multipartFile.getBytes());
        attachment.setName(multipartFile.getOriginalFilename());
        setPersonnelAttachment(attachment);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getContentType() {
        if (personnelAttachment != null){
            return this.getPersonnelAttachment().getType();
        }
        return null;
    }

    @Override
    public void setContentType(String contentType) {
        if (personnelAttachment != null) {
            this.getPersonnelAttachment().setType(contentType);
        }
    }

    @Override
    public Long getSize() {
        return size;
    }

    @Override
    public void setSize(Long size) {
        this.size = size;
    }

    @Override
    public Date getDateUploaded() {
        return dateUploaded;
    }

    @Override
    public void setDateUploaded(Date dateUploaded) {
        this.dateUploaded = dateUploaded;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getSizeFormatted() {
        return getKcAttachmentService().formatFileSizeString(size);
    }

    @Override
    public String getDateUploadedFormatted() {
        if (this.getUpdateTimestamp() != null) {
            return getDateTimeService().toString(new Date(this.getUpdateTimestamp().getTime()), CoreConstants.TIMESTAMP_TO_STRING_FORMAT_FOR_USER_INTERFACE_DEFAULT);
        }
        return StringUtils.EMPTY;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    @Override
    public Integer getProposalPersonNumber() {
        return proposalPersonNumber;
    }

    public void setProposalPersonNumber(Integer proposalPersonNumber) {
        this.proposalPersonNumber = proposalPersonNumber;
        this.proposalPersonNumberString = proposalPersonNumber!=null?proposalPersonNumber.toString():null;
    }

    public String getProposalPersonNumberString() {
        if (proposalPersonNumberString == null && proposalPersonNumber != null) {
            return proposalPersonNumber.toString();
        }
        return proposalPersonNumberString;
    }

    public void setProposalPersonNumberString(String proposalPersonNumberString) {
        this.proposalPersonNumberString = proposalPersonNumberString;
        this.proposalPersonNumber = proposalPersonNumberString!=null?Integer.parseInt(proposalPersonNumberString):null;
    }

    @Override
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public DevelopmentProposal getDevelopmentProposal() {
        return developmentProposal;
    }

    public void setDevelopmentProposal(DevelopmentProposal developmentProposal) {
        this.developmentProposal = developmentProposal;
    }

    @Override
    public String getProposalNumber() {
        return this.getDevelopmentProposal().getProposalNumber();
    }

    public void setProposalNumber(String proposalNumber) {
        this.getDevelopmentProposal().setProposalNumber(proposalNumber);
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

    @Override
    public ProposalPersonBiographyAttachment getPersonnelAttachment() {
        return personnelAttachment;
    }

    public void setPersonnelAttachment(ProposalPersonBiographyAttachment personnelAttachment) {
        this.personnelAttachment = personnelAttachment;
        if (personnelAttachment!=null) {
            this.personnelAttachment.setProposalPersonBiography(this);
        }
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
        if (getPersonnelAttachment() == null || getPersonnelAttachment().getUploadTimestamp() == null) {
            return getDateTimeService().getCurrentTimestamp();
        }
        return getPersonnelAttachment().getUploadTimestamp();
    }

    public String getUploadUserDisplay() {
        if (getPersonnelAttachment() == null || StringUtils.isBlank(getPersonnelAttachment().getUploadUser())) {
            return this.getUpdateUser();
        }

        return getPersonnelAttachment().getUploadUser();
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

    public static final class ProposalPersonBiographyId implements Serializable, Comparable<ProposalPersonBiographyId> {

        private Integer proposalPersonNumber;

        private Integer biographyNumber;

        private String developmentProposal;

        public ProposalPersonBiographyId(Integer proposalPersonNumber, Integer biographyNumber, String developmentProposal) {
            this.proposalPersonNumber = proposalPersonNumber;
            this.biographyNumber = biographyNumber;
            this.developmentProposal = developmentProposal;
        }

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

        public String getDevelopmentProposal() {
            return developmentProposal;
        }

        public void setDevelopmentProposal(String developmentProposal) {
            this.developmentProposal = developmentProposal;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("proposalPersonNumber", this.proposalPersonNumber).append("biographyNumber", this.biographyNumber).append("developmentProposal", this.developmentProposal).toString();
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
            return new EqualsBuilder().append(this.proposalPersonNumber, rhs.proposalPersonNumber).append(this.biographyNumber, rhs.biographyNumber).append(this.developmentProposal, rhs.developmentProposal).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.proposalPersonNumber).append(this.biographyNumber).append(this.developmentProposal).toHashCode();
        }

        @Override
        public int compareTo(ProposalPersonBiographyId other) {
            return new CompareToBuilder().append(this.proposalPersonNumber, other.proposalPersonNumber).append(this.biographyNumber, other.biographyNumber).append(this.developmentProposal, other.developmentProposal).toComparison();
        }
    }

    public DateTimeService getDateTimeService() {
        if (dateTimeService == null)
            dateTimeService = KcServiceLocator.getService(DateTimeService.class);
        return dateTimeService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    public KcAttachmentService getKcAttachmentService() {
        if (kcAttachmentService == null)
            kcAttachmentService = KcServiceLocator.getService(KcAttachmentService.class);
        return kcAttachmentService;
    }

    public void setKcAttachmentService(KcAttachmentService kcAttachmentService) {
        this.kcAttachmentService = kcAttachmentService;
    }

    @PostRemove
    public void removeData() {
        if (getPersonnelAttachment() != null && getPersonnelAttachment().getFileDataId() != null) {
            getKcAttachmentDao().removeData(getPersonnelAttachment().getFileDataId());
        }
    }

    private KcAttachmentDataDao getKcAttachmentDao() {
        return KcServiceLocator.getService(KcAttachmentDataDao.class);
    }
}
