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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.lookup.keyvalues.KeyValuesBase;
import org.kuali.core.service.KeyValuesService;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.award.bo.ValidClassReportFrequency;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.lookup.keyvalue.KeyValueFinderService;

/**
 * 
 * This class is a values finder for <code>Frequency</code> business object.
 */
@SuppressWarnings("unchecked")
public class FrequencyCodeValuesFinder extends KeyValuesBase {
    
    KeyValueFinderService keyValueFinderService= 
        (KeyValueFinderService) KraServiceLocator.getService("keyValueFinderService");
    
    private String reportClassCode;
    private String reportCode;
    
    public FrequencyCodeValuesFinder() {
        super();
    }
    
    public FrequencyCodeValuesFinder(String reportClassCode, String reportCode) {
        super();
        this.reportClassCode = reportClassCode;
        this.reportCode = reportCode;
    }
    
    /**
     * Constructs the list of Reports.  Each entry
     * in the list is a &lt;key, value&gt; pair, where the "key" is the unique
     * report code and the "value" is the textual description that is viewed
     * by a user.  The list is obtained from a lookup of the REPORT database table
     * via the "KeyValueFinderService".
     * 
     * @return the list of &lt;key, value&gt; pairs of abstract types.  The first entry
     * is always &lt;"", "select:"&gt;.
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyLabelPair> getKeyValues() {
        KeyValuesService keyValuesService = getKeyValuesService();
        
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        
        Collection validClassReportFrequencies = keyValuesService.findAll(ValidClassReportFrequency.class);
        
        HashMap uniqueFrequencyCodes = new HashMap();
        ValidClassReportFrequency validClassReportFrequency = new ValidClassReportFrequency();
        int key=0;
        keyValues.add(new KeyLabelPair("","select"));
        for (Iterator iter1 = validClassReportFrequencies.iterator(); iter1.hasNext();) {
            validClassReportFrequency = (ValidClassReportFrequency) iter1.next();
            validClassReportFrequency.refreshReferenceObject("frequency");            
            if((!uniqueFrequencyCodes.containsValue(validClassReportFrequency.getFrequencyCode()))
                    && StringUtils.equalsIgnoreCase(validClassReportFrequency.getReportClassCode(),
                            getReportClassCode())
                    && StringUtils.equalsIgnoreCase(validClassReportFrequency.getReportCode(),
                            getReportCode())){                
                keyValues.add(new KeyLabelPair(validClassReportFrequency.getFrequencyCode().toString()
                        , validClassReportFrequency.getFrequency().getDescription()));
                uniqueFrequencyCodes.put(key, validClassReportFrequency.getFrequencyCode());                
            }
            key++;     
        }
        return keyValues;
    }
    
    public String getReportClassCode() {
        return reportClassCode;
    }

    public void setReportClassCode(String reportClassCode) {
        this.reportClassCode = reportClassCode;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }
    
    protected KeyValuesService getKeyValuesService(){
        return (KeyValuesService) KraServiceLocator.getService("keyValuesService");
    }
   
}
