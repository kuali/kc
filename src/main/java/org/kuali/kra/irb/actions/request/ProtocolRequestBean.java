/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.actions.request;

import java.io.Serializable;

import org.apache.struts.upload.FormFile;

/**
 * The ProtocolRequestBean is used for some of the common, yet simple,
 * protocol request actions.  Those actions are:
 * 
 * 1. Request to Close
 * 2. Request for Suspension
 * 3. Request to Close Enrollment
 * 4. Request to Re-open Enrollment
 * 5. Request for Data Analysis Only
 * 
 * For each of these request actions, a user can select a committee and give
 * a reason for the request.  Each request, though, will require a different
 * protocol action type and submission type entry in the database.  Please
 * see the ActionHelper class for how this class is used.
 */
@SuppressWarnings("serial")
public class ProtocolRequestBean implements Serializable {
    
    private String protocolActionTypeCode;
    private String submissionTypeCode;
    private String committeeId;
    private String reason = "";
    private String fileName;
    private transient FormFile file;
    
    public ProtocolRequestBean(String protocolActionTypeCode, String submissionTypeCode) {
        this.protocolActionTypeCode = protocolActionTypeCode;
        this.submissionTypeCode = submissionTypeCode;
    }

    public void setCommitteeId(String committeeId) {
        this.committeeId = committeeId;
    }

    public String getCommitteeId() {
        return committeeId;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public String getProtocolActionTypeCode() {
        return protocolActionTypeCode;
    }

    public String getSubmissionTypeCode() {
        return submissionTypeCode;
    }
    
    public FormFile getFile() {
        return file;
    }

    public void setFile(FormFile file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
