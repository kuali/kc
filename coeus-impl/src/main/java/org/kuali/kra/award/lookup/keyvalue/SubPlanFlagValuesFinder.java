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
package org.kuali.kra.award.lookup.keyvalue;

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class is a values finder for subPlanFlag of Award business object.
 */
@SuppressWarnings("unchecked")
public class SubPlanFlagValuesFinder extends UifKeyValuesFinderBase {
    
    /**
     * This method adds 3 pre-determined values to a key values pair and returns it.
     * 
     */
    @Override
    public List<KeyValue> getKeyValues() {
        
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        
        keyValues.add(new ConcreteKeyValue("U", new String("Unknown")));
        keyValues.add(new ConcreteKeyValue("Y", new String("Required")));
        keyValues.add(new ConcreteKeyValue("N", new String("Not Required")));
                
        return keyValues;
    }
   
}
