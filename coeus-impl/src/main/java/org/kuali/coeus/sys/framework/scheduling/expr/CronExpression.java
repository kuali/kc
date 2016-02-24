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

import org.kuali.coeus.sys.framework.scheduling.util.Time24HrFmt;

import java.text.ParseException;
import java.util.Date;

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
