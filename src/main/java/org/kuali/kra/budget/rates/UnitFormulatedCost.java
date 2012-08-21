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
import org.kuali.kra.bo.Unit;
import org.kuali.kra.budget.BudgetDecimal;

public class UnitFormulatedCost extends KraPersistableBusinessObjectBase { 
    

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 8388817616860002696L;
    private Long unitFormulatedCostId;
    private String formulatedTypeCode; 
    private String unitNumber; 
    private BudgetDecimal unitCost; 
    
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

    public BudgetDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BudgetDecimal unitCost) {
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