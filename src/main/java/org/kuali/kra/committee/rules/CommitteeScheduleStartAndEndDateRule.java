/*
 * Copyright 2005-2010 The Kuali Foundation
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

import org.kuali.kra.committee.rule.event.CommitteeScheduleStartAndEndDateEvent;
import org.kuali.kra.committee.web.struts.form.schedule.ScheduleData;
import org.kuali.kra.committee.web.struts.form.schedule.StyleKey;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

public class CommitteeScheduleStartAndEndDateRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<CommitteeScheduleStartAndEndDateEvent> {
    
    private enum Constants {committeeHelper, scheduleData, dailySchedule, weeklySchedule, monthlySchedule, yearlySchedule, scheduleEndDate, scheduleStartDate, Date};

    public static final String DOT = ".";
    
    public static final String BLANK = "";
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.committee.rule.AddCommitteeScheduleStartAndEndDateRule#processAddCommitteeScheduleRuleBusinessRules(
     *      org.kuali.kra.committee.rule.event.CommitteeScheduleStartAndEndDateEvent)
     */
    public boolean processRules(CommitteeScheduleStartAndEndDateEvent addCommitteeScheduleEvent) {
        boolean valid = true;
        
        StringBuilder errorPathBuilder = new StringBuilder();
        errorPathBuilder.append(Constants.committeeHelper).append(DOT);
        errorPathBuilder.append(Constants.scheduleData).append(DOT);
        
        ScheduleData scheduleData = addCommitteeScheduleEvent.getScheduleData();
        Date startDate = scheduleData.getScheduleStartDate();
        
        if (startDate == null) {
            errorPathBuilder.append(Constants.scheduleStartDate);
            reportError(errorPathBuilder.toString(), KeyConstants.ERROR_COMMITTEESCHEDULE_STARTDATE_BLANK);
            valid = false;
        } else {
            valid &= validateStartDateEndDateAfterOrEquals(scheduleData, errorPathBuilder);
        }
        
        return valid;
    }
    
    private boolean validateStartDateEndDateAfterOrEquals(ScheduleData scheduleData, StringBuilder errorPathBuilder) {
        boolean rulePassed = true;
        Date startDate = scheduleData.getScheduleStartDate();
        Date endDate = null;
        String [] msg = new String[1];
        StyleKey key = StyleKey.valueOf(scheduleData.getRecurrenceType());        
        switch (key) {
            case NEVER :
                break;
            case DAILY : 
                endDate = scheduleData.getDailySchedule().getScheduleEndDate();
                rulePassed = !isStartDateEndDateAfterOrEquals(startDate, endDate, msg);
                errorPathBuilder.append(Constants.dailySchedule);
                break;
            case WEEKLY :
                endDate = scheduleData.getWeeklySchedule().getScheduleEndDate();
                rulePassed = !isStartDateEndDateAfterOrEquals(startDate, endDate, msg); 
                errorPathBuilder.append(Constants.weeklySchedule);
                break;
            case MONTHLY :
                endDate = scheduleData.getMonthlySchedule().getScheduleEndDate();
                rulePassed = !isStartDateEndDateAfterOrEquals(startDate, endDate, msg);
                errorPathBuilder.append(Constants.monthlySchedule);
                break;
            case YEARLY : 
                endDate = scheduleData.getYearlySchedule().getScheduleEndDate();
                rulePassed = !isStartDateEndDateAfterOrEquals(startDate, endDate, msg);
                errorPathBuilder.append(Constants.yearlySchedule);
                break;            
        }
        if (!rulePassed) {
            errorPathBuilder.append(DOT).append(Constants.scheduleEndDate);  
            reportError(errorPathBuilder.toString(), msg[0]);
        }
        return rulePassed;
    }
    
    private boolean isStartDateEndDateAfterOrEquals(Date startDate, Date endDate, String... msg) {
        boolean retVal = false;
        if(startDate.toString().equals(endDate.toString())) {
            msg[0] = KeyConstants.ERROR_COMMITTEESCHEDULE_STARTANDENDDATE_EQUAL;
            retVal = true;
        }
        if(!retVal && startDate.after(endDate)) {
            msg[0] = KeyConstants.ERROR_COMMITTEESCHEDULE_STARTANDENDDATE;
            retVal = true;
        }       
        return retVal;
    }
}
