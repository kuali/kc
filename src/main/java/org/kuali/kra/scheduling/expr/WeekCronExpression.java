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
import java.util.Date;

import org.kuali.kra.scheduling.Time;

public class WeekCronExpression extends CronExpression {
    
    private CronSpecialChars [] weekdays;
    
    public WeekCronExpression(Date startDate, Time time, CronSpecialChars [] weekdays) throws ParseException {
        super(startDate, time);
        this.weekdays = weekdays;
    }

    @Override
    public String getExpression() {

        StringBuilder exp = new StringBuilder();
        exp.append(SECONDS).append(CronSpecialChars.SPACE);
        exp.append(getTime().getMinutes()).append(CronSpecialChars.SPACE);
        exp.append(getTime().getHours()).append(CronSpecialChars.SPACE);        
        exp.append("?").append(CronSpecialChars.SPACE);
        exp.append("*").append(CronSpecialChars.SPACE);
        exp.append(toStringWeekDays(weekdays,CronSpecialChars.COMMASEPRATOR));
        return exp.toString();
    }
    
    private String toStringWeekDays(CronSpecialChars [] day, CronSpecialChars seperator) {
        
        StringBuilder sb = new StringBuilder();
        for(CronSpecialChars str: day) {
            sb.append(str).append(seperator);
        }
        return sb.substring(0, sb.length()-1);
    }
}
