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
package org.kuali.kra.budget.bo;

import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

@Entity
@Table(name="RATE_CLASS")
public class RateClass extends KraPersistableBusinessObjectBase {
    
	@Id
	@Column(name="RATE_CLASS_CODE")
	private String rateClassCode;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="RATE_CLASS_TYPE")
	private String rateClassType;
	
	@OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="RATE_CLASS_TYPE", insertable=false, updatable=false)
	private RateClassType rateClassTypeT;
	
	@Transient
    private String rateClassTypeDescription;
    
	public String getRateClassCode() {
		return rateClassCode;
	}

	public void setRateClassCode(String rateClassCode) {
		this.rateClassCode = rateClassCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRateClassType() {
		return rateClassType;
	}

	public void setRateClassType(String rateClassType) {
		this.rateClassType = rateClassType;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("rateClassCode", getRateClassCode());
		hashMap.put("description", getDescription());
		hashMap.put("rateClassType", getRateClassType());
		return hashMap;
	}

    public String getRateClassTypeDescription() {
        return rateClassTypeDescription;
    }

    public void setRateClassTypeDescription(String rateClassTypeDescription) {
        this.rateClassTypeDescription = rateClassTypeDescription;
    }

    public RateClassType getRateClassTypeT() {
        return rateClassTypeT;
    }

    public void setRateClassTypeT(RateClassType rateClassTypeT) {
        this.rateClassTypeT = rateClassTypeT;
    }
}

