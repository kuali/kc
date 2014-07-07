package org.kuali.coeus.common.budget.api.core.category;

import org.kuali.coeus.sys.api.model.Describable;

import java.util.List;

public interface BudgetCategoryMapContract extends Describable {

    String getMappingName();
    String getTargetCategoryCode();
    String getCategoryType();
    List<? extends BudgetCategoryMappingContract> getBudgetCategoryMappings();
}
