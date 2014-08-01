/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.budget.framework.personnel;

import org.kuali.coeus.common.budget.api.personnel.BudgetPersonnelDetailsContract;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;

/**
 * Budget Person Service interface
 */
public interface BudgetPersonService {
    
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
    
    /**
     * Adds a new budget person and adds default info. If the budget person is an employee/person
     * then will also create a budget person for each applicable appointment that person has
     * @param budget
     * @param budgetPerson
     */
    public void addBudgetPerson(Budget budget, BudgetPerson budgetPerson);

    /**
     *
     * This method compares a proposal person with budget person. It checks
     * whether the proposal person is from PERSON or ROLODEX and matches the
     * respective person ID with the person in {@link BudgetPersonnelDetails}
     *
     * @param proposalPerson -
     *            key person from proposal
     * @param budgetPersonnelDetails
     *            person from BudgetPersonnelDetails
     * @return true if persons match, false otherwise
     */
    public boolean proposalPersonEqualsBudgetPerson(
            ProposalPersonContract proposalPerson,
            BudgetPersonnelDetailsContract budgetPersonnelDetails);


    public void refreshBudgetPerson(BudgetPerson budgetPerson);

}
