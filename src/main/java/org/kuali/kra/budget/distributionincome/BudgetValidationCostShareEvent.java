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
package org.kuali.kra.budget.distributionincome;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.BusinessRule;

public class BudgetValidationCostShareEvent extends KraDocumentEventBase {
    private static final Log LOG = LogFactory.getLog(BudgetValidationCostShareEvent.class);
    
    private BudgetCostShare budgetCostShare;
    
    public BudgetValidationCostShareEvent(String description, String errorPathPrefix, Document document, BudgetCostShare budgetCostShare) {
        super(description, errorPathPrefix, document);
        this.budgetCostShare = budgetCostShare;
    }

    @Override
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");

        LOG.debug((budgetCostShare == null) ? "null budgetCostShare" : budgetCostShare.toString());
    }

    public Class<BudgetValidationCostShareRule> getRuleInterfaceClass() {
        return BudgetValidationCostShareRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((BudgetValidationCostShareRule) rule).processBudgetValidationCostShareBusinessRules(this);
    }

    public BudgetCostShare getBudgetCostShare() {
        return budgetCostShare;
    }

}
