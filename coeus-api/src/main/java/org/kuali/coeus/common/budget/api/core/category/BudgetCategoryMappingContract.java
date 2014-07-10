package org.kuali.coeus.common.budget.api.core.category;


public interface BudgetCategoryMappingContract {
    String getBudgetCategoryCode();
    String getMappingName();
    String getTargetCategoryCode();
    BudgetCategoryContract getBudgetCategory();
}
