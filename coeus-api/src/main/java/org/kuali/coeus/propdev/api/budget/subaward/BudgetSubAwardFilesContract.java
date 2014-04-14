package org.kuali.coeus.propdev.api.budget.subaward;

public interface BudgetSubAwardFilesContract extends IdentifiableSubAwardBudget {

    byte[] getSubAwardXfdFileData();
    String getSubAwardXfdFileName();
    String getSubAwardXmlFileData();
}
