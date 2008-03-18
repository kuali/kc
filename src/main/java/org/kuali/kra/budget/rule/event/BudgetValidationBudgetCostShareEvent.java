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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.core.document.Document;
import org.kuali.core.rule.BusinessRule;
import org.kuali.kra.budget.bo.BudgetCostShare;
import org.kuali.kra.budget.rule.AddBudgetCostShareRule;
import org.kuali.kra.budget.rule.BudgetValidationBudgetCostShareRule;
import org.kuali.kra.rule.event.KraDocumentEventBase;

public class BudgetValidationBudgetCostShareEvent extends KraDocumentEventBase {
    private static final Log LOG = LogFactory.getLog(BudgetValidationBudgetCostShareEvent.class);
    
    private BudgetCostShare budgetCostShare;
    
    public BudgetValidationBudgetCostShareEvent(String description, String errorPathPrefix, Document document, BudgetCostShare budgetCostShare) {
        super(description, errorPathPrefix, document);
        this.budgetCostShare = budgetCostShare;
    }

    @Override
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");

        LOG.debug((budgetCostShare == null) ? "null budgetCostShare" : budgetCostShare.toString());
    }

    public Class<AddBudgetCostShareRule> getRuleInterfaceClass() {
        return AddBudgetCostShareRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((BudgetValidationBudgetCostShareRule) rule).processBudgetValidationBudgetCostShareBusinessRules(this);
    }

    public BudgetCostShare getBudgetCostShare() {
        return budgetCostShare;
    }

}
