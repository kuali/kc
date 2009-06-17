/*
 * Copyright 2006-2009 The Kuali Foundation
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
/**
 * 
 * This class represents S2S_SUBMISSION_TYPE record
 */
public class S2sSubmissionType extends KraPersistableBusinessObjectBase {
	
	private String s2sSubmissionTypeCode;
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getS2sSubmissionTypeCode() {
		return s2sSubmissionTypeCode;
	}
	public void setS2sSubmissionTypeCode(String s2sSubmissionTypeCode) {
		this.s2sSubmissionTypeCode = s2sSubmissionTypeCode;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap propMap = new LinkedHashMap();
		propMap.put("s2sSubmissionTypeCode", getS2sSubmissionTypeCode());
		propMap.put("description", getDescription());
		return propMap;
	}
}
