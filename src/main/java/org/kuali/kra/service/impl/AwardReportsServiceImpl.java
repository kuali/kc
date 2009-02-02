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
import java.util.List;

import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.award.bo.AwardReportTerm;
import org.kuali.kra.award.bo.AwardReportTermRecipient;
import org.kuali.kra.award.lookup.keyvalue.FrequencyBaseCodeValuesFinder;
import org.kuali.kra.award.lookup.keyvalue.FrequencyCodeValuesFinder;
import org.kuali.kra.award.lookup.keyvalue.ReportClassValuesFinder;
import org.kuali.kra.award.web.struts.form.AwardForm;
import org.kuali.kra.service.AwardReportsService;

/**
 * 
 * This is the implementation of <code>AwardReportsService</code> interface.
 */
public class AwardReportsServiceImpl implements AwardReportsService {
    
    private static final String SEMICOLON_AS_DELIMITOR = ";";
    private static final String COMA_AS_DELIMITOR = ",";
    
    /**
     * 
     * @see org.kuali.kra.service.AwardReportsService#doPreparations(
     * org.kuali.kra.award.web.struts.form.AwardForm)
     */
    public void doPreparations(AwardForm awardForm){
        
        assignReportClassesToAwardFormForPanelHeaderDisplay(awardForm);        
        addEmptyNewAwardReportTermRecipients(awardForm);
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
        for(int i=0;i<reportClasses.size();i++){
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
        for(int i=0;i<awardForm.getAwardDocument().getAward().getAwardReportTerms().size();i++){
            awardForm.getNewAwardReportTermRecipient().add(new AwardReportTermRecipient());
        }
    }
    
    /**
     * 
     * @see org.kuali.kra.service.AwardReportsService#getFrequencyCodes(java.lang.String, java.lang.String)
     */
    public String getFrequencyCodes(String reportClassCode, String reportCode){        
        FrequencyCodeValuesFinder frequencyCodeValuesFinder = new FrequencyCodeValuesFinder();
                
        frequencyCodeValuesFinder.setReportClassCode(reportClassCode);
        frequencyCodeValuesFinder.setReportCode(reportCode);
        
        List<KeyLabelPair> frequencyCodes = frequencyCodeValuesFinder.getKeyValues();
                
        return processKeyLabelPairList(frequencyCodes);
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
        int i = 1;
        StringBuffer strBuffer = new StringBuffer();
        for(KeyLabelPair keyLabelPair : keyLabelPairList){
            strBuffer.append(keyLabelPair.key);
            strBuffer.append(SEMICOLON_AS_DELIMITOR);
            strBuffer.append(keyLabelPair.label);
            if(i!=keyLabelPairList.size()){
                strBuffer.append(COMA_AS_DELIMITOR);    
            }
            i++;
        }
        return strBuffer.toString();
    }
    
    
    /**
     * 
     * @see org.kuali.kra.service.AwardReportsService#getFrequencyBaseCodes(java.lang.String)
     */
    public String getFrequencyBaseCodes(String frequencyCode){        
        FrequencyBaseCodeValuesFinder frequencyBaseCodeValuesFinder 
            = new FrequencyBaseCodeValuesFinder();        
        
        frequencyBaseCodeValuesFinder.setFrequencyCode(frequencyCode);
        
        List<KeyLabelPair> frequencyBaseCodes = frequencyBaseCodeValuesFinder.getKeyValues();
        return processKeyLabelPairList(frequencyBaseCodes);
        
    }

}
