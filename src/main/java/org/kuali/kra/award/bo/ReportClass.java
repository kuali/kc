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

public class ReportClass extends KraPersistableBusinessObjectBase { 
	
	private Integer reportClassCode; 
	private String description; 
	private boolean generateReportRequirements; 
	
	/*private TemplateReportTerms templateReportTerms; 
	private ValidClassReportFreq validClassReportFreq; 
	private AwardReporting awardReporting; 
	private AwardReportTerms awardReportTerms;*/ 
	
	public ReportClass() { 

	} 
	
	public Integer getReportClassCode() {
		return reportClassCode;
	}

	public void setReportClassCode(Integer reportClassCode) {
		this.reportClassCode = reportClassCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getGenerateReportRequirements() {
		return generateReportRequirements;
	}

	public void setGenerateReportRequirements(boolean generateReportRequirements) {
		this.generateReportRequirements = generateReportRequirements;
	}

	/*public TemplateReportTerms getTemplateReportTerms() {
		return templateReportTerms;
	}

	public void setTemplateReportTerms(TemplateReportTerms templateReportTerms) {
		this.templateReportTerms = templateReportTerms;
	}

	public ValidClassReportFreq getValidClassReportFreq() {
		return validClassReportFreq;
	}

	public void setValidClassReportFreq(ValidClassReportFreq validClassReportFreq) {
		this.validClassReportFreq = validClassReportFreq;
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
		hashMap.put("reportClassCode", getReportClassCode());
		hashMap.put("description", getDescription());
		hashMap.put("generateReportRequirements", getGenerateReportRequirements());
		return hashMap;
	}
	
}