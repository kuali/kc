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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.struts.upload.FormFile;
import org.kuali.coeus.common.framework.attachment.KcAttachmentService;
import org.kuali.coeus.propdev.api.person.attachment.ProposalPersonBiographyContract;
import org.kuali.coeus.propdev.impl.attachment.NarrativeAttachment;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.sys.api.model.KcFile;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.CoreConstants;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.file.FileMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * This is bo for eps_prop_person_bio.
 */
@Entity
@Table(name = "EPS_PROP_PERSON_BIO")
@IdClass(ProposalPersonBiography.ProposalPersonBiographyId.class)
public class ProposalPersonBiography extends KcPersistableBusinessObjectBase implements ProposalPersonBiographyContract, KcFile, FileMeta {

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

    @OneToOne(mappedBy = "proposalPersonBiography", cascade = CascadeType.ALL)
    private ProposalPersonBiographyAttachment personnelAttachment;

    @Transient
    private String proposalPersonNumberString;

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
    private MultipartFile multipartFile;

    @Transient
    private PersonService personService;

    @Transient
    private transient GlobalVariableService globalVariableService;

    @Override
    public void init(MultipartFile multipartFile) throws Exception {
        this.name = multipartFile.getOriginalFilename();
        this.size = multipartFile.getSize();


        ProposalPersonBiographyAttachment attachment = new ProposalPersonBiographyAttachment();
        attachment.setType(multipartFile.getContentType());
        attachment.setData(multipartFile.getBytes());
        attachment.setName(multipartFile.getName());
        attachment.setUpdateTimestamp(getDateTimeService().getCurrentTimestamp());
        attachment.setUpdateUser(getGlobalVariableService().getUserSession().getPrincipalName());
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
        return this.getPersonnelAttachment().getType();
    }

    @Override
    public void setContentType(String contentType) {
        this.getPersonnelAttachment().setType(contentType);
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
        if (getPersonnelAttachment() == null || getPersonnelAttachment().getUpdateTimestamp() == null){
            return this.getUpdateTimestamp();
        }

        return getPersonnelAttachment().getUpdateTimestamp();
    }

    public void setTimestampDisplay(Timestamp timestampDisplay) {
        this.timestampDisplay = timestampDisplay;
    }

    public String getUploadUserDisplay() {
        if (getPersonnelAttachment() == null || StringUtils.isBlank(getPersonnelAttachment().getUpdateUser())){
            return this.getUpdateUser();
        }

        return getPersonnelAttachment().getUpdateUser();
    }

    public void setUploadUserDisplay(String uploadUserDisplay) {
        this.uploadUserDisplay = uploadUserDisplay;
    }

    public String getUploadUserFullName() {
        Person person = getPersonService().getPersonByPrincipalName(getUploadUserDisplay());
        return person == null ? getUploadUserDisplay() : person.getName();
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

        private String developmentProposal;

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

    public PersonService getPersonService() {
        if (personService == null)
            personService = KcServiceLocator.getService(PersonService.class);
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public GlobalVariableService getGlobalVariableService() {
        if (globalVariableService == null)
            globalVariableService = KcServiceLocator.getService(GlobalVariableService.class);
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }
}
