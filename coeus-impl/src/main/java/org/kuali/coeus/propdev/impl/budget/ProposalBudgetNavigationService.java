package org.kuali.coeus.propdev.impl.budget;

import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetForm;
import org.kuali.rice.krad.uif.element.Action;

public interface ProposalBudgetNavigationService {
    public void createNavigationLink(Action action, ProposalBudgetForm form, String direction);
}
