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
package org.kuali.kra.bo;

import java.util.LinkedHashMap;

public class SponsorFormTemplateList extends AbstractSponsorFormTemplate {
    private Boolean selectToPrint = false;

    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("packageNumber", getPackageNumber());
		hashMap.put("pageNumber", getPageNumber());
		hashMap.put("sponsorCode", getSponsorCode());
		hashMap.put("pageDescription", getPageDescription());
		return hashMap;
	}

    public final Boolean getSelectToPrint() {
        return selectToPrint;
    }

    public final void setSelectToPrint(Boolean selectToPrint) {
        this.selectToPrint = selectToPrint;
    }
    
}
