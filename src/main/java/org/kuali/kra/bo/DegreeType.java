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
 * Class representation of the Degree Type Business Object
 *
 * $Id: DegreeType.java,v 1.1 2007-07-11 22:39:34 lprzybyl Exp $
 */
public class DegreeType extends KraPersistableBusinessObjectBase {
	
	private String degreeCode;
    private Integer degreeLevel;
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
     * Retrieves the degree code attribute from the degree type bo
     *
     * @return String
     */
	public String getDegreeCode() {
		return degreeCode;
	}
    
    /**
     * Assigns the degree code attribute to the degree type bo
     *
     * @param degreeCode
     */
	public void setDegreeCode(String degreeCode) {
		this.degreeCode = degreeCode;
	}
    
    /**
     * Gets the value of degreeLevel
     *
     * @return the value of degreeLevel
     */
    public Integer getDegreeLevel() {
        return this.degreeLevel;
    }
    
    /**
     * Sets the value of degreeLevel
     *
     * @param argDegreeLevel Value to assign to this.degreeLevel
     */
    public void setDegreeLevel(Integer argDegreeLevel) {
        this.degreeLevel = argDegreeLevel;
    }


	@Override
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap propMap = new LinkedHashMap();
		propMap.put("degreeCode", this.getDegreeCode());
		propMap.put("degreeLevel", this.getDegreeLevel());
		propMap.put("description", this.getDescription());
		propMap.put("updateTimestamp", this.getUpdateTimestamp());
		propMap.put("updateUser", this.getUpdateUser());
		return propMap;
	}
}
