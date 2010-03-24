/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.budget;

import java.util.Collection;

import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.budget.core.BudgetCommonService;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.rice.kew.exception.WorkflowException;

public interface AwardBudgetService extends BudgetCommonService<Award> {

    /**
     * 
     */
    public void processSubmision(AwardBudgetDocument awardBudgetDocument);
    
    /**
     * 
     */
    public void processApproval(AwardBudgetDocument awardBudgetDocument);
    
    /**
     * 
     */
    public void processDisapproval(AwardBudgetDocument awardBudgetDocument);
    
    /**
     * 
     */
    public void post(AwardBudgetDocument awardBudgetDocument);
    
    /**
     * 
     */
    public AwardBudgetDocument rebudget(AwardDocument awardDocument,String documentDescription) throws WorkflowException;
    /**
     * 
     * This method...
     * @param rawValues
     * @param document
     * @param versionName
     * @return
     * @throws WorkflowException
     */
    public BudgetDocument<Award> createBudgetDocumentWithCopiedBudgetPeriods(Collection rawValues, BudgetParentDocument<Award> document, String versionName) throws WorkflowException;
}
