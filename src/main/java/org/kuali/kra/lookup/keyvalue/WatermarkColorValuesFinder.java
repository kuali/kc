/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.lookup.keyvalue;

import java.util.ArrayList;
import java.util.List;

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

/**
 * 
 * This class for storing the details of watermark Color.
 */
public class WatermarkColorValuesFinder extends KeyValuesBase {
    /**
     * This method for storing lookup keyvalues of watermark font Color.
     * 
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */

    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("LIGHT_GRAY", "LIGHT_GRAY"));
        keyValues.add(new ConcreteKeyValue("BLACK", "BLACK"));
        keyValues.add(new ConcreteKeyValue("BLUE", "BLUE"));
        keyValues.add(new ConcreteKeyValue("MAGENTA", "MAGENTA"));
        keyValues.add(new ConcreteKeyValue("CYAN", "CYAN"));
        keyValues.add(new ConcreteKeyValue("ORANGE", "ORANGE"));
        keyValues.add(new ConcreteKeyValue("DARKGRAY", "DARKGRAY"));
        keyValues.add(new ConcreteKeyValue("PINK", "PINK"));
        keyValues.add(new ConcreteKeyValue("GRAY", "GRAY"));
        keyValues.add(new ConcreteKeyValue("RED", "RED"));
        keyValues.add(new ConcreteKeyValue("GREEN", "GREEN"));
        keyValues.add(new ConcreteKeyValue("WHITE", "WHITE"));
        keyValues.add(new ConcreteKeyValue("YELLOW", "YELLOW"));

        return keyValues;
    }


}
