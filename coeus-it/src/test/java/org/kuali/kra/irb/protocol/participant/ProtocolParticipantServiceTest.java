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

import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

/**
 * 
 * The JUnit test class for <code>{@link ProtocolParticipantService}</code>
 * 
 * @author Kuali Research Administration Team (kc.dev@kuali.org)
 */
public class ProtocolParticipantServiceTest extends KcIntegrationTestBase {

    private Protocol protocol;

    @Before
    public void setUp() {

        ProtocolParticipant protocolParticipant1 = new ProtocolParticipant();
        protocolParticipant1.setParticipantTypeCode("1");
        protocolParticipant1.setParticipantCount(15);

        ProtocolParticipant protocolParticipant2 = new ProtocolParticipant();
        protocolParticipant2.setParticipantTypeCode("2");
        protocolParticipant2.setParticipantCount(25);

        ProtocolParticipant protocolParticipant3 = new ProtocolParticipant();
        protocolParticipant3.setParticipantTypeCode("3");
        protocolParticipant3.setParticipantCount(35);

        protocol = new Protocol(){
            @Override
            public void refreshReferenceObject(String referenceObjectName) {}    
        };
        protocol.setProtocolParticipants(new ArrayList<ProtocolParticipant>());
        protocol.getProtocolParticipants().add(protocolParticipant1);
        protocol.getProtocolParticipants().add(protocolParticipant2);
        protocol.getProtocolParticipants().add(protocolParticipant3);
    }

    @Test
    public void testAddProtocolParticipant() {
        ProtocolParticipant protocolParticipant = new ProtocolParticipant();
        protocolParticipant.setParticipantTypeCode("4");

        final ProtocolParticipantServiceImpl protocolParticipantService = new ProtocolParticipantServiceImpl();

        protocolParticipantService.addProtocolParticipant(protocol, protocolParticipant);
        
        int participantSize = protocol.getProtocolParticipants().size();
        assertTrue("participant size is " + participantSize, participantSize == 4);
        String participantCode1 = protocol.getProtocolParticipant(0).getParticipantTypeCode();
        assertTrue("participant type code of participant 1 is " + participantCode1, "1".equals(participantCode1));
        String participantCode2 = protocol.getProtocolParticipant(1).getParticipantTypeCode();
        assertTrue("participant type code of participant 2 is " + participantCode2, "2".equals(participantCode2));
        String participantCode3 = protocol.getProtocolParticipant(2).getParticipantTypeCode();
        assertTrue("participant type code of participant 3 is " + participantCode3, "3".equals(participantCode3));
        String participantCode4 = protocol.getProtocolParticipant(3).getParticipantTypeCode();
        assertTrue("participant type code of participant 4 is " + participantCode4, "4".equals(participantCode4));
        
    }

}
