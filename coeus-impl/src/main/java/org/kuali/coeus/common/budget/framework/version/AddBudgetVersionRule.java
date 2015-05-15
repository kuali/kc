/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.common.budget.framework.version;

import org.kuali.coeus.common.budget.framework.core.BudgetDocumentRule;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * A composited rule of the {@link BudgetDocumentRule}. It is expected that the {@link BudgetDocumentRule} will call this rule directly on save,
 * so it does not use or require an event.
 * 
 **/
public interface AddBudgetVersionRule  extends BusinessRule {

    /**
     * Entry method for the business rule. 
     *
     * @param event The {@link org.kuali.coeus.common.budget.framework.version.AddBudgetVersionEvent} triggering the rule
     * @return true if it passed, false if it failed
     */
    public boolean processAddBudgetVersionName(AddBudgetVersionEvent event);
    
    public boolean processAddBudgetVersion(AddBudgetVersionEvent event) throws WorkflowException;
    
 }
