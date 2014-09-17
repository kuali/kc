/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.common.budget.impl.core;

import java.util.List;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("budgetPersonnelCostElementValuesFinder")
public class BudgetPersonnelCostElementValuesFinder extends CostElementValuesFinder {
    
	@Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;
	
    @Override
    public List<KeyValue> getKeyValues() {
    	setBudgetCategoryTypeCode(getPersonnelBudgetCategoryTypeCode());
        return super.getKeyValues();
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
