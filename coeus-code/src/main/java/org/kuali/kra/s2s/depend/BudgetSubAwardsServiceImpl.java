package org.kuali.kra.s2s.depend;

import org.apache.commons.collections4.ListUtils;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwards;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("budgetSubAwardsService")
public class BudgetSubAwardsServiceImpl implements BudgetSubAwardsService {

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Override
    public List<BudgetSubAwards> findBudgetSubAwardsByBudgetId(Long budgetId) {
        final Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("budgetId", budgetId);
        return ListUtils.emptyIfNull((List<BudgetSubAwards>) getBusinessObjectService().findMatching(BudgetSubAwards.class, paramMap));

    }

    @Override
    public List<BudgetSubAwards> findBudgetSubAwardsByBudgetIdAndNamespace(Long budgetId, String namespace) {
        final Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("budgetId", budgetId);
        paramMap.put("namespace", namespace);
        return ListUtils.emptyIfNull((List<BudgetSubAwards>) getBusinessObjectService().findMatching(BudgetSubAwards.class, paramMap));
    }

    @Override
    public List<BudgetSubAwards> findBudgetSubAwardsByBudgetIdAndNullNamespace(Long budgetId) {
        final Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("budgetId", budgetId);
        paramMap.put("namespace", null);
        return ListUtils.emptyIfNull((List<BudgetSubAwards>) getBusinessObjectService().findMatching(BudgetSubAwards.class, paramMap));
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}
