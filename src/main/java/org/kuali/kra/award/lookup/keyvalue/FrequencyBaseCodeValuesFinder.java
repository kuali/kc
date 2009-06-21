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
import org.kuali.kra.award.paymentreports.ValidFrequencyBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.service.KeyValuesService;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

/**
 * 
 * This class is a values finder for <code>Frequency</code> business object.
 */
public class FrequencyBaseCodeValuesFinder extends KeyValuesBase {
    
    private String frequencyCode;
    private KeyValuesService keyValuesService;
    
    /**
     * 
     * Constructs a CopyOfFrequencyBaseCodeValuesFinder.java.
     */
    public FrequencyBaseCodeValuesFinder() {
        super();
    }
    
    /**
     * 
     * Constructs a CopyOfFrequencyBaseCodeValuesFinder.java.
     * @param frequencyCode
     */
    public FrequencyBaseCodeValuesFinder(String frequencyCode) {
        super();
        this.frequencyCode = frequencyCode;
    }
    
    /**
     * Constructs the list of Frequency Bases.  Each entry
     * in the list is a &lt;key, value&gt; pair, where the "key" is the unique
     * frequency base code and the "value" is the textual description that is viewed
     * by a user. 
     * 
     * @return the list of &lt;key, value&gt; pairs of abstract types.  The first entry
     * is always &lt;"", "select:"&gt;.
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */    
    @SuppressWarnings("all")
    public List<KeyLabelPair> getKeyValues() {
        
        Collection<ValidFrequencyBase> validFrequencyBaseCodes 
            = getKeyValuesService().findAll(ValidFrequencyBase.class);
        
        return getKeyValues(getUniqueRelevantFrequencyBaseCodes(validFrequencyBaseCodes));
    }

    /**
     * 
     * This method...
     * @return
     */
    public String getFrequencyCode() {
        return frequencyCode;
    }

    /**
     * 
     * This method...
     * @param frequencyCode
     */
    public void setFrequencyCode(String frequencyCode) {
        this.frequencyCode = frequencyCode;
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
     * This method iterates through the validFrequencyBaseCodes and puts the valid ones
     * in a set for another method to process. 
     * 
     * @param validFrequencyBaseCodes
     * @return
     */
    protected Set<String> getUniqueRelevantFrequencyBaseCodes(
            Collection<ValidFrequencyBase> validFrequencyBaseCodes){
        
        Set<String> uniqueRelevantFrequencyBaseCodes
            = new HashSet<String>();
        
        for(ValidFrequencyBase validFrequencyBase: validFrequencyBaseCodes){
            if(StringUtils.equalsIgnoreCase(validFrequencyBase.getFrequencyCode()
                    ,getFrequencyCode())){                
                uniqueRelevantFrequencyBaseCodes.add(validFrequencyBase.getFrequencyBaseCode());    
            }
        }
        
        return uniqueRelevantFrequencyBaseCodes;
    }
    
    /**
     * 
     * This method browses through set and creates the keylabelpair list from it.
     * 
     * @param uniqueValidFrequencyBases
     * @return
     */
    protected List<KeyLabelPair> getKeyValues(
            Set<String> uniqueValidFrequencyBases){
        
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        ValidFrequencyBase validFrequencyBase = new ValidFrequencyBase();
        keyValues.add(new KeyLabelPair("","select"));
        for(String frequencyBaseCode: uniqueValidFrequencyBases){
            validFrequencyBase.setFrequencyBaseCode(frequencyBaseCode);
            validFrequencyBase.refreshReferenceObject("frequencyBase");            
            keyValues.add(new KeyLabelPair(validFrequencyBase.getFrequencyBaseCode()
                    , validFrequencyBase.getFrequencyBase().getDescription()));
        }
        
        return keyValues;
    }
   
}
