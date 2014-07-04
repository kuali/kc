package org.kuali.coeus.common.budget.api.period;

import org.kuali.coeus.common.budget.api.core.IdentifiableBudget;
import org.kuali.coeus.common.budget.api.nonpersonnel.BudgetLineItemContract;
import org.kuali.coeus.propdev.api.budget.modular.BudgetModularContract;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.util.Date;
import java.util.List;

public interface BudgetPeriodContract extends IdentifiableBudget {

    Long getBudgetPeriodId();

    Integer getBudgetPeriod();
    
    String getComments();
    
    ScaleTwoDecimal getCostSharingAmount();
    
    Date getEndDate();
    
    Date getStartDate();
    
    ScaleTwoDecimal getTotalCost();
    
    ScaleTwoDecimal getTotalCostLimit();
    
    ScaleTwoDecimal getTotalDirectCost();
    
    ScaleTwoDecimal getTotalIndirectCost();
    
    ScaleTwoDecimal getUnderrecoveryAmount();
    
    List<? extends BudgetLineItemContract> getBudgetLineItems();
    
    Integer getNumberOfParticipants();
    
    ScaleTwoDecimal getDirectCostLimit();
    
    BudgetModularContract getBudgetModular();
}
