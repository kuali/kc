package org.kuali.coeus.budget.api.core;

import org.kuali.coeus.sys.api.model.DocumentNumbered;

public interface BudgetDocumentVersionContract extends DocumentNumbered {

    String getParentDocumentKey();

    String getParentDocumentTypeCode();

    BudgetVersionOverviewContract getBudgetVersionOverview();
}
