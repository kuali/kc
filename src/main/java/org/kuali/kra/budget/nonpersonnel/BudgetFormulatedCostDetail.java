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
package org.kuali.kra.budget.nonpersonnel;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.rates.FormulatedType;

public class BudgetFormulatedCostDetail extends KraPersistableBusinessObjectBase { 
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -7218113007594733976L;
    private Long budgetFormulatedCostDetailId; 
    private Long budgetLineItemId; 
    private Integer formulatedNumber; 
    private String formulatedTypeCode; 
    private BudgetDecimal unitCost; 
    private Integer count; 
    private Integer frequency; 
    private BudgetDecimal calculatedExpenses; 
    
    private FormulatedType formulatedType;
    
    private transient int budgetLineItemNumber;
    private transient int budgetPeriod;

    public BudgetFormulatedCostDetail() { 

    } 
    
    public Long getBudgetFormulatedCostDetailId() {
        return budgetFormulatedCostDetailId;
    }

    public void setBudgetFormulatedCostDetailId(Long budgetFormulatedCostDetailId) {
        this.budgetFormulatedCostDetailId = budgetFormulatedCostDetailId;
    }

    public Long getBudgetLineItemId() {
        return budgetLineItemId;
    }

    public void setBudgetLineItemId(Long budgetLineItemId) {
        this.budgetLineItemId = budgetLineItemId;
    }

    public Integer getFormulatedNumber() {
        return formulatedNumber;
    }

    public void setFormulatedNumber(Integer formulatedNumber) {
        this.formulatedNumber = formulatedNumber;
    }

    public String getFormulatedTypeCode() {
        return formulatedTypeCode;
    }

    public void setFormulatedTypeCode(String formulatedTypeCode) {
        this.formulatedTypeCode = formulatedTypeCode;
    }

    public BudgetDecimal getUnitCost() {
        return unitCost==null?BudgetDecimal.ZERO:unitCost;
    }

    public void setUnitCost(BudgetDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public Integer getCount() {
        return count==null?0:count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getFrequency() {
        return frequency==null?0:frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public BudgetDecimal getCalculatedExpenses() {
        return calculatedExpenses;
    }

    public void setCalculatedExpenses(BudgetDecimal calculatedExpenses) {
        this.calculatedExpenses = calculatedExpenses;
    }

    public FormulatedType getFormulatedType() {
        return formulatedType;
    }

    public void setFormulatedType(FormulatedType formulatedType) {
        this.formulatedType = formulatedType;
    }

    public int getBudgetLineItemNumber() {
        return budgetLineItemNumber;
    }

    public void setBudgetLineItemNumber(int budgetLineItemNumber) {
        this.budgetLineItemNumber = budgetLineItemNumber;
    }

    public int getBudgetPeriod() {
        return budgetPeriod;
    }

    public void setBudgetPeriod(int budgetPeriod) {
        this.budgetPeriod = budgetPeriod;
    }

}