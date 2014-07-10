package org.kuali.coeus.s2sgen.impl.budget;

import org.kuali.coeus.propdev.api.budget.ProposalDevelopmentBudgetExtContract;
import org.kuali.coeus.propdev.api.core.DevelopmentProposalContract;
import org.springframework.stereotype.Component;

@Component("s2SCommonBudgetService")
public class S2SCommonBudgetServiceImpl implements S2SCommonBudgetService {
    @Override
    public ProposalDevelopmentBudgetExtContract getBudget(DevelopmentProposalContract developmentProposal) {
        if (developmentProposal == null) {
            throw new IllegalArgumentException("developmentProposal is null");
        }

        final ProposalDevelopmentBudgetExtContract finalBudget = developmentProposal.getFinalBudget();
        return finalBudget != null ? finalBudget : developmentProposal.getLatestBudget();
    }
}
