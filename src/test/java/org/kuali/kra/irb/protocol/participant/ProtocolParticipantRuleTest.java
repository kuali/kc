/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.protocol.participant;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.protocol.participant.AddProtocolParticipantEvent;
import org.kuali.kra.irb.protocol.participant.ParticipantType;
import org.kuali.kra.irb.protocol.participant.ProtocolParticipant;
import org.kuali.kra.irb.protocol.participant.ProtocolParticipantRule;
import org.kuali.kra.irb.test.ProtocolRuleTestBase;
import org.kuali.rice.kns.service.KeyValuesService;
import org.kuali.rice.kns.util.ErrorMessage;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.TypedArrayList;

/**
 * Test the business rules for Protocol Participant.
 * 
 * @author Kuali Research Administration Team (kc.dev@kuali.org)
 */
public class ProtocolParticipantRuleTest extends ProtocolRuleTestBase {

    private static final Integer NEW_PARTICIPANT_COUNT = 5;
    private static final Integer NEW_PARTICIPANT_NEG_COUNT = -5;
    private static final String NEW_PROTOCOL_PARTICIPANT = "participantsHelper.newProtocolParticipant";
    private static final String INVALID_PARTICIPANT_TYPE_CD = "999";
    private ProtocolParticipantRule rule;
    private List<ParticipantType> participantTypes;
    private KeyValuesService keyValuesService;


    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new ProtocolParticipantRule();
        keyValuesService = (KeyValuesService) KraServiceLocator.getService("keyValuesService");
        participantTypes = (List)keyValuesService.findAll(ParticipantType.class);
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        keyValuesService = null;
        participantTypes = null;
        super.tearDown();
    }

    /** 
     * This method ensures that participant types list has members defined.
     * 
     */
    @Test
    public void checkParticipantTypes() {
        assertNotNull(participantTypes);
        assertTrue(participantTypes.size()>0);
    }

    /**
     * Test adding a protocol participant when a count is specified.  This is allowed.
     * 
     * @throws Exception
     */
    @Test
    public void testAddProtocolParticipantWithCount() throws Exception {
        ProtocolParticipant newProtocolParticipant = new ProtocolParticipant();
        newProtocolParticipant.setParticipantType(participantTypes.get(0));
        newProtocolParticipant.setParticipantTypeCode(participantTypes.get(0).getParticipantTypeCode());
        newProtocolParticipant.setParticipantCount(NEW_PARTICIPANT_COUNT);
        assertTrue(rule.processAddProtocolParticipantBusinessRules(
                getAddProtocolParticipantEvent(newProtocolParticipant)));
    }

    /**
     * Test adding a protocol participant when no count is specified.  This is allowed.
     * 
     * @throws Exception
     */
    @Test
    public void testAddProtocolParticipantWithoutCount() throws Exception {
        ProtocolParticipant newProtocolParticipant = new ProtocolParticipant();
        newProtocolParticipant.setParticipantType(participantTypes.get(0));
        newProtocolParticipant.setParticipantTypeCode(participantTypes.get(0).getParticipantTypeCode());
        newProtocolParticipant.setParticipantCount(null);
        assertTrue(rule.processAddProtocolParticipantBusinessRules(
                getAddProtocolParticipantEvent(newProtocolParticipant)));
    }

    /**
     * Test adding a protocol participant when a negative count is specified.  This is not allowed.
     * 
     * @throws Exception
     */
    @Test
    public void testAddProtocolParticipantWithNegCount() throws Exception {
        ProtocolParticipant newProtocolParticipant = new ProtocolParticipant();
        newProtocolParticipant.setParticipantType(participantTypes.get(0));
        newProtocolParticipant.setParticipantTypeCode(participantTypes.get(0).getParticipantTypeCode());
        newProtocolParticipant.setParticipantCount(NEW_PARTICIPANT_NEG_COUNT);
        assertFalse(rule.processAddProtocolParticipantBusinessRules(
                getAddProtocolParticipantEvent(newProtocolParticipant)));
        
        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages(NEW_PROTOCOL_PARTICIPANT + 
                ".participantCount");
        assertTrue(errors.size()== 1);
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_PROTOCOL_PARTICIPANT_COUNT_INVALID);
    }
    
    /**
     * Test adding a duplicate protocol participant.  This is not allowed.
     * 
     * @throws Exception
     */
    @Test
    public void testAddDuplicateProtocolParticipant() throws Exception {
        ProtocolParticipant newProtocolParticipant = new ProtocolParticipant();
        newProtocolParticipant.setParticipantType(participantTypes.get(0));
        newProtocolParticipant.setParticipantTypeCode(participantTypes.get(0).getParticipantTypeCode());
        newProtocolParticipant.setParticipantCount(NEW_PARTICIPANT_COUNT);

        ProtocolDocument document = getNewProtocolDocument();
        document.getProtocol().getProtocolParticipants().add(newProtocolParticipant);

        newProtocolParticipant.setParticipantType(participantTypes.get(0));
        newProtocolParticipant.setParticipantTypeCode(participantTypes.get(0).getParticipantTypeCode());
        newProtocolParticipant.setParticipantCount(null);

        
        AddProtocolParticipantEvent event = new AddProtocolParticipantEvent(Constants.EMPTY_STRING, 
                document, newProtocolParticipant);
        assertFalse(rule.processAddProtocolParticipantBusinessRules(event));

        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages(NEW_PROTOCOL_PARTICIPANT + 
                ".participantTypeCode");
        assertTrue(errors.size()== 1);
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_PROTOCOL_PARTICIPANT_TYPE_DUPLICATE);
    }

    /**
     * Test adding a participant with an unspecified participant type.  This is not allowed.
     */
    @Test
    public void testAddUnspecifiedProtocolParticipant() throws Exception {
      ProtocolParticipant newProtocolParticipant = new ProtocolParticipant();
      ParticipantType participantType = new ParticipantType();
      newProtocolParticipant.setParticipantType(participantType);
      newProtocolParticipant.setParticipantTypeCode("");
      assertFalse(rule.processAddProtocolParticipantBusinessRules(
              getAddProtocolParticipantEvent(newProtocolParticipant)));
      
      TypedArrayList errors = GlobalVariables.getErrorMap().getMessages(NEW_PROTOCOL_PARTICIPANT + 
              ".participantTypeCode");
      assertTrue(errors.size()== 1);
      ErrorMessage message = (ErrorMessage) errors.get(0);
      assertEquals(message.getErrorKey(), KeyConstants.ERROR_PROTOCOL_PARTICIPANT_TYPE_NOT_SELECTED);
    }

    /**
     * Test adding an invalid participant type.  This is not allowed.
     */
    @Test
    public void testAddInvalidProtocolParticipant() throws Exception {
      ProtocolParticipant newProtocolParticipant = new ProtocolParticipant();
      ParticipantType participantType = new ParticipantType();
      participantType.setParticipantTypeCode(INVALID_PARTICIPANT_TYPE_CD);
      newProtocolParticipant.setParticipantType(participantType);
      newProtocolParticipant.setParticipantTypeCode(INVALID_PARTICIPANT_TYPE_CD);
      assertFalse(rule.processAddProtocolParticipantBusinessRules(
              getAddProtocolParticipantEvent(newProtocolParticipant)));
      
      TypedArrayList errors = GlobalVariables.getErrorMap().getMessages(NEW_PROTOCOL_PARTICIPANT +
              ".participantTypeCode");
      assertTrue(errors.size()== 1);
      ErrorMessage message = (ErrorMessage) errors.get(0);
      assertEquals(message.getErrorKey(), KeyConstants.ERROR_PROTOCOL_PARTICIPANT_TYPE_INVALID);
    }

    /*
     * This method is to get add protocol participant event
     * @param newProtocolParticipant
     * @return event
     * @throws Exception
     */
    private AddProtocolParticipantEvent getAddProtocolParticipantEvent(ProtocolParticipant 
            newProtocolParticipant) throws Exception {
        ProtocolDocument document = getNewProtocolDocument();
        AddProtocolParticipantEvent event = new AddProtocolParticipantEvent(Constants.EMPTY_STRING, document,
                newProtocolParticipant);
        return event;
    }
}
