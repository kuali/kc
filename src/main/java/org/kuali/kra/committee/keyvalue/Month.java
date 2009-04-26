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

public class Month extends KeyValuesBase {

    public static final String JANUARY = "JANUARY";
    
    public static final String FEBRUARY = "FEBRUARY";
    
    public static final String MARCH = "MARCH";
    
    public static final String APRIL = "APRIL";
    
    public static final String MAY = "MAY";
    
    public static final String JUNE = "JUNE";
    
    public static final String JULY = "JULY";
    
    public static final String AUGUST = "AUGUST";
    
    public static final String SEPTEMBER = "SEPTEMBER";
    
    public static final String OCTOBER = "OCTOBER";
    
    public static final String NOVEMBER = "NOVEMBER";
    
    public static final String DECEMBER = "DECEMBER";
    
    public Month() {
    }

    /**
     * Creates and return List of months.
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @SuppressWarnings("unchecked")
    public List getKeyValues() {
        List keyValues = new ArrayList();
        keyValues.add(new KeyLabelPair(JANUARY,JANUARY));
        keyValues.add(new KeyLabelPair(FEBRUARY,FEBRUARY));
        keyValues.add(new KeyLabelPair(MARCH,MARCH));
        keyValues.add(new KeyLabelPair(APRIL,APRIL));
        keyValues.add(new KeyLabelPair(MAY,MAY));
        keyValues.add(new KeyLabelPair(JUNE,JUNE));
        keyValues.add(new KeyLabelPair(JULY,JULY));
        keyValues.add(new KeyLabelPair(AUGUST,AUGUST));
        keyValues.add(new KeyLabelPair(SEPTEMBER,SEPTEMBER));
        keyValues.add(new KeyLabelPair(OCTOBER,OCTOBER));
        keyValues.add(new KeyLabelPair(NOVEMBER,NOVEMBER));
        keyValues.add(new KeyLabelPair(DECEMBER,DECEMBER));
        return keyValues;
    }

}
