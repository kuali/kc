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

public class AwardTemplateContact extends AwardSponsorContactBase { 
	
	/**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5168275576240665727L;
    private Integer templateContactId; 
	private AwardTemplate awardTemplate; 
	
	public AwardTemplateContact() { 

	} 
	
	public Integer getTemplateContactId() {
		return templateContactId;
	}

	public void setTemplateContactId(Integer templateContactId) {
		this.templateContactId = templateContactId;
	}

	public AwardTemplate getAwardTemplate() {
		return awardTemplate;
	}

	public void setAwardTemplate(AwardTemplate awardTemplate) {
		this.awardTemplate = awardTemplate;
	}

	@SuppressWarnings("unchecked")
    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = super.toStringMapper();
		hashMap.put("templateContactId", getTemplateContactId());
		return hashMap;
	}
	
}