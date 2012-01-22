/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.budget.parameters;

import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.rice.krad.rules.rule.event.KualiDocumentEvent;

/**
 * Event triggered when a budget Period is modified on a 
 * <code>{@link BudgetDocument}</code>
 *
 */
public interface BudgetPeriodEvent extends KualiDocumentEvent{

    /**
     * @return <code>{@link BudgetPeriod}</code> that triggered this event.
     */
    public BudgetPeriod getBudgetPeriod();
    public int getBudgetPeriodNumber();
}
