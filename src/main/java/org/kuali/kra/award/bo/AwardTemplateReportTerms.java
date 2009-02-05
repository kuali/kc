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
import org.kuali.kra.bo.Rolodex;

import java.util.LinkedHashMap;
import java.sql.Date;

public class AwardTemplateReportTerms extends KraPersistableBusinessObjectBase { 
	
	/**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5576147195207094418L;
    private Integer templateReportTermsId; 
	private String reportClassCode; 
	private String reportCode; 
	private String frequencyCode; 
	private String frequencyBaseCode; 
	private String ospDistributionCode; 
	private Date dueDate; 
	private String contactTypeCode; 
	private Integer rolodexId; 
	private Integer numberOfCopies; 
	
	private ContactType contactType; 
	private Distribution distribution; 
	private Frequency frequency; 
	private FrequencyBase frequencyBase; 
	private Report report; 
	private ReportClass reportClass; 
	private Rolodex rolodex; 
	private AwardTemplate template; 
	
	public AwardTemplateReportTerms() { 

	} 
	
	public Integer getTemplateReportTermsId() {
		return templateReportTermsId;
	}

	public void setTemplateReportTermsId(Integer templateReportTermsId) {
		this.templateReportTermsId = templateReportTermsId;
	}

	public String getReportClassCode() {
		return reportClassCode;
	}

	public void setReportClassCode(String reportClassCode) {
		this.reportClassCode = reportClassCode;
	}

	public String getReportCode() {
		return reportCode;
	}

	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}

	public String getFrequencyCode() {
		return frequencyCode;
	}

	public void setFrequencyCode(String frequencyCode) {
		this.frequencyCode = frequencyCode;
	}

	public String getFrequencyBaseCode() {
		return frequencyBaseCode;
	}

	public void setFrequencyBaseCode(String frequencyBaseCode) {
		this.frequencyBaseCode = frequencyBaseCode;
	}

	public String getOspDistributionCode() {
		return ospDistributionCode;
	}

	public void setOspDistributionCode(String ospDistributionCode) {
		this.ospDistributionCode = ospDistributionCode;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getContactTypeCode() {
		return contactTypeCode;
	}

	public void setContactTypeCode(String contactTypeCode) {
		this.contactTypeCode = contactTypeCode;
	}

	public Integer getRolodexId() {
		return rolodexId;
	}

	public void setRolodexId(Integer rolodexId) {
		this.rolodexId = rolodexId;
	}

	public Integer getNumberOfCopies() {
		return numberOfCopies;
	}

	public void setNumberOfCopies(Integer numberOfCopies) {
		this.numberOfCopies = numberOfCopies;
	}

	public ContactType getContactType() {
		return contactType;
	}

	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
	}

	public Distribution getDistribution() {
		return distribution;
	}

	public void setDistribution(Distribution distribution) {
		this.distribution = distribution;
	}

	public Frequency getFrequency() {
		return frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}

	public FrequencyBase getFrequencyBase() {
		return frequencyBase;
	}

	public void setFrequencyBase(FrequencyBase frequencyBase) {
		this.frequencyBase = frequencyBase;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public ReportClass getReportClass() {
		return reportClass;
	}

	public void setReportClass(ReportClass reportClass) {
		this.reportClass = reportClass;
	}

	public Rolodex getRolodex() {
		return rolodex;
	}

	public void setRolodex(Rolodex rolodex) {
		this.rolodex = rolodex;
	}

	public AwardTemplate getTemplate() {
		return template;
	}

	public void setTemplate(AwardTemplate template) {
		this.template = template;
	}

	@SuppressWarnings("unchecked")
    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("templateReportTermsId", getTemplateReportTermsId());
		hashMap.put("reportClassCode", getReportClassCode());
		hashMap.put("reportCode", getReportCode());
		hashMap.put("frequencyCode", getFrequencyCode());
		hashMap.put("frequencyBaseCode", getFrequencyBaseCode());
		hashMap.put("ospDistributionCode", getOspDistributionCode());
		hashMap.put("dueDate", getDueDate());
		hashMap.put("contactTypeCode", getContactTypeCode());
		hashMap.put("rolodexId", getRolodexId());
		hashMap.put("numberOfCopies", getNumberOfCopies());
		return hashMap;
	}
	
}