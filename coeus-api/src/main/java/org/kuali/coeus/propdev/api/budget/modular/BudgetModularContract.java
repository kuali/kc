package org.kuali.coeus.propdev.api.budget.modular;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.util.List;

public interface BudgetModularContract {
    Long getBudgetPeriodId();
    
    Long getBudgetId();
    
    Integer getBudgetPeriod();
    
    ScaleTwoDecimal getDirectCostLessConsortiumFna();
    
    ScaleTwoDecimal getConsortiumFna();
    
    ScaleTwoDecimal getTotalDirectCost();

    List<? extends BudgetModularIdcContract> getBudgetModularIdcs();
}
