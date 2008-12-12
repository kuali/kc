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
package org.kuali.kra.irb.bo;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import java.util.LinkedHashMap;
import java.sql.Date;

public class ProtocolReference extends KraPersistableBusinessObjectBase { 
	
    private Long protocolReferenceId;
	private Long protocolId; 
	private String protocolNumber; 
	private Integer sequenceNumber; 
	private Integer protocolReferenceNumber; 
	private Integer protocolReferenceTypeCode; 
	private String referenceKey; 
	private Date applicationDate; 
	private Date approvalDate; 
	private String comments; 
	
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

	public String getProtocolNumber() {
		return protocolNumber;
	}

	public void setProtocolNumber(String protocolNumber) {
		this.protocolNumber = protocolNumber;
	}

	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
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

	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("protocolReferenceId", getProtocolReferenceId());
		hashMap.put("protocolId", getProtocolId());
		hashMap.put("protocolNumber", getProtocolNumber());
		hashMap.put("sequenceNumber", getSequenceNumber());
		hashMap.put("protocolReferenceNumber", getProtocolReferenceNumber());
		hashMap.put("protocolReferenceTypeCode", getProtocolReferenceTypeCode());
		hashMap.put("referenceKey", getReferenceKey());
		hashMap.put("applicationDate", getApplicationDate());
		hashMap.put("approvalDate", getApprovalDate());
		hashMap.put("comments", getComments());
		return hashMap;
	}
	
}