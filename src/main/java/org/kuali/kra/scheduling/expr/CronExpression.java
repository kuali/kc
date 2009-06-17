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

import org.kuali.kra.scheduling.util.Time24HrFmt;

/**
 * This is abstract class, expects to implement expression generation logic.
 */
public abstract class CronExpression {
    
    private Date startDate;
    
    private Time24HrFmt time;
    
    public final String SECONDS = "0";
    
    /**
     * Constructs a CronExpression.java.
     * @param startDate is begin date for schedule generation.
     * @param time which schedule date refers to.
     * @throws ParseException might be thrown if CronExpression is incorrect.
     */
    public CronExpression(Date startDate, Time24HrFmt time) throws ParseException {
        super();
        this.startDate = startDate;
        this.time = time;
    }
    
    /**
     * This method implementation must provide valid Cron Expression.
     * @return Cron Expression.
     */
    public abstract String getExpression();

    protected Date getStartDate() {
        return startDate;
    }

    protected Time24HrFmt getTime() {
        return time;
    }

}
