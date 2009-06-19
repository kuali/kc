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

import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

@Entity 
@Table(name="PROTOCOL_REFERENCE_TYPE")
public class ProtocolReferenceType extends KraPersistableBusinessObjectBase { 
	
    @Id 
    @Column(name="PROTOCOL_REFERENCE_TYPE_CODE")
	private Integer protocolReferenceTypeCode; 
    
    @Column(name="DESCRIPTION")
	private String description; 
	
	public ProtocolReferenceType() { 

	} 
	
	public Integer getProtocolReferenceTypeCode() {
		return protocolReferenceTypeCode;
	}

	public void setProtocolReferenceTypeCode(Integer protocolReferenceTypeCode) {
		this.protocolReferenceTypeCode = protocolReferenceTypeCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("protocolReferenceTypeCode", getProtocolReferenceTypeCode());
		hashMap.put("description", getDescription());
		return hashMap;
	}
	
}