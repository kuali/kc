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
package org.kuali.kra.protocol.onlinereview;

import org.apache.struts.upload.FormFile;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.framework.attachment.AttachmentFile;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;

import java.sql.Timestamp;

/**
 * 
 * This class is the bo for reviewer attachment.
 */
public abstract class ProtocolReviewAttachmentBase extends ProtocolReviewableBase {

    private static final long serialVersionUID = 354343904514258435L;
    private static final String PERSON_NOT_FOUND_FORMAT_STRING = "%s (not found)";
    private Long reviewerAttachmentId;
    private Long fileId;
    private Integer attachmentId;
    private Long protocolOnlineReviewIdFk;
    private String description;
    private String mimeType;
    private String createUser;
    private boolean privateFlag; 
    private boolean protocolPersonCanView;
    private Long submissionIdFk; 
    private String personId;
    private Timestamp createTimestamp;
    private transient AttachmentFile file;
    private transient FormFile newFile;
    private Long protocolIdFk; 
    @SkipVersioning
    private ProtocolBase protocol;
    @SkipVersioning
    private ProtocolSubmissionBase protocolSubmission;

    private ProtocolOnlineReviewBase protocolOnlineReview;
    @SkipVersioning
    private transient String createUserFullName;
    @SkipVersioning
    private transient String updateUserFullName;
    private transient boolean displayReviewerName;
    private transient boolean displayViewButton;
    private transient boolean shouldBeSaved = false;
    private transient KcPersonService kcPersonService;

    public Long getReviewerAttachmentId() {
        return reviewerAttachmentId;
    }

    public void setReviewerAttachmentId(Long reviewerAttachmentId) {
        this.reviewerAttachmentId = reviewerAttachmentId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Integer getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Integer attachmentId) {
        this.attachmentId = attachmentId;
    }

    public Long getProtocolOnlineReviewIdFk() {
        return protocolOnlineReviewIdFk;
    }

    public void setProtocolOnlineReviewIdFk(Long protocolOnlineReviewIdFk) {
        this.protocolOnlineReviewIdFk = protocolOnlineReviewIdFk;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null && !description.equals(getDescription())) {
            shouldBeSaved = true;
        }
        this.description = description;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public AttachmentFile getFile() {
        if (this.fileId != null && this.file == null) {
            refreshReferenceObject("file");
        }
        return this.file;
    }
    
    /**
     * Sets the ProtocolBase Attachment Base File.
     * @param file the ProtocolBase Attachment Base File
     */
    public void setFile(AttachmentFile file) {
        if (file != null && !file.equals(getFile())) {
            shouldBeSaved = true;
        }
        this.file = file;
    }

    public ProtocolOnlineReviewBase getProtocolOnlineReview() {
        return protocolOnlineReview;
    }

    public void setProtocolOnlineReview(ProtocolOnlineReviewBase protocolOnlineReview) {
        if (protocolOnlineReview != null && !protocolOnlineReview.equals(getProtocolOnlineReview())) {
            shouldBeSaved = true;
        }
        this.protocolOnlineReview = protocolOnlineReview;
    }

    public boolean isPrivateFlag() {
        return privateFlag;
    }

    public void setPrivateFlag(boolean privateFlag) {
        if (privateFlag != isPrivateFlag()) {
            shouldBeSaved = true;
        }
        this.privateFlag = privateFlag;
    }

    public FormFile getNewFile() {
        return newFile;
    }

    public void setNewFile(FormFile newFile) {
        this.newFile = newFile;
    }
    public boolean isDisplayReviewerName() {
        return displayReviewerName;
    }

    public void setDisplayReviewerName(boolean displayReviewerName) {
        this.displayReviewerName = displayReviewerName;
    }
    public String getCreateUserFullName() {
        if (createUserFullName == null && getCreateUser() != null) {
            KcPerson person = getKcPersonService().getKcPersonByUserName(getCreateUser());
            createUserFullName = person==null?String.format(PERSON_NOT_FOUND_FORMAT_STRING,getCreateUser()):person.getFullName();
        }
        return createUserFullName;
    }

