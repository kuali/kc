/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.budget.core;

import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.rice.kew.exception.WorkflowException;

/**
 * This class...
 */
public interface BudgetCommonService<T extends BudgetParent> {
    /**
     * Returns a new finalized BudgetDocument based on the given ProposalDevelopmentDocument and documentDescription.
     * 
     * @param pdDoc
     * @param documentDescription
     * @return BudgetDocument
     * @throws WorkflowException
     */
    public BudgetDocument<T> getNewBudgetVersion(BudgetParentDocument<T> parentDocument, String documentDescription) throws WorkflowException;
    /**
     * Returns a new finalized BudgetDocument with the data from the given BudgetDocument copied over.
     * @param budgetDocument
     * @return BudgetDocument
     * @throws WorkflowException
     */
    public BudgetDocument<T> copyBudgetVersion(BudgetDocument<T> budgetDocument) throws WorkflowException;

}
