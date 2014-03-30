package org.kuali.kra.s2s.depend;

import org.kuali.kra.budget.core.BudgetCategoryMap;
import org.kuali.kra.budget.core.BudgetCategoryMapping;

import java.util.List;

public interface BudgetCategoryMapService {

    /**
     * Finds the {@link BudgetCategoryMap}s by targetCategoryCode and mappingName.  Will return an empty
     * List if no items exist.
     * @param targetCategoryCode the target category code.  Cannot be blank.
     * @param mappingName the mapping name.  Cannot be blank.
     * @return a list of {@link BudgetCategoryMap}s or an empty list.
     * @throws IllegalArgumentException if the targetCategoryCode is blank, if the mappingName is blank
     */
    List<BudgetCategoryMap> findCatMapByTargetAndMappingName(String targetCategoryCode, String mappingName);

    /**
     * Finds the {@link BudgetCategoryMap}s by mappingName.  Will return an empty
     * List if no items exist.
     * @param mappingName the mapping name.  Cannot be blank.
     * @return a list of {@link BudgetCategoryMap}s or an empty list.
     * @throws IllegalArgumentException if the mappingName is blank
     */
    List<BudgetCategoryMap> findCatMapByMappingName(String mappingName);

    /**
     * Finds the {@link BudgetCategoryMapping}s by targetCategoryCode and mappingName.  Will return an empty
     * List if no items exist.
     * @param targetCategoryCode the target category code.  Cannot be blank.
     * @param mappingName the mapping name.  Cannot be blank.
     * @return a list of {@link BudgetCategoryMapping}s or an empty list.
     * @throws IllegalArgumentException if the targetCategoryCode is blank, if the mappingName is blank
     */
    List<BudgetCategoryMapping> findCatMappingByTargetAndMappingName(String targetCategoryCode, String mappingName);

    /**
     * Finds the {@link BudgetCategoryMapping}s by mappingName.  Will return an empty
     * List if no items exist.
     * @param mappingName the mapping name.  Cannot be blank.
     * @return a list of {@link BudgetCategoryMapping}s or an empty list.
     * @throws IllegalArgumentException if the mappingName is blank
     */
    List<BudgetCategoryMapping> findCatMappingByMappingName(String mappingName);
}
