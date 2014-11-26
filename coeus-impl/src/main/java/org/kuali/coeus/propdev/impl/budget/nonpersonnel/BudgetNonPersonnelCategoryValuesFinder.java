package org.kuali.coeus.propdev.impl.budget.nonpersonnel;

import java.util.ArrayList;
import java.util.List;

import org.kuali.coeus.common.budget.framework.core.category.BudgetCategoryValuesFinder;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.criteria.Predicate;
import org.kuali.rice.core.api.criteria.PredicateFactory;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("budgetNonPersonnelBudgetCategoryValuesFinder")
public class BudgetNonPersonnelCategoryValuesFinder extends BudgetCategoryValuesFinder {
	
	@Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;
	
	@Override
    public List<KeyValue> getKeyValues() {
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(PredicateFactory.notEqual("budgetCategoryTypeCode", getPersonnelBudgetCategoryTypeCode()));
        List<KeyValue> keyValues = super.getKeyValues(predicates);
        keyValues.add(0, new ConcreteKeyValue("", "Select"));
        return keyValues;
    }

    private String getPersonnelBudgetCategoryTypeCode() {
        return this.getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_BUDGET, ParameterConstants.DOCUMENT_COMPONENT,Constants.BUDGET_CATEGORY_TYPE_PERSONNEL);
    }
    
	public ParameterService getParameterService() {
		return parameterService;
	}

	public void setParameterService(ParameterService parameterService) {
		this.parameterService = parameterService;
	}

}
