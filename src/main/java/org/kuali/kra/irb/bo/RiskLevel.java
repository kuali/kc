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

package org.kuali.kra.irb.bo;

import java.util.LinkedHashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

@Entity 
@Table(name="RISK_LEVEL")
public class RiskLevel extends KraPersistableBusinessObjectBase { 
	
	@Id 
	@Column(name="RISK_LEVEL_CODE")
	private Integer riskLevelCode; 
	@Column(name="DESCRIPTION")
	private String description; 
	
/*
	@OneToMany(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="RISK_LEVEL_CODE", insertable=false, updatable=false)
	private ProtocolRiskLevels protocolRiskLevels;
*/	
	
	public RiskLevel() { 

	} 
	
	public Integer getRiskLevelCode() {
		return riskLevelCode;
	}

	public void setRiskLevelCode(Integer riskLevelCode) {
		this.riskLevelCode = riskLevelCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*
	public ProtocolRiskLevels getProtocolRiskLevels() {
		return protocolRiskLevels;
	}

	public void setProtocolRiskLevels(ProtocolRiskLevels protocolRiskLevels) {
		this.protocolRiskLevels = protocolRiskLevels;
	}
*/
	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("riskLevelCode", getRiskLevelCode());
		hashMap.put("description", getDescription());
		return hashMap;
	}
	
}