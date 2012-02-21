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

import org.kuali.kra.committee.bo.CommitteeScheduleAttributeReferenceDummy;
import org.kuali.kra.committee.web.struts.form.schedule.DailyScheduleDetails;
import org.kuali.kra.committee.web.struts.form.schedule.MonthlyScheduleDetails;
import org.kuali.kra.committee.web.struts.form.schedule.ScheduleData;
import org.kuali.kra.committee.web.struts.form.schedule.StyleKey;
import org.kuali.kra.committee.web.struts.form.schedule.YearlyScheduleDetails;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.util.GlobalVariables;

public class CommitteeScheduleDataDictionaryValidationRule {
        
    public static final String ATTRIBUTE_DAY_RECURRENCE = "dayRecurrence";
    public static final String ATTRIBUTE_WEEK_RECURRENCE = "weekRecurrence";
    public static final String ATTRIBUTE_MONTH_DAY = "monthDay";
    public static final String ATTRIBUTE_MONTH_RECURRENCE = "monthRecurrence";
    public static final String ATTRIBUTE_YEAR_DAY = "yearDay";
    public static final String ATTRIBUTE_YEAR_RECURRENCE = "yearRecurrence";
    
    public static final String ERROR_KEY_SCHEDULEDATA_DAILYSCHEDULE_DAY = "committeeHelper.scheduleData.dailySchedule.day";
    
    public static final String ERROR_KEY_SCHEDULEDATA_WEEKLYSCHEDULE_DAY = "committeeHelper.scheduleData.weeklySchedule.week";
    
    public static final String ERROR_KEY_SCHEDULEDATA_MONTHLYSCHEDULE_DAY = "committeeHelper.scheduleData.monthlySchedule.day";
    
    public static final String ERROR_KEY_SCHEDULEDATA_MONTHLYSCHEDULE_OPTION1MONTH = "committeeHelper.scheduleData.monthlySchedule.option1Month";
    
    public static final String ERROR_KEY_SCHEDULEDATA_MONTHLYSCHEDULE_OPTION2MONTH = "committeeHelper.scheduleData.monthlySchedule.option2Month";
    
    public static final String ERROR_KEY_SCHEDULEDATA_YEARLYSCHEDULE_DAY = "committeeHelper.scheduleData.yearlySchedule.day";
    
    public static final String ERROR_KEY_SCHEDULEDATA_YEARLYSCHEDULE_OPTION1YEAR = "committeeHelper.scheduleData.yearlySchedule.option1Year";
    
    public static final String ERROR_KEY_SCHEDULEDATA_YEARLYSCHEDULE_OPTION2YEAR = "committeeHelper.scheduleData.yearlySchedule.option2Year";
    
    /**
     * This method test scheduleData for day, week, month and yearly integer data field validation. 
     * @param scheduleData
     * @return true if validation passes.
     */
    public boolean applyRules(ScheduleData scheduleData) {
        boolean retVal = false;
        StyleKey key = StyleKey.valueOf(scheduleData.getRecurrenceType());        
        switch (key) {
            case NEVER :
                 retVal = true;
                 break;
            case DAILY : 
                DailyScheduleDetails.optionValues dailyoption = DailyScheduleDetails.optionValues.valueOf(scheduleData.getDailySchedule().getDayOption());
                switch (dailyoption) {
                    case XDAY: 
                        retVal = applyRules(ATTRIBUTE_DAY_RECURRENCE, scheduleData.getDailySchedule().getDay(), ERROR_KEY_SCHEDULEDATA_DAILYSCHEDULE_DAY);
                        break;
                    case WEEKDAY:
                        retVal = true;
                        break;
                }
                break;
            case WEEKLY :
                retVal = applyRules(ATTRIBUTE_WEEK_RECURRENCE, scheduleData.getWeeklySchedule().getWeek(), ERROR_KEY_SCHEDULEDATA_WEEKLYSCHEDULE_DAY);
                break;
            case MONTHLY :
                MonthlyScheduleDetails.optionValues monthOption = MonthlyScheduleDetails.optionValues.valueOf(scheduleData.getMonthlySchedule().getMonthOption());
                switch(monthOption) {
                    case XDAYANDXMONTH :
                        retVal = applyRules(ATTRIBUTE_MONTH_DAY, scheduleData.getMonthlySchedule().getDay(), ERROR_KEY_SCHEDULEDATA_MONTHLYSCHEDULE_DAY);
                        if(retVal)
                            retVal = applyRules(ATTRIBUTE_MONTH_RECURRENCE, scheduleData.getMonthlySchedule().getOption1Month(), ERROR_KEY_SCHEDULEDATA_MONTHLYSCHEDULE_OPTION1MONTH);
                        break;
                    case XDAYOFWEEKANDXMONTH :
                        retVal = applyRules(ATTRIBUTE_MONTH_RECURRENCE, scheduleData.getMonthlySchedule().getOption2Month(), ERROR_KEY_SCHEDULEDATA_MONTHLYSCHEDULE_OPTION2MONTH);
                        break;
                }
                break;
            case YEARLY : 
                YearlyScheduleDetails.yearOptionValues yearOption = YearlyScheduleDetails.yearOptionValues.valueOf(scheduleData.getYearlySchedule().getYearOption());
                switch(yearOption) {
                    case XDAY :
                        retVal = applyRules(ATTRIBUTE_YEAR_DAY, scheduleData.getYearlySchedule().getDay(), ERROR_KEY_SCHEDULEDATA_YEARLYSCHEDULE_DAY);
                        if(retVal)
                            retVal = applyRules(ATTRIBUTE_YEAR_RECURRENCE, scheduleData.getYearlySchedule().getOption1Year(), ERROR_KEY_SCHEDULEDATA_YEARLYSCHEDULE_OPTION1YEAR);
                        break;
                    case CMPLX:
                        retVal = applyRules(ATTRIBUTE_YEAR_RECURRENCE, scheduleData.getYearlySchedule().getOption2Year(), ERROR_KEY_SCHEDULEDATA_YEARLYSCHEDULE_OPTION2YEAR);
                        break;
                }
                break;            
        }
        return retVal;
    }
    
    /**
     * This method is helper method to test rule specific to field. 
     * Note: See if there is better way to test this feature.
     * @param attributeName
     * @param value
     * @param errorKey
     * @return
     */
    private  boolean applyRules(String attributeName, Integer value, String errorKey) {
        boolean retVal = false;
        getService().validateAttributeRequired(CommitteeScheduleAttributeReferenceDummy.class.getName(), attributeName, value, false, errorKey);
        retVal = isError(errorKey);
        if(retVal) {
            getService().validateAttributeFormat(CommitteeScheduleAttributeReferenceDummy.class.getName(), attributeName, value.toString(), errorKey);
            retVal = isError(errorKey);
        }        
        return retVal;
    }
    
    private boolean isError(String errorKey) {
        boolean retVal = false;
        if(null == GlobalVariables.getMessageMap().getErrorMessages().get(errorKey))
            retVal = true;
        return retVal;
    }
    
    private DictionaryValidationService getService() {
        return KNSServiceLocator.getKNSDictionaryValidationService();
    }
    
}
