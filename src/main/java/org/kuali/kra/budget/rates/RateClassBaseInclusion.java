/* Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.budget.rates;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class RateClassBaseInclusion extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 1L;

    private Long rateClassBaseInclusionId; 
    private String rateClassCode; 
    private String rateTypeCode; 
    private String rateClassCodeIncl; 
    private String rateTypeCodeIncl; 
    
    private RateType rateType; 
    private RateType rateTypeInclusion;
    
    public RateClassBaseInclusion() { 

    } 
    
    public Long getRateClassBaseInclusionId() {
        return rateClassBaseInclusionId;
    }

    public void setRateClassBaseInclusionId(Long rateClassBaseInclusionId) {
        this.rateClassBaseInclusionId = rateClassBaseInclusionId;
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

    public String getRateClassCodeIncl() {
        return rateClassCodeIncl;
    }

    public void setRateClassCodeIncl(String rateClassCodeIncl) {
        this.rateClassCodeIncl = rateClassCodeIncl;
    }

    public String getRateTypeCodeIncl() {
        return rateTypeCodeIncl;
    }

    public void setRateTypeCodeIncl(String rateTypeCodeIncl) {
        this.rateTypeCodeIncl = rateTypeCodeIncl;
    }

    public RateType getRateType() {
        return rateType;
    }

    public void setRateType(RateType rateType) {
        this.rateType = rateType;
    }

    /**
     * Gets the rateTypeInclusion attribute. 
     * @return Returns the rateTypeInclusion.
     */
    public RateType getRateTypeInclusion() {
        return rateTypeInclusion;
    }

    /**
     * Sets the rateTypeInclusion attribute value.
     * @param rateTypeInclusion The rateTypeInclusion to set.
     */
    public void setRateTypeInclusion(RateType rateTypeInclusion) {
        this.rateTypeInclusion = rateTypeInclusion;
    }

}