/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.onlinereview;

import java.sql.Timestamp;
import java.util.LinkedHashMap;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerException;
import org.apache.struts.upload.FormFile;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.bo.AttachmentFile;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;

/**
 * 
 * This class is the bo for reviewer attachment.
 */
public class ProtocolReviewAttachment extends ProtocolReviewable {

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
    private Protocol protocol;
    @SkipVersioning
    private ProtocolSubmission protocolSubmission;

    private ProtocolOnlineReview protocolOnlineReview;
    @SkipVersioning
    private transient String createUserFullName;
    @SkipVersioning
    private transient String updateUserFullName;
    private transient boolean displayReviewerName;
    private transient boolean displayViewButton;
    private transient boolean shouldBeSaved = false;

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
     * Sets the Protocol Attachment Base File.
     * @param file the Protocol Attachment Base File
     */
    public void setFile(AttachmentFile file) {
        if (file != null && !file.equals(getFile())) {
            shouldBeSaved = true;
        }
        this.file = file;
    }

    public ProtocolOnlineReview getProtocolOnlineReview() {
        return protocolOnlineReview;
    }

    public void setProtocolOnlineReview(ProtocolOnlineReview protocolOnlineReview) {
        if (protocolOnlineReview != null && !protocolOnlineReview.equals(getProtocolOnlineReview())) {
            shouldBeSaved = true;
        }
        this.protocolOnlineReview = protocolOnlineReview;
    }

    @Override
    protected LinkedHashMap toStringMapper() {
        // TODO Auto-generated method stub
        return null;
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

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
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

    public CommitteeSchedule getCommitteeSchedule() {
        if (getProtocolSubmission()!= null) {
            if (getProtocolSubmission().getScheduleIdFk() != null && getProtocolSubmission().getCommitteeSchedule() == null) {
                getProtocolSubmission().refreshReferenceObject("committeeSchedule");
            }
            return getProtocolSubmission().getCommitteeSchedule();
        }
        return null;
    }


    public ProtocolSubmission getProtocolSubmission() {
        if (submissionIdFk != null && protocolSubmission == null) {
            this.refreshReferenceObject("protocolSubmission");
        }
        return protocolSubmission;
    }

    public void setProtocolSubmission(ProtocolSubmission protocolSubmission) {
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
    public void beforeUpdate(PersistenceBroker persistenceBroker) throws PersistenceBrokerException {
        if (isShouldBeSaved()) {
            super.beforeUpdate(persistenceBroker);
        }
    }
    
    public boolean isShouldBeSaved() {
        return shouldBeSaved;
    }

}
