/*
 * Copyright 2007 The Kuali Foundation.
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
package org.kuali.kra.budget.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class ValidCalcType extends KraPersistableBusinessObjectBase {
	private String calcTypeId;
	private Integer dependentSeqNumber;
	private String rateClassType;
	private String dependentRateClassType;
	private String rateClassCode;
	private String rateTypeCode;

	public String getCalcTypeId() {
		return calcTypeId;
	}

	public void setCalcTypeId(String calcTypeId) {
		this.calcTypeId = calcTypeId;
	}

	public Integer getDependentSeqNumber() {
		return dependentSeqNumber;
	}

	public void setDependentSeqNumber(Integer dependentSeqNumber) {
		this.dependentSeqNumber = dependentSeqNumber;
	}

	public String getRateClassType() {
		return rateClassType;
	}

	public void setRateClassType(String rateClassType) {
		this.rateClassType = rateClassType;
	}

	public String getDependentRateClassType() {
		return dependentRateClassType;
	}

	public void setDependentRateClassType(String dependentRateClassType) {
		this.dependentRateClassType = dependentRateClassType;
	}

	public String getRateClassCode() {
		return rateClassCode;
	}

	public void setRateClassCode(String rateClassCode) {
		this.rateClassCode = rateClassCode;
	}

	public String getRateTypeCode() {
		return rateTypeCode;
	}

	public void setRateTypeCode(String rateTypeCode) {
		this.rateTypeCode = rateTypeCode;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("calcTypeId", getCalcTypeId());
		hashMap.put("dependentSeqNumber", getDependentSeqNumber());
		hashMap.put("rateClassType", getRateClassType());
		hashMap.put("dependentRateClassType", getDependentRateClassType());
		hashMap.put("rateClassCode", getRateClassCode());
		hashMap.put("rateTypeCode", getRateTypeCode());
		return hashMap;
	}
}
