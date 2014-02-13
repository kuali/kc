/*
 * Copyright 2005-2013 The Kuali Foundation
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

import org.apache.commons.lang.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.kra.committee.rule.event.CommitteeScheduleDayEvent;
import org.kuali.kra.common.committee.web.struts.form.schedule.MonthlyScheduleDetails;
import org.kuali.kra.common.committee.web.struts.form.schedule.ScheduleData;
import org.kuali.kra.common.committee.web.struts.form.schedule.StyleKey;
import org.kuali.kra.common.committee.web.struts.form.schedule.YearlyScheduleDetails;
import org.kuali.kra.infrastructure.KeyConstants;

public class CommitteeScheduleDayRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<CommitteeScheduleDayEvent> {
    
    public static final String SCHEDULEDATA_MONTHLY_DAY = "committeeHelper.scheduleData.monthlySchedule.day";

    public static final String SCHEDULEDATA_YEARLY_DAY = "committeeHelper.scheduleData.yearlySchedule.day";
    
    /**
     * @see org.kuali.coeus.sys.framework.rule.KcBusinessRule#processRules(org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension)
     */
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
