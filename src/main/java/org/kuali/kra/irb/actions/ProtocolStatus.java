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

package org.kuali.kra.irb.actions;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class ProtocolStatus extends KraPersistableBusinessObjectBase { 
	
    public static final String IN_PROGRESS = "100";
    public static final String SUBMITTED_TO_IRB = "101";
    public static final String DELETED = "303";
    
	private String protocolStatusCode; 
	private String description; 
	
	public ProtocolStatus() { 
	}
		
	public String getProtocolStatusCode() {
		return protocolStatusCode;
	}

	public void setProtocolStatusCode(String protocolStatusCode){
		this.protocolStatusCode = protocolStatusCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("protocolStatusCode", getProtocolStatusCode());
		hashMap.put("description", getDescription());
		return hashMap;
	}
	
}