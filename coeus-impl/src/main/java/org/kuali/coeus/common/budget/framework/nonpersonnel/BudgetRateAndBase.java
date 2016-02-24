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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.kuali.coeus.common.budget.api.nonpersonnel.BudgetRateAndBaseContract;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.persistence.ScaleTwoDecimalConverter;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "BUDGET_RATE_AND_BASE")
public class BudgetRateAndBase extends AbstractBudgetRateAndBase implements BudgetRateAndBaseContract {

    private static final long serialVersionUID = -6003003851261499575L;

    @Column(name = "BASE_COST")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal baseCost;

    @PortableSequenceGenerator(name = "SEQ_BUDGET_RATE_AND_BASE_ID")
    @GeneratedValue(generator = "SEQ_BUDGET_RATE_AND_BASE_ID")
    @Id
    @Column(name = "BUDGET_RATE_AND_BASE_ID")
    private Long budgetRateAndBaseId;

    @Column(name = "BUDGET_DETAILS_CAL_AMTS_ID")
    private Long budgetLineItemCalculatedAmountId;

    @Column(name = "BUDGET_DETAILS_ID", insertable = false, updatable = false)
    private Long budgetLineItemId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "BUDGET_DETAILS_ID", referencedColumnName = "BUDGET_DETAILS_ID")
    private BudgetLineItem budgetLineItem;

    @Override
    public ScaleTwoDecimal getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(ScaleTwoDecimal baseCost) {
        this.baseCost = baseCost;
    }

    @Override
    public Long getBudgetRateAndBaseId() {
        return budgetRateAndBaseId;
    }

    public void setBudgetRateAndBaseId(Long budgetRateAndBaseId) {
        this.budgetRateAndBaseId = budgetRateAndBaseId;
    }

    @Override
    public Long getBudgetLineItemCalculatedAmountId() {
        return budgetLineItemCalculatedAmountId;
    }

    public void setBudgetLineItemCalculatedAmountId(Long budgetLineItemCalculatedAmountId) {
        this.budgetLineItemCalculatedAmountId = budgetLineItemCalculatedAmountId;
    }

    @Override
    public Long getBudgetLineItemId() {
        return budgetLineItemId;
    }

    public void setBudgetLineItemId(Long budgetLineItemId) {
        this.budgetLineItemId = budgetLineItemId;
    }

	public BudgetLineItem getBudgetLineItem() {
		return budgetLineItem;
	}

	public void setBudgetLineItem(BudgetLineItem budgetLineItem) {
		this.budgetLineItem = budgetLineItem;
	}
}
