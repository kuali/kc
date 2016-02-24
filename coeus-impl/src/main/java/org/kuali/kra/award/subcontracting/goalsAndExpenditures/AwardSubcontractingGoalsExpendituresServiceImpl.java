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
package org.kuali.kra.award.subcontracting.goalsAndExpenditures;

import org.kuali.kra.award.subcontracting.reporting.SubcontractingExpenditureCategoryAmounts;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.Map;

public class AwardSubcontractingGoalsExpendituresServiceImpl implements AwardSubcontractingGoalsExpendituresService {

    private static final String AWARD_NUMBER = "awardNumber";
    private BusinessObjectService businessObjectService;

    @Override
    public AwardSubcontractingBudgetedGoals getBudgetedGoalsBOForAward(String awardNumber) {
        AwardSubcontractingBudgetedGoals retVal;            
        // check if the goals-expense BO for this award number was previously stored, or else create a fresh one.
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(AWARD_NUMBER, awardNumber);
        retVal = getBusinessObjectService().findByPrimaryKey(AwardSubcontractingBudgetedGoals.class, fieldValues);            
        if (retVal == null) {
            retVal = new AwardSubcontractingBudgetedGoals(awardNumber);
        }
        return retVal;
    }

    @Override
    public void saveBudgetedGoalsBO(AwardSubcontractingBudgetedGoals budgetedGoalsBO) {
        // use the boService to save the BO
        getBusinessObjectService().save(budgetedGoalsBO);
        // reset the fresh flag on the BO, since its now in the data store
        budgetedGoalsBO.setFresh(false); 
    }
    
    @Override
    public SubcontractingExpenditureCategoryAmounts getExpenditureAmountsBOForAward(String awardNumber) {
        SubcontractingExpenditureCategoryAmounts retVal;            
        // check if the expense BO for this award number was previously stored, or else create a fresh one.
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(AWARD_NUMBER, awardNumber);
        retVal = getBusinessObjectService().findByPrimaryKey(SubcontractingExpenditureCategoryAmounts.class, fieldValues);            
        if (retVal == null) {
            retVal = new SubcontractingExpenditureCategoryAmounts(awardNumber);
        }
        return retVal;
    }

    // dependency injection
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public BusinessObjectService getBusinessObjectService() {
        if (this.businessObjectService == null) {
            this.businessObjectService = KNSServiceLocator.getBusinessObjectService();
        }
        return this.businessObjectService;
    }

}
