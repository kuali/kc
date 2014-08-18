/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.meeting;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * This class is form data bean for protocol submitted tab on meeting page.
 */
public class ProtocolSubmittedBean implements Serializable {

    private static final long serialVersionUID = -5980527218611646250L;
    private String protocolNumber; 
    private String protocolTitle; 
    private String submissionTypeCode;
    private String submissionTypeQualifierCode;
    private String submissionStatusCode;
    private String protocolReviewTypeCode;
    private Timestamp submissionDate; 
    private Integer protocolPersonId;
    private String personId;
    private String personName;
    private Integer rolodexId;
    public String getProtocolNumber() {
        return protocolNumber;
    }
    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }
    public String getProtocolTitle() {
        return protocolTitle;
    }
    public void setProtocolTitle(String protocolTitle) {
        this.protocolTitle = protocolTitle;
    }
    public String getSubmissionTypeCode() {
        return submissionTypeCode;
    }
    public void setSubmissionTypeCode(String submissionTypeCode) {
        this.submissionTypeCode = submissionTypeCode;
    }
    public String getSubmissionTypeQualifierCode() {
        return submissionTypeQualifierCode;
    }
    public void setSubmissionTypeQualifierCode(String submissionTypeQualifierCode) {
        this.submissionTypeQualifierCode = submissionTypeQualifierCode;
    }
    public String getSubmissionStatusCode() {
        return submissionStatusCode;
    }
    public void setSubmissionStatusCode(String submissionStatusCode) {
        this.submissionStatusCode = submissionStatusCode;
    }
    public String getProtocolReviewTypeCode() {
        return protocolReviewTypeCode;
    }
    public void setProtocolReviewTypeCode(String protocolReviewTypeCode) {
        this.protocolReviewTypeCode = protocolReviewTypeCode;
    }
    public Timestamp getSubmissionDate() {
        return submissionDate;
    }
    public void setSubmissionDate(Timestamp submissionDate) {
        this.submissionDate = submissionDate;
    }
    public Integer getProtocolPersonId() {
        return protocolPersonId;
    }
    public void setProtocolPersonId(Integer protocolPersonId) {
        this.protocolPersonId = protocolPersonId;
    }
    public String getPersonId() {
        return personId;
    }
    public void setPersonId(String personId) {
        this.personId = personId;
    }
    public String getPersonName() {
        return personName;
    }
    public void setPersonName(String personName) {
        this.personName = personName;
    }
    public Integer getRolodexId() {
        return rolodexId;
    }
    public void setRolodexId(Integer rolodexId) {
        this.rolodexId = rolodexId;
    }

}
