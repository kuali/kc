/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.scheduling.expr;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.kuali.kra.scheduling.expr.util.CronSpecialChars;
import org.kuali.kra.scheduling.util.Time24HrFmt;


/**
* This class extends CronExpression and provides MonthDayOrLastDayMultipleYearsCronExpression implementation.
* <p>
* This implementation generates expression for monthly scheduling dates using day of month and month.
* 
* This is different from MonthDayCronExpression in a way that this generates dates between multiple years.
* 
* day of month: 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31 
* if 29 or greater is supplied, the last day of the month will be used.
* 
* month: 1,2,3,4,5,6,7,8,9,10,11,12
* 
* Note: Start day is skipped, date boundary is handled by ScheduleSequence implementation  during schedule generation.
*  
* e.g. Start Date : 02/24/09, Time : 10:10 (hh:mm) Format (second minute hour day month year) Generated Expression :0 10 10 1 * ?
* Explanation: Generate dates for 1st day of each month at 10:10 (hh:mm), starting from the start date to end date
*
* 
*/
public class MonthDayOrLastDayMultipleYearsCronExpression extends CronExpression {
    
    /**
     * There are at least 28 days in every month, so this is the cut over point.
     */
    protected static final Integer MAXIMUM_DAY_VALUE = 28;
    
    private Integer day;
    
    /**
     * 
     * Constructs a MonthDayOrLastDayMultipleYearsCronExpression.java.
     * @param startDate
     * @param time
     * @param day
     * @throws ParseException
     */
    public MonthDayOrLastDayMultipleYearsCronExpression(Date startDate, Time24HrFmt time, Integer day) throws ParseException {
        super(startDate, time);
        this.day = day;
    }

    @Override
    public String getExpression() {

        Calendar stDt = new GregorianCalendar();
        stDt.setTime(getStartDate());

        StringBuilder exp = new StringBuilder();
        exp.append(SECONDS).append(CronSpecialChars.SPACE);
        exp.append(getTime().getMinutes()).append(CronSpecialChars.SPACE);
        exp.append(getTime().getHours()).append(CronSpecialChars.SPACE);
        if (day > MAXIMUM_DAY_VALUE) {
            exp.append("L");
        } else {
            exp.append(day);
        }
        exp.append(CronSpecialChars.SPACE);
        exp.append(CronSpecialChars.STAR).append(CronSpecialChars.SPACE);
        exp.append(CronSpecialChars.QUESTION).append(CronSpecialChars.SPACE);     
        System.err.println("MonthDayMultipleYearsCronExpression getExpression: " + exp.toString());
        return exp.toString();
    }

}
