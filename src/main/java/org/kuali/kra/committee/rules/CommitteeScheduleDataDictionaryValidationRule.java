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

import org.kuali.kra.committee.bo.CommitteeScheduleAttributeReferenceDummy;
import org.kuali.kra.committee.web.struts.form.schedule.DailyScheduleDetails;
import org.kuali.kra.committee.web.struts.form.schedule.MonthlyScheduleDetails;
import org.kuali.kra.committee.web.struts.form.schedule.ScheduleData;
import org.kuali.kra.committee.web.struts.form.schedule.StyleKey;
import org.kuali.kra.committee.web.struts.form.schedule.YearlyScheduleDetails;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.util.GlobalVariables;

public class CommitteeScheduleDataDictionaryValidationRule {
        
    public static final String ATTRIBUTE_NAME = "intValue";
    
    public static final String ERROR_KEY_SCHEDULEDATA_DAILYSCHEDULE_DAY = "committeeScheduleHelper.scheduleData.dailySchedule.day";
    
    public static final String ERROR_KEY_SCHEDULEDATA_WEEKLYSCHEDULE_DAY = "committeeScheduleHelper.scheduleData.weeklySchedule.week";
    
    public static final String ERROR_KEY_SCHEDULEDATA_MONTHLYSCHEDULE_DAY = "committeeScheduleHelper.scheduleData.monthlySchedule.day";
    
    public static final String ERROR_KEY_SCHEDULEDATA_MONTHLYSCHEDULE_OPTION1MONTH = "committeeScheduleHelper.scheduleData.monthlySchedule.option1Month";
    
    public static final String ERROR_KEY_SCHEDULEDATA_MONTHLYSCHEDULE_OPTION2MONTH = "committeeScheduleHelper.scheduleData.monthlySchedule.option2Month";
    
    public static final String ERROR_KEY_SCHEDULEDATA_YEARLYSCHEDULE_DAY = "committeeScheduleHelper.scheduleData.yearlySchedule.day";
    
    public static final String ERROR_KEY_SCHEDULEDATA_YEARLYSCHEDULE_OPTION1YEAR = "committeeScheduleHelper.scheduleData.yearlySchedule.option1Year";
    
    public static final String ERROR_KEY_SCHEDULEDATA_YEARLYSCHEDULE_OPTION2YEAR = "committeeScheduleHelper.scheduleData.yearlySchedule.option2Year";
    
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
                        retVal = applyRules(ATTRIBUTE_NAME, scheduleData.getDailySchedule().getDay(), ERROR_KEY_SCHEDULEDATA_DAILYSCHEDULE_DAY);
                        break;
                    case WEEKDAY:
                        retVal = true;
                        break;
                }
                break;
            case WEEKLY :
                retVal = applyRules(ATTRIBUTE_NAME, scheduleData.getWeeklySchedule().getWeek(), ERROR_KEY_SCHEDULEDATA_WEEKLYSCHEDULE_DAY);
                break;
            case MONTHLY :
                MonthlyScheduleDetails.optionValues monthOption = MonthlyScheduleDetails.optionValues.valueOf(scheduleData.getMonthlySchedule().getMonthOption());
                switch(monthOption) {
                    case XDAYANDXMONTH :
                        retVal = applyRules(ATTRIBUTE_NAME, scheduleData.getMonthlySchedule().getDay(), ERROR_KEY_SCHEDULEDATA_MONTHLYSCHEDULE_DAY);
                        if(retVal)
                            retVal = applyRules(ATTRIBUTE_NAME, scheduleData.getMonthlySchedule().getOption1Month(), ERROR_KEY_SCHEDULEDATA_MONTHLYSCHEDULE_OPTION1MONTH);
                        break;
                    case XDAYOFWEEKANDXMONTH :
                        retVal = applyRules(ATTRIBUTE_NAME, scheduleData.getMonthlySchedule().getOption2Month(), ERROR_KEY_SCHEDULEDATA_MONTHLYSCHEDULE_OPTION2MONTH);
                        break;
                }
                break;
            case YEARLY : 
                YearlyScheduleDetails.yearOptionValues yearOption = YearlyScheduleDetails.yearOptionValues.valueOf(scheduleData.getYearlySchedule().getYearOption());
                switch(yearOption) {
                    case XDAY :
                        retVal = applyRules(ATTRIBUTE_NAME, scheduleData.getYearlySchedule().getDay(), ERROR_KEY_SCHEDULEDATA_YEARLYSCHEDULE_DAY);
                        if(retVal)
                            retVal = applyRules(ATTRIBUTE_NAME, scheduleData.getYearlySchedule().getOption1Year(), ERROR_KEY_SCHEDULEDATA_YEARLYSCHEDULE_OPTION1YEAR);
                        break;
                    case CMPLX:
                        retVal = applyRules(ATTRIBUTE_NAME, scheduleData.getYearlySchedule().getOption2Year(), ERROR_KEY_SCHEDULEDATA_YEARLYSCHEDULE_OPTION2YEAR);
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
        if(null == GlobalVariables.getErrorMap().get(errorKey))
            retVal = true;
        return retVal;
    }
    
    private DictionaryValidationService getService() {
        return KraServiceLocator.getService(DictionaryValidationService.class);
    }
    
}
