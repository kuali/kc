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

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Rolodex;

public class AwardTemplateContact extends KraPersistableBusinessObjectBase { 
	
	private Integer templateContactId; 
//	private Integer templateCode; 
	private String contactTypeCode; 
	private Integer rolodexId; 
	
	private ContactType contactType; 
	private Rolodex rolodex; 
	private AwardTemplate awardTemplate; 
	
	public AwardTemplateContact() { 

	} 
	
	public Integer getTemplateContactId() {
		return templateContactId;
	}

	public void setTemplateContactId(Integer templateContactId) {
		this.templateContactId = templateContactId;
	}

//	public Integer getTemplateCode() {
//		return templateCode;
//	}
//
//	public void setTemplateCode(Integer templateCode) {
//		this.templateCode = templateCode;
//	}

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

	public ContactType getContactType() {
		return contactType;
	}

	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
	}

	public Rolodex getRolodex() {
		return rolodex;
	}

	public void setRolodex(Rolodex rolodex) {
		this.rolodex = rolodex;
	}

	public AwardTemplate getAwardTemplate() {
		return awardTemplate;
	}

	public void setAwardTemplate(AwardTemplate awardTemplate) {
		this.awardTemplate = awardTemplate;
	}

	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("templateContactId", getTemplateContactId());
//		hashMap.put("templateCode", getTemplateCode());
//		hashMap.put("contactTypeCode", getContactTypeCode());
//		hashMap.put("rolodexId", getRolodexId());
		return hashMap;
	}
	
}