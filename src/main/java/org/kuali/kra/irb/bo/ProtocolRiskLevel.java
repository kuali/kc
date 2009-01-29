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

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.RiskLevel;
import org.kuali.kra.irb.document.ProtocolDocument;

/**
 * 
 * This is BO class of protocol risk levels. 
 */
public class ProtocolRiskLevel extends KraPersistableBusinessObjectBase { 
	
	private Integer protocolRiskLevelId; 
	private Long protocolId; 
	private String protocolNumber; 
	private Integer sequenceNumber; 
	private Integer riskLevelCode; 
	private String comments; 
	private Date dateAssigned; 
	private Date dateUpdated; 
	private String status; 
	
	private ProtocolDocument protocol;
	
	private RiskLevel riskLevel;
	
	
	public ProtocolRiskLevel() { 

	} 
	
	public Integer getProtocolRiskLevelId() {
		return protocolRiskLevelId;
	}

	public void setProtocolRiskLevelId(Integer protocolRiskLevelId) {
		this.protocolRiskLevelId = protocolRiskLevelId;
	}

	public Long getProtocolId() {
		return protocolId;
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

	public Integer getRiskLevelCode() {
		return riskLevelCode;
	}

	public void setRiskLevelCode(Integer riskLevelCode) {
		this.riskLevelCode = riskLevelCode;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getDateAssigned() {
		return dateAssigned;
	}

	public void setDateAssigned(Date dateAssigned) {
		this.dateAssigned = dateAssigned;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ProtocolDocument getProtocol() {
		return protocol;
	}

	public void setProtocol(ProtocolDocument protocol) {
		this.protocol = protocol;
	}

	public RiskLevel getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(RiskLevel riskLevel) {
		this.riskLevel = riskLevel;
	}

	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("protocolRiskLevelId", getProtocolRiskLevelId());
		hashMap.put("protocolId", getProtocolId());
		hashMap.put("protocolNumber", getProtocolNumber());
		hashMap.put("sequenceNumber", getSequenceNumber());
		hashMap.put("riskLevelCode", getRiskLevelCode());
		hashMap.put("comments", getComments());
		hashMap.put("dateAssigned", getDateAssigned());
		hashMap.put("dateUpdated", getDateUpdated());
		hashMap.put("status", getStatus());
		return hashMap;
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((comments == null) ? 0 : comments.hashCode());
        result = prime * result + ((dateAssigned == null) ? 0 : dateAssigned.hashCode());
        result = prime * result + ((dateUpdated == null) ? 0 : dateUpdated.hashCode());
        result = prime * result + ((protocol == null) ? 0 : protocol.hashCode());
        result = prime * result + ((protocolId == null) ? 0 : protocolId.hashCode());
        result = prime * result + ((protocolNumber == null) ? 0 : protocolNumber.hashCode());
        result = prime * result + ((protocolRiskLevelId == null) ? 0 : protocolRiskLevelId.hashCode());
        result = prime * result + ((riskLevel == null) ? 0 : riskLevel.hashCode());
        result = prime * result + ((riskLevelCode == null) ? 0 : riskLevelCode.hashCode());
        result = prime * result + ((sequenceNumber == null) ? 0 : sequenceNumber.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final ProtocolRiskLevel other = (ProtocolRiskLevel) obj;
        if (protocolId == null) {
            if (other.protocolId != null)
                return false;
        }
        else if (!protocolId.equals(other.protocolId))
            return false;
        if (protocolNumber == null) {
            if (other.protocolNumber != null)
                return false;
        }
        else if (!protocolNumber.equals(other.protocolNumber))
            return false;
        if (protocolRiskLevelId == null) {
            if (other.protocolRiskLevelId != null)
                return false;
        }
        else if (!protocolRiskLevelId.equals(other.protocolRiskLevelId))
            return false;
        if (riskLevelCode == null) {
            if (other.riskLevelCode != null)
                return false;
        }
        else if (!riskLevelCode.equals(other.riskLevelCode))
            return false;
        if (sequenceNumber == null) {
            if (other.sequenceNumber != null)
                return false;
        }
        else if (!sequenceNumber.equals(other.sequenceNumber))
            return false;

        return true;
    }
	
}