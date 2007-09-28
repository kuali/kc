/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class NarrativeUserRights extends KraPersistableBusinessObjectBase {
	private Integer moduleNumber;
	private Integer proposalNumber;
	private String userId;
	private Boolean accessType;

	public Integer getModuleNumber() {
		return moduleNumber;
	}

	public void setModuleNumber(Integer moduleNumber) {
		this.moduleNumber = moduleNumber;
	}

	public Integer getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(Integer proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Boolean getAccessType() {
		return accessType;
	}

	public void setAccessType(Boolean accessType) {
		this.accessType = accessType;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("moduleNumber", getModuleNumber());
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("userId", getUserId());
		hashMap.put("accessType", getAccessType());
		return hashMap;
	}
}
