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
package org.kuali.kra.irb.protocol;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * 
 * This class represents the Protocol Organization Type Business Object.
 */
public class ProtocolOrganizationType extends KraPersistableBusinessObjectBase { 
	
	/**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 148098563046181725L;
    private String protocolOrganizationTypeCode; 
	private String description; 
	
	/**
	 * Constructs a ProtocolOrganizationType.java.
	 */
	public ProtocolOrganizationType() { 

	} 
	
	/**
	 * This method...
	 * @return
	 */
	public String getProtocolOrganizationTypeCode() {
		return protocolOrganizationTypeCode;
	}

	/**
	 * This method...
	 * @param protocolOrganizationTypeCode
	 */
	public void setProtocolOrganizationTypeCode(String protocolOrganizationTypeCode) {
		this.protocolOrganizationTypeCode = protocolOrganizationTypeCode;
	}

	/**
	 * This method...
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method...
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @see org.kuali.rice.kns.bo.BusinessObjectBase#toStringMapper()
	 */
	@Override 
	protected LinkedHashMap<String,Object> toStringMapper() {
		LinkedHashMap<String,Object> hashMap = new LinkedHashMap<String,Object>();
		hashMap.put("protocolOrganizationTypeCode", getProtocolOrganizationTypeCode());
		hashMap.put("description", getDescription());
		return hashMap;
	}
	
}