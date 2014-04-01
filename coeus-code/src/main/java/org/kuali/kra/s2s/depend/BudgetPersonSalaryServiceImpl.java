package org.kuali.kra.s2s.depend;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.budget.personnel.BudgetPersonSalaryDetails;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component("budgetPersonSalaryService")
public class BudgetPersonSalaryServiceImpl implements BudgetPersonSalaryService {

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Override
    public List<BudgetPersonSalaryDetails> findSalaryDetailsByBudgetIdAndPersonIdAndBudgetPeriod(Long budgetId, String personId, Integer budgetPeriod) {
        if (budgetId == null) {
            throw new IllegalArgumentException("budgetId is null");
        }

        if (StringUtils.isBlank(personId)) {
            throw new IllegalArgumentException("personId is blank");
        }

        if (budgetPeriod == null) {
            throw new IllegalArgumentException("budgetPeriod is null");
        }

        final HashMap<String, Object> criteria = new HashMap<>();
        criteria.put("budgetId", budgetId);
        criteria.put("personId", personId);
        criteria.put("budgetPeriod", budgetPeriod);
        return ListUtils.emptyIfNull((List<BudgetPersonSalaryDetails>) businessObjectService.findMatchingOrderBy(BudgetPersonSalaryDetails.class, criteria, "personSequenceNumber", true));
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}
