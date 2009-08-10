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
package org.kuali.kra.budget.personnel;

import org.kuali.kra.budget.core.Budget;

/**
 * Budget Person Service interface
 */
public interface BudgetPersonService {
    
    /**
     * Populate the given BudgetPerson with system default or institution data.
     * 
     * @param budgetPerson
     */
    public void populateBudgetPersonData(Budget budget, BudgetPerson budgetPerson);
    
    /**
     * Populate each Budget Person's system default data, where it is not already populated.
     * 
     * @param budget
     */
    public void populateBudgetPersonDefaultDataIfEmpty(Budget budget);
    
    /**
     * This method will synchronize BudgetPersons with ProposalPersons.  New proposal persons will
     * be copied in.
     * 
     * @param budget
     */
    public void synchBudgetPersonsToProposal(Budget budget);

    public BudgetPerson findBudgetPerson(BudgetPersonnelDetails budgetPersonnelDetails);

}
