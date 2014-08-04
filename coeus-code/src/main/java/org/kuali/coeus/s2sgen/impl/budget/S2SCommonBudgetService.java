package org.kuali.coeus.s2sgen.impl.budget;

import org.kuali.coeus.propdev.api.budget.ProposalDevelopmentBudgetExtContract;
import org.kuali.coeus.propdev.api.core.DevelopmentProposalContract;

/**
 * This service contains generic budget handling code for S2S.
 */
public interface S2SCommonBudgetService {

    /**
     * This method retrieves a Budget for use in S2S generators.  S2S has special rules, where
     * it first will look for a final budget and if none exists it will then look for the latest
     * budget.
     *
     * @param developmentProposal the development proposal, cannot be null.
     * @return a Budget, either a final one, the latest non-final one, or null
     */
    ProposalDevelopmentBudgetExtContract getBudget(DevelopmentProposalContract developmentProposal);
}
