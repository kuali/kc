/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.award.budget;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.home.Award;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.versions.AddBudgetVersionEvent;
import org.kuali.kra.budget.versions.BudgetVersionRule;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;

public class AwardBudgetVersionRule extends BudgetVersionRule {

    BusinessObjectService businessObjectService;
    
    @Override
    public boolean processAddBudgetVersion(AddBudgetVersionEvent event) {
        boolean success =  super.processAddBudgetVersion(event);
        Budget budget = event.getBudget();
        Award award = (Award) budget.getBudgetParent();
        if(!award.getObligatedTotal().isPositive()){
            GlobalVariables.getErrorMap().putError(event.getErrorPathPrefix(), 
                  KeyConstants.ERROR_BUDGET_OBLIGATED_AMOUNT_INVALID, "Name");
            success &= false;
        }
        
        Map<String, Long> fieldValues = new HashMap<String, Long>();
        fieldValues.put("awardId", award.getAwardId());
        List<Award> awards = (List<Award>)getBusinessObjectService().findMatchingOrderBy(Award.class, fieldValues, "awardId", true);
        boolean anyAwardVersionFinal = false;
        for(Award testAward : awards) {
            if (testAward.getAwardDocument().getDocumentHeader().hasWorkflowDocument()) {
                if (testAward.getAwardDocument().getDocumentHeader().getWorkflowDocument().stateIsFinal()) {
                    anyAwardVersionFinal = true;
                    break;
                }
            }
        }
        if(!anyAwardVersionFinal) {
            GlobalVariables.getErrorMap().putError(event.getErrorPathPrefix(), KeyConstants.ERROR_AWARD_NOT_FINAL);
            success &= false;
        }
        return success;
    }
    
    /**
     * Gets the businessObjectService attribute. 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}
