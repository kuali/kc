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
import java.util.Date;

/**
 * This class extends CronExpression and provides DayCronExpression implementation.
 * <p>
 * This implementation generates expression for daily recurrence. 
 * 
 * e.g. Start Date : 02/24/09, Time : 10:10 (hh:mm)
 * Format (second minute hour day month year)
 * Generated Expression :Expression :0 10 10 24/1 * ?  
 * Explanation: Will generate dates starting 24/02/09 every day at 10:10.
 * 
 * e.g. Start Date : 02/24/09, Time : 10:10 (hh:mm)
 * Format (second minute hour day month year)
 * Generated Expression :Expression :0 10 10 24/2 * ?  
 * Explanation: Will generate dates starting 24/02/09 every other day at 10:10.
 */
public class DayCronExpression extends CronExpression {

    private Integer frequency;
    
    public DayCronExpression(Date startDate, Time24HrFmt time, Integer frequency) throws ParseException {
        super(startDate, time);
        this.frequency = frequency;
    }

    @Override
    public String getExpression() {
 
        StringBuilder exp = new StringBuilder();
        exp.append(SECONDS).append(CronSpecialChars.SPACE);
        exp.append(getTime().getMinutes()).append(CronSpecialChars.SPACE);
        exp.append(getTime().getHours()).append(CronSpecialChars.SPACE);
        exp.append(CronSpecialChars.FIRST).append(CronSpecialChars.SLASH).append(frequency).append(CronSpecialChars.SPACE);
        exp.append(CronSpecialChars.STAR).append(CronSpecialChars.SPACE);
        exp.append(CronSpecialChars.QUESTION);        
        return exp.toString();
    }

}
