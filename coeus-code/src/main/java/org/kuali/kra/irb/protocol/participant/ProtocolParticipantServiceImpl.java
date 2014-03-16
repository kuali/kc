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
package org.kuali.kra.irb.protocol.participant;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.irb.Protocol;

/**
 * 
 * This class implements the services to maintain the <code>{@link ProtocolParticipant}</code>s of a 
 * <code>{@link Protocol}</code>.
 * 
 * @author Kuali Research Administration Team (kc.dev@kuali.org)
 */
public class ProtocolParticipantServiceImpl implements ProtocolParticipantService {

    /**
     * This method adds the ProtocolParticipant to the List of ProtocolParticipants along with the 
     * appropriate ParticipantType.
     * 
     * @param protocol which contains list of ProtocolParticipant.
     * @param protocolParticipant which is added to ProtocolParticipants list after setting ParticipantType.
     */
    public void addProtocolParticipant(Protocol protocol, ProtocolParticipant protocolParticipant) {
        protocolParticipant.setProtocol(protocol);
        if (StringUtils.isBlank(protocolParticipant.getProtocolNumber())) {
            protocolParticipant.setProtocolNumber("0");
        }
        protocolParticipant.refreshReferenceObject("participantType");
        protocol.getProtocolParticipants().add(protocolParticipant);
    }

}