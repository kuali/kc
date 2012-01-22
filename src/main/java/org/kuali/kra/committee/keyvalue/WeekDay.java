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

public class WeekDay extends KeyValuesBase {

    public static final String SUNDAY = "Sunday";
    
    public static final String MONDAY = "Monday";
    
    public static final String TUESDAY = "Tuesday";
    
    public static final String WEDNESDAY = "Wednesday";
    
    public static final String THURSDAY = "Thursday";
    
    public static final String FRIDAY = "Friday";
    
    public static final String SATURDAY = "Saturday";
    
    public WeekDay() {
    }

    /**
     * Creates and return List of week days.
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @SuppressWarnings("unchecked")
    public List getKeyValues() {
        List keyValues = new ArrayList();
        keyValues.add(new ConcreteKeyValue(SUNDAY, SUNDAY));
        keyValues.add(new ConcreteKeyValue(MONDAY, MONDAY));
        keyValues.add(new ConcreteKeyValue(TUESDAY, TUESDAY));
        keyValues.add(new ConcreteKeyValue(WEDNESDAY, WEDNESDAY));
        keyValues.add(new ConcreteKeyValue(THURSDAY, THURSDAY));
        keyValues.add(new ConcreteKeyValue(FRIDAY, FRIDAY));
        keyValues.add(new ConcreteKeyValue(SATURDAY, SATURDAY));
        return keyValues;
    }

}
