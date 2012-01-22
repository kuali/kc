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
package org.kuali.kra.meeting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * 
 * This class is to get minute entry type code and sorted by sortid
 */
public class MinuteEntryTypeValuesFinder extends KeyValuesBase {

    /**
     * @see org.kuali.core.lookup.keyvalues.KeyValuesBase#getKeyValues()
     */
    public List getKeyValues() {

        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        for (MinuteEntryType minuteEntryType : getMinuteEntryTypes()) {
            if (!MinuteEntryType.PROTOCOL_REVIEWER_COMMENT.equals(minuteEntryType.getMinuteEntryTypeCode())) {
                keyValues.add(new ConcreteKeyValue(minuteEntryType.getMinuteEntryTypeCode(), minuteEntryType.getDescription()));
            }
        }
        keyValues.add(0, new ConcreteKeyValue("", "select"));
        return keyValues;
    }

    private List<MinuteEntryType> getMinuteEntryTypes() {
        List<MinuteEntryType> entryTypes = (List<MinuteEntryType>) KraServiceLocator.getService(BusinessObjectService.class)
                .findAll(MinuteEntryType.class);
        Collections.sort(entryTypes);
        return entryTypes;
    }
}
