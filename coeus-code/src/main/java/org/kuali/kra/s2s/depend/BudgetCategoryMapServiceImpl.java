package org.kuali.kra.s2s.depend;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.budget.core.BudgetCategoryMap;
import org.kuali.kra.budget.core.BudgetCategoryMapping;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("budgetCategoryMapService")
public class BudgetCategoryMapServiceImpl implements BudgetCategoryMapService {

    public static final String KEY_MAPPING_NAME = "mappingName";
    public static final String KEY_TARGET_CATEGORY_CODE = "targetCategoryCode";

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Override
    public List<BudgetCategoryMap> findCatMapByTargetAndMappingName(String targetCategoryCode, String mappingName) {
        return findByTargetAndMappingName(targetCategoryCode, mappingName, BudgetCategoryMap.class);
    }

    @Override
    public List<BudgetCategoryMap> findCatMapByMappingName(String mappingName) {
        return findByMappingName(mappingName, BudgetCategoryMap.class);
    }

    @Override
    public List<BudgetCategoryMapping> findCatMappingByTargetAndMappingName(String targetCategoryCode, String mappingName) {
        return findByTargetAndMappingName(targetCategoryCode, mappingName, BudgetCategoryMapping.class);
    }

    @Override
    public List<BudgetCategoryMapping> findCatMappingByMappingName(String mappingName) {
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
