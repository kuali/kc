/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.budget.framework.period;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

public class DeleteBudgetPeriodEvent extends BudgetPeriodEventBase {
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

    @Override
    public Class getRuleInterfaceClass() {
        return DeleteBudgetPeriodRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((DeleteBudgetPeriodRule) rule).processDeleteBudgetPeriodBusinessRules(this);
    }
    
}

