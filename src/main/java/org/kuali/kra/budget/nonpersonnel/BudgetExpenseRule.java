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
package org.kuali.kra.budget.nonpersonnel;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.budget.calculator.QueryList;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

public class BudgetExpenseRule {
    private static final String PERSONNEL_CATEGORY = "P";

    public BudgetExpenseRule() {
    }

    public boolean processCheckExistBudgetPersonnelDetailsBusinessRules(BudgetDocument budgetDocument,
            BudgetLineItem budgetLineItem, int lineItemToDelete) {
        boolean valid = true;

        MessageMap errorMap = GlobalVariables.getMessageMap();
        if (CollectionUtils.isNotEmpty(budgetLineItem.getBudgetPersonnelDetailsList())) {
            // just try to make sure key is on budget personnel tab
            errorMap.putError("document.budgetPeriod[" + (budgetLineItem.getBudgetPeriod() - 1) + "].budgetLineItem["
                    + lineItemToDelete + "].costElement", KeyConstants.ERROR_DELETE_LINE_ITEM);
            valid = false;
        }

        return valid;
    }

    public boolean processApplyToLaterPeriodsWithPersonnelDetails(BudgetDocument budgetDocument, BudgetPeriod currentBudgetPeriod,
            BudgetLineItem currentBudgetLineItem, int selectedLineItem) {

        MessageMap errorMap = GlobalVariables.getMessageMap();


        List<BudgetPeriod> budgetPeriods = budgetDocument.getBudget().getBudgetPeriods();
        BudgetLineItem prevBudgetLineItem = currentBudgetLineItem;
        for (BudgetPeriod budgetPeriod : budgetPeriods) {
            if (budgetPeriod.getBudgetPeriod() <= currentBudgetPeriod.getBudgetPeriod())
                continue;
            QueryList<BudgetLineItem> currentBudgetPeriodLineItems = new QueryList<BudgetLineItem>(budgetPeriod
                    .getBudgetLineItems());
            for (BudgetLineItem budgetLineItemToBeApplied : currentBudgetPeriodLineItems) {
                if (prevBudgetLineItem.getLineItemNumber().equals(budgetLineItemToBeApplied.getBasedOnLineItem())) {
                    if (budgetLineItemToBeApplied.getBudgetCategory().getBudgetCategoryTypeCode().equals(PERSONNEL_CATEGORY)
                            && (!budgetLineItemToBeApplied.getBudgetPersonnelDetailsList().isEmpty() || !prevBudgetLineItem
                                    .getBudgetPersonnelDetailsList().isEmpty())) {
                        errorMap.putError("document.budgetPeriod[" + (currentBudgetLineItem.getBudgetPeriod() - 1)
                                + "].budgetLineItem[" + selectedLineItem + "].costElement",
                                KeyConstants.ERROR_APPLY_TO_LATER_PERIODS, budgetLineItemToBeApplied.getBudgetPeriod().toString());
                        return false;
                    }
                }
                else if (StringUtils.equals(currentBudgetLineItem.getBudgetCategory().getBudgetCategoryTypeCode(),
                        PERSONNEL_CATEGORY)) {
                    // Additional Check for Personnel Source Line Item
                    if (StringUtils.equals(currentBudgetLineItem.getCostElement(), budgetLineItemToBeApplied.getCostElement())
                            && StringUtils.equals(currentBudgetLineItem.getGroupName(), budgetLineItemToBeApplied.getGroupName())) {
                        errorMap.putError("document.budgetPeriod[" + (currentBudgetLineItem.getBudgetPeriod() - 1)
                                + "].budgetLineItem[" + selectedLineItem + "].costElement",
                                KeyConstants.ERROR_PERSONNELLINEITEM_APPLY_TO_LATER_PERIODS, budgetLineItemToBeApplied
                                        .getBudgetPeriod().toString());
                        return false;
                    }

                }

            }
        }


        return true;
    }

