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
package org.kuali.kra.award.paymentreports.closeout;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.award.web.struts.form.AwardForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.service.DateTimeService;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.service.KualiRuleService;
import org.kuali.rice.kns.service.PersistenceService;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

/**
 * This class supports the AwardForm class.
 */
public class AwardCloseoutBean implements Serializable {    
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 7888151034323714279L;
    
    /**
     * Used for the refreshing Report object on AwardReportTerm object.
     */
    private static final String REPORT_OBJECT = "report";
    /**
     * Used for the refreshing Frequency object on AwardReportTerm object.
     */
    private static final String FREQUENCY_OBJECT = "frequency";
    /**
     * Represents that the closeout report due date should be set as multiple.
     */
    private static final String MULTIPLE = "M";
    
    private AwardCloseout newAwardCloseout;
    private KualiRuleService ruleService;
    private AwardForm form;
    private String closeoutReportTypeUserDefined;
    private String closeoutReportTypeFinancialReport;
    private String closeoutReportTypePatent;
    private String closeoutReportTypeTechnical;
    private String closeoutReportTypeProperty;
   
    /**
     * Constructs an AwardCloseoutBean.
     * @param parent
     */
    public AwardCloseoutBean(AwardForm form) {
        this.form = form;
    }
    
    /**
     * 
     * Constructs a AwardCloseoutBean.java.
     */
    public AwardCloseoutBean() {
        
    }
    
    /**
     * This method is called when adding a new Award Closeout item.
     * @param formHelper
     * @return
     */
    public boolean addAwardCloseoutItem() {
        AddAwardCloseoutRuleEvent event = generateAddEvent();
        boolean success = getRuleService().applyRules(event);
        getNewAwardCloseout().setCloseoutReportCode(this.getCloseoutReportTypeUserDefined());
        if(success){
            getAward().add(getNewAwardCloseout());
            init();
        }
        return success;
    }

    /**
     * 
     * This method deletes an award closeout item.
     * @param deletedItemIndex
     */
    public void deleteAwardCloseoutItem(int deletedItemIndex) {
        List<AwardCloseout> items = getAward().getAwardCloseoutItems();
        if (deletedItemIndex >= 0 && deletedItemIndex < items.size()) {
            items.remove(deletedItemIndex);
        }        
    }
    
    /**
     * 
     * This method adds the Award Closeout static reports. This gets called at the time of creation of Award.
     *   
     * @param keyLabelPairs
     */
    public void addAwardCloseoutStaticItems(List<KeyLabelPair> keyLabelPairs) {
        AwardCloseout awardCloseout = new AwardCloseout();
        for (KeyLabelPair keyLabelPair : keyLabelPairs) {
            awardCloseout.setCloseoutReportCode(keyLabelPair.getKey().toString());
            awardCloseout.setCloseoutReportName(keyLabelPair.getLabel());
            getAward().add(awardCloseout);
            awardCloseout = new AwardCloseout();
        }
    }
    
