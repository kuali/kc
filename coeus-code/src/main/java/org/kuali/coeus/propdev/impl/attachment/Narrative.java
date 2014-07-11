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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.struts.upload.FormFile;
import org.eclipse.persistence.internal.weaving.RelationshipInfo;
import org.kuali.coeus.common.framework.attachment.KcAttachmentService;
import org.kuali.coeus.sys.api.model.KcFile;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentForm;
import org.kuali.coeus.sys.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.coeus.propdev.impl.hierarchy.HierarchyMaintainable;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.CoreConstants;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;
import org.kuali.rice.krad.file.FileMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name = "NARRATIVE")
@IdClass(Narrative.NarrativeId.class)
public class Narrative extends KcPersistableBusinessObjectBase implements HierarchyMaintainable, KcFile, NarrativeContract, FileMeta {

    @Id
    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "PROPOSAL_NUMBER")
    private DevelopmentProposal developmentProposal;

    @Id
    @Column(name = "MODULE_NUMBER")
    private Integer moduleNumber;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "CONTACT_NAME")
    private String contactName;

    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;

    @Column(name = "MODULE_SEQUENCE_NUMBER")
    private Integer moduleSequenceNumber;

    @Column(name = "MODULE_STATUS_CODE")
    private String moduleStatusCode;

    @Column(name = "MODULE_TITLE")
    private String moduleTitle;

    @Column(name = "NARRATIVE_TYPE_CODE")
    private String narrativeTypeCode;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "FILE_NAME")
    private String name;

    @Column(name = "CONTENT_TYPE")
    private String type;

    @Column(name = "HIERARCHY_PROPOSAL_NUMBER")
    private String hierarchyProposalNumber;

    @Column(name = "HIDE_IN_HIERARCHY")
    @Convert(converter = BooleanYNConverter.class)
    private boolean hiddenInHierarchy;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "NARRATIVE_TYPE_CODE", referencedColumnName = "NARRATIVE_TYPE_CODE", insertable = false, updatable = false)
    private NarrativeType narrativeType;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "MODULE_STATUS_CODE", referencedColumnName = "NARRATIVE_STATUS_CODE", insertable = false, updatable = false)
    private NarrativeStatus narrativeStatus;

    @OneToMany(orphanRemoval = true, cascade = { CascadeType.ALL })
    @JoinColumns({ @JoinColumn(name = "PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER"), @JoinColumn(name = "MODULE_NUMBER", referencedColumnName = "MODULE_NUMBER") })
    private List<NarrativeUserRights> narrativeUserRights;

    @OneToOne(mappedBy = "narrative", orphanRemoval = true, cascade = { CascadeType.ALL })
    private NarrativeAttachment narrativeAttachment;

    @Transient
    private transient FormFile narrativeFile;

    @Transient
    private Timestamp timestampDisplay;

    @Transient
    private String uploadUserDisplay;

    @Transient
    private String uploadUserFullName;
 
    @Transient
    private transient TaskAuthorizationService taskAuthorizationService;

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

    @Override
    public void init(MultipartFile multipartFile) throws Exception {
        this.name = multipartFile.getOriginalFilename();
        this.size = multipartFile.getSize();


        NarrativeAttachment attachment = new NarrativeAttachment();
        attachment.setType(multipartFile.getContentType());
        attachment.setData(multipartFile.getBytes());
        attachment.setName(multipartFile.getName());
        setNarrativeAttachment(attachment);
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
        return this.getNarrativeAttachment().getType();
    }

    @Override
    public void setContentType(String contentType) {
        this.getNarrativeAttachment().setType(contentType);
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

    protected TaskAuthorizationService getTaskAuthorizationService(){
        if (taskAuthorizationService == null)
            taskAuthorizationService = KcServiceLocator.getService(TaskAuthorizationService.class);
        return taskAuthorizationService;
    }

    public Narrative() {
        narrativeUserRights = new ArrayList<NarrativeUserRights>();
    }

    @Override
    public Integer getModuleNumber() {
        return moduleNumber;
    }

    public void setModuleNumber(Integer moduleNumber) {
        this.moduleNumber = moduleNumber;
    }

    @Override
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @Override
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public Integer getModuleSequenceNumber() {
        return moduleSequenceNumber;
    }

    public void setModuleSequenceNumber(Integer moduleSequenceNumber) {
        this.moduleSequenceNumber = moduleSequenceNumber;
    }

    public String getModuleStatusCode() {
        return moduleStatusCode;
    }

    public void setModuleStatusCode(String moduleStatusCode) {
        this.moduleStatusCode = moduleStatusCode;
    }

    @Override
    public String getModuleTitle() {
        return moduleTitle;
    }

    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    public String getNarrativeTypeCode() {
        return narrativeTypeCode;
    }

    public void setNarrativeTypeCode(String narrativeTypeCode) {
        this.narrativeTypeCode = narrativeTypeCode;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public NarrativeType getNarrativeType() {
        return narrativeType;
    }

    public void setNarrativeType(NarrativeType narrativeType) {
        this.narrativeType = narrativeType;
    }

    @Override
    public List<NarrativeUserRights> getNarrativeUserRights() {
        if (narrativeUserRights != null) {
            Collections.sort(this.narrativeUserRights, new Comparator() {

                public int compare(Object o1, Object o2) {
                    NarrativeUserRights r1 = (NarrativeUserRights) o1;
                    NarrativeUserRights r2 = (NarrativeUserRights) o2;
                    if (r1 == null || r2 == null)
                        return 0;
                    String name1 = r1.getPersonName();
                    String name2 = r2.getPersonName();
                    if (name1 == null || name2 == null)
                        return 0;
                    return name1.compareTo(name2);
                }
            });
        }
        return narrativeUserRights;
    }

    public void setNarrativeUserRights(List<NarrativeUserRights> narrativeUserRights) {
        this.narrativeUserRights = narrativeUserRights;
    }

    @Override
    public NarrativeStatus getNarrativeStatus() {
        return narrativeStatus;
    }

    public void setNarrativeStatus(NarrativeStatus narrativeStatus) {
        this.narrativeStatus = narrativeStatus;
    }

    @Override
    public NarrativeAttachment getNarrativeAttachment() {
        return narrativeAttachment;
    }

    public void setNarrativeAttachment(NarrativeAttachment narrativeAttachment) {
        this.narrativeAttachment = narrativeAttachment;
        if (narrativeAttachment!=null) {
            this.narrativeAttachment.setNarrative(this);
        }
    }

    public FormFile getNarrativeFile() {
        return narrativeFile;
    }

    public void setNarrativeFile(FormFile narrativeFile) {
        this.narrativeFile = narrativeFile;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * This method is to return institutional attachment type.  This column does not exist in table.
     * Basically, it is for narrativetypecode in table. 
     * @return
     */
    public String getInstitutionalAttachmentTypeCode() {
        return narrativeTypeCode;
    }

    /**
     * 
     * This method set the institutional attachment type.  Since instituteattachmenttypecode is set as 'required' in DD.
     * So, it is set to narrativetypecode, and hence to return narrative type code.
     * @param institutionalAttachmentTypeCode
     */
    public void setInstitutionalAttachmentTypeCode(String institutionalAttachmentTypeCode) {
        //this.institutionalAttachmentTypeCode = institutionalAttachmentTypeCode;  
        this.narrativeTypeCode = institutionalAttachmentTypeCode;
    }

    /**
     * Can the current user download (view) the attachment?
     * @param userId
     * @return true if the user can view the attachment; otherwise false
     */
    public boolean getDownloadAttachment(String userId) {
        if (getNarrativeUserRights().isEmpty()) {
            refreshReferenceObject("narrativeUserRights");
        }
        TaskAuthorizationService taskAuthorizationService = getTaskAuthorizationService();
        return taskAuthorizationService.isAuthorized(userId, new NarrativeTask(TaskName.DOWNLOAD_NARRATIVE, getDocument(), this));
    }

    /**
     * Can the current user replace the attachment?
     * @return true if the user can replace the attachment; otherwise false
     */
    public boolean getReplaceAttachment(String userId) {
        if (getNarrativeUserRights().isEmpty()) {
            refreshReferenceObject("narrativeUserRights");
        }
        TaskAuthorizationService taskAuthorizationService = getTaskAuthorizationService();
        return taskAuthorizationService.isAuthorized(userId, new NarrativeTask(TaskName.REPLACE_NARRATIVE, getDocument(), this));
    }

    /**
     * Can the current user delete the attachment?
     * @return true if the user can delete the attachment; otherwise false
     */
    public boolean getDeleteAttachment(String userId) {
        if (getNarrativeUserRights().isEmpty()) {
            refreshReferenceObject("narrativeUserRights");
        }
        TaskAuthorizationService taskAuthorizationService = getTaskAuthorizationService();
        return taskAuthorizationService.isAuthorized(userId, new NarrativeTask(TaskName.DELETE_NARRATIVE, getDocument(), this));
    }

    /**
     * Can the current user change the status of attachment?
     * @return true if the user can modify the status of attachments; otherwise false
     */
    public boolean getModifyAttachmentStatus(String userId) {
        if (getNarrativeUserRights().isEmpty()) {
            refreshReferenceObject("narrativeUserRights");
        }
        TaskAuthorizationService taskAuthorizatioNService = getTaskAuthorizationService();
        return taskAuthorizatioNService.isAuthorized(userId, new NarrativeTask(TaskName.MODIFY_NARRATIVE_STATUS, getDocument(), this));
    }

    /**
     * Can the current user modify the user rights for the attachment?
     * @return true if the user can modify the user rights; otherwise false
     */
    public boolean getModifyNarrativeRights(String userId) {
        if (getNarrativeUserRights().isEmpty()) {
            refreshReferenceObject("narrativeUserRights");
        }
        TaskAuthorizationService taskAuthorizationService = getTaskAuthorizationService();
        return taskAuthorizationService.isAuthorized(userId, new NarrativeTask(TaskName.MODIFY_NARRATIVE_RIGHTS, getDocument(), this));
    }

    /**
     * Get the Proposal Development Document for the current session.  The
     * document is within the current form.
     * 
     * @return the current document or null if not found
     */
    private ProposalDevelopmentDocument getDocument() {
        ProposalDevelopmentDocument doc = null;
        ProposalDevelopmentForm form = (ProposalDevelopmentForm) KNSGlobalVariables.getKualiForm();
        if (form != null) {
            doc = form.getProposalDevelopmentDocument();
        }
        return doc;
    }

    /**
     * 
     * This method used to populate the attachment to narrative object by reading FormFile 
     */
    public void populateAttachment() {
        FormFile narrativeFile = getNarrativeFile();
        if (narrativeFile == null)
            return;
        byte[] narrativeFileData;
        try {
            narrativeFileData = narrativeFile.getFileData();
            if (narrativeFileData.length > 0) {
                NarrativeAttachment narrativeAttachment = getNarrativeAttachment();
                if (narrativeAttachment == null) {
                    narrativeAttachment = new NarrativeAttachment();
                    setNarrativeAttachment(narrativeAttachment);
                }
                String fileName = narrativeFile.getFileName();
                narrativeAttachment.setName(fileName);
                narrativeAttachment.setType(narrativeFile.getContentType());
                narrativeAttachment.setData(narrativeFile.getFileData());
                narrativeAttachment.setModuleNumber(getModuleNumber());
                setName(narrativeAttachment.getName());
                setType(narrativeAttachment.getType());
            } else {
                setNarrativeAttachment(null);
            }
        } catch (IOException e) {
            setNarrativeAttachment(null);
        }
    }

    /**
     * Gets index i from the narrativeUserRights list.
     * 
     * @param index
     * @return Question at index i
     */
    public NarrativeUserRights getNarrativeUserRight(int index) {
        while (getNarrativeUserRights().size() <= index) {
            getNarrativeUserRights().add(new NarrativeUserRights());
        }
        return (NarrativeUserRights) getNarrativeUserRights().get(index);
    }

    public void clearAttachment() {
        setNarrativeAttachment(null);
    }

    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        managedLists.add(getNarrativeUserRights());
        return managedLists;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((comments == null) ? 0 : comments.hashCode());
        result = prime * result + ((contactName == null) ? 0 : contactName.hashCode());
        result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((moduleNumber == null) ? 0 : moduleNumber.hashCode());
        result = prime * result + ((moduleSequenceNumber == null) ? 0 : moduleSequenceNumber.hashCode());
        result = prime * result + ((moduleStatusCode == null) ? 0 : moduleStatusCode.hashCode());
        result = prime * result + ((moduleTitle == null) ? 0 : moduleTitle.hashCode());
        result = prime * result + ((narrativeStatus == null) ? 0 : narrativeStatus.hashCode());
        result = prime * result + ((narrativeType == null) ? 0 : narrativeType.hashCode());
        result = prime * result + ((narrativeTypeCode == null) ? 0 : narrativeTypeCode.hashCode());
        result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
        result = prime * result + ((developmentProposal == null) ? 0 : developmentProposal.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Narrative other = (Narrative) obj;
        if (comments == null) {
            if (other.comments != null)
                return false;
        } else if (!comments.equals(other.comments))
            return false;
        if (contactName == null) {
            if (other.contactName != null)
                return false;
        } else if (!contactName.equals(other.contactName))
            return false;
        if (emailAddress == null) {
            if (other.emailAddress != null)
                return false;
        } else if (!emailAddress.equals(other.emailAddress))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (moduleNumber == null) {
            if (other.moduleNumber != null)
                return false;
        } else if (!moduleNumber.equals(other.moduleNumber))
            return false;
        if (moduleSequenceNumber == null) {
            if (other.moduleSequenceNumber != null)
                return false;
        } else if (!moduleSequenceNumber.equals(other.moduleSequenceNumber))
            return false;
        if (moduleStatusCode == null) {
            if (other.moduleStatusCode != null)
                return false;
        } else if (!moduleStatusCode.equals(other.moduleStatusCode))
            return false;
        if (moduleTitle == null) {
            if (other.moduleTitle != null)
                return false;
        } else if (!moduleTitle.equals(other.moduleTitle))
            return false;
        if (narrativeStatus == null) {
            if (other.narrativeStatus != null)
                return false;
        } else if (!narrativeStatus.equals(other.narrativeStatus))
            return false;
        if (narrativeType == null) {
            if (other.narrativeType != null)
                return false;
        } else if (!narrativeType.equals(other.narrativeType))
            return false;
        if (narrativeTypeCode == null) {
            if (other.narrativeTypeCode != null)
                return false;
        } else if (!narrativeTypeCode.equals(other.narrativeTypeCode))
            return false;
        if (phoneNumber == null) {
            if (other.phoneNumber != null)
                return false;
        } else if (!phoneNumber.equals(other.phoneNumber))
            return false;
        if (developmentProposal == null) {
            if (other.developmentProposal != null)
                return false;
        } else if (!developmentProposal.equals(other.developmentProposal))
            return false;
        return true;
    }

    public int hierarchyHashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((comments == null) ? 0 : comments.hashCode());
        result = prime * result + ((contactName == null) ? 0 : contactName.hashCode());
        result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((moduleNumber == null) ? 0 : moduleNumber.hashCode());
        result = prime * result + ((moduleSequenceNumber == null) ? 0 : moduleSequenceNumber.hashCode());
        result = prime * result + ((moduleStatusCode == null) ? 0 : moduleStatusCode.hashCode());
        result = prime * result + ((moduleTitle == null) ? 0 : moduleTitle.hashCode());
        result = prime * result + ((narrativeStatus == null) ? 0 : narrativeStatus.hashCode());
        result = prime * result + ((narrativeType == null) ? 0 : narrativeType.hashCode());
        result = prime * result + ((narrativeTypeCode == null) ? 0 : narrativeTypeCode.hashCode());
        result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
        return result;
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

    /**
     * Gets the hierarchyProposalNumber attribute. 
     * @return Returns the hierarchyProposalNumber.
     */
    @Override
    public String getHierarchyProposalNumber() {
        return hierarchyProposalNumber;
    }

    /**
     * Sets the hierarchyProposalNumber attribute value.
     * @param hierarchyProposalNumber The hierarchyProposalNumber to set.
     */
    public void setHierarchyProposalNumber(String hierarchyProposalNumber) {
        this.hierarchyProposalNumber = hierarchyProposalNumber;
    }

    /**
     * Gets the hiddenInHierarchy attribute. 
     * @return Returns the hiddenInHierarchy.
     */
    @Override
    public boolean isHiddenInHierarchy() {
        return hiddenInHierarchy;
    }

    /**
     * Sets the hiddenInHierarchy attribute value.
     * @param hiddenInHierarchy The hiddenInHierarchy to set.
     */
    public void setHiddenInHierarchy(boolean hiddenInHierarchy) {
        this.hiddenInHierarchy = hiddenInHierarchy;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String contentType) {
        this.type = type;
    }

    public byte[] getData() {
        return getNarrativeAttachment() != null ? getNarrativeAttachment().getData() : null;
    }



    public static final class NarrativeId implements Serializable, Comparable<NarrativeId> {

        private String developmentProposal;

        private Integer moduleNumber;

        public Integer getModuleNumber() {
            return this.moduleNumber;
        }

        public void setModuleNumber(Integer moduleNumber) {
            this.moduleNumber = moduleNumber;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("developmentProposal", this.developmentProposal).append("moduleNumber", this.moduleNumber).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final NarrativeId rhs = (NarrativeId) other;
            return new EqualsBuilder().append(this.developmentProposal, rhs.developmentProposal).append(this.moduleNumber, rhs.moduleNumber).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.developmentProposal).append(this.moduleNumber).toHashCode();
        }

        @Override
        public int compareTo(NarrativeId other) {
            return new CompareToBuilder().append(this.developmentProposal, other.developmentProposal).append(this.moduleNumber, other.moduleNumber).toComparison();
        }

		public String getDevelopmentProposal() {
			return developmentProposal;
		}

		public void setDevelopmentProposal(String developmentProposal) {
			this.developmentProposal = developmentProposal;
		}
    }



	public DevelopmentProposal getDevelopmentProposal() {
		return developmentProposal;
	}

	public void setDevelopmentProposal(DevelopmentProposal developmentProposal) {
		this.developmentProposal = developmentProposal;
	}

	@Override
	public String getProposalNumber() {
		return getDevelopmentProposal().getProposalNumber();
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

}

