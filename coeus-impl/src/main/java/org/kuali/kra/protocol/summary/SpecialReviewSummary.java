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
