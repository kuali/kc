package org.kuali.coeus.common.budget.api.core;

import org.kuali.coeus.common.budget.api.core.category.BudgetCategoryContract;
import org.kuali.coeus.common.budget.api.rate.ValidCeRateTypeContract;
import org.kuali.coeus.sys.api.model.Describable;
import org.kuali.coeus.sys.api.model.Inactivatable;

import java.util.List;

public interface CostElementContract extends Describable, Inactivatable {
    
    String getCostElement();
    
    String getBudgetCategoryCode();
    
    Boolean getOnOffCampusFlag();

    String getFinancialObjectCode();

    List<? extends ValidCeRateTypeContract> getValidCeRateTypes();

    BudgetCategoryContract getBudgetCategory();
}
