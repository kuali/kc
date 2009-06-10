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

public class ProtocolSubmissionQualifierType extends KraPersistableBusinessObjectBase { 
	
    public static final String ANNUAL_SCHEDULED_BY_IRB = "2"; 
    public static final String CONTINGENT = "3";
    public static final String ELIGIBILITY_DEVIATIONS = "4";
    public static final String AE_UADE = "5";
    public static final String COMPLAINT = "6";
    public static final String DEVIATION = "7";
    public static final String COI_REPORT = "8";
    public static final String SELF_REPORT_NON_COMPLIANCE = "9";
    public static final String REQUEST_FOR_ELIGIBILITY_EX = "10";
    public static final String TRAINING_CERTIFICATION = "11";
    public static final String UNANTICIPATED_PROBLEMS = "12";
    public static final String DSMB_REPORT = "13";
    public static final String ANNUAL_REPORT = "14";
    
	private String submissionQualifierTypeCode; 
	private String description; 
	
	public ProtocolSubmissionQualifierType() { 

	} 
	
	public String getSubmissionQualifierTypeCode() {
		return submissionQualifierTypeCode;
	}

	public void setSubmissionQualifierTypeCode(String submissionQualifierTypeCode){
		this.submissionQualifierTypeCode = submissionQualifierTypeCode;
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
		hashMap.put("submissionQualifierTypeCode", getSubmissionQualifierTypeCode());
		hashMap.put("description", getDescription());
		return hashMap;
	}
}