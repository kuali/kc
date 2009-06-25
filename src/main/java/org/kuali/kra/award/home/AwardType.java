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
package org.kuali.kra.award.home;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * Award Type business object
 * 
 * @author Kuali Coeus development team (kc.dev@kuali.org)
 */
public class AwardType extends KraPersistableBusinessObjectBase { 
	
    private static final long serialVersionUID = 1652576733758069217L;
    
    private Integer awardTypeCode; 
	private String description; 
	
	public AwardType() { 

	} 
	
	public Integer getAwardTypeCode() {
		return awardTypeCode;
	}

	public void setAwardTypeCode(Integer awardTypeCode) {
		this.awardTypeCode = awardTypeCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override 
	protected LinkedHashMap<String,Object> toStringMapper() {
		LinkedHashMap<String,Object> hashMap = new LinkedHashMap<String,Object>();
		hashMap.put("awardTypeCode", getAwardTypeCode());
		hashMap.put("description", getDescription());
		return hashMap;
	}

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((awardTypeCode == null) ? 0 : awardTypeCode.hashCode());
        result = PRIME * result + ((description == null) ? 0 : description.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final AwardType other = (AwardType) obj;
        if (awardTypeCode == null) {
            if (other.awardTypeCode != null)
                return false;
        }
        else if (!awardTypeCode.equals(other.awardTypeCode))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        }
        else if (!description.equals(other.description))
            return false;
        return true;
    }
    
}
