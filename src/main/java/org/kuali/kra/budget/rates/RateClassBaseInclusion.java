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
    

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1079390676098311747L;
    private Long rateClassBaseInclusionId; 
    private String rateClassCode; 
    private String rateTypeCode; 
    private String rateClassCodeIncl; 
    private String rateTypeCodeIncl; 
    
    private RateType rateType; 
    private RateType rateTypeInclusion;

    private int rowIndex;
    private int parentRowIndex;
    private boolean calculated;
    
    private Long parentRateClassBaseInclusionId;
    
        
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

    /**
     * Gets the rowIndex attribute. 
     * @return Returns the rowIndex.
     */
    public int getRowIndex() {
        return rowIndex;
    }

    /**
     * Sets the rowIndex attribute value.
     * @param rowIndex The rowIndex to set.
     */
    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    /**
     * Gets the parentRowIndex attribute. 
     * @return Returns the parentRowIndex.
     */
    public int getParentRowIndex() {
        return parentRowIndex;
    }

    /**
     * Sets the parentRowIndex attribute value.
     * @param parentRowIndex The parentRowIndex to set.
     */
    public void setParentRowIndex(int parentRowIndex) {
        this.parentRowIndex = parentRowIndex;
    }

    /**
     * Gets the calculated attribute. 
     * @return Returns the calculated.
     */
    public boolean isCalculated() {
        return calculated;
    }

    /**
     * Sets the calculated attribute value.
     * @param calculated The calculated to set.
     */
    public void setCalculated(boolean calculated) {
        this.calculated = calculated;
    }

    /**
     * Gets the parentRateClassBaseInclusionId attribute. 
     * @return Returns the parentRateClassBaseInclusionId.
     */
    public Long isParentRateClassBaseInclusionId() {
        return parentRateClassBaseInclusionId;
    }

    /**
     * Sets the parentRateClassBaseInclusionId attribute value.
     * @param parentRateClassBaseInclusionId The parentRateClassBaseInclusionId to set.
     */
    public void setParentRateClassBaseInclusionId(Long parentRateClassBaseInclusionId) {
        this.parentRateClassBaseInclusionId = parentRateClassBaseInclusionId;
    }

 }