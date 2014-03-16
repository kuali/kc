/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.paymentreports.ValidFrequencyBase;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.KeyValuesService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.*;

/**
 * 
 * This class is a values finder for <code>Frequency</code> business object.
 */
public class FrequencyBaseCodeValuesFinder extends UifKeyValuesFinderBase {

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
     * 
     * @param frequencyCode
     */
    public FrequencyBaseCodeValuesFinder(String frequencyCode) {
        super();
        this.frequencyCode = frequencyCode;
    }

    /**
     * Constructs the list of Frequency Bases. Each entry in the list is a &lt;key, value&gt; pair, where the "key" is the unique
     * frequency base code and the "value" is the textual description that is viewed by a user.
     * 
     * @return the list of &lt;key, value&gt; pairs of abstract types. The first entry is always &lt;"", "select:"&gt;.
     *
     */
    @Override
    public List<KeyValue> getKeyValues() {
        if (GlobalVariables.getUserSession().retrieveObject("awfreqbase" + getFrequencyCode()) != null) {
            return (List<KeyValue>) GlobalVariables.getUserSession().retrieveObject("awfreqbase" + getFrequencyCode());
        } else {
            Collection<ValidFrequencyBase> validFrequencyBaseCodes = getKeyValuesService().findAll(ValidFrequencyBase.class);

            return getKeyValues(getUniqueRelevantFrequencyBaseCodes(validFrequencyBaseCodes));
        }
    }

    /**
     * 
     * This method...
     * 
     * @return
     */
    public String getFrequencyCode() {
        return frequencyCode;
    }

    /**
     * 
     * This method...
     * 
     * @param frequencyCode
     */
    public void setFrequencyCode(String frequencyCode) {
        this.frequencyCode = frequencyCode;
    }

    /**
     * Wrapper method for retrieval of KeyValuesService.
     * 
     * @return
     */
    protected KeyValuesService getKeyValuesService() {
        if (keyValuesService == null) {
            keyValuesService = (KeyValuesService) KcServiceLocator.getService("keyValuesService");
        }
        return keyValuesService;
    }

    /**
     * 
     * This method iterates through the validFrequencyBaseCodes and puts the valid ones in a set for another method to process.
     * 
     * @param validFrequencyBaseCodes
     * @return
     */
    protected Set<String> getUniqueRelevantFrequencyBaseCodes(Collection<ValidFrequencyBase> validFrequencyBaseCodes) {

        Set<String> uniqueRelevantFrequencyBaseCodes = new HashSet<String>();

        for (ValidFrequencyBase validFrequencyBase : validFrequencyBaseCodes) {
            if (StringUtils.equalsIgnoreCase(validFrequencyBase.getFrequencyCode(), getFrequencyCode())) {
                uniqueRelevantFrequencyBaseCodes.add(validFrequencyBase.getFrequencyBaseCode());
            }
        }

        return uniqueRelevantFrequencyBaseCodes;
    }
    
    /**
     * 
     * This class does the comparator for the FrequencyBase object.
     */
    class FrequenceBaseComparator implements Comparator {
        /**
         * 
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        public int compare(Object kv1, Object kv2) {
            try {
                String desc1 = ((KeyValue) kv1).getValue();
                String desc2 = ((KeyValue) kv2).getValue();
                if (desc1 == null) {
                    desc1 = "";
                }
                if (desc2 == null) {
                    desc2 = "";
                }
                return desc1.compareTo(desc2);
            } catch (Exception e) {
                return 0;
            }
        }

    }


    /**
     * 
     * This method browses through set and creates the KeyValue list from it.
     * 
     * @param uniqueValidFrequencyBases
     * @return
     */
    protected List<KeyValue> getKeyValues(Set<String> uniqueValidFrequencyBases) {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        ValidFrequencyBase validFrequencyBase = new ValidFrequencyBase();
        for (String frequencyBaseCode : uniqueValidFrequencyBases) {
            validFrequencyBase.setFrequencyBaseCode(frequencyBaseCode);
            validFrequencyBase.refreshReferenceObject("frequencyBase");
            if (validFrequencyBase.getFrequencyBase().isActive()) {
                keyValues.add(new ConcreteKeyValue(validFrequencyBase.getFrequencyBaseCode(), validFrequencyBase.getFrequencyBase()
                        .getDescription()));
            }
        }
        Collections.sort(keyValues, new FrequenceBaseComparator());
        keyValues.add(0, new ConcreteKeyValue("", "select"));
        GlobalVariables.getUserSession().addObject("awfreqbase" + getFrequencyCode(), keyValues);

        return keyValues;
    }

}