    /**
     * 
     * Whenever a save occurs on Payment, Reports and Terms tab; This method gets called from the action upon save 
     * and updates the due dates for award closeout static reports.
     * 
     */
    public void updateCloseoutDueDatesBeforeSave() {
        Award award = getAward();
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

    /**
     * This method checks if finalInvoiceDue is not null and adds it to the final expiration date and returns the calculated date.
     * 
     * @param award
     * @param finalExpirationDate
     * @return
     */
    private Calendar getDateCalculatedUsingFinalInvoiceDue(Award award, Date finalExpirationDate) {
        Calendar calendar = getDateTimeService().getCalendar(finalExpirationDate);        
        if (award.getFinalInvoiceDue() != null) {
            calendar.add(Calendar.DAY_OF_YEAR, award.getFinalInvoiceDue());    
        }
        return calendar;
    }
    
    /**
     * 
     * This method updates the due dates for Award Closeout static reports for the case when total number of awardReportTerm objects for the particular 
     * closeout  report type is 0.
     * 
     * @param closeoutDueDates
     * @param dateCalculatedUsingFinalInvoiceDue
     * @param closeoutReportTypeCode
     */
    protected void updateCloseoutDueDateWhenFilteredListSizeIsZero(Map<String, Object> closeoutDueDates, java.util.Date dateCalculatedUsingFinalInvoiceDue
                            , String closeoutReportTypeCode) {
        if (StringUtils.equalsIgnoreCase(getCloseoutReportTypeFinancialReport(),closeoutReportTypeCode)) {
            closeoutDueDates.put(closeoutReportTypeCode, new Date(dateCalculatedUsingFinalInvoiceDue.getTime()));   
        } else {
            closeoutDueDates.put(closeoutReportTypeCode, null);    
        }
    }

    /**
     * This method updates the due dates for Award Closeout static reports based on allDueDatesAreEqual flag and 
     * by comparing the dateCalculatedUsingFinalInvoiceDue with dateCalculatedUsingFrequency.
     * 
     * @param closeoutDueDates
     * @param dateCalculatedUsingFinalInvoiceDue
     * @param dateCalculatedUsingFrequency
     * @param allDueDatesAreEqual
     * @param closeoutReportTypeCode
     */
    protected void updateCloseoutDueDate(Map<String, Object> closeoutDueDates, java.util.Date dateCalculatedUsingFinalInvoiceDue,
            java.util.Date dateCalculatedUsingFrequency, boolean allDueDatesAreEqual, String closeoutReportTypeCode) {
        if (allDueDatesAreEqual && dateCalculatedUsingFrequency.equals(dateCalculatedUsingFinalInvoiceDue)) {
            closeoutDueDates.put(closeoutReportTypeCode, new Date(dateCalculatedUsingFinalInvoiceDue.getTime()));
        } else {
            closeoutDueDates.put(closeoutReportTypeCode, MULTIPLE);
        }
    }
    
    /**
     * 
     * This is a helper method for retrieval of DateTimeService.
     * 
     * @return
     */
    protected DateTimeService getDateTimeService() {
        return KraServiceLocator.getService(DateTimeService.class);
    }

    /**
     * 
     * This method calculates and returns a date by adding frequency to finalExpirationDate.
     * 
     * @param finalExpirationDate
     * @param awardReportTerm
     * @param calendar
     * @return
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

    /**
     * This method updates the dueDates on AwardCloseout static reports.
     *   
     * @param awardDocument
     * @param map
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
    
    /**
     * 
     * This method filters the awardReportTerm objects based on reportClassCode.
     * 
     * @param awardReportTermItems
     * @param reportClassCode
     * @return
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
    
    /**
     * 
     * This method...
     * @return
     */
    public AddAwardCloseoutRuleEvent generateAddEvent(){
        AddAwardCloseoutRuleEvent event = new AddAwardCloseoutRuleEvent(
                "awardCloseoutBean.newAwardCloseout",
                getAwardDocument(),
                getAward(),
                getNewAwardCloseout());
        return event;
    }
    
    /**
     * @return
     */
    public Award getAward() {
        return form.getAwardDocument().getAward();
    }

    /**
     * @return
     */
    public AwardDocument getAwardDocument() {
        return form.getAwardDocument();
    }
    
    /**
     * @return
     */
    public Object getData() {
        return getNewAwardCloseout();
    }
    
    /**
     * Initialize subform
     */
    public void init() {
        newAwardCloseout = new AwardCloseout(); 
    }
    
    /**
     * 
     * This is a helper method for the retrieval of KualiRuleService
     * @return
     */
    protected KualiRuleService getRuleService() {
        if(ruleService == null) {
            ruleService = (KualiRuleService) KraServiceLocator.getService(KualiRuleService.class); 
        }
        return ruleService;
    }
    
    /**
     * 
     * @param ruleService
     */
    protected void setRuleService(KualiRuleService ruleService) {
        this.ruleService = ruleService;
    }

    /**
     * Gets the newAwardCloseout attribute. 
     * @return Returns the newAwardCloseout.
     */
    public AwardCloseout getNewAwardCloseout() {
        return newAwardCloseout;
    }

    /**
     * Sets the newAwardCloseout attribute value.
     * @param newAwardCloseout The newAwardCloseout to set.
     */
    public void setNewAwardCloseout(AwardCloseout newAwardCloseout) {
        this.newAwardCloseout = newAwardCloseout;
    }

    /**
     * Gets the closeoutReportTypeUserDefined attribute. 
     * @return Returns the closeoutReportTypeUserDefined.
     */
    public String getCloseoutReportTypeUserDefined() {
        return closeoutReportTypeUserDefined;
    }

    /**
     * Sets the closeoutReportTypeUserDefined attribute value.
     * @param closeoutReportTypeUserDefined The closeoutReportTypeUserDefined to set.
     */
    public void setCloseoutReportTypeUserDefined(String closeoutReportTypeUserDefined) {
        this.closeoutReportTypeUserDefined = closeoutReportTypeUserDefined;
    }
    
    /**
     * 
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
     * 
     * This is a helper method for the retrieval of PersistenceService
     * @return
     */
    protected PersistenceService getPersistenceService() {
        return KraServiceLocator.getService(PersistenceService.class);
    }

    /**
     * Gets the closeoutReportTypeFinancialReport attribute. 
     * @return Returns the closeoutReportTypeFinancialReport.
     */
    public String getCloseoutReportTypeFinancialReport() {
        return closeoutReportTypeFinancialReport;
    }

    /**
     * Sets the closeoutReportTypeFinancialReport attribute value.
     * @param closeoutReportTypeFinancialReport The closeoutReportTypeFinancialReport to set.
     */
    public void setCloseoutReportTypeFinancialReport(String closeoutReportTypeFinancialReport) {
        this.closeoutReportTypeFinancialReport = closeoutReportTypeFinancialReport;
    }

    /**
     * Gets the closeoutReportTypePatent attribute. 
     * @return Returns the closeoutReportTypePatent.
     */
    public String getCloseoutReportTypePatent() {
        return closeoutReportTypePatent;
    }

    /**
     * Sets the closeoutReportTypePatent attribute value.
     * @param closeoutReportTypePatent The closeoutReportTypePatent to set.
     */
    public void setCloseoutReportTypePatent(String closeoutReportTypePatent) {
        this.closeoutReportTypePatent = closeoutReportTypePatent;
    }

    /**
     * Gets the closeoutReportTypeTechnical attribute. 
     * @return Returns the closeoutReportTypeTechnical.
     */
    public String getCloseoutReportTypeTechnical() {
        return closeoutReportTypeTechnical;
    }

    /**
     * Sets the closeoutReportTypeTechnical attribute value.
     * @param closeoutReportTypeTechnical The closeoutReportTypeTechnical to set.
     */
    public void setCloseoutReportTypeTechnical(String closeoutReportTypeTechnical) {
        this.closeoutReportTypeTechnical = closeoutReportTypeTechnical;
    }

    /**
     * Gets the closeoutReportTypeProperty attribute. 
     * @return Returns the closeoutReportTypeProperty.
     */
    public String getCloseoutReportTypeProperty() {
        return closeoutReportTypeProperty;
    }

    /**
     * Sets the closeoutReportTypeProperty attribute value.
     * @param closeoutReportTypeProperty The closeoutReportTypeProperty to set.
     */
    public void setCloseoutReportTypeProperty(String closeoutReportTypeProperty) {
        this.closeoutReportTypeProperty = closeoutReportTypeProperty;
    }
}
