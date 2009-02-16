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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.bo.AwardReportTerm;
import org.kuali.kra.award.bo.AwardReportTermRecipient;
import org.kuali.kra.award.bo.ReportClass;
import org.kuali.kra.award.lookup.keyvalue.FrequencyBaseCodeValuesFinder;
import org.kuali.kra.award.lookup.keyvalue.FrequencyCodeValuesFinder;
import org.kuali.kra.award.lookup.keyvalue.ReportClassValuesFinder;
import org.kuali.kra.award.web.struts.form.AwardForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.service.AwardReportsService;

/**
 * 
 * This is the implementation of <code>AwardReportsService</code> interface.
 */
public class AwardReportsServiceImpl implements AwardReportsService {
    
    private static final String SEMICOLON_AS_DELIMITOR = ";";
    private static final String COMA_AS_DELIMITOR = ",";
    
    KualiConfigurationService kualiConfigurationService;
    BusinessObjectService businessObjectService;
    
    public HashMap doPreps(Award award){
        HashMap a = new HashMap();
        ReportClassValuesFinder reportClassValuesFinder = new ReportClassValuesFinder();
        List<KeyLabelPair> reportClasses = new ArrayList<KeyLabelPair>();
        List<AwardReportTerm> newAwardReportTerm = new ArrayList<AwardReportTerm>();
        reportClasses = reportClassValuesFinder.getKeyValues();
        
        a.put("reportClasses",  reportClasses);
        
        for(int i=0;i<reportClasses.size();i++){
            newAwardReportTerm.add(new AwardReportTerm());
        }
        a.put("newAwardReportTermList",  newAwardReportTerm);
        
        List<AwardReportTermRecipient> newAwardReportTermRecipients = new ArrayList<AwardReportTermRecipient>();
        for(int i=0;i<award.getAwardReportTerms().size();i++){
            newAwardReportTermRecipients.add(new AwardReportTermRecipient());
        }
        a.put("newAwardReportTermRecipientsList",  newAwardReportTermRecipients);
        
        return a;
    }
    /**
     * 
     * @see org.kuali.kra.service.AwardReportsService#doPreparations(
     * org.kuali.kra.award.web.struts.form.AwardForm)
     */
    public void doPreparations(AwardForm awardForm){
        
        assignReportClassesToAwardFormForPanelHeaderDisplay(awardForm);        
        addEmptyNewAwardReportTermRecipients(awardForm);
        setReportClassInFormForPaymentsAndInvoicesSubPanel(awardForm);
    }
    
    
    /**
     * 
     * This method fetches the reportClass object from the database using system paramter as the primary
     * key and sets it in the awardForm. Its used in the jsp/tag files to populate the report class panel
     * under Payments and Invoices panel.
     * 
     * @param awardForm
     */
    protected void setReportClassInFormForPaymentsAndInvoicesSubPanel(AwardForm awardForm){
        Map<String, String> primaryKeyField = new HashMap<String, String>();
        
        primaryKeyField.put("reportClassCode",kualiConfigurationService.getParameter(Constants
                .PARAMETER_MODULE_AWARD,Constants.PARAMETER_COMPONENT_DOCUMENT
                ,KeyConstants.MIT_IDC_VALIDATION_ENABLED).getParameterValue());        
        
        awardForm.setReportClassForPaymentsAndInvoices((ReportClass) businessObjectService.findByPrimaryKey(
                ReportClass.class, primaryKeyField));
    }
    
    /**
     * 
     * This method adds an empty AwardReportTerm object to awardForm.newAwardReportTerm
     * list for every report class.
     * 
     * @param awardForm
     */
    protected void assignReportClassesToAwardFormForPanelHeaderDisplay(AwardForm awardForm){
        ReportClassValuesFinder reportClassValuesFinder = new ReportClassValuesFinder();
        List<KeyLabelPair> reportClasses = new ArrayList<KeyLabelPair>();
        
        reportClasses = reportClassValuesFinder.getKeyValues();
        awardForm.setReportClasses(reportClasses);
        
        addEmptyNewAwardReportTerms(awardForm, reportClasses);                
    }
    
    /**
     * 
     * This method is a helper method for assignReportClassesToAwardFormForPanelHeaderDisplay
     * 
     * @param awardForm
     * @param reportClasses
     */
    protected void addEmptyNewAwardReportTerms(AwardForm awardForm, List<KeyLabelPair> reportClasses){
        for(KeyLabelPair keyLabelPair:reportClasses){
            awardForm.getNewAwardReportTerm().add(new AwardReportTerm());
        }
    }
    
    /**
     * 
     * This method adds an empty AwardReportTermRecipient object to 
     * AwardForm.newAwardReportTermRecipient list for every AwardReportTerm object present
     * in Award.
     * @param awardForm
     */
    protected void addEmptyNewAwardReportTermRecipients(AwardForm awardForm){
        for(AwardReportTerm awardReportTerm:awardForm.getAwardDocument().getAward().getAwardReportTerms()){
            awardForm.getNewAwardReportTermRecipient().add(new AwardReportTermRecipient());
        }
    }
    
    /**
     * 
     * @see org.kuali.kra.service.AwardReportsService#getFrequencyCodes(java.lang.String, java.lang.String)
     */
    public String getFrequencyCodes(String reportClassCode, String reportCode){        
        FrequencyCodeValuesFinder frequencyCodeValuesFinder 
            = new FrequencyCodeValuesFinder(reportClassCode, reportCode);
                
        return processKeyLabelPairList(frequencyCodeValuesFinder.getKeyValues());
    }
    
    /**
     * 
     * This method processes a list of KeyLabelPair and converts them to a string separated
     * by semi-colons and comas.
     * This is used in both getFrequencyCodes and getFrequencyBaseCodes services.
     *  
     * @param keyLabelPairList
     * @return
     */
    public String processKeyLabelPairList(List<KeyLabelPair> keyLabelPairList){
        
        StringBuilder strBuilder = new StringBuilder();
        
        for(int i=0;i<keyLabelPairList.size()-1; i++){
            strBuilder.append(keyLabelPairList.get(i).key);
            strBuilder.append(SEMICOLON_AS_DELIMITOR);
            strBuilder.append(keyLabelPairList.get(i).label);
            strBuilder.append(COMA_AS_DELIMITOR);
        }
        
        strBuilder.append(keyLabelPairList.get(keyLabelPairList.size()-1).key);
        strBuilder.append(SEMICOLON_AS_DELIMITOR);
        strBuilder.append(keyLabelPairList.get(keyLabelPairList.size()-1).label);
        
        return strBuilder.toString();
    }
    
    
    /**
     * 
     * @see org.kuali.kra.service.AwardReportsService#getFrequencyBaseCodes(java.lang.String)
     */
    public String getFrequencyBaseCodes(String frequencyCode){        
        FrequencyBaseCodeValuesFinder frequencyBaseCodeValuesFinder 
            = new FrequencyBaseCodeValuesFinder(frequencyCode);        
        
        return processKeyLabelPairList(frequencyBaseCodeValuesFinder.getKeyValues());
        
    }

    public void setKualiConfigurationService(KualiConfigurationService kualiConfigurationService) {
        this.kualiConfigurationService = kualiConfigurationService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}
