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

public class RateClassBaseInclusion extends KcPersistableBusinessObjectBase {
    


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
