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
package org.kuali.kra.irb.noteattachment;

import java.util.ArrayList;
import java.util.List;

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

/**
 * 
 * This class provides a values finder for the fields used to sort
 * attachment tables.  
 */
public class SortByValuesFinder extends KeyValuesBase {

    /**
     * 
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyValue> getKeyValues() {
        
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", "None"));
        keyValues.add(new ConcreteKeyValue("ATTP", new String("Attachment Type")));
        keyValues.add(new ConcreteKeyValue("DESC", new String("Description")));
        keyValues.add(new ConcreteKeyValue("LAUP", new String("Last Updated")));
        keyValues.add(new ConcreteKeyValue("UPBY", new String("Last Updated By")));
        
        return keyValues;
    }

    
}
