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
import org.kuali.kra.award.bo.Frequency;
import org.kuali.kra.award.paymentreports.paymentschedule.AwardPaymentSchedule;
import org.kuali.kra.scheduling.sequence.XMonthlyScheduleSequence;
import org.kuali.kra.scheduling.service.ScheduleService;
import org.kuali.kra.scheduling.util.Time24HrFmt;

/**
 * 
 * This is the AwardPaymentScheduleGenerationService class.
 */

public class AwardPaymentScheduleGenerationServiceImpl implements org.kuali.kra.service.AwardPaymentScheduleGenerationService {
    
    private ScheduleService scheduleService;
    //private PersistenceService persistenceService;
    private static final java.util.Date EXPIRATION_DATE = new Date(2010,3,1);
    private static final java.util.Date AWARD_EXECUTION_DATE = new Date(2009,3,1);
    private static final java.util.Date AWARD_EFFECTIVE_DATE = new Date(2009,5,1);
    private static final java.util.Date EXPIRATION_DATE_OF_OBLIGATION = new Date(2009,10,1);
    private static final java.util.Date EFFECTIVE_DATE_OF_OBLIGATION = new Date(2010,5,1);
    
    public void generatePaymentSchedules(Award award, List<AwardReportTerm> awardReportTerms) throws ParseException{
        List<Date> dates = new ArrayList<Date>();
        AwardPaymentSchedule newAwardPaymentSchedule;
        //refreshAwardReportTerms(awardReportTerms);
        dates = generateSchedules(award,awardReportTerms);
        for(Date date: dates){
            newAwardPaymentSchedule = new AwardPaymentSchedule();
            java.sql.Date sqldate = new java.sql.Date(date.getTime());
            newAwardPaymentSchedule.setDueDate(sqldate);
            newAwardPaymentSchedule.setAward(award);
            
            award.getPaymentScheduleItems().add(newAwardPaymentSchedule);
        }
    }

    protected List<Date> generateSchedules(Award award, List<AwardReportTerm> awardReportTerms) throws ParseException{
        
        List<Date> dates = new ArrayList<Date>();
        GregorianCalendar calendar = new GregorianCalendar();        
        java.util.Date startDate = calendar.getTime();
        java.util.Date endDate = calendar.getTime();
        int dayOfMonth = 0;
        int startYear = 0;
        int endYear = 0;
        
        for(AwardReportTerm awardReportTerm: awardReportTerms){
            if(StringUtils.equalsIgnoreCase(awardReportTerm.getReportClassCode(), "6")){
                
                if(awardReportTerm.getFrequencyBaseCode()!=null){
                    if(awardReportTerm.getFrequencyBaseCode().equals("1")){
                        calendar.clear();
                        calendar.set(2009, 3, 1);//temp hardcoded award execution date.
                        startDate = calendar.getTime();
                        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                        startYear = calendar.get(Calendar.YEAR);                        
                    }else if(awardReportTerm.getFrequencyBaseCode().equals("2")){                        
                        calendar.clear();
                        calendar.set(2009, 4, 1);//temp hardcoded award effective date.
                        startDate = calendar.getTime();
                        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                        startYear = calendar.get(Calendar.YEAR);
                    }else if(awardReportTerm.getFrequencyBaseCode().equals("3")){
                        calendar.clear();
                        calendar.set(2009, 5, 1);//temp hardcoded award expiration date of obligation.
                        startDate = calendar.getTime();
                        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                        startYear = calendar.get(Calendar.YEAR);
                    }else if(awardReportTerm.getFrequencyBaseCode().equals("4")){
                        calendar.clear();
                        calendar.set(2011, 4, 1);//temp hardcoded award expiration date.
                        startDate = calendar.getTime();
                        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                        startYear = calendar.get(Calendar.YEAR);
                    }else if(awardReportTerm.getFrequencyBaseCode().equals("5")){
                        calendar.clear();
                        calendar.set(2009, 7, 1);//temp hardcoded award effective date of obligation..
                        startDate = calendar.getTime();
                        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                        startYear = calendar.get(Calendar.YEAR);
                    }else{
                        startDate = awardReportTerm.getDueDate();
                    }                    
                    
                    if(awardReportTerm.getFrequencyBaseCode().equals("4")){                        
                        calendar.clear();
                        calendar.setTime(startDate);   
                        calendar.add(Calendar.YEAR, 1);
                        endDate = calendar.getTime();
                        endYear = calendar.get(Calendar.YEAR);
                    }else{
                        calendar.clear();
                        calendar.set(2011, 4, 1);//temp hardcoded award expiration date.
                        endDate = calendar.getTime();
                        endYear = calendar.get(Calendar.YEAR);
                    }
                    
                }
                
                awardReportTerm.refreshReferenceObject("frequency");
                Frequency frequency = awardReportTerm.getFrequency();
                if(frequency.getNumberOfMonths()!=null){                   
                    calendar.setTime(startDate);
                    if(frequency.getRepeatFlag()){                        
                        if(frequency.getNumberOfDays()!=null){
                            calendar.add(Calendar.DAY_OF_YEAR,frequency.getNumberOfDays());
                        }else if(frequency.getAdvanceNumberOfDays()!=null){
                            calendar.add(Calendar.DAY_OF_YEAR,-frequency.getAdvanceNumberOfDays());
                        }else if(frequency.getAdvanceNumberOfMonths()!=null){
                            calendar.add(Calendar.MONTH,-frequency.getAdvanceNumberOfMonths());
                        }
                        
                        dates = scheduleService.getScheduledDates(
                                startDate, endDate, new Time24HrFmt("00:00"), dayOfMonth, 1, new XMonthlyScheduleSequence(frequency.getNumberOfMonths()));
                        
                    }else{
                        if(frequency.getNumberOfDays()!=null){
                            calendar.add(Calendar.DAY_OF_YEAR,frequency.getNumberOfDays());
                        }else if(frequency.getNumberOfMonths()!=null){
                            calendar.add(Calendar.MONTH,frequency.getNumberOfMonths());
                        }else if(frequency.getAdvanceNumberOfDays()!=null){
                            calendar.add(Calendar.DAY_OF_YEAR,-frequency.getAdvanceNumberOfDays());
                        }else if(frequency.getAdvanceNumberOfMonths()!=null){
                            calendar.add(Calendar.MONTH,-frequency.getAdvanceNumberOfMonths());
                        }
                        
                        startDate = calendar.getTime();
                        dates.add(startDate);
                    }
                    
                }
                
            }
        }
        
        return dates;

    }
    
