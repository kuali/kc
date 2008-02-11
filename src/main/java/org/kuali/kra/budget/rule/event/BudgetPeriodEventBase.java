/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.kra.budget.rule.event;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.document.Document;
import org.kuali.core.util.ObjectUtils;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.rule.event.KraDocumentEventBase;

public abstract class BudgetPeriodEventBase extends KraDocumentEventBase implements BudgetPeriodEvent {

    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
    .getLog(BudgetPeriodEventBase.class);

    private BudgetPeriod budgetPeriod;
    private int budgetPeriodNumber;

    /**
     * @see org.kuali.kra.rule.event.KraDocumentEventBase#KraDocumentEventBase(String, String, Document)
     */
    protected BudgetPeriodEventBase(String description, String errorPathPrefix, BudgetDocument document,
            BudgetPeriod budgetPeriod) {
        super(description, errorPathPrefix, document);

        //by doing a deep copy, we are ensuring that the business rule class can't update
        //the original object by reference
        //this.budgetPeriod = (BudgetPeriod) ObjectUtils.deepCopy(budgetPeriod);
        this.budgetPeriod = budgetPeriod;
        logEvent();
    }

    /**
     * @see org.kuali.kra.rule.event.KraDocumentEventBase#KraDocumentEventBase(String, String, Document)
     */
    protected BudgetPeriodEventBase(String description, String errorPathPrefix, BudgetDocument document,
            int budgetPeriodNumber) {
        super(description, errorPathPrefix, document);

        //by doing a deep copy, we are ensuring that the business rule class can't update
        //the original object by reference
        //this.budgetPeriod = (BudgetPeriod) ObjectUtils.deepCopy(budgetPeriod);
        this.budgetPeriodNumber = budgetPeriodNumber;
        logEvent();
    }

    /**
     * @see org.kuali.kra.rule.event.KraDocumentEventBase#KraDocumentEventBase(String, String, Document)
     */
    protected BudgetPeriodEventBase(String description, String errorPathPrefix, BudgetDocument document) {
        super(description, errorPathPrefix, document);

        //by doing a deep copy, we are ensuring that the business rule class can't update
        //the original object by reference
        //this.budgetPeriod = (BudgetPeriod) ObjectUtils.deepCopy(budgetPeriod);
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

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#validate()
     */
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

