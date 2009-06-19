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
package org.kuali.kra.irb.protocol.participant;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.protocol.participant.ParticipantType;
import org.kuali.kra.irb.protocol.participant.ProtocolParticipant;
import org.kuali.kra.irb.protocol.participant.ProtocolParticipantService;
import org.kuali.kra.irb.protocol.participant.ProtocolParticipantServiceImpl;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * 
 * The JUnit test class for <code>{@link ProtocolParticipantService}</code>
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProtocolParticipantServiceTest {

    private Mockery context = new JUnit4Mockery();

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

        protocol = new Protocol();
        protocol.setProtocolParticipants(new ArrayList<ProtocolParticipant>());
        protocol.getProtocolParticipants().add(protocolParticipant1);
        protocol.getProtocolParticipants().add(protocolParticipant2);
        protocol.getProtocolParticipants().add(protocolParticipant3);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testAddProtocolParticipant() {
        ProtocolParticipant protocolParticipant = new ProtocolParticipant();
        protocolParticipant.setParticipantTypeCode("4");

        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        final ProtocolParticipantServiceImpl protocolParticipantService = new ProtocolParticipantServiceImpl();
        protocolParticipantService.setBusinessObjectService(businessObjectService);

        final Map keyMap = new HashMap();
        keyMap.put("participantTypeCode", "4");
        context.checking(new Expectations() {{
            one(businessObjectService).findByPrimaryKey(ParticipantType.class, keyMap); will(returnValue(new ParticipantType()));
        }});

        protocolParticipantService.addProtocolParticipant(protocol, protocolParticipant);

        context.assertIsSatisfied();
        
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
