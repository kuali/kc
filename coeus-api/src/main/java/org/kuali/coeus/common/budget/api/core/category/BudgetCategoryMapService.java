package org.kuali.coeus.common.budget.api.core.category;


import java.util.List;

public interface BudgetCategoryMapService {

    /**
     * Finds the {@link org.kuali.coeus.common.budget.api.core.category.BudgetCategoryMapContract}s by targetCategoryCode and mappingName.  Will return an empty
     * List if no items exist.
     * @param targetCategoryCode the target category code.  Cannot be blank.
     * @param mappingName the mapping name.  Cannot be blank.
     * @return a list of {@link org.kuali.coeus.common.budget.api.core.category.BudgetCategoryMapContract}s or an empty list.
     * @throws IllegalArgumentException if the targetCategoryCode is blank, if the mappingName is blank
     */
    List<? extends BudgetCategoryMapContract> findCatMapByTargetAndMappingName(String targetCategoryCode, String mappingName);

    /**
     * Finds the {@link BudgetCategoryMapContract}s by mappingName.  Will return an empty
     * List if no items exist.
     * @param mappingName the mapping name.  Cannot be blank.
     * @return a list of {@link BudgetCategoryMapContract}s or an empty list.
     * @throws IllegalArgumentException if the mappingName is blank
     */
    List<? extends BudgetCategoryMapContract> findCatMapByMappingName(String mappingName);

    /**
     * Finds the {@link org.kuali.coeus.common.budget.api.core.category.BudgetCategoryMappingContract}s by targetCategoryCode and mappingName.  Will return an empty
     * List if no items exist.
     * @param targetCategoryCode the target category code.  Cannot be blank.
     * @param mappingName the mapping name.  Cannot be blank.
     * @return a list of {@link org.kuali.coeus.common.budget.api.core.category.BudgetCategoryMappingContract}s or an empty list.
     * @throws IllegalArgumentException if the targetCategoryCode is blank, if the mappingName is blank
     */
    List<? extends BudgetCategoryMappingContract> findCatMappingByTargetAndMappingName(String targetCategoryCode, String mappingName);

    /**
     * Finds the {@link BudgetCategoryMappingContract}s by mappingName.  Will return an empty
     * List if no items exist.
     * @param mappingName the mapping name.  Cannot be blank.
     * @return a list of {@link BudgetCategoryMappingContract}s or an empty list.
     * @throws IllegalArgumentException if the mappingName is blank
     */
    List<? extends BudgetCategoryMappingContract> findCatMappingByMappingName(String mappingName);
}
