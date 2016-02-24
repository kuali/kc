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
package org.kuali.coeus.common.committee.impl.meeting;

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
