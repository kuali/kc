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
package org.kuali.kra.irb.summary;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.codehaus.plexus.util.StringUtils;
import org.kuali.kra.irb.specialreview.ProtocolSpecialReviewExemption;

public class SpecialReviewSummary implements Serializable {

    private static final long serialVersionUID = -8047038042526446451L;
    
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    
    private String type;
    private String approvalStatus;
    private String protocolNumber;
    private String applicationDate;
    private String approvalDate;
    private String expirationDate;
    private String exemptionNumbers;
    private String comment;
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getApprovalStatus() {
        return approvalStatus;
    }
    
    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
    
    public String getProtocolNumber() {
        return protocolNumber;
    }
    
    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }
    
    public String getApplicationDate() {
        return applicationDate;
    }
    
    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = formatDate(applicationDate);
    }
    
    public String getApprovalDate() {
        return approvalDate;
    }
    
    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = formatDate(approvalDate);
    }
    
    public String getExpirationDate() {
        return expirationDate;
    }
    
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = formatDate(expirationDate);
    }
    
    public String getExemptionNumbers() {
        return exemptionNumbers;
    }
    
    public void setExemptionNumbers(List<ProtocolSpecialReviewExemption> exemptions) {
        this.exemptionNumbers = "";
        for (ProtocolSpecialReviewExemption exemption : exemptions) {
            if (!StringUtils.isBlank(this.exemptionNumbers)) {
                this.exemptionNumbers += ", ";
            }
            exemption.refreshReferenceObject("exemptionType");
            this.exemptionNumbers += exemption.getExemptionType().getDescription();
        }
    }
    
    public String getComment() {
        return comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    private String formatDate(Date date) {
        return (date == null) ? "" : dateFormat.format(date);
    }
}
