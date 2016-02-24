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
package org.kuali.coeus.propdev.impl.budget.nonpersonnel;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.budget.ProposalBudgetCategoryValueFinder;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.criteria.Predicate;
import org.kuali.rice.core.api.criteria.PredicateFactory;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.uif.view.ViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("budgetNonPersonnelBudgetCategoryValuesFinder")
public class BudgetNonPersonnelCategoryValuesFinder extends ProposalBudgetCategoryValueFinder {

    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;
    
    @Override
    protected List<Predicate> getPredicates(ProposalBudgetForm model) {
        String budgetCategoryTypeCode = model.getAddProjectBudgetLineItemHelper().getBudgetCategoryTypeCode();
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.isNotEmpty(budgetCategoryTypeCode)) {
            predicates.add(PredicateFactory.equal(BUDGET_CATEGORY_TYPE_CODE, budgetCategoryTypeCode));
        } else {
            predicates.add(PredicateFactory.notEqual(BUDGET_CATEGORY_TYPE_CODE,getPersonnelBudgetCategoryTypeCode()));
        }
        return predicates;
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
