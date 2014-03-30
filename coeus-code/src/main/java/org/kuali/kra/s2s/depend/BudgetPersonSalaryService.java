package org.kuali.kra.s2s.depend;

import org.kuali.kra.budget.personnel.BudgetPersonSalaryDetails;

import java.util.List;

public interface BudgetPersonSalaryService {

    /**
     * Finds the {@link BudgetPersonSalaryDetails} by budgetId, personId, and budgetPeriod.  The personId can either be a personId or rolodexId.
     *
     * @param budgetId The budget Id. cannot be null.
     * @param personId The personId or rolodexId.  Cannot be blank.
     * @param budgetPeriod the budget period. Cannot be null.
     * @return a List of {@link BudgetPersonSalaryDetails} or an empty list.
     * @throws java.lang.IllegalArgumentException if budgetId is null, if personId is blank, if budgetPeriod is null
     */
    List<BudgetPersonSalaryDetails> findSalaryDetailsByBudgetIdAndPersonIdAndBudgetPeriod(Long budgetId, String personId, Integer budgetPeriod);
}
