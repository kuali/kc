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
package org.kuali.kra.protocol.protocol.reference;

import java.io.Serializable;

/**
 * 
 * This class is a form helper for ProtocolReferenceBase objects.
 */
public abstract class ProtocolReferenceBeanBase implements Serializable {


    private static final long serialVersionUID = 6775965004016986063L;

    private Long protocolReferenceId;
    private Integer protocolReferenceNumber;
    private Integer protocolReferenceTypeCode;
    private String referenceKey;
    private String applicationDate;
    private String approvalDate;
    private String comments;


    public ProtocolReferenceBeanBase() {
        init();
    }
    
    /**
     * 
     * This method initializes this class.
     */
    public void init() {
            setProtocolReferenceId(null);
            setProtocolReferenceNumber(null);
            setProtocolReferenceTypeCode(null);
            setReferenceKey("");
            setApplicationDate("");
            setApprovalDate("");
            setComments("");
    }

    public void setProtocolReferenceId(Long protocolReferenceId) {
        this.protocolReferenceId = protocolReferenceId;
    }

    public Long getProtocolReferenceId() {
        return protocolReferenceId;
    }

    public Integer getProtocolReferenceNumber() {
        return protocolReferenceNumber;
    }

    public void setProtocolReferenceNumber(Integer protocolReferenceNumber) {
        this.protocolReferenceNumber = protocolReferenceNumber;
    }

    public Integer getProtocolReferenceTypeCode() {
        return protocolReferenceTypeCode;
    }

    public void setProtocolReferenceTypeCode(Integer protocolReferenceTypeCode) {
        this.protocolReferenceTypeCode = protocolReferenceTypeCode;
    }

    public String getReferenceKey() {
        return referenceKey;
    }

    public void setReferenceKey(String referenceKey) {
        this.referenceKey = referenceKey;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        String newLine = "\n";
        sb.append("protocolReferenceId: ").append(this.protocolReferenceId).append(newLine);
        sb.append("protocolReferenceNumber: ").append(this.protocolReferenceNumber).append(newLine);
        sb.append("protocolReferenceTypeCode: ").append(this.protocolReferenceTypeCode).append(newLine);
        sb.append("referenceKey: ").append(this.referenceKey).append(newLine);
        sb.append("applicationDate: ").append(this.applicationDate).append(newLine);
        sb.append("approvalDate: ").append(this.approvalDate).append(newLine);
        sb.append("comments: ").append(this.comments).append(newLine);
        return sb.toString();
    }
}
