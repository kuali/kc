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

public interface AwardSubcontractingGoalsExpendituresService {
    
    // return the existing BO instance for the award number if found in the data store, or else return a 'fresh' instance of the BO
    public AwardSubcontractingBudgetedGoals getBudgetedGoalsBOForAward(String awardNumber);
   
    public void saveBudgetedGoalsBO(AwardSubcontractingBudgetedGoals goalsExpendituresBO);

    
    // return the existing BO instance for the award number if found in the data store, or else return a 'fresh' instance of the BO
    public SubcontractingExpenditureCategoryAmounts getExpenditureAmountsBOForAward(String awardNumber);
}
