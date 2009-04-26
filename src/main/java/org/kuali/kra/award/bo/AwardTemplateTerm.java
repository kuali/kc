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

import org.kuali.kra.bo.SponsorTermType;

public class AwardTemplateTerm extends AwardTermBase { 
	
	/**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 737831469929642714L;
    private Integer awardTemplateTermId; 
	private String sponsorTermTypeCode;
	private AwardTemplate awardTemplate; 
    private SponsorTermType sponsorTermType; 
	
	public AwardTemplateTerm() { 

	} 
	
	public Integer getAwardTemplateTermId() {
		return awardTemplateTermId;
	}

	public void setAwardTemplateTermId(Integer awardTemplateTermId) {
		this.awardTemplateTermId = awardTemplateTermId;
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
		hashMap.put("awardTemplateTermsId", getAwardTemplateTermId());
		return hashMap;
	}


    /**
     * Gets the sponsorTermsTypeCode attribute. 
     * @return Returns the sponsorTermsTypeCode.
     */
    public String getSponsorTermTypeCode() {
        return sponsorTermTypeCode;
    }

    /**
     * Sets the sponsorTermsTypeCode attribute value.
     * @param sponsorTermsTypeCode The sponsorTermsTypeCode to set.
     */
    public void setSponsorTermTypeCode(String sponsorTermTypeCode) {
        this.sponsorTermTypeCode = sponsorTermTypeCode;
    }

    /**
     * Gets the sponsorTermsType attribute. 
     * @return Returns the sponsorTermsType.
     */
    public SponsorTermType getSponsorTermType() {
        return sponsorTermType;
    }

    /**
     * Sets the sponsorTermsType attribute value.
     * @param sponsorTermsType The sponsorTermsType to set.
     */
    public void setSponsorTermType(SponsorTermType sponsorTermType) {
        this.sponsorTermType = sponsorTermType;
    }
	
}