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

import java.util.LinkedHashMap;

import org.kuali.kra.bo.AffiliationType;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.irb.document.ProtocolDocument;

public class ProtocolKeyPerson extends KraPersistableBusinessObjectBase { 
	
	private Integer protocolKeyPersonsId; 
	private Long protocolId; 
	private String protocolNumber; 
	private Integer sequenceNumber; 
	private String personId; 
	private String personName; 
	private String personRole; 
	private boolean nonEmployeeFlag; 
	private Integer affiliationTypeCode; 
	
	private ProtocolDocument protocolDocument; 
	private AffiliationType affiliationType; 
	
	public ProtocolKeyPerson() { 

	} 
	
	public Integer getProtocolKeyPersonsId() {
		return protocolKeyPersonsId;
	}

	public void setProtocolKeyPersonsId(Integer protocolKeyPersonsId) {
		this.protocolKeyPersonsId = protocolKeyPersonsId;
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

	public String getPersonRole() {
		return personRole;
	}

	public void setPersonRole(String personRole) {
		this.personRole = personRole;
	}

	public boolean getNonEmployeeFlag() {
		return nonEmployeeFlag;
	}

	public void setNonEmployeeFlag(boolean nonEmployeeFlag) {
		this.nonEmployeeFlag = nonEmployeeFlag;
	}

	public Integer getAffiliationTypeCode() {
		return affiliationTypeCode;
	}

	public void setAffiliationTypeCode(Integer affiliationTypeCode) {
		this.affiliationTypeCode = affiliationTypeCode;
	}

	public ProtocolDocument getProtocol() {
		return protocolDocument;
	}

	public void setProtocol(ProtocolDocument protocol) {
		this.protocolDocument = protocolDocument;
	}

	public AffiliationType getAffiliationType() {
		return affiliationType;
	}

	public void setAffiliationType(AffiliationType affiliationType) {
		this.affiliationType = affiliationType;
	}

	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("protocolKeyPersonsId", getProtocolKeyPersonsId());
		hashMap.put("protocolId", getProtocolId());
		hashMap.put("protocolNumber", getProtocolNumber());
		hashMap.put("sequenceNumber", getSequenceNumber());
		hashMap.put("personId", getPersonId());
		hashMap.put("personName", getPersonName());
		hashMap.put("personRole", getPersonRole());
		hashMap.put("nonEmployeeFlag", getNonEmployeeFlag());
		hashMap.put("affiliationTypeCode", getAffiliationTypeCode());
		return hashMap;
	}
	
}