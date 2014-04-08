package org.kuali.coeus.budget.api.category;


import org.kuali.coeus.sys.api.model.Coded;
import org.kuali.coeus.sys.api.model.Describable;

public interface BudgetCategoryContract extends Coded, Describable {
    BudgetCategoryTypeContract getBudgetCategoryType();
}
