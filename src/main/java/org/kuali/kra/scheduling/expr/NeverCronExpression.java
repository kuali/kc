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
 * This class extends CronExpression and provides NeverCronExpression implementation. 
 * <p>
 * This implementation generates expression for retrieving first schedule date only, using start date's day of month. 
 * 
 * e.g. Start Date : 02/24/09, Time : 10:10 (hh:mm)
 * Format (second minute hour day month year)
 * Generated Expression :0 10 10 24 * ?
 * Explanation: Generate date for 24th starting at 10:10 (hh:mm)  
 */
public class NeverCronExpression extends CronExpression {

    public NeverCronExpression(Date startDate, Time24HrFmt time) throws ParseException {
        super(startDate, time);
    }

    @Override
    public String getExpression() {
       
        Calendar stDt = new GregorianCalendar();
        stDt.setTime(getStartDate());        
        int stDt_dayOfMonth = stDt.get(Calendar.DAY_OF_MONTH);
        
        StringBuilder exp = new StringBuilder();
        exp.append(SECONDS).append(CronSpecialChars.SPACE);
        exp.append(getTime().getMinutes()).append(CronSpecialChars.SPACE);
        exp.append(getTime().getHours()).append(CronSpecialChars.SPACE);
        exp.append(stDt_dayOfMonth).append(CronSpecialChars.SPACE);        
        exp.append(CronSpecialChars.STAR).append(CronSpecialChars.SPACE);
        exp.append(CronSpecialChars.QUESTION);
        return exp.toString();
    }

}
