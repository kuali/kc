package org.kuali.coeus.propdev.api.budget.subaward;


import java.util.List;

public interface BudgetSubAwardsService {

    /**
     * Finds the {@link BudgetSubAwardsContract}s by budgetId.  Will return an empty
     * List if no items exist.
     * @param budgetId the budget Id.  Cannot be null.
     * @return a list of {@link BudgetSubAwardsContract}s or an empty list.
     * @throws IllegalArgumentException if the budgetId is null
     */
    List<? extends BudgetSubAwardsContract> findBudgetSubAwardsByBudgetId(Long budgetId);

    /**
     * Finds the {@link BudgetSubAwardsContract}s by budgetId and namespace.  Will return an empty
     * List if no items exist.
     * @param budgetId the budget Id.  Cannot be null.
     * @param namespace the namespace.  Cannot be blank.
     * @return a list of {@link BudgetSubAwardsContract}s or an empty list.
     * @throws IllegalArgumentException if the budgetId is null, if the namespace is blank
     */
    List<? extends BudgetSubAwardsContract> findBudgetSubAwardsByBudgetIdAndNamespace(Long budgetId, String namespace);

    /**
     * Finds the {@link BudgetSubAwardsContract}s by budgetId and where the namespace is null.
     * Will return an empty List if no items exist.
     * @param budgetId the budget Id.  Cannot be null.
     * @return a list of {@link BudgetSubAwardsContract}s or an empty list.
     * @throws IllegalArgumentException if the budgetId is null
     */
    List<? extends BudgetSubAwardsContract> findBudgetSubAwardsByBudgetIdAndNullNamespace(Long budgetId);
}
