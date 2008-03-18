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
package org.kuali.kra.budget.rules;

import org.kuali.core.util.GlobalVariables;
import org.kuali.core.web.struts.form.KualiForm;
import org.kuali.kra.budget.bo.BudgetCostShare;
import org.kuali.kra.budget.document.BudgetDocumentContainer;
import org.kuali.kra.budget.rule.AddBudgetCostShareRule;
import org.kuali.kra.budget.rule.BudgetValidationBudgetCostShareRule;
import org.kuali.kra.budget.rule.event.AddBudgetCostShareEvent;
import org.kuali.kra.budget.rule.event.BudgetValidationBudgetCostShareEvent;

/**
 * Processes Budget Project Income rules
 */
public class BudgetCostShareRuleImpl implements AddBudgetCostShareRule, BudgetValidationBudgetCostShareRule {

    private static final String ADD_ERROR_KEY = "error.custom";
    
    /**
     * Provides general validation support
     */
    private ValidationHelper validationHelper;
    
    /**
     * Constructs a BudgetCostShareRuleImpl
     */
    public BudgetCostShareRuleImpl() {
        validationHelper = new ValidationHelper();
    }
    
    /**
     * @see org.kuali.kra.budget.rule.AddBudgetCostShareRule#processAddBudgetCostSharingRules(org.kuali.kra.budget.rule.event.AddBudgetCostShareEvent)
     */
    public boolean processAddBudgetCostShareBusinessRules(AddBudgetCostShareEvent budgetCostShareEvent) {
        return !areDuplicatesPresent(budgetCostShareEvent.getBudgetCostShare());
    }

    /**
     * @see org.kuali.kra.budget.rule.BudgetValidationBudgetCostShareRule#processBudgetValidationBudgetCostShareBusinessRules(org.kuali.kra.budget.rule.event.BudgetValidationBudgetCostShareEvent)
     */
    public boolean processBudgetValidationBudgetCostShareBusinessRules(BudgetValidationBudgetCostShareEvent budgetCostShareEvent) {
        return areRequiredRulesSatisfied(budgetCostShareEvent.getBudgetCostShare());
    }

    /**
     * This method ensures that an added BudgetCostShare won't duplicate another. A duplicate record would have the same source account, share amount, 
     * and fiscal year as another already in the list. 
     * @param budgetCostShare
     * @return
     */
    private boolean areDuplicatesPresent(BudgetCostShare testBudgetCostShare) {
        boolean duplicate = false;
        
        if(testBudgetCostShare == null) {
            return duplicate;
        }
        
        KualiForm form = GlobalVariables.getKualiForm();
        if(form instanceof BudgetDocumentContainer) {
            BudgetDocumentContainer budgetContainerForm = (BudgetDocumentContainer) form;
            
            for(BudgetCostShare budgetCostShare: budgetContainerForm.getBudgetDocument().getBudgetCostShares()) {
                duplicate = checkForDuplicateFields(testBudgetCostShare, budgetCostShare);
                if(duplicate) { break; }
            }            
        }

        return duplicate;
    }
	
	/**
     * This method checks each required field, tracking validation state
     * @param budgetCostShare The Budget Cost Share
     * @return Validation state; true if all required fields are not null, and if String, not empty
     */
    private boolean areRequiredRulesSatisfied(BudgetCostShare budgetCostShare) {
        boolean valid = validationHelper.checkRequiredField(budgetCostShare.getFiscalYear(), "newCostShare.fiscalYear", "Fiscal Year");
        valid &= validationHelper.checkRequiredField(budgetCostShare.getShareAmount(), "newCostShare.shareAmount", "Amount");
        valid &= validationHelper.checkRequiredField(budgetCostShare.getSourceAccount(), "newCostShare.sourceAccount", "Source Account");
        
        return valid;
    }
	
    /** 
     * This method checks if two BudgetCostShare objects are duplicates, meaning the fiscalYear, shareAmount, and sourceAccount are equal 
     * @param testBudgetCostShare
     * @param budgetCostShare
     * @return
     */
	private boolean checkForDuplicateFields(BudgetCostShare testBudgetCostShare, BudgetCostShare budgetCostShare) {
        boolean duplicate = testBudgetCostShare.equals(budgetCostShare);
        if(duplicate) {
            GlobalVariables.getErrorMap().putError("newCostShare.*", ADD_ERROR_KEY, "A Cost Share with the same Fiscal Year, Source Account and Amount exists in the table");
        }
        return duplicate;
    }

}
