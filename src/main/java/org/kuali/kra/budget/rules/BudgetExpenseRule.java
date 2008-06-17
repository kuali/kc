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

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.calculator.QueryList;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;

public class BudgetExpenseRule {
    private static final String PERSONNEL_CATEGORY = "P";

    public BudgetExpenseRule() {
    }

    public boolean processCheckExistBudgetPersonnelDetailsBusinessRules(BudgetDocument budgetDocument, BudgetLineItem budgetLineItem, int lineItemToDelete) {
        boolean valid = true;
        
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        if (CollectionUtils.isNotEmpty(budgetLineItem.getBudgetPersonnelDetailsList())) {
            // just try to make sure key is on budget personnel tab
            errorMap.putError("document.budgetPeriod["+(budgetLineItem.getBudgetPeriod()-1)+"].budgetLineItem["+lineItemToDelete+"].costElement", KeyConstants.ERROR_DELETE_LINE_ITEM);
                valid = false;
    }
                    
        return valid;
    }

    public boolean processApplyToLaterPeriodsWithPersonnelDetails(BudgetDocument budgetDocument, BudgetPeriod currentBudgetPeriod, BudgetLineItem currentBudgetLineItem, int selectedLineItem) {
        
        ErrorMap errorMap = GlobalVariables.getErrorMap();

        
        List<BudgetPeriod> budgetPeriods = budgetDocument.getBudgetPeriods();
        BudgetLineItem prevBudgetLineItem = currentBudgetLineItem;
        for (BudgetPeriod budgetPeriod : budgetPeriods) {
            if(budgetPeriod.getBudgetPeriod()<=currentBudgetPeriod.getBudgetPeriod()) continue;
            QueryList<BudgetLineItem> currentBudgetPeriodLineItems = new QueryList<BudgetLineItem>(budgetPeriod.getBudgetLineItems());
            for (BudgetLineItem budgetLineItemToBeApplied : currentBudgetPeriodLineItems) {
                if(prevBudgetLineItem.getLineItemNumber().equals(budgetLineItemToBeApplied.getBasedOnLineItem())) {                        
                    if (budgetLineItemToBeApplied.getBudgetCategory().getBudgetCategoryTypeCode().equals(PERSONNEL_CATEGORY)
                            && (!budgetLineItemToBeApplied.getBudgetPersonnelDetailsList().isEmpty() || !prevBudgetLineItem.getBudgetPersonnelDetailsList().isEmpty() )) {
                        errorMap.putError("document.budgetPeriod["+(currentBudgetLineItem.getBudgetPeriod()-1)+"].budgetLineItem["+selectedLineItem+"].costElement", KeyConstants.ERROR_APPLY_TO_LATER_PERIODS,budgetLineItemToBeApplied.getBudgetPeriod().toString());
                        return false;
                    }
                }
            }
        }
        
                    
        return true;
    }

}
