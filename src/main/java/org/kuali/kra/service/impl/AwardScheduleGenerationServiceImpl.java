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
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.bo.Frequency;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.award.paymentreports.paymentschedule.FrequencyBaseConstants;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.scheduling.sequence.XMonthlyScheduleSequence;
import org.kuali.kra.scheduling.service.ScheduleService;
import org.kuali.kra.scheduling.util.Time24HrFmt;
import org.kuali.kra.service.AwardScheduleGenerationService;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.service.PersistenceService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * This is the AwardScheduleGenerationService class.
 */

@Transactional
public class AwardScheduleGenerationServiceImpl implements AwardScheduleGenerationService {
    
    public static final String ZERO_HOURS = "00:00";
    public static final String FREQUENCY_OBJECT_STRING = "frequency";
    
    private ScheduleService scheduleService;
    private PersistenceService persistenceService;
    private KualiConfigurationService kualiConfigurationService;
    private int periodInYears;
    private HashMap<String, java.util.Date> mapOfDates;
    private GregorianCalendar calendarClassLevel;
    
    /**
     * 
     * Constructs a AwardScheduleGenerationServiceImpl.java.
     */
    public AwardScheduleGenerationServiceImpl(){
        
    }
    
    /**
     * 
     * This method gathers all the relevant dates from Award and child objects and puts them in a map.
     * 
     * @param award
     */
    protected void initializeDatesForThisAward(Award award){
        calendarClassLevel = new GregorianCalendar();
        mapOfDates = new HashMap<String, java.util.Date>();
        
        mapOfDates.put(FrequencyBaseConstants.AWARD_EXECUTION_DATE.getfrequencyBase(), award.getAwardExecutionDate()); 
        
        mapOfDates.put(FrequencyBaseConstants.AWARD_EFFECTIVE_DATE.getfrequencyBase(), award.getAwardEffectiveDate());
        
        calendarClassLevel.clear();
        calendarClassLevel.set(2009, Calendar.JUNE, 1);//temp hardcoded award expiration date of obligation.
        mapOfDates.put(FrequencyBaseConstants.AWARD_EXPIRATION_DATE_OF_OBLIGATION.getfrequencyBase(), calendarClassLevel.getTime());
        
        mapOfDates.put(FrequencyBaseConstants.FINAL_EXPIRATION_DATE.getfrequencyBase(), award.getProjectEndDate());
        
        calendarClassLevel.clear();
        calendarClassLevel.set(2009, Calendar.AUGUST, 1);//temp hardcoded award effective date of obligation.
        mapOfDates.put(FrequencyBaseConstants.AWARD_EFFECTIVE_DATE_OF_OBLIGATION.getfrequencyBase(), calendarClassLevel.getTime());
    }

    /**
     * 
     * @see org.kuali.kra.service.AwardScheduleGenerationService#generateSchedules(org.kuali.kra.award.bo.Award, java.util.List)
     */
    public List<Date> generateSchedules(Award award, List<AwardReportTerm> awardReportTerms) throws ParseException{
        
        initializeDatesForThisAward(award);        
        refreshAwardReportTerms(awardReportTerms);
        
        setPeriodInYears(Integer.parseInt(
                kualiConfigurationService.getParameter(Constants.PARAMETER_MODULE_AWARD,Constants.PARAMETER_COMPONENT_DOCUMENT
                ,KeyConstants.PERIOD_IN_YEARS_WHEN_FREQUENCY_BASE_IS_FINAL_EXPIRATION_DATE).getParameterValue()));
        
        return getDates(awardReportTerms);
        
    }

    /**
     * This is a helper method. This method calls evaluates the frequency and frequency base and generates dates either by calling the scheduling service or
     * without that.
     * 
     * @param awardReportTerms
     * @param dates
     * @param calendar
     * @return
     * @throws ParseException
     */
    List<Date> getDates(List<AwardReportTerm> awardReportTerms)
            throws ParseException {
        List<Date> dates = new ArrayList<Date>();        
        java.util.Date startDate;
        java.util.Date endDate;
        Calendar calendar = new GregorianCalendar();
        
        for(AwardReportTerm awardReportTerm: awardReportTerms){
            if(StringUtils.equalsIgnoreCase(awardReportTerm.getReportClassCode(), kualiConfigurationService.getParameter(Constants.PARAMETER_MODULE_AWARD
                    ,Constants.PARAMETER_COMPONENT_DOCUMENT,KeyConstants.REPORT_CLASS_FOR_PAYMENTS_AND_INVOICES).getParameterValue())){
                startDate = getStartDate(awardReportTerm);
                endDate = getEndDate(awardReportTerm.getFrequencyBaseCode(),startDate);
                
                if(startDate!=null){                    
                    calendar.setTime(startDate);
                    if(endDate!=null && awardReportTerm.getFrequency().getRepeatFlag() && awardReportTerm.getFrequency().getNumberOfMonths()!=null){            
                        dates = scheduleService.getScheduledDates(startDate, endDate, new Time24HrFmt(ZERO_HOURS)
                                    , new XMonthlyScheduleSequence(awardReportTerm.getFrequency().getNumberOfMonths()), calendar.get(Calendar.DAY_OF_MONTH));
                    }else{            
                        dates.add(startDate);
                    }                        
                }                
            }            
        }
        return dates;
    }
    
