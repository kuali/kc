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
package org.kuali.coeus.common.budget.framework.nonpersonnel;

import javax.persistence.*;

import org.kuali.coeus.common.budget.api.nonpersonnel.BudgetFormulatedCostDetailContract;
import org.kuali.coeus.common.budget.framework.rate.FormulatedType;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.persistence.ScaleTwoDecimalConverter;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "BUD_FORMULATED_COST_DETAIL")
public class BudgetFormulatedCostDetail extends KcPersistableBusinessObjectBase implements BudgetFormulatedCostDetailContract {

    private static final long serialVersionUID = -7218113007594733976L;

    @PortableSequenceGenerator(name = "SEQ_BUD_FRMLTD_COST_DETAIL_ID")
    @GeneratedValue(generator = "SEQ_BUD_FRMLTD_COST_DETAIL_ID")
    @Id
    @Column(name = "BUD_FORMULATED_COST_DETAIL_ID")
    private Long budgetFormulatedCostDetailId;

    @Column(name = "BUDGET_DETAILS_ID")
    private Long budgetLineItemId;

    @Column(name = "FORMULATED_NUMBER")
    private Integer formulatedNumber;

    @Column(name = "FORMULATED_TYPE_CODE")
    private String formulatedTypeCode;

    @Column(name = "UNIT_COST")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal unitCost;

    @Column(name = "COUNT")
    private Integer count;

    @Column(name = "FREQUENCY")
    private Integer frequency;

    @Column(name = "CALCULATED_EXPENSES")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal calculatedExpenses;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "FORMULATED_TYPE_CODE", referencedColumnName = "FORMULATED_TYPE_CODE", insertable = false, updatable = false)
    private FormulatedType formulatedType;

    @Transient
    private transient int budgetLineItemNumber;

    @Transient
    private transient int budgetPeriod;

    @Override
    public Long getBudgetFormulatedCostDetailId() {
        return budgetFormulatedCostDetailId;
    }

    public void setBudgetFormulatedCostDetailId(Long budgetFormulatedCostDetailId) {
        this.budgetFormulatedCostDetailId = budgetFormulatedCostDetailId;
    }

    @Override
    public Long getBudgetLineItemId() {
        return budgetLineItemId;
    }

    public void setBudgetLineItemId(Long budgetLineItemId) {
        this.budgetLineItemId = budgetLineItemId;
    }

    @Override
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

    @Override
    public ScaleTwoDecimal getUnitCost() {
        return unitCost == null ? ScaleTwoDecimal.ZERO : unitCost;
    }

    public void setUnitCost(ScaleTwoDecimal unitCost) {
        this.unitCost = unitCost;
    }

    @Override
    public Integer getCount() {
        return count == null ? 0 : count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public Integer getFrequency() {
        return frequency == null ? 0 : frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    @Override
    public ScaleTwoDecimal getCalculatedExpenses() {
        return calculatedExpenses;
    }

    public void setCalculatedExpenses(ScaleTwoDecimal calculatedExpenses) {
        this.calculatedExpenses = calculatedExpenses;
    }

    @Override
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
