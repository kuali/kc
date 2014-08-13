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
package org.kuali.coeus.common.budget.impl.distribution;

import org.kuali.coeus.common.budget.framework.distribution.AddBudgetUnrecoveredFandARule;
import org.kuali.coeus.common.budget.framework.distribution.BudgetUnrecoveredFandA;
import org.kuali.coeus.common.budget.framework.distribution.BudgetValidationUnrecoveredFandARule;
import org.kuali.coeus.common.budget.impl.core.ValidationHelper;
import org.kuali.coeus.common.budget.framework.core.BudgetDocumentContainer;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.List;

/**
 * ProcessDefinitionDefinitiones Budget Project Income rules
 */
public class BudgetUnrecoveredFandARuleImpl implements AddBudgetUnrecoveredFandARule, BudgetValidationUnrecoveredFandARule {

private static final String ADD_ERROR_KEY = "error.custom";
    
    /**
     * Provides general validation support
     */
    private ValidationHelper validationHelper;
    

    public BudgetUnrecoveredFandARuleImpl() {
        validationHelper = new ValidationHelper();
    }
    
    @Override
    public boolean processAddBudgetUnrecoveredFandABusinessRules(AddBudgetUnrecoveredFandAEvent budgetUnrecoveredFandAEvent) {
        return !areDuplicatesPresent(budgetUnrecoveredFandAEvent.getBudgetUnrecoveredFandA());
    }

    @Override
    public boolean processBudgetValidationUnrecoveredFandABusinessRules(BudgetValidationUnrecoveredFandAEvent budgetUnrecoveredFandAEvent) {
        return areRequiredRulesSatisfied(budgetUnrecoveredFandAEvent.getBudgetUnrecoveredFandA());
    }

    /**
     * This method ensures that an added BudgetCostShare won't duplicate another. A duplicate record would have the same source account, share amount, 
     * and fiscal year as another already in the list. 
     * @param testBudgetUnrecoveredFandA
     * @return
     */
    private boolean areDuplicatesPresent(BudgetUnrecoveredFandA testBudgetUnrecoveredFandA) {
        boolean duplicate = false;
        
        if(testBudgetUnrecoveredFandA == null) {
            return duplicate;
        }
        
        KualiForm form = KNSGlobalVariables.getKualiForm();
        if(form instanceof BudgetDocumentContainer) {
            BudgetDocumentContainer budgetContainerForm = (BudgetDocumentContainer) form;
            List<BudgetUnrecoveredFandA> budgetUnrecoveredFandAs = budgetContainerForm.getBudgetDocument().getBudget().getBudgetUnrecoveredFandAs();
            for(BudgetUnrecoveredFandA budgetUnrecoveredFandA: budgetUnrecoveredFandAs) {
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
     * @param testBudgetUnrecoveredFandA
     * @param budgetUnrecoveredFandA
     * @return
     */
    private boolean checkForDuplicateFields(BudgetUnrecoveredFandA testBudgetUnrecoveredFandA, BudgetUnrecoveredFandA budgetUnrecoveredFandA) {
        boolean duplicate = testBudgetUnrecoveredFandA.equals(budgetUnrecoveredFandA);
        if(duplicate) {
            GlobalVariables.getMessageMap().putError("newUnrecoveredFandA.*", ADD_ERROR_KEY, "A duplicate Unrecovered F&A exists in the table");
        }
        return duplicate;
    }
}
