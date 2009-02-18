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
package org.kuali.kra.scheduling.expr;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.kuali.kra.scheduling.Time;

public class YearMonthDayOfWeekCronExpression extends CronExpression {
    
    private CronSpecialChars weekOfMonth;
    
    private CronSpecialChars dayOfWeek;
    
    private CronSpecialChars month;
    
    private Integer frequencyInYear;
    
    public YearMonthDayOfWeekCronExpression(Date startDate, Time time, CronSpecialChars weekOfMonth, CronSpecialChars dayOfWeek, CronSpecialChars month, Integer frequencyInYear) throws ParseException {
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
        if(!(weekOfMonth == CronSpecialChars.FIFTH))
            exp.append(dayOfWeek).append(CronSpecialChars.HASH).append(weekOfMonth).append(CronSpecialChars.SPACE);
        else
            exp.append(dayOfWeek).append(CronSpecialChars.LAST).append(CronSpecialChars.SPACE);        
        exp.append(stDt_year).append(CronSpecialChars.SLASH).append(frequencyInYear);
        return exp.toString();
    }

}
