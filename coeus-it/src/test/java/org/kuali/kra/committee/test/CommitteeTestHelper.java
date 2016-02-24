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
package org.kuali.kra.committee.test;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.correspondence.BatchCorrespondence;
import org.kuali.kra.irb.correspondence.BatchCorrespondenceDetail;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondence;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceType;
import org.kuali.kra.protocol.correspondence.BatchCorrespondenceDetailBase;
import org.kuali.kra.service.impl.adapters.BusinessObjectServiceAdapter;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

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
            batchCorrespondence.setBatchCorrespondenceDetails(new ArrayList<BatchCorrespondenceDetailBase>());
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
