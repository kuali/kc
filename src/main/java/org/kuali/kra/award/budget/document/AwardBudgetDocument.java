/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.award.budget.document;

import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.service.ParameterConstants.COMPONENT;
import org.kuali.rice.kns.service.ParameterConstants.NAMESPACE;

@NAMESPACE(namespace=Constants.MODULE_NAMESPACE_AWARD_BUDGET)
@COMPONENT(component=Constants.PARAMETER_COMPONENT_DOCUMENT)
public class AwardBudgetDocument extends BudgetDocument<org.kuali.kra.award.home.Award> {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 3564659576355229703L;
    /**
     * 
     * This method returns Budget object. Creates new budget instance if the budgets list is empty
     * @return Budget
     */
    public Budget getBudget(){
        if(getBudgets().isEmpty()){
            getBudgets().add(new AwardBudgetExt());
        }
        return getBudgets().get(0);
    }
    
    public AwardBudgetExt getAwardBudget(){
        return (AwardBudgetExt)getBudget();
    }

}
