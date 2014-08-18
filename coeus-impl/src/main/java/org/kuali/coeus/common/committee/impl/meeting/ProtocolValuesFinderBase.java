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
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.*;

/**
 * 
 * This class is to find protocols submitted to this committee schedule.
 */
public abstract class ProtocolValuesFinderBase extends UifKeyValuesFinderBase {

    private static final long serialVersionUID = 5144171874415568112L;
    
    private String scheduleId;

    @Override
    public List<KeyValue> getKeyValues() {

        // note: the following will overwrite existing elements in the tree; that's the whole point.  We
        // want discrete values in the list.
        TreeMap<String, KeyValue> valuesMap = new TreeMap<String, KeyValue>();
        for (ProtocolSubmissionBase protocolSubmission : getProtocols()) {
            KeyValue keyValue = new ConcreteKeyValue(protocolSubmission.getProtocolId().toString(), protocolSubmission.getProtocolNumber());
            valuesMap.put(protocolSubmission.getProtocolNumber(), keyValue);
        }
        List<KeyValue> keyValues = new ArrayList<KeyValue>(valuesMap.values());
        keyValues.add(0, new ConcreteKeyValue("", "select"));
        return keyValues;
    }

    private List<? extends ProtocolSubmissionBase> getProtocols() {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("scheduleIdFk", scheduleId);

        return (List<? extends ProtocolSubmissionBase>) getBusinessObjectService().findMatching(getProtocolSubmissionBOClassHook(), fieldValues);
    }

    protected abstract Class<? extends ProtocolSubmissionBase> getProtocolSubmissionBOClassHook();

    protected BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

}
