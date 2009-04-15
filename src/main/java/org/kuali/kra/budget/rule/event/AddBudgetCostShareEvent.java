/*
 * Copyright 2006-2009 The Kuali Foundation
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
import org.kuali.core.document.Document;
import org.kuali.core.rule.BusinessRule;
import org.kuali.kra.budget.bo.BudgetDistributionAndIncomeComponent;
import org.kuali.kra.budget.rule.AddBudgetCostShareRule;
import org.kuali.kra.rule.event.KraDocumentEventBase;

public class AddBudgetCostShareEvent extends KraDocumentEventBase {
    private static final Log LOG = LogFactory.getLog(AddBudgetCostShareEvent.class);
    
    private BudgetDistributionAndIncomeComponent budgetCostShare;
    
    public AddBudgetCostShareEvent(String description, String errorPathPrefix, Document document, BudgetDistributionAndIncomeComponent budgetCostShare) {
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
        return ((AddBudgetCostShareRule) rule).processAddBudgetCostShareBusinessRules(this);
    }

    public BudgetDistributionAndIncomeComponent getBudgetCostShare() {
        return budgetCostShare;
    }

}
