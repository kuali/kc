package org.kuali.coeus.common.budget.api.personnel;

import org.kuali.coeus.common.budget.api.nonpersonnel.AbstractBudgetCalculatedAmountContract;

public interface BudgetPersonnelCalculatedAmountContract extends AbstractBudgetCalculatedAmountContract {

    Integer getPersonNumber();

    Long getBudgetPersonnelCalculatedAmountId();

    Long getBudgetPersonnelLineItemId();
}
