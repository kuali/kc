/*
 * Copyright 2006-2009 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.rule;

import org.kuali.core.rule.BusinessRule;
import org.kuali.kra.proposaldevelopment.rule.event.AddBudgetVersionEvent;

/**
 * A composited rule of the {@link BudgetDocumentRule}. It is expected that the {@link BudgetDocumentRule} will call this rule directly on save,
 * so it does not use or require an event.
 * 
 **/
public interface AddBudgetVersionRule  extends BusinessRule {

    /**
     * Entry method for the business rule. 
     *
     * @param event The {@link AddBudgetVersionEvent} triggering the rule
     * @returns true if it passed, false if it failed
     */
    public boolean processAddBudgetVersion(AddBudgetVersionEvent event);
 }
