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
package org.kuali.coeus.sys.framework.scheduling.expr;

import org.kuali.coeus.sys.framework.scheduling.util.CronSpecialChars;
import org.kuali.coeus.sys.framework.scheduling.util.Time24HrFmt;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


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
        return exp.toString();
    }

}
