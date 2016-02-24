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

import org.kuali.kra.award.home.InvInstructionsIndicatorConstants;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Values Finder for Invoice Instructions Indicator Values.
 * 
 */
public class InvInstructionsIndicatorValuesFinder extends UifKeyValuesFinderBase {
    
    private List<KeyValue> labels;

    @Override
    public List<KeyValue> getKeyValues() {
        if( labels!=null ) return labels;
        labels = new ArrayList<KeyValue>();
        
        for( InvInstructionsIndicatorConstants inv : InvInstructionsIndicatorConstants.values() ) 
            labels.add(new ConcreteKeyValue( inv.getCode(),  inv.toString()));
        return labels;
    }
    
}
