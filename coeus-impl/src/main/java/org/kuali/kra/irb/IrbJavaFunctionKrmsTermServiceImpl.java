/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.irb;

import org.apache.commons.lang3.StringUtils;
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

    public Integer getProtocolParticipantTypeCount(Protocol protocol, String participantType) {
        Integer count = 0;
        for (ProtocolParticipant participant : protocol.getProtocolParticipants()) {
            if (StringUtils.equals(participant.getParticipantTypeCode(), participantType)) {
                count += participant.getParticipantCount();
            }
            else if (participant.getParticipantType() != null && StringUtils.equals(participant.getParticipantType().getDescription(), participantType)) {
                count += participant.getParticipantCount();
            }
        }
        return count;
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
