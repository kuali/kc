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
package org.kuali.kra.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.lookup.keyvalue.FrequencyBaseCodeValuesFinder;
import org.kuali.kra.award.lookup.keyvalue.FrequencyCodeValuesFinder;
import org.kuali.kra.award.lookup.keyvalue.ReportClassValuesFinder;
import org.kuali.kra.award.paymentreports.ReportClass;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRecipient;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.service.AwardReportsService;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * This is the implementation of <code>AwardReportsService</code> interface.
 */
@Transactional
public class AwardReportsServiceImpl implements AwardReportsService {
    protected static final String REPORT_CLASS_CODE_FIELD = "reportClassCode";
    
    private static final String SEMICOLON_AS_DELIMITOR = ";";
    private static final String COMMA_AS_DELIMITOR = ",";
    
    private ParameterService parameterService;
    private BusinessObjectService businessObjectService;
    
    
    /**
     * 
     * @see org.kuali.kra.service.AwardReportsService#initializeObjectsForReportsAndPayments(
     * org.kuali.kra.award.home.Award)
     */
    public Map<String, Object> initializeObjectsForReportsAndPayments(Award award){
        
        Map<String, Object> initializedObjects = new HashMap<String, Object>();
        
        assignReportClassesForPanelHeaderDisplay(initializedObjects);
        
        addEmptyNewAwardReportTermRecipients(award, initializedObjects);
        
        setReportClassForPaymentsAndInvoicesSubPanel(initializedObjects);
        
        return initializedObjects;
    }
    
    /**
     * 
     * This method fetches the reportClass object from the database using system paramter as the primary
     * key and puts it in the map and returns it. 
     * It is used in the jsp/tag files to populate the report class panel under Payments and Invoices panel.
     * 
     * @param hashMap
     * @return
     */
    protected void setReportClassForPaymentsAndInvoicesSubPanel(
            Map<String, Object> hashMap){        
        Map<String, String> primaryKeyField = new HashMap<String, String>();
        
        primaryKeyField.put(REPORT_CLASS_CODE_FIELD, this.getParameterService().getParameterValueAsString(AwardDocument.class
                ,KeyConstants.REPORT_CLASS_FOR_PAYMENTS_AND_INVOICES));        
        
        hashMap.put(Constants.REPORT_CLASS_FOR_PAYMENTS_AND_INVOICES_PANEL, 
                (ReportClass) getBusinessObjectService().findByPrimaryKey(ReportClass.class, primaryKeyField));
    }

    /**
     * 
     * This method puts an empty AwardReportTerm object to an empty 
     * list for every report class and puts it in a hashmap.
     * 
     * @param hashMap
     * @return
     */
    protected void assignReportClassesForPanelHeaderDisplay(
            Map<String, Object> hashMap){
        
        ReportClassValuesFinder reportClassValuesFinder = getReportClassValuesFinder();
        List<KeyValue> reportClasses = new ArrayList<KeyValue>();
        
        reportClasses = reportClassValuesFinder.getKeyValues();
        
        hashMap.put(Constants.REPORT_CLASSES_KEY_FOR_INITIALIZE_OBJECTS,  reportClasses);
        
        addEmptyNewAwardReportTerms(hashMap, reportClasses);
    }
    
    /**
     * 
     * This method is a helper method for assignReportClassesForPanelHeaderDisplay
     * 
     * @param hashMap
     * @param reportClasses
     * @return
     */
    @SuppressWarnings("all")    
    protected void addEmptyNewAwardReportTerms(
            Map<String, Object> hashMap, List<KeyValue> reportClasses){
        List<AwardReportTerm> newAwardReportTerms = new ArrayList<AwardReportTerm>();
        for(KeyValue KeyValue : reportClasses){
            newAwardReportTerms.add(new AwardReportTerm());
        }
        hashMap.put(
                Constants.NEW_AWARD_REPORT_TERMS_LIST_KEY_FOR_INITIALIZE_OBJECTS,  newAwardReportTerms);
    }
    
