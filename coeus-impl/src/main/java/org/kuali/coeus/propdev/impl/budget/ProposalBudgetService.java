/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.coeus.propdev.impl.budget;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetCommonService;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;

public interface ProposalBudgetService extends BudgetCommonService<DevelopmentProposal> {

    /**
     *
     * This method returns the final version of {@link org.kuali.coeus.common.budget.framework.core.Budget} for a given {@link org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument}
     *
     * @param parentDocument The parent document that has the budget.
     * @return parentDocument final version of budget corresponding to the parentDocument object.
     */
    ProposalDevelopmentBudgetExt getFinalBudgetVersion(ProposalDevelopmentDocument parentDocument);
    
    /**
     * Copy the specified budget, using the optional developmentProposal as the new budget parent if provided.
     */
    ProposalDevelopmentBudgetExt copyBudgetVersion(ProposalDevelopmentBudgetExt budget, boolean onlyOnePeriod, DevelopmentProposal developmentProposal);

    boolean isBudgetMarkedForSubmission(Budget finalBudget, Budget currentBudget);

    void syncBudgetReferencesForCopy(ProposalDevelopmentBudgetExt budget);
}
