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
package org.kuali.kra.budget.rules;

import org.kuali.kra.budget.bo.BudgetUnrecoveredFandA;
import org.kuali.kra.budget.document.BudgetDocumentContainer;
import org.kuali.kra.budget.rule.AddBudgetUnrecoveredFandARule;
import org.kuali.kra.budget.rule.BudgetUnrecoveredFandAAllocationRule;
import org.kuali.kra.budget.rule.BudgetValidationUnrecoveredFandARule;
import org.kuali.kra.budget.rule.event.AddBudgetUnrecoveredFandAEvent;
import org.kuali.kra.budget.rule.event.BudgetUnrecoveredFandAAllocationEvent;
import org.kuali.kra.budget.rule.event.BudgetValidationUnrecoveredFandAEvent;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiForm;

/**
 * Processes Budget Project Income rules
 */
public class BudgetUnrecoveredFandARuleImpl implements AddBudgetUnrecoveredFandARule, BudgetValidationUnrecoveredFandARule, BudgetUnrecoveredFandAAllocationRule {

private static final String ADD_ERROR_KEY = "error.custom";
    
    /**
     * Provides general validation support
     */
    private ValidationHelper validationHelper;
    
    /**
     * Constructor
     */
    public BudgetUnrecoveredFandARuleImpl() {
        validationHelper = new ValidationHelper();
    }
    
    /**
     * 
     * @see org.kuali.kra.budget.rule.AddBudgetUnrecoveredFandARule#processAddBudgetUnrecoveredFandABusinessRules(org.kuali.kra.budget.rule.event.AddBudgetUnrecoveredFandAEvent)
     */
    public boolean processAddBudgetUnrecoveredFandABusinessRules(AddBudgetUnrecoveredFandAEvent budgetUnrecoveredFandAEvent) {
        return !areDuplicatesPresent(budgetUnrecoveredFandAEvent.getBudgetUnrecoveredFandA());
    }

    /**
     * 
     * @see org.kuali.kra.budget.rule.BudgetValidationUnrecoveredFandARule#processBudgetValidationUnrecoveredFandABusinessRules(org.kuali.kra.budget.rule.event.BudgetValidationUnrecoveredFandAEvent)
     */
    public boolean processBudgetValidationUnrecoveredFandABusinessRules(BudgetValidationUnrecoveredFandAEvent budgetUnrecoveredFandAEvent) {
        return areRequiredRulesSatisfied(budgetUnrecoveredFandAEvent.getBudgetUnrecoveredFandA());
    }

    /**
     * This method ensures that an added BudgetCostShare won't duplicate another. A duplicate record would have the same source account, share amount, 
     * and fiscal year as another already in the list. 
     * @param budgetCostShare
     * @return
     */
    private boolean areDuplicatesPresent(BudgetUnrecoveredFandA testBudgetUnrecoveredFandA) {
        boolean duplicate = false;
        
        if(testBudgetUnrecoveredFandA == null) {
            return duplicate;
        }
        
        KualiForm form = GlobalVariables.getKualiForm();
        if(form instanceof BudgetDocumentContainer) {
            BudgetDocumentContainer budgetContainerForm = (BudgetDocumentContainer) form;
            
            for(BudgetUnrecoveredFandA budgetUnrecoveredFandA: budgetContainerForm.getBudgetDocument().getBudgetUnrecoveredFandAs()) {
                duplicate = checkForDuplicateFields(testBudgetUnrecoveredFandA, budgetUnrecoveredFandA);
                if(duplicate) { break; }
            }            
        }

        return duplicate;
    }
    
    /**
     * This method checks each required field, tracking validation state
     * @param budgetUnrecoveredFandA The Budget Cost Share
     * @return Validation state; true if all required fields are not null, and if String, not empty
     */
    private boolean areRequiredRulesSatisfied(BudgetUnrecoveredFandA budgetUnrecoveredFandA) {
        boolean valid = validationHelper.checkRequiredField(budgetUnrecoveredFandA.getFiscalYear(), "budgetUnrecoveredFandA.fiscalYear", "Fiscal Year");
        valid &= validationHelper.checkRequiredField(budgetUnrecoveredFandA.getAmount(), "budgetUnrecoveredFandA.amount", "Amount");
        valid &= validationHelper.checkRequiredField(budgetUnrecoveredFandA.getOnCampusFlag(), "budgetUnrecoveredFandA.campus", "Campus");
        valid &= validationHelper.checkRequiredField(budgetUnrecoveredFandA.getApplicableRate(), "budgetUnrecoveredFandA.applicableRate", "ApplicableRate");
        valid &= validationHelper.checkRequiredField(budgetUnrecoveredFandA.getSourceAccount(), "budgetUnrecoveredFandA.sourceAccount", "Source Account");
        
        return valid;
    }
    
    /** 
     * This method checks if two BudgetCostShare objects are duplicates, meaning the fiscalYear, shareAmount, and sourceAccount are equal 
     * @param testBudgetCostShare
     * @param budgetCostShare
     * @return
     */
    private boolean checkForDuplicateFields(BudgetUnrecoveredFandA testBudgetUnrecoveredFandA, BudgetUnrecoveredFandA budgetUnrecoveredFandA) {
        boolean duplicate = testBudgetUnrecoveredFandA.equals(budgetUnrecoveredFandA);
        if(duplicate) {
            GlobalVariables.getErrorMap().putError("newUnrecoveredFandA.*", ADD_ERROR_KEY, "A duplicate Unrecovered F&A exists in the table");
        }
        return duplicate;
    }

    /**
     * 
     * @see org.kuali.kra.budget.rule.BudgetUnrecoveredFandAAllocationRule#processBudgetUnrecoveredFandAAllocationBusinessRules(org.kuali.kra.budget.rule.event.BudgetUnrecoveredFandAAllocationEvent)
     */
    public boolean processBudgetUnrecoveredFandAAllocationBusinessRules(BudgetUnrecoveredFandAAllocationEvent budgetUnrecoveredFandAEvent) {
        boolean result = budgetUnrecoveredFandAEvent.getBudgetDocument().getUnallocatedUnrecoveredFandA().isNonZero();
        if (result) {
            GlobalVariables.getErrorMap().putError("unrecoveredFandA*", ADD_ERROR_KEY, "Unrecovered F&A allocation doesn't total available unrecovered F&A");
        }
        return result;
    }
}
