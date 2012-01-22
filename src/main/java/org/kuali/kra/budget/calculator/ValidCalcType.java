/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.budget.calculator;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.rates.RateClass;
import org.kuali.kra.budget.rates.RateClassType;
import org.kuali.kra.budget.rates.RateType;

public class ValidCalcType extends KraPersistableBusinessObjectBase {

    private String calcTypeId;

    private Integer dependentSeqNumber;

    private String rateClassType;

    private String dependentRateClassType;

    private String rateClassCode;

    private String rateTypeCode;

    private RateClassType rateClassTypeRef;

    private RateClassType dependentRateClassTypeRef;

    private RateClass rateClass;

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
}
