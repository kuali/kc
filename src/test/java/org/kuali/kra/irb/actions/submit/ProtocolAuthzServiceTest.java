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
package org.kuali.kra.irb.actions.submit;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.auth.ProtocolAuthorizationService;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.service.UnitAuthorizationService;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.util.GlobalVariables;

public class ProtocolAuthzServiceTest extends ProtocolActionServiceTestBase {
    
    private static final String MODIFY_ANY_PROTOCOL = "MODIFY_ANY_PROTOCOL";
    
    private static final String SUBMIT_PROTOCOL = "SUBMIT_PROTOCOL";
    
    private static final String PERFORM_IRB_ACTIONS_ON_PROTO = "PERFORM_IRB_ACTIONS_ON_PROTO";
    
    private static final String DEFAULT_ORGANIZATION_UNIT = "000001";
    
    private Mockery context;
    
    private Protocol protocol;
    
    private ProtocolActionServiceImpl protocolActionService;
    
    private UnitAuthorizationService unitAuthorizationService;
    
    private ProtocolAuthorizationService protocolAuthorizationService;
    
    @Before
    public void setUp() {
        context  = new JUnit4Mockery() {{
            setImposteriser(ClassImposteriser.INSTANCE);
        }};
        protocol = getProtocol(context);
        protocolActionService = new ProtocolActionServiceImpl();
        
        unitAuthorizationService = context.mock(UnitAuthorizationService.class);
        protocolActionService.setUnitAuthorizationService(unitAuthorizationService);
        
        protocolAuthorizationService = context.mock(ProtocolAuthorizationService.class);
        protocolActionService.setProtocolAuthorizationService(protocolAuthorizationService);
        
        protocol.setProtocolNumber("001Z");        
    }  
    
    @Test
    public void permissionLeadUnitTest() {
        protocol.setLeadUnitNumber("0001");
        mockSession();
        
        context.checking(new Expectations() {{
            allowing(unitAuthorizationService).hasPermission(null, "0001", MODIFY_ANY_PROTOCOL);will(returnValue(true));
        }});
        assertTrue(protocolActionService.isActionAllowed("105", protocol));
        assertTrue(protocolActionService.isActionAllowed("106", protocol));
        assertTrue(protocolActionService.isActionAllowed("108", protocol));
        assertTrue(protocolActionService.isActionAllowed("114", protocol));
        assertTrue(protocolActionService.isActionAllowed("115", protocol));
        assertTrue(protocolActionService.isActionAllowed("116", protocol));
        assertTrue(protocolActionService.isActionAllowed("303", protocol));
    }
    
    @Test
    public void permissionToSubmitTest() {
        protocol.setLeadUnitNumber("0001");
        mockSession();
        
        context.checking(new Expectations() {{
            allowing(unitAuthorizationService).hasPermission(null, "0001", MODIFY_ANY_PROTOCOL);will(returnValue(false));
        }});
        
        context.checking(new Expectations() {{
            allowing(protocolAuthorizationService).hasPermission(null, protocol, SUBMIT_PROTOCOL);will(returnValue(true));
        }});

        assertTrue(protocolActionService.isActionAllowed("105", protocol));
        assertTrue(protocolActionService.isActionAllowed("106", protocol));
        assertTrue(protocolActionService.isActionAllowed("108", protocol));
        assertTrue(protocolActionService.isActionAllowed("114", protocol));
        assertTrue(protocolActionService.isActionAllowed("115", protocol));
        assertTrue(protocolActionService.isActionAllowed("116", protocol));
        assertTrue(protocolActionService.isActionAllowed("303", protocol));
        assertTrue(protocolActionService.isActionAllowed("101", protocol));
    }
    
    @Test
    public void permissionAsCommitteeMemberWithScheduleIdTest() {
        protocol.setLeadUnitNumber("0001");
        mockSession();
        
        ProtocolSubmission ps = new ProtocolSubmission();
        ps.setScheduleId("101");
        ps.setCommitteeId(null);
        protocol.setProtocolSubmission(ps);
        
        context.checking(new Expectations() {{
            allowing(unitAuthorizationService).hasPermission(null, "0001", MODIFY_ANY_PROTOCOL);will(returnValue(false));
        }});
        
        context.checking(new Expectations() {{
            allowing(protocolAuthorizationService).hasPermission(null, protocol, SUBMIT_PROTOCOL);will(returnValue(false));
        }});
        
        context.checking(new Expectations() {{
            allowing(unitAuthorizationService).hasPermission(null, "0001", PERFORM_IRB_ACTIONS_ON_PROTO);will(returnValue(true));
        }});
        
        assertTrue(protocolActionService.isActionAllowed("303", protocol));
        assertTrue(protocolActionService.isActionAllowed("207", protocol));
        assertTrue(protocolActionService.isActionAllowed("301", protocol));
        assertTrue(protocolActionService.isActionAllowed("300", protocol));
        assertTrue(protocolActionService.isActionAllowed("302", protocol));
        assertTrue(protocolActionService.isActionAllowed("305", protocol));
        assertTrue(protocolActionService.isActionAllowed("306", protocol));
        assertTrue(protocolActionService.isActionAllowed("205", protocol));
        assertTrue(protocolActionService.isActionAllowed("206", protocol));
        assertTrue(protocolActionService.isActionAllowed("109", protocol));
        assertTrue(protocolActionService.isActionAllowed("201", protocol));
        assertTrue(protocolActionService.isActionAllowed("200", protocol));
        assertTrue(protocolActionService.isActionAllowed("210", protocol));
        assertTrue(protocolActionService.isActionAllowed("208", protocol));
        assertTrue(protocolActionService.isActionAllowed("209", protocol));
        assertTrue(protocolActionService.isActionAllowed("202", protocol));
        assertTrue(protocolActionService.isActionAllowed("203", protocol));
        assertTrue(protocolActionService.isActionAllowed("304", protocol));
        assertTrue(protocolActionService.isActionAllowed("204", protocol));
        assertTrue(protocolActionService.isActionAllowed("211", protocol));
        assertTrue(protocolActionService.isActionAllowed("212", protocol));
    }    
    
