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
