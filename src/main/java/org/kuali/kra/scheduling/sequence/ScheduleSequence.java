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
package org.kuali.kra.scheduling.sequence;

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
     * @param expr can be any valid CronExpression.
     * @param startDate, is expression's begin date.
     * @param endDate, is expression's end date.
     * @return list of dates is returned.
     * @throws ParseException can thrown in case of invalid expression.
     */
    public List<Date> executeScheduleSequence(String expression, Date startDate, Date endDate) throws ParseException;

}
