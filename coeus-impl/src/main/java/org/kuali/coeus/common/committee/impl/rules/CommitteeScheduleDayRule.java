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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.rule.event.CommitteeScheduleDayEvent;
import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.MonthlyScheduleDetails;
import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.ScheduleData;
import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.StyleKey;
import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.YearlyScheduleDetails;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.kra.infrastructure.KeyConstants;

public class CommitteeScheduleDayRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<CommitteeScheduleDayEvent> {
    
    public static final String SCHEDULEDATA_MONTHLY_DAY = "committeeHelper.scheduleData.monthlySchedule.day";

    public static final String SCHEDULEDATA_YEARLY_DAY = "committeeHelper.scheduleData.yearlySchedule.day";
    
    @Override
    public boolean processRules(CommitteeScheduleDayEvent event) {
        boolean rulePassed = true;
        ScheduleData scheduleData = event.getScheduleData();
        StyleKey key = StyleKey.valueOf(scheduleData.getRecurrenceType());        
        switch (key) {
            case MONTHLY :
                MonthlyScheduleDetails.optionValues monthOption = MonthlyScheduleDetails.optionValues.valueOf(scheduleData.getMonthlySchedule().getMonthOption());
                switch(monthOption) {
                    case XDAYANDXMONTH :
                        rulePassed = validateDay(scheduleData.getMonthlySchedule().getDay(), SCHEDULEDATA_MONTHLY_DAY);
                        break;
                }                
                break;
            case YEARLY : 
                YearlyScheduleDetails.yearOptionValues yearOption = YearlyScheduleDetails.yearOptionValues.valueOf(scheduleData.getYearlySchedule().getYearOption());
                switch(yearOption) {
                    case XDAY :
                        rulePassed = validateDay(scheduleData.getYearlySchedule().getDay(), scheduleData.getYearlySchedule().getSelectedOption1Month(), SCHEDULEDATA_YEARLY_DAY);   
                        break;
                }
                break;              
        }
        return rulePassed;
    }
    
    private boolean validateDay(Integer day, String key) {
        boolean rulePassed = true;
        if((day != null) && (day.compareTo(31) > 0)) {
            rulePassed = false;
            reportError(key, KeyConstants.ERROR_COMMITTEESCHEDULE_DAY, "31");
        }
        return rulePassed;     
    }

    private boolean validateDay(Integer day, String month, String key) {
        boolean rulePassed = true;
        int maxDay;
        
        if (StringUtils.equalsIgnoreCase(month, "FEBRUARY")) {
            maxDay = 29;
        } else if(StringUtils.equalsIgnoreCase(month, "APRIL") || StringUtils.equalsIgnoreCase(month, "JUNE")
                || StringUtils.equalsIgnoreCase(month, "SEPTEMBER") || StringUtils.equalsIgnoreCase(month, "NOVEMBER")) {
            maxDay = 30;
        } else {
            maxDay = 31;
        }

        if((day != null) && (day.compareTo(maxDay) > 0)) {
            rulePassed = false;
            reportError(key, KeyConstants.ERROR_COMMITTEESCHEDULE_DAY, "31");
        }

        return rulePassed;     
    }

}
