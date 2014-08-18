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
package org.kuali.coeus.common.budget.framework.personnel;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BudgetPersonnelExpenseRule {

    public BudgetPersonnelExpenseRule() {
    }

    public boolean processCheckSummaryAddBusinessRules(BudgetLineItem budgetLineItem) {
        boolean valid = true;
        MessageMap errorMap = GlobalVariables.getMessageMap();
        
        //Check for Req.8: Condition where Personnel are already added for the line item
        if(budgetLineItem.getBudgetPersonnelDetailsList().size() > 0 ) {
            errorMap.putError("newBudgetPersonnelDetails.personSequenceNumber", KeyConstants.ERROR_PERSONNEL_EXISTS);
            valid = false;
        }
        //Check for Req.9: Summary is already added and user is attempting to add a second summary
        if(budgetLineItem.getBudgetPersonnelDetailsList().size() == 0) {
            errorMap.putError("newBudgetPersonnelDetails.personSequenceNumber", KeyConstants.ERROR_SUMMARY_LINEITEM_EXISTS);
            valid = false;
        }
        
        return valid;
    }
    
    public boolean processCheckPersonAddBusinessRules(BudgetLineItem budgetLineItem) {
        boolean valid = true;
        MessageMap errorMap = GlobalVariables.getMessageMap();
        
        //Check for Req.9: Summary is already added and user is attempting to add a second summary
        if(budgetLineItem.getBudgetPersonnelDetailsList().size() == 0) {
            errorMap.putError("newBudgetPersonnelDetails.personSequenceNumber", KeyConstants.ERROR_SUMMARY_LINEITEM_EXISTS_ADD_PERSON);
            valid = false;
        }
        
        return valid;
    }
    
    public boolean processCheckDuplicateBudgetPersonnel(BudgetDocument budgetDocument, int budgetPeriodIndex, int budgetLineItemIndex) {
        boolean valid=true;
        MessageMap errorMap = GlobalVariables.getMessageMap();
        
        int k=0;
        int l=0;
        Map<String, String> errorCombinations = new HashMap<String, String>();
        
        BudgetPeriod selectedBudgetPeriod = budgetDocument.getBudget().getBudgetPeriod(budgetPeriodIndex);
        BudgetLineItem selectedBudgetLineItem = selectedBudgetPeriod.getBudgetLineItem(budgetLineItemIndex);

        for(BudgetPersonnelDetails personnelDetails : selectedBudgetLineItem.getBudgetPersonnelDetailsList()) {
            l=0;
            for(BudgetPersonnelDetails personnelDetailsForcomparison : selectedBudgetLineItem.getBudgetPersonnelDetailsList()) {
                if(k != l && personnelDetails.getPersonSequenceNumber().intValue() == personnelDetailsForcomparison.getPersonSequenceNumber().intValue() && 
                      StringUtils.equals(personnelDetails.getJobCode(), personnelDetailsForcomparison.getJobCode()) && 
                      personnelDetails.getStartDate().equals(personnelDetailsForcomparison.getStartDate())) {
                      if(errorCombinations.get(k + "" + l) == null) {
                          errorMap.putError("document.budgetPeriod["+budgetPeriodIndex+"].budgetLineItem["+budgetPeriodIndex+"].budgetPersonnelDetailsList["+l+"].startDate", KeyConstants.ERROR_DUPLICATE_PERSON, personnelDetailsForcomparison.getBudgetPerson().getPersonName());
                      }
                      errorCombinations.put(k + "" + l, l + "" + k);
                      errorCombinations.put(l + "" + k, k + "" + l);
                      valid = false;
                }
                
                l++;
            }
            //increment the PersonnelDetails counter
            k++;
        }
        
        return valid;
    }
    
    public boolean processSaveCheckDuplicateBudgetPersonnel(BudgetDocument budgetDocument) {
        boolean valid = true;
        List<BudgetPeriod> budgetPeriods = budgetDocument.getBudget().getBudgetPeriods();
        List<BudgetLineItem> budgetLineItems;
        int i=0;
        int j=0;
        
        for(BudgetPeriod budgetPeriod: budgetPeriods){
            j=0;
            budgetLineItems = budgetPeriod.getBudgetLineItems();
            for(BudgetLineItem budgetLineItem: budgetLineItems){
                if (budgetLineItem.getBudgetCategory().getBudgetCategoryTypeCode().equals("P")) {
                    valid &= processCheckDuplicateBudgetPersonnel(budgetDocument, i, j);
                }
                j++;
            }
            i++;
        }
        
        return valid;
  }
}
