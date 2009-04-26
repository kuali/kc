/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
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

import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

/**
 * 
 * This class is a values finder for subPlanFlag of Award business object.
 */
@SuppressWarnings("unchecked")
public class SubPlanFlagValuesFinder extends KeyValuesBase {
    
    /**
     * This method adds 3 pre-determined values to a key values pair and returns it.
     * 
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyLabelPair> getKeyValues() {
        
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        
        keyValues.add(new KeyLabelPair('U', new String("Unknown")));
        keyValues.add(new KeyLabelPair('R', new String("Required")));
        keyValues.add(new KeyLabelPair('N', new String("Not Required")));
                
        return keyValues;
    }
   
}
