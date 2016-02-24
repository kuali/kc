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
