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
import org.kuali.coeus.common.budget.impl.core.CostElementValuesFinder;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.uif.view.ViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("budgetNonPersonnelCostElementValuesFinder")
public class BudgetNonPersonnelCostElementValuesFinder extends CostElementValuesFinder {

    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;

    public List<KeyValue> getKeyValues(ViewModel model) {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        String budgetCategoryCode = ((ProposalBudgetForm)model).getAddProjectBudgetLineItemHelper().getBudgetLineItem().getBudgetCategoryCode();
        String budgetCategoryTypeCode = ((ProposalBudgetForm)model).getAddProjectBudgetLineItemHelper().getBudgetCategoryTypeCode();

        boolean budgetCategoryTypeCodeEqual = true;
        if(StringUtils.isEmpty(budgetCategoryTypeCode)) {
            budgetCategoryTypeCode = getPersonnelBudgetCategoryTypeCode();
            budgetCategoryTypeCodeEqual = false;
        }
        return super.getKeyValues(budgetCategoryTypeCode, budgetCategoryTypeCodeEqual ,budgetCategoryCode);

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
