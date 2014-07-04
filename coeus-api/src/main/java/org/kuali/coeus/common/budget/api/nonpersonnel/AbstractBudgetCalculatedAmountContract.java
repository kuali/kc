package org.kuali.coeus.common.budget.api.nonpersonnel;

import org.kuali.coeus.common.budget.api.core.IdentifiableBudget;
import org.kuali.coeus.common.budget.api.rate.RateClassContract;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

public interface AbstractBudgetCalculatedAmountContract extends IdentifiableBudget {
    
    Integer getBudgetPeriod();
    
    Integer getLineItemNumber();
    
    String getRateTypeCode();
    
    Boolean getApplyRateFlag();
    
    ScaleTwoDecimal getCalculatedCost();
    
    ScaleTwoDecimal getCalculatedCostSharing();
    
    String getRateTypeDescription();
    
    Long getBudgetLineItemId();

    RateClassContract getRateClass();
}
