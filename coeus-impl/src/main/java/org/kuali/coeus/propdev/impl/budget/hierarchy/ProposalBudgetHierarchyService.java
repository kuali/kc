/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
	 */
	void synchronizeAllChildBudgets(DevelopmentProposal hierarchyProposal);

	/**
	 * Does not persist changes to either proposal. Caller is responsible for this.
	 */
    void synchronizeChildBudget(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal, List<BudgetPeriod> oldBudgetPeriods);
    
    /**
     * Does not persist changes to proposal or budget. Caller is responsible for this.
     */
    void synchronizeChildBudget(DevelopmentProposal hierarchyProposal, ProposalDevelopmentBudgetExt budget);
    
    void persistProposalHierarchyBudget(DevelopmentProposal hierarchyProposal);

    void removeMergeableChildBudgetElements(ProposalDevelopmentBudgetExt parentBudget);

    void removeChildBudgetElements(DevelopmentProposal parentProposal, ProposalDevelopmentBudgetExt parentBudget, String childProposalNumber);
    
    void initializeBudget (DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) throws ProposalHierarchyException;
 
    List<ProposalHierarchyErrorWarningDto> validateChildBudgetPeriods(DevelopmentProposal hierarchyProposal,
            DevelopmentProposal childProposal, boolean allowEndDateChange) throws ProposalHierarchyException;
    
    ProposalDevelopmentBudgetExt getHierarchyBudget(DevelopmentProposal hierarchyProposal) throws ProposalHierarchyException;
    
    ProposalDevelopmentBudgetExt getSyncableBudget(DevelopmentProposal childProposal) throws ProposalHierarchyException;
    
    int computeHierarchyHashCode(Budget budget);
    
    List<ProposalDevelopmentBudgetExt> getHierarchyBudgets(DevelopmentProposal hierarchyProposal) throws ProposalHierarchyException;
}
