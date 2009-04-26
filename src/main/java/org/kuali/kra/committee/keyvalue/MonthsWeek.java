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
package org.kuali.kra.committee.keyvalue;

import java.util.ArrayList;
import java.util.List;

import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

public class MonthsWeek extends KeyValuesBase {

    public static final String FIRST =  "first";
    
    public static final String SECOND = "second";
    
    public static final String THIRD = "third";
    
    public static final String FOURTH = "fourth";
    
    public static final String LAST = "last";
    
    public MonthsWeek() {
    }

    /**
     * Creates and return List of week of month.
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @SuppressWarnings("unchecked")
    public List getKeyValues() {
        List keyValues = new ArrayList();
        keyValues.add(new KeyLabelPair(FIRST, FIRST));
        keyValues.add(new KeyLabelPair(SECOND, SECOND));
        keyValues.add(new KeyLabelPair(THIRD, THIRD));
        keyValues.add(new KeyLabelPair(FOURTH, FOURTH));
        keyValues.add(new KeyLabelPair(LAST, LAST));
        return keyValues;
    }

}
