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
package org.kuali.kra.award.paymentreports.closeout;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.paymentreports.Frequency;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.PersistenceService;

/**
 * 
 * This class implements the AwardCloseoutService.
 * 
 */
public class AwardCloseoutServiceImpl implements AwardCloseoutService {
    
    /*
     * Used for the refreshing Report object on AwardReportTerm object.
     */
    private static final String REPORT_OBJECT = "report";
    /*
     * Used for the refreshing Frequency object on AwardReportTerm object.
     */
    private static final String FREQUENCY_OBJECT = "frequency";
    /*
     * Represents that the closeout report due date should be set as multiple.
     */
    private static final String MULTIPLE = "M";
    
    private PersistenceService persistenceService;
    private DateTimeService dateTimeService;

    /**
     *
     *
     * @see org.kuali.kra.award.paymentreports.closeout.AwardCloseoutService#updateCloseoutDueDatesBeforeSave(Award)
     */
    public void updateCloseoutDueDatesBeforeSaveOrg(Award award) {
        Map<String, Object> closeoutDueDates = new HashMap<String, Object>();                
        Date finalExpirationDate = award.getAwardAmountInfos().get(0).getFinalExpirationDate();
        java.util.Date dateCalculatedUsingFrequency;
        java.util.Date dateCalculatedUsingFrequencyOld;
        boolean allDueDatesAreEqual;
        String closeoutReportTypeCode;
        
        //java.util.Date dateCalculatedUsingFinalInvoiceDue = getDateCalculatedUsingFinalInvoiceDue(award, finalExpirationDate).getTime();
        
        refreshAwardReportTerms(award.getAwardReportTermItems());
                
        for (KeyValue kl : (new CloseoutReportTypeValuesFinder()).getKeyValues()) {
            closeoutReportTypeCode = kl.getKey().toString();
            allDueDatesAreEqual = true;
            dateCalculatedUsingFrequency = null;
            dateCalculatedUsingFrequencyOld = null;
            List<AwardReportTerm> awardReportTerms = filterAwardReportTerms(award.getAwardReportTermItems(), closeoutReportTypeCode);
            if (awardReportTerms.size() == 0) {
                closeoutDueDates.put(closeoutReportTypeCode, null);
            } else {
                Calendar calendar = getDateTimeService().getCalendar(finalExpirationDate);
                for (AwardReportTerm awardReportTerm : awardReportTerms) {
                    if (StringUtils.isNotBlank(awardReportTerm.getFrequencyCode())) {
                        dateCalculatedUsingFrequency = getCalculatedDueDate(finalExpirationDate, awardReportTerm, calendar);
                        if (dateCalculatedUsingFrequencyOld != null
                                && !dateCalculatedUsingFrequencyOld.equals(dateCalculatedUsingFrequency)) {
                                //&& dateCalculatedUsingFrequencyOld != dateCalculatedUsingFrequency) {
                            allDueDatesAreEqual = false;
                            break;
                        }
                        dateCalculatedUsingFrequencyOld = dateCalculatedUsingFrequency;
                    }
                }
                if (dateCalculatedUsingFrequency != null) {                    
                    updateCloseoutDueDate(closeoutDueDates, dateCalculatedUsingFrequency, allDueDatesAreEqual, closeoutReportTypeCode);
                }
            }
        }
        
        assignedDueDatesOnAwardCloseouts(award.getAwardCloseoutItems(), closeoutDueDates);

    }
    
    /**
     * 
     * This method updates the due dates for Award Closeout static reports based on allDueDatesAreEqual flag and 
     * by comparing the dateCalculatedUsingFinalInvoiceDue with dateCalculatedUsingFrequency.
     * @param closeoutDueDates
     * @param dateCalculatedUsingFrequency
     * @param allDueDatesAreEqual
     * @param closeoutReportTypeCode
     */
    protected void updateCloseoutDueDate(Map<String, Object> closeoutDueDates, 
            java.util.Date dateCalculatedUsingFrequency, boolean allDueDatesAreEqual, String closeoutReportTypeCode) {
        if (allDueDatesAreEqual) {
            closeoutDueDates.put(closeoutReportTypeCode, new Date(dateCalculatedUsingFrequency.getTime()));
        } else {
            closeoutDueDates.put(closeoutReportTypeCode, MULTIPLE);
        }
    }

