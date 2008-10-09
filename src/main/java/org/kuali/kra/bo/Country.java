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

import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;

import java.util.LinkedHashMap;

/**
 * Class representation of a Country Code Business Object
 */
@Entity
@Table(name="COUNTRY_CODE")
public class Country extends KraPersistableBusinessObjectBase {
	
	@Id
	@Column(name="COUNTRY_CODE")
	private String countryCode;
	@Column(name="COUNTRY_NAME")
	private String countryName;
	
    /**
     * Retrieve Country Name Attribute
     * 
     * @return String
     */
	public String getCountryName() {
		return countryName;
	}

    /**
     * Assign the Country Name Attribute of this BO
     *
     * @param countryName
     */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
    
    /**
     * Retrieve Country Code Attribute
     *
     * @return String
     */
	public String getCountryCode() {
		return countryCode;
	}

    /**
     * Assign the Country Code Attribute 
     *
     * @param countryCode 
     */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

    /**
     * @see org.kuali.core.bo.BusinessObject#toStringMapper()
     */
	@Override
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap propMap = new LinkedHashMap();
		propMap.put("countryCode", this.getCountryCode());
		propMap.put("countryName", this.getCountryName());
		propMap.put("updateTimestamp", this.getUpdateTimestamp());
		propMap.put("updateUser", this.getUpdateUser());
		return propMap;
	}
}

