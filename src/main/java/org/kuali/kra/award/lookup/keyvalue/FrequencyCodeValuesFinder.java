/*
 * Copyright 2005-2010 The Kuali Foundation
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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.paymentreports.ValidClassReportFrequency;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;
import org.kuali.rice.krad.service.KeyValuesService;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * 
 * This class is a values finder for <code>Frequency</code> business object.
 */
public class FrequencyCodeValuesFinder extends KeyValuesBase {
    
    private String reportClassCode;
    private String reportCode;
    private KeyValuesService keyValuesService;
    
    /**
     * 
     * Constructs a FrequencyCodeValuesFinder.java.
     */
    public FrequencyCodeValuesFinder() {
        super();
    }
    
    /**
     * 
     * Constructs a FrequencyCodeValuesFinder.java.
     * @param reportClassCode
     * @param reportCode
     */
    public FrequencyCodeValuesFinder(String reportClassCode, String reportCode) {
        super();
        this.reportClassCode = reportClassCode;
        this.reportCode = reportCode;
    }
    
    /**
     * Constructs the list of Reports.  Each entry
     * in the list is a &lt;key, value&gt; pair, where the "key" is the unique
     * frequency code and the "value" is the textual description that is viewed
     * by a user. 
     * 
     * @return the list of &lt;key, value&gt; pairs of abstract types.  The first entry
     * is always &lt;"", "select"&gt;.
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @SuppressWarnings("all")
    public List<KeyValue> getKeyValues() {
        
        
        if (GlobalVariables.getUserSession().retrieveObject("awfreqr"+getReportClassCode()+"c"+getReportCode()) != null) {
            return (List<KeyValue>)GlobalVariables.getUserSession().retrieveObject("awfreqr"+getReportClassCode()+"c"+getReportCode());
        } else {

            Collection<ValidClassReportFrequency> validClassReportFrequencies 
            = (Collection<ValidClassReportFrequency>) getKeyValuesService().findAll(
                    ValidClassReportFrequency.class);
            return getKeyValues((Set<String>) 
                    getUniqueRelevantFrequencyCodes(validClassReportFrequencies));
        }
    }
    
    /**
     * 
     * This method...
     * @return
     */
    public String getReportClassCode() {
        return reportClassCode;
    }

    /**
     * 
     * This method...
     * @param reportClassCode
     */
    public void setReportClassCode(String reportClassCode) {
        this.reportClassCode = reportClassCode;
    }

    /**
     * 
     * This method...
     * @return
     */
    public String getReportCode() {
        return reportCode;
    }

    /**
     * 
     * This method...
     * @param reportCode
     */
    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }    
    
    /**
     * 
     * Wrapper method for retrieval of KeyValuesService
     * @return
     */
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
     * in a set for another method to process. 
     * 
     * @param validClassReportFrequencies
     * @return
     */
    protected Set<String> getUniqueRelevantFrequencyCodes(
            Collection<ValidClassReportFrequency> validClassReportFrequencies){
        
        Set<String> uniqueRelevantFrequencyCodes
            = new HashSet<String>();
        
        for(ValidClassReportFrequency validClassReportFrequency: validClassReportFrequencies){
            if(StringUtils.equalsIgnoreCase(validClassReportFrequency.getReportClassCode()
                    ,getReportClassCode())
                  && StringUtils.equalsIgnoreCase(validClassReportFrequency.getReportCode(),getReportCode())){
                
                uniqueRelevantFrequencyCodes.add(validClassReportFrequency.getFrequencyCode());    
            }
        }
        
        return uniqueRelevantFrequencyCodes;
    }
    
    class FrequenceComparator implements Comparator
    {    
        public int compare(Object kv1, Object kv2 )
        {    
            try
            {
                String desc1 = ((KeyValue)kv1).getValue();
                String desc2 = ((KeyValue)kv2).getValue();
                if (desc1 == null)
                {
                    desc1 = "";
                }
                if (desc2 == null)
                {
                    desc2 = "";
                }
                return desc1.compareTo(desc2);  
            }
            catch (Exception e)
            {
                return 0;
            }
        }
        
    }
    
    /**
     * 
     * This method browses through set and creates the KeyValue list from it.
     * 
     * @param uniqueValidClassReportFrequencies
     * @return
     */
    protected List<KeyValue> getKeyValues(
            Set<String> uniqueValidClassReportFrequencies){
        
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        ValidClassReportFrequency validClassReportFrequency = new ValidClassReportFrequency();
        for(String frequencyCode: uniqueValidClassReportFrequencies){
            if(frequencyCode!=null){
                validClassReportFrequency.setFrequencyCode(frequencyCode);
                validClassReportFrequency.refreshReferenceObject("frequency");
                keyValues.add(new ConcreteKeyValue(validClassReportFrequency.getFrequencyCode()
                        , validClassReportFrequency.getFrequency().getDescription()));    
            }
        }
        Collections.sort(keyValues, new FrequenceComparator());
        keyValues.add(0, new ConcreteKeyValue("","select"));
        GlobalVariables.getUserSession().addObject("awfreqr"+getReportClassCode()+"c"+getReportCode(), keyValues);
        return keyValues;
    }
}