    /*private void getStartAndEndDates(AwardReportTerm awardReportTerm, Date startDate, Date endDate){
        GregorianCalendar calendar = new GregorianCalendar();
        
        if(awardReportTerm.getFrequencyBaseCode()!=null){
            if(awardReportTerm.getFrequencyBaseCode().equals("1")){
                startDate = AWARD_EXECUTION_DATE;                
            }else if(awardReportTerm.getFrequencyBaseCode().equals("2")){
                startDate = AWARD_EFFECTIVE_DATE;
            }else if(awardReportTerm.getFrequencyBaseCode().equals("3")){
                startDate = EXPIRATION_DATE_OF_OBLIGATION;
            }else if(awardReportTerm.getFrequencyBaseCode().equals("4")){
                startDate = EXPIRATION_DATE;
            }else if(awardReportTerm.getFrequencyBaseCode().equals("5")){
                startDate = EFFECTIVE_DATE_OF_OBLIGATION;
            }else{
                startDate = awardReportTerm.getDueDate();
            }                    
            
            if(awardReportTerm.getFrequencyBaseCode().equals("4")){
                calendar.setTime(startDate);   
                calendar.add(Calendar.YEAR, 1);
                endDate = calendar.getTime();
            }else{
                endDate = EXPIRATION_DATE;
            }
            
        }
    }*/

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
    
/*    protected void refreshAwardReportTerms(List<AwardReportTerm> awardReportTerms) {
        List<AwardReportTerm> persistableObjects = new ArrayList<AwardReportTerm>();
        List<String> referenceObjectNames = new ArrayList<String>();
        
        for(AwardReportTerm awardReportTerm : awardReportTerms){
            persistableObjects.add(awardReportTerm);
            referenceObjectNames.add("frequency");            
        }
        
        if(persistableObjects.size()>0 && referenceObjectNames.size()>0 ){            
            getPersistenceService().retrieveReferenceObjects(persistableObjects, referenceObjectNames);
        }
    }*/
    
/*    *//**
     * Gets the persistenceService attribute. 
     * @return Returns the persistenceService.
     *//*
    public PersistenceService getPersistenceService() {
        return persistenceService;
    }

    *//**
     * Sets the persistenceService attribute value.
     * @param persistenceService The persistenceService to set.
     *//*
    public void setPersistenceService(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }*/

}

    
