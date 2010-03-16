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
package org.kuali.kra.award.budget;

import org.kuali.kra.award.home.Award;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.versions.AddBudgetVersionEvent;
import org.kuali.kra.budget.versions.BudgetVersionRule;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.util.GlobalVariables;

public class AwardBudgetVersionRule extends BudgetVersionRule {

    @Override
    public boolean processAddBudgetVersion(AddBudgetVersionEvent event) {
        boolean success =  super.processAddBudgetVersion(event);
        Budget budget = event.getBudget();
        Award award = (Award) budget.getBudgetParent();
        if(!award.getObligatedTotal().isPositive()){
            GlobalVariables.getErrorMap().putError(event.getErrorPathPrefix(), 
                  KeyConstants.ERROR_BUDGET_OBLIGATED_AMOUNT_INVALID, "Name");
            success &= false;
        }
        return success;
    }
}
