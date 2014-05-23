package org.kuali.coeus.budget.api.version;

import org.kuali.coeus.sys.api.model.DocumentNumbered;

import java.util.List;

public interface BudgetDocumentVersionContract extends DocumentNumbered {

    String getParentDocumentKey();

    String getParentDocumentTypeCode();

    BudgetVersionOverviewContract getBudgetVersionOverview();
}
