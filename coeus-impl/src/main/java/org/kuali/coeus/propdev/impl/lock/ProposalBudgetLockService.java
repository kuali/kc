package org.kuali.coeus.propdev.impl.lock;

import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;

public interface ProposalBudgetLockService {
    public void establishBudgetLock(ProposalDevelopmentBudgetExt budget);
    public void deleteBudgetLock(ProposalDevelopmentBudgetExt budget);
    public boolean doesBudgetVersionMatchDescriptor(String lockDescriptor, int budgetVersionNumber);
}
