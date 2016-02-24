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
