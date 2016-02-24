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
package org.kuali.kra.award.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.scheduling.ScheduleService;
import org.kuali.coeus.sys.framework.scheduling.seq.DefaultScheduleSequence;
import org.kuali.coeus.sys.framework.scheduling.seq.ScheduleSequence;
import org.kuali.coeus.sys.framework.scheduling.seq.TrimDatesScheduleSequenceDecorator;
import org.kuali.coeus.sys.framework.scheduling.seq.XMonthlyScheduleSequenceDecorator;
import org.kuali.coeus.sys.framework.scheduling.util.Time24HrFmt;
import org.kuali.kra.award.AwardAmountInfoService;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.paymentreports.Frequency;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.award.paymentreports.paymentschedule.FrequencyBaseConstants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.award.service.AwardScheduleGenerationService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.PersistenceService;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;

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
    private ParameterService parameterService;
    private AwardAmountInfoService awardAmountInfoService;
    

    public AwardScheduleGenerationServiceImpl(){
        
    }
    
    /**
     * 
     * This method gathers all the relevant dates from Award and child objects and puts them in a map.
     * 
     * @param award
     */
    protected void initializeDatesForThisAward(Award award, Map<String, java.util.Date> mapOfDates){
        AwardAmountInfo awardAmountInfo = awardAmountInfoService.fetchAwardAmountInfoWithHighestTransactionId(award.getAwardAmountInfos());
        
        mapOfDates.put(FrequencyBaseConstants.AWARD_EXECUTION_DATE.getfrequencyBase(), award.getAwardExecutionDate()); 

        mapOfDates.put(FrequencyBaseConstants.AWARD_EFFECTIVE_DATE.getfrequencyBase(), award.getAwardEffectiveDate());
        
        mapOfDates.put(FrequencyBaseConstants.AWARD_EXPIRATION_DATE_OF_OBLIGATION.getfrequencyBase(), awardAmountInfo.getObligationExpirationDate());
        
        mapOfDates.put(FrequencyBaseConstants.FINAL_EXPIRATION_DATE.getfrequencyBase(), awardAmountInfo.getFinalExpirationDate());
        
        mapOfDates.put(FrequencyBaseConstants.AWARD_EFFECTIVE_DATE_OF_OBLIGATION.getfrequencyBase(), awardAmountInfo.getCurrentFundEffectiveDate());
    }

    @Override
    public List<Date> generateSchedules(Award award, List<AwardReportTerm> awardReportTerms, boolean isThisNotPaymentPanel) throws ParseException{
        List<Date> dates = new ArrayList<Date>();
        Map<String, java.util.Date> mapOfDates = new HashMap<String, java.util.Date>();
        
        initializeDatesForThisAward(award, mapOfDates);        
        refreshAwardReportTerms(awardReportTerms);
        
        int index = 0;
        for(AwardReportTerm awardReportTerm: awardReportTerms){
            if(canGenerateSchedules(awardReportTerm, isThisNotPaymentPanel)){                
                dates.addAll(getDates(awardReportTerm, mapOfDates, index));
            }
            index++;
        }
        
        return dates;
    }
    
    /**
     * This is a helper method. This method calls evaluates the frequency and frequency base and generates dates either by calling the scheduling service or
     * without that.
     * 
     * @param awardReportTerm     
     * @param index TODO
     * @return
     * @throws ParseException
     */
    protected List<Date> getDates(AwardReportTerm awardReportTerm, Map<String, java.util.Date> mapOfDates, int index) throws ParseException {
        List<Date> dates = new ArrayList<Date>();        
        java.util.Date startDate;
        java.util.Date endDate = null;
        Calendar calendar = new GregorianCalendar();
        final String SF_269_EXPENDITURE_REPORT_CODE = "33";
        final String FREQUENCY_ANNUAL = "Annual";
        if (awardReportTerm.getReportCode().equalsIgnoreCase(SF_269_EXPENDITURE_REPORT_CODE)
                && awardReportTerm.getFrequencyBaseCode().equalsIgnoreCase(
                        FrequencyBaseConstants.AWARD_EXPIRATION_DATE_OF_OBLIGATION.getfrequencyBase())
                && awardReportTerm.getFrequency().getDescription().equalsIgnoreCase(FREQUENCY_ANNUAL)
                && (mapOfDates.get(FrequencyBaseConstants.AWARD_EXPIRATION_DATE_OF_OBLIGATION.getfrequencyBase()) != null)) {
            calendar.setTime(mapOfDates.get(FrequencyBaseConstants.AWARD_EXPIRATION_DATE_OF_OBLIGATION.getfrequencyBase()));
            startDate = calendar.getTime();
        }
        else {
            startDate = getStartDate(awardReportTerm, mapOfDates);
        }
        if (StringUtils.isNotBlank(awardReportTerm.getFrequencyBaseCode())) {
            endDate = getEndDate(awardReportTerm.getFrequencyBaseCode(),startDate, mapOfDates);
        }
        
        if (startDate != null && endDate != null) {
            calendar.setTime(startDate);
            if (awardReportTerm.getFrequency().getRepeatFlag()
                    && awardReportTerm.getFrequency().getNumberOfMonths() != null) {
                //if the end date is before the start date, set the end date to the start date
                //so the schedule generation doesn't error and creates a single date.
                if (endDate.before(startDate)) {
                    endDate = startDate;
                }
                ScheduleSequence scheduleSequence = new XMonthlyScheduleSequenceDecorator(
                    new TrimDatesScheduleSequenceDecorator(new DefaultScheduleSequence()), awardReportTerm.getFrequency()
                            .getNumberOfMonths());
                dates = scheduleService.getScheduledDates(startDate, endDate, new Time24HrFmt(ZERO_HOURS), scheduleSequence,
                        calendar.get(Calendar.DAY_OF_MONTH));
            }
            else {
                dates.add(startDate);
            }
        }
        
        return dates;
    }
    /*
    protected void reportError(String errorKey) {
        KcServiceLocator.getService(ErrorReporter.class).reportSoftError(errorKey, KeyConstants.ERROR_SCHEDULE_START_DATE_PRECEDES_END_DATE);
        
    }*/
    
    /**
     * This method determines if the schedules should be generated for the particular <code>AwardReportTerm</code> object.
     * 
     * @param awardReportTerm
     * @param isThisNotPaymentPanel - if the method is being called for generating schedules on report panel, the value passed will be false;
     *                                  if the method is being called from payment panel, the value passed will be true.
     *                                      This is to filter the special report class for payment panel. 
     * @return
     */
    protected boolean canGenerateSchedules(AwardReportTerm awardReportTerm, boolean isThisNotPaymentPanel) {
        return isThisNotPaymentPanel || StringUtils.equalsIgnoreCase(awardReportTerm.getReportClassCode(), getParameterService().getParameterValueAsString(AwardDocument.class, KeyConstants.REPORT_CLASS_FOR_PAYMENTS_AND_INVOICES));
    }    
    
    
    
    /**
     * 
     * This method determines and returns the start date based on the frequency base code.
     * 
     * @param awardReportTerm
     * @return
     */
    protected Date getStartDate(AwardReportTerm awardReportTerm, Map<String, java.util.Date> mapOfDates){
        Calendar calendar = new GregorianCalendar();
        Date date = null;
        boolean startDateIsNull = false;
        
        if(mapOfDates.containsKey(awardReportTerm.getFrequencyBaseCode()) && mapOfDates.get(awardReportTerm.getFrequencyBaseCode())!=null){            
            calendar.setTime(mapOfDates.get(awardReportTerm.getFrequencyBaseCode()));            
        }else if(awardReportTerm.getDueDate()!=null){
            calendar.setTimeInMillis(awardReportTerm.getDueDate().getTime());
        }else{
            startDateIsNull = true;
        }
        
        if (!startDateIsNull && awardReportTerm.getFrequency() != null) {
            date = getStartDateFromTheBaseDate(calendar, awardReportTerm.getFrequency()); 
        }
        
        return date;
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
        if (frequency != null) {
            if(frequency.getNumberOfDays()!=null) {
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
    protected Date getEndDate(String frequencyBaseCode, Date startDate, Map<String, java.util.Date> mapOfDates){
        Calendar calendar = new GregorianCalendar();
        
        if(frequencyBaseCode.equals(FrequencyBaseConstants.FINAL_EXPIRATION_DATE.getfrequencyBase())){
            calendar.setTime(startDate);   
            calendar.add(Calendar.YEAR, Integer.parseInt(this.getParameterService().getParameterValueAsString(AwardDocument.class
                    ,KeyConstants.PERIOD_IN_YEARS_WHEN_FREQUENCY_BASE_IS_FINAL_EXPIRATION_DATE)));            
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
     * Gets the ParameterService attribute. 
     * @return Returns the ParameterService.
     */
    protected ParameterService getParameterService() {
        return this.parameterService;
    }

    /**
     * Sets the ParameterService attribute value.
     * @param parameterService The ParameterService to set.
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    /**
     * Gets the awardAmountInfoService attribute. 
     * @return Returns the awardAmountInfoService.
     */
    public AwardAmountInfoService getAwardAmountInfoService() {
        return awardAmountInfoService;
    }

    /**
     * Sets the awardAmountInfoService attribute value.
     * @param awardAmountInfoService The awardAmountInfoService to set.
     */
    public void setAwardAmountInfoService(AwardAmountInfoService awardAmountInfoService) {
        this.awardAmountInfoService = awardAmountInfoService;
    }
}

    
