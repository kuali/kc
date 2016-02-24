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
package org.kuali.coeus.common.budget.framework.summary;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;

import java.sql.Date;
import java.util.List;

public interface BudgetSummaryService {

    public List<BudgetPeriod> generateBudgetPeriods(Budget budget);
    public void addBudgetPeriod(Budget budget, BudgetPeriod newBudgetPeriod);
    public void deleteBudgetPeriod(Budget budget, int delPeriod);
    public boolean budgetLineItemExists(Budget budget, Integer budgetPeriod);
    public void generateAllPeriods(Budget budget);
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
    
    public void adjustStartEndDatesForLineItems(BudgetPeriod budgetPeriod);
    
    /**
     * This method is to sync personnel salary details to new budget period changes.
     * @param budget
     */
    public void syncBudgetPersonSalaryDetails(Budget budget);
        
}
