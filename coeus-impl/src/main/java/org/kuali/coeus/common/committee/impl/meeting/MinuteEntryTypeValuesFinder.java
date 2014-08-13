/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.committee.impl.meeting;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * This class is to get minute entry type code and sorted by sortid
 */
public class MinuteEntryTypeValuesFinder extends UifKeyValuesFinderBase {

    @Override
    public List<KeyValue> getKeyValues() {

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
        List<MinuteEntryType> entryTypes = (List<MinuteEntryType>) KcServiceLocator.getService(BusinessObjectService.class)
                .findAll(MinuteEntryType.class);
        Collections.sort(entryTypes);
        return entryTypes;
    }
}
