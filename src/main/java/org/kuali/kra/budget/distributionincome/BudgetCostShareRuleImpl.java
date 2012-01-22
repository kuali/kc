/*
 * Copyright 2005-2010 The Kuali Foundation
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

import org.kuali.kra.budget.document.BudgetDocumentContainer;
import org.kuali.kra.costshare.CostShareRuleResearchDocumentBase;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * 
 * This class ProcessDefinitionDefinitiones Budget Project Income rules.
 */
public class BudgetCostShareRuleImpl extends CostShareRuleResearchDocumentBase implements AddBudgetCostShareRule,
        BudgetValidationCostShareRule, BudgetCostShareAllocationRule {

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
     * @see org.kuali.kra.budget.distributionincome.AddBudgetCostShareRule#processAddBudgetCostSharingRules(org.kuali.kra.budget.distributionincome.AddBudgetCostShareEvent)
     */
    public boolean processAddBudgetCostShareBusinessRules(AddBudgetCostShareEvent budgetCostShareEvent) {
        boolean retVal = !areDuplicatesPresent(budgetCostShareEvent.getBudgetCostShare());
        retVal &= validateProjectPeriod(budgetCostShareEvent);
        return retVal;
    }

    /**
     * @see org.kuali.kra.budget.distributionincome.BudgetValidationCostShareRule#processBudgetValidationCostShareBusinessRules(org.kuali.kra.budget.distributionincome.BudgetValidationCostShareEvent)
     */
    public boolean processBudgetValidationCostShareBusinessRules(BudgetValidationCostShareEvent budgetCostShareEvent) {
        boolean retVal = areRequiredRulesSatisfied(budgetCostShareEvent.getBudgetCostShare());
        return retVal;
    }

    /**
     * This method ensures that an added BudgetCostShare won't duplicate another. A duplicate record would have the same source
     * account, share amount, and fiscal year as another already in the list.
     * 
     * @param budgetCostShare
     * @return
     */
    private boolean areDuplicatesPresent(BudgetDistributionAndIncomeComponent testBudgetCostShare) {
        boolean duplicate = false;

        if (testBudgetCostShare == null) {
            return duplicate;
        }

        KualiForm form = KNSGlobalVariables.getKualiForm();
        if (form instanceof BudgetDocumentContainer) {
            BudgetDocumentContainer budgetContainerForm = (BudgetDocumentContainer) form;

            for (BudgetDistributionAndIncomeComponent budgetCostShare : budgetContainerForm.getBudgetDocument().getBudget()
                    .getBudgetCostShares()) {
                duplicate = checkForDuplicateFields(testBudgetCostShare, budgetCostShare);
                if (duplicate) {
                    break;
                }
            }
        }
        return duplicate;
    }

    /**
     * This method checks each required field, tracking validation state
     * 
     * @param budgetCostShare The Budget Cost Share
     * @return Validation state; true if all required fields are not null, and if String, not empty
     */
    private boolean areRequiredRulesSatisfied(BudgetCostShare budgetCostShare) {
        boolean valid = validationHelper.checkRequiredField(budgetCostShare.getProjectPeriod(), "budgetCostShare.fiscalYear",
                "Fiscal Year");
        valid &= validationHelper.checkRequiredField(budgetCostShare.getShareAmount(), "budgetCostShare.shareAmount", "Amount");
        valid &= validationHelper.checkRequiredField(budgetCostShare.getSourceAccount(), "budgetCostShare.sourceAccount",
                "Source Account");

        return valid;
    }

    /**
     * This method checks if two BudgetCostShare objects are duplicates, meaning the fiscalYear, shareAmount, and sourceAccount are
     * equal
     * 
     * @param testBudgetCostShare
     * @param budgetCostShare
     * @return
     */
    private boolean checkForDuplicateFields(BudgetDistributionAndIncomeComponent testBudgetCostShare,
            BudgetDistributionAndIncomeComponent budgetCostShare) {
        boolean duplicate = testBudgetCostShare.equals(budgetCostShare);
        if (duplicate) {
            GlobalVariables.getMessageMap().putError("newCostShare.*", ADD_ERROR_KEY,
                    "A Cost Share with the same Fiscal Year, Source Account and Amount exists in the table");
        }
        return duplicate;
    }

    /**
     * 
     * @see org.kuali.kra.budget.distributionincome.BudgetCostShareAllocationRule#processBudgetCostShareAllocationBusinessRules(org.kuali.kra.budget.distributionincome.BudgetCostShareAllocationEvent)
     */
    public boolean processBudgetCostShareAllocationBusinessRules(BudgetCostShareAllocationEvent budgetCostShareEvent) {
        boolean unallocatedCostSharingExists = budgetCostShareEvent.getBudgetDocument().getBudget().getUnallocatedCostSharing()
                .isNonZero();
        if (unallocatedCostSharingExists) {
            GlobalVariables.getMessageMap().putError("document.budgetCostShare*", ADD_ERROR_KEY,
                    "Cost share allocation doesn't total available cost sharing");
        }
        return unallocatedCostSharingExists;
    }

    private boolean validateProjectPeriod(AddBudgetCostShareEvent budgetCostShareEvent) {
        String projectPeriodField = "newBudgetCostShare.projectPeriod";
        int numberOfProjectPeriods = budgetCostShareEvent.getBudgetDocument().getBudget().getBudgetPeriods().size();
        return this.validateProjectPeriod(budgetCostShareEvent.getBudgetCostShare().getProjectPeriod(), projectPeriodField, numberOfProjectPeriods);
    }
}