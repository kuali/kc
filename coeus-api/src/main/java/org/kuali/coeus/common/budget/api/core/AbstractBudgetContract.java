package org.kuali.coeus.common.budget.api.core;

import org.kuali.coeus.common.budget.api.rate.RateClassContract;
import org.kuali.coeus.sys.api.model.DocumentNumbered;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.util.Date;

public interface AbstractBudgetContract extends IdentifiableBudget, DocumentNumbered {
    
    Integer getBudgetVersionNumber();
    
    ScaleTwoDecimal getCostSharingAmount();
    
    Date getEndDate();
    
    Date getStartDate();
    
    Boolean getFinalVersionFlag();
    
    String getOhRateTypeCode();
    
    ScaleTwoDecimal getResidualFunds();
    
    ScaleTwoDecimal getTotalCost();
    
    ScaleTwoDecimal getTotalDirectCost();
    
    ScaleTwoDecimal getTotalIndirectCost();
    
    ScaleTwoDecimal getTotalCostLimit();
    
    ScaleTwoDecimal getTotalDirectCostLimit();
    
    ScaleTwoDecimal getUnderrecoveryAmount();
    
    String getComments();
    
    Boolean getModularBudgetFlag();
    
    String getOnOffCampusFlag();
    
    Boolean getSubmitCostSharingFlag();
    
    RateClassContract getUrRateClass();
    
    RateClassContract getRateClass();
}
