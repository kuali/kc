package org.kuali.coeus.common.budget.api.rate;

import org.kuali.coeus.common.budget.api.core.IdentifiableBudget;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

public interface BudgetRateContract extends IdentifiableBudget, AbstractInstituteRateContract {
    String getActivityTypeCode();
    ScaleTwoDecimal getApplicableRate();
}
