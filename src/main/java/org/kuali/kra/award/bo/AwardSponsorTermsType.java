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

public class AwardSponsorTermsType extends KraPersistableBusinessObjectBase { 
	
	private String awardSponsorTermsTypeCode; 
	private String description; 
	
//	private AwardSponsorTerms awardSponsorTerms; 
	
	public AwardSponsorTermsType() { 

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

//	public AwardSponsorTerms getAwardSponsorTerms() {
//		return awardSponsorTerms;
//	}
//
//	public void setAwardSponsorTerms(AwardSponsorTerms awardSponsorTerms) {
//		this.awardSponsorTerms = awardSponsorTerms;
//	}

	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("awardSponsorTermsTypeCode", getAwardSponsorTermsTypeCode());
		hashMap.put("description", getDescription());
		return hashMap;
	}
	
}