    /**
     * 
     * This method determines and returns the start date based on the frequency base code.
     * 
     * @param awardReportTerm
     * @return
     */
    protected Date getStartDate(AwardReportTerm awardReportTerm){
        Calendar calendar = new GregorianCalendar();
        
        calendar.clear();
        
        if(mapOfDates.containsKey(awardReportTerm.getFrequencyBaseCode())){
            calendar.setTime(mapOfDates.get(awardReportTerm.getFrequencyBaseCode()));
        }else{
            calendar.setTimeInMillis(awardReportTerm.getDueDate().getTime());
        }
        
        return getStartDateFromTheBaseDate(calendar, awardReportTerm.getFrequency());
    }

    /**
     * 
     * This is a helper method that updates the base date based on frequency if required to get the start date.
     * 
     * @param startDate
     * @param frequency
     * @return
     */
    protected Date getStartDateFromTheBaseDate(Calendar calendar, Frequency frequency) {
        
        addOffSetPeriodToStartDate(frequency, calendar);
        
        addNumberOfMonthsToStartDate(frequency, calendar);
        
        return calendar.getTime();
    }

    /**
     * numberOfDays,AdvanceNumberOfDays and AdvanceNumberOfMonths fields of Frequency BO represent any offset from the base date, if present.
     * 
     * Only 1 out of the three can be not null for any frequency. This method determines it and adds the required offset to the base date.
     * 
     * @param frequency
     * @param calendar
     */
    protected void addOffSetPeriodToStartDate(Frequency frequency, Calendar calendar) {
        if(frequency!= null){
            if(frequency.getNumberOfDays()!=null){
                calendar.add(Calendar.DAY_OF_YEAR,frequency.getNumberOfDays());
            }else if(frequency.getAdvanceNumberOfDays()!=null){
                calendar.add(Calendar.DAY_OF_YEAR,-frequency.getAdvanceNumberOfDays());
            }else if(frequency.getAdvanceNumberOfMonths()!=null){
                calendar.add(Calendar.MONTH,-frequency.getAdvanceNumberOfMonths());
            }    
        }
    }

    /**
     * If the frequency is x monthly, numberOfMonths field in Frequency BO specifies the same.
     * 
     * This method adds the number of months from Frequency BO to base date to get the first date.
     * 
     * @param frequency
     * @param calendar
     */
    protected void addNumberOfMonthsToStartDate(Frequency frequency, Calendar calendar) {
        if(frequency.getNumberOfMonths()!=null){
            calendar.add(Calendar.MONTH, frequency.getNumberOfMonths());
        }
    }
    
    /**
     * 
     * This method returns the end date based on start date and frequency base code.
     * 
     * If frequency base code is 4(Final Expiration Date), it adds 1 year to the start date and returns it.
     * otherwise it returns the final expiration date itself.
     * 
     * @param frequencyBaseCode
     * @param startDate
     * @return
     */
    protected Date getEndDate(String frequencyBaseCode, Date startDate){
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.clear();
        if(frequencyBaseCode.equals(FrequencyBaseConstants.FINAL_EXPIRATION_DATE.getfrequencyBase())){
            calendar.setTime(startDate);   
            calendar.add(Calendar.YEAR, getPeriodInYears());            
        }else{
            calendar.setTime(mapOfDates.get(FrequencyBaseConstants.FINAL_EXPIRATION_DATE.getfrequencyBase()));
        }
        return calendar.getTime();
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
    
    /**
     * 
     * This method collects all the AwardReportTerm objects in one collection and does a refresh reference object
     * on all of them in one single transaction.
     * 
     * @param awardReportTerms
     */
    void refreshAwardReportTerms(List<AwardReportTerm> awardReportTerms) {
        List<AwardReportTerm> persistableObjects = new ArrayList<AwardReportTerm>();
        List<String> referenceObjectNames = new ArrayList<String>();
        
        for(AwardReportTerm awardReportTerm : awardReportTerms){
            persistableObjects.add(awardReportTerm);
            referenceObjectNames.add(FREQUENCY_OBJECT_STRING);            
        }
        
        if(!awardReportTerms.isEmpty()){
            getPersistenceService().retrieveReferenceObjects(persistableObjects, referenceObjectNames);
        }
    }
    
    /**
     * Gets the persistenceService attribute. 
     * @return Returns the persistenceService.
     */
    public PersistenceService getPersistenceService() {
        return persistenceService;
    }

    /**
     * Sets the persistenceService attribute value.
     * @param persistenceService The persistenceService to set.
     */
    public void setPersistenceService(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    /**
     * Gets the kualiConfigurationService attribute. 
     * @return Returns the kualiConfigurationService.
     */
    public KualiConfigurationService getKualiConfigurationService() {
        return kualiConfigurationService;
    }

    /**
     * Sets the kualiConfigurationService attribute value.
     * @param kualiConfigurationService The kualiConfigurationService to set.
     */
    public void setKualiConfigurationService(KualiConfigurationService kualiConfigurationService) {
        this.kualiConfigurationService = kualiConfigurationService;
    }

    /**
     * Gets the periodInYears attribute. 
     * @return Returns the periodInYears.
     */
    public int getPeriodInYears() {
        return periodInYears;
    }

    /**
     * Sets the periodInYears attribute value.
     * @param periodInYears The periodInYears to set.
     */
    public void setPeriodInYears(int periodInYears) {
        this.periodInYears = periodInYears;
    }

}

    
