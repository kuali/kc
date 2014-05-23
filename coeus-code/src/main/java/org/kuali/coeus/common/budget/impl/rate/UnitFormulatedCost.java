/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.budget.impl.rate;

import org.kuali.coeus.common.budget.framework.rate.FormulatedType;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

public class UnitFormulatedCost extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 8388817616860002696L;
    private Long unitFormulatedCostId;
    private String formulatedTypeCode; 
    private String unitNumber; 
    private ScaleTwoDecimal unitCost;
    
    private Unit unit; 
    private FormulatedType formulatedType; 
    
    public Long getUnitFormulatedCostId() {
        return unitFormulatedCostId;
    }

    public void setUnitFormulatedCostId(Long unitFormulatedCostId) {
        this.unitFormulatedCostId = unitFormulatedCostId;
    }

    public UnitFormulatedCost() { 

    } 
    
    public String getFormulatedTypeCode() {
        return formulatedTypeCode;
    }

    public void setFormulatedTypeCode(String formulatedTypeCode) {
        this.formulatedTypeCode = formulatedTypeCode;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public ScaleTwoDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(ScaleTwoDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public FormulatedType getFormulatedType() {
        return formulatedType;
    }

    public void setFormulatedType(FormulatedType formulatedType) {
        this.formulatedType = formulatedType;
    }

}