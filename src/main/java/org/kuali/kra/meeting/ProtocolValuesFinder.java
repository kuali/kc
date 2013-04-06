/*
 * Copyright 2005-2013 The Kuali Foundation
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

import org.kuali.kra.common.committee.meeting.ProtocolValuesFinderBase;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;

/**
 * 
 * This class is to find protocols submitted to this committee schedule.
 */
public class ProtocolValuesFinder extends ProtocolValuesFinderBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -6742435002576211916L;

    @Override
    protected Class<? extends ProtocolSubmissionBase> getProtocolSubmissionBOClassHook() {
        return ProtocolSubmission.class;
    }
    
// TODO ********************** commented out during IRB backfit ************************    
//    private String scheduleId;
//
//    /**
//     * @see org.kuali.core.lookup.keyvalues.KeyValuesBase#getKeyValues()
//     */
//    public List getKeyValues() {
//
//        // note: the following will overwrite existing elements in the tree; that's the whole point.  We
//        // want discrete values in the list.
//        TreeMap<String, KeyValue> valuesMap = new TreeMap<String, KeyValue>();
//        for (ProtocolSubmission protocolSubmission : getProtocols()) {
//            KeyValue keyValue = new ConcreteKeyValue(protocolSubmission.getProtocolId().toString(), protocolSubmission.getProtocolNumber());
//            valuesMap.put(protocolSubmission.getProtocolNumber(), keyValue);
//        }
//        List<KeyValue> keyValues = new ArrayList<KeyValue>(valuesMap.values());
//        keyValues.add(0, new ConcreteKeyValue("", "select"));
//        return keyValues;
//    }
//
//    private List<ProtocolSubmission> getProtocols() {
//        Map<String, String> fieldValues = new HashMap<String, String>();
//        fieldValues.put("scheduleIdFk", scheduleId);
//        return (List<ProtocolSubmission>) getBusinessObjectService().findMatching(ProtocolSubmission.class, fieldValues);
//    }
//
//    protected BusinessObjectService getBusinessObjectService() {
//        return KraServiceLocator.getService(BusinessObjectService.class);
//    }
//
//    public String getScheduleId() {
//        return scheduleId;
//    }
//
//    public void setScheduleId(String scheduleId) {
//        this.scheduleId = scheduleId;
//    }

}