    /* 
     * This method calculates and returns a date by adding frequency to finalExpirationDate.
     *
     */
    protected java.util.Date getCalculatedDueDate(Date finalExpirationDate, AwardReportTerm awardReportTerm, Calendar calendar) {
        Frequency frequency = awardReportTerm.getFrequency();
        if(frequency != null) {
            if (frequency.getNumberOfDays() != null) {
                calendar.add(Calendar.DAY_OF_YEAR, frequency.getNumberOfDays());
            }
            if (frequency.getNumberOfMonths() != null) {
                calendar.add(Calendar.MONTH, frequency.getNumberOfMonths());    
            }
            if(frequency.getAdvanceNumberOfDays()!=null){
                calendar.add(Calendar.DAY_OF_YEAR, -frequency.getAdvanceNumberOfDays());
            }
            if(frequency.getAdvanceNumberOfMonths()!=null){
                calendar.add(Calendar.MONTH, -frequency.getAdvanceNumberOfMonths());
            }    
        }
        return calendar.getTime();
    }
    
    /**
     * 
     * This method updates the dueDates on AwardCloseout static reports.
     * @param awardCloseoutItems
     * @param closeoutDueDates
     */
    protected void assignedDueDatesOnAwardCloseouts(List<AwardCloseout> awardCloseoutItems, Map<String, Object> closeoutDueDates) {
        for (AwardCloseout awardCloseout : awardCloseoutItems) {            
            if (closeoutDueDates.containsKey(awardCloseout.getCloseoutReportCode())) {
                if (closeoutDueDates.get(awardCloseout.getCloseoutReportCode()) instanceof Date) {
                    awardCloseout.setDueDate((Date) closeoutDueDates.get(awardCloseout.getCloseoutReportCode()));
                    awardCloseout.setMultiple(false);
                } else if (closeoutDueDates.get(awardCloseout.getCloseoutReportCode()) == null) {
                    awardCloseout.setDueDate(null);
                    awardCloseout.setMultiple(false);
                } else if (StringUtils.equalsIgnoreCase(MULTIPLE,(String) closeoutDueDates.get(awardCloseout.getCloseoutReportCode()))) {
                    awardCloseout.setMultiple(true);
                }
            }
        }
    }
    
    /* 
     * This method filters the awardReportTerm objects based on reportClassCode.
     *
     */
    protected List<AwardReportTerm> filterAwardReportTerms(List<AwardReportTerm> awardReportTermItems, String reportClassCode){
        List<AwardReportTerm> filteredAwardReportTerms = new ArrayList<AwardReportTerm>();
        
        for(AwardReportTerm awardReportTerm : awardReportTermItems){
            if(StringUtils.equalsIgnoreCase(awardReportTerm.getReportClassCode(), reportClassCode) && awardReportTerm.getReport().getFinalReportFlag()){
                filteredAwardReportTerms.add(awardReportTerm);
            }
        }
        
        return filteredAwardReportTerms;
    }
    
