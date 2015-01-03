package org.kuali.coeus.propdev.impl.budget.hierarchy;

import java.util.List;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyErrorWarningDto;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyException;

public interface ProposalBudgetHierarchyService {
	
	/**
	 * Persists changes to child proposal and budget, but does not persist changes to hierarchy proposal. Caller is responsible for this
	 * @param hierarchyProposal
	 */
	public void synchronizeAllChildBudgets(DevelopmentProposal hierarchyProposal);

	/**
	 * Does not persist changes to either proposal. Caller is responsible for this
	 * @param hierarchyProposal
	 * @param childProposal
	 * @param oldBudgetPeriods
	 */
    public void synchronizeChildBudget(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal, List<BudgetPeriod> oldBudgetPeriods);
    
    /**
     * Does not persist changes to proposal or budget. Caller is responsible for this
     * @param hierarchyProposal
     * @param budget
     */
    public void synchronizeChildBudget(DevelopmentProposal hierarchyProposal, ProposalDevelopmentBudgetExt budget);
    
    public void persistProposalHierarchyBudget(DevelopmentProposal hierarchyProposal);
    
    public void removeChildBudgetElements(DevelopmentProposal parentProposal, ProposalDevelopmentBudgetExt parentBudget, String childProposalNumber);
    
    public void initializeBudget (DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) throws ProposalHierarchyException;
 
    public List<ProposalHierarchyErrorWarningDto> validateChildBudgetPeriods(DevelopmentProposal hierarchyProposal,
            DevelopmentProposal childProposal, boolean allowEndDateChange) throws ProposalHierarchyException;
    
    public ProposalDevelopmentBudgetExt getHierarchyBudget(DevelopmentProposal hierarchyProposal) throws ProposalHierarchyException;
    
    public ProposalDevelopmentBudgetExt getSyncableBudget(DevelopmentProposal childProposal) throws ProposalHierarchyException;
    
    public int computeHierarchyHashCode(Budget budget);
    
    public List<ProposalDevelopmentBudgetExt> getHierarchyBudgets(DevelopmentProposal hierarchyProposal) throws ProposalHierarchyException;
}
