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

public class MonthsWeek extends UifKeyValuesFinderBase {

    public static final String FIRST =  "first";
    
    public static final String SECOND = "second";
    
    public static final String THIRD = "third";
    
    public static final String FOURTH = "fourth";
    
    public static final String LAST = "last";
    
    public MonthsWeek() {
    }

    /**
     * Creates and return List of week of month.
     */
    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue(FIRST, FIRST));
        keyValues.add(new ConcreteKeyValue(SECOND, SECOND));
        keyValues.add(new ConcreteKeyValue(THIRD, THIRD));
        keyValues.add(new ConcreteKeyValue(FOURTH, FOURTH));
        keyValues.add(new ConcreteKeyValue(LAST, LAST));
        return keyValues;
    }

}
