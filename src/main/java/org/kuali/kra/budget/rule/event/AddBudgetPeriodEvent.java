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
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.rule.AddBudgetPeriodRule;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.BusinessRule;

public class AddBudgetPeriodEvent extends BudgetPeriodEventBase{
    /**
     * Constructs an AddBudgetPeriodEvent with the given errorPathPrefix, document, and budgetPeriod.
     * 
     * @param errorPathPrefix
     * @param budgetDocument
     * @param budgetPeriod
     */
    public AddBudgetPeriodEvent(String errorPathPrefix, BudgetDocument document, BudgetPeriod budgetPeriod) {
        super("adding budget period to document " + getDocumentId(document), errorPathPrefix, document, budgetPeriod);
    }

    /**
     * Constructs an AddBudgetPeriodEvent with the given errorPathPrefix, document, and budgetPeriod.
     * 
     * @param errorPathPrefix
     * @param document
     * @param budgetPeriod
     */
    public AddBudgetPeriodEvent(String errorPathPrefix, Document document, BudgetPeriod budgetPeriod) {
        this(errorPathPrefix, (BudgetDocument) document, budgetPeriod);
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class getRuleInterfaceClass() {
        return AddBudgetPeriodRule.class;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddBudgetPeriodRule) rule).processAddBudgetPeriodBusinessRules(this);
    }
    
}

