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

import org.kuali.kra.committee.rule.event.CommitteeScheduleDayEvent;
import org.kuali.kra.committee.web.struts.form.schedule.MonthlyScheduleDetails;
import org.kuali.kra.committee.web.struts.form.schedule.ScheduleData;
import org.kuali.kra.committee.web.struts.form.schedule.StyleKey;
import org.kuali.kra.committee.web.struts.form.schedule.YearlyScheduleDetails;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

public class CommitteeScheduleDayRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<CommitteeScheduleDayEvent> {
    
    public static final String SCHEDULEDATA_MONTHLY_DAY = "committeeScheduleHelper.scheduleData.monthlySchedule.day";

    public static final String SCHEDULEDATA_YEARLY_DAY = "committeeScheduleHelper.scheduleData.yearlySchedule.day";
    
    /**
     * @see org.kuali.kra.rule.BusinessRuleInterface#processRules(org.kuali.kra.rule.event.KraDocumentEventBaseExtension)
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
                        rulePassed = validateDay(scheduleData.getYearlySchedule().getDay(), SCHEDULEDATA_YEARLY_DAY);   
                        break;
                }
                break;              
        }
        return rulePassed;
    }
    
    private boolean validateDay(int day, String key) {
        boolean rulePassed = true;
        if(day > 31 ) {
            rulePassed = false;
            reportError(key, KeyConstants.ERROR_COMMITTEESCHEDULE_DAY);
        }
        return rulePassed;     
    }

}