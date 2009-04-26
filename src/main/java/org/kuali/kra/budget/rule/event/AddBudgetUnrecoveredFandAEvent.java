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
import org.kuali.kra.budget.bo.BudgetUnrecoveredFandA;
import org.kuali.kra.budget.rule.AddBudgetUnrecoveredFandARule;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.BusinessRule;

public class AddBudgetUnrecoveredFandAEvent extends KraDocumentEventBase {
    private static final Log LOG = LogFactory.getLog(AddBudgetUnrecoveredFandAEvent.class);
    
    private BudgetUnrecoveredFandA budgetUnrecoveredFandA;
    
    public AddBudgetUnrecoveredFandAEvent(String description, String errorPathPrefix, Document document, BudgetUnrecoveredFandA budgetUnrecoveredFandA) {
        super(description, errorPathPrefix, document);
        this.budgetUnrecoveredFandA = budgetUnrecoveredFandA;
    }

    @Override
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");

        //vary logging detail as needed
        String msg = (budgetUnrecoveredFandA == null) ? "null budgetUnrecoveredFandA" : budgetUnrecoveredFandA.toString();
        LOG.debug(msg);
    }

    public Class<AddBudgetUnrecoveredFandARule> getRuleInterfaceClass() {
        return AddBudgetUnrecoveredFandARule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddBudgetUnrecoveredFandARule) rule).processAddBudgetUnrecoveredFandABusinessRules(this);
    }

    public BudgetUnrecoveredFandA getBudgetUnrecoveredFandA() {
        return budgetUnrecoveredFandA;
    }

}
