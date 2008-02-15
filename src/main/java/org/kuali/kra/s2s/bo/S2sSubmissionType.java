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
package org.kuali.kra.s2s.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class S2sSubmissionType extends KraPersistableBusinessObjectBase {
	private String s2sSubmissionTypeCode;
	private String description;

	public String getS2sSubmissionTypeCode() {
		return s2sSubmissionTypeCode;
	}

	public void setS2sSubmissionTypeCode(String s2sSubmissionTypeCode) {
		this.s2sSubmissionTypeCode = s2sSubmissionTypeCode;
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
		hashMap.put("s2sSubmissionTypeCode", getS2sSubmissionTypeCode());
		hashMap.put("description", getDescription());
		hashMap.put("updateTimestamp", this.getUpdateTimestamp());
		hashMap.put("updateUser", this.getUpdateUser());
		return hashMap;
	}
}
