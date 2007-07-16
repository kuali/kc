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
 * Class representation of a State Code Business Object
 */
public class State extends KraPersistableBusinessObjectBase {

	private String countryCode;
    private String stateCode;
	private String description;

    private Country country;

    public State() {
        country = new Country();
    }

    /**
     * @see org.kuali.core.bo.BusinessObject#toStringMapper()
     */
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap propMap = new LinkedHashMap();
        propMap.put("countryCode", this.getCountryCode());
        propMap.put("stateCode", this.getStateCode());
        propMap.put("description", this.getDescription());
        propMap.put("updateTimestamp", this.getUpdateTimestamp());
        propMap.put("updateUser", this.getUpdateUser());
        return propMap;
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
     * Assign the State Code Attribute
     *
     * @param stateCode
     */
    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    /**
     * Retrieve State Code Attribute
     *
     * @return String
     */
    public String getStateCode() {
        return stateCode;
    }

    /**
     * Retrieve Country Name Attribute
     *
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Assign the Country Name Attribute of this BO
     *
     * @param countryName
     */
    public void setDescription(String countryName) {
        this.description = countryName;
    }

    /**
     * Gets the country attribute.
     * @return Returns the country.
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Sets the country attribute value.
     * @param country The country to set.
     */
    public void setCountry(Country country) {
        this.country = country;
    }

}
