package org.kuali.coeus.propdev.impl.budget.subaward;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.api.budget.subaward.BudgetSubAwardsContract;
import org.kuali.coeus.propdev.api.budget.subaward.BudgetSubAwardsService;

import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.krad.data.DataObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.kuali.rice.core.api.criteria.PredicateFactory.*;

@Service("budgetSubAwardsService")
public class BudgetSubAwardsServiceImpl implements BudgetSubAwardsService {

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Override
    public List<? extends BudgetSubAwardsContract> findBudgetSubAwardsByBudgetId(Long budgetId) {
        if (budgetId == null) {
            throw new IllegalArgumentException("budgetId is null");
        }

        return ListUtils.emptyIfNull(getDataObjectService().findMatching(BudgetSubAwards.class,
                QueryByCriteria.Builder.fromPredicates(equal("budgetId", budgetId))).getResults());
    }

    @Override
    public List<? extends BudgetSubAwardsContract> findBudgetSubAwardsByBudgetIdAndNamespace(Long budgetId, String namespace) {
        if (budgetId == null) {
            throw new IllegalArgumentException("budgetId is null");
        }

        if (StringUtils.isBlank(namespace)) {
            throw new IllegalArgumentException("namespace is blank");
        }

        return ListUtils.emptyIfNull(getDataObjectService().findMatching(BudgetSubAwards.class,
                QueryByCriteria.Builder.fromPredicates(equal("budgetId", budgetId), equal("namespace", namespace))
        ).getResults());
    }

    @Override
    public List<? extends BudgetSubAwardsContract> findBudgetSubAwardsByBudgetIdAndNullNamespace(Long budgetId) {
        if (budgetId == null) {
            throw new IllegalArgumentException("budgetId is null");
        }

        return ListUtils.emptyIfNull(getDataObjectService().findMatching(BudgetSubAwards.class,
                QueryByCriteria.Builder.fromPredicates(equal("budgetId", budgetId), isNull("namespace"))
        ).getResults());
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }
}
