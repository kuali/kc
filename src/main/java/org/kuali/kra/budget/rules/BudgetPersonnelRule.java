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

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetPerson;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.ObjectUtils;
import org.kuali.rice.kns.util.RiceKeyConstants;

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
        List <BudgetPerson> budgetPersons = (List <BudgetPerson>)ObjectUtils.deepCopy((Serializable)budgetDocument.getBudgetPersons());
        for (BudgetPerson budgetPerson : budgetDocument.getBudgetPersons()) {
            if (budgetPerson.getCalculationBase() == null) {
                errorMap.putError("document.budgetPerson["+i+"].calculationBase", RiceKeyConstants.ERROR_REQUIRED, new String[] { "Base Salary"});
                    valid = false;
            } else if (budgetPerson.getCalculationBase().isNegative()) {
                errorMap.putError("document.budgetPerson["+i+"].calculationBase", KeyConstants.ERROR_NEGATIVE_AMOUNT, new String[] { "Base Salary"});
                valid = false;
            }
            for (BudgetPerson dupBudgetPerson : budgetPersons) {
                if (dupBudgetPerson.getPersonId() != null && dupBudgetPerson.getPersonId().equals(budgetPerson.getPersonId()) && dupBudgetPerson.getPersonSequenceNumber().intValue() < budgetPerson.getPersonSequenceNumber().intValue()
                        && dupBudgetPerson.getJobCode().equals(budgetPerson.getJobCode()) && dupBudgetPerson.getEffectiveDate().compareTo(budgetPerson.getEffectiveDate()) == 0) 
                {
                    errorMap.putError("document.budgetPerson["+i+"].dupkey", KeyConstants.ERROR_DUPLICATE_PERSON, new String[] { ""});
                    valid = false;
                }
                if (dupBudgetPerson.getRolodexId() != null && dupBudgetPerson.getRolodexId().equals(budgetPerson.getRolodexId()) && dupBudgetPerson.getPersonSequenceNumber().intValue() < budgetPerson.getPersonSequenceNumber().intValue()
                        && dupBudgetPerson.getJobCode().equals(budgetPerson.getJobCode()) && dupBudgetPerson.getEffectiveDate().compareTo(budgetPerson.getEffectiveDate()) == 0) 
                {
                    errorMap.putError("document.budgetPerson["+i+"].dupkey", KeyConstants.ERROR_DUPLICATE_PERSON, new String[] { ""});
                    valid = false;
                }
            }
            i++;
        }
                    
        return valid;
    }

}
