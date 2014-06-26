package org.kuali.coeus.common.budget.api.nonpersonnel;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

public interface BudgetRateAndBaseContract extends AbstractBudgetRateAndBaseContract {

    ScaleTwoDecimal getBaseCost();

    Long getBudgetRateAndBaseId();

    Long getBudgetLineItemCalculatedAmountId();

    Long getBudgetLineItemId();
}
