package org.kuali.coeus.common.budget.api.income;

import org.kuali.coeus.common.budget.api.period.BudgetPeriodContract;

import org.kuali.coeus.common.budget.api.core.IdentifiableBudget;
import org.kuali.coeus.propdev.api.hierarchy.HierarchicalProposal;
import org.kuali.coeus.sys.api.model.Describable;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

public interface BudgetProjectIncomeContract extends IdentifiableBudget, Describable, HierarchicalProposal {

    Integer getDocumentComponentId();

    Long getBudgetPeriodId();

    BudgetPeriodContract getBudgetPeriod();

    Integer getBudgetPeriodNumber();

    ScaleTwoDecimal getProjectIncome();

}
