/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.bo;

import java.util.LinkedHashMap;
/**
 * 
 * This is an abstract class for holding common properties of ScienceKeyword
 * 
 */
public abstract class AbstractScienceKeyword extends KraPersistableBusinessObjectBase {

	private String scienceKeywordCode;
	private String scienceKeywordDescription;
	private ScienceKeyword scienceKeyword;
	private Boolean selectKeyword = false;

    /**
     * Gets the scienceKeywordCode attribute. 
     * @return Returns the scienceKeywordCode.
     */
	public String getScienceKeywordCode() {
		return scienceKeywordCode;
	}
	
    /**
     * Sets the scienceKeywordCode attribute value.
     * @param scienceKeywordCode.
     */
	public void setScienceKeywordCode(String scienceCode) {
		this.scienceKeywordCode = scienceCode;
	}

    /**
     * Gets the scienceKeyword attribute. 
     * @return Returns the scienceKeyword.
     */
    public ScienceKeyword getScienceKeyword() {
        return scienceKeyword;
    }
    /**
     * Sets the scienceKeyword attribute value.
     * @param scienceKeyword The scienceKeyword to set.
     */
    public void setScienceKeyword(ScienceKeyword scienceKeyword) {
        this.scienceKeyword = scienceKeyword;
    }
    /**
     * Gets the selectKeyword attribute. 
     * @return Returns the selectKeyword.
     */
    public Boolean getSelectKeyword() {
        return selectKeyword;
    }
    /**
     * Sets the selectKeyword attribute value.
     * @param selectKeyword The selectKeyword to set.
     */
    public void setSelectKeyword(Boolean selectKeyword) {
        this.selectKeyword = selectKeyword;
    }
    /**
     * Gets the scienceKeywordDescription attribute. 
     * @return Returns the scienceKeywordDescription.
     */
    public String getScienceKeywordDescription() {
        return scienceKeywordDescription;
    }
    /**
     * Sets the scienceKeywordDescription attribute value.
     * @param scienceKeywordDescription The scienceKeywordDescription to set.
     */
    public void setScienceKeywordDescription(String scienceKeywordDescription) {
        this.scienceKeywordDescription = scienceKeywordDescription;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap propMap = new LinkedHashMap();
        propMap.put("scienceKeywordCode", this.getScienceKeywordCode());
        propMap.put("description", this.getScienceKeywordDescription());
        propMap.put("updateTimestamp", this.getUpdateTimestamp());
        propMap.put("updateUser", this.getUpdateUser());
        return propMap;
    }
}
