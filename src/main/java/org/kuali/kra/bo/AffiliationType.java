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

package org.kuali.kra.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.irb.bo.ProtocolInvestigator;
import org.kuali.kra.irb.bo.ProtocolKeyPerson;

public class AffiliationType extends KraPersistableBusinessObjectBase { 
	
	private Integer affiliationTypeCode; 
	private String description; 
	
	private ProtocolInvestigator protocolInvestigator; 
	private ProtocolKeyPerson protocolKeyPerson; 
	
	public AffiliationType() { 

	} 
	
	public Integer getAffiliationTypeCode() {
		return affiliationTypeCode;
	}

	public void setAffiliationTypeCode(Integer affiliationTypeCode) {
		this.affiliationTypeCode = affiliationTypeCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProtocolInvestigator getProtocolInvestigator() {
		return protocolInvestigator;
	}

	public void setProtocolInvestigator(ProtocolInvestigator protocolInvestigator) {
		this.protocolInvestigator = protocolInvestigator;
	}

	public ProtocolKeyPerson getProtocolKeyPersons() {
		return protocolKeyPerson;
	}

	public void setProtocolKeyPersons(ProtocolKeyPerson protocolKeyPersons) {
		this.protocolKeyPerson = protocolKeyPerson;
	}

	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("affiliationTypeCode", getAffiliationTypeCode());
		hashMap.put("description", getDescription());
		return hashMap;
	}
	
}