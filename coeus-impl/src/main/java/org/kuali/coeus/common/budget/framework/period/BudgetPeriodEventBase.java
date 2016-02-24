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

