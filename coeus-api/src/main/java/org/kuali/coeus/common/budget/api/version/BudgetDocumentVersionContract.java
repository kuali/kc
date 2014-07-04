package org.kuali.coeus.common.budget.api.version;

import org.kuali.coeus.sys.api.model.DocumentNumbered;

public interface BudgetDocumentVersionContract extends DocumentNumbered {

    String getParentDocumentKey();

    String getParentDocumentTypeCode();

    BudgetVersionOverviewContract getBudgetVersionOverview();
}
