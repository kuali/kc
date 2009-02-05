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

public class AwardSponsorTerms extends KraPersistableBusinessObjectBase { 
	
	/**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -7264877348038592021L;
    private Integer awardSponsorTermsId; 
	private String awardSponsorTermsCode; 
	private String awardSponsorTermsTypeCode; 
	private String description; 
	
	private AwardSponsorTermsType awardSponsorTermsType; 
//	private AwardTemplateTerms awardTemplateTerms; 
	
	public AwardSponsorTerms() { 

	} 
	
	public Integer getAwardSponsorTermsId() {
		return awardSponsorTermsId;
	}

	public void setAwardSponsorTermsId(Integer awardSponsorTermsId) {
		this.awardSponsorTermsId = awardSponsorTermsId;
	}

	public String getAwardSponsorTermsCode() {
		return awardSponsorTermsCode;
	}

	public void setAwardSponsorTermsCode(String awardSponsorTermsCode) {
		this.awardSponsorTermsCode = awardSponsorTermsCode;
	}

	public String getAwardSponsorTermsTypeCode() {
		return awardSponsorTermsTypeCode;
	}

	public void setAwardSponsorTermsTypeCode(String awardSponsorTermsTypeCode) {
		this.awardSponsorTermsTypeCode = awardSponsorTermsTypeCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AwardSponsorTermsType getAwardSponsorTermsType() {
		return awardSponsorTermsType;
	}

	public void setAwardSponsorTermsType(AwardSponsorTermsType awardSponsorTermsType) {
		this.awardSponsorTermsType = awardSponsorTermsType;
	}

//	public AwardTemplateTerms getAwardTemplateTerms() {
//		return awardTemplateTerms;
//	}
//
//	public void setAwardTemplateTerms(AwardTemplateTerms awardTemplateTerms) {
//		this.awardTemplateTerms = awardTemplateTerms;
//	}

	@SuppressWarnings("unchecked")
    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("awardSponsorTermsId", getAwardSponsorTermsId());
		hashMap.put("awardSponsorTermsCode", getAwardSponsorTermsCode());
		hashMap.put("awardSponsorTermsTypeCode", getAwardSponsorTermsTypeCode());
		hashMap.put("description", getDescription());
		return hashMap;
	}
	
}