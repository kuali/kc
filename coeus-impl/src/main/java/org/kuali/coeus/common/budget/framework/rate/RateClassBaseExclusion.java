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
package org.kuali.coeus.common.budget.framework.rate;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class RateClassBaseExclusion extends KcPersistableBusinessObjectBase {
    

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
