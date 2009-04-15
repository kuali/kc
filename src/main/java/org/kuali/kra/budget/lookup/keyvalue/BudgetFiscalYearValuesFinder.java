/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.budget.lookup.keyvalue;

import java.util.ArrayList;
import java.util.List;

import org.kuali.core.lookup.keyvalues.KeyValuesBase;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.lookup.keyvalue.KeyValueFinderService;

/**
 * Finds the available set of supported Narrative Statuses.  See
 * the method <code>getKeyValues()</code> for a full description.
 * 
 * @author KRADEV team
 */
public class BudgetFiscalYearValuesFinder extends KeyValuesBase {
    KeyValueFinderService keyValueFinderService= (KeyValueFinderService)KraServiceLocator.getService("keyValueFinderService");
    
    /**
     * Constructs the list of Budget Fiscal Years.  Each entry
     * in the list is a &lt;key, value&gt; pair, where the "key" is the unique
     * status code and the "value" is the textual description that is viewed
     * by a user.
     * 
     * Until it's determined if the list should be obtained from a database,
     * the list will be canned, covering 2008 - 2010
     * 
     * @return the list of &lt;key, value&gt; pairs of abstract types.  The first entry
     * is always &lt;"", "select:"&gt;.
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyLabelPair> getKeyValues() {
        List<KeyLabelPair> keyLabelPairs = new ArrayList<KeyLabelPair>();
        
        keyLabelPairs.add(new KeyLabelPair(null, "Select"));
        for(Integer fiscalYear: loadFiscalYears()) {
            keyLabelPairs.add(createKeyLabelPair(fiscalYear));
        }
        
        return keyLabelPairs; 
    }
    
    private KeyLabelPair createKeyLabelPair(Integer fiscalYear) {
        return new KeyLabelPair(fiscalYear, fiscalYear.toString());
    }

    private Integer[] loadFiscalYears() {
        return new Integer[] { 2008, 2009, 2010 };
    }
}
