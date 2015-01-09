/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
     * @throws org.kuali.rice.kew.api.exception.WorkflowException
     */
    public ProposalDevelopmentBudgetExt getFinalBudgetVersion(ProposalDevelopmentDocument parentDocument) throws WorkflowException;
    
    /**
     * Copy the specified budget, using the optional developmentProposal as the new budget parent if provided.
     * @param budget
     * @param onlyOnePeriod
     * @param developmentProposal
     * @return
     */
    public ProposalDevelopmentBudgetExt copyBudgetVersion(ProposalDevelopmentBudgetExt budget, boolean onlyOnePeriod, DevelopmentProposal developmentProposal);

    public boolean isBudgetMarkedForSubmission(Budget finalBudget, Budget currentBudget);
    
}
