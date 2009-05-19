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

package org.kuali.kra.irb.actions.submit;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class ProtocolSubmissionType extends KraPersistableBusinessObjectBase { 
	
    public static final String INITIAL_SUBMISSION = "100";
    public static final String CONTINUATION = "101";
    public static final String AMENDMENT = "102";
    public static final String RESPONSE_TO_PREV_IRB_NOTIFICATION = "103";
	public static final String REQUEST_TO_CLOSE = "109";
	public static final String CONTINUATION_WITH_AMENDMENT = "115";
    public static final String REQUEST_FOR_SUSPENSION = "110";
	
    private String submissionTypeCode; 
	private String description; 
	
	public ProtocolSubmissionType() { 

	} 
	
	public String getSubmissionTypeCode() {
		return submissionTypeCode;
	}

	public void setSubmissionTypeCode(String submissionTypeCode){
		this.submissionTypeCode = submissionTypeCode;
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
		hashMap.put("submissionTypeCode", getSubmissionTypeCode());
		hashMap.put("description", getDescription());
		return hashMap;
	}
}