/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.protocol;

import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.irb.bo.Protocol;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * 
 * This class implements the services to maintain the <code>{@link ProtocolParticipant}</code>s of a 
 * <code>{@link Protocol}</code>.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
class ProtocolParticipantServiceImpl implements ProtocolParticipantService {

    private BusinessObjectService businessObjectService;

    /**
     * Set the Business Object Service. Injected by the Spring Framework.
     * 
     * @param businessObjectService the business object service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * This method adds the ProtocolParticipant to the List of ProtocolParticipants along with the 
     * appropriate ParticipantType.
     * 
     * @param protocol which contains list of ProtocolParticipant.
     * @param protocolParticipant which is added to ProtocolParticipants list after setting ParticipantType.
     */
    public void addProtocolParticipant(Protocol protocol, ProtocolParticipant protocolParticipant) {
        Map keyMap = new HashMap();
        keyMap.put("participantTypeCode", protocolParticipant.getParticipantTypeCode());

        ParticipantType participantType = 
            (ParticipantType) businessObjectService.findByPrimaryKey(ParticipantType.class, keyMap);

        protocolParticipant.setParticipantType(participantType);

        protocolParticipant.setProtocolNumber("0");
        protocolParticipant.setSequenceNumber(0);

        protocol.getProtocolParticipants().add(protocolParticipant);

    }

}
