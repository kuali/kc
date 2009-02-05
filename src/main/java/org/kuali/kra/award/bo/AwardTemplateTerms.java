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

public class AwardTemplateTerms extends KraPersistableBusinessObjectBase { 
	
	/**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 737831469929642714L;
    private Integer awardTemplateTermsId; 
//	private Integer awardTemplateCode; 
	private Integer awardSponsorTermsId; 
    private String awardSponsorTermsTypeCode; 
	
	private AwardTemplate awardTemplate; 
	private AwardSponsorTerms awardSponsorTerms; 
	
	public AwardTemplateTerms() { 

	} 
	
	public Integer getAwardTemplateTermsId() {
		return awardTemplateTermsId;
	}

	public void setAwardTemplateTermsId(Integer awardTemplateTermsId) {
		this.awardTemplateTermsId = awardTemplateTermsId;
	}

//	public Integer getAwardTemplateCode() {
//		return awardTemplateCode;
//	}
//
//	public void setAwardTemplateCode(Integer awardTemplateCode) {
//		this.awardTemplateCode = awardTemplateCode;
//	}

	public Integer getAwardSponsorTermsId() {
		return awardSponsorTermsId;
	}

	public void setAwardSponsorTermsId(Integer awardSponsorTermsId) {
		this.awardSponsorTermsId = awardSponsorTermsId;
	}

	public AwardTemplate getAwardTemplate() {
		return awardTemplate;
	}

	public void setAwardTemplate(AwardTemplate awardTemplate) {
		this.awardTemplate = awardTemplate;
	}

	public AwardSponsorTerms getAwardSponsorTerms() {
		return awardSponsorTerms;
	}

	public void setAwardSponsorTerms(AwardSponsorTerms awardSponsorTerms) {
		this.awardSponsorTerms = awardSponsorTerms;
	}

	@SuppressWarnings("unchecked")
    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("awardTemplateTermsId", getAwardTemplateTermsId());
//		hashMap.put("awardTemplateCode", getAwardTemplateCode());
		hashMap.put("awardSponsorTermsId", getAwardSponsorTermsId());
		return hashMap;
	}

    /**
     * Gets the awardSponsorTermsTypeCode attribute. 
     * @return Returns the awardSponsorTermsTypeCode.
     */
    public String getAwardSponsorTermsTypeCode() {
        return awardSponsorTermsTypeCode;
    }

    /**
     * Sets the awardSponsorTermsTypeCode attribute value.
     * @param awardSponsorTermsTypeCode The awardSponsorTermsTypeCode to set.
     */
    public void setAwardSponsorTermsTypeCode(String awardSponsorTermsTypeCode) {
        this.awardSponsorTermsTypeCode = awardSponsorTermsTypeCode;
    }
	
}