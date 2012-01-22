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
package org.kuali.kra.committee.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.correspondence.BatchCorrespondence;
import org.kuali.kra.irb.correspondence.BatchCorrespondenceDetail;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondence;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceType;
import org.kuali.kra.service.impl.adapters.BusinessObjectServiceAdapter;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

public class CommitteeTestHelper {

    public static class MockBusinessObjectService extends BusinessObjectServiceAdapter {
        @SuppressWarnings("unchecked")
        public PersistableBusinessObject findByPrimaryKey(Class clazz, Map identifiers) {
            if (clazz.equals(BatchCorrespondence.class)) {
                String batchCorrespondenceTypeCode = (String) identifiers.get("batchCorrespondenceTypeCode");
                return getBatchCorrespondence(batchCorrespondenceTypeCode);
            }
            
            return null;
        }
        
        private BatchCorrespondence getBatchCorrespondence(String batchCorrespondenceTypeCode) {    
            BatchCorrespondence batchCorrespondence = new BatchCorrespondence();
            batchCorrespondence.setBatchCorrespondenceTypeCode(batchCorrespondenceTypeCode);
            batchCorrespondence.setBatchCorrespondenceDetails(new ArrayList<BatchCorrespondenceDetail>());
            if (StringUtils.equals(batchCorrespondenceTypeCode, Constants.PROTOCOL_RENEWAL_REMINDERS)) {
                batchCorrespondence.setDescription("Protocol Renewal Reminders");
                batchCorrespondence.setSendCorrespondence(BatchCorrespondence.SEND_CORRESPONDENCE_BEFORE_EVENT);
                batchCorrespondence.setFinalActionDay(0);
                batchCorrespondence.setFinalActionCorrespType("300");
                batchCorrespondence.setFinalActionTypeCode(null);
                batchCorrespondence.getBatchCorrespondenceDetails().add(initBatchCorrespondenceDetail(batchCorrespondenceTypeCode, "20", 60));
                batchCorrespondence.getBatchCorrespondenceDetails().add(initBatchCorrespondenceDetail(batchCorrespondenceTypeCode, "21", 15));
            } else {
                batchCorrespondence.setDescription("Reminder to IRB Notifications");
                batchCorrespondence.setSendCorrespondence(BatchCorrespondence.SEND_CORRESPONDENCE_AFTER_EVENT);
                batchCorrespondence.setFinalActionDay(30);
                batchCorrespondence.setFinalActionCorrespType(null);
                batchCorrespondence.setFinalActionTypeCode("24");
                batchCorrespondence.getBatchCorrespondenceDetails().add(initBatchCorrespondenceDetail(batchCorrespondenceTypeCode, "23", 15));
            }
            
            return batchCorrespondence;
        }
        
        private BatchCorrespondenceDetail initBatchCorrespondenceDetail(String batchCorrespondenceTypeCode, String protoCorrespTypeCode, int daysToEvent) {
            BatchCorrespondenceDetail batchCorrespondenceDetail = new BatchCorrespondenceDetail();
            batchCorrespondenceDetail.setBatchCorrespondenceTypeCode(batchCorrespondenceTypeCode);
            batchCorrespondenceDetail.setProtoCorrespTypeCode(protoCorrespTypeCode);
            batchCorrespondenceDetail.setDaysToEvent(daysToEvent);
            batchCorrespondenceDetail.setProtocolCorrespondenceType(initProtocolCorrespondenceType(protoCorrespTypeCode));
            return batchCorrespondenceDetail;
        }
        
        private ProtocolCorrespondenceType initProtocolCorrespondenceType(String protocolCorrespondenceTypeCode) {
            ProtocolCorrespondenceType protocolCorrespondenceType = new ProtocolCorrespondenceType();
            protocolCorrespondenceType.setProtoCorrespTypeCode(protocolCorrespondenceTypeCode);
            switch (Integer.parseInt(protocolCorrespondenceTypeCode)) {
                case 20 :
                    protocolCorrespondenceType.setDescription("Renewal Reminder Letter #1");
                    break;
                case 21 :
                    protocolCorrespondenceType.setDescription("Renewal Reminder Letter #2");
                    break;
                case 22 :
                    protocolCorrespondenceType.setDescription("Renewal Reminder Letter #3");
                    break;
                case 23 :
                    protocolCorrespondenceType.setDescription("Reminder to IRB Notification #1");
                    break;
                case 24 :
                    protocolCorrespondenceType.setDescription("Reminder to IRB Notification #2");
                    break;
                case 25 :
                    protocolCorrespondenceType.setDescription("Reminder to IRB Notification #3");
                    break;
            }

            return protocolCorrespondenceType;
        }

        @SuppressWarnings("unchecked")
        public Collection findMatching(Class clazz, Map indentifiers) {
            if (clazz.equals(ProtocolCorrespondence.class)) {
                return new ArrayList();
            }
            
            return null;
        }
        
        public PersistableBusinessObject save(PersistableBusinessObject bo) {
            return bo;
        }
 
    }
}
