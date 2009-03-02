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
package org.kuali.kra.committee.rules;

import java.sql.Date;

import org.kuali.kra.committee.rule.AddCommitteeScheduleRule;
import org.kuali.kra.committee.rule.event.AddCommitteeScheduleEvent;
import org.kuali.kra.committee.web.struts.form.schedule.ScheduleData;
import org.kuali.kra.committee.web.struts.form.schedule.StyleKey;
import org.kuali.kra.infrastructure.KeyConstants;

public class CommitteeScheduleRule extends CommitteeDocumentRule implements AddCommitteeScheduleRule {
    
    private enum constants {scheduleData, dailySchedule, weeklySchedule, monthlySchedule, yearlySchedule, scheduleEndDate, scheduleStartDate, Date};
    
    public static final String ENDINGON = "Ending On";
    
    public static final String DATE = "Date";
    
    public static final String DOT = ".";
    
    public static final String BLANK = "";
    
    public boolean processAddCommitteeScheduleRuleBusinessRules(AddCommitteeScheduleEvent addCommitteeScheduleEvent) {
        
        StringBuilder endDateId = new StringBuilder();
        StringBuilder startDateId = new StringBuilder();
        endDateId.append(constants.scheduleData).append(DOT);
        boolean rulePassed = true;
        ScheduleData scheduleData = addCommitteeScheduleEvent.getScheduleData();
        Date startDate = scheduleData.getScheduleStartDate();
        Date endDate = null;
        StyleKey key = StyleKey.valueOf(scheduleData.getRecurrenceType());        
        switch (key) {
            case NEVER :
                break;
            case DAILY : 
                endDate = scheduleData.getDailySchedule().getScheduleEndDate();
                rulePassed = isStartDateBeforeEndDate(startDate, endDate);
                endDateId.append(constants.dailySchedule);
                break;
            case WEEKLY :
                endDate = scheduleData.getWeeklySchedule().getScheduleEndDate();
                rulePassed = isStartDateBeforeEndDate(startDate, endDate);  
                endDateId.append(constants.weeklySchedule);
                break;
            case MONTHLY :
                endDate = scheduleData.getMonthlySchedule().getScheduleEndDate();
                rulePassed = isStartDateBeforeEndDate(startDate, endDate); 
                endDateId.append(constants.monthlySchedule);
                break;
            case YEARLY : 
                endDate = scheduleData.getYearlySchedule().getScheduleEndDate();
                rulePassed = isStartDateBeforeEndDate(startDate, endDate); 
                endDateId.append(constants.yearlySchedule);
                break;            
        }
        if(!rulePassed) {
            endDateId.append(DOT).append(constants.scheduleEndDate);  
            startDateId.append(constants.scheduleData).append(DOT).append(constants.scheduleStartDate);
            reportError(startDateId.toString(), KeyConstants.ERROR_COMMITTEESCHEDULE_SCHEDULEDATES, DATE, ENDINGON);
            reportError(endDateId.toString(), KeyConstants.ERROR_COMMITTEESCHEDULE_BLANK, BLANK);
        }
        return rulePassed;
    }
    
    private boolean isStartDateBeforeEndDate(Date startDate, Date endDate) {
        boolean retVal = false;
        if(startDate.before(endDate))
            retVal = true;
        return retVal;
    }
}
