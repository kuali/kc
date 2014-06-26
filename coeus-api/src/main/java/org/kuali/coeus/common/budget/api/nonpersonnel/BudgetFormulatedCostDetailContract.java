package org.kuali.coeus.common.budget.api.nonpersonnel;

import org.kuali.coeus.common.budget.api.rate.FormulatedTypeContract;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

public interface BudgetFormulatedCostDetailContract {
    
    Long getBudgetFormulatedCostDetailId();

    Long getBudgetLineItemId();

    Integer getFormulatedNumber();

    ScaleTwoDecimal getUnitCost();
    
    Integer getCount();
    
    Integer getFrequency();
    
    ScaleTwoDecimal getCalculatedExpenses();

    FormulatedTypeContract getFormulatedType();
}
