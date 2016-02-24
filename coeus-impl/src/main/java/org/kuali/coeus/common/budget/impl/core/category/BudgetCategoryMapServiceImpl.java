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
package org.kuali.coeus.common.budget.impl.core.category;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.api.core.category.BudgetCategoryMapContract;
import org.kuali.coeus.common.budget.api.core.category.BudgetCategoryMapService;
import org.kuali.coeus.common.budget.api.core.category.BudgetCategoryMappingContract;
import org.kuali.coeus.common.budget.framework.core.category.BudgetCategoryMap;
import org.kuali.coeus.common.budget.framework.core.category.BudgetCategoryMapping;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("budgetCategoryMapService")
public class BudgetCategoryMapServiceImpl implements BudgetCategoryMapService {

    public static final String KEY_MAPPING_NAME = "mappingName";
    public static final String KEY_TARGET_CATEGORY_CODE = "targetCategoryCode";

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Override
    public List<? extends BudgetCategoryMapContract> findCatMapByTargetAndMappingName(String targetCategoryCode, String mappingName) {
        return findByTargetAndMappingName(targetCategoryCode, mappingName, BudgetCategoryMap.class);
    }

    @Override
    public List<? extends BudgetCategoryMapContract> findCatMapByMappingName(String mappingName) {
        return findByMappingName(mappingName, BudgetCategoryMap.class);
    }

    @Override
    public List<? extends BudgetCategoryMappingContract> findCatMappingByTargetAndMappingName(String targetCategoryCode, String mappingName) {
        return findByTargetAndMappingName(targetCategoryCode, mappingName, BudgetCategoryMapping.class);
    }

    @Override
    public List<? extends BudgetCategoryMappingContract> findCatMappingByMappingName(String mappingName) {
        return findByMappingName(mappingName, BudgetCategoryMapping.class);
    }

    private <T extends BusinessObject> List<T> findByMappingName(String mappingName, Class<T> clazz) {
        if (StringUtils.isBlank(mappingName)) {
            throw new IllegalArgumentException("mappingName is blank");
        }
        return ListUtils.emptyIfNull((List<T>) businessObjectService.findMatching(clazz, Collections.singletonMap(KEY_MAPPING_NAME, mappingName)));
    }

    private <T extends BusinessObject> List<T> findByTargetAndMappingName(String targetCategoryCode, String mappingName, Class<T> clazz) {
        if (StringUtils.isBlank(targetCategoryCode)) {
            throw new IllegalArgumentException("targetCategoryCode is blank");
        }

        if (StringUtils.isBlank(mappingName)) {
            throw new IllegalArgumentException("mappingName is blank");
        }

        final Map<String, String> conditionMap = new HashMap<String, String>();
        conditionMap.put(KEY_MAPPING_NAME, mappingName);
        conditionMap.put(KEY_TARGET_CATEGORY_CODE, targetCategoryCode);
        return ListUtils.emptyIfNull((List<T>) businessObjectService.findMatching(clazz, conditionMap));
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}