    /**
     * This method is checks line item start and end dates for validity and proper ordering in the entire budget document
     * 
     * @param budgetDocument the BudgetDocument
     * @return true if the dates are valid, false otherwise
     */
    public boolean processCheckLineItemDates(BudgetDocument budgetDocument) {
        boolean valid = true;
        // TODO - put budget expense validation rules here.
        List<BudgetPeriod> budgetPeriods = budgetDocument.getBudget().getBudgetPeriods();
        int numLineItems = 0;
        for (BudgetPeriod budgetPeriod : budgetPeriods) {
            numLineItems = budgetPeriod.getBudgetLineItems().size();
            for (int i = 0; i < numLineItems; i++) {
                valid &= processCheckLineItemDates(budgetPeriod, i);
            }
        }
        return valid;
    }


    /**
     * This method is checks individual line item start and end dates for validity and proper ordering.
     * 
     * @param currentBudgetPeriod the period containing the line item
     * @param selectedLineItem the number of the line item
     * @return true if the dates are valid, false otherwise
     */
    public boolean processCheckLineItemDates(BudgetPeriod currentBudgetPeriod, int selectedLineItem) {
        boolean valid = true;
        MessageMap errorMap = GlobalVariables.getMessageMap();
        BudgetLineItem budgetLineItem = currentBudgetPeriod.getBudgetLineItems().get(selectedLineItem);

        if (budgetLineItem.getEndDate() == null) {
            errorMap.putError("document.budgetPeriod[" + (currentBudgetPeriod.getBudgetPeriod() - 1) + "].budgetLineItem["
                    + selectedLineItem + "].endDate", KeyConstants.ERROR_REQUIRED, "End Date");
            valid = false;
        }

        if (budgetLineItem.getStartDate() == null) {
            errorMap.putError("document.budgetPeriod[" + (currentBudgetPeriod.getBudgetPeriod() - 1) + "].budgetLineItem["
                    + selectedLineItem + "].startDate", KeyConstants.ERROR_REQUIRED, "Start Date");
            valid = false;
        }

        if (!valid)
            return valid;

        if (budgetLineItem.getEndDate().compareTo(budgetLineItem.getStartDate()) < 0) {
            errorMap.putError("document.budgetPeriod[" + (currentBudgetPeriod.getBudgetPeriod() - 1) + "].budgetLineItem["
                    + selectedLineItem + "].endDate", KeyConstants.ERROR_LINE_ITEM_DATES);
            valid = false;
        }

        if (currentBudgetPeriod.getEndDate().compareTo(budgetLineItem.getEndDate()) < 0) {
            errorMap.putError("document.budgetPeriod[" + (currentBudgetPeriod.getBudgetPeriod() - 1) + "].budgetLineItem["
                    + selectedLineItem + "].endDate", KeyConstants.ERROR_LINE_ITEM_END_DATE, new String[] { "can not be after",
                    "end date" });
            valid = false;
        }
        if (currentBudgetPeriod.getStartDate().compareTo(budgetLineItem.getEndDate()) > 0) {
            errorMap.putError("document.budgetPeriod[" + (currentBudgetPeriod.getBudgetPeriod() - 1) + "].budgetLineItem["
                    + selectedLineItem + "].endDate", KeyConstants.ERROR_LINE_ITEM_END_DATE, new String[] { "can not be before",
                    "start date" });
            valid = false;
        }
        if (currentBudgetPeriod.getStartDate().compareTo(budgetLineItem.getStartDate()) > 0) {
            errorMap.putError("document.budgetPeriod[" + (currentBudgetPeriod.getBudgetPeriod() - 1) + "].budgetLineItem["
                    + selectedLineItem + "].startDate", KeyConstants.ERROR_LINE_ITEM_START_DATE, new String[] {
                    "can not be before", "start date" });
            valid = false;
        }
        if (currentBudgetPeriod.getEndDate().compareTo(budgetLineItem.getStartDate()) < 0) {
            errorMap.putError("document.budgetPeriod[" + (currentBudgetPeriod.getBudgetPeriod() - 1) + "].budgetLineItem["
                    + selectedLineItem + "].startDate", KeyConstants.ERROR_LINE_ITEM_START_DATE, new String[] { "can not be after",
                    "end date" });
            valid = false;
        }

        return valid;
    }
}
