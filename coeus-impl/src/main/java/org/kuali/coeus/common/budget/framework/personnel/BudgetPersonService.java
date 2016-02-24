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
package org.kuali.coeus.common.budget.framework.personnel;

import java.util.List;

import org.kuali.coeus.common.budget.api.personnel.BudgetPersonContract;
import org.kuali.coeus.common.budget.api.personnel.BudgetPersonnelDetailsContract;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.framework.rolodex.PersonRolodex;
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
    
    /**
     * This method is to get budget person details
     * @param budget
     * @param budgetPerson
     * @return
     */
    public PersonRolodex getBudgetPersonRolodex(Budget budget, BudgetPersonContract budgetPerson);
    
    /**
     * This method returns the applicable Object Codes (Cost Elements) for a given Budget Person 
     * based on his Job Code
     * @param budget
     * @param personSequenceNumber
     * @return List of Cost Elements
     */
    public List<ValidCeJobCode> getApplicableCostElements(Budget budget, String personSequenceNumber);
    
    /**
     * 
     * This method returns the applicable Object Codes (Cost Elements) for a given Budget Person, converted to string separated by ",".
     * @param budgetId
     * @param personSequenceNumber
     * @param budgetCategoryTypeCode
     * @return List of Cost Elements
     */
    public String getApplicableCostElementsForAjaxCall(Long budgetId, String personSequenceNumber, String budgetCategoryTypeCode);    

}