   /**
    * 
     * This method adds an empty AwardReportTermRecipient object to 
     * an empty list for every AwardReportTerm object present in Award
     * and puts the list in a hashmap
     * 
    * @param award
    * @param hashMap
    * @return
    */
    @SuppressWarnings("all")
    protected void addEmptyNewAwardReportTermRecipients(
            Award award, Map<String, Object> hashMap){
        
        List<AwardReportTermRecipient> newAwardReportTermRecipients 
            = new ArrayList<AwardReportTermRecipient>();        
        
        for(AwardReportTerm awardReportTerm : award.getAwardReportTermItems()){    
            newAwardReportTermRecipients.add(new AwardReportTermRecipient());
        }
        
        hashMap.put(Constants.NEW_AWARD_REPORT_TERM_RECIPIENTS_LIST_KEY_FOR_INITIALIZE_OBJECTS
                ,  newAwardReportTermRecipients);
        
    }
    
    /**
     * 
     * @see org.kuali.kra.service.AwardReportsService#getFrequencyCodes(java.lang.String, java.lang.String)
     */
    public String getFrequencyCodes(String reportClassCode, String reportCode){        
        
        FrequencyCodeValuesFinder frequencyCodeValuesFinder = getFrequencyCodeValuesFinder(reportClassCode, reportCode);
                
        return processKeyValueList(frequencyCodeValuesFinder.getKeyValues());
    }
    
    /**
     * 
     * This method processes a list of KeyValue and converts them to a string separated
     * by semi-colons and comas.
     * This is used in both getFrequencyCodes and getFrequencyBaseCodes services.
     *  
     * @param KeyValueList
     * @return
     */
    protected String processKeyValueList(List<KeyValue> KeyValueList){
        
        StringBuilder strBuilder = new StringBuilder();
        
        int lastElementIndex = KeyValueList.size()-1;
        
        for(int i = 0; i < lastElementIndex; i++){
            strBuilder.append(KeyValueList.get(i).getKey());
            strBuilder.append(SEMICOLON_AS_DELIMITOR);
            strBuilder.append(KeyValueList.get(i).getValue());
            strBuilder.append(COMMA_AS_DELIMITOR);
        }
        
        strBuilder.append(KeyValueList.get(lastElementIndex).getKey());
        strBuilder.append(SEMICOLON_AS_DELIMITOR);
        strBuilder.append(KeyValueList.get(lastElementIndex).getValue());
        
        return strBuilder.toString();
    }
    
    
    /**
     * 
     * @see org.kuali.kra.service.AwardReportsService#getFrequencyBaseCodes(java.lang.String)
     */
    public String getFrequencyBaseCodes(String frequencyCode){        
        FrequencyBaseCodeValuesFinder frequencyBaseCodeValuesFinder = getFrequencyBaseCodeValuesFinder(frequencyCode);
            
        return processKeyValueList(frequencyBaseCodeValuesFinder.getKeyValues());
        
    }

    /**
     * Sets the ParameterService.
     * @param parameterService the parameter service. 
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    
    /**
     * 
     * This method...
     * @return
     */
    protected ParameterService getParameterService(){
        return this.parameterService;
    }

    /**
     * 
     * This method...
     * @param businessObjectService
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * 
     * This method...
     * @return
     */
    protected BusinessObjectService getBusinessObjectService(){
        return businessObjectService;
    }
    
    /**
     * 
     * This method returns a new instance of FrequencyCodeValuesFinder which can be overriden in unit test to test the service method only.
     * @param reportClassCode
     * @param reportCode
     * @return
     */
    protected FrequencyCodeValuesFinder getFrequencyCodeValuesFinder(String reportClassCode, String reportCode){
        return new FrequencyCodeValuesFinder(reportClassCode, reportCode);
    }
    
    /**
     * 
     * This method returns a new instance of FrequencyBaseCodeValuesFinder which can be overriden in unit test to test the service method only.
     * @param frequencyCode
     * @return
     */
    protected FrequencyBaseCodeValuesFinder getFrequencyBaseCodeValuesFinder(String frequencyCode){
        return new FrequencyBaseCodeValuesFinder(frequencyCode);
    }
    
    /**
     * 
     * This method returns a new instance of ReportClassValuesFinder which can be overriden in unit test to test the service method only. 
     * @return
     */
    protected ReportClassValuesFinder getReportClassValuesFinder() {
        return new ReportClassValuesFinder();
    }

}
