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

public class MonthDayCronExpression extends CronExpression {
    
    private Integer day;
    
    private Integer frequencyInMonth;
    
    public MonthDayCronExpression(Date startDate, Time time, Integer day, Integer frequencyInMonth) throws ParseException {
        super(startDate, time);
        this.day = day;
        this.frequencyInMonth = frequencyInMonth;
    }

    @Override
    public String getExpression() {
        
        Calendar stDt = new GregorianCalendar();
        stDt.setTime(getStartDate());
        
        StringBuilder exp = new StringBuilder();
        exp.append(SECONDS).append(CronSpecialChars.SPACE);
        exp.append(getTime().getMinutes()).append(CronSpecialChars.SPACE);
        exp.append(getTime().getHours()).append(CronSpecialChars.SPACE);
        exp.append(day).append(CronSpecialChars.SPACE);
        exp.append(stDt.get(Calendar.MONTH)+1).append(CronSpecialChars.SLASH).append(frequencyInMonth).append(CronSpecialChars.SPACE);
        exp.append(CronSpecialChars.QUESTION);
        return exp.toString();
    }

}
