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
package org.kuali.kra.proposaldevelopment.bo;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.struts.upload.FormFile;
import org.kuali.coeus.common.framework.attachment.KcAttachment;
import org.kuali.coeus.common.framework.attachment.KcAttachmentService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentForm;
import org.kuali.coeus.sys.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.proposaldevelopment.document.authorization.NarrativeTask;
import org.kuali.kra.proposaldevelopment.hierarchy.HierarchyMaintainable;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

import javax.persistence.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * This class is BO for narrarive
 */
@Entity
@Table(name = "NARRATIVE")
@IdClass(Narrative.NarrativeId.class)
public class Narrative extends KcPersistableBusinessObjectBase implements HierarchyMaintainable, KcAttachment {

    @Id
    @Column(name = "PROPOSAL_NUMBER")
    private String proposalNumber;

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

    @ManyToOne(targetEntity = NarrativeType.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "NARRATIVE_TYPE_CODE", referencedColumnName = "NARRATIVE_TYPE_CODE", insertable = false, updatable = false)
    private NarrativeType narrativeType;

    @ManyToOne(targetEntity = NarrativeStatus.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "MODULE_STATUS_CODE", referencedColumnName = "NARRATIVE_STATUS_CODE", insertable = false, updatable = false)
    private NarrativeStatus narrativeStatus;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "CONTENT_TYPE")
    private String contentType;

    @OneToMany(targetEntity = NarrativeUserRights.class, fetch = FetchType.LAZY, orphanRemoval = true, cascade = { CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.PERSIST })

    @JoinColumns({ @JoinColumn(name = "PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER", insertable = false, updatable = false), @JoinColumn(name = "MODULE_NUMBER", referencedColumnName = "MODULE_NUMBER", insertable = false, updatable = false) })
    private List<NarrativeUserRights> narrativeUserRights;

    @OneToMany(targetEntity = NarrativeAttachment.class, fetch = FetchType.LAZY, orphanRemoval = true, cascade = { CascadeType.REMOVE, CascadeType.PERSIST })

    @JoinColumns({ @JoinColumn(name = "PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER", insertable = false, updatable = false), @JoinColumn(name = "MODULE_NUMBER", referencedColumnName = "MODULE_NUMBER", insertable = false, updatable = false) })
    private List<NarrativeAttachment> narrativeAttachmentList;

    @Transient
    private transient FormFile narrativeFile;

    @Transient
    private Timestamp timestampDisplay;

    @Transient
    private String uploadUserDisplay;

    @Transient
    private String uploadUserFullName;

    @Column(name = "HIERARCHY_PROPOSAL_NUMBER")
    private String hierarchyProposalNumber;

    @Column(name = "HIDE_IN_HIERARCHY")
    @Convert(converter = BooleanYNConverter.class)
    private boolean hiddenInHierarchy;

    public Narrative() {
        narrativeAttachmentList = new ArrayList<NarrativeAttachment>(1);
        narrativeUserRights = new ArrayList<NarrativeUserRights>();
    }

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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public NarrativeType getNarrativeType() {
        return narrativeType;
    }

    public void setNarrativeType(NarrativeType narrativeType) {
        this.narrativeType = narrativeType;
    }

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

    /**
     * Gets the narrativeStatus attribute.
     * 
     * @return Returns the narrativeStatus.
     */
    public NarrativeStatus getNarrativeStatus() {
        return narrativeStatus;
    }

    /**
     * Sets the narrativeStatus attribute value.
     * 
     * @param narrativeStatus The narrativeStatus to set.
     */
    public void setNarrativeStatus(NarrativeStatus narrativeStatus) {
        this.narrativeStatus = narrativeStatus;
    }

    public List<NarrativeAttachment> getNarrativeAttachmentList() {
        return narrativeAttachmentList;
    }

    public void setNarrativeAttachmentList(List<NarrativeAttachment> narrativePdfList) {
        this.narrativeAttachmentList = narrativePdfList;
    }

    public FormFile getNarrativeFile() {
        return narrativeFile;
    }

    public void setNarrativeFile(FormFile narrativeFile) {
        this.narrativeFile = narrativeFile;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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
        TaskAuthorizationService taskAuthorizationService = KcServiceLocator.getService(TaskAuthorizationService.class);
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
        TaskAuthorizationService taskAuthorizationService = KcServiceLocator.getService(TaskAuthorizationService.class);
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
        TaskAuthorizationService taskAuthorizationService = KcServiceLocator.getService(TaskAuthorizationService.class);
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
        TaskAuthorizationService taskAuthorizatioNService = KcServiceLocator.getService(TaskAuthorizationService.class);
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
        TaskAuthorizationService taskAuthorizationService = KcServiceLocator.getService(TaskAuthorizationService.class);
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
                NarrativeAttachment narrativeAttachment;
                if (getNarrativeAttachmentList().isEmpty()) {
                    narrativeAttachment = new NarrativeAttachment();
                    getNarrativeAttachmentList().add(narrativeAttachment);
                } else {
                    narrativeAttachment = getNarrativeAttachmentList().get(0);
                    if (narrativeAttachment == null) {
                        narrativeAttachment = new NarrativeAttachment();
                        getNarrativeAttachmentList().set(0, narrativeAttachment);
                    }
                }
                String fileName = narrativeFile.getFileName();
                narrativeAttachment.setFileName(fileName);
                narrativeAttachment.setContentType(narrativeFile.getContentType());
                narrativeAttachment.setNarrativeData(narrativeFile.getFileData());
                narrativeAttachment.setProposalNumber(getProposalNumber());
                narrativeAttachment.setModuleNumber(getModuleNumber());
                setFileName(narrativeAttachment.getFileName());
                setContentType(narrativeAttachment.getContentType());
            } else {
                getNarrativeAttachmentList().clear();
            }
        } catch (FileNotFoundException e) {
            getNarrativeAttachmentList().clear();
        } catch (IOException e) {
            getNarrativeAttachmentList().clear();
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
        getNarrativeAttachmentList().clear();
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
        result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
        result = prime * result + ((moduleNumber == null) ? 0 : moduleNumber.hashCode());
        result = prime * result + ((moduleSequenceNumber == null) ? 0 : moduleSequenceNumber.hashCode());
        result = prime * result + ((moduleStatusCode == null) ? 0 : moduleStatusCode.hashCode());
        result = prime * result + ((moduleTitle == null) ? 0 : moduleTitle.hashCode());
        result = prime * result + ((narrativeStatus == null) ? 0 : narrativeStatus.hashCode());
        result = prime * result + ((narrativeType == null) ? 0 : narrativeType.hashCode());
        result = prime * result + ((narrativeTypeCode == null) ? 0 : narrativeTypeCode.hashCode());
        result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
        result = prime * result + ((proposalNumber == null) ? 0 : proposalNumber.hashCode());
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
        if (fileName == null) {
            if (other.fileName != null)
                return false;
        } else if (!fileName.equals(other.fileName))
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
        if (proposalNumber == null) {
            if (other.proposalNumber != null)
                return false;
        } else if (!proposalNumber.equals(other.proposalNumber))
            return false;
        return true;
    }

    public int hierarchyHashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((comments == null) ? 0 : comments.hashCode());
        result = prime * result + ((contactName == null) ? 0 : contactName.hashCode());
        result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
        result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
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

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getData() {
        if (getNarrativeAttachmentList().isEmpty()) {
            return null;
        } else {
            return getNarrativeAttachmentList().get(0).getContent();
        }
    }

    public String getIconPath() {
        return KcServiceLocator.getService(KcAttachmentService.class).getFileTypeIcon(this);
    }

    public String getName() {
        return getFileName();
    }

    public String getType() {
        return getContentType();
    }

    public static final class NarrativeId implements Serializable, Comparable<NarrativeId> {

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
            final NarrativeId rhs = (NarrativeId) other;
            return new EqualsBuilder().append(this.proposalNumber, rhs.proposalNumber).append(this.moduleNumber, rhs.moduleNumber).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.proposalNumber).append(this.moduleNumber).toHashCode();
        }

        @Override
        public int compareTo(NarrativeId other) {
            return new CompareToBuilder().append(this.proposalNumber, other.proposalNumber).append(this.moduleNumber, other.moduleNumber).toComparison();
        }
    }
}
