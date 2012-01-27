/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.test.ProtocolRuleTestBase;
import org.kuali.kra.rules.TemplateRuleTest;
import org.kuali.rice.kns.datadictionary.validation.charlevel.NumericValidationPattern;

/**
 * Test the business rules for Protocol Participant.
 * 
 * @author Kuali Research Administration Team (kc.dev@kuali.org)
 */
public class ProtocolParticipantRuleTest extends ProtocolRuleTestBase {

    private static final String CHILDREN_PARTICIPANT_TYPE_CD = "1";
    private static final String OTHER_PARTICIPANT_TYPE_CD = "10";
    private static final Integer NEW_PARTICIPANT_COUNT = 5;
    private static final Integer NEW_PARTICIPANT_NEG_COUNT = -5;
    
    private static final String NEW_PROTOCOL_PARTICIPANT_FIELD = "protocolHelper.newProtocolParticipant";
    private static final String DOT = ".";
    private static final String PARTICIPANT_TYPE_CODE_FIELD = "participantTypeCode";
    private static final String PARTICIPANT_COUNT_FIELD = "participantCount";

    /**
     * Test adding a protocol participant when a count is specified.  This is allowed.
     * 
     * @throws Exception
     */
    @Test
    public void testAddProtocolParticipantWithCount() throws Exception {
        new TemplateRuleTest<AddProtocolParticipantEvent, AddProtocolParticipantRule> (){            
            @Override
            protected void prerequisite() {
                event = new AddProtocolParticipantEvent(null, getProtocolParticipant(OTHER_PARTICIPANT_TYPE_CD, NEW_PARTICIPANT_COUNT), 
                        getProtocolParticipants());
                rule = new AddProtocolParticipantRule();
                expectedReturnValue = true;
            }
        };
    }

    /**
     * Test adding a protocol participant when no count is specified.  This is allowed.
     * 
     * @throws Exception
     */
    @Test
    public void testAddProtocolParticipantWithoutCount() throws Exception {
        new TemplateRuleTest<AddProtocolParticipantEvent, AddProtocolParticipantRule> (){            
            @Override
            protected void prerequisite() {
                event = new AddProtocolParticipantEvent(null, getProtocolParticipant(OTHER_PARTICIPANT_TYPE_CD, null), getProtocolParticipants());
                rule = new AddProtocolParticipantRule();
                expectedReturnValue = true;
            }
        };
    }

    /**
     * Test adding a protocol participant when a negative count is specified.  This is not allowed.
     * 
     * @throws Exception
     */
    /* Test has been marked with ignore flag due to KCINFR-445 
     * Once the related Rice issue is resolved, this can be included back */
    @Ignore
    @Test
    public void testAddProtocolParticipantWithNegCount() throws Exception {
        new TemplateRuleTest<AddProtocolParticipantEvent, AddProtocolParticipantRule> (){            
            @Override
            protected void prerequisite() {
                event = new AddProtocolParticipantEvent(null, getProtocolParticipant(CHILDREN_PARTICIPANT_TYPE_CD, NEW_PARTICIPANT_NEG_COUNT), 
                        getProtocolParticipants());
                rule = new AddProtocolParticipantRule();
                expectedReturnValue = false;
            }

            @Override
            public void checkRuleAssertions() {
                assertError(PARTICIPANT_COUNT_FIELD, "error.format" + DOT + NumericValidationPattern.class.getName());
            }
        };
    }
    
    /**
     * Test adding a duplicate protocol participant.  This is not allowed.
     * 
     * @throws Exception
     */
    @Test
    public void testAddDuplicateProtocolParticipant() throws Exception {
        new TemplateRuleTest<AddProtocolParticipantEvent, AddProtocolParticipantRule> (){            
            @Override
            protected void prerequisite() {
                event = new AddProtocolParticipantEvent(null, getProtocolParticipant(CHILDREN_PARTICIPANT_TYPE_CD, NEW_PARTICIPANT_COUNT), 
                        getProtocolParticipants());
                rule = new AddProtocolParticipantRule();
                expectedReturnValue = false;
            }

            @Override
            public void checkRuleAssertions() {
                assertError(NEW_PROTOCOL_PARTICIPANT_FIELD + DOT + PARTICIPANT_TYPE_CODE_FIELD, KeyConstants.ERROR_PROTOCOL_PARTICIPANT_TYPE_DUPLICATE);
            }
        };
    }

    /**
     * Test adding a participant with an unspecified participant type.  This is not allowed.
     */
    /* Test has been marked with ignore flag due to KCINFR-445 
     * Once the related Rice issue is resolved, this can be included back */
    @Ignore
    @Test
    public void testAddUnspecifiedProtocolParticipant() throws Exception {
        new TemplateRuleTest<AddProtocolParticipantEvent, AddProtocolParticipantRule> (){            
            @Override
            protected void prerequisite() {
                event = new AddProtocolParticipantEvent(null, getProtocolParticipant(Constants.EMPTY_STRING, NEW_PARTICIPANT_COUNT), getProtocolParticipants());
                rule = new AddProtocolParticipantRule();
                expectedReturnValue = false;
            }

            @Override
            public void checkRuleAssertions() {
                assertError(PARTICIPANT_TYPE_CODE_FIELD, KeyConstants.ERROR_REQUIRED);
            }
        };
    }

    private ProtocolParticipant getProtocolParticipant(String participantTypeCode, Integer participantCount) {
        ProtocolParticipant participant = new ProtocolParticipant();
        participant.setParticipantTypeCode(participantTypeCode);
        participant.setParticipantCount(participantCount);
        return participant;
    }
    
    private List<ProtocolParticipant> getProtocolParticipants(){
        List<ProtocolParticipant> participants = new ArrayList<ProtocolParticipant>();
        ProtocolParticipant participant = getProtocolParticipant(CHILDREN_PARTICIPANT_TYPE_CD, NEW_PARTICIPANT_COUNT);
        participants.add(participant);
        return participants;
    }
    
}