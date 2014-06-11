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

import org.kuali.rice.krad.rules.rule.event.DocumentEvent;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;

/**
 * Event triggered when a budget Period is modified on a 
 * <code>{@link BudgetDocument}</code>
 *
 */
public interface BudgetPeriodEvent extends DocumentEvent{

    /**
     * @return <code>{@link org.kuali.coeus.common.budget.framework.period.BudgetPeriod}</code> that triggered this event.
     */
    public BudgetPeriod getBudgetPeriod();
    public int getBudgetPeriodNumber();
}
