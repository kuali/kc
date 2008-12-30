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

public class ContactType extends KraPersistableBusinessObjectBase { 
	
	private Integer contactTypeCode; 
	private String description; 
	
	/*private SubcontractContact subcontractContact; 
	private TemplateReportTerms templateReportTerms; 
	private TemplateContact templateContact; 
	private AwardContact awardContact; 
	private AwardReportTerms awardReportTerms;*/ 
	
	public ContactType() { 

	} 
	
	public Integer getContactTypeCode() {
		return contactTypeCode;
	}

	public void setContactTypeCode(Integer contactTypeCode) {
		this.contactTypeCode = contactTypeCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*public SubcontractContact getSubcontractContact() {
		return subcontractContact;
	}

	public void setSubcontractContact(SubcontractContact subcontractContact) {
		this.subcontractContact = subcontractContact;
	}

	public TemplateReportTerms getTemplateReportTerms() {
		return templateReportTerms;
	}

	public void setTemplateReportTerms(TemplateReportTerms templateReportTerms) {
		this.templateReportTerms = templateReportTerms;
	}

	public TemplateContact getTemplateContact() {
		return templateContact;
	}

	public void setTemplateContact(TemplateContact templateContact) {
		this.templateContact = templateContact;
	}

	public AwardContact getAwardContact() {
		return awardContact;
	}

	public void setAwardContact(AwardContact awardContact) {
		this.awardContact = awardContact;
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
		hashMap.put("contactTypeCode", getContactTypeCode());
		hashMap.put("description", getDescription());
		return hashMap;
	}
	
}