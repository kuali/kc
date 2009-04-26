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
package org.kuali.kra.award.lookup.keyvalue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.bo.Frequency;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.lookup.keyvalue.KeyValueFinderService;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.service.KeyValuesService;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

/**
 * This class...
 */
public class PaymentInvoiceFrequencyValueFinder  extends KeyValuesBase {
    KeyValueFinderService keyValueFinderService= (KeyValueFinderService)KraServiceLocator.getService("keyValueFinderService");
    
    /**
     * Constructs the list of Frequency BOs.  Each entry
     * in the list is a &lt;key, value&gt; pair, where the "key" is the unique
     * report class code and the "value" is the textual description that is viewed
     * by a user.  The list is obtained from the AwardDocument if any are defined there. 
     * Otherwise, it is obtained from a lookup of the FREQUENCY database table
     * via the "KeyValueFinderService".
     * 
     * @return the list of &lt;key, value&gt; pairs of abstract types.  The first entry
     * is always &lt;"", "select:"&gt;.
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyLabelPair> getKeyValues() {
        KeyValuesService keyValuesService = (KeyValuesService) KraServiceLocator.getService("keyValuesService");
        Map<String,Boolean> fieldValues = new HashMap<String,Boolean>();
        fieldValues.put("invoiceFlag", Boolean.TRUE);
        Collection reportClasses = keyValuesService.findMatching(Frequency.class, fieldValues);
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        for (Iterator iter = reportClasses.iterator(); iter.hasNext();) {
            Frequency frequency = (Frequency) iter.next();
            keyValues.add(new KeyLabelPair(frequency.getFrequencyCode(), frequency.getDescription()));                            
        }
                
        return keyValues;
    }
   

}