    @Test
    public void permissionAsCommitteeMemberWithCommitteeIdTest() {
        protocol.setLeadUnitNumber("0001");
        mockSession();
        
        ProtocolSubmission ps = new ProtocolSubmission();
        ps.setScheduleId(null);
        ps.setCommitteeId("101");
        protocol.setProtocolSubmission(ps);
        
        context.checking(new Expectations() {{
            allowing(unitAuthorizationService).hasPermission(null, "0001", MODIFY_ANY_PROTOCOL);will(returnValue(false));
        }});
        
        context.checking(new Expectations() {{
            allowing(protocolAuthorizationService).hasPermission(null, protocol, SUBMIT_PROTOCOL);will(returnValue(false));
        }});
        
        context.checking(new Expectations() {{
            allowing(unitAuthorizationService).hasPermission(null, "0001", PERFORM_IRB_ACTIONS_ON_PROTO);will(returnValue(true));
        }});
        
        assertTrue(protocolActionService.isActionAllowed("303", protocol));
        assertTrue(protocolActionService.isActionAllowed("303", protocol));
        assertTrue(protocolActionService.isActionAllowed("207", protocol));
        assertTrue(protocolActionService.isActionAllowed("301", protocol));
        assertTrue(protocolActionService.isActionAllowed("300", protocol));
        assertTrue(protocolActionService.isActionAllowed("302", protocol));
        assertTrue(protocolActionService.isActionAllowed("305", protocol));
        assertTrue(protocolActionService.isActionAllowed("306", protocol));
        assertTrue(protocolActionService.isActionAllowed("205", protocol));
        assertTrue(protocolActionService.isActionAllowed("206", protocol));
        assertTrue(protocolActionService.isActionAllowed("109", protocol));
        assertTrue(protocolActionService.isActionAllowed("201", protocol));
        assertTrue(protocolActionService.isActionAllowed("200", protocol));
        assertTrue(protocolActionService.isActionAllowed("210", protocol));
        assertTrue(protocolActionService.isActionAllowed("208", protocol));
        assertTrue(protocolActionService.isActionAllowed("209", protocol));
        assertTrue(protocolActionService.isActionAllowed("202", protocol));
        assertTrue(protocolActionService.isActionAllowed("203", protocol));
        assertTrue(protocolActionService.isActionAllowed("304", protocol));
        assertTrue(protocolActionService.isActionAllowed("204", protocol));
        assertTrue(protocolActionService.isActionAllowed("211", protocol));
        assertTrue(protocolActionService.isActionAllowed("212", protocol));
    }     
    
    @Test
    public void permissionSpecialCaseTest() {
        protocol.setLeadUnitNumber("0001");
        mockSession();
        
        ProtocolSubmission ps = new ProtocolSubmission();
        ps.setScheduleId("101");
        ps.setCommitteeId(null);
        protocol.setProtocolSubmission(ps);
        
        context.checking(new Expectations() {{
            allowing(unitAuthorizationService).hasPermission(null, "0001", MODIFY_ANY_PROTOCOL);will(returnValue(false));
        }});
        
        context.checking(new Expectations() {{
            allowing(protocolAuthorizationService).hasPermission(null, protocol, SUBMIT_PROTOCOL);will(returnValue(false));
        }});
        
        context.checking(new Expectations() {{
            allowing(unitAuthorizationService).hasPermission(null, "0001", PERFORM_IRB_ACTIONS_ON_PROTO);will(returnValue(false));
        }});
        
        context.checking(new Expectations() {{
            allowing(unitAuthorizationService).hasPermission(null, DEFAULT_ORGANIZATION_UNIT, PERFORM_IRB_ACTIONS_ON_PROTO);will(returnValue(true));
        }});
        
        assertTrue(protocolActionService.isActionAllowed("202", protocol));
        assertTrue(protocolActionService.isActionAllowed("203", protocol));
        assertTrue(protocolActionService.isActionAllowed("204", protocol));
        assertTrue(protocolActionService.isActionAllowed("304", protocol));
    } 
    
    private void mockSession() {
        final UniversalUser person = new UniversalUser();
        person.setPersonUniversalIdentifier("kpatel");
        final UserSession session = context.mock(UserSession.class); 
        
        GlobalVariables.setUserSession(session);
        
        context.checking(new Expectations() {{
            allowing(session).getPerson();will(returnValue(person));
        }});
    }
}
