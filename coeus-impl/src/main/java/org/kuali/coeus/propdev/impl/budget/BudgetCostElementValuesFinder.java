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

import org.kuali.coeus.common.budget.impl.core.CostElementValuesFinder;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetForm;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.view.ViewModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("budgetCostElementValuesFinder")
public class BudgetCostElementValuesFinder extends CostElementValuesFinder {
    public List<KeyValue> getKeyValues(ViewModel model) {
        String budgetCategoryCode = ((ProposalBudgetForm)model).getAddProjectBudgetLineItemHelper().getBudgetLineItem().getBudgetCategoryCode();
        String budgetCategoryTypeCode = ((ProposalBudgetForm)model).getAddProjectBudgetLineItemHelper().getBudgetCategoryTypeCode();

        return super.getKeyValues(budgetCategoryTypeCode, true ,budgetCategoryCode);

    }
}
