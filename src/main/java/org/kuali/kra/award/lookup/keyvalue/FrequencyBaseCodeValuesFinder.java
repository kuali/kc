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
import org.kuali.kra.award.bo.ValidFrequencyBase;
import org.kuali.kra.infrastructure.KraServiceLocator;

/**
 * 
 * This class is a values finder for <code>Frequency</code> business object.
 */
public class FrequencyBaseCodeValuesFinder extends KeyValuesBase {
    
    private String frequencyCode;
    
    public FrequencyBaseCodeValuesFinder() {
        super();
    }
    
    public FrequencyBaseCodeValuesFinder(String frequencyCode) {
        super();
        this.frequencyCode = frequencyCode;
    }
    
    /**
     * Constructs the list of Frequency Bases.  Each entry
     * in the list is a &lt;key, value&gt; pair, where the "key" is the unique
     * frequency base code and the "value" is the textual description that is viewed
     * by a user.  The list is obtained from a lookup of the FREQUENCY_BASE database table
     * via the "KeyValueService".
     * 
     * @return the list of &lt;key, value&gt; pairs of abstract types.  The first entry
     * is always &lt;"", "select:"&gt;.
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @SuppressWarnings("unchecked")
    public List<KeyLabelPair> getKeyValues() {
        KeyValuesService keyValuesService = getKeyValuesService();
        
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        
        Collection validFrequencyBaseCodes = keyValuesService.findAll(ValidFrequencyBase.class);
        
        HashMap<Object, Object> uniqueFrequencyBaseCodes = new HashMap<Object, Object>();
        ValidFrequencyBase validFrequencyBase = new ValidFrequencyBase();
        int key=0;
        
        keyValues.add(new KeyLabelPair("","select"));        
        for (Iterator iter1 = validFrequencyBaseCodes.iterator(); iter1.hasNext();) {
            validFrequencyBase = (ValidFrequencyBase) iter1.next();
            validFrequencyBase.refreshReferenceObject("frequencyBase");            
            if((!uniqueFrequencyBaseCodes.containsValue(validFrequencyBase.getFrequencyBaseCode()))
                    && StringUtils.equalsIgnoreCase(
                            validFrequencyBase.getFrequencyCode().toString(),getFrequencyCode())){
                
                keyValues.add(new KeyLabelPair(validFrequencyBase.getFrequencyBaseCode().toString()
                        , validFrequencyBase.getFrequencyBase().getDescription()));
                uniqueFrequencyBaseCodes.put(key, validFrequencyBase.getFrequencyBaseCode());                
            }
            key++;     
        }
        return keyValues;
    }

    public String getFrequencyCode() {
        return frequencyCode;
    }

    public void setFrequencyCode(String frequencyCode) {
        this.frequencyCode = frequencyCode;
    }
    
    protected KeyValuesService getKeyValuesService(){
        return (KeyValuesService) KraServiceLocator.getService("keyValuesService");
    }
   
}
