/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.budget.summary;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;

public interface BudgetSummaryService {

    /**
     * This method is used to generate all budget periods initially based on project/proposal start date
     * and end date.
     * @param budgetPeriods
     * @param projectStartDate
     * @param projectEndDate
     */
//    public void generateBudgetPeriods(List<BudgetPeriod> budgetPeriods, Date projectStartDate, Date projectEndDate);
    public void generateBudgetPeriods(Budget budget,List<BudgetPeriod> budgetPeriods);
    public void addBudgetPeriod(Budget budget, BudgetPeriod newBudgetPeriod);
    public void deleteBudgetPeriod(Budget budget, int delPeriod);
    public boolean budgetLineItemExists(Budget budget, Integer budgetPeriod);
    public void generateAllPeriods(Budget budget);
    public Collection<BudgetLineItem> getBudgetLineItemForPeriod(Budget budget, int budgetPeriodNumber);
    public Collection<BudgetPersonnelDetails> getBudgetPersonnelDetailsForPeriod(Budget budget, int budgetPeriodNumber);
    public void calculateBudget(Budget budget);

    /**
     * 
     * This method to get on/off campus flag description.
     * @param onOffCampusFlag
     */
    public String getOnOffCampusFlagDescription(String onOffCampusFlag);
    
    /**
     * 
     * This method to update the on/off campus flag for line item detail if on/off campus flag is changed in budget level.
     * @param budget
     * @param onOffCampusFlag
     */
    public void updateOnOffCampusFlag(Budget budget, String onOffCampusFlag);
    
    /**
     * 
     * This method to adjust the start/end dates of budget line items if budget period's start/end
     * date was adjusted
     * @param budget
     */
    public void adjustStartEndDatesForLineItems(Budget budget);
    
    /**
     * 
     * This method hold the old start/end date, so it can be used for comparison upon save.
     * 
     * @param budget
     */
    public void setupOldStartEndDate (Budget budget, boolean resetAll);

    /**
     * 
     * This method is to hold old start/end date for line items, then they can be used for date adjustment.
     * @param budgetLineItems
     */
    public void setupOldStartEndDate (List <BudgetLineItem > budgetLineItems);

    public List<Date> getNewStartEndDates(List<Date> startEndDates, int gap, int duration, Date prevDate, boolean leapDayInPeriod, boolean leapDayInGap);

    public boolean isLeapDaysInPeriod(Date sDate, Date eDate);
    
    /** 
     * 
     * This method is to generate default periods when default button is clicked.
     * Generally does this when project period is adjusted
     * @param budget
     */
    public void defaultBudgetPeriods(Budget budget);

    public String defaultWarningMessage(Budget budget);

}
