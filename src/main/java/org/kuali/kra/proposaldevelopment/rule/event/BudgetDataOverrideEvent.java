/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.rule.event;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetChangedData;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.BudgetDataOverrideRule;
import org.kuali.rice.krad.rules.rule.BusinessRule;
import org.kuali.kra.rule.event.KraDocumentEventBase;

public class BudgetDataOverrideEvent extends KraDocumentEventBase {
private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalDataOverrideEvent.class);
    
    private BudgetChangedData budgetChangedData;
    
    /**
     * Constructs an BudgetDataOverrideEvent.
     * 
     * @param document proposal development document
     * @param criteria the budget Changed Data
     */
    public BudgetDataOverrideEvent(ProposalDevelopmentDocument document, BudgetChangedData budgetChangedData) {
        super("Override Budget Data " + getDocumentId(document), "", document);
        this.setBudgetChangedData(budgetChangedData);
        logEvent();
    }
    
    /**
     * @see org.kuali.rice.krad.rules.rule.event.KualiDocumentEventBase#validate()
     */
    public void validate() {
        super.validate();
    }
    
    /**
     * @see org.kuali.kra.rule.event.KraDocumentEventBase#logEvent()
     */
    @Override
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        LOG.debug(logMessage);
    }

    /**
     * @see org.kuali.rice.krad.rules.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class getRuleInterfaceClass() {
        return BudgetDataOverrideRule.class;
    }

    /**
     * @see org.kuali.rice.krad.rules.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.rice.krad.rules.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((BudgetDataOverrideRule) rule).processBudgetDataOverrideRules(this);
    }

        public void setBudgetChangedData(BudgetChangedData budgetChangedData) {
        this.budgetChangedData = budgetChangedData;
    }

    public BudgetChangedData getBudgetChangedData() {
        return budgetChangedData;
    }
}
