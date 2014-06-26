/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.common.budget.framework.nonpersonnel;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.kuali.coeus.common.budget.api.nonpersonnel.BudgetRateAndBaseContract;
import org.kuali.coeus.common.budget.framework.copy.DeepCopyIgnore;
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

    @DeepCopyIgnore
    @PortableSequenceGenerator(name = "SEQ_BUDGET_RATE_AND_BASE_ID")
    @GeneratedValue(generator = "SEQ_BUDGET_RATE_AND_BASE_ID")
    @Id
    @Column(name = "BUDGET_RATE_AND_BASE_ID")
    private Long budgetRateAndBaseId;

    @Column(name = "BUDGET_DETAILS_CAL_AMTS_ID")
    private Long budgetLineItemCalculatedAmountId;

    @Column(name = "BUDGET_DETAILS_ID")
    private Long budgetLineItemId;

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
}
