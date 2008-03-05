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
import org.kuali.kra.budget.bo.BudgetProjectIncome;
import org.kuali.kra.budget.rule.AddBudgetProjectIncomeRule;
import org.kuali.kra.rule.event.KraDocumentEventBase;

public class AddBudgetProjectIncomeEvent extends KraDocumentEventBase {
    private static final Log LOG = LogFactory.getLog(BudgetPeriodEventBase.class);
    
    private BudgetProjectIncome budgetProjectIncome;
    
    public AddBudgetProjectIncomeEvent(String description, String errorPathPrefix, Document document, 
                                    BudgetProjectIncome budgetProjectIncome) {
        super(description, errorPathPrefix, document);
        this.budgetProjectIncome = budgetProjectIncome;
    }

    @Override
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");

        //vary logging detail as needed
        String msg = (budgetProjectIncome == null) ? "null budgetProjectIncome" : budgetProjectIncome.toString();
        LOG.debug(msg);
    }

    public Class getRuleInterfaceClass() {
        return AddBudgetProjectIncomeRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddBudgetProjectIncomeRule) rule).processAddBudgetProjectIncomeBusinessRules(this);
    }

    public BudgetProjectIncome getBudgetProjectIncome() {
        return budgetProjectIncome;
    }

}
