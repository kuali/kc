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
package org.kuali.kra.scheduling.sequence;


/**
 * This class is variation of DefaultSchduleSequence, it adds logic wrapper to dates returned by Quartz util.
 * <p>
 * Logic wrapper filters dates, to generate schedule for every week, every other week, etc ...
 * <p>
 * This implementation is very specifically addressed to deal with week, biweekly, etc date filtering requirements. Implementation
 * uses org.quartz.TriggerUtils class to generate List of dates from Cron expression.
 * <p>
 * It uses current time zone, where application is hosted.
 * <p>
 * Note: Dates used in generating schedule must be wrapped with required time accuracy.
 * e.g.  Start Date: 02/01/09 10:10 End Date: 02/05/09 10:10 
 * Generated Dates will be in between 02/01/09 10:10 and 02/05/09 10:10.
 * Any date expected before 02/01/09 10:09 will be ignored. Date 02/01/09 10:00 will be ignored.
 * Any date expected after  02/05/09 10:11 will be ignored. Date 02/05/09 12:00 will be ignored.
 */
public abstract class ScheduleSequenceDecorator  implements ScheduleSequence {
    
    protected ScheduleSequence scheduleSequence; 
    
    public ScheduleSequenceDecorator(ScheduleSequence scheduleSequence) {
        this.scheduleSequence = scheduleSequence;
    }

}
