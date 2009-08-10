/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.budget.service;

import org.kuali.kra.budget.core.Budget;
/**
 * 
 * This class is for handling Budget Sub awards
 */
public interface BudgetSubAwardService {
    /**
     * 
     * This method is to populate the subaward files
     * @param budgetSubAwards
     */
    public void populateBudgetSubAwardFiles(org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwards budgetSubAwards);
    /**
     * 
     * This method is to handle subaward attachments
     * @param budget
     */
    public void populateBudgetSubAwardAttachments(Budget budget);
}
