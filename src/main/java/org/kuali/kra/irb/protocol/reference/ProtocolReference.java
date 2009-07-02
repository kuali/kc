/*
 * Copyright 2006-2008 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.protocol.reference;

import java.sql.Date;
import java.util.LinkedHashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolAssociate;

@Entity 
@Table(name="PROTOCOL_REFERENCES")
public class ProtocolReference extends ProtocolAssociate { 
	
    @Id 
    @Column(name="PROTOCOL_REFERENCE_ID")
    private Long protocolReferenceId;
    
    @Column(name="PROTOCOL_ID")
	private Long protocolId; 
    
    @Column(name="PROTOCOL_REFERENCE_NUMBER")
	private Integer protocolReferenceNumber; 
    
    @Column(name="PROTOCOL_REFERENCE_TYPE_CODE")
	private Integer protocolReferenceTypeCode; 
    
    @Column(name="REFERENCE_KEY")
	private String referenceKey; 
    
    @Column(name="APPLICATION_DATE")
	private Date applicationDate; 
    
    @Column(name="APPROVAL_DATE")
	private Date approvalDate; 
    
    @Column(name="COMMENTS")
	private String comments; 
	
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="PROTOCOL_REFERENCE_TYPE_CODE", insertable=true, updatable=true)
	private ProtocolReferenceType protocolReferenceType; 
	
	public ProtocolReference() { 

	} 
	
	public Long getProtocolId() {
		return protocolId;
	}

    public void setProtocolReferenceId(Long protocolReferenceId) {
        this.protocolReferenceId = protocolReferenceId;
    }

    public Long getProtocolReferenceId() {
        return protocolReferenceId;
    }
	
	public void setProtocolId(Long protocolId) {
		this.protocolId = protocolId;
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

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public ProtocolReferenceType getProtocolReferenceType() {
		return protocolReferenceType;
	}

	public void setProtocolReferenceType(ProtocolReferenceType protocolReferenceType) {
		this.protocolReferenceType = protocolReferenceType;
	}

	@SuppressWarnings("unchecked")
    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = super.toStringMapper();
		hashMap.put("protocolReferenceId", getProtocolReferenceId());
		hashMap.put("protocolId", getProtocolId());
		hashMap.put("protocolReferenceNumber", getProtocolReferenceNumber());
		hashMap.put("protocolReferenceTypeCode", getProtocolReferenceTypeCode());
		hashMap.put("referenceKey", getReferenceKey());
		hashMap.put("applicationDate", getApplicationDate());
		hashMap.put("approvalDate", getApprovalDate());
		hashMap.put("comments", getComments());
		return hashMap;
	}

    public void init(Protocol protocol) {
        setProtocolReferenceId(null);
        setProtocolId(protocol.getProtocolId());
        setProtocolNumber(protocol.getProtocolNumber());
        setProtocolReferenceNumber(null);
        setSequenceNumber(protocol.getSequenceNumber());
    }
	
}