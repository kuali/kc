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
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.rice.core.util.KeyLabelPair;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * 
 * This class is to find protocols submitted to this committee schedule.
 */
public class ProtocolValuesFinder extends KeyValuesBase {
    private String scheduleId;

    /**
     * @see org.kuali.core.lookup.keyvalues.KeyValuesBase#getKeyValues()
     */
    public List getKeyValues() {

        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        for (ProtocolSubmission protocolSubmission : getProtocols()) {
            keyValues.add(new KeyLabelPair(protocolSubmission.getProtocolId(), protocolSubmission.getProtocolNumber()));
        }
        keyValues.add(0, new KeyLabelPair("", "select"));
        return keyValues;
    }

    private List<ProtocolSubmission> getProtocols() {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("scheduleIdFk", scheduleId);
        return (List<ProtocolSubmission>) getBusinessObjectService().findMatching(ProtocolSubmission.class, fieldValues);
    }

    protected BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

}
