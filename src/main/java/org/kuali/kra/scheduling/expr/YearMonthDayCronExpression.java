/*
 * Copyright 2006-2009 The Kuali Foundation
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
 * This class extends CronExpression and provides YearMonthDayCronExpression implementation.
 * <p>
 * This implementation generates expression for yearly scheduling dates using month, day of month, and year.
 * 
 * month: CronSpecialChars.FEB, CronSpecialChars.MAR, CronSpecialChars.APR, CronSpecialChars.MAY, CronSpecialChars.JUN,
 * CronSpecialChars.JUL, CronSpecialChars.AUG, CronSpecialChars.SEP, CronSpecialChars.OCT, CronSpecialChars.NOV,
 * CronSpecialChars.DEC
 * 
 * day of month: 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31
 * 
 * year: 2009, 2010, etc...
 * 
 * Note: Start date is skipped, date boundary is handled by ScheduleSequence implementation  during schedule generation.
 * 
 * e.g. Start Date : 02/24/09, Time : 10:10 (hh:mm) Format (second minute hour day month year) Generated Expression :0 10 10 6 JAN ? 2009/1
 * Explanation: Generate dates for 6th January of every year at 10:10 (hh:mm), starting from 2009.  
 * 
 * e.g. Start Date : 02/24/09, Time : 10:10 (hh:mm) Format (second minute hour day month year) Generated Expression :0 10 10 6 JAN ? 2009/2
 * Explanation: Generate dates for 6th January of every other year at 10:10 (hh:mm), starting from 2009 
 */
public class YearMonthDayCronExpression extends CronExpression {
    
    private CronSpecialChars month;
    
    private Integer day;
    
    private Integer frequencyInYear;
    
    public YearMonthDayCronExpression(Date startDate, Time24HrFmt time, CronSpecialChars month, Integer day, Integer frequencyInYear) throws ParseException {
        super(startDate, time);
        this.month = month;
        this.day = day;
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
        exp.append(day).append(CronSpecialChars.SPACE);
        exp.append(month).append(CronSpecialChars.SPACE);
        exp.append(CronSpecialChars.QUESTION).append(CronSpecialChars.SPACE);
        exp.append(stDt_year).append(CronSpecialChars.SLASH).append(frequencyInYear);
        return exp.toString();
    }

}
