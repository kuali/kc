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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.core.util.KeyLabelPair;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * Provides a values finder for an "Other Business" <code>CommScheduleActItem</code>.
 */
public class OtherBusinessValuesFinder extends KeyValuesBase {
    
    private static final int TRUNCATE_LENGTH = 20;
    private static final String TRUNCATE_END = "...";
    
    private String scheduleId;
    
    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    /**
     * Returns the list of key values for the "Other Business", making sure the description is truncated to 20 characters.
     * 
     * @see org.kuali.rice.kns.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @SuppressWarnings("unchecked")
    public List getKeyValues() {
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        for (CommScheduleActItem commScheduleActItem : getOtherBusinessActionItem()) {
            keyValues.add(new KeyLabelPair(commScheduleActItem.getActionItemNumber(), truncate(commScheduleActItem.getItemDesctiption(), TRUNCATE_LENGTH)));
        }
        keyValues.add(0, new KeyLabelPair("", "select"));
        return keyValues;
    }
    
    @SuppressWarnings("unchecked")
    private List<CommScheduleActItem> getOtherBusinessActionItem() {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("scheduleIdFk", scheduleId);
        fieldValues.put("scheduleActItemTypeCode", ScheduleActItemType.OTHER_BUSINESS);
        return (List<CommScheduleActItem>) getBusinessObjectService().findMatching(CommScheduleActItem.class, fieldValues);
    }
    
    private String truncate(String originalString, int length) {
        StringBuffer buffer = new StringBuffer(originalString);
        buffer.setLength(length - TRUNCATE_END.length());
        buffer.append(TRUNCATE_END);
        return buffer.toString();
    }

    protected BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }

}