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
import org.kuali.core.document.Document;
import org.kuali.core.rule.BusinessRule;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.rule.DeleteBudgetPeriodRule;

public class DeleteBudgetPeriodEvent extends BudgetPeriodEventBase{
    /**
     * Constructs an DeleteBudgetPeriodEvent with the given errorPathPrefix, document, and budgetPeriod.
     * 
     * @param errorPathPrefix
     * @param budgetDocument
     * @param budgetPeriod
     */
    public DeleteBudgetPeriodEvent(String errorPathPrefix, BudgetDocument document, int budgetPeriodNumber) {
        super("deleting budget period to document " + getDocumentId(document), errorPathPrefix, document, budgetPeriodNumber);
    }

    /**
     * Constructs an DeleteBudgetPeriodEvent with the given errorPathPrefix, document, and budgetPeriod.
     * 
     * @param errorPathPrefix
     * @param document
     * @param budgetPeriod
     */
    public DeleteBudgetPeriodEvent(String errorPathPrefix, Document document, int budgetPeriodNumber) {
        this(errorPathPrefix, (BudgetDocument) document, budgetPeriodNumber);
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class getRuleInterfaceClass() {
        return DeleteBudgetPeriodRule.class;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((DeleteBudgetPeriodRule) rule).processDeleteBudgetPeriodBusinessRules(this);
    }
    
}

