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
package org.kuali.kra.irb;

import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.amendrenew.ProtocolModule;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.protocol.participant.ProtocolParticipant;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolJavaFunctionKrmsTermServiceBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IrbJavaFunctionKrmsTermServiceImpl extends ProtocolJavaFunctionKrmsTermServiceBase implements IrbJavaFunctionKrmsTermService {
    @Override
    public Boolean hasProtocolContainsSubjectType(Protocol protocol, String subjectTypeCode) {
        boolean result = false;
        List<ProtocolParticipant> participants = protocol.getProtocolParticipants();
        for (ProtocolParticipant protocolParticipant : participants) {
            if(protocolParticipant.getParticipantTypeCode().equals(subjectTypeCode)){
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    protected ProtocolBase getActiveProtocol(String protocolNumber) {
        Map<String,String> keyMap = new HashMap<String,String>();
        keyMap.put("protocolNumber", protocolNumber);
        List<Protocol> protocols = (List <Protocol>)getBusinessObjectService().findMatchingOrderBy(Protocol.class, keyMap, "sequenceNumber", false);
        return protocols.get(0);
    }

    @Override
    public String getRenewalActionTypeCode() {
        return ProtocolActionType.RENEWAL_CREATED;
    }

    @Override
    public String getProtocolPersonnelModuleTypeCode() {
        return ProtocolModule.PROTOCOL_PERSONNEL;
    }

    @Override
    public String getProtocolOrganizationModuleTypeCode() {
        return ProtocolModule.PROTOCOL_ORGANIZATIONS;
    }

    @Override
    public String getNotifySubmissionTypeCode() {
        return ProtocolSubmissionType.NOTIFY_IRB;
    }

}
