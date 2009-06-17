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
import java.util.Date;

import org.kuali.kra.scheduling.expr.util.CronSpecialChars;
import org.kuali.kra.scheduling.util.Time24HrFmt;

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

    /**
     * @see org.kuali.kra.scheduling.expr.CronExpression#getExpression()
     */
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
