/*
 * Copyright 2005-2010 The Kuali Foundation
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

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

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
        keyValues.add(new ConcreteKeyValue(JANUARY,JANUARY));
        keyValues.add(new ConcreteKeyValue(FEBRUARY,FEBRUARY));
        keyValues.add(new ConcreteKeyValue(MARCH,MARCH));
        keyValues.add(new ConcreteKeyValue(APRIL,APRIL));
        keyValues.add(new ConcreteKeyValue(MAY,MAY));
        keyValues.add(new ConcreteKeyValue(JUNE,JUNE));
        keyValues.add(new ConcreteKeyValue(JULY,JULY));
        keyValues.add(new ConcreteKeyValue(AUGUST,AUGUST));
        keyValues.add(new ConcreteKeyValue(SEPTEMBER,SEPTEMBER));
        keyValues.add(new ConcreteKeyValue(OCTOBER,OCTOBER));
        keyValues.add(new ConcreteKeyValue(NOVEMBER,NOVEMBER));
        keyValues.add(new ConcreteKeyValue(DECEMBER,DECEMBER));
        return keyValues;
    }

}
