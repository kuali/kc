package org.kuali.coeus.propdev.api.budget.subaward;

import org.kuali.coeus.propdev.api.hierarchy.HierarchicalProposal;

import java.util.Date;
import java.util.List;

public interface BudgetSubAwardsContract extends IdentifiableSubAwardBudget, HierarchicalProposal {
    public String getProposalNumber();
    Integer getBudgetVersionNumber();
    String getComments();
    String getOrganizationName();
    String getOrganizationId();
    Integer getSubAwardStatusCode();
    String getTranslationComments();

    String getSubAwardXfdFileName();
    byte[] getSubAwardXfdFileData();
    Date getXfdUpdateTimestamp();
    String getXfdUpdateUser();

    String getSubAwardXmlFileData();
    Date getXmlUpdateTimestamp();
    String getXmlUpdateUser();

    String getNamespace();
    String getFormName();

    List<? extends BudgetSubAwardAttachmentContract> getBudgetSubAwardAttachments();
    List<? extends BudgetSubAwardFilesContract> getBudgetSubAwardFiles();
    List<? extends BudgetSubAwardPeriodDetailContract> getBudgetSubAwardPeriodDetails();
}
