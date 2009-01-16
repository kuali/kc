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
import java.util.Iterator;
import java.util.List;

import org.kuali.core.lookup.keyvalues.KeyValuesBase;
import org.kuali.core.service.KeyValuesService;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.award.bo.Report;
import org.kuali.kra.infrastructure.KraServiceLocator;

/**
 * 
 * This class is a values finder for <code>Report</code> business object.
 */
@SuppressWarnings("unchecked")
public class ReportCodeAllValuesFinder extends KeyValuesBase {
    
    KeyValuesService keyValuesService = 
        (KeyValuesService) KraServiceLocator.getService("keyValuesService");
    
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
        
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        
        Collection reportCodes = keyValuesService.findAll(Report.class);        
        
        Report report = new Report();
        
        for (Iterator iter1 = reportCodes.iterator(); iter1.hasNext();) {
            report = (Report) iter1.next();
            keyValues.add(new KeyLabelPair(report.getReportCode().toString()
                        , report.getDescription()));
                     
        }
        return keyValues;
    }
}
