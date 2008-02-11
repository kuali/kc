/*
 * Copyright 2007 The Kuali Foundation.
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
package org.kuali.kra.budget.rules;

import org.kuali.kra.budget.rule.AddBudgetPeriodRule;
import org.kuali.kra.budget.rule.DeleteBudgetPeriodRule;
import org.kuali.kra.budget.rule.GenerateBudgetPeriodRule;
import org.kuali.kra.budget.rule.SaveBudgetPeriodRule;
import org.kuali.kra.budget.rule.event.AddBudgetPeriodEvent;
import org.kuali.kra.budget.rule.event.DeleteBudgetPeriodEvent;
import org.kuali.kra.budget.rule.event.GenerateBudgetPeriodEvent;
import org.kuali.kra.budget.rule.event.SaveBudgetPeriodEvent;
import org.kuali.core.document.Document;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.ObjectUtils;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

public class BudgetDocumentRule extends ResearchDocumentRuleBase implements AddBudgetPeriodRule, SaveBudgetPeriodRule, DeleteBudgetPeriodRule, GenerateBudgetPeriodRule{

    /**
     * @see org.kuali.kra.budget.rule.AddBudgetPeriodRule#processAddBudgetPeriodBusinessRules(org.kuali.kra.budget.document.BudgetDocument,org.kuali.kra.budget.bo.BudgetPeriod)
     */
    public boolean processAddBudgetPeriodBusinessRules(AddBudgetPeriodEvent addBudgetPeriodEvent) {
        return new BudgetPeriodRule().processAddBudgetPeriodBusinessRules(addBudgetPeriodEvent);    
    }

    public boolean processSaveBudgetPeriodBusinessRules(SaveBudgetPeriodEvent saveBudgetPeriodEvent) {
        return new BudgetPeriodRule().processSaveBudgetPeriodBusinessRules(saveBudgetPeriodEvent);    
    }
    
    public boolean processDeleteBudgetPeriodBusinessRules(DeleteBudgetPeriodEvent deleteBudgetPeriodEvent) {
        return new BudgetPeriodRule().processDeleteBudgetPeriodBusinessRules(deleteBudgetPeriodEvent);    
    }

    public boolean processGenerateBudgetPeriodBusinessRules(GenerateBudgetPeriodEvent generateBudgetPeriodEvent) {
        return new BudgetPeriodRule().processGenerateBudgetPeriodBusinessRules(generateBudgetPeriodEvent);    
    }
    
    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        if (!(document instanceof BudgetDocument)) {
            return false;
        }

        boolean valid = true;
        
        BudgetDocument budgetDocument = (BudgetDocument) document;
        
        GlobalVariables.getErrorMap().addToErrorPath("document");
        
        GlobalVariables.getErrorMap().addToErrorPath("proposal");
        if (ObjectUtils.isNull(budgetDocument.getProposal())) {
            budgetDocument.refreshReferenceObject("proposal");
        }
        valid &= processBudgetVersionsBusinessRule(budgetDocument.getProposal().getBudgetVersionOverviews());
        GlobalVariables.getErrorMap().removeFromErrorPath("proposal");
        
        GlobalVariables.getErrorMap().removeFromErrorPath("document");
        
        return true;
    }

}
