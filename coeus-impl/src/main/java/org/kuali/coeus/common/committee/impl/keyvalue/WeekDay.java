/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.common.committee.impl.keyvalue;

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.List;

public class WeekDay extends UifKeyValuesFinderBase {

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
     */
    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
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
