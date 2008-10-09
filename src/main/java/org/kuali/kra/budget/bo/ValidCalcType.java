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

import java.util.LinkedHashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

@IdClass(org.kuali.kra.budget.bo.id.ValidCalcTypeId.class)
@Entity
@Table(name="VALID_CALC_TYPES")
public class ValidCalcType extends KraPersistableBusinessObjectBase {
	@Id
	@Column(name="CALC_TYPE_ID")
	private String calcTypeId;
	@Id
	@Column(name="DEPENDENT_SEQ_NUMBER")
	private Integer dependentSeqNumber;
	@Id
	@Column(name="RATE_CLASS_TYPE")
	private String rateClassType;
	@Column(name="DEPENDENT_RATE_CLASS_TYPE")
	private String dependentRateClassType;
	@Column(name="RATE_CLASS_CODE")
	private String rateClassCode;
	@Column(name="RATE_TYPE_CODE")
	private String rateTypeCode;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="RATE_CLASS_TYPE", insertable=false, updatable=false)
	private RateClassType rateClassTypeRef;
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="DEPENDENT_RATE_CLASS_TYPE", insertable=false, updatable=false)
	private RateClassType dependentRateClassTypeRef;
    @OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="RATE_CLASS_CODE", insertable=false, updatable=false)
	private RateClass rateClass;
    @OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumns({@JoinColumn(name="RATE_TYPE_CODE", insertable=false, updatable=false), @JoinColumn(name="RATE_CLASS_CODE", insertable=false, updatable=false)})
	private RateType rateType;

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

    public String getDependentRateClassType() {
        return dependentRateClassType;
    }

    public void setDependentRateClassType(String dependentRateClassType) {
        this.dependentRateClassType = dependentRateClassType;
    }

    public RateClassType getDependentRateClassTypeRef() {
        return dependentRateClassTypeRef;
    }

    public void setDependentRateClassTypeRef(RateClassType dependentRateClassTypeRef) {
        this.dependentRateClassTypeRef = dependentRateClassTypeRef;
    }

    public String getRateClassType() {
        return rateClassType;
    }

    public void setRateClassType(String rateClassType) {
        this.rateClassType = rateClassType;
    }

    public RateClassType getRateClassTypeRef() {
        return rateClassTypeRef;
    }

    public void setRateClassTypeRef(RateClassType rateClassTypeRef) {
        this.rateClassTypeRef = rateClassTypeRef;
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
    
    public RateClass getRateClass() {
        return rateClass;
    }

    public void setRateClass(RateClass rateClass) {
        this.rateClass = rateClass;
    }

    public RateType getRateType() {
        return rateType;
    }

    public void setRateType(RateType rateType) {
        this.rateType = rateType;
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

