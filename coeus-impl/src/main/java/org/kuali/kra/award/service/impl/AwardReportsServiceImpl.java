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

import org.kuali.coeus.sys.framework.util.ValuesFinderUtils;
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
import org.kuali.kra.award.service.AwardReportsService;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * This is the implementation of <code>AwardReportsService</code> interface.
 */
@Transactional
public class AwardReportsServiceImpl implements AwardReportsService {
    protected static final String REPORT_CLASS_CODE_FIELD = "reportClassCode";

    private ParameterService parameterService;
    private BusinessObjectService businessObjectService;
    
    
    /**
     * 
     * @see org.kuali.kra.award.service.AwardReportsService#initializeObjectsForReportsAndPayments(
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
    
    @Override
    public String getFrequencyCodes(String reportClassCode, String reportCode){        
        
        FrequencyCodeValuesFinder frequencyCodeValuesFinder = getFrequencyCodeValuesFinder(reportClassCode, reportCode);
                
        return ValuesFinderUtils.processKeyValueList(frequencyCodeValuesFinder.getKeyValues());
    }
    
    
    
    
    @Override
    public String getFrequencyBaseCodes(String frequencyCode){        
        FrequencyBaseCodeValuesFinder frequencyBaseCodeValuesFinder = getFrequencyBaseCodeValuesFinder(frequencyCode);
            
        return ValuesFinderUtils.processKeyValueList(frequencyBaseCodeValuesFinder.getKeyValues());
        
    }

    /**
     * Sets the ParameterService.
     * @param parameterService the parameter service. 
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    protected ParameterService getParameterService(){
        return this.parameterService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

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
