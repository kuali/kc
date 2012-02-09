/*
 * Copyright 2005-2010 The Kuali Foundation
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


public class RateClassBaseExclusion extends KraPersistableBusinessObjectBase { 
    
    

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5945293631529734148L;
    private Long rateClassBaseExclusionId; 
    private String rateClassCode; 
    private String rateTypeCode; 
    private String rateClassCodeExcl; 
    private String rateTypeCodeExcl; 
    
    private RateType rateType; 
    private RateType rateTypeExclusion;
    
    public RateClassBaseExclusion() { 

    } 
    
    public Long getRateClassBaseExclusionId() {
        return rateClassBaseExclusionId;
    }

    public void setRateClassBaseExclusionId(Long rateClassBaseExclusionId) {
        this.rateClassBaseExclusionId = rateClassBaseExclusionId;
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

    public String getRateClassCodeExcl() {
        return rateClassCodeExcl;
    }

    public void setRateClassCodeExcl(String rateClassCodeExcl) {
        this.rateClassCodeExcl = rateClassCodeExcl;
    }

    public String getRateTypeCodeExcl() {
        return rateTypeCodeExcl;
    }

    public void setRateTypeCodeExcl(String rateTypeCodeExcl) {
        this.rateTypeCodeExcl = rateTypeCodeExcl;
    }

    public RateType getRateType() {
        return rateType;
    }

    public void setRateType(RateType rateType) {
        this.rateType = rateType;
    }

    /**
     * Gets the rateTypeExclusion attribute. 
     * @return Returns the rateTypeExclusion.
     */
    public RateType getRateTypeExclusion() {
        return rateTypeExclusion;
    }

    /**
     * Sets the rateTypeExclusion attribute value.
     * @param rateTypeExclusion The rateTypeExclusion to set.
     */
    public void setRateTypeExclusion(RateType rateTypeExclusion) {
        this.rateTypeExclusion = rateTypeExclusion;
    }
    
}