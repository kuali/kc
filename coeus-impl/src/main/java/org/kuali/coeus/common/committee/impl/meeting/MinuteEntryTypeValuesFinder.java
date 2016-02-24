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