    /*
     * This method does a refreshReference object on all of awardReportTerm objects in one single transaction.
     *  
     * @param awardReportTerms
     */
    protected void refreshAwardReportTerms(List<AwardReportTerm> awardReportTerms) {
        List<AwardReportTerm> persistableObjects = new ArrayList<AwardReportTerm>();
        List<String> referenceObjectNames = new ArrayList<String>();
        List<String> referenceObjectNames1 = new ArrayList<String>();
        
        for (AwardReportTerm awardReportTerm : awardReportTerms) {
            persistableObjects.add(awardReportTerm);
            referenceObjectNames.add(REPORT_OBJECT);
            referenceObjectNames1.add(FREQUENCY_OBJECT);
        }
        
        if (persistableObjects.size() > 0 && referenceObjectNames.size() > 0) {
            getPersistenceService().retrieveReferenceObjects(persistableObjects, referenceObjectNames);
        }
        if (persistableObjects.size() > 0 && referenceObjectNames1.size() > 0) {
            getPersistenceService().retrieveReferenceObjects(persistableObjects, referenceObjectNames1);
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
     * Gets the dateTimeService attribute. 
     * @return Returns the dateTimeService.
     */
    public DateTimeService getDateTimeService() {
        return dateTimeService;
    }

    /**
     * Sets the dateTimeService attribute value.
     * @param dateTimeService The dateTimeService to set.
     */
    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }
    
    public void updateCloseoutDueDatesBeforeSave(Award award) {
        Map<String, Object> closeoutDueDates = new HashMap<String, Object>();                
        Date finalExpirationDate = award.getAwardAmountInfos().get(award.getIndexOfLastAwardAmountInfo()).getFinalExpirationDate();
        java.util.Date dateCalculatedUsingFrequency;
        //java.util.Date dateCalculatedUsingFrequencyOld;
        boolean allDueDatesAreEqual;
        String closeoutReportTypeCode;
        
        //java.util.Date dateCalculatedUsingFinalInvoiceDue = getDateCalculatedUsingFinalInvoiceDue(award, finalExpirationDate).getTime();
        
        refreshAwardReportTerms(award.getAwardReportTermItems());
                
        for (KeyValue kl : (new CloseoutReportTypeValuesFinder()).getKeyValues()) {
            closeoutReportTypeCode = kl.getKey().toString();
            allDueDatesAreEqual = true;
            dateCalculatedUsingFrequency = null;
            //dateCalculatedUsingFrequencyOld = null;
            List<AwardReportTerm> awardReportTerms = filterAwardReportTerms(award.getAwardReportTermItems(), closeoutReportTypeCode);
            if (awardReportTerms.size() == 0) {
                closeoutDueDates.put(closeoutReportTypeCode, null);
            } else {
                Calendar calendar = getDateTimeService().getCalendar(finalExpirationDate);
                java.util.Date dueDate = null;
                for (AwardReportTerm awardReportTerm : awardReportTerms) {
                    dateCalculatedUsingFrequency = getCloseoutDueDate(finalExpirationDate, awardReportTerm, calendar);  
                    if (dueDate != null && !dueDate.equals(dateCalculatedUsingFrequency)) {
                        allDueDatesAreEqual = false;
                        break;
                    }
                    dueDate = dateCalculatedUsingFrequency;
                }
                if (dateCalculatedUsingFrequency != null) {                    
                    updateCloseoutDueDate(closeoutDueDates, dateCalculatedUsingFrequency, allDueDatesAreEqual, closeoutReportTypeCode);
                }
            }
        }
        
        assignedDueDatesOnAwardCloseouts(award.getAwardCloseoutItems(), closeoutDueDates);

    }

    protected java.util.Date getCloseoutDueDate(Date finalExpirationDate, AwardReportTerm awardReportTerm, Calendar calendar) {
        java.util.Date dueDate = calendar.getTime();
        if (awardReportTerm.getDueDate() != null) {
            dueDate =  awardReportTerm.getDueDate();
        } else if (awardReportTerm.getFrequency() != null 
                    && (awardReportTerm.getFrequency().getNumberOfMonths() != null
                        || awardReportTerm.getFrequency().getNumberOfDays() != null 
                        || awardReportTerm.getFrequency().getAdvanceNumberOfMonths() != null
                        || awardReportTerm.getFrequency().getAdvanceNumberOfDays() != null)) {
            // don't want to change calendar's value
            dueDate = getCalculatedDueDate(finalExpirationDate, awardReportTerm, (Calendar)calendar.clone());
        }
        
        return dueDate;
    }

}


