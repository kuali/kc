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
package org.kuali.kra.irb.actions.notifyirb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
@SuppressWarnings("serial")
public class ProtocolNotifyIrbBean implements Serializable{
    
    private String submissionQualifierTypeCode;
    private String reviewTypeCode;
    private String committeeId;
    private String comment = "";
//    private String fileName;
//    private transient FormFile file;
    // add following for multiple files attachments
    private ProtocolActionAttachment newActionAttachment;
    private List<ProtocolActionAttachment> actionAttachments = new ArrayList<ProtocolActionAttachment>();
    
    public ProtocolNotifyIrbBean() {
        
    }

    public void setCommitteeId(String committeeId) {
        this.committeeId = committeeId;
    }

    public String getCommitteeId() {
        return committeeId;
    }

    public String getSubmissionQualifierTypeCode() {
        return submissionQualifierTypeCode;
    }

    public void setSubmissionQualifierTypeCode(String submissionQualifierTypeCode) {
        this.submissionQualifierTypeCode = submissionQualifierTypeCode;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
//    public FormFile getFile() {
//        return file;
//    }
//
//    public void setFile(FormFile file) {
//        this.file = file;
//    }
//
//    public String getFileName() {
//        return fileName;
//    }
//
//    public void setFileName(String fileName) {
//        this.fileName = fileName;
//    }

    public String getReviewTypeCode() {
        return reviewTypeCode;
    }

    public void setReviewTypeCode(String reviewTypeCode) {
        this.reviewTypeCode = reviewTypeCode;
    }

    public List<ProtocolActionAttachment> getActionAttachments() {
        return actionAttachments;
    }

    public void setActionAttachments(List<ProtocolActionAttachment> actionAttachments) {
        this.actionAttachments = actionAttachments;
    }

    public ProtocolActionAttachment getNewActionAttachment() {
        return newActionAttachment;
    }

    public void setNewActionAttachment(ProtocolActionAttachment newActionAttachment) {
        this.newActionAttachment = newActionAttachment;
    }
}
