/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.award.lookup.keyvalue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.paymentreports.ValidClassReportFrequency;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.service.KeyValuesService;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

/**
 * 
 * This class is a values finder for <code>Report</code> business object.
 */
public class ReportCodeValuesFinder extends KeyValuesBase {
    
    KeyValuesService keyValuesService;
    
    private String reportClassCode;
    
    /**
     * 
     * Constructs a ReportCodeValuesFinder.java.
     */
    public ReportCodeValuesFinder(){
        
    }
    
    /**
     * 
     * Constructs a ReportCodeValuesFinder.java.
     * @param reportClassCode
     */
    public ReportCodeValuesFinder(String reportClassCode){
        this.reportClassCode=reportClassCode;
    }
    
    /**
     * Constructs the list of Reports.  Each entry
     * in the list is a &lt;key, value&gt; pair, where the "key" is the unique
     * report code and the "value" is the textual description that is viewed
     * by a user. 
     * 
     * @return the list of &lt;key, value&gt; pairs of abstract types.  The first entry
     * is always &lt;"", "select:"&gt;.
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @SuppressWarnings("all")
    public List<KeyLabelPair> getKeyValues() {
        Collection<ValidClassReportFrequency> validClassReportFrequencies =  
            (Collection<ValidClassReportFrequency>) getKeyValuesService()
                .findAll(ValidClassReportFrequency.class);        
        
        return getKeyValues(getUniqueRelevantReportClassCodes(validClassReportFrequencies));
    }
    
    public String getReportClassCode() {
        return reportClassCode;
    }

    public void setReportClassCode(String reportClassCode) {
        this.reportClassCode = reportClassCode;
    }

    protected KeyValuesService getKeyValuesService(){
        if(keyValuesService == null){
            keyValuesService = 
                (KeyValuesService) KraServiceLocator.getService("keyValuesService");
        }
        return keyValuesService;
    }
    
    /**
     * 
     * This method iterates through the validClassReportFrequency and puts the valid ones
     * in a set for another method to process. This method sets the frequency code to null to make all the 
     * objects having same report class code and report code but different frequency code to resemble same;
     * as it is not relevant here. 
     * @param validClassReportFrequencies
     * @return
     */
    protected Set<String> getUniqueRelevantReportClassCodes(
            Collection<ValidClassReportFrequency> validClassReportFrequencies){
        
        Set<String> uniqueRelevantReportClassCodes
            = new HashSet<String>();
        
        for(ValidClassReportFrequency validClassReportFrequency: validClassReportFrequencies){
            if(StringUtils.equalsIgnoreCase(
                    validClassReportFrequency.getReportClassCode(),getReportClassCode())){
                uniqueRelevantReportClassCodes.add(validClassReportFrequency.getReportCode());    
            }
        }
        
        return uniqueRelevantReportClassCodes;
    }
    
    /**
     * 
     * This method browses through set and creates the keylabelpair list from it.
     * 
     * @param uniqueValidClassReportFrequencies
     * @return
     */
    protected List<KeyLabelPair> getKeyValues(
            Set<String> uniqueValidClassReportFrequencies){
        
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        
        keyValues.add(new KeyLabelPair("","select"));
        ValidClassReportFrequency validClassReportFrequency = new ValidClassReportFrequency();
        for(String reportCode: uniqueValidClassReportFrequencies){        
            validClassReportFrequency.setReportCode(reportCode);
            validClassReportFrequency.refreshReferenceObject("report");
            keyValues.add(new KeyLabelPair(validClassReportFrequency.getReportCode().toString()
                    , validClassReportFrequency.getReport().getDescription()));            
        }
        
        return keyValues;
    }
   
}
