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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;

public abstract class BudgetPeriodEventBase extends KcDocumentEventBase implements BudgetPeriodEvent {

    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
    .getLog(BudgetPeriodEventBase.class);

    private BudgetPeriod budgetPeriod;
    private int budgetPeriodNumber;

    protected BudgetPeriodEventBase(String description, String errorPathPrefix, BudgetDocument document,
            BudgetPeriod budgetPeriod) {
        super(description, errorPathPrefix, document);

        this.budgetPeriod = budgetPeriod;
        logEvent();
    }

    protected BudgetPeriodEventBase(String description, String errorPathPrefix, BudgetDocument document,
            int budgetPeriodNumber) {
        super(description, errorPathPrefix, document);

        this.budgetPeriodNumber = budgetPeriodNumber;
        logEvent();
    }

    protected BudgetPeriodEventBase(String description, String errorPathPrefix, BudgetDocument document) {
        super(description, errorPathPrefix, document);
        logEvent();
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

    @Override
    public void validate() {
        super.validate();
    }

    /**
     * Logs the event type and some information about the associated budget period
     */
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");

        //vary logging detail as needed
        if (getBudgetPeriod() == null) {
            logMessage.append("null budgetPeriod");
        }
        else {
            logMessage.append(getBudgetPeriod().toString());
        }

        LOG.debug(logMessage);
    }
}

