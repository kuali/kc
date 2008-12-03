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
import org.kuali.kra.bo.ResearchAreas;
import org.kuali.kra.irb.document.ProtocolDocument;

import java.util.LinkedHashMap;

public class ProtocolResearchAreas extends KraPersistableBusinessObjectBase { 
	
	private Long protocolId; 
	private String protocolNumber; 
	private Integer sequenceNumber; 
	private String researchAreaCode; 
	
	private ProtocolDocument protocol; 
	private ResearchAreas researchAreas; 
	
	//Is transient, used for lookup selection and deletion in UI by KNS 
	private Boolean selectResearchArea = false;
	
	public ProtocolResearchAreas() { 

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

	public String getResearchAreaCode() {
		return researchAreaCode;
	}

	public void setResearchAreaCode(String researchAreaCode) {
		this.researchAreaCode = researchAreaCode;
	}

	public ProtocolDocument getProtocol() {
		return protocol;
	}

	public void setProtocol(ProtocolDocument protocol) {
		this.protocol = protocol;
	}

	public ResearchAreas getResearchAreas() {
		return researchAreas;
	}

	public void setResearchAreas(ResearchAreas researchAreas) {
		this.researchAreas = researchAreas;
	}

	@SuppressWarnings("unchecked")
    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();		
		hashMap.put("protocolId", getProtocolId());
		hashMap.put("protocolNumber", getProtocolNumber());
		hashMap.put("sequenceNumber", getSequenceNumber());
		hashMap.put("researchAreaCode", getResearchAreaCode());
		return hashMap;
	}
	
    public Boolean getSelectResearchArea() {
        return selectResearchArea;
    }

    public void setSelectResearchArea(Boolean selectResearchArea) {
        this.selectResearchArea = selectResearchArea;
    }
}