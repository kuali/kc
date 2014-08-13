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
package org.kuali.kra.protocol.summary;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.protocol.specialreview.ProtocolSpecialReviewExemption;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SpecialReviewSummary implements Serializable {

    private static final long serialVersionUID = -8047038042526446451L;
    
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    
    private String type;
    private String approvalStatus;
    private String protocolNumber;
    private String applicationDate;
    private String approvalDate;
    private String expirationDate;
    private String exemptionNumbers;
    private String comment;
    
    private boolean typeChanged;
    private boolean approvalStatusChanged;
    private boolean protocolNumberChanged;
    private boolean applicationDateChanged;
    private boolean approvalDateChanged;
    private boolean expirationDateChanged;
    private boolean exemptionNumbersChanged;
    private boolean commentChanged;
    
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
        Collections.sort(exemptions, new Comparator<ProtocolSpecialReviewExemption>() {
            public int compare(ProtocolSpecialReviewExemption e1, ProtocolSpecialReviewExemption e2) {
                if (e1.getExemptionType() == null) {
                    e1.refreshReferenceObject("exemptionType");
                }
                if (e2.getExemptionType() == null) {
                    e2.refreshReferenceObject("exemptionType");
                }
                return e1.getExemptionType().getDescription().compareTo(e2.getExemptionType().getDescription());
            }
        });
        for (ProtocolSpecialReviewExemption exemption : exemptions) {
            if (!StringUtils.isBlank(this.exemptionNumbers)) {
                this.exemptionNumbers += ", ";
            }
            if (exemption.getExemptionType() == null) {
                exemption.refreshReferenceObject("exemptionType");
            }
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

    public boolean isTypeChanged() {
        return typeChanged;
    }

    public boolean isApprovalStatusChanged() {
        return approvalStatusChanged;
    }

    public boolean isProtocolNumberChanged() {
        return protocolNumberChanged;
    }

    public boolean isApplicationDateChanged() {
        return applicationDateChanged;
    }

    public boolean isApprovalDateChanged() {
        return approvalDateChanged;
    }

    public boolean isExpirationDateChanged() {
        return expirationDateChanged;
    }

    public boolean isExemptionNumbersChanged() {
        return exemptionNumbersChanged;
    }

    public boolean isCommentChanged() {
        return commentChanged;
    }

    public void compare(ProtocolSummary other) {
        SpecialReviewSummary otherSpecialReview = other.findSpecialReview(type, approvalStatus);
        if (otherSpecialReview == null) {
            typeChanged = true;
            approvalStatusChanged = true;
            protocolNumberChanged = true;
            applicationDateChanged = true;
            approvalDateChanged = true;
            expirationDateChanged = true;
            exemptionNumbersChanged = true;
            commentChanged = true;
        }
        else {
            typeChanged = !StringUtils.equals(type, otherSpecialReview.type);
            approvalStatusChanged = !StringUtils.equals(approvalStatus, otherSpecialReview.approvalStatus);
            protocolNumberChanged = !StringUtils.equals(protocolNumber, otherSpecialReview.protocolNumber);
            applicationDateChanged = !StringUtils.equals(applicationDate, otherSpecialReview.applicationDate);
            approvalDateChanged = !StringUtils.equals(approvalDate, otherSpecialReview.approvalDate);
            expirationDateChanged = !StringUtils.equals(expirationDate, otherSpecialReview.expirationDate);
            exemptionNumbersChanged = !StringUtils.equals(exemptionNumbers, otherSpecialReview.exemptionNumbers);
            commentChanged = !StringUtils.equals(comment, otherSpecialReview.comment);
        }
    }
}
