/*
 * Copyright 2006-2008 The Kuali Foundation
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
import java.util.List;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

import edu.emory.mathcs.backport.java.util.Collections;

public class MinuteEntryTypeValuesFinder extends KeyValuesBase {

    /**
     * @see org.kuali.core.lookup.keyvalues.KeyValuesBase#getKeyValues()
     */
    public List getKeyValues() {

        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        for (MinuteEntryType minuteEntryType : getMinuteEntryTypes()) {
            keyValues.add(new KeyLabelPair(minuteEntryType.getMinuteEntryTypeCode(), minuteEntryType.getDescription()));
        }
        keyValues.add(0, new KeyLabelPair("", "select"));
        return keyValues;
    }

    private List<MinuteEntryType> getMinuteEntryTypes() {
        List<MinuteEntryType> entryTypes = (List<MinuteEntryType>) KraServiceLocator.getService(BusinessObjectService.class)
                .findAll(MinuteEntryType.class);
        Collections.sort(entryTypes);
        return entryTypes;
    }
}
