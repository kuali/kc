package org.kuali.coeus.propdev.api.budget.subaward;

import org.kuali.coeus.sys.api.model.IdentifiableNumeric;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

public interface BudgetSubAwardPeriodDetailContract extends IdentifiableNumeric, IdentifiableSubAwardBudget  {

    Integer getBudgetPeriod();
    ScaleTwoDecimal getDirectCost();
    ScaleTwoDecimal getIndirectCost();
    ScaleTwoDecimal getCostShare();
    ScaleTwoDecimal getTotalCost();
}
