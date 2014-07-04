package org.kuali.coeus.propdev.api.budget.modular;

import org.kuali.coeus.common.budget.api.core.IdentifiableBudget;
import org.kuali.coeus.common.budget.api.rate.RateClassContract;
import org.kuali.coeus.sys.api.model.Describable;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

public interface BudgetModularIdcContract extends Describable, IdentifiableBudget {
    
    Long getBudgetPeriodId();
    
    Integer getBudgetPeriod();
    
    Integer getRateNumber();
    
    ScaleTwoDecimal getIdcRate();
    
    ScaleTwoDecimal getIdcBase();
    
    ScaleTwoDecimal getFundsRequested();

    RateClassContract getRateClass();
}
