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
package org.kuali.kra.irb.protocol.participant;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.irb.Protocol;

/**
 * 
 * The JUnit test class for <code>{@link ProtocolParticipantService}</code>
 * 
 * @author Kuali Research Administration Team (kc.dev@kuali.org)
 */
public class ProtocolParticipantServiceTest {

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
