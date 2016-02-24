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

public class Month extends UifKeyValuesFinderBase {

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
     */
    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
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
