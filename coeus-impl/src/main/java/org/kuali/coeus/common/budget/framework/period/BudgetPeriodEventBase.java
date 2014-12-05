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
package org.kuali.coeus.common.budget.framework.period;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetEventBase;

public abstract class BudgetPeriodEventBase extends BudgetEventBase {

    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
    .getLog(BudgetPeriodEventBase.class);

    private BudgetPeriod budgetPeriod;
    private int budgetPeriodNumber;

    protected BudgetPeriodEventBase(Budget budget, BudgetPeriod budgetPeriod) {
    	super(budget);
        this.budgetPeriod = budgetPeriod;
    }

    protected BudgetPeriodEventBase(Budget budget, BudgetPeriod budgetPeriod, String errorPath) {
    	super(budget, errorPath);
        this.budgetPeriod = budgetPeriod;
    }
    
    /**
     * @return <code>{@link BudgetPeriod}</code> that triggered this event.
     */
    public BudgetPeriod getBudgetPeriod() {
        return budgetPeriod;
    }

    /**
     * @return <code>{@link BudgetPeriod}</code> that triggered this event.
     */
    public int getBudgetPeriodNumber() {
        return budgetPeriodNumber;
    }
}

