/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.common.budget.framework.calculator;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.common.budget.framework.rate.RateClass;
import org.kuali.coeus.common.budget.framework.rate.RateClassType;
import org.kuali.coeus.common.budget.framework.rate.RateType;

public class ValidCalcType extends KcPersistableBusinessObjectBase {

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
