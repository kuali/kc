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

import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.IdClass;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

@IdClass(org.kuali.kra.budget.bo.id.ValidCeRateTypeId.class)
@Entity
@Table(name="VALID_CE_RATE_TYPES")
public class ValidCeRateType extends KraPersistableBusinessObjectBase {
	@Id
	@Column(name="COST_ELEMENT")
	private String costElement;
	@Id
	@Column(name="RATE_CLASS_CODE")
	private String rateClassCode;
	@Id
	@Column(name="RATE_TYPE_CODE")
	private String rateTypeCode;
	@ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="RATE_CLASS_CODE", insertable=false, updatable=false)
	private RateClass rateClass;
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumns({@JoinColumn(name="RATE_CLASS_CODE", insertable=false, updatable=false), @JoinColumn(name="RATE_TYPE_CODE", insertable=false, updatable=false)})
	private RateType rateType;
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="COST_ELEMENT", insertable=false, updatable=false)
	private CostElement costElementBo;

	/**
     * Gets the rateClass attribute. 
     * @return Returns the rateClass.
     */
    public RateClass getRateClass() {
        return rateClass;
    }

    /**
     * Sets the rateClass attribute value.
     * @param rateClass The rateClass to set.
     */
    public void setRateClass(RateClass rateClass) {
        this.rateClass = rateClass;
    }

    public String getCostElement() {
		return costElement;
	}

	public void setCostElement(String costElement) {
		this.costElement = costElement;
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
    
	public RateType getRateType() {
        return rateType;
    }

    public void setRateType(RateType rateType) {
        this.rateType = rateType;
    }

    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("costElement", getCostElement());
		hashMap.put("rateClassCode", getRateClassCode());
		hashMap.put("rateTypeCode", getRateTypeCode());
        hashMap.put("rateClassType", getRateClassType());
		return hashMap;
	}

    public String getRateClassType() {
        return rateClass.getRateClassType();
    }

    public CostElement getCostElementBo() {
        return costElementBo;
    }

    public void setCostElementBo(CostElement costElementBo) {
        this.costElementBo = costElementBo;
    }
}

