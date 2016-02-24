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
 * This class extends CronExpression and provides YearMonthDayCronExpression implementation.
 * <p>
 * This implementation generates expression for yearly scheduling dates using week of month, day of week, month, and year.
 * 
 * week of month: CronSpecialChars.FIRST, CronSpecialChars.SECOND, CronSpecialChars.THIRD, CronSpecialChars.FOURTH,
 * CronSpecialChars.FIFTH 
 * 
 * day of week: CronSpecialChars.SUN, CronSpecialChars.MON, CronSpecialChars.TUE, CronSpecialChars.WED,
 * CronSpecialChars.THU, CronSpecialChars.FRI, CronSpecialChars.SAT 
 * 
 * month: CronSpecialChars.FEB, CronSpecialChars.MAR, CronSpecialChars.APR, CronSpecialChars.MAY, CronSpecialChars.JUN,
 * CronSpecialChars.JUL, CronSpecialChars.AUG, CronSpecialChars.SEP, CronSpecialChars.OCT, CronSpecialChars.NOV,
 * CronSpecialChars.DEC
 * 
 * year: 2009, 2010, etc...
 * 
 * Note: Start date is skipped, date boundary is handled by ScheduleSequence implementation  during schedule generation.
 * 
 * e.g. Start Date : 02/24/09, Time : 10:10 (hh:mm) Format (second minute hour day month year) Generated Expression : 0 10 10 ? JAN MONL 2009/1
 * Explanation: Generate dates for every last Monday in January of every year at 10:10 (hh:mm), starting from 2009.  
 * 
 * e.g. Start Date : 02/24/09, Time : 10:10 (hh:mm) Format (second minute hour day month year) Generated Expression : 0 10 10 ? JAN MONL 2009/2
 * Explanation: Generate dates for every last Monday in January of every other year at 10:10 (hh:mm), starting from 2009. 
 */
public class YearMonthDayOfWeekCronExpression extends CronExpression {
    
    private CronSpecialChars weekOfMonth;
    
    private CronSpecialChars dayOfWeek;
    
    private CronSpecialChars month;
    
    private Integer frequencyInYear;
    
    public YearMonthDayOfWeekCronExpression(Date startDate, Time24HrFmt time, CronSpecialChars weekOfMonth, CronSpecialChars dayOfWeek, CronSpecialChars month, Integer frequencyInYear) throws ParseException {
        super(startDate, time);
        this.weekOfMonth = weekOfMonth;
        this.dayOfWeek = dayOfWeek;
        this.month = month;
        this.frequencyInYear = frequencyInYear;
    }

    @Override
    public String getExpression() {
    
        Calendar stDt = new GregorianCalendar();
        stDt.setTime(getStartDate());
        int stDt_year = stDt.get(Calendar.YEAR);
        
        StringBuilder exp = new StringBuilder();
        exp.append(SECONDS).append(CronSpecialChars.SPACE);
        exp.append(getTime().getMinutes()).append(CronSpecialChars.SPACE);
        exp.append(getTime().getHours()).append(CronSpecialChars.SPACE);        
        exp.append(CronSpecialChars.QUESTION).append(CronSpecialChars.SPACE);
        exp.append(month).append(CronSpecialChars.SPACE);
        if(!(weekOfMonth == CronSpecialChars.LAST))
            exp.append(dayOfWeek).append(CronSpecialChars.HASH).append(weekOfMonth).append(CronSpecialChars.SPACE);
        else
            exp.append(dayOfWeek).append(CronSpecialChars.LAST).append(CronSpecialChars.SPACE);        
        exp.append(stDt_year).append(CronSpecialChars.SLASH).append(frequencyInYear);
        return exp.toString();
    }

}
