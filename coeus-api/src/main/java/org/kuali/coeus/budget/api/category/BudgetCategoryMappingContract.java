package org.kuali.coeus.budget.api.category;


public interface BudgetCategoryMappingContract {
    String getBudgetCategoryCode();
    String getMappingName();
    String getTargetCategoryCode();
    BudgetCategoryContract getBudgetCategory();
}
