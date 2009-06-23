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
package org.kuali.kra.award.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.award.paymentreports.closeout.AwardCloseout;
import org.kuali.kra.award.paymentreports.closeout.CloseoutReportTypeValuesFinder;
import org.kuali.kra.award.service.AwardCloseoutService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.service.DateTimeService;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.service.PersistenceService;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

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
    private KualiConfigurationService kualiConfigurationService;
    private DateTimeService dateTimeService;

    /**
     *
     *
     * @see org.kuali.kra.award.service.AwardCloseoutService#updateCloseoutDueDatesBeforeSave(Award)
     */
    public void updateCloseoutDueDatesBeforeSave(Award award) {
        Map<String, Object> closeoutDueDates = new HashMap<String, Object>();                
        Date finalExpirationDate = award.getAwardAmountInfos().get(0).getFinalExpirationDate();
        java.util.Date dateCalculatedUsingFrequency;
        java.util.Date dateCalculatedUsingFrequencyOld;
        boolean allDueDatesAreEqual;
        String closeoutReportTypeCode;
        
        java.util.Date dateCalculatedUsingFinalInvoiceDue = getDateCalculatedUsingFinalInvoiceDue(award, finalExpirationDate).getTime();
        
        refreshAwardReportTerms(award.getAwardReportTermItems());
                
        for (KeyLabelPair kl : (new CloseoutReportTypeValuesFinder()).getKeyValues()) {
            closeoutReportTypeCode = kl.getKey().toString();
            allDueDatesAreEqual = true;
            dateCalculatedUsingFrequency = null;
            dateCalculatedUsingFrequencyOld = null;
            List<AwardReportTerm> awardReportTerms = filterAwardReportTerms(award.getAwardReportTermItems(), closeoutReportTypeCode);
            if (awardReportTerms.size() == 0) {
                updateCloseoutDueDateWhenFilteredListSizeIsZero(closeoutDueDates, dateCalculatedUsingFinalInvoiceDue, closeoutReportTypeCode);
            } else {
                Calendar calendar = getDateTimeService().getCalendar(finalExpirationDate);
                for (AwardReportTerm awardReportTerm : awardReportTerms) {
                    dateCalculatedUsingFrequency = getCalculatedDueDate(finalExpirationDate, awardReportTerm, calendar);                    
                    if (dateCalculatedUsingFrequencyOld != null && dateCalculatedUsingFrequencyOld != dateCalculatedUsingFrequency) {
                        allDueDatesAreEqual = false;
                        break;
                    }
                    dateCalculatedUsingFrequencyOld = dateCalculatedUsingFrequency;
                }
                updateCloseoutDueDate(closeoutDueDates, dateCalculatedUsingFinalInvoiceDue, dateCalculatedUsingFrequency, allDueDatesAreEqual
                                        , closeoutReportTypeCode);
            }
        }
        
        assignedDueDatesOnAwardCloseouts(award.getAwardCloseoutItems(), closeoutDueDates);

    }
    
    /*
     * This method checks if finalInvoiceDue is not null and adds it to the final expiration date and returns the calculated date.
     * 
     */
    private Calendar getDateCalculatedUsingFinalInvoiceDue(Award award, Date finalExpirationDate) {
        Calendar calendar = getDateTimeService().getCalendar(finalExpirationDate);        
        if (award.getFinalInvoiceDue() != null) {
            calendar.add(Calendar.DAY_OF_YEAR, award.getFinalInvoiceDue());    
        }
        return calendar;
    }
    
    /*
     * This method updates the due dates for Award Closeout static reports for the case when total number of awardReportTerm objects for the particular 
     * closeout  report type is 0.
     *
     */
    protected void updateCloseoutDueDateWhenFilteredListSizeIsZero(Map<String, Object> closeoutDueDates, java.util.Date dateCalculatedUsingFinalInvoiceDue
                            , String closeoutReportTypeCode) {
        if (StringUtils.equalsIgnoreCase(getKualiConfigurationService().getParameter(Constants.PARAMETER_MODULE_AWARD, Constants.PARAMETER_COMPONENT_DOCUMENT
                , KeyConstants.CLOSE_OUT_REPORT_TYPE_FINANCIAL_REPORT).getParameterName(),closeoutReportTypeCode)) {
            closeoutDueDates.put(closeoutReportTypeCode, new Date(dateCalculatedUsingFinalInvoiceDue.getTime()));   
        } else {
            closeoutDueDates.put(closeoutReportTypeCode, null);    
        }
    }

    /*
     * This method updates the due dates for Award Closeout static reports based on allDueDatesAreEqual flag and 
     * by comparing the dateCalculatedUsingFinalInvoiceDue with dateCalculatedUsingFrequency.
     * 
     */
    protected void updateCloseoutDueDate(Map<String, Object> closeoutDueDates, java.util.Date dateCalculatedUsingFinalInvoiceDue,
            java.util.Date dateCalculatedUsingFrequency, boolean allDueDatesAreEqual, String closeoutReportTypeCode) {
        if (allDueDatesAreEqual && dateCalculatedUsingFrequency.equals(dateCalculatedUsingFinalInvoiceDue)) {
            closeoutDueDates.put(closeoutReportTypeCode, new Date(dateCalculatedUsingFinalInvoiceDue.getTime()));
        } else {
            closeoutDueDates.put(closeoutReportTypeCode, MULTIPLE);
        }
    }

    /* 
     * This method calculates and returns a date by adding frequency to finalExpirationDate.
     *
     */
    protected java.util.Date getCalculatedDueDate(Date finalExpirationDate, AwardReportTerm awardReportTerm, Calendar calendar) {
        if (awardReportTerm.getFrequency().getNumberOfDays() != null) {
            calendar.add(Calendar.DAY_OF_YEAR, awardReportTerm.getFrequency().getNumberOfDays());
        }
        if (awardReportTerm.getFrequency().getNumberOfMonths() != null) {
            calendar.add(Calendar.MONTH, awardReportTerm.getFrequency().getNumberOfMonths());    
        }
        
        return calendar.getTime();
    }

    /*
     * This method updates the dueDates on AwardCloseout static reports.
     *
     */
    protected void assignedDueDatesOnAwardCloseouts(List<AwardCloseout> awardCloseoutItems, Map<String, Object> map) {
        for (AwardCloseout awardCloseout : awardCloseoutItems) {
            if (map.containsKey(awardCloseout.getCloseoutReportCode())) {
                if (map.get(awardCloseout.getCloseoutReportCode()) instanceof Date) {
                    awardCloseout.setDueDate((Date) map.get(awardCloseout.getCloseoutReportCode()));
                    awardCloseout.setMultiple(false);
                } else if (StringUtils.equalsIgnoreCase(MULTIPLE,(String) map.get(awardCloseout.getCloseoutReportCode()))) {
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
    private void refreshAwardReportTerms(List<AwardReportTerm> awardReportTerms) {
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
}


