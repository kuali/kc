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
package org.kuali.kra.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.bo.AwardReportTerm;
import org.kuali.kra.award.paymentreports.paymentschedule.AwardPaymentSchedule;
import org.kuali.kra.scheduling.service.ScheduleService;
import org.kuali.kra.scheduling.util.Time24HrFmt;

/**
 * 
 * This is the AwardPaymentScheduleGenerationService class.
 */

public class AwardPaymentScheduleGenerationServiceImpl implements org.kuali.kra.service.AwardPaymentScheduleGenerationService {
    
    private ScheduleService scheduleService;

    public void generateSchedules(Award award, List<AwardReportTerm> awardReportTerms) throws ParseException{
        AwardPaymentSchedule newAwardPaymentSchedule;
        Time24HrFmt time = new Time24HrFmt("00:00");
        List<Date> dates;
        GregorianCalendar calendar = new GregorianCalendar();        
        java.util.Date startDate = calendar.getTime();
        calendar.add(Calendar.YEAR, 1);
        java.util.Date endDate = calendar.getTime();
        for(AwardReportTerm awardReportTerm: awardReportTerms){
            if(StringUtils.equalsIgnoreCase(awardReportTerm.getReportClassCode(), "6")){
                dates = new ArrayList<Date>();
                if(StringUtils.equalsIgnoreCase(awardReportTerm.getFrequencyCode(),"2")){
                    dates = scheduleService.getScheduledDates(startDate, endDate, time, 5, 1, null);
                    
                }else if (StringUtils.equalsIgnoreCase(awardReportTerm.getFrequencyCode(),"3")){
                    dates = scheduleService.getScheduledDates(startDate, endDate, time, 5, 3, null);
                    
                }
                
                for(Date date: dates){
                    newAwardPaymentSchedule = new AwardPaymentSchedule();
                    java.sql.Date sqldate = new java.sql.Date(date.getTime());
                    newAwardPaymentSchedule.setDueDate(sqldate);
                    newAwardPaymentSchedule.setAward(award);
                    
                    award.getPaymentScheduleItems().add(newAwardPaymentSchedule);
                }
            }
        }

    }

    /**
     * Gets the scheduleService attribute. 
     * @return Returns the scheduleService.
     */
    public ScheduleService getScheduleService() {
        return scheduleService;
    }

    /**
     * Sets the scheduleService attribute value.
     * @param scheduleService The scheduleService to set.
     */
    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

}
