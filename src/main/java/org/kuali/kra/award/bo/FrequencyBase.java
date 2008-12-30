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

package org.kuali.kra.award.bo;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import java.util.LinkedHashMap;
import java.sql.Date;

public class FrequencyBase extends KraPersistableBusinessObjectBase { 
	
	private Integer frequencyBaseCode; 
	private String description; 
	
	/*private TemplateReportTerms templateReportTerms; 
	private ValidFrequencyBase validFrequencyBase; 
	private AwardReporting awardReporting; 
	private AwardReportTerms awardReportTerms;*/ 
	
	public FrequencyBase() { 

	} 
	
	public Integer getFrequencyBaseCode() {
		return frequencyBaseCode;
	}

	public void setFrequencyBaseCode(Integer frequencyBaseCode) {
		this.frequencyBaseCode = frequencyBaseCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*public TemplateReportTerms getTemplateReportTerms() {
		return templateReportTerms;
	}

	public void setTemplateReportTerms(TemplateReportTerms templateReportTerms) {
		this.templateReportTerms = templateReportTerms;
	}

	public ValidFrequencyBase getValidFrequencyBase() {
		return validFrequencyBase;
	}

	public void setValidFrequencyBase(ValidFrequencyBase validFrequencyBase) {
		this.validFrequencyBase = validFrequencyBase;
	}

	public AwardReporting getAwardReporting() {
		return awardReporting;
	}

	public void setAwardReporting(AwardReporting awardReporting) {
		this.awardReporting = awardReporting;
	}

	public AwardReportTerms getAwardReportTerms() {
		return awardReportTerms;
	}

	public void setAwardReportTerms(AwardReportTerms awardReportTerms) {
		this.awardReportTerms = awardReportTerms;
	}*/

	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("frequencyBaseCode", getFrequencyBaseCode());
		hashMap.put("description", getDescription());
		return hashMap;
	}
	
}