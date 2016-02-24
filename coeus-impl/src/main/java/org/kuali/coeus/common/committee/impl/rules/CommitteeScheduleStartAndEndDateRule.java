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
package org.kuali.coeus.common.committee.impl.rules;

import org.kuali.coeus.common.committee.impl.rule.event.CommitteeScheduleStartAndEndDateEvent;
import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.ScheduleData;
import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.StyleKey;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.kra.infrastructure.KeyConstants;

import java.sql.Date;

public class CommitteeScheduleStartAndEndDateRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<CommitteeScheduleStartAndEndDateEvent> {
    
    private enum Constants {committeeHelper, scheduleData, dailySchedule, weeklySchedule, monthlySchedule, yearlySchedule, scheduleEndDate, scheduleStartDate, Date};

    public static final String DOT = ".";
    
    @Override
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
