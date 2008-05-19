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

import org.apache.commons.lang.StringUtils;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetPerson;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.KeyConstants;

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
            if (StringUtils.isBlank(budgetPerson.getJobCode()) || StringUtils.isBlank(budgetPerson.getAppointmentTypeCode()) || budgetPerson.getCalculationBase().isLessEqual(BudgetDecimal.ZERO) || budgetPerson.getEffectiveDate() == null) {
                errorMap.putError("newBudgetPersonnelDetails.personSequenceNumber", KeyConstants.ERROR_IMCOMPLETE_PERSON_ENTRIES);
                    valid = false;
            }
        }
                    
        return valid;
    }

}
