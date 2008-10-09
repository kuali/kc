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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

@IdClass(org.kuali.kra.budget.bo.id.RateTypeId.class)
@Entity
@Table(name="RATE_TYPE")
public class RateType extends KraPersistableBusinessObjectBase implements Comparable {
    @Id
	@Column(name="RATE_CLASS_CODE")
	private String rateClassCode;
    @Id
	@Column(name="RATE_TYPE_CODE")
	private String rateTypeCode;
    @Column(name="DESCRIPTION")
	private String description;
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="RATE_CLASS_CODE", insertable=false, updatable=false)
	private RateClass rateClass;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("rateClassCode", getRateClassCode());
        hashMap.put("rateTypeCode", getRateTypeCode());
        hashMap.put("description", getDescription());
        return hashMap;
    }

    /**
     * 
     * This is helper method to get prefix for total page display.
     * @return
     */
    public String getRateClassPrefix() {
        this.refreshReferenceObject("rateClass");
        RateClassType rateClassType = getRateClass().getRateClassTypeT();
        return rateClassType.getDescription();
    }

    public RateClass getRateClass() {
        return rateClass;
    }

    public void setRateClass(RateClass rateClass) {
        this.rateClass = rateClass;
    }
    
    /**
     * This is for totals page to sort it by rateclasstypecode
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Object o) {
        return compareTo((RateType) o);
    }
    
    public int compareTo(RateType rateType) {
        rateType.refreshReferenceObject("rateClass");
        this.refreshReferenceObject("rateClass");

        return this.rateClass.getRateClassCode().compareTo(rateType.rateClass.getRateClassCode());
    }

}

