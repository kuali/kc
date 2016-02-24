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
package org.kuali.coeus.sys.framework.scheduling.seq;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface ScheduleSequence {
        
    public static final String NAME = "t";
    
    public static final String GROUP = "g";
    
    public static final String JOBNAME = "j";
    
    public static final String JOBGROUP = "g";
    
    /**
     * This method expects to generate list of dates between start and end date using cron expression.
     * 
     * @param expression can be any valid CronExpression.
     * @param startDate, is expression's begin date.
     * @param endDate, is expression's end date.
     * @return list of dates is returned.
     * @throws ParseException can thrown in case of invalid expression.
     */
    public List<Date> executeScheduleSequence(String expression, Date startDate, Date endDate) throws ParseException;

}
