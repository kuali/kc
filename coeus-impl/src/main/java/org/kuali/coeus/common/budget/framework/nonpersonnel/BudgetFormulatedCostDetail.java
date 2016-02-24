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

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "BUDGET_DETAILS_ID")
    private BudgetLineItem budgetLineItem;

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
        return getBudgetLineItem().getBudgetLineItemId();
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

	public BudgetLineItem getBudgetLineItem() {
		return budgetLineItem;
	}

	public void setBudgetLineItem(BudgetLineItem budgetLineItem) {
		this.budgetLineItem = budgetLineItem;
	}
}
