/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.budget;


import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Component("proposalBudgetNumberOfMonthsService")
public class ProposalBudgetNumberOfMonthsServiceImpl implements ProposalBudgetNumberOfMonthsService {
    
    protected static final double[] THIRTY_ONE_DAY_COUNT = {0.03, 0.06, 0.10, 0.13, 0.16, 0.19, 0.23, 0.26, 0.29, 0.32, 0.35, 0.39, 0.42, 
        0.45, 0.48, 0.52, 0.55, 0.58, 0.61, 0.65, 0.68, 0.71, 0.74, 0.77, 0.81, 0.84, 0.87, 0.90, 0.94, 0.97, 1.00};
    
    protected static final double[] THIRTY_DAY_COUNT = {0.03, 0.07, 0.10, 0.13, 0.17, 0.20, 0.23, 0.27, 0.30, 0.33, 0.37, 0.40, 0.43, 0.47, 
        0.50, 0.53, 0.57, 0.60, 0.63, 0.67, 0.70, 0.73, 0.77, 0.80, 0.83, 0.87, 0.90, 0.93, 0.97, 1.00};
    
    protected static final double[] TWENTY_NINE_DAY_COUNT = {0.03, 0.07, 0.10, 0.14, 0.17, 0.21, 0.24, 0.28, 0.31, 0.34, 0.38, 0.41, 0.45, 0.48, 
        0.52, 0.55, 0.59, 0.62, 0.66, 0.69, 0.72, 0.76, 0.79, 0.83, 0.86, 0.90, 0.93, 0.97, 1.00};
    
    protected static final double[] TWENTY_EIGHT_DAY_COUNT = {0.04, 0.07, 0.11, 0.14, 0.18, 0.21, 0.25, 0.29, 0.32, 0.36, 0.39, 0.43, 0.46, 0.50, 
        0.54, 0.57, 0.61, 0.64, 0.68, 0.71, 0.75, 0.79, 0.82, 0.86, 0.89, 0.93, 0.96, 1.00};

    @Override
    public double getNumberOfMonth(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return 0.00;
        } else {
            return countMonthsStepOne(startDate, endDate);
        }
    }
    
    protected double countMonthsStepOne(Date startDate, Date endDate) {
       double numberOfMonths = 0 ;
       Date startDateAdded = rollMonths(startDate, 1);
       startDateAdded = rollDays(startDateAdded, -1);
       while (startDateAdded.before(endDate) || startDateAdded.equals(endDate)) {
           startDateAdded = rollDays(startDateAdded, 1);
           numberOfMonths++;
           startDateAdded = rollMonths(startDateAdded, 1);
           startDateAdded = rollDays(startDateAdded, -1);
       }
       
       //right now startDateAdded is rolled one month minus one day past the last full month counted, so reverse that, then pass that as the start date to step two
       startDateAdded = rollMonths(startDateAdded, -1);
       startDateAdded = rollDays(startDateAdded, 1);
       double numberOfPartialMonths = countMonthsStepTwo(startDateAdded, endDate);
       
       return numberOfMonths + numberOfPartialMonths;
    }    
    
    protected double countMonthsStepTwo(Date startDate, Date endDate) {
        double totalPartialMonthCount = 0;
        int yearEnd = endDate.getYear();
        boolean isLeapYear = ((yearEnd % 4 == 0) && (yearEnd % 100 != 0) || (yearEnd % 400 == 0));
        
        Date startDateAdded = rollDays(startDate, 1);
        int previousMonth = startDateAdded.getMonth();
        int previousMonthDayCount = 0;
        while (startDateAdded.before(endDate)) {
            startDateAdded = rollDays(startDateAdded, 1);
            int currentMonth = startDateAdded.getMonth();
            if (previousMonth != currentMonth) {
                double[] dailyPercentageArray = getMonthDailyPercentageArray(previousMonth, isLeapYear);
                totalPartialMonthCount = totalPartialMonthCount + dailyPercentageArray[previousMonthDayCount];
                previousMonthDayCount = 0;
            }
            previousMonthDayCount++;
        }
        if (previousMonthDayCount > 0) {
            double[] dailyPercentageArray = getMonthDailyPercentageArray(previousMonth, isLeapYear);
            totalPartialMonthCount = totalPartialMonthCount + dailyPercentageArray[previousMonthDayCount];
        }
        
        return totalPartialMonthCount;
    }
    
    protected final double[] getMonthDailyPercentageArray(int month, boolean isLeapYear) {
        switch(month) {
            case Calendar.JANUARY:
            case Calendar.MARCH:
            case Calendar.MAY:
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.OCTOBER:
            case Calendar.DECEMBER:
                return THIRTY_ONE_DAY_COUNT;
            case Calendar.FEBRUARY:
                if (isLeapYear) {
                    return TWENTY_NINE_DAY_COUNT;
                } else {
                    return TWENTY_EIGHT_DAY_COUNT;
                }
            case Calendar.APRIL:
            case Calendar.JUNE:
            case Calendar.SEPTEMBER:
            case Calendar.NOVEMBER:
                return THIRTY_DAY_COUNT;
        }
        throw new IllegalArgumentException("Illegal Month '" + month + "' passed in.");
    }
    
    public static java.sql.Date rollMonths(Date startDate, int months ) {
        return rollDate(startDate, Calendar.MONTH, months);
    }
    
    public static java.sql.Date rollDays(Date startDate, int days ) {
        return rollDate(startDate, Calendar.DATE, days);
    }
    
    public static java.sql.Date rollDate(Date startDate, int period, int amount ) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(startDate);
        gc.add(period, amount);
        return new java.sql.Date(gc.getTime().getTime());
    }
}
