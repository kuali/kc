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
import java.util.List;

import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

/**
 * 
 * This class is a values finder for Contact.
 * 
 */
//TODO: this method should be refactored once the contact functionality is complete
public class ContactsValuesFinder extends KeyValuesBase {
    
    /**
     * Constructs the list of Contacts.  Each entry
     * in the list is a &lt;key, value&gt; pair, where the "key" is the unique
     * contact_id and the "value" is the textual description that is viewed
     * by a user - the value is a combination of contact type and rolodex which
     * is added in the Contacts screen.  
     * 
     * This Valuesfinder class has hard coded values. It should be refactored in future
     * to when Contacts functinality is complete.
     * 
     * @return the list of &lt;key, value&gt; pairs of abstract types.  The first entry
     * is always &lt;"", "select:"&gt;.
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */    
    public List<KeyLabelPair> getKeyValues() {
        //KeyValuesService keyValuesService = 
            //(KeyValuesService) KraServiceLocator.getService("keyValuesService");
        
        //Collection reportClasses = keyValuesService.findAll(ReportClass.class);
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        
        keyValues.add(new KeyLabelPair("", "select "));
        keyValues.add(new KeyLabelPair(1, "Subcontracting Reporting Contact - University of California"));
        keyValues.add(new KeyLabelPair(2, "Property Office Contact-1 - University of California"));
        keyValues.add(new KeyLabelPair(3, "Intellectual Property Contact - University of California"));
        keyValues.add(new KeyLabelPair(4, "Payment/Fiscal Reporting Contact - University of California"));
        keyValues.add(new KeyLabelPair(5, "Awarding Office Contact--1 - University of California"));
        keyValues.add(new KeyLabelPair(6, "Procurement Reporting Contact-1 - University of California"));
        /*for (Iterator iter = reportClasses.iterator(); iter.hasNext();) {
            ReportClass reportClass = (ReportClass) iter.next();
            keyValues.add(new KeyLabelPair(reportClass.getReportClassCode(),
                    reportClass.getDescription()));                            
        }*/
                
        return keyValues;
    }
   
}
