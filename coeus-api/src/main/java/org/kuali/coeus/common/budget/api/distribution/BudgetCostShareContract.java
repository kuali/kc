package org.kuali.coeus.common.budget.api.distribution;

import org.kuali.coeus.common.budget.api.core.IdentifiableBudget;
import org.kuali.coeus.propdev.api.hierarchy.HierarchicalProposal;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

public interface BudgetCostShareContract extends IdentifiableBudget, HierarchicalProposal{

    Integer getDocumentComponentId();

    Integer getProjectPeriod();

    ScaleTwoDecimal getShareAmount();

    ScaleTwoDecimal getSharePercentage();

    String getSourceAccount();

}