    /**
     * Sets the createUserFullName attribute value.
     * @param createUserFullName The createUserFullName to set.
     */
    public void setCreateUserFullName(String createUserFullName) {
        this.createUserFullName = createUserFullName;
    }
    
    public String getUpdateUserFullName() {
        if (updateUserFullName == null && getUpdateUser() != null) {
            KcPerson person = getKcPersonService().getKcPersonByUserName(getUpdateUser());
            updateUserFullName = person==null?String.format(PERSON_NOT_FOUND_FORMAT_STRING,getUpdateUser()):person.getFullName();
        }
        return updateUserFullName;
    }

    /**
     * Sets the updateUserFullName attribute value.
     * @param updateUserFullName The updateUserFullName to set.
     */
    public void setUpdateUserFullName(String updateUserFullName) {
        this.updateUserFullName = updateUserFullName;
    }

    public Long getSubmissionIdFk() {
        return submissionIdFk;
    }

    public void setSubmissionIdFk(Long submissionIdFk) {
        this.submissionIdFk = submissionIdFk;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Long getProtocolIdFk() {
        return protocolIdFk;
    }

    public void setProtocolIdFk(Long protocolIdFk) {
        this.protocolIdFk = protocolIdFk;
    }

    public ProtocolBase getProtocol() {
        return protocol;
    }

    public void setProtocol(ProtocolBase protocol) {
        this.protocol = protocol;
    }

    public Long getProtocolId() {
        Long protocolId = null;
        if (this.protocol != null) {
            protocolId = this.protocol.getProtocolId();
        } else {
            if (this.protocolIdFk != null) {
                this.refreshReferenceObject("protocol");
            }
            if (protocol != null) {
                protocolId = this.protocol.getProtocolId();
                
            } 
        }
        return protocolId;
    }

    public boolean isProtocolPersonCanView() {
        return protocolPersonCanView;
    }

    public void setProtocolPersonCanView(boolean protocolPersonCanView) {
        this.protocolPersonCanView = protocolPersonCanView;
    }

    public CommitteeScheduleBase getCommitteeSchedule() {
        if (getProtocolSubmission()!= null) {
            if (getProtocolSubmission().getScheduleIdFk() != null && getProtocolSubmission().getCommitteeSchedule() == null) {
                getProtocolSubmission().refreshReferenceObject("committeeSchedule");
            }
            return getProtocolSubmission().getCommitteeSchedule();
        }
        return null;
    }


    public ProtocolSubmissionBase getProtocolSubmission() {
        if (submissionIdFk != null && protocolSubmission == null) {
            this.refreshReferenceObject("protocolSubmission");
        }
        return protocolSubmission;
    }

    public void setProtocolSubmission(ProtocolSubmissionBase protocolSubmission) {
        this.protocolSubmission = protocolSubmission;
    }

    public boolean isDisplayViewButton() {
        return displayViewButton;
    }

    public void setDisplayViewButton(boolean displayViewButton) {
        this.displayViewButton = displayViewButton;
    }

    @Override
    public boolean isFinal() {
        // TODO not sure if att is entered from management review att
        return getProtocolOnlineReviewIdFk() == null || (getProtocolOnlineReview().isReviewerApproved() && getProtocolOnlineReview().isAdminAccepted());
    }

    @Override
    public boolean isPrivate() {
        return isPrivateFlag();
    }

    /*
     * beforeUpdate - only do actual update if a change has been made to the comment.
     */
    @Override
    protected void preUpdate() {
        if (isShouldBeSaved()) {
            super.preUpdate();
        }
    }
    
    public boolean isShouldBeSaved() {
        return shouldBeSaved;
    }


    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        return this.kcPersonService;
    }

    public String getProtocolNumber() {
        return this.getProtocol() != null ? this.getProtocol().getProtocolNumber() : null;
    }

    public Integer getSubmissionNumber() {
        return this.getProtocolSubmission() != null ? this.getProtocolSubmission().getSubmissionNumber() : null;
    }
}
