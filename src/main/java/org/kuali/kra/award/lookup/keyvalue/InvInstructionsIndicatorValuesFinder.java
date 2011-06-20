/*
 * Copyright 2005-2011 The Kuali Foundation
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
package org.kuali.kra.award.lookup.keyvalue;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.award.home.InvInstructionsIndicatorConstants;
import org.kuali.rice.core.util.KeyLabelPair;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;

/**
 * Values Finder for Invoice Instructions Indicator Values.
 * 
 */
public class InvInstructionsIndicatorValuesFinder extends KeyValuesBase {
    
    private List<KeyLabelPair> labels;

    public List<KeyLabelPair> getKeyValues() {
        if( labels!=null ) return labels;
        labels = new ArrayList<KeyLabelPair>();
        
        for( InvInstructionsIndicatorConstants inv : InvInstructionsIndicatorConstants.values() ) 
            labels.add(new KeyLabelPair( inv.getCode(),  inv.toString()));
        return labels;
    }
    
}
