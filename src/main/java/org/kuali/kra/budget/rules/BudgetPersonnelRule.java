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
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.RiceKeyConstants;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetPerson;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;

public class BudgetPersonnelRule {

    public BudgetPersonnelRule() {
    }
    
    /**
     * 
     * This method is to give an error if the person drop down list is not complete because the person entries
     * is not complete.  ALso, let user know to go to person page to fix it.
     * This is more like a warning message.
     * @param budgetDocument
     * @return
     */
    
    public boolean processCheckCompleteEntriesBusinessRules(BudgetDocument budgetDocument) {
        boolean valid = true;
        
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        for (BudgetPerson budgetPerson : budgetDocument.getBudgetPersons()) {
            if (StringUtils.isBlank(budgetPerson.getJobCode()) || StringUtils.isBlank(budgetPerson.getAppointmentTypeCode()) || budgetPerson.getCalculationBase().isLessThan(BudgetDecimal.ZERO) || budgetPerson.getEffectiveDate() == null) {
                errorMap.putError("newBudgetPersonnelDetails.personSequenceNumber", KeyConstants.ERROR_IMCOMPLETE_PERSON_ENTRIES);
                    valid = false;
            }
        }
                    
        return valid;
    }

    /**
     * 
     * This method to check the 'selected' person to delete is not associate with budget personnel details
     * @param budgetDocument
     * @param budgetPerson
     * @return
     */
    public boolean processCheckExistBudgetPersonnelDetailsBusinessRules(BudgetDocument budgetDocument, BudgetPerson budgetPerson) {
        boolean valid = true;
        
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        Map qMap = new HashMap();
        qMap.put("proposalNumber", budgetDocument.getProposalNumber());
        qMap.put("budgetVersionNumber", budgetDocument.getBudgetVersionNumber());
        qMap.put("personId", budgetPerson.getPersonId());
        qMap.put("personSequenceNumber", budgetPerson.getPersonSequenceNumber());
        Collection budgetPersonnelDetails = KraServiceLocator.getService(BusinessObjectService.class).findMatching(BudgetPersonnelDetails.class, qMap);
        if (CollectionUtils.isNotEmpty(budgetPersonnelDetails)) {
                // just try to make sure key is on budget personnel tab
                errorMap.putError("document.budgetPersons[0].personNumber", KeyConstants.ERROR_DELETE_PERSON_WITH_PERSONNEL_DETAIL, budgetPerson.getPersonName());
                    valid = false;
        }
                    
        return valid;
    }

    public boolean processCheckBaseSalaryFormat(BudgetDocument budgetDocument) {
        boolean valid = true;
        
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        int i = 0;
        for (BudgetPerson budgetPerson : budgetDocument.getBudgetPersons()) {
            if (budgetPerson.getCalculationBase() == null || budgetPerson.getCalculationBase().isNegative()) {
                errorMap.putError("document.budgetPerson["+(i++)+"].calculationBase", RiceKeyConstants.ERROR_INVALID_FORMAT, new String[] { "Base Salary", budgetPerson.getCalculationBase()!=null ? budgetPerson.getCalculationBase().toString() : null });
                    valid = false;
            }
        }
                    
        return valid;
    }

}
