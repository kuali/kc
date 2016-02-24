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
package org.kuali.coeus.propdev.impl.budget;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.core.category.BudgetCategoryValuesFinder;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetForm;
import org.kuali.rice.core.api.criteria.Predicate;
import org.kuali.rice.core.api.criteria.PredicateFactory;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.view.ViewModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("proposalBudgetCategoryValueFinder")
public class ProposalBudgetCategoryValueFinder extends BudgetCategoryValuesFinder {

    public static final String BUDGET_CATEGORY_TYPE_CODE = "budgetCategoryTypeCode";
    public static final String SELECT = "Select";

    @Override
    public List<KeyValue> getKeyValues(ViewModel model) {
        List<KeyValue> keyValues = super.getKeyValues(getPredicates((ProposalBudgetForm) model));
        keyValues.add(0, new ConcreteKeyValue(StringUtils.EMPTY, SELECT));
        return keyValues;
    }

    protected List<Predicate> getPredicates(ProposalBudgetForm model) {
        String budgetCategoryTypeCode = model.getAddProjectBudgetLineItemHelper().getBudgetCategoryTypeCode();
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.isNotEmpty(budgetCategoryTypeCode)) {
            predicates.add(PredicateFactory.equal(BUDGET_CATEGORY_TYPE_CODE, budgetCategoryTypeCode));
        }
        return predicates;
    }
}
