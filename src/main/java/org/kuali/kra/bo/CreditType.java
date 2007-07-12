/*
 * Copyright 2006-2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
 * Class representation of the Credit Type Business Object
 *
 * $Id: CreditType.java,v 1.1 2007-07-12 18:56:37 lprzybyl Exp $
 */
public class CreditType extends KraPersistableBusinessObjectBase {
	
	private String creditTypeCode;
    private Boolean addsToHundred;
	private String description;
	
    /**
     * Retrieves the description attribute
     * 
     * @return String
     */
	public String getDescription() {
		return description;
	}
    
    /**
     * Assigns the description attribute
     *
     * @param description
     */
	public void setDescription(String description) {
		this.description = description;
	}

    /**
     * Retrieves the credit type code attribute from the credit type bo
     *
     * @return String
     */
	public String getCreditTypeCode() {
		return creditTypeCode;
	}
    
    /**
     * Assigns the credit type code attribute to the credit type bo
     *
     * @param creditTypeCode
     */
	public void setCreditTypeCode(String creditTypeCode) {
		this.creditTypeCode = creditTypeCode;
	}

    /**
     * Gets the value of addsToHundred
     *
     * @return the value of addsToHundred
     */
    public final Boolean isAddsToHundred() {
        return this.addsToHundred;
    }

    /**
     * Sets the value of addsToHundred
     *
     * @param argAddsToHundred Value to assign to this.addsToHundred
     */
    public final void setAddsToHundred(final Boolean argAddsToHundred) {
        this.addsToHundred = argAddsToHundred;
    }    

	@Override
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap propMap = new LinkedHashMap();
		propMap.put("creditTypeCode", this.getCreditTypeCode());
		propMap.put("description", this.getDescription());
		propMap.put("addsToHundred", this.isAddsToHundred());
		propMap.put("updateTimestamp", this.getUpdateTimestamp());
		propMap.put("updateUser", this.getUpdateUser());
		return propMap;
	}
}
