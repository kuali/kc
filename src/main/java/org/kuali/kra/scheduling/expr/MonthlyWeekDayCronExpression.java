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

public class MonthlyWeekDayCronExpression extends CronExpression {
    
    private Integer frequencyInMonth;
    
    private CronSpecialChars dayOfWeek;
    
    private Integer weekOfMonth;
    
    public MonthlyWeekDayCronExpression(Date startDate, String time, CronSpecialChars dayOfWeek, Integer weekOfMonth, Integer frequencyInMonth) throws ParseException {
        super(startDate, time);
        this.frequencyInMonth = frequencyInMonth;
        this.dayOfWeek = dayOfWeek;
        this.weekOfMonth = weekOfMonth;            
    }

    @Override
    public String getExpression() {
        
        Calendar stDt = new GregorianCalendar();
        stDt.setTime(getStartDate());
        
        StringBuilder exp = new StringBuilder();
        exp.append(SECONDS).append(CronSpecialChars.SPACE);
        exp.append(getMinutes()).append(CronSpecialChars.SPACE);
        exp.append(getHours()).append(CronSpecialChars.SPACE);        
        exp.append(CronSpecialChars.QUESTION).append(CronSpecialChars.SPACE);
        exp.append(stDt.get(Calendar.MONTH)+1).append(CronSpecialChars.SLASH).append(frequencyInMonth).append(CronSpecialChars.SPACE);
        //TODO value of week can only be between 1 to 5 or should we store as string
        if(!(weekOfMonth == 5))
            exp.append(dayOfWeek).append(CronSpecialChars.HASH).append(weekOfMonth);
        else
            exp.append(dayOfWeek).append(CronSpecialChars.LAST);
        return exp.toString();
    }

}
