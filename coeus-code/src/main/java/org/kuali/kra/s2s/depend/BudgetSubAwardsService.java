package org.kuali.kra.s2s.depend;


import org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwards;

import java.util.List;

public interface BudgetSubAwardsService {

    /**
     * Finds the {@link BudgetSubAwards}s by budgetId.  Will return an empty
     * List if no items exist.
     * @param budgetId the budget Id.  Cannot be null.
     * @return a list of {@link BudgetSubAwards}s or an empty list.
     * @throws IllegalArgumentException if the budgetId is null
     */
    List<BudgetSubAwards> findBudgetSubAwardsByBudgetId(Long budgetId);

    /**
     * Finds the {@link BudgetSubAwards}s by budgetId and namespace.  Will return an empty
     * List if no items exist.
     * @param budgetId the budget Id.  Cannot be null.
     * @param namespace the namespace.  Cannot be blank.
     * @return a list of {@link BudgetSubAwards}s or an empty list.
     * @throws IllegalArgumentException if the budgetId is null, if the namespace is blank
     */
    List<BudgetSubAwards> findBudgetSubAwardsByBudgetIdAndNamespace(Long budgetId, String namespace);

    /**
     * Finds the {@link BudgetSubAwards}s by budgetId and where the namespace is null.
     * Will return an empty List if no items exist.
     * @param budgetId the budget Id.  Cannot be null.
     * @return a list of {@link BudgetSubAwards}s or an empty list.
     * @throws IllegalArgumentException if the budgetId is null
     */
    List<BudgetSubAwards> findBudgetSubAwardsByBudgetIdAndNullNamespace(Long budgetId);
}
