/*
 * Copyright 2006-2009 The Kuali Foundation
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

package org.kuali.kra.bo;

import java.util.LinkedHashMap;

public class SponsorType extends KraPersistableBusinessObjectBase {
	
	private String sponsorTypeCode;
	private String description;
	
	/**
     * Gets the sponsorTypeCode attribute. 
     * @return Returns the sponsorTypeCode.
     */
    public String getSponsorTypeCode() {
        return sponsorTypeCode;
    }
    /**
     * Sets the sponsorTypeCode attribute value.
     * @param sponsorTypeCode The sponsorTypeCode to set.
     */
    public void setSponsorTypeCode(String sponsorTypeCode) {
        this.sponsorTypeCode = sponsorTypeCode;
    }
    public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap lkupMap = new LinkedHashMap();
		lkupMap.put("sponsorTypeCode", getSponsorTypeCode());
		lkupMap.put("description", getDescription());
		return lkupMap;
	}
}
