package org.kuali.coeus.common.budget.api.nonpersonnel;

import org.kuali.coeus.common.budget.api.core.category.BudgetCategoryContract;
import org.kuali.coeus.common.budget.api.core.CostElementContract;
import org.kuali.coeus.common.budget.api.core.IdentifiableBudget;
import org.kuali.coeus.common.budget.api.personnel.BudgetPersonnelDetailsContract;
import org.kuali.coeus.propdev.api.hierarchy.HierarchicalProposal;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.util.Date;
import java.util.List;

public interface BudgetLineItemContract extends IdentifiableBudget, HierarchicalProposal {

    Long getBudgetLineItemId();

    Long getBudgetPeriodId();

    Integer getLineItemNumber();

    Integer getBudgetPeriod();

    Boolean getApplyInRateFlag();

    Integer getBasedOnLineItem();

    String getBudgetJustification();

    String getGroupName();

    ScaleTwoDecimal getTotalCostSharingAmount();

    Date getEndDate();

    ScaleTwoDecimal getLineItemCost();

    String getLineItemDescription();

    Integer getLineItemSequence();

    Boolean getOnOffCampusFlag();

    Integer getQuantity();

    Date getStartDate();

    ScaleTwoDecimal getUnderrecoveryAmount();

    Boolean getSubmitCostSharingFlag();

    Boolean getFormulatedCostElementFlag();

    List<? extends BudgetLineItemCalculatedAmountContract> getBudgetLineItemCalculatedAmounts();

    List<? extends BudgetPersonnelDetailsContract> getBudgetPersonnelDetailsList();

    Integer getSubAwardNumber();

    List<? extends BudgetRateAndBaseContract> getBudgetRateAndBaseList();

    List<? extends BudgetFormulatedCostDetailContract> getBudgetFormulatedCosts();

    BudgetCategoryContract getBudgetCategory();

    CostElementContract getCostElementBO();

    ScaleTwoDecimal getCostSharingAmount();
}
