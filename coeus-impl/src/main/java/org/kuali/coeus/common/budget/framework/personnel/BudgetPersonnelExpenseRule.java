/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.common.budget.framework.personnel;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRule;
import org.kuali.coeus.common.framework.ruleengine.KcEventMethod;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.util.HashMap;
import java.util.Map;

@KcBusinessRule("budgetPersonnelExpenseRule")
public class BudgetPersonnelExpenseRule {

	@KcEventMethod
    public boolean processCheckSummaryAddBusinessRules(AddSummaryPersonnelLineItemBudgetEvent event) {
        boolean valid = true;
        MessageMap errorMap = GlobalVariables.getMessageMap();
        errorMap.addToErrorPath(event.getErrorPath());

        //Check for Req.8: Condition where Personnel are already added for the line item
        if(event.getBudgetLineItem().getBudgetPersonnelDetailsList().size() > 0 ) {
            errorMap.putError("personSequenceNumber", KeyConstants.ERROR_PERSONNEL_EXISTS);
            valid = false;
        }
        //Check for Req.9: Summary is already added and user is attempting to add a second summary
        if(event.getBudgetLineItem().getBudgetPersonnelDetailsList().size() == 0) {
            errorMap.putError("personSequenceNumber", KeyConstants.ERROR_SUMMARY_LINEITEM_EXISTS);
            valid = false;
        }
        errorMap.removeFromErrorPath(event.getErrorPath());
        
        return valid;
    }
    
	@KcEventMethod
    public boolean processCheckPersonAddBusinessRules(AddPersonnelLineItemBudgetEvent event) {
        boolean valid = true;
        MessageMap errorMap = GlobalVariables.getMessageMap();
        errorMap.addToErrorPath(event.getErrorPath());
        
        //Check for Req.9: Summary is already added and user is attempting to add a second summary
        if(event.getBudgetLineItem().getBudgetPersonnelDetailsList().size() == 0) {
            errorMap.putError("personSequenceNumber", KeyConstants.ERROR_SUMMARY_LINEITEM_EXISTS_ADD_PERSON);
            valid = false;
        }
        errorMap.removeFromErrorPath(event.getErrorPath());
        
        return valid;
    }
    
    @KcEventMethod
    public boolean processCheckDuplicateBudgetPersonnel(PersonnelApplyToPeriodsBudgetEvent event) {
    	return processCheckDuplicateBudgetPersonnel(event.getBudget(), event.getBudgetLineItem(), event.getErrorPath());
    }
    
    protected boolean processCheckDuplicateBudgetPersonnel(Budget budget, BudgetLineItem budgetLineItem, String errorPath) {
        boolean valid=true;
        MessageMap errorMap = GlobalVariables.getMessageMap();
        errorMap.addToErrorPath(errorPath);
        
        int k=0;
        int l=0;
        Map<String, String> errorCombinations = new HashMap<String, String>();

        for(BudgetPersonnelDetails personnelDetails : budgetLineItem.getBudgetPersonnelDetailsList()) {
            l=0;
            for(BudgetPersonnelDetails personnelDetailsForcomparison : budgetLineItem.getBudgetPersonnelDetailsList()) {
                if(k != l && personnelDetails.getPersonSequenceNumber().intValue() == personnelDetailsForcomparison.getPersonSequenceNumber().intValue() && 
                      StringUtils.equals(personnelDetails.getJobCode(), personnelDetailsForcomparison.getJobCode()) && 
                      personnelDetails.getStartDate().equals(personnelDetailsForcomparison.getStartDate())) {
                      if(errorCombinations.get(k + "" + l) == null) {
                          errorMap.putError("budgetPersonnelDetailsList["+l+"].startDate", KeyConstants.ERROR_DUPLICATE_PERSON, personnelDetailsForcomparison.getBudgetPerson().getPersonName());
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
        errorMap.removeFromErrorPath(errorPath);
        
        return valid;
    }

}
