/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.budget.rule.event;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.rule.BudgetCostShareAllocationRule;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.kns.rule.BusinessRule;

public class BudgetCostShareAllocationEvent extends KraDocumentEventBase {
    private static final Log LOG = LogFactory.getLog(BudgetCostShareAllocationEvent.class);
    
    public BudgetCostShareAllocationEvent(String description, String errorPathPrefix, BudgetDocument document) {
        super(description, errorPathPrefix, document);
    }

    @Override
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");

        LOG.debug((getBudgetDocument() == null) ? "null budgetDocument" : getBudgetDocument().toString());
    }

    public Class<BudgetCostShareAllocationRule> getRuleInterfaceClass() {
        return BudgetCostShareAllocationRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((BudgetCostShareAllocationRule) rule).processBudgetCostShareAllocationBusinessRules(this);
    }

    public BudgetDocument getBudgetDocument() {
        return (BudgetDocument) getDocument();
    }

}